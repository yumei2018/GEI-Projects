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
package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class StateSystems extends CoordinateSystemCategory {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private final ProjectionInfo NAD_1927_Alaska_Albers_Feet;
  private final ProjectionInfo NAD_1927_Alaska_Albers_Meters;
  private final ProjectionInfo NAD_1927_California_Teale_Albers;
  private final ProjectionInfo NAD_1927_Georgia_Statewide_Albers;
  private final ProjectionInfo NAD_1927_Michigan_GeoRef_Meters;
  private final ProjectionInfo NAD_1927_Michigan_GeoRef_US_feet;
  private final ProjectionInfo NAD_1927_Texas_Statewide_Mapping_System;
  private final ProjectionInfo NAD_1927_Wisconsin_TM;
  private final ProjectionInfo NAD_1983_California_Teale_Albers;
  private final ProjectionInfo NAD_1983_Florida_GDL_Albers;
  private final ProjectionInfo NAD_1983_Georgia_Statewide_Lambert;
  private final ProjectionInfo NAD_1983_HARN_California_Teale_Albers;
  private final ProjectionInfo NAD_1983_HARN_Florida_GDL_Albers;
  private final ProjectionInfo NAD_1983_HARN_Michigan_GeoRef_Meters;
  private final ProjectionInfo NAD_1983_HARN_Michigan_GeoRef;
  private final ProjectionInfo NAD_1983_HARN_Mississippi_TM;
  private final ProjectionInfo NAD_1983_HARN_Oregon_Statewide_Lambert_Feet_Intl;
  private final ProjectionInfo NAD_1983_HARN_Oregon_Statewide_Lambert;
  private final ProjectionInfo NAD_1983_HARN_Wisconsin_TM_US_Feet;
  private final ProjectionInfo NAD_1983_HARN_Wisconsin_TM;
  private final ProjectionInfo NAD_1983_Idaho_TM;
  private final ProjectionInfo NAD_1983_Michigan_GeoRef_Meters;
  private final ProjectionInfo NAD_1983_Michigan_GeoRef_US_feet;
  private final ProjectionInfo NAD_1983_Mississippi_TM;
  private final ProjectionInfo NAD_1983_Oregon_Statewide_Lambert_Feet_Intl;
  private final ProjectionInfo NAD_1983_Oregon_Statewide_Lambert;
  private final ProjectionInfo NAD_1983_Texas_Centric_Mapping_System_Albers;
  private final ProjectionInfo NAD_1983_Texas_Centric_Mapping_System_Lambert;
  private final ProjectionInfo NAD_1983_Texas_Statewide_Mapping_System;
  private final ProjectionInfo NAD_1983_USFS_R6_Albers;
  private final ProjectionInfo NAD_1983_Wisconsin_TM_US_Feet;
  private final ProjectionInfo NAD_1983_Wisconsin_TM;

  //</editor-fold>
  /**
   * Creates a new instance of the StateSystems class.
   */
  public StateSystems() {
    NAD_1927_Alaska_Albers_Feet = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1927_Alaska_Albers_Feet\",GEOGCS[\"GCS_North_American_1927\",DATUM[\"D_North_American_1927\",SPHEROID[\"Clarke_1866\",6378206.4,294.9786982]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Albers\"],PARAMETER[\"False_Easting\",0.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-154.0],PARAMETER[\"Standard_Parallel_1\",55.0],PARAMETER[\"Standard_Parallel_2\",65.0],PARAMETER[\"Latitude_Of_Origin\",50.0],UNIT[\"Foot_US\",0.3048006096012192]]");
    NAD_1927_Alaska_Albers_Meters = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1927_Alaska_Albers_Meters\",GEOGCS[\"GCS_North_American_1927\",DATUM[\"D_North_American_1927\",SPHEROID[\"Clarke_1866\",6378206.4,294.9786982]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Albers\"],PARAMETER[\"False_Easting\",0.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-154.0],PARAMETER[\"Standard_Parallel_1\",55.0],PARAMETER[\"Standard_Parallel_2\",65.0],PARAMETER[\"Latitude_Of_Origin\",50.0],UNIT[\"Meter\",1.0]]");
    NAD_1927_California_Teale_Albers = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1927_California_Teale_Albers\",GEOGCS[\"GCS_North_American_1927\",DATUM[\"D_North_American_1927\",SPHEROID[\"Clarke_1866\",6378206.4,294.9786982]],PRIMEM[\"Greenwich\",0],UNIT[\"Degree\",0.017453292519943295]],PROJECTION[\"Albers\"],PARAMETER[\"False_Easting\",0.0],PARAMETER[\"False_Northing\",-4000000.0],PARAMETER[\"Central_Meridian\",-120.0],PARAMETER[\"Standard_Parallel_1\",34.0],PARAMETER[\"Standard_Parallel_2\",40.5],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]");
    NAD_1927_Georgia_Statewide_Albers = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1927_Georgia_Statewide_Albers\",GEOGCS[\"GCS_North_American_1927\",DATUM[\"D_North_American_1927\",SPHEROID[\"Clarke_1866\",6378206.4,294.9786982]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Albers\"],PARAMETER[\"False_Easting\",0.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-83.5],PARAMETER[\"Standard_Parallel_1\",29.5],PARAMETER[\"Standard_Parallel_2\",45.5],PARAMETER[\"Latitude_Of_Origin\",23.0],UNIT[\"Foot_US\",0.3048006096012192]]");
    NAD_1927_Michigan_GeoRef_Meters = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1927_Michigan_GeoRef_Meters\",GEOGCS[\"GCS_North_American_1927\",DATUM[\"D_North_American_1927\",SPHEROID[\"Clarke_1866\",6378206.4,294.9786982]],PRIMEM[\"Greenwich\",0],UNIT[\"Degree\",0.0174532925199432955]],PROJECTION[\"Hotine_Oblique_Mercator_Azimuth_Natural_Origin\"],PARAMETER[\"False_Easting\",2546731.496],PARAMETER[\"False_Northing\",-4354009.816],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Azimuth\",337.25556],PARAMETER[\"Longitude_Of_Center\",-86],PARAMETER[\"Latitude_Of_Center\",45.30916666666666],UNIT[\"Meter\",1]]");
    NAD_1927_Michigan_GeoRef_US_feet = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1927_Michigan_GeoRef_Feet_US\",GEOGCS[\"GCS_North_American_1927\",DATUM[\"D_North_American_1927\",SPHEROID[\"Clarke_1866\",6378206.4,294.9786982]],PRIMEM[\"Greenwich\",0],UNIT[\"Degree\",0.0174532925199432955]],PROJECTION[\"Hotine_Oblique_Mercator_Azimuth_Natural_Origin\"],PARAMETER[\"False_Easting\",8355401.583],PARAMETER[\"False_Northing\",-14284780.538],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Azimuth\",337.25556],PARAMETER[\"Longitude_Of_Center\",-86],PARAMETER[\"Latitude_Of_Center\",45.30916666666666],UNIT[\"Foot_US\",0.304800609601219241]]");
    NAD_1927_Texas_Statewide_Mapping_System = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1927_Texas_Statewide_Mapping_System\",GEOGCS[\"GCS_North_American_1927\",DATUM[\"D_North_American_1927\",SPHEROID[\"Clarke_1866\",6378206.4,294.9786982]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Lambert_Conformal_Conic\"],PARAMETER[\"False_Easting\",3000000.0],PARAMETER[\"False_Northing\",3000000.0],PARAMETER[\"Central_Meridian\",-100.0],PARAMETER[\"Standard_Parallel_1\",27.41666666666667],PARAMETER[\"Standard_Parallel_2\",34.91666666666666],PARAMETER[\"Latitude_Of_Origin\",31.16666666666667],UNIT[\"Foot\",0.3048]]");
    NAD_1927_Wisconsin_TM = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1927_Wisconsin_TM\",GEOGCS[\"GCS_North_American_1927\",DATUM[\"D_North_American_1927\",SPHEROID[\"Clarke_1866\",6378206.4,294.9786982]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",500000.0],PARAMETER[\"False_Northing\",-4500000.0],PARAMETER[\"Central_Meridian\",-90.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]");
    NAD_1983_California_Teale_Albers = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_California_Teale_Albers\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Albers\"],PARAMETER[\"False_Easting\",0.0],PARAMETER[\"False_Northing\",-4000000.0],PARAMETER[\"Central_Meridian\",-120.0],PARAMETER[\"Standard_Parallel_1\",34.0],PARAMETER[\"Standard_Parallel_2\",40.5],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]");

    NAD_1983_Florida_GDL_Albers = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Florida_GDL_Albers\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Albers\"],PARAMETER[\"False_Easting\",400000.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-84.0],PARAMETER[\"Standard_Parallel_1\",24.0],PARAMETER[\"Standard_Parallel_2\",31.5],PARAMETER[\"Latitude_Of_Origin\",24.0],UNIT[\"Meter\",1.0]]");
    NAD_1983_Georgia_Statewide_Lambert = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Georgia_Statewide_Lambert\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Lambert_Conformal_Conic\"],PARAMETER[\"False_Easting\",0.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-83.5],PARAMETER[\"Standard_Parallel_1\",31.41666666666667],PARAMETER[\"Standard_Parallel_2\",34.28333333333333],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]");
    NAD_1983_HARN_California_Teale_Albers = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_HARN_California_Teale_Albers\",GEOGCS[\"GCS_North_American_1983_HARN\",DATUM[\"D_North_American_1983_HARN\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Albers\"],PARAMETER[\"False_Easting\",0.0],PARAMETER[\"False_Northing\",-4000000.0],PARAMETER[\"Central_Meridian\",-120.0],PARAMETER[\"Standard_Parallel_1\",34.0],PARAMETER[\"Standard_Parallel_2\",40.5],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]");
    NAD_1983_HARN_Florida_GDL_Albers = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_HARN_Florida_GDL_Albers\",GEOGCS[\"GCS_North_American_1983_HARN\",DATUM[\"D_North_American_1983_HARN\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Albers\"],PARAMETER[\"False_Easting\",400000.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-84.0],PARAMETER[\"Standard_Parallel_1\",24.0],PARAMETER[\"Standard_Parallel_2\",31.5],PARAMETER[\"Latitude_Of_Origin\",24.0],UNIT[\"Meter\",1.0]]");
    NAD_1983_HARN_Michigan_GeoRef_Meters = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_HARN_Michigan_GeoRef_Meters\",GEOGCS[\"GCS_North_American_1983_HARN\",DATUM[\"D_North_American_1983_HARN\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Hotine_Oblique_Mercator_Azimuth_Natural_Origin\"],PARAMETER[\"False_Easting\",2546731.496],PARAMETER[\"False_Northing\",-4354009.816],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Azimuth\",337.25556],PARAMETER[\"Longitude_Of_Center\",-86.0],PARAMETER[\"Latitude_Of_Center\",45.30916666666666],UNIT[\"Meter\",1.0]]");
    NAD_1983_HARN_Michigan_GeoRef = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_HARN_Michigan_GeoRef_Meters\",GEOGCS[\"GCS_North_American_1983_HARN\",DATUM[\"D_North_American_1983_HARN\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Hotine_Oblique_Mercator_Azimuth_Natural_Origin\"],PARAMETER[\"False_Easting\",2546731.496],PARAMETER[\"False_Northing\",-4354009.816],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Azimuth\",337.25556],PARAMETER[\"Longitude_Of_Center\",-86.0],PARAMETER[\"Latitude_Of_Center\",45.30916666666666],UNIT[\"Meter\",1.0]]");
    NAD_1983_HARN_Mississippi_TM = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_HARN_Mississippi_TM\",GEOGCS[\"GCS_North_American_1983_HARN\",DATUM[\"D_North_American_1983_HARN\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",500000.0],PARAMETER[\"False_Northing\",1300000.0],PARAMETER[\"Central_Meridian\",-89.75],PARAMETER[\"Scale_Factor\",0.9998335],PARAMETER[\"Latitude_Of_Origin\",32.5],UNIT[\"Meter\",1.0]]");
    NAD_1983_HARN_Oregon_Statewide_Lambert_Feet_Intl = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_HARN_Oregon_Statewide_Lambert_Feet_Intl\",GEOGCS[\"GCS_North_American_1983_HARN\",DATUM[\"D_North_American_1983_HARN\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Lambert_Conformal_Conic\"],PARAMETER[\"False_Easting\",1312335.958005249],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-120.5],PARAMETER[\"Standard_Parallel_1\",43.0],PARAMETER[\"Standard_Parallel_2\",45.5],PARAMETER[\"Latitude_Of_Origin\",41.75],UNIT[\"Foot\",0.3048]]");
    NAD_1983_HARN_Oregon_Statewide_Lambert = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_HARN_Oregon_Statewide_Lambert\",GEOGCS[\"GCS_North_American_1983_HARN\",DATUM[\"D_North_American_1983_HARN\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Lambert_Conformal_Conic\"],PARAMETER[\"False_Easting\",400000.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-120.5],PARAMETER[\"Standard_Parallel_1\",43.0],PARAMETER[\"Standard_Parallel_2\",45.5],PARAMETER[\"Latitude_Of_Origin\",41.75],UNIT[\"Meter\",1.0]]");
    NAD_1983_HARN_Wisconsin_TM_US_Feet = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_HARN_Wisconsin_TM_US_Ft\",GEOGCS[\"GCS_North_American_1983_HARN\",DATUM[\"D_North_American_1983_HARN\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1706033.333333333],PARAMETER[\"False_Northing\",-14698133.33333333],PARAMETER[\"Central_Meridian\",-90.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]");
    NAD_1983_HARN_Wisconsin_TM = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_HARN_Wisconsin_TM\",GEOGCS[\"GCS_North_American_1983_HARN\",DATUM[\"D_North_American_1983_HARN\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",520000.0],PARAMETER[\"False_Northing\",-4480000.0],PARAMETER[\"Central_Meridian\",-90.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]");
    NAD_1983_Idaho_TM = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Idaho_TM\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",2500000.0],PARAMETER[\"False_Northing\",1200000.0],PARAMETER[\"Central_Meridian\",-114.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",42.0],UNIT[\"Meter\",1.0]]");
    NAD_1983_Michigan_GeoRef_Meters = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Michigan_GeoRef_Meters\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137,298.257222101]],PRIMEM[\"Greenwich\",0],UNIT[\"Degree\",0.0174532925199432955]],PROJECTION[\"Hotine_Oblique_Mercator_Azimuth_Natural_Origin\"],PARAMETER[\"False_Easting\",2546731.496],PARAMETER[\"False_Northing\",-4354009.816],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Azimuth\",337.25556],PARAMETER[\"Longitude_Of_Center\",-86],PARAMETER[\"Latitude_Of_Center\",45.30916666666666],UNIT[\"Meter\",1]]");
    NAD_1983_Michigan_GeoRef_US_feet = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Michigan_GeoRef_Feet_US\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137,298.257222101]],PRIMEM[\"Greenwich\",0],UNIT[\"Degree\",0.0174532925199432955]],PROJECTION[\"Hotine_Oblique_Mercator_Azimuth_Natural_Origin\"],PARAMETER[\"False_Easting\",8355401.583],PARAMETER[\"False_Northing\",-14284780.538],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Azimuth\",337.25556],PARAMETER[\"Longitude_Of_Center\",-86],PARAMETER[\"Latitude_Of_Center\",45.30916666666666],UNIT[\"Foot_US\",0.304800609601219241]]");
    NAD_1983_Mississippi_TM = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Mississippi_TM\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",500000.0],PARAMETER[\"False_Northing\",1300000.0],PARAMETER[\"Central_Meridian\",-89.75],PARAMETER[\"Scale_Factor\",0.9998335],PARAMETER[\"Latitude_Of_Origin\",32.5],UNIT[\"Meter\",1.0]]");
    NAD_1983_Oregon_Statewide_Lambert_Feet_Intl = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Oregon_Statewide_Lambert_Feet_Intl\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Lambert_Conformal_Conic\"],PARAMETER[\"False_Easting\",1312335.958005249],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-120.5],PARAMETER[\"Standard_Parallel_1\",43.0],PARAMETER[\"Standard_Parallel_2\",45.5],PARAMETER[\"Latitude_Of_Origin\",41.75],UNIT[\"Foot\",0.3048]]");
    NAD_1983_Oregon_Statewide_Lambert = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Oregon_Statewide_Lambert\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Lambert_Conformal_Conic\"],PARAMETER[\"False_Easting\",400000.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-120.5],PARAMETER[\"Standard_Parallel_1\",43.0],PARAMETER[\"Standard_Parallel_2\",45.5],PARAMETER[\"Latitude_Of_Origin\",41.75],UNIT[\"Meter\",1.0]]");
    NAD_1983_Texas_Centric_Mapping_System_Albers = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Texas_Centric_Mapping_System_Albers\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Albers\"],PARAMETER[\"False_Easting\",1500000.0],PARAMETER[\"False_Northing\",6000000.0],PARAMETER[\"Central_Meridian\",-100.0],PARAMETER[\"Standard_Parallel_1\",27.5],PARAMETER[\"Standard_Parallel_2\",35.0],PARAMETER[\"Latitude_Of_Origin\",18.0],UNIT[\"Meter\",1.0]]");
    NAD_1983_Texas_Centric_Mapping_System_Lambert = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Texas_Centric_Mapping_System_Lambert\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Lambert_Conformal_Conic\"],PARAMETER[\"False_Easting\",1500000.0],PARAMETER[\"False_Northing\",5000000.0],PARAMETER[\"Central_Meridian\",-100.0],PARAMETER[\"Standard_Parallel_1\",27.5],PARAMETER[\"Standard_Parallel_2\",35.0],PARAMETER[\"Latitude_Of_Origin\",18.0],UNIT[\"Meter\",1.0]]");
    NAD_1983_Texas_Statewide_Mapping_System = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Texas_Statewide_Mapping_System\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Lambert_Conformal_Conic\"],PARAMETER[\"False_Easting\",1000000.0],PARAMETER[\"False_Northing\",1000000.0],PARAMETER[\"Central_Meridian\",-100.0],PARAMETER[\"Standard_Parallel_1\",27.41666666666667],PARAMETER[\"Standard_Parallel_2\",34.91666666666666],PARAMETER[\"Latitude_Of_Origin\",31.16666666666667],UNIT[\"Meter\",1.0]]");
    NAD_1983_USFS_R6_Albers = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_USFS_R6_Albers\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Albers\"],PARAMETER[\"False_Easting\",600000.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",-120.0],PARAMETER[\"Standard_Parallel_1\",43.0],PARAMETER[\"Standard_Parallel_2\",48.0],PARAMETER[\"Latitude_Of_Origin\",34.0],UNIT[\"Meter\",1.0]]");
    NAD_1983_Wisconsin_TM_US_Feet = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Wisconsin_TM_US_Ft\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",1706033.333333333],PARAMETER[\"False_Northing\",-14698133.33333333],PARAMETER[\"Central_Meridian\",-90.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Foot_US\",0.3048006096012192]]");
    NAD_1983_Wisconsin_TM = ProjectionInfo.fromEsriString("PROJCS[\"NAD_1983_Wisconsin_TM\",GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"False_Easting\",520000.0],PARAMETER[\"False_Northing\",-4480000.0],PARAMETER[\"Central_Meridian\",-90.0],PARAMETER[\"Scale_Factor\",0.9996],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]");
  }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the NAD_1927_Alaska_Albers_Feet
   */
  public ProjectionInfo getNAD_1927_Alaska_Albers_Feet() {
    return NAD_1927_Alaska_Albers_Feet.copy();
  }

  /**
   * @return the NAD_1927_Alaska_Albers_Meters
   */
  public ProjectionInfo getNAD_1927_Alaska_Albers_Meters() {
    return NAD_1927_Alaska_Albers_Meters.copy();
  }

  /**
   * @return the NAD_1927_California_Teale_Albers
   */
  public ProjectionInfo getNAD_1927_California_Teale_Albers() {
    return NAD_1927_California_Teale_Albers.copy();
  }

  /**
   * @return the NAD_1927_Georgia_Statewide_Albers
   */
  public ProjectionInfo getNAD_1927_Georgia_Statewide_Albers() {
    return NAD_1927_Georgia_Statewide_Albers.copy();
  }

  /**
   * @return the NAD_1927_Michigan_GeoRef_Meters
   */
  public ProjectionInfo getNAD_1927_Michigan_GeoRef_Meters() {
    return NAD_1927_Michigan_GeoRef_Meters.copy();
  }

  /**
   * @return the NAD_1927_Michigan_GeoRef_US_feet
   */
  public ProjectionInfo getNAD_1927_Michigan_GeoRef_US_feet() {
    return NAD_1927_Michigan_GeoRef_US_feet.copy();
  }

  /**
   * @return the NAD_1927_Texas_Statewide_Mapping_System
   */
  public ProjectionInfo getNAD_1927_Texas_Statewide_Mapping_System() {
    return NAD_1927_Texas_Statewide_Mapping_System.copy();
  }

  /**
   * @return the NAD_1927_Wisconsin_TM
   */
  public ProjectionInfo getNAD_1927_Wisconsin_TM() {
    return NAD_1927_Wisconsin_TM.copy();
  }

  /**
   * @return the NAD_1983_California_Teale_Albers
   */
  public ProjectionInfo getNAD_1983_California_Teale_Albers() {
    return NAD_1983_California_Teale_Albers.copy();
  }

  /**
   * @return the NAD_1983_Florida_GDL_Albers
   */
  public ProjectionInfo getNAD_1983_Florida_GDL_Albers() {
    return NAD_1983_Florida_GDL_Albers.copy();
  }

  /**
   * @return the NAD_1983_Georgia_Statewide_Lambert
   */
  public ProjectionInfo getNAD_1983_Georgia_Statewide_Lambert() {
    return NAD_1983_Georgia_Statewide_Lambert.copy();
  }

  /**
   * @return the NAD_1983_HARN_California_Teale_Albers
   */
  public ProjectionInfo getNAD_1983_HARN_California_Teale_Albers() {
    return NAD_1983_HARN_California_Teale_Albers.copy();
  }

  /**
   * @return the NAD_1983_HARN_Florida_GDL_Albers
   */
  public ProjectionInfo getNAD_1983_HARN_Florida_GDL_Albers() {
    return NAD_1983_HARN_Florida_GDL_Albers.copy();
  }

  /**
   * @return the NAD_1983_HARN_Michigan_GeoRef_Meters
   */
  public ProjectionInfo getNAD_1983_HARN_Michigan_GeoRef_Meters() {
    return NAD_1983_HARN_Michigan_GeoRef_Meters.copy();
  }

  /**
   * @return the NAD_1983_HARN_Michigan_GeoRef
   */
  public ProjectionInfo getNAD_1983_HARN_Michigan_GeoRef() {
    return NAD_1983_HARN_Michigan_GeoRef.copy();
  }

  /**
   * @return the NAD_1983_HARN_Mississippi_TM
   */
  public ProjectionInfo getNAD_1983_HARN_Mississippi_TM() {
    return NAD_1983_HARN_Mississippi_TM.copy();
  }

  /**
   * @return the NAD_1983_HARN_Oregon_Statewide_Lambert_Feet_Intl
   */
  public ProjectionInfo getNAD_1983_HARN_Oregon_Statewide_Lambert_Feet_Intl() {
    return NAD_1983_HARN_Oregon_Statewide_Lambert_Feet_Intl.copy();
  }

  /**
   * @return the NAD_1983_HARN_Oregon_Statewide_Lambert
   */
  public ProjectionInfo getNAD_1983_HARN_Oregon_Statewide_Lambert() {
    return NAD_1983_HARN_Oregon_Statewide_Lambert.copy();
  }

  /**
   * @return the NAD_1983_HARN_Wisconsin_TM_US_Feet
   */
  public ProjectionInfo getNAD_1983_HARN_Wisconsin_TM_US_Feet() {
    return NAD_1983_HARN_Wisconsin_TM_US_Feet.copy();
  }

  /**
   * @return the NAD_1983_HARN_Wisconsin_TM
   */
  public ProjectionInfo getNAD_1983_HARN_Wisconsin_TM() {
    return NAD_1983_HARN_Wisconsin_TM.copy();
  }

  /**
   * @return the NAD_1983_Idaho_TM
   */
  public ProjectionInfo getNAD_1983_Idaho_TM() {
    return NAD_1983_Idaho_TM.copy();
  }

  /**
   * @return the NAD_1983_Michigan_GeoRef_Meters
   */
  public ProjectionInfo getNAD_1983_Michigan_GeoRef_Meters() {
    return NAD_1983_Michigan_GeoRef_Meters.copy();
  }

  /**
   * @return the NAD_1983_Michigan_GeoRef_US_feet
   */
  public ProjectionInfo getNAD_1983_Michigan_GeoRef_US_feet() {
    return NAD_1983_Michigan_GeoRef_US_feet.copy();
  }

  /**
   * @return the NAD_1983_Mississippi_TM
   */
  public ProjectionInfo getNAD_1983_Mississippi_TM() {
    return NAD_1983_Mississippi_TM.copy();
  }

  /**
   * @return the NAD_1983_Oregon_Statewide_Lambert_Feet_Intl
   */
  public ProjectionInfo getNAD_1983_Oregon_Statewide_Lambert_Feet_Intl() {
    return NAD_1983_Oregon_Statewide_Lambert_Feet_Intl.copy();
  }

  /**
   * @return the NAD_1983_Oregon_Statewide_Lambert
   */
  public ProjectionInfo getNAD_1983_Oregon_Statewide_Lambert() {
    return NAD_1983_Oregon_Statewide_Lambert.copy();
  }

  /**
   * @return the NAD_1983_Texas_Centric_Mapping_System_Albers
   */
  public ProjectionInfo getNAD_1983_Texas_Centric_Mapping_System_Albers() {
    return NAD_1983_Texas_Centric_Mapping_System_Albers.copy();
  }

  /**
   * @return the NAD_1983_Texas_Centric_Mapping_System_Lambert
   */
  public ProjectionInfo getNAD_1983_Texas_Centric_Mapping_System_Lambert() {
    return NAD_1983_Texas_Centric_Mapping_System_Lambert.copy();
  }

  /**
   * @return the NAD_1983_Texas_Statewide_Mapping_System
   */
  public ProjectionInfo getNAD_1983_Texas_Statewide_Mapping_System() {
    return NAD_1983_Texas_Statewide_Mapping_System.copy();
  }

  /**
   * @return the NAD_1983_USFS_R6_Albers
   */
  public ProjectionInfo getNAD_1983_USFS_R6_Albers() {
    return NAD_1983_USFS_R6_Albers.copy();
  }

  /**
   * @return the NAD_1983_Wisconsin_TM_US_Feet
   */
  public ProjectionInfo getNAD_1983_Wisconsin_TM_US_Feet() {
    return NAD_1983_Wisconsin_TM_US_Feet.copy();
  }

  /**
   * @return the NAD_1983_Wisconsin_TM
   */
  public ProjectionInfo getNAD_1983_Wisconsin_TM() {
    return NAD_1983_Wisconsin_TM.copy();
  }

  //</editor-fold>
}
