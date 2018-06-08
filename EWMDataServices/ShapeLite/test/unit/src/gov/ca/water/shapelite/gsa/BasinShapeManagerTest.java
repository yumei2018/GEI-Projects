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
package gov.ca.water.shapelite.gsa;

import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.ShapefileException;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class BasinShapeManagerTest {

  public BasinShapeManagerTest() {
  }

  @Test
  public void templateTest() throws ShapefileException {
    BasinShapeManager manager = new BasinShapeManager();
    manager.saveReportShapefile("D:\\Data\\Basins\\Template.zip");
  }

  @Test
  public void addSingleTest() throws IOException, ShapefileException {
    BasinShapeManager manager = new BasinShapeManager();
    //manager.loadBasinZipStream(stream);
    manager.loadBasinShapefile("D:\\Data\\Basins\\GWBasins.zip");
    manager.add(1, "AAgency", "ABasin", "9-29");
    manager.saveReportShapefile("D:\\Data\\Basins\\BasinReportSingle.zip");
    //manager.saveReportShapefile(stream);
    FeatureSet fs = manager.getReportMaster();
    boolean stop = true;
  }

  @Test
  public void addMultipleTest() throws IOException, ShapefileException {
    BasinShapeManager manager = new BasinShapeManager();
    manager.loadBasinShapefile("D:\\Data\\Basins\\GWBasins.zip");
    manager.add(1, "AAgency", "ABasin", "9-29");
    manager.add(2, "BAgency", "BBasin", "7-47");
    manager.add(3, "BAgency", "BBasin", "9-15");
    manager.remove(2);
    manager.saveReportShapefile("D:\\Data\\Basins\\BasinReportMultiple.zip");
    FeatureSet fs = manager.getReportMaster();
    boolean stop = true;
  }

  @Test
  public void RemoveTest() throws IOException, ShapefileException {
    BasinShapeManager manager = new BasinShapeManager();
    manager.loadBasinShapefile("D:\\Data\\Basins\\GWBasins.zip");
    manager.add(1, "AAgency", "ABasin", "9-29");
    manager.remove(1);
    manager.saveReportShapefile("D:\\Data\\Basins\\BasinReportDeleted.zip");
    FeatureSet fs = manager.getReportMaster();
    boolean stop = true;
  }

}
