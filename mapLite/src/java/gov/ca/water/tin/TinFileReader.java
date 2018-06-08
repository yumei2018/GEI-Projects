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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import gov.ca.water.shapelite.Coord;

/**
 *
 * @author hdunsford
 */
public class TinFileReader {
    
    /**
     * Logs exceptions that are not serious enough to halt reading.
     */
    private static final Logger logger = Logger.getLogger(TinFileReader.class.getName());
    
    
    /**
     * This method reads the entire TIN file into memory at once as a TriangleNetwork.
     * If the file has triangles, this will also populate the Triangles in the network.
     * If not, then only the vertices may be populated.
     * @param filename The String filename of the TIN file to open.
     * @return A TriangleNetwork
     * @throws FileNotFoundException If the file can't be found.
     * @throws IOException If there is an error reading the file.
     * @throws InvalidFormatException If the file formatted in the expected TIN format.
     */
    public TriangleNetwork readAll(String filename) throws FileNotFoundException, IOException, InvalidFormatException{
        FileInputStream stream = new FileInputStream(filename);
        return readAll(stream);
    }
    
    /**
     * This method reads the entire TIN file into memory at once as a triangle network.
     * @param stream The InputStream that contains the TIN data.
     * @throws IOException If the stream cannot be parsed
     * @throws InvalidFormatException If the file formatted in the expected TIN format.
     */
    public TriangleNetwork readAll(InputStream stream) throws IOException, InvalidFormatException {
        TriangleNetwork result = new TriangleNetwork();
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String line;
        int iLine = 0;
        Integer iVert = null;
        Integer iTri = null;
        Integer triCount = 0;
        Integer vertCount = 0;
        TriangleGroup currentGroup = null;

        while ((line = br.readLine()) != null) {
            if (iLine == 0) {
                if (!"TIN".equals(line)) {
                    logger.log(Level.FINE, "The stream does not begin "
                            + "with 'TIN', indicating that it may not be a "
                            + "propperly formatted tin file.");
                }
            }
            if (line.trim().isEmpty()) {
                // skip any blank lines.  
                continue;
            }
            if (line.equals("BEGT")) {
                // begin a new triangle group.
                if (currentGroup != null) {
                    logger.log(Level.FINE, "BEGT without closing ENDT on "
                            + "line {0}", iLine);
                    result.getGroups().add(currentGroup);
                    iVert = null;
                    iTri = null;
                }
                currentGroup = new TriangleGroup();

            } else if (line.contains("TNAM")) {
                if (currentGroup == null) {
                    throw new InvalidFormatException("Line " + iLine + "'"
                            + line + "' occured outside of a BEGT/ENDT block.");
                }
                currentGroup.setName(line.substring(5));
            } else if (line.contains("TCOL")) {
                if (currentGroup == null) {
                    throw new InvalidFormatException("Line " + iLine + "'"
                            + line + "' occured outside of a BEGT/ENDT block.");
                }
                String[] words = line.substring(5).trim().split(" ");
                if (words.length >= 3) {
                    try {

                        int r = Integer.parseInt(words[0]);
                        int g = Integer.parseInt(words[1]);
                        int b = Integer.parseInt(words[3]);
                        currentGroup.setColor(new Color(r, g, b));
                    } catch (NumberFormatException ex) {
                        logger.log(Level.FINE, "Tin color could not be read", ex);
                    }
                }

            } else if (line.contains("VERT")) {
                if (currentGroup == null) {
                    throw new InvalidFormatException("Line " + iLine + "'"
                            + line + "' occured outside of a BEGT/ENDT block.");
                }
                try {
                    vertCount = Integer.parseInt(line.substring(5));
                } catch (NumberFormatException ex) {
                    throw new InvalidFormatException("Expected a vertex count, "
                            + "but could not parse the integer count from: "
                            + line, ex);
                }
                iVert = 0;
            } else if (line.contains("TRI")) {
                if (currentGroup == null) {
                    throw new InvalidFormatException("Line " + iLine + "'"
                            + line + "' occured outside of a BEGT/ENDT block.");
                }
                try {
                   triCount = Integer.parseInt(line.substring(5));
                } catch (NumberFormatException ex) {
                    throw new InvalidFormatException("Expected a Triangle count,"
                            + " but could not parse the integer count from: "
                            + line, ex);
                }
                if (iVert < vertCount - 1) {
                    logger.log(Level.SEVERE, "Expected vertex count: {0}, "
                            + "actual vertex count: {1}",
                            new Object[]{vertCount, iVert});
                }
                iTri = 0;
            } else if (line.contains("ENDT")) {
                result.getGroups().add(currentGroup);
                currentGroup = null;
                iVert = null;
                iTri = null;
            }
            if (iTri != null) {
                if (currentGroup == null) {
                    throw new InvalidFormatException("Line " + iLine + "'"
                            + line + "' occured outside of a BEGT/ENDT block.");
                }
                if (iTri > currentGroup.getTriangleCount() - 1) {
                    throw new InvalidFormatException("The line " + line
                            + " on line " + iLine + " represented triangle "
                            + iTri + 1 + " for a group that was only supposed "
                            + "to have " + triCount + " triangles.");
                }
                String[] words = line.substring(5).trim().split(" ");
                if (words.length >= 3) {
                    try {

                        int a = Integer.parseInt(words[0]);
                        int b = Integer.parseInt(words[1]);
                        int c = Integer.parseInt(words[2]);
                        Triangle t = new Triangle(a, b, c, currentGroup.getVertices());
                        currentGroup.getTriangles().add(t);
                    } catch (NumberFormatException ex) {
                        logger.log(Level.SEVERE, "Triangle on line "
                                + iLine + ": '" + line + "' could not be parsed.", ex);
                    } catch (IndexOutOfBoundsException ex) {
                        logger.log(Level.SEVERE, "Triangle on line "
                                + iLine + ": '" + line + "' had an index out of range.", ex);
                    }
                }

                iTri++;
            } else if (iVert != null) {
                if (currentGroup == null) {
                    throw new InvalidFormatException("Line " + iLine + "'"
                            + line + "' occured outside of a BEGT/ENDT block.");
                }
                if (iVert > currentGroup.getTriangleCount() - 1) {
                    throw new InvalidFormatException("The line " + line
                            + " on line " + iLine + " represented triangle "
                            + iVert + 1 + " for a group that was only supposed "
                            + "to have " + currentGroup.getTriangleCount()
                            + " triangles.");
                }
                String[] words = line.substring(5).trim().split(" ");
                if (words.length >= 3) {
                    try {
                        double x = Double.parseDouble(words[0]);
                        double y = Double.parseDouble(words[1]);
                        double z = Double.parseDouble(words[3]);
                        Coord coord = new Coord(x, y, z);
                        if (words.length > 3) {
                            double m = Double.parseDouble(words[4]);
                            coord.M = m; // locked if M = 1, unlocked if M = 0
                        }
                        currentGroup.getVertices().add(coord);
                    } catch (NumberFormatException ex) {
                        logger.log(Level.SEVERE, "Triangle on line "
                                + iLine + ": '" + line + "' could not be parsed.", ex);
                    } catch (IndexOutOfBoundsException ex) {
                        logger.log(Level.SEVERE, "Triangle on line "
                                + iLine + ": '" + line + "' references a vertex out"
                                + " of range.", ex);
                    }
                }

                iVert++;
            }

            iLine++;
        }
        return result;
    }
    
}
