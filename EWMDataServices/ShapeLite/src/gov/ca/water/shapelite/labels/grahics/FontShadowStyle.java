package gov.ca.water.shapelite.labels.grahics;

/**
 * An set Enum defining font styles
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public enum FontShadowStyle {
  PLAIN(),
  SHADOW(new CanvasOffset(1.0f, 1.0f)),
  ENGRAVED(new CanvasOffset(1.0f, 1.0f)),
  OUTLINED(new CanvasOffset(1.0f, 1.0f),
           new CanvasOffset(0.0f, 1.0f),
           new CanvasOffset(-1.0f, 1.0f),
           new CanvasOffset(-1.0f, 0.0f),
           new CanvasOffset(-1.0f, -1.0f),
           new CanvasOffset(0.0f, -1.0f),
           new CanvasOffset(1.0f, -1.0f),
           new CanvasOffset(1.0f, 0.0f)),
  HOLLOWED(new CanvasOffset(1.0f, 1.0f),
           new CanvasOffset(0.0f, 1.0f),
           new CanvasOffset(-1.0f, 1.0f),
           new CanvasOffset(-1.0f, 0.0f),
           new CanvasOffset(-1.0f, -1.0f),
           new CanvasOffset(0.0f, -1.0f),
           new CanvasOffset(1.0f, -1.0f),
           new CanvasOffset(1.0f, 0.0f));
  
  //<editor-fold defaultstate="collapsed" desc="Private FontShadowStyle Definition">
  /**
   * The array of Offset to draw the background text
   */
  public CanvasOffset[] shiftArray;
  /**
   * Private constructor with an array of offset directions
   * @param shiftArray
   */
  private FontShadowStyle(CanvasOffset...shiftArray) {
    CanvasOffset[] array = (shiftArray == null)? new CanvasOffset[]{}: shiftArray;
    this.shiftArray = array;
  }
//</editor-fold>
}
