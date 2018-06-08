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
import gov.ca.water.shapelite.Nullable;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.ShapeCategory;
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.io.ShapefileWriter;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.Projections;
import gov.ca.water.shapelite.trace.PolygonFeaturesetIntersector;
import gov.ca.water.shapelite.utils.StringUtils;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GSAPolygonManager {

  // <editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The logger utility for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(
      GSAPolygonManager.class.getName());

  /**
   * The Service areas shapefile name.
   */
  private static final String DEFAULT_SERVICE_AREA = "AgencyServiceAreas.shp";

  /**
   * The GSA Areas shapefile name.
   */
  private static final String DEFAULT_GSA = "GSAAreas.shp";

  /**
   * The character length of the field.
   */
  private static final int DEFAULT_FIELD_LENGTH = 50;

  /**
   * The String shapefile path of the combined set of all the agency service
   * area shapes.
   */
  private String serviceAreasShapefile;
  /**
   * The String shapefile path of the combined set of all the GSA areas.
   */
  private String gsaAreasShapefile;

  /**
   * The county shapefile that will have intersection performed with it. If this
   * is null, the intersection step will be skipped.
   */
  private String countyShapefile;

  /**
   * The basin shapefile that will be intersected with the GSA. If this is null,
   * the intersection step will be skipped.
   */
  private String basinShapefile;

  /**
   * The combined agency service area containing the combined GSA Service areas
   * from all applications.
   */
  private FeatureSet serviceAreas;

  /**
   * The FeatureSet containing the combined GSA Areas from all applications.
   */
  private FeatureSet gsaAreas;

  /**
   * The FeatureSet containing county information.
   */
  private FeatureSet counties;

  /**
   * The FeatureSet containing basin information.
   */
  private FeatureSet basins;

  /**
   * The name of the field where the GSA ID is stored, to uniquely identify the
   * GSA Id.
   */
  private String gsaAreasIdField;

  /**
   * The GSA Id field name in the agency service area shapes.
   */
  private String agencyServiceAreasGsaIdField;

  /**
   * Clears existing intersection files during addGSAPolygonLayers if this is
   * true.
   */
  private boolean clearingIntersectionsOnUpdate;

  /**
   * The field name to return from the Basin shapefile in the list.
   */
  private String basinFieldName;

  /**
   * The String field name that is used for the "name" field of the county. This
   * is the field that will be used in the list of intersection returns.
   */
  private String countyFieldName;

  /**
   * The distance in feet to buffer around the shape for intersection testing. A
   * negative buffer reduces the shape, while a positive buffer expands the
   * shape.
   */
  private double bufferFeet;

  // </editor-fold>
  /**
   * Creates a new instance of the GSA Polygon Manager.
   */
  public GSAPolygonManager() {
    String gsa = System.getProperty("user.home") + File.separator + "GSA";
    serviceAreasShapefile = gsa + File.separator + DEFAULT_SERVICE_AREA;
    gsaAreasShapefile = gsa + File.separator + DEFAULT_GSA;
    gsaAreasIdField = "GSA_ID";
    agencyServiceAreasGsaIdField = "GSA_ID";
    clearingIntersectionsOnUpdate = false;
    countyFieldName = "NAME";
    basinFieldName = "Basin_ID";
  }

  /**
   * Add a polygon layer.
   *
   * @param myGsaId The String ID of the GSA ID to update.
   * @param outputFolder The folder path where the master should be saved, and
   * where the intersection shapefiles will be created.
   * @param masterGSAShapefile The full path of the shapefile to act as the
   * master for all the GSA ID shapes.
   * @param serviceAreaShapefile The full path to the input service area
   * shapefile.
   * @param gsaPolygonShapefile The full path to the input polygon shapefile.
   * @return The String representing an array of GSA Id values, such as {1,2}
   * which had intersections with the new GSA.
   * @throws java.io.IOException If there was an error parsing the file.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there was
   * an error reprojecting the shapes for intersection.
   * @throws gov.ca.water.shapelite.ShapefileException If there was an error
   * reading or writing shapefiles.
   */
  public final String addGSAPolygonLayer(String myGsaId, String outputFolder,
      String masterGSAShapefile, String serviceAreaShapefile, String gsaPolygonShapefile)
      throws IOException, ProjectionException, ShapefileException {
    ShapefileReader reader = new ShapefileReader();
    if (clearingIntersectionsOnUpdate) {
      clearIntersections(outputFolder);
    }

    FeatureSet master = new FeatureSet(ShapeType.Polygon);
    File masterFile = new File(masterGSAShapefile);
    master.setName("GSA_Master");
    if (masterFile.exists()) {
      reader.open(masterGSAShapefile);
      master = reader.getFeatures();
    }

    reader.open(gsaPolygonShapefile);
    FeatureSet gsa = reader.getFeatures();
    setGsaAreasShapefile(null);
    setAgencyServiceAreasShapefile(null);
    setGsaAreas(master);
    UpdateResults results = update(myGsaId, null, gsa);
    ShapefileWriter writer = new ShapefileWriter();

    String overlapped = "{";
    List<String> overlapNames = new ArrayList<>();
    for (Feature f : results.getGsaIntersections().getFeatures()) {
      FeatureSet individualIntersection = new FeatureSet(ShapeType.Polygon);
      individualIntersection.addFields(results.getGsaIntersections().getFields());
      String gsaId = f.getAttributes().get(gsaAreasIdField);
      String intersectShapefile = outputFolder + File.separator
          + "GSA_Intersect_" + myGsaId + "_" + gsaId + ".zip";
      individualIntersection.getFeatures().add(f);
      individualIntersection.setName("GSA_Intersect_" + myGsaId + "_" + gsaId);
      writer.write(individualIntersection, intersectShapefile);

      if (!overlapNames.contains(gsaId)) {
        if (!("{".equals(overlapped))) {
          overlapped += ",";
        }
        overlapped += gsaId;
        overlapNames.add(gsaId);
      }

    }
    overlapped += "}";

    writer.write(results.getCombinedGSA(), outputFolder + File.separator
        + "GSA_Master.zip");
    return overlapped;
  }

  /**
   * This method will clear any existing intersection shapefiles. This is called
   * automatically before an "add" method is called to ensure that the
   * intersections are only for the most recently added shapes.
   *
   * @param outputFolder The String path to the output folder to clean.
   */
  public final void clearIntersections(String outputFolder) {
    File folder = new File(outputFolder);
    File[] files = folder.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (!pathname.isFile()) {
          return false;
        }
        return pathname.getAbsolutePath().contains("GSA_Intersect_");
      }
    });
    for (File file : files) {
      file.delete();
    }
  }

  /**
   * The distance in feet to buffer around the shape for intersection testing. A
   * negative buffer reduces the shape, while a positive buffer expands the
   * shape.
   *
   * @return the bufferFeet
   */
  public double getBufferFeet() {
    return bufferFeet;
  }

  /**
   * The distance in feet to buffer around the shape for intersection testing. A
   * negative buffer reduces the shape, while a positive buffer expands the
   * shape.
   *
   * @param bufferFeet the bufferFeet to set
   */
  public void setBufferFeet(double bufferFeet) {
    this.bufferFeet = bufferFeet;
  }

  /**
   * Public polygon layer.
   *
   * @param gsaID The gsaID representing this shape. This can also be a comma
   * delimited string of multiple id values to remove.
   * @param masterShapefile The Master Shapefile.
   * @return The list of string ids that were successfully removed. Items
   * missing from the list may have not been found.
   * @throws java.io.IOException If there is an error.
   * @throws gov.ca.water.shapelite.ShapefileException If there is an error
   * reading or writing shapefiles.
   */
  public final List<String> removeGSAPolygonLayer(String gsaID, String masterShapefile)
      throws IOException, ShapefileException {
    List<String> result = new ArrayList<>();
    if (gsaID == null) {
      return result;
    }
    String[] ids = gsaID.split(",");
    ShapefileReader reader = new ShapefileReader();
    reader.open(masterShapefile);
    FeatureSet master = reader.getFeatures();
    for (String id : ids) {
      String trim = id.trim(); // remove any white space.
      List<Feature> features = master.remove(gsaAreasIdField, trim);
      if (!features.isEmpty()) {
        result.add(trim);
      }
    }
    ShapefileWriter writer = new ShapefileWriter();
    writer.write(master, masterShapefile);
    return result;
  }

  /**
   * Gets a FeatureSet containing the features loaded from the gsaAreaShapefile.
   *
   * @return A FeatureSet. This will be an empty FeatureSet if there was any
   * problem opening the master shapefile or if the shapefile does not exist.
   */
  public final FeatureSet getGSAAreas() {
    if (gsaAreas == null) {
      if (gsaAreasShapefile != null) {
        File f = new File(gsaAreasShapefile);
        if (f.exists()) {
          ShapefileReader reader = new ShapefileReader();
          try {
            reader.open(gsaAreasShapefile);
            setGsaAreas(reader.getFeatures());
          } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
          }
        }
      }
    }
    if (gsaAreas == null) {
      gsaAreas = new FeatureSet(ShapeType.Polygon);
    }
    return gsaAreas;
  }

  /**
   * Gets a FeatureSet containing the features loaded from the gsaAreaShapefile.
   *
   * @return A FeatureSet. This will be an empty FeatureSet if there was any
   * problem opening the master shapefile or if the shapefile does not exist.
   */
  public final FeatureSet getServiceAreas() {
    if (serviceAreas == null) {
      if (serviceAreasShapefile != null) {
        File f = new File(serviceAreasShapefile);
        if (f.exists()) {
          ShapefileReader reader = new ShapefileReader();
          try {
            reader.open(serviceAreasShapefile);
            setServiceAreas(reader.getFeatures());
          } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
          }
        }
      }
    }
    if (serviceAreas == null) {
      setServiceAreas(new FeatureSet(ShapeType.Polygon));
    }
    return serviceAreas;
  }

  /**
   * This modifies the existing FeatureSet, loading it from the file if
   * necessary, and removing the specified GSA id.
   *
   * @param gsaID The String uniquely identifying this GSA.
   * @throws gov.ca.water.shapelite.ShapefileException If the in memory version
   * of the gsaAreas is not being used, and writing to the shapefile fails.
   */
  public final void deleteGSA(String gsaID) throws ShapefileException {
    if (gsaAreas != null) {
      gsaAreas.remove(gsaAreasIdField, gsaID);
      return;
    }
    if (gsaAreasShapefile != null) {
      File f = new File(gsaAreasShapefile);
      if (!f.exists()) {
        return;
      }
      FeatureSet areas = getGSAAreas();
      areas.remove(gsaAreasIdField, gsaID);
      ShapefileWriter writer = new ShapefileWriter();
      writer.write(areas, gsaAreasShapefile);
    }
  }

  /**
   * The only difference between this method and the update method is that
   * passing a null value for a featureset to an update method will delete the
   * features for the specified gsaID, while passing a null value into the add
   * method will leave it unchanged.
   *
   * @param gsaID The String uniquely identifying this GSA.
   * @param agencyServiceArea The Agency service area that should contain the
   * specified GSA Area.
   * @param gsaArea The GSA Area that will be compared against master datasets.
   * @return UpdateResults containing the intersection information from an
   * updated situation.
   * @throws gov.ca.water.shapelite.ShapefileException If the in memory version
   * of the gsaAreas is not being used, and writing to the shapefile fails.
   * @throws gov.ca.water.shapelite.projection.ProjectionException if there is a
   * problem converting the features to WGS84 for intersections.
   */
  public final UpdateResults add(String gsaID, FeatureSet agencyServiceArea,
      FeatureSet gsaArea) throws ShapefileException, ProjectionException {
    return update(gsaID, agencyServiceArea, gsaArea, false);
  }

  /**
   * This modifies the existing FeatureSet, loading it from the file if
   * necessary, and removing the specified GSA id.
   *
   * @param gsaID The String uniquely identifying this GSA.
   * @throws gov.ca.water.shapelite.ShapefileException If the in memory version
   * of the gsaAreas is not being used, and writing to the shapefile fails.
   */
  public final void deleteServiceArea(String gsaID) throws ShapefileException {
    if (serviceAreas != null) {
      serviceAreas.remove(agencyServiceAreasGsaIdField, gsaID);
      return;
    }
    if (serviceAreasShapefile != null) {
      File f = new File(serviceAreasShapefile);
      if (!f.exists()) {
        return;
      }
      FeatureSet areas = getGSAAreas();
      areas.remove(agencyServiceAreasGsaIdField, gsaID);
      ShapefileWriter writer = new ShapefileWriter();
      writer.write(areas, serviceAreasShapefile);
    }
  }

  /**
   * Updates the master polygons. If the collective polygons don't exist yet,
   * they will be created.
   *
   * @param gsaID The String uniquely identifying this GSA.
   * @param agencyServiceArea The Agency service area that should contain the
   * specified GSA Area.
   * @param gsaArea The GSA Area that will be compared against master datasets.
   * @return UpdateResults containing the intersection information from an
   * updated situation.
   * @throws gov.ca.water.shapelite.projection.ProjectionException If there is a
   * problem reprojecting the given gsaArea or agencyServiceArea to the
   * projection of the county or basins shapes.
   * @throws gov.ca.water.shapelite.ShapefileException only if the cache is
   * being used and there is an error writing to it.
   */
  public final UpdateResults update(String gsaID, @Nullable FeatureSet agencyServiceArea,
      @Nullable FeatureSet gsaArea) throws ProjectionException, ShapefileException {
    return update(gsaID, agencyServiceArea, gsaArea, true);
  }

  /**
   * Updates the master polygons. If the collective polygons don't exist yet,
   * they will be created.
   *
   * @param gsaID The String uniquely identifying this GSA.
   * @param agencyServiceArea The Agency service area that should contain the
   * specified GSA Area.
   * @param gsaArea The GSA Area that will be compared against master datasets.
   * @param allowRemove if this is true, and gsaARea is null, then this will
   * remove the feature associated with the specified gsaID.
   * @return UpdateResults containing the intersection information from an
   * updated situation.
   * @throws ProjectionException If there is a problem reprojecting the given
   * gsaArea or agencyServiceArea to the projection of the county or basins
   * shapes.
   * @throws ShapefileException if there is an error caching the dataset.
   */
  private UpdateResults update(String gsaID, @Nullable FeatureSet agencyServiceArea,
      @Nullable FeatureSet gsaArea, boolean allowRemove)
      throws ProjectionException, ShapefileException {
    UpdateResults result = new UpdateResults();
    if (gsaID == null) {
      throw new IllegalArgumentException("gsaID field cannot be null.");
    }
    FeatureSet area = null;
    if (gsaArea != null && gsaArea.getFeatureCategory() != ShapeCategory.Polygon) {
      throw new IllegalArgumentException("GSA Area was expecting a Polygon, "
          + "but instead got a " + gsaArea.getFeatureCategory() + ".");
    }
    if (agencyServiceArea != null && agencyServiceArea.getFeatureCategory()
        != ShapeCategory.Polygon) {
      throw new IllegalArgumentException("Agency Service Area was expecting"
          + " a Polygon, but instead got a "
          + agencyServiceArea.getFeatureCategory() + ".");
    }
    FeatureSet gsa = null;
    if (agencyServiceArea != null) {
      area = agencyServiceArea.union();
      if (!area.getProjection().isWGS84()) {
        area.reproject(Projections.getWGS84());
      }
    }
    if (gsaArea != null) {
      gsa = gsaArea.union();
      if (!gsa.getProjection().isWGS84()) {
        gsa.reproject(Projections.getWGS84());
      }
      result.setGsaWGS84(gsa);
    }
    if (area != null) {
      result.setServiceIntersections(updateServiceAreas(area, gsaID));
    } else if (allowRemove) {
      deleteServiceArea(gsaID);
    }
    if (gsa != null) {

      result.setGsaCountyNames(findIntersectingCounties(gsa));
      result.setGsaBasinIds(findIntersectingBasins(gsa));
      if (allowRemove) {
        deleteGSA(gsaID);
      }
      result.setGsaIntersections(updateGSA(gsa, gsaID));
      List<String> overlapped = new ArrayList<>();
      for (Feature f : result.getGsaIntersections().getFeatures()) {
        if (f.getAttributes().containsKey(gsaAreasIdField)) {
          String id = f.getAttributes().get(gsaAreasIdField);
          if (!overlapped.contains(id)) {
            overlapped.add(id);
          }
        }
      }
      result.setOverlappingGsaIds(overlapped);
      result.setGsaBasinIntersections(findBasinIntersections(gsa));

      if (area != null) {
        result.setGsaAreasOutsideServiceAreas(findGSAOutsideServiceArea(gsa,
            area));

      } else if (this.serviceAreas != null) {
        Optional<Feature> maybeFeature = serviceAreas.getFeature(
            agencyServiceAreasGsaIdField, gsaID);
        if (maybeFeature.isPresent()) {
          FeatureSet areas = new FeatureSet(maybeFeature.get());
          result.setGsaAreasOutsideServiceAreas(findGSAOutsideServiceArea(gsa,
              areas));
        }
      }
    } else if (allowRemove) {
      deleteGSA(gsaID);
    }

    result.setCombinedGSA(gsaAreas);
    result.setCombinedServiceAreas(serviceAreas);
    return result;
  }

  /**
   * Finds the list of string names of intersecting counties. If there is a
   * problem loading the county file, or if there are no intersections this may
   * return an empty list. It will never return null.
   *
   * @param gsaArea The FeatureSet to intersect with the counties.
   * @return The List of String names.
   * @throws ProjectionException If there is an error matching the gsaArea
   * projection to the counties projection.
   *
   */
  private List<String> findIntersectingCounties(FeatureSet gsaArea)
      throws ProjectionException {
    List<String> result = new ArrayList<>();
    if (counties == null) {
      if (countyShapefile != null) {
        try {
          ShapefileReader reader = new ShapefileReader();
          reader.open(countyShapefile);
          counties = reader.getFeatures();
        } catch (IOException ex) {
          return result;
        }
      }
    }
    if (counties != null) {
      FeatureSet area = gsaArea;
      if (area != null) {
        if (!area.getProjection().equals(counties.getProjection())) {
          area.reproject(counties.getProjection());
          area.setProjectionFrom(counties.getProjection());
        }
      }
      PolygonFeaturesetIntersector sect = new PolygonFeaturesetIntersector();
      result = sect.getIntersectionFieldValues(counties, area, countyFieldName,
          bufferFeet);
    }
    return result;
  }

  /**
   * Gets the loaded counties FeatureSet or loads it from a shapefile.
   *
   * @return The FeatureSet.
   */
  public FeatureSet getCounties() {
    if (counties == null) {
      if (countyShapefile != null) {
        try {
          ShapefileReader reader = new ShapefileReader();
          reader.open(countyShapefile);
          counties = reader.getFeatures();
        } catch (IOException ex) {
          return null;
        }
      }
    }
    return counties;
  }

  /**
   * Finds the list of string names of intersecting basins. If there is a
   * problem loading the basin file, or if there are no intersections this may
   * return an empty list. It will never return null.
   *
   * @param gsaArea The FeatureSet to intersect with the basins.
   * @return The List of String names.
   * @throws ProjectionException if there was an error reprojecting the shape to
   * match the basin projection.
   */
  public List<String> findIntersectingBasins(FeatureSet gsaArea)
      throws ProjectionException {
    List<String> result = new ArrayList<>();
    if (basins == null) {
      if (basinShapefile != null) {
        try {
          ShapefileReader reader = new ShapefileReader();
          reader.open(basinShapefile);
          basins = reader.getFeatures();
        } catch (IOException ex) {
          return result;
        }
      }
    }
    if (basins != null) {
      if (gsaArea != null) {
        FeatureSet area = gsaArea.copy();
        if (!gsaArea.getProjection().equals(basins.getProjection())) {
          area.reproject(basins.getProjection());
        }
        PolygonFeaturesetIntersector sect = new PolygonFeaturesetIntersector();
        result = sect.getIntersectionFieldValues(basins, area, basinFieldName, bufferFeet);
      }
    }
    return result;
  }

  /**
   * Finds the list of string names of intersecting basins. If there is a
   * problem loading the basin file, or if there are no intersections this may
   * return an empty list. It will never return null.
   *
   * @param gsaArea The FeatureSet to intersect with the basins.
   * @return The List of String names.
   * @throws ProjectionException If there is a problem converting between the
   * drawn projection and the basin or county projection.
   */
  public FeatureSet findBasinIntersections(FeatureSet gsaArea)
      throws ProjectionException {
    FeatureSet result = new FeatureSet(ShapeType.Polygon);
    getBasins();
    if (basins != null && gsaArea != null) {
      result.addFields(gsaArea.getFields());
      result.addMissingFields(basins.getFields());
      result = gsaArea.intersection(basins, 0, bufferFeet);
      if ((result != null) && !result.isEmpty()) {
        this.getCounties(); // load counties
        if (counties != null) {
          result.getFields().add(new Field("Counties", FieldType.Character, 255));
          for (Feature f : result.getFeatures()) {
            List<String> countyNames = new ArrayList<>();
            FeatureSet countyReproject = counties;
            if (!counties.getProjection().equals(gsaArea.getProjection())) {
              countyReproject = counties.copy();
              countyReproject.reproject(gsaArea.getProjection());
            }
            for (Feature county : countyReproject.getFeatures()) {
              if (f.intersects(county)) {
                String name = county.getAttributes().get(this.countyFieldName);
                if (!countyNames.contains(name)) {
                  countyNames.add(name);
                }
              }
            }
            f.getAttributes().put("Counties", StringUtils.join(",", countyNames));
          }
        }
      }
    }
    return result;
  }

  /**
   * Gets the basins shape, or if basins is null and the Basins shapefile is not
   * null, attempts to load the basins shapefile.
   *
   * @return FeatureSet basins.
   */
  public FeatureSet getBasins() {
    if (basins == null) {
      if (basinShapefile != null) {
        try {
          ShapefileReader reader = new ShapefileReader();
          reader.open(basinShapefile);
          basins = reader.getFeatures();
        } catch (IOException ex) {
          return null;
        }
      }
    }
    return basins;
  }
  
  public void setBasins(FeatureSet fs) {
    this.basins = fs;
  }
  
  /**
   * Finds the intersection shapes so that each shape has exactly one county and
   * one basin. This is independent of the GSA or GSA Area shapes, and simply
   * shows how the counties and basins relate to each other.
   *
   * @return FeatureSet with the intersection shapes and the basin and county
   * name attributes.
   * @throws ProjectionException if there was an error reprojecting in order to
   * intersect.
   */
  public final FeatureSet findCountyBasins() throws ProjectionException {
    FeatureSet result = new FeatureSet(ShapeType.Polygon);
    getBasins();
    getCounties();
    if (basins != null && counties != null) {
      result = basins.intersection(counties);
    }
    return result;
  }

  /**
   * Finds the places where shapes from gsaArea are outside the service areas.
   *
   * @param gsaArea The GSA Area.
   * @param serviceAreas The specified Service Area that should surround.
   * @return A FeatureSet complete with the cropped exeeding shapes from the GSA
   * Area FeatureSet that are outside the bounds of the serviceArea shape.
   */
  private FeatureSet findGSAOutsideServiceArea(FeatureSet gsaArea,
      FeatureSet serviceAreas) {
    // Pass a positive buffer to make differencing slivers less likely.
    FeatureSet result = gsaArea.difference(serviceAreas, Math.abs(bufferFeet));
    return result;
  }

  /**
   * This checks the master list of GSA areas, removes the area with the
   * specified gsaID if one exists, and updates the shape.
   *
   * @param gsaArea The area to add to the collective and find intersections
   * with.
   * @param gsaId The area to replace in the master.
   * @return FeatureSet with the intersections.
   * @throws ShapefileException if the shapefile path is being used but writing
   * failed.
   */
  public FeatureSet updateGSA(FeatureSet gsaArea, String gsaId)
      throws ShapefileException {
    FeatureSet result;
    getGSAAreas(); // load from shapefile if necessary.
    deleteGSA(gsaId);
    if (!gsaArea.containsFieldNamed(gsaAreasIdField)) {
      gsaArea.getFields().add(new Field(gsaAreasIdField,
          FieldType.Character, DEFAULT_FIELD_LENGTH));
      for (Feature feature : gsaArea.getFeatures()) {
        feature.getAttributes().put(gsaAreasIdField, gsaId);
      }
    }
    PolygonFeaturesetIntersector intersector = new PolygonFeaturesetIntersector();
//    gsaAreas.addMissingFields(gsaArea.getFields());
    result = intersector.getGroupedIntersections(gsaAreas, gsaArea, bufferFeet);

//    gsaAreas.getFeatures().addAll(gsaArea.getFeatures());

//    if (gsaAreasShapefile != null) {
//      try {
//        ShapefileWriter writer = new ShapefileWriter();
//        writer.write(gsaAreas, gsaAreasShapefile);
//      } catch (ShapefileException ex) {
//        Logger.getLogger(GSAPolygonManager.class.getName()).log(Level.SEVERE, null, ex);
//      }
//    }

    return result;
  }

  /**
   * This checks the master list of GSA areas, removes the area with the
   * specified gsaID if one exists, and updates the shape.
   *
   * @param serviceArea The area to add to the collective and find intersections
   * with.
   * @param gsaId The area to replace in the master.
   * @return FeatureSet with the intersections.
   * @throws ShapefileException if writing to the shapefile cache didn't work.
   */
  private FeatureSet updateServiceAreas(FeatureSet serviceArea, String gsaId)
      throws ShapefileException {
    FeatureSet result = new FeatureSet(ShapeType.Polygon);
    deleteServiceArea(gsaId);
    getServiceAreas(); // ensure the Service Areas master is loaded.
    if (serviceArea != null) {
      deleteServiceArea(gsaId);
      if (!serviceArea.containsFieldNamed(agencyServiceAreasGsaIdField)) {
        serviceArea.getFields().add(new Field(agencyServiceAreasGsaIdField,
            FieldType.Character, DEFAULT_FIELD_LENGTH));
        for (Feature feature : serviceArea.getFeatures()) {
          feature.getAttributes().put(agencyServiceAreasGsaIdField, gsaId);
        }
      }
      PolygonFeaturesetIntersector intersector = new PolygonFeaturesetIntersector();
      if (serviceAreas != null) {
        serviceAreas.addMissingFields(serviceArea.getFields());
        result = intersector.getGroupedIntersections(serviceAreas, serviceArea, bufferFeet);
        serviceAreas.getFeatures().addAll(serviceArea.getFeatures());
      } else {
        setServiceAreas(serviceArea);
      }
    }

    if (serviceAreasShapefile != null) {
      try {
        ShapefileWriter writer = new ShapefileWriter();
        writer.write(serviceAreas, serviceAreasShapefile);
      } catch (ShapefileException ex) {
        Logger.getLogger(GSAPolygonManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return result;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the String shapefile path of the combined set of all the agency
   * service area shapes.
   *
   * @return the serviceAreasShapefile
   */
  public final String getAgencyServiceAreasShapefile() {
    return serviceAreasShapefile;
  }

  /**
   * Sets the String shapefile path of the combined set of all the agency
   * service area shapes.
   *
   * @param agencyServiceAreasShapefile the serviceAreasShapefile to set
   */
  public final void setAgencyServiceAreasShapefile(String agencyServiceAreasShapefile) {
    this.serviceAreasShapefile = agencyServiceAreasShapefile;
  }

  /**
   * Gets the String shapefile path of the combined set of all the GSA areas.
   *
   * @return the gsaAreasShapefile
   */
  public final String getGsaAreasShapefile() {
    return gsaAreasShapefile;
  }

  /**
   * Sets the String shapefile path of the combined set of all the GSA areas.
   *
   * @param gsaAreasShapefile the gsaAreasShapefile to set
   */
  public final void setGsaAreasShapefile(String gsaAreasShapefile) {
    this.gsaAreasShapefile = gsaAreasShapefile;
  }

  /**
   * Gets the county shapefile that will have intersection performed with it. If
   * this is null, the intersection step will be skipped.
   *
   * @return the countyShapefile
   */
  public final String getCountyShapefile() {
    return countyShapefile;
  }
  
  /**
   * Sets the county shapefile that will have intersection performed with it. If
   * this is null, the intersection step will be skipped.
   *
   * @param countyShapefile the countyShapefile to set
   */
  public final void setCountyShapefile(String countyShapefile) {
    this.countyShapefile = countyShapefile;
  }
  
  public final void setCountyShapefile(FeatureSet fs) {
    this.counties = fs;
  }

  /**
   * Gets the basin shapefile that will be intersected with the GSA. If this is
   * null, the intersection step will be skipped.
   *
   * @return the basinShapefile
   */
  public final String getBasinShapefile() {
    return basinShapefile;
  }

  /**
   * Sets the basin shapefile that will be intersected with the GSA. If this is
   * null, the intersection step will be skipped.
   *
   * @param basinShapefile the basinShapefile to set
   */
  public final void setBasinShapefile(String basinShapefile) {
    this.basinShapefile = basinShapefile;
  }

  /**
   * @return the gsaAreasIdField
   */
  public final String getGsaAreasIdField() {
    return gsaAreasIdField;
  }

  /**
   * @param gsaAreasIdField the gsaAreasIdField to set
   */
  public final void setGsaAreasIdField(String gsaAreasIdField) {
    this.gsaAreasIdField = gsaAreasIdField;
  }

  /**
   * @return the agencyServiceAreasGsaIdField
   */
  public final String getAgencyServiceAreasGsaIdField() {
    return agencyServiceAreasGsaIdField;
  }

  /**
   * @param agencyServiceAreasGsaIdField the agencyServiceAreasGsaIdField to set
   */
  public final void setAgencyServiceAreasGsaIdField(String agencyServiceAreasGsaIdField) {
    this.agencyServiceAreasGsaIdField = agencyServiceAreasGsaIdField;
  }

  /**
   * @param gsaAreas the gsaAreas to set
   */
  public final void setGsaAreas(FeatureSet gsaAreas) {
    this.gsaAreas = gsaAreas;
  }

  /**
   * @param serviceAreas the serviceAreas to set
   */
  public final void setServiceAreas(FeatureSet serviceAreas) {
    this.serviceAreas = serviceAreas;
  }

  /**
   * Gets a boolean that clears existing intersection files during
   * addGSAPolygonLayers if true.
   *
   * @return the clearingIntersectionsOnUpdate
   */
  public final boolean isClearingIntersectionsOnUpdate() {
    return clearingIntersectionsOnUpdate;
  }

  /**
   * Sets a boolean that clears existing intersection files during
   * addGSAPolygonLayers if true.
   *
   * @param clearIntersectionsOnUpdate the clearingIntersectionsOnUpdate to set
   */
  public final void setClearingIntersectionsOnUpdate(boolean clearIntersectionsOnUpdate) {
    this.clearingIntersectionsOnUpdate = clearIntersectionsOnUpdate;
  }

  /**
   * Gets the field name to return from the Basin shapefile in the list.
   *
   * @return the basinFieldName
   */
  public final String getBasinFieldName() {
    return basinFieldName;
  }

  /**
   * Sets the field name to return from the Basin shapefile in the list.
   *
   * @param basinFieldName the basinFieldName to set
   */
  public final void setBasinFieldName(String basinFieldName) {
    this.basinFieldName = basinFieldName;
  }

  // </editor-fold>
  /**
   * @return the countyFieldName
   */
  public String getCountyFieldName() {
    return countyFieldName;
  }

  /**
   * @param countyFieldName the countyFieldName to set
   */
  public void setCountyFieldName(String countyFieldName) {
    this.countyFieldName = countyFieldName;
  }
}
