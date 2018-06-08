
package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
    /// <summary>
/// NationalGridsSweden
/// </summary>

public class NationalGridsSweden extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo RT380gon;
  private final ProjectionInfo RT3825gonO;
  private final ProjectionInfo RT3825gonV;
  private final ProjectionInfo RT385gonO;
  private final ProjectionInfo RT385gonV;
  private final ProjectionInfo RT3875gonV;
  private final ProjectionInfo RT900gon;
  private final ProjectionInfo RT9025gonO;
  private final ProjectionInfo RT9025gonV;
  private final ProjectionInfo RT905gonO;
  private final ProjectionInfo RT905gonV;
  private final ProjectionInfo RT9075gonV;
  private final ProjectionInfo SWEREF991200;
  private final ProjectionInfo SWEREF991330;
  private final ProjectionInfo SWEREF991415;
  private final ProjectionInfo SWEREF991500;
  private final ProjectionInfo SWEREF991545;
  private final ProjectionInfo SWEREF991630;
  private final ProjectionInfo SWEREF991715;
  private final ProjectionInfo SWEREF991800;
  private final ProjectionInfo SWEREF991845;
  private final ProjectionInfo SWEREF992015;
  private final ProjectionInfo SWEREF992145;
  private final ProjectionInfo SWEREF992315;
  private final ProjectionInfo SWEREF99TM;

        //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   *  Creates a new instance of NationalGridsSweden
   */
  public NationalGridsSweden() {
    RT380gon = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=18.05827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT3825gonO = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=20.30827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT3825gonV = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15.80827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT385gonO = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=22.55827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT385gonV = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=13.55827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT3875gonV = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=11.30827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT900gon = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=18.05827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT9025gonO = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=20.30827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT9025gonV = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15.80827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT905gonO = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=22.55827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT905gonV = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=13.55827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    RT9075gonV = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=11.30827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
    SWEREF991200 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=12 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF991330 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=13.5 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF991415 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=14.25 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF991500 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF991545 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15.75 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF991630 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=16.5 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF991715 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=17.25 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF991800 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=18 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF991845 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=18.75 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF992015 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=20.25 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF992145 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=21.75 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF992315 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=23.25 +k=1.000000 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    SWEREF99TM = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    RT380gon.setName("RT38_0_gon");
    RT3825gonO.setName("RT38_25_gon_O");
    RT3825gonV.setName("RT38_25_gon_V");
    RT385gonO.setName("RT38_5_gon_O");
    RT385gonV.setName("RT38_5_gon_V");
    RT3875gonV.setName("RT38_75_gon_V");
    RT900gon.setName("RT90_0_gon");
    RT9025gonO.setName("RT90_25_gon_O");
    RT9025gonV.setName("RT90_25_gon_V");
    RT905gonO.setName("RT90_5_gon_O");
    RT905gonV.setName("RT90_5_gon_V");
    RT9075gonV.setName("RT90_75_gon_V");
    SWEREF991200.setName("SWEREF99_12_00");
    SWEREF991330.setName("SWEREF99_13_30");
    SWEREF991415.setName("SWEREF99_14_15");
    SWEREF991500.setName("SWEREF99_15_00");
    SWEREF991545.setName("SWEREF99_15_45");
    SWEREF991630.setName("SWEREF99_16_30");
    SWEREF991715.setName("SWEREF99_17_15");
    SWEREF991800.setName("SWEREF99_18_00");
    SWEREF991845.setName("SWEREF99_18_45");
    SWEREF992015.setName("SWEREF99_20_15");
    SWEREF992145.setName("SWEREF99_21_45");
    SWEREF992315.setName("SWEREF99_23_15");
    SWEREF99TM.setName("SWEREF99_TM");
    RT380gon.getGeographicInfo().setName("GCS_RT38");
    RT3825gonO.getGeographicInfo().setName("GCS_RT38");
    RT3825gonV.getGeographicInfo().setName("GCS_RT38");
    RT385gonO.getGeographicInfo().setName("GCS_RT38");
    RT385gonV.getGeographicInfo().setName("GCS_RT38");
    RT3875gonV.getGeographicInfo().setName("GCS_RT38");
    RT900gon.getGeographicInfo().setName("GCS_RT_1990");
    RT9025gonO.getGeographicInfo().setName("GCS_RT_1990");
    RT9025gonV.getGeographicInfo().setName("GCS_RT_1990");
    RT905gonO.getGeographicInfo().setName("GCS_RT_1990");
    RT905gonV.getGeographicInfo().setName("GCS_RT_1990");
    RT9075gonV.getGeographicInfo().setName("GCS_RT_1990");
    SWEREF991200.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF991330.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF991415.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF991500.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF991545.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF991630.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF991715.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF991800.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF991845.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF992015.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF992145.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF992315.getGeographicInfo().setName("GCS_SWEREF99");
    SWEREF99TM.getGeographicInfo().setName("GCS_SWEREF99");
    RT380gon.getGeographicInfo().getDatum().setName("D_Stockholm_1938");
    RT3825gonO.getGeographicInfo().getDatum().setName("D_Stockholm_1938");
    RT3825gonV.getGeographicInfo().getDatum().setName("D_Stockholm_1938");
    RT385gonO.getGeographicInfo().getDatum().setName("D_Stockholm_1938");
    RT385gonV.getGeographicInfo().getDatum().setName("D_Stockholm_1938");
    RT3875gonV.getGeographicInfo().getDatum().setName("D_Stockholm_1938");
    RT900gon.getGeographicInfo().getDatum().setName("D_RT_1990");
    RT9025gonO.getGeographicInfo().getDatum().setName("D_RT_1990");
    RT9025gonV.getGeographicInfo().getDatum().setName("D_RT_1990");
    RT905gonO.getGeographicInfo().getDatum().setName("D_RT_1990");
    RT905gonV.getGeographicInfo().getDatum().setName("D_RT_1990");
    RT9075gonV.getGeographicInfo().getDatum().setName("D_RT_1990");
    SWEREF991200.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF991330.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF991415.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF991500.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF991545.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF991630.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF991715.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF991800.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF991845.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF992015.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF992145.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF992315.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SWEREF99TM.getGeographicInfo().getDatum().setName("D_SWEREF99");
  }

  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Properties">

  /**
   * @return the RT380gon
   */
  public ProjectionInfo getRT380gon() {
    return RT380gon.copy();
  }

  /**
   * @return the RT3825gonO
   */
  public ProjectionInfo getRT3825gonO() {
    return RT3825gonO.copy();
  }

  /**
   * @return the RT3825gonV
   */
  public ProjectionInfo getRT3825gonV() {
    return RT3825gonV.copy();
  }

  /**
   * @return the RT385gonO
   */
  public ProjectionInfo getRT385gonO() {
    return RT385gonO.copy();
  }

  /**
   * @return the RT385gonV
   */
  public ProjectionInfo getRT385gonV() {
    return RT385gonV.copy();
  }

  /**
   * @return the RT3875gonV
   */
  public ProjectionInfo getRT3875gonV() {
    return RT3875gonV.copy();
  }

  /**
   * @return the RT900gon
   */
  public ProjectionInfo getRT900gon() {
    return RT900gon.copy();
  }

  /**
   * @return the RT9025gonO
   */
  public ProjectionInfo getRT9025gonO() {
    return RT9025gonO.copy();
  }

  /**
   * @return the RT9025gonV
   */
  public ProjectionInfo getRT9025gonV() {
    return RT9025gonV.copy();
  }

  /**
   * @return the RT905gonO
   */
  public ProjectionInfo getRT905gonO() {
    return RT905gonO.copy();
  }

  /**
   * @return the RT905gonV
   */
  public ProjectionInfo getRT905gonV() {
    return RT905gonV.copy();
  }

  /**
   * @return the RT9075gonV
   */
  public ProjectionInfo getRT9075gonV() {
    return RT9075gonV.copy();
  }

  /**
   * @return the SWEREF991200
   */
  public ProjectionInfo getSWEREF991200() {
    return SWEREF991200.copy();
  }

  /**
   * @return the SWEREF991330
   */
  public ProjectionInfo getSWEREF991330() {
    return SWEREF991330.copy();
  }

  /**
   * @return the SWEREF991415
   */
  public ProjectionInfo getSWEREF991415() {
    return SWEREF991415.copy();
  }

  /**
   * @return the SWEREF991500
   */
  public ProjectionInfo getSWEREF991500() {
    return SWEREF991500.copy();
  }

  /**
   * @return the SWEREF991545
   */
  public ProjectionInfo getSWEREF991545() {
    return SWEREF991545.copy();
  }

  /**
   * @return the SWEREF991630
   */
  public ProjectionInfo getSWEREF991630() {
    return SWEREF991630.copy();
  }

  /**
   * @return the SWEREF991715
   */
  public ProjectionInfo getSWEREF991715() {
    return SWEREF991715.copy();
  }

  /**
   * @return the SWEREF991800
   */
  public ProjectionInfo getSWEREF991800() {
    return SWEREF991800.copy();
  }

  /**
   * @return the SWEREF991845
   */
  public ProjectionInfo getSWEREF991845() {
    return SWEREF991845.copy();
  }

  /**
   * @return the SWEREF992015
   */
  public ProjectionInfo getSWEREF992015() {
    return SWEREF992015.copy();
  }

  /**
   * @return the SWEREF992145
   */
  public ProjectionInfo getSWEREF992145() {
    return SWEREF992145.copy();
  }

  /**
   * @return the SWEREF992315
   */
  public ProjectionInfo getSWEREF992315() {
    return SWEREF992315.copy();
  }

  /**
   * @return the SWEREF99TM
   */
  public ProjectionInfo getSWEREF99TM() {
    return SWEREF99TM.copy();
  }
  // </editor-fold>
}
