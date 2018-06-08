package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
import java.util.logging.Logger;
    /// <summary>
/// Europe
/// </summary>

public class Europe extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private static final Logger LOGGER = Logger.getLogger(Europe.class.getName());

  private ProjectionInfo EMEP150KilometerGrid;
  private ProjectionInfo EMEP50KilometerGrid;
  private final ProjectionInfo ETRS1989LAEA;
  private final ProjectionInfo ETRS1989LCC;
  private final ProjectionInfo EuropeAlbersEqualAreaConic;
  private final ProjectionInfo EuropeEquidistantConic;
  private final ProjectionInfo EuropeLambertConformalConic;

        //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   *  Creates a new instance of Europe
   */
  public Europe() {

    EMEP150KilometerGrid = ProjectionInfo.fromEsriString("PROJCS[\"EMEP_150_Kilometer_Grid\", GEOGCS[\"GCS_Sphere_EMEP\", DATUM[\"D_Sphere_EMEP\", SPHEROID[\"Sphere_EMEP\", 6370000.0, 0.0]], PRIMEM[\"Greenwich\", 0.0], UNIT[\"Degree\", 0.0174532925199433]], PROJECTION[\"Stereographic_North_Pole\"], PARAMETER[\"False_Easting\", 3.0], PARAMETER[\"False_Northing\", 37.0], PARAMETER[\"Central_Meridian\", -32.0], PARAMETER[\"Standard_Parallel_1\", 60.0], UNIT[\"150_Kilometers\", 150000.0]]");
    EMEP50KilometerGrid = ProjectionInfo.fromEsriString("PROJCS[\"EMEP_50_Kilometer_Grid\", GEOGCS[\"GCS_Sphere_EMEP\", DATUM[\"D_Sphere_EMEP\", SPHEROID[\"Sphere_EMEP\", 6370000.0, 0.0]], PRIMEM[\"Greenwich\", 0.0], UNIT[\"Degree\", 0.0174532925199433]], PROJECTION[\"Stereographic_North_Pole\"], PARAMETER[\"False_Easting\", 8.0], PARAMETER[\"False_Northing\", 110.0], PARAMETER[\"Central_Meridian\", -32.0], PARAMETER[\"Standard_Parallel_1\", 60.0], UNIT[\"50_Kilometers\", 50000.0]]");
    EMEP50KilometerGrid.setName("EMEP_50_Kilometer_Grid");
    EMEP150KilometerGrid.getGeographicInfo().setName("GCS_Sphere_EMEP");
    EMEP50KilometerGrid.getGeographicInfo().setName("GCS_Sphere_EMEP");
    EMEP150KilometerGrid.getGeographicInfo().getDatum().setName("D_Sphere_EMEP");
    EMEP50KilometerGrid.getGeographicInfo().getDatum().setName("D_Sphere_EMEP");

    ETRS1989LAEA = ProjectionInfo.fromProj4String("+proj=laea +lat_0=52 +lon_0=10 +x_0=4321000 +y_0=3210000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    ETRS1989LCC = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=35 +lat_2=65 +lat_0=52 +lon_0=10 +x_0=4000000 +y_0=2800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    EuropeAlbersEqualAreaConic = ProjectionInfo.fromProj4String("+proj=aea +lat_1=43 +lat_2=62 +lat_0=30 +lon_0=10 +x_0=0 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
    EuropeEquidistantConic = ProjectionInfo.fromProj4String("+proj=eqdc +lat_0=0 +lon_0=0 +lat_1=43 +lat_2=62 +x_0=0 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
    EuropeLambertConformalConic = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43 +lat_2=62 +lat_0=30 +lon_0=10 +x_0=0 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);

    ETRS1989LAEA.setName("ETRS_1989_LAEA");
    ETRS1989LCC.setName("ETRS_1989_LCC");
    EuropeAlbersEqualAreaConic.setName("Europe_Albers_Equal_Area_Conic");
    EuropeEquidistantConic.setName("Europe_Equidistant_Conic");
    EuropeLambertConformalConic.setName("Europe_Lambert_Conformal_Conic");

    ETRS1989LAEA.getGeographicInfo().setName("GCS_ETRS_1989");
    ETRS1989LCC.getGeographicInfo().setName("GCS_ETRS_1989");
    EuropeAlbersEqualAreaConic.getGeographicInfo().setName("GCS_European_1950");
    EuropeEquidistantConic.getGeographicInfo().setName("GCS_European_1950");
    EuropeLambertConformalConic.getGeographicInfo().setName("GCS_European_1950");

    ETRS1989LAEA.getGeographicInfo().getDatum().setName("D_ETRS_1989");
    ETRS1989LCC.getGeographicInfo().getDatum().setName("D_ETRS_1989");
    EuropeAlbersEqualAreaConic.getGeographicInfo().getDatum().setName("D_European_1950");
    EuropeEquidistantConic.getGeographicInfo().getDatum().setName("D_European_1950");
    EuropeLambertConformalConic.getGeographicInfo().getDatum().setName("D_European_1950");
  }

  //</editor-fold>

  /**
   * @return the EMEP150KilometerGrid
   */
  public ProjectionInfo getEMEP150KilometerGrid() {
    return EMEP150KilometerGrid.copy();
  }

  /**
   * @param EMEP150KilometerGrid the EMEP150KilometerGrid to set
   */
  public void setEMEP150KilometerGrid(ProjectionInfo EMEP150KilometerGrid) {
    this.EMEP150KilometerGrid = EMEP150KilometerGrid.copy();
  }

  /**
   * @return the EMEP50KilometerGrid
   */
  public ProjectionInfo getEMEP50KilometerGrid() {
    return EMEP50KilometerGrid.copy();
  }

  /**
   * @param EMEP50KilometerGrid the EMEP50KilometerGrid to set
   */
  public void setEMEP50KilometerGrid(ProjectionInfo EMEP50KilometerGrid) {
    this.EMEP50KilometerGrid = EMEP50KilometerGrid.copy();
  }

  /**
   * @return the ETRS1989LAEA
   */
  public ProjectionInfo getETRS1989LAEA() {
    return ETRS1989LAEA.copy();
  }

  /**
   * @return the ETRS1989LCC
   */
  public ProjectionInfo getETRS1989LCC() {
    return ETRS1989LCC.copy();
  }

  /**
   * @return the EuropeAlbersEqualAreaConic
   */
  public ProjectionInfo getEuropeAlbersEqualAreaConic() {
    return EuropeAlbersEqualAreaConic.copy();
  }

  /**
   * @return the EuropeEquidistantConic
   */
  public ProjectionInfo getEuropeEquidistantConic() {
    return EuropeEquidistantConic.copy();
  }

  /**
   * @return the EuropeLambertConformalConic
   */
  public ProjectionInfo getEuropeLambertConformalConic() {
    return EuropeLambertConformalConic.copy();
  }
}
