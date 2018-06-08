package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.labels.grahics.MapCanvas;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class SimpleFeatureLabelDataset extends FeatureLabelDataset<ContourLabel> {

  /**
   * By default the contour is set up to look for a field named ELEVATION.
   */
  private static final String ELEVATION = "ELEVATION";

  /**
   * The maximum spacing between labels positioned on a single line measured in
   * font points.
   */
  private static final Double MAX_SPACING = 250.0;

  /**
   * The minimum spacing between labels positionined on a single line measured
   * in font points.
   */
  private static final Double MIN_SPACING = 50.0;

  /**
   * The default number of labels for each part.
   */
  private static final int NUM_LABELS = 4;

  /**
   * The LOGGER utility for this class.
   */
  private static final Logger LOGGER =
      Logger.getLogger(SimpleFeatureLabelDataset.class.getName());

  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The attribute field to use as a label descriptor.
   */
  private String contourField;

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor.
   */
  public SimpleFeatureLabelDataset() {
    super();
    contourField = ELEVATION;
  }

  /**
   *
   * @param fs
   */
  public SimpleFeatureLabelDataset(FeatureSet fs){
    super(fs, new FeatureLabelDelegate<ContourLabel>() {

      @Override
      public void prepare(MapCanvas canvas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

      @Override
      public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }

      @Override
      public List<ContourLabel> getLabels(Shape shape, HashMap<String, String> attrs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      }
    });
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Private/Protected Methods">
  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Called to assign the Contour FeatureSet and initiate the ContourLabeler
   * delegate.
   *
   * @param contours The Featureset with contours to label.
   */
  public final void initContours(FeatureSet contours) {
    this.clearLabels();
    try {
      Double maxSpacing = MAX_SPACING; //points
      Double minSpacing = MIN_SPACING; //points
      SimpleFeatureLabelerDelegate delegate
          = new SimpleFeatureLabelerDelegate(NUM_LABELS, minSpacing, maxSpacing) {
            @Override
            protected String getCountourLabel(Double contourValue,
                HashMap<String, String> attrs) {
              return attrs.get(getContourField());
            }
          };

      this.initDataset(contours, delegate);
    } catch (Exception exp) {
      LOGGER.log(Level.WARNING, "{0}.initContours Error:\n {1}",
          new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
  }
  // </editor-fold>

  /**
   * Gets the attribute field to use as a label descriptor.
   * @return the String contourField.
   */
  public final String getContourField() {
    return contourField;
  }

  /**
   * Sets the attribute field to use as a label descriptor.
   * @param contourField the contourField to set
   */
  public final void setContourField(String contourField) {
    this.contourField = contourField;
  }

}
