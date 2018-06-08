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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:03:57 PM
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
/// Antarctica
/// </summary>

public class Antarctica extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo AustralianAntarctic1998;
  private final ProjectionInfo CampAreaAstro;
  private final ProjectionInfo DeceptionIsland;
  private final ProjectionInfo Petrels1972;
  private final ProjectionInfo PointeGeologiePerroud1950;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of Antarctica
  /// </summary>
  public Antarctica() {
    AustralianAntarctic1998 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    CampAreaAstro = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    DeceptionIsland = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Petrels1972 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    PointeGeologiePerroud1950 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);

    AustralianAntarctic1998.setLatLon(true);
    CampAreaAstro.setLatLon(true);
    DeceptionIsland.setLatLon(true);
    Petrels1972.setLatLon(true);
    PointeGeologiePerroud1950.setLatLon(true);

    AustralianAntarctic1998.getGeographicInfo().setName("GCS_Australian_Antarctic_1998");
    CampAreaAstro.getGeographicInfo().setName("GCS_Camp_Area");
    DeceptionIsland.getGeographicInfo().setName("GCS_Deception_Island");
    Petrels1972.getGeographicInfo().setName("GCS_Petrels_1972");
    PointeGeologiePerroud1950.getGeographicInfo().setName("GCS_Pointe_Geologie_Perroud_1950");

    AustralianAntarctic1998.setName("GCS_Australian_Antarctic_1998");
    CampAreaAstro.setName("GCS_Camp_Area");
    DeceptionIsland.setName("GCS_Deception_Island");
    Petrels1972.setName("GCS_Petrels_1972");
    PointeGeologiePerroud1950.setName("GCS_Pointe_Geologie_Perroud_1950");

    AustralianAntarctic1998.getGeographicInfo().getDatum().setName("D_Australian_Antarctic_1998");
    CampAreaAstro.getGeographicInfo().getDatum().setName("D_Camp_Area");
    DeceptionIsland.getGeographicInfo().getDatum().setName("D_Deception_Island");
    Petrels1972.getGeographicInfo().getDatum().setName("D_Petrels_1972");
    PointeGeologiePerroud1950.getGeographicInfo().getDatum().setName("D_Pointe_Geologie_Perroud_1950");
  }

  //</editor-fold>
  /**
   * @return the AustralianAntarctic1998
   */
  public ProjectionInfo getAustralianAntarctic1998() {
    return AustralianAntarctic1998.copy();
  }

  /**
   * @return the CampAreaAstro
   */
  public ProjectionInfo getCampAreaAstro() {
    return CampAreaAstro.copy();
  }

  /**
   * @return the DeceptionIsland
   */
  public ProjectionInfo getDeceptionIsland() {
    return DeceptionIsland.copy();
  }

  /**
   * @return the Petrels1972
   */
  public ProjectionInfo getPetrels1972() {
    return Petrels1972.copy();
  }

  /**
   * @return the PointeGeologiePerroud1950
   */
  public ProjectionInfo getPointeGeologiePerroud1950() {
    return PointeGeologiePerroud1950.copy();
  }
}
