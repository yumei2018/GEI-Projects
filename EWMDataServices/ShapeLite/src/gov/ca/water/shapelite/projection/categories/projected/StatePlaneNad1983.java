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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:57:57 PM
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
/// StatePlaneNad1983
/// </summary>

public class StatePlaneNad1983 extends CoordinateSystemCategory {
        //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo MichiganGeoRef2008;
  private final ProjectionInfo NAD1983Maine2000CentralZone;
  private final ProjectionInfo NAD1983Maine2000EastZone;
  private final ProjectionInfo NAD1983Maine2000WestZone;
  private final ProjectionInfo NAD1983StatePlaneAlabamaEastFIPS0101;
  private final ProjectionInfo NAD1983StatePlaneAlabamaWestFIPS0102;
  private final ProjectionInfo NAD1983StatePlaneAlaska10FIPS5010;
  private final ProjectionInfo NAD1983StatePlaneAlaska1FIPS5001;
  private final ProjectionInfo NAD1983StatePlaneAlaska2FIPS5002;
  private final ProjectionInfo NAD1983StatePlaneAlaska3FIPS5003;
  private final ProjectionInfo NAD1983StatePlaneAlaska4FIPS5004;
  private final ProjectionInfo NAD1983StatePlaneAlaska5FIPS5005;
  private final ProjectionInfo NAD1983StatePlaneAlaska6FIPS5006;
  private final ProjectionInfo NAD1983StatePlaneAlaska7FIPS5007;
  private final ProjectionInfo NAD1983StatePlaneAlaska8FIPS5008;
  private final ProjectionInfo NAD1983StatePlaneAlaska9FIPS5009;
  private final ProjectionInfo NAD1983StatePlaneArizonaCentralFIPS0202;
  private final ProjectionInfo NAD1983StatePlaneArizonaEastFIPS0201;
  private final ProjectionInfo NAD1983StatePlaneArizonaWestFIPS0203;
  private final ProjectionInfo NAD1983StatePlaneArkansasNorthFIPS0301;
  private final ProjectionInfo NAD1983StatePlaneArkansasSouthFIPS0302;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaIFIPS0401;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaIIFIPS0402;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaIIIFIPS0403;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaIVFIPS0404;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaVFIPS0405;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaVIFIPS0406;
  private final ProjectionInfo NAD1983StatePlaneColoradoCentralFIPS0502;
  private final ProjectionInfo NAD1983StatePlaneColoradoNorthFIPS0501;
  private final ProjectionInfo NAD1983StatePlaneColoradoSouthFIPS0503;
  private final ProjectionInfo NAD1983StatePlaneConnecticutFIPS0600;
  private final ProjectionInfo NAD1983StatePlaneDelawareFIPS0700;
  private final ProjectionInfo NAD1983StatePlaneFloridaEastFIPS0901;
  private final ProjectionInfo NAD1983StatePlaneFloridaNorthFIPS0903;
  private final ProjectionInfo NAD1983StatePlaneFloridaWestFIPS0902;
  private final ProjectionInfo NAD1983StatePlaneGeorgiaEastFIPS1001;
  private final ProjectionInfo NAD1983StatePlaneGeorgiaWestFIPS1002;
  private final ProjectionInfo NAD1983StatePlaneGuamFIPS5400;
  private final ProjectionInfo NAD1983StatePlaneHawaii1FIPS5101;
  private final ProjectionInfo NAD1983StatePlaneHawaii2FIPS5102;
  private final ProjectionInfo NAD1983StatePlaneHawaii3FIPS5103;
  private final ProjectionInfo NAD1983StatePlaneHawaii4FIPS5104;
  private final ProjectionInfo NAD1983StatePlaneHawaii5FIPS5105;
  private final ProjectionInfo NAD1983StatePlaneIdahoCentralFIPS1102;
  private final ProjectionInfo NAD1983StatePlaneIdahoEastFIPS1101;
  private final ProjectionInfo NAD1983StatePlaneIdahoWestFIPS1103;
  private final ProjectionInfo NAD1983StatePlaneIllinoisEastFIPS1201;
  private final ProjectionInfo NAD1983StatePlaneIllinoisWestFIPS1202;
  private final ProjectionInfo NAD1983StatePlaneIndianaEastFIPS1301;
  private final ProjectionInfo NAD1983StatePlaneIndianaWestFIPS1302;
  private final ProjectionInfo NAD1983StatePlaneIowaNorthFIPS1401;
  private final ProjectionInfo NAD1983StatePlaneIowaSouthFIPS1402;
  private final ProjectionInfo NAD1983StatePlaneKansasNorthFIPS1501;
  private final ProjectionInfo NAD1983StatePlaneKansasSouthFIPS1502;
  private final ProjectionInfo NAD1983StatePlaneKentuckyFIPS1600;
  private final ProjectionInfo NAD1983StatePlaneKentuckyNorthFIPS1601;
  private final ProjectionInfo NAD1983StatePlaneKentuckySouthFIPS1602;
  private final ProjectionInfo NAD1983StatePlaneLouisianaNorthFIPS1701;
  private final ProjectionInfo NAD1983StatePlaneLouisianaSouthFIPS1702;
  private final ProjectionInfo NAD1983StatePlaneMaineEastFIPS1801;
  private final ProjectionInfo NAD1983StatePlaneMaineWestFIPS1802;
  private final ProjectionInfo NAD1983StatePlaneMarylandFIPS1900;
  private final ProjectionInfo NAD1983StatePlaneMassachusettsIslandFIPS2002;
  private final ProjectionInfo NAD1983StatePlaneMassachusettsMainlandFIPS2001;
  private final ProjectionInfo NAD1983StatePlaneMichiganCentralFIPS2112;
  private final ProjectionInfo NAD1983StatePlaneMichiganNorthFIPS2111;
  private final ProjectionInfo NAD1983StatePlaneMichiganSouthFIPS2113;
  private final ProjectionInfo NAD1983StatePlaneMinnesotaCentralFIPS2202;
  private final ProjectionInfo NAD1983StatePlaneMinnesotaNorthFIPS2201;
  private final ProjectionInfo NAD1983StatePlaneMinnesotaSouthFIPS2203;
  private final ProjectionInfo NAD1983StatePlaneMississippiEastFIPS2301;
  private final ProjectionInfo NAD1983StatePlaneMississippiWestFIPS2302;
  private final ProjectionInfo NAD1983StatePlaneMissouriCentralFIPS2402;
  private final ProjectionInfo NAD1983StatePlaneMissouriEastFIPS2401;
  private final ProjectionInfo NAD1983StatePlaneMissouriWestFIPS2403;
  private final ProjectionInfo NAD1983StatePlaneMontanaFIPS2500;
  private final ProjectionInfo NAD1983StatePlaneNebraskaFIPS2600;
  private final ProjectionInfo NAD1983StatePlaneNevadaCentralFIPS2702;
  private final ProjectionInfo NAD1983StatePlaneNevadaEastFIPS2701;
  private final ProjectionInfo NAD1983StatePlaneNevadaWestFIPS2703;
  private final ProjectionInfo NAD1983StatePlaneNewHampshireFIPS2800;
  private final ProjectionInfo NAD1983StatePlaneNewJerseyFIPS2900;
  private final ProjectionInfo NAD1983StatePlaneNewMexicoCentralFIPS3002;
  private final ProjectionInfo NAD1983StatePlaneNewMexicoEastFIPS3001;
  private final ProjectionInfo NAD1983StatePlaneNewMexicoWestFIPS3003;
  private final ProjectionInfo NAD1983StatePlaneNewYorkCentralFIPS3102;
  private final ProjectionInfo NAD1983StatePlaneNewYorkEastFIPS3101;
  private final ProjectionInfo NAD1983StatePlaneNewYorkLongIslandFIPS3104;
  private final ProjectionInfo NAD1983StatePlaneNewYorkWestFIPS3103;
  private final ProjectionInfo NAD1983StatePlaneNorthCarolinaFIPS3200;
  private final ProjectionInfo NAD1983StatePlaneNorthDakotaNorthFIPS3301;
  private final ProjectionInfo NAD1983StatePlaneNorthDakotaSouthFIPS3302;
  private final ProjectionInfo NAD1983StatePlaneOhioNorthFIPS3401;
  private final ProjectionInfo NAD1983StatePlaneOhioSouthFIPS3402;
  private final ProjectionInfo NAD1983StatePlaneOklahomaNorthFIPS3501;
  private final ProjectionInfo NAD1983StatePlaneOklahomaSouthFIPS3502;
  private final ProjectionInfo NAD1983StatePlaneOregonNorthFIPS3601;
  private final ProjectionInfo NAD1983StatePlaneOregonSouthFIPS3602;
  private final ProjectionInfo NAD1983StatePlanePennsylvaniaNorthFIPS3701;
  private final ProjectionInfo NAD1983StatePlanePennsylvaniaSouthFIPS3702;
  private final ProjectionInfo NAD1983StatePlanePuertoRicoVirginIslandsFIPS5200;
  private final ProjectionInfo NAD1983StatePlaneRhodeIslandFIPS3800;
  private final ProjectionInfo NAD1983StatePlaneSouthCarolinaFIPS3900;
  private final ProjectionInfo NAD1983StatePlaneSouthDakotaNorthFIPS4001;
  private final ProjectionInfo NAD1983StatePlaneSouthDakotaSouthFIPS4002;
  private final ProjectionInfo NAD1983StatePlaneTennesseeFIPS4100;
  private final ProjectionInfo NAD1983StatePlaneTexasCentralFIPS4203;
  private final ProjectionInfo NAD1983StatePlaneTexasNorthCentralFIPS4202;
  private final ProjectionInfo NAD1983StatePlaneTexasNorthFIPS4201;
  private final ProjectionInfo NAD1983StatePlaneTexasSouthCentralFIPS4204;
  private final ProjectionInfo NAD1983StatePlaneTexasSouthFIPS4205;
  private final ProjectionInfo NAD1983StatePlaneUtahCentralFIPS4302;
  private final ProjectionInfo NAD1983StatePlaneUtahNorthFIPS4301;
  private final ProjectionInfo NAD1983StatePlaneUtahSouthFIPS4303;
  private final ProjectionInfo NAD1983StatePlaneVermontFIPS4400;
  private final ProjectionInfo NAD1983StatePlaneVirginiaNorthFIPS4501;
  private final ProjectionInfo NAD1983StatePlaneVirginiaSouthFIPS4502;
  private final ProjectionInfo NAD1983StatePlaneWashingtonNorthFIPS4601;
  private final ProjectionInfo NAD1983StatePlaneWashingtonSouthFIPS4602;
  private final ProjectionInfo NAD1983StatePlaneWestVirginiaNorthFIPS4701;
  private final ProjectionInfo NAD1983StatePlaneWestVirginiaSouthFIPS4702;
  private final ProjectionInfo NAD1983StatePlaneWisconsinCentralFIPS4802;
  private final ProjectionInfo NAD1983StatePlaneWisconsinNorthFIPS4801;
  private final ProjectionInfo NAD1983StatePlaneWisconsinSouthFIPS4803;
  private final ProjectionInfo NAD1983StatePlaneWyomingEastCentralFIPS4902;
  private final ProjectionInfo NAD1983StatePlaneWyomingEastFIPS4901;
  private final ProjectionInfo NAD1983StatePlaneWyomingWestCentralFIPS4903;
  private final ProjectionInfo NAD1983StatePlaneWyomingWestFIPS4904;

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of StatePlaneNad1983.
   */
  public StatePlaneNad1983() {
    MichiganGeoRef2008
        = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=45.30916666666666 +lonc=-86 +alpha=337.25556 +k=0.9996 +x_0=2546731.496 +y_0=-4354009.816 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983Maine2000CentralZone
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.5 +lon_0=-69.125 +k=0.999980 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983Maine2000EastZone
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.83333333333334 +lon_0=-67.875 +k=0.999980 +x_0=700000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983Maine2000WestZone
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.83333333333334 +lon_0=-70.375 +k=0.999980 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlabamaEastFIPS0101
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30.5 +lon_0=-85.83333333333333 +k=0.999960 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlabamaWestFIPS0102
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-87.5 +k=0.999933 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska10FIPS5010
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=51.83333333333334 +lat_2=53.83333333333334 +lat_0=51 +lon_0=-176 +x_0=1000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska1FIPS5001
        = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=57 +lonc=-133.6666666666667 +alpha=-36.86989764583333 +k=0.9999 +x_0=5000000 +y_0=-5000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska2FIPS5002
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-142 +k=0.999900 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska3FIPS5003
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-146 +k=0.999900 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska4FIPS5004
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-150 +k=0.999900 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska5FIPS5005
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-154 +k=0.999900 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska6FIPS5006
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-158 +k=0.999900 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska7FIPS5007
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-162 +k=0.999900 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska8FIPS5008
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-166 +k=0.999900 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska9FIPS5009
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-170 +k=0.999900 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneArizonaCentralFIPS0202
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-111.9166666666667 +k=0.999900 +x_0=213360 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneArizonaEastFIPS0201
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-110.1666666666667 +k=0.999900 +x_0=213360 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneArizonaWestFIPS0203
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-113.75 +k=0.999933 +x_0=213360 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneArkansasNorthFIPS0301
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.93333333333333 +lat_2=36.23333333333333 +lat_0=34.33333333333334 +lon_0=-92 +x_0=400000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneArkansasSouthFIPS0302
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.3 +lat_2=34.76666666666667 +lat_0=32.66666666666666 +lon_0=-92 +x_0=400000 +y_0=400000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaIFIPS0401
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40 +lat_2=41.66666666666666 +lat_0=39.33333333333334 +lon_0=-122 +x_0=2000000 +y_0=500000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaIIFIPS0402
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.33333333333334 +lat_2=39.83333333333334 +lat_0=37.66666666666666 +lon_0=-122 +x_0=2000000 +y_0=500000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaIIIFIPS0403
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.06666666666667 +lat_2=38.43333333333333 +lat_0=36.5 +lon_0=-120.5 +x_0=2000000 +y_0=500000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaIVFIPS0404
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36 +lat_2=37.25 +lat_0=35.33333333333334 +lon_0=-119 +x_0=2000000 +y_0=500000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaVFIPS0405
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.03333333333333 +lat_2=35.46666666666667 +lat_0=33.5 +lon_0=-118 +x_0=2000000 +y_0=500000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaVIFIPS0406
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.78333333333333 +lat_2=33.88333333333333 +lat_0=32.16666666666666 +lon_0=-116.25 +x_0=2000000 +y_0=500000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneColoradoCentralFIPS0502
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.45 +lat_2=39.75 +lat_0=37.83333333333334 +lon_0=-105.5 +x_0=914401.8289 +y_0=304800.6096 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneColoradoNorthFIPS0501
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.71666666666667 +lat_2=40.78333333333333 +lat_0=39.33333333333334 +lon_0=-105.5 +x_0=914401.8289 +y_0=304800.6096 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneColoradoSouthFIPS0503
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.23333333333333 +lat_2=38.43333333333333 +lat_0=36.66666666666666 +lon_0=-105.5 +x_0=914401.8289 +y_0=304800.6096 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneConnecticutFIPS0600
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.2 +lat_2=41.86666666666667 +lat_0=40.83333333333334 +lon_0=-72.75 +x_0=304800.6096 +y_0=152400.3048 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneDelawareFIPS0700
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38 +lon_0=-75.41666666666667 +k=0.999995 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneFloridaEastFIPS0901
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=24.33333333333333 +lon_0=-81 +k=0.999941 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneFloridaNorthFIPS0903
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=29.58333333333333 +lat_2=30.75 +lat_0=29 +lon_0=-84.5 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneFloridaWestFIPS0902
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=24.33333333333333 +lon_0=-82 +k=0.999941 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneGeorgiaEastFIPS1001
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-82.16666666666667 +k=0.999900 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneGeorgiaWestFIPS1002
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-84.16666666666667 +k=0.999900 +x_0=700000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneGuamFIPS5400
        = ProjectionInfo.fromProj4String("+proj=poly +lat_0=13.47246635277778 +lon_0=144.7487507055556 +x_0=50000 +y_0=50000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneHawaii1FIPS5101
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=18.83333333333333 +lon_0=-155.5 +k=0.999967 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneHawaii2FIPS5102
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=20.33333333333333 +lon_0=-156.6666666666667 +k=0.999967 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneHawaii3FIPS5103
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.16666666666667 +lon_0=-158 +k=0.999990 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneHawaii4FIPS5104
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.83333333333333 +lon_0=-159.5 +k=0.999990 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneHawaii5FIPS5105
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.66666666666667 +lon_0=-160.1666666666667 +k=1.000000 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneIdahoCentralFIPS1102
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-114 +k=0.999947 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneIdahoEastFIPS1101
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-112.1666666666667 +k=0.999947 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneIdahoWestFIPS1103
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-115.75 +k=0.999933 +x_0=800000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneIllinoisEastFIPS1201
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36.66666666666666 +lon_0=-88.33333333333333 +k=0.999975 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneIllinoisWestFIPS1202
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36.66666666666666 +lon_0=-90.16666666666667 +k=0.999941 +x_0=700000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneIndianaEastFIPS1301
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=37.5 +lon_0=-85.66666666666667 +k=0.999967 +x_0=100000 +y_0=250000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneIndianaWestFIPS1302
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=37.5 +lon_0=-87.08333333333333 +k=0.999967 +x_0=900000 +y_0=250000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneIowaNorthFIPS1401
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.06666666666667 +lat_2=43.26666666666667 +lat_0=41.5 +lon_0=-93.5 +x_0=1500000 +y_0=1000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneIowaSouthFIPS1402
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.61666666666667 +lat_2=41.78333333333333 +lat_0=40 +lon_0=-93.5 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneKansasNorthFIPS1501
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.71666666666667 +lat_2=39.78333333333333 +lat_0=38.33333333333334 +lon_0=-98 +x_0=400000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneKansasSouthFIPS1502
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.26666666666667 +lat_2=38.56666666666667 +lat_0=36.66666666666666 +lon_0=-98.5 +x_0=400000 +y_0=400000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneKentuckyFIPS1600
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.08333333333334 +lat_2=38.66666666666666 +lat_0=36.33333333333334 +lon_0=-85.75 +x_0=1500000 +y_0=1000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneKentuckyNorthFIPS1601
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.96666666666667 +lat_2=38.96666666666667 +lat_0=37.5 +lon_0=-84.25 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneKentuckySouthFIPS1602
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36.73333333333333 +lat_2=37.93333333333333 +lat_0=36.33333333333334 +lon_0=-85.75 +x_0=500000 +y_0=500000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneLouisianaNorthFIPS1701
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=31.16666666666667 +lat_2=32.66666666666666 +lat_0=30.5 +lon_0=-92.5 +x_0=1000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneLouisianaSouthFIPS1702
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=29.3 +lat_2=30.7 +lat_0=28.5 +lon_0=-91.33333333333333 +x_0=1000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMaineEastFIPS1801
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.66666666666666 +lon_0=-68.5 +k=0.999900 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMaineWestFIPS1802
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.83333333333334 +lon_0=-70.16666666666667 +k=0.999967 +x_0=900000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMarylandFIPS1900
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.3 +lat_2=39.45 +lat_0=37.66666666666666 +lon_0=-77 +x_0=400000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMassachusettsIslandFIPS2002
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.28333333333333 +lat_2=41.48333333333333 +lat_0=41 +lon_0=-70.5 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMassachusettsMainlandFIPS2001
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.71666666666667 +lat_2=42.68333333333333 +lat_0=41 +lon_0=-71.5 +x_0=200000 +y_0=750000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMichiganCentralFIPS2112
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.18333333333333 +lat_2=45.7 +lat_0=43.31666666666667 +lon_0=-84.36666666666666 +x_0=6000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMichiganNorthFIPS2111
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.48333333333333 +lat_2=47.08333333333334 +lat_0=44.78333333333333 +lon_0=-87 +x_0=8000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMichiganSouthFIPS2113
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.1 +lat_2=43.66666666666666 +lat_0=41.5 +lon_0=-84.36666666666666 +x_0=4000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMinnesotaCentralFIPS2202
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.61666666666667 +lat_2=47.05 +lat_0=45 +lon_0=-94.25 +x_0=800000 +y_0=100000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMinnesotaNorthFIPS2201
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.03333333333333 +lat_2=48.63333333333333 +lat_0=46.5 +lon_0=-93.09999999999999 +x_0=800000 +y_0=100000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMinnesotaSouthFIPS2203
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.78333333333333 +lat_2=45.21666666666667 +lat_0=43 +lon_0=-94 +x_0=800000 +y_0=100000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMississippiEastFIPS2301
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=29.5 +lon_0=-88.83333333333333 +k=0.999950 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMississippiWestFIPS2302
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=29.5 +lon_0=-90.33333333333333 +k=0.999950 +x_0=700000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMissouriCentralFIPS2402
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=35.83333333333334 +lon_0=-92.5 +k=0.999933 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMissouriEastFIPS2401
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=35.83333333333334 +lon_0=-90.5 +k=0.999933 +x_0=250000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMissouriWestFIPS2403
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36.16666666666666 +lon_0=-94.5 +k=0.999941 +x_0=850000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneMontanaFIPS2500
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45 +lat_2=49 +lat_0=44.25 +lon_0=-109.5 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNebraskaFIPS2600
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40 +lat_2=43 +lat_0=39.83333333333334 +lon_0=-100 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNevadaCentralFIPS2702
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=34.75 +lon_0=-116.6666666666667 +k=0.999900 +x_0=500000 +y_0=6000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNevadaEastFIPS2701
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=34.75 +lon_0=-115.5833333333333 +k=0.999900 +x_0=200000 +y_0=8000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNevadaWestFIPS2703
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=34.75 +lon_0=-118.5833333333333 +k=0.999900 +x_0=800000 +y_0=4000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNewHampshireFIPS2800
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.5 +lon_0=-71.66666666666667 +k=0.999967 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNewJerseyFIPS2900
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38.83333333333334 +lon_0=-74.5 +k=0.999900 +x_0=150000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNewMexicoCentralFIPS3002
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-106.25 +k=0.999900 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNewMexicoEastFIPS3001
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-104.3333333333333 +k=0.999909 +x_0=165000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNewMexicoWestFIPS3003
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-107.8333333333333 +k=0.999917 +x_0=830000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNewYorkCentralFIPS3102
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40 +lon_0=-76.58333333333333 +k=0.999938 +x_0=250000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNewYorkEastFIPS3101
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38.83333333333334 +lon_0=-74.5 +k=0.999900 +x_0=150000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNewYorkLongIslandFIPS3104
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.66666666666666 +lat_2=41.03333333333333 +lat_0=40.16666666666666 +lon_0=-74 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNewYorkWestFIPS3103
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40 +lon_0=-78.58333333333333 +k=0.999938 +x_0=350000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNorthCarolinaFIPS3200
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.33333333333334 +lat_2=36.16666666666666 +lat_0=33.75 +lon_0=-79 +x_0=609601.22 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNorthDakotaNorthFIPS3301
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.43333333333333 +lat_2=48.73333333333333 +lat_0=47 +lon_0=-100.5 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneNorthDakotaSouthFIPS3302
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.18333333333333 +lat_2=47.48333333333333 +lat_0=45.66666666666666 +lon_0=-100.5 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneOhioNorthFIPS3401
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.43333333333333 +lat_2=41.7 +lat_0=39.66666666666666 +lon_0=-82.5 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneOhioSouthFIPS3402
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.73333333333333 +lat_2=40.03333333333333 +lat_0=38 +lon_0=-82.5 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneOklahomaNorthFIPS3501
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=35.56666666666667 +lat_2=36.76666666666667 +lat_0=35 +lon_0=-98 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneOklahomaSouthFIPS3502
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.93333333333333 +lat_2=35.23333333333333 +lat_0=33.33333333333334 +lon_0=-98 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneOregonNorthFIPS3601
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.33333333333334 +lat_2=46 +lat_0=43.66666666666666 +lon_0=-120.5 +x_0=2500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneOregonSouthFIPS3602
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.33333333333334 +lat_2=44 +lat_0=41.66666666666666 +lon_0=-120.5 +x_0=1500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlanePennsylvaniaNorthFIPS3701
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.88333333333333 +lat_2=41.95 +lat_0=40.16666666666666 +lon_0=-77.75 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlanePennsylvaniaSouthFIPS3702
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.93333333333333 +lat_2=40.96666666666667 +lat_0=39.33333333333334 +lon_0=-77.75 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlanePuertoRicoVirginIslandsFIPS5200
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=18.03333333333333 +lat_2=18.43333333333333 +lat_0=17.83333333333333 +lon_0=-66.43333333333334 +x_0=200000 +y_0=200000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneRhodeIslandFIPS3800
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.08333333333334 +lon_0=-71.5 +k=0.999994 +x_0=100000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneSouthCarolinaFIPS3900
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.5 +lat_2=34.83333333333334 +lat_0=31.83333333333333 +lon_0=-81 +x_0=609600 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneSouthDakotaNorthFIPS4001
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.41666666666666 +lat_2=45.68333333333333 +lat_0=43.83333333333334 +lon_0=-100 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneSouthDakotaSouthFIPS4002
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.83333333333334 +lat_2=44.4 +lat_0=42.33333333333334 +lon_0=-100.3333333333333 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneTennesseeFIPS4100
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=35.25 +lat_2=36.41666666666666 +lat_0=34.33333333333334 +lon_0=-86 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneTexasCentralFIPS4203
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=30.11666666666667 +lat_2=31.88333333333333 +lat_0=29.66666666666667 +lon_0=-100.3333333333333 +x_0=700000 +y_0=3000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneTexasNorthCentralFIPS4202
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.13333333333333 +lat_2=33.96666666666667 +lat_0=31.66666666666667 +lon_0=-98.5 +x_0=600000 +y_0=2000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneTexasNorthFIPS4201
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.65 +lat_2=36.18333333333333 +lat_0=34 +lon_0=-101.5 +x_0=200000 +y_0=1000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneTexasSouthCentralFIPS4204
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=28.38333333333333 +lat_2=30.28333333333333 +lat_0=27.83333333333333 +lon_0=-99 +x_0=600000 +y_0=4000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneTexasSouthFIPS4205
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26.16666666666667 +lat_2=27.83333333333333 +lat_0=25.66666666666667 +lon_0=-98.5 +x_0=300000 +y_0=5000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneUtahCentralFIPS4302
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.01666666666667 +lat_2=40.65 +lat_0=38.33333333333334 +lon_0=-111.5 +x_0=500000 +y_0=2000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneUtahNorthFIPS4301
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.71666666666667 +lat_2=41.78333333333333 +lat_0=40.33333333333334 +lon_0=-111.5 +x_0=500000 +y_0=1000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneUtahSouthFIPS4303
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.21666666666667 +lat_2=38.35 +lat_0=36.66666666666666 +lon_0=-111.5 +x_0=500000 +y_0=3000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneVermontFIPS4400
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.5 +lon_0=-72.5 +k=0.999964 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneVirginiaNorthFIPS4501
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.03333333333333 +lat_2=39.2 +lat_0=37.66666666666666 +lon_0=-78.5 +x_0=3500000 +y_0=2000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneVirginiaSouthFIPS4502
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36.76666666666667 +lat_2=37.96666666666667 +lat_0=36.33333333333334 +lon_0=-78.5 +x_0=3500000 +y_0=1000000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWashingtonNorthFIPS4601
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.5 +lat_2=48.73333333333333 +lat_0=47 +lon_0=-120.8333333333333 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWashingtonSouthFIPS4602
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.83333333333334 +lat_2=47.33333333333334 +lat_0=45.33333333333334 +lon_0=-120.5 +x_0=500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWestVirginiaNorthFIPS4701
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39 +lat_2=40.25 +lat_0=38.5 +lon_0=-79.5 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWestVirginiaSouthFIPS4702
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.48333333333333 +lat_2=38.88333333333333 +lat_0=37 +lon_0=-81 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWisconsinCentralFIPS4802
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.25 +lat_2=45.5 +lat_0=43.83333333333334 +lon_0=-90 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWisconsinNorthFIPS4801
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.56666666666667 +lat_2=46.76666666666667 +lat_0=45.16666666666666 +lon_0=-90 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWisconsinSouthFIPS4803
        = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.73333333333333 +lat_2=44.06666666666667 +lat_0=42 +lon_0=-90 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWyomingEastCentralFIPS4902
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-107.3333333333333 +k=0.999938 +x_0=400000 +y_0=100000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWyomingEastFIPS4901
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-105.1666666666667 +k=0.999938 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWyomingWestCentralFIPS4903
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-108.75 +k=0.999938 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983StatePlaneWyomingWestFIPS4904
        = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-110.0833333333333 +k=0.999938 +x_0=800000 +y_0=100000 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);

    NAD1983Maine2000CentralZone.setName("NAD_1983_Maine_2000_Central_Zone");
    NAD1983Maine2000EastZone.setName("NAD_1983_Maine_2000_East_Zone");
    NAD1983Maine2000WestZone.setName("NAD_1983_Maine_2000_West_Zone");
    NAD1983StatePlaneAlabamaEastFIPS0101.setName("NAD_1983_StatePlane_Alabama_East_FIPS_0101");
    NAD1983StatePlaneAlabamaWestFIPS0102.setName("NAD_1983_StatePlane_Alabama_West_FIPS_0102");
    NAD1983StatePlaneAlaska10FIPS5010.setName("NAD_1983_StatePlane_Alaska_10_FIPS_5010");
    NAD1983StatePlaneAlaska1FIPS5001.setName("NAD_1983_StatePlane_Alaska_1_FIPS_5001");
    NAD1983StatePlaneAlaska2FIPS5002.setName("NAD_1983_StatePlane_Alaska_2_FIPS_5002");
    NAD1983StatePlaneAlaska3FIPS5003.setName("NAD_1983_StatePlane_Alaska_3_FIPS_5003");
    NAD1983StatePlaneAlaska4FIPS5004.setName("NAD_1983_StatePlane_Alaska_4_FIPS_5004");
    NAD1983StatePlaneAlaska5FIPS5005.setName("NAD_1983_StatePlane_Alaska_5_FIPS_5005");
    NAD1983StatePlaneAlaska6FIPS5006.setName("NAD_1983_StatePlane_Alaska_6_FIPS_5006");
    NAD1983StatePlaneAlaska7FIPS5007.setName("NAD_1983_StatePlane_Alaska_7_FIPS_5007");
    NAD1983StatePlaneAlaska8FIPS5008.setName("NAD_1983_StatePlane_Alaska_8_FIPS_5008");
    NAD1983StatePlaneAlaska9FIPS5009.setName("NAD_1983_StatePlane_Alaska_9_FIPS_5009");
    NAD1983StatePlaneArizonaCentralFIPS0202.setName("NAD_1983_StatePlane_Arizona_Central_FIPS_0202");
    NAD1983StatePlaneArizonaEastFIPS0201.setName("NAD_1983_StatePlane_Arizona_East_FIPS_0201");
    NAD1983StatePlaneArizonaWestFIPS0203.setName("NAD_1983_StatePlane_Arizona_West_FIPS_0203");
    NAD1983StatePlaneArkansasNorthFIPS0301.setName("NAD_1983_StatePlane_Arkansas_North_FIPS_0301");
    NAD1983StatePlaneArkansasSouthFIPS0302.setName("NAD_1983_StatePlane_Arkansas_South_FIPS_0302");
    NAD1983StatePlaneCaliforniaIFIPS0401.setName("NAD_1983_StatePlane_California_I_FIPS_0401");
    NAD1983StatePlaneCaliforniaIIFIPS0402.setName("NAD_1983_StatePlane_California_II_FIPS_0402");
    NAD1983StatePlaneCaliforniaIIIFIPS0403.setName("NAD_1983_StatePlane_California_III_FIPS_0403");
    NAD1983StatePlaneCaliforniaIVFIPS0404.setName("NAD_1983_StatePlane_California_IV_FIPS_0404");
    NAD1983StatePlaneCaliforniaVFIPS0405.setName("NAD_1983_StatePlane_California_V_FIPS_0405");
    NAD1983StatePlaneCaliforniaVIFIPS0406.setName("NAD_1983_StatePlane_California_VI_FIPS_0406");
    NAD1983StatePlaneColoradoCentralFIPS0502.setName("NAD_1983_StatePlane_Colorado_Central_FIPS_0502");
    NAD1983StatePlaneColoradoNorthFIPS0501.setName("NAD_1983_StatePlane_Colorado_North_FIPS_0501");
    NAD1983StatePlaneColoradoSouthFIPS0503.setName("NAD_1983_StatePlane_Colorado_South_FIPS_0503");
    NAD1983StatePlaneConnecticutFIPS0600.setName("NAD_1983_StatePlane_Connecticut_FIPS_0600");
    NAD1983StatePlaneDelawareFIPS0700.setName("NAD_1983_StatePlane_Delaware_FIPS_0700");
    NAD1983StatePlaneFloridaEastFIPS0901.setName("NAD_1983_StatePlane_Florida_East_FIPS_0901");
    NAD1983StatePlaneFloridaNorthFIPS0903.setName("NAD_1983_StatePlane_Florida_North_FIPS_0903");
    NAD1983StatePlaneFloridaWestFIPS0902.setName("NAD_1983_StatePlane_Florida_West_FIPS_0902");
    NAD1983StatePlaneGeorgiaEastFIPS1001.setName("NAD_1983_StatePlane_Georgia_East_FIPS_1001");
    NAD1983StatePlaneGeorgiaWestFIPS1002.setName("NAD_1983_StatePlane_Georgia_West_FIPS_1002");
    NAD1983StatePlaneGuamFIPS5400.setName("NAD_1983_StatePlane_Guam_FIPS_5400");
    NAD1983StatePlaneHawaii1FIPS5101.setName("NAD_1983_StatePlane_Hawaii_1_FIPS_5101");
    NAD1983StatePlaneHawaii2FIPS5102.setName("NAD_1983_StatePlane_Hawaii_2_FIPS_5102");
    NAD1983StatePlaneHawaii3FIPS5103.setName("NAD_1983_StatePlane_Hawaii_3_FIPS_5103");
    NAD1983StatePlaneHawaii4FIPS5104.setName("NAD_1983_StatePlane_Hawaii_4_FIPS_5104");
    NAD1983StatePlaneHawaii5FIPS5105.setName("NAD_1983_StatePlane_Hawaii_5_FIPS_5105");
    NAD1983StatePlaneIdahoCentralFIPS1102.setName("NAD_1983_StatePlane_Idaho_Central_FIPS_1102");
    NAD1983StatePlaneIdahoEastFIPS1101.setName("NAD_1983_StatePlane_Idaho_East_FIPS_1101");
    NAD1983StatePlaneIdahoWestFIPS1103.setName("NAD_1983_StatePlane_Idaho_West_FIPS_1103");
    NAD1983StatePlaneIllinoisEastFIPS1201.setName("NAD_1983_StatePlane_Illinois_East_FIPS_1201");
    NAD1983StatePlaneIllinoisWestFIPS1202.setName("NAD_1983_StatePlane_Illinois_West_FIPS_1202");
    NAD1983StatePlaneIndianaEastFIPS1301.setName("NAD_1983_StatePlane_Indiana_East_FIPS_1301");
    NAD1983StatePlaneIndianaWestFIPS1302.setName("NAD_1983_StatePlane_Indiana_West_FIPS_1302");
    NAD1983StatePlaneIowaNorthFIPS1401.setName("NAD_1983_StatePlane_Iowa_North_FIPS_1401");
    NAD1983StatePlaneIowaSouthFIPS1402.setName("NAD_1983_StatePlane_Iowa_South_FIPS_1402");
    NAD1983StatePlaneKansasNorthFIPS1501.setName("NAD_1983_StatePlane_Kansas_North_FIPS_1501");
    NAD1983StatePlaneKansasSouthFIPS1502.setName("NAD_1983_StatePlane_Kansas_South_FIPS_1502");
    NAD1983StatePlaneKentuckyFIPS1600.setName("NAD_1983_StatePlane_Kentucky_FIPS_1600");
    NAD1983StatePlaneKentuckyNorthFIPS1601.setName("NAD_1983_StatePlane_Kentucky_North_FIPS_1601");
    NAD1983StatePlaneKentuckySouthFIPS1602.setName("NAD_1983_StatePlane_Kentucky_South_FIPS_1602");
    NAD1983StatePlaneLouisianaNorthFIPS1701.setName("NAD_1983_StatePlane_Louisiana_North_FIPS_1701");
    NAD1983StatePlaneLouisianaSouthFIPS1702.setName("NAD_1983_StatePlane_Louisiana_South_FIPS_1702");
    NAD1983StatePlaneMaineEastFIPS1801.setName("NAD_1983_StatePlane_Maine_East_FIPS_1801");
    NAD1983StatePlaneMaineWestFIPS1802.setName("NAD_1983_StatePlane_Maine_West_FIPS_1802");
    NAD1983StatePlaneMarylandFIPS1900.setName("NAD_1983_StatePlane_Maryland_FIPS_1900");
    NAD1983StatePlaneMassachusettsIslandFIPS2002.setName("NAD_1983_StatePlane_Massachusetts_Island_FIPS_2002");
    NAD1983StatePlaneMassachusettsMainlandFIPS2001.setName("NAD_1983_StatePlane_Massachusetts_Mainland_FIPS_2001");
    NAD1983StatePlaneMichiganCentralFIPS2112.setName("NAD_1983_StatePlane_Michigan_Central_FIPS_2112");
    NAD1983StatePlaneMichiganNorthFIPS2111.setName("NAD_1983_StatePlane_Michigan_North_FIPS_2111");
    NAD1983StatePlaneMichiganSouthFIPS2113.setName("NAD_1983_StatePlane_Michigan_South_FIPS_2113");
    NAD1983StatePlaneMinnesotaCentralFIPS2202.setName("NAD_1983_StatePlane_Minnesota_Central_FIPS_2202");
    NAD1983StatePlaneMinnesotaNorthFIPS2201.setName("NAD_1983_StatePlane_Minnesota_North_FIPS_2201");
    NAD1983StatePlaneMinnesotaSouthFIPS2203.setName("NAD_1983_StatePlane_Minnesota_South_FIPS_2203");
    NAD1983StatePlaneMississippiEastFIPS2301.setName("NAD_1983_StatePlane_Mississippi_East_FIPS_2301");
    NAD1983StatePlaneMississippiWestFIPS2302.setName("NAD_1983_StatePlane_Mississippi_West_FIPS_2302");
    NAD1983StatePlaneMissouriCentralFIPS2402.setName("NAD_1983_StatePlane_Missouri_Central_FIPS_2402");
    NAD1983StatePlaneMissouriEastFIPS2401.setName("NAD_1983_StatePlane_Missouri_East_FIPS_2401");
    NAD1983StatePlaneMissouriWestFIPS2403.setName("NAD_1983_StatePlane_Missouri_West_FIPS_2403");
    NAD1983StatePlaneMontanaFIPS2500.setName("NAD_1983_StatePlane_Montana_FIPS_2500");
    NAD1983StatePlaneNebraskaFIPS2600.setName("NAD_1983_StatePlane_Nebraska_FIPS_2600");
    NAD1983StatePlaneNevadaCentralFIPS2702.setName("NAD_1983_StatePlane_Nevada_Central_FIPS_2702");
    NAD1983StatePlaneNevadaEastFIPS2701.setName("NAD_1983_StatePlane_Nevada_East_FIPS_2701");
    NAD1983StatePlaneNevadaWestFIPS2703.setName("NAD_1983_StatePlane_Nevada_West_FIPS_2703");
    NAD1983StatePlaneNewHampshireFIPS2800.setName("NAD_1983_StatePlane_New_Hampshire_FIPS_2800");
    NAD1983StatePlaneNewJerseyFIPS2900.setName("NAD_1983_StatePlane_New_Jersey_FIPS_2900");
    NAD1983StatePlaneNewMexicoCentralFIPS3002.setName("NAD_1983_StatePlane_New_Mexico_Central_FIPS_3002");
    NAD1983StatePlaneNewMexicoEastFIPS3001.setName("NAD_1983_StatePlane_New_Mexico_East_FIPS_3001");
    NAD1983StatePlaneNewMexicoWestFIPS3003.setName("NAD_1983_StatePlane_New_Mexico_West_FIPS_3003");
    NAD1983StatePlaneNewYorkCentralFIPS3102.setName("NAD_1983_StatePlane_New_York_Central_FIPS_3102");
    NAD1983StatePlaneNewYorkEastFIPS3101.setName("NAD_1983_StatePlane_New_York_East_FIPS_3101");
    NAD1983StatePlaneNewYorkLongIslandFIPS3104.setName("NAD_1983_StatePlane_New_York_Long_Island_FIPS_3104");
    NAD1983StatePlaneNewYorkWestFIPS3103.setName("NAD_1983_StatePlane_New_York_West_FIPS_3103");
    NAD1983StatePlaneNorthCarolinaFIPS3200.setName("NAD_1983_StatePlane_North_Carolina_FIPS_3200");
    NAD1983StatePlaneNorthDakotaNorthFIPS3301.setName("NAD_1983_StatePlane_North_Dakota_North_FIPS_3301");
    NAD1983StatePlaneNorthDakotaSouthFIPS3302.setName("NAD_1983_StatePlane_North_Dakota_South_FIPS_3302");
    NAD1983StatePlaneOhioNorthFIPS3401.setName("NAD_1983_StatePlane_Ohio_North_FIPS_3401");
    NAD1983StatePlaneOhioSouthFIPS3402.setName("NAD_1983_StatePlane_Ohio_South_FIPS_3402");
    NAD1983StatePlaneOklahomaNorthFIPS3501.setName("NAD_1983_StatePlane_Oklahoma_North_FIPS_3501");
    NAD1983StatePlaneOklahomaSouthFIPS3502.setName("NAD_1983_StatePlane_Oklahoma_South_FIPS_3502");
    NAD1983StatePlaneOregonNorthFIPS3601.setName("NAD_1983_StatePlane_Oregon_North_FIPS_3601");
    NAD1983StatePlaneOregonSouthFIPS3602.setName("NAD_1983_StatePlane_Oregon_South_FIPS_3602");
    NAD1983StatePlanePennsylvaniaNorthFIPS3701.setName("NAD_1983_StatePlane_Pennsylvania_North_FIPS_3701");
    NAD1983StatePlanePennsylvaniaSouthFIPS3702.setName("NAD_1983_StatePlane_Pennsylvania_South_FIPS_3702");
    NAD1983StatePlanePuertoRicoVirginIslandsFIPS5200.setName("NAD_1983_StatePlane_Puerto_Rico_Virgin_Islands_FIPS_5200");
    NAD1983StatePlaneRhodeIslandFIPS3800.setName("NAD_1983_StatePlane_Rhode_Island_FIPS_3800");
    NAD1983StatePlaneSouthCarolinaFIPS3900.setName("NAD_1983_StatePlane_South_Carolina_FIPS_3900");
    NAD1983StatePlaneSouthDakotaNorthFIPS4001.setName("NAD_1983_StatePlane_South_Dakota_North_FIPS_4001");
    NAD1983StatePlaneSouthDakotaSouthFIPS4002.setName("NAD_1983_StatePlane_South_Dakota_South_FIPS_4002");
    NAD1983StatePlaneTennesseeFIPS4100.setName("NAD_1983_StatePlane_Tennessee_FIPS_4100");
    NAD1983StatePlaneTexasCentralFIPS4203.setName("NAD_1983_StatePlane_Texas_Central_FIPS_4203");
    NAD1983StatePlaneTexasNorthCentralFIPS4202.setName("NAD_1983_StatePlane_Texas_North_Central_FIPS_4202");
    NAD1983StatePlaneTexasNorthFIPS4201.setName("NAD_1983_StatePlane_Texas_North_FIPS_4201");
    NAD1983StatePlaneTexasSouthCentralFIPS4204.setName("NAD_1983_StatePlane_Texas_South_Central_FIPS_4204");
    NAD1983StatePlaneTexasSouthFIPS4205.setName("NAD_1983_StatePlane_Texas_South_FIPS_4205");
    NAD1983StatePlaneUtahCentralFIPS4302.setName("NAD_1983_StatePlane_Utah_Central_FIPS_4302");
    NAD1983StatePlaneUtahNorthFIPS4301.setName("NAD_1983_StatePlane_Utah_North_FIPS_4301");
    NAD1983StatePlaneUtahSouthFIPS4303.setName("NAD_1983_StatePlane_Utah_South_FIPS_4303");
    NAD1983StatePlaneVermontFIPS4400.setName("NAD_1983_StatePlane_Vermont_FIPS_4400");
    NAD1983StatePlaneVirginiaNorthFIPS4501.setName("NAD_1983_StatePlane_Virginia_North_FIPS_4501");
    NAD1983StatePlaneVirginiaSouthFIPS4502.setName("NAD_1983_StatePlane_Virginia_South_FIPS_4502");
    NAD1983StatePlaneWashingtonNorthFIPS4601.setName("NAD_1983_StatePlane_Washington_North_FIPS_4601");
    NAD1983StatePlaneWashingtonSouthFIPS4602.setName("NAD_1983_StatePlane_Washington_South_FIPS_4602");
    NAD1983StatePlaneWestVirginiaNorthFIPS4701.setName("NAD_1983_StatePlane_West_Virginia_North_FIPS_4701");
    NAD1983StatePlaneWestVirginiaSouthFIPS4702.setName("NAD_1983_StatePlane_West_Virginia_South_FIPS_4702");
    NAD1983StatePlaneWisconsinCentralFIPS4802.setName("NAD_1983_StatePlane_Wisconsin_Central_FIPS_4802");
    NAD1983StatePlaneWisconsinNorthFIPS4801.setName("NAD_1983_StatePlane_Wisconsin_North_FIPS_4801");
    NAD1983StatePlaneWisconsinSouthFIPS4803.setName("NAD_1983_StatePlane_Wisconsin_South_FIPS_4803");
    NAD1983StatePlaneWyomingEastCentralFIPS4902.setName("NAD_1983_StatePlane_Wyoming_East_Central_FIPS_4902");
    NAD1983StatePlaneWyomingEastFIPS4901.setName("NAD_1983_StatePlane_Wyoming_East_FIPS_4901");
    NAD1983StatePlaneWyomingWestCentralFIPS4903.setName("NAD_1983_StatePlane_Wyoming_West_Central_FIPS_4903");
    NAD1983StatePlaneWyomingWestFIPS4904.setName("NAD_1983_StatePlane_Wyoming_West_FIPS_4904");

    NAD1983Maine2000CentralZone.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983Maine2000EastZone.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983Maine2000WestZone.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlabamaEastFIPS0101.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlabamaWestFIPS0102.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska10FIPS5010.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska1FIPS5001.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska2FIPS5002.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska3FIPS5003.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska4FIPS5004.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska5FIPS5005.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska6FIPS5006.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska7FIPS5007.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska8FIPS5008.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska9FIPS5009.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneArizonaCentralFIPS0202.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneArizonaEastFIPS0201.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneArizonaWestFIPS0203.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneArkansasNorthFIPS0301.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneArkansasSouthFIPS0302.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaIFIPS0401.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaIIFIPS0402.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaIIIFIPS0403.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaIVFIPS0404.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaVFIPS0405.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaVIFIPS0406.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneColoradoCentralFIPS0502.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneColoradoNorthFIPS0501.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneColoradoSouthFIPS0503.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneConnecticutFIPS0600.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneDelawareFIPS0700.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneFloridaEastFIPS0901.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneFloridaNorthFIPS0903.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneFloridaWestFIPS0902.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneGeorgiaEastFIPS1001.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneGeorgiaWestFIPS1002.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneGuamFIPS5400.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneHawaii1FIPS5101.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneHawaii2FIPS5102.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneHawaii3FIPS5103.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneHawaii4FIPS5104.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneHawaii5FIPS5105.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIdahoCentralFIPS1102.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIdahoEastFIPS1101.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIdahoWestFIPS1103.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIllinoisEastFIPS1201.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIllinoisWestFIPS1202.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIndianaEastFIPS1301.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIndianaWestFIPS1302.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIowaNorthFIPS1401.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIowaSouthFIPS1402.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneKansasNorthFIPS1501.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneKansasSouthFIPS1502.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneKentuckyFIPS1600.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneKentuckyNorthFIPS1601.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneKentuckySouthFIPS1602.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneLouisianaNorthFIPS1701.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneLouisianaSouthFIPS1702.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMaineEastFIPS1801.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMaineWestFIPS1802.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMarylandFIPS1900.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMassachusettsIslandFIPS2002.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMassachusettsMainlandFIPS2001.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMichiganCentralFIPS2112.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMichiganNorthFIPS2111.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMichiganSouthFIPS2113.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMinnesotaCentralFIPS2202.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMinnesotaNorthFIPS2201.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMinnesotaSouthFIPS2203.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMississippiEastFIPS2301.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMississippiWestFIPS2302.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMissouriCentralFIPS2402.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMissouriEastFIPS2401.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMissouriWestFIPS2403.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMontanaFIPS2500.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNebraskaFIPS2600.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNevadaCentralFIPS2702.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNevadaEastFIPS2701.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNevadaWestFIPS2703.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewHampshireFIPS2800.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewJerseyFIPS2900.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewMexicoCentralFIPS3002.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewMexicoEastFIPS3001.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewMexicoWestFIPS3003.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewYorkCentralFIPS3102.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewYorkEastFIPS3101.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewYorkLongIslandFIPS3104.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewYorkWestFIPS3103.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNorthCarolinaFIPS3200.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNorthDakotaNorthFIPS3301.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNorthDakotaSouthFIPS3302.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOhioNorthFIPS3401.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOhioSouthFIPS3402.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOklahomaNorthFIPS3501.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOklahomaSouthFIPS3502.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOregonNorthFIPS3601.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOregonSouthFIPS3602.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlanePennsylvaniaNorthFIPS3701.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlanePennsylvaniaSouthFIPS3702.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlanePuertoRicoVirginIslandsFIPS5200.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneRhodeIslandFIPS3800.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneSouthCarolinaFIPS3900.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneSouthDakotaNorthFIPS4001.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneSouthDakotaSouthFIPS4002.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTennesseeFIPS4100.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTexasCentralFIPS4203.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTexasNorthCentralFIPS4202.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTexasNorthFIPS4201.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTexasSouthCentralFIPS4204.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTexasSouthFIPS4205.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneUtahCentralFIPS4302.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneUtahNorthFIPS4301.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneUtahSouthFIPS4303.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneVermontFIPS4400.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneVirginiaNorthFIPS4501.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneVirginiaSouthFIPS4502.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWashingtonNorthFIPS4601.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWashingtonSouthFIPS4602.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWestVirginiaNorthFIPS4701.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWestVirginiaSouthFIPS4702.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWisconsinCentralFIPS4802.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWisconsinNorthFIPS4801.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWisconsinSouthFIPS4803.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWyomingEastCentralFIPS4902.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWyomingEastFIPS4901.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWyomingWestCentralFIPS4903.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWyomingWestFIPS4904.getGeographicInfo().setName("GCS_North_American_1983");

    NAD1983Maine2000CentralZone.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983Maine2000EastZone.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983Maine2000WestZone.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlabamaEastFIPS0101.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlabamaWestFIPS0102.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska10FIPS5010.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska1FIPS5001.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska2FIPS5002.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska3FIPS5003.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska4FIPS5004.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska5FIPS5005.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska6FIPS5006.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska7FIPS5007.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska8FIPS5008.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska9FIPS5009.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneArizonaCentralFIPS0202.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneArizonaEastFIPS0201.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneArizonaWestFIPS0203.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneArkansasNorthFIPS0301.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneArkansasSouthFIPS0302.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaIFIPS0401.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaIIFIPS0402.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaIIIFIPS0403.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaIVFIPS0404.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaVFIPS0405.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaVIFIPS0406.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneColoradoCentralFIPS0502.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneColoradoNorthFIPS0501.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneColoradoSouthFIPS0503.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneConnecticutFIPS0600.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneDelawareFIPS0700.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneFloridaEastFIPS0901.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneFloridaNorthFIPS0903.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneFloridaWestFIPS0902.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneGeorgiaEastFIPS1001.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneGeorgiaWestFIPS1002.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneGuamFIPS5400.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneHawaii1FIPS5101.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneHawaii2FIPS5102.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneHawaii3FIPS5103.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneHawaii4FIPS5104.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneHawaii5FIPS5105.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIdahoCentralFIPS1102.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIdahoEastFIPS1101.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIdahoWestFIPS1103.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIllinoisEastFIPS1201.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIllinoisWestFIPS1202.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIndianaEastFIPS1301.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIndianaWestFIPS1302.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIowaNorthFIPS1401.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIowaSouthFIPS1402.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneKansasNorthFIPS1501.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneKansasSouthFIPS1502.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneKentuckyFIPS1600.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneKentuckyNorthFIPS1601.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneKentuckySouthFIPS1602.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneLouisianaNorthFIPS1701.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneLouisianaSouthFIPS1702.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMaineEastFIPS1801.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMaineWestFIPS1802.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMarylandFIPS1900.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMassachusettsIslandFIPS2002.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMassachusettsMainlandFIPS2001.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMichiganCentralFIPS2112.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMichiganNorthFIPS2111.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMichiganSouthFIPS2113.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMinnesotaCentralFIPS2202.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMinnesotaNorthFIPS2201.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMinnesotaSouthFIPS2203.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMississippiEastFIPS2301.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMississippiWestFIPS2302.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMissouriCentralFIPS2402.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMissouriEastFIPS2401.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMissouriWestFIPS2403.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMontanaFIPS2500.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNebraskaFIPS2600.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNevadaCentralFIPS2702.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNevadaEastFIPS2701.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNevadaWestFIPS2703.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewHampshireFIPS2800.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewJerseyFIPS2900.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewMexicoCentralFIPS3002.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewMexicoEastFIPS3001.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewMexicoWestFIPS3003.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewYorkCentralFIPS3102.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewYorkEastFIPS3101.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewYorkLongIslandFIPS3104.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewYorkWestFIPS3103.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNorthCarolinaFIPS3200.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNorthDakotaNorthFIPS3301.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNorthDakotaSouthFIPS3302.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOhioNorthFIPS3401.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOhioSouthFIPS3402.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOklahomaNorthFIPS3501.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOklahomaSouthFIPS3502.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOregonNorthFIPS3601.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOregonSouthFIPS3602.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlanePennsylvaniaNorthFIPS3701.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlanePennsylvaniaSouthFIPS3702.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlanePuertoRicoVirginIslandsFIPS5200.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneRhodeIslandFIPS3800.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneSouthCarolinaFIPS3900.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneSouthDakotaNorthFIPS4001.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneSouthDakotaSouthFIPS4002.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTennesseeFIPS4100.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTexasCentralFIPS4203.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTexasNorthCentralFIPS4202.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTexasNorthFIPS4201.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTexasSouthCentralFIPS4204.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTexasSouthFIPS4205.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneUtahCentralFIPS4302.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneUtahNorthFIPS4301.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneUtahSouthFIPS4303.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneVermontFIPS4400.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneVirginiaNorthFIPS4501.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneVirginiaSouthFIPS4502.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWashingtonNorthFIPS4601.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWashingtonSouthFIPS4602.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWestVirginiaNorthFIPS4701.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWestVirginiaSouthFIPS4702.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWisconsinCentralFIPS4802.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWisconsinNorthFIPS4801.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWisconsinSouthFIPS4803.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWyomingEastCentralFIPS4902.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWyomingEastFIPS4901.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWyomingWestCentralFIPS4903.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWyomingWestFIPS4904.getGeographicInfo().getDatum().setName("D_North_American_1983");
  }

  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Properties">


