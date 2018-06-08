/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.symbology;

import gov.ca.water.shapelite.data.PointShapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * This class defines how a Marker will be symbolized.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointSymbolizer extends FeatureSymbolizer {

  /**
   * The size of the default icon on the map.
   */
  public static final int ICON_SIZE = 10;

  /**
   * The size of the default icon in the legend.
   */
  public static final int LEGEND_ICON_SIZE = 16;

  /**
   * The HSB ratio used for pastel coloring.
   */
  public static final float PASTEL = .8f;

  /**
   * The ratio of rounding to use for rounded rectangles.
   */
  public static final double ROUNDING = .25;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The color to surround the marker shape with. This is not used if an icon is
   * set.
   */
  private Color borderColor;
  /**
   * The color to fill the marker shape with. This is not used if an icon is
   * set.
   */
  private Color fillColor;
  /**
   * The height of the symbol in pixels. This is not used for drawing if a shape
   * is defined, but it is still used for determining intersection. So even if a
   * shape is set, this should be defined.
   */
  private int height;
  /**
   * The icon. If an icon exists, it overrides the typical behavior. If no
   * selectedIcon exists, when selected, this icon will be surrounded by a
   * rectangle of the selection border color.
   */
  private BufferedImage icon;
  /**
   * A boolean that, if true, will enable or disable using tool tips for the
   * markers. Tool tips also depend on the appropriate MapToolToolTip being
   * enabled on the map.
   */
  private boolean popupEnabled;
  /**
   * The color that surrounds markers when they are selected. This is not used
   * if the selectedIcon is not null. By default this color is cyan.
   */
  private Color selectedBorderColor;
  /**
   * The selected icon. If the icon is selected, this will be checked first. If
   * this is not defined, but an icon is defined, then the icon will be
   * surrounded by a rectangle in the selected border color.
   */
  private BufferedImage selectedIcon;
  /**
   * An optional <code>java.awt.Shape</code> to use for drawing the symbol. If
   * this is set, it will override the default circular shape.
   */
  private Shape shape;
  /**
   * The width of the symbol in pixels. This is not used for drawing if a shape
   * is defined, but it is still used for determining intersection. So even if a
   * shape is set, this should be defined.
   */
  private int width;

  /**
   * This specifies an enumeration that can be used to build a default shape.
   * The Icon or Shape property will override this property.
   */
  private PointShapes pointShape;

  /**
   * Controls the rotation angle for the shape in degrees.
   */
  private double angle;

  /**
   * Controls the X and Y offset in pixel coordinates. This shift will be
   * applied to the marker when drawing.
   */
  private Point.Double offset;

  /**
   * Controls the width of the image appearing in the legend.
   */
  private int legendWidth;

  /**
   * Controls the height of the image appearing in the legend.
   */
  private int legendHeight;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of the SymbolizerMarker class.
   */
  public PointSymbolizer() {
    Random rnd = new Random();
    this.fillColor = Color.getHSBColor(rnd.nextFloat(), PASTEL, PASTEL);
    this.borderColor = Color.BLACK;
    this.width = ICON_SIZE;
    this.height = ICON_SIZE;
    this.selectedBorderColor = Color.CYAN;
    this.popupEnabled = true;
    this.pointShape = PointShapes.Ellipse;
    this.legendHeight = LEGEND_ICON_SIZE;
    this.legendWidth = LEGEND_ICON_SIZE;
  }

  /**
   * Creates a new instance of the SymbolizerMarker class.
   *
   * @param fillColor The color to use as the fill color for this symbolizer.
   */
  public PointSymbolizer(Color fillColor) {
    this();
    this.fillColor = fillColor;
  }

  /**
   * Creates a new instance of the SymbolizerMarker class with the specified
   * size.
   *
   * @param fillColor The fill color for the marker.
   * @param width integer width in pixels
   * @param height integer height in pixels
   */
  public PointSymbolizer(Color fillColor, int width, int height) {
    this();
    this.fillColor = fillColor;
    this.width = width;
    this.height = height;
  }

  /**
   * Creates a new instance of the SymbolizerMarker class with the specified
   * size.
   *
   * @param fillColor The fill color for the marker.
   * @param width integer width in pixels
   * @param height integer height in pixels
   * @param pointShape The PointShapes enumeration defining a preset pointShape
   * type.
   */
  public PointSymbolizer(Color fillColor, int width, int height, PointShapes pointShape) {
    this();
    this.fillColor = fillColor;
    this.width = width;
    this.height = height;
    this.pointShape = pointShape;
  }

  //</editor-fold>
  /**
   * Copy buffered image.
   *
   * @param source The BufferedImage to copy.
   * @return BufferedImage.
   */
  private static BufferedImage copy(BufferedImage source) {
    if (source == null) {
      return null;
    }
    BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(),
        source.getType());
    Graphics g = b.getGraphics();
    g.drawImage(source, 0, 0, null);
    g.dispose();
    return b;
  }

  /**
   * Creates a copy of this symbolizer marker.
   *
   * @return A PointSymbolizer.
   */
  public PointSymbolizer copy() {
    PointSymbolizer result = new PointSymbolizer();
    result.copyProperties(this);
    return result;
  }

  /**
   * Deep copies the properties of the specified item.
   *
   * @param other
   */
  public void copyProperties(PointSymbolizer other) {
    borderColor = other.borderColor;
    fillColor = other.fillColor;
    height = other.height;
    icon = copy(other.icon);
    popupEnabled = other.popupEnabled;
    selectedBorderColor = other.selectedBorderColor;
    selectedIcon = copy(other.selectedIcon);
    shape = other.shape; // TO DO: Deep Clone (preferably not requiring commons library)
    width = other.width;
    pointShape = other.pointShape;
    angle = other.angle;
    offset = other.offset;
    legendWidth = other.legendWidth;
    legendHeight = other.legendHeight;
    super.setLabelField(other.getLabelField());
  }

  /**
   * Gets a BufferedImage the size of the symbol that represents and unselected
   * point made by this symbolizer.
   *
   * @return
   */
  @Override
  public final BufferedImage getLegendIcon() {
    BufferedImage result = new BufferedImage(this.legendWidth,
        this.legendHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = result.createGraphics();
    Point center = new Point(legendWidth / 2, legendHeight / 2);
    Rectangle box = new Rectangle(center.x - width / 2, center.y
        - height / 2, width, height);
    paint(g, box);
    return result;
  }

  /**
   * Draws the symbol onto the specified graphics within the specified box. If
   * the "Shape" property is set, the shape will be centered in the box, but the
   * dimensions of the shape will not be affected by the box.
   *
   * @param g The Graphics2D object to draw on.
   * @param box The Rectangle box to draw inside of.
   */
  public final void paint(Graphics2D g, Rectangle box) {

    // move the shape to the position of the marker.
    AffineTransform original = g.getTransform();
    g.getTransform().rotate(angle);
    // Icon rendering takes priority
    if (icon != null) {
      g.drawImage(icon, box.x, box.y, box.width, box.height, null);
    } else if (shape != null) {
      // If the icon is null, but the shape is defined, draw the shape instead.
      g.translate(box.x + box.width / 2, box.y + box.height / 2);

      // Antialiasing for smooth curves, not pixelated curves.
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);

      // Fill the shape first, so the border is not hidden by the fill.
      g.setColor(fillColor);
      g.fill(shape);

      g.setStroke(new BasicStroke(1));
      g.setColor(borderColor);

      g.draw(shape);

    } else {
      // The default circle shape
      // Antialiasing for smooth curves, not pixelated curves.
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);

      // Fill the shape first so the fill doesn't hide the border
      g.setColor(fillColor);
      Rectangle inner = new Rectangle(box.x, box.y, box.width - 1, box.height - 1);
      Shape symbol = createShape(inner);
      g.fill(symbol);
      // If the marker is selected, draw a thick border in the selectedBorderColor.
      g.setStroke(new BasicStroke(1));
      g.setColor(borderColor);
      // draw the border to the graphics surface.
      g.draw(symbol);
    }
    // Reset the transform
    g.setTransform(original);

  }

  /**
   * Gets the shape based on the enumeration defining the shape. This makes it
   * easier to select some basic options.
   *
   * @param box The box to encapsulate the shape.
   * @return A java.awt.Shape for the specified shape.
   */
  public final Shape createShape(Rectangle box) {
    switch (pointShape) {
      case Ellipse:
        return getEllipse(box);
      case RoundedRectangle:
        return getRoundRectangle(box);
      case Square:
        return getSquare(box);
      case TriangleDown:
        return getDownTriangle(box);
      case TriangleUp:
        return getUpTriangle(box);
      case Star:
        return getStar(box);
      default:
        return getEllipse(box);
    }

  }

  /**
   * Gets the star shape based on the rectangle box.
   *
   * @param box The rectangle box that contains the star shape.
   * @return A java.awt.Shape for the star.
   */
  private Shape getStar(Rectangle box) {
    GeneralPath result = new GeneralPath();
    double w = box.width;
    double h = box.height;
    double x = box.x;
    double y = box.y;

    result.moveTo(x + 0.5000 * w, y + 0.0000 * h);
    result.lineTo(x + 0.6129 * w, y + 0.3815 * h);
    result.lineTo(x + 1.0000 * w, y + 0.3815 * h);
    result.lineTo(x + 0.6828 * w, y + 0.6188 * h);
    result.lineTo(x + 0.8065 * w, y + 1.0000 * h);
    result.lineTo(x + 0.5000 * w, y + 0.7563 * h);
    result.lineTo(x + 0.1881 * w, y + 1.0000 * h);
    result.lineTo(x + 0.3225 * w, y + 0.6188 * h);
    result.lineTo(x + 0.0000 * w, y + 0.3815 * h);
    result.lineTo(x + 0.3815 * w, y + 0.3815 * h);
    result.lineTo(x + 0.5000 * w, y + 0.0000 * h);
    return result;
  }

  /**
   * Gets an ellipse shape contained in the box.
   *
   * @param box The box that contains the ellipse.
   * @return Shape
   */
  private Shape getEllipse(Rectangle box) {
    return new Ellipse2D.Double(box.x, box.y, box.width, box.height);
  }

  /**
   * Gets an upward pointing triangle.
   *
   * @param box The rectangular box that contains the triangle.
   * @return The java.awt.Shape to use.
   */
  private Shape getUpTriangle(Rectangle box) {
    GeneralPath result = new GeneralPath();
    result.moveTo(box.x, box.y + box.height);
    result.lineTo(box.x + box.width / 2.0, box.y);
    result.lineTo(box.x + box.width, box.y + height);
    result.lineTo(box.x, box.y + box.height);
    return result;
  }

  /**
   * Gets the downward pointing triangle as a java.awt.Shape.
   *
   * @param box The rectangular box that contains the triangle.
   * @return A java.awt.Shape of the triangle.
   */
  private Shape getDownTriangle(Rectangle box) {
    GeneralPath result = new GeneralPath();
    result.moveTo(box.x, box.y);
    result.lineTo(box.x + box.width / 2.0, box.y + height);
    result.lineTo(box.x + box.width, box.y);
    result.lineTo(box.x, box.y);
    return result;
  }

  /**
   * Gets a square java.awt.Shape.
   *
   * @param box The rectangle box.
   * @return The Shape object for a square.
   */
  private Shape getSquare(Rectangle box) {
    return new Rectangle2D.Double(box.x, box.y, box.width, box.height);
  }

  /**
   * Gets a rounded rectangle as a java.awt.Shape.
   *
   * @param box The rectangle size to return.
   * @return The rounded rectangle shape.
   */
  private Shape getRoundRectangle(Rectangle box) {
    return new RoundRectangle2D.Double(box.x, box.y, box.width, box.height,
        box.width * ROUNDING, box.height * ROUNDING);

  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the color to surround the marker shape with. This is not used if an
   * icon is set.
   *
   * @return the borderColor
   */
  public final Color getBorderColor() {
    return borderColor;
  }

  /**
   * Sets the color to surround the marker shape with. This is not used if an
   * icon is set.
   *
   * @param borderColor the borderColor to set
   */
  public final void setBorderColor(Color borderColor) {
    this.borderColor = borderColor;
  }

  /**
   * Gets the color to fill the marker shape with. This is not used if an icon
   * is set.
   *
   * @return the fillColor
   */
  public final Color getFillColor() {
    return fillColor;
  }

  /**
   * Sets the color to fill the marker shape with. This is not used if an icon
   * is set.
   *
   * @param fillColor the fillColor to set
   */
  public final void setFillColor(Color fillColor) {
    this.fillColor = fillColor;
  }

  /**
   * Gets the height of the symbol in pixels. This is not used for drawing if a
   * shape is defined, but it is still used for determining intersection. So
   * even if a shape is set, this should be defined.
   *
   * @return the height
   */
  public final int getHeight() {
    if (icon != null) {
      return icon.getWidth(null);
    }
    return height;
  }

  /**
   * Sets the height of the symbol in pixels. This is not used for drawing if a
   * shape is defined, but it is still used for determining intersection. So
   * even if a shape is set, this should be defined.
   *
   * @param height the height to set
   */
  public final void setHeight(int height) {
    this.height = height;
  }

  /**
   * Gets the icon. If an icon exists, it overrides the typical behavior. If no
   * selectedIcon exists, when selected, this icon will be surrounded by a
   * selection border color.
   *
   * @return the icon
   */
  public final BufferedImage getIcon() {
    return icon;
  }

  /**
   * Sets the icon. If an icon exists, it overrides the typical behavior. If no
   * selectedIcon exists, when selected, this icon will be surrounded by a
   * selection border color.
   *
   * @param icon the icon to set
   */
  public final void setIcon(BufferedImage icon) {
    this.icon = icon;
  }

  /**
   * Gets the Double precision point offset that controls the X and Y offset in
   * pixel coordinates. This shift will be applied to the marker when drawing.
   * The offset is ignored in the legend.
   *
   * @return the offset
   */
  public final Point.Double getOffset() {
    return offset;
  }

  /**
   * Gets the Double precision point offset that controls the X and Y offset in
   * pixel coordinates. This shift will be applied to the marker when drawing.
   * The offset is ignored in the legend.
   *
   * @param offset the offset to set
   */
  public final void setOffset(Point.Double offset) {
    this.offset = offset;
  }

  /**
   * Gets a boolean that, if true, will enable or disable using tool tips for
   * the markers. Tool tips also depend on the appropriate MapToolToolTip being
   * enabled on the map.
   *
   * @return the popupEnabled
   */
  public final boolean isPopupEnabled() {
    return popupEnabled;
  }

  /**
   * Sets a boolean that, if true, will enable or disable using tool tips for
   * the markers. Tool tips also depend on the appropriate MapToolToolTip being
   * enabled on the map.
   *
   * @param popupEnabled the popupEnabled to set
   */
  public final void setPopupEnabled(boolean popupEnabled) {
    this.popupEnabled = popupEnabled;
  }

  /**
   * Gets a PointShapes enumeration that specifies one of a few default shapes
   * to use for symbolizing. If the Shape or Icon property are specified, this
   * property will be ignored.
   *
   * @return the pointShape
   */
  public final PointShapes getPointShape() {
    return pointShape;
  }

  /**
   * Sets a PointShapes enumeration that specifies one of a few default shapes
   * to use for symbolizing. If the Shape or Icon property are specified, this
   * property will be ignored.
   *
   * @param pointShape the pointShape to set
   */
  public final void setPointShape(PointShapes pointShape) {
    this.pointShape = pointShape;
  }

  /**
   * Gets the color that surrounds markers when they are selected. By default
   * this color is cyan. This is not used if the selectedIcon is not null.
   *
   * @return the selectedFillColor
   */
  public final Color getSelectedBorderColor() {
    return selectedBorderColor;
  }

  /**
   * Sets the color that surrounds the markers when they are selected. By
   * default this color is cyan. This is not used if the selectedIcon is not
   * null.
   *
   * @param selectedFillColor the selectedFillColor to set
   */
  public final void setSelectedBorderColor(Color selectedFillColor) {
    this.selectedBorderColor = selectedFillColor;
  }

  /**
   * Gets an optional <code>java.awt.Shape</code> to use for drawing the symbol.
   * If this is set, it will override the default circular shape.
   *
   * @return the shape
   */
  public final Shape getShape() {
    return shape;
  }

  /**
   * Sets an optional <code>java.awt.Shape</code> to use for drawing the symbol.
   * If this is set, it will override the default circular shape.
   *
   * @param shape the shape to set
   */
  public final void setShape(Shape shape) {
    this.shape = shape;
  }

  /**
   * Gets the selected icon. If the icon is selected, this will be checked
   * first. If this is not defined, but an icon is defined, then the icon will
   * be surrounded by a rectangle in the selected border color.
   *
   * @return the selectedIcon
   */
  public final BufferedImage getSelectedIcon() {
    return selectedIcon;
  }

  /**
   * Sets the selected icon. If the icon is selected, this will be checked
   * first. If this is not defined, but an icon is defined, then the icon will
   * be surrounded by a rectangle in the selected border color.
   *
   * @param selectedIcon the selectedIcon to set
   */
  public final void setSelectedIcon(BufferedImage selectedIcon) {
    this.selectedIcon = selectedIcon;
  }

  /**
   * Gets the width of the symbol in pixels. This is not used for drawing if a
   * shape is defined, but it is still used for determining intersection. So
   * even if a shape is set, this should be defined.
   *
   * @return the width
   */
  public final int getWidth() {
    if (icon != null) {
      return icon.getHeight(null);
    }
    return width;
  }

  /**
   * Sets the width of the symbol in pixels. This is not used for drawing if a
   * shape is defined, but it is still used for determining intersection. So
   * even if a shape is set, this should be defined.
   *
   * @param width the width to set
   */
  public final void setWidth(int width) {
    this.width = width;
  }

  //</editor-fold>
  /**
   * @return the legendWidth
   */
  public final int getLegendWidth() {
    return legendWidth;
  }

  /**
   * @param legendWidth the legendWidth to set
   */
  public final void setLegendWidth(int legendWidth) {
    this.legendWidth = legendWidth;
  }

  /**
   * @return the legendHeight
   */
  public final int getLegendHeight() {
    return legendHeight;
  }

  /**
   * @param legendHeight the legendHeight to set
   */
  public final void setLegendHeight(int legendHeight) {
    this.legendHeight = legendHeight;
  }

}
