/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileReader;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.Projections;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ContainsTests {

  /**
   * The contains should be true here.
   *
   * @throws gov.ca.water.shapelite.io.ShapefileException
   * @throws gov.ca.water.shapelite.projection.ProjectionException
   */
  @Test
  public void contains() throws ShapefileException, ProjectionException, IOException {
    String gsaPath = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\ECCID_GSA.zip";
    ShapefileReader reader = new ShapefileReader();
    reader.open(gsaPath);
    FeatureSet inside = reader.getFeatures();
    inside.round(6);

    String lafcoPath = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\ECCID_LAFCO.zip";
    reader.open(lafcoPath);
    FeatureSet outside = reader.getFeatures();
    outside.round(6);
    outside = outside.buffer(1);
    assertTrue(outside.contains(inside));
  }


  /**
   * The contains should be true here.
   *
   * @throws gov.ca.water.shapelite.io.ShapefileException
   * @throws gov.ca.water.shapelite.projection.ProjectionException
   */
  @Test
  public void contains2() throws ShapefileException, ProjectionException {
    String masterLoc = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\NonPostedSubmittedGsaSet.zip";
    FeatureSet master = FeatureSet.from(masterLoc);
    String gsaLoc = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\SKGSA Boundary.zip";
    FeatureSet gsa = FeatureSet.from(gsaLoc);
    FeatureSet kings = master.copy();
    kings.getFeatures().clear();
    for(Feature f : master.getFeatures()){
      if("74".equals(f.getAttributes().get("DWR GSA ID"))){
        // this should be Kings River East
        kings.getFeatures().add(f);
      }
    }
    FeatureSet gsa84 = gsa.reprojectCopy(Projections.getWGS84());
    gsa84 = gsa84.union();
    gsa84.round(6);
    FeatureSet fs = kings.intersection(gsa84, -500);
    boolean stop = true;


  }

//  /**
//   * The contains should be true here.
//   *
//   * @throws gov.ca.water.shapelite.io.ShapefileException
//   * @throws gov.ca.water.shapelite.projection.ProjectionException
//   */
//  @Test
//  public void contains2() throws ShapefileException, ProjectionException {
//    String gsaPath = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\ECCID_GSA.zip";
//    FeatureSet inside = FeatureSet.from(gsaPath);
//    inside.round(6);
//    inside = inside.buffer(100);
//    String lafcoPath = "J:\\For Donghai\\GIS_Test\\overlap_test\\GEI_Test_Data\\Test Polygons\\ECCID_GSA.zip";
//    FeatureSet outside = FeatureSet.from(lafcoPath);
//    outside.round(6);
//    assertTrue(!outside.contains(inside));
//  }
}
