package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
/// <summary>
/// NationalGridsJapan
/// </summary>

public class NationalGridsJapan extends CoordinateSystemCategory {

  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo JGD2000JapanZone1;
  private final ProjectionInfo JGD2000JapanZone10;
  private final ProjectionInfo JGD2000JapanZone11;
  private final ProjectionInfo JGD2000JapanZone12;
  private final ProjectionInfo JGD2000JapanZone13;
  private final ProjectionInfo JGD2000JapanZone14;
  private final ProjectionInfo JGD2000JapanZone15;
  private final ProjectionInfo JGD2000JapanZone16;
  private final ProjectionInfo JGD2000JapanZone17;
  private final ProjectionInfo JGD2000JapanZone18;
  private final ProjectionInfo JGD2000JapanZone19;
  private final ProjectionInfo JGD2000JapanZone2;
  private final ProjectionInfo JGD2000JapanZone3;
  private final ProjectionInfo JGD2000JapanZone4;
  private final ProjectionInfo JGD2000JapanZone5;
  private final ProjectionInfo JGD2000JapanZone6;
  private final ProjectionInfo JGD2000JapanZone7;
  private final ProjectionInfo JGD2000JapanZone8;
  private final ProjectionInfo JGD2000JapanZone9;
  private final ProjectionInfo JapanZone1;
  private final ProjectionInfo JapanZone10;
  private final ProjectionInfo JapanZone11;
  private final ProjectionInfo JapanZone12;
  private final ProjectionInfo JapanZone13;
  private final ProjectionInfo JapanZone14;
  private final ProjectionInfo JapanZone15;
  private final ProjectionInfo JapanZone16;
  private final ProjectionInfo JapanZone17;
  private final ProjectionInfo JapanZone18;
  private final ProjectionInfo JapanZone19;
  private final ProjectionInfo JapanZone2;
  private final ProjectionInfo JapanZone3;
  private final ProjectionInfo JapanZone4;
  private final ProjectionInfo JapanZone5;
  private final ProjectionInfo JapanZone6;
  private final ProjectionInfo JapanZone7;
  private final ProjectionInfo JapanZone8;
  private final ProjectionInfo JapanZone9;

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of NationalGridsJapan
  /// </summary>
  public NationalGridsJapan() {
    JapanZone1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=33 +lon_0=129.5 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone10 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40 +lon_0=140.8333333333333 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone11 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44 +lon_0=140.25 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone12 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44 +lon_0=142.25 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone13 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44 +lon_0=144.25 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone14 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=26 +lon_0=142 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone15 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=26 +lon_0=127.5 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone16 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=26 +lon_0=124 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone17 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=26 +lon_0=131 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone18 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=20 +lon_0=136 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone19 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=26 +lon_0=154 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=33 +lon_0=131 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=132.1666666666667 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=33 +lon_0=133.5 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=134.3333333333333 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=136 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=137.1666666666667 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=138.5 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JapanZone9 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=139.8333333333333 +k=0.999900 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    JGD2000JapanZone1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=33 +lon_0=129.5 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone10 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40 +lon_0=140.8333333333333 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone11 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44 +lon_0=140.25 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone12 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44 +lon_0=142.25 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone13 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44 +lon_0=144.25 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone14 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=26 +lon_0=142 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone15 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=26 +lon_0=127.5 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone16 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=26 +lon_0=124 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone17 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=26 +lon_0=131 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone18 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=20 +lon_0=136 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone19 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=26 +lon_0=154 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=33 +lon_0=131 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=132.1666666666667 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=33 +lon_0=133.5 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=134.3333333333333 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=136 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=137.1666666666667 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=138.5 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    JGD2000JapanZone9 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36 +lon_0=139.8333333333333 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);

