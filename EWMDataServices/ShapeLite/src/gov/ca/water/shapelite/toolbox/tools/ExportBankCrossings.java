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
package gov.ca.water.shapelite.toolbox.tools;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import gov.ca.water.shapelite.coordinate.ClosestCoord;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.OutputFileParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import gov.ca.water.shapelite.topology.Adapter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ExportBankCrossings extends ToolBase {

  /**
   * The field length for Bank and Elevation fields.
   */
  private static final int FIELD_LENGTH = 10;

  /**
   * The number of decimals for the elevation field.
   */
  private static final int DECIMALS = 2;

  /**
   * Two decimal format for numbers.
   */
  private static final DecimalFormat TWO_DIGITS = new DecimalFormat("#.00");
  /**
   * Logger.
   */
  private static final Logger LOGGER = Logger.getLogger(
          ExportBankCrossings.class.getName());

  /**
   * A comparator to sort feature spots by index.
   */
  private static final Comparator<FeatureSpot> BY_INDEX
          = new Comparator<FeatureSpot>() {

    @Override
    public int compare(FeatureSpot o1, FeatureSpot o2) {
      if (o1.getSegIndex() == null) {
        if (o2.getSegIndex() == null) {
          return 0;
        }
        return 1;
      }
      if (o2.getSegIndex() == null) {
        return -1;
      }
      return o1.getSegIndex().compareTo(o2.getSegIndex());
    }
  };

  /**
   * A parameter to specify the input file for the bank lines.
   */
  private final InputFileParameter paramInputBankLines;
  /**
   * A parameter to specify the input cross sections.
   */
  private final InputFileParameter paramInputCrossSections;
  /**
   * A parameter output.
   */
  private final OutputFileParameter paramOutput;

  /**
   * The export bank crossing.
   */
  public ExportBankCrossings() {
    setName("Export Bank Crossings");
    setDescription("This cycles through the cross section lines, checking for"
            + " intersections with bank lines, and exports the results as a"
            + " csv.");
    this.setHelpText("This cycles through the cross section lines, checking "
            + "for intersections with bank lines, and exports the results as "
            + "a csv.");
    this.setCategory("DSS");
    this.setHelpImageFilename("resources/ExportBankCrossings.png");

    paramInputBankLines = new InputFileParameter();
    paramInputBankLines.setParameterName("Bank Line Shapefile");
    paramInputBankLines.setExtension(".shp");
    paramInputBankLines.setDescription("The Shapefile containing the bank lines of"
            + " the river.");
    paramInputBankLines.setHelpText("The Shapefile containing the bank lines of the"
            + " river.  These can be created using the GmzToShapefiles tool.");
    this.getParameters().add(paramInputBankLines);

    paramInputCrossSections = new InputFileParameter();
    paramInputCrossSections.setParameterName("Cross Section Shapefile");
    paramInputCrossSections.setExtension(".shp");
    paramInputCrossSections.setDescription("The XS Shapefile containing the cross"
            + " sections.  If the XS Shapefile is used, the Z values should "
            + "also be calculated for these points.");
    paramInputCrossSections.setHelpText("The Shapefile containing the cross "
            + "sections.  These can be created using the GmzToShapefiles "
            + "tool.");
    this.getParameters().add(paramInputCrossSections);

    paramOutput = new OutputFileParameter();
    paramOutput.setParameterName("Output Intersection Points");
    paramOutput.setExtension(".shp");
    paramOutput.setDescription("The output shapefile store the extracted points in.");
    paramOutput.setHelpText("The output shapefile store the extracted points in.");
    this.getParameters().add(paramOutput);
  }

  /**
   * This allows the tool to be executed directly from code.
   *
   * @param bankLineShapefile The String bank line shapefile.
   * @param crossSectionShapefile The cross Section shapefile.
   * @param outputShapefile The output shapefile.
   */
  public final void runImmediately(String bankLineShapefile,
          String crossSectionShapefile, String outputShapefile) {
    paramInputBankLines.setParameterText(bankLineShapefile);
    paramInputCrossSections.setParameterText(crossSectionShapefile);
    paramOutput.setParameterText(outputShapefile);
  }

  /**
   * The actual implementation of the process.
   */
  @Override
  public final void runImmediately() {

    getProgress().create("Find Bank Intersections", this.getCancellable());
    ShapefileReader xsReader = new ShapefileReader();
    ShapefileReader bankReader = new ShapefileReader();
    try {
      xsReader.open(paramInputCrossSections.getParameterText());
      bankReader.open(paramInputBankLines.getParameterText());
      List<Shape> banks = bankReader.getShapes();
      FeatureSet xsFeatures = xsReader.getFeatures();
      FeatureSet intersectFeatures = new FeatureSet();
      intersectFeatures.copyFields(xsFeatures);
      intersectFeatures.setProjectionESRI(xsFeatures.getProjectionESRI());
      intersectFeatures.getFields().add(
              new Field("Bank", FieldType.Character, FIELD_LENGTH));
      intersectFeatures.getFields().add(
              new Field("Elevation", FIELD_LENGTH, DECIMALS));
      getProgress().start(xsFeatures.getFeatures().size());
      int i = 0;
      for (Feature xsFeature : xsFeatures.getFeatures()) {
        if (xsFeature.getAttributes().get("XS_NAME").
                contains("YOL_Putah-Cache Sl_24.09")) {
          boolean stop = true;
        }
        Optional<Geometry> geom = Adapter.getGeometry(xsFeature.getShape());
        if (!geom.isPresent()) {
          continue;
        }
        Geometry xs = geom.get();
        List<FeatureSpot> featureSpots = new ArrayList<>();
        for (Shape bank : banks) {
          if (xsFeature.getShape().getEnvelope().intersects2D(bank.getEnvelope())) {
            Optional<Geometry> bankLine = Adapter.getGeometry(bank);

            if (!bankLine.isPresent() || !xs.intersects(bankLine.get())) {
              continue;
            }
            Geometry intersect = null;
            try {
              intersect = xs.intersection(bankLine.get());
            } catch (Exception ex) {
              Logger.getLogger(ExportBankCrossings.class.getName()).log(
                      Level.SEVERE, ex.getMessage());
            }
            if (intersect != null) {
              for (Coordinate c : intersect.getCoordinates()) {
                Coord spotCoord = new CoordZ(c.x, c.y, 0.0);
                Shape intersectShape = new Shape(spotCoord);
                Feature intersectFeature = new Feature(intersectShape);
                intersectFeature.setShape(intersectShape);
                intersectFeature.copyAttributes(xsFeature.getAttributes());
                FeatureSpot spot = new FeatureSpot();
                featureSpots.add(spot);
                spot.setFeature(intersectFeature);
                double minDist = Double.MAX_VALUE;
                int index = 0;
                for (Segment segment : xsFeature.getShape().getSegments()) {
                  double dist = segment.distanceTo(spotCoord);
                  if (dist < minDist) {
                    spot.setSegment(segment);
                    spot.setSegIndex((Integer) index);
                    minDist = dist;
                  }
                  index++;
                }

                ClosestCoord segPt = spot.getSegment().closestPointTo(spotCoord);

                if (segPt.getCoord().equals(spot.getSegment().getP1())) {
                  spot.setElevation(spot.getSegment().getP1().get(Coord.Index.Z));
                }
                if (segPt.getCoord().equals(spot.getSegment().getP2())) {
                  spot.setElevation(spot.getSegment().getP2().get(Coord.Index.Z));
                } else {
                  // Linearly interpolate elevation from segment endpoints, but
                  // along the line, not to wherever the real point may be.
                  double f1 = spot.getSegment().getP2().distance(segPt.getCoord())
                          / spot.getSegment().length();
                  double f2 = 1 - f1;
                  spot.setElevation(f1 * spot.getSegment().getP1().
                          get(Coord.Index.Z) + f2
                          * spot.getSegment().getP2().get(Coord.Index.Z));
                }

              }
            }
          }
        }
        // there should be two feature spots, and the one with the smaller
        // index should be left bank.

        Collections.sort(featureSpots, BY_INDEX);
        if (featureSpots.size() > 0) {
          featureSpots.get(0).getFeature().getAttributes().put("Bank", "Left");
          featureSpots.get(featureSpots.size() - 1).getFeature().
                  getAttributes().put("Bank", "Right");
        }
        for (FeatureSpot fs : featureSpots) {
          fs.getFeature().getAttributes().put("Elevation",
                  TWO_DIGITS.format(fs.getElevation()));
          intersectFeatures.getFeatures().add(fs.getFeature());
        }
        getProgress().progress(i);
        i++;
      }
      ShapefileWriter writer = new ShapefileWriter();
      writer.write(intersectFeatures, paramOutput.getParameterText());
      getProgress().finish();

    } catch (IOException | ShapefileException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  /**
   * An inner class to sort feature spots.
   */
  private class FeatureSpot {

    /**
     * The line segment for the feature spot.
     */
    private Segment segment;
    /**
     * The feature shape and attributes for this feature spot.
     */
    private Feature feature;
    /**
     * The elevation of the feature.
     */
    private double elevation;
    /**
     * The index value of the segment.
     */
    private Integer segIndex;

    //<editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * Gets the line segment for the feature spot.
     *
     * @return the segment
     */
    public Segment getSegment() {
      return segment;
    }

    /**
     * Sets the line segment for the feature spot.
     *
     * @param segment the segment to set
     */
    public void setSegment(Segment segment) {
      this.segment = segment;
    }

    /**
     * Gets the feature shape and attributes for this feature spot.
     *
     * @return the feature
     */
    public Feature getFeature() {
      return feature;
    }

    /**
     * Sets the feature shape and attributes for this feature spot.
     *
     * @param feature the feature to set
     */
    public void setFeature(Feature feature) {
      this.feature = feature;
    }

    /**
     * Gets the elevation of the feature.
     *
     * @return the elevation
     */
    public double getElevation() {
      return elevation;
    }

    /**
     * Sets the elevation of the feature.
     *
     * @param elevation the elevation to set
     */
    public void setElevation(double elevation) {
      this.elevation = elevation;
    }

    /**
     * Gets the index value of the segment.
     *
     * @return the segIndex
     */
    public Integer getSegIndex() {
      return segIndex;
    }

    /**
     * Sets the index value of the segment.
     *
     * @param segIndex the segIndex to set
     */
    public void setSegIndex(Integer segIndex) {
      this.segIndex = segIndex;
    }

    // </editor-fold>
  }

}
