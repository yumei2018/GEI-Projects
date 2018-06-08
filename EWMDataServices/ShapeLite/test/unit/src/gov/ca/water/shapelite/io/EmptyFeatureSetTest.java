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

import com.vividsolutions.jts.util.Assert;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import java.io.File;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class EmptyFeatureSetTest {

  @Rule
  public final TemporaryFolder testFolder = new TemporaryFolder();

  /**
   * A test to write and read an empty featureset that is set to a nullshape.
   *
   * @throws java.io.IOException if there is an error reading the file.
   * @throws gov.ca.water.shapelite.ShapefileException  If the shapefile
   * data is not correctly parsed.
   */
  @Test
  public void testWriteRead() throws IOException, ShapefileException {
    FeatureSet fs = new FeatureSet();
    File file = testFolder.newFile("FeatureSet.shp");
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    ShapefileReader reader = new ShapefileReader();
    reader.open(file.getAbsolutePath());
    FeatureSet test = reader.getFeatures();
    Assert.equals(fs.getDefaultShapeType(), test.getDefaultShapeType());
  }

  /**
   * A test to write and read an empty featureset that is set to a nullshape.
   *
   * @throws java.io.IOException
   * @throws gov.ca.water.shapelite.ShapefileException
   */
  @Test
  public void testWriteReadPolygon() throws IOException, ShapefileException {
    FeatureSet fs = new FeatureSet();
    fs.setDefaultShapeType(ShapeType.Polygon);
    File file = testFolder.newFile("FeatureSet.shp");
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(fs, file.getAbsolutePath());
    ShapefileReader reader = new ShapefileReader();
    reader.open(file.getAbsolutePath());
    FeatureSet test = reader.getFeatures();
    Assert.equals(fs.getDefaultShapeType(), test.getDefaultShapeType());
  }



}
