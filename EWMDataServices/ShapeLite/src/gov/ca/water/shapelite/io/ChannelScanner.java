/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class works just like the reader, but it does not necessarily read the whole file.
 * This enables reading just the header, for instance, or skipping to just a particular
 * portion of the file and reading that into memory.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ChannelScanner {

  //<editor-fold defaultstate="collapsed" desc="Fields">
    FileChannel fileChannel;
    RandomAccessFile randomAccessFile;
    MappedByteBuffer buffer;
    File file;

  //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new reader using the specified filename. The bytes will be read from the
     * file.
     *
     * @param filename The string filename to read from.
     * @throws FileNotFoundException Occurs if the file cannot be found.
     */
    public ChannelScanner(String filename) throws FileNotFoundException, IOException {
        
        file = new File(filename);
        randomAccessFile = new RandomAccessFile(file, "r");
        fileChannel = randomAccessFile.getChannel();
        buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        buffer.load();

    }
    
    
    /**
     * This opens the file as a random access file, and maps the specified length
     * of bytes into memory starting from the specified offset.  This should only 
     * be called if the cahnnel scanner has been closed.
     * @param offset
     * @param length The length in bytes to read.
     * @throws IOException 
     */
    public void map(long offset, long length) throws IOException{
        randomAccessFile = new RandomAccessFile(file, "r");
        fileChannel = randomAccessFile.getChannel();
        buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, offset, length);
        buffer.load();
    }

   //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Advances the buffer by the specified offset without actually reading anything.
     *
     * @param offset the integer number of byte values to skip.
     */
    public void advance(int offset) {
        buffer.position(buffer.position() + offset);
    }

    /**
     * Moves the current position to the specified offset.
     *
     * @param offset The read position to move to in the stream.
     */
    public void advanceTo(long offset) {
        buffer.position((int)offset);
    }

    /**
     * Closes the stream.
     */
    public void close() {
        try {
            
            fileChannel.close();
        } catch (IOException ex) {
            Logger.getLogger(ChannelScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reads the specified number of bytes from the current position and returns them as a
     * byte array.
     *
     * @param count The number of bytes to read.
     * @return The array of bytes read.
     */
    public byte[] read(int count) {
        byte[] result = new byte[count];
        buffer.get(result);
        return result;
    }

    /**
     * Reads an integer in big Endian order. This is the default read order for the
     * ByteBuffer in java regardless of platform, so the byte order does not have to be
     * reversed.
     *
     * @return the integer value read from the next 4 byte in big Endian order.
     */
    public int readBigInt()  {
        buffer.order(ByteOrder.BIG_ENDIAN);
        return buffer.getInt();
    }

    /**
     * Java uses a byte range from -128 to 127 instead of 0 to 255. So if the full range
     * is needed, we need to upcast to int. If -1 is returned, then reading was not
     * successful.
     *
     * @return the int read from the data.
     * @throws java.io.IOException
     */
    public int readByte() throws IOException {
        byte result = buffer.get();
        if (result < 0) {
            result += 256;
        }
        return result;
    }

    /**
     * Reads the double value from the next 8 bytes and advances the position 8 bytes.
     * Since the java reader always works as big-endian, this reverses it so that the
     * bytes are in little Endian order.
     *
     * @return The double created from the next 8 bytes in the stream.
     */
    public double readDouble() {
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getDouble();
    }

    /**
     * Reads the specified number of double values into an array.
     *
     * @param count The number of doubles to read.
     * @return 
     */
    public double[] readDouble(int count) {
        double[] result = new double[count];
        for(int i = 0; i < count; i++){
            result[i] = buffer.getDouble();
        }
        return result;
    }

    /**
     * Reads the next 4 bytes into an integer and advances the read position by 4 bytes.
     *
     * @return The integer read.
     */
    public int readInt() {
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getInt();
    }

    /**
     * Reads the next 4*count bytes as an array of integers.
     *
     * @param count The number of integers to read.
     * @return The array of int.
     */
    public int[] readInt(int count) {
        int[] result = new int[count];
        for(int i = 0; i < count; i++){
            result[i] = buffer.getInt();
        }
        return result;
    }

    /**
     * Reads the next short value from the next 2 bytes, and advances the position 2
     * bytes.
     *
     * @return The short value read from the stream.
     * @throws java.io.IOException
     */
    public short readShort() throws IOException {
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getShort();
    }

    /**
     * Reads a string with a specific length specified by count.
     *
     * @param count the integer count of the number of bytes in the string to read.
     * @return A String read from the next number of bytes specified by count.
     * @throws java.io.IOException
     */
    public String readString(int count) throws IOException {
        
        
        char[] stringBuffer = new char[count];
        for(int i = 0; i< count; i++){
            stringBuffer[i] = buffer.getChar();
        }
        String result = new String(stringBuffer);
        int nullPoint = result.indexOf((char) 0);
        if (nullPoint != -1) {
            result = result.substring(0, nullPoint);
        }
        return result.trim();
    }

   

   //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    

  //</editor-fold>
}
