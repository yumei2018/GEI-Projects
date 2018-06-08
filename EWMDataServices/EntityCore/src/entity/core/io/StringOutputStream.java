package entity.core.io;

import java.io.OutputStream;
import java.util.Objects;

/**
 * @author Mohamed Mansour
 */
public class StringOutputStream extends OutputStream {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  private StringBuffer mBuffer;
  private Boolean mManualFlush;

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mBuffer = null;
    this.mManualFlush = null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  public StringOutputStream() {
    this(false);
  }
  public StringOutputStream(boolean manualFlush) {
    this.mBuffer = new StringBuffer();
    this.mManualFlush = manualFlush;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Output Stream Implementations">
  public void close() {}
  
  public void flush() {
    if (!this.isManualFlush()) {
      this.mBuffer.delete(0, this.mBuffer.length());
    }
  }
  
  public void write(byte[] b) {
    String str = new String(b);
    this.mBuffer.append(str);
  }
  
  public void write(byte[] b, int off, int len) {
    String str = new String(b, off, len);
    this.mBuffer.append(str);
  }
  
  public void write(int b) {
    String str = Integer.toString(b);
    this.mBuffer.append(str);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="To String Override">
  public String toString() {
    return this.mBuffer.toString();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Is Manual Flush">
  public boolean isManualFlush() {
    return Objects.equals(this.mManualFlush, true);
  }
  //</editor-fold>
}