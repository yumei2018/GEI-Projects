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
package gov.ca.water.shapelite.projection.categories.geographic;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class SpheroidBased extends CoordinateSystemCategory {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private final ProjectionInfo Airy1830;
  private final ProjectionInfo Airymodified;
  private final ProjectionInfo AustralianNational;
  private final ProjectionInfo Authalicsphere;
  private final ProjectionInfo AuthalicsphereARCINFO;
  private final ProjectionInfo AverageTerrestrialSystem1977;
  private final ProjectionInfo Bessel1841;
  private final ProjectionInfo BesselNamibia;
  private final ProjectionInfo Besselmodified;
  private final ProjectionInfo Clarke1858;
  private final ProjectionInfo Clarke1866;
  private final ProjectionInfo Clarke1866Michigan;
  private final ProjectionInfo Clarke1880;
  private final ProjectionInfo Clarke1880Arc;
  private final ProjectionInfo Clarke1880Benoit;
  private final ProjectionInfo Clarke1880IGN;
  private final ProjectionInfo Clarke1880RGS;
  private final ProjectionInfo Clarke1880SGA;
  private final ProjectionInfo Everest1830;
  private final ProjectionInfo Everestdefinition1967;
  private final ProjectionInfo Everestdefinition1975;
  private final ProjectionInfo Everestmodified;
  private final ProjectionInfo Everestmodified1969;
  private final ProjectionInfo Fischer1960;
  private final ProjectionInfo Fischer1968;
  private final ProjectionInfo Fischermodified;
  private final ProjectionInfo GRS1967;
  private final ProjectionInfo GRS1980;
  private final ProjectionInfo Helmert1906;
  private final ProjectionInfo Hough1960;
  private final ProjectionInfo IndonesianNational;
  private final ProjectionInfo International1924;
  private final ProjectionInfo International1967;
  private final ProjectionInfo Krasovsky1940;
  private final ProjectionInfo OSU1986geoidalmodel;
  private final ProjectionInfo OSU1991geoidalmodel;
  private final ProjectionInfo Plessis1817;
  private final ProjectionInfo SphereEMEP;
  private final ProjectionInfo Struve1860;
  private final ProjectionInfo Transitpreciseephemeris;
  private final ProjectionInfo WGS1966;
  private final ProjectionInfo Walbeck;
  private final ProjectionInfo WarOffice;

  //</editor-fold>
  /**
   * Creates a new instance of the SpheroidBased class.
   */
  public SpheroidBased() {
    Airy1830 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=airy +no_defs ").orElse(null);
    Airymodified = ProjectionInfo.fromProj4String("+proj=longlat +a=6377340.189 +b=6356034.447938534 +no_defs ").orElse(null);
    AustralianNational = ProjectionInfo.fromProj4String("+proj=longlat +ellps=aust_SA +no_defs ").orElse(null);
    Authalicsphere = ProjectionInfo.fromProj4String("+proj=longlat +a=6371000 +b=6371000 +no_defs ").orElse(null);
    AuthalicsphereARCINFO = ProjectionInfo.fromProj4String("+proj=longlat +a=6370997 +b=6370997 +no_defs ").orElse(null);
    AverageTerrestrialSystem1977 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378135 +b=6356750.304921594 +no_defs ").orElse(null);
    Bessel1841 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Besselmodified = ProjectionInfo.fromProj4String("+proj=longlat +a=6377492.018 +b=6356173.508712696 +no_defs ").orElse(null);
    BesselNamibia = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bess_nam +no_defs ").orElse(null);
    Clarke1858 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378293.639 +b=6356617.98149216 +no_defs ").orElse(null);
    Clarke1866 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Clarke1866Michigan = ProjectionInfo.fromProj4String("+proj=longlat +a=6378450.047 +b=6356826.620025999 +no_defs ").orElse(null);
    Clarke1880 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378249.138 +b=6356514.959419348 +no_defs ").orElse(null);
    Clarke1880Arc = ProjectionInfo.fromProj4String("+proj=longlat +a=6378249.145 +b=6356514.966395495 +no_defs ").orElse(null);
    Clarke1880Benoit = ProjectionInfo.fromProj4String("+proj=longlat +a=6378300.79 +b=6356566.430000036 +no_defs ").orElse(null);
    Clarke1880IGN = ProjectionInfo.fromProj4String("+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Clarke1880RGS = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Clarke1880SGA = ProjectionInfo.fromProj4String("+proj=longlat +a=6378249.2 +b=6356514.996941779 +no_defs ").orElse(null);
    Everestdefinition1967 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=evrstSS +no_defs ").orElse(null);
    Everestdefinition1975 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377301.243 +b=6356100.228368102 +no_defs ").orElse(null);
    Everest1830 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377276.345 +b=6356075.41314024 +no_defs ").orElse(null);
    Everestmodified = ProjectionInfo.fromProj4String("+proj=longlat +a=6377304.063 +b=6356103.041812424 +no_defs ").orElse(null);
    Everestmodified1969 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377295.664 +b=6356094.667915204 +no_defs ").orElse(null);
    Fischer1960 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378166 +b=6356784.283607107 +no_defs ").orElse(null);
    Fischer1968 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378150 +b=6356768.337244385 +no_defs ").orElse(null);
    Fischermodified = ProjectionInfo.fromProj4String("+proj=longlat +ellps=fschr60m +no_defs ").orElse(null);
    GRS1967 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS67 +no_defs ").orElse(null);
    GRS1980 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    Helmert1906 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=helmert +no_defs ").orElse(null);
    Hough1960 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378270 +b=6356794.343434343 +no_defs ").orElse(null);
    IndonesianNational = ProjectionInfo.fromProj4String("+proj=longlat +a=6378160 +b=6356774.50408554 +no_defs ").orElse(null);
    International1924 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    International1967 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=aust_SA +no_defs ").orElse(null);
    Krasovsky1940 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    OSU1986geoidalmodel = ProjectionInfo.fromProj4String("+proj=longlat +a=6378136.2 +b=6356751.516671965 +no_defs ").orElse(null);
    OSU1991geoidalmodel = ProjectionInfo.fromProj4String("+proj=longlat +a=6378136.3 +b=6356751.616336684 +no_defs ").orElse(null);
    Plessis1817 = ProjectionInfo.fromProj4String("+proj=longlat +a=6376523 +b=6355862.933255573 +no_defs ").orElse(null);
    SphereEMEP = ProjectionInfo.fromProj4String("+proj=longlat +a=6370000 +b=6370000 +no_defs ").orElse(null);
    Struve1860 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378297 +b=6356655.847080379 +no_defs ").orElse(null);
    Transitpreciseephemeris = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS66 +no_defs ").orElse(null);
    Walbeck = ProjectionInfo.fromProj4String("+proj=longlat +a=6376896 +b=6355834.846687364 +no_defs ").orElse(null);
    WarOffice = ProjectionInfo.fromProj4String("+proj=longlat +a=6378300.583 +b=6356752.270219594 +no_defs ").orElse(null);
    WGS1966 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS66 +no_defs ").orElse(null);

    Airy1830.setLatLon(true);
    Airymodified.setLatLon(true);
    AustralianNational.setLatLon(true);
    Authalicsphere.setLatLon(true);
    AuthalicsphereARCINFO.setLatLon(true);
    AverageTerrestrialSystem1977.setLatLon(true);
    Bessel1841.setLatLon(true);
    Besselmodified.setLatLon(true);
    BesselNamibia.setLatLon(true);
    Clarke1858.setLatLon(true);
    Clarke1866.setLatLon(true);
    Clarke1866Michigan.setLatLon(true);
    Clarke1880.setLatLon(true);
    Clarke1880Arc.setLatLon(true);
    Clarke1880Benoit.setLatLon(true);
    Clarke1880IGN.setLatLon(true);
    Clarke1880RGS.setLatLon(true);
    Clarke1880SGA.setLatLon(true);
    Everestdefinition1967.setLatLon(true);
    Everestdefinition1975.setLatLon(true);
    Everest1830.setLatLon(true);
    Everestmodified.setLatLon(true);
    Everestmodified1969.setLatLon(true);
    Fischer1960.setLatLon(true);
    Fischer1968.setLatLon(true);
    Fischermodified.setLatLon(true);
    GRS1967.setLatLon(true);
    GRS1980.setLatLon(true);
    Helmert1906.setLatLon(true);
    Hough1960.setLatLon(true);
    IndonesianNational.setLatLon(true);
    International1924.setLatLon(true);
    International1967.setLatLon(true);
    Krasovsky1940.setLatLon(true);
    OSU1986geoidalmodel.setLatLon(true);
    OSU1991geoidalmodel.setLatLon(true);
    Plessis1817.setLatLon(true);
    SphereEMEP.setLatLon(true);
    Struve1860.setLatLon(true);
    Transitpreciseephemeris.setLatLon(true);
    Walbeck.setLatLon(true);
    WarOffice.setLatLon(true);
    WGS1966.setLatLon(true);

    Airy1830.getGeographicInfo().setName("GCS_Airy_1830");
    Airymodified.getGeographicInfo().setName("GCS_Airy_Modified");
    AustralianNational.getGeographicInfo().setName("GCS_Australian");
    Authalicsphere.getGeographicInfo().setName("GCS_Sphere");
    AuthalicsphereARCINFO.getGeographicInfo().setName("GCS_Sphere_ARC_INFO");
    AverageTerrestrialSystem1977.getGeographicInfo().setName("GCS_ATS_1977");
    Bessel1841.getGeographicInfo().setName("GCS_Bessel_1841");
    Besselmodified.getGeographicInfo().setName("GCS_Bessel_Modified");
    BesselNamibia.getGeographicInfo().setName("GCS_Bessel_Namibia");
    Clarke1858.getGeographicInfo().setName("GCS_Clarke_1858");
    Clarke1866.getGeographicInfo().setName("GCS_Clarke_1866");
    Clarke1866Michigan.getGeographicInfo().setName("GCS_Clarke_1866_Michigan");
    Clarke1880.getGeographicInfo().setName("GCS_Clarke_1880");
    Clarke1880Arc.getGeographicInfo().setName("GCS_Clarke_1880_Arc");
    Clarke1880Benoit.getGeographicInfo().setName("GCS_Clarke_1880_Benoit");
    Clarke1880IGN.getGeographicInfo().setName("GCS_Clarke_1880_IGN");
    Clarke1880RGS.getGeographicInfo().setName("GCS_Clarke_1880_RGS");
    Clarke1880SGA.getGeographicInfo().setName("GCS_Clarke_1880_SGA");
    Everestdefinition1967.getGeographicInfo().setName("GCS_Everest_def_1967");
    Everestdefinition1975.getGeographicInfo().setName("GCS_Everest_def_1975");
    Everest1830.getGeographicInfo().setName("GCS_Everest_1830");
    Everestmodified.getGeographicInfo().setName("GCS_Everest_Modified");
    Everestmodified1969.getGeographicInfo().setName("GCS_Everest_Modified_1969");
    Fischer1960.getGeographicInfo().setName("GCS_Fischer_1960");
    Fischer1968.getGeographicInfo().setName("GCS_Fischer_1968");
    Fischermodified.getGeographicInfo().setName("GCS_Fischer_Modified");
    GRS1967.getGeographicInfo().setName("GCS_GRS_1967");
    GRS1980.getGeographicInfo().setName("GCS_GRS_1980");
    Helmert1906.getGeographicInfo().setName("GCS_Helmert_1906");
    Hough1960.getGeographicInfo().setName("GCS_Hough_1960");
    IndonesianNational.getGeographicInfo().setName("GCS_Indonesian");
    International1924.getGeographicInfo().setName("GCS_International_1924");
    International1967.getGeographicInfo().setName("GCS_International_1967");
    Krasovsky1940.getGeographicInfo().setName("GCS_Krasovsky_1940");
    OSU1986geoidalmodel.getGeographicInfo().setName("GCS_OSU_86F");
    OSU1991geoidalmodel.getGeographicInfo().setName("GCS_OSU_91A");
    Plessis1817.getGeographicInfo().setName("GCS_Plessis_1817");
    SphereEMEP.getGeographicInfo().setName("GCS_Sphere_EMEP");
    Struve1860.getGeographicInfo().setName("GCS_Struve_1860");
    Transitpreciseephemeris.getGeographicInfo().setName("GCS_NWL_9D");
    Walbeck.getGeographicInfo().setName("GCS_Walbeck");
    WarOffice.getGeographicInfo().setName("GCS_War_Office");
    WGS1966.getGeographicInfo().setName("GCS_WGS_1966");

    Airy1830.setName("GCS_Airy_1830");
    Airymodified.setName("GCS_Airy_Modified");
    AustralianNational.setName("GCS_Australian");
    Authalicsphere.setName("GCS_Sphere");
    AuthalicsphereARCINFO.setName("GCS_Sphere_ARC_INFO");
    AverageTerrestrialSystem1977.setName("GCS_ATS_1977");
    Bessel1841.setName("GCS_Bessel_1841");
    Besselmodified.setName("GCS_Bessel_Modified");
    BesselNamibia.setName("GCS_Bessel_Namibia");
    Clarke1858.setName("GCS_Clarke_1858");
    Clarke1866.setName("GCS_Clarke_1866");
    Clarke1866Michigan.setName("GCS_Clarke_1866_Michigan");
    Clarke1880.setName("GCS_Clarke_1880");
    Clarke1880Arc.setName("GCS_Clarke_1880_Arc");
    Clarke1880Benoit.setName("GCS_Clarke_1880_Benoit");
    Clarke1880IGN.setName("GCS_Clarke_1880_IGN");
    Clarke1880RGS.setName("GCS_Clarke_1880_RGS");
    Clarke1880SGA.setName("GCS_Clarke_1880_SGA");
    Everestdefinition1967.setName("GCS_Everest_def_1967");
    Everestdefinition1975.setName("GCS_Everest_def_1975");
    Everest1830.setName("GCS_Everest_1830");
    Everestmodified.setName("GCS_Everest_Modified");
    Everestmodified1969.setName("GCS_Everest_Modified_1969");
    Fischer1960.setName("GCS_Fischer_1960");
    Fischer1968.setName("GCS_Fischer_1968");
    Fischermodified.setName("GCS_Fischer_Modified");
    GRS1967.setName("GCS_GRS_1967");
    GRS1980.setName("GCS_GRS_1980");
    Helmert1906.setName("GCS_Helmert_1906");
    Hough1960.setName("GCS_Hough_1960");
    IndonesianNational.setName("GCS_Indonesian");
    International1924.setName("GCS_International_1924");
    International1967.setName("GCS_International_1967");
    Krasovsky1940.setName("GCS_Krasovsky_1940");
    OSU1986geoidalmodel.setName("GCS_OSU_86F");
    OSU1991geoidalmodel.setName("GCS_OSU_91A");
    Plessis1817.setName("GCS_Plessis_1817");
    SphereEMEP.setName("GCS_Sphere_EMEP");
    Struve1860.setName("GCS_Struve_1860");
    Transitpreciseephemeris.setName("GCS_NWL_9D");
    Walbeck.setName("GCS_Walbeck");
    WarOffice.setName("GCS_War_Office");
    WGS1966.setName("GCS_WGS_1966");

    Airy1830.getGeographicInfo().getDatum().setName("D_Airy_1830");
    Airymodified.getGeographicInfo().getDatum().setName("D_Airy_Modified");
    AustralianNational.getGeographicInfo().getDatum().setName("D_Australian");
    Authalicsphere.getGeographicInfo().getDatum().setName("D_Sphere");
    AuthalicsphereARCINFO.getGeographicInfo().getDatum().setName("D_Sphere_ARC_INFO");
    AverageTerrestrialSystem1977.getGeographicInfo().getDatum().setName("D_ATS_1977");
    Bessel1841.getGeographicInfo().getDatum().setName("D_Bessel_1841");
    Besselmodified.getGeographicInfo().getDatum().setName("D_Bessel_Modified");
    BesselNamibia.getGeographicInfo().getDatum().setName("D_Bessel_Namibia");
    Clarke1858.getGeographicInfo().getDatum().setName("D_Clarke_1858");
    Clarke1866.getGeographicInfo().getDatum().setName("D_Clarke_1866");
    Clarke1866Michigan.getGeographicInfo().getDatum().setName("D_Clarke_1866_Michigan");
    Clarke1880.getGeographicInfo().getDatum().setName("D_Clarke_1880");
    Clarke1880Arc.getGeographicInfo().getDatum().setName("D_Clarke_1880_Arc");
    Clarke1880Benoit.getGeographicInfo().getDatum().setName("D_Clarke_1880_Benoit");
    Clarke1880IGN.getGeographicInfo().getDatum().setName("D_Clarke_1880_IGN");
    Clarke1880RGS.getGeographicInfo().getDatum().setName("D_Clarke_1880_RGS");
    Clarke1880SGA.getGeographicInfo().getDatum().setName("D_Clarke_1880_SGA");
    Everestdefinition1967.getGeographicInfo().getDatum().setName("D_Everest_Def_1967");
    Everestdefinition1975.getGeographicInfo().getDatum().setName("D_Everest_Def_1975");
    Everest1830.getGeographicInfo().getDatum().setName("D_Everest_1830");
    Everestmodified.getGeographicInfo().getDatum().setName("D_Everest_Modified");
    Everestmodified1969.getGeographicInfo().getDatum().setName("D_Everest_Modified_1969");
    Fischer1960.getGeographicInfo().getDatum().setName("D_Fischer_1960");
    Fischer1968.getGeographicInfo().getDatum().setName("D_Fischer_1968");
    Fischermodified.getGeographicInfo().getDatum().setName("D_Fischer_Modified");
    GRS1967.getGeographicInfo().getDatum().setName("D_GRS_1967");
    GRS1980.getGeographicInfo().getDatum().setName("D_GRS_1980");
    Helmert1906.getGeographicInfo().getDatum().setName("D_Helmert_1906");
    Hough1960.getGeographicInfo().getDatum().setName("D_Hough_1960");
    IndonesianNational.getGeographicInfo().getDatum().setName("D_Indonesian");
    International1924.getGeographicInfo().getDatum().setName("D_International_1924");
    International1967.getGeographicInfo().getDatum().setName("D_International_1967");
    Krasovsky1940.getGeographicInfo().getDatum().setName("D_Krasovsky_1940");
    OSU1986geoidalmodel.getGeographicInfo().getDatum().setName("D_OSU_86F");
    OSU1991geoidalmodel.getGeographicInfo().getDatum().setName("D_OSU_91A");
    Plessis1817.getGeographicInfo().getDatum().setName("D_Plessis_1817");
    SphereEMEP.getGeographicInfo().getDatum().setName("D_Sphere_EMEP");
    Struve1860.getGeographicInfo().getDatum().setName("D_Struve_1860");
    Transitpreciseephemeris.getGeographicInfo().getDatum().setName("D_NWL_9D");
    Walbeck.getGeographicInfo().getDatum().setName("D_Walbeck");
    WarOffice.getGeographicInfo().getDatum().setName("D_War_Office");
    WGS1966.getGeographicInfo().getDatum().setName("D_WGS_1966");
  }

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the Airy1830
   */
  public ProjectionInfo getAiry1830() {
    return Airy1830.copy();
  }

  /**
   * @return the Airymodified
   */
  public ProjectionInfo getAirymodified() {
    return Airymodified.copy();
  }

  /**
   * @return the AustralianNational
   */
  public ProjectionInfo getAustralianNational() {
    return AustralianNational.copy();
  }

  /**
   * @return the Authalicsphere
   */
  public ProjectionInfo getAuthalicsphere() {
    return Authalicsphere.copy();
  }

  /**
   * @return the AuthalicsphereARCINFO
   */
  public ProjectionInfo getAuthalicsphereARCINFO() {
    return AuthalicsphereARCINFO.copy();
  }

  /**
   * @return the AverageTerrestrialSystem1977
   */
  public ProjectionInfo getAverageTerrestrialSystem1977() {
    return AverageTerrestrialSystem1977.copy();
  }

  /**
   * @return the Bessel1841
   */
  public ProjectionInfo getBessel1841() {
    return Bessel1841.copy();
  }

  /**
   * @return the BesselNamibia
   */
  public ProjectionInfo getBesselNamibia() {
    return BesselNamibia.copy();
  }

  /**
   * @return the Besselmodified
   */
  public ProjectionInfo getBesselmodified() {
    return Besselmodified.copy();
  }

  /**
   * @return the Clarke1858
   */
  public ProjectionInfo getClarke1858() {
    return Clarke1858.copy();
  }

  /**
   * @return the Clarke1866
   */
  public ProjectionInfo getClarke1866() {
    return Clarke1866.copy();
  }

  /**
   * @return the Clarke1866Michigan
   */
  public ProjectionInfo getClarke1866Michigan() {
    return Clarke1866Michigan.copy();
  }

  /**
   * @return the Clarke1880
   */
  public ProjectionInfo getClarke1880() {
    return Clarke1880.copy();
  }

  /**
   * @return the Clarke1880Arc
   */
  public ProjectionInfo getClarke1880Arc() {
    return Clarke1880Arc.copy();
  }

  /**
   * @return the Clarke1880Benoit
   */
  public ProjectionInfo getClarke1880Benoit() {
    return Clarke1880Benoit.copy();
  }

  /**
   * @return the Clarke1880IGN
   */
  public ProjectionInfo getClarke1880IGN() {
    return Clarke1880IGN.copy();
  }

  /**
   * @return the Clarke1880RGS
   */
  public ProjectionInfo getClarke1880RGS() {
    return Clarke1880RGS.copy();
  }

  /**
   * @return the Clarke1880SGA
   */
  public ProjectionInfo getClarke1880SGA() {
    return Clarke1880SGA.copy();
  }

  /**
   * @return the Everest1830
   */
  public ProjectionInfo getEverest1830() {
    return Everest1830.copy();
  }

  /**
   * @return the Everestdefinition1967
   */
  public ProjectionInfo getEverestdefinition1967() {
    return Everestdefinition1967.copy();
  }

  /**
   * @return the Everestdefinition1975
   */
  public ProjectionInfo getEverestdefinition1975() {
    return Everestdefinition1975.copy();
  }

  /**
   * @return the Everestmodified
   */
  public ProjectionInfo getEverestmodified() {
    return Everestmodified.copy();
  }

  /**
   * @return the Everestmodified1969
   */
  public ProjectionInfo getEverestmodified1969() {
    return Everestmodified1969.copy();
  }

  /**
   * @return the Fischer1960
   */
  public ProjectionInfo getFischer1960() {
    return Fischer1960.copy();
  }

  /**
   * @return the Fischer1968
   */
  public ProjectionInfo getFischer1968() {
    return Fischer1968.copy();
  }

  /**
   * @return the Fischermodified
   */
  public ProjectionInfo getFischermodified() {
    return Fischermodified.copy();
  }

  /**
   * @return the GRS1967
   */
  public ProjectionInfo getGRS1967() {
    return GRS1967.copy();
  }

  /**
   * @return the GRS1980
   */
  public ProjectionInfo getGRS1980() {
    return GRS1980.copy();
  }

  /**
   * @return the Helmert1906
   */
  public ProjectionInfo getHelmert1906() {
    return Helmert1906.copy();
  }

  /**
   * @return the Hough1960
   */
  public ProjectionInfo getHough1960() {
    return Hough1960.copy();
  }

  /**
   * @return the IndonesianNational
   */
  public ProjectionInfo getIndonesianNational() {
    return IndonesianNational.copy();
  }

  /**
   * @return the International1924
   */
  public ProjectionInfo getInternational1924() {
    return International1924.copy();
  }

  /**
   * @return the International1967
   */
  public ProjectionInfo getInternational1967() {
    return International1967.copy();
  }

  /**
   * @return the Krasovsky1940
   */
  public ProjectionInfo getKrasovsky1940() {
    return Krasovsky1940.copy();
  }

  /**
   * @return the OSU1986geoidalmodel
   */
  public ProjectionInfo getOSU1986geoidalmodel() {
    return OSU1986geoidalmodel.copy();
  }

  /**
   * @return the OSU1991geoidalmodel
   */
  public ProjectionInfo getOSU1991geoidalmodel() {
    return OSU1991geoidalmodel.copy();
  }

  /**
   * @return the Plessis1817
   */
  public ProjectionInfo getPlessis1817() {
    return Plessis1817.copy();
  }

  /**
   * @return the SphereEMEP
   */
  public ProjectionInfo getSphereEMEP() {
    return SphereEMEP.copy();
  }

  /**
   * @return the Struve1860
   */
  public ProjectionInfo getStruve1860() {
    return Struve1860.copy();
  }

  /**
   * @return the Transitpreciseephemeris
   */
  public ProjectionInfo getTransitpreciseephemeris() {
    return Transitpreciseephemeris.copy();
  }

  /**
   * @return the WGS1966
   */
  public ProjectionInfo getWGS1966() {
    return WGS1966.copy();
  }

  /**
   * @return the Walbeck
   */
  public ProjectionInfo getWalbeck() {
    return Walbeck.copy();
  }

  /**
   * @return the WarOffice
   */
  public ProjectionInfo getWarOffice() {
    return WarOffice.copy();
  }
  //</editor-fold>
}
