package gov.ca.water.shapelite.delegate;

/**
 *
 * @author clay
 */
public class Delegate {
  //<editor-fold defaultstate="expanded" desc="Private Properties">
  private Object mListener;
  
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mListener = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public Delegate(Object listener){
    this.setListener(listener);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Set Listener">
  public final <D extends Delegate> D setListener(Object listener) {
    this.mListener = listener;
    return (D) this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Get Listener">
  public final <O extends Object> O getListener() {
    O result = null;
    
    try {
      result = (O) this.mListener;
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
  
    return result;
  }
  //</editor-fold>
}
