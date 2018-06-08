/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.AuxiliarySphereType;
import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.Spheroid;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
import gov.ca.water.shapelite.projection.transforms.MercatorAuxiliarySphere;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class World extends CoordinateSystemCategory {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private final ProjectionInfo Aitoffworld;
  private final ProjectionInfo Behrmannworld;
  private final ProjectionInfo Bonneworld;
  private final ProjectionInfo CrasterParabolicworld;
  private final ProjectionInfo Cubeworld;
  private final ProjectionInfo CylindricalEqualAreaworld;
  private final ProjectionInfo EckertIIIworld;
  private final ProjectionInfo EckertIIworld;
  private final ProjectionInfo EckertIVworld;
  private final ProjectionInfo EckertIworld;
  private final ProjectionInfo EckertVIworld;
  private final ProjectionInfo EckertVworld;
  private final ProjectionInfo EquidistantConicworld;
  private final ProjectionInfo EquidistantCylindricalworld;
  private final ProjectionInfo FlatPolarQuarticworld;
  private final ProjectionInfo Fullerworld;
  private final ProjectionInfo GallStereographicworld;
  private final ProjectionInfo HammerAitoffworld;
  private final ProjectionInfo Loximuthalworld;
  private final ProjectionInfo Mercatorworld;

  private final ProjectionInfo MillerCylindricalworld;
  private final ProjectionInfo Mollweideworld;
  private final ProjectionInfo PlateCarreeworld;
  private final ProjectionInfo Polyconicworld;
  private final ProjectionInfo QuarticAuthalicworld;
  private final ProjectionInfo Robinsonworld;
  private final ProjectionInfo Sinusoidalworld;
  private final ProjectionInfo TheWorldfromSpace;
  private final ProjectionInfo Timesworld;
  private final ProjectionInfo VanderGrintenIworld;
  private final ProjectionInfo VerticalPerspectiveworld;
  private final ProjectionInfo WebMercator;
  private final ProjectionInfo WinkelIIworld;
  private final ProjectionInfo WinkelIworld;
  private final ProjectionInfo WinkelTripelNGSworld;

  //</editor-fold>
  /**
   * Creates a new instance of the ProjectionsProjectedWorld class.
   */
  public World() {

    Aitoffworld = ProjectionInfo.fromProj4String("+proj=aitoff +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Behrmannworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Bonneworld = ProjectionInfo.fromProj4String("+proj=bonne +lon_0=0 +lat_1=60 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    CrasterParabolicworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Cubeworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    CylindricalEqualAreaworld = ProjectionInfo.fromProj4String("+proj=cea +lon_0=0 +lat_ts=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    EckertIIIworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    EckertIIworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    EckertIVworld = ProjectionInfo.fromProj4String("+proj=eck4 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    EckertIworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    EckertVIworld = ProjectionInfo.fromProj4String("+proj=eck6 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    EckertVworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    EquidistantConicworld = ProjectionInfo.fromProj4String("+proj=eqdc +lat_0=0 +lon_0=0 +lat_1=60 +lat_2=60 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    EquidistantCylindricalworld = ProjectionInfo.fromProj4String("+proj=eqc +lat_ts=0 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    FlatPolarQuarticworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Fullerworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    GallStereographicworld = ProjectionInfo.fromProj4String("+proj=gall +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    HammerAitoffworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Loximuthalworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Mercatorworld = ProjectionInfo.fromProj4String("+proj=merc +lat_ts=0 +lon_0=0 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    MillerCylindricalworld = ProjectionInfo.fromProj4String("+proj=mill +lat_0=0 +lon_0=0 +x_0=0 +y_0=0 +R_A +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Mollweideworld = ProjectionInfo.fromProj4String("+proj=moll +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    PlateCarreeworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Polyconicworld = ProjectionInfo.fromProj4String("+proj=poly +lat_0=0 +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    QuarticAuthalicworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Robinsonworld = ProjectionInfo.fromProj4String("+proj=robin +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    Sinusoidalworld = ProjectionInfo.fromProj4String("+proj=sinu +lon_0=0 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    TheWorldfromSpace = ProjectionInfo.fromProj4String("+proj=ortho +lat_0=42.5333333333 +lon_0=-72.5333333334 +x_0=0 +y_0=0 +a=6370997 +b=6370997 +units=m +no_defs ").orElse(null);
    Timesworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    VanderGrintenIworld = ProjectionInfo.fromProj4String("+proj=vandg +lon_0=0 +x_0=0 +y_0=0 +R_A +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    VerticalPerspectiveworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);

    //JIRI -- Changed the web mercator projection definition
    WebMercator = ProjectionInfo.fromProj4String("+proj=merc +a=6378137 +b=6378137 +lat_ts=0.0 +lon_0=0.0 +x_0=0.0 +y_0=0.0 +k=1.0 +units=m +nadgrids=@null +no_defs ").orElse(null);
    WebMercator.setTransform(new MercatorAuxiliarySphere());
    WebMercator.setScaleFactor(1);
    WebMercator.setAuxiliarySphereType(AuxiliarySphereType.SemimajorAxis);
    WebMercator.getGeographicInfo().getDatum().setSpheroid(new Spheroid(WebMercator.getGeographicInfo().getDatum().getSpheroid().getEquatorialRadius()));
    try {
      WebMercator.transform.init(WebMercator);
      //ITransform originalTransform = WebMercator.Transform;
      //WebMercator.GeographicInfo.Datum.DatumType = DatumType.WGS84; //web mercator has a WGS84 datum type
      //WebMercator.Transform = originalTransform; //reset the transform
    } catch (ProjectionException ex) {
      Logger.getLogger(World.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
    }

    WinkelIIworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WinkelIworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WinkelTripelNGSworld = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);

    Aitoffworld.setName("World_Aitoff");
    Behrmannworld.setName("World_Behrmann");
    Bonneworld.setName("World_Bonne");
    CrasterParabolicworld.setName("World_Craster_Parabolic");
    Cubeworld.setName("World_Cube");
    CylindricalEqualAreaworld.setName("World_Cylindrical_Equal_Area");
    EckertIIIworld.setName("World_Eckert_III");
    EckertIIworld.setName("World_Eckert_II");
    EckertIVworld.setName("World_Eckert_IV");
    EckertIworld.setName("World_Eckert_I");
    EckertVIworld.setName("World_Eckert_VI");
    EckertVworld.setName("World_Eckert_V");
    EquidistantConicworld.setName("World_Equidistant_Conic");
    EquidistantCylindricalworld.setName("World_Equidistant_Cylindrical");
    FlatPolarQuarticworld.setName("World_Flat_Polar_Quartic");
    Fullerworld.setName("World_Fuller");
    GallStereographicworld.setName("World_Gall_Stereographic");
    HammerAitoffworld.setName("World_Hammer_Aitoff");
    Loximuthalworld.setName("World_Loximuthal");
    Mercatorworld.setName("World_Mercator");
    MillerCylindricalworld.setName("World_Miller_Cylindrical");
    Mollweideworld.setName("World_Mollweide");
    PlateCarreeworld.setName("World_Plate_Carree");
    Polyconicworld.setName("World_Polyconic");
    QuarticAuthalicworld.setName("World_Quartic_Authalic");
    Robinsonworld.setName("World_Robinson");
    Sinusoidalworld.setName("World_Sinusoidal");
    TheWorldfromSpace.setName("The_World_From_Space");
    Timesworld.setName("World_Times");
    VanderGrintenIworld.setName("World_Van_der_Grinten_I");
    VerticalPerspectiveworld.setName("World_Vertical_Perspective");
    //name changed by JK to match the esri string
    //WebMercator.setName("WGS_1984_Web_Mercator");
    WebMercator.setName("WGS_1984_Web_Mercator_Auxiliary_Sphere");
    WinkelIworld.setName("World_Winkel_II");
    WinkelTripelNGSworld.setName("World_Winkel_Tripel_NGS");

    Aitoffworld.getGeographicInfo().setName("GCS_WGS_1984");
    Behrmannworld.getGeographicInfo().setName("GCS_WGS_1984");
    Bonneworld.getGeographicInfo().setName("GCS_WGS_1984");
    CrasterParabolicworld.getGeographicInfo().setName("GCS_WGS_1984");
    Cubeworld.getGeographicInfo().setName("GCS_WGS_1984");
    CylindricalEqualAreaworld.getGeographicInfo().setName("GCS_WGS_1984");
    EckertIIIworld.getGeographicInfo().setName("GCS_WGS_1984");
    EckertIIworld.getGeographicInfo().setName("GCS_WGS_1984");
    EckertIVworld.getGeographicInfo().setName("GCS_WGS_1984");
    EckertIworld.getGeographicInfo().setName("GCS_WGS_1984");
    EckertVIworld.getGeographicInfo().setName("GCS_WGS_1984");
    EckertVworld.getGeographicInfo().setName("GCS_WGS_1984");
    EquidistantConicworld.getGeographicInfo().setName("GCS_WGS_1984");
    EquidistantCylindricalworld.getGeographicInfo().setName("GCS_WGS_1984");
    FlatPolarQuarticworld.getGeographicInfo().setName("GCS_WGS_1984");
    Fullerworld.getGeographicInfo().setName("GCS_WGS_1984");
    GallStereographicworld.getGeographicInfo().setName("GCS_WGS_1984");
    HammerAitoffworld.getGeographicInfo().setName("GCS_WGS_1984");
    Loximuthalworld.getGeographicInfo().setName("GCS_WGS_1984");
    Mercatorworld.getGeographicInfo().setName("GCS_WGS_1984");
    MillerCylindricalworld.getGeographicInfo().setName("GCS_WGS_1984");
    Mollweideworld.getGeographicInfo().setName("GCS_WGS_1984");
    PlateCarreeworld.getGeographicInfo().setName("GCS_WGS_1984");
    Polyconicworld.getGeographicInfo().setName("GCS_WGS_1984");
    QuarticAuthalicworld.getGeographicInfo().setName("GCS_WGS_1984");
    Robinsonworld.getGeographicInfo().setName("GCS_WGS_1984");
    Sinusoidalworld.getGeographicInfo().setName("GCS_WGS_1984");
    TheWorldfromSpace.getGeographicInfo().setName("GCS_Sphere_ARC_INFO");
    Timesworld.getGeographicInfo().setName("GCS_WGS_1984");
    VanderGrintenIworld.getGeographicInfo().setName("GCS_WGS_1984");
    VerticalPerspectiveworld.getGeographicInfo().setName("GCS_WGS_1984");
    WinkelIworld.getGeographicInfo().setName("GCS_WGS_1984");
    WinkelTripelNGSworld.getGeographicInfo().setName("GCS_WGS_1984");
    //Jiri Kadlec - changed the 'web mercator' geographic info name
    //WebMercator.getGeographicInfo().setName("GCS_WGS_1984_Major_Auxiliary_Sphere");
    WebMercator.getGeographicInfo().setName("GCS_WGS_1984");

    Aitoffworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Behrmannworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Bonneworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    CrasterParabolicworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Cubeworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    CylindricalEqualAreaworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    EckertIIIworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    EckertIIworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    EckertIVworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    EckertIworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    EckertVIworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    EckertVworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    EquidistantConicworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    EquidistantCylindricalworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    FlatPolarQuarticworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Fullerworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    GallStereographicworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    HammerAitoffworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Loximuthalworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Mercatorworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    MillerCylindricalworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Mollweideworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    PlateCarreeworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Polyconicworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    QuarticAuthalicworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Robinsonworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    Sinusoidalworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    TheWorldfromSpace.getGeographicInfo().getDatum().setName("D_Sphere_ARC_INFO");
    Timesworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    VanderGrintenIworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    VerticalPerspectiveworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WinkelIworld.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WinkelTripelNGSworld.getGeographicInfo().getDatum().setName("D_WGS_1984");

    //Jiri Kadlec - Changed the WebMercator datum name to match the EsriString
    //WebMercator.getGeographicInfo().getDatum().setName("D_WGS_1984_Major_Auxiliary_Sphere");
    WebMercator.getGeographicInfo().getDatum().setName("D_WGS_1984");
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">

  /**
   * @return the Aitoffworld
   */
  public final ProjectionInfo getAitoffworld() {
    return Aitoffworld.copy();
  }

  /**
   * @return the Behrmannworld
   */
  public final ProjectionInfo getBehrmannworld() {
    return Behrmannworld.copy();
  }

  /**
   * @return the Bonneworld
   */
  public final ProjectionInfo getBonneworld() {
    return Bonneworld.copy();
  }

  /**
   * @return the CrasterParabolicworld
   */
  public final ProjectionInfo getCrasterParabolicworld() {
    return CrasterParabolicworld.copy();
  }

  /**
   * @return the Cubeworld
   */
  public final ProjectionInfo getCubeworld() {
    return Cubeworld.copy();
  }

  /**
   * @return the CylindricalEqualAreaworld
   */
  public final ProjectionInfo getCylindricalEqualAreaworld() {
    return CylindricalEqualAreaworld.copy();
  }

  /**
   * @return the EckertIIIworld
   */
  public final ProjectionInfo getEckertIIIworld() {
    return EckertIIIworld.copy();
  }

  /**
   * @return the EckertIIworld
   */
  public final ProjectionInfo getEckertIIworld() {
    return EckertIIworld.copy();
  }

  /**
   * @return the EckertIVworld
   */
  public final ProjectionInfo getEckertIVworld() {
    return EckertIVworld.copy();
  }

  /**
   * @return the EckertIworld
   */
  public final ProjectionInfo getEckertIworld() {
    return EckertIworld.copy();
  }

  /**
   * @return the EckertVIworld
   */
  public final ProjectionInfo getEckertVIworld() {
    return EckertVIworld.copy();
  }

  /**
   * @return the EckertVworld
   */
  public final ProjectionInfo getEckertVworld() {
    return EckertVworld.copy();
  }

  /**
   * @return the EquidistantConicworld
   */
  public final ProjectionInfo getEquidistantConicworld() {
    return EquidistantConicworld.copy();
  }

  /**
   * @return the EquidistantCylindricalworld
   */
  public final ProjectionInfo getEquidistantCylindricalworld() {
    return EquidistantCylindricalworld.copy();
  }

  /**
   * @return the FlatPolarQuarticworld
   */
  public final ProjectionInfo getFlatPolarQuarticworld() {
    return FlatPolarQuarticworld.copy();
  }

  /**
   * @return the Fullerworld
   */
  public final ProjectionInfo getFullerworld() {
    return Fullerworld.copy();
  }

  /**
   * @return the GallStereographicworld
   */
  public final ProjectionInfo getGallStereographicworld() {
    return GallStereographicworld.copy();
  }

  /**
   * @return the HammerAitoffworld
   */
  public final ProjectionInfo getHammerAitoffworld() {
    return HammerAitoffworld.copy();
  }

  /**
   * @return the Loximuthalworld
   */
  public final ProjectionInfo getLoximuthalworld() {
    return Loximuthalworld.copy();
  }

  /**
   * @return the Mercatorworld
   */
  public final ProjectionInfo getMercatorworld() {
    return Mercatorworld.copy();
  }

  /**
   * @return the MillerCylindricalworld
   */
  public final ProjectionInfo getMillerCylindricalworld() {
    return MillerCylindricalworld.copy();
  }

  /**
   * @return the Mollweideworld
   */
  public final ProjectionInfo getMollweideworld() {
    return Mollweideworld.copy();
  }

  /**
   * @return the PlateCarreeworld
   */
  public final ProjectionInfo getPlateCarreeworld() {
    return PlateCarreeworld.copy();
  }

  /**
   * @return the Polyconicworld
   */
  public final ProjectionInfo getPolyconicworld() {
    return Polyconicworld.copy();
  }

  /**
   * @return the QuarticAuthalicworld
   */
  public final ProjectionInfo getQuarticAuthalicworld() {
    return QuarticAuthalicworld.copy();
  }

  /**
   * @return the Robinsonworld
   */
  public final ProjectionInfo getRobinsonworld() {
    return Robinsonworld.copy();
  }

  /**
   * @return the Sinusoidalworld
   */
  public final ProjectionInfo getSinusoidalworld() {
    return Sinusoidalworld.copy();
  }

  /**
   * @return the TheWorldfromSpace
   */
  public final ProjectionInfo getTheWorldfromSpace() {
    return TheWorldfromSpace.copy();
  }

  /**
   * @return the Timesworld
   */
  public final ProjectionInfo getTimesworld() {
    return Timesworld.copy();
  }

  /**
   * @return the VanderGrintenIworld
   */
  public final ProjectionInfo getVanderGrintenIworld() {
    return VanderGrintenIworld.copy();
  }

  /**
   * @return the VerticalPerspectiveworld
   */
  public final ProjectionInfo getVerticalPerspectiveworld() {
    return VerticalPerspectiveworld.copy();
  }

  /**
   * @return the WebMercator
   */
  public final ProjectionInfo getWebMercator() {
    return WebMercator.copy();
  }

  /**
   * @return the WinkelIIworld
   */
  public final ProjectionInfo getWinkelIIworld() {
    return WinkelIIworld.copy();
  }

  /**
   * @return the WinkelIworld
   */
  public final ProjectionInfo getWinkelIworld() {
    return WinkelIworld.copy();
  }

  /**
   * @return the WinkelTripelNGSworld
   */
  public final ProjectionInfo getWinkelTripelNGSworld() {
    return WinkelTripelNGSworld.copy();
  }

  //</editor-fold>
}
