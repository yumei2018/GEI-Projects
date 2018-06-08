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
package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.data.Projector;
import gov.ca.water.shapelite.data.dataset.ImageDataset;
import gov.ca.water.shapelite.map.Mercator;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.projection.Reproject;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.imageio.ImageIO;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TrimAnimationImagesTest {

  private Shape bounds;

  private boolean[][] inPolygon;

  public TrimAnimationImagesTest() {
  }

  public void cropImages() throws IOException, ProjectionException {
    String path = "D:\\Data\\Animations\\CAC\\TUFLOW\\1997_240pct";
    getBounds(path);
    cropImages(path + "\\Images", path + "\\CroppedImages");

  }

  private void getBounds(String path) throws IOException, ProjectionException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(path + "\\Bounds.shp");
    List<Shape> shapes = reader.getShapes();
    bounds = shapes.get(0);
    ProjectionInfo proj = ProjectionInfo.fromEsriString(reader.getProjection());
    bounds = Reproject.reprojectShape(bounds, proj, Projections.getGeographic()
        .getWorld().getWGS1984());

  }

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
    int width = img.getWidth();
    int height = img.getHeight();
    inPolygon = new boolean[width][height];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Coord merc = proj.getCoordinate(new Point(x, y));
        Coord c = Mercator.fromMerc(merc);
        if (bounds.intersects(c)) {
          inPolygon[x][y] = true;
        }
      }
    }
    for (File file : files) {
      if (!file.getAbsolutePath().endsWith(".png")) {
        Files.copy(file.toPath(), Paths.get(outPath + "\\" + file.getName()));
      } else {
        BufferedImage source = ImageIO.read(file);
        BufferedImage image = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            if (inPolygon[x][y]) {
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
