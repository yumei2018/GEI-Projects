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
package gov.ca.water.shapelite.trace;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * This method is used to read all the bytes from an input stream at once.
 *
 * @author hdunsford
 */
public class ReadBytes {

    /**
     * Reads all the bytes from an input stream.
     *
     * @param stream The stream to read.
     * @return An array of bytes.
     * @throws IOException Occurs if there is an error reading the bytes from the stream.
     */
    public static byte[] readAll(InputStream stream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    /**
     * This readAll method has been optimized to read directly from a file and reads
     * the bytes in a more compact fashion, than what happens when reading an
     * input stream.
     * @param fileName
     * @return
     * @throws IOException 
     */
    public static byte[] readAll(String fileName) throws IOException {
        int bufferSize = 1024 * 100;
        byte[] result;
        try (FileInputStream fileStrm = new FileInputStream(fileName)) {
            FileChannel fileChannel = fileStrm.getChannel();
            Long fileSize = fileChannel.size();
            result = new byte[fileSize.intValue()];
            MappedByteBuffer mapBuffer
                    = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, fileSize.longValue());
            int numGet;
            int position = 0;
            while (mapBuffer.hasRemaining()) {
                numGet = Math.min(mapBuffer.remaining(), bufferSize);
                mapBuffer.get(result, position, numGet);
                position += numGet;
            }
        }
        return result;

    }
}
