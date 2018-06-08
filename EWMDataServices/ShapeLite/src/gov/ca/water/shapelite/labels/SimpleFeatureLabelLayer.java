package gov.ca.water.shapelite.labels;
import gov.ca.water.shapelite.symbology.LabelSymbolizer;
import gov.ca.water.shapelite.labels.grahics.CanvasFont;
import gov.ca.water.shapelite.labels.grahics.CanvasOffset;
import gov.ca.water.shapelite.labels.grahics.FillStyle;
import gov.ca.water.shapelite.labels.grahics.FontShadowStyle;
import gov.ca.water.shapelite.labels.grahics.LineStyle;
import java.awt.Color;

/**
 *
 * @author J.G. "Koos" Prins, D.Eng. PE.
 */
public class SimpleFeatureLabelLayer extends
    LabelMapLayer<SimpleFeatureLabelDataset, ContourLabel,
    LabelSymbolizer<ContourLabel>> {


  /**
   * The minimum text offset in font points.
   */
  private static final float MIN_OFFSET = 1.5f;

  /**
   * The maximum text offset in font points.
   */
  private static final float MAX_OFFSET = 2.5f;

  /**
   * The label offset from the anchor position measured in font points.
   */
  private static final float LABEL_OFFSET = 4.0f;

  /**
   * The font size for the labels.
   */
  private static final float FONT_SIZE = 9.0f;

  /**
   * Sets the maximum zoom scale for which labels will be created.
   */
  private static final double MAX_SCALE = 4000000;

  // <editor-fold defaultstate="collapsed" desc="Private Fields">

  /**
   * The style object controlling how the labels will appear.
   */
  private LabelStyle labelStyle;

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Public Constructor.
   */
  public SimpleFeatureLabelLayer() {

    LabelSymbolizer<ContourLabel> symbolizer = new LabelSymbolizer<>();

    CanvasFont font = new CanvasFont();
    font.setFontSize(FONT_SIZE);
    font.setColor(Color.BLACK);
    font.setFontShadows(FontShadowStyle.OUTLINED, 2.0f, Color.WHITE);

    labelStyle = symbolizer.getLabelStyle();
    labelStyle.setStyle(font, PositionLabel.CENTER, new CanvasOffset(LABEL_OFFSET,
        LABEL_OFFSET));
    BoxStyleLabel boxStyle = labelStyle.getLabelBoxStyle();

    LineStyle borderStyle = boxStyle.getBorderStyle();
    borderStyle.setColor(Color.yellow);
    borderStyle.setStyle(LineStyle.Style.None);
    borderStyle.setThickness(LineStyle.Thickness.Thin);

    FillStyle fill = boxStyle.getFillStyle();
    fill.setFillColors(Color.WHITE, Color.WHITE);
    fill.setStyle(FillStyle.Style.None);

    TextOffsets txtOffsets = boxStyle.getTextOffsets();
    txtOffsets.setTop(MIN_OFFSET);
    txtOffsets.setLeft(MAX_OFFSET);
    txtOffsets.setBottom(MIN_OFFSET);
    txtOffsets.setRight(MAX_OFFSET);

    this.setDefaultSymbolizer(symbolizer);

    this.setMaxScale(MAX_SCALE);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="LabelMapLayer Overrides">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: If the dataset is not yet initiated, initiate and assign the
   * layer's dataset. This method can be called to get and initiate the
   * dataset.</p>
   *
   * @return The SimpleFeatureLableDataset to use for this layer.
   */
  @Override
  public final SimpleFeatureLabelDataset getDataset() {
    SimpleFeatureLabelDataset result = super.getDataset();
    if (result == null) {
      result = new SimpleFeatureLabelDataset();
      this.setDataset(result);
    }
    return result;
  }
  // </editor-fold>

  /**
   * Gets the style object controlling how the labels will appear.
   * @return the labelStyle
   */
  public final LabelStyle getLabelStyle() {
    return labelStyle;
  }

  /**
   * Sets the style object controlling how the labels will appear.
   * @param labelStyle the labelStyle to set
   */
  public final void setLabelStyle(LabelStyle labelStyle) {
    this.labelStyle = labelStyle;
  }


}
