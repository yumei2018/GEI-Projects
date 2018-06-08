package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.labels.grahics.MapCanvas;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An abstract generic base Dataset class for managing any Map Labels.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TLabel> extends LabelBase
 */
public abstract class FeatureLabelDataset<TLabel extends LabelBase>
        extends LabelDataset<TLabel> {

  /**
   * The LOGGER for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
          FeatureLabelDataset.class.getName());

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of markers stored in memory that make up this Dataset.
   */
  private FeatureSet featureSet;

  /**
   * The delegate that should be used to describe the labels.
   */
  private FeatureLabelDelegate<TLabel> labelDelegate;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Parameterless constructor.
   */
  protected FeatureLabelDataset() {
    super();
    this.featureSet = null;
    this.labelDelegate = null;
  }

  /**
   * Creates a new DatasetMarker from the specified FeatureSet and
   * FeatureLabelDelegate.
   *
   * @param featureSet the featureSet to label
   * @param labelDelegate the labeDelegate to use for Labeling
   */
  protected FeatureLabelDataset(FeatureSet featureSet,
          FeatureLabelDelegate<TLabel> labelDelegate) {
    super();
    if (labelDelegate == null) {
      throw new NullPointerException("The Dataset cannot be initiated if the "
              + "Feature Label Delegate is unassigned.");
    }
    this.featureSet = featureSet;
    this.labelDelegate = labelDelegate;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Called to initiate the Dataset from the specified <tt>featureSet</tt> using
   * the defined <tt>delegate</tt> to initiate each feature's label
   *
   * @param featureSet the source FeatureSet
   * @param delegate the FeatureLabelDelegate
   */
  public final void initDataset(FeatureSet featureSet,
          FeatureLabelDelegate<TLabel> delegate) {
    try {
      this.clearLabels();
      if (delegate == null) {
        throw new Exception("The Dataset cannot be initiated if the Feature Label "
                + "Delegate is unassigned.");
      }
      this.featureSet = featureSet;
      this.labelDelegate = delegate;
    } catch (Exception exp) {
      LOGGER.log(Level.WARNING, "{0}.initLabels Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
  }

  /**
   * Called to initiate the Dataset from the specified <tt>featureSet</tt> using
   * the defined <tt>delegate</tt> to initiate each feature's label
   *
   */
  @Override
  protected void onInitLabels(MapCanvas canvas) {
    try {
      this.clearLabels();
      List<Feature> features = null;
      if ((this.labelDelegate == null) || (featureSet == null) || (canvas == null)
              || ((features = featureSet.getFeatures()) == null) || (features.isEmpty())) {
        return;
      }

      this.labelDelegate.prepare(canvas);

      boolean sameProjection = true;
      ProjectionInfo wgs84 = Projections.getGeographic().getWorld().getWGS1984();
      ProjectionInfo sourceProjection = null;
      if ((featureSet.getProjectionESRI() != null) && ((sourceProjection
              = ProjectionInfo.fromEsriString(featureSet.getProjectionESRI())) != null)) {
        sameProjection = wgs84.equals(sourceProjection);
      }

      List<TLabel> newLabels = new ArrayList<>();
      List<TLabel> featureLabels;
      for (Feature feature : features) {
        Shape shape = feature.getShape();
        if ((shape != null) && (!sameProjection)) {
          shape = Reproject.reprojectShape(shape, sourceProjection, wgs84);
        }
        featureLabels = this.labelDelegate.getLabels(shape, feature.getAttributes());
        if ((shape != null)
                && ((featureLabels) != null)
                && (!featureLabels.isEmpty())) {
          newLabels.addAll(featureLabels);
        }
      }

      this.setLabels(newLabels);
    } catch (Exception exp) {
      LOGGER.log(Level.WARNING, "{0}.initLabels Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
  }
  // </editor-fold>
}
