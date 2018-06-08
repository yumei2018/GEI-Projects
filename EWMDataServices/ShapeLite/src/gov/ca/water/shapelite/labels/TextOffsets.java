package gov.ca.water.shapelite.labels;

import java.io.Serializable;

/**
 * The Text Offset relative to the TextBox
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class TextOffsets implements Serializable {

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  private Float top;
  private Float right;
  private Float bottom;
  private Float left;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Default Public Constructor  - no parameters
   */
  public TextOffsets() {
    super();  
    this.top = null;
    this.right = null;
    this.bottom = null;
    this.left = null;
  }
  
  /**
   * Public Constructor with defined offsets 
   */
  public TextOffsets(Float top, Float right, Float bottom, Float left) {
    super();  
    this.setTop(top);
    this.setRight(right);
    this.setBottom(bottom);
    this.setLeft(left);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the Top offset
   * @return the assigned value (0 if unassigned)
   */
  public final Float getTop() {
    return (this.top == null)? 0.0f: this.top;
  }

  /**
   * Set the Top offset
   * @param top the offset (null if NaN | &le; 0.0)
   */
  public final void setTop(Float top) {
    this.top = ((top == null) || (top.isNaN()) || (top <= 0.0f))? null: top;
  }

  /**
   * Get the Right offset
   * @return the assigned value (0 if unassigned)
   */
  public final Float getRight() {
    return (this.right == null)? 0.0f: this.right;
  }

  /**
   * Set the Right offset
   * @param right the offset (null if NaN | &le; 0.0)
   */
  public final void setRight(Float right) {
    this.right = ((right == null) || (right.isNaN()) || (right <= 0.0f))? null: right;
  }

  /**
   * Get the Bottom offset
   * @return the assigned value (0 if unassigned)
   */
  public final Float getBottom() {
    return (this.bottom == null)? 0.0f: this.bottom;
  }

  /**
   * Set the Bottom offset
   * @param bottom the offset (null if NaN | &le; 0.0)
   */
  public final void setBottom(Float bottom) {
    this.bottom = ((bottom == null) || (bottom.isNaN()) || (bottom <= 0.0f))? null: bottom;
  }

  /**
   * Get the Left offset
   * @return the assigned value (0 if unassigned)
   */
  public final Float getLeft() {
    return (this.left == null)? 0.0f: this.left;
  }

  /**
   * Set the Left offset
   * @param left the offset (null if NaN | &le; 0.0)
   */
  public final void setLeft(Float left) {
    this.left = ((left == null) || (left.isNaN()) || (left <= 0.0f))? null: left;
  }
  
  /**
   * Check if all the TextOffsets are undefined or null
   * @return true if all are undefined or 0.0.
   */
  public final boolean isEmpty() {
    return ((this.top == null) && (this.right == null) && (this.bottom == null) && 
            (this.left == null));
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Object Overrides">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: </p>
   */
  @Override
  public String toString() {
    return "TextOffset[ T=" + this.getTop() + "; R=" + this.getRight() 
            + "; B=" + this.getBottom() + "; L=" + this.getLeft()+ "]";
  }

  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Return a new instance with the same offsets</p>
   */
  @Override
  protected TextOffsets clone()  {
    return new TextOffsets(this.top, this.right, this.bottom, this.left);
  }
  // </editor-fold>
}
