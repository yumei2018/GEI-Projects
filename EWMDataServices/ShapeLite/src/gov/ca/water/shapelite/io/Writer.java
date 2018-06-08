/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileHeader;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Writer {
    //<editor-fold defaultstate="collapsed" desc="Fields">

    /**
     * The long index offset of the current byte location where reading is
     * occurring.
     */
    private long position;

    /**
     * The stream with the raw byte values.
     */
    private final BufferedOutputStream stream;
    private final ByteArrayOutputStream byteData;
    private final byte[] memorySource; 
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new reader using the specified filename. The bytes will be read
     * from the file.
     *
     * @param filename The string filename to read from.
     * @throws FileNotFoundException Occurs if the file cannot be found.
     */
    public Writer(String filename) throws FileNotFoundException {
        stream = new BufferedOutputStream(new FileOutputStream(filename), 10000 * 1024);
        position = 0;
        byteData = null;
        memorySource = null;
    }

    /**
     * Creates a new reader using the specified input stream. The bytes will be
     * read directly from the stream.
     *
     * @param source The stream to read values from.
     */
    public Writer(OutputStream source) {
        stream = new BufferedOutputStream(source, 10000 * 1024);
        position = 0;
        byteData = null;
        memorySource = null;

    }

    /**
     * Creates a new reader from an array of bytes. This allows an in memory
     * collection of bytes to be read using the same logic as a file.
     *
     * @param memorysource
     */
    public Writer(byte[] memorysource) {
        this.byteData = new ByteArrayOutputStream();
        this.stream = new BufferedOutputStream(byteData, memorysource.length);
        this.position = 0;
        this.memorySource = memorysource;
    }
    
    

    
    
    

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Closes the stream.
     */
    public void close() {
        try {
            stream.close();
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes a value from 0 to 256 as a byte value.  Though the input is an 
     * integer value, it attempts to convert this value to a byte before
     * writing it to a file.
     * @param data The integer value to write as a byte.
     */
    public void writeByte(int data){
        write(new byte[]{(byte)data});
    }
    
    /**
     * Reads the specified number of bytes from the current position and returns
     * them as a byte array..
     *
     * @param data The array of bytes read.
     */
    public void write(byte[] data) {
        try {
            stream.write(data);
            position += data.length;
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Force the memory buffer to actually be written to the file.
     * If a memory stream was used, this will attempt to copy the values
     * from the ByteArrayBuffer to the byte array used to initialize this 
     * writer.
     * @throws IOException 
     */
    public void flush() throws IOException{
        stream.flush();
        if(memorySource != null){
            byte[] result = byteData.toByteArray();
            System.arraycopy(result, 0, memorySource, 0, (int)position);
        }
    }
    
    /**
     * Reads an integer in big Endian order. This is the default read order for
     * the ByteBuffer in java regardless of platform, so the byte order does not
     * have to be reversed.
     *
     * @param value the integer value read from the next 4 byte in big Endian order.
     */
    public void writeBigInt(int value) {
        byte[] b = ByteBuffer.allocate(4).putInt(value).array();
        write(b);
    }
    
    
    
    /**
     * ShapefileHeader header
     * @param header The ShapefileHeader to write.
     * @param fileLength The integer length to write as the file length.
     * This is different between shx and shp headers. 
     */
    public void write(ShapefileHeader header, int fileLength){
        writeBigInt(header.getFilecode());
        writeBlank(20);
        writeBigInt(fileLength);
        write(header.getVersion());
        write(header.getShapeType().getValue());
        write(header.getBounds().toArray());
    }
    
    
    
    /**
     * This only writes the XY boundaries, and does not write M or Z boundaries.
     * @param envelope 
     */
    public void writeBox(Envelope envelope){
        write(envelope.getMin().getX());
        write(envelope.getMin().getY());
        write(envelope.getMax().getX());
        write(envelope.getMax().getY());
    }
    
    
    
    
    /**
     * Writes the shapetype.  
     * @param value 
     */
    public void write(ShapeType value){
        write(value.getValue());
    }

    /**
     * Reads the double value from the next 8 bytes and advances the position 8
     * bytes. Since the java reader always works as big-endian, this reverses it
     * so that the bytes are in little Endian order.
     *
     * @param value The double to write to the stream.
     */
    public void write(double value) {
        ByteBuffer b = ByteBuffer.allocate(8);
        b.putDouble(value);
        byte[] data = b.array();
        reverse(data);
        write(data);
    }

    /**
     * Reads the specified number of double values into an array.
     *
     * @param values The array of doubles to write.
     */
    public void write(double[] values) {

        for (int i = 0; i < values.length; i++) {
            write(values[i]);
        }

    }

    /**
     * Writes a single integer value to the file.
     * 4 bytes.
     *
     * @param value The integer read.
     */
    public void write(int value) {
        byte[] b = ByteBuffer.allocate(4).putInt(value).array();
        reverse(b);
        write(b);
    }
    
    /**
     * Writes an integer to a file as a 16 bit short integer.
     * @param value 
     */
    public void writeShort(int value){
        byte[] b = ByteBuffer.allocate(2).putShort((short)value).array();
        reverse(b);
        write(b);
    }

    /**
     * Reads the next 4*count bytes as an array of integers.
     *
     * @param values The array of integer values to write to the file.
     */
    public void write(int[] values) {
        for(int i = 0; i < values.length; i++){
            write(values[i]);
        }
    }

    /**
     * Writes numChars of characters.  If text is shorter than numChars,
     * the remaining characters are filled with 0.
     *
     * @param text the text content to write.
     * @param numChars the integer count of the number of bytes in the string to
     * read.
     */
    public void writeBlank(String text, int numChars) {
        byte[] buffer = new byte[numChars];
        byte[] content = text.getBytes(Charset.forName("UTF-8"));
        int len = Math.min(buffer.length, content.length);
        System.arraycopy(content, 0, buffer, 0, len);
        write(buffer);
        
    }
    
    /**
     * Writes numChars of characters.  If text is shorter than numChars,
     * the remaining characters are filled with spaces.
     * @param text
     * @param numChars 
     */
    public void writeSpace(String text, int numChars) {
        if(text == null || text.isEmpty()){
            writeBlank(numChars);
            return;
        }
        byte[] buffer = new byte[numChars];
        for(int i = 0;i < numChars; i++){
            buffer[i] = 32;
        }
        byte[] content = text.getBytes(Charset.forName("UTF-8"));
        int len = Math.min(buffer.length, content.length);
        System.arraycopy(content, 0, buffer, 0, len);
        write(buffer);
        
    }
    
    /**
     * Writes the specified number of empty bytes.
     * @param count 
     */
    public void writeBlank(int count){
        byte[] content = new byte[count];
        write(content);
    }
    /**
     * Writes the specified number of empty bytes.
     * @param count 
     */
    public void writeSpace(int count){
        byte[] content = new byte[count];
        for(int i = 0;i < count; i++){
            content[i] = 32;
        }
        write(content);
    }

    /**
     * Reverses the bytes in the array.
     *
     * @param array The array of values to reverse.
     */
    public static void reverse(byte[] array) {
        int l = array.length;
        for (int i = 0; i < l / 2; i++) {
            byte t = array[i];
            array[i] = array[l - i - 1];
            array[l - i - 1] = t;
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * The current long read position in the stream.
     *
     * @return the position
     */
    public long getPosition() {
        return position;
    }

    //</editor-fold>
}
