/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.FileHelper;
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.data.Band;
import gov.ca.water.shapelite.data.DataFrame;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.events.EnvelopeChangedEvent;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class RasterDataset extends Dataset implements DataFrame {

  /**
   * WGS84 projection.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  /**
   * The LOGGER utility for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(RasterDataset.class.getName());

  /**
   * The number of pixels tall and wide for a standard tile.
   */
  public static final int TILE_SIZE = 256;

  /**
   * The maximum latitude/longitude in degrees.
   */
  public static final double LL_MAX = 180;

  /**
   * The full span from -180 to 180 degrees.
   */
  public static final double LL_SPAN = 360;

  /**
   * The number of meters in a decimal degree at the equator.
   */
  public static final double METERS_PER_DEGREE = 111319.9;

  /**
   * The number of inches in a meter.
   */
  public static final double INCHES_PER_METER = 39.3701;

  /**
   * Pixels Per Inch (assumed to be 96 for most screens).
   */
  public static final double PPI = 96;

  /**
   * The number of lines in a typical Ascii raster header.
   */
  public static final int HEADER_SIZE = 6;

  /**
   * An extrusion value to multiply to all cell values before being converted to
   * an integer and saved as a png raster.
   */
  public static final double SCALE_FACTOR = 100;

  /**
   * A default no data value.
   */
  private static final double DEFAULT_NO_DATA = -99;

  /**
   * The list of bands. This can be empty but will never be null.
   */
  private final List<Band> bands;
  /**
   * The integer number of horizontal raster cells. By default this is 256.
   */
  private int width;
  /**
   * The integer number of vertical raster cells. By default this is 256.
   */
  private int height;

  /**
   * The double value representing missing data. By default this is -99.
   */
  private double noDataValue;
  /**
   * The lower left corner X value.
   */
  private double xllcorner;
  /**
   * The lower left corner Y value.
   */
  private double yllcorner;
  /**
   * The integer width and height of a raster cell in the raster's native
   * geographic projection.
   */
  private double cellsize;
  /**
   * The double minimum value of the raster Z values.
   */
  private double min;
  /**
   * The double maximum value of the raster Z values.
   */
  private double max;

  /**
   * The String filename of the raster.
   */
  private String filename;
  /**
   * The decimal format for writing digits to the ascii file.
   */
  private static DecimalFormat format = new DecimalFormat("#.##");

  /**
   * A boolean to track changes in the envelope.
   */
  private boolean ignoreEnvelopeChange = false;

  /**
   * This is only true if the projection was successfully loaded from a file.
   */
  private boolean projectionLoaded;

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new instance of a dataset raster. By default, this will create a
   * 256x256 raster with one band.
   */
  public RasterDataset() {
    this.bands = new ArrayList<>();
    this.width = TILE_SIZE;
    this.height = TILE_SIZE;
    Band band = new Band(TILE_SIZE, TILE_SIZE, DEFAULT_NO_DATA);
    this.noDataValue = DEFAULT_NO_DATA;
    this.bands.add(band);
    addEnvelopeListener();
    projectionLoaded = false;
  }

  /**
   * Creates a raster with the specified width, height and bands.
   *
   * @param width The integer width (number of horizontal cells)
   * @param height The integer height (number of vertical cells)
   * @param numBands The integer number of bands.
   */
  public RasterDataset(int width, int height, int numBands) {
    this.height = height;
    this.width = width;
    bands = new ArrayList<>();
    for (int i = 0; i < numBands; i++) {
      Band band = new Band(width, height, DEFAULT_NO_DATA);
      bands.add(band);
    }
    this.noDataValue = DEFAULT_NO_DATA;
    addEnvelopeListener();
    projectionLoaded = false;
  }

  /**
   * Creates a raster with the specified width, height and bands.
   *
   * @param width The integer width (number of horizontal cells)
   * @param height The integer height (number of vertical cells)
   * @param numBands The integer number of bands.
   * @param noDataValue The value to use as no data. This will also initialize
   * all the values in all the bands to this value.
   */
  public RasterDataset(int width, int height, int numBands,
      double noDataValue) {
    this.height = height;
    this.width = width;
    bands = new ArrayList<>();
    for (int i = 0; i < numBands; i++) {
      Band band = new Band(width, height, noDataValue);
      bands.add(band);
    }
    this.noDataValue = noDataValue;
    for (Band band : bands) {
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          band.getData()[y][x] = this.noDataValue;
        }
      }
    }
    addEnvelopeListener();
    projectionLoaded = false;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This will reset this current raster to match the specified width, height
   * and band count. This will discard any data currently in the raster.
   *
   * @param width The integer number of columns.
   * @param height The integer number of rows.
   * @param numBands The integer number of bands.
   */
  public final void dimension(int width, int height, int numBands) {
    bands.clear();
    for (int i = 0; i < numBands; i++) {
      bands.add(new Band(width, height, this.noDataValue));
    }
  }

  /**
   * Adds an envelope listener to ensure that the various xll, yll and cellsize
   * properties match the envelope.
   */
  private void addEnvelopeListener() {
    super.getEnvelopeMercator().addEnvelopeChangedListener(
        new EnvelopeChangedEvent.Listener() {

      @Override
      public void envelopeChanged(EnvelopeChangedEvent e) {
        updateHeader(e.getEnvelope());
      }
    });
  }

  /**
   * Updates the header properties to match the current envelopeMercator.
   *
   * @param envelopeMercator The envelope to use to define the lower left x and
   * y values as well as the cell size.
   */
  private void updateHeader(Envelope envelopeMercator) {
    if (ignoreEnvelopeChange) {
      return;
    }
    Coord ll = Mercator.fromMerc(envelopeMercator.getMin());
    Coord ur = Mercator.fromMerc(envelopeMercator.getMax());
    if (!super.getProjection().equals(WGS84)) {
      try {
        ll = Reproject.reprojectCoordinate(ll, WGS84, getProjection());
        ur = Reproject.reprojectCoordinate(ur, WGS84, getProjection());
      } catch (ProjectionException ex) {
        // ensure that ll is set back to WGS84 if projection failed.
        ll = Mercator.fromMerc(envelopeMercator.getMin());
        Logger.getLogger(RasterDataset.class.getName()).log(Level.INFO,
            "DatasetRaster.updateHeader: failed to reproject.");
      }
    }
    xllcorner = ll.getX();
    yllcorner = ll.getY();
    cellsize = (ur.getX() - ll.getX()) / this.getWidth();
  }

  /**
   * Gets the zoom level from 0 to 18 for the open street map tile layers that
   * most closely matches the pixel resolution of the current view for
   * displaying the current extent.
   *
   * @return
   */
  @Override
  public final int getClosestLevel() {

    double target = this.getEnvelopeMercator().getWidth() / getView().getWidth();
    // from the longitude per pixel, estimate how many 256x256 tiles are
    // required
    double n = LL_SPAN / (target * TILE_SIZE);

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
    double displayInch = width / PPI;
    return worldInch / displayInch;
  }

  /**
   * Clears the raster by storing the no-data value to every cell in the raster.
   */
  public final void writeNoData() {
    for (Band band : bands) {
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          band.getData()[y][x] = this.noDataValue;
        }
      }
    }
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
   * Opens the ascii raster from the specified string filename.
   *
   * @param filename The string filename to open.
   * @throws IOException If there is an error reading the file stream.
   */
  public final void open(String filename) throws IOException {
    open(new File(filename));
  }

  /**
   * Opens the ascii raster.
   *
   * @param file The File object to open.
   * @throws java.io.IOException If there was an error reading the file.
   */
  public final void open(File file) throws IOException {
    this.filename = file.getAbsolutePath();
    String prjFilename = FileHelper.setExtension(file.getAbsolutePath(),
          ".prj");
    try {
      File prjFile = new File(prjFilename);
      if (prjFile.exists()) {
        String proj = FileHelper.readAll(prjFilename);
        ProjectionInfo info = ProjectionInfo.fromEsriString(proj);
        super.getProjection().copyProperties(info);
        projectionLoaded = true;
      }
    } catch (Exception ex) {
      Logger.getLogger(RasterDataset.class.getName()).log(Level.INFO, "{0}: {1}",
          new Object[]{prjFilename, ex.getMessage()});
      // do not prevent loading just because the projection failed.
    }
    FileReader reader = new FileReader(file);
    open(reader);

  }

  /**
   * This version of open is intended for use with an embedded file.
   *
   * @param clazz A class from the same project as the embedded file.
   * @param embeddedUrl The url relative to the clazz package or an absolute
   * url.
   * @throws IOException If there is a problem reading the file.
   */
  public final void open(Class<?> clazz, String embeddedUrl)
      throws IOException {
    InputStream stream = clazz.getResourceAsStream(embeddedUrl);
    String prjFile = FileHelper.setExtension(embeddedUrl, ".prj");
    InputStream prjStream = clazz.getResourceAsStream(prjFile);
    openStream(stream, prjStream);
  }

  /**
   * Use this for any stream other than a tiff stream.
   *
   * @param asciiStream A stream containing the ascii content.
   * @param prjStream A stream containing the projection content. This can be
   * null.
   * @throws java.io.IOException If there is an error reading the streams.
   */
  public final void openStream(InputStream asciiStream, InputStream prjStream)
      throws IOException {
    InputStreamReader reader = new InputStreamReader(asciiStream);
    String proj = FileHelper.readAll(prjStream);
    if(proj != null){
      super.getProjection().copyProperties(ProjectionInfo.fromEsriString(proj));
    }
    open(reader);

  }

  /**
   * Opens the raster using the specified reader for data.
   *
   * @param reader The reader providing binary data.
   * @throws IOException If there is an error reading.
   */
  private void open(Reader reader) throws IOException {

    bands.clear();
    int iLine = 0;
    Band result = null;
    try (BufferedReader br = new BufferedReader(reader)) {
      String line = br.readLine();
      while (line != null) {
        String[] parts = line.split("\\s+");
        if (parts.length < 1) {
          return;
        }
        String fieldName = parts[0].toLowerCase();
        if (iLine < HEADER_SIZE) {
          switch (fieldName) {
            case "ncols":
              this.width = Integer.parseInt(parts[1]);
              break;
            case "nrows":
              this.height = Integer.parseInt(parts[1]);
              break;
            case "xllcorner":
              this.xllcorner = Double.parseDouble(parts[1]);
              break;
            case "yllcorner":
              this.yllcorner = Double.parseDouble(parts[1]);
              break;
            case "cellsize":
              this.cellsize = Double.parseDouble(parts[1]);
              break;
            case "nodata_value":
              this.noDataValue = Double.parseDouble(parts[1]);
              break;
            default:
              Logger.getLogger(RasterDataset.class.getName()).log(Level.INFO,
                  "Unrecognized header field: {0}", fieldName);
              break;
          }
        } else {
          if (result == null) {
            if (width == 0) {
              width = TILE_SIZE; // don't allow 0 size rasters.
            }
            if (height == 0) {
              height = TILE_SIZE; // don't allow 0 size rasters.
            }
            result = new Band(width, height, this.noDataValue);
          }
          // trim to remove potential starting whitespace which is not a
          // delimeter
          String[] text = line.trim().split(" ");

          for (int col = 0; col < text.length; col++) {
            if (text.length > col && iLine - HEADER_SIZE < this.height) {
              result.getData()[iLine - HEADER_SIZE][col]
                  = Double.parseDouble(text[col]);
            }

          }
        }

        iLine++;
        line = br.readLine();
      }
    }
    if (result == null) {
      if (width > 0 && height > 0) {
        this.getBands().add(new Band(width, height, this.noDataValue));
      } else {
        this.getBands().add(new Band(TILE_SIZE, TILE_SIZE, this.noDataValue));
        this.width = TILE_SIZE;
        this.height = TILE_SIZE;
      }
    } else {
      this.getBands().add(result);
    }
    Envelope env = new DefaultEnvelope(xllcorner, yllcorner, xllcorner
        + width * cellsize, yllcorner + height * cellsize);
    if (!getProjection().equals(WGS84)) {
      try {
        env = Reproject.reprojectEnvelope(env, getProjection(), WGS84);
      } catch (ProjectionException ex) {
        Logger.getLogger(RasterDataset.class.getName()).log(Level.INFO,
            "Failed to project from {0}", getProjection().getName());
      }
    }
    try{
      env = Mercator.toMerc(env);
    }catch(Exception ex){
      boolean stop = true;
    }

    ignoreEnvelopeChange = true;
    super.getEnvelopeMercator().copyProperties(env);
    ignoreEnvelopeChange = false;

  }

  /**
   * Creates a duplicate of the current raster. This will only copy values
   * within the specified envelope. Values outside the envelope will retain the
   * no-data value.
   *
   * @param envelope The envelope to copy values for in the projection for this
   * raster dataset.
   * @return An optional RasterDataset with values that are within the specified
 envelope. If no part of the raster is in the envelope, the optional will be
 empty.
   */
  public final Optional<RasterDataset> copy(@Nullable Envelope envelope) {
    RasterDataset result = new RasterDataset(
        getWidth(), getHeight(),
        getBands().size(), getNoDataValue());
    result.setProjectionFrom(getProjection());
    result.setXllcorner(xllcorner);
    result.setYllcorner(yllcorner);
    result.setCellsize(cellsize);
    result.setMin(min);
    result.setMax(max);
    boolean empty = true;
    for (Band band : getBands()) {
      result.getBands().clear();
      Band resultBand = new Band(getWidth(), getHeight(), this.noDataValue);
      for (int row = 0; row < getHeight(); row++) {
        for (int col = 0; col < getWidth(); col++) {
          Envelope cellBounds = getCellBounds(row, col);

          if (envelope == null || cellBounds.intersects2D(envelope)) {
            resultBand.getData()[row][col] = band.getData()[row][col];
            empty = false;
          } else {
            resultBand.getData()[row][col] = noDataValue;
          }
        }
      }
      result.getBands().add(resultBand);
    }
    if (empty) {
      return Optional.empty();
    }
    return Optional.of(result);
  }

  /**
   * Gets an envelope defining a single raster cell at the specified index.
   *
   * @param row The integer row.
   * @param column The integer column.
   * @return An Envelope defining the raster cell.
   */
  public final Envelope getCellBounds(int row, int column) {
    double x1 = getXllcorner() + column * getCellsize();
    double x2 = getXllcorner() + (column + 1) * getCellsize();

    double y1 = getYllcorner() + (height - row) * getCellsize();
    double y2 = getYllcorner() + (height - (row + 1)) * getCellsize();
    return new DefaultEnvelope(x1, y1, x2, y2);
  }

  /**
   * Calculates the minimum and maximum on each band, ignoring the noDataValue
   * entries.
   */
  public final void calculateMinMax() {
    min = Double.MAX_VALUE;
    max = Double.MIN_VALUE;
    for (Band band : bands) {
      double bandMin = Double.MAX_VALUE;
      double bandMax = Double.MIN_VALUE;
      double[][] data = band.getData();
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          double val = data[y][x];
          if (val == this.noDataValue) {
            continue;
          }
          if (val > bandMax) {
            bandMax = val;
          }
          if (val < bandMin) {
            bandMin = val;
          }
        }
      }
      band.setMax(bandMax);
      band.setMin(bandMin);
      if (bandMax > max) {
        max = bandMax;
      }
      if (bandMin < min) {
        min = bandMin;
      }
    }

  }

  /**
   * Saves the first band of this raster to the specified filename as an ascii
   * file.
   *
   * @throws FileNotFoundException if there was an error reading the file.
   */
  public final void save() throws FileNotFoundException {
    save(this.filename, 0);
  }

  /**
   * Defaults to saving the first band of this raster to the specified ascii
   * file.
   *
   * @param asciiFile The filename of the ascii file to save.
   * @throws FileNotFoundException If write access could not be obtained for the
   * file.
   */
  public final void save(String asciiFile) throws FileNotFoundException {
    save(asciiFile, 0);
  }

  /**
   * Saves the specified band of this raster as an ascii file.
   *
   * @param asciiFile The string filename
   * @param band the band.
   * @throws FileNotFoundException If there was an error writing to the file.
   */
  public final void save(String asciiFile, int band)
      throws FileNotFoundException {
    File f = new File(asciiFile);
    if (f.exists()) {
      f.delete();
    }
    File dir = f.getParentFile();
    if (!dir.exists()) {
      dir.mkdirs();
    }
    if (this.bands.isEmpty()) {
      this.bands.add(new Band(width, height, this.noDataValue));
    }
    try (PrintWriter writer = new PrintWriter(asciiFile)) {
      writer.write("ncols         " + this.width + "\n");
      writer.write("nrows         " + this.height + "\n");
      writer.write("xllcorner     " + this.xllcorner + "\n");
      writer.write("yllcorner     " + this.yllcorner + "\n");
      writer.write("cellsize      " + this.cellsize + "\n");
      writer.write("NODATA_value  " + format.format(this.noDataValue) + "\n");

      double[][] data = this.bands.get(band).getData();
      for (int y = 0; y < this.height; y++) {

        for (int x = 0; x < this.width; x++) {
          if (x > 0) {
            writer.append(" ");
          }
          try {
            writer.append(format.format(data[y][x]));
          } catch (ArrayIndexOutOfBoundsException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
          }

        }
        writer.append("\n");
      }

    } catch (Exception ex) {
      boolean stop = true;
    }
    String prjPath = FileHelper.setExtension(asciiFile, ".prj");
    File prjFile = new File(prjPath);
    if (prjFile.exists()) {
      prjFile.delete();
    }
    try (PrintWriter writer = new PrintWriter(prjPath)) {
      writer.write(getProjection().toEsriString());
    }
  }

  /**
   * Writes 100 * the raster array values to the specified png.
   *
   * @param png The path to the png output file.
   * @param iBand The integer band index of the raster to save.
   * @throws IOException If there was an error writing to the file.
   */
  public final void savePng(String png, int iBand) throws IOException {
    Band band = this.getBands().get(iBand);
    BufferedImage img = new BufferedImage(band.getWidth(), band.getHeight(),
        BufferedImage.TYPE_USHORT_GRAY);
    double[][] data = this.getBands().get(0).getData();
    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.height; x++) {
        int val = (int) (data[y][x] * SCALE_FACTOR);
        if (val > TILE_SIZE * TILE_SIZE) {
          val = TILE_SIZE * TILE_SIZE;
        }
        if (val < 0) {
          val = 0;
        }
        img.setRGB(x, y, val);
      }
    }
    ImageIO.write(img, "png", new File(png));
  }

  /**
   * This is commented out because it required a reference to JAI, which is not
   * currently included as part of this library. Use this for opening tiff
   * streams
   *
   * @param stream The InputStream to open.
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
//    BufferedImage.TYPE_INT_ARGB);
//    Graphics2D g = img.createGraphics();
//    DisplayJAI jai = new DisplayJAI(image1);
//    g.drawImage(jai.createImage(width, height), 0, 0, null);
//    this.setImage(img);
  }

  //</editor-fold>
  /**
   * Gets a geographic version of the envelope in the native projection for this
   * dataset.
   *
   * @return The projected geographic envelope.
   */
  @Override
  public final Envelope getEnvelope() {
    Envelope result = Mercator.fromMerc(this.getEnvelopeMercator());
    if (!WGS84.equals(getProjection())) {
      try {
        result = Reproject.reprojectEnvelope(result, WGS84, getProjection());
      } catch (ProjectionException ex) {
        Logger.getLogger(RasterDataset.class.getName()).log(Level.INFO,
            "DatasetRaster.getEnvelope() : Failed to reproject" + " to {0}",
            getProjection().getName());
      }
    }
    return result;
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the height of the image stored in this dataset.
   *
   * @return the height
   */
  public final int getHeight() {
    return this.height;
  }

  /**
   * Gets the width of the image stored in this dataset.
   *
   * @return the width
   */
  public final int getWidth() {
    return this.width;
  }

  /**
   * @param width the width to set
   */
  public final void setWidth(int width) {
    this.width = width;
  }

  /**
   * @param height the height to set
   */
  public final void setHeight(int height) {
    this.height = height;
  }

  /**
   * Gets the List of bands.
   *
   * @return the bands
   */
  public final List<Band> getBands() {
    return bands;
  }

  /**
   * @return the noDataValue
   */
  public final double getNoDataValue() {
    return noDataValue;
  }

  /**
   * @param noDataValue the noDataValue to set
   */
  public final void setNoDataValue(double noDataValue) {
    this.noDataValue = noDataValue;
  }

  /**
   * @return the xllcorner
   */
  public final double getXllcorner() {
    return xllcorner;
  }

  /**
   * @param xllcorner the xllcorner to set
   */
  public final void setXllcorner(double xllcorner) {
    this.xllcorner = xllcorner;
  }

  /**
   * @return the yllcorner
   */
  public final double getYllcorner() {
    return yllcorner;
  }

  /**
   * @param yllcorner the yllcorner to set
   */
  public final void setYllcorner(double yllcorner) {
    this.yllcorner = yllcorner;
  }

  /**
   * @return the cellsize
   */
  public final double getCellsize() {
    return cellsize;
  }

  /**
   * @param cellsize the cellsize to set
   */
  public final void setCellsize(double cellsize) {
    this.cellsize = cellsize;
  }

  /**
   * @return the min
   */
  public final double getMin() {
    return min;
  }

  /**
   * @param min the min to set
   */
  public final void setMin(double min) {
    this.min = min;
  }

  /**
   * @return the max
   */
  public final double getMax() {
    return max;
  }

  /**
   * @param max the max to set
   */
  public final void setMax(double max) {
    this.max = max;
  }

  /**
   * @return the format
   */
  public final static DecimalFormat getFormat() {
    return format;
  }

  /**
   * @param aFormat the format to set
   */
  public final static void setFormat(DecimalFormat aFormat) {
    format = aFormat;
  }

  /**
   * For most rasters that only have one band, this gets the data from the first
   * band.
   *
   * @return a jagged array of doubles, in row major, like [y][x].
   */
  public final double[][] getData() {
    if (this.bands.isEmpty()) {
      bands.add(new Band(TILE_SIZE, TILE_SIZE, this.noDataValue));
    }
    return this.bands.get(0).getData();
  }

  /**
   * When the envelope is defined, it should re-define the cell properties
   * appropriately.
   */
  private void updateExtents() {
    this.cellsize = this.getEnvelopeMercator().getWidth() / this.getWidth();
    this.xllcorner = this.getEnvelopeMercator().getMin().getX();
    this.yllcorner = this.getEnvelopeMercator().getMin().getY();
  }

  //</editor-fold>

  /**
   * @return the projectionLoaded
   */
  public boolean isProjectionLoaded() {
    return projectionLoaded;
  }
}
