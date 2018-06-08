package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.Coord;
import java.io.Serializable;
import java.util.Objects;

/**
 * The Base Label data point supporting a the label's text and an insert point
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class LabelBase implements Comparable<LabelBase>, Serializable {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The Label's Text
   */
  private String text;
  /**
   * The Label's inert Point
   */
  private Coord point;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor  
   */
  public LabelBase(String text, Coord point) {
    super();
    if ((text == null) || ((text = text.trim()) == null)) {
      throw new NullPointerException("The Label's Text cannot be unassigned.");
    }
    
    if ((point == null) || (Double.isNaN(point.getX())) || (Double.isNaN(point.getY()))) {
      throw new NullPointerException("The Label's Insert Point is unassigned or "
              + "incomplete (i.e., missing X- or Y-values).");
    }
    this.text = text;
    this.point = point;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Private/Protected Methods">
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the Label's Text
   * @return the assigned text
   */
  public String getText() {
    return this.text;
  }
  
  /**
   * Get the Label Insert Point
   * @return the assigned coordinate
   */
  public Coord getPoint() {
    return this.point;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: "Label[text = '" + this.text + "'; \n point = " + this.point + "]</p>
   */
  @Override
  public String toString() {
    return "Label:text = '" + this.text + "'; \npoint = " + this.point ;
  }

  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Return hash code on this.text and this.point</p>
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.text);
    hash = 89 * hash + Objects.hashCode(this.point);
    return hash;
  }

  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: return true of (obj != null) and (instance of LabelBase) and
   * this.text = obj.text and this.point == obj.point</p>
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public boolean equals(Object obj) {
    boolean result = ((obj != null) && (obj instanceof LabelBase));
    if (result) {
      LabelBase other = (LabelBase) obj;
      result = ((this.text.equals(other.text)) && (this.point.equals(other.point)));
    }
    return result;
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Override Comparable">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Compare the two Label's based on Text</p>
   */
  @Override
  public int compareTo(LabelBase other) {
    int result = 0;
    if (other == null) {
      result = -1;
    } else {
      result = this.text.compareTo(other.getText());
    }
    return result;
  }
  // </editor-fold>

  
}