    JapanZone1.setName("Japan_Zone_1");
    JapanZone10.setName("Japan_Zone_10");
    JapanZone11.setName("Japan_Zone_11");
    JapanZone12.setName("Japan_Zone_12");
    JapanZone13.setName("Japan_Zone_13");
    JapanZone14.setName("Japan_Zone_14");
    JapanZone15.setName("Japan_Zone_15");
    JapanZone16.setName("Japan_Zone_16");
    JapanZone17.setName("Japan_Zone_17");
    JapanZone18.setName("Japan_Zone_18");
    JapanZone19.setName("Japan_Zone_19");
    JapanZone2.setName("Japan_Zone_2");
    JapanZone3.setName("Japan_Zone_3");
    JapanZone4.setName("Japan_Zone_4");
    JapanZone5.setName("Japan_Zone_5");
    JapanZone6.setName("Japan_Zone_6");
    JapanZone7.setName("Japan_Zone_7");
    JapanZone8.setName("Japan_Zone_8");
    JapanZone9.setName("Japan_Zone_9");
    JGD2000JapanZone1.setName("JGD_2000_Japan_Zone_1");
    JGD2000JapanZone10.setName("JGD_2000_Japan_Zone_10");
    JGD2000JapanZone11.setName("JGD_2000_Japan_Zone_11");
    JGD2000JapanZone12.setName("JGD_2000_Japan_Zone_12");
    JGD2000JapanZone13.setName("JGD_2000_Japan_Zone_13");
    JGD2000JapanZone14.setName("JGD_2000_Japan_Zone_14");
    JGD2000JapanZone15.setName("JGD_2000_Japan_Zone_15");
    JGD2000JapanZone16.setName("JGD_2000_Japan_Zone_16");
    JGD2000JapanZone17.setName("JGD_2000_Japan_Zone_17");
    JGD2000JapanZone18.setName("JGD_2000_Japan_Zone_18");
    JGD2000JapanZone19.setName("JGD_2000_Japan_Zone_19");
    JGD2000JapanZone2.setName("JGD_2000_Japan_Zone_2");
    JGD2000JapanZone3.setName("JGD_2000_Japan_Zone_3");
    JGD2000JapanZone4.setName("JGD_2000_Japan_Zone_4");
    JGD2000JapanZone5.setName("JGD_2000_Japan_Zone_5");
    JGD2000JapanZone6.setName("JGD_2000_Japan_Zone_6");
    JGD2000JapanZone7.setName("JGD_2000_Japan_Zone_7");
    JGD2000JapanZone8.setName("JGD_2000_Japan_Zone_8");
    JGD2000JapanZone9.setName("JGD_2000_Japan_Zone_9");

    JapanZone1.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone10.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone11.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone12.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone13.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone14.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone15.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone16.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone17.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone18.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone19.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone2.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone3.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone4.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone5.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone6.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone7.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone8.getGeographicInfo().setName("GCS_Tokyo");
    JapanZone9.getGeographicInfo().setName("GCS_Tokyo");
    JGD2000JapanZone1.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone10.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone11.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone12.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone13.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone14.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone15.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone16.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone17.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone18.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone19.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone2.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone3.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone4.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone5.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone6.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone7.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone8.getGeographicInfo().setName("GCS_JGD_2000");
    JGD2000JapanZone9.getGeographicInfo().setName("GCS_JGD_2000");

