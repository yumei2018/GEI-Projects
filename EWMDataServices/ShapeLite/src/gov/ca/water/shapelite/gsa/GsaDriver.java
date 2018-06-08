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
import gov.ca.water.shapelite.ShapeType;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.projection.ProjectionException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GsaDriver {

  /**
   *
   * @param gsaID The string ID to assign as the GSA ID for the newly added
   * polygon shapes.
   * @param outputFolder The String output folder where the intersection files
   * and the ohter files should be written.
   * @param masterGSAShapefile The full path of the Master GSA File, where
   * shapes from all the GSA areas are stored.
   * @param serviceAreaShapefile The full path of the new service area shapefile
   * for the specified GSA.
   * @param gsaPolygonShapefile The Polygon shapefile containing the GSA Id.
   * @return A String array format like {1,2} which represents the GSA ID values
   * that were intersected with.
   * @throws IOException If there is an error opening one of the files.
   * @throws ProjectionException If there is a problem reprojecting the shapes
   * to WGS84 for intersection.
   * @throws ShapefileException If there is an error in the shapefile format.
   */
  public static String addGSAPolyonLayer(String gsaID, String outputFolder,
      String masterGSAShapefile, String serviceAreaShapefile, String gsaPolygonShapefile)
      throws IOException, ProjectionException, ShapefileException {
    GSAPolygonManager manager = new GSAPolygonManager();

    manager.setGsaAreasShapefile(null);
    manager.setAgencyServiceAreasShapefile(null);
    manager.setServiceAreas(new FeatureSet(ShapeType.Polygon));
    manager.setGsaAreas(new FeatureSet(ShapeType.Polygon));
    manager.setClearingIntersectionsOnUpdate(false);
    manager.setBufferFeet(-1000);
    return manager.addGSAPolygonLayer(gsaID, outputFolder, masterGSAShapefile,
        serviceAreaShapefile, gsaPolygonShapefile);

  }

  /**
   * Public polygon layer.
   *
   * @param gsaID The gsaID representing this shape.
   * @param masterShapefile The Master Shapefile.
   * @return The list of String GSA Id values that were removed.
   * @throws java.io.IOException If there is an error.
   * @throws gov.ca.water.shapelite.ShapefileException If there is an error
   * reading or writing shapefiles.
   */
  public static final List<String> removeGSAPolygonLayer(String gsaID,
      String masterShapefile)
      throws IOException, ShapefileException {
    GSAPolygonManager manager = new GSAPolygonManager();
    manager.setGsaAreasShapefile(null);
    manager.setAgencyServiceAreasShapefile(null);
    manager.setServiceAreas(new FeatureSet(ShapeType.Polygon));
    manager.setGsaAreas(new FeatureSet(ShapeType.Polygon));
    manager.setBufferFeet(-1000);
    return manager.removeGSAPolygonLayer(gsaID, masterShapefile);

  }

}
