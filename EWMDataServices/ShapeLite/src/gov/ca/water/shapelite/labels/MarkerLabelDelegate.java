package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.data.marker.PointMarker;
import java.util.HashMap;

/**
 * An abstract delegate class for a delegate for initiating labels for the generic type
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TLabel> the generic label type (extends LabelBase)
 */
public abstract class MarkerLabelDelegate<TLabel extends LabelBase> {
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor  
   */
  protected MarkerLabelDelegate() {
  }
  // </editor-fold>

  /**
   * Call to initiate a new label representing the specified PointMarker. It calls 
   * {@link #getLabel(gov.ca.water.shapelite.Coord, java.lang.String)  
   * this.getLabel(marker.coordinate, marker.popupText)} and return the result. 
   * @param marker the marker to initiate the label for
   * @return an new label instance or null if marker or marker.coordinate != null.
   */
  public final TLabel getLabel(PointMarker marker) {
    TLabel result = null;
    Coord coord = null;
    if ((marker != null) && ((coord = marker.getCoordinate()) != null)) {
      result = this.getLabel(coord, marker.getName());
    }
    return result;
  }
  
  // <editor-fold defaultstate="collapsed" desc="Public Abstract Methods">
  /**
   * Call to update the symbolizer based on the properties attributes of the specified
   * shape or the shapes attributes.
   * @param shape the Shape to initiate the marker for
   * @param text the marker's text
   * @return an new marker instance 
   */
  public abstract TLabel getLabel(Coord coord, String text);
  // </editor-fold>
}
