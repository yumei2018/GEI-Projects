package gov.ca.water.shapelite.projection.categories.projected;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
    /// <summary>
/// Asia
/// </summary>

public class Asia extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo AsiaLambertConformalConic;
  private final ProjectionInfo AsiaNorthAlbersEqualAreaConic;
  private final ProjectionInfo AsiaNorthEquidistantConic;
  private final ProjectionInfo AsiaNorthLambertConformalConic;
  private final ProjectionInfo AsiaSouthAlbersEqualAreaConic;
  private final ProjectionInfo AsiaSouthEquidistantConic;
  private final ProjectionInfo AsiaSouthLambertConformalConic;

        //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of Asia.
   */
  public Asia() {
    AsiaLambertConformalConic = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=30 +lat_2=62 +lat_0=0 +lon_0=105 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    AsiaNorthAlbersEqualAreaConic = ProjectionInfo.fromProj4String("+proj=aea +lat_1=15 +lat_2=65 +lat_0=30 +lon_0=95 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    AsiaNorthEquidistantConic = ProjectionInfo.fromProj4String("+proj=eqdc +lat_0=0 +lon_0=0 +lat_1=15 +lat_2=65 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    AsiaNorthLambertConformalConic = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=15 +lat_2=65 +lat_0=30 +lon_0=95 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    AsiaSouthAlbersEqualAreaConic = ProjectionInfo.fromProj4String("+proj=aea +lat_1=7 +lat_2=-32 +lat_0=-15 +lon_0=125 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    AsiaSouthEquidistantConic = ProjectionInfo.fromProj4String("+proj=eqdc +lat_0=0 +lon_0=0 +lat_1=7 +lat_2=-32 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    AsiaSouthLambertConformalConic = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=7 +lat_2=-32 +lat_0=-15 +lon_0=125 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);

    AsiaLambertConformalConic.setName("Asia_Lambert_Conformal_Conic");
    AsiaNorthAlbersEqualAreaConic.setName("Asia_North_Albers_Equal_Area_Conic");
    AsiaNorthEquidistantConic.setName("Asia_North_Equidistant_Conic");
    AsiaNorthLambertConformalConic.setName("Asia_North_Lambert_Conformal_Conic");
    AsiaSouthAlbersEqualAreaConic.setName("Asia_South_Albers_Equal_Area_Conic");
    AsiaSouthEquidistantConic.setName("Asia_South_Equidistant_Conic");
    AsiaSouthLambertConformalConic.setName("Asia_South_Lambert_Conformal_Conic");

    AsiaLambertConformalConic.getGeographicInfo().setName("GCS_WGS_1984");
    AsiaNorthAlbersEqualAreaConic.getGeographicInfo().setName("GCS_WGS_1984");
    AsiaNorthEquidistantConic.getGeographicInfo().setName("GCS_WGS_1984");
    AsiaNorthLambertConformalConic.getGeographicInfo().setName("GCS_WGS_1984");
    AsiaSouthAlbersEqualAreaConic.getGeographicInfo().setName("GCS_WGS_1984");
    AsiaSouthEquidistantConic.getGeographicInfo().setName("GCS_WGS_1984");
    AsiaSouthLambertConformalConic.getGeographicInfo().setName("GCS_WGS_1984");

    AsiaLambertConformalConic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    AsiaNorthAlbersEqualAreaConic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    AsiaNorthEquidistantConic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    AsiaNorthLambertConformalConic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    AsiaSouthAlbersEqualAreaConic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    AsiaSouthEquidistantConic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    AsiaSouthLambertConformalConic.getGeographicInfo().getDatum().setName("D_WGS_1984");
  }

  //</editor-fold>

  /**
   * @return the AsiaLambertConformalConic
   */
  public ProjectionInfo getAsiaLambertConformalConic() {
    return AsiaLambertConformalConic.copy();
  }

  /**
   * @return the AsiaNorthAlbersEqualAreaConic
   */
  public ProjectionInfo getAsiaNorthAlbersEqualAreaConic() {
    return AsiaNorthAlbersEqualAreaConic.copy();
  }

  /**
   * @return the AsiaNorthEquidistantConic
   */
  public ProjectionInfo getAsiaNorthEquidistantConic() {
    return AsiaNorthEquidistantConic.copy();
  }

  /**
   * @return the AsiaNorthLambertConformalConic
   */
  public ProjectionInfo getAsiaNorthLambertConformalConic() {
    return AsiaNorthLambertConformalConic.copy();
  }

  /**
   * @return the AsiaSouthAlbersEqualAreaConic
   */
  public ProjectionInfo getAsiaSouthAlbersEqualAreaConic() {
    return AsiaSouthAlbersEqualAreaConic.copy();
  }

  /**
   * @return the AsiaSouthEquidistantConic
   */
  public ProjectionInfo getAsiaSouthEquidistantConic() {
    return AsiaSouthEquidistantConic.copy();
  }

  /**
   * @return the AsiaSouthLambertConformalConic
   */
  public ProjectionInfo getAsiaSouthLambertConformalConic() {
    return AsiaSouthLambertConformalConic.copy();
  }
}
