package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 * SouthAmerica.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class SouthAmerica extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo SouthAmericaAlbersEqualAreaConic;
  private final ProjectionInfo SouthAmericaEquidistantConic;
  private final ProjectionInfo SouthAmericaLambertConformalConic;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of SouthAmerica
  /// </summary>
  public SouthAmerica() {
    SouthAmericaAlbersEqualAreaConic = ProjectionInfo.fromProj4String("+proj=aea +lat_1=-5 +lat_2=-42 +lat_0=-32 +lon_0=-60 +x_0=0 +y_0=0 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    SouthAmericaEquidistantConic = ProjectionInfo.fromProj4String("+proj=eqdc +lat_0=0 +lon_0=0 +lat_1=-5 +lat_2=-42 +x_0=0 +y_0=0 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    SouthAmericaLambertConformalConic = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=-5 +lat_2=-42 +lat_0=-32 +lon_0=-60 +x_0=0 +y_0=0 +ellps=aust_SA +units=m +no_defs ").orElse(null);

    SouthAmericaAlbersEqualAreaConic.setName("South_America_Albers_Equal_Area_Conic");
    SouthAmericaEquidistantConic.setName("South_America_Equidistant_Conic");
    SouthAmericaLambertConformalConic.setName("South_America_Lambert_Conformal_Conic");

    SouthAmericaAlbersEqualAreaConic.getGeographicInfo().setName("GCS_South_American_1969");
    SouthAmericaEquidistantConic.getGeographicInfo().setName("GCS_South_American_1969");
    SouthAmericaLambertConformalConic.getGeographicInfo().setName("GCS_South_American_1969");

    SouthAmericaAlbersEqualAreaConic.getGeographicInfo().getDatum().setName("D_South_American_1969");
    SouthAmericaEquidistantConic.getGeographicInfo().getDatum().setName("D_South_American_1969");
    SouthAmericaLambertConformalConic.getGeographicInfo().getDatum().setName("D_South_American_1969");
  }

  //</editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the SouthAmericaAlbersEqualAreaConic
   */
  public final ProjectionInfo getSouthAmericaAlbersEqualAreaConic() {
    return SouthAmericaAlbersEqualAreaConic;
  }

  /**
   * @return the SouthAmericaEquidistantConic
   */
  public final ProjectionInfo getSouthAmericaEquidistantConic() {
    return SouthAmericaEquidistantConic;
  }

  /**
   * @return the SouthAmericaLambertConformalConic
   */
  public final ProjectionInfo getSouthAmericaLambertConformalConic() {
    return SouthAmericaLambertConformalConic;
  }

  // </editor-fold>
}
