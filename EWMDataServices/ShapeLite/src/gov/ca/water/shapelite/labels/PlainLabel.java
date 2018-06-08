package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.Coord;

/**
 * A Plain Text Label - no background or text box borders.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class PlainLabel extends LabelBase {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The text angle in radial units with 0.0 in the x-direction (west-to-east)
   * on the map.
   */
  private Float angle;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor with a text and insert point.
   *
   * @param text the label text (required)
   * @param point the label insert point (required)
   * @throws NullPointerException is <tt>text</tt> or <tt>point</tt> is
   * unassigned.
   */
  public PlainLabel(String text, Coord point) {
    this(text, point, null);
  }

  /**
   * Public Constructor with a text, insert point and a defined text angle.
   *
   * @param text the label text (required)
   * @param point the label insert point (required)
   * @param angle the text angle (can be null | NaN)
   * @throws NullPointerException is <tt>text</tt> or <tt>point</tt> is
   * unassigned.
   */
  public PlainLabel(String text, Coord point, Float angle) {
    super(text, point);
    if (this.angle == null || this.angle.isNaN()) {
      this.angle = null;
    } else {
      this.angle = angle;
    }
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Private/Protected Methods">
  /**
   * Called to set the Label's rotation angle.
   *
   * @param angle The Float angle to set.
   */
  protected void setAngle(Float angle) {
    if (angle == null || angle.isNaN()) {
      this.angle = null;
    } else {
      this.angle = angle;
    }
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the assigned angle (default = 0.0f).
   *
   * @return the assigned value.
   */
  public final float getAngle() {
    if (this.angle == null) {
      return 0.0f;
    } else {
      return this.angle;
    }
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}.
   * <p>
   * OVERRIDE: </p>
   *
   * @return A String representation of this label with angle information.
   */
  @Override
  public final String toString() {
    String result = super.toString();
    if (this.angle != null) {
      result += "; angle = " + this.angle;
    }
    return result;
  }
  // </editor-fold>
}
