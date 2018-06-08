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
package gov.ca.water.shapelite.map;

import gov.ca.water.shapelite.map.layer.TileLineLayer;
import gov.ca.water.shapelite.map.layer.OraclePointLayer;
import gov.ca.water.shapelite.map.layer.PolygonLayer;
import gov.ca.water.shapelite.map.layer.LineLayer;
import gov.ca.water.shapelite.map.layer.Layer;
import gov.ca.water.shapelite.map.layer.PointLayer;
import gov.ca.water.shapelite.map.layer.ImageLayer;
import gov.ca.water.shapelite.map.layer.TileLineLabelLayer;
import gov.ca.water.shapelite.map.layer.TileLayer;
import gov.ca.water.shapelite.map.layer.RasterTileLayer;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.data.DataFrame;
import gov.ca.water.shapelite.data.DataSourceMarkerOracle;
import gov.ca.water.shapelite.data.DataSourceTiles;
import gov.ca.water.shapelite.data.DataSourceTilesPath;
import gov.ca.water.shapelite.data.DataSourceTilesRaster;
import gov.ca.water.shapelite.data.dataset.ImageDataset;
import gov.ca.water.shapelite.data.dataset.PointDataset;
import gov.ca.water.shapelite.data.dataset.LineDataset;
import gov.ca.water.shapelite.data.dataset.PolygonDataset;
import gov.ca.water.shapelite.data.FeatureMarkerByNameDelegate;
import gov.ca.water.shapelite.data.FeatureMarkerDelegate;
import gov.ca.water.shapelite.data.FeatureMarkerPathByNameDelegate;
import gov.ca.water.shapelite.data.FeatureMarkerPolygonByNameDelegate;
import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.data.marker.LineMarker;
import gov.ca.water.shapelite.data.marker.PolygonMarker;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.symbology.Symbolizer;
import gov.ca.water.shapelite.symbology.PointSymbolizer;
import gov.ca.water.shapelite.symbology.LineSymbolizer;
import gov.ca.water.shapelite.symbology.PolygonSymbolizer;
import gov.ca.water.shapelite.data.TileUrlFormatESRI;
import gov.ca.water.shapelite.data.TileUrlFormatGoogleMap;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.event.EventListenerList;
import javax.swing.filechooser.FileFilter;

