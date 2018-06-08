package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.data.*;
import gov.ca.water.shapelite.labels.grahics.MapCanvas;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * An abstract generic base Dataset class for managing any Map Labels</p>
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TLabel> extends LabelBase
 */
public abstract class MarkerLabelDataset<TLabel extends LabelBase>
        extends LabelDataset<TLabel> {

  /**
   * The LOGGER utility for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
          MarkerLabelDataset.class.getName());

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The list of markers stored in memory that make up this Dataset.
   */
  private List<TLabel> markerLabels;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Parameterless constructor
   */
  protected MarkerLabelDataset() {
    super();
  }

  /**
   * Creates a new DatasetMarker from the specified list of markers.
   *
   * @param labels
   */
  protected MarkerLabelDataset(List<TLabel> labels) {
    super(labels);
  }
  //</editor-fold>

  /**
   * Called to initiate the Dataset from the specified <tt>markerList</tt> using
   * the defined <tt>delegate</tt> to initiate each marker's label
   *
   * @param markerList the source list of markers
   * @param delegate the MarkerLabelDelegate
   */
  protected void initLabels(List<PointMarker> markerList, MarkerLabelDelegate<TLabel> delegate) {
    try {
      this.clearLabels();
      if (delegate == null) {
        throw new Exception("The Dataset cannot be initiated if the Marker Label "
                + "Delegate is unassigned.");
      }
      if ((markerList == null) || (markerList.isEmpty())) {
        return;
      }

      TLabel label;
      List<TLabel> newLabels = new ArrayList<>();
      for (PointMarker marker : markerList) {
        if ((marker != null) && (label = delegate.getLabel(marker)) != null) {
          newLabels.add(label);
        }
      }

      this.setLabels(newLabels);
    } catch (Exception exp) {
      LOGGER.log(Level.WARNING, "{0}.initLabels Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Override LabelDataset">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Do Nothing the labels are initiated when the markers are
   * assigned.</p>
   */
  @Override
  protected void onInitLabels(MapCanvas canvas) {
  }
  // </editor-fold>
}
