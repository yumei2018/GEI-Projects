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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:46:07 PM
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
/// NatGridsAustralia
/// </summary>

public class NationalGridsAustralia extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo AGD1966ACTGridAGCZone;
  private final ProjectionInfo AGD1966AMGZone48;
  private final ProjectionInfo AGD1966AMGZone49;
  private final ProjectionInfo AGD1966AMGZone50;
  private final ProjectionInfo AGD1966AMGZone51;
  private final ProjectionInfo AGD1966AMGZone52;
  private final ProjectionInfo AGD1966AMGZone53;
  private final ProjectionInfo AGD1966AMGZone54;
  private final ProjectionInfo AGD1966AMGZone55;
  private final ProjectionInfo AGD1966AMGZone56;
  private final ProjectionInfo AGD1966AMGZone57;
  private final ProjectionInfo AGD1966AMGZone58;
  private final ProjectionInfo AGD1966ISG542;
  private final ProjectionInfo AGD1966ISG543;
  private final ProjectionInfo AGD1966ISG551;
  private final ProjectionInfo AGD1966ISG552;
  private final ProjectionInfo AGD1966ISG553;
  private final ProjectionInfo AGD1966ISG561;
  private final ProjectionInfo AGD1966ISG562;
  private final ProjectionInfo AGD1966ISG563;
  private final ProjectionInfo AGD1966VICGRID;
  private final ProjectionInfo AGD1984AMGZone48;
  private final ProjectionInfo AGD1984AMGZone49;
  private final ProjectionInfo AGD1984AMGZone50;
  private final ProjectionInfo AGD1984AMGZone51;
  private final ProjectionInfo AGD1984AMGZone52;
  private final ProjectionInfo AGD1984AMGZone53;
  private final ProjectionInfo AGD1984AMGZone54;
  private final ProjectionInfo AGD1984AMGZone55;
  private final ProjectionInfo AGD1984AMGZone56;
  private final ProjectionInfo AGD1984AMGZone57;
  private final ProjectionInfo AGD1984AMGZone58;
  private final ProjectionInfo GDA1994MGAZone48;
  private final ProjectionInfo GDA1994MGAZone49;
  private final ProjectionInfo GDA1994MGAZone50;
  private final ProjectionInfo GDA1994MGAZone51;
  private final ProjectionInfo GDA1994MGAZone52;
  private final ProjectionInfo GDA1994MGAZone53;
  private final ProjectionInfo GDA1994MGAZone54;
  private final ProjectionInfo GDA1994MGAZone55;
  private final ProjectionInfo GDA1994MGAZone56;
  private final ProjectionInfo GDA1994MGAZone57;
  private final ProjectionInfo GDA1994MGAZone58;
  private final ProjectionInfo GDA1994SouthAustraliaLambert;
  private final ProjectionInfo GDA1994VICGRID94;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of NatGridsAustralia
  /// </summary>
  public NationalGridsAustralia() {
    AGD1966ACTGridAGCZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=149.0092948333333 +k=1.000086 +x_0=200000 +y_0=4510193.4939 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone48 = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone49 = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone50 = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone51 = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone52 = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone53 = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone54 = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone55 = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone56 = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone57 = ProjectionInfo.fromProj4String("+proj=utm +zone=57 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966AMGZone58 = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966ISG542 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=141 +k=0.999940 +x_0=300000 +y_0=5000000 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966ISG543 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=143 +k=0.999940 +x_0=300000 +y_0=5000000 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966ISG551 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=145 +k=0.999940 +x_0=300000 +y_0=5000000 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966ISG552 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=147 +k=0.999940 +x_0=300000 +y_0=5000000 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966ISG553 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=149 +k=0.999940 +x_0=300000 +y_0=5000000 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966ISG561 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=151 +k=0.999940 +x_0=300000 +y_0=5000000 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966ISG562 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=153 +k=0.999940 +x_0=300000 +y_0=5000000 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966ISG563 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=155 +k=0.999940 +x_0=300000 +y_0=5000000 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1966VICGRID = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=-36 +lat_2=-38 +lat_0=-37 +lon_0=145 +x_0=2500000 +y_0=4500000 +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone48 = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone49 = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone50 = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone51 = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone52 = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone53 = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone54 = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone55 = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone56 = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone57 = ProjectionInfo.fromProj4String("+proj=utm +zone=57 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    AGD1984AMGZone58 = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
    GDA1994MGAZone48 = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994MGAZone49 = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994MGAZone50 = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994MGAZone51 = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994MGAZone52 = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994MGAZone53 = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994MGAZone54 = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994MGAZone55 = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994MGAZone56 = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994MGAZone57 = ProjectionInfo.fromProj4String("+proj=utm +zone=57 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994MGAZone58 = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994SouthAustraliaLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=-28 +lat_2=-36 +lat_0=-32 +lon_0=135 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    GDA1994VICGRID94 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=-36 +lat_2=-38 +lat_0=-37 +lon_0=145 +x_0=2500000 +y_0=2500000 +ellps=GRS80 +units=m +no_defs ").orElse(null);

    AGD1966ACTGridAGCZone.setName("AGD_1966_ACT_Grid_AGC_Zone");
    AGD1966AMGZone48.setName("AGD_1966_AMG_Zone_48");
    AGD1966AMGZone49.setName("AGD_1966_AMG_Zone_49");
    AGD1966AMGZone50.setName("AGD_1966_AMG_Zone_50");
    AGD1966AMGZone51.setName("AGD_1966_AMG_Zone_51");
    AGD1966AMGZone52.setName("AGD_1966_AMG_Zone_52");
    AGD1966AMGZone53.setName("AGD_1966_AMG_Zone_53");
    AGD1966AMGZone54.setName("AGD_1966_AMG_Zone_54");
    AGD1966AMGZone55.setName("AGD_1966_AMG_Zone_55");
    AGD1966AMGZone56.setName("AGD_1966_AMG_Zone_56");
    AGD1966AMGZone57.setName("AGD_1966_AMG_Zone_57");
    AGD1966AMGZone58.setName("AGD_1966_AMG_Zone_58");
    AGD1966ISG542.setName("AGD_1966_ISG_54_2");
    AGD1966ISG543.setName("AGD_1966_ISG_54_3");
    AGD1966ISG551.setName("AGD_1966_ISG_55_1");
    AGD1966ISG552.setName("AGD_1966_ISG_55_2");
    AGD1966ISG553.setName("AGD_1966_ISG_55_3");
    AGD1966ISG561.setName("AGD_1966_ISG_56_1");
    AGD1966ISG562.setName("AGD_1966_ISG_56_2");
    AGD1966ISG563.setName("AGD_1966_ISG_56_3");
    AGD1966VICGRID.setName("AGD_1966_VICGRID");
    AGD1984AMGZone48.setName("AGD_1984_AMG_Zone_48");
    AGD1984AMGZone49.setName("AGD_1984_AMG_Zone_49");
    AGD1984AMGZone50.setName("AGD_1984_AMG_Zone_50");
    AGD1984AMGZone51.setName("AGD_1984_AMG_Zone_51");
    AGD1984AMGZone52.setName("AGD_1984_AMG_Zone_52");
    AGD1984AMGZone53.setName("AGD_1984_AMG_Zone_53");
    AGD1984AMGZone54.setName("AGD_1984_AMG_Zone_54");
    AGD1984AMGZone55.setName("AGD_1984_AMG_Zone_55");
    AGD1984AMGZone56.setName("AGD_1984_AMG_Zone_56");
    AGD1984AMGZone57.setName("AGD_1984_AMG_Zone_57");
    AGD1984AMGZone58.setName("AGD_1984_AMG_Zone_58");
    GDA1994MGAZone48.setName("GDA_1994_MGA_Zone_48");
    GDA1994MGAZone49.setName("GDA_1994_MGA_Zone_49");
    GDA1994MGAZone50.setName("GDA_1994_MGA_Zone_50");
    GDA1994MGAZone51.setName("GDA_1994_MGA_Zone_51");
    GDA1994MGAZone52.setName("GDA_1994_MGA_Zone_52");
    GDA1994MGAZone53.setName("GDA_1994_MGA_Zone_53");
    GDA1994MGAZone54.setName("GDA_1994_MGA_Zone_54");
    GDA1994MGAZone55.setName("GDA_1994_MGA_Zone_55");
    GDA1994MGAZone56.setName("GDA_1994_MGA_Zone_56");
    GDA1994MGAZone57.setName("GDA_1994_MGA_Zone_57");
    GDA1994MGAZone58.setName("GDA_1994_MGA_Zone_58");
    GDA1994SouthAustraliaLambert.setName("GDA_1994_South_Australia_Lambert");
    GDA1994VICGRID94.setName("GDA_1994_VICGRID94");

    AGD1966ACTGridAGCZone.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone48.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone49.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone50.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone51.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone52.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone53.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone54.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone55.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone56.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone57.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966AMGZone58.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966ISG542.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966ISG543.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966ISG551.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966ISG552.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966ISG553.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966ISG561.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966ISG562.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966ISG563.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1966VICGRID.getGeographicInfo().setName("GCS_Australian_1966");
    AGD1984AMGZone48.getGeographicInfo().setName("GCS_Australian_1984");
    AGD1984AMGZone49.getGeographicInfo().setName("GCS_Australian_1984");
    AGD1984AMGZone50.getGeographicInfo().setName("GCS_Australian_1984");
    AGD1984AMGZone51.getGeographicInfo().setName("GCS_Australian_1984");
    AGD1984AMGZone52.getGeographicInfo().setName("GCS_Australian_1984");
    AGD1984AMGZone53.getGeographicInfo().setName("GCS_Australian_1984");
    AGD1984AMGZone54.getGeographicInfo().setName("GCS_Australian_1984");
    AGD1984AMGZone55.getGeographicInfo().setName("GCS_Australian_1984");
    AGD1984AMGZone56.getGeographicInfo().setName("GCS_Australian_1984");
    AGD1984AMGZone57.getGeographicInfo().setName("GCS_Australian_1984");
    AGD1984AMGZone58.getGeographicInfo().setName("GCS_Australian_1984");
    GDA1994MGAZone48.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994MGAZone49.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994MGAZone50.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994MGAZone51.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994MGAZone52.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994MGAZone53.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994MGAZone54.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994MGAZone55.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994MGAZone56.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994MGAZone57.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994MGAZone58.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994SouthAustraliaLambert.getGeographicInfo().setName("GCS_GDA_1994");
    GDA1994VICGRID94.getGeographicInfo().setName("GCS_GDA_1994");

    AGD1966ACTGridAGCZone.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone48.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone49.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone50.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone51.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone52.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone53.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone54.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone55.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone56.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone57.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966AMGZone58.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966ISG542.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966ISG543.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966ISG551.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966ISG552.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966ISG553.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966ISG561.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966ISG562.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966ISG563.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1966VICGRID.getGeographicInfo().getDatum().setName("D_Australian_1966");
    AGD1984AMGZone48.getGeographicInfo().getDatum().setName("D_Australian_1984");
    AGD1984AMGZone49.getGeographicInfo().getDatum().setName("D_Australian_1984");
    AGD1984AMGZone50.getGeographicInfo().getDatum().setName("D_Australian_1984");
    AGD1984AMGZone51.getGeographicInfo().getDatum().setName("D_Australian_1984");
    AGD1984AMGZone52.getGeographicInfo().getDatum().setName("D_Australian_1984");
    AGD1984AMGZone53.getGeographicInfo().getDatum().setName("D_Australian_1984");
    AGD1984AMGZone54.getGeographicInfo().getDatum().setName("D_Australian_1984");
    AGD1984AMGZone55.getGeographicInfo().getDatum().setName("D_Australian_1984");
    AGD1984AMGZone56.getGeographicInfo().getDatum().setName("D_Australian_1984");
    AGD1984AMGZone57.getGeographicInfo().getDatum().setName("D_Australian_1984");
    AGD1984AMGZone58.getGeographicInfo().getDatum().setName("D_Australian_1984");
    GDA1994MGAZone48.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994MGAZone49.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994MGAZone50.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994MGAZone51.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994MGAZone52.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994MGAZone53.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994MGAZone54.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994MGAZone55.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994MGAZone56.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994MGAZone57.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994MGAZone58.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994SouthAustraliaLambert.getGeographicInfo().getDatum().setName("D_GDA_1994");
    GDA1994VICGRID94.getGeographicInfo().getDatum().setName("D_GDA_1994");
  }

  //</editor-fold>
  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the AGD1966ACTGridAGCZone
   */
  public ProjectionInfo getAGD1966ACTGridAGCZone() {
    return AGD1966ACTGridAGCZone.copy();
  }

  /**
   * @return the AGD1966AMGZone48
   */
  public ProjectionInfo getAGD1966AMGZone48() {
    return AGD1966AMGZone48.copy();
  }

  /**
   * @return the AGD1966AMGZone49
   */
  public ProjectionInfo getAGD1966AMGZone49() {
    return AGD1966AMGZone49.copy();
  }

  /**
   * @return the AGD1966AMGZone50
   */
  public ProjectionInfo getAGD1966AMGZone50() {
    return AGD1966AMGZone50.copy();
  }

  /**
   * @return the AGD1966AMGZone51
   */
  public ProjectionInfo getAGD1966AMGZone51() {
    return AGD1966AMGZone51.copy();
  }

  /**
   * @return the AGD1966AMGZone52
   */
  public ProjectionInfo getAGD1966AMGZone52() {
    return AGD1966AMGZone52.copy();
  }

  /**
   * @return the AGD1966AMGZone53
   */
  public ProjectionInfo getAGD1966AMGZone53() {
    return AGD1966AMGZone53.copy();
  }

  /**
   * @return the AGD1966AMGZone54
   */
  public ProjectionInfo getAGD1966AMGZone54() {
    return AGD1966AMGZone54.copy();
  }

  /**
   * @return the AGD1966AMGZone55
   */
  public ProjectionInfo getAGD1966AMGZone55() {
    return AGD1966AMGZone55.copy();
  }

  /**
   * @return the AGD1966AMGZone56
   */
  public ProjectionInfo getAGD1966AMGZone56() {
    return AGD1966AMGZone56.copy();
  }

  /**
   * @return the AGD1966AMGZone57
   */
  public ProjectionInfo getAGD1966AMGZone57() {
    return AGD1966AMGZone57.copy();
  }

  /**
   * @return the AGD1966AMGZone58
   */
  public ProjectionInfo getAGD1966AMGZone58() {
    return AGD1966AMGZone58.copy();
  }

  /**
   * @return the AGD1966ISG542
   */
  public ProjectionInfo getAGD1966ISG542() {
    return AGD1966ISG542.copy();
  }

  /**
   * @return the AGD1966ISG543
   */
  public ProjectionInfo getAGD1966ISG543() {
    return AGD1966ISG543.copy();
  }

  /**
   * @return the AGD1966ISG551
   */
  public ProjectionInfo getAGD1966ISG551() {
    return AGD1966ISG551.copy();
  }

  /**
   * @return the AGD1966ISG552
   */
  public ProjectionInfo getAGD1966ISG552() {
    return AGD1966ISG552.copy();
  }

  /**
   * @return the AGD1966ISG553
   */
  public ProjectionInfo getAGD1966ISG553() {
    return AGD1966ISG553.copy();
  }

  /**
   * @return the AGD1966ISG561
   */
  public ProjectionInfo getAGD1966ISG561() {
    return AGD1966ISG561.copy();
  }

  /**
   * @return the AGD1966ISG562
   */
  public ProjectionInfo getAGD1966ISG562() {
    return AGD1966ISG562.copy();
  }

  /**
   * @return the AGD1966ISG563
   */
  public ProjectionInfo getAGD1966ISG563() {
    return AGD1966ISG563.copy();
  }

  /**
   * @return the AGD1966VICGRID
   */
  public ProjectionInfo getAGD1966VICGRID() {
    return AGD1966VICGRID.copy();
  }

  /**
   * @return the AGD1984AMGZone48
   */
  public ProjectionInfo getAGD1984AMGZone48() {
    return AGD1984AMGZone48.copy();
  }

  /**
   * @return the AGD1984AMGZone49
   */
  public ProjectionInfo getAGD1984AMGZone49() {
    return AGD1984AMGZone49.copy();
  }

  /**
   * @return the AGD1984AMGZone50
   */
  public ProjectionInfo getAGD1984AMGZone50() {
    return AGD1984AMGZone50.copy();
  }

  /**
   * @return the AGD1984AMGZone51
   */
  public ProjectionInfo getAGD1984AMGZone51() {
    return AGD1984AMGZone51.copy();
  }

  /**
   * @return the AGD1984AMGZone52
   */
  public ProjectionInfo getAGD1984AMGZone52() {
    return AGD1984AMGZone52.copy();
  }

  /**
   * @return the AGD1984AMGZone53
   */
  public ProjectionInfo getAGD1984AMGZone53() {
    return AGD1984AMGZone53.copy();
  }

  /**
   * @return the AGD1984AMGZone54
   */
  public ProjectionInfo getAGD1984AMGZone54() {
    return AGD1984AMGZone54.copy();
  }

  /**
   * @return the AGD1984AMGZone55
   */
  public ProjectionInfo getAGD1984AMGZone55() {
    return AGD1984AMGZone55.copy();
  }

  /**
   * @return the AGD1984AMGZone56
   */
  public ProjectionInfo getAGD1984AMGZone56() {
    return AGD1984AMGZone56.copy();
  }

  /**
   * @return the AGD1984AMGZone57
   */
  public ProjectionInfo getAGD1984AMGZone57() {
    return AGD1984AMGZone57.copy();
  }

  /**
   * @return the AGD1984AMGZone58
   */
  public ProjectionInfo getAGD1984AMGZone58() {
    return AGD1984AMGZone58.copy();
  }

  /**
   * @return the GDA1994MGAZone48
   */
  public ProjectionInfo getGDA1994MGAZone48() {
    return GDA1994MGAZone48.copy();
  }

  /**
   * @return the GDA1994MGAZone49
   */
  public ProjectionInfo getGDA1994MGAZone49() {
    return GDA1994MGAZone49.copy();
  }

  /**
   * @return the GDA1994MGAZone50
   */
  public ProjectionInfo getGDA1994MGAZone50() {
    return GDA1994MGAZone50.copy();
  }

  /**
   * @return the GDA1994MGAZone51
   */
  public ProjectionInfo getGDA1994MGAZone51() {
    return GDA1994MGAZone51.copy();
  }

  /**
   * @return the GDA1994MGAZone52
   */
  public ProjectionInfo getGDA1994MGAZone52() {
    return GDA1994MGAZone52.copy();
  }

  /**
   * @return the GDA1994MGAZone53
   */
  public ProjectionInfo getGDA1994MGAZone53() {
    return GDA1994MGAZone53.copy();
  }

  /**
   * @return the GDA1994MGAZone54
   */
  public ProjectionInfo getGDA1994MGAZone54() {
    return GDA1994MGAZone54.copy();
  }

  /**
   * @return the GDA1994MGAZone55
   */
  public ProjectionInfo getGDA1994MGAZone55() {
    return GDA1994MGAZone55.copy();
  }

  /**
   * @return the GDA1994MGAZone56
   */
  public ProjectionInfo getGDA1994MGAZone56() {
    return GDA1994MGAZone56.copy();
  }

  /**
   * @return the GDA1994MGAZone57
   */
  public ProjectionInfo getGDA1994MGAZone57() {
    return GDA1994MGAZone57.copy();
  }

  /**
   * @return the GDA1994MGAZone58
   */
  public ProjectionInfo getGDA1994MGAZone58() {
    return GDA1994MGAZone58.copy();
  }

  /**
   * @return the GDA1994SouthAustraliaLambert
   */
  public ProjectionInfo getGDA1994SouthAustraliaLambert() {
    return GDA1994SouthAustraliaLambert.copy();
  }

  /**
   * @return the GDA1994VICGRID94
   */
  public ProjectionInfo getGDA1994VICGRID94() {
    return GDA1994VICGRID94.copy();
  }

  // </editor-fold>
}