    JapanZone1.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone10.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone11.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone12.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone13.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone14.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone15.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone16.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone17.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone18.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone19.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone2.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone3.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone4.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone5.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone6.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone7.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone8.getGeographicInfo().getDatum().setName("D_Tokyo");
    JapanZone9.getGeographicInfo().getDatum().setName("D_Tokyo");
    JGD2000JapanZone1.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone10.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone11.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone12.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone13.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone14.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone15.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone16.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone17.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone18.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone19.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone2.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone3.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone4.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone5.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone6.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone7.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone8.getGeographicInfo().getDatum().setName("D_JGD_2000");
    JGD2000JapanZone9.getGeographicInfo().getDatum().setName("D_JGD_2000");
  }

  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the JGD2000JapanZone1
   */
  public ProjectionInfo getJGD2000JapanZone1() {
    return JGD2000JapanZone1.copy();
  }

  /**
   * @return the JGD2000JapanZone10
   */
  public ProjectionInfo getJGD2000JapanZone10() {
    return JGD2000JapanZone10.copy();
  }

  /**
   * @return the JGD2000JapanZone11
   */
  public ProjectionInfo getJGD2000JapanZone11() {
    return JGD2000JapanZone11.copy();
  }

  /**
   * @return the JGD2000JapanZone12
   */
  public ProjectionInfo getJGD2000JapanZone12() {
    return JGD2000JapanZone12.copy();
  }

  /**
   * @return the JGD2000JapanZone13
   */
  public ProjectionInfo getJGD2000JapanZone13() {
    return JGD2000JapanZone13.copy();
  }

  /**
   * @return the JGD2000JapanZone14
   */
  public ProjectionInfo getJGD2000JapanZone14() {
    return JGD2000JapanZone14.copy();
  }

  /**
   * @return the JGD2000JapanZone15
   */
  public ProjectionInfo getJGD2000JapanZone15() {
    return JGD2000JapanZone15.copy();
  }

  /**
   * @return the JGD2000JapanZone16
   */
  public ProjectionInfo getJGD2000JapanZone16() {
    return JGD2000JapanZone16.copy();
  }

  /**
   * @return the JGD2000JapanZone17
   */
  public ProjectionInfo getJGD2000JapanZone17() {
    return JGD2000JapanZone17.copy();
  }

  /**
   * @return the JGD2000JapanZone18
   */
  public ProjectionInfo getJGD2000JapanZone18() {
    return JGD2000JapanZone18.copy();
  }

  /**
   * @return the JGD2000JapanZone19
   */
  public ProjectionInfo getJGD2000JapanZone19() {
    return JGD2000JapanZone19.copy();
  }

  /**
   * @return the JGD2000JapanZone2
   */
  public ProjectionInfo getJGD2000JapanZone2() {
    return JGD2000JapanZone2.copy();
  }

  /**
   * @return the JGD2000JapanZone3
   */
  public ProjectionInfo getJGD2000JapanZone3() {
    return JGD2000JapanZone3.copy();
  }

  /**
   * @return the JGD2000JapanZone4
   */
  public ProjectionInfo getJGD2000JapanZone4() {
    return JGD2000JapanZone4.copy();
  }

  /**
   * @return the JGD2000JapanZone5
   */
  public ProjectionInfo getJGD2000JapanZone5() {
    return JGD2000JapanZone5.copy();
  }

  /**
   * @return the JGD2000JapanZone6
   */
  public ProjectionInfo getJGD2000JapanZone6() {
    return JGD2000JapanZone6.copy();
  }

  /**
   * @return the JGD2000JapanZone7
   */
  public ProjectionInfo getJGD2000JapanZone7() {
    return JGD2000JapanZone7.copy();
  }

  /**
   * @return the JGD2000JapanZone8
   */
  public ProjectionInfo getJGD2000JapanZone8() {
    return JGD2000JapanZone8.copy();
  }

  /**
   * @return the JGD2000JapanZone9
   */
  public ProjectionInfo getJGD2000JapanZone9() {
    return JGD2000JapanZone9.copy();
  }

  /**
   * @return the JapanZone1
   */
  public ProjectionInfo getJapanZone1() {
    return JapanZone1.copy();
  }

  /**
   * @return the JapanZone10
   */
  public ProjectionInfo getJapanZone10() {
    return JapanZone10.copy();
  }

  /**
   * @return the JapanZone11
   */
  public ProjectionInfo getJapanZone11() {
    return JapanZone11.copy();
  }

  /**
   * @return the JapanZone12
   */
  public ProjectionInfo getJapanZone12() {
    return JapanZone12.copy();
  }

  /**
   * @return the JapanZone13
   */
  public ProjectionInfo getJapanZone13() {
    return JapanZone13.copy();
  }

  /**
   * @return the JapanZone14
   */
  public ProjectionInfo getJapanZone14() {
    return JapanZone14.copy();
  }

  /**
   * @return the JapanZone15
   */
  public ProjectionInfo getJapanZone15() {
    return JapanZone15.copy();
  }

  /**
   * @return the JapanZone16
   */
  public ProjectionInfo getJapanZone16() {
    return JapanZone16.copy();
  }

  /**
   * @return the JapanZone17
   */
  public ProjectionInfo getJapanZone17() {
    return JapanZone17.copy();
  }

  /**
   * @return the JapanZone18
   */
  public ProjectionInfo getJapanZone18() {
    return JapanZone18.copy();
  }

  /**
   * @return the JapanZone19
   */
  public ProjectionInfo getJapanZone19() {
    return JapanZone19.copy();
  }

  /**
   * @return the JapanZone2
   */
  public ProjectionInfo getJapanZone2() {
    return JapanZone2.copy();
  }

  /**
   * @return the JapanZone3
   */
  public ProjectionInfo getJapanZone3() {
    return JapanZone3.copy();
  }

  /**
   * @return the JapanZone4
   */
  public ProjectionInfo getJapanZone4() {
    return JapanZone4.copy();
  }

  /**
   * @return the JapanZone5
   */
  public ProjectionInfo getJapanZone5() {
    return JapanZone5.copy();
  }

  /**
   * @return the JapanZone6
   */
  public ProjectionInfo getJapanZone6() {
    return JapanZone6.copy();
  }

  /**
   * @return the JapanZone7
   */
  public ProjectionInfo getJapanZone7() {
    return JapanZone7.copy();
  }

  /**
   * @return the JapanZone8
   */
  public ProjectionInfo getJapanZone8() {
    return JapanZone8.copy();
  }

  /**
   * @return the JapanZone9
   */
  public ProjectionInfo getJapanZone9() {
    return JapanZone9.copy();
  }

  // </editor-fold>
}
