// ********************************************************************************************************
// Product Name: DotSpatial.Projection
// Description:  The basic module for MapWindow version 6.0
// ********************************************************************************************************
// The contents of this file are subject to the MIT License (MIT)
// you may not use this file except in compliance with the License. You may obtain a copy of the License at
// http://dotspatial.codeplex.com/license
//
// Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF
// ANY KIND, either expressed or implied. See the License for the specific language governing rights and
// limitations under the License.
//
// The Original Code is from MapWindow.dll version 6.0
//
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 5:13:38 PM
//
// Contributor(s): (Open source contributors should list themselves and their modifications here).
//        Name         |    Date    |        Comment
// --------------------|------------|------------------------------------------------------------
// Ted Dunsford        |   5/3/2010 |  Updated project to DotSpatial.Projection and license to LGPL
// ********************************************************************************************************


package gov.ca.water.shapelite.projection.categories.projected;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
    /// <summary>
    /// WorldSpheroid
    /// </summary>
    public class WorldSpheroid extends CoordinateSystemCategory
    {
        //<editor-fold defaultstate="collapsed" desc="Fields">

      private final ProjectionInfo Aitoffsphere;
      private final ProjectionInfo Behrmannsphere;
      private final ProjectionInfo Bonnesphere;
      private final ProjectionInfo CrasterParabolicsphere;
      private final ProjectionInfo CylindricalEqualAreasphere;
      private final ProjectionInfo EckertIIIsphere;
      private final ProjectionInfo EckertIIsphere;
      private final ProjectionInfo EckertIVsphere;
      private final ProjectionInfo EckertIsphere;
      private final ProjectionInfo EckertVIsphere;
      private final ProjectionInfo EckertVsphere;
      private final ProjectionInfo EquidistantConicsphere;
      private final ProjectionInfo EquidistantCylindricalsphere;
      private final ProjectionInfo FlatPolarQuarticsphere;
      private final ProjectionInfo GallStereographicsphere;
      private final ProjectionInfo HammerAitoffsphere;
      private final ProjectionInfo Loximuthalsphere;
      private final ProjectionInfo Mercatorsphere;
      private final ProjectionInfo MillerCylindricalsphere;
      private final ProjectionInfo Mollweidesphere;
      private final ProjectionInfo PlateCarreesphere;
      private final ProjectionInfo Polyconicsphere;
      private final ProjectionInfo QuarticAuthalicsphere;
      private final ProjectionInfo Robinsonsphere;
      private final ProjectionInfo Sinusoidalsphere;
      private final ProjectionInfo Timessphere;
      private final ProjectionInfo VanderGrintenIsphere;
      private final ProjectionInfo VerticalPerspectivesphere;
      private final ProjectionInfo WinkelIIsphere;
      private final ProjectionInfo WinkelIsphere;
      private final ProjectionInfo WinkelTripelNGSsphere;

        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Constructor">

        /// <summary>
        /// Creates a new instance of WorldSpheroid
        /// </summary>
        public WorldSpheroid()
        {
            Aitoffsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            Behrmannsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            Bonnesphere = ProjectionInfo.fromProj4String("+proj=bonne +lon_0=0 +lat_1=60 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            CrasterParabolicsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            CylindricalEqualAreasphere = ProjectionInfo.fromProj4String("+proj=cea +lon_0=0 +lat_ts=0 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            EckertIIIsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            EckertIIsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            EckertIsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            EckertIVsphere = ProjectionInfo.fromProj4String("+proj=eck4 +lon_0=0 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            EckertVIsphere = ProjectionInfo.fromProj4String("+proj=eck6 +lon_0=0 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            EckertVsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            EquidistantConicsphere = ProjectionInfo.fromProj4String("+proj=eqdc +lat_0=0 +lon_0=0 +lat_1=60 +lat_2=60 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            EquidistantCylindricalsphere = ProjectionInfo.fromProj4String("+proj=eqc +lat_ts=0 +lon_0=0 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            FlatPolarQuarticsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            GallStereographicsphere = ProjectionInfo.fromProj4String("+proj=gall +lon_0=0 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            HammerAitoffsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            Loximuthalsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            Mercatorsphere = ProjectionInfo.fromProj4String("+proj=merc +lat_ts=0 +lon_0=0 +k=1.000000 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            MillerCylindricalsphere = ProjectionInfo.fromProj4String("+proj=mill +lat_0=0 +lon_0=0 +x_0=0 +y_0=0 +R_A +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            Mollweidesphere = ProjectionInfo.fromProj4String("+proj=moll +lon_0=0 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            PlateCarreesphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            Polyconicsphere = ProjectionInfo.fromProj4String("+proj=poly +lat_0=0 +lon_0=0 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            QuarticAuthalicsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            Robinsonsphere = ProjectionInfo.fromProj4String("+proj=robin +lon_0=0 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            Sinusoidalsphere = ProjectionInfo.fromProj4String("+proj=sinu +lon_0=0 +x_0=0 +y_0=0 +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            Timessphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            VanderGrintenIsphere = ProjectionInfo.fromProj4String("+proj=vandg +lon_0=0 +x_0=0 +y_0=0 +R_A +a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            VerticalPerspectivesphere = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WinkelIIsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            WinkelIsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);
            WinkelTripelNGSsphere = ProjectionInfo.fromProj4String("+a=6371000 +b=6371000 +units=m +no_defs ").orElse(null);

            Aitoffsphere.setName("Sphere_Aitoff");
            Behrmannsphere.setName("Sphere_Behrmann");
            Bonnesphere.setName("Sphere_Bonne");
            CrasterParabolicsphere.setName("Sphere_Craster_Parabolic");
            CylindricalEqualAreasphere.setName("Sphere_Cylindrical_Equal_Area");
            EckertIsphere.setName("Sphere_Eckert_I");
            EckertIIsphere.setName("Sphere_Eckert_II");
            EckertIIIsphere.setName("Sphere_Eckert_III");
            EckertIVsphere.setName("Sphere_Eckert_IV");
            EckertVsphere.setName("Sphere_Eckert_V");
            EckertVIsphere.setName("Sphere_Eckert_VI");
            EquidistantConicsphere.setName("Sphere_Equidistant_Conic");
            EquidistantCylindricalsphere.setName("Sphere_Equidistant_Cylindrical");
            FlatPolarQuarticsphere.setName("Sphere_Flat_Polar_Quartic");
            GallStereographicsphere.setName("Sphere_Gall_Stereographic");
            HammerAitoffsphere.setName("Sphere_Hammer_Aitoff");
            Loximuthalsphere.setName("Sphere_Loximuthal");
            Mercatorsphere.setName("Sphere_Mercator");
            MillerCylindricalsphere.setName("Sphere_Miller_Cylindrical");
            Mollweidesphere.setName("Sphere_Mollweide");
            PlateCarreesphere.setName("Sphere_Plate_Carree");
            Polyconicsphere.setName("Sphere_Polyconic");
            QuarticAuthalicsphere.setName("Sphere_Quartic_Authalic");
            Robinsonsphere.setName("Sphere_Robinson");
            Sinusoidalsphere.setName("Sphere_Sinusoidal");
            Timessphere.setName("Sphere_Times");
            VanderGrintenIsphere.setName("Sphere_Van_der_Grinten_I");
            VerticalPerspectivesphere.setName("Sphere_Vertical_Perspective");
            WinkelIsphere.setName("Sphere_Winkel_II");
            WinkelTripelNGSsphere.setName("Sphere_Winkel_Tripel_NGS");

            Aitoffsphere.getGeographicInfo().setName("GCS_Sphere");
            Behrmannsphere.getGeographicInfo().setName("GCS_Sphere");
            Bonnesphere.getGeographicInfo().setName("GCS_Sphere");
            CrasterParabolicsphere.getGeographicInfo().setName("GCS_Sphere");
            CylindricalEqualAreasphere.getGeographicInfo().setName("GCS_Sphere");
            EckertIsphere.getGeographicInfo().setName("GCS_Sphere");
            EckertIIsphere.getGeographicInfo().setName("GCS_Sphere");
            EckertIIIsphere.getGeographicInfo().setName("GCS_Sphere");
            EckertIVsphere.getGeographicInfo().setName("GCS_Sphere");
            EckertVsphere.getGeographicInfo().setName("GCS_Sphere");
            EckertVIsphere.getGeographicInfo().setName("GCS_Sphere");
            EquidistantConicsphere.getGeographicInfo().setName("GCS_Sphere");
            EquidistantCylindricalsphere.getGeographicInfo().setName("GCS_Sphere");
            FlatPolarQuarticsphere.getGeographicInfo().setName("GCS_Sphere");
            GallStereographicsphere.getGeographicInfo().setName("GCS_Sphere");
            HammerAitoffsphere.getGeographicInfo().setName("GCS_Sphere");
            Loximuthalsphere.getGeographicInfo().setName("GCS_Sphere");
            Mercatorsphere.getGeographicInfo().setName("GCS_Sphere");
            MillerCylindricalsphere.getGeographicInfo().setName("GCS_Sphere");
            Mollweidesphere.getGeographicInfo().setName("GCS_Sphere");
            PlateCarreesphere.getGeographicInfo().setName("GCS_Sphere");
            Polyconicsphere.getGeographicInfo().setName("GCS_Sphere");
            QuarticAuthalicsphere.getGeographicInfo().setName("GCS_Sphere");
            Robinsonsphere.getGeographicInfo().setName("GCS_Sphere");
            Sinusoidalsphere.getGeographicInfo().setName("GCS_Sphere");
            Timessphere.getGeographicInfo().setName("GCS_Sphere");
            VanderGrintenIsphere.getGeographicInfo().setName("GCS_Sphere");
            VerticalPerspectivesphere.getGeographicInfo().setName("GCS_WGS_1984");
            WinkelIsphere.getGeographicInfo().setName("GCS_Sphere");
            WinkelTripelNGSsphere.getGeographicInfo().setName("GCS_Sphere");

            Aitoffsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            Behrmannsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            Bonnesphere.getGeographicInfo().getDatum().setName("D_Sphere");
            CrasterParabolicsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            CylindricalEqualAreasphere.getGeographicInfo().getDatum().setName("D_Sphere");
            EckertIsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            EckertIIsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            EckertIIIsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            EckertIVsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            EckertVsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            EckertVIsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            EquidistantConicsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            EquidistantCylindricalsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            FlatPolarQuarticsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            GallStereographicsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            HammerAitoffsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            Loximuthalsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            Mercatorsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            MillerCylindricalsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            Mollweidesphere.getGeographicInfo().getDatum().setName("D_Sphere");
            PlateCarreesphere.getGeographicInfo().getDatum().setName("D_Sphere");
            Polyconicsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            QuarticAuthalicsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            Robinsonsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            Sinusoidalsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            Timessphere.getGeographicInfo().getDatum().setName("D_Sphere");
            VanderGrintenIsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            VerticalPerspectivesphere.getGeographicInfo().getDatum().setName("D_WGS_1984");
            WinkelIsphere.getGeographicInfo().getDatum().setName("D_Sphere");
            WinkelTripelNGSsphere.getGeographicInfo().getDatum().setName("D_Sphere");
        }

        //</editor-fold>

  /**
   * @return the Aitoffsphere
   */
  public ProjectionInfo getAitoffsphere() {
    return Aitoffsphere.copy();
  }

  /**
   * @return the Behrmannsphere
   */
  public ProjectionInfo getBehrmannsphere() {
    return Behrmannsphere.copy();
  }

  /**
   * @return the Bonnesphere
   */
  public ProjectionInfo getBonnesphere() {
    return Bonnesphere.copy();
  }

  /**
   * @return the CrasterParabolicsphere
   */
  public ProjectionInfo getCrasterParabolicsphere() {
    return CrasterParabolicsphere.copy();
  }

  /**
   * @return the CylindricalEqualAreasphere
   */
  public ProjectionInfo getCylindricalEqualAreasphere() {
    return CylindricalEqualAreasphere.copy();
  }

  /**
   * @return the EckertIIIsphere
   */
  public ProjectionInfo getEckertIIIsphere() {
    return EckertIIIsphere.copy();
  }

  /**
   * @return the EckertIIsphere
   */
  public ProjectionInfo getEckertIIsphere() {
    return EckertIIsphere.copy();
  }

  /**
   * @return the EckertIVsphere
   */
  public ProjectionInfo getEckertIVsphere() {
    return EckertIVsphere.copy();
  }

  /**
   * @return the EckertIsphere
   */
  public ProjectionInfo getEckertIsphere() {
    return EckertIsphere.copy();
  }

  /**
   * @return the EckertVIsphere
   */
  public ProjectionInfo getEckertVIsphere() {
    return EckertVIsphere.copy();
  }

  /**
   * @return the EckertVsphere
   */
  public ProjectionInfo getEckertVsphere() {
    return EckertVsphere.copy();
  }

  /**
   * @return the EquidistantConicsphere
   */
  public ProjectionInfo getEquidistantConicsphere() {
    return EquidistantConicsphere.copy();
  }

  /**
   * @return the EquidistantCylindricalsphere
   */
  public ProjectionInfo getEquidistantCylindricalsphere() {
    return EquidistantCylindricalsphere.copy();
  }

  /**
   * @return the FlatPolarQuarticsphere
   */
  public ProjectionInfo getFlatPolarQuarticsphere() {
    return FlatPolarQuarticsphere.copy();
  }

  /**
   * @return the GallStereographicsphere
   */
  public ProjectionInfo getGallStereographicsphere() {
    return GallStereographicsphere.copy();
  }

  /**
   * @return the HammerAitoffsphere
   */
  public ProjectionInfo getHammerAitoffsphere() {
    return HammerAitoffsphere.copy();
  }

  /**
   * @return the Loximuthalsphere
   */
  public ProjectionInfo getLoximuthalsphere() {
    return Loximuthalsphere.copy();
  }

  /**
   * @return the Mercatorsphere
   */
  public ProjectionInfo getMercatorsphere() {
    return Mercatorsphere.copy();
  }

  /**
   * @return the MillerCylindricalsphere
   */
  public ProjectionInfo getMillerCylindricalsphere() {
    return MillerCylindricalsphere.copy();
  }

  /**
   * @return the Mollweidesphere
   */
  public ProjectionInfo getMollweidesphere() {
    return Mollweidesphere.copy();
  }

  /**
   * @return the PlateCarreesphere
   */
  public ProjectionInfo getPlateCarreesphere() {
    return PlateCarreesphere.copy();
  }

  /**
   * @return the Polyconicsphere
   */
  public ProjectionInfo getPolyconicsphere() {
    return Polyconicsphere.copy();
  }

  /**
   * @return the QuarticAuthalicsphere
   */
  public ProjectionInfo getQuarticAuthalicsphere() {
    return QuarticAuthalicsphere.copy();
  }

  /**
   * @return the Robinsonsphere
   */
  public ProjectionInfo getRobinsonsphere() {
    return Robinsonsphere.copy();
  }

  /**
   * @return the Sinusoidalsphere
   */
  public ProjectionInfo getSinusoidalsphere() {
    return Sinusoidalsphere.copy();
  }

  /**
   * @return the Timessphere
   */
  public ProjectionInfo getTimessphere() {
    return Timessphere.copy();
  }

  /**
   * @return the VanderGrintenIsphere
   */
  public ProjectionInfo getVanderGrintenIsphere() {
    return VanderGrintenIsphere.copy();
  }

  /**
   * @return the VerticalPerspectivesphere
   */
  public ProjectionInfo getVerticalPerspectivesphere() {
    return VerticalPerspectivesphere.copy();
  }

  /**
   * @return the WinkelIIsphere
   */
  public ProjectionInfo getWinkelIIsphere() {
    return WinkelIIsphere.copy();
  }

  /**
   * @return the WinkelIsphere
   */
  public ProjectionInfo getWinkelIsphere() {
    return WinkelIsphere.copy();
  }

  /**
   * @return the WinkelTripelNGSsphere
   */
  public ProjectionInfo getWinkelTripelNGSsphere() {
    return WinkelTripelNGSsphere.copy();
  }


    }


