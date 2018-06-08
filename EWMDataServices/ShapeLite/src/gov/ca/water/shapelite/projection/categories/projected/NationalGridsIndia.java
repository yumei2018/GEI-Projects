package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 * A Class for supporting the National Grids for India.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class NationalGridsIndia extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo Kalianpur1880IndiaZone0;
  private final ProjectionInfo Kalianpur1880IndiaZoneI;
  private final ProjectionInfo Kalianpur1880IndiaZoneIII;
  private final ProjectionInfo Kalianpur1880IndiaZoneIIa;
  private final ProjectionInfo Kalianpur1880IndiaZoneIIb;
  private final ProjectionInfo Kalianpur1880IndiaZoneIV;
  private final ProjectionInfo Kalianpur1937IndiaZoneIIb;
  private final ProjectionInfo Kalianpur1937UTMZone45N;
  private final ProjectionInfo Kalianpur1937UTMZone46N;
  private final ProjectionInfo Kalianpur1962IndiaZoneI;
  private final ProjectionInfo Kalianpur1962IndiaZoneIIa;
  private final ProjectionInfo Kalianpur1962UTMZone41N;
  private final ProjectionInfo Kalianpur1962UTMZone42N;
  private final ProjectionInfo Kalianpur1962UTMZone43N;
  private final ProjectionInfo Kalianpur1975IndiaZoneI;
  private final ProjectionInfo Kalianpur1975IndiaZoneIII;
  private final ProjectionInfo Kalianpur1975IndiaZoneIIa;
  private final ProjectionInfo Kalianpur1975IndiaZoneIIb;
  private final ProjectionInfo Kalianpur1975IndiaZoneIV;
  private final ProjectionInfo Kalianpur1975UTMZone42N;
  private final ProjectionInfo Kalianpur1975UTMZone43N;
  private final ProjectionInfo Kalianpur1975UTMZone44N;
  private final ProjectionInfo Kalianpur1975UTMZone45N;
  private final ProjectionInfo Kalianpur1975UTMZone46N;
  private final ProjectionInfo Kalianpur1975UTMZone47N;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of IndianSubcontinent
  /// </summary>
  public NationalGridsIndia() {
    Kalianpur1880IndiaZone0 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.5 +lat_0=39.5 +lon_0=68 +k_0=0.99846154 +x_0=2153865.73916853 +y_0=2368292.194628102 +a=6377299.36 +b=6356098.35162804 +to_meter=0.9143985307444408 +no_defs ").orElse(null);
    Kalianpur1880IndiaZoneI = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.5 +lat_0=32.5 +lon_0=68 +k_0=0.99878641 +x_0=2743195.592233322 +y_0=914398.5307444407 +a=6377299.36 +b=6356098.35162804 +to_meter=0.9143985307444408 +no_defs ").orElse(null);
    Kalianpur1880IndiaZoneIIa = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26 +lat_0=26 +lon_0=74 +k_0=0.99878641 +x_0=2743195.592233322 +y_0=914398.5307444407 +a=6377299.36 +b=6356098.35162804 +to_meter=0.9143985307444408 +no_defs ").orElse(null);
    Kalianpur1880IndiaZoneIIb = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26 +lat_0=26 +lon_0=90 +k_0=0.99878641 +x_0=2743195.592233322 +y_0=914398.5307444407 +a=6377299.36 +b=6356098.35162804 +to_meter=0.9143985307444408 +no_defs ").orElse(null);
    Kalianpur1880IndiaZoneIII = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=19 +lat_0=19 +lon_0=80 +k_0=0.99878641 +x_0=2743195.592233322 +y_0=914398.5307444407 +a=6377299.36 +b=6356098.35162804 +to_meter=0.9143985307444408 +no_defs ").orElse(null);
    Kalianpur1880IndiaZoneIV = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=12 +lat_0=12 +lon_0=80 +k_0=0.99878641 +x_0=2743195.592233322 +y_0=914398.5307444407 +a=6377299.36 +b=6356098.35162804 +to_meter=0.9143985307444408 +no_defs ").orElse(null);
    Kalianpur1937IndiaZoneIIb = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26 +lat_0=26 +lon_0=90 +k_0=0.99878641 +x_0=2743195.5 +y_0=914398.5 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
    Kalianpur1937UTMZone45N = ProjectionInfo.fromProj4String("+proj=utm +zone=45 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
    Kalianpur1937UTMZone46N = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
    Kalianpur1962IndiaZoneI = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.5 +lat_0=32.5 +lon_0=68 +k_0=0.99878641 +x_0=2743196.4 +y_0=914398.8000000001 +a=6377301.243 +b=6356100.230165384 +units=m +no_defs ").orElse(null);
    Kalianpur1962IndiaZoneIIa = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26 +lat_0=26 +lon_0=74 +k_0=0.99878641 +x_0=2743196.4 +y_0=914398.8000000001 +a=6377301.243 +b=6356100.230165384 +units=m +no_defs ").orElse(null);
    Kalianpur1962UTMZone41N = ProjectionInfo.fromProj4String("+proj=utm +zone=41 +a=6377301.243 +b=6356100.230165384 +units=m +no_defs ").orElse(null);
    Kalianpur1962UTMZone42N = ProjectionInfo.fromProj4String("+proj=utm +zone=42 +a=6377301.243 +b=6356100.230165384 +units=m +no_defs ").orElse(null);
    Kalianpur1962UTMZone43N = ProjectionInfo.fromProj4String("+proj=utm +zone=43 +a=6377301.243 +b=6356100.230165384 +units=m +no_defs ").orElse(null);
    Kalianpur1975IndiaZoneI = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.5 +lat_0=32.5 +lon_0=68 +k_0=0.99878641 +x_0=2743185.69 +y_0=914395.23 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);
    Kalianpur1975IndiaZoneIIa = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26 +lat_0=26 +lon_0=74 +k_0=0.99878641 +x_0=2743185.69 +y_0=914395.23 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);
    Kalianpur1975IndiaZoneIIb = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26 +lat_0=26 +lon_0=90 +k_0=0.99878641 +x_0=2743185.69 +y_0=914395.23 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);
    Kalianpur1975IndiaZoneIII = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=19 +lat_0=19 +lon_0=80 +k_0=0.99878641 +x_0=2743185.69 +y_0=914395.23 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);
    Kalianpur1975IndiaZoneIV = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=12 +lat_0=12 +lon_0=80 +k_0=0.99878641 +x_0=2743185.69 +y_0=914395.23 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);
    Kalianpur1975UTMZone42N = ProjectionInfo.fromProj4String("+proj=utm +zone=42 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);
    Kalianpur1975UTMZone43N = ProjectionInfo.fromProj4String("+proj=utm +zone=43 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);
    Kalianpur1975UTMZone44N = ProjectionInfo.fromProj4String("+proj=utm +zone=44 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);
    Kalianpur1975UTMZone45N = ProjectionInfo.fromProj4String("+proj=utm +zone=45 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);
    Kalianpur1975UTMZone46N = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);
    Kalianpur1975UTMZone47N = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +a=6377299.151 +b=6356098.145120132 +units=m +no_defs ").orElse(null);

    Kalianpur1880IndiaZone0.setName("Kalianpur_1880_India_Zone_0");
    Kalianpur1880IndiaZoneI.setName("Kalianpur_1880_India_Zone_I");
    Kalianpur1880IndiaZoneIIa.setName("Kalianpur_1880_India_Zone_IIa");
    Kalianpur1880IndiaZoneIIb.setName("Kalianpur_1880_India_Zone_IIb");
    Kalianpur1880IndiaZoneIII.setName("Kalianpur_1880_India_Zone_III");
    Kalianpur1880IndiaZoneIV.setName("Kalianpur_1880_India_Zone_IV");
    Kalianpur1937IndiaZoneIIb.setName("Kalianpur_1937_India_Zone_IIb");
    Kalianpur1937UTMZone45N.setName("Kalianpur_1937_UTM_Zone_45N");
    Kalianpur1937UTMZone46N.setName("Kalianpur_1937_UTM_Zone_46N");
    Kalianpur1962IndiaZoneI.setName("Kalianpur_1962_India_Zone_I");
    Kalianpur1962IndiaZoneIIa.setName("Kalianpur_1962_India_Zone_IIa");
    Kalianpur1962UTMZone41N.setName("Kalianpur_1962_UTM_Zone_41N");
    Kalianpur1962UTMZone42N.setName("Kalianpur_1962_UTM_Zone_42N");
    Kalianpur1962UTMZone43N.setName("Kalianpur_1962_UTM_Zone_43N");
    Kalianpur1975IndiaZoneI.setName("Kalianpur_1975_India_Zone_I");
    Kalianpur1975IndiaZoneIIa.setName("Kalianpur_1975_India_Zone_IIa");
    Kalianpur1975IndiaZoneIIb.setName("Kalianpur_1975_India_Zone_IIb");
    Kalianpur1975IndiaZoneIII.setName("Kalianpur_1975_India_Zone_III");
    Kalianpur1975IndiaZoneIV.setName("Kalianpur_1975_India_Zone_IV");
    Kalianpur1975UTMZone42N.setName("Kalianpur_1975_UTM_Zone_42N");
    Kalianpur1975UTMZone43N.setName("Kalianpur_1975_UTM_Zone_43N");
    Kalianpur1975UTMZone44N.setName("Kalianpur_1975_UTM_Zone_44N");
    Kalianpur1975UTMZone45N.setName("Kalianpur_1975_UTM_Zone_45N");
    Kalianpur1975UTMZone46N.setName("Kalianpur_1975_UTM_Zone_46N");
    Kalianpur1975UTMZone47N.setName("Kalianpur_1975_UTM_Zone_47N");

    Kalianpur1880IndiaZone0.getGeographicInfo().setName("GCS_Kalianpur_1880");
    Kalianpur1880IndiaZoneI.getGeographicInfo().setName("GCS_Kalianpur_1880");
    Kalianpur1880IndiaZoneIIa.getGeographicInfo().setName("GCS_Kalianpur_1880");
    Kalianpur1880IndiaZoneIIb.getGeographicInfo().setName("GCS_Kalianpur_1880");
    Kalianpur1880IndiaZoneIII.getGeographicInfo().setName("GCS_Kalianpur_1880");
    Kalianpur1880IndiaZoneIV.getGeographicInfo().setName("GCS_Kalianpur_1880");
    Kalianpur1937IndiaZoneIIb.getGeographicInfo().setName("GCS_Kalianpur_1937");
    Kalianpur1937UTMZone45N.getGeographicInfo().setName("GCS_Kalianpur_1937");
    Kalianpur1937UTMZone46N.getGeographicInfo().setName("GCS_Kalianpur_1937");
    Kalianpur1962IndiaZoneI.getGeographicInfo().setName("GCS_Kalianpur_1962");
    Kalianpur1962IndiaZoneIIa.getGeographicInfo().setName("GCS_Kalianpur_1962");
    Kalianpur1962UTMZone41N.getGeographicInfo().setName("GCS_Kalianpur_1962");
    Kalianpur1962UTMZone42N.getGeographicInfo().setName("GCS_Kalianpur_1962");
    Kalianpur1962UTMZone43N.getGeographicInfo().setName("GCS_Kalianpur_1962");
    Kalianpur1975IndiaZoneI.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kalianpur1975IndiaZoneIIa.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kalianpur1975IndiaZoneIIb.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kalianpur1975IndiaZoneIII.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kalianpur1975IndiaZoneIV.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kalianpur1975UTMZone42N.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kalianpur1975UTMZone43N.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kalianpur1975UTMZone44N.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kalianpur1975UTMZone45N.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kalianpur1975UTMZone46N.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kalianpur1975UTMZone47N.getGeographicInfo().setName("GCS_Kalianpur_1975");

    Kalianpur1880IndiaZone0.getGeographicInfo().getDatum().setName("D_Kalianpur_1880");
    Kalianpur1880IndiaZoneI.getGeographicInfo().getDatum().setName("D_Kalianpur_1880");
    Kalianpur1880IndiaZoneIIa.getGeographicInfo().getDatum().setName("D_Kalianpur_1880");
    Kalianpur1880IndiaZoneIIb.getGeographicInfo().getDatum().setName("D_Kalianpur_1880");
    Kalianpur1880IndiaZoneIII.getGeographicInfo().getDatum().setName("D_Kalianpur_1880");
    Kalianpur1880IndiaZoneIV.getGeographicInfo().getDatum().setName("D_Kalianpur_1880");
    Kalianpur1937IndiaZoneIIb.getGeographicInfo().getDatum().setName("D_Kalianpur_1937");
    Kalianpur1937UTMZone45N.getGeographicInfo().getDatum().setName("D_Kalianpur_1937");
    Kalianpur1937UTMZone46N.getGeographicInfo().getDatum().setName("D_Kalianpur_1937");
    Kalianpur1962IndiaZoneI.getGeographicInfo().getDatum().setName("D_Kalianpur_1962");
    Kalianpur1962IndiaZoneIIa.getGeographicInfo().getDatum().setName("D_Kalianpur_1962");
    Kalianpur1962UTMZone41N.getGeographicInfo().getDatum().setName("D_Kalianpur_1962");
    Kalianpur1962UTMZone42N.getGeographicInfo().getDatum().setName("D_Kalianpur_1962");
    Kalianpur1962UTMZone43N.getGeographicInfo().getDatum().setName("D_Kalianpur_1962");
    Kalianpur1975IndiaZoneI.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kalianpur1975IndiaZoneIIa.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kalianpur1975IndiaZoneIIb.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kalianpur1975IndiaZoneIII.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kalianpur1975IndiaZoneIV.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kalianpur1975UTMZone42N.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kalianpur1975UTMZone43N.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kalianpur1975UTMZone44N.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kalianpur1975UTMZone45N.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kalianpur1975UTMZone46N.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kalianpur1975UTMZone47N.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the Kalianpur1880IndiaZone0
   */
  public ProjectionInfo getKalianpur1880IndiaZone0() {
    return Kalianpur1880IndiaZone0.copy();
  }

  /**
   * @return the Kalianpur1880IndiaZoneI
   */
  public ProjectionInfo getKalianpur1880IndiaZoneI() {
    return Kalianpur1880IndiaZoneI.copy();
  }

  /**
   * @return the Kalianpur1880IndiaZoneIII
   */
  public ProjectionInfo getKalianpur1880IndiaZoneIII() {
    return Kalianpur1880IndiaZoneIII.copy();
  }

  /**
   * @return the Kalianpur1880IndiaZoneIIa
   */
  public ProjectionInfo getKalianpur1880IndiaZoneIIa() {
    return Kalianpur1880IndiaZoneIIa.copy();
  }

  /**
   * @return the Kalianpur1880IndiaZoneIIb
   */
  public ProjectionInfo getKalianpur1880IndiaZoneIIb() {
    return Kalianpur1880IndiaZoneIIb.copy();
  }

  /**
   * @return the Kalianpur1880IndiaZoneIV
   */
  public ProjectionInfo getKalianpur1880IndiaZoneIV() {
    return Kalianpur1880IndiaZoneIV.copy();
  }

  /**
   * @return the Kalianpur1937IndiaZoneIIb
   */
  public ProjectionInfo getKalianpur1937IndiaZoneIIb() {
    return Kalianpur1937IndiaZoneIIb.copy();
  }

  /**
   * @return the Kalianpur1937UTMZone45N
   */
  public ProjectionInfo getKalianpur1937UTMZone45N() {
    return Kalianpur1937UTMZone45N.copy();
  }

  /**
   * @return the Kalianpur1937UTMZone46N
   */
  public ProjectionInfo getKalianpur1937UTMZone46N() {
    return Kalianpur1937UTMZone46N.copy();
  }

  /**
   * @return the Kalianpur1962IndiaZoneI
   */
  public ProjectionInfo getKalianpur1962IndiaZoneI() {
    return Kalianpur1962IndiaZoneI.copy();
  }

  /**
   * @return the Kalianpur1962IndiaZoneIIa
   */
  public ProjectionInfo getKalianpur1962IndiaZoneIIa() {
    return Kalianpur1962IndiaZoneIIa.copy();
  }

  /**
   * @return the Kalianpur1962UTMZone41N
   */
  public ProjectionInfo getKalianpur1962UTMZone41N() {
    return Kalianpur1962UTMZone41N.copy();
  }

  /**
   * @return the Kalianpur1962UTMZone42N
   */
  public ProjectionInfo getKalianpur1962UTMZone42N() {
    return Kalianpur1962UTMZone42N.copy();
  }

  /**
   * @return the Kalianpur1962UTMZone43N
   */
  public ProjectionInfo getKalianpur1962UTMZone43N() {
    return Kalianpur1962UTMZone43N.copy();
  }

  /**
   * @return the Kalianpur1975IndiaZoneI
   */
  public ProjectionInfo getKalianpur1975IndiaZoneI() {
    return Kalianpur1975IndiaZoneI.copy();
  }

  /**
   * @return the Kalianpur1975IndiaZoneIII
   */
  public ProjectionInfo getKalianpur1975IndiaZoneIII() {
    return Kalianpur1975IndiaZoneIII.copy();
  }

  /**
   * @return the Kalianpur1975IndiaZoneIIa
   */
  public ProjectionInfo getKalianpur1975IndiaZoneIIa() {
    return Kalianpur1975IndiaZoneIIa.copy();
  }

  /**
   * @return the Kalianpur1975IndiaZoneIIb
   */
  public ProjectionInfo getKalianpur1975IndiaZoneIIb() {
    return Kalianpur1975IndiaZoneIIb.copy();
  }

  /**
   * @return the Kalianpur1975IndiaZoneIV
   */
  public ProjectionInfo getKalianpur1975IndiaZoneIV() {
    return Kalianpur1975IndiaZoneIV.copy();
  }

  /**
   * @return the Kalianpur1975UTMZone42N
   */
  public ProjectionInfo getKalianpur1975UTMZone42N() {
    return Kalianpur1975UTMZone42N.copy();
  }

  /**
   * @return the Kalianpur1975UTMZone43N
   */
  public ProjectionInfo getKalianpur1975UTMZone43N() {
    return Kalianpur1975UTMZone43N.copy();
  }

  /**
   * @return the Kalianpur1975UTMZone44N
   */
  public ProjectionInfo getKalianpur1975UTMZone44N() {
    return Kalianpur1975UTMZone44N.copy();
  }

  /**
   * @return the Kalianpur1975UTMZone45N
   */
  public ProjectionInfo getKalianpur1975UTMZone45N() {
    return Kalianpur1975UTMZone45N.copy();
  }

  /**
   * @return the Kalianpur1975UTMZone46N
   */
  public ProjectionInfo getKalianpur1975UTMZone46N() {
    return Kalianpur1975UTMZone46N.copy();
  }

  /**
   * @return the Kalianpur1975UTMZone47N
   */
  public ProjectionInfo getKalianpur1975UTMZone47N() {
    return Kalianpur1975UTMZone47N.copy();
  }

  //</editor-fold>
}
