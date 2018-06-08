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
package gov.ca.water.shapelite.data.dataset;

import gov.ca.water.shapelite.DefaultEnvelope;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.data.DataFrame;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.io.WorldFile;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;

/**
 * This class supports an image using the standard java image library.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ImageDataset extends Dataset implements DataFrame {

  /**
   * The span of longitude from -180 to 180 in decimal degrees.
   */
  public static final int LONGITUDE_SPAN = 360;

  /**
   * The number of pixels in a tile both width and height.
   */
  public static final int TILE_SIZE = 256;

  /**
   * The number of meters in a decimal degree at the equator.
   */
  public static final double METERS_PER_DEGREE = 111319.9;

  /**
   * The number of inches.
   */
  public static final double INCHES_PER_METER = 39.3701;

  /**
   * Pixels Per Inch.
   */
  public static final int PPI = 96;

  /**
   * WGS84 geographic projection.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The image stored in memory.
   */
  private BufferedImage image;

  /**
   * The world file associated with this image dataset.
   */
  private WorldFile worldFile;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Gets the zoom level from 0 to 18 for the open street map tile layers that
   * most closely matches the pixel resolution of the current view for
   * displaying the current extent.
   *
   * @return the integer closest scale.
   */
  @Override
  public final int getClosestLevel() {

    double target = this.getEnvelopeMercator().getWidth() / getView().getWidth();
    // from the longitude per pixel, estimate how many 256x256
    // tiles are required
    double n = LONGITUDE_SPAN / (target * TILE_SIZE);

    // using the number of tiles, estimate the tile level that has
    // approximately that many.
    double level = Math.log(n) / Math.log(2);

    return (int) Math.round(level);
  }

  /**
   * Gets a Projector class that can handle the math to transform between the
   * image coordinates and the geographic coordinates.
   *
   * @return A Projector class.
   */
  @Override
  public final Projector getProjector() {
    return new Projector(this);
  }

  /**
   * Gets the ratio of real world size to image size.
   *
   * @return The scale of the image.
   */
  @Override
  public final double getScale() {
    double worldInch = this.getEnvelopeMercator().getWidth()
        * METERS_PER_DEGREE * INCHES_PER_METER;
    double displayInch = image.getWidth() / PPI;
    return worldInch / displayInch;
  }

  /**
   * Gets the rectangle view that defines the image boundaries.
   *
   * @return The rectangle image boundaries.
   */
  @Override
  public final Rectangle getView() {
    return new Rectangle(0, 0, getWidth(), this.getHeight());
  }

  /**
   * Opens the specified file.
   *
   * @param file The File object to open.
   * @throws java.io.IOException If there was an error opening the image.
   */
  public final void open(File file) throws IOException {
    image = ImageIO.read(file);
    String path = file.getAbsolutePath();
    String worldFilePath = getWorldfileName(path);
    File worldfileFile = new File(worldFilePath);
    if (worldfileFile.exists()) {
      worldFile = new WorldFile();
      worldFile.read(worldFilePath);
      this.setEnvelopeFrom(worldFile.getEnvelope(image.getWidth(), image.getHeight()));
    }

  }

  /**
   * Gets the worldfile name based on the image name.
   *
   * @param path The path of the image.
   * @return The String path of the world file. The path string cannot be null.
   */
  private String getWorldfileName(@NonNull String path) {
    int p = path.lastIndexOf(".");
    if (p > 0) {
      String ext = path.substring(p);
      String worldExt;
      switch (ext) {
        case ".png":
          worldExt = ".pgw";
          break;
        case ".jpg":
          worldExt = ".jgw";
          break;
        case ".gif":
          worldExt = ".gfw";
          break;
        case ".bmp":
          worldExt = ".bpw";
          break;
        default:
          worldExt = ".wld";
          break;
      }
      return path.substring(0, p) + worldExt;
    }
    return path + ".wld";
  }

  /**
   * Gets the string extension for the specified path.
   *
   * @param path The path to get the extension from.
   * @return The optional String extension. The optional can never be null, but
   * it can be empty.
   */
  @NonNull
  private Optional<String> getExtension(String path) {
    int p = path.lastIndexOf(".") + 1;
    if (p > 0) {
      String ext = path.substring(p);
      return Optional.of(ext);
    }
    return Optional.empty();
  }

  /**
   * Saves both the image and a worldfile for the image.
   *
   * @see WorldFile
   * @param path The full path of the image.
   * @throws java.io.IOException If there was an error writing either the image
   * file or the world file.
   */
  public final void save(String path) throws IOException {
    save(path, true);
  }

  /**
   * Saves the file to the specified path.
   *
   * @param path The path to save the file to.
   * @param saveWorldFile Boolean, true if the worldfile should be created as
   * well and saved based on the envelope.
   * @throws java.io.IOException If there was an exception writing either the
   * image file or world file.
   */
  public final void save(@NonNull String path, boolean saveWorldFile) throws IOException {
    if (path == null) {
      throw new IllegalArgumentException("path cannot be null for save.");
    }
    File output = new File(path);
    if (output.exists()) {
      output.delete();
    }
    File dir = output.getParentFile();
    if (!dir.exists()) {
      dir.mkdirs();
    }
    String ext = getExtension(path).orElse("png");
    ImageIO.write(image, ext, output);
    Envelope env = this.getEnvelopeMercator();
    if (worldFile != null) {
      try {
        worldFile.save();
      } catch (Exception ex) {
        throw new IOException("Could not save world file: " + worldFile, ex);
      }

    } else {
      WorldFile wf = new WorldFile(env.getWidth() / image.getWidth(), 0,
          0, -env.getHeight() / image.getHeight(), env.getMin().getX(),
          env.getMax().getY());
      try {
        wf.write(getWorldfileName(path));
      } catch (Exception ex) {
        throw new IOException("Could not save world file based on image data set's "
                + "envelope dimensions.", ex);
      }

    }

  }

  /**
   * Opens the Web-Mercator projection BlueMarble image.
   */
  public final void openBlueMarble() {
    InputStream imgStream = this.getClass().getResourceAsStream("Mercator.png");
    openStream(imgStream);
    Envelope env = new DefaultEnvelope(-MAX_LON, -MAX_LON, MAX_LON, MAX_LON);
    this.getEnvelopeMercator().copyProperties(env);
  }

  /**
   * Use this for any stream other than a tiff stream.
   *
   * @param stream The input file or memory stream to open.
   */
  public final void openStream(InputStream stream) {
    try {
      image = ImageIO.read(stream);
    } catch (IOException ex) {
      Logger.getLogger(ImageDataset.class.getName()).log(Level.SEVERE,
          null, ex);
    }
  }

  /**
   * Gets the geographic envelope in the native projection for this dataset.
   *
   * @return The geographic envelope.
   */
  @Override
  public final Envelope getEnvelope() {
    Envelope env = this.getEnvelopeMercator().copy();
    Envelope wgs84 = Mercator.fromMerc(env);
    Envelope result = wgs84;
    if (!getProjection().equals(WGS84)) {
      try {
        result = Reproject.reprojectEnvelope(wgs84, WGS84, getProjection());
      } catch (ProjectionException ex) {
        System.out.println("DatasetImage.getEnvelope(): Failed to reproject "
            + getProjection().getName() + " to WGS84.");
      }
    }
    return result;
  }

  /**
   * Defines the mercator envelope from the specified envelope in the projection
   * of this dataset.
   *
   * @param envelope The Envelope to use for the definition.
   */
  public final void setEnvelopeFrom(Envelope envelope) {
    Envelope env = envelope;
    if (!getProjection().equals(WGS84)) {
      try {
        env = Reproject.reprojectEnvelope(env, getProjection(), WGS84);
      } catch (ProjectionException ex) {
        System.out.println("DatasetImage.getEnvelope(): Failed to reproject "
            + getProjection().getName() + " to WGS84.");
      }
    }
    Envelope envMerc = Mercator.toMerc(env);
    this.setEnvelopeMercatorFrom(envMerc);
  }

  /**
   * This is commented out because it required a reference to JAI, which is not
   * currently included as part of this library. Use this for opening tiff
   * streams
   *
   * @param stream The input stream to open.
   */
  public final void opentiff(InputStream stream) {

//    SeekableStream str = SeekableStream.wrapInputStream(stream, true);
//
//    // Store the input stream in a ParameterBlock to be sent to
//    // the operation registry, and eventually to the TIFF
//    // decoder.
//    ParameterBlock params = new ParameterBlock();
//    params.add(str);
//
//    // Specify to TIFF decoder to decode images as they are and
//    // not to convert unsigned short images to byte images.
//    TIFFDecodeParam decodeParam = new TIFFDecodeParam();
//    decodeParam.setDecodePaletteAsShorts(true);
//
//    // Create an operator to decode the TIFF file.
//    RenderedOp image1 = JAI.create("tiff", params);
//    int dataType = image1.getSampleModel().getDataType();
//    int width = image1.getWidth();
//    int height = image1.getHeight();
//    BufferedImage img = new BufferedImage(width, height,
//       BufferedImage.TYPE_INT_ARGB);
//    Graphics2D g = img.createGraphics();
//    DisplayJAI jai = new DisplayJAI(image1);
//    g.drawImage(jai.createImage(width, height), 0, 0, null);
//    this.setImage(img);
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the height of the image stored in this dataset.
   *
   * @return the height
   */
  public final int getHeight() {
    if (image != null) {
      return image.getHeight();
    }
    return 0;
  }

  /**
   * Gets the image stored in memory for this dataset.
   *
   * @return the image.
   */
  public final BufferedImage getImage() {
    return image;
  }

  /**
   * Sets the image stored in memory for this dataset.
   *
   * @param image the image to set.
   */
  public final void setImage(BufferedImage image) {
    this.image = image;
  }

  /**
   * Gets the width of the image stored in this dataset.
   *
   * @return the width
   */
  public final int getWidth() {
    if (image != null) {
      return image.getWidth();
    }
    return 0;
  }

  /**
   * Gets the world file associated with this image dataset.
   *
   * @return the worldFile
   */
  public final WorldFile getWorldFile() {
    return worldFile;
  }

  /**
   * Sets the world file associated with this image dataset.
   *
   * @param worldFile the worldFile to set
   */
  public final void setWorldFile(WorldFile worldFile) {
    try {
      this.worldFile = worldFile;
      double endX = new Integer(this.image.getWidth()).doubleValue()*this.worldFile.getDx();
      double endY = new Integer(this.image.getHeight()).doubleValue()*(-this.worldFile.getDy());
      double upperLeft_X = this.worldFile.getX() - this.worldFile.getDx()*0.5;
      double upperLeft_Y = this.worldFile.getY() - this.worldFile.getDy()*0.5;
      
      Envelope env = new DefaultEnvelope(
              new CoordXY(upperLeft_X, upperLeft_Y)
              , new CoordXY(upperLeft_X + endX, upperLeft_Y - endY));
      String currentProjection = this.worldFile.getCurrentProjection();
      ProjectionInfo source = ProjectionInfo.fromEsriString(currentProjection);
      Envelope reprojectedEnv = Reproject.reprojectEnvelope(env, source, WGS84);
      this.setEnvelopeFrom(reprojectedEnv);
    } catch (ProjectionException ex) {
      ex.printStackTrace();
    }
  }

  //</editor-fold>
}
