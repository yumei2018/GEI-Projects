/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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

import de.micromata.opengis.kml.v_2_2_0.Data;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.ExtendedData;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.IconStyle;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.symbology.Symbolizer;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import gov.ca.water.shapelite.symbology.LineSymbolizer;
import gov.ca.water.shapelite.symbology.PolygonSymbolizer;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class KmzWriter {

    private static final ProjectionInfo wgs84 = Projections.getGeographic().getWorld().getWGS1984();

    private String nameField;
    private Symbolizer symbolizer;

    public KmzWriter() {

    }

    /**
     * This only writes to the specified stream. This will not close the stream.
     *
     * @param featureSet
     * @param stream
     * @throws java.io.FileNotFoundException
     * @throws gov.ca.water.shapelite.projection.ProjectionException
     */
    public void write(FeatureSet featureSet, OutputStream stream) throws FileNotFoundException, ProjectionException, IOException {

        final Kml kml = new Kml();
        Document document = kml.createAndSetDocument().withName(featureSet.getName());
        int iFeature = 0;
        ProjectionInfo proj = ProjectionInfo.fromEsriString(featureSet.getProjectionESRI());
        if (proj.equals(wgs84)) {
            proj = null;
        }

        for (Feature feature : featureSet.getFeatures()) {
            ShapeType type = feature.getShape().getShapeType();
            if (type == ShapeType.Point || type == ShapeType.MultiPointM || type == ShapeType.MultiPointZ) {
                addPoint(featureSet, document, iFeature, proj);
            }
            iFeature++;
        }
        try (ZipOutputStream zStream = new ZipOutputStream(stream)) {
            // KML
            ZipEntry entry = new ZipEntry(featureSet.getName() + ".kml");
            zStream.putNextEntry(entry);
            kml.marshal(zStream);
            zStream.closeEntry();
            
            // icon
            zStream.putNextEntry(new ZipEntry("point.png"));
            BufferedImage point = symbolizer.getLegendIcon();
            ImageIO.write(point, "png", zStream);
            zStream.closeEntry();
            
            zStream.flush();
        }
    }

    private void addPoint(FeatureSet fs, Document document, int index, ProjectionInfo projection) throws ProjectionException {
        String name = "point " + index;
        Feature item = fs.getFeatures().get(index);
        if (nameField != null) {
            name = item.getAttributes().get(nameField);
        }
        Coord c = item.getShape().first();
        if (projection != null) {
            c = Reproject.reprojectCoordinate(c, projection, wgs84);
        }
        Placemark mark = document.createAndAddPlacemark().withName(name).withOpen(Boolean.TRUE);
        mark.createAndSetPoint().addToCoordinates(c.getX(), c.getY());
        addAttributes(fs, item, mark);
        addStyle(item, mark);
    }

    /**
     * Not fully implemented
     *
     * @param item
     * @param mark
     */
    private void addAttributes(FeatureSet fs, Feature item, Placemark mark) {

        if (item.getAttributes() != null && !item.getAttributes().isEmpty()) {
            ExtendedData extendedData = new ExtendedData();
            List<Data> dataList = new ArrayList<>();
            extendedData.setData(dataList);
            for (Field field : fs.getFields()) {
                String name = field.getName();
                Data data = new Data(name);
                data.setDisplayName(name);
                data.setValue(item.getAttributes().get(field.getName()));
                dataList.add(data);
            }
            mark.setExtendedData(extendedData);
        }

    }

    /**
     * Adds the style for points.
     *
     * @param item
     * @param mark
     */
    private void addStyle(Feature item, Placemark mark) {

        if (symbolizer == null) {
            ShapeType type = item.getShape().getShapeType();
            switch (type.getCategory()) {
                case Point:
                case MultiPoint:
                    symbolizer = new PointSymbolizer();
                    break;
                case PolyLine:
                    symbolizer = new LineSymbolizer();
                    break;
                case Polygon:
                    symbolizer = new PolygonSymbolizer();
                    break;
            }
        }

        //Adds Style elements to the KML file
        Style style = new Style().withId("point");
        style.withIconStyle(new IconStyle().withIcon(new Icon().withHref("point.png")).withScale(1.0));
        mark.addToStyleSelector(style);
    }

    /**
     * @return the nameField
     */
    public String getNameField() {
        return nameField;
    }

    /**
     * @param nameField the nameField to set
     */
    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    /**
     * @return the symbolizer
     */
    public Symbolizer getSymbolizer() {
        return symbolizer;
    }

    /**
     * @param symbolizer the symbolizer to set
     */
    public void setSymbolizer(Symbolizer symbolizer) {
        this.symbolizer = symbolizer;
    }
}
