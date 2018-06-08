package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

public class Africa extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo AfricaAlbersEqualAreaConic;
  private final ProjectionInfo AfricaEquidistantConic;
  private final ProjectionInfo AfricaLambertConformalConic;
  private final ProjectionInfo AfricaSinusoidal;

        //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of Africa
   */
  public Africa() {
    AfricaAlbersEqualAreaConic = ProjectionInfo.fromProj4String("+proj=aea +lat_1=20 +lat_2=-23 +lat_0=0 +lon_0=25 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    AfricaEquidistantConic = ProjectionInfo.fromProj4String("+proj=eqdc +lat_0=0 +lon_0=0 +lat_1=20 +lat_2=-23 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    AfricaLambertConformalConic = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=20 +lat_2=-23 +lat_0=0 +lon_0=25 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    AfricaSinusoidal = ProjectionInfo.fromProj4String("+proj=sinu +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);

    AfricaAlbersEqualAreaConic.setName("Africa_Albers_Equal_Area_Conic");
    AfricaEquidistantConic.setName("Africa_Equidistant_Conic");
    AfricaLambertConformalConic.setName("Africa_Lambert_Conformal_Conic");
    AfricaSinusoidal.setName("Africa_Sinusoidal");

    AfricaAlbersEqualAreaConic.getGeographicInfo().setName("GCS_WGS_1984");
    AfricaEquidistantConic.getGeographicInfo().setName("GCS_WGS_1984");
    AfricaLambertConformalConic.getGeographicInfo().setName("GCS_WGS_1984");
    AfricaSinusoidal.getGeographicInfo().setName("GCS_WGS_1984");

    AfricaAlbersEqualAreaConic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    AfricaEquidistantConic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    AfricaLambertConformalConic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    AfricaSinusoidal.getGeographicInfo().getDatum().setName("D_WGS_1984");
  }

  //</editor-fold>

  /**
   * @return the AfricaAlbersEqualAreaConic
   */
  public ProjectionInfo getAfricaAlbersEqualAreaConic() {
    return AfricaAlbersEqualAreaConic.copy();
  }

  /**
   * @return the AfricaEquidistantConic
   */
  public ProjectionInfo getAfricaEquidistantConic() {
    return AfricaEquidistantConic.copy();
  }

  /**
   * @return the AfricaLambertConformalConic
   */
  public ProjectionInfo getAfricaLambertConformalConic() {
    return AfricaLambertConformalConic.copy();
  }

  /**
   * @return the AfricaSinusoidal
   */
  public ProjectionInfo getAfricaSinusoidal() {
    return AfricaSinusoidal.copy();
  }
}
