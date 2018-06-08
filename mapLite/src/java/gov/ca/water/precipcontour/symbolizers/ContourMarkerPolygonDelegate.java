package gov.ca.water.precipcontour.symbolizers;

import gov.ca.water.contours.ContourMap;
import gov.ca.water.contours.intervals.ContourIntervals;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.data.FeatureMarkerDelegate;
import gov.ca.water.shapelite.data.MarkerPath;
import gov.ca.water.shapelite.data.MarkerPolygon;
import gov.ca.water.shapelite.data.SymbolizerMarkerPath;
import gov.ca.water.shapelite.data.SymbolizerMarkerPolygon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;

/**
 * FeatureMarkerDelegate for generating the MarkPaths to draw Contour Lines
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class ContourMarkerPolygonDelegate 
                 extends FeatureMarkerDelegate<MarkerPolygon, SymbolizerMarkerPolygon>{

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  private ContourIntervals contourIntervals;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor  
   */
  public ContourMarkerPolygonDelegate(ContourIntervals contourIntervals) {
    super();  
    this.contourIntervals = contourIntervals;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="FeatureMarkerDelegate">
  /**
   * {@inheritDoc}
   * <p>OVERRIDE: Initiating the new MarkerPath and set marker.name = 
   * attrs[{@linkplain ContourMap#ContourFld}], initiate the SymbolizerMarkerPath's  
   * fillColor based on the attrs[{@linkplain ContourMap#ContourIdxFld}] setting, and
   * set the SymbolizerMarkerPath.stroke size=1.5.</p>
   */
  @Override
  public MarkerPolygon getMarker(Shape shape, HashMap<String, String> attrs) {
    MarkerPolygon result = null;
    if (shape != null) {
      String name = null;
      int contourIdx = -1;
      Color fillColor = Color.getHSBColor(0.0f , 0.0f, .75f);
      if (attrs != null) {
        name = (attrs.containsKey(ContourMap.ContourFld))? 
                                                  attrs.get(ContourMap.ContourFld) : null;
        contourIdx = (attrs.containsKey(ContourMap.ContourIdxFld))? 
                  Integer.parseInt(attrs.get(ContourMap.ContourIdxFld)) : -1;
        
        int numContours = 0;
        if ((contourIdx >= 0) && (this.contourIntervals != null) &&
                ((numContours = this.contourIntervals.getNumContours()) > 0)) {
          float colorIdx = (1.0f * (contourIdx+1))/(numContours * 2.0f);
          fillColor = Color.getHSBColor(colorIdx , .75f, .75f);
        }
      }
      result = new MarkerPolygon(shape, name);
      SymbolizerMarkerPolygon symbolizer = 
                                SymbolizerMarkerPolygon.simpleSymbolizer(fillColor,1.5f);
      result.setSymbolizer(symbolizer);
    }
    return result;
  }
  // </editor-fold>
}
