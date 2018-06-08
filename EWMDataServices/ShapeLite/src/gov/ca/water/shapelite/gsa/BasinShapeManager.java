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

import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Field;
import gov.ca.water.shapelite.FieldType;
import gov.ca.water.shapelite.FileHelper;
import gov.ca.water.shapelite.Shape;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class BasinShapeManager {

  /**
   * The integer size in single byte characters of the featureset fields.
   */
  private static final int FIELD_SIZE = 255;

  /**
   * The length of the field in characters of the integer ID field.
   */
  private static final int ID_SIZE = 10;

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * The FeatureSet with polygons for the basins that are identified by the
   * basin_su_l id field.
   */
  private FeatureSet basins;

  /**
   * The string ID fieldname to use to uniquely identify basin features.
   */
  private String basinIdField;

  /**
   * The composite basin shapes organized by their reporting index.
   */
  private FeatureSet reportMaster;

  /**
   * The String ID field where the report ID should be stored in the report
   * master.
   */
  private String reportIdField;

  /**
   * The String field name of the basin name field in the basin shapefile that
   * should be copied to the output.
   */
  private String basinNameField;

  /**
   * The list of fields that should be used for the report master in the case
   * that the report master is null.
   */
  private final List<Field> fields;
  
  /**
   * 
   */
  private Boolean basinRounded;

  // </editor-fold>
  /**
   * Creates a new instance of the BasinShapeManager tool. This tool is used to
   * track composite basin shapes that are associated with specific features.
   */
  public BasinShapeManager() {
    reportIdField = "AltRptId";
    basinIdField = "Basin_Subb";
    basinNameField = "Basin_Name";
    fields = new ArrayList<>();
    this.basinRounded = false;
    fields.add(new Field(reportIdField, FieldType.Numeric, ID_SIZE));
    fields.add(new Field("AgencyNm", FieldType.Character, FIELD_SIZE));
    fields.add(new Field("BasinNm", FieldType.Character, FIELD_SIZE));
    fields.add(new Field("Basin_Subb", FieldType.Character, FIELD_SIZE));
  }

  /**
   * Removes the master feature with the master id.
   *
   * @param altRptId The id of the report feature to remove from the report
   * master shapes.
   */
  public final void remove(int altRptId) {
    String id = "" + altRptId;
    reportMaster.remove(reportIdField, id);
  }

  /**
   * This will add an entry in the report master shapefile for the specified
   * altRptId that includes the union of all the basnSu1 shapes.
   *
   * @param altRptId The integer altRptId of the new shape.
   * @param agencyNm The String agency name to be added to the field in the
   * output file.
   * @param basinNm The String basin name field as it should appear in the
   * output file.
   * @param basinSu The comma delimited Basin identifier Basin_Subb values to
   * merge into a shape to add to the master file.
   */
  public final void add(Integer altRptId, String agencyNm, String basinNm,String basinSu) {
    Map<String,String> attrMap = new HashMap<>();
    attrMap.put("AgencyNm", agencyNm);
    attrMap.put("BasinNm", basinNm);
    attrMap.put("Basin_Subb", basinSu);
    this.add(altRptId, attrMap);
  }
  
  public final void add(Integer altRptId, Map<String,String> attrMap) {
    String id = "" + altRptId;
    if (reportMaster == null) {
      reportMaster = new FeatureSet(ShapeType.Polygon);
      reportMaster.addFields(fields);
    }
    if (reportMaster.getFeature(reportIdField, id).isPresent()) {
      throw new IllegalArgumentException("The master shapefile already has a"
          + " shape with the id: " + altRptId + ".  This should first be removed.");
    }
    String basinSu = attrMap.get("Basin_Subb");
    String[] basinIds = basinSu.split(",");
    List<String> basinList = Arrays.asList(basinIds);
    FeatureSet selectedItems = new FeatureSet(ShapeType.Polygon);
    selectedItems.copyFields(basins);
    List<Feature> basinFeatures = basins.getFeatures(basinIdField, basinList);
    selectedItems.getFeatures().addAll(basinFeatures);
    selectedItems.union();
    Shape shape = new Shape(ShapeType.NullShape);
    if (!selectedItems.getFeatures().isEmpty()) {
      shape = selectedItems.getFeatures().get(0).getShape();
    }
    Feature resultFeature = new Feature(shape);
    attrMap.put(reportIdField, altRptId.toString());
    resultFeature.getAttributes().putAll(attrMap);
    reportMaster.getFeatures().add(resultFeature);
    reportMaster.setProjectionFrom(basins.getProjection());
  }

  /**
   * Loads the basin shapefile.
   *
   * @param basinShapefile the basins to set
   * @throws java.io.IOException If there is an error reading the shapefile.
   */
  public final void loadBasinShapefile(String basinShapefile) throws IOException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(basinShapefile);
    this.basins = reader.getFeatures();
  }

  /**
   * Loads the basins from a zip stream.
   *
   * @param stream The InputStream that has the data for the zip stream for the
   * basins shapefile.
   * @throws IOException If there is an error reading the file.
   */
  public final void loadBasinZipStream(InputStream stream) throws IOException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(stream);
    this.basins = reader.getFeatures();
  }

  /**
   * Loads the report zip stream.
   *
   * @param filename the String filename of the .shp or .zip file.
   * @throws IOException if there was an error reading the file.
   */
  public final void loadReportShapefile(String filename) throws IOException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(filename);
    this.reportMaster = reader.getFeatures();
  }

  /**
   * Loads the report zip stream.
   *
   * @param stream The InputStream to load.
   * @throws IOException if there was an error reading the stream.
   */
  public final void loadReportZipStream(InputStream stream) throws IOException {
    ShapefileReader reader = new ShapefileReader();
    reader.open(stream);
    this.reportMaster = reader.getFeatures();
  }

  /**
   * Saves the current report shapefile that has all the basin shapes organized
   * based on the altrptid.
   *
   * @param filename The String filename to use to save the report.
   * @throws ShapefileException if there is an error writing the file.
   */
  public final void saveReportShapefile(String filename) throws ShapefileException {
    if (reportMaster == null) {
      reportMaster = new FeatureSet(ShapeType.Polygon);
      reportMaster.addFields(fields);
    }
    reportMaster.setName(FileHelper.getBaseName(filename));
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(reportMaster, filename);
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the basins
   */
  public final FeatureSet getBasins() {
    if (!this.basinRounded) {
      basins.round(6);
      this.basinRounded = true;
    }
    return basins;
  }

  /**
   * @param basins the basins to set
   */
  public final void setBasins(FeatureSet basins) {
    this.basins = basins;
  }

  /**
   * Saves the report shapefile to the specified output stream as a zipped
   * shapefile.
   *
   * @param stream The output stream to write the zipped shapefile data to.
   * @throws ShapefileException if there is an error writing the shapefile.
   * @throws java.io.IOException if there is an error writing to the stream.
   */
  public final void saveReportShapefile(OutputStream stream)
      throws ShapefileException, IOException {
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(reportMaster, stream);
  }

  /**
   * Gets the string ID fieldname to use to uniquely identify basin features.
   *
   * @return the basinIdField
   */
  public final String getBasinIdField() {
    return basinIdField;
  }

  /**
   * Sets the string ID fieldname to use to uniquely identify basin features.
   *
   * @param basinIdField the basinIdField to set
   */
  public final void setBasinIdField(String basinIdField) {
    this.basinIdField = basinIdField;
  }

  /**
   * Gets the composite basin shapes organized by their reporting index.
   *
   * @return the reportMaster
   */
  public final FeatureSet getReportMaster() {
    return reportMaster;
  }

  /**
   * Sets the composite basin shapes organized by their reporting index.
   *
   * @param reportMaster the reportMaster to set
   */
  public final void setReportMaster(FeatureSet reportMaster) {
    this.reportMaster = reportMaster;
  }

  /**
   * Gets the list of fields that should be used for the report master in the
   * case that the report master is null.
   *
   * @return the fields
   */
  public final List<Field> getFields() {
    return fields;
  }

  /**
   * Gets the String field name of the basin name field in the basin shapefile
   * that should be copied to the output.
   *
   * @return the basinNameField
   */
  public final String getBasinNameField() {
    return basinNameField;
  }

  /**
   * Sets the String field name of the basin name field in the basin shapefile
   * that should be copied to the output.
   *
   * @param basinNameField the basinNameField to set
   */
  public final void setBasinNameField(String basinNameField) {
    this.basinNameField = basinNameField;
  }

  // </editor-fold>
}