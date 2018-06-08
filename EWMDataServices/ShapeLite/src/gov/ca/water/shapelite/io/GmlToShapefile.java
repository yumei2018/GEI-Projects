/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.ShapefileException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GmlToShapefile {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private ShapefileBuilder builder;

  private String projection;

  private FeatureModifier customHandler;

  //</editor-fold>
  /**
   * Creates a new instance of the GmlToShapefile class.
   */
  public GmlToShapefile() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This method will convert the specified InputStream for a GML file into a
   * shapefile while holding only a small amount in memory at a time.
   *
   * @param stream
   * @param shapefileName
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  public void convert(InputStream stream, String shapefileName) throws ParserConfigurationException, SAXException, IOException {
    builder = new ShapefileBuilder();
    builder.setProjection(projection);
    try {
      builder.open(shapefileName);
    } catch (ShapefileException ex) {
      Logger.getLogger(GmlToShapefile.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
    }
    if (customHandler != null) {
      builder.setFields(customHandler.modifyFields(builder.getFields()));
    }

    GmlFeatureScanner scanner = new GmlFeatureScanner(new FeatureHandler() {

      int index = 0;

      @Override
      public void featureCreated(Feature f) {
        try {
          if (customHandler != null) {
            f = customHandler.modifyFeature(f);
          }
          builder.add(f);
        } catch (ShapefileException | IOException ex) {
          Logger.getLogger(GmlToShapefile.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);
        }
        index++;
      }
    });
    scanner.parse(stream);

    builder.close();
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the projection
   */
  public String getProjection() {
    return projection;
  }

  /**
   * @param projection the projection to set
   */
  public void setProjection(String projection) {
    this.projection = projection;
  }

  //</editor-fold>
  /**
   * @return the customHandler
   */
  public FeatureModifier getCustomHandler() {
    return customHandler;
  }

  /**
   * @param customHandler the customHandler to set
   */
  public void setCustomHandler(FeatureModifier customHandler) {
    this.customHandler = customHandler;
  }
}
