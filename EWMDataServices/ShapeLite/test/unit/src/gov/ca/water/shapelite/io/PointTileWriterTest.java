/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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
import gov.ca.water.shapelite.analysis.TileLocation;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.data.PointTile;
import gov.ca.water.shapelite.data.ColRow;
import gov.ca.water.shapelite.data.TileUrl;
import gov.ca.water.shapelite.map.Mercator;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointTileWriterTest {

  public PointTileWriterTest() {
  }

  /**
   * Test of write method, of class PointTileWriter.
   *
   * @throws java.lang.Exception if there is an issue.
   */
  @Test
  public final void testWrite() throws Exception {
    Random rnd = new Random();
    int level = 16;
    double cellSize = TileUrl.getCellSize(level);
    int tileX = 500;
    int tileY = 500;
    Coord tl = TileUrl.getTopLeftMercator(level, new TileLocation(tileX, tileY, 0, 0));
    PointTile pt = new PointTile();
    pt.setTileX(tileX);
    pt.setTileY(tileY);
    pt.setZoomLevel(level);
    for (int i = 0; i < 1000; i++) {
      double x = tl.getX() + (rnd.nextDouble() * cellSize * 256);
      double y = tl.getY() - (rnd.nextDouble() * cellSize * 256);
      double z = rnd.nextDouble() * 100;
      CoordZ c = new CoordZ(x, y, z);
      CoordZ wgs84 = Mercator.fromMerc(c);
      pt.addCoords(wgs84);
    }

    Path temp = Files.createTempDirectory("PointTileWriterTest");
    PointTileWriter writer = new PointTileWriter();
    writer.write(pt, temp.toString());
    String readPath = temp.toString() + File.separator
        + level + File.separator
        + tileX + File.separator
        + tileY + ".pts";
    PointTileReader reader = new PointTileReader();
    PointTile opened = reader.open(readPath);
    assertEquals(level, opened.getZoomLevel());
    assertEquals(tileX, opened.getTileX());
    assertEquals(tileY, opened.getTileY());
    double tolerance = cellSize / 256.0;
    for (int row = 0; row < 256; row++) {
      for (int col = 0; col < 256; col++) {
        ColRow rc = new ColRow(col, row);
        if (!pt.getCells().containsKey(rc)) {
          if (opened.getCells().containsKey(rc)) {
            fail("Did not expect coordinates for row " + rc.getRow() + ", col "
                + rc.getColumn());
          }
          continue;
        }
        List<CoordZ> original = pt.getCoords(rc);
        List<CoordZ> other = opened.getCoords(rc);
        assertEquals(original.size(), other.size());

        for (int i = 0; i < original.size(); i++) {
          CoordZ a = original.get(i);
          CoordZ b = other.get(i);
          if (Math.abs(a.getX() - b.getX()) > tolerance) {
            fail("Difference detected between " + a.toString() + ", and "
                + b.toString());
          }
          if (Math.abs(a.getY() - b.getY()) > tolerance) {
            fail("Difference detected between " + a.toString() + ", and "
                + b.toString());
          }
          if (Math.abs(a.getZ() - b.getZ()) > .01) {
            fail("Difference detected between " + a.toString() + ", and "
                + b.toString());
          }
        }
      }
    }
  }

}
