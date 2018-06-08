package gov.ca.water.shapelite.labels;

import gov.ca.water.shapelite.symbology.TextSymbolizer;
import gov.ca.water.shapelite.data.DataFrame;
import gov.ca.water.shapelite.labels.grahics.MapCanvas;
import gov.ca.water.shapelite.map.layer.DatasetLayer;
import gov.ca.water.shapelite.map.PaintArgs;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * An base MapLayerDataset class for supporting all Label Dataset that extends
 * the {@link LabelDataset} class and using a {@link TextSymbolizer} for
 * rendering the labels. Both the Dataset and the symbolizer must be generically
 * compatible with the Label class TLabel</p>
 * <p>
 * This class has an generically implementation of the abstract {@linkplain #paint(
 * gov.ca.water.shapelite.map.PaintArgs) MapLayer.paint} method.
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 * @param <TDataset> extends LabelDataset
 * @param <TLabel> extends LabelBase
 * @param <TSym> extends SymbolizerLabel
 */
public abstract class LabelMapLayer<TDataset extends LabelDataset<TLabel>,
    TLabel extends LabelBase, TSym extends TextSymbolizer<TLabel>>
    extends DatasetLayer<TDataset, TSym> {

  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info
   * messages.
   */
  protected static final Logger LOGGER
      = Logger.getLogger(LabelMapLayer.class.getName());
  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor.
   */
  public LabelMapLayer() {
    super();
  }

  /**
   * Creates a new instance of the Base MapLayerLabel with a preset
   * <tt>dataset</tt> and
   * <tt>symbolizer</tt>.
   *
   * @param symbolizer the associated default Marker Symbolizer
   */
  public LabelMapLayer(TSym symbolizer) {
    super();
    this.setDefaultSymbolizer(symbolizer);
  }

  /**
   * Creates a new instance of the Base MapLayerLabel with a preset
   * <tt>dataset</tt> and
   * <tt>symbolizer</tt>.
   *
   * @param dataset the Dataset of type DatasetMarkerBase
   * @param symbolizer the associated default Marker Symbolizer
   */
  public LabelMapLayer(TDataset dataset, TSym symbolizer) {
    super(dataset, symbolizer);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="MapLayerDataset Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: This implementation check whether the dataset and symbolizer are
   * assigned. If not, it return the request unhandled.
   * <p>
   * Otherwise, the process is:<ul>
   * <li>Capture transform = args.graphics.transform; curFont =
   * args.graphics.font; and curColor = args.graphics.color to save current
   * settings</li>
   * <li>Initiate the canvas = new {@linkplain MapCanvas#MapCanvas(java.awt.Graphics2D,
   * gov.ca.water.shapelite.data.DataFrame) MapCanvas(arg.graphics, args.frame)}</li>
   * <li>Call {@link TextSymbolizer#prepareCanvas(gov.ca.water.shapelite.labels.grahics.MapCanvas) symbolizer.prepareCanvas(canvas)}
   * to set canvas to symbolizer's styles.</li>
   * <li>Get labels = {@linkplain LabelDataset#initLabels(
   * gov.ca.water.shapelite.labels.grahics.MapCanvas) this.dataset.initLabels(canvas)}
   * to initiate the labels for the current mapView and scale.</li>
   * <li>If (!labels.isEmpty), call {@link TextSymbolizer#paint(gov.ca.water.shapelite.labels.grahics.MapCanvas, gov.ca.water.shapelite.labels.LabelBase) symbolizer.paint(canvas, label)}
   * to paint each label in <tt>labels</tt></li>
   * <li>FINALLY, restore args.graphics transform,, color, and font with saved
   * settings.
   * </li>
   * </ul>
   * <p>
   * <b>NOTE:</b> All errors are trapped and logged.</p>
   */
  @Override
  public final void paint(PaintArgs args) {
    TDataset dataset;
    TSym symbolizer;
    Graphics2D graphics;
    DataFrame dataFrame;
    if (args == null) {
      return;
    }
    graphics = args.getGraphics();
    dataFrame = args.getFrame();
    dataset = this.getDataset();
    symbolizer = this.getDefaultSymbolizer();

    if (graphics == null || dataFrame == null || dataset == null
        || symbolizer == null) {
      return;
    }
    Font curFont = graphics.getFont();
    Color curColor = graphics.getColor();
    AffineTransform transform = graphics.getTransform();
    try {
      MapCanvas canvas = new MapCanvas(graphics, dataFrame);
      symbolizer.prepareCanvas(canvas);

      List<TLabel> labels = dataset.initLabels(canvas);

      if (labels != null && !labels.isEmpty()) {
        for (TLabel label : labels) {
          symbolizer.paint(canvas, label);
        }
      }
    } catch (Exception exp) {
      LOGGER.log(Level.WARNING, "{0}.paint Error:\n {1}",
          new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    } finally {
      symbolizer.resetCanvas();
      graphics.setColor(curColor);
      graphics.setFont(curFont);
      graphics.setTransform(transform);
    }
  }
  // </editor-fold>
}
