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
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.ShapefileHeader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Unlike the ShapefileWriter, this class starts with the assumption that the shapefile is
 * too large to store in memory. It allows building the shapefile on disk one feature at a
 * time, building the shx content and header information as it goes.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileBuilder implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(ShapefileBuilder.class.getName());

    private Writer shpWriter;
    private Writer shxWriter;
    private String shapefileName;
    private String shp;
    private String shx;
    private String dbf;
    private ShapefileHeader header;
    private Integer iShape = null;
    private Integer shxOffset = null;
    private List<Field> fields;
    private AttributeTableBuilder atb;
    private String projection;

    /**
     * Creates a new instance of the ShapefileBuilder
     */
    public ShapefileBuilder(){
        fields = new ArrayList<>();
    }

    /**
     * This method saves the specified featureSet.
     *
     * @param shapefileName the string filename ending in .shp.
     * @throws ShapefileException
     */
    public void open(String shapefileName) throws ShapefileException {
        this.shapefileName = shapefileName;
        FileOutputStream shpStream = null;
        FileOutputStream shxStream = null;
        try {
            shp = ShapefileWriter.setExtension(shapefileName, ".shp");
            shx = ShapefileWriter.setExtension(shapefileName, ".shx");
            dbf = ShapefileWriter.setExtension(shapefileName, ".dbf");
            String prj = ShapefileWriter.setExtension(shapefileName, ".prj");

            File shpFile = new File(shp);
            if (shpFile.exists()) {
                shpFile.delete();
            }
            File shxFile = new File(shx);
            if (shxFile.exists()) {
                shxFile.delete();
            }
            File dbfFile = new File(dbf);
            if (dbfFile.exists()) {
                dbfFile.delete();
            }
            File prjFile = new File(prj);
            if(prjFile.exists()){
                prjFile.delete();
            }
            if (!shpFile.getParentFile().exists()) {
                shpFile.getParentFile().mkdirs();
            }
            if(projection != null){

                try(PrintWriter pw = new PrintWriter(prj)){
                    pw.write(projection);
                    pw.flush();
                }
            }

            shpStream = new FileOutputStream(shp);
            shxStream = new FileOutputStream(shx);


        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShapefileBuilder.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);
            try {
                if (shpStream != null) {
                    shpStream.flush();
                    shpStream.close();
                }
            } catch (IOException ex2) {
                Logger.getLogger(ShapefileBuilder.class.getName()).log(
                      Level.SEVERE, ex.getMessage(), ex2);
            }
            try {
                if (shxStream != null) {
                    shxStream.flush();
                    shxStream.close();
                }
            } catch (IOException ex2) {
               Logger.getLogger(ShapefileBuilder.class.getName()).log(
                      Level.SEVERE, ex.getMessage(), ex2);
            }

        }

        atb = new AttributeTableBuilder();
        atb.setFields(fields);
        try {
            atb.open(dbf);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShapefileBuilder.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);
        }

        shpWriter = new Writer(shpStream);
        shpWriter.writeSpace(100); // skip past header bytes
        shxWriter = new Writer(shxStream);
        shxWriter.writeSpace(100);
        header = new ShapefileHeader();
        iShape = 0;
        shxOffset = 50;
    }

    /**
     * Optionally write the prj file using the specified projection.
     *
     * @param projection
     * @throws java.io.FileNotFoundException
     */
    public void writePrjFile(String projection) throws FileNotFoundException, IOException {
        String prj = ShapefileWriter.setExtension(shapefileName, ".prj");
        File prjFile = new File(prj);
        if (prjFile.exists()) {
            prjFile.delete();
        }
        try (FileOutputStream prjStream = new FileOutputStream(prj)) {
            writePrjFile(projection, prjStream);
        }
    }

    /**
     * Writes the specified projection to the specified output stream.
     *
     * @param projection
     * @param prj
     * @throws IOException
     */
    public void writePrjFile(String projection, OutputStream prj) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(prj));
        writer.write(projection);
        writer.flush();
        // be sure not to close the stream.
    }

    /**
     * This assumes that rather than writing to files, we are writing to streams instead.
     * There is a separate output file stream for the shp, shx and dbf extensions.
     *
     * @param feature The feature to append to the shapefile.
     * @throws ShapefileException if the featureset is empty or null.
     * @throws java.io.IOException if there was a problem writing to the file.
     */
    public void add(Feature feature) throws ShapefileException, IOException {

        // update the header information (but don't write to disk yet.
        updateHeader(feature);

        // write record header and shape data to disk immediately.
        shpWriter.writeBigInt(iShape + 1); // record numbers start at 1
        int size = (header.getByteSizes().getSizes().get(iShape) - 8) / 2;
        shpWriter.writeBigInt(size);
        shpWriter.write(feature.getShape().getShapeType().getValue());
        ShapefileWriter.writeShape(shpWriter, feature.getShape());

        shxWriter.writeBigInt(shxOffset);
        shxWriter.writeBigInt(size);
        shxOffset += (size + 4);
        iShape++;

        atb.add(feature);

    }

    /**
     * This method updates the header content, stored in ram. This is used when closing
     * the shapefile at the end to write the final shapefile header information.
     *
     * @param feature
     * @throws ShapefileException
     */
    private void updateHeader(Feature feature) throws ShapefileException {
        // Shape Type
        if (header.getShapeType() == null || header.getShapeType() == ShapeType.NullShape) {
            header.setShapeType(feature.getShape().getShapeType());
        }
        if (feature.getShape().getShapeType() != header.getShapeType()) {
            throw new ShapefileException("The feature type was "
                    + feature.getShape().getShapeType() + " but the expected type was "
                    + header.getShapeType());
        }
        // Bounds
        Envelope bounds = header.getBounds();
        if (bounds == null) {
            bounds = feature.getEnvelope();
        } else {
            bounds.expandToInclude(feature.getEnvelope());
        }
        header.setBoundsFrom(bounds);

        ByteSizes featureMeasures = header.getByteSizes();
        if (featureMeasures == null) {
            featureMeasures = new ByteSizes();
            header.setByteSizes(featureMeasures);
        }
        featureMeasures.getSizes().add(ShapefileWriter.calculateSize(feature));

        // Be sure to convert from length in bytes to length in 16-bit words.
        header.setLength(featureMeasures.getTotalLength() / 2);

        header.setShxLength(50 + featureMeasures.getSizes().size() * 4);

        header.setByteSizes(featureMeasures);

    }

    /**
     * This closes any open writers, but also attempts to finish the shapefile by opening
     * the writers again and writing the header to the files.
     */
    @Override
    public void close() {
        if (shpWriter != null) {

            try {
                shpWriter.flush();
            } catch (IOException ex) {
                Logger.getLogger(ShapefileBuilder.class.getName()).log(
                      Level.SEVERE, ex.getMessage(), ex);
            }
            shpWriter.close();
        }
        if (shxWriter != null) {
            try {
                shxWriter.flush();
            } catch (IOException ex) {
                Logger.getLogger(ShapefileBuilder.class.getName()).log(
                      Level.SEVERE, ex.getMessage(), ex);
            }
            shxWriter.close();
        }
        if (atb != null) {
            try {
                atb.close();
            } catch (IOException ex) {
                Logger.getLogger(ShapefileBuilder.class.getName()).log(
                      Level.SEVERE, ex.getMessage(), ex);
            }
        }

        finalizeHeaders();
    }

    /**
     * This method finishes the writing by writing the now-defined headers.
     */
    private void finalizeHeaders() {
        RandomAccessFile shpFile;
        try {
            shpFile = new RandomAccessFile(shp, "rw");
            shpFile.seek(0);
            shpFile.write(header.getShpHeader());
            shpFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShapefileBuilder.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);
        } catch (IOException ex) {
            Logger.getLogger(ShapefileBuilder.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);
        }

        RandomAccessFile shxFile;
        try {
            shxFile = new RandomAccessFile(shx, "rw");
            shxFile.seek(0);
            shxFile.write(header.getShxHeader());
            shxFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShapefileBuilder.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);
        } catch (IOException ex) {
            Logger.getLogger(ShapefileBuilder.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);
        }

    }






    /**
     * Gets the fields that can override the default Char, 25 length fields that will be
     * created. If this list is specified, then no attributes will appear except the ones
     * in this list.
     *
     * @return the fields
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
        if(this.atb != null){
            atb.setFields(fields);
        }
    }

    /**
     * @return the projection
     */
    public String getProjection() {
        return projection;
    }

    /**
     * @param projection the projection to set
     */
    public void setProjection(String projection) {
        this.projection = projection;
    }

}
