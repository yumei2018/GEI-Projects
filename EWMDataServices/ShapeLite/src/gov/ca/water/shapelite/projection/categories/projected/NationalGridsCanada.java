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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:47:45 PM
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
/// NationalGridsCanada
/// </summary>

public class NationalGridsCanada extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo ATS1977MTM4NovaScotia;
  private final ProjectionInfo ATS1977MTM5NovaScotia;
  private final ProjectionInfo ATS1977NewBrunswickStereographic;
  private final ProjectionInfo NAD192710TMAEPForest;
  private final ProjectionInfo NAD192710TMAEPResource;
  private final ProjectionInfo NAD19273TM111;
  private final ProjectionInfo NAD19273TM114;
  private final ProjectionInfo NAD19273TM117;
  private final ProjectionInfo NAD19273TM120;
  private final ProjectionInfo NAD1927CGQ77MTM10SCoPQ;
  private final ProjectionInfo NAD1927CGQ77MTM2SCoPQ;
  private final ProjectionInfo NAD1927CGQ77MTM3SCoPQ;
  private final ProjectionInfo NAD1927CGQ77MTM4SCoPQ;
  private final ProjectionInfo NAD1927CGQ77MTM5SCoPQ;
  private final ProjectionInfo NAD1927CGQ77MTM6SCoPQ;
  private final ProjectionInfo NAD1927CGQ77MTM7SCoPQ;
  private final ProjectionInfo NAD1927CGQ77MTM8SCoPQ;
  private final ProjectionInfo NAD1927CGQ77MTM9SCoPQ;
  private final ProjectionInfo NAD1927CGQ77QuebecLambert;
  private final ProjectionInfo NAD1927CGQ77UTMZone17N;
  private final ProjectionInfo NAD1927CGQ77UTMZone18N;
  private final ProjectionInfo NAD1927CGQ77UTMZone19N;
  private final ProjectionInfo NAD1927CGQ77UTMZone20N;
  private final ProjectionInfo NAD1927CGQ77UTMZone21N;
  private final ProjectionInfo NAD1927DEF1976MTM10;
  private final ProjectionInfo NAD1927DEF1976MTM11;
  private final ProjectionInfo NAD1927DEF1976MTM12;
  private final ProjectionInfo NAD1927DEF1976MTM13;
  private final ProjectionInfo NAD1927DEF1976MTM14;
  private final ProjectionInfo NAD1927DEF1976MTM15;
  private final ProjectionInfo NAD1927DEF1976MTM16;
  private final ProjectionInfo NAD1927DEF1976MTM17;
  private final ProjectionInfo NAD1927DEF1976MTM8;
  private final ProjectionInfo NAD1927DEF1976MTM9;
  private final ProjectionInfo NAD1927DEF1976UTMZone15N;
  private final ProjectionInfo NAD1927DEF1976UTMZone16N;
  private final ProjectionInfo NAD1927DEF1976UTMZone17N;
  private final ProjectionInfo NAD1927DEF1976UTMZone18N;
  private final ProjectionInfo NAD1927MTM1;
  private final ProjectionInfo NAD1927MTM2;
  private final ProjectionInfo NAD1927MTM3;
  private final ProjectionInfo NAD1927MTM4;
  private final ProjectionInfo NAD1927MTM5;
  private final ProjectionInfo NAD1927MTM6;
  private final ProjectionInfo NAD1927QuebecLambert;
  private final ProjectionInfo NAD198310TMAEPForest;
  private final ProjectionInfo NAD198310TMAEPResource;
  private final ProjectionInfo NAD19833TM111;
  private final ProjectionInfo NAD19833TM114;
  private final ProjectionInfo NAD19833TM117;
  private final ProjectionInfo NAD19833TM120;
  private final ProjectionInfo NAD1983BCEnvironmentAlbers;
  private final ProjectionInfo NAD1983CSRS98MTM10;
  private final ProjectionInfo NAD1983CSRS98MTM2SCoPQ;
  private final ProjectionInfo NAD1983CSRS98MTM3;
  private final ProjectionInfo NAD1983CSRS98MTM4;
  private final ProjectionInfo NAD1983CSRS98MTM5;
  private final ProjectionInfo NAD1983CSRS98MTM6;
  private final ProjectionInfo NAD1983CSRS98MTM7;
  private final ProjectionInfo NAD1983CSRS98MTM8;
  private final ProjectionInfo NAD1983CSRS98MTM9;
  private final ProjectionInfo NAD1983CSRS98NewBrunswickStereographic;
  private final ProjectionInfo NAD1983CSRS98PrinceEdwardIsland;
  private final ProjectionInfo NAD1983CSRS98UTMZone11N;
  private final ProjectionInfo NAD1983CSRS98UTMZone12N;
  private final ProjectionInfo NAD1983CSRS98UTMZone13N;
  private final ProjectionInfo NAD1983CSRS98UTMZone17N;
  private final ProjectionInfo NAD1983CSRS98UTMZone18N;
  private final ProjectionInfo NAD1983CSRS98UTMZone19N;
  private final ProjectionInfo NAD1983CSRS98UTMZone20N;
  private final ProjectionInfo NAD1983CSRS98UTMZone21N;
  private final ProjectionInfo NAD1983MTM1;
  private final ProjectionInfo NAD1983MTM10;
  private final ProjectionInfo NAD1983MTM11;
  private final ProjectionInfo NAD1983MTM12;
  private final ProjectionInfo NAD1983MTM13;
  private final ProjectionInfo NAD1983MTM14;
  private final ProjectionInfo NAD1983MTM15;
  private final ProjectionInfo NAD1983MTM16;
  private final ProjectionInfo NAD1983MTM17;
  private final ProjectionInfo NAD1983MTM2;
  private final ProjectionInfo NAD1983MTM2SCoPQ;
  private final ProjectionInfo NAD1983MTM3;
  private final ProjectionInfo NAD1983MTM4;
  private final ProjectionInfo NAD1983MTM5;
  private final ProjectionInfo NAD1983MTM6;
  private final ProjectionInfo NAD1983MTM7;
  private final ProjectionInfo NAD1983MTM8;
  private final ProjectionInfo NAD1983MTM9;
  private final ProjectionInfo NAD1983QuebecLambert;
  private final ProjectionInfo PrinceEdwardIslandStereographic;

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of NationalGridsCanada
  /// </summary>
  public NationalGridsCanada() {
    ATS1977MTM4NovaScotia = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-61.5 +k=0.999900 +x_0=4500000 +y_0=0 +a=6378135 +b=6356750.304921594 +units=m +no_defs ").orElse(null);
    ATS1977MTM5NovaScotia = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-64.5 +k=0.999900 +x_0=5500000 +y_0=0 +a=6378135 +b=6356750.304921594 +units=m +no_defs ").orElse(null);
    ATS1977NewBrunswickStereographic = ProjectionInfo.fromProj4String("+a=6378135 +b=6356750.304921594 +units=m +no_defs ").orElse(null);
    NAD192710TMAEPForest = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-115 +k=0.999200 +x_0=500000 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD192710TMAEPResource = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-115 +k=0.999200 +x_0=0 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD19273TM111 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-111 +k=0.999900 +x_0=0 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD19273TM114 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-114 +k=0.999900 +x_0=0 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD19273TM117 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-117 +k=0.999900 +x_0=0 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD19273TM120 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-120 +k=0.999900 +x_0=0 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77MTM10SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-79.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77MTM2SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-55.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77MTM3SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-58.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77MTM4SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-61.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77MTM5SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-64.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77MTM6SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-67.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77MTM7SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-70.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77MTM8SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-73.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77MTM9SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-76.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77QuebecLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46 +lat_2=60 +lat_0=44 +lon_0=-68.5 +x_0=0 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77UTMZone17N = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77UTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77UTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927CGQ77UTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976MTM10 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-79.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976MTM11 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-82.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976MTM12 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-81 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976MTM13 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-84 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976MTM14 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-87 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976MTM15 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-90 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976MTM16 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-93 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976MTM17 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-96 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976MTM8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-73.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976MTM9 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-76.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976UTMZone15N = ProjectionInfo.fromProj4String("+proj=utm +zone=15 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976UTMZone16N = ProjectionInfo.fromProj4String("+proj=utm +zone=16 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976UTMZone17N = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927DEF1976UTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=clrk66 +units=m +no_defs ").orElse(null);
    NAD1927MTM1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-53 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD1927MTM2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-56 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD1927MTM3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-58.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD1927MTM4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-61.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD1927MTM5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-64.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD1927MTM6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-67.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD1927QuebecLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46 +lat_2=60 +lat_0=44 +lon_0=-68.5 +x_0=0 +y_0=0 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
    NAD198310TMAEPForest = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-115 +k=0.999200 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD198310TMAEPResource = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-115 +k=0.999200 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD19833TM111 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-111 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD19833TM114 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-114 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD19833TM117 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-117 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD19833TM120 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-120 +k=0.999900 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983BCEnvironmentAlbers = ProjectionInfo.fromProj4String("+proj=aea +lat_1=50 +lat_2=58.5 +lat_0=45 +lon_0=-126 +x_0=1000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98MTM10 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-79.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98MTM2SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-55.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98MTM3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-58.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98MTM4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-61.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98MTM5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-64.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98MTM6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-67.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98MTM7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-70.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98MTM8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-73.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98MTM9 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-76.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98NewBrunswickStereographic = ProjectionInfo.fromProj4String("+ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98PrinceEdwardIsland = ProjectionInfo.fromProj4String("+ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98UTMZone11N = ProjectionInfo.fromProj4String("+proj=utm +zone=11 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98UTMZone12N = ProjectionInfo.fromProj4String("+proj=utm +zone=12 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98UTMZone13N = ProjectionInfo.fromProj4String("+proj=utm +zone=13 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98UTMZone17N = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98UTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98UTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983CSRS98UTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983MTM1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-53 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM10 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-79.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM11 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-82.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM12 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-81 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM13 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-84 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM14 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-87 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM15 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-90 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM16 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-93 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM17 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-96 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-56 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM2SCoPQ = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-55.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-58.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-61.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-64.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-67.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-70.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-73.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983MTM9 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-76.5 +k=0.999900 +x_0=304800 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983QuebecLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46 +lat_2=60 +lat_0=44 +lon_0=-68.5 +x_0=0 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    PrinceEdwardIslandStereographic = ProjectionInfo.fromProj4String("+a=6378135 +b=6356750.304921594 +units=m +no_defs ").orElse(null);

    ATS1977MTM4NovaScotia.setName("ATS_1977_MTM_4_Nova_Scotia");
    ATS1977MTM5NovaScotia.setName("ATS_1977_MTM_5_Nova_Scotia");
    ATS1977NewBrunswickStereographic.setName("ATS_1977_New_Brunswick_Stereographic");
    NAD192710TMAEPForest.setName("NAD_1927_10TM_AEP_Forest");
    NAD192710TMAEPResource.setName("NAD_1927_10TM_AEP_Resource");
    NAD19273TM111.setName("NAD_1927_3TM_111");
    NAD19273TM114.setName("NAD_1927_3TM_114");
    NAD19273TM117.setName("NAD_1927_3TM_117");
    NAD19273TM120.setName("NAD_1927_3TM_120");
    NAD1927CGQ77MTM10SCoPQ.setName("NAD_1927_CGQ77_MTM_10_SCoPQ");
    NAD1927CGQ77MTM2SCoPQ.setName("NAD_1927_CGQ77_MTM_2_SCoPQ");
    NAD1927CGQ77MTM3SCoPQ.setName("NAD_1927_CGQ77_MTM_3_SCoPQ");
    NAD1927CGQ77MTM4SCoPQ.setName("NAD_1927_CGQ77_MTM_4_SCoPQ");
    NAD1927CGQ77MTM5SCoPQ.setName("NAD_1927_CGQ77_MTM_5_SCoPQ");
    NAD1927CGQ77MTM6SCoPQ.setName("NAD_1927_CGQ77_MTM_6_SCoPQ");
    NAD1927CGQ77MTM7SCoPQ.setName("NAD_1927_CGQ77_MTM_7_SCoPQ");
    NAD1927CGQ77MTM8SCoPQ.setName("NAD_1927_CGQ77_MTM_8_SCoPQ");
    NAD1927CGQ77MTM9SCoPQ.setName("NAD_1927_CGQ77_MTM_9_SCoPQ");
    NAD1927CGQ77QuebecLambert.setName("NAD_1927_CGQ77_Quebec_Lambert");
    NAD1927CGQ77UTMZone17N.setName("NAD_1927_CGQ77_UTM_Zone_17N");
    NAD1927CGQ77UTMZone18N.setName("NAD_1927_CGQ77_UTM_Zone_18N");
    NAD1927CGQ77UTMZone19N.setName("NAD_1927_CGQ77_UTM_Zone_19N");
    NAD1927CGQ77UTMZone20N.setName("NAD_1927_CGQ77_UTM_Zone_20N");
    NAD1927CGQ77UTMZone21N.setName("NAD_1927_CGQ77_UTM_Zone_21N");
    NAD1927DEF1976MTM10.setName("NAD_1927_DEF_1976_MTM_10");
    NAD1927DEF1976MTM11.setName("NAD_1927_DEF_1976_MTM_11");
    NAD1927DEF1976MTM12.setName("NAD_1927_DEF_1976_MTM_12");
    NAD1927DEF1976MTM13.setName("NAD_1927_DEF_1976_MTM_13");
    NAD1927DEF1976MTM14.setName("NAD_1927_DEF_1976_MTM_14");
    NAD1927DEF1976MTM15.setName("NAD_1927_DEF_1976_MTM_15");
    NAD1927DEF1976MTM16.setName("NAD_1927_DEF_1976_MTM_16");
    NAD1927DEF1976MTM17.setName("NAD_1927_DEF_1976_MTM_17");
    NAD1927DEF1976MTM8.setName("NAD_1927_DEF_1976_MTM_8");
    NAD1927DEF1976MTM9.setName("NAD_1927_DEF_1976_MTM_9");
    NAD1927DEF1976UTMZone15N.setName("NAD_1927_DEF_1976_UTM_Zone_15N");
    NAD1927DEF1976UTMZone16N.setName("NAD_1927_DEF_1976_UTM_Zone_16N");
    NAD1927DEF1976UTMZone17N.setName("NAD_1927_DEF_1976_UTM_Zone_17N");
    NAD1927DEF1976UTMZone18N.setName("NAD_1927_DEF_1976_UTM_Zone_18N");
    NAD1927MTM1.setName("NAD_1927_MTM_1");
    NAD1927MTM2.setName("NAD_1927_MTM_2");
    NAD1927MTM3.setName("NAD_1927_MTM_3");
    NAD1927MTM4.setName("NAD_1927_MTM_4");
    NAD1927MTM5.setName("NAD_1927_MTM_5");
    NAD1927MTM6.setName("NAD_1927_MTM_6");
    NAD1927QuebecLambert.setName("NAD_1927_Quebec_Lambert");
    NAD198310TMAEPForest.setName("NAD_1983_10TM_AEP_Forest");
    NAD198310TMAEPResource.setName("NAD_1983_10TM_AEP_Resource");
    NAD19833TM111.setName("NAD_1983_3TM_111");
    NAD19833TM114.setName("NAD_1983_3TM_114");
    NAD19833TM117.setName("NAD_1983_3TM_117");
    NAD19833TM120.setName("NAD_1983_3TM_120");
    NAD1983BCEnvironmentAlbers.setName("NAD_1983_BC_Environment_Albers");
    NAD1983CSRS98MTM10.setName("NAD_1983_CRS98_MTM_10");
    NAD1983CSRS98MTM2SCoPQ.setName("NAD_1983_CSRS98_MTM_2_SCoPQ");
    NAD1983CSRS98MTM3.setName("NAD_1983_CRS98_MTM_3");
    NAD1983CSRS98MTM4.setName("NAD_1983_CRS98_MTM_4");
    NAD1983CSRS98MTM5.setName("NAD_1983_CRS98_MTM_5");
    NAD1983CSRS98MTM6.setName("NAD_1983_CRS98_MTM_6");
    NAD1983CSRS98MTM7.setName("NAD_1983_CRS98_MTM_7");
    NAD1983CSRS98MTM8.setName("NAD_1983_CRS98_MTM_8");
    NAD1983CSRS98MTM9.setName("NAD_1983_CRS98_MTM_9");
    NAD1983CSRS98NewBrunswickStereographic.setName("NAD_1983_CSRS98_New_Brunswick_Stereographic");
    NAD1983CSRS98PrinceEdwardIsland.setName("NAD_1983_CSRS98_Prince_Edward_Island");
    NAD1983CSRS98UTMZone11N.setName("NAD_1983_CSRS98_UTM_Zone_11N");
    NAD1983CSRS98UTMZone12N.setName("NAD_1983_CSRS98_UTM_Zone_12N");
    NAD1983CSRS98UTMZone13N.setName("NAD_1983_CSRS98_UTM_Zone_13N");
    NAD1983CSRS98UTMZone17N.setName("NAD_1983_CSRS98_UTM_Zone_17N");
    NAD1983CSRS98UTMZone18N.setName("NAD_1983_CSRS98_UTM_Zone_18N");
    NAD1983CSRS98UTMZone19N.setName("NAD_1983_CSRS98_UTM_Zone_19N");
    NAD1983CSRS98UTMZone20N.setName("NAD_1983_CSRS98_UTM_Zone_20N");
    NAD1983CSRS98UTMZone21N.setName("NAD_1983_CSRS98_UTM_Zone_21N");
    NAD1983MTM1.setName("NAD_1983_MTM_1");
    NAD1983MTM10.setName("NAD_1983_MTM_10");
    NAD1983MTM11.setName("NAD_1983_MTM_11");
    NAD1983MTM12.setName("NAD_1983_MTM_12");
    NAD1983MTM13.setName("NAD_1983_MTM_13");
    NAD1983MTM14.setName("NAD_1983_MTM_14");
    NAD1983MTM15.setName("NAD_1983_MTM_15");
    NAD1983MTM16.setName("NAD_1983_MTM_16");
    NAD1983MTM17.setName("NAD_1983_MTM_17");
    NAD1983MTM2.setName("NAD_1983_MTM_2");
    NAD1983MTM2SCoPQ.setName("NAD_1983_MTM_2_SCoPQ");
    NAD1983MTM3.setName("NAD_1983_MTM_3");
    NAD1983MTM4.setName("NAD_1983_MTM_4");
    NAD1983MTM5.setName("NAD_1983_MTM_5");
    NAD1983MTM6.setName("NAD_1983_MTM_6");
    NAD1983MTM7.setName("NAD_1983_MTM_7");
    NAD1983MTM8.setName("NAD_1983_MTM_8");
    NAD1983MTM9.setName("NAD_1983_MTM_9");
    NAD1983QuebecLambert.setName("NAD_1983_Quebec_Lambert");
    PrinceEdwardIslandStereographic.setName("Prince_Edward_Island_Stereographic");

    ATS1977MTM4NovaScotia.getGeographicInfo().setName("GCS_ATS_1977");
    ATS1977MTM5NovaScotia.getGeographicInfo().setName("GCS_ATS_1977");
    ATS1977NewBrunswickStereographic.getGeographicInfo().setName("GCS_ATS_1977");
    NAD192710TMAEPForest.getGeographicInfo().setName("GCS_North_American_1927");
    NAD192710TMAEPResource.getGeographicInfo().setName("GCS_North_American_1927");
    NAD19273TM111.getGeographicInfo().setName("GCS_North_American_1927");
    NAD19273TM114.getGeographicInfo().setName("GCS_North_American_1927");
    NAD19273TM117.getGeographicInfo().setName("GCS_North_American_1927");
    NAD19273TM120.getGeographicInfo().setName("GCS_North_American_1927");
    NAD1927CGQ77MTM10SCoPQ.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77MTM2SCoPQ.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77MTM3SCoPQ.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77MTM4SCoPQ.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77MTM5SCoPQ.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77MTM6SCoPQ.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77MTM7SCoPQ.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77MTM8SCoPQ.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77MTM9SCoPQ.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77QuebecLambert.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77UTMZone17N.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77UTMZone18N.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77UTMZone19N.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77UTMZone20N.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927CGQ77UTMZone21N.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927DEF1976MTM10.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM11.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM12.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM13.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM14.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM15.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM16.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM17.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM8.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM9.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976UTMZone15N.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976UTMZone16N.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976UTMZone17N.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927DEF1976UTMZone18N.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NAD1927MTM1.getGeographicInfo().setName("GCS_North_American_1927");
    NAD1927MTM2.getGeographicInfo().setName("GCS_North_American_1927");
    NAD1927MTM3.getGeographicInfo().setName("GCS_North_American_1927");
    NAD1927MTM4.getGeographicInfo().setName("GCS_North_American_1927");
    NAD1927MTM5.getGeographicInfo().setName("GCS_North_American_1927");
    NAD1927MTM6.getGeographicInfo().setName("GCS_North_American_1927");
    NAD1927QuebecLambert.getGeographicInfo().setName("GCS_North_American_1927");
    NAD198310TMAEPForest.getGeographicInfo().setName("GCS_North_American_1983");
    NAD198310TMAEPResource.getGeographicInfo().setName("GCS_North_American_1983");
    NAD19833TM111.getGeographicInfo().setName("GCS_North_American_1983");
    NAD19833TM114.getGeographicInfo().setName("GCS_North_American_1983");
    NAD19833TM117.getGeographicInfo().setName("GCS_North_American_1983");
    NAD19833TM120.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983BCEnvironmentAlbers.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983CSRS98MTM10.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98MTM2SCoPQ.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98MTM3.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98MTM4.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98MTM5.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98MTM6.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98MTM7.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98MTM8.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98MTM9.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98NewBrunswickStereographic.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98PrinceEdwardIsland.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone11N.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone12N.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone13N.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone17N.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone18N.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone19N.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone20N.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone21N.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NAD1983MTM1.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM10.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM11.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM12.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM13.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM14.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM15.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM16.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM17.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM2.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM2SCoPQ.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM3.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM4.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM5.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM6.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM7.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM8.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983MTM9.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983QuebecLambert.getGeographicInfo().setName("GCS_North_American_1983");
    PrinceEdwardIslandStereographic.getGeographicInfo().setName("GCS_ATS_1977");

    ATS1977MTM4NovaScotia.getGeographicInfo().getDatum().setName("D_ATS_1977");
    ATS1977MTM5NovaScotia.getGeographicInfo().getDatum().setName("D_ATS_1977");
    ATS1977NewBrunswickStereographic.getGeographicInfo().getDatum().setName("D_ATS_1977");
    NAD192710TMAEPForest.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD192710TMAEPResource.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD19273TM111.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD19273TM114.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD19273TM117.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD19273TM120.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD1927CGQ77MTM10SCoPQ.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77MTM2SCoPQ.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77MTM3SCoPQ.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77MTM4SCoPQ.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77MTM5SCoPQ.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77MTM6SCoPQ.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77MTM7SCoPQ.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77MTM8SCoPQ.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77MTM9SCoPQ.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77QuebecLambert.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77UTMZone17N.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77UTMZone18N.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77UTMZone19N.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77UTMZone20N.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927CGQ77UTMZone21N.getGeographicInfo().getDatum().setName("D_NAD_1927_CGQ77");
    NAD1927DEF1976MTM10.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM11.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM12.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM13.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM14.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM15.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM16.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM17.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM8.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976MTM9.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976UTMZone15N.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976UTMZone16N.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976UTMZone17N.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927DEF1976UTMZone18N.getGeographicInfo().getDatum().setName("D_NAD_1927_Definition_1976");
    NAD1927MTM1.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD1927MTM2.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD1927MTM3.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD1927MTM4.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD1927MTM5.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD1927MTM6.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD1927QuebecLambert.getGeographicInfo().getDatum().setName("D_North_American_1927");
    NAD198310TMAEPForest.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD198310TMAEPResource.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD19833TM111.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD19833TM114.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD19833TM117.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD19833TM120.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983BCEnvironmentAlbers.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983CSRS98MTM10.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98MTM2SCoPQ.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98MTM3.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98MTM4.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98MTM5.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98MTM6.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98MTM7.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98MTM8.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98MTM9.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98NewBrunswickStereographic.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98PrinceEdwardIsland.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone11N.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone12N.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone13N.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone17N.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone18N.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone19N.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone20N.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983CSRS98UTMZone21N.getGeographicInfo().getDatum().setName("D_North_American_1983_CSRS98");
    NAD1983MTM1.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM10.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM11.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM12.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM13.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM14.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM15.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM16.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM17.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM2.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM2SCoPQ.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM3.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM4.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM5.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM6.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM7.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM8.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983MTM9.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983QuebecLambert.getGeographicInfo().getDatum().setName("D_North_American_1983");
    PrinceEdwardIslandStereographic.getGeographicInfo().getDatum().setName("D_ATS_1977");
  }

  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the ATS1977MTM4NovaScotia
   */
  public ProjectionInfo getATS1977MTM4NovaScotia() {
    return ATS1977MTM4NovaScotia.copy();
  }

  /**
   * @return the ATS1977MTM5NovaScotia
   */
  public ProjectionInfo getATS1977MTM5NovaScotia() {
    return ATS1977MTM5NovaScotia.copy();
  }

  /**
   * @return the ATS1977NewBrunswickStereographic
   */
  public ProjectionInfo getATS1977NewBrunswickStereographic() {
    return ATS1977NewBrunswickStereographic.copy();
  }

  /**
   * @return the NAD192710TMAEPForest
   */
  public ProjectionInfo getNAD192710TMAEPForest() {
    return NAD192710TMAEPForest.copy();
  }

  /**
   * @return the NAD192710TMAEPResource
   */
  public ProjectionInfo getNAD192710TMAEPResource() {
    return NAD192710TMAEPResource.copy();
  }

  /**
   * @return the NAD19273TM111
   */
  public ProjectionInfo getNAD19273TM111() {
    return NAD19273TM111.copy();
  }

  /**
   * @return the NAD19273TM114
   */
  public ProjectionInfo getNAD19273TM114() {
    return NAD19273TM114.copy();
  }

  /**
   * @return the NAD19273TM117
   */
  public ProjectionInfo getNAD19273TM117() {
    return NAD19273TM117.copy();
  }

  /**
   * @return the NAD19273TM120
   */
  public ProjectionInfo getNAD19273TM120() {
    return NAD19273TM120.copy();
  }

  /**
   * @return the NAD1927CGQ77MTM10SCoPQ
   */
  public ProjectionInfo getNAD1927CGQ77MTM10SCoPQ() {
    return NAD1927CGQ77MTM10SCoPQ.copy();
  }

  /**
   * @return the NAD1927CGQ77MTM2SCoPQ
   */
  public ProjectionInfo getNAD1927CGQ77MTM2SCoPQ() {
    return NAD1927CGQ77MTM2SCoPQ.copy();
  }

  /**
   * @return the NAD1927CGQ77MTM3SCoPQ
   */
  public ProjectionInfo getNAD1927CGQ77MTM3SCoPQ() {
    return NAD1927CGQ77MTM3SCoPQ.copy();
  }

  /**
   * @return the NAD1927CGQ77MTM4SCoPQ
   */
  public ProjectionInfo getNAD1927CGQ77MTM4SCoPQ() {
    return NAD1927CGQ77MTM4SCoPQ.copy();
  }

  /**
   * @return the NAD1927CGQ77MTM5SCoPQ
   */
  public ProjectionInfo getNAD1927CGQ77MTM5SCoPQ() {
    return NAD1927CGQ77MTM5SCoPQ.copy();
  }

  /**
   * @return the NAD1927CGQ77MTM6SCoPQ
   */
  public ProjectionInfo getNAD1927CGQ77MTM6SCoPQ() {
    return NAD1927CGQ77MTM6SCoPQ.copy();
  }

  /**
   * @return the NAD1927CGQ77MTM7SCoPQ
   */
  public ProjectionInfo getNAD1927CGQ77MTM7SCoPQ() {
    return NAD1927CGQ77MTM7SCoPQ.copy();
  }

  /**
   * @return the NAD1927CGQ77MTM8SCoPQ
   */
  public ProjectionInfo getNAD1927CGQ77MTM8SCoPQ() {
    return NAD1927CGQ77MTM8SCoPQ.copy();
  }

  /**
   * @return the NAD1927CGQ77MTM9SCoPQ
   */
  public ProjectionInfo getNAD1927CGQ77MTM9SCoPQ() {
    return NAD1927CGQ77MTM9SCoPQ.copy();
  }

  /**
   * @return the NAD1927CGQ77QuebecLambert
   */
  public ProjectionInfo getNAD1927CGQ77QuebecLambert() {
    return NAD1927CGQ77QuebecLambert.copy();
  }

  /**
   * @return the NAD1927CGQ77UTMZone17N
   */
  public ProjectionInfo getNAD1927CGQ77UTMZone17N() {
    return NAD1927CGQ77UTMZone17N.copy();
  }

  /**
   * @return the NAD1927CGQ77UTMZone18N
   */
  public ProjectionInfo getNAD1927CGQ77UTMZone18N() {
    return NAD1927CGQ77UTMZone18N.copy();
  }

  /**
   * @return the NAD1927CGQ77UTMZone19N
   */
  public ProjectionInfo getNAD1927CGQ77UTMZone19N() {
    return NAD1927CGQ77UTMZone19N.copy();
  }

  /**
   * @return the NAD1927CGQ77UTMZone20N
   */
  public ProjectionInfo getNAD1927CGQ77UTMZone20N() {
    return NAD1927CGQ77UTMZone20N.copy();
  }

  /**
   * @return the NAD1927CGQ77UTMZone21N
   */
  public ProjectionInfo getNAD1927CGQ77UTMZone21N() {
    return NAD1927CGQ77UTMZone21N.copy();
  }

  /**
   * @return the NAD1927DEF1976MTM10
   */
  public ProjectionInfo getNAD1927DEF1976MTM10() {
    return NAD1927DEF1976MTM10.copy();
  }

  /**
   * @return the NAD1927DEF1976MTM11
   */
  public ProjectionInfo getNAD1927DEF1976MTM11() {
    return NAD1927DEF1976MTM11.copy();
  }

  /**
   * @return the NAD1927DEF1976MTM12
   */
  public ProjectionInfo getNAD1927DEF1976MTM12() {
    return NAD1927DEF1976MTM12.copy();
  }

  /**
   * @return the NAD1927DEF1976MTM13
   */
  public ProjectionInfo getNAD1927DEF1976MTM13() {
    return NAD1927DEF1976MTM13.copy();
  }

  /**
   * @return the NAD1927DEF1976MTM14
   */
  public ProjectionInfo getNAD1927DEF1976MTM14() {
    return NAD1927DEF1976MTM14.copy();
  }

  /**
   * @return the NAD1927DEF1976MTM15
   */
  public ProjectionInfo getNAD1927DEF1976MTM15() {
    return NAD1927DEF1976MTM15.copy();
  }

  /**
   * @return the NAD1927DEF1976MTM16
   */
  public ProjectionInfo getNAD1927DEF1976MTM16() {
    return NAD1927DEF1976MTM16.copy();
  }

  /**
   * @return the NAD1927DEF1976MTM17
   */
  public ProjectionInfo getNAD1927DEF1976MTM17() {
    return NAD1927DEF1976MTM17.copy();
  }

  /**
   * @return the NAD1927DEF1976MTM8
   */
  public ProjectionInfo getNAD1927DEF1976MTM8() {
    return NAD1927DEF1976MTM8.copy();
  }

  /**
   * @return the NAD1927DEF1976MTM9
   */
  public ProjectionInfo getNAD1927DEF1976MTM9() {
    return NAD1927DEF1976MTM9.copy();
  }

  /**
   * @return the NAD1927DEF1976UTMZone15N
   */
  public ProjectionInfo getNAD1927DEF1976UTMZone15N() {
    return NAD1927DEF1976UTMZone15N.copy();
  }

  /**
   * @return the NAD1927DEF1976UTMZone16N
   */
  public ProjectionInfo getNAD1927DEF1976UTMZone16N() {
    return NAD1927DEF1976UTMZone16N.copy();
  }

  /**
   * @return the NAD1927DEF1976UTMZone17N
   */
  public ProjectionInfo getNAD1927DEF1976UTMZone17N() {
    return NAD1927DEF1976UTMZone17N.copy();
  }

  /**
   * @return the NAD1927DEF1976UTMZone18N
   */
  public ProjectionInfo getNAD1927DEF1976UTMZone18N() {
    return NAD1927DEF1976UTMZone18N.copy();
  }

  /**
   * @return the NAD1927MTM1
   */
  public ProjectionInfo getNAD1927MTM1() {
    return NAD1927MTM1.copy();
  }

  /**
   * @return the NAD1927MTM2
   */
  public ProjectionInfo getNAD1927MTM2() {
    return NAD1927MTM2.copy();
  }

  /**
   * @return the NAD1927MTM3
   */
  public ProjectionInfo getNAD1927MTM3() {
    return NAD1927MTM3.copy();
  }

  /**
   * @return the NAD1927MTM4
   */
  public ProjectionInfo getNAD1927MTM4() {
    return NAD1927MTM4.copy();
  }

  /**
   * @return the NAD1927MTM5
   */
  public ProjectionInfo getNAD1927MTM5() {
    return NAD1927MTM5.copy();
  }

  /**
   * @return the NAD1927MTM6
   */
  public ProjectionInfo getNAD1927MTM6() {
    return NAD1927MTM6.copy();
  }

  /**
   * @return the NAD1927QuebecLambert
   */
  public ProjectionInfo getNAD1927QuebecLambert() {
    return NAD1927QuebecLambert.copy();
  }

  /**
   * @return the NAD198310TMAEPForest
   */
  public ProjectionInfo getNAD198310TMAEPForest() {
    return NAD198310TMAEPForest.copy();
  }

  /**
   * @return the NAD198310TMAEPResource
   */
  public ProjectionInfo getNAD198310TMAEPResource() {
    return NAD198310TMAEPResource.copy();
  }

  /**
   * @return the NAD19833TM111
   */
  public ProjectionInfo getNAD19833TM111() {
    return NAD19833TM111.copy();
  }

  /**
   * @return the NAD19833TM114
   */
  public ProjectionInfo getNAD19833TM114() {
    return NAD19833TM114.copy();
  }

  /**
   * @return the NAD19833TM117
   */
  public ProjectionInfo getNAD19833TM117() {
    return NAD19833TM117.copy();
  }

  /**
   * @return the NAD19833TM120
   */
  public ProjectionInfo getNAD19833TM120() {
    return NAD19833TM120.copy();
  }

  /**
   * @return the NAD1983BCEnvironmentAlbers
   */
  public ProjectionInfo getNAD1983BCEnvironmentAlbers() {
    return NAD1983BCEnvironmentAlbers.copy();
  }

  /**
   * @return the NAD1983CSRS98MTM10
   */
  public ProjectionInfo getNAD1983CSRS98MTM10() {
    return NAD1983CSRS98MTM10.copy();
  }

  /**
   * @return the NAD1983CSRS98MTM2SCoPQ
   */
  public ProjectionInfo getNAD1983CSRS98MTM2SCoPQ() {
    return NAD1983CSRS98MTM2SCoPQ.copy();
  }

  /**
   * @return the NAD1983CSRS98MTM3
   */
  public ProjectionInfo getNAD1983CSRS98MTM3() {
    return NAD1983CSRS98MTM3.copy();
  }

  /**
   * @return the NAD1983CSRS98MTM4
   */
  public ProjectionInfo getNAD1983CSRS98MTM4() {
    return NAD1983CSRS98MTM4.copy();
  }

  /**
   * @return the NAD1983CSRS98MTM5
   */
  public ProjectionInfo getNAD1983CSRS98MTM5() {
    return NAD1983CSRS98MTM5.copy();
  }

  /**
   * @return the NAD1983CSRS98MTM6
   */
  public ProjectionInfo getNAD1983CSRS98MTM6() {
    return NAD1983CSRS98MTM6.copy();
  }

  /**
   * @return the NAD1983CSRS98MTM7
   */
  public ProjectionInfo getNAD1983CSRS98MTM7() {
    return NAD1983CSRS98MTM7.copy();
  }

  /**
   * @return the NAD1983CSRS98MTM8
   */
  public ProjectionInfo getNAD1983CSRS98MTM8() {
    return NAD1983CSRS98MTM8.copy();
  }

  /**
   * @return the NAD1983CSRS98MTM9
   */
  public ProjectionInfo getNAD1983CSRS98MTM9() {
    return NAD1983CSRS98MTM9.copy();
  }

  /**
   * @return the NAD1983CSRS98NewBrunswickStereographic
   */
  public ProjectionInfo getNAD1983CSRS98NewBrunswickStereographic() {
    return NAD1983CSRS98NewBrunswickStereographic.copy();
  }

  /**
   * @return the NAD1983CSRS98PrinceEdwardIsland
   */
  public ProjectionInfo getNAD1983CSRS98PrinceEdwardIsland() {
    return NAD1983CSRS98PrinceEdwardIsland.copy();
  }

  /**
   * @return the NAD1983CSRS98UTMZone11N
   */
  public ProjectionInfo getNAD1983CSRS98UTMZone11N() {
    return NAD1983CSRS98UTMZone11N.copy();
  }

  /**
   * @return the NAD1983CSRS98UTMZone12N
   */
  public ProjectionInfo getNAD1983CSRS98UTMZone12N() {
    return NAD1983CSRS98UTMZone12N.copy();
  }

  /**
   * @return the NAD1983CSRS98UTMZone13N
   */
  public ProjectionInfo getNAD1983CSRS98UTMZone13N() {
    return NAD1983CSRS98UTMZone13N.copy();
  }

  /**
   * @return the NAD1983CSRS98UTMZone17N
   */
  public ProjectionInfo getNAD1983CSRS98UTMZone17N() {
    return NAD1983CSRS98UTMZone17N.copy();
  }

  /**
   * @return the NAD1983CSRS98UTMZone18N
   */
  public ProjectionInfo getNAD1983CSRS98UTMZone18N() {
    return NAD1983CSRS98UTMZone18N.copy();
  }

  /**
   * @return the NAD1983CSRS98UTMZone19N
   */
  public ProjectionInfo getNAD1983CSRS98UTMZone19N() {
    return NAD1983CSRS98UTMZone19N.copy();
  }

  /**
   * @return the NAD1983CSRS98UTMZone20N
   */
  public ProjectionInfo getNAD1983CSRS98UTMZone20N() {
    return NAD1983CSRS98UTMZone20N.copy();
  }

  /**
   * @return the NAD1983CSRS98UTMZone21N
   */
  public ProjectionInfo getNAD1983CSRS98UTMZone21N() {
    return NAD1983CSRS98UTMZone21N.copy();
  }

  /**
   * @return the NAD1983MTM1
   */
  public ProjectionInfo getNAD1983MTM1() {
    return NAD1983MTM1.copy();
  }

  /**
   * @return the NAD1983MTM10
   */
  public ProjectionInfo getNAD1983MTM10() {
    return NAD1983MTM10.copy();
  }

  /**
   * @return the NAD1983MTM11
   */
  public ProjectionInfo getNAD1983MTM11() {
    return NAD1983MTM11.copy();
  }

  /**
   * @return the NAD1983MTM12
   */
  public ProjectionInfo getNAD1983MTM12() {
    return NAD1983MTM12.copy();
  }

  /**
   * @return the NAD1983MTM13
   */
  public ProjectionInfo getNAD1983MTM13() {
    return NAD1983MTM13.copy();
  }

  /**
   * @return the NAD1983MTM14
   */
  public ProjectionInfo getNAD1983MTM14() {
    return NAD1983MTM14.copy();
  }

  /**
   * @return the NAD1983MTM15
   */
  public ProjectionInfo getNAD1983MTM15() {
    return NAD1983MTM15.copy();
  }

  /**
   * @return the NAD1983MTM16
   */
  public ProjectionInfo getNAD1983MTM16() {
    return NAD1983MTM16.copy();
  }

  /**
   * @return the NAD1983MTM17
   */
  public ProjectionInfo getNAD1983MTM17() {
    return NAD1983MTM17.copy();
  }

  /**
   * @return the NAD1983MTM2
   */
  public ProjectionInfo getNAD1983MTM2() {
    return NAD1983MTM2.copy();
  }

  /**
   * @return the NAD1983MTM2SCoPQ
   */
  public ProjectionInfo getNAD1983MTM2SCoPQ() {
    return NAD1983MTM2SCoPQ.copy();
  }

  /**
   * @return the NAD1983MTM3
   */
  public ProjectionInfo getNAD1983MTM3() {
    return NAD1983MTM3.copy();
  }

  /**
   * @return the NAD1983MTM4
   */
  public ProjectionInfo getNAD1983MTM4() {
    return NAD1983MTM4.copy();
  }

  /**
   * @return the NAD1983MTM5
   */
  public ProjectionInfo getNAD1983MTM5() {
    return NAD1983MTM5.copy();
  }

  /**
   * @return the NAD1983MTM6
   */
  public ProjectionInfo getNAD1983MTM6() {
    return NAD1983MTM6.copy();
  }

  /**
   * @return the NAD1983MTM7
   */
  public ProjectionInfo getNAD1983MTM7() {
    return NAD1983MTM7.copy();
  }

  /**
   * @return the NAD1983MTM8
   */
  public ProjectionInfo getNAD1983MTM8() {
    return NAD1983MTM8.copy();
  }

  /**
   * @return the NAD1983MTM9
   */
  public ProjectionInfo getNAD1983MTM9() {
    return NAD1983MTM9.copy();
  }

  /**
   * @return the NAD1983QuebecLambert
   */
  public ProjectionInfo getNAD1983QuebecLambert() {
    return NAD1983QuebecLambert.copy();
  }

  /**
   * @return the PrinceEdwardIslandStereographic
   */
  public ProjectionInfo getPrinceEdwardIslandStereographic() {
    return PrinceEdwardIslandStereographic.copy();
  }

  // </editor-fold>
}
