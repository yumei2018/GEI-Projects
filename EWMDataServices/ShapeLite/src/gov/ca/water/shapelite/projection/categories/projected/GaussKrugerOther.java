
package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
/// <summary>
/// GaussKrugerOther
/// </summary>

public class GaussKrugerOther extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo Albanian1987GKZone4;
  private final ProjectionInfo ED19503DegreeGKZone10;
  private final ProjectionInfo ED19503DegreeGKZone11;
  private final ProjectionInfo ED19503DegreeGKZone12;
  private final ProjectionInfo ED19503DegreeGKZone13;
  private final ProjectionInfo ED19503DegreeGKZone14;
  private final ProjectionInfo ED19503DegreeGKZone15;
  private final ProjectionInfo ED19503DegreeGKZone9;
  private final ProjectionInfo Hanoi1972GKZone18;
  private final ProjectionInfo Hanoi1972GKZone19;
  private final ProjectionInfo Pulkovo1942Adj19833DegreeGKZone3;
  private final ProjectionInfo Pulkovo1942Adj19833DegreeGKZone4;
  private final ProjectionInfo Pulkovo1942Adj19833DegreeGKZone5;
  private final ProjectionInfo SouthYemenGKZone8;
  private final ProjectionInfo SouthYemenGKZone9;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of GaussKrugerOther.
   */
  public GaussKrugerOther() {
    Albanian1987GKZone4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=21 +k=1.000000 +x_0=4500000 +y_0=0 +ellps=krass +units=m +no_defs ").orElse(null);
    ED19503DegreeGKZone10 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=30 +k=1.000000 +x_0=10500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
    ED19503DegreeGKZone11 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=33 +k=1.000000 +x_0=11500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
    ED19503DegreeGKZone12 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=36 +k=1.000000 +x_0=12500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
    ED19503DegreeGKZone13 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=39 +k=1.000000 +x_0=13500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
    ED19503DegreeGKZone14 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=42 +k=1.000000 +x_0=14500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
    ED19503DegreeGKZone15 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=45 +k=1.000000 +x_0=15500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
    ED19503DegreeGKZone9 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=27 +k=1.000000 +x_0=9500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
    Hanoi1972GKZone18 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=105 +k=1.000000 +x_0=18500000 +y_0=0 +ellps=krass +units=m +no_defs ").orElse(null);
    Hanoi1972GKZone19 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=111 +k=1.000000 +x_0=19500000 +y_0=0 +ellps=krass +units=m +no_defs ").orElse(null);
    Pulkovo1942Adj19833DegreeGKZone3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=9 +k=1.000000 +x_0=3500000 +y_0=0 +ellps=krass +units=m +no_defs ").orElse(null);
    Pulkovo1942Adj19833DegreeGKZone4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=12 +k=1.000000 +x_0=4500000 +y_0=0 +ellps=krass +units=m +no_defs ").orElse(null);
    Pulkovo1942Adj19833DegreeGKZone5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=1.000000 +x_0=5500000 +y_0=0 +ellps=krass +units=m +no_defs ").orElse(null);
    SouthYemenGKZone8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=45 +k=1.000000 +x_0=8500000 +y_0=0 +ellps=krass +units=m +no_defs ").orElse(null);
    SouthYemenGKZone9 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=51 +k=1.000000 +x_0=9500000 +y_0=0 +ellps=krass +units=m +no_defs ").orElse(null);

    Albanian1987GKZone4.setName("Albanian_1987_GK_Zone_4");
    ED19503DegreeGKZone10.setName("ED_1950_3_Degree_GK_Zone_10");
    ED19503DegreeGKZone11.setName("ED_1950_3_Degree_GK_Zone_11");
    ED19503DegreeGKZone12.setName("ED_1950_3_Degree_GK_Zone_12");
    ED19503DegreeGKZone13.setName("ED_1950_3_Degree_GK_Zone_13");
    ED19503DegreeGKZone14.setName("ED_1950_3_Degree_GK_Zone_14");
    ED19503DegreeGKZone15.setName("ED_1950_3_Degree_GK_Zone_15");
    ED19503DegreeGKZone9.setName("ED_1950_3_Degree_GK_Zone_9");
    Hanoi1972GKZone18.setName("Hanoi_1972_GK_Zone_18");
    Hanoi1972GKZone19.setName("Hanoi_1972_GK_Zone_19");
    Pulkovo1942Adj19833DegreeGKZone3.setName("Pulkovo_1942_Adj_1983_3_Degree_GK_Zone_3");
    Pulkovo1942Adj19833DegreeGKZone4.setName("Pulkovo_1942_Adj_1983_3_Degree_GK_Zone_4");
    Pulkovo1942Adj19833DegreeGKZone5.setName("Pulkovo_1942_Adj_1983_3_Degree_GK_Zone_5");
    SouthYemenGKZone8.setName("South_Yemen_GK_Zone_8");
    SouthYemenGKZone9.setName("South_Yemen_GK_Zone_9");

    Albanian1987GKZone4.getGeographicInfo().setName("GCS_Albanian_1987");
    ED19503DegreeGKZone10.getGeographicInfo().setName("GCS_European_1950");
    ED19503DegreeGKZone11.getGeographicInfo().setName("GCS_European_1950");
    ED19503DegreeGKZone12.getGeographicInfo().setName("GCS_European_1950");
    ED19503DegreeGKZone13.getGeographicInfo().setName("GCS_European_1950");
    ED19503DegreeGKZone14.getGeographicInfo().setName("GCS_European_1950");
    ED19503DegreeGKZone15.getGeographicInfo().setName("GCS_European_1950");
    ED19503DegreeGKZone9.getGeographicInfo().setName("GCS_European_1950");
    Hanoi1972GKZone18.getGeographicInfo().setName("GCS_Hanoi_1972");
    Hanoi1972GKZone19.getGeographicInfo().setName("GCS_Hanoi_1972");
    Pulkovo1942Adj19833DegreeGKZone3.getGeographicInfo().setName("GCS_Pulkovo_1942_Adj_1983");
    Pulkovo1942Adj19833DegreeGKZone4.getGeographicInfo().setName("GCS_Pulkovo_1942_Adj_1983");
    Pulkovo1942Adj19833DegreeGKZone5.getGeographicInfo().setName("GCS_Pulkovo_1942_Adj_1983");
    SouthYemenGKZone8.getGeographicInfo().setName("GCS_South_Yemen");
    SouthYemenGKZone9.getGeographicInfo().setName("GCS_South_Yemen");

    Albanian1987GKZone4.getGeographicInfo().getDatum().setName("D_Albanian_1987");
    ED19503DegreeGKZone10.getGeographicInfo().getDatum().setName("D_European_1950");
    ED19503DegreeGKZone11.getGeographicInfo().getDatum().setName("D_European_1950");
    ED19503DegreeGKZone12.getGeographicInfo().getDatum().setName("D_European_1950");
    ED19503DegreeGKZone13.getGeographicInfo().getDatum().setName("D_European_1950");
    ED19503DegreeGKZone14.getGeographicInfo().getDatum().setName("D_European_1950");
    ED19503DegreeGKZone15.getGeographicInfo().getDatum().setName("D_European_1950");
    ED19503DegreeGKZone9.getGeographicInfo().getDatum().setName("D_European_1950");
    Hanoi1972GKZone18.getGeographicInfo().getDatum().setName("D_Hanoi_1972");
    Hanoi1972GKZone19.getGeographicInfo().getDatum().setName("D_Hanoi_1972");
    Pulkovo1942Adj19833DegreeGKZone3.getGeographicInfo().getDatum().setName("D_Pulkovo_1942_Adj_1983");
    Pulkovo1942Adj19833DegreeGKZone4.getGeographicInfo().getDatum().setName("D_Pulkovo_1942_Adj_1983");
    Pulkovo1942Adj19833DegreeGKZone5.getGeographicInfo().getDatum().setName("D_Pulkovo_1942_Adj_1983");
    SouthYemenGKZone8.getGeographicInfo().getDatum().setName("D_South_Yemen");
    SouthYemenGKZone9.getGeographicInfo().getDatum().setName("D_South_Yemen");
  }

  //</editor-fold>
  /**
   * @return the Albanian1987GKZone4
   */
  public ProjectionInfo getAlbanian1987GKZone4() {
    return Albanian1987GKZone4.copy();
  }

  /**
   * @return the ED19503DegreeGKZone10
   */
  public ProjectionInfo getED19503DegreeGKZone10() {
    return ED19503DegreeGKZone10.copy();
  }

  /**
   * @return the ED19503DegreeGKZone11
   */
  public ProjectionInfo getED19503DegreeGKZone11() {
    return ED19503DegreeGKZone11.copy();
  }

  /**
   * @return the ED19503DegreeGKZone12
   */
  public ProjectionInfo getED19503DegreeGKZone12() {
    return ED19503DegreeGKZone12.copy();
  }

  /**
   * @return the ED19503DegreeGKZone13
   */
  public ProjectionInfo getED19503DegreeGKZone13() {
    return ED19503DegreeGKZone13.copy();
  }

  /**
   * @return the ED19503DegreeGKZone14
   */
  public ProjectionInfo getED19503DegreeGKZone14() {
    return ED19503DegreeGKZone14.copy();
  }

  /**
   * @return the ED19503DegreeGKZone15
   */
  public ProjectionInfo getED19503DegreeGKZone15() {
    return ED19503DegreeGKZone15.copy();
  }

  /**
   * @return the ED19503DegreeGKZone9
   */
  public ProjectionInfo getED19503DegreeGKZone9() {
    return ED19503DegreeGKZone9.copy();
  }

  /**
   * @return the Hanoi1972GKZone18
   */
  public ProjectionInfo getHanoi1972GKZone18() {
    return Hanoi1972GKZone18.copy();
  }

  /**
   * @return the Hanoi1972GKZone19
   */
  public ProjectionInfo getHanoi1972GKZone19() {
    return Hanoi1972GKZone19.copy();
  }

  /**
   * @return the Pulkovo1942Adj19833DegreeGKZone3
   */
  public ProjectionInfo getPulkovo1942Adj19833DegreeGKZone3() {
    return Pulkovo1942Adj19833DegreeGKZone3.copy();
  }

  /**
   * @return the Pulkovo1942Adj19833DegreeGKZone4
   */
  public ProjectionInfo getPulkovo1942Adj19833DegreeGKZone4() {
    return Pulkovo1942Adj19833DegreeGKZone4.copy();
  }

  /**
   * @return the Pulkovo1942Adj19833DegreeGKZone5
   */
  public ProjectionInfo getPulkovo1942Adj19833DegreeGKZone5() {
    return Pulkovo1942Adj19833DegreeGKZone5.copy();
  }

  /**
   * @return the SouthYemenGKZone8
   */
  public ProjectionInfo getSouthYemenGKZone8() {
    return SouthYemenGKZone8.copy();
  }

  /**
   * @return the SouthYemenGKZone9
   */
  public ProjectionInfo getSouthYemenGKZone9() {
    return SouthYemenGKZone9.copy();
  }

}
