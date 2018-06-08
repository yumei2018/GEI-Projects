package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;

import gov.ca.water.shapelite.labels.grahics.MapCanvas;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Contour Labeler Delegate for generating contour labels of Point, PolyLine,
 * or Polygon shape types. The labeler supports three settings for non-point
 * shape types:
 * <ul>
 * <li>numLabels = (required) the number of labels along the length of each part
 * of the shape (valid values &ge; 1).</li>
 * <li>minSpacing = (optional) if defined and the default spacing is greater
 * than this limit, the number of labels will be reduced to maintain this
 * constraint.</li>
 * <li>maxSpacing = (optional) if defined and the default spacing is less than
 * this limit, the number of labels will be increase to maintain this
 * constraint.</li>
 * </ul>
 * <p>
 * For points a single label is added with an insert at the point and label
 * angle = 0.0.
 * <p>
 * For polylines or polygons labels will be added for each part of the shape at
 * the calculated spacing for the part (based on constraints) starting a
 * spacing/2 from the part's start and ending spacing/2 from the part's end. No
 * labels are added if the minSpacing is set and the part's length is less than
 * minSpacing.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public abstract class SimpleFeatureLabelerDelegate extends FeatureLabelDelegate<ContourLabel> {

  /**
   * The default number of labels to appear on a single part.
   */
  private static final int DEFAULT_NUM_LABELS = 4;

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The default number of labels per part of a polyline, polygon, or multiPoint
   * (default = 4).
   */
  private Integer numLabels;
  /**
   * The Maximum spacing between labels (in points) - increase the numLabels
   * (default = null| no limit).
   */
  private Double maxSpacing;
  /**
   * The Minimum spacing between labels (in points) - decrease the numLabels
   * (default = null| no limit).
   */
  private Double minSpacing;
  /**
   * The current MapCanvas to draw on.
   */
  private transient MapCanvas canvas;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Default parameterless public Constructor (numLabels = 4, no minimum or
   * maximum spacing limits).
   */
  public SimpleFeatureLabelerDelegate() {
    super();
    this.numLabels = DEFAULT_NUM_LABELS;
    this.maxSpacing = null;
    this.minSpacing = null;
  }

  /**
   * Public Constructor with the labeling spacing specifications.
   *
   * @param numLabels the typical number numbers
   * @param minSpacing the minimum label spacing (points)
   * @param maxSpacing the maximum label spacing (points)
   */
  public SimpleFeatureLabelerDelegate(int numLabels,
      Double minSpacing, Double maxSpacing) {
    super();
    if (numLabels <= 0) {
      throw new IllegalArgumentException("The number of labels must "
          + "be greater than 0");
    }
    this.numLabels = numLabels;
    if (((minSpacing == null) || (minSpacing <= 0.0d)
        || (minSpacing.isNaN()))) {
      this.minSpacing = null;
    } else {
      this.minSpacing = minSpacing;
    }
    if (((maxSpacing == null) || (minSpacing <= 0.0d)
        || (maxSpacing.isNaN()))) {
      this.maxSpacing = null;
    } else {
      this.maxSpacing = maxSpacing;
    }

    if ((this.minSpacing != null) && (this.maxSpacing != null)
        && (this.minSpacing > this.maxSpacing)) {
      Double temp = this.maxSpacing;
      this.maxSpacing = this.minSpacing;
      this.minSpacing = temp;
    }
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Private Methods">
  /**
   * Gets the part length in pixels.
   *
   * @param part The part to draw. If part is null, this returns 0.
   * @return A double representing the length of the part in pixels.
   */
  private double getPartLengthInPixels(Part part) {
    double result = 0.0d;
    if (part == null) {
      return 0;
    }
    if (this.canvas == null) {
      result = part.length();
    } else {
      List<Segment> partSegments = part.getSegments();
      for (Segment segment : partSegments) {
        result += this.canvas.getSegmentLengthInPixel(segment);
      }
    }
    return result;
  }

  /**
   * Calculate the label positions and add the labels for part of a polylines or
   * polygons at the calculated spacing for the part (based on constraints). The
   * label points starts a spacing/2 from the part's start and ends spacing/2
   * from the part's end. No labels are added if the minSpacing is set and the
   * part's length is less than minSpacing.
   *
   * @param part the polyline's or polygon's part
   * @param text the label text
   * @param labelList the list to add new labels to
   */
  private void getPolyLineLabels(Part part, String text, List<ContourLabel> labelList) {
    Double minSpace = this.minSpacing;
    Double maxSpace = this.maxSpacing;
    /* Convert the set spacing to Canvas pixels */
    if (this.canvas != null) {
      if (minSpace != null) {
        minSpace = new Double(this.canvas.getStrokeSize(minSpace.floatValue()));
      }
      if (maxSpace != null) {
        maxSpace = new Double(this.canvas.getStrokeSize(maxSpace.floatValue()));
      }
    } else {
      /* Ignore spacing if the canvas is not set*/
      minSpace = null;
      maxSpace = null;
    }

    List<Segment> partSegments = null;
    double partLen = 0.0d;
    if ((part == null) || ((partLen = this.getPartLengthInPixels(part)) <= 0.0d)
        || ((minSpace != null) && (partLen < minSpace))
        || ((partSegments = part.getSegments()) == null)
        || (partSegments.isEmpty())) {
      return;
    }

    int numPnts = this.numLabels;
    double spacing = partLen / (1.0d * numPnts);
    if ((maxSpace != null) && (spacing > maxSpace)) {
      Double num = Math.ceil(partLen / maxSpace);
      numPnts = num.intValue();
      spacing = partLen / (1.0d * numPnts);
    } else if ((minSpace != null) && (spacing < minSpace)) {
      Double num = Math.floor(partLen / minSpace);
      numPnts = num.intValue();
      spacing = partLen / (1.0d * numPnts);
    }

    int iSeg = 0;
    Double along = 0.0d;
    for (int iPnt = 0; iPnt < numPnts; iPnt++) {
      double xLen = (iPnt + 0.5) * spacing;
      while (iSeg < partSegments.size()) {
        Segment seg = partSegments.get(iSeg);
        double lenSeg;
        if (this.canvas == null) {
          lenSeg = seg.length();
        } else {
          lenSeg = this.canvas.getSegmentLengthInPixel(seg);
        }
        if ((along + lenSeg) >= xLen) {
          float angle;
          Coord point;
          if ((along + lenSeg) == xLen) {
            point = seg.getP2().copy();
            Double segAngle = seg.getAngle2D();
            Segment nextSeg = null;
            if (iSeg == (partSegments.size() - 1)) {
              if (part.isClosed()) {
                nextSeg = partSegments.get(0);
              }
            } else {
              nextSeg = partSegments.get(iSeg + 1);
            }
            if (nextSeg != null) {
              Double segAngle2 = nextSeg.getAngle2D();
              segAngle = ((segAngle) + segAngle2) / 2.0d;
            }
            angle = segAngle.floatValue();
          } else {
            Double segAngle = seg.getAngle2D();
            angle = segAngle.floatValue();
            if (lenSeg == 0.0d) {
              point = seg.getP1().copy();
            } else {
              point = seg.coordAt((((xLen - along) / lenSeg) * seg.length()));
            }
          }

          along = xLen;
          ContourLabel label = this.getLabel(point, text, angle);
          labelList.add(label);
          break;
        } else {
          along += lenSeg;
        }
        iSeg++;
      }
    }
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Override FeatureLabelDelegate">
  /**
   * {@inheritDoc}.
   * <p>
   * OVERRIDE: Assign the internal canvas reference.</p>
   */
  @Override
  public final void prepare(MapCanvas canvas) {
    this.canvas = canvas;
  }

  /**
   * Clear the internal canvas reference.
   */
  @Override
  public final void reset() {
    this.canvas = null;
  }

  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: The first step in this process is to retrieve the text that will
   * be added to all label. From the shape if finds the first assigned z-Value
   * and then calls null null null null   {@linkplain #getCountourLabel(java.lang.Double, java.util.HashMap)
   * this.getCountourLabel} to get the label text. Inheritors must override this
   * method to return the formatted contour label.</p>
   * <p>
   * The second step is to initiate and return the list of
   * {@linkplain ContourLabels}. For a point shapeType a single label with a
   * rotation angle of zero will returned. For polyline and polygon shapeTypes
   * its calls {@linkplain #getPolyLineLabels(
   * gov.ca.water.shapelite.Part, java.lang.String, java.util.List)
   * this.getPolyLineLabels} to add the label points along the polyline or the
   * polygons border.
   *
   * @return A list of contour labels for the specified shape.
   */
  @Override
  public final List<ContourLabel> getLabels(Shape shape, HashMap<String, String> attrs) {
    List<ContourLabel> result = new ArrayList<>();
    if(shape == null){
      return result;
    }

    if (!shape.getShapeType().isMultiPoint()) {
      Double contourValue = null;
      double[] zValues = null;

      if (shape.hasZ()
          && ((zValues = shape.getArrayZ()) != null)
          && (zValues.length > 0)) {
        for (double zVal : zValues) {
          if (!Double.isNaN(zVal)) {
            contourValue = zVal;
            break;
          }
        }
      }
      ShapeType shpType = shape.getShapeType();
      String text = this.getCountourLabel(contourValue, attrs);
      if (text != null) {
        if (ShapeType.isPoint(shpType)) {
          Coord point = shape.first().copy();
          ContourLabel label = this.getLabel(point, text, 0.0f);
          result.add(label);
        } else {
          for (Part part : shape.getParts()) {
            this.getPolyLineLabels(part, text, result);
          }
        }
      }
    }
    return result;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Protected Overridable/Asbtract Methods">
  /**
   * CAN OVERRIDE: Called by {@linkplain #getLabels(gov.ca.water.shapelite.Shape,
   * java.util.HashMap) this.getLabels} to initiate the label - base method
   * return new LabelPlain(text, point, angle).
   *
   * @param point label insert point
   * @param text label text
   * @param angle label rotation angle (default = 0.0)
   * @return new LabelPlain instance
   */
  protected ContourLabel getLabel(Coord point, String text, float angle) {
    return new ContourLabel(text, point, angle);
  }

  /**
   * ABSTRACT: Called by {@linkplain #getLabels(gov.ca.water.shapelite.Shape,
   * java.util.HashMap) this.getLabel} to get the Contour Label's formatted
   * text.
   *
   * @param contourValue the z-value of the the first point (can be null is no
   * z-values are assigned)
   * @param attrs the shape's attributes (can be null | empty) if the shape has
   * no attributes
   * @return the formatted contour label or null (labeling will be skipped).
   */
  protected abstract String getCountourLabel(Double contourValue,
      HashMap<String, String> attrs);
  // </editor-fold>
}