  /**
   * @return the MichiganGeoRef2008
   */
  public ProjectionInfo getMichiganGeoRef2008() {
    return MichiganGeoRef2008.copy();
  }

  /**
   * @return the NAD1983Maine2000CentralZone
   */
  public ProjectionInfo getNAD1983Maine2000CentralZone() {
    return NAD1983Maine2000CentralZone.copy();
  }

  /**
   * @return the NAD1983Maine2000EastZone
   */
  public ProjectionInfo getNAD1983Maine2000EastZone() {
    return NAD1983Maine2000EastZone.copy();
  }

  /**
   * @return the NAD1983Maine2000WestZone
   */
  public ProjectionInfo getNAD1983Maine2000WestZone() {
    return NAD1983Maine2000WestZone.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlabamaEastFIPS0101
   */
  public ProjectionInfo getNAD1983StatePlaneAlabamaEastFIPS0101() {
    return NAD1983StatePlaneAlabamaEastFIPS0101.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlabamaWestFIPS0102
   */
  public ProjectionInfo getNAD1983StatePlaneAlabamaWestFIPS0102() {
    return NAD1983StatePlaneAlabamaWestFIPS0102.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska10FIPS5010
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska10FIPS5010() {
    return NAD1983StatePlaneAlaska10FIPS5010.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska1FIPS5001
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska1FIPS5001() {
    return NAD1983StatePlaneAlaska1FIPS5001.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska2FIPS5002
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska2FIPS5002() {
    return NAD1983StatePlaneAlaska2FIPS5002.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska3FIPS5003
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska3FIPS5003() {
    return NAD1983StatePlaneAlaska3FIPS5003.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska4FIPS5004
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska4FIPS5004() {
    return NAD1983StatePlaneAlaska4FIPS5004.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska5FIPS5005
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska5FIPS5005() {
    return NAD1983StatePlaneAlaska5FIPS5005.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska6FIPS5006
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska6FIPS5006() {
    return NAD1983StatePlaneAlaska6FIPS5006.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska7FIPS5007
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska7FIPS5007() {
    return NAD1983StatePlaneAlaska7FIPS5007.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska8FIPS5008
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska8FIPS5008() {
    return NAD1983StatePlaneAlaska8FIPS5008.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska9FIPS5009
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska9FIPS5009() {
    return NAD1983StatePlaneAlaska9FIPS5009.copy();
  }

  /**
   * @return the NAD1983StatePlaneArizonaCentralFIPS0202
   */
  public ProjectionInfo getNAD1983StatePlaneArizonaCentralFIPS0202() {
    return NAD1983StatePlaneArizonaCentralFIPS0202.copy();
  }

  /**
   * @return the NAD1983StatePlaneArizonaEastFIPS0201
   */
  public ProjectionInfo getNAD1983StatePlaneArizonaEastFIPS0201() {
    return NAD1983StatePlaneArizonaEastFIPS0201.copy();
  }

  /**
   * @return the NAD1983StatePlaneArizonaWestFIPS0203
   */
  public ProjectionInfo getNAD1983StatePlaneArizonaWestFIPS0203() {
    return NAD1983StatePlaneArizonaWestFIPS0203.copy();
  }

  /**
   * @return the NAD1983StatePlaneArkansasNorthFIPS0301
   */
  public ProjectionInfo getNAD1983StatePlaneArkansasNorthFIPS0301() {
    return NAD1983StatePlaneArkansasNorthFIPS0301.copy();
  }

  /**
   * @return the NAD1983StatePlaneArkansasSouthFIPS0302
   */
  public ProjectionInfo getNAD1983StatePlaneArkansasSouthFIPS0302() {
    return NAD1983StatePlaneArkansasSouthFIPS0302.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaIFIPS0401
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaIFIPS0401() {
    return NAD1983StatePlaneCaliforniaIFIPS0401.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaIIFIPS0402
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaIIFIPS0402() {
    return NAD1983StatePlaneCaliforniaIIFIPS0402.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaIIIFIPS0403
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaIIIFIPS0403() {
    return NAD1983StatePlaneCaliforniaIIIFIPS0403.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaIVFIPS0404
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaIVFIPS0404() {
    return NAD1983StatePlaneCaliforniaIVFIPS0404.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaVFIPS0405
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaVFIPS0405() {
    return NAD1983StatePlaneCaliforniaVFIPS0405.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaVIFIPS0406
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaVIFIPS0406() {
    return NAD1983StatePlaneCaliforniaVIFIPS0406.copy();
  }

  /**
   * @return the NAD1983StatePlaneColoradoCentralFIPS0502
   */
  public ProjectionInfo getNAD1983StatePlaneColoradoCentralFIPS0502() {
    return NAD1983StatePlaneColoradoCentralFIPS0502.copy();
  }

  /**
   * @return the NAD1983StatePlaneColoradoNorthFIPS0501
   */
  public ProjectionInfo getNAD1983StatePlaneColoradoNorthFIPS0501() {
    return NAD1983StatePlaneColoradoNorthFIPS0501.copy();
  }

  /**
   * @return the NAD1983StatePlaneColoradoSouthFIPS0503
   */
  public ProjectionInfo getNAD1983StatePlaneColoradoSouthFIPS0503() {
    return NAD1983StatePlaneColoradoSouthFIPS0503.copy();
  }

  /**
   * @return the NAD1983StatePlaneConnecticutFIPS0600
   */
  public ProjectionInfo getNAD1983StatePlaneConnecticutFIPS0600() {
    return NAD1983StatePlaneConnecticutFIPS0600.copy();
  }

  /**
   * @return the NAD1983StatePlaneDelawareFIPS0700
   */
  public ProjectionInfo getNAD1983StatePlaneDelawareFIPS0700() {
    return NAD1983StatePlaneDelawareFIPS0700.copy();
  }

  /**
   * @return the NAD1983StatePlaneFloridaEastFIPS0901
   */
  public ProjectionInfo getNAD1983StatePlaneFloridaEastFIPS0901() {
    return NAD1983StatePlaneFloridaEastFIPS0901.copy();
  }

  /**
   * @return the NAD1983StatePlaneFloridaNorthFIPS0903
   */
  public ProjectionInfo getNAD1983StatePlaneFloridaNorthFIPS0903() {
    return NAD1983StatePlaneFloridaNorthFIPS0903.copy();
  }

  /**
   * @return the NAD1983StatePlaneFloridaWestFIPS0902
   */
  public ProjectionInfo getNAD1983StatePlaneFloridaWestFIPS0902() {
    return NAD1983StatePlaneFloridaWestFIPS0902.copy();
  }

  /**
   * @return the NAD1983StatePlaneGeorgiaEastFIPS1001
   */
  public ProjectionInfo getNAD1983StatePlaneGeorgiaEastFIPS1001() {
    return NAD1983StatePlaneGeorgiaEastFIPS1001.copy();
  }

  /**
   * @return the NAD1983StatePlaneGeorgiaWestFIPS1002
   */
  public ProjectionInfo getNAD1983StatePlaneGeorgiaWestFIPS1002() {
    return NAD1983StatePlaneGeorgiaWestFIPS1002.copy();
  }

  /**
   * @return the NAD1983StatePlaneGuamFIPS5400
   */
  public ProjectionInfo getNAD1983StatePlaneGuamFIPS5400() {
    return NAD1983StatePlaneGuamFIPS5400.copy();
  }

  /**
   * @return the NAD1983StatePlaneHawaii1FIPS5101
   */
  public ProjectionInfo getNAD1983StatePlaneHawaii1FIPS5101() {
    return NAD1983StatePlaneHawaii1FIPS5101.copy();
  }

  /**
   * @return the NAD1983StatePlaneHawaii2FIPS5102
   */
  public ProjectionInfo getNAD1983StatePlaneHawaii2FIPS5102() {
    return NAD1983StatePlaneHawaii2FIPS5102.copy();
  }

  /**
   * @return the NAD1983StatePlaneHawaii3FIPS5103
   */
  public ProjectionInfo getNAD1983StatePlaneHawaii3FIPS5103() {
    return NAD1983StatePlaneHawaii3FIPS5103.copy();
  }

  /**
   * @return the NAD1983StatePlaneHawaii4FIPS5104
   */
  public ProjectionInfo getNAD1983StatePlaneHawaii4FIPS5104() {
    return NAD1983StatePlaneHawaii4FIPS5104.copy();
  }

  /**
   * @return the NAD1983StatePlaneHawaii5FIPS5105
   */
  public ProjectionInfo getNAD1983StatePlaneHawaii5FIPS5105() {
    return NAD1983StatePlaneHawaii5FIPS5105.copy();
  }

  /**
   * @return the NAD1983StatePlaneIdahoCentralFIPS1102
   */
  public ProjectionInfo getNAD1983StatePlaneIdahoCentralFIPS1102() {
    return NAD1983StatePlaneIdahoCentralFIPS1102.copy();
  }

  /**
   * @return the NAD1983StatePlaneIdahoEastFIPS1101
   */
  public ProjectionInfo getNAD1983StatePlaneIdahoEastFIPS1101() {
    return NAD1983StatePlaneIdahoEastFIPS1101.copy();
  }

  /**
   * @return the NAD1983StatePlaneIdahoWestFIPS1103
   */
  public ProjectionInfo getNAD1983StatePlaneIdahoWestFIPS1103() {
    return NAD1983StatePlaneIdahoWestFIPS1103.copy();
  }

  /**
   * @return the NAD1983StatePlaneIllinoisEastFIPS1201
   */
  public ProjectionInfo getNAD1983StatePlaneIllinoisEastFIPS1201() {
    return NAD1983StatePlaneIllinoisEastFIPS1201.copy();
  }

  /**
   * @return the NAD1983StatePlaneIllinoisWestFIPS1202
   */
  public ProjectionInfo getNAD1983StatePlaneIllinoisWestFIPS1202() {
    return NAD1983StatePlaneIllinoisWestFIPS1202.copy();
  }

  /**
   * @return the NAD1983StatePlaneIndianaEastFIPS1301
   */
  public ProjectionInfo getNAD1983StatePlaneIndianaEastFIPS1301() {
    return NAD1983StatePlaneIndianaEastFIPS1301.copy();
  }

  /**
   * @return the NAD1983StatePlaneIndianaWestFIPS1302
   */
  public ProjectionInfo getNAD1983StatePlaneIndianaWestFIPS1302() {
    return NAD1983StatePlaneIndianaWestFIPS1302.copy();
  }

  /**
   * @return the NAD1983StatePlaneIowaNorthFIPS1401
   */
  public ProjectionInfo getNAD1983StatePlaneIowaNorthFIPS1401() {
    return NAD1983StatePlaneIowaNorthFIPS1401.copy();
  }

  /**
   * @return the NAD1983StatePlaneIowaSouthFIPS1402
   */
  public ProjectionInfo getNAD1983StatePlaneIowaSouthFIPS1402() {
    return NAD1983StatePlaneIowaSouthFIPS1402.copy();
  }

  /**
   * @return the NAD1983StatePlaneKansasNorthFIPS1501
   */
  public ProjectionInfo getNAD1983StatePlaneKansasNorthFIPS1501() {
    return NAD1983StatePlaneKansasNorthFIPS1501.copy();
  }

  /**
   * @return the NAD1983StatePlaneKansasSouthFIPS1502
   */
  public ProjectionInfo getNAD1983StatePlaneKansasSouthFIPS1502() {
    return NAD1983StatePlaneKansasSouthFIPS1502.copy();
  }

  /**
   * @return the NAD1983StatePlaneKentuckyFIPS1600
   */
  public ProjectionInfo getNAD1983StatePlaneKentuckyFIPS1600() {
    return NAD1983StatePlaneKentuckyFIPS1600.copy();
  }

  /**
   * @return the NAD1983StatePlaneKentuckyNorthFIPS1601
   */
  public ProjectionInfo getNAD1983StatePlaneKentuckyNorthFIPS1601() {
    return NAD1983StatePlaneKentuckyNorthFIPS1601.copy();
  }

  /**
   * @return the NAD1983StatePlaneKentuckySouthFIPS1602
   */
  public ProjectionInfo getNAD1983StatePlaneKentuckySouthFIPS1602() {
    return NAD1983StatePlaneKentuckySouthFIPS1602.copy();
  }

  /**
   * @return the NAD1983StatePlaneLouisianaNorthFIPS1701
   */
  public ProjectionInfo getNAD1983StatePlaneLouisianaNorthFIPS1701() {
    return NAD1983StatePlaneLouisianaNorthFIPS1701.copy();
  }

  /**
   * @return the NAD1983StatePlaneLouisianaSouthFIPS1702
   */
  public ProjectionInfo getNAD1983StatePlaneLouisianaSouthFIPS1702() {
    return NAD1983StatePlaneLouisianaSouthFIPS1702.copy();
  }

  /**
   * @return the NAD1983StatePlaneMaineEastFIPS1801
   */
  public ProjectionInfo getNAD1983StatePlaneMaineEastFIPS1801() {
    return NAD1983StatePlaneMaineEastFIPS1801.copy();
  }

  /**
   * @return the NAD1983StatePlaneMaineWestFIPS1802
   */
  public ProjectionInfo getNAD1983StatePlaneMaineWestFIPS1802() {
    return NAD1983StatePlaneMaineWestFIPS1802.copy();
  }

  /**
   * @return the NAD1983StatePlaneMarylandFIPS1900
   */
  public ProjectionInfo getNAD1983StatePlaneMarylandFIPS1900() {
    return NAD1983StatePlaneMarylandFIPS1900.copy();
  }

  /**
   * @return the NAD1983StatePlaneMassachusettsIslandFIPS2002
   */
  public ProjectionInfo getNAD1983StatePlaneMassachusettsIslandFIPS2002() {
    return NAD1983StatePlaneMassachusettsIslandFIPS2002.copy();
  }

  /**
   * @return the NAD1983StatePlaneMassachusettsMainlandFIPS2001
   */
  public ProjectionInfo getNAD1983StatePlaneMassachusettsMainlandFIPS2001() {
    return NAD1983StatePlaneMassachusettsMainlandFIPS2001.copy();
  }

  /**
   * @return the NAD1983StatePlaneMichiganCentralFIPS2112
   */
  public ProjectionInfo getNAD1983StatePlaneMichiganCentralFIPS2112() {
    return NAD1983StatePlaneMichiganCentralFIPS2112.copy();
  }

  /**
   * @return the NAD1983StatePlaneMichiganNorthFIPS2111
   */
  public ProjectionInfo getNAD1983StatePlaneMichiganNorthFIPS2111() {
    return NAD1983StatePlaneMichiganNorthFIPS2111.copy();
  }

  /**
   * @return the NAD1983StatePlaneMichiganSouthFIPS2113
   */
  public ProjectionInfo getNAD1983StatePlaneMichiganSouthFIPS2113() {
    return NAD1983StatePlaneMichiganSouthFIPS2113.copy();
  }

  /**
   * @return the NAD1983StatePlaneMinnesotaCentralFIPS2202
   */
  public ProjectionInfo getNAD1983StatePlaneMinnesotaCentralFIPS2202() {
    return NAD1983StatePlaneMinnesotaCentralFIPS2202.copy();
  }

  /**
   * @return the NAD1983StatePlaneMinnesotaNorthFIPS2201
   */
  public ProjectionInfo getNAD1983StatePlaneMinnesotaNorthFIPS2201() {
    return NAD1983StatePlaneMinnesotaNorthFIPS2201.copy();
  }

  /**
   * @return the NAD1983StatePlaneMinnesotaSouthFIPS2203
   */
  public ProjectionInfo getNAD1983StatePlaneMinnesotaSouthFIPS2203() {
    return NAD1983StatePlaneMinnesotaSouthFIPS2203.copy();
  }

  /**
   * @return the NAD1983StatePlaneMississippiEastFIPS2301
   */
  public ProjectionInfo getNAD1983StatePlaneMississippiEastFIPS2301() {
    return NAD1983StatePlaneMississippiEastFIPS2301.copy();
  }

  /**
   * @return the NAD1983StatePlaneMississippiWestFIPS2302
   */
  public ProjectionInfo getNAD1983StatePlaneMississippiWestFIPS2302() {
    return NAD1983StatePlaneMississippiWestFIPS2302.copy();
  }

  /**
   * @return the NAD1983StatePlaneMissouriCentralFIPS2402
   */
  public ProjectionInfo getNAD1983StatePlaneMissouriCentralFIPS2402() {
    return NAD1983StatePlaneMissouriCentralFIPS2402.copy();
  }

  /**
   * @return the NAD1983StatePlaneMissouriEastFIPS2401
   */
  public ProjectionInfo getNAD1983StatePlaneMissouriEastFIPS2401() {
    return NAD1983StatePlaneMissouriEastFIPS2401.copy();
  }

  /**
   * @return the NAD1983StatePlaneMissouriWestFIPS2403
   */
  public ProjectionInfo getNAD1983StatePlaneMissouriWestFIPS2403() {
    return NAD1983StatePlaneMissouriWestFIPS2403.copy();
  }

  /**
   * @return the NAD1983StatePlaneMontanaFIPS2500
   */
  public ProjectionInfo getNAD1983StatePlaneMontanaFIPS2500() {
    return NAD1983StatePlaneMontanaFIPS2500.copy();
  }

  /**
   * @return the NAD1983StatePlaneNebraskaFIPS2600
   */
  public ProjectionInfo getNAD1983StatePlaneNebraskaFIPS2600() {
    return NAD1983StatePlaneNebraskaFIPS2600.copy();
  }

  /**
   * @return the NAD1983StatePlaneNevadaCentralFIPS2702
   */
  public ProjectionInfo getNAD1983StatePlaneNevadaCentralFIPS2702() {
    return NAD1983StatePlaneNevadaCentralFIPS2702.copy();
  }

  /**
   * @return the NAD1983StatePlaneNevadaEastFIPS2701
   */
  public ProjectionInfo getNAD1983StatePlaneNevadaEastFIPS2701() {
    return NAD1983StatePlaneNevadaEastFIPS2701.copy();
  }

  /**
   * @return the NAD1983StatePlaneNevadaWestFIPS2703
   */
  public ProjectionInfo getNAD1983StatePlaneNevadaWestFIPS2703() {
    return NAD1983StatePlaneNevadaWestFIPS2703.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewHampshireFIPS2800
   */
  public ProjectionInfo getNAD1983StatePlaneNewHampshireFIPS2800() {
    return NAD1983StatePlaneNewHampshireFIPS2800.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewJerseyFIPS2900
   */
  public ProjectionInfo getNAD1983StatePlaneNewJerseyFIPS2900() {
    return NAD1983StatePlaneNewJerseyFIPS2900.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewMexicoCentralFIPS3002
   */
  public ProjectionInfo getNAD1983StatePlaneNewMexicoCentralFIPS3002() {
    return NAD1983StatePlaneNewMexicoCentralFIPS3002.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewMexicoEastFIPS3001
   */
  public ProjectionInfo getNAD1983StatePlaneNewMexicoEastFIPS3001() {
    return NAD1983StatePlaneNewMexicoEastFIPS3001.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewMexicoWestFIPS3003
   */
  public ProjectionInfo getNAD1983StatePlaneNewMexicoWestFIPS3003() {
    return NAD1983StatePlaneNewMexicoWestFIPS3003.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewYorkCentralFIPS3102
   */
  public ProjectionInfo getNAD1983StatePlaneNewYorkCentralFIPS3102() {
    return NAD1983StatePlaneNewYorkCentralFIPS3102.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewYorkEastFIPS3101
   */
  public ProjectionInfo getNAD1983StatePlaneNewYorkEastFIPS3101() {
    return NAD1983StatePlaneNewYorkEastFIPS3101.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewYorkLongIslandFIPS3104
   */
  public ProjectionInfo getNAD1983StatePlaneNewYorkLongIslandFIPS3104() {
    return NAD1983StatePlaneNewYorkLongIslandFIPS3104.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewYorkWestFIPS3103
   */
  public ProjectionInfo getNAD1983StatePlaneNewYorkWestFIPS3103() {
    return NAD1983StatePlaneNewYorkWestFIPS3103.copy();
  }

  /**
   * @return the NAD1983StatePlaneNorthCarolinaFIPS3200
   */
  public ProjectionInfo getNAD1983StatePlaneNorthCarolinaFIPS3200() {
    return NAD1983StatePlaneNorthCarolinaFIPS3200.copy();
  }

  /**
   * @return the NAD1983StatePlaneNorthDakotaNorthFIPS3301
   */
  public ProjectionInfo getNAD1983StatePlaneNorthDakotaNorthFIPS3301() {
    return NAD1983StatePlaneNorthDakotaNorthFIPS3301.copy();
  }

  /**
   * @return the NAD1983StatePlaneNorthDakotaSouthFIPS3302
   */
  public ProjectionInfo getNAD1983StatePlaneNorthDakotaSouthFIPS3302() {
    return NAD1983StatePlaneNorthDakotaSouthFIPS3302.copy();
  }

  /**
   * @return the NAD1983StatePlaneOhioNorthFIPS3401
   */
  public ProjectionInfo getNAD1983StatePlaneOhioNorthFIPS3401() {
    return NAD1983StatePlaneOhioNorthFIPS3401.copy();
  }

  /**
   * @return the NAD1983StatePlaneOhioSouthFIPS3402
   */
  public ProjectionInfo getNAD1983StatePlaneOhioSouthFIPS3402() {
    return NAD1983StatePlaneOhioSouthFIPS3402.copy();
  }

  /**
   * @return the NAD1983StatePlaneOklahomaNorthFIPS3501
   */
  public ProjectionInfo getNAD1983StatePlaneOklahomaNorthFIPS3501() {
    return NAD1983StatePlaneOklahomaNorthFIPS3501.copy();
  }

  /**
   * @return the NAD1983StatePlaneOklahomaSouthFIPS3502
   */
  public ProjectionInfo getNAD1983StatePlaneOklahomaSouthFIPS3502() {
    return NAD1983StatePlaneOklahomaSouthFIPS3502.copy();
  }

  /**
   * @return the NAD1983StatePlaneOregonNorthFIPS3601
   */
  public ProjectionInfo getNAD1983StatePlaneOregonNorthFIPS3601() {
    return NAD1983StatePlaneOregonNorthFIPS3601.copy();
  }

  /**
   * @return the NAD1983StatePlaneOregonSouthFIPS3602
   */
  public ProjectionInfo getNAD1983StatePlaneOregonSouthFIPS3602() {
    return NAD1983StatePlaneOregonSouthFIPS3602.copy();
  }

  /**
   * @return the NAD1983StatePlanePennsylvaniaNorthFIPS3701
   */
  public ProjectionInfo getNAD1983StatePlanePennsylvaniaNorthFIPS3701() {
    return NAD1983StatePlanePennsylvaniaNorthFIPS3701.copy();
  }

  /**
   * @return the NAD1983StatePlanePennsylvaniaSouthFIPS3702
   */
  public ProjectionInfo getNAD1983StatePlanePennsylvaniaSouthFIPS3702() {
    return NAD1983StatePlanePennsylvaniaSouthFIPS3702.copy();
  }

  /**
   * @return the NAD1983StatePlanePuertoRicoVirginIslandsFIPS5200
   */
  public ProjectionInfo getNAD1983StatePlanePuertoRicoVirginIslandsFIPS5200() {
    return NAD1983StatePlanePuertoRicoVirginIslandsFIPS5200.copy();
  }

  /**
   * @return the NAD1983StatePlaneRhodeIslandFIPS3800
   */
  public ProjectionInfo getNAD1983StatePlaneRhodeIslandFIPS3800() {
    return NAD1983StatePlaneRhodeIslandFIPS3800.copy();
  }

  /**
   * @return the NAD1983StatePlaneSouthCarolinaFIPS3900
   */
  public ProjectionInfo getNAD1983StatePlaneSouthCarolinaFIPS3900() {
    return NAD1983StatePlaneSouthCarolinaFIPS3900.copy();
  }

  /**
   * @return the NAD1983StatePlaneSouthDakotaNorthFIPS4001
   */
  public ProjectionInfo getNAD1983StatePlaneSouthDakotaNorthFIPS4001() {
    return NAD1983StatePlaneSouthDakotaNorthFIPS4001.copy();
  }

  /**
   * @return the NAD1983StatePlaneSouthDakotaSouthFIPS4002
   */
  public ProjectionInfo getNAD1983StatePlaneSouthDakotaSouthFIPS4002() {
    return NAD1983StatePlaneSouthDakotaSouthFIPS4002.copy();
  }

  /**
   * @return the NAD1983StatePlaneTennesseeFIPS4100
   */
  public ProjectionInfo getNAD1983StatePlaneTennesseeFIPS4100() {
    return NAD1983StatePlaneTennesseeFIPS4100.copy();
  }

  /**
   * @return the NAD1983StatePlaneTexasCentralFIPS4203
   */
  public ProjectionInfo getNAD1983StatePlaneTexasCentralFIPS4203() {
    return NAD1983StatePlaneTexasCentralFIPS4203.copy();
  }

  /**
   * @return the NAD1983StatePlaneTexasNorthCentralFIPS4202
   */
  public ProjectionInfo getNAD1983StatePlaneTexasNorthCentralFIPS4202() {
    return NAD1983StatePlaneTexasNorthCentralFIPS4202.copy();
  }

  /**
   * @return the NAD1983StatePlaneTexasNorthFIPS4201
   */
  public ProjectionInfo getNAD1983StatePlaneTexasNorthFIPS4201() {
    return NAD1983StatePlaneTexasNorthFIPS4201.copy();
  }

  /**
   * @return the NAD1983StatePlaneTexasSouthCentralFIPS4204
   */
  public ProjectionInfo getNAD1983StatePlaneTexasSouthCentralFIPS4204() {
    return NAD1983StatePlaneTexasSouthCentralFIPS4204.copy();
  }

  /**
   * @return the NAD1983StatePlaneTexasSouthFIPS4205
   */
  public ProjectionInfo getNAD1983StatePlaneTexasSouthFIPS4205() {
    return NAD1983StatePlaneTexasSouthFIPS4205.copy();
  }

  /**
   * @return the NAD1983StatePlaneUtahCentralFIPS4302
   */
  public ProjectionInfo getNAD1983StatePlaneUtahCentralFIPS4302() {
    return NAD1983StatePlaneUtahCentralFIPS4302.copy();
  }

  /**
   * @return the NAD1983StatePlaneUtahNorthFIPS4301
   */
  public ProjectionInfo getNAD1983StatePlaneUtahNorthFIPS4301() {
    return NAD1983StatePlaneUtahNorthFIPS4301.copy();
  }

  /**
   * @return the NAD1983StatePlaneUtahSouthFIPS4303
   */
  public ProjectionInfo getNAD1983StatePlaneUtahSouthFIPS4303() {
    return NAD1983StatePlaneUtahSouthFIPS4303.copy();
  }

  /**
   * @return the NAD1983StatePlaneVermontFIPS4400
   */
  public ProjectionInfo getNAD1983StatePlaneVermontFIPS4400() {
    return NAD1983StatePlaneVermontFIPS4400.copy();
  }

  /**
   * @return the NAD1983StatePlaneVirginiaNorthFIPS4501
   */
  public ProjectionInfo getNAD1983StatePlaneVirginiaNorthFIPS4501() {
    return NAD1983StatePlaneVirginiaNorthFIPS4501.copy();
  }

  /**
   * @return the NAD1983StatePlaneVirginiaSouthFIPS4502
   */
  public ProjectionInfo getNAD1983StatePlaneVirginiaSouthFIPS4502() {
    return NAD1983StatePlaneVirginiaSouthFIPS4502.copy();
  }

  /**
   * @return the NAD1983StatePlaneWashingtonNorthFIPS4601
   */
  public ProjectionInfo getNAD1983StatePlaneWashingtonNorthFIPS4601() {
    return NAD1983StatePlaneWashingtonNorthFIPS4601.copy();
  }

  /**
   * @return the NAD1983StatePlaneWashingtonSouthFIPS4602
   */
  public ProjectionInfo getNAD1983StatePlaneWashingtonSouthFIPS4602() {
    return NAD1983StatePlaneWashingtonSouthFIPS4602.copy();
  }

  /**
   * @return the NAD1983StatePlaneWestVirginiaNorthFIPS4701
   */
  public ProjectionInfo getNAD1983StatePlaneWestVirginiaNorthFIPS4701() {
    return NAD1983StatePlaneWestVirginiaNorthFIPS4701.copy();
  }

  /**
   * @return the NAD1983StatePlaneWestVirginiaSouthFIPS4702
   */
  public ProjectionInfo getNAD1983StatePlaneWestVirginiaSouthFIPS4702() {
    return NAD1983StatePlaneWestVirginiaSouthFIPS4702.copy();
  }

  /**
   * @return the NAD1983StatePlaneWisconsinCentralFIPS4802
   */
  public ProjectionInfo getNAD1983StatePlaneWisconsinCentralFIPS4802() {
    return NAD1983StatePlaneWisconsinCentralFIPS4802.copy();
  }

  /**
   * @return the NAD1983StatePlaneWisconsinNorthFIPS4801
   */
  public ProjectionInfo getNAD1983StatePlaneWisconsinNorthFIPS4801() {
    return NAD1983StatePlaneWisconsinNorthFIPS4801.copy();
  }

  /**
   * @return the NAD1983StatePlaneWisconsinSouthFIPS4803
   */
  public ProjectionInfo getNAD1983StatePlaneWisconsinSouthFIPS4803() {
    return NAD1983StatePlaneWisconsinSouthFIPS4803.copy();
  }

  /**
   * @return the NAD1983StatePlaneWyomingEastCentralFIPS4902
   */
  public ProjectionInfo getNAD1983StatePlaneWyomingEastCentralFIPS4902() {
    return NAD1983StatePlaneWyomingEastCentralFIPS4902.copy();
  }

  /**
   * @return the NAD1983StatePlaneWyomingEastFIPS4901
   */
  public ProjectionInfo getNAD1983StatePlaneWyomingEastFIPS4901() {
    return NAD1983StatePlaneWyomingEastFIPS4901.copy();
  }

  /**
   * @return the NAD1983StatePlaneWyomingWestCentralFIPS4903
   */
  public ProjectionInfo getNAD1983StatePlaneWyomingWestCentralFIPS4903() {
    return NAD1983StatePlaneWyomingWestCentralFIPS4903.copy();
  }

  /**
   * @return the NAD1983StatePlaneWyomingWestFIPS4904
   */
  public ProjectionInfo getNAD1983StatePlaneWyomingWestFIPS4904() {
    return NAD1983StatePlaneWyomingWestFIPS4904.copy();
  }

  // </editor-fold>
}
