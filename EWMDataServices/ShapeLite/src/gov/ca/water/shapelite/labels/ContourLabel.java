package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class ContourLabel extends PlainLabel {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * A floating point version of PI for angle calculations.
   */
  private static final float PI = Double.valueOf(Math.PI).floatValue();

  /**
   * PI/2, or 90 degrees used for label angle calculations.
   */
  private static final float HALF_PI = .5f * PI;

  /**
   * The envelope for this label.
   */
  private final Envelope envelope;

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor.
   *
   * @param text The String text for the label.
   * @param point The Coordinate anchor point for the label.
   */
  public ContourLabel(String text, Coord point) {
    super(text, point);
    envelope = new DefaultEnvelope();
  }

  /**
   * Public Constructor.
   *
   * @param text The String text for the label.
   * @param point The Coordinate anchor point for the label.
   * @param angle The float angle for the label.
   */
  public ContourLabel(String text, Coord point, float angle) {
    super(text, point);
    this.setAngle(angle);
    envelope = new DefaultEnvelope();
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Protected PlainLabel Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Calculate the label angle based on the line angle at the inset
   * point. The resulting angle is: -PI/2 &ge; angle &le; PI/2</p>
   */
  @Override
  protected final void setAngle(Float angle) {

    if ((angle == null) || (angle.isNaN()) || (angle > (2 * PI))
        || (angle < (-PI * 2))) {
      angle = 0.0f;
    } else {
      angle = (-1.0f * angle);
      if (angle > PI) {
        angle = (angle - (2 * PI));
      } else if (angle < (-1.0f * PI)) {
        angle = ((2 * PI) - angle);
      }

      if (angle > (HALF_PI)) {
        angle = (angle - PI);
      } else if (angle < (-HALF_PI)) {
        angle = (PI - angle);
      }
    }
    super.setAngle(angle);
  }
  // </editor-fold>

  /**
   * Gets the bounds for the label.
   *
   * @return the envelope
   */
  public final Envelope getEnvelope() {
    return envelope;
  }

  /**
   * Copies the properties of the other envelope to the envelope on this label.
   *
   * @param other the envelope The envelope to copy.
   */
  public final void setEnvelopeFrom(Envelope other) {
    envelope.copyProperties(other);
  }
}
