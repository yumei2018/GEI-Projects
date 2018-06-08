package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.labels.grahics.MapCanvas;
import java.beans.FeatureDescriptor;
import java.util.HashMap;
import java.util.List;

/**
 * An abstract delegate class for a delegate for initiating labels for the generic type
 * for a Feature, which can be a point, a polyline, a polygon, or a multi-point shape 
 * type.
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TLabel> the generic label type (extends LabelBase)
 */
public abstract class FeatureLabelDelegate<TLabel extends LabelBase> {
  
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor  
   */
  protected FeatureLabelDelegate() {
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Public Abstract Methods">
  /**
   * Called by {@linkplain FeatureLabelDataset#initLabels(
   * gov.ca.water.shapelite.labels.grahics.MapCanvas) FeatureLabelDataset.initLabels} to
   * prepare the Feature Label Delegate for conditions of the canvas.
   * @param canvas the current MapConvas to draw the labels on.
   */
  public abstract void prepare(MapCanvas canvas);
  /**
   * Called by {@linkplain FeatureLabelDataset#initLabels(
   * gov.ca.water.shapelite.labels.grahics.MapCanvas) FeatureLabelDataset.initLabels} to
   * after generating the label to reset the processing settings.
   * @param canvas the current MapConvas to draw the labels on.
   */
  public abstract void reset();
  
  /**
   * Call to initiate a Label based on the geometry and attributes of the specified
   * shape.
   * @param shape the Shape to initiate the marker for
   * @param attrs the shape's attributes
   * @return a list if new labels for shape
   */
  public abstract List<TLabel> getLabels(Shape shape, HashMap<String,String> attrs);
  // </editor-fold>
}
