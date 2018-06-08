
package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
/// <summary>
/// NationalGridsNorway
/// </summary>

public class NationalGridsNorway extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo NGO1948BaerumKommune;
  private final ProjectionInfo NGO1948Bergenhalvoen;
  private final ProjectionInfo NGO1948NorwayZone1;
  private final ProjectionInfo NGO1948NorwayZone2;
  private final ProjectionInfo NGO1948NorwayZone3;
  private final ProjectionInfo NGO1948NorwayZone4;
  private final ProjectionInfo NGO1948NorwayZone5;
  private final ProjectionInfo NGO1948NorwayZone6;
  private final ProjectionInfo NGO1948NorwayZone7;
  private final ProjectionInfo NGO1948NorwayZone8;
  private final ProjectionInfo NGO1948OsloKommune;
  private final ProjectionInfo NGO1948OsloNorwayZone1;
  private final ProjectionInfo NGO1948OsloNorwayZone2;
  private final ProjectionInfo NGO1948OsloNorwayZone3;
  private final ProjectionInfo NGO1948OsloNorwayZone4;
  private final ProjectionInfo NGO1948OsloNorwayZone5;
  private final ProjectionInfo NGO1948OsloNorwayZone6;
  private final ProjectionInfo NGO1948OsloNorwayZone7;
  private final ProjectionInfo NGO1948OsloNorwayZone8;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of NationalGridsNorway
  /// </summary>
  public NationalGridsNorway() {
    NGO1948BaerumKommune = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=10.72291666666667 +k=1.000000 +x_0=19999.32 +y_0=-202977.79 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948Bergenhalvoen = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=6.05625 +k=1.000000 +x_0=100000 +y_0=-200000 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948NorwayZone1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=6.056249999999999 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948NorwayZone2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=8.389583333333333 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948NorwayZone3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=10.72291666666667 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948NorwayZone4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=13.22291666666667 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948NorwayZone5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=16.88958333333333 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948NorwayZone6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=20.88958333333333 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948NorwayZone7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=24.88958333333333 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948NorwayZone8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=29.05625 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948OsloKommune = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=10.72291666666667 +k=1.000000 +x_0=0 +y_0=-212979.18 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
    NGO1948OsloNorwayZone1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=-15.38958333333334 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +pm=10.72291666666667 +units=m +no_defs ").orElse(null);
    NGO1948OsloNorwayZone2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=-13.05625 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +pm=10.72291666666667 +units=m +no_defs ").orElse(null);
    NGO1948OsloNorwayZone3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=-10.72291666666667 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +pm=10.72291666666667 +units=m +no_defs ").orElse(null);
    NGO1948OsloNorwayZone4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=-8.22291666666667 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +pm=10.72291666666667 +units=m +no_defs ").orElse(null);
    NGO1948OsloNorwayZone5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=-4.556250000000003 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +pm=10.72291666666667 +units=m +no_defs ").orElse(null);
    NGO1948OsloNorwayZone6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=-0.5562500000000004 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +pm=10.72291666666667 +units=m +no_defs ").orElse(null);
    NGO1948OsloNorwayZone7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=3.44375 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +pm=10.72291666666667 +units=m +no_defs ").orElse(null);
    NGO1948OsloNorwayZone8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=58 +lon_0=7.610416666666659 +k=1.000000 +x_0=0 +y_0=0 +a=6377492.018 +b=6356173.508712696 +pm=10.72291666666667 +units=m +no_defs ").orElse(null);

    NGO1948BaerumKommune.setName("NGO_1948_Baerum_Kommune");
    NGO1948Bergenhalvoen.setName("NGO_1948_Bergenhalvoen");
    NGO1948NorwayZone1.setName("NGO_1948_Norway_Zone_1");
    NGO1948NorwayZone2.setName("NGO_1948_Norway_Zone_2");
    NGO1948NorwayZone3.setName("NGO_1948_Norway_Zone_3");
    NGO1948NorwayZone4.setName("NGO_1948_Norway_Zone_4");
    NGO1948NorwayZone5.setName("NGO_1948_Norway_Zone_5");
    NGO1948NorwayZone6.setName("NGO_1948_Norway_Zone_6");
    NGO1948NorwayZone7.setName("NGO_1948_Norway_Zone_7");
    NGO1948NorwayZone8.setName("NGO_1948_Norway_Zone_8");
    NGO1948OsloKommune.setName("NGO_1948_Oslo_Kommune");
    NGO1948OsloNorwayZone1.setName("NGO_1948_Oslo_Norway_Zone_1");
    NGO1948OsloNorwayZone2.setName("NGO_1948_Oslo_Norway_Zone_2");
    NGO1948OsloNorwayZone3.setName("NGO_1948_Oslo_Norway_Zone_3");
    NGO1948OsloNorwayZone4.setName("NGO_1948_Oslo_Norway_Zone_4");
    NGO1948OsloNorwayZone5.setName("NGO_1948_Oslo_Norway_Zone_5");
    NGO1948OsloNorwayZone6.setName("NGO_1948_Oslo_Norway_Zone_6");
    NGO1948OsloNorwayZone7.setName("NGO_1948_Oslo_Norway_Zone_7");
    NGO1948OsloNorwayZone8.setName("NGO_1948_Oslo_Norway_Zone_8");

    NGO1948BaerumKommune.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948Bergenhalvoen.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948NorwayZone1.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948NorwayZone2.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948NorwayZone3.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948NorwayZone4.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948NorwayZone5.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948NorwayZone6.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948NorwayZone7.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948NorwayZone8.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948OsloKommune.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948OsloNorwayZone1.getGeographicInfo().setName("GCS_NGO_1948_Oslo");
    NGO1948OsloNorwayZone2.getGeographicInfo().setName("GCS_NGO_1948_Oslo");
    NGO1948OsloNorwayZone3.getGeographicInfo().setName("GCS_NGO_1948_Oslo");
    NGO1948OsloNorwayZone4.getGeographicInfo().setName("GCS_NGO_1948_Oslo");
    NGO1948OsloNorwayZone5.getGeographicInfo().setName("GCS_NGO_1948_Oslo");
    NGO1948OsloNorwayZone6.getGeographicInfo().setName("GCS_NGO_1948_Oslo");
    NGO1948OsloNorwayZone7.getGeographicInfo().setName("GCS_NGO_1948_Oslo");
    NGO1948OsloNorwayZone8.getGeographicInfo().setName("GCS_NGO_1948_Oslo");

    NGO1948BaerumKommune.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948Bergenhalvoen.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948NorwayZone1.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948NorwayZone2.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948NorwayZone3.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948NorwayZone4.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948NorwayZone5.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948NorwayZone6.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948NorwayZone7.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948NorwayZone8.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948OsloKommune.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948OsloNorwayZone1.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948OsloNorwayZone2.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948OsloNorwayZone3.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948OsloNorwayZone4.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948OsloNorwayZone5.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948OsloNorwayZone6.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948OsloNorwayZone7.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948OsloNorwayZone8.getGeographicInfo().getDatum().setName("D_NGO_1948");
  }

  //</editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the NGO1948BaerumKommune
   */
  public ProjectionInfo getNGO1948BaerumKommune() {
    return NGO1948BaerumKommune.copy();
  }

  /**
   * @return the NGO1948Bergenhalvoen
   */
  public ProjectionInfo getNGO1948Bergenhalvoen() {
    return NGO1948Bergenhalvoen.copy();
  }

  /**
   * @return the NGO1948NorwayZone1
   */
  public ProjectionInfo getNGO1948NorwayZone1() {
    return NGO1948NorwayZone1.copy();
  }

  /**
   * @return the NGO1948NorwayZone2
   */
  public ProjectionInfo getNGO1948NorwayZone2() {
    return NGO1948NorwayZone2.copy();
  }

  /**
   * @return the NGO1948NorwayZone3
   */
  public ProjectionInfo getNGO1948NorwayZone3() {
    return NGO1948NorwayZone3.copy();
  }

  /**
   * @return the NGO1948NorwayZone4
   */
  public ProjectionInfo getNGO1948NorwayZone4() {
    return NGO1948NorwayZone4.copy();
  }

  /**
   * @return the NGO1948NorwayZone5
   */
  public ProjectionInfo getNGO1948NorwayZone5() {
    return NGO1948NorwayZone5.copy();
  }

  /**
   * @return the NGO1948NorwayZone6
   */
  public ProjectionInfo getNGO1948NorwayZone6() {
    return NGO1948NorwayZone6.copy();
  }

  /**
   * @return the NGO1948NorwayZone7
   */
  public ProjectionInfo getNGO1948NorwayZone7() {
    return NGO1948NorwayZone7.copy();
  }

  /**
   * @return the NGO1948NorwayZone8
   */
  public ProjectionInfo getNGO1948NorwayZone8() {
    return NGO1948NorwayZone8.copy();
  }

  /**
   * @return the NGO1948OsloKommune
   */
  public ProjectionInfo getNGO1948OsloKommune() {
    return NGO1948OsloKommune.copy();
  }

  /**
   * @return the NGO1948OsloNorwayZone1
   */
  public ProjectionInfo getNGO1948OsloNorwayZone1() {
    return NGO1948OsloNorwayZone1.copy();
  }

  /**
   * @return the NGO1948OsloNorwayZone2
   */
  public ProjectionInfo getNGO1948OsloNorwayZone2() {
    return NGO1948OsloNorwayZone2.copy();
  }

  /**
   * @return the NGO1948OsloNorwayZone3
   */
  public ProjectionInfo getNGO1948OsloNorwayZone3() {
    return NGO1948OsloNorwayZone3.copy();
  }

  /**
   * @return the NGO1948OsloNorwayZone4
   */
  public ProjectionInfo getNGO1948OsloNorwayZone4() {
    return NGO1948OsloNorwayZone4.copy();
  }

  /**
   * @return the NGO1948OsloNorwayZone5
   */
  public ProjectionInfo getNGO1948OsloNorwayZone5() {
    return NGO1948OsloNorwayZone5.copy();
  }

  /**
   * @return the NGO1948OsloNorwayZone6
   */
  public ProjectionInfo getNGO1948OsloNorwayZone6() {
    return NGO1948OsloNorwayZone6.copy();
  }

  /**
   * @return the NGO1948OsloNorwayZone7
   */
  public ProjectionInfo getNGO1948OsloNorwayZone7() {
    return NGO1948OsloNorwayZone7.copy();
  }

  /**
   * @return the NGO1948OsloNorwayZone8
   */
  public ProjectionInfo getNGO1948OsloNorwayZone8() {
    return NGO1948OsloNorwayZone8.copy();
  }
  // </editor-fold>
}
