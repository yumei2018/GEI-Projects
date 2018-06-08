package entity.core.map.util;

import entity.core.map.data.ClipOptions;
import entity.core.map.data.MosaicCollection;
import entity.core.map.data.Overlayable;
import entity.core.map.data.FeatureLayer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONObject;

public class ImageUtil implements Serializable {

  public static final Logger logger = Logger.getLogger(ImageUtil.class.getName());

  /**
   * 
   * @param url
   * @return
   * @throws Exception 
   */
  public static BufferedImage readImage(URL url) throws Exception {
    BufferedImage image = null;
    try {
      image = ImageIO.read(url);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error reading image, " + e.getMessage());
      throw e;
    }
    return image;
  }

  /**
   * 
   * @param collection
   * @return
   * @throws Exception 
   */
  public static BufferedImage mosaicImages(MosaicCollection collection) throws Exception {
    BufferedImage mosaic = null;
    BufferedImage clipImage = null;
    BufferedImage[][] orderedCollection = null;
    //no of images
    int rows = 0;
    int cols = 0;
    //image properties
    int width = 0;
    int height = 0;
    int imageType = 0;
    //return clip
    boolean returnClip = false;
    try {
      if (collection != null) {
        //check if minRow, minCol values are valid
        int minRow = collection.getMinRow();
        int minCol = collection.getMinCol();
        int maxRow = collection.getMaxRow();
        int maxCol = collection.getMaxCol();
        if (minRow == 999999 || minCol == 999999 || maxRow == -1 || maxCol == -1) {
          throw new Exception("Mosaic Collection invalid.");
        } else {
          //read images
          rows = maxRow - minRow + 1;
          cols = maxCol - minCol + 1;
          orderedCollection = new BufferedImage[rows][cols];
          for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
              orderedCollection[i][j] = readImage(collection.getLayer(minRow + i, minCol + j).getUrl());
            }
          }
          //validate images
          if (orderedCollection != null) {
            width = orderedCollection[0][0].getWidth();
            height = orderedCollection[0][0].getHeight();
            imageType = orderedCollection[0][0].getType();
            //writing imageType RGB Alpha
            imageType = imageType == 0 ? BufferedImage.TYPE_INT_ARGB : imageType;
            for (int i = 0; i < rows; i++) {
              for (int j = 0; j < cols; j++) {
                if (orderedCollection[i][j].getWidth() != width || orderedCollection[i][j].getHeight() != height) {
                  throw new Exception("Images are of different sizes.");
                }
              }
            }
          }
          //validation complete, start mosaic
          mosaic = new BufferedImage(width * cols, height * rows, imageType);
          WritableRaster raster = mosaic.getRaster();
          int xOffset = 0;
          int yOffset = 0;
          for (int i = 0; i < rows; i++) {
            //reset xOffset
            xOffset = 0;
            for (int j = 0; j < cols; j++) {
              raster.setRect(xOffset, yOffset, orderedCollection[i][j].getData());
              xOffset += width;
            }
            yOffset += height;
          }
          //mosaic complete, clip based on clip options
          ClipOptions options = collection.getClipOptions();
          if (options != null) {
            //clip
            int offsetX = options.getOffsetX();
            int offsetY = options.getOffsetY();
            int nWidth = options.getClipWidth();
            int nHeight = options.getClipHeight();
            if ((offsetX + nWidth) > mosaic.getWidth() || (offsetY + nHeight) > mosaic.getHeight()) {
              throw new Exception("Invalid Clip Options.");
            }
            clipImage = mosaic.getSubimage(offsetX, offsetY, nWidth, nHeight);
            returnClip = true;
          }
        }
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error Mosaicing Images, " + e.getMessage());
      throw e;
    }
    if (returnClip) {
      return clipImage;
    } else {
      return mosaic;
    }
  }

  /**
   * 
   * @param overlays
   * @return
   * @throws Exception 
   */
  public static BufferedImage overlayImages(List<? extends Overlayable> overlays) throws Exception {
    BufferedImage image = null;
    try {
      //validate overlayable images
      if (overlays != null && overlays.size() > 0) {
        Overlayable ref = overlays.get(0);
        for (int i = 1; i < overlays.size(); i++) {
          if (!(ref.getWidth() == overlays.get(i).getWidth()) && (ref.getHeight() == overlays.get(i).getHeight())) {
            //overlays not of same size.
            throw new Exception("Images being overlaid are not in same size.");
          }
        }
        //validation complete
        image = new BufferedImage(ref.getWidth(), ref.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        int j = 0;
        for (Overlayable layer : overlays) {
          if (layer instanceof FeatureLayer) {
            Stroke drawingStroke = new BasicStroke(12.0f);
            graphics.setStroke(drawingStroke);
            List<String> imageTypes = ((FeatureLayer) layer).getImageTypes();
            if (imageTypes.get(0).equalsIgnoreCase("outline")) {
              graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.6));
            } else {
              graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, layer.getTransparency()));
            }
          } else {
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, layer.getTransparency()));
          }
          graphics.drawImage(layer.getImage(), 0, 0, null);
        }
        //dispose Graphics
        graphics.dispose();
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error Overlaying Images, " + e.getMessage());
      throw e;
    }
    return image;
  }

  /**
   * 
   * @param layer
   * @return
   * @throws Exception 
   */
  public static BufferedImage generateFeatureRepresentation(FeatureLayer layer) throws Exception {
    BufferedImage image = null;
    try {
      image = new BufferedImage(layer.getWidth(), layer.getHeight(), BufferedImage.TYPE_INT_ARGB);
      Graphics2D graphics = image.createGraphics();
      graphics.setStroke(new BasicStroke(1.5f));
      List<Shape> shapes = layer.getShapes();
      List<Color> colors = layer.getColors();
      List<String> imageTypes = layer.getImageTypes();
      List<Double> transList = layer.getTransparentList();
      if (shapes != null && colors != null) {
        for (int i = 0; i < shapes.size(); i++) {
          Color c = colors.get(i);
          Shape s = shapes.get(i);
          graphics.setColor(c);
          graphics.setRenderingHint(
                  RenderingHints.KEY_ANTIALIASING,
                  RenderingHints.VALUE_ANTIALIAS_ON);
          if (!imageTypes.get(i).equalsIgnoreCase("outline")) {
            graphics.fill(shapes.get(i));
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transList.get(i).floatValue()));
          } else {
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0));
          }
          graphics.draw(s);
        }
      }
      //dispose graphics
      graphics.dispose();
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error Generating Feature Images, " + e.getMessage());
      throw e;
    }
    return image;
  }
  
  /**
   * 
   * @param obj
   * @param bufferedImage
   * @return
   * @throws Exception 
   */
  public static BufferedImage createPoint(JSONObject obj, BufferedImage bufferedImage) throws Exception {
    JSONObject geomJson = obj.getJSONArray("geometries").getJSONObject(0);
    String symbolType = obj.optString("symboltype", "");
    if (symbolType.equalsIgnoreCase("textsymbol")) {
      return createTextPoint(obj, bufferedImage);
    }

    double x = geomJson.getDouble("x");
    double y = geomJson.getDouble("y");
    int x1 = (int) Math.round(x);
    int y1 = (int) Math.round(y);
    String symbolurl = obj.optString("symbolurl", "");
    Graphics2D g2d = bufferedImage.createGraphics();

    if (!symbolType.equalsIgnoreCase("picturemarkersymbol")) {
      int size = geomJson.getInt("size");
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      String style = obj.getString("symbolstyle").toLowerCase();
      JSONObject color = obj.getJSONArray("colors").getJSONObject(0);
      g2d.setColor(toColor(color));
      g2d.setStroke(toStroke(obj.getJSONObject("stroke")));
      x1 -= size / 2;
      y1 -= size / 2;
      if (style.equals("circle")) {
        g2d.fillOval(x1, y1, size, size);
      } else {
        g2d.fillRect(x1, y1, size, size);
      }
      g2d.setColor(toColor(obj.getJSONObject("stroke").getJSONObject("color")));
      if (style.equals("circle")) {
        g2d.drawOval(x1, y1, size, size);
      } else {
        g2d.drawRect(x1, y1, size, size);
      }
    } else {
      try {
        ImageUtil imgUtil = new ImageUtil();
        symbolurl = symbolurl.replaceAll(" ", "%20");
        int imgHeight, imgWidth;
        if (geomJson.has("width") && geomJson.has("height")) {
          imgHeight = (int) geomJson.getDouble("width");
          imgWidth = (int) geomJson.getDouble("height");
        } else {
          URL symbollink = new URL(symbolurl);
          imgHeight = imgUtil.readImage(symbollink).getHeight();
          imgWidth = imgUtil.readImage(symbollink).getWidth();
        }
        x1 -= imgWidth / 2;
        y1 -= imgHeight / 2;
        g2d.drawImage(imgUtil.readImage(new URL(symbolurl)), x1, y1, imgWidth, imgHeight, null);
      } catch (MalformedURLException ex) {
        ex.printStackTrace();
      }
    }
    //g2d.draw(new Line2D.Double(x, y, x+10, y+10));
    g2d.dispose();
    return bufferedImage;
  }

  /**
   * 
   * @param obj
   * @param bufferedImage
   * @return 
   */
  public static BufferedImage createTextPoint(JSONObject obj, BufferedImage bufferedImage) {
    JSONObject geomJson = obj.getJSONArray("geometries").getJSONObject(0);
    double x = geomJson.getDouble("x");
    double y = geomJson.getDouble("y");
    int x1 = (int) Math.round(x);
    int y1 = (int) Math.round(y);
    String text = obj.optString("text", "");
    JSONObject font = obj.getJSONObject("font");

    Graphics2D g2d = bufferedImage.createGraphics();
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    JSONObject color = obj.getJSONArray("colors").getJSONObject(0);
    g2d.setPaint(toColor(color));
    g2d.setFont(toFont(font));
    FontMetrics fm = g2d.getFontMetrics();
    g2d.drawString(text, x1 - fm.stringWidth(text) / 2, y1);

    g2d.dispose();
    return bufferedImage;
  }

  /**
   * 
   * @param color
   * @return 
   */
  public static Color toColor(JSONObject color) {
    if (!color.has("r") || !color.has("g") || !color.has("b")) {
      return new Color(255, 255, 255, 0);
    } else if (color.has("a")) {
      return new Color(color.getInt("r"), color.getInt("g"), color.getInt("b"), toAlpha(color.getDouble("a")));
    } else {
      return new Color(color.getInt("r"), color.getInt("g"), color.getInt("b"));
    }
  }

  /**
   * 
   * @param value
   * @return 
   */
  private static int toAlpha(double value) {
    value = Math.max(0, Math.min(value, 1));
    return (int) Math.round(value * 255);
  }

  /**
   * 
   * @param font
   * @return 
   */
  public static Font toFont(JSONObject font) {
    return new Font(getFamily(font), toWeight(font.getString("weight")), toPt(font.getString("size")));
  }

  /**
   * 
   * @param font
   * @return 
   */
  public static String getFamily(JSONObject font) {
    if (font.has("family")) {
      return font.getString("family");
    } else {
      return "times new roman";
    }
  }

  /**
   * 
   * @param weight
   * @return 
   */
  public static int toWeight(String weight) {
    switch (weight.toLowerCase()) {
      case "bold":
      case "bolder":
        return Font.BOLD;
      default:
        return Font.PLAIN;
    }
  }

  /**
   * 
   * @param value
   * @return 
   */
  public static int toPt(String value) {
    Pattern numberPattern = Pattern.compile("(\\d*\\.?\\d*)");
    Matcher matcher = numberPattern.matcher(value);
    matcher.find();
    double px = Double.parseDouble(matcher.group());
    if (value.endsWith("pt"))
            ; // do nothing
    else // px
    {
      px = px * 72 / 96;
    }
    px *= 1.3;
    return (int) Math.round(px);
  }

  /**
   * 
   * @param obj
   * @param bufferedImage
   * @return 
   */
  public static BufferedImage createPolyline(JSONObject obj, BufferedImage bufferedImage) {
    int num;
    Graphics2D g2d = bufferedImage.createGraphics();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    JSONArray paths = obj.getJSONArray("geometries").getJSONObject(0).getJSONArray("paths");
    for (int i = 0; i < paths.length(); i++) {
      num = paths.getJSONArray(i).length();
      int[] x = new int[num];
      int[] y = new int[num];
      for (int k = 0; k < paths.getJSONArray(i).length(); k++) {
        x[k] = paths.getJSONArray(i).getJSONArray(k).getInt(0);
        y[k] = paths.getJSONArray(i).getJSONArray(k).getInt(1);
      }
      JSONObject color = obj.getJSONArray("colors").getJSONObject(0);
      g2d.setColor(toColor(color));
      g2d.setStroke(toStroke(obj.getJSONObject("stroke")));
      g2d.drawPolyline(x, y, num);
    }
    g2d.dispose();
    return bufferedImage;
  }

  /**
   * 
   * @param obj
   * @param bufferedImage
   * @return 
   */
  public static BufferedImage createPolygon(JSONObject obj, BufferedImage bufferedImage) {
    JSONArray rings = obj.getJSONArray("geometries").getJSONObject(0).getJSONArray("rings").getJSONArray(0);
    int num = rings.length() - 1;
    int[] x = new int[num];
    int[] y = new int[num];
    for (int i = 0; i < num; i++) {
      x[i] = rings.getJSONArray(i).getInt(0);
      y[i] = rings.getJSONArray(i).getInt(1);
    }
    Graphics2D g2d = bufferedImage.createGraphics();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setColor(toColor(obj.getJSONArray("colors").getJSONObject(0)));
    g2d.setStroke(toStroke(obj.getJSONObject("stroke")));
    g2d.fillPolygon(x, y, num);
    g2d.setColor(toColor(obj.getJSONObject("stroke").getJSONObject("color")));
    g2d.drawPolygon(x, y, num);
    g2d.dispose();
    return bufferedImage;
  }

  /**
   * 
   * @param obj
   * @param bufferedImage
   * @return 
   */
  public static BufferedImage fillPolygon(JSONObject obj, BufferedImage bufferedImage) {
    JSONArray rings = obj.getJSONArray("geometries").getJSONObject(0).getJSONArray("rings").getJSONArray(0);
    int num = rings.length() - 1;
    int[] x = new int[num];
    int[] y = new int[num];
    for (int i = 0; i < num; i++) {
      x[i] = rings.getJSONArray(i).getInt(0);
      y[i] = rings.getJSONArray(i).getInt(1);
    }
    Graphics2D g2d = bufferedImage.createGraphics();
    JSONObject color = obj.getJSONArray("colors").getJSONObject(0);
    g2d.setColor(toColor(color));
    g2d.setStroke(toStroke(obj.getJSONObject("stroke")));
    g2d.fillPolygon(x, y, num);
    g2d.dispose();
    return bufferedImage;
  }

  /**
   * 
   * @param stroke
   * @return 
   */
  public static BasicStroke toStroke(JSONObject stroke) {
    String style = stroke.optString("style", "").toLowerCase();
    switch (style) {
      case "dash":
        return new BasicStroke(stroke.getInt("width"), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10, new float[]{16, 20}, 0);
      case "dot":
        return new BasicStroke(stroke.getInt("width"), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10, new float[]{1, 20}, 0);
      case "dashdot":
        return new BasicStroke(stroke.getInt("width"), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10, new float[]{16, 20, 1, 20}, 0);
      case "dashdotdot":
        return new BasicStroke(stroke.getInt("width"), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10, new float[]{16, 20, 1, 20, 1, 20}, 0);
      case "longdashdotdot":
        return new BasicStroke(stroke.getInt("width"), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10, new float[]{20, 20, 1, 20, 1, 20}, 0);
      default: // solid
        return new BasicStroke(stroke.getInt("width"));
    }
  }

  /**
   * 
   * @param buffer 
   */
  public static void clear(BufferedImage buffer) {
    Graphics2D g = (Graphics2D) buffer.getGraphics();
    g.setComposite(AlphaComposite.Clear);
    g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
    g.dispose();
  }

  /**
   * 
   * @param arr
   * @return
   * @throws IOException 
   */
  public static BufferedImage drawImage(JSONArray arr) throws IOException {
    String imgType;
    BufferedImage rendImage;
    int width = arr.getJSONObject(0).getInt("width"), height = arr.getJSONObject(0).getInt("height");
    BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = combined.createGraphics();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    for (int i = 0; i < arr.length(); i++) {
      rendImage = null;
      clear(bufferedImage);
      try {
        imgType = arr.getJSONObject(i).getJSONArray("geometries").getJSONObject(0).getString("geometryType");
        switch (imgType) {
          case "esriGeometryPolygon":
            rendImage = createPolygon(arr.getJSONObject(i), bufferedImage);
            break;
          case "esriGeometryPoint":
            rendImage = createPoint(arr.getJSONObject(i), bufferedImage);
            break;
          case "esriGeometryPolyline":
            rendImage = createPolyline(arr.getJSONObject(i), bufferedImage);
            break;
        }
        //File file = new File(outputpath + "newimage.png");
        //ImageIO.write(rendImage, "png", file);
        if (rendImage != null) {
          g.drawImage(rendImage, 0, 0, null);
        }
      } catch (Exception ex) {
        ex.printStackTrace(System.err);
      }
    }
    g.dispose();
    return combined;
  }

  /**
   * 
   * @param baseImage
   * @param overlayImage
   * @return 
   */
  public static BufferedImage mergeImage(BufferedImage baseImage, BufferedImage overlayImage) {
    Graphics2D g = baseImage.createGraphics();
    g.drawImage(overlayImage, 0, 0, null);
    return baseImage;
  }
}
