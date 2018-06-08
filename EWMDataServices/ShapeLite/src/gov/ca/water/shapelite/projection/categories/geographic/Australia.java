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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:06:28 PM
//
// Contributor(s): (Open source contributors should list themselves and their modifications here).
//        Name         |    Date    |        Comment
// --------------------|------------|------------------------------------------------------------
// Ted Dunsford        |   5/3/2010 |  Updated project to DotSpatial.Projection and license to LGPL
// ********************************************************************************************************
package gov.ca.water.shapelite.projection.categories.geographic;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

    /// <summary>
/// Australia
/// </summary>
public class Australia extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo AustralianGeodeticDatum1966;
  private final ProjectionInfo AustralianGeodeticDatum1984;
  private final ProjectionInfo ChathamIslands1979;
  private final ProjectionInfo GeocentricDatumofAustralia1994;
  private final ProjectionInfo NZGD2000;
  private final ProjectionInfo NewZealandGeodeticDatum1949;

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Constructor">
        /// <summary>
  /// Creates a new instance of Australia
  /// </summary>
  public Australia() {
    AustralianGeodeticDatum1966 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=aust_SA +no_defs ").orElse(null);
    AustralianGeodeticDatum1984 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=aust_SA +no_defs ").orElse(null);
    ChathamIslands1979 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    GeocentricDatumofAustralia1994 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    NewZealandGeodeticDatum1949 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    NZGD2000 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);

    AustralianGeodeticDatum1966.setLatLon(true);
    AustralianGeodeticDatum1984.setLatLon(true);
    ChathamIslands1979.setLatLon(true);
    GeocentricDatumofAustralia1994.setLatLon(true);
    NewZealandGeodeticDatum1949.setLatLon(true);
    NZGD2000.setLatLon(true);

    AustralianGeodeticDatum1966.getGeographicInfo().setName("GCS_Australian_1966");
    AustralianGeodeticDatum1984.getGeographicInfo().setName("GCS_Australian_1984");
    ChathamIslands1979.getGeographicInfo().setName("GCS_Chatham_Islands_1979");
    GeocentricDatumofAustralia1994.getGeographicInfo().setName("GCS_GDA_1994");
    NewZealandGeodeticDatum1949.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD2000.getGeographicInfo().setName("GCS_NZGD_2000");

    AustralianGeodeticDatum1966.setName("GCS_Australian_1966");
    AustralianGeodeticDatum1984.setName("GCS_Australian_1984");
    ChathamIslands1979.setName("GCS_Chatham_Islands_1979");
    GeocentricDatumofAustralia1994.setName("GCS_GDA_1994");
    NewZealandGeodeticDatum1949.setName("GCS_New_Zealand_1949");
    NZGD2000.setName("GCS_NZGD_2000");

    AustralianGeodeticDatum1966.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AustralianGeodeticDatum1984.getGeographicInfo().getDatum().setName("D_Australian_1984");
    ChathamIslands1979.getGeographicInfo().getDatum().setName("D_Chatham_Islands_1979");
    GeocentricDatumofAustralia1994.getGeographicInfo().getDatum().setName("D_GDA_1994");
    NewZealandGeodeticDatum1949.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD2000.getGeographicInfo().getDatum().setName("D_NZGD_2000");
  }

  //</editor-fold>

  /**
   * @return the AustralianGeodeticDatum1966
   */
  public ProjectionInfo getAustralianGeodeticDatum1966() {
    return AustralianGeodeticDatum1966.copy();
  }

  /**
   * @return the AustralianGeodeticDatum1984
   */
  public ProjectionInfo getAustralianGeodeticDatum1984() {
    return AustralianGeodeticDatum1984.copy();
  }

  /**
   * @return the ChathamIslands1979
   */
  public ProjectionInfo getChathamIslands1979() {
    return ChathamIslands1979.copy();
  }

  /**
   * @return the GeocentricDatumofAustralia1994
   */
  public ProjectionInfo getGeocentricDatumofAustralia1994() {
    return GeocentricDatumofAustralia1994.copy();
  }

  /**
   * @return the NZGD2000
   */
  public ProjectionInfo getNZGD2000() {
    return NZGD2000.copy();
  }

  /**
   * @return the NewZealandGeodeticDatum1949
   */
  public ProjectionInfo getNewZealandGeodeticDatum1949() {
    return NewZealandGeodeticDatum1949.copy();
  }
}
