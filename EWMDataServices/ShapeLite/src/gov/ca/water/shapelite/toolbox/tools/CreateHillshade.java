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
package gov.ca.water.shapelite.toolbox.tools;

import gov.ca.water.shapelite.raster.HillShade;
import gov.ca.water.shapelite.data.dataset.RasterDataset;
import gov.ca.water.shapelite.toolbox.DoubleParameter;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.OutputFileParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CreateHillshade extends ToolBase {

  /**
   * The maximum altitude in degrees.
   */
  private static final double MAX_ALTITUDE = 90;

  /**
   * The maximum zenith angle in degrees.
   */
  private static final double MAX_AZIMUTH = 360;

  /**
   * The intput raster parameter with elevations to hillshade.
   */
  private final InputFileParameter paramInputRaster;
  /**
   * The double Z factor parameter.
   */
  private final DoubleParameter paramZFactor;
  /**
   * The double degree angle of light direction with north as 0 and moving east.
   */
  private final DoubleParameter paramAzimuth;
  /**
   * The zenith angle of the light, starting with 0 at the horizon.
   */
  private final DoubleParameter paramAltitude;
  /**
   * The output image parameter.
   */
  private final OutputFileParameter paramOutputImage;
  /**
   * An error logger for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
          CreateHillshade.class.getName());

  /**
   * Creates a new Create Hillshade tool.
   */
  public CreateHillshade() {
    this.setDescription("Extract Lines by Intersecting with Polygons");
    this.setHelpText("This tool calculates the intersection between "
            + "all the lines in the line shapefile and each polygon "
            + "from the polygon shapefile.  The fields are combined, "
            + "with the fields from the original line file starting.");
    this.setCategory("Raster");
    this.setHelpImageFilename("resources/hillshade.png");
    this.setName("Hillshade");

    paramInputRaster = new InputFileParameter();
    paramInputRaster.setParameterName("Input Ascii Raster");
    paramInputRaster.setDescription("The Ascii elevation raster to convert"
            + " into a HillShade image.");
    paramInputRaster.setHelpText("The Ascii elevation raster to convert"
            + " into a HillShade image.");
    this.getParameters().add(paramInputRaster);

    paramZFactor = new DoubleParameter();
    paramZFactor.setParameterName("Z-Factor");
    paramZFactor.setMin(0.0);
    paramZFactor.setMax(Double.MAX_VALUE);
    paramZFactor.setDescription("The double conversion from elevation units "
            + "to geographic units.");
    paramZFactor.setHelpText("The double conversion from elevation units to "
            + "geographic units.  For instance, if the elevation is stored"
            + " in feet, but the x,y values are in degrees, you"
            + " should use .000003");
    paramZFactor.setHelpImageFilename("resources/zFactor.png");
    this.getParameters().add(paramZFactor);

    paramAzimuth = new DoubleParameter();
    paramAzimuth.setParameterName("Azimuth");
    paramAzimuth.setMin(0.0);
    paramAzimuth.setMax(MAX_AZIMUTH);
    paramAzimuth.setDescription("The double angle in degrees measured clockwise"
            + " from north.");
    paramAzimuth.setHelpImage(null);
    paramAzimuth.setHelpText("The double angle in degrees measured clockwise"
            + " from north.");
    this.getParameters().add(paramAzimuth);

    paramAltitude = new DoubleParameter();
    paramAltitude.setParameterName("Altitude");
    paramAltitude.setMin(0.0);
    paramAltitude.setMax(MAX_ALTITUDE);
    paramAltitude.setDescription("The double zenith angle measured in "
            + "degrees of the light angle ranging from 0 to 90.");
    paramAltitude.setHelpText("The double zenith angle measured in "
            + "degrees of the light angle ranging from 0 to 90.");
    paramAltitude.setHelpImageFilename("resources/altitudeAngle.png");
    this.getParameters().add(paramAltitude);

    paramOutputImage = new OutputFileParameter();
    paramOutputImage.setParameterName("Output File Raster");
    paramOutputImage.setExtension(".png");
    paramOutputImage.setDescription("The Hillshade image file.");
    paramOutputImage.setHelpText("The output Hillshade image file.");
    this.getParameters().add(paramOutputImage);

  }

  /**
   * Runs the task on the current thread.
   */
  @Override
  public final void runImmediately() {
    try {
      RasterDataset r = new RasterDataset();
      r.open(new File(paramInputRaster.getParameterText()));
      BufferedImage image = HillShade.createHillshadeTransparent(r, 0,
              paramAltitude.getValue(), paramAzimuth.getValue(), paramZFactor.getValue());
      ImageIO.write(image, "png", new File(paramOutputImage.getParameterText()));
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }
}
