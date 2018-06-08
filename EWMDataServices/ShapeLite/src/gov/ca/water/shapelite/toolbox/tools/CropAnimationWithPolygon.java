/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.toolbox.tools;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.data.dataset.ImageDataset;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.InputFolderParameter;
import gov.ca.water.shapelite.toolbox.IntegerParameter;
import gov.ca.water.shapelite.toolbox.OutputFolderParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CropAnimationWithPolygon extends ToolBase {

  /**
   * Input polygon shape.
   */
  private final InputFileParameter paramInputCropPolygon;

  /**
   * The folder with input files.
   */
  private final InputFolderParameter paramInputFolder;

  /**
   * The output folder parameter where images will be written.
   */
  private final OutputFolderParameter paramOutputFolder;

  /**
   * The start index parameter.
   */
  private final IntegerParameter paramStartIndex;

  /**
   * The bounds polygon
   */
  private Shape bounds;

  /**
   * Creates a new instance of the CropAnimationWithPolygon tool.
   */
  public CropAnimationWithPolygon() {
    setName("Crop Animation with Polygon");
    setDescription("This cycles through the images in an animation sequence.  "
        + "It will always keep the top-left corner the same.  It will reduce "
        + "the size of the image to fit the polygon on the bottom right.  "
        + "It will only write pixel values that are inside the polygon. "
        + " Pixel values outside the polygon will be transparent.");
    this.setHelpText("This cycles through the images in an animation sequence.  "
        + "It will always keep the top-left corner the same.  It will reduce "
        + "the size of the image to fit the polygon on the bottom right.  "
        + "It will only write pixel values that are inside the polygon. "
        + " Pixel values outside the polygon will be transparent.");
    this.setCategory("Animation");
    this.setHelpImageFilename("resources/CropAnimation.png");

    paramInputFolder = new InputFolderParameter();
    paramInputFolder.setParameterName("Source Image folder");
    paramInputFolder.setDescription("The folder with the images like base0001.png");
    paramInputFolder.setHelpText("The folder with the images like base0001.png");
    this.getParameters().add(paramInputFolder);

    paramOutputFolder = new OutputFolderParameter();
    paramOutputFolder.setParameterName("Output Folder for Line Shapefiles");
    paramOutputFolder.setDescription("The Shapefile output folder to store the "
            + "shapefiles with extracted lines.");
    paramOutputFolder.setHelpText("The Shapefile output folder to store the "
            + "shapefiles with extracted lines.");
    this.getParameters().add(paramOutputFolder);

    paramInputCropPolygon = new InputFileParameter();
    paramInputCropPolygon.setParameterName("Point Shapefile");
    paramInputCropPolygon.setExtension(".shp");
    paramInputCropPolygon.setDescription("The Shapefile where the first polyon"
        + " in the shapefile is the boundary to be used. ");
    paramInputCropPolygon.setHelpText("The Shapefile where the first polyon"
        + " in the shapefile is the boundary to be used. ");
    this.getParameters().add(paramInputCropPolygon);


    paramStartIndex = new IntegerParameter();
    paramStartIndex.setParameterName("Start Index");
    paramStartIndex.setMin(0);
    paramStartIndex.setParameterText("0");
    paramStartIndex.setHelpText("The first frame to start processing.");
    paramStartIndex.setDescription("The first frame to start processing.");


  }

  @Override
  public void runImmediately() {
    try {
      getBounds(paramInputCropPolygon.getParameterText());
      cropImages(paramInputFolder.getParameterText(),
          paramOutputFolder.getParameterText());
    } catch (IOException | ProjectionException ex) {
      Logger.getLogger(CropAnimationWithPolygon.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Gets the polygon bounds from the specified path.
   * @param path
   * @throws IOException
   * @throws ProjectionException
   */
  private void getBounds(String path) throws IOException, ProjectionException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(path + "\\Bounds.shp");
    List<Shape> shapes = reader.getShapes();
    bounds = shapes.get(0);
    ProjectionInfo proj = ProjectionInfo.fromEsriString(reader.getProjection());
    bounds = Reproject.reprojectShape(bounds, proj, Projections.getGeographic()
        .getWorld().getWGS1984());

  }

  /**
   * Crops the images.
   * @param inPath
   * @param outPath
   * @throws IOException
   */
  private void cropImages(String inPath, String outPath) throws IOException {
    File outfolder = new File(outPath);
    if (!outfolder.exists()) {
      outfolder.mkdirs();
    }
    File dir = new File(inPath);
    File[] files = dir.listFiles();
    ImageDataset img = new ImageDataset();
    img.open(new File(inPath + "\\base0000.png"));
    Projector proj = img.getProjector();
    bounds.calculateBounds();
    Envelope mercBounds = Mercator.toMerc(bounds.getEnvelope());
    Point bottomLeft = proj.getPoint(mercBounds.getMin());
    Point topRight = proj.getPoint(mercBounds.getMax());
    int width = topRight.x;
    int height = bottomLeft.y;

    for (File file : files) {
      if (!file.getAbsolutePath().endsWith(".png")) {
        Files.copy(file.toPath(), Paths.get(outPath + "\\" + file.getName()));
      } else {
        BufferedImage source = ImageIO.read(file);
        BufferedImage image = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            Coord merc = proj.getCoordinate(new Point(x, y));
            Coord c = Mercator.fromMerc(merc);
            if (bounds.intersects(c)) {
              int clr = source.getRGB(x, y);
              image.setRGB(x, y, clr);
            }
          }
        }
        ImageIO.write(image, "PNG", new File(outPath + "\\" + file.getName()));
      }
    }
  }

}