/**
 * This lists the openedLayers, and shows the geographic extents.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapContent implements DataFrame, Display {

  /**
   * WGS84 geographic shapefileProjection.
   */
  private static final ProjectionInfo WGS84
      = Projections.getGeographic().getWorld().getWGS1984();

  /**
   * A LOGGER for logging problems to the IDE Log.
   */
  private static final Logger LOGGER = Logger.getLogger(MapContent.class.getName());

  /**
   * Gets an envelope that is big enough to show most of the world.
   */
  private static final Envelope WORLD = new DefaultEnvelope(-180, -85, 180, 85);

  /**
   * The integer size of the tile.
   */
  private static final int TILE_SIZE = 256;

  /**
   * The file chooser.
   */
  private static final JFileChooser fileChooser = new JFileChooser();

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The buffered cached image to render by the paint method.
   */
  private BufferedImage display;
  /**
   * The envelope in geographic Mercator coordinates, where both latitude and
   * longitude range from -180 to 180. X corresponds to longitude, but Y is
   * modified by the Mercator shapefileProjection, and does not map to latitude
   * exactly.
   */
  private final Envelope displayEnvelope;
  /**
   * The geographic envelope.
   */
  private final Envelope envelope;
  /**
   * A random UUID. I have no idea why this is here. It may have been added by
   * team Kwabena for the RainBrain project.
   */
  private final UUID id;
  /**
   * The list of openedLayers. The ObservedList is responsible for firing events
   * if the list is altered in a way that required redrawing of the map.
   */
  private ObservedList<Layer<?>> layers;
  /**
   * The list of dynamic openedLayers. Since these openedLayers are not drawn as
   * part of the back buffer, but instead are drawn on top of the buffer every
   * time a paint operation is called, it is best for these to be small, fast
   * openedLayers.
   */
  private List<Layer<?>> dynamicLayers;
  /**
   * The list of event listeners that respond to the MapContentListener events.
   */
  private final EventListenerList listenerList;
  /**
   * The boolean value indicating whether or not the current extents correspond
   * with a zoom level resolution wise.
   */
  private boolean offLevel;
  /**
   * A background thread to perform rendering in a delayed way, which is useful
   * when zooming using the mouse.
   */
  private MapContentRenderThread renderThread;
  /**
   * The client rectangle of the map extents in pixels.
   */
  private Rectangle view;
  /**
   * A boolean value that determines whether or not the map is displayed.
   */
  private boolean visible;

  /**
   * The shapefileProjection info defining the geographic shapefileProjection
   * for this GeoFrame. By default this is WGS84. This is used for the
   * "getEnvelope()" Method only. It will not actually change the appearance of
   * the shapes on the map.
   */
  private final ProjectionInfo projection;

  /**
   * The HSB ratio used for pastel coloring.
   */
  public static final float PASTEL = .8f;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of the MapContent class.
   */
  public MapContent() {
    layers = new ObservedList<>();
    dynamicLayers = new ArrayList<>();
    envelope = WORLD.copy();
    displayEnvelope = new DefaultEnvelope();
    visible = true;
    id = java.util.UUID.randomUUID();
    listenerList = new EventListenerList();
    projection = ProjectionInfo.getDefault();
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * Adds the listener to this MapContent for rendering and pointLayer updates.
   * Duplicates are not added.
   *
   * @param listener The MapContentListener interface implementation that
   * responds to the event.
   */
  public final void addMapContentListener(MapContentListener listener) {
    if (!Arrays.asList(listenerList.getListeners(MapContentListener.class)).
        contains(listener)) {
      listenerList.add(MapContentListener.class, listener);
    }
  }

  /**
   * Removes the listener from this object for rendering and pointLayer updates.
   *
   * @param listener The MapContentListener interface implementation that
   * responds to the event.
   */
  public final void removeMapContentListener(MapContentListener listener) {
    listenerList.remove(MapContentListener.class, listener);
  }

  /**
   * Fires the render complete event after drawing has taken place.
   *
   * @param evt The DisplayEvent has the information about the source object of
   * the event.
   */
  private void fireRenderComplete(DisplayEvent evt) {
    MapContentListener[] listeners = listenerList.getListeners(MapContentListener.class);
    for (MapContentListener listener : listeners) {
      listener.renderComplete(evt);
    }
  }

  /**
   * Fires the content changed event after the openedLayers have been modified.
   * This instructs the owning map control to repaint itself completely.
   *
   * @param evt The DisplayEvent has the information about the source object of
   * the event.
   */
  private void fireContentChanged(DisplayEvent evt) {
    MapContentListener[] listeners = listenerList.getListeners(MapContentListener.class);
    for (MapContentListener listener : listeners) {
      listener.contentChanged(evt);
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Allows the user to open some random shapefiles.
   *
   * @return The list of MapLayers created. The type of the pointLayer will
   * depend on the feature type.
   */
  public final List<Layer<?>> openShapefiles() {

    fileChooser.setMultiSelectionEnabled(true);
    fileChooser.setFileFilter(new FileFilter() {
      @Override
      public boolean accept(File f) {
        if (f.isDirectory()) {
          return true;
        }
        return f.getAbsolutePath().endsWith(".shp");
      }

      @Override
      public String getDescription() {
        return "Shapefiles (*.shp)";
      }
    });
    if (fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
      return new ArrayList<>();
    }

    return openShapefiles(fileChooser.getSelectedFiles());
  }

  /**
   * Allows the user to open some random shapefiles.
   *
   * @param fileNames The String file paths to open.
   * @return The list of MapLayers created. The type of the pointLayer will
   * depend on the feature type.
   */
  public final List<Layer<?>> openShapefilePaths(String... fileNames) {
    return openShapefilePaths(Arrays.asList(fileNames));
  }

  /**
   * Allows the user to open some random shapefiles.
   *
   * @param fileNames The list of strings to open.
   * @return The list of MapLayers created. The type of the pointLayer will
   * depend on the feature type.
   */
  public final List<Layer<?>> openShapefilePaths(List<String> fileNames) {
    List<File> files = new ArrayList<>();
    for (String path : fileNames) {
      File f = new File(path);
      if (f.exists()) {
        files.add(f);
      }
    }
    return openShapefiles(files);
  }

  /**
   * Allows the user to open some random shapefiles.
   *
   * @param files The String file paths to open.
   * @return The list of MapLayers created. The type of the pointLayer will
   * depend on the feature type.
   */
  public final List<Layer<?>> openShapefiles(File... files) {
    return openShapefiles(Arrays.asList(files));
  }

  /**
   * Allows the user to open some random shapefiles.
   *
   * @param files The String file paths to open.
   * @return The list of MapLayers created. The type of the pointLayer will
   * depend on the feature type.
   */
  public final List<Layer<?>> openShapefiles(List<File> files) {
    ShapefileReader reader = new ShapefileReader();
    List<Layer<?>> openedLayers = new ArrayList<>();
    Random rnd = new Random();
    Color color = Color.getHSBColor(rnd.nextFloat(), PASTEL, PASTEL);
    for (File file : files) {
      try {
        reader.open(file.getAbsolutePath());
        FeatureSet fs = reader.getFeatures();
        if (null != fs.getFeatureCategory()) {
          switch (fs.getFeatureCategory()) {
            case Point:
              PointLayer pointLayer = this.addPoints(fs, fs.getName(), color);
              openedLayers.add(pointLayer);
              break;

            case PolyLine:
              LineLayer lineLayer = this.addMarkerLines(fs, fs.getName(), color);
              openedLayers.add(lineLayer);
              break;

            case Polygon:
              PolygonLayer polygonLayer = this.addMarkerPolygon(fs, fs.getName(), color);
              openedLayers.add(polygonLayer);
              break;

            default:
              break;
          }
        }
      } catch (IOException | ProjectionException ex) {
        Logger.getLogger(MapContent.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return openedLayers;
  }

  /**
   * Adds the specified image. In future this will use a world file.
   *
   * @param file The Image file to show as a pointLayer.
   * @return A MapLayerImage object that was created to show the image file.
   * @throws java.io.IOException If there was an error opening the image.
   */
  public final ImageLayer addImage(File file) throws IOException {
    return addImage(file, this.displayEnvelope);
  }

  /**
   * Adds the image specified to the map with the specified extent.
   *
   * @param file The Image file to show as a pointLayer.
   * @param env The geographic envelope to force the image to appear within.
   * @return A MapLayerImage object that was created to show the image file.
   * @throws java.io.IOException If there was an error opening the image.
   */
  public final ImageLayer addImage(File file, Envelope env) throws IOException {
    ImageDataset image = new ImageDataset();
    image.open(file);
    image.getEnvelopeMercator().copyProperties(env);
    ImageLayer layer = new ImageLayer(image);
    layers.add(0, layer); // add images to the bottom of the stack.
    // center the view on the image.
    this.setEnvelope(layer.getEnvelope());
    // Modify the envelope to accomodate the aspect ratio of the control.
    this.fixEnvelope();

    return layer;
  }

  /**
   * Adds the specified shapefile. The file extension should be .shp.
   *
   * @param shapeFile the shapefile of lines to add.
   * @param name The String name of the file
   * @param nameField the String name of the column to use for the popup names
   * of shapes.
   * @param layerColor the Color to use to color all the lines the same. Leave
   * this blank in order for the colors to be assigned randomly.
   * @return MapLayerMarkerPath The created MapLayerMarkerPath object.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * opening the shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error reprojecting the shape to WGS84.
   */
  public final LineLayer addMarkerLines(File shapeFile, String name,
      String nameField, Color layerColor) throws ShapefileException,
      ProjectionException {
    LineLayer result = null;
    try {
      if (!shapeFile.getAbsolutePath().endsWith(".shp")) {
        throw new ShapefileException("The specified file did not end with .shp");
      }
      ShapefileReader sfReader = new ShapefileReader();
      sfReader.open(shapeFile.getAbsolutePath());
      if (!ShapeType.isPolyLine(sfReader.header.getShapeType())) {
        throw new ShapefileException("The file " + shapeFile
            + " was not a Polyline shapefile type.");
      }

      Optional<String[]> namesArray = sfReader.getAttributes().getFieldStrings(nameField);
      List<Shape> shapes = sfReader.getShapes();
      List<Shape> wgs84Shapes = new ArrayList<>();
      String shapefileProjection = sfReader.getProjection();
      if (shapefileProjection == null) {
        wgs84Shapes = shapes;
      } else {
        ProjectionInfo source = ProjectionInfo.fromEsriString(shapefileProjection);
        if (source.equals(Projections.getGeographic().getWorld().getWGS1984())) {
          wgs84Shapes = shapes;
        } else {
          for (Shape shape : shapes) {
            wgs84Shapes.add(Reproject.reprojectShape(shape, source,
                Projections.getGeographic().getWorld().getWGS1984()));
          }
        }
      }

      String[] names = null;
      if (namesArray.isPresent()) {
        names = namesArray.get();
      }
      List<LineMarker> markers
          = LineDataset.getMarkersFromShapes(wgs84Shapes, names);
      LineDataset dataset = new LineDataset(markers);
      result = new LineLayer(dataset);
      result.setName(name);
      if (layerColor != null) {
        result.getDefaultSymbolizer().setFillColor(layerColor);
        for (LineMarker p : dataset.getMarkers()) {
          p.setSymbolizer(null);
        }
      }
      layers.add(result);
    } catch (ShapefileException | IOException ex) {
      throw new ShapefileException(ex);
    }
    return result;
  }

  /**
   * This method does not require any attributes for symbolization, and allows
   * the pure vector shapes to be used to add lines. Since the pointLayer has no
   * attributes to speak of, the pointLayer color will be fixed for all the
   * lines in the specified list of shapes.
   *
   * @param shapes The list of shapes that will be converted into a featureset
   * on a new pointLayer.
   * @param layerName The name of the new pointLayer.
   * @param layerColor The color of the new pointLayer.
   * @param projection The shapefileProjection for the pointLayer. If not
   * specified, then this will be assumed to be latitude, longitude (WGS1984)
   * @return A MapLayerMarkerPath for the lines just added.
   * @throws gov.ca.water.shapelite.ShapefileException If one of the shapes is
   * not a polyline, polylineM or PolylineZ
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error reprojecting the coordinates into map coordinates.
   */
  public final LineLayer addMarkerLines(List<Shape> shapes,
      String layerName, Color layerColor, String projection)
      throws ShapefileException, ProjectionException {
    int i = 0;
    FeatureSet fs = new FeatureSet();
    if (projection == null) {
      projection = Projections.getGeographic().getWorld().getWGS1984().toEsriString();
    }
    fs.setProjectionESRI(projection);
    for (Shape shape : shapes) {
      if (shape.getShapeType() != ShapeType.PolyLine
          && shape.getShapeType() != ShapeType.PolyLineM
          && shape.getShapeType() != ShapeType.PolyLineZ) {
        LOGGER.log(Level.FINE, "Shape {0} from layer: {1} was not a line shape."
            + " Skipping shape.", new Object[]{i, layerName});
      }
      Feature f = new Feature();
      f.setShape(shape);
      fs.getFeatures().add(f);
      i++;
    }
    return addMarkerLines(fs, layerName, layerColor);
  }

  /**
   * Adds the marker lines for a featureset without specifying a delegate. The
   * MapLayerMarkerPath can then be used in order to control the symbology of
   * the features.
   *
   * @param featureSet The FeatureSet of lines to add.
   * @param layerName the name of the pointLayer being created.
   * @param layerColor the line color to use for the pointLayer.
   * @return A MapLayerMarkerPath for the specified
   * @throws gov.ca.water.shapelite.projection.ProjectionException
   */
  public final LineLayer addMarkerLines(FeatureSet featureSet,
      String layerName, Color layerColor) throws ProjectionException {
    return addMarkerLines(featureSet, layerName,
        (FeatureMarkerDelegate<LineMarker, LineSymbolizer>) null,
        layerColor);
  }

  /**
   * Initiate a MapLayerMarkerPath with PointMarker representing each Feature in
   * the FeatureSet. This overload initiates a
   * {@linkplain FeatureMarkerByNameDelegate} and call {@linkplain
   * #addMarkerLines(gov.ca.water.shapelite.FeatureSet,
   * java.lang.String, gov.ca.water.shapelite.data.FeatureMarkerDelegate,
   * java.awt.Color) this.addMarkerLines(featureSet,name,delegate,layerColor} to
   * initiate the MapLayerMarkerPath.
   *
   * @param featureSet the featureSet with shapes to add.
   * @param name The String name of the file.
   * @param nameField the String name of the column to use for the popup names
   * of shapes.
   * @param layerColor the Color to use to color all the lines the same. Leave
   * this blank in order for the colors to be assigned randomly.
   * @return The MapLayerMarkerPath that was created and added to the map.
   * @throws gov.ca.water.shapelite.projection.ProjectionException
   */
  public final LineLayer addMarkerLines(FeatureSet featureSet, String name,
      String nameField, Color layerColor) throws ProjectionException {
    LineLayer result;
    FeatureMarkerPathByNameDelegate delegate
        = new FeatureMarkerPathByNameDelegate(nameField);
    result = this.addMarkerLines(featureSet, name, delegate, layerColor);
    return result;
  }

  /**
   * Initiate a MapLayerMarkerPath with PointMarker representing each Feature in
   * the FeatureSet. It uses the specified delegate to initiates the PointMarker
   * for each feature.
   *
   * @param featureSet the featureSet with shapes to add.
   * @param name The String name of the file
   * @param delegate a FeatureMarkerDelegate to initiate the PointMarker for
   * each feature in the featureSet..
   * @param layerColor the Color to use to color all the lines the same. Leave
   * this blank in order for the colors to be assigned randomly.
   * @return MapLayerMarkerPath
   * @throws gov.ca.water.shapelite.projection.ProjectionException If a
   * shapefileProjection exists, but the shapefileProjection cannot be converted
   * into WGS1984. If the shapefileProjection is null, it will be assumed to
   * already be in WGS1984.
   */
  public final LineLayer addMarkerLines(FeatureSet featureSet, String name,
      FeatureMarkerDelegate<LineMarker, LineSymbolizer> delegate,
      Color layerColor) throws ProjectionException {

    List<LineMarker> markers
        = LineDataset.getMarkersFromFeatureSet(featureSet, delegate);

    LineDataset dataset = new LineDataset(markers);
    LineLayer layer = new LineLayer(dataset);
    layer.setName(name);
    if (layerColor != null) {
      layer.getDefaultSymbolizer().setFillColor(layerColor);
    }
    this.layers.add(layer);
    return layer;
  }

  /**
   * Adds the dataset of markers to this map, and returns a pointLayer for
   * controlling the symbology of the markers.
   *
   * @param markers A list of PolygonMarker to add to the map. A new
   * DatasetMarkerPolygon will be created to contain the markers, and a new
   * MapLayerMarkerPolygon will be returned with the specified markers contained
   * in it.
   * @param zoomToLayer if true the map's displayEnvelope will be set to the
   * extend of the pointLayer.
   * <p>
   * <b>NOTE:</b> Add by JGP for more adding a MapLayerMarkerPolygon</p>
   * @return A MapLayerMarker which has already been added to the map.
   */
  public final PolygonLayer addMarkerPolygon(List<PolygonMarker> markers,
      boolean zoomToLayer) {
    PolygonLayer layer = new PolygonLayer();
    layer.setDataset(new PolygonDataset(markers));
    layers.add(layer);
    Envelope layerEnv = null;
    if ((zoomToLayer) && ((layerEnv = layer.getEnvelope()) != null)
        && (!layerEnv.isEmpty()) && (layerEnv.getMin().getX()
        != layerEnv.getMax().getX())) {
      if (layerEnv.getMin().getY() != layerEnv.getMax().getY()) {
        this.setEnvelope(Mercator.toMerc(layerEnv));
        this.fixEnvelope();
      }
    }
    return layer;
  }

  /**
   * Adds the specified Polygon shapefile. The file extension should be .shp.
   *
   * @param shapeFile the shapefile of lines to add.
   * @param name The String name of the file
   * @param nameField the String name of the column to use for the popup names
   * of shapes.
   * @param layerColor the Color to use to color all the lines the same. Leave
   * this blank in order for the colors to be assigned randomly.
   * @return MapLayerMarkerPath
   * @throws gov.ca.water.shapelite.ShapefileException If there was a problem
   * reading the shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * a problem projecting to the map shapefileProjection.
   */
  public final PolygonLayer addMarkerPolygon(File shapeFile, String name,
      String nameField, Color layerColor) throws ShapefileException,
      ProjectionException {
    PolygonLayer result = null;
    try {
      if (!shapeFile.getAbsolutePath().endsWith(".shp")) {
        throw new ShapefileException("The specified file did not end with .shp");
      }
      ShapefileReader sfReader = new ShapefileReader();
      sfReader.open(shapeFile.getAbsolutePath());
      if (!ShapeType.isPolygon(sfReader.header.getShapeType())) {
        throw new ShapefileException("The file " + shapeFile
            + " was not a Polygon shapefile type.");
      }

      Optional<String[]> namesArray
          = sfReader.getAttributes().getFieldStrings(nameField);
      List<Shape> shapes = sfReader.getShapes();
      List<Shape> wgs84Shapes = new ArrayList<>();
      String shapefileProjection = sfReader.getProjection();
      if (shapefileProjection == null) {
        wgs84Shapes = shapes;
      } else {
        ProjectionInfo source = ProjectionInfo.fromEsriString(shapefileProjection);
        if (source.equals(Projections.getGeographic().getWorld().getWGS1984())) {
          wgs84Shapes = shapes;
        } else {
          for (Shape shape : shapes) {
            wgs84Shapes.add(Reproject.reprojectShape(shape, source,
                Projections.getGeographic().getWorld().getWGS1984()));
          }
        }
      }

      String[] names = namesArray.orElse(null);
      List<PolygonMarker> markers
          = PolygonDataset.getMarkersFromShapes(wgs84Shapes, names);
      PolygonDataset dataset = new PolygonDataset(markers);
      result = new PolygonLayer(dataset);
      result.setName(name);
      if (layerColor != null) {
        result.getDefaultSymbolizer().setFillColor(layerColor);
        for (PolygonMarker p : dataset.getMarkers()) {
          p.setSymbolizer(null);
        }
      }
      layers.add(result);
    } catch (ShapefileException | IOException ex) {
      throw new ShapefileException(ex);
    }
    return result;
  }

  /**
   * Initiate a MapLayerMarkerPath with PointMarker representing each Feature in
   * the FeatureSet. This overload initiates a
   * {@linkplain FeatureMarkerPolygonByNameDelegate} and call {@linkplain
   * #addMarkerPolygon(gov.ca.water.shapelite.FeatureSet,
   * java.lang.String, gov.ca.water.shapelite.data.FeatureMarkerDelegate,
   * java.awt.Color) this.addMarkerPolygon(featureSet,name,delegate,layerColor}
   * to initiate the MapLayerMarkerPolygon.
   *
   * @param featureSet the featureSet with shapes to add.
   * @param name The String name of the file
   * @param layerColor the Color to use to color all the lines the same. Leave
   * this blank in order for the colors to be assigned randomly.
   * @return MapLayerMarkerPolygon
   * @throws gov.ca.water.shapelite.projection.ProjectionException
   */
  public final PolygonLayer addMarkerPolygon(FeatureSet featureSet,
      String name, Color layerColor)
      throws ProjectionException {
    List<PolygonMarker> markers
        = PolygonDataset.getMarkersFromFeatureSet(featureSet);
    PolygonDataset dataset = new PolygonDataset(markers);
    dataset.setFieldsFrom(featureSet.getFields());
    PolygonLayer layer = new PolygonLayer(dataset);
    layer.setName(name);
    if (layerColor != null) {
      layer.getDefaultSymbolizer().setFillColor(layerColor);
    }
    int index;
    for (index = 0; index < layers.size(); index++) {
      Layer<?> lyr = this.layers.get(index);
      if (lyr instanceof LineLayer || lyr instanceof PointLayer) {
        break;
      }
    }
    this.layers.add(index, layer);
    return layer;
  }

  /**
   * Initiate a MapLayerMarkerPath with PointMarker representing each Feature in
   * the FeatureSet. This overload initiates a
   * {@linkplain FeatureMarkerPolygonByNameDelegate} and call {@linkplain
   * #addMarkerPolygon(gov.ca.water.shapelite.FeatureSet,
   * java.lang.String, gov.ca.water.shapelite.data.FeatureMarkerDelegate,
   * java.awt.Color) this.addMarkerPolygon(featureSet,name,delegate,layerColor}
   * to initiate the MapLayerMarkerPolygon.
   *
   * @param featureSet the featureSet with shapes to add.
   * @param name The String name of the file
   * @param nameField the String name of the column to use for the popup names
   * of shapes.
   * @param layerColor the Color to use to color all the lines the same. Leave
   * this blank in order for the colors to be assigned randomly.
   * @return MapLayerMarkerPolygon
   * @throws gov.ca.water.shapelite.projection.ProjectionException
   */
  public final PolygonLayer addMarkerPolygon(FeatureSet featureSet,
      String name, String nameField, Color layerColor)
      throws ProjectionException {
    PolygonLayer result;
    FeatureMarkerPolygonByNameDelegate delegate
        = new FeatureMarkerPolygonByNameDelegate(nameField);
    result = this.addMarkerPolygon(featureSet, name, delegate, layerColor);
    return result;
  }

  /**
   * Initiate a MapLayerMarkerPolygon with Markers representing each Feature in
   * the FeatureSet. It uses the specified delegate to initiates the PointMarker
   * for each feature.
   *
   * @param featureSet the featureSet with shapes to add.
   * @param name The String name of the file
   * @param delegate a FeatureMarkerDelegate to initiate the PointMarker for
   * each feature in the featureSet.
   * @param layerColor the Color to use to color all the lines the same. Leave
   * this blank in order for the colors to be assigned randomly.
   * @return MapLayerMarkerPolygon
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * a problem projecting the featureSet to the map projection.
   */
  public final PolygonLayer addMarkerPolygon(FeatureSet featureSet, String name,
      FeatureMarkerDelegate<PolygonMarker, PolygonSymbolizer> delegate,
      Color layerColor) throws ProjectionException {

    List<PolygonMarker> markers
        = PolygonDataset.getMarkersFromFeatureSet(featureSet, delegate);
    PolygonDataset dataset = new PolygonDataset(markers);
    PolygonLayer layer = new PolygonLayer(dataset);
    layer.setName(name);
    if (layerColor != null) {
      layer.getDefaultSymbolizer().setFillColor(layerColor);
    }
    this.layers.add(layer);
    return layer;
  }

  /**
   * Rather than drawing from an in-ram set of points, this pointLayer will
   * query oracle each time, only pulling as many points as are within the
   * selection. The advantage is that the markers are not kept in memory, but
   * the trade off is that oracle will be queried every time the content is
   * rendered.
   *
   * @param markers the DataSourceMarkerOracle that provides the latitude and
   * longitude for the markers.
   * @return A MapLayerMarkerOracle pointLayer, which can draw the markers on
   * the map.
   */
  public final OraclePointLayer addOracleMarkers(DataSourceMarkerOracle markers) {
    OraclePointLayer layer = new OraclePointLayer(markers);

    Envelope bounds = markers.getEnvelopeMercator();
    layers.add(layer);
    this.getEnvelopeMercator().copyProperties(Mercator.toMerc(bounds));
    this.fixEnvelope();
    return layer;
  }

  /**
   * Generates a new MapLayerMarker based on the specified values.
   *
   * @param coord
   * @param markerName
   * @param layerName
   * @param layerColor
   * @return
   */
  public final PointLayer addMarker(Coord coord, String markerName,
      String layerName, Color layerColor) {
    PointDataset dm = new PointDataset();
    PointMarker m = new PointMarker(coord, markerName);
    dm.getMarkers().add(m);
    PointLayer layer = new PointLayer(dm);
    layer.setName(layerName);
    if (layerColor != null) {
      layer.getDefaultSymbolizer().setFillColor(layerColor);
    }
    this.layers.add(layer);
    return layer;
  }

  /**
   * Adds the dataset of markers to this map, and returns a pointLayer for
   * controlling the symbology of the markers.
   *
   * @param markers The DatasetMarker collection of markers to add to the map.
   * @return A newly created MapLayerMarker that uses the given dataset to draw
   * points.
   */
  public final PointLayer addMarkers(PointDataset markers) {
    PointLayer layer = new PointLayer();
    layer.setDataset(markers);
    layers.add(layer);
    if (!markers.getMarkers().isEmpty() && !layer.getEnvelope().isEmpty()) {
      this.setEnvelope(Mercator.toMerc(layer.getEnvelope()));
      this.fixEnvelope();
    }

    return layer;
  }

  /**
   * Overload 1: Adds the dataset of markers to this map, and returns a
   * pointLayer for controlling the symbology of the markers. This method calls {@linkplain
   * #addMarkers(java.util.List, boolean) Overload 2} with zoomToLayer=true.
   *
   * @param markers A list of markers to add to the map. A new DatasetMarker
   * will be created to contain the markers, and a new MapLayerMarker will be
   * returned with the specified markers contained in it.
   * @return A MapLayerMarker which has already been added to the map.
   */
  public final PointLayer addMarkers(List<PointMarker> markers) {
    return this.addMarkers(markers, true);
  }

  /**
   * Overload 2: Adds the dataset of markers to this map, and returns a
   * pointLayer for controlling the symbology of the markers.
   *
   * @param markers A list of markers to add to the map. A new DatasetMarker
   * will be created to contain the markers, and a new MapLayerMarker will be
   * returned with the specified markers contained in it.
   * @param zoomToLayer if true the map's displayEnvelope will be set to the
   * extend of the pointLayer.
   * <p>
   * <b>NOTE:</b> Add by JGP for more flexibility</p>
   * @return A MapLayerMarker which has already been added to the map.
   */
  public final PointLayer addMarkers(List<PointMarker> markers, boolean zoomToLayer) {
    PointLayer layer = new PointLayer();
    layer.setDataset(new PointDataset(markers));
    layers.add(layer);
    Envelope layerEnv = layer.getEnvelope();
    if (zoomToLayer) {
      if (markers.size() > 0
          && layerEnv != null
          && !layerEnv.isEmpty()
          && layerEnv.getMin().getX() != layerEnv.getMax().getX()
          && layerEnv.getMin().getY() != layerEnv.getMax().getY()) {
        this.setEnvelope(Mercator.toMerc(layerEnv));
        this.fixEnvelope();
      }
    }
    return layer;
  }

  /**
   * Adds the specified point shapefile. The file extension should be .shp.
   *
   * @param shapeFile the shapefile of lines to add.
   * @param name The String name of the file
   * @param nameField the String name of the column to use for the popup names
   * of shapes.
   * @param layerColor the Color to use to color all the lines the same. Leave
   * this blank in order for the colors to be assigned randomly.
   * @return MapLayerMarkerPath
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * opening the shapefile.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there was
   * an error projecting the shapefile to map coordinates.
   */
  public final PointLayer addMarkers(File shapeFile, String name, String nameField,
      Color layerColor) throws ShapefileException, ProjectionException {
    PointLayer result = null;
    try {
      if (!shapeFile.getAbsolutePath().endsWith(".shp")) {
        throw new ShapefileException("The specified file did not end with .shp");
      }
      ShapefileReader sfReader = new ShapefileReader();
      sfReader.open(shapeFile.getAbsolutePath());
      if (ShapeType.isPoint(sfReader.header.getShapeType())) {
        throw new ShapefileException("The file " + shapeFile
            + " was not a Point shapefile type.");
      }

      Optional<String[]> namesArray = sfReader.getAttributes().getFieldStrings(nameField);
      List<Shape> shapes = sfReader.getShapes();
      List<Shape> wgs84Shapes = new ArrayList<>();
      String projection = sfReader.getProjection();
      if (projection == null) {
        wgs84Shapes = shapes;
      } else {
        ProjectionInfo source = ProjectionInfo.fromEsriString(projection);
        if (source.equals(Projections.getGeographic().getWorld().getWGS1984())) {
          wgs84Shapes = shapes;
        } else {
          for (Shape shape : shapes) {
            wgs84Shapes.add(Reproject.reprojectShape(shape, source, Projections.getGeographic().getWorld().getWGS1984()));
          }
        }
      }
      List<PointMarker> markers
          = PointDataset.getMarkersFromShapes(wgs84Shapes, namesArray.orElse(null));
      PointDataset dataset = new PointDataset(markers);
      result = new PointLayer(dataset);
      result.setName(name);
      if (layerColor != null) {
        result.getDefaultSymbolizer().setFillColor(layerColor);
        for (PointMarker p : dataset.getMarkers()) {
          p.setSymbolizer(null);
        }
      }
      layers.add(result);
    } catch (ShapefileException | IOException ex) {
      throw new ShapefileException(ex);
    }
    return result;
  }

  /**
   * Initiate a MapLayerMarker with PointMarker representing each Feature in the
   * FeatureSet. This overload initiates a
   * {@linkplain FeatureMarkerByNameDelegate} and call null null null null null
   * null null null
   * {@link #addPointMarkers(gov.ca.water.shapelite.FeatureSet, java.lang.String, gov.ca.water.shapelite.data.FeatureMarkerDelegate, java.awt.Color) this.addPointMarkers(featureSet,name,delegate,layerColor}
   * to initiate the MapLayerMarker.
   *
   * @param featureSet the featureSet with shapes to add.
   * @param name The String name of the file
   * @param nameField the String name of the column to use for the popup names
   * of shapes.
   * @param layerColor the Color to use to color all the lines the same. Leave
   * this blank in order for the colors to be assigned randomly.
   * @return MapLayerMarker
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the
   * featureset could not be projected into WGS84.
   */
  public final PointLayer addMarkers(FeatureSet featureSet, String name,
      String nameField, Color layerColor) throws ProjectionException {
    PointLayer result;
    FeatureMarkerByNameDelegate delegate = new FeatureMarkerByNameDelegate(nameField);
    result = this.addPointMarkers(featureSet, name, delegate, layerColor);
    return result;
  }

  /**
   * Adds the specified featureset to the map, giving all the features the
   * specified pointLayer color and giving the pointLayer.
   *
   * @param featureSet The featureset of points to add.
   * @param layerName the String pointLayer name to assign to the new
   * pointLayer.
   * @param layerColor the default color of the shapes on the new pointLayer.
   * @return A MapLayerMarker which can be used to symbolize and update the
   * shapes.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the
   * specified featureset could not be projected to WGS84.
   */
  public final PointLayer addPoints(FeatureSet featureSet, String layerName,
      Color layerColor) throws ProjectionException {
    return addPointMarkers(featureSet, layerName,
        (FeatureMarkerDelegate<PointMarker, PointSymbolizer>) null, layerColor);
  }

  /**
   * Initiate a MapLayerMarker with Markers representing each Feature in the
   * FeatureSet. It uses the specified delegate to initiates the PointMarker for
   * each feature.
   *
   * @param featureSet the featureSet with shapes to add.
   * @param layerName The String name of the pointLayer
   * @param delegate a FeatureMarkerDelegate to initiate the PointMarker for
   * each feature in the featureSet.
   * @param layerColor the Color to use to color all the lines the same. Leave
   * this blank in order for the colors to be assigned randomly.
   * @return MapLayerMarker
   * @throws gov.ca.water.shapelite.projection.ProjectionException If the
   * featureset could not be projected into WGS84.
   */
  public final PointLayer addPointMarkers(FeatureSet featureSet, String layerName,
      FeatureMarkerDelegate<PointMarker, PointSymbolizer> delegate,
      Color layerColor) throws ProjectionException {
    List<PointMarker> markers = PointDataset.getMarkersFromFeatureSet(featureSet,
        delegate);
    PointDataset dataset = new PointDataset(markers);
    dataset.setFieldsFrom(featureSet.getFields());
    PointLayer layer = new PointLayer(dataset);
    layer.setName(layerName);
    if (layerColor != null) {
      layer.getDefaultSymbolizer().setFillColor(layerColor);
    }
    this.layers.add(layer);
    return layer;
  }

  /**
   * Generates a point pointLayer from the specified coordinates.
   *
   * @param coordinates The list of coordinates to add to the map.
   * @param name The String name of the pointLayer to create.
   * @param layerColor The color of the fill color for the points. The symbology
   * can be controlled more completely using the returned pointLayer.
   * @param projection The optional shapefileProjection string. If this is null,
   * then it is assumed to be WGS1984 (latitude, longitude).
   * @return A map pointLayer created entirely from the coordinates. The
   * individual names for the markers will be null.
   */
  public final PointLayer addMarkers(List<? extends Coord> coordinates, String name,
      Color layerColor, String projection) {
    FeatureSet fs = new FeatureSet();
    if (projection == null) {
      projection = Projections.getGeographic().getWorld().getWGS1984().toEsriString();
    }
    fs.setProjectionESRI(projection);
    PointDataset dm = new PointDataset();
    int i = 1;
    for (Coord coord : coordinates) {
      PointMarker m = new PointMarker(coord);
      dm.getMarkers().add(m);
      i++;
    }
    PointLayer layer = new PointLayer(dm);
    layer.setName(name);
    if (layerColor != null) {
      layer.getDefaultSymbolizer().setFillColor(layerColor);
    }
    this.layers.add(layer);
    return layer;
  }

  /**
   * Adds the pointLayer to the map's openedLayers collection and if
   * <tt>zoomToLayer</tt>
   * = true, zoom the map to the pointLayer.envelope.
   *
   * @param mapLayer The pointLayer to add to the map.
   * @param zoomToLayer true to zoom to the pointLayer
   */
  public final void addMapLayer(Layer<?> mapLayer, boolean zoomToLayer) {
    if (mapLayer != null) {
      layers.add(mapLayer);
      Envelope layerEnv = null;
      if ((zoomToLayer) && ((layerEnv = mapLayer.getEnvelope()) != null)
          && (!layerEnv.isEmpty()) && (layerEnv.getMin().getX()
          != layerEnv.getMax().getX())) {
        if (layerEnv.getMin().getY() != layerEnv.getMax().getY()) {
//          this.setEnvelope(Mercator.toMerc(layerEnv));
          this.setEnvelope(layerEnv);
          this.fixEnvelope();
        }
      }
    }
  }

  /**
   * Adds the BlueMarble background pointLayer. This adds this image to the top
   * of the stack.
   *
   * @return A MapLayerImage that was added to the map to show blue marble
   * images named "bluemarble".
   */
  public final ImageLayer addBlueMarble() {
    ImageDataset image = new ImageDataset();
    image.openBlueMarble();
    ImageLayer layer = new ImageLayer(image);
    layer.setName("bluemarble");
    int index = 0;
    for (int i = 0; i < layers.size(); i++) {
      if (("street".equals(layers.get(i).getName()))
          || ("google".equals(layers.get(i).getName()))) {
        index = i + 1;
      }
    }
    layers.add(index, layer);
    //envelope = pointLayer.getEnvelopeMercator();
    //this.fixEnvelope();
    return layer;
  }

  /**
   * Open Street Map is a free and open source map tile server which was an
   * international effort to create web mapping tiles that are free to use,
   * unlike Google, or Bing, which are proprietary.
   *
   * @return MapLayerTiles that show the open street map content name "street".
   */
  public final TileLayer addOpenStreetMap() {
    DataSourceTiles tiles = new DataSourceTiles();
    TileLayer layer = this.addTiles(tiles);
    layer.setName("street");
    return layer;
  }

  /**
   * Not sure if this is working. But theoretically this adds a MapLayerTiles
   * for google map.
   *
   * @return A MapLayerTiles named "google".
   */
  public final TileLayer addGoogleMap() {
    DataSourceTiles tiles = new DataSourceTiles();
    tiles.setUrlFormat(new TileUrlFormatGoogleMap());
    tiles.setPath("Google Map");
    TileLayer layer = addTiles(tiles);
    layer.setName("google");
    return layer;
  }

  /**
   * Creates and adds a new ESRI World Imagery tile pointLayer to the map.
   *
   * @return A MapLayerTiles reference to the newly created pointLayer.
   */
  public final TileLayer addEsriWoldImagery() {
    DataSourceTiles tiles = new DataSourceTiles();
    tiles.setUrlFormat(new TileUrlFormatESRI());
    tiles.setPath("ESRI World Imagery");
    TileLayer layer = addTiles(tiles);
    layer.setName("worldImagery");
    return layer;
  }

  /**
   * Creates and adds a new ESRI Street Map tile pointLayer to the map.
   *
   * @return A MapLayerTiles reference to the newly created pointLayer.
   */
  public final TileLayer addEsriStreetMap() {
    DataSourceTiles tiles = new DataSourceTiles();
    tiles.setUrlFormat(new TileUrlFormatESRI());
    tiles.getUrlFormat().setBaseUrl("http://services.arcgisonline.com/arcgis/"
        + "rest/services/World_Street_Map/MapServer/tile");
    tiles.setPath("ESRI Street Map");
    TileLayer layer = addTiles(tiles);
    layer.setName("streetMap");
    return layer;
  }

  /**
   * Creates and adds a new ESRI USA Topo Maps pointLayer to the map.
   *
   * @return A MapLayerTiles reference to the newly created pointLayer.
   */
  public final TileLayer addEsriTopoMap() {
    DataSourceTiles tiles = new DataSourceTiles();
    tiles.setUrlFormat(new TileUrlFormatESRI());
    tiles.getUrlFormat().setBaseUrl("http://server.arcgisonline.com/ArcGIS/"
        + "rest/services/USA_Topo_Maps/MapServer/tile");
    tiles.setPath("USA Topo Maps");
    TileLayer layer = addTiles(tiles);
    layer.setName("topomap");
    return layer;
  }

  /**
   * Adds MapLayerTiles pointLayer to show hill shades from
   * http://toolserver.org/~cmarqu/hill/. The pointLayer is named "hillshade".
   * These are not terrain tiles, but rather are shading values that can be
   * super-imposed on another tile map to add a shaded relief effect.
   *
   * @return The MapLayerTiles pointLayer named hillshade.
   */
  public final TileLayer addHillshadeMap() {
    DataSourceTiles tiles = new DataSourceTiles();
    tiles.getUrlFormat().setBaseUrl("http://toolserver.org/~cmarqu/hill/");
    tiles.setMaxLevel(17);
    tiles.setPath("Hill");
    TileLayer layer = addTiles(tiles);
    layer.setName("hillshade");
    return layer;
  }

  /**
   * Adds a MapLayerTiles pointLayer to show terrain tiles. These are fully
   * opaque tiles and don't necessarily cover the entire global range. It is
   * recommended that the tiles outside the bounds are adjusted to use a
   * different tile source.
   *
   * @return A MapLayerTiles pointLayer named "terrain".
   */
  public final TileLayer addTerrainTiles() {
    DataSourceTiles tiles = new DataSourceTiles();
    tiles.getUrlFormat().setBaseUrl("http://tile.stamen.com/terrain");
    tiles.getUrlFormat().setExtension(".jpg");
    tiles.setMaxLevel(17);
    tiles.setPath("Terrain");
    TileLayer layer = addTiles(tiles);
    layer.getEnvelope().copyProperties(new DefaultEnvelope(-122, 20, -80, 50));
    layer.setName("terrain");
    return layer;
  }

  /**
   * Adds a newly created MapLayerTiles to host the specified DataSourcTiles
   * object that provides tiles from a web location.
   *
   * @param tiles The DataSourceTiles to show.
   * @return the newly added MapLayerTiles. It will not yet have a name.
   */
  public final TileLayer addTiles(DataSourceTiles tiles) {
    TileLayer layer = new TileLayer(tiles);
    layers.add(0, layer);
    return layer;
  }

  /**
   * Adds a newly created MapLayerTiles to host the specified DataSourcTiles
   * object that provides tiles from a web location.
   *
   * @param tiles The DataSourceTiles to show.
   * @return the newly added MapLayerTiles. It will not yet have a name.
   */
  public final RasterTileLayer addTiledRasters(DataSourceTilesRaster tiles) {
    RasterTileLayer layer = new RasterTileLayer(tiles);
    layers.add(0, layer);
    return layer;
  }

  /**
   * Adds the DataSourceTilesPath to the map as a pointLayer.
   *
   * @param tiles The DataSourceTilesPath to add.
   * @return The MapLayerTilesPath that was created for the tiles.
   */
  public final TileLineLayer addTiles(DataSourceTilesPath tiles) {
    TileLineLayer layer = new TileLineLayer(tiles);
    layers.add(0, layer);
    return layer;
  }

  /**
   *
   * @param tiles
   * @param hasLabels
   * @return
   */
  public final TileLineLabelLayer addTiles(DataSourceTilesPath tiles,
      boolean hasLabels) {
    TileLineLabelLayer layer = new TileLineLabelLayer(tiles);
    layers.add(layer);
    return layer;
  }

  /**
   * Adjusts the envelope to account for the aspect ratio of the view.
   */
  public final void fixEnvelope() {

    // Regardless of zoom scale, behavior depends on the aspect ratio of
    // the maximum zoom of the content.  If the full content were fit
    // exactly into the view, then we reduced the width, the envelope
    // needs to get taller.
    Envelope bounds = this.getEnvelopeMercator();
    double geoAspect = bounds.getWidth() / bounds.getHeight();
    if (view.width == 0 || view.height == 0) {
      return;
    }
    double aspect = view.width / (double) view.height;

    if (geoAspect < aspect) {
      // original data is taller than current data, so modify width.
      double half = (envelope.getHeight() / 2) * aspect;
      double mid = (envelope.getMin().getX()
          + envelope.getMax().getX()) / 2;
      double min = mid - half;
      double max = mid + half;
      envelope.getMin().setX(min);
      envelope.getMax().setX(max);
    } else {
      double half = envelope.getWidth() / (aspect * 2);
      double mid = (envelope.getMin().getY()
          + envelope.getMax().getY()) / 2;
      double min = mid - half;
      double max = mid + half;
      envelope.getMin().setY(min);
      envelope.getMax().setY(max);
    }
    this.invalidate(view);
  }

  /**
   * Gets the geographic extents that contains all the openedLayers. If this is
   * null, then the default geographic extents are returned.
   *
   * @return
   */
  public final Envelope getMaxEnvelope() {
    if (layers == null || layers.isEmpty()) {
      return new DefaultEnvelope(-180, -85, 180, 85);
    }
    Envelope result = new DefaultEnvelope();
    for (Layer<?> layer : layers) {
      result.expandToInclude(layer.getEnvelope());
    }
    return result;

  }

  /**
   * Gets the first instance of a pointLayer named the specified name.
   *
   * @param name The string name to search for.
   * @return A Layer that matches the specified name.
   */
  public final Optional<? extends Layer<?>> getLayer(String name) {
    for (Layer<?> layer : this.layers) {
      if (layer.getName() != null && layer.getName().equals(name)) {
        return Optional.of(layer);
      }
    }
    return Optional.empty();
  }

  /**
   * Forces a redrawing from the data openedLayers of a rectangular region. This
   * should happen any time the view is resized or the geographic extents in the
   * view need to be updated, but we would rather delay temporarily until we
   * think the process has finished.
   *
   * @param rect
   */
  public final void invalidate(Rectangle rect) {
    if (rect.width == 0 || rect.height == 0) {
      return;
    }
    if (layers == null || layers.isEmpty()) {
      return;
    }
    if (this.offLevel) {
      doSetLevel(getClosestLevel());
      this.offLevel = false;
    }
    if (renderThread != null && renderThread.isAlive()) {
      renderThread.restart();
    } else {
      renderThread = new MapContentRenderThread(new MapContentRenderer(this));
      renderThread.start();
    }

  }

  /**
   * This simply draws the cached display image, so long as visible is true.
   *
   * @param args The PaintArgs containing the drawing canvas.
   */
  public final void paint(MapPaintArgs args) {
    if (!visible) {
      return;
    }
    if (display == null) {
      return;
    }
    // draw static background openedLayers
//    if (!args.getGraphics().getClipBounds().equals(args.getMap().getBounds())) {
//      args.getMap().setSize(args.getGraphics().getClipBounds().getSize());
//      this.invalidate(args.getGraphics().getClipBounds());
//      args.getMap().getParent().getParent().repaint();
//    }

    args.getGraphics().drawImage(display, 0, 0, null);
    paintDynamicLayers(args);
  }

  /**
   * This uses the frame on the paint args to paint the dynamic openedLayers,
   * and take into consideration whether the pointLayer should be visible at the
   * current scale.
   *
   * @param args
   */
  public final void paintDynamicLayers(PaintArgs args) {
    // draw "dynamic" openedLayers, which simply aren't part of the back buffer.
    for (Layer<?> layer : dynamicLayers) {
      if (!layer.isVisible()) {
        continue;
      }
      if (layer.getMinScale() < 0 || this.getScale() > layer.getMinScale()) {
        if (layer.getMaxScale() < 0 || this.getScale() < layer.getMaxScale()) {
          // If one pointLayer fails, still try other openedLayers.
          try {
            layer.paintWeb(args);
          } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, "Error in paintImmediately drawing a layer:"
                + layer.toString(), ex);
          }
        }
      }
    }
  }

  /**
   * Rather than using the built in buffer, this instructs the openedLayers to
   * draw themselves to the specified graphics object. This ignores visibility,
   * and has no affect on the existing "display" or "display Envelope". The
   * frame does not have to match the current map frame at all. Further, any
   * special handling for slow-loading content should be disabled.
   *
   * @param args
   */
  public final void paintContent(PaintArgs args) {

    for (Layer<?> layer : layers) {
      if (!layer.isVisible()) {
        continue;
      }
      if (layer.getMinScale() < 0 || this.getScale() > layer.getMinScale()) {
        if (layer.getMaxScale() < 0 || this.getScale() < layer.getMaxScale()) {
          // If one pointLayer fails, still try other openedLayers.
          try {
            layer.paintWeb(args);
          } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, "Error in paintImmediately drawing a layer:"
                + layer.toString(), ex);
          }
        }
      }
    }
  }

  /**
   * While paint draws the background image, which is cached, paintImmediately
   * will force a paint from the actual openedLayers. If offLevel is true, then
   * this will adjust the view to the closest level first, to ensure the tile
   * graphics look good.
   */
  public final void paintImmediately() {
    if (this.view.width == 0 || this.view.height == 0) {
      return;
    }
    if (this.offLevel) {
      doSetLevel(getClosestLevel());
      this.offLevel = false;
    }
    BufferedImage background = new BufferedImage(this.view.width, this.view.height,
        BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics = background.createGraphics();
    PaintArgs args = new PaintArgs(this, graphics, new GeoFrame(this.envelope,
        this.view));

    ArrayList<Layer<?>> _layers = new ArrayList<>(this.layers);
    for (Layer<?> layer : _layers) {
      if (!layer.isVisible()) {
        continue;
      }
      if (layer.getMinScale() < 0 || this.getScale() > layer.getMinScale()) {
        if (layer.getMaxScale() < 0 || this.getScale() < layer.getMaxScale()) {
          // If one pointLayer fails, still try other openedLayers.
          try {
            layer.paintWeb(args);
          } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, "Error in paintImmediately drawing a layer:"
                + layer.toString(), ex);
          }

        }
      }
    }
    graphics.dispose();
    display = background;
    displayEnvelope.copyProperties(this.getEnvelopeMercator());
  }

  /**
   * Removes the first pointLayer found with the specified name. If no
   * pointLayer is found with the specified name, nothing happens.
   *
   * @param name The string name of the pointLayer to remove.
   */
  public final void removeLayer(String name) {
    Optional<? extends Layer<?>> layer = getLayer(name);
    if (layer.isPresent()) {
      this.layers.remove(layer.get());
    }
  }

  /**
   * Removes the specified pointLayer from the map content. Ignores if the
   * MapCOntents does not contain the pointLayer.
   *
   * @param layer the pointLayer to remove.
   */
  public final void removeLayer(Layer<?> layer) {
    if ((layer != null) && (this.layers.contains(layer))) {
      this.layers.remove(layer);
    }
    if ((layer != null) && (this.dynamicLayers.contains(layer))) {
      this.dynamicLayers.remove(layer);
    }
  }

  /**
   * Change the displayEnvelope to the pointLayer's Envelope - ignored if
   * pointLayer.envelope is a point (delta X and delta Y = 0).
   *
   * @param layer the pointLayer to zoom to.
   */
  public final void zoomToLayer(Layer<?> layer) {
    if ((layer != null) && (layer.getEnvelope().getMin().getX()
        != layer.getEnvelope().getMax().getX())
        && (layer.getEnvelope().getMin().getY()
        != layer.getEnvelope().getMax().getY())) {
      this.setEnvelope(Mercator.toMerc(layer.getEnvelope()));
      this.fixEnvelope();
    }
  }

  /**
   * Zooms to envelope, expects argument to be WGS84 envelope.
   *
   * @param env
   */
  public void zoomToEnv(Envelope env) {
    this.setEnvelope(Mercator.toMerc(env));
    this.fixEnvelope();
  }

  /**
   * Zooms to the specified coordinate.
   *
   * @param coord The coordinate to zoom to.
   */
  public final void zoomToCoordinate(Coord coord) {
    Envelope currentEnvelope = this.getEnvelopeMercator().copy();
    Coord currentCenter = Mercator.fromMerc(currentEnvelope.getCenter());
    Coord translation = new CoordXY(currentCenter.getX() - coord.getX(),
        currentCenter.getY() - coord.getY());
    Envelope zoomEnvelope = Mercator.fromMerc(currentEnvelope);
    zoomEnvelope.getMax().setX(zoomEnvelope.getMax().getX()
        - translation.getX());
    zoomEnvelope.getMax().setY(zoomEnvelope.getMax().getY()
        - translation.getY());
    zoomEnvelope.getMin().setX(zoomEnvelope.getMin().getX()
        - translation.getX());
    zoomEnvelope.getMin().setY(zoomEnvelope.getMin().getY()
        - translation.getY());
    this.setEnvelope(Mercator.toMerc(zoomEnvelope));
    this.fixEnvelope();
  }

  /**
   * Sets the zoom level so that the pixel resolution and map scale size the
   * tiles so that the tiles of the specified level are 256x256 pixels.
   *
   * @param level the integer level to set from 0 to 18.
   */
  public final void setLevel(int level) {
    doSetLevel(level);
    this.paintImmediately();
  }

  /**
   * Sets the level to modify the envelope, but does not repaint. This is
   * necessary for calls from other internal methods besides the public setLevel
   * like invalidate and paintImmediately.
   *
   * @param level The integer level to assign as the current zoom level from 0
   * to 18.
   */
  private void doSetLevel(int level) {
    double mapScale = this.getEnvelopeMercator().getWidth() / getView().getWidth();
    if (mapScale == 0) {
      return;
    }
    if (Double.isNaN(mapScale)) {
      return;
    }
    // using the rounded tile scale, we now need the degrees to pixels of that scale
    double dpp = 360 / (256 * Math.pow(2, level));

    // Adjust the factor so that when we use it, it will result in a new scale
    // that is exactly one of the tile level scales.
    Envelope result = this.getEnvelopeMercator();
    double sc = dpp / mapScale;
    result = result.zoom(sc);
    this.envelope.copyProperties(result);
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * When updates are made, rendering occurs in a threaded process. The display
   * envelope is the envelope of the last successfully rendered display image.
   * The "current" envelope may be different.
   *
   * @return the displayEnvelope
   */
  public final Envelope getDisplayEnvelope() {
    return displayEnvelope;
  }

  /**
   * When updates are made, rendering occurs in a threaded process. The display
   * envelope is the envelope of the last successfully rendered display image.
   * The "current" envelope may be different.
   *
   * @param displayEnvelope the displayEnvelope to set
   */
  public final void setDisplayEnvelope(Envelope displayEnvelope) {
    this.displayEnvelope.copyProperties(displayEnvelope);
  }

  /**
   * Gets the dynamic pointLayer for a specific pointLayer name.
   *
   * @param name The string name of the pointLayer to get.
   * @return A Layer representing the first match of a dynamic pointLayer with
   * the specified name.
   */
  public final Optional<? extends Layer<?>> getDynamicLayer(String name) {
    if (dynamicLayers != null) {
      for (Layer<?> dynamicLayer : dynamicLayers) {
        if (dynamicLayer.getName() != null
            && dynamicLayer.getName().equals(name)) {
          return Optional.of(dynamicLayer);
        }
      }
    }
    return Optional.empty();
  }

  /**
   * Gets the list of dynamic openedLayers that are drawn on top of the back
   * buffer every time. This allows static openedLayers that don't change to
   * serve as a background for dynamic openedLayers that are changed more
   * frequently.
   *
   * @return the dynamicLayers
   */
  public final List<Layer<?>> getDynamicLayers() {

    return dynamicLayers;
  }

  /**
   * Gets the list of all openedLayers.
   *
   * @return The list of all openedLayers from the map.
   */
  public final List<Layer<?>> getAllLayers() {
    List<Layer<?>> allLayers = new ArrayList<>();
    allLayers.addAll(this.layers);
    allLayers.addAll(this.dynamicLayers);
    return allLayers;
  }

  /**
   * Sets the list of dynamic openedLayers that are drawn on top of the back
   * buffer every time. This allows static openedLayers that don't change to
   * serve as a background for dynamic openedLayers that are changed more
   * frequently.
   *
   * @param dynamicLayers the dynamicLayers to set
   */
  public final void setDynamicLayers(List<Layer<?>> dynamicLayers) {
    this.dynamicLayers = dynamicLayers;
  }

  /**
   * Sets the display envelope, which is not necessarily the maximum bounds of
   * the content. Setting this directly will force offLevel to become true.
   *
   * @param displayEnvelope The envelope for the display. This may force the map
   * into a state where it is not exactly on a level.
   */
  public final void setEnvelope(Envelope displayEnvelope) {
    this.envelope.copyProperties(displayEnvelope);
    this.offLevel = true;
  }

  /**
   * Gets the list of openedLayers. The ObservedList is responsible for firing
   * events if the list is altered in a way that required redrawing of the map.
   *
   * @return the openedLayers
   * @see #getLayer(String)
   * @see #getDynamicLayers()
   */
  public final ObservedList<Layer<?>> getLayers() {
    return layers;
  }

  /**
   * If both openedLayers are not within the map, then this will return null. If
   * both openedLayers are found, then this will return all the openedLayers
   * including the openedLayers and between the openedLayers in the current
   * pointLayer stack.
   *
   * @param start The start pointLayer.
   * @param end The end pointLayer.
   * @return A List of Layer elements positioned between the start and end
   * openedLayers.
   */
  public final List<Layer<?>> getLayersBetween(Layer<?> start,
      Layer<?> end) {
    List<Layer<?>> result = new ArrayList<>();
    if (!this.layers.contains(start) || !this.layers.contains(end)) {
      return result;
    }
    boolean include = false;
    for (Layer<?> layer : layers) {
      if (layer == start || layer == end) {
        if (include) {
          result.add(layer);
          break;
        }
        include = true;
      }
      if (include) {
        result.add(layer);
      }
    }
    return result;
  }

  /**
   * Sets the list of openedLayers. The ObservedList is responsible for firing
   * events if the list is altered in a way that required redrawing of the map.
   *
   * @param layers the openedLayers to set.
   */
  public final void setLayers(ObservedList<Layer<?>> layers) {
    if (layers != this.layers) {
      List<ObservedListListener> items = new ArrayList<>();
      items.addAll(Arrays.asList(this.layers.getListenerList().
          getListeners(ObservedListListener.class)));
      this.layers = layers;
      for (ObservedListListener listener : items) {
        this.layers.addListener(listener);
      }
    }
  }

  /**
   * Moves the specified pointLayer to the specified index.
   *
   * @param layer The pointLayer to move.
   * @param index The zero based integer index describing the pointLayer
   * position.
   */
  public final void moveToIndex(Layer<?> layer, int index) {
    if (this.layers != null) {
      this.layers.move(layer, index);
    }
  }

  /**
   * Move the <tt>fromLayer</tt> to the current position of the
   * <tt>targetLayer</tt>, if the former's render position is above the latter.
   * That will result in targetLayer being rendered on top of the fromLayer.
   *
   * @param moveLayer the pointLayer to move
   * @param targetLayer the target pointLayer
   */
  public final void moveBelowLayer(Layer<?> moveLayer, Layer<?> targetLayer) {
    if ((this.layers == null) || (moveLayer == null) || (targetLayer == null)
        || (moveLayer == targetLayer)) {
      return;
    }
    int fromIdx = this.layers.indexOf(moveLayer);
    int toIdx = this.layers.indexOf(targetLayer);
    if ((toIdx >= 0) && (fromIdx > toIdx)) {
      this.layers.move(moveLayer, toIdx);
    }
  }

  /**
   * Move the <tt>fromLayer</tt> to the current position of the
   * <tt>targetLayer</tt>, if the former's render position is below the latter.
   * That will result in fromLayer being rendered on top of the targetLayer.
   *
   * @param moveLayer the pointLayer to move
   * @param targetLayer the target pointLayer
   */
  public final void moveAboveLayer(Layer<?> moveLayer, Layer<?> targetLayer) {
    if ((this.layers == null) || (moveLayer == null) || (targetLayer == null)
        || (moveLayer == targetLayer)) {
      return;
    }
    int fromIdx = this.layers.indexOf(moveLayer);
    int toIdx = this.layers.indexOf(targetLayer);
    if ((toIdx >= 0) && (fromIdx < toIdx)) {
      this.layers.move(moveLayer, toIdx);
    }
  }

  /**
   * Move the <tt>fromLayer</tt> to the top of pointLayer list - the last
   * pointLayer to render.
   *
   * @param moveLayer the pointLayer to move
   */
  public final void moveToTop(Layer<?> moveLayer) {
    if ((this.layers == null) || (moveLayer == null)) {
      return;
    }
    int fromIdx = this.layers.indexOf(moveLayer);
    if ((fromIdx >= 0) && (fromIdx < (this.layers.size() - 1))) {
      this.layers.move(moveLayer, (this.layers.size() - 1));
    }
  }

  /**
   * Move the <tt>fromLayer</tt> to the first pointLayer to render. If
   * <tt>fromLayer</tt> is a title pointLayer, it will be move to position
   * zero(0). Otherwise, the pointLayer will be moved to the first pointLayer
   * position above the title openedLayers.
   *
   * @param moveLayer the pointLayer to move
   */
  public final void moveToBottom(Layer<?> moveLayer) {
    if ((this.layers == null) || (moveLayer == null)) {
      return;
    }
    int fromIdx = this.layers.indexOf(moveLayer);
    int toIdx = 0;
    if (!(moveLayer instanceof TileLayer)) {
      for (int iLayer = 0; iLayer < layers.size(); iLayer++) {
        Layer<? extends Symbolizer> mapLayer = layers.get(iLayer);
        if (!(mapLayer instanceof TileLayer)) {
          toIdx = iLayer;
          break;
        }
      }
    }
    if ((fromIdx >= 0) && (fromIdx > toIdx)) {
      this.layers.move(moveLayer, toIdx);
    }
  }

  /**
   * Gets the boolean value indicating whether or not the current extents
   * correspond with a zoom level resolution wise.
   *
   * @return the offLevel
   */
  public final boolean isOffLevel() {
    return offLevel;
  }

  /**
   * Sets the boolean value indicating whether or not the current extents
   * correspond with a zoom level resolution wise.
   *
   * @param offLevel the offLevel to set
   */
  public final void setOffLevel(boolean offLevel) {
    this.offLevel = offLevel;
  }

  /**
   * Gets a random UUID for this map that uniquely defines the map.
   *
   * @return the id
   */
  public final UUID getId() {
    return id;
  }

  /**
   * When setting the view, this should be set to the actual rectangular size of
   * the containing control. The geographic extent will be modified and may be
   * larger than the actual earth.
   *
   * @param view the Rectangle defining the complete drawing space.
   */
  public final void setView(Rectangle view) {
    this.view = view;
  }

  /**
   * Gets a boolean value that determines whether or not the map is displayed.
   *
   * @return the visible
   */
  public final boolean isVisible() {
    return visible;
  }

  /**
   * Sets a boolean value that determines whether or not the map is displayed.
   *
   * @param visible the visible to set
   */
  public final void setVisible(boolean visible) {
    this.visible = visible;
  }
//</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Display Implementation">
  /**
   * Gets the buffered cached image to render by the paint method.
   *
   * @return the display image.
   */
  @Override
  public final BufferedImage getDisplay() {
    return display;
  }

  /**
   * Sets the buffered cached image to render by the paint method.
   *
   * @param display the display to set
   */
  @Override
  public final synchronized void setDisplay(BufferedImage display) {
    this.display = display;
    displayEnvelope.copyProperties(this.getEnvelopeMercator());
    this.fireRenderComplete(new DisplayEvent(this));
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="DataFrame Implementation">
  /**
   * {@inheritDoc}
   * <p>
   * OVERRIDE: Gets the display envelope, which is not necessarily the maximum
   * bounds of the content.</p>
   */
  @Override
  public final Envelope getEnvelopeMercator() {
    return envelope;
  }

  /**
   * Gets the view rectangle where the geographic map content is drawn. When
   * zoomed all the way out, this may in fact be smaller than the control.
   *
   * @return the Rectangle defining the complete drawing space.
   */
  @Override
  public final Rectangle getView() {
    return view;
  }

  /**
   * Gets a mathematical scale calculation.
   *
   * @return the double value ratio of world distance to display distance.
   */
  @Override
  public final double getScale() {
    double worldInch = envelope.getWidth() * 111319.9 * 39.3701;
    double displayInch = view.width / 96;
    return worldInch / displayInch;
  }

  /**
   * Gets the zoom level from 0 to 18 for the open street map tile openedLayers
   * that most closely matches the pixel resolution of the current view for
   * displaying the current extent.
   *
   * @return the integer level from 0 to 18.
   */
  @Override
  public final int getClosestLevel() {
    if (this.getEnvelopeMercator() == null || this.getEnvelopeMercator().isEmpty()) {
      return 0;
    }
    double target = this.getEnvelopeMercator().getWidth() / getView().getWidth();
    // from the longitude per pixel, estimate how many 256x256 tiles are required
    double n = Mercator.LL_SPAN / (target * TILE_SIZE);

    // using the number of tiles, estimate the tile level that has approximately that many.
    double level = Math.log(n) / Math.log(2);

    return (int) Math.round(level);
  }

  /**
   * Gets the Projector object that supports the transform method for relating
   * the pixel rectangular display to a geographic extents.
   *
   * @return the Projector object.
   */
  @Override
  public final Projector getProjector() {
    return new Projector(this);
  }
  //</editor-fold>

  /**
   * Gets the geographic envelope in the native shapefileProjection for this
   * dataset. This is not the same as the actual map coordinates, which are in
   * TedMercator, but should match instead a geographic shapefileProjection
   * specified in the getProjection() property.
   *
   * @return The geographic envelope.
   */
  @Override
  public final Envelope getEnvelope() {
    Envelope env = this.getEnvelopeMercator().copy();
    Envelope wgs84 = Mercator.fromMerc(env);
    Envelope result = wgs84;
    if (!projection.equals(WGS84)) {
      try {
        result = Reproject.reprojectEnvelope(wgs84, WGS84, projection);
      } catch (ProjectionException ex) {
        System.out.println("GeoFrame.getEnvelope(): Failed to reproject "
            + projection.getName() + " to WGS84.");
      }
    }
    return result;
  }

  /**
   * The shapefileProjection that defines the geographic envelope. This does not
   * have any effect on EnvelopeMercator, which is always in the Ted Mercator
   * shapefileProjection that the map panel uses. This is used for the
   * getEnvelope() Method only. It will not actually change the appearance of
   * the shapes on the map.
   *
   * @return the shapefileProjection
   */
  public final ProjectionInfo getProjection() {
    return projection;
  }

  public static BufferedImage resize(BufferedImage img, int newW, int newH) {
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
  }

}
