package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.data.dataset.Dataset;
import gov.ca.water.shapelite.labels.grahics.MapCanvas;
import gov.ca.water.shapelite.map.Mercator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * An abstract generic base Dataset class for managing any Map Labels</p>.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TLabel> extends LabelBase
 */
public abstract class LabelDataset<TLabel extends LabelBase> extends Dataset {

  /**
   * The LOGGER utility for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
      LabelDataset.class.getName());

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of markers stored in memory that make up this Dataset.
   */
  private List<TLabel> labels;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Parameterless constructor.
   */
  protected LabelDataset() {
    super();
    this.labels = new ArrayList<>();
    this.onCalculateEnvelope();
  }

  /**
   * Creates a new DatasetMarker from the specified list of markers.
   *
   * @param labels
   */
  protected LabelDataset(List<TLabel> labels) {
    super();
    this.setLabels(labels);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Methods">
  /**
   * Actual implementation of the calculate envelope method. It initiates a new
   * envelope and expands it to include the shape of each marker. If the dataset
   * contains no markers, it initiates an all inclusive envelope (the hole
   * world).
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  private void onCalculateEnvelope() {
    Envelope newEnv = new DefaultEnvelope();
    if ((this.labels != null) && (!this.labels.isEmpty())) {
      for (LabelBase label : this.labels) {
        Coord coord = label.getPoint();
        if (coord != null) {
          newEnv.expandToInclude(Mercator.toMerc(coord));
        }
      }
    }

    if (newEnv.isEmpty()) {
      newEnv = new DefaultEnvelope(-180, -90, 180, 90);
    }
    super.getEnvelopeMercator().copyProperties(newEnv);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Cycles through all of the markers and defines the envelope for those
   * markers. it Calls
   * {@linkplain #onCalculateEnvelope() this.doCalculateEnvelope} to calculate
   * the new envelope
   */
  public final void calculateEnvelope() {
    this.onCalculateEnvelope();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the list of markers stored in memory that make up this Dataset.
   *
   * @return the markers
   */
  public final List<TLabel> getLabels() {
    return this.labels;
  }

  /**
   * Sets the list of markers stored in memory that make up this Dataset.
   *
   * @param labels the markers to set
   */
  public final void setLabels(List<TLabel> labels) {
    if ((labels != null) && (!labels.isEmpty())) {
      if (this.labels != null) {
        this.labels.clear();
      }
      this.labels = labels;
      this.calculateEnvelope();
    } else {
      this.labels = new ArrayList<>();
      this.getEnvelopeMercator().copyProperties(new DefaultEnvelope(-180, -90, 180, 90));
    }
  }

  /**
   * Called to clear previously set labels
   */
  public final void clearLabels() {
    if (this.labels != null) {
      this.labels.clear();
    } else {
      this.labels = new ArrayList<>();
    }
    this.onCalculateEnvelope();
  }

  /**
   * Called by {@linkplain LabelMapLayer#paint(gov.ca.water.shapelite.map.PaintArgs)
   * LabelMapLayer.paint} to initiate the Dataset labels for the current mapView
   * (as defined by the {@linkplain MapCanvas}.
   * <p>
   * It calls {@linkplain #onInitLabels(gov.ca.water.shapelite.labels.grahics.MapCanvas)
   * this.onInitLabels(canvas)} to custom handle the request.
   * <p>
   * <b>NOTE:</b> It does not clear the current list before calling
   * this.onInitLabels. and all errors are trapped and logged. All labels are
   * cleared on error.
   * </p>
   *
   * @param canvas the current canvas.
   * @return this.getLabels() (can be and empty list)
   */
  public final List<TLabel> initLabels(MapCanvas canvas) {
    try {
      this.onInitLabels(canvas);
    } catch (Exception exp) {
      this.clearLabels();
      LOGGER.log(Level.WARNING, "{0}.initLabels Error:\n {1}",
          new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
    return this.getLabels();
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Protected Abstract Methods">
  /**
   * ABSTRACT: Called by {@linkplain #initLabels(gov.ca.water.shapelite.labels.grahics.MapCanvas)
   * this.initLabels} to custom handles the request to initiate a set of label
   * for drawing to the {@linkplain MapCanvas canvas} constraints.
   *
   * @param canvas the current MapCanvas to which the labels will be painted
   */
  protected abstract void onInitLabels(MapCanvas canvas);
  // </editor-fold>
}
