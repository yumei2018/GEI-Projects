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

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.Section;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.OutputFileParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CreateSectionQuarters extends ToolBase {

  /**
   * The logger utility for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
      CreateSectionQuarters.class.getName());

  /**
   * The input folder with polygons to use.
   */
  private final InputFileParameter paramInputSections;

  /**
   * The paramOutputFolder folder.
   */
  private final OutputFileParameter paramOutputSectionQuarters;

  /**
   * Generates the quarter quarters as a process.
   */
  public CreateSectionQuarters() {

    setName("Create Sections and Quarters");
    setDescription("Given an input polygon for a township range, This creates "
        + "576 shapes.  They are each 1/16 of 1/36 of the original polygon.");
    this.setHelpText("Given an input polygon for a township range, extract "
        + "and label section and quarters.");
    this.setCategory("PLSS");
    this.setHelpImageFilename("resources/SectionQuarter.png");

    paramInputSections = new InputFileParameter();
    paramInputSections.setExtension(".shp");
    paramInputSections.setParameterName("Input Section Polygon Shapefile");
    paramInputSections.setDescription("The Shapefile containing the polygon sections.");
    paramInputSections.setHelpText("The Shapefile containing the polygons to "
        + "intersect with the lines.");
    this.getParameters().add(paramInputSections);

    paramOutputSectionQuarters = new OutputFileParameter();
    paramOutputSectionQuarters.setExtension(".shp");
    paramOutputSectionQuarters.setParameterName("Output QuarterQuarter Shapefile.");
    paramOutputSectionQuarters.setDescription("The Shapefile output to store the "
        + "polygons showing the section quarter quarters.");
    paramOutputSectionQuarters.setHelpText("The Shapefile output to store the "
        + "polygons showing the section quarter quarters.");
    this.getParameters().add(paramOutputSectionQuarters);
  }

  /**
   * Run the function to split the quarters.
   */
  @Override
  public final void runImmediately() {
    try {
      ShapefileReader reader = new ShapefileReader();
      reader.open(paramInputSections.getParameterText());
      FeatureSet fs = reader.getFeatures();
      FeatureSet result = new FeatureSet();

      getProgress().create("Creating Section Quarter Quarters.", this.getCancellable());
      getProgress().start(fs.getFeatures().size());
      int iFeature = 0;
      result.copyFields(fs);
      result.getFields().addAll(Section.getFields());
      result.setProjectionFrom(fs.getProjection());
      for (Feature sectionFeature : fs.getFeatures()) {

        Section section = new Section(sectionFeature);

        result.getFeatures().addAll(section.createQuarterQuarters());
        getProgress().progress(iFeature);
        iFeature++;
      }

      getProgress().finish();
      ShapefileWriter writer = new ShapefileWriter();
      writer.setProgress(getProgress());
      writer.write(result, paramOutputSectionQuarters.getParameterText());

    } catch (IOException | ShapefileException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);

    }

    getMessageHandler().message("Finished creating shapes.",
        "Finished creating quarter quarter shapes.");
  }

}
