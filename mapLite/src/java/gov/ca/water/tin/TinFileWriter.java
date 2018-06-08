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
package gov.ca.water.tin;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import gov.ca.water.shapelite.Coord;

/**
 * The writer can store either a single triangle group or a whole network,
 * consisting of several groups.
 * @author hdunsford
 */
public class TinFileWriter {

    private static final Logger logger = Logger.getLogger(TinFileWriter.class.getName());

    // The format of the vertex values in the output file.
    private DecimalFormat outputFormat = new DecimalFormat("#.000");

    private BufferedWriter bufferedWriter;

    /**
     * Writes the specified triangle network to the specified file.
     * @param outputFile
     * @param network
     * @throws FileNotFoundException
     * @throws InvalidFormatException 
     * @throws SecurityException - if the file exists and can't be deleted
     */
    public void write(String outputFile, TriangleNetwork network) throws FileNotFoundException, InvalidFormatException, SecurityException{
        File f = new File(outputFile);
        if(f.exists()){
            f.delete();
        }
        if(!f.getParentFile().exists()){
            f.getParentFile().mkdirs();
        }
        OutputStream stream = new FileOutputStream(f);
        write(stream, network);
    }
    
    /**
     * Writes the vertices and triangle indices to the stream, which can be
     * a file stream or other output stream.
     * @param stream
     * @param network
     * @throws InvalidFormatException 
     */
    public void write(OutputStream stream, TriangleNetwork network) throws InvalidFormatException {
        int iGroup = 0;
        for (TriangleGroup group : network.getGroups()) {

            if (group.getTriangles() == null || group.getTriangles().isEmpty()) {
                throw new InvalidFormatException("Group " + iGroup + " had "
                        + "no triangles.  Please define triangles before "
                        + "attempting to save the TIN.");
            }

            if (group.getVertices() == null || group.getVertices().isEmpty()) {
                throw new InvalidFormatException("Group " + iGroup + " had"
                        + "triangles but no vertices.  Please"
                        + " call verticesFromTriangles before attempting to "
                        + "save the TIN");
            }
        }
        OutputStreamWriter writer = new OutputStreamWriter(stream);
        bufferedWriter = new BufferedWriter(writer);
        try {
            bufferedWriter.write("TIN\n");
            for (TriangleGroup group : network.getGroups()) {
                doWrite(group);
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Could not close writer.", ex);
            }
        }
    }

    public void write(OutputStream stream, TriangleGroup group) 
                                                          throws InvalidFormatException {
        if (group.getTriangles() == null || group.getTriangles().isEmpty()) {
            throw new InvalidFormatException("The group had "
                    + "no triangles.  Please define triangles before "
                    + "attempting to save the TIN.");
        }

        if (group.getVertices() == null || group.getVertices().isEmpty()) {
            throw new InvalidFormatException("The group had"
                    + "triangles but no vertices.  Please"
                    + " call verticesFromTriangles before attempting to "
                    + "save the TIN");
        }
        
        OutputStreamWriter writer = new OutputStreamWriter(stream);
        bufferedWriter = new BufferedWriter(writer);
        try {
            bufferedWriter.write("TIN\n");
            doWrite(group);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Could not close writer.", ex);
            }
        }
    }

    private void doWrite(TriangleGroup group) throws IOException {
        bufferedWriter.write("BEGT\n");
        bufferedWriter.write(group.getName() + "\n");
        Color col = group.getColor();
        if (col != null) {
            bufferedWriter.write("COL " + col.getRed() + " " + col.getGreen() + " " + col.getBlue() + "\n");
        }
        bufferedWriter.write("VERT " + group.getVertexCount() + "\n");

        for (Coord vert : group.getVertices()) {
            int locked = (int) vert.M;
            bufferedWriter.write(outputFormat.format(vert.X) + " "
                    + outputFormat.format(vert.Y) + " "
                    + outputFormat.format(vert.Z) + " "
                    + locked + "\n");
        }

        bufferedWriter.write("TRI " + group.getTriangleCount() + "\n");
        for (Triangle triangle : group.getTriangles()) {
            bufferedWriter.write(triangle.iA + " " + triangle.iB + " " + triangle.iC);
        }

        bufferedWriter.write("ENDT\n");
    }

    /**
     * @return the outputFormat
     */
    public DecimalFormat getOutputFormat() {
        return outputFormat;
    }

    /**
     * @param outputFormat the outputFormat to set
     */
    public void setOutputFormat(DecimalFormat outputFormat) {
        this.outputFormat = outputFormat;
    }
}
