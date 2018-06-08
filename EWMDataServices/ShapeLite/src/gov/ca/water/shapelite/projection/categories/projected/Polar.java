package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 * A class for polar projections.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Polar extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo NorthPoleAzimuthalEquidistant;
  private final ProjectionInfo NorthPoleGnomonic;
  private final ProjectionInfo NorthPoleLambertAzimuthalEqualArea;
  private final ProjectionInfo NorthPoleOrthographic;
  private final ProjectionInfo NorthPoleStereographic;
  private final ProjectionInfo Perroud1950TerreAdeliePolarStereographic;
  private final ProjectionInfo Petrels1972TerreAdeliePolarStereographic;
  private final ProjectionInfo SouthPoleAzimuthalEquidistant;
  private final ProjectionInfo SouthPoleGnomonic;
  private final ProjectionInfo SouthPoleLambertAzimuthalEqualArea;
  private final ProjectionInfo SouthPoleOrthographic;
  private final ProjectionInfo SouthPoleStereographic;
  private final ProjectionInfo UPSNorth;
  private final ProjectionInfo UPSSouth;
  private final ProjectionInfo WGS1984AntarcticPolarStereographic;
  private final ProjectionInfo WGS1984AustralianAntarcticLambert;
  private final ProjectionInfo WGS1984AustralianAntarcticPolarStereographic;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of Polar
  /// </summary>
  public Polar() {
    NorthPoleAzimuthalEquidistant = ProjectionInfo.fromProj4String("+proj=aeqd +lat_0=90 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    NorthPoleGnomonic = ProjectionInfo.fromProj4String("+proj=gnom +lat_0=90 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    NorthPoleLambertAzimuthalEqualArea = ProjectionInfo.fromProj4String("+proj=laea +lat_0=90 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    NorthPoleOrthographic = ProjectionInfo.fromProj4String("+proj=ortho +lat_0=90 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    NorthPoleStereographic = ProjectionInfo.fromProj4String("+proj=stere +lat_0=90 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Perroud1950TerreAdeliePolarStereographic = ProjectionInfo.fromProj4String("+ellps=intl +units=m +no_defs ").orElse(null);
    Petrels1972TerreAdeliePolarStereographic = ProjectionInfo.fromProj4String("+ellps=intl +units=m +no_defs ").orElse(null);
    SouthPoleAzimuthalEquidistant = ProjectionInfo.fromProj4String("+proj=aeqd +lat_0=-90 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    SouthPoleGnomonic = ProjectionInfo.fromProj4String("+proj=gnom +lat_0=-90 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    SouthPoleLambertAzimuthalEqualArea = ProjectionInfo.fromProj4String("+proj=laea +lat_0=-90 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    SouthPoleOrthographic = ProjectionInfo.fromProj4String("+proj=ortho +lat_0=-90 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    SouthPoleStereographic = ProjectionInfo.fromProj4String("+proj=stere +lat_0=-90 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    UPSNorth = ProjectionInfo.fromProj4String("+proj=stere +lat_0=90 +lon_0=0 +k=.994 +x_0=2000000 +y_0=2000000 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    UPSSouth = ProjectionInfo.fromProj4String("+proj=stere +lat_0=-90 +lon_0=0 +k=.994 +x_0=2000000 +y_0=2000000 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984AntarcticPolarStereographic = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984AustralianAntarcticLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=-68.5 +lat_2=-74.5 +lat_0=-50 +lon_0=70 +x_0=6000000 +y_0=6000000 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984AustralianAntarcticPolarStereographic = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);

    NorthPoleAzimuthalEquidistant.setName("North_Pole_Azimuthal_Equidistant");
    NorthPoleGnomonic.setName("North_Pole_Gnomonic");
    NorthPoleLambertAzimuthalEqualArea.setName("North_Pole_Lambert_Azimuthal_Equal_Area");
    NorthPoleOrthographic.setName("North_Pole_Orthographic");
    NorthPoleStereographic.setName("North_Pole_Stereographic");
    Perroud1950TerreAdeliePolarStereographic.setName("Perroud_1950_Terre_Adelie_Polar_Stereographic");
    Petrels1972TerreAdeliePolarStereographic.setName("Petrels_1972_Terre_Adelie_Polar_Stereographic");
    SouthPoleAzimuthalEquidistant.setName("South_Pole_Azimuthal_Equidistant");
    SouthPoleGnomonic.setName("South_Pole_Gnomonic");
    SouthPoleLambertAzimuthalEqualArea.setName("South_Pole_Lambert_Azimuthal_Equal_Area");
    SouthPoleOrthographic.setName("South_Pole_Orthographic");
    SouthPoleStereographic.setName("South_Pole_Stereographic");
    UPSNorth.setName("UPS_North");
    UPSSouth.setName("UPS_South");
    WGS1984AntarcticPolarStereographic.setName("WGS_1984_Antarctic_Polar_Stereographic");
    WGS1984AustralianAntarcticLambert.setName("WGS_1984_Australian_Antarctic_Lambert");
    WGS1984AustralianAntarcticPolarStereographic.setName("WGS_1984_Australian_Antarctic_Polar_Stereographic");

    NorthPoleAzimuthalEquidistant.getGeographicInfo().setName("GCS_WGS_1984");
    NorthPoleGnomonic.getGeographicInfo().setName("GCS_WGS_1984");
    NorthPoleLambertAzimuthalEqualArea.getGeographicInfo().setName("GCS_WGS_1984");
    NorthPoleOrthographic.getGeographicInfo().setName("GCS_WGS_1984");
    NorthPoleStereographic.getGeographicInfo().setName("GCS_WGS_1984");
    Perroud1950TerreAdeliePolarStereographic.getGeographicInfo().setName("GCS_Pointe_Geologie_Perroud_1950");
    Petrels1972TerreAdeliePolarStereographic.getGeographicInfo().setName("GCS_Petrels_1972");
    SouthPoleAzimuthalEquidistant.getGeographicInfo().setName("GCS_WGS_1984");
    SouthPoleGnomonic.getGeographicInfo().setName("GCS_WGS_1984");
    SouthPoleLambertAzimuthalEqualArea.getGeographicInfo().setName("GCS_WGS_1984");
    SouthPoleOrthographic.getGeographicInfo().setName("GCS_WGS_1984");
    SouthPoleStereographic.getGeographicInfo().setName("GCS_WGS_1984");
    UPSNorth.getGeographicInfo().setName("GCS_WGS_1984");
    UPSSouth.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984AntarcticPolarStereographic.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984AustralianAntarcticLambert.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984AustralianAntarcticPolarStereographic.getGeographicInfo().setName("GCS_WGS_1984");

    NorthPoleAzimuthalEquidistant.getGeographicInfo().getDatum().setName("D_WGS_1984");
    NorthPoleGnomonic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    NorthPoleLambertAzimuthalEqualArea.getGeographicInfo().getDatum().setName("D_WGS_1984");
    NorthPoleOrthographic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    NorthPoleStereographic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Perroud1950TerreAdeliePolarStereographic.getGeographicInfo().getDatum().setName("D_Pointe_Geologie_Perroud_1950");
    Petrels1972TerreAdeliePolarStereographic.getGeographicInfo().getDatum().setName("D_Petrels_1972");
    SouthPoleAzimuthalEquidistant.getGeographicInfo().getDatum().setName("D_WGS_1984");
    SouthPoleGnomonic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    SouthPoleLambertAzimuthalEqualArea.getGeographicInfo().getDatum().setName("D_WGS_1984");
    SouthPoleOrthographic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    SouthPoleStereographic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    UPSNorth.getGeographicInfo().getDatum().setName("D_WGS_1984");
    UPSSouth.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984AntarcticPolarStereographic.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984AustralianAntarcticLambert.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984AustralianAntarcticPolarStereographic.getGeographicInfo().getDatum().setName("D_WGS_1984");
  }

  //</editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the NorthPoleAzimuthalEquidistant
   */
  public ProjectionInfo getNorthPoleAzimuthalEquidistant() {
    return NorthPoleAzimuthalEquidistant.copy();
  }

  /**
   * @return the NorthPoleGnomonic
   */
  public ProjectionInfo getNorthPoleGnomonic() {
    return NorthPoleGnomonic.copy();
  }

  /**
   * @return the NorthPoleLambertAzimuthalEqualArea
   */
  public ProjectionInfo getNorthPoleLambertAzimuthalEqualArea() {
    return NorthPoleLambertAzimuthalEqualArea.copy();
  }

  /**
   * @return the NorthPoleOrthographic
   */
  public ProjectionInfo getNorthPoleOrthographic() {
    return NorthPoleOrthographic.copy();
  }

  /**
   * @return the NorthPoleStereographic
   */
  public ProjectionInfo getNorthPoleStereographic() {
    return NorthPoleStereographic.copy();
  }

  /**
   * @return the Perroud1950TerreAdeliePolarStereographic
   */
  public ProjectionInfo getPerroud1950TerreAdeliePolarStereographic() {
    return Perroud1950TerreAdeliePolarStereographic.copy();
  }

  /**
   * @return the Petrels1972TerreAdeliePolarStereographic
   */
  public ProjectionInfo getPetrels1972TerreAdeliePolarStereographic() {
    return Petrels1972TerreAdeliePolarStereographic.copy();
  }

  /**
   * @return the SouthPoleAzimuthalEquidistant
   */
  public ProjectionInfo getSouthPoleAzimuthalEquidistant() {
    return SouthPoleAzimuthalEquidistant.copy();
  }

  /**
   * @return the SouthPoleGnomonic
   */
  public ProjectionInfo getSouthPoleGnomonic() {
    return SouthPoleGnomonic.copy();
  }

  /**
   * @return the SouthPoleLambertAzimuthalEqualArea
   */
  public ProjectionInfo getSouthPoleLambertAzimuthalEqualArea() {
    return SouthPoleLambertAzimuthalEqualArea.copy();
  }

  /**
   * @return the SouthPoleOrthographic
   */
  public ProjectionInfo getSouthPoleOrthographic() {
    return SouthPoleOrthographic.copy();
  }

  /**
   * @return the SouthPoleStereographic
   */
  public ProjectionInfo getSouthPoleStereographic() {
    return SouthPoleStereographic.copy();
  }

  /**
   * @return the UPSNorth
   */
  public ProjectionInfo getUPSNorth() {
    return UPSNorth.copy();
  }

  /**
   * @return the UPSSouth
   */
  public ProjectionInfo getUPSSouth() {
    return UPSSouth.copy();
  }

  /**
   * @return the WGS1984AntarcticPolarStereographic
   */
  public ProjectionInfo getWGS1984AntarcticPolarStereographic() {
    return WGS1984AntarcticPolarStereographic.copy();
  }

  /**
   * @return the WGS1984AustralianAntarcticLambert
   */
  public ProjectionInfo getWGS1984AustralianAntarcticLambert() {
    return WGS1984AustralianAntarcticLambert.copy();
  }

  /**
   * @return the WGS1984AustralianAntarcticPolarStereographic
   */
  public ProjectionInfo getWGS1984AustralianAntarcticPolarStereographic() {
    return WGS1984AustralianAntarcticPolarStereographic.copy();
  }

  // </editor-fold>
}
