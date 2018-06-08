package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;

import gov.ca.water.shapelite.labels.grahics.MapCanvas;
import java.awt.FontMetrics;
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
 * @author Ted Dunsford derived from Koos's SimpleFeatureLabelerDelegate
 */
public abstract class SimpleFeatureLabelerDelegateFlex
    extends FeatureLabelDelegate<ContourLabel> {

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
   * The Minimum spacing between labels in projected coordinates - increase the
   * numLabels
   */
  private Double minSpacing;

  /**
   * The Maximum spacing between labels in projected coordinates - increase the
   * numLabels (default = null| no limit).
   */
  private Double maxSpacing;

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
  public SimpleFeatureLabelerDelegateFlex() {
    super();
    this.numLabels = DEFAULT_NUM_LABELS;
  }

  /**
   * Public Constructor with the labeling spacing specifications.
   *
   * @param numLabels the typical number numbers
   * @param minSpacing the minimum label spacing (points)
   * @param maxSpacing the maximum label spacing (points)
   */
  public SimpleFeatureLabelerDelegateFlex(int numLabels, Double minSpacing,
      Double maxSpacing) {
    super();
    if (numLabels <= 0) {
      throw new IllegalArgumentException("The number of labels must "
          + "be greater than 0");
    }
    this.numLabels = numLabels;
    this.minSpacing = minSpacing;
    this.maxSpacing = maxSpacing;

  }
  // </editor-fold>

  /**
   * Calculate the label positions and add the labels for part of a polylines or
   * polygons at the calculated spacing for the part (based on constraints). The
   * label points starts a spacing/2 from the part's start and ends spacing/2
   * from the part's end. No labels are added if the minSpacing is set and the
   * part's length is less than minSpacing.
   *
   * @param part the polyline's or polygon's part
   * @param text the label text return
   * @return The List of ContourLabels with the created labels.
   */
  private List<ContourLabel> getPolyLineLabels(Part part, String text) {
    List<ContourLabel> result = new ArrayList<>();
    /* Convert the set spacing to Canvas pixels */
    Double minSpace = this.minSpacing;
    Double maxSpace = this.maxSpacing;
    if (this.canvas != null) {
      if (this.minSpacing != null) {
        minSpace = new Double(this.canvas.getStrokeSize(minSpacing.floatValue()));
      }
      if (this.maxSpacing != null) {
        maxSpace = new Double(this.canvas.getStrokeSize(maxSpacing.floatValue()));
      }
    }
    // switch to real-world distances.
    minSpace = canvas.projectedLength(minSpace.intValue());
    maxSpace = canvas.projectedLength(maxSpace.intValue());
    double partLength = part.length();
    if (partLength < minSpace) {
      return result;
    }
    // the plus 1 accounts for 2 half spacings on either end.
    double spacing = partLength / (this.numLabels + 1);
    if (spacing < minSpace) {
      spacing = minSpace;
    }
    if (spacing > maxSpace) {
      spacing = maxSpace;
    }
    double station = spacing / 2;
    double end = partLength - spacing / 2;
    while (station < end) {
      Optional<Coord> coord = part.getCoordinateAtDistance(station);
      Optional<Double> angle = part.getAngleAtDistance(station);
      if (coord.isPresent() && angle.isPresent()) {
        ContourLabel label = this.getLabel(coord.get(), text,
            angle.get().floatValue());

        result.add(label);
      }
      station += spacing;
    }
    return result;
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
   * and then calls null null null null null null null null null null    {@linkplain #getCountourLabel(java.lang.Double, java.util.HashMap)
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
  public final List<ContourLabel> getLabels(@Nullable Shape shape,
      HashMap<String, String> attrs) {
    List<ContourLabel> result = new ArrayList<>();
    if (shape == null) {
      return result;
    }

    if (!shape.getShapeType().isMultiPoint()) {
      Double contourValue = null;
      if (shape.hasZ() && !shape.isEmpty()) {
        contourValue = shape.first().get(Coord.Index.Z);
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
            result.addAll(this.getPolyLineLabels(part, text));
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
  final protected ContourLabel getLabel(Coord point, String text, float angle) {
    ContourLabel result = new ContourLabel(text, point, angle);
    FontMetrics metrics = canvas.graphics.getFontMetrics();
    double width = metrics.stringWidth(text);
    double height = metrics.getHeight();
    double dx = canvas.projectedLengthFromPixels((int) width);
    double dy = canvas.projectedLengthFromPixels((int) height);
    double xMin = point.getX();
    if (angle > 0) {
      xMin = xMin - dy * Math.sin(angle);
    }
    double yMin = point.getY();
    if (angle < 0) {
      yMin = yMin - dx * Math.sin(angle);
    }
    double xMax = point.getX() + dx * Math.cos(angle);
    if (angle < 0) {
      xMax = xMax + dy * Math.sin(angle);
    }
    double yMax = point.getY() + dy * Math.cos(angle);
    if (angle > 0) {
      yMax = yMax + dx * Math.sin(angle);
    }
    result.getEnvelope().getMin().setX(xMin);
    result.getEnvelope().getMin().setY(yMin);
    result.getEnvelope().getMax().setX(xMax);
    result.getEnvelope().getMax().setY(yMax);
    return result;
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
