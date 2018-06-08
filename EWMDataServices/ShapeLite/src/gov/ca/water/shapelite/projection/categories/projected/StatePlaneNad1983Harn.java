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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 5:01:59 PM
//
// Contributor(s): (Open source contributors should list themselves and their modifications here).
//        Name         |    Date    |        Comment
// --------------------|------------|------------------------------------------------------------
// Ted Dunsford        |   5/3/2010 |  Updated project to DotSpatial.Projection and license to LGPL
// ********************************************************************************************************
package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
    /**
     *  StatePlaneNad1983Harn
     * @author Harold A. Dunsford Jr. Ph.D.
     */
public class StatePlaneNad1983Harn extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo NAD1983HARNMaine2000CentralZone;
  private final ProjectionInfo NAD1983HARNMaine2000EastZone;
  private final ProjectionInfo NAD1983HARNMaine2000WestZone;
  private final ProjectionInfo NAD1983HARNStatePlaneAlabamaEastFIPS0101;
  private final ProjectionInfo NAD1983HARNStatePlaneAlabamaWestFIPS0102;
  private final ProjectionInfo NAD1983HARNStatePlaneArizonaCentralFIPS0202;
  private final ProjectionInfo NAD1983HARNStatePlaneArizonaEastFIPS0201;
  private final ProjectionInfo NAD1983HARNStatePlaneArizonaWestFIPS0203;
  private final ProjectionInfo NAD1983HARNStatePlaneArkansasNorthFIPS0301;
  private final ProjectionInfo NAD1983HARNStatePlaneArkansasSouthFIPS0302;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaIFIPS0401;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaIIFIPS0402;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaIIIFIPS0403;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaIVFIPS0404;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaVFIPS0405;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaVIFIPS0406;
  private final ProjectionInfo NAD1983HARNStatePlaneColoradoCentralFIPS0502;
  private final ProjectionInfo NAD1983HARNStatePlaneColoradoNorthFIPS0501;
  private final ProjectionInfo NAD1983HARNStatePlaneColoradoSouthFIPS0503;
  private final ProjectionInfo NAD1983HARNStatePlaneConnecticutFIPS0600;
  private final ProjectionInfo NAD1983HARNStatePlaneDelawareFIPS0700;
  private final ProjectionInfo NAD1983HARNStatePlaneFloridaEastFIPS0901;
  private final ProjectionInfo NAD1983HARNStatePlaneFloridaNorthFIPS0903;
  private final ProjectionInfo NAD1983HARNStatePlaneFloridaWestFIPS0902;
  private final ProjectionInfo NAD1983HARNStatePlaneGeorgiaEastFIPS1001;
  private final ProjectionInfo NAD1983HARNStatePlaneGeorgiaWestFIPS1002;
  private final ProjectionInfo NAD1983HARNStatePlaneHawaii1FIPS5101;
  private final ProjectionInfo NAD1983HARNStatePlaneHawaii2FIPS5102;
  private final ProjectionInfo NAD1983HARNStatePlaneHawaii3FIPS5103;
  private final ProjectionInfo NAD1983HARNStatePlaneHawaii4FIPS5104;
  private final ProjectionInfo NAD1983HARNStatePlaneHawaii5FIPS5105;
  private final ProjectionInfo NAD1983HARNStatePlaneIdahoCentralFIPS1102;
  private final ProjectionInfo NAD1983HARNStatePlaneIdahoEastFIPS1101;
  private final ProjectionInfo NAD1983HARNStatePlaneIdahoWestFIPS1103;
  private final ProjectionInfo NAD1983HARNStatePlaneIllinoisEastFIPS1201;
  private final ProjectionInfo NAD1983HARNStatePlaneIllinoisWestFIPS1202;
  private final ProjectionInfo NAD1983HARNStatePlaneIndianaEastFIPS1301;
  private final ProjectionInfo NAD1983HARNStatePlaneIndianaWestFIPS1302;
  private final ProjectionInfo NAD1983HARNStatePlaneIowaNorthFIPS1401;
  private final ProjectionInfo NAD1983HARNStatePlaneIowaSouthFIPS1402;
  private final ProjectionInfo NAD1983HARNStatePlaneKansasNorthFIPS1501;
  private final ProjectionInfo NAD1983HARNStatePlaneKansasSouthFIPS1502;
  private final ProjectionInfo NAD1983HARNStatePlaneKentuckyNorthFIPS1601;
  private final ProjectionInfo NAD1983HARNStatePlaneKentuckySouthFIPS1602;
  private final ProjectionInfo NAD1983HARNStatePlaneLouisianaNorthFIPS1701;
  private final ProjectionInfo NAD1983HARNStatePlaneLouisianaSouthFIPS1702;
  private final ProjectionInfo NAD1983HARNStatePlaneMaineEastFIPS1801;
  private final ProjectionInfo NAD1983HARNStatePlaneMaineWestFIPS1802;
  private final ProjectionInfo NAD1983HARNStatePlaneMarylandFIPS1900;
  private final ProjectionInfo NAD1983HARNStatePlaneMassachusettsIslandFIPS2002;
  private final ProjectionInfo NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001;
  private final ProjectionInfo NAD1983HARNStatePlaneMichiganCentralFIPS2112;
  private final ProjectionInfo NAD1983HARNStatePlaneMichiganNorthFIPS2111;
  private final ProjectionInfo NAD1983HARNStatePlaneMichiganSouthFIPS2113;
  private final ProjectionInfo NAD1983HARNStatePlaneMinnesotaCentralFIPS2202;
  private final ProjectionInfo NAD1983HARNStatePlaneMinnesotaNorthFIPS2201;
  private final ProjectionInfo NAD1983HARNStatePlaneMinnesotaSouthFIPS2203;
  private final ProjectionInfo NAD1983HARNStatePlaneMississippiEastFIPS2301;
  private final ProjectionInfo NAD1983HARNStatePlaneMississippiWestFIPS2302;
  private final ProjectionInfo NAD1983HARNStatePlaneMissouriCentralFIPS2402;
  private final ProjectionInfo NAD1983HARNStatePlaneMissouriEastFIPS2401;
  private final ProjectionInfo NAD1983HARNStatePlaneMissouriWestFIPS2403;
  private final ProjectionInfo NAD1983HARNStatePlaneMontanaFIPS2500;
  private final ProjectionInfo NAD1983HARNStatePlaneNebraskaFIPS2600;
  private final ProjectionInfo NAD1983HARNStatePlaneNevadaCentralFIPS2702;
  private final ProjectionInfo NAD1983HARNStatePlaneNevadaEastFIPS2701;
  private final ProjectionInfo NAD1983HARNStatePlaneNevadaWestFIPS2703;
  private final ProjectionInfo NAD1983HARNStatePlaneNewHampshireFIPS2800;
  private final ProjectionInfo NAD1983HARNStatePlaneNewJerseyFIPS2900;
  private final ProjectionInfo NAD1983HARNStatePlaneNewMexicoCentralFIPS3002;
  private final ProjectionInfo NAD1983HARNStatePlaneNewMexicoEastFIPS3001;
  private final ProjectionInfo NAD1983HARNStatePlaneNewMexicoWestFIPS3003;
  private final ProjectionInfo NAD1983HARNStatePlaneNewYorkCentralFIPS3102;
  private final ProjectionInfo NAD1983HARNStatePlaneNewYorkEastFIPS3101;
  private final ProjectionInfo NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104;
  private final ProjectionInfo NAD1983HARNStatePlaneNewYorkWestFIPS3103;
  private final ProjectionInfo NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301;
  private final ProjectionInfo NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302;
  private final ProjectionInfo NAD1983HARNStatePlaneOhioNorthFIPS3401;
  private final ProjectionInfo NAD1983HARNStatePlaneOhioSouthFIPS3402;
  private final ProjectionInfo NAD1983HARNStatePlaneOklahomaNorthFIPS3501;
  private final ProjectionInfo NAD1983HARNStatePlaneOklahomaSouthFIPS3502;
  private final ProjectionInfo NAD1983HARNStatePlaneOregonNorthFIPS3601;
  private final ProjectionInfo NAD1983HARNStatePlaneOregonSouthFIPS3602;
  private final ProjectionInfo NAD1983HARNStatePlanePRVirginIslandsFIPS5200;
  private final ProjectionInfo NAD1983HARNStatePlaneRhodeIslandFIPS3800;
  private final ProjectionInfo NAD1983HARNStatePlaneSouthDakotaNorthFIPS4001;
  private final ProjectionInfo NAD1983HARNStatePlaneSouthDakotaSouthFIPS4002;
  private final ProjectionInfo NAD1983HARNStatePlaneTennesseeFIPS4100;
  private final ProjectionInfo NAD1983HARNStatePlaneTexasCentralFIPS4203;
  private final ProjectionInfo NAD1983HARNStatePlaneTexasNorthCentralFIPS4202;
  private final ProjectionInfo NAD1983HARNStatePlaneTexasNorthFIPS4201;
  private final ProjectionInfo NAD1983HARNStatePlaneTexasSouthCentralFIPS4204;
  private final ProjectionInfo NAD1983HARNStatePlaneTexasSouthFIPS4205;
  private final ProjectionInfo NAD1983HARNStatePlaneUtahCentralFIPS4302;
  private final ProjectionInfo NAD1983HARNStatePlaneUtahNorthFIPS4301;
  private final ProjectionInfo NAD1983HARNStatePlaneUtahSouthFIPS4303;
  private final ProjectionInfo NAD1983HARNStatePlaneVermontFIPS4400;
  private final ProjectionInfo NAD1983HARNStatePlaneVirginiaNorthFIPS4501;
  private final ProjectionInfo NAD1983HARNStatePlaneVirginiaSouthFIPS4502;
  private final ProjectionInfo NAD1983HARNStatePlaneWashingtonNorthFIPS4601;
  private final ProjectionInfo NAD1983HARNStatePlaneWashingtonSouthFIPS4602;
  private final ProjectionInfo NAD1983HARNStatePlaneWestVirginiaNorthFIPS4701;
  private final ProjectionInfo NAD1983HARNStatePlaneWestVirginiaSouthFIPS4702;
  private final ProjectionInfo NAD1983HARNStatePlaneWisconsinCentralFIPS4802;
  private final ProjectionInfo NAD1983HARNStatePlaneWisconsinNorthFIPS4801;
  private final ProjectionInfo NAD1983HARNStatePlaneWisconsinSouthFIPS4803;
  private final ProjectionInfo NAD1983HARNStatePlaneWyomingECFIPS4902;
  private final ProjectionInfo NAD1983HARNStatePlaneWyomingEastFIPS4901;
  private final ProjectionInfo NAD1983HARNStatePlaneWyomingWCFIPS4903;
  private final ProjectionInfo NAD1983HARNStatePlaneWyomingWestFIPS4904;

        //</editor-fold>
  /**
   * Creates a new instance of StatePlaneNad1983Harn
   */
  public StatePlaneNad1983Harn() {
    NAD1983HARNMaine2000CentralZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.5 +lon_0=-69.125 +k=0.999980 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNMaine2000EastZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.83333333333334 +lon_0=-67.875 +k=0.999980 +x_0=700000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNMaine2000WestZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.83333333333334 +lon_0=-70.375 +k=0.999980 +x_0=300000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneAlabamaEastFIPS0101 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30.5 +lon_0=-85.83333333333333 +k=0.999960 +x_0=200000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneAlabamaWestFIPS0102 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-87.5 +k=0.999933 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneArizonaCentralFIPS0202 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-111.9166666666667 +k=0.999900 +x_0=213360 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneArizonaEastFIPS0201 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-110.1666666666667 +k=0.999900 +x_0=213360 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneArizonaWestFIPS0203 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-113.75 +k=0.999933 +x_0=213360 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneArkansasNorthFIPS0301 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.93333333333333 +lat_2=36.23333333333333 +lat_0=34.33333333333334 +lon_0=-92 +x_0=400000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneArkansasSouthFIPS0302 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.3 +lat_2=34.76666666666667 +lat_0=32.66666666666666 +lon_0=-92 +x_0=400000 +y_0=400000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaIFIPS0401 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40 +lat_2=41.66666666666666 +lat_0=39.33333333333334 +lon_0=-122 +x_0=2000000 +y_0=500000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaIIFIPS0402 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.33333333333334 +lat_2=39.83333333333334 +lat_0=37.66666666666666 +lon_0=-122 +x_0=2000000 +y_0=500000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaIIIFIPS0403 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.06666666666667 +lat_2=38.43333333333333 +lat_0=36.5 +lon_0=-120.5 +x_0=2000000 +y_0=500000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaIVFIPS0404 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36 +lat_2=37.25 +lat_0=35.33333333333334 +lon_0=-119 +x_0=2000000 +y_0=500000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaVFIPS0405 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.03333333333333 +lat_2=35.46666666666667 +lat_0=33.5 +lon_0=-118 +x_0=2000000 +y_0=500000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaVIFIPS0406 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.78333333333333 +lat_2=33.88333333333333 +lat_0=32.16666666666666 +lon_0=-116.25 +x_0=2000000 +y_0=500000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneColoradoCentralFIPS0502 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.45 +lat_2=39.75 +lat_0=37.83333333333334 +lon_0=-105.5 +x_0=914401.8289 +y_0=304800.6096 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneColoradoNorthFIPS0501 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.71666666666667 +lat_2=40.78333333333333 +lat_0=39.33333333333334 +lon_0=-105.5 +x_0=914401.8289 +y_0=304800.6096 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneColoradoSouthFIPS0503 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.23333333333333 +lat_2=38.43333333333333 +lat_0=36.66666666666666 +lon_0=-105.5 +x_0=914401.8289 +y_0=304800.6096 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneConnecticutFIPS0600 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.2 +lat_2=41.86666666666667 +lat_0=40.83333333333334 +lon_0=-72.75 +x_0=304800.6096 +y_0=152400.3048 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneDelawareFIPS0700 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38 +lon_0=-75.41666666666667 +k=0.999995 +x_0=200000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneFloridaEastFIPS0901 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=24.33333333333333 +lon_0=-81 +k=0.999941 +x_0=200000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneFloridaNorthFIPS0903 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=29.58333333333333 +lat_2=30.75 +lat_0=29 +lon_0=-84.5 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneFloridaWestFIPS0902 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=24.33333333333333 +lon_0=-82 +k=0.999941 +x_0=200000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneGeorgiaEastFIPS1001 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-82.16666666666667 +k=0.999900 +x_0=200000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneGeorgiaWestFIPS1002 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-84.16666666666667 +k=0.999900 +x_0=700000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneHawaii1FIPS5101 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=18.83333333333333 +lon_0=-155.5 +k=0.999967 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneHawaii2FIPS5102 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=20.33333333333333 +lon_0=-156.6666666666667 +k=0.999967 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneHawaii3FIPS5103 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.16666666666667 +lon_0=-158 +k=0.999990 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneHawaii4FIPS5104 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.83333333333333 +lon_0=-159.5 +k=0.999990 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneHawaii5FIPS5105 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.66666666666667 +lon_0=-160.1666666666667 +k=1.000000 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIdahoCentralFIPS1102 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-114 +k=0.999947 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIdahoEastFIPS1101 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-112.1666666666667 +k=0.999947 +x_0=200000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIdahoWestFIPS1103 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-115.75 +k=0.999933 +x_0=800000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIllinoisEastFIPS1201 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36.66666666666666 +lon_0=-88.33333333333333 +k=0.999975 +x_0=300000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIllinoisWestFIPS1202 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36.66666666666666 +lon_0=-90.16666666666667 +k=0.999941 +x_0=700000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIndianaEastFIPS1301 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=37.5 +lon_0=-85.66666666666667 +k=0.999967 +x_0=100000 +y_0=250000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIndianaWestFIPS1302 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=37.5 +lon_0=-87.08333333333333 +k=0.999967 +x_0=900000 +y_0=250000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIowaNorthFIPS1401 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.06666666666667 +lat_2=43.26666666666667 +lat_0=41.5 +lon_0=-93.5 +x_0=1500000 +y_0=1000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIowaSouthFIPS1402 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.61666666666667 +lat_2=41.78333333333333 +lat_0=40 +lon_0=-93.5 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneKansasNorthFIPS1501 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.71666666666667 +lat_2=39.78333333333333 +lat_0=38.33333333333334 +lon_0=-98 +x_0=400000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneKansasSouthFIPS1502 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.26666666666667 +lat_2=38.56666666666667 +lat_0=36.66666666666666 +lon_0=-98.5 +x_0=400000 +y_0=400000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneKentuckyNorthFIPS1601 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.96666666666667 +lat_2=38.96666666666667 +lat_0=37.5 +lon_0=-84.25 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneKentuckySouthFIPS1602 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36.73333333333333 +lat_2=37.93333333333333 +lat_0=36.33333333333334 +lon_0=-85.75 +x_0=500000 +y_0=500000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneLouisianaNorthFIPS1701 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=31.16666666666667 +lat_2=32.66666666666666 +lat_0=30.5 +lon_0=-92.5 +x_0=1000000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneLouisianaSouthFIPS1702 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=29.3 +lat_2=30.7 +lat_0=28.5 +lon_0=-91.33333333333333 +x_0=1000000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMaineEastFIPS1801 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.66666666666666 +lon_0=-68.5 +k=0.999900 +x_0=300000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMaineWestFIPS1802 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.83333333333334 +lon_0=-70.16666666666667 +k=0.999967 +x_0=900000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMarylandFIPS1900 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.3 +lat_2=39.45 +lat_0=37.66666666666666 +lon_0=-77 +x_0=400000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMassachusettsIslandFIPS2002 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.28333333333333 +lat_2=41.48333333333333 +lat_0=41 +lon_0=-70.5 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.71666666666667 +lat_2=42.68333333333333 +lat_0=41 +lon_0=-71.5 +x_0=200000 +y_0=750000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMichiganCentralFIPS2112 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.18333333333333 +lat_2=45.7 +lat_0=43.31666666666667 +lon_0=-84.36666666666666 +x_0=6000000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMichiganNorthFIPS2111 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.48333333333333 +lat_2=47.08333333333334 +lat_0=44.78333333333333 +lon_0=-87 +x_0=8000000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMichiganSouthFIPS2113 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.1 +lat_2=43.66666666666666 +lat_0=41.5 +lon_0=-84.36666666666666 +x_0=4000000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMinnesotaCentralFIPS2202 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.61666666666667 +lat_2=47.05 +lat_0=45 +lon_0=-94.25 +x_0=800000 +y_0=100000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMinnesotaNorthFIPS2201 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.03333333333333 +lat_2=48.63333333333333 +lat_0=46.5 +lon_0=-93.09999999999999 +x_0=800000 +y_0=100000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMinnesotaSouthFIPS2203 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.78333333333333 +lat_2=45.21666666666667 +lat_0=43 +lon_0=-94 +x_0=800000 +y_0=100000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMississippiEastFIPS2301 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=29.5 +lon_0=-88.83333333333333 +k=0.999950 +x_0=300000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMississippiWestFIPS2302 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=29.5 +lon_0=-90.33333333333333 +k=0.999950 +x_0=700000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMissouriCentralFIPS2402 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=35.83333333333334 +lon_0=-92.5 +k=0.999933 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMissouriEastFIPS2401 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=35.83333333333334 +lon_0=-90.5 +k=0.999933 +x_0=250000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMissouriWestFIPS2403 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36.16666666666666 +lon_0=-94.5 +k=0.999941 +x_0=850000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMontanaFIPS2500 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45 +lat_2=49 +lat_0=44.25 +lon_0=-109.5 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNebraskaFIPS2600 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40 +lat_2=43 +lat_0=39.83333333333334 +lon_0=-100 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNevadaCentralFIPS2702 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=34.75 +lon_0=-116.6666666666667 +k=0.999900 +x_0=500000 +y_0=6000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNevadaEastFIPS2701 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=34.75 +lon_0=-115.5833333333333 +k=0.999900 +x_0=200000 +y_0=8000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNevadaWestFIPS2703 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=34.75 +lon_0=-118.5833333333333 +k=0.999900 +x_0=800000 +y_0=4000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewHampshireFIPS2800 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.5 +lon_0=-71.66666666666667 +k=0.999967 +x_0=300000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewJerseyFIPS2900 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38.83333333333334 +lon_0=-74.5 +k=0.999900 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewMexicoCentralFIPS3002 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-106.25 +k=0.999900 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewMexicoEastFIPS3001 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-104.3333333333333 +k=0.999909 +x_0=165000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewMexicoWestFIPS3003 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-107.8333333333333 +k=0.999917 +x_0=830000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewYorkCentralFIPS3102 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40 +lon_0=-76.58333333333333 +k=0.999938 +x_0=250000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewYorkEastFIPS3101 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38.83333333333334 +lon_0=-74.5 +k=0.999900 +x_0=150000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.66666666666666 +lat_2=41.03333333333333 +lat_0=40.16666666666666 +lon_0=-74 +x_0=300000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewYorkWestFIPS3103 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40 +lon_0=-78.58333333333333 +k=0.999938 +x_0=350000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.43333333333333 +lat_2=48.73333333333333 +lat_0=47 +lon_0=-100.5 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.18333333333333 +lat_2=47.48333333333333 +lat_0=45.66666666666666 +lon_0=-100.5 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneOhioNorthFIPS3401 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.43333333333333 +lat_2=41.7 +lat_0=39.66666666666666 +lon_0=-82.5 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneOhioSouthFIPS3402 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.73333333333333 +lat_2=40.03333333333333 +lat_0=38 +lon_0=-82.5 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneOklahomaNorthFIPS3501 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=35.56666666666667 +lat_2=36.76666666666667 +lat_0=35 +lon_0=-98 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneOklahomaSouthFIPS3502 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.93333333333333 +lat_2=35.23333333333333 +lat_0=33.33333333333334 +lon_0=-98 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneOregonNorthFIPS3601 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.33333333333334 +lat_2=46 +lat_0=43.66666666666666 +lon_0=-120.5 +x_0=2500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneOregonSouthFIPS3602 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.33333333333334 +lat_2=44 +lat_0=41.66666666666666 +lon_0=-120.5 +x_0=1500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlanePRVirginIslandsFIPS5200 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=18.03333333333333 +lat_2=18.43333333333333 +lat_0=17.83333333333333 +lon_0=-66.43333333333334 +x_0=200000 +y_0=200000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneRhodeIslandFIPS3800 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.08333333333334 +lon_0=-71.5 +k=0.999994 +x_0=100000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneSouthDakotaNorthFIPS4001 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.41666666666666 +lat_2=45.68333333333333 +lat_0=43.83333333333334 +lon_0=-100 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneSouthDakotaSouthFIPS4002 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.83333333333334 +lat_2=44.4 +lat_0=42.33333333333334 +lon_0=-100.3333333333333 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTennesseeFIPS4100 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=35.25 +lat_2=36.41666666666666 +lat_0=34.33333333333334 +lon_0=-86 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTexasCentralFIPS4203 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=30.11666666666667 +lat_2=31.88333333333333 +lat_0=29.66666666666667 +lon_0=-100.3333333333333 +x_0=700000 +y_0=3000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTexasNorthCentralFIPS4202 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.13333333333333 +lat_2=33.96666666666667 +lat_0=31.66666666666667 +lon_0=-98.5 +x_0=600000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTexasNorthFIPS4201 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.65 +lat_2=36.18333333333333 +lat_0=34 +lon_0=-101.5 +x_0=200000 +y_0=1000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTexasSouthCentralFIPS4204 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=28.38333333333333 +lat_2=30.28333333333333 +lat_0=27.83333333333333 +lon_0=-99 +x_0=600000 +y_0=4000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTexasSouthFIPS4205 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26.16666666666667 +lat_2=27.83333333333333 +lat_0=25.66666666666667 +lon_0=-98.5 +x_0=300000 +y_0=5000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneUtahCentralFIPS4302 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.01666666666667 +lat_2=40.65 +lat_0=38.33333333333334 +lon_0=-111.5 +x_0=500000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneUtahNorthFIPS4301 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.71666666666667 +lat_2=41.78333333333333 +lat_0=40.33333333333334 +lon_0=-111.5 +x_0=500000 +y_0=1000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneUtahSouthFIPS4303 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.21666666666667 +lat_2=38.35 +lat_0=36.66666666666666 +lon_0=-111.5 +x_0=500000 +y_0=3000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneVermontFIPS4400 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.5 +lon_0=-72.5 +k=0.999964 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneVirginiaNorthFIPS4501 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.03333333333333 +lat_2=39.2 +lat_0=37.66666666666666 +lon_0=-78.5 +x_0=3500000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneVirginiaSouthFIPS4502 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36.76666666666667 +lat_2=37.96666666666667 +lat_0=36.33333333333334 +lon_0=-78.5 +x_0=3500000 +y_0=1000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWashingtonNorthFIPS4601 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.5 +lat_2=48.73333333333333 +lat_0=47 +lon_0=-120.8333333333333 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWashingtonSouthFIPS4602 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.83333333333334 +lat_2=47.33333333333334 +lat_0=45.33333333333334 +lon_0=-120.5 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWestVirginiaNorthFIPS4701 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39 +lat_2=40.25 +lat_0=38.5 +lon_0=-79.5 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWestVirginiaSouthFIPS4702 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.48333333333333 +lat_2=38.88333333333333 +lat_0=37 +lon_0=-81 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWisconsinCentralFIPS4802 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.25 +lat_2=45.5 +lat_0=43.83333333333334 +lon_0=-90 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWisconsinNorthFIPS4801 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.56666666666667 +lat_2=46.76666666666667 +lat_0=45.16666666666666 +lon_0=-90 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWisconsinSouthFIPS4803 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.73333333333333 +lat_2=44.06666666666667 +lat_0=42 +lon_0=-90 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWyomingEastFIPS4901 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-105.1666666666667 +k=0.999938 +x_0=200000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWyomingECFIPS4902 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-107.3333333333333 +k=0.999938 +x_0=400000 +y_0=100000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWyomingWCFIPS4903 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-108.75 +k=0.999938 +x_0=600000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWyomingWestFIPS4904 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-110.0833333333333 +k=0.999938 +x_0=800000 +y_0=100000 +ellps=GRS80 +units=m +no_defs ").orElse(null);

    NAD1983HARNMaine2000CentralZone.setName("NAD_1983_HARN_Maine_2000_Central_Zone");
    NAD1983HARNMaine2000EastZone.setName("NAD_1983_HARN_Maine_2000_East_Zone");
    NAD1983HARNMaine2000WestZone.setName("NAD_1983_HARN_Maine_2000_West_Zone");
    NAD1983HARNStatePlaneAlabamaEastFIPS0101.setName("NAD_1983_HARN_StatePlane_Alabama_East_FIPS_0101");
    NAD1983HARNStatePlaneAlabamaWestFIPS0102.setName("NAD_1983_HARN_StatePlane_Alabama_West_FIPS_0102");
    NAD1983HARNStatePlaneArizonaCentralFIPS0202.setName("NAD_1983_HARN_StatePlane_Arizona_Central_FIPS_0202");
    NAD1983HARNStatePlaneArizonaEastFIPS0201.setName("NAD_1983_HARN_StatePlane_Arizona_East_FIPS_0201");
    NAD1983HARNStatePlaneArizonaWestFIPS0203.setName("NAD_1983_HARN_StatePlane_Arizona_West_FIPS_0203");
    NAD1983HARNStatePlaneArkansasNorthFIPS0301.setName("NAD_1983_HARN_StatePlane_Arkansas_North_FIPS_0301");
    NAD1983HARNStatePlaneArkansasSouthFIPS0302.setName("NAD_1983_HARN_StatePlane_Arkansas_South_FIPS_0302");
    NAD1983HARNStatePlaneCaliforniaIFIPS0401.setName("NAD_1983_HARN_StatePlane_California_I_FIPS_0401");
    NAD1983HARNStatePlaneCaliforniaIIFIPS0402.setName("NAD_1983_HARN_StatePlane_California_II_FIPS_0402");
    NAD1983HARNStatePlaneCaliforniaIIIFIPS0403.setName("NAD_1983_HARN_StatePlane_California_III_FIPS_0403");
    NAD1983HARNStatePlaneCaliforniaIVFIPS0404.setName("NAD_1983_HARN_StatePlane_California_IV_FIPS_0404");
    NAD1983HARNStatePlaneCaliforniaVFIPS0405.setName("NAD_1983_HARN_StatePlane_California_V_FIPS_0405");
    NAD1983HARNStatePlaneCaliforniaVIFIPS0406.setName("NAD_1983_HARN_StatePlane_California_VI_FIPS_0406");
    NAD1983HARNStatePlaneColoradoCentralFIPS0502.setName("NAD_1983_HARN_StatePlane_Colorado_Central_FIPS_0502");
    NAD1983HARNStatePlaneColoradoNorthFIPS0501.setName("NAD_1983_HARN_StatePlane_Colorado_North_FIPS_0501");
    NAD1983HARNStatePlaneColoradoSouthFIPS0503.setName("NAD_1983_HARN_StatePlane_Colorado_South_FIPS_0503");
    NAD1983HARNStatePlaneConnecticutFIPS0600.setName("NAD_1983_HARN_StatePlane_Connecticut_FIPS_0600");
    NAD1983HARNStatePlaneDelawareFIPS0700.setName("NAD_1983_HARN_StatePlane_Delaware_FIPS_0700");
    NAD1983HARNStatePlaneFloridaEastFIPS0901.setName("NAD_1983_HARN_StatePlane_Florida_East_FIPS_0901");
    NAD1983HARNStatePlaneFloridaNorthFIPS0903.setName("NAD_1983_HARN_StatePlane_Florida_North_FIPS_0903");
    NAD1983HARNStatePlaneFloridaWestFIPS0902.setName("NAD_1983_HARN_StatePlane_Florida_West_FIPS_0902");
    NAD1983HARNStatePlaneGeorgiaEastFIPS1001.setName("NAD_1983_HARN_StatePlane_Georgia_East_FIPS_1001");
    NAD1983HARNStatePlaneGeorgiaWestFIPS1002.setName("NAD_1983_HARN_StatePlane_Georgia_West_FIPS_1002");
    NAD1983HARNStatePlaneHawaii1FIPS5101.setName("NAD_1983_HARN_StatePlane_Hawaii_1_FIPS_5101");
    NAD1983HARNStatePlaneHawaii2FIPS5102.setName("NAD_1983_HARN_StatePlane_Hawaii_2_FIPS_5102");
    NAD1983HARNStatePlaneHawaii3FIPS5103.setName("NAD_1983_HARN_StatePlane_Hawaii_3_FIPS_5103");
    NAD1983HARNStatePlaneHawaii4FIPS5104.setName("NAD_1983_HARN_StatePlane_Hawaii_4_FIPS_5104");
    NAD1983HARNStatePlaneHawaii5FIPS5105.setName("NAD_1983_HARN_StatePlane_Hawaii_5_FIPS_5105");
    NAD1983HARNStatePlaneIdahoCentralFIPS1102.setName("NAD_1983_HARN_StatePlane_Idaho_Central_FIPS_1102");
    NAD1983HARNStatePlaneIdahoEastFIPS1101.setName("NAD_1983_HARN_StatePlane_Idaho_East_FIPS_1101");
    NAD1983HARNStatePlaneIdahoWestFIPS1103.setName("NAD_1983_HARN_StatePlane_Idaho_West_FIPS_1103");
    NAD1983HARNStatePlaneIllinoisEastFIPS1201.setName("NAD_1983_HARN_StatePlane_Illinois_East_FIPS_1201");
    NAD1983HARNStatePlaneIllinoisWestFIPS1202.setName("NAD_1983_HARN_StatePlane_Illinois_West_FIPS_1202");
    NAD1983HARNStatePlaneIndianaEastFIPS1301.setName("NAD_1983_HARN_StatePlane_Indiana_East_FIPS_1301");
    NAD1983HARNStatePlaneIndianaWestFIPS1302.setName("NAD_1983_HARN_StatePlane_Indiana_West_FIPS_1302");
    NAD1983HARNStatePlaneIowaNorthFIPS1401.setName("NAD_1983_HARN_StatePlane_Iowa_North_FIPS_1401");
    NAD1983HARNStatePlaneIowaSouthFIPS1402.setName("NAD_1983_HARN_StatePlane_Iowa_South_FIPS_1402");
    NAD1983HARNStatePlaneKansasNorthFIPS1501.setName("NAD_1983_HARN_StatePlane_Kansas_North_FIPS_1501");
    NAD1983HARNStatePlaneKansasSouthFIPS1502.setName("NAD_1983_HARN_StatePlane_Kansas_South_FIPS_1502");
    NAD1983HARNStatePlaneKentuckyNorthFIPS1601.setName("NAD_1983_HARN_StatePlane_Kentucky_North_FIPS_1601");
    NAD1983HARNStatePlaneKentuckySouthFIPS1602.setName("NAD_1983_HARN_StatePlane_Kentucky_South_FIPS_1602");
    NAD1983HARNStatePlaneLouisianaNorthFIPS1701.setName("NAD_1983_HARN_StatePlane_Louisiana_North_FIPS_1701");
    NAD1983HARNStatePlaneLouisianaSouthFIPS1702.setName("NAD_1983_HARN_StatePlane_Louisiana_South_FIPS_1702");
    NAD1983HARNStatePlaneMaineEastFIPS1801.setName("NAD_1983_HARN_StatePlane_Maine_East_FIPS_1801");
    NAD1983HARNStatePlaneMaineWestFIPS1802.setName("NAD_1983_HARN_StatePlane_Maine_West_FIPS_1802");
    NAD1983HARNStatePlaneMarylandFIPS1900.setName("NAD_1983_HARN_StatePlane_Maryland_FIPS_1900");
    NAD1983HARNStatePlaneMassachusettsIslandFIPS2002.setName("NAD_1983_HARN_StatePlane_Massachusetts_Island_FIPS_2002");
    NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001.setName("NAD_1983_HARN_StatePlane_Massachusetts_Mainland_FIPS_2001");
    NAD1983HARNStatePlaneMichiganCentralFIPS2112.setName("NAD_1983_HARN_StatePlane_Michigan_Central_FIPS_2202");
    NAD1983HARNStatePlaneMichiganNorthFIPS2111.setName("NAD_1983_HARN_StatePlane_Michigan_North_FIPS_2111");
    NAD1983HARNStatePlaneMichiganSouthFIPS2113.setName("NAD_1983_HARN_StatePlane_Michigan_South_FIPS_2113");
    NAD1983HARNStatePlaneMinnesotaCentralFIPS2202.setName("NAD_1983_HARN_StatePlane_Minnesota_Central_FIPS_2202");
    NAD1983HARNStatePlaneMinnesotaNorthFIPS2201.setName("NAD_1983_HARN_StatePlane_Minnesota_North_FIPS_2201");
    NAD1983HARNStatePlaneMinnesotaSouthFIPS2203.setName("NAD_1983_HARN_StatePlane_Minnesota_South_FIPS_2203");
    NAD1983HARNStatePlaneMississippiEastFIPS2301.setName("NAD_1983_HARN_StatePlane_Mississippi_East_FIPS_2301");
    NAD1983HARNStatePlaneMississippiWestFIPS2302.setName("NAD_1983_HARN_StatePlane_Mississippi_West_FIPS_2302");
    NAD1983HARNStatePlaneMissouriCentralFIPS2402.setName("NAD_1983_HARN_StatePlane_Missouri_Central_FIPS_2402");
    NAD1983HARNStatePlaneMissouriEastFIPS2401.setName("NAD_1983_HARN_StatePlane_Missouri_East_FIPS_2401");
    NAD1983HARNStatePlaneMissouriWestFIPS2403.setName("NAD_1983_HARN_StatePlane_Missouri_West_FIPS_2403");
    NAD1983HARNStatePlaneMontanaFIPS2500.setName("NAD_1983_HARN_StatePlane_Montana_FIPS_2500");
    NAD1983HARNStatePlaneNebraskaFIPS2600.setName("NAD_1983_HARN_StatePlane_Nebraska_FIPS_2600");
    NAD1983HARNStatePlaneNevadaCentralFIPS2702.setName("NAD_1983_HARN_StatePlane_Nevada_Central_FIPS_2702");
    NAD1983HARNStatePlaneNevadaEastFIPS2701.setName("NAD_1983_HARN_StatePlane_Nevada_East_FIPS_2701");
    NAD1983HARNStatePlaneNevadaWestFIPS2703.setName("NAD_1983_HARN_StatePlane_Nevada_West_FIPS_2703");
    NAD1983HARNStatePlaneNewHampshireFIPS2800.setName("NAD_1983_HARN_StatePlane_New_Hampshire_FIPS_2800");
    NAD1983HARNStatePlaneNewJerseyFIPS2900.setName("NAD_1983_HARN_StatePlane_New_Jersey_FIPS_2900");
    NAD1983HARNStatePlaneNewMexicoCentralFIPS3002.setName("NAD_1983_HARN_StatePlane_New_Mexico_Central_FIPS_3002");
    NAD1983HARNStatePlaneNewMexicoEastFIPS3001.setName("NAD_1983_HARN_StatePlane_New_Mexico_East_FIPS_3001");
    NAD1983HARNStatePlaneNewMexicoWestFIPS3003.setName("NAD_1983_HARN_StatePlane_New_Mexico_West_FIPS_3003");
    NAD1983HARNStatePlaneNewYorkCentralFIPS3102.setName("NAD_1983_HARN_StatePlane_New_York_Central_FIPS_3102");
    NAD1983HARNStatePlaneNewYorkEastFIPS3101.setName("NAD_1983_HARN_StatePlane_New_York_East_FIPS_3101");
    NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104.setName("NAD_1983_HARN_StatePlane_New_York_Long_Island_FIPS_3104");
    NAD1983HARNStatePlaneNewYorkWestFIPS3103.setName("NAD_1983_HARN_StatePlane_New_York_West_FIPS_3103");
    NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301.setName("NAD_1983_HARN_StatePlane_North_Dakota_North_FIPS_3301");
    NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302.setName("NAD_1983_HARN_StatePlane_North_Dakota_South_FIPS_3302");
    NAD1983HARNStatePlaneOhioNorthFIPS3401.setName("NAD_1983_HARN_StatePlane_Ohio_North_FIPS_3401");
    NAD1983HARNStatePlaneOhioSouthFIPS3402.setName("NAD_1983_HARN_StatePlane_Ohio_South_FIPS_3402");
    NAD1983HARNStatePlaneOklahomaNorthFIPS3501.setName("NAD_1983_HARN_StatePlane_Oklahoma_North_FIPS_3501");
    NAD1983HARNStatePlaneOklahomaSouthFIPS3502.setName("NAD_1983_HARN_StatePlane_Oklahoma_South_FIPS_3502");
    NAD1983HARNStatePlaneOregonNorthFIPS3601.setName("NAD_1983_HARN_StatePlane_Oregon_North_FIPS_3601");
    NAD1983HARNStatePlaneOregonSouthFIPS3602.setName("NAD_1983_HARN_StatePlane_Oregon_South_FIPS_3602");
    NAD1983HARNStatePlanePRVirginIslandsFIPS5200.setName("NAD_1983_HARN_StatePlane_Puerto_Rico_Virgin_Islands_FIPS_5200");
    NAD1983HARNStatePlaneRhodeIslandFIPS3800.setName("NAD_1983_HARN_StatePlane_Rhode_Island_FIPS_3800");
    NAD1983HARNStatePlaneSouthDakotaNorthFIPS4001.setName("NAD_1983_HARN_StatePlane_South_Dakota_North_FIPS_4001");
    NAD1983HARNStatePlaneSouthDakotaSouthFIPS4002.setName("NAD_1983_HARN_StatePlane_South_Dakota_South_FIPS_4002");
    NAD1983HARNStatePlaneTennesseeFIPS4100.setName("NAD_1983_HARN_StatePlane_Tennessee_FIPS_4100");
    NAD1983HARNStatePlaneTexasCentralFIPS4203.setName("NAD_1983_HARN_StatePlane_Texas_Central_FIPS_4203");
    NAD1983HARNStatePlaneTexasNorthCentralFIPS4202.setName("NAD_1983_HARN_StatePlane_Texas_North_Central_FIPS_4202");
    NAD1983HARNStatePlaneTexasNorthFIPS4201.setName("NAD_1983_HARN_StatePlane_Texas_North_FIPS_4201");
    NAD1983HARNStatePlaneTexasSouthCentralFIPS4204.setName("NAD_1983_HARN_StatePlane_Texas_South_Central_FIPS_4204");
    NAD1983HARNStatePlaneTexasSouthFIPS4205.setName("NAD_1983_HARN_StatePlane_Texas_South_FIPS_4205");
    NAD1983HARNStatePlaneUtahCentralFIPS4302.setName("NAD_1983_HARN_StatePlane_Utah_Central_FIPS_4302");
    NAD1983HARNStatePlaneUtahNorthFIPS4301.setName("NAD_1983_HARN_StatePlane_Utah_North_FIPS_4301");
    NAD1983HARNStatePlaneUtahSouthFIPS4303.setName("NAD_1983_HARN_StatePlane_Utah_South_FIPS_4303");
    NAD1983HARNStatePlaneVermontFIPS4400.setName("NAD_1983_HARN_StatePlane_Vermont_FIPS_4400");
    NAD1983HARNStatePlaneVirginiaNorthFIPS4501.setName("NAD_1983_HARN_StatePlane_Virginia_North_FIPS_4501");
    NAD1983HARNStatePlaneVirginiaSouthFIPS4502.setName("NAD_1983_HARN_StatePlane_Virginia_South_FIPS_4502");
    NAD1983HARNStatePlaneWashingtonNorthFIPS4601.setName("NAD_1983_HARN_StatePlane_Washington_North_FIPS_4601");
    NAD1983HARNStatePlaneWashingtonSouthFIPS4602.setName("NAD_1983_HARN_StatePlane_Washington_South_FIPS_4602");
    NAD1983HARNStatePlaneWestVirginiaNorthFIPS4701.setName("NAD_1983_HARN_StatePlane_West_Virginia_North_FIPS_4701");
    NAD1983HARNStatePlaneWestVirginiaSouthFIPS4702.setName("NAD_1983_HARN_StatePlane_West_Virginia_South_FIPS_4702");
    NAD1983HARNStatePlaneWisconsinCentralFIPS4802.setName("NAD_1983_HARN_StatePlane_Wisconsin_Central_FIPS_4802");
    NAD1983HARNStatePlaneWisconsinNorthFIPS4801.setName("NAD_1983_HARN_StatePlane_Wisconsin_North_FIPS_4801");
    NAD1983HARNStatePlaneWisconsinSouthFIPS4803.setName("NAD_1983_HARN_StatePlane_Wisconsin_South_FIPS_4803");
    NAD1983HARNStatePlaneWyomingEastFIPS4901.setName("NAD_1983_HARN_StatePlane_Wyoming_East_FIPS_4901");
    NAD1983HARNStatePlaneWyomingECFIPS4902.setName("NAD_1983_HARN_StatePlane_Wyoming_East_Central_FIPS_4902");
    NAD1983HARNStatePlaneWyomingWCFIPS4903.setName("NAD_1983_HARN_StatePlane_Wyoming_West_Central_FIPS_4903");
    NAD1983HARNStatePlaneWyomingWestFIPS4904.setName("NAD_1983_HARN_StatePlane_Wyoming_West_FIPS_4904");

    NAD1983HARNMaine2000CentralZone.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNMaine2000EastZone.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNMaine2000WestZone.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneAlabamaEastFIPS0101.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneAlabamaWestFIPS0102.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneArizonaCentralFIPS0202.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneArizonaEastFIPS0201.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneArizonaWestFIPS0203.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneArkansasNorthFIPS0301.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneArkansasSouthFIPS0302.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIFIPS0401.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIIFIPS0402.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIIIFIPS0403.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIVFIPS0404.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaVFIPS0405.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaVIFIPS0406.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoCentralFIPS0502.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoNorthFIPS0501.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoSouthFIPS0503.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneConnecticutFIPS0600.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneDelawareFIPS0700.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaEastFIPS0901.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaNorthFIPS0903.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaWestFIPS0902.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneGeorgiaEastFIPS1001.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneGeorgiaWestFIPS1002.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii1FIPS5101.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii2FIPS5102.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii3FIPS5103.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii4FIPS5104.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii5FIPS5105.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoCentralFIPS1102.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoEastFIPS1101.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoWestFIPS1103.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIllinoisEastFIPS1201.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIllinoisWestFIPS1202.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIndianaEastFIPS1301.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIndianaWestFIPS1302.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIowaNorthFIPS1401.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIowaSouthFIPS1402.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneKansasNorthFIPS1501.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneKansasSouthFIPS1502.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneKentuckyNorthFIPS1601.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneKentuckySouthFIPS1602.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneLouisianaNorthFIPS1701.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneLouisianaSouthFIPS1702.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMaineEastFIPS1801.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMaineWestFIPS1802.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMarylandFIPS1900.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMassachusettsIslandFIPS2002.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganCentralFIPS2112.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganNorthFIPS2111.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganSouthFIPS2113.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMinnesotaCentralFIPS2202.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMinnesotaNorthFIPS2201.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMinnesotaSouthFIPS2203.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMississippiEastFIPS2301.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMississippiWestFIPS2302.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMissouriCentralFIPS2402.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMissouriEastFIPS2401.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMissouriWestFIPS2403.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMontanaFIPS2500.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNebraskaFIPS2600.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNevadaCentralFIPS2702.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNevadaEastFIPS2701.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNevadaWestFIPS2703.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewHampshireFIPS2800.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewJerseyFIPS2900.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoCentralFIPS3002.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoEastFIPS3001.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoWestFIPS3003.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkCentralFIPS3102.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkEastFIPS3101.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkWestFIPS3103.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneOhioNorthFIPS3401.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneOhioSouthFIPS3402.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneOklahomaNorthFIPS3501.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneOklahomaSouthFIPS3502.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneOregonNorthFIPS3601.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneOregonSouthFIPS3602.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlanePRVirginIslandsFIPS5200.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneRhodeIslandFIPS3800.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneSouthDakotaNorthFIPS4001.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneSouthDakotaSouthFIPS4002.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTennesseeFIPS4100.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasCentralFIPS4203.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasNorthCentralFIPS4202.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasNorthFIPS4201.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasSouthCentralFIPS4204.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasSouthFIPS4205.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahCentralFIPS4302.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahNorthFIPS4301.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahSouthFIPS4303.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneVermontFIPS4400.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneVirginiaNorthFIPS4501.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneVirginiaSouthFIPS4502.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWashingtonNorthFIPS4601.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWashingtonSouthFIPS4602.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWestVirginiaNorthFIPS4701.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWestVirginiaSouthFIPS4702.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinCentralFIPS4802.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinNorthFIPS4801.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinSouthFIPS4803.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWyomingEastFIPS4901.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWyomingECFIPS4902.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWyomingWCFIPS4903.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWyomingWestFIPS4904.getGeographicInfo().setName("GCS_North_American_1983_HARN");

    NAD1983HARNMaine2000CentralZone.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNMaine2000EastZone.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNMaine2000WestZone.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneAlabamaEastFIPS0101.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneAlabamaWestFIPS0102.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneArizonaCentralFIPS0202.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneArizonaEastFIPS0201.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneArizonaWestFIPS0203.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneArkansasNorthFIPS0301.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneArkansasSouthFIPS0302.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIFIPS0401.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIIFIPS0402.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIIIFIPS0403.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIVFIPS0404.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaVFIPS0405.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaVIFIPS0406.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoCentralFIPS0502.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoNorthFIPS0501.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoSouthFIPS0503.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneConnecticutFIPS0600.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneDelawareFIPS0700.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaEastFIPS0901.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaNorthFIPS0903.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaWestFIPS0902.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneGeorgiaEastFIPS1001.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneGeorgiaWestFIPS1002.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii1FIPS5101.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii2FIPS5102.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii3FIPS5103.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii4FIPS5104.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii5FIPS5105.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoCentralFIPS1102.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoEastFIPS1101.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoWestFIPS1103.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIllinoisEastFIPS1201.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIllinoisWestFIPS1202.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIndianaEastFIPS1301.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIndianaWestFIPS1302.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIowaNorthFIPS1401.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIowaSouthFIPS1402.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneKansasNorthFIPS1501.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneKansasSouthFIPS1502.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneKentuckyNorthFIPS1601.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneKentuckySouthFIPS1602.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneLouisianaNorthFIPS1701.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneLouisianaSouthFIPS1702.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMaineEastFIPS1801.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMaineWestFIPS1802.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMarylandFIPS1900.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMassachusettsIslandFIPS2002.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganCentralFIPS2112.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganNorthFIPS2111.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganSouthFIPS2113.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMinnesotaCentralFIPS2202.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMinnesotaNorthFIPS2201.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMinnesotaSouthFIPS2203.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMississippiEastFIPS2301.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMississippiWestFIPS2302.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMissouriCentralFIPS2402.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMissouriEastFIPS2401.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMissouriWestFIPS2403.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMontanaFIPS2500.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNebraskaFIPS2600.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNevadaCentralFIPS2702.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNevadaEastFIPS2701.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNevadaWestFIPS2703.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewHampshireFIPS2800.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewJerseyFIPS2900.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoCentralFIPS3002.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoEastFIPS3001.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoWestFIPS3003.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkCentralFIPS3102.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkEastFIPS3101.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkWestFIPS3103.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneOhioNorthFIPS3401.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneOhioSouthFIPS3402.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneOklahomaNorthFIPS3501.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneOklahomaSouthFIPS3502.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneOregonNorthFIPS3601.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneOregonSouthFIPS3602.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlanePRVirginIslandsFIPS5200.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneRhodeIslandFIPS3800.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneSouthDakotaNorthFIPS4001.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneSouthDakotaSouthFIPS4002.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTennesseeFIPS4100.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasCentralFIPS4203.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasNorthCentralFIPS4202.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasNorthFIPS4201.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasSouthCentralFIPS4204.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasSouthFIPS4205.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahCentralFIPS4302.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahNorthFIPS4301.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahSouthFIPS4303.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneVermontFIPS4400.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneVirginiaNorthFIPS4501.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneVirginiaSouthFIPS4502.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWashingtonNorthFIPS4601.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWashingtonSouthFIPS4602.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWestVirginiaNorthFIPS4701.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWestVirginiaSouthFIPS4702.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinCentralFIPS4802.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinNorthFIPS4801.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinSouthFIPS4803.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWyomingEastFIPS4901.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWyomingECFIPS4902.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWyomingWCFIPS4903.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWyomingWestFIPS4904.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
  }

  //</editor-fold>

  /**
   * @return the NAD1983HARNMaine2000CentralZone
   */
  public ProjectionInfo getNAD1983HARNMaine2000CentralZone() {
    return NAD1983HARNMaine2000CentralZone.copy();
  }

  /**
   * @return the NAD1983HARNMaine2000EastZone
   */
  public ProjectionInfo getNAD1983HARNMaine2000EastZone() {
    return NAD1983HARNMaine2000EastZone.copy();
  }

  /**
   * @return the NAD1983HARNMaine2000WestZone
   */
  public ProjectionInfo getNAD1983HARNMaine2000WestZone() {
    return NAD1983HARNMaine2000WestZone.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneAlabamaEastFIPS0101
   */
  public ProjectionInfo getNAD1983HARNStatePlaneAlabamaEastFIPS0101() {
    return NAD1983HARNStatePlaneAlabamaEastFIPS0101.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneAlabamaWestFIPS0102
   */
  public ProjectionInfo getNAD1983HARNStatePlaneAlabamaWestFIPS0102() {
    return NAD1983HARNStatePlaneAlabamaWestFIPS0102.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneArizonaCentralFIPS0202
   */
  public ProjectionInfo getNAD1983HARNStatePlaneArizonaCentralFIPS0202() {
    return NAD1983HARNStatePlaneArizonaCentralFIPS0202.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneArizonaEastFIPS0201
   */
  public ProjectionInfo getNAD1983HARNStatePlaneArizonaEastFIPS0201() {
    return NAD1983HARNStatePlaneArizonaEastFIPS0201.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneArizonaWestFIPS0203
   */
  public ProjectionInfo getNAD1983HARNStatePlaneArizonaWestFIPS0203() {
    return NAD1983HARNStatePlaneArizonaWestFIPS0203.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneArkansasNorthFIPS0301
   */
  public ProjectionInfo getNAD1983HARNStatePlaneArkansasNorthFIPS0301() {
    return NAD1983HARNStatePlaneArkansasNorthFIPS0301.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneArkansasSouthFIPS0302
   */
  public ProjectionInfo getNAD1983HARNStatePlaneArkansasSouthFIPS0302() {
    return NAD1983HARNStatePlaneArkansasSouthFIPS0302.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaIFIPS0401
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaIFIPS0401() {
    return NAD1983HARNStatePlaneCaliforniaIFIPS0401.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaIIFIPS0402
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaIIFIPS0402() {
    return NAD1983HARNStatePlaneCaliforniaIIFIPS0402.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaIIIFIPS0403
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaIIIFIPS0403() {
    return NAD1983HARNStatePlaneCaliforniaIIIFIPS0403.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaIVFIPS0404
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaIVFIPS0404() {
    return NAD1983HARNStatePlaneCaliforniaIVFIPS0404.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaVFIPS0405
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaVFIPS0405() {
    return NAD1983HARNStatePlaneCaliforniaVFIPS0405.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaVIFIPS0406
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaVIFIPS0406() {
    return NAD1983HARNStatePlaneCaliforniaVIFIPS0406.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneColoradoCentralFIPS0502
   */
  public ProjectionInfo getNAD1983HARNStatePlaneColoradoCentralFIPS0502() {
    return NAD1983HARNStatePlaneColoradoCentralFIPS0502.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneColoradoNorthFIPS0501
   */
  public ProjectionInfo getNAD1983HARNStatePlaneColoradoNorthFIPS0501() {
    return NAD1983HARNStatePlaneColoradoNorthFIPS0501.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneColoradoSouthFIPS0503
   */
  public ProjectionInfo getNAD1983HARNStatePlaneColoradoSouthFIPS0503() {
    return NAD1983HARNStatePlaneColoradoSouthFIPS0503.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneConnecticutFIPS0600
   */
  public ProjectionInfo getNAD1983HARNStatePlaneConnecticutFIPS0600() {
    return NAD1983HARNStatePlaneConnecticutFIPS0600.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneDelawareFIPS0700
   */
  public ProjectionInfo getNAD1983HARNStatePlaneDelawareFIPS0700() {
    return NAD1983HARNStatePlaneDelawareFIPS0700.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneFloridaEastFIPS0901
   */
  public ProjectionInfo getNAD1983HARNStatePlaneFloridaEastFIPS0901() {
    return NAD1983HARNStatePlaneFloridaEastFIPS0901.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneFloridaNorthFIPS0903
   */
  public ProjectionInfo getNAD1983HARNStatePlaneFloridaNorthFIPS0903() {
    return NAD1983HARNStatePlaneFloridaNorthFIPS0903.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneFloridaWestFIPS0902
   */
  public ProjectionInfo getNAD1983HARNStatePlaneFloridaWestFIPS0902() {
    return NAD1983HARNStatePlaneFloridaWestFIPS0902.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneGeorgiaEastFIPS1001
   */
  public ProjectionInfo getNAD1983HARNStatePlaneGeorgiaEastFIPS1001() {
    return NAD1983HARNStatePlaneGeorgiaEastFIPS1001.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneGeorgiaWestFIPS1002
   */
  public ProjectionInfo getNAD1983HARNStatePlaneGeorgiaWestFIPS1002() {
    return NAD1983HARNStatePlaneGeorgiaWestFIPS1002.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneHawaii1FIPS5101
   */
  public ProjectionInfo getNAD1983HARNStatePlaneHawaii1FIPS5101() {
    return NAD1983HARNStatePlaneHawaii1FIPS5101.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneHawaii2FIPS5102
   */
  public ProjectionInfo getNAD1983HARNStatePlaneHawaii2FIPS5102() {
    return NAD1983HARNStatePlaneHawaii2FIPS5102.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneHawaii3FIPS5103
   */
  public ProjectionInfo getNAD1983HARNStatePlaneHawaii3FIPS5103() {
    return NAD1983HARNStatePlaneHawaii3FIPS5103.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneHawaii4FIPS5104
   */
  public ProjectionInfo getNAD1983HARNStatePlaneHawaii4FIPS5104() {
    return NAD1983HARNStatePlaneHawaii4FIPS5104.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneHawaii5FIPS5105
   */
  public ProjectionInfo getNAD1983HARNStatePlaneHawaii5FIPS5105() {
    return NAD1983HARNStatePlaneHawaii5FIPS5105.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIdahoCentralFIPS1102
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIdahoCentralFIPS1102() {
    return NAD1983HARNStatePlaneIdahoCentralFIPS1102.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIdahoEastFIPS1101
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIdahoEastFIPS1101() {
    return NAD1983HARNStatePlaneIdahoEastFIPS1101.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIdahoWestFIPS1103
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIdahoWestFIPS1103() {
    return NAD1983HARNStatePlaneIdahoWestFIPS1103.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIllinoisEastFIPS1201
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIllinoisEastFIPS1201() {
    return NAD1983HARNStatePlaneIllinoisEastFIPS1201.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIllinoisWestFIPS1202
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIllinoisWestFIPS1202() {
    return NAD1983HARNStatePlaneIllinoisWestFIPS1202.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIndianaEastFIPS1301
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIndianaEastFIPS1301() {
    return NAD1983HARNStatePlaneIndianaEastFIPS1301.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIndianaWestFIPS1302
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIndianaWestFIPS1302() {
    return NAD1983HARNStatePlaneIndianaWestFIPS1302.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIowaNorthFIPS1401
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIowaNorthFIPS1401() {
    return NAD1983HARNStatePlaneIowaNorthFIPS1401.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIowaSouthFIPS1402
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIowaSouthFIPS1402() {
    return NAD1983HARNStatePlaneIowaSouthFIPS1402.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneKansasNorthFIPS1501
   */
  public ProjectionInfo getNAD1983HARNStatePlaneKansasNorthFIPS1501() {
    return NAD1983HARNStatePlaneKansasNorthFIPS1501.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneKansasSouthFIPS1502
   */
  public ProjectionInfo getNAD1983HARNStatePlaneKansasSouthFIPS1502() {
    return NAD1983HARNStatePlaneKansasSouthFIPS1502.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneKentuckyNorthFIPS1601
   */
  public ProjectionInfo getNAD1983HARNStatePlaneKentuckyNorthFIPS1601() {
    return NAD1983HARNStatePlaneKentuckyNorthFIPS1601.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneKentuckySouthFIPS1602
   */
  public ProjectionInfo getNAD1983HARNStatePlaneKentuckySouthFIPS1602() {
    return NAD1983HARNStatePlaneKentuckySouthFIPS1602.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneLouisianaNorthFIPS1701
   */
  public ProjectionInfo getNAD1983HARNStatePlaneLouisianaNorthFIPS1701() {
    return NAD1983HARNStatePlaneLouisianaNorthFIPS1701.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneLouisianaSouthFIPS1702
   */
  public ProjectionInfo getNAD1983HARNStatePlaneLouisianaSouthFIPS1702() {
    return NAD1983HARNStatePlaneLouisianaSouthFIPS1702.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMaineEastFIPS1801
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMaineEastFIPS1801() {
    return NAD1983HARNStatePlaneMaineEastFIPS1801.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMaineWestFIPS1802
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMaineWestFIPS1802() {
    return NAD1983HARNStatePlaneMaineWestFIPS1802.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMarylandFIPS1900
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMarylandFIPS1900() {
    return NAD1983HARNStatePlaneMarylandFIPS1900.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMassachusettsIslandFIPS2002
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMassachusettsIslandFIPS2002() {
    return NAD1983HARNStatePlaneMassachusettsIslandFIPS2002.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMassachusettsMainlandFIPS2001() {
    return NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMichiganCentralFIPS2112
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMichiganCentralFIPS2112() {
    return NAD1983HARNStatePlaneMichiganCentralFIPS2112.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMichiganNorthFIPS2111
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMichiganNorthFIPS2111() {
    return NAD1983HARNStatePlaneMichiganNorthFIPS2111.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMichiganSouthFIPS2113
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMichiganSouthFIPS2113() {
    return NAD1983HARNStatePlaneMichiganSouthFIPS2113.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMinnesotaCentralFIPS2202
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMinnesotaCentralFIPS2202() {
    return NAD1983HARNStatePlaneMinnesotaCentralFIPS2202.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMinnesotaNorthFIPS2201
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMinnesotaNorthFIPS2201() {
    return NAD1983HARNStatePlaneMinnesotaNorthFIPS2201.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMinnesotaSouthFIPS2203
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMinnesotaSouthFIPS2203() {
    return NAD1983HARNStatePlaneMinnesotaSouthFIPS2203.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMississippiEastFIPS2301
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMississippiEastFIPS2301() {
    return NAD1983HARNStatePlaneMississippiEastFIPS2301.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMississippiWestFIPS2302
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMississippiWestFIPS2302() {
    return NAD1983HARNStatePlaneMississippiWestFIPS2302.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMissouriCentralFIPS2402
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMissouriCentralFIPS2402() {
    return NAD1983HARNStatePlaneMissouriCentralFIPS2402.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMissouriEastFIPS2401
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMissouriEastFIPS2401() {
    return NAD1983HARNStatePlaneMissouriEastFIPS2401.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMissouriWestFIPS2403
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMissouriWestFIPS2403() {
    return NAD1983HARNStatePlaneMissouriWestFIPS2403.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMontanaFIPS2500
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMontanaFIPS2500() {
    return NAD1983HARNStatePlaneMontanaFIPS2500.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNebraskaFIPS2600
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNebraskaFIPS2600() {
    return NAD1983HARNStatePlaneNebraskaFIPS2600.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNevadaCentralFIPS2702
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNevadaCentralFIPS2702() {
    return NAD1983HARNStatePlaneNevadaCentralFIPS2702.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNevadaEastFIPS2701
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNevadaEastFIPS2701() {
    return NAD1983HARNStatePlaneNevadaEastFIPS2701.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNevadaWestFIPS2703
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNevadaWestFIPS2703() {
    return NAD1983HARNStatePlaneNevadaWestFIPS2703.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewHampshireFIPS2800
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewHampshireFIPS2800() {
    return NAD1983HARNStatePlaneNewHampshireFIPS2800.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewJerseyFIPS2900
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewJerseyFIPS2900() {
    return NAD1983HARNStatePlaneNewJerseyFIPS2900.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewMexicoCentralFIPS3002
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewMexicoCentralFIPS3002() {
    return NAD1983HARNStatePlaneNewMexicoCentralFIPS3002.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewMexicoEastFIPS3001
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewMexicoEastFIPS3001() {
    return NAD1983HARNStatePlaneNewMexicoEastFIPS3001.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewMexicoWestFIPS3003
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewMexicoWestFIPS3003() {
    return NAD1983HARNStatePlaneNewMexicoWestFIPS3003.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewYorkCentralFIPS3102
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewYorkCentralFIPS3102() {
    return NAD1983HARNStatePlaneNewYorkCentralFIPS3102.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewYorkEastFIPS3101
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewYorkEastFIPS3101() {
    return NAD1983HARNStatePlaneNewYorkEastFIPS3101.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewYorkLongIslandFIPS3104() {
    return NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewYorkWestFIPS3103
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewYorkWestFIPS3103() {
    return NAD1983HARNStatePlaneNewYorkWestFIPS3103.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNorthDakotaNorthFIPS3301() {
    return NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNorthDakotaSouthFIPS3302() {
    return NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneOhioNorthFIPS3401
   */
  public ProjectionInfo getNAD1983HARNStatePlaneOhioNorthFIPS3401() {
    return NAD1983HARNStatePlaneOhioNorthFIPS3401.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneOhioSouthFIPS3402
   */
  public ProjectionInfo getNAD1983HARNStatePlaneOhioSouthFIPS3402() {
    return NAD1983HARNStatePlaneOhioSouthFIPS3402.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneOklahomaNorthFIPS3501
   */
  public ProjectionInfo getNAD1983HARNStatePlaneOklahomaNorthFIPS3501() {
    return NAD1983HARNStatePlaneOklahomaNorthFIPS3501.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneOklahomaSouthFIPS3502
   */
  public ProjectionInfo getNAD1983HARNStatePlaneOklahomaSouthFIPS3502() {
    return NAD1983HARNStatePlaneOklahomaSouthFIPS3502.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneOregonNorthFIPS3601
   */
  public ProjectionInfo getNAD1983HARNStatePlaneOregonNorthFIPS3601() {
    return NAD1983HARNStatePlaneOregonNorthFIPS3601.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneOregonSouthFIPS3602
   */
  public ProjectionInfo getNAD1983HARNStatePlaneOregonSouthFIPS3602() {
    return NAD1983HARNStatePlaneOregonSouthFIPS3602.copy();
  }

  /**
   * @return the NAD1983HARNStatePlanePRVirginIslandsFIPS5200
   */
  public ProjectionInfo getNAD1983HARNStatePlanePRVirginIslandsFIPS5200() {
    return NAD1983HARNStatePlanePRVirginIslandsFIPS5200.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneRhodeIslandFIPS3800
   */
  public ProjectionInfo getNAD1983HARNStatePlaneRhodeIslandFIPS3800() {
    return NAD1983HARNStatePlaneRhodeIslandFIPS3800.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneSouthDakotaNorthFIPS4001
   */
  public ProjectionInfo getNAD1983HARNStatePlaneSouthDakotaNorthFIPS4001() {
    return NAD1983HARNStatePlaneSouthDakotaNorthFIPS4001.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneSouthDakotaSouthFIPS4002
   */
  public ProjectionInfo getNAD1983HARNStatePlaneSouthDakotaSouthFIPS4002() {
    return NAD1983HARNStatePlaneSouthDakotaSouthFIPS4002.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTennesseeFIPS4100
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTennesseeFIPS4100() {
    return NAD1983HARNStatePlaneTennesseeFIPS4100.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTexasCentralFIPS4203
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTexasCentralFIPS4203() {
    return NAD1983HARNStatePlaneTexasCentralFIPS4203.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTexasNorthCentralFIPS4202
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTexasNorthCentralFIPS4202() {
    return NAD1983HARNStatePlaneTexasNorthCentralFIPS4202.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTexasNorthFIPS4201
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTexasNorthFIPS4201() {
    return NAD1983HARNStatePlaneTexasNorthFIPS4201.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTexasSouthCentralFIPS4204
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTexasSouthCentralFIPS4204() {
    return NAD1983HARNStatePlaneTexasSouthCentralFIPS4204.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTexasSouthFIPS4205
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTexasSouthFIPS4205() {
    return NAD1983HARNStatePlaneTexasSouthFIPS4205.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneUtahCentralFIPS4302
   */
  public ProjectionInfo getNAD1983HARNStatePlaneUtahCentralFIPS4302() {
    return NAD1983HARNStatePlaneUtahCentralFIPS4302.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneUtahNorthFIPS4301
   */
  public ProjectionInfo getNAD1983HARNStatePlaneUtahNorthFIPS4301() {
    return NAD1983HARNStatePlaneUtahNorthFIPS4301.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneUtahSouthFIPS4303
   */
  public ProjectionInfo getNAD1983HARNStatePlaneUtahSouthFIPS4303() {
    return NAD1983HARNStatePlaneUtahSouthFIPS4303.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneVermontFIPS4400
   */
  public ProjectionInfo getNAD1983HARNStatePlaneVermontFIPS4400() {
    return NAD1983HARNStatePlaneVermontFIPS4400.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneVirginiaNorthFIPS4501
   */
  public ProjectionInfo getNAD1983HARNStatePlaneVirginiaNorthFIPS4501() {
    return NAD1983HARNStatePlaneVirginiaNorthFIPS4501.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneVirginiaSouthFIPS4502
   */
  public ProjectionInfo getNAD1983HARNStatePlaneVirginiaSouthFIPS4502() {
    return NAD1983HARNStatePlaneVirginiaSouthFIPS4502.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWashingtonNorthFIPS4601
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWashingtonNorthFIPS4601() {
    return NAD1983HARNStatePlaneWashingtonNorthFIPS4601.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWashingtonSouthFIPS4602
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWashingtonSouthFIPS4602() {
    return NAD1983HARNStatePlaneWashingtonSouthFIPS4602.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWestVirginiaNorthFIPS4701
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWestVirginiaNorthFIPS4701() {
    return NAD1983HARNStatePlaneWestVirginiaNorthFIPS4701.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWestVirginiaSouthFIPS4702
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWestVirginiaSouthFIPS4702() {
    return NAD1983HARNStatePlaneWestVirginiaSouthFIPS4702.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWisconsinCentralFIPS4802
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWisconsinCentralFIPS4802() {
    return NAD1983HARNStatePlaneWisconsinCentralFIPS4802.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWisconsinNorthFIPS4801
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWisconsinNorthFIPS4801() {
    return NAD1983HARNStatePlaneWisconsinNorthFIPS4801.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWisconsinSouthFIPS4803
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWisconsinSouthFIPS4803() {
    return NAD1983HARNStatePlaneWisconsinSouthFIPS4803.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWyomingECFIPS4902
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWyomingECFIPS4902() {
    return NAD1983HARNStatePlaneWyomingECFIPS4902.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWyomingEastFIPS4901
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWyomingEastFIPS4901() {
    return NAD1983HARNStatePlaneWyomingEastFIPS4901.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWyomingWCFIPS4903
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWyomingWCFIPS4903() {
    return NAD1983HARNStatePlaneWyomingWCFIPS4903.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWyomingWestFIPS4904
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWyomingWestFIPS4904() {
    return NAD1983HARNStatePlaneWyomingWestFIPS4904.copy();
  }
}
