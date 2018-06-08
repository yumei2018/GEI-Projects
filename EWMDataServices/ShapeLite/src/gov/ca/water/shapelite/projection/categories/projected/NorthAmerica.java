package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
/// <summary>
/// NorthAmerica
/// </summary>

public class NorthAmerica extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo AlaskaAlbersEqualAreaConic;
  private final ProjectionInfo CanadaAlbersEqualAreaConic;
  private final ProjectionInfo CanadaLambertConformalConic;
  private final ProjectionInfo HawaiiAlbersEqualAreaConic;
  private final ProjectionInfo NorthAmericaAlbersEqualAreaConic;
  private final ProjectionInfo NorthAmericaEquidistantConic;
  private final ProjectionInfo NorthAmericaLambertConformalConic;
  private final ProjectionInfo USAContiguousAlbersEqualAreaConic;
  private final ProjectionInfo USAContiguousAlbersEqualAreaConicUSGS;
  private final ProjectionInfo USAContiguousEquidistantConic;
  private final ProjectionInfo USAContiguousLambertConformalConic;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of NorthAmerica
  /// </summary>
  public NorthAmerica() {
    AlaskaAlbersEqualAreaConic = ProjectionInfo.fromProj4String("+proj=aea +lat_1=55 +lat_2=65 +lat_0=50 +lon_0=-154 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    CanadaAlbersEqualAreaConic = ProjectionInfo.fromProj4String("+proj=aea +lat_1=50 +lat_2=70 +lat_0=40 +lon_0=-96 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    CanadaLambertConformalConic = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=50 +lat_2=70 +lat_0=40 +lon_0=-96 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    HawaiiAlbersEqualAreaConic = ProjectionInfo.fromProj4String("+proj=aea +lat_1=8 +lat_2=18 +lat_0=13 +lon_0=-157 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NorthAmericaAlbersEqualAreaConic = ProjectionInfo.fromProj4String("+proj=aea +lat_1=20 +lat_2=60 +lat_0=40 +lon_0=-96 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NorthAmericaEquidistantConic = ProjectionInfo.fromProj4String("+proj=eqdc +lat_0=0 +lon_0=0 +lat_1=20 +lat_2=60 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NorthAmericaLambertConformalConic = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=20 +lat_2=60 +lat_0=40 +lon_0=-96 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    USAContiguousAlbersEqualAreaConic = ProjectionInfo.fromProj4String("+proj=aea +lat_1=29.5 +lat_2=45.5 +lat_0=37.5 +lon_0=-96 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    USAContiguousAlbersEqualAreaConicUSGS = ProjectionInfo.fromProj4String("+proj=aea +lat_1=29.5 +lat_2=45.5 +lat_0=23 +lon_0=-96 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    USAContiguousEquidistantConic = ProjectionInfo.fromProj4String("+proj=eqdc +lat_0=0 +lon_0=0 +lat_1=33 +lat_2=45 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    USAContiguousLambertConformalConic = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33 +lat_2=45 +lat_0=39 +lon_0=-96 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);

    AlaskaAlbersEqualAreaConic.setName("Alaska_Albers_Equal_Area_Conic");
    CanadaAlbersEqualAreaConic.setName("Canada_Albers_Equal_Area_Conic");
    CanadaLambertConformalConic.setName("Canada_Lambert_Conformal_Conic");
    HawaiiAlbersEqualAreaConic.setName("Hawaii_Albers_Equal_Area_Conic");
    NorthAmericaAlbersEqualAreaConic.setName("North_America_Albers_Equal_Area_Conic");
    NorthAmericaEquidistantConic.setName("North_America_Equidistant_Conic");
    NorthAmericaLambertConformalConic.setName("North_America_Lambert_Conformal_Conic");
    USAContiguousAlbersEqualAreaConic.setName("USA_Contiguous_Albers_Equal_Area_Conic");
    USAContiguousAlbersEqualAreaConicUSGS.setName("USA_Contiguous_Albers_Equal_Area_Conic_USGS_version");
    USAContiguousEquidistantConic.setName("USA_Contiguous_Equidistant_Conic");
    USAContiguousLambertConformalConic.setName("USA_Contiguous_Lambert_Conformal_Conic");

    AlaskaAlbersEqualAreaConic.getGeographicInfo().setName("GCS_North_American_1983");
    CanadaAlbersEqualAreaConic.getGeographicInfo().setName("GCS_North_American_1983");
    CanadaLambertConformalConic.getGeographicInfo().setName("GCS_North_American_1983");
    HawaiiAlbersEqualAreaConic.getGeographicInfo().setName("GCS_North_American_1983");
    NorthAmericaAlbersEqualAreaConic.getGeographicInfo().setName("GCS_North_American_1983");
    NorthAmericaEquidistantConic.getGeographicInfo().setName("GCS_North_American_1983");
    NorthAmericaLambertConformalConic.getGeographicInfo().setName("GCS_North_American_1983");
    USAContiguousAlbersEqualAreaConic.getGeographicInfo().setName("GCS_North_American_1983");
    USAContiguousAlbersEqualAreaConicUSGS.getGeographicInfo().setName("GCS_North_American_1983");
    USAContiguousEquidistantConic.getGeographicInfo().setName("GCS_North_American_1983");
    USAContiguousLambertConformalConic.getGeographicInfo().setName("GCS_North_American_1983");

    AlaskaAlbersEqualAreaConic.getGeographicInfo().getDatum().setName("D_North_American_1983");
    CanadaAlbersEqualAreaConic.getGeographicInfo().getDatum().setName("D_North_American_1983");
    CanadaLambertConformalConic.getGeographicInfo().getDatum().setName("D_North_American_1983");
    HawaiiAlbersEqualAreaConic.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NorthAmericaAlbersEqualAreaConic.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NorthAmericaEquidistantConic.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NorthAmericaLambertConformalConic.getGeographicInfo().getDatum().setName("D_North_American_1983");
    USAContiguousAlbersEqualAreaConic.getGeographicInfo().getDatum().setName("D_North_American_1983");
    USAContiguousAlbersEqualAreaConicUSGS.getGeographicInfo().getDatum().setName("D_North_American_1983");
    USAContiguousEquidistantConic.getGeographicInfo().getDatum().setName("D_North_American_1983");
    USAContiguousLambertConformalConic.getGeographicInfo().getDatum().setName("D_North_American_1983");
  }

  //</editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the AlaskaAlbersEqualAreaConic
   */
  public ProjectionInfo getAlaskaAlbersEqualAreaConic() {
    return AlaskaAlbersEqualAreaConic.copy();
  }

  /**
   * @return the CanadaAlbersEqualAreaConic
   */
  public ProjectionInfo getCanadaAlbersEqualAreaConic() {
    return CanadaAlbersEqualAreaConic.copy();
  }

  /**
   * @return the CanadaLambertConformalConic
   */
  public ProjectionInfo getCanadaLambertConformalConic() {
    return CanadaLambertConformalConic.copy();
  }

  /**
   * @return the HawaiiAlbersEqualAreaConic
   */
  public ProjectionInfo getHawaiiAlbersEqualAreaConic() {
    return HawaiiAlbersEqualAreaConic.copy();
  }

  /**
   * @return the NorthAmericaAlbersEqualAreaConic
   */
  public ProjectionInfo getNorthAmericaAlbersEqualAreaConic() {
    return NorthAmericaAlbersEqualAreaConic.copy();
  }

  /**
   * @return the NorthAmericaEquidistantConic
   */
  public ProjectionInfo getNorthAmericaEquidistantConic() {
    return NorthAmericaEquidistantConic.copy();
  }

  /**
   * @return the NorthAmericaLambertConformalConic
   */
  public ProjectionInfo getNorthAmericaLambertConformalConic() {
    return NorthAmericaLambertConformalConic.copy();
  }

  /**
   * @return the USAContiguousAlbersEqualAreaConic
   */
  public ProjectionInfo getUSAContiguousAlbersEqualAreaConic() {
    return USAContiguousAlbersEqualAreaConic.copy();
  }

  /**
   * @return the USAContiguousAlbersEqualAreaConicUSGS
   */
  public ProjectionInfo getUSAContiguousAlbersEqualAreaConicUSGS() {
    return USAContiguousAlbersEqualAreaConicUSGS.copy();
  }

  /**
   * @return the USAContiguousEquidistantConic
   */
  public ProjectionInfo getUSAContiguousEquidistantConic() {
    return USAContiguousEquidistantConic.copy();
  }

  /**
   * @return the USAContiguousLambertConformalConic
   */
  public ProjectionInfo getUSAContiguousLambertConformalConic() {
    return USAContiguousLambertConformalConic.copy();
  }

  // </editor-fold>
}
