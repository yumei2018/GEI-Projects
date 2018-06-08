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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:59:34 PM
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
/// StatePlaneNad1983Feet
/// </summary>

public class StatePlaneNad1983Feet extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo NAD1983StatePlaneAlabamaEastFIPS0101Feet;
  private final ProjectionInfo NAD1983StatePlaneAlabamaWestFIPS0102Feet;
  private final ProjectionInfo NAD1983StatePlaneAlaska10FIPS5010Feet;
  private final ProjectionInfo NAD1983StatePlaneAlaska1FIPS5001Feet;
  private final ProjectionInfo NAD1983StatePlaneAlaska2FIPS5002Feet;
  private final ProjectionInfo NAD1983StatePlaneAlaska3FIPS5003Feet;
  private final ProjectionInfo NAD1983StatePlaneAlaska4FIPS5004Feet;
  private final ProjectionInfo NAD1983StatePlaneAlaska5FIPS5005Feet;
  private final ProjectionInfo NAD1983StatePlaneAlaska6FIPS5006Feet;
  private final ProjectionInfo NAD1983StatePlaneAlaska7FIPS5007Feet;
  private final ProjectionInfo NAD1983StatePlaneAlaska8FIPS5008Feet;
  private final ProjectionInfo NAD1983StatePlaneAlaska9FIPS5009Feet;
  private final ProjectionInfo NAD1983StatePlaneArizonaCentralFIPS0202Feet;
  private final ProjectionInfo NAD1983StatePlaneArizonaEastFIPS0201Feet;
  private final ProjectionInfo NAD1983StatePlaneArizonaWestFIPS0203Feet;
  private final ProjectionInfo NAD1983StatePlaneArkansasNorthFIPS0301Feet;
  private final ProjectionInfo NAD1983StatePlaneArkansasSouthFIPS0302Feet;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaIFIPS0401Feet;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaIIFIPS0402Feet;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaIIIFIPS0403Feet;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaIVFIPS0404Feet;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaVFIPS0405Feet;
  private final ProjectionInfo NAD1983StatePlaneCaliforniaVIFIPS0406Feet;
  private final ProjectionInfo NAD1983StatePlaneColoradoCentralFIPS0502Feet;
  private final ProjectionInfo NAD1983StatePlaneColoradoNorthFIPS0501Feet;
  private final ProjectionInfo NAD1983StatePlaneColoradoSouthFIPS0503Feet;
  private final ProjectionInfo NAD1983StatePlaneConnecticutFIPS0600Feet;
  private final ProjectionInfo NAD1983StatePlaneDelawareFIPS0700Feet;
  private final ProjectionInfo NAD1983StatePlaneFloridaEastFIPS0901Feet;
  private final ProjectionInfo NAD1983StatePlaneFloridaNorthFIPS0903Feet;
  private final ProjectionInfo NAD1983StatePlaneFloridaWestFIPS0902Feet;
  private final ProjectionInfo NAD1983StatePlaneGeorgiaEastFIPS1001Feet;
  private final ProjectionInfo NAD1983StatePlaneGeorgiaWestFIPS1002Feet;
  private final ProjectionInfo NAD1983StatePlaneGuamFIPS5400Feet;
  private final ProjectionInfo NAD1983StatePlaneHawaii1FIPS5101Feet;
  private final ProjectionInfo NAD1983StatePlaneHawaii2FIPS5102Feet;
  private final ProjectionInfo NAD1983StatePlaneHawaii3FIPS5103Feet;
  private final ProjectionInfo NAD1983StatePlaneHawaii4FIPS5104Feet;
  private final ProjectionInfo NAD1983StatePlaneHawaii5FIPS5105Feet;
  private final ProjectionInfo NAD1983StatePlaneIdahoCentralFIPS1102Feet;
  private final ProjectionInfo NAD1983StatePlaneIdahoEastFIPS1101Feet;
  private final ProjectionInfo NAD1983StatePlaneIdahoWestFIPS1103Feet;
  private final ProjectionInfo NAD1983StatePlaneIllinoisEastFIPS1201Feet;
  private final ProjectionInfo NAD1983StatePlaneIllinoisWestFIPS1202Feet;
  private final ProjectionInfo NAD1983StatePlaneIndianaEastFIPS1301Feet;
  private final ProjectionInfo NAD1983StatePlaneIndianaWestFIPS1302Feet;
  private final ProjectionInfo NAD1983StatePlaneIowaNorthFIPS1401Feet;
  private final ProjectionInfo NAD1983StatePlaneIowaSouthFIPS1402Feet;
  private final ProjectionInfo NAD1983StatePlaneKansasNorthFIPS1501Feet;
  private final ProjectionInfo NAD1983StatePlaneKansasSouthFIPS1502Feet;
  private final ProjectionInfo NAD1983StatePlaneKentuckyFIPS1600Feet;
  private final ProjectionInfo NAD1983StatePlaneKentuckyNorthFIPS1601Feet;
  private final ProjectionInfo NAD1983StatePlaneKentuckySouthFIPS1602Feet;
  private final ProjectionInfo NAD1983StatePlaneLouisianaNorthFIPS1701Feet;
  private final ProjectionInfo NAD1983StatePlaneLouisianaSouthFIPS1702Feet;
  private final ProjectionInfo NAD1983StatePlaneMaineEastFIPS1801Feet;
  private final ProjectionInfo NAD1983StatePlaneMaineWestFIPS1802Feet;
  private final ProjectionInfo NAD1983StatePlaneMarylandFIPS1900Feet;
  private final ProjectionInfo NAD1983StatePlaneMassachusettsIslandFIPS2002Feet;
  private final ProjectionInfo NAD1983StatePlaneMassachusettsMainlandFIPS2001Feet;
  private final ProjectionInfo NAD1983StatePlaneMichiganCentralFIPS2112Feet;
  private final ProjectionInfo NAD1983StatePlaneMichiganNorthFIPS2111Feet;
  private final ProjectionInfo NAD1983StatePlaneMichiganSouthFIPS2113Feet;
  private final ProjectionInfo NAD1983StatePlaneMinnesotaCentralFIPS2202Feet;
  private final ProjectionInfo NAD1983StatePlaneMinnesotaNorthFIPS2201Feet;
  private final ProjectionInfo NAD1983StatePlaneMinnesotaSouthFIPS2203Feet;
  private final ProjectionInfo NAD1983StatePlaneMississippiEastFIPS2301Feet;
  private final ProjectionInfo NAD1983StatePlaneMississippiWestFIPS2302Feet;
  private final ProjectionInfo NAD1983StatePlaneMissouriCentralFIPS2402Feet;
  private final ProjectionInfo NAD1983StatePlaneMissouriEastFIPS2401Feet;
  private final ProjectionInfo NAD1983StatePlaneMissouriWestFIPS2403Feet;
  private final ProjectionInfo NAD1983StatePlaneMontanaFIPS2500Feet;
  private final ProjectionInfo NAD1983StatePlaneNebraskaFIPS2600Feet;
  private final ProjectionInfo NAD1983StatePlaneNevadaCentralFIPS2702Feet;
  private final ProjectionInfo NAD1983StatePlaneNevadaEastFIPS2701Feet;
  private final ProjectionInfo NAD1983StatePlaneNevadaWestFIPS2703Feet;
  private final ProjectionInfo NAD1983StatePlaneNewHampshireFIPS2800Feet;
  private final ProjectionInfo NAD1983StatePlaneNewJerseyFIPS2900Feet;
  private final ProjectionInfo NAD1983StatePlaneNewMexicoCentralFIPS3002Feet;
  private final ProjectionInfo NAD1983StatePlaneNewMexicoEastFIPS3001Feet;
  private final ProjectionInfo NAD1983StatePlaneNewMexicoWestFIPS3003Feet;
  private final ProjectionInfo NAD1983StatePlaneNewYorkCentralFIPS3102Feet;
  private final ProjectionInfo NAD1983StatePlaneNewYorkEastFIPS3101Feet;
  private final ProjectionInfo NAD1983StatePlaneNewYorkLongIslandFIPS3104Feet;
  private final ProjectionInfo NAD1983StatePlaneNewYorkWestFIPS3103Feet;
  private final ProjectionInfo NAD1983StatePlaneNorthCarolinaFIPS3200Feet;
  private final ProjectionInfo NAD1983StatePlaneNorthDakotaNorthFIPS3301Feet;
  private final ProjectionInfo NAD1983StatePlaneNorthDakotaSouthFIPS3302Feet;
  private final ProjectionInfo NAD1983StatePlaneOhioNorthFIPS3401Feet;
  private final ProjectionInfo NAD1983StatePlaneOhioSouthFIPS3402Feet;
  private final ProjectionInfo NAD1983StatePlaneOklahomaNorthFIPS3501Feet;
  private final ProjectionInfo NAD1983StatePlaneOklahomaSouthFIPS3502Feet;
  private final ProjectionInfo NAD1983StatePlaneOregonNorthFIPS3601Feet;
  private final ProjectionInfo NAD1983StatePlaneOregonSouthFIPS3602Feet;
  private final ProjectionInfo NAD1983StatePlanePRVirginIslandsFIPS5200Feet;
  private final ProjectionInfo NAD1983StatePlanePennsylvaniaNorthFIPS3701Feet;
  private final ProjectionInfo NAD1983StatePlanePennsylvaniaSouthFIPS3702Feet;
  private final ProjectionInfo NAD1983StatePlaneRhodeIslandFIPS3800Feet;
  private final ProjectionInfo NAD1983StatePlaneSouthCarolinaFIPS3900Feet;
  private final ProjectionInfo NAD1983StatePlaneSouthDakotaNorthFIPS4001Feet;
  private final ProjectionInfo NAD1983StatePlaneSouthDakotaSouthFIPS4002Feet;
  private final ProjectionInfo NAD1983StatePlaneTennesseeFIPS4100Feet;
  private final ProjectionInfo NAD1983StatePlaneTexasCentralFIPS4203Feet;
  private final ProjectionInfo NAD1983StatePlaneTexasNorthCentralFIPS4202Feet;
  private final ProjectionInfo NAD1983StatePlaneTexasNorthFIPS4201Feet;
  private final ProjectionInfo NAD1983StatePlaneTexasSouthCentralFIPS4204Feet;
  private final ProjectionInfo NAD1983StatePlaneTexasSouthFIPS4205Feet;
  private final ProjectionInfo NAD1983StatePlaneUtahCentralFIPS4302Feet;
  private final ProjectionInfo NAD1983StatePlaneUtahNorthFIPS4301Feet;
  private final ProjectionInfo NAD1983StatePlaneUtahSouthFIPS4303Feet;
  private final ProjectionInfo NAD1983StatePlaneVermontFIPS4400Feet;
  private final ProjectionInfo NAD1983StatePlaneVirginiaNorthFIPS4501Feet;
  private final ProjectionInfo NAD1983StatePlaneVirginiaSouthFIPS4502Feet;
  private final ProjectionInfo NAD1983StatePlaneWashingtonNorthFIPS4601Feet;
  private final ProjectionInfo NAD1983StatePlaneWashingtonSouthFIPS4602Feet;
  private final ProjectionInfo NAD1983StatePlaneWestVirginiaNorthFIPS4701Feet;
  private final ProjectionInfo NAD1983StatePlaneWestVirginiaSouthFIPS4702Feet;
  private final ProjectionInfo NAD1983StatePlaneWisconsinCentralFIPS4802Feet;
  private final ProjectionInfo NAD1983StatePlaneWisconsinNorthFIPS4801Feet;
  private final ProjectionInfo NAD1983StatePlaneWisconsinSouthFIPS4803Feet;
  private final ProjectionInfo NAD1983StatePlaneWyomingEastCentralFIPS4902Feet;
  private final ProjectionInfo NAD1983StatePlaneWyomingEastFIPS4901Feet;
  private final ProjectionInfo NAD1983StatePlaneWyomingWestCentralFIPS4903Feet;
  private final ProjectionInfo NAD1983StatePlaneWyomingWestFIPS4904Feet;

        //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of StatePlaneNad1983Feet
   */
  public StatePlaneNad1983Feet() {
    NAD1983StatePlaneAlabamaEastFIPS0101Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30.5 +lon_0=-85.83333333333333 +k=0.999960 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlabamaWestFIPS0102Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-87.5 +k=0.999933 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska10FIPS5010Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=51.83333333333334 +lat_2=53.83333333333334 +lat_0=51 +lon_0=-176 +x_0=1000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska1FIPS5001Feet = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=57 +lonc=-133.6666666666667 +alpha=-36.86989764583333 +k=0.9999 +x_0=4999999.999999999 +y_0=-4999999.999999999 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska2FIPS5002Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-142 +k=0.999900 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska3FIPS5003Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-146 +k=0.999900 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska4FIPS5004Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-150 +k=0.999900 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska5FIPS5005Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-154 +k=0.999900 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska6FIPS5006Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-158 +k=0.999900 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska7FIPS5007Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-162 +k=0.999900 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska8FIPS5008Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-166 +k=0.999900 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneAlaska9FIPS5009Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=54 +lon_0=-170 +k=0.999900 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneArizonaCentralFIPS0202Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-111.9166666666667 +k=0.999900 +x_0=213360 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneArizonaEastFIPS0201Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-110.1666666666667 +k=0.999900 +x_0=213360 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneArizonaWestFIPS0203Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-113.75 +k=0.999933 +x_0=213360 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneArkansasNorthFIPS0301Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.93333333333333 +lat_2=36.23333333333333 +lat_0=34.33333333333334 +lon_0=-92 +x_0=399999.9999999999 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneArkansasSouthFIPS0302Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.3 +lat_2=34.76666666666667 +lat_0=32.66666666666666 +lon_0=-92 +x_0=399999.9999999999 +y_0=399999.9999999999 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaIFIPS0401Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40 +lat_2=41.66666666666666 +lat_0=39.33333333333334 +lon_0=-122 +x_0=2000000 +y_0=500000.0000000002 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaIIFIPS0402Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.33333333333334 +lat_2=39.83333333333334 +lat_0=37.66666666666666 +lon_0=-122 +x_0=2000000 +y_0=500000.0000000002 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaIIIFIPS0403Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.06666666666667 +lat_2=38.43333333333333 +lat_0=36.5 +lon_0=-120.5 +x_0=2000000 +y_0=500000.0000000002 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaIVFIPS0404Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36 +lat_2=37.25 +lat_0=35.33333333333334 +lon_0=-119 +x_0=2000000 +y_0=500000.0000000002 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaVFIPS0405Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.03333333333333 +lat_2=35.46666666666667 +lat_0=33.5 +lon_0=-118 +x_0=2000000 +y_0=500000.0000000002 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneCaliforniaVIFIPS0406Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.78333333333333 +lat_2=33.88333333333333 +lat_0=32.16666666666666 +lon_0=-116.25 +x_0=2000000 +y_0=500000.0000000002 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneColoradoCentralFIPS0502Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.45 +lat_2=39.75 +lat_0=37.83333333333334 +lon_0=-105.5 +x_0=914401.8289 +y_0=304800.6096 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneColoradoNorthFIPS0501Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.71666666666667 +lat_2=40.78333333333333 +lat_0=39.33333333333334 +lon_0=-105.5 +x_0=914401.8289 +y_0=304800.6096 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneColoradoSouthFIPS0503Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.23333333333333 +lat_2=38.43333333333333 +lat_0=36.66666666666666 +lon_0=-105.5 +x_0=914401.8289 +y_0=304800.6096 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneConnecticutFIPS0600Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.2 +lat_2=41.86666666666667 +lat_0=40.83333333333334 +lon_0=-72.75 +x_0=304800.6096 +y_0=152400.3048 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneDelawareFIPS0700Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38 +lon_0=-75.41666666666667 +k=0.999995 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneFloridaEastFIPS0901Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=24.33333333333333 +lon_0=-81 +k=0.999941 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneFloridaNorthFIPS0903Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=29.58333333333333 +lat_2=30.75 +lat_0=29 +lon_0=-84.5 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneFloridaWestFIPS0902Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=24.33333333333333 +lon_0=-82 +k=0.999941 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneGeorgiaEastFIPS1001Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-82.16666666666667 +k=0.999900 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneGeorgiaWestFIPS1002Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-84.16666666666667 +k=0.999900 +x_0=700000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneGuamFIPS5400Feet = ProjectionInfo.fromProj4String("+proj=poly +lat_0=13.47246635277778 +lon_0=144.7487507055556 +x_0=49999.99999999999 +y_0=49999.99999999999 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneHawaii1FIPS5101Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=18.83333333333333 +lon_0=-155.5 +k=0.999967 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneHawaii2FIPS5102Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=20.33333333333333 +lon_0=-156.6666666666667 +k=0.999967 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneHawaii3FIPS5103Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.16666666666667 +lon_0=-158 +k=0.999990 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneHawaii4FIPS5104Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.83333333333333 +lon_0=-159.5 +k=0.999990 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneHawaii5FIPS5105Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.66666666666667 +lon_0=-160.1666666666667 +k=1.000000 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneIdahoCentralFIPS1102Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-114 +k=0.999947 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneIdahoEastFIPS1101Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-112.1666666666667 +k=0.999947 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneIdahoWestFIPS1103Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-115.75 +k=0.999933 +x_0=799999.9999999999 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneIllinoisEastFIPS1201Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36.66666666666666 +lon_0=-88.33333333333333 +k=0.999975 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneIllinoisWestFIPS1202Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36.66666666666666 +lon_0=-90.16666666666667 +k=0.999941 +x_0=700000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneIndianaEastFIPS1301Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=37.5 +lon_0=-85.66666666666667 +k=0.999967 +x_0=100000 +y_0=250000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneIndianaWestFIPS1302Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=37.5 +lon_0=-87.08333333333333 +k=0.999967 +x_0=900000.0000000001 +y_0=250000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneIowaNorthFIPS1401Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.06666666666667 +lat_2=43.26666666666667 +lat_0=41.5 +lon_0=-93.5 +x_0=1500000 +y_0=1000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneIowaSouthFIPS1402Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.61666666666667 +lat_2=41.78333333333333 +lat_0=40 +lon_0=-93.5 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneKansasNorthFIPS1501Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.71666666666667 +lat_2=39.78333333333333 +lat_0=38.33333333333334 +lon_0=-98 +x_0=399999.9999999999 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneKansasSouthFIPS1502Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.26666666666667 +lat_2=38.56666666666667 +lat_0=36.66666666666666 +lon_0=-98.5 +x_0=399999.9999999999 +y_0=399999.9999999999 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneKentuckyFIPS1600Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.08333333333334 +lat_2=38.66666666666666 +lat_0=36.33333333333334 +lon_0=-85.75 +x_0=1500000 +y_0=999999.9999999999 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneKentuckyNorthFIPS1601Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.96666666666667 +lat_2=38.96666666666667 +lat_0=37.5 +lon_0=-84.25 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneKentuckySouthFIPS1602Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36.73333333333333 +lat_2=37.93333333333333 +lat_0=36.33333333333334 +lon_0=-85.75 +x_0=500000.0000000002 +y_0=500000.0000000002 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneLouisianaNorthFIPS1701Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=31.16666666666667 +lat_2=32.66666666666666 +lat_0=30.5 +lon_0=-92.5 +x_0=1000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneLouisianaSouthFIPS1702Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=29.3 +lat_2=30.7 +lat_0=28.5 +lon_0=-91.33333333333333 +x_0=1000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMaineEastFIPS1801Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.66666666666666 +lon_0=-68.5 +k=0.999900 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMaineWestFIPS1802Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.83333333333334 +lon_0=-70.16666666666667 +k=0.999967 +x_0=900000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMarylandFIPS1900Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.3 +lat_2=39.45 +lat_0=37.66666666666666 +lon_0=-77 +x_0=399999.9999999999 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMassachusettsIslandFIPS2002Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.28333333333333 +lat_2=41.48333333333333 +lat_0=41 +lon_0=-70.5 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMassachusettsMainlandFIPS2001Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.71666666666667 +lat_2=42.68333333333333 +lat_0=41 +lon_0=-71.5 +x_0=200000 +y_0=750000.0000000001 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMichiganCentralFIPS2112Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.18333333333333 +lat_2=45.7 +lat_0=43.31666666666667 +lon_0=-84.36666666666666 +x_0=6000000.000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMichiganNorthFIPS2111Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.48333333333333 +lat_2=47.08333333333334 +lat_0=44.78333333333333 +lon_0=-87 +x_0=7999999.999999999 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMichiganSouthFIPS2113Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.1 +lat_2=43.66666666666666 +lat_0=41.5 +lon_0=-84.36666666666666 +x_0=4000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMinnesotaCentralFIPS2202Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.61666666666667 +lat_2=47.05 +lat_0=45 +lon_0=-94.25 +x_0=799999.9999999999 +y_0=100000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMinnesotaNorthFIPS2201Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.03333333333333 +lat_2=48.63333333333333 +lat_0=46.5 +lon_0=-93.09999999999999 +x_0=799999.9999999999 +y_0=100000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMinnesotaSouthFIPS2203Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.78333333333333 +lat_2=45.21666666666667 +lat_0=43 +lon_0=-94 +x_0=799999.9999999999 +y_0=100000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMississippiEastFIPS2301Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=29.5 +lon_0=-88.83333333333333 +k=0.999950 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMississippiWestFIPS2302Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=29.5 +lon_0=-90.33333333333333 +k=0.999950 +x_0=700000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMissouriCentralFIPS2402Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=35.83333333333334 +lon_0=-92.5 +k=0.999933 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMissouriEastFIPS2401Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=35.83333333333334 +lon_0=-90.5 +k=0.999933 +x_0=250000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMissouriWestFIPS2403Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=36.16666666666666 +lon_0=-94.5 +k=0.999941 +x_0=850000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneMontanaFIPS2500Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45 +lat_2=49 +lat_0=44.25 +lon_0=-109.5 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNebraskaFIPS2600Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40 +lat_2=43 +lat_0=39.83333333333334 +lon_0=-100 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNevadaCentralFIPS2702Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=34.75 +lon_0=-116.6666666666667 +k=0.999900 +x_0=500000.0000000002 +y_0=6000000.000000001 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNevadaEastFIPS2701Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=34.75 +lon_0=-115.5833333333333 +k=0.999900 +x_0=200000 +y_0=7999999.999999999 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNevadaWestFIPS2703Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=34.75 +lon_0=-118.5833333333333 +k=0.999900 +x_0=799999.9999999999 +y_0=4000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNewHampshireFIPS2800Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.5 +lon_0=-71.66666666666667 +k=0.999967 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNewJerseyFIPS2900Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38.83333333333334 +lon_0=-74.5 +k=0.999900 +x_0=150000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNewMexicoCentralFIPS3002Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-106.25 +k=0.999900 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNewMexicoEastFIPS3001Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-104.3333333333333 +k=0.999909 +x_0=165000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNewMexicoWestFIPS3003Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-107.8333333333333 +k=0.999917 +x_0=829999.9999999999 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNewYorkCentralFIPS3102Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40 +lon_0=-76.58333333333333 +k=0.999938 +x_0=250000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNewYorkEastFIPS3101Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38.83333333333334 +lon_0=-74.5 +k=0.999900 +x_0=150000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNewYorkLongIslandFIPS3104Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.66666666666666 +lat_2=41.03333333333333 +lat_0=40.16666666666666 +lon_0=-74 +x_0=300000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNewYorkWestFIPS3103Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40 +lon_0=-78.58333333333333 +k=0.999938 +x_0=350000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNorthCarolinaFIPS3200Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.33333333333334 +lat_2=36.16666666666666 +lat_0=33.75 +lon_0=-79 +x_0=609601.2199999999 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNorthDakotaNorthFIPS3301Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.43333333333333 +lat_2=48.73333333333333 +lat_0=47 +lon_0=-100.5 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneNorthDakotaSouthFIPS3302Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.18333333333333 +lat_2=47.48333333333333 +lat_0=45.66666666666666 +lon_0=-100.5 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneOhioNorthFIPS3401Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.43333333333333 +lat_2=41.7 +lat_0=39.66666666666666 +lon_0=-82.5 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneOhioSouthFIPS3402Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.73333333333333 +lat_2=40.03333333333333 +lat_0=38 +lon_0=-82.5 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneOklahomaNorthFIPS3501Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=35.56666666666667 +lat_2=36.76666666666667 +lat_0=35 +lon_0=-98 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneOklahomaSouthFIPS3502Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.93333333333333 +lat_2=35.23333333333333 +lat_0=33.33333333333334 +lon_0=-98 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneOregonNorthFIPS3601Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.33333333333334 +lat_2=46 +lat_0=43.66666666666666 +lon_0=-120.5 +x_0=2500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneOregonSouthFIPS3602Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.33333333333334 +lat_2=44 +lat_0=41.66666666666666 +lon_0=-120.5 +x_0=1500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlanePennsylvaniaNorthFIPS3701Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.88333333333333 +lat_2=41.95 +lat_0=40.16666666666666 +lon_0=-77.75 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlanePennsylvaniaSouthFIPS3702Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.93333333333333 +lat_2=40.96666666666667 +lat_0=39.33333333333334 +lon_0=-77.75 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlanePRVirginIslandsFIPS5200Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=18.03333333333333 +lat_2=18.43333333333333 +lat_0=17.83333333333333 +lon_0=-66.43333333333334 +x_0=199999.9999999999 +y_0=199999.9999999999 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneRhodeIslandFIPS3800Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.08333333333334 +lon_0=-71.5 +k=0.999994 +x_0=100000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneSouthCarolinaFIPS3900Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.5 +lat_2=34.83333333333334 +lat_0=31.83333333333333 +lon_0=-81 +x_0=609600.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneSouthDakotaNorthFIPS4001Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.41666666666666 +lat_2=45.68333333333333 +lat_0=43.83333333333334 +lon_0=-100 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneSouthDakotaSouthFIPS4002Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.83333333333334 +lat_2=44.4 +lat_0=42.33333333333334 +lon_0=-100.3333333333333 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneTennesseeFIPS4100Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=35.25 +lat_2=36.41666666666666 +lat_0=34.33333333333334 +lon_0=-86 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneTexasCentralFIPS4203Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=30.11666666666667 +lat_2=31.88333333333333 +lat_0=29.66666666666667 +lon_0=-100.3333333333333 +x_0=700000 +y_0=3000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneTexasNorthCentralFIPS4202Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.13333333333333 +lat_2=33.96666666666667 +lat_0=31.66666666666667 +lon_0=-98.5 +x_0=600000.0000000001 +y_0=2000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneTexasNorthFIPS4201Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.65 +lat_2=36.18333333333333 +lat_0=34 +lon_0=-101.5 +x_0=200000 +y_0=1000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneTexasSouthCentralFIPS4204Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=28.38333333333333 +lat_2=30.28333333333333 +lat_0=27.83333333333333 +lon_0=-99 +x_0=600000.0000000001 +y_0=4000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneTexasSouthFIPS4205Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26.16666666666667 +lat_2=27.83333333333333 +lat_0=25.66666666666667 +lon_0=-98.5 +x_0=300000 +y_0=4999999.999999999 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneUtahCentralFIPS4302Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.01666666666667 +lat_2=40.65 +lat_0=38.33333333333334 +lon_0=-111.5 +x_0=500000.0000000002 +y_0=2000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneUtahNorthFIPS4301Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.71666666666667 +lat_2=41.78333333333333 +lat_0=40.33333333333334 +lon_0=-111.5 +x_0=500000.0000000002 +y_0=1000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneUtahSouthFIPS4303Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.21666666666667 +lat_2=38.35 +lat_0=36.66666666666666 +lon_0=-111.5 +x_0=500000.0000000002 +y_0=3000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneVermontFIPS4400Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.5 +lon_0=-72.5 +k=0.999964 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneVirginiaNorthFIPS4501Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.03333333333333 +lat_2=39.2 +lat_0=37.66666666666666 +lon_0=-78.5 +x_0=3499999.999999999 +y_0=2000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneVirginiaSouthFIPS4502Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36.76666666666667 +lat_2=37.96666666666667 +lat_0=36.33333333333334 +lon_0=-78.5 +x_0=3499999.999999999 +y_0=1000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWashingtonNorthFIPS4601Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.5 +lat_2=48.73333333333333 +lat_0=47 +lon_0=-120.8333333333333 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWashingtonSouthFIPS4602Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.83333333333334 +lat_2=47.33333333333334 +lat_0=45.33333333333334 +lon_0=-120.5 +x_0=500000.0000000002 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWestVirginiaNorthFIPS4701Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39 +lat_2=40.25 +lat_0=38.5 +lon_0=-79.5 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWestVirginiaSouthFIPS4702Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.48333333333333 +lat_2=38.88333333333333 +lat_0=37 +lon_0=-81 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWisconsinCentralFIPS4802Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.25 +lat_2=45.5 +lat_0=43.83333333333334 +lon_0=-90 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWisconsinNorthFIPS4801Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.56666666666667 +lat_2=46.76666666666667 +lat_0=45.16666666666666 +lon_0=-90 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWisconsinSouthFIPS4803Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.73333333333333 +lat_2=44.06666666666667 +lat_0=42 +lon_0=-90 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWyomingEastCentralFIPS4902Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-107.3333333333333 +k=0.999938 +x_0=399999.9999999999 +y_0=100000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWyomingEastFIPS4901Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-105.1666666666667 +k=0.999938 +x_0=200000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWyomingWestCentralFIPS4903Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-108.75 +k=0.999938 +x_0=600000.0000000001 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983StatePlaneWyomingWestFIPS4904Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40.5 +lon_0=-110.0833333333333 +k=0.999938 +x_0=799999.9999999999 +y_0=100000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);

    NAD1983StatePlaneAlabamaEastFIPS0101Feet.setName("NAD_1983_StatePlane_Alabama_East_FIPS_0101_Feet");
    NAD1983StatePlaneAlabamaWestFIPS0102Feet.setName("NAD_1983_StatePlane_Alabama_West_FIPS_0102_Feet");
    NAD1983StatePlaneAlaska10FIPS5010Feet.setName("NAD_1983_StatePlane_Alaska_10_FIPS_5010_Feet");
    NAD1983StatePlaneAlaska1FIPS5001Feet.setName("NAD_1983_StatePlane_Alaska_1_FIPS_5001_Feet");
    NAD1983StatePlaneAlaska2FIPS5002Feet.setName("NAD_1983_StatePlane_Alaska_2_FIPS_5002_Feet");
    NAD1983StatePlaneAlaska3FIPS5003Feet.setName("NAD_1983_StatePlane_Alaska_3_FIPS_5003_Feet");
    NAD1983StatePlaneAlaska4FIPS5004Feet.setName("NAD_1983_StatePlane_Alaska_4_FIPS_5004_Feet");
    NAD1983StatePlaneAlaska5FIPS5005Feet.setName("NAD_1983_StatePlane_Alaska_5_FIPS_5005_Feet");
    NAD1983StatePlaneAlaska6FIPS5006Feet.setName("NAD_1983_StatePlane_Alaska_6_FIPS_5006_Feet");
    NAD1983StatePlaneAlaska7FIPS5007Feet.setName("NAD_1983_StatePlane_Alaska_7_FIPS_5007_Feet");
    NAD1983StatePlaneAlaska8FIPS5008Feet.setName("NAD_1983_StatePlane_Alaska_8_FIPS_5008_Feet");
    NAD1983StatePlaneAlaska9FIPS5009Feet.setName("NAD_1983_StatePlane_Alaska_9_FIPS_5009_Feet");
    NAD1983StatePlaneArizonaCentralFIPS0202Feet.setName("NAD_1983_StatePlane_Arizona_Central_FIPS_0202_Feet");
    NAD1983StatePlaneArizonaEastFIPS0201Feet.setName("NAD_1983_StatePlane_Arizona_East_FIPS_0201_Feet");
    NAD1983StatePlaneArizonaWestFIPS0203Feet.setName("NAD_1983_StatePlane_Arizona_West_FIPS_0203_Feet");
    NAD1983StatePlaneArkansasNorthFIPS0301Feet.setName("NAD_1983_StatePlane_Arkansas_North_FIPS_0301_Feet");
    NAD1983StatePlaneArkansasSouthFIPS0302Feet.setName("NAD_1983_StatePlane_Arkansas_South_FIPS_0302_Feet");
    NAD1983StatePlaneCaliforniaIFIPS0401Feet.setName("NAD_1983_StatePlane_California_I_FIPS_0401_Feet");
    NAD1983StatePlaneCaliforniaIIFIPS0402Feet.setName("NAD_1983_StatePlane_California_II_FIPS_0402_Feet");
    NAD1983StatePlaneCaliforniaIIIFIPS0403Feet.setName("NAD_1983_StatePlane_California_III_FIPS_0403_Feet");
    NAD1983StatePlaneCaliforniaIVFIPS0404Feet.setName("NAD_1983_StatePlane_California_IV_FIPS_0404_Feet");
    NAD1983StatePlaneCaliforniaVFIPS0405Feet.setName("NAD_1983_StatePlane_California_V_FIPS_0405_Feet");
    NAD1983StatePlaneCaliforniaVIFIPS0406Feet.setName("NAD_1983_StatePlane_California_VI_FIPS_0406_Feet");
    NAD1983StatePlaneColoradoCentralFIPS0502Feet.setName("NAD_1983_StatePlane_Colorado_Central_FIPS_0502_Feet");
    NAD1983StatePlaneColoradoNorthFIPS0501Feet.setName("NAD_1983_StatePlane_Colorado_North_FIPS_0501_Feet");
    NAD1983StatePlaneColoradoSouthFIPS0503Feet.setName("NAD_1983_StatePlane_Colorado_South_FIPS_0503_Feet");
    NAD1983StatePlaneConnecticutFIPS0600Feet.setName("NAD_1983_StatePlane_Connecticut_FIPS_0600_Feet");
    NAD1983StatePlaneDelawareFIPS0700Feet.setName("NAD_1983_StatePlane_Delaware_FIPS_0700_Feet");
    NAD1983StatePlaneFloridaEastFIPS0901Feet.setName("NAD_1983_StatePlane_Florida_East_FIPS_0901_Feet");
    NAD1983StatePlaneFloridaNorthFIPS0903Feet.setName("NAD_1983_StatePlane_Florida_North_FIPS_0903_Feet");
    NAD1983StatePlaneFloridaWestFIPS0902Feet.setName("NAD_1983_StatePlane_Florida_West_FIPS_0902_Feet");
    NAD1983StatePlaneGeorgiaEastFIPS1001Feet.setName("NAD_1983_StatePlane_Georgia_East_FIPS_1001_Feet");
    NAD1983StatePlaneGeorgiaWestFIPS1002Feet.setName("NAD_1983_StatePlane_Georgia_West_FIPS_1002_Feet");
    NAD1983StatePlaneGuamFIPS5400Feet.setName("NAD_1983_StatePlane_Guam_FIPS_5400_Feet");
    NAD1983StatePlaneHawaii1FIPS5101Feet.setName("NAD_1983_StatePlane_Hawaii_1_FIPS_5101_Feet");
    NAD1983StatePlaneHawaii2FIPS5102Feet.setName("NAD_1983_StatePlane_Hawaii_2_FIPS_5102_Feet");
    NAD1983StatePlaneHawaii3FIPS5103Feet.setName("NAD_1983_StatePlane_Hawaii_3_FIPS_5103_Feet");
    NAD1983StatePlaneHawaii4FIPS5104Feet.setName("NAD_1983_StatePlane_Hawaii_4_FIPS_5104_Feet");
    NAD1983StatePlaneHawaii5FIPS5105Feet.setName("NAD_1983_StatePlane_Hawaii_5_FIPS_5105_Feet");
    NAD1983StatePlaneIdahoCentralFIPS1102Feet.setName("NAD_1983_StatePlane_Idaho_Central_FIPS_1102_Feet");
    NAD1983StatePlaneIdahoEastFIPS1101Feet.setName("NAD_1983_StatePlane_Idaho_East_FIPS_1101_Feet");
    NAD1983StatePlaneIdahoWestFIPS1103Feet.setName("NAD_1983_StatePlane_Idaho_West_FIPS_1103_Feet");
    NAD1983StatePlaneIllinoisEastFIPS1201Feet.setName("NAD_1983_StatePlane_Illinois_East_FIPS_1201_Feet");
    NAD1983StatePlaneIllinoisWestFIPS1202Feet.setName("NAD_1983_StatePlane_Illinois_West_FIPS_1202_Feet");
    NAD1983StatePlaneIndianaEastFIPS1301Feet.setName("NAD_1983_StatePlane_Indiana_East_FIPS_1301_Feet");
    NAD1983StatePlaneIndianaWestFIPS1302Feet.setName("NAD_1983_StatePlane_Indiana_West_FIPS_1302_Feet");
    NAD1983StatePlaneIowaNorthFIPS1401Feet.setName("NAD_1983_StatePlane_Iowa_North_FIPS_1401_Feet");
    NAD1983StatePlaneIowaSouthFIPS1402Feet.setName("NAD_1983_StatePlane_Iowa_South_FIPS_1402_Feet");
    NAD1983StatePlaneKansasNorthFIPS1501Feet.setName("NAD_1983_StatePlane_Kansas_North_FIPS_1501_Feet");
    NAD1983StatePlaneKansasSouthFIPS1502Feet.setName("NAD_1983_StatePlane_Kansas_South_FIPS_1502_Feet");
    NAD1983StatePlaneKentuckyFIPS1600Feet.setName("NAD_1983_StatePlane_Kentucky_FIPS_1600_Feet");
    NAD1983StatePlaneKentuckyNorthFIPS1601Feet.setName("NAD_1983_StatePlane_Kentucky_North_FIPS_1601_Feet");
    NAD1983StatePlaneKentuckySouthFIPS1602Feet.setName("NAD_1983_StatePlane_Kentucky_South_FIPS_1602_Feet");
    NAD1983StatePlaneLouisianaNorthFIPS1701Feet.setName("NAD_1983_StatePlane_Louisiana_North_FIPS_1701_Feet");
    NAD1983StatePlaneLouisianaSouthFIPS1702Feet.setName("NAD_1983_StatePlane_Louisiana_South_FIPS_1702_Feet");
    NAD1983StatePlaneMaineEastFIPS1801Feet.setName("NAD_1983_StatePlane_Maine_East_FIPS_1801_Feet");
    NAD1983StatePlaneMaineWestFIPS1802Feet.setName("NAD_1983_StatePlane_Maine_West_FIPS_1802_Feet");
    NAD1983StatePlaneMarylandFIPS1900Feet.setName("NAD_1983_StatePlane_Maryland_FIPS_1900_Feet");
    NAD1983StatePlaneMassachusettsIslandFIPS2002Feet.setName("NAD_1983_StatePlane_Massachusetts_Island_FIPS_2002_Feet");
    NAD1983StatePlaneMassachusettsMainlandFIPS2001Feet.setName("NAD_1983_StatePlane_Massachusetts_Mainland_FIPS_2001_Feet");
    NAD1983StatePlaneMichiganCentralFIPS2112Feet.setName("NAD_1983_StatePlane_Michigan_Central_FIPS_2112_Feet");
    NAD1983StatePlaneMichiganNorthFIPS2111Feet.setName("NAD_1983_StatePlane_Michigan_North_FIPS_2111_Feet");
    NAD1983StatePlaneMichiganSouthFIPS2113Feet.setName("NAD_1983_StatePlane_Michigan_South_FIPS_2113_Feet");
    NAD1983StatePlaneMinnesotaCentralFIPS2202Feet.setName("NAD_1983_StatePlane_Minnesota_Central_FIPS_2202_Feet");
    NAD1983StatePlaneMinnesotaNorthFIPS2201Feet.setName("NAD_1983_StatePlane_Minnesota_North_FIPS_2201_Feet");
    NAD1983StatePlaneMinnesotaSouthFIPS2203Feet.setName("NAD_1983_StatePlane_Minnesota_South_FIPS_2203_Feet");
    NAD1983StatePlaneMississippiEastFIPS2301Feet.setName("NAD_1983_StatePlane_Mississippi_East_FIPS_2301_Feet");
    NAD1983StatePlaneMississippiWestFIPS2302Feet.setName("NAD_1983_StatePlane_Mississippi_West_FIPS_2302_Feet");
    NAD1983StatePlaneMissouriCentralFIPS2402Feet.setName("NAD_1983_StatePlane_Missouri_Central_FIPS_2402_Feet");
    NAD1983StatePlaneMissouriEastFIPS2401Feet.setName("NAD_1983_StatePlane_Missouri_East_FIPS_2401_Feet");
    NAD1983StatePlaneMissouriWestFIPS2403Feet.setName("NAD_1983_StatePlane_Missouri_West_FIPS_2403_Feet");
    NAD1983StatePlaneMontanaFIPS2500Feet.setName("NAD_1983_StatePlane_Montana_FIPS_2500_Feet");
    NAD1983StatePlaneNebraskaFIPS2600Feet.setName("NAD_1983_StatePlane_Nebraska_FIPS_2600_Feet");
    NAD1983StatePlaneNevadaCentralFIPS2702Feet.setName("NAD_1983_StatePlane_Nevada_Central_FIPS_2702_Feet");
    NAD1983StatePlaneNevadaEastFIPS2701Feet.setName("NAD_1983_StatePlane_Nevada_East_FIPS_2701_Feet");
    NAD1983StatePlaneNevadaWestFIPS2703Feet.setName("NAD_1983_StatePlane_Nevada_West_FIPS_2703_Feet");
    NAD1983StatePlaneNewHampshireFIPS2800Feet.setName("NAD_1983_StatePlane_New_Hampshire_FIPS_2800_Feet");
    NAD1983StatePlaneNewJerseyFIPS2900Feet.setName("NAD_1983_StatePlane_New_Jersey_FIPS_2900_Feet");
    NAD1983StatePlaneNewMexicoCentralFIPS3002Feet.setName("NAD_1983_StatePlane_New_Mexico_Central_FIPS_3002_Feet");
    NAD1983StatePlaneNewMexicoEastFIPS3001Feet.setName("NAD_1983_StatePlane_New_Mexico_East_FIPS_3001_Feet");
    NAD1983StatePlaneNewMexicoWestFIPS3003Feet.setName("NAD_1983_StatePlane_New_Mexico_West_FIPS_3003_Feet");
    NAD1983StatePlaneNewYorkCentralFIPS3102Feet.setName("NAD_1983_StatePlane_New_York_Central_FIPS_3102_Feet");
    NAD1983StatePlaneNewYorkEastFIPS3101Feet.setName("NAD_1983_StatePlane_New_York_East_FIPS_3101_Feet");
    NAD1983StatePlaneNewYorkLongIslandFIPS3104Feet.setName("NAD_1983_StatePlane_New_York_Long_Island_FIPS_3104_Feet");
    NAD1983StatePlaneNewYorkWestFIPS3103Feet.setName("NAD_1983_StatePlane_New_York_West_FIPS_3103_Feet");
    NAD1983StatePlaneNorthCarolinaFIPS3200Feet.setName("NAD_1983_StatePlane_North_Carolina_FIPS_3200_Feet");
    NAD1983StatePlaneNorthDakotaNorthFIPS3301Feet.setName("NAD_1983_StatePlane_North_Dakota_North_FIPS_3301_Feet");
    NAD1983StatePlaneNorthDakotaSouthFIPS3302Feet.setName("NAD_1983_StatePlane_North_Dakota_South_FIPS_3302_Feet");
    NAD1983StatePlaneOhioNorthFIPS3401Feet.setName("NAD_1983_StatePlane_Ohio_North_FIPS_3401_Feet");
    NAD1983StatePlaneOhioSouthFIPS3402Feet.setName("NAD_1983_StatePlane_Ohio_South_FIPS_3402_Feet");
    NAD1983StatePlaneOklahomaNorthFIPS3501Feet.setName("NAD_1983_StatePlane_Oklahoma_North_FIPS_3501_Feet");
    NAD1983StatePlaneOklahomaSouthFIPS3502Feet.setName("NAD_1983_StatePlane_Oklahoma_South_FIPS_3502_Feet");
    NAD1983StatePlaneOregonNorthFIPS3601Feet.setName("NAD_1983_StatePlane_Oregon_North_FIPS_3601_Feet");
    NAD1983StatePlaneOregonSouthFIPS3602Feet.setName("NAD_1983_StatePlane_Oregon_South_FIPS_3602_Feet");
    NAD1983StatePlanePennsylvaniaNorthFIPS3701Feet.setName("NAD_1983_StatePlane_Pennsylvania_North_FIPS_3701_Feet");
    NAD1983StatePlanePennsylvaniaSouthFIPS3702Feet.setName("NAD_1983_StatePlane_Pennsylvania_South_FIPS_3702_Feet");
    NAD1983StatePlanePRVirginIslandsFIPS5200Feet.setName("NAD_1983_StatePlane_Puerto_Rico_Virgin_Islands_FIPS_5200_Feet");
    NAD1983StatePlaneRhodeIslandFIPS3800Feet.setName("NAD_1983_StatePlane_Rhode_Island_FIPS_3800_Feet");
    NAD1983StatePlaneSouthCarolinaFIPS3900Feet.setName("NAD_1983_StatePlane_South_Carolina_FIPS_3900_Feet");
    NAD1983StatePlaneSouthDakotaNorthFIPS4001Feet.setName("NAD_1983_StatePlane_South_Dakota_North_FIPS_4001_Feet");
    NAD1983StatePlaneSouthDakotaSouthFIPS4002Feet.setName("NAD_1983_StatePlane_South_Dakota_South_FIPS_4002_Feet");
    NAD1983StatePlaneTennesseeFIPS4100Feet.setName("NAD_1983_StatePlane_Tennessee_FIPS_4100_Feet");
    NAD1983StatePlaneTexasCentralFIPS4203Feet.setName("NAD_1983_StatePlane_Texas_Central_FIPS_4203_Feet");
    NAD1983StatePlaneTexasNorthCentralFIPS4202Feet.setName("NAD_1983_StatePlane_Texas_North_Central_FIPS_4202_Feet");
    NAD1983StatePlaneTexasNorthFIPS4201Feet.setName("NAD_1983_StatePlane_Texas_North_FIPS_4201_Feet");
    NAD1983StatePlaneTexasSouthCentralFIPS4204Feet.setName("NAD_1983_StatePlane_Texas_South_Central_FIPS_4204_Feet");
    NAD1983StatePlaneTexasSouthFIPS4205Feet.setName("NAD_1983_StatePlane_Texas_South_FIPS_4205_Feet");
    NAD1983StatePlaneUtahCentralFIPS4302Feet.setName("NAD_1983_StatePlane_Utah_Central_FIPS_4302_Feet");
    NAD1983StatePlaneUtahNorthFIPS4301Feet.setName("NAD_1983_StatePlane_Utah_North_FIPS_4301_Feet");
    NAD1983StatePlaneUtahSouthFIPS4303Feet.setName("NAD_1983_StatePlane_Utah_South_FIPS_4303_Feet");
    NAD1983StatePlaneVermontFIPS4400Feet.setName("NAD_1983_StatePlane_Vermont_FIPS_4400_Feet");
    NAD1983StatePlaneVirginiaNorthFIPS4501Feet.setName("NAD_1983_StatePlane_Virginia_North_FIPS_4501_Feet");
    NAD1983StatePlaneVirginiaSouthFIPS4502Feet.setName("NAD_1983_StatePlane_Virginia_South_FIPS_4502_Feet");
    NAD1983StatePlaneWashingtonNorthFIPS4601Feet.setName("NAD_1983_StatePlane_Washington_North_FIPS_4601_Feet");
    NAD1983StatePlaneWashingtonSouthFIPS4602Feet.setName("NAD_1983_StatePlane_Washington_South_FIPS_4602_Feet");
    NAD1983StatePlaneWestVirginiaNorthFIPS4701Feet.setName("NAD_1983_StatePlane_West_Virginia_North_FIPS_4701_Feet");
    NAD1983StatePlaneWestVirginiaSouthFIPS4702Feet.setName("NAD_1983_StatePlane_West_Virginia_South_FIPS_4702_Feet");
    NAD1983StatePlaneWisconsinCentralFIPS4802Feet.setName("NAD_1983_StatePlane_Wisconsin_Central_FIPS_4802_Feet");
    NAD1983StatePlaneWisconsinNorthFIPS4801Feet.setName("NAD_1983_StatePlane_Wisconsin_North_FIPS_4801_Feet");
    NAD1983StatePlaneWisconsinSouthFIPS4803Feet.setName("NAD_1983_StatePlane_Wisconsin_South_FIPS_4803_Feet");
    NAD1983StatePlaneWyomingEastCentralFIPS4902Feet.setName("NAD_1983_StatePlane_Wyoming_East_Central_FIPS_4902_Feet");
    NAD1983StatePlaneWyomingEastFIPS4901Feet.setName("NAD_1983_StatePlane_Wyoming_East_FIPS_4901_Feet");
    NAD1983StatePlaneWyomingWestCentralFIPS4903Feet.setName("NAD_1983_StatePlane_Wyoming_West_Central_FIPS_4903_Feet");
    NAD1983StatePlaneWyomingWestFIPS4904Feet.setName("NAD_1983_StatePlane_Wyoming_West_FIPS_4904_Feet");

    NAD1983StatePlaneAlabamaEastFIPS0101Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlabamaWestFIPS0102Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska10FIPS5010Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska1FIPS5001Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska2FIPS5002Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska3FIPS5003Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska4FIPS5004Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska5FIPS5005Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska6FIPS5006Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska7FIPS5007Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska8FIPS5008Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneAlaska9FIPS5009Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneArizonaCentralFIPS0202Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneArizonaEastFIPS0201Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneArizonaWestFIPS0203Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneArkansasNorthFIPS0301Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneArkansasSouthFIPS0302Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaIFIPS0401Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaIIFIPS0402Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaIIIFIPS0403Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaIVFIPS0404Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaVFIPS0405Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneCaliforniaVIFIPS0406Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneColoradoCentralFIPS0502Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneColoradoNorthFIPS0501Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneColoradoSouthFIPS0503Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneConnecticutFIPS0600Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneDelawareFIPS0700Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneFloridaEastFIPS0901Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneFloridaNorthFIPS0903Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneFloridaWestFIPS0902Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneGeorgiaEastFIPS1001Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneGeorgiaWestFIPS1002Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneGuamFIPS5400Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneHawaii1FIPS5101Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneHawaii2FIPS5102Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneHawaii3FIPS5103Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneHawaii4FIPS5104Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneHawaii5FIPS5105Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIdahoCentralFIPS1102Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIdahoEastFIPS1101Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIdahoWestFIPS1103Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIllinoisEastFIPS1201Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIllinoisWestFIPS1202Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIndianaEastFIPS1301Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIndianaWestFIPS1302Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIowaNorthFIPS1401Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneIowaSouthFIPS1402Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneKansasNorthFIPS1501Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneKansasSouthFIPS1502Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneKentuckyFIPS1600Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneKentuckyNorthFIPS1601Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneKentuckySouthFIPS1602Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneLouisianaNorthFIPS1701Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneLouisianaSouthFIPS1702Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMaineEastFIPS1801Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMaineWestFIPS1802Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMarylandFIPS1900Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMassachusettsIslandFIPS2002Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMassachusettsMainlandFIPS2001Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMichiganCentralFIPS2112Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMichiganNorthFIPS2111Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMichiganSouthFIPS2113Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMinnesotaCentralFIPS2202Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMinnesotaNorthFIPS2201Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMinnesotaSouthFIPS2203Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMississippiEastFIPS2301Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMississippiWestFIPS2302Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMissouriCentralFIPS2402Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMissouriEastFIPS2401Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMissouriWestFIPS2403Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneMontanaFIPS2500Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNebraskaFIPS2600Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNevadaCentralFIPS2702Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNevadaEastFIPS2701Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNevadaWestFIPS2703Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewHampshireFIPS2800Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewJerseyFIPS2900Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewMexicoCentralFIPS3002Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewMexicoEastFIPS3001Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewMexicoWestFIPS3003Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewYorkCentralFIPS3102Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewYorkEastFIPS3101Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewYorkLongIslandFIPS3104Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNewYorkWestFIPS3103Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNorthCarolinaFIPS3200Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNorthDakotaNorthFIPS3301Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneNorthDakotaSouthFIPS3302Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOhioNorthFIPS3401Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOhioSouthFIPS3402Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOklahomaNorthFIPS3501Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOklahomaSouthFIPS3502Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOregonNorthFIPS3601Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneOregonSouthFIPS3602Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlanePennsylvaniaNorthFIPS3701Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlanePennsylvaniaSouthFIPS3702Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlanePRVirginIslandsFIPS5200Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneRhodeIslandFIPS3800Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneSouthCarolinaFIPS3900Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneSouthDakotaNorthFIPS4001Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneSouthDakotaSouthFIPS4002Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTennesseeFIPS4100Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTexasCentralFIPS4203Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTexasNorthCentralFIPS4202Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTexasNorthFIPS4201Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTexasSouthCentralFIPS4204Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneTexasSouthFIPS4205Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneUtahCentralFIPS4302Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneUtahNorthFIPS4301Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneUtahSouthFIPS4303Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneVermontFIPS4400Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneVirginiaNorthFIPS4501Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneVirginiaSouthFIPS4502Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWashingtonNorthFIPS4601Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWashingtonSouthFIPS4602Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWestVirginiaNorthFIPS4701Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWestVirginiaSouthFIPS4702Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWisconsinCentralFIPS4802Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWisconsinNorthFIPS4801Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWisconsinSouthFIPS4803Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWyomingEastCentralFIPS4902Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWyomingEastFIPS4901Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWyomingWestCentralFIPS4903Feet.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983StatePlaneWyomingWestFIPS4904Feet.getGeographicInfo().setName("GCS_North_American_1983");

    NAD1983StatePlaneAlabamaEastFIPS0101Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlabamaWestFIPS0102Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska10FIPS5010Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska1FIPS5001Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska2FIPS5002Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska3FIPS5003Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska4FIPS5004Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska5FIPS5005Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska6FIPS5006Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska7FIPS5007Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska8FIPS5008Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneAlaska9FIPS5009Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneArizonaCentralFIPS0202Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneArizonaEastFIPS0201Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneArizonaWestFIPS0203Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneArkansasNorthFIPS0301Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneArkansasSouthFIPS0302Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaIFIPS0401Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaIIFIPS0402Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaIIIFIPS0403Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaIVFIPS0404Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaVFIPS0405Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneCaliforniaVIFIPS0406Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneColoradoCentralFIPS0502Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneColoradoNorthFIPS0501Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneColoradoSouthFIPS0503Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneConnecticutFIPS0600Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneDelawareFIPS0700Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneFloridaEastFIPS0901Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneFloridaNorthFIPS0903Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneFloridaWestFIPS0902Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneGeorgiaEastFIPS1001Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneGeorgiaWestFIPS1002Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneGuamFIPS5400Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneHawaii1FIPS5101Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneHawaii2FIPS5102Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneHawaii3FIPS5103Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneHawaii4FIPS5104Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneHawaii5FIPS5105Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIdahoCentralFIPS1102Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIdahoEastFIPS1101Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIdahoWestFIPS1103Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIllinoisEastFIPS1201Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIllinoisWestFIPS1202Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIndianaEastFIPS1301Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIndianaWestFIPS1302Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIowaNorthFIPS1401Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneIowaSouthFIPS1402Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneKansasNorthFIPS1501Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneKansasSouthFIPS1502Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneKentuckyFIPS1600Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneKentuckyNorthFIPS1601Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneKentuckySouthFIPS1602Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneLouisianaNorthFIPS1701Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneLouisianaSouthFIPS1702Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMaineEastFIPS1801Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMaineWestFIPS1802Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMarylandFIPS1900Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMassachusettsIslandFIPS2002Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMassachusettsMainlandFIPS2001Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMichiganCentralFIPS2112Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMichiganNorthFIPS2111Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMichiganSouthFIPS2113Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMinnesotaCentralFIPS2202Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMinnesotaNorthFIPS2201Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMinnesotaSouthFIPS2203Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMississippiEastFIPS2301Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMississippiWestFIPS2302Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMissouriCentralFIPS2402Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMissouriEastFIPS2401Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMissouriWestFIPS2403Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneMontanaFIPS2500Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNebraskaFIPS2600Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNevadaCentralFIPS2702Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNevadaEastFIPS2701Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNevadaWestFIPS2703Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewHampshireFIPS2800Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewJerseyFIPS2900Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewMexicoCentralFIPS3002Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewMexicoEastFIPS3001Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewMexicoWestFIPS3003Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewYorkCentralFIPS3102Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewYorkEastFIPS3101Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewYorkLongIslandFIPS3104Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNewYorkWestFIPS3103Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNorthCarolinaFIPS3200Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNorthDakotaNorthFIPS3301Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneNorthDakotaSouthFIPS3302Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOhioNorthFIPS3401Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOhioSouthFIPS3402Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOklahomaNorthFIPS3501Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOklahomaSouthFIPS3502Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOregonNorthFIPS3601Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneOregonSouthFIPS3602Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlanePennsylvaniaNorthFIPS3701Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlanePennsylvaniaSouthFIPS3702Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlanePRVirginIslandsFIPS5200Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneRhodeIslandFIPS3800Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneSouthCarolinaFIPS3900Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneSouthDakotaNorthFIPS4001Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneSouthDakotaSouthFIPS4002Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTennesseeFIPS4100Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTexasCentralFIPS4203Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTexasNorthCentralFIPS4202Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTexasNorthFIPS4201Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTexasSouthCentralFIPS4204Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneTexasSouthFIPS4205Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneUtahCentralFIPS4302Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneUtahNorthFIPS4301Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneUtahSouthFIPS4303Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneVermontFIPS4400Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneVirginiaNorthFIPS4501Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneVirginiaSouthFIPS4502Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWashingtonNorthFIPS4601Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWashingtonSouthFIPS4602Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWestVirginiaNorthFIPS4701Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWestVirginiaSouthFIPS4702Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWisconsinCentralFIPS4802Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWisconsinNorthFIPS4801Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWisconsinSouthFIPS4803Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWyomingEastCentralFIPS4902Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWyomingEastFIPS4901Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWyomingWestCentralFIPS4903Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983StatePlaneWyomingWestFIPS4904Feet.getGeographicInfo().getDatum().setName("D_North_American_1983");
  }

  //</editor-fold>

  /**
   * @return the NAD1983StatePlaneAlabamaEastFIPS0101Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlabamaEastFIPS0101Feet() {
    return NAD1983StatePlaneAlabamaEastFIPS0101Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlabamaWestFIPS0102Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlabamaWestFIPS0102Feet() {
    return NAD1983StatePlaneAlabamaWestFIPS0102Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska10FIPS5010Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska10FIPS5010Feet() {
    return NAD1983StatePlaneAlaska10FIPS5010Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska1FIPS5001Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska1FIPS5001Feet() {
    return NAD1983StatePlaneAlaska1FIPS5001Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska2FIPS5002Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska2FIPS5002Feet() {
    return NAD1983StatePlaneAlaska2FIPS5002Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska3FIPS5003Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska3FIPS5003Feet() {
    return NAD1983StatePlaneAlaska3FIPS5003Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska4FIPS5004Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska4FIPS5004Feet() {
    return NAD1983StatePlaneAlaska4FIPS5004Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska5FIPS5005Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska5FIPS5005Feet() {
    return NAD1983StatePlaneAlaska5FIPS5005Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska6FIPS5006Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska6FIPS5006Feet() {
    return NAD1983StatePlaneAlaska6FIPS5006Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska7FIPS5007Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska7FIPS5007Feet() {
    return NAD1983StatePlaneAlaska7FIPS5007Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska8FIPS5008Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska8FIPS5008Feet() {
    return NAD1983StatePlaneAlaska8FIPS5008Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneAlaska9FIPS5009Feet
   */
  public ProjectionInfo getNAD1983StatePlaneAlaska9FIPS5009Feet() {
    return NAD1983StatePlaneAlaska9FIPS5009Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneArizonaCentralFIPS0202Feet
   */
  public ProjectionInfo getNAD1983StatePlaneArizonaCentralFIPS0202Feet() {
    return NAD1983StatePlaneArizonaCentralFIPS0202Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneArizonaEastFIPS0201Feet
   */
  public ProjectionInfo getNAD1983StatePlaneArizonaEastFIPS0201Feet() {
    return NAD1983StatePlaneArizonaEastFIPS0201Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneArizonaWestFIPS0203Feet
   */
  public ProjectionInfo getNAD1983StatePlaneArizonaWestFIPS0203Feet() {
    return NAD1983StatePlaneArizonaWestFIPS0203Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneArkansasNorthFIPS0301Feet
   */
  public ProjectionInfo getNAD1983StatePlaneArkansasNorthFIPS0301Feet() {
    return NAD1983StatePlaneArkansasNorthFIPS0301Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneArkansasSouthFIPS0302Feet
   */
  public ProjectionInfo getNAD1983StatePlaneArkansasSouthFIPS0302Feet() {
    return NAD1983StatePlaneArkansasSouthFIPS0302Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaIFIPS0401Feet
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaIFIPS0401Feet() {
    return NAD1983StatePlaneCaliforniaIFIPS0401Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaIIFIPS0402Feet
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaIIFIPS0402Feet() {
    return NAD1983StatePlaneCaliforniaIIFIPS0402Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaIIIFIPS0403Feet
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaIIIFIPS0403Feet() {
    return NAD1983StatePlaneCaliforniaIIIFIPS0403Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaIVFIPS0404Feet
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaIVFIPS0404Feet() {
    return NAD1983StatePlaneCaliforniaIVFIPS0404Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaVFIPS0405Feet
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaVFIPS0405Feet() {
    return NAD1983StatePlaneCaliforniaVFIPS0405Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneCaliforniaVIFIPS0406Feet
   */
  public ProjectionInfo getNAD1983StatePlaneCaliforniaVIFIPS0406Feet() {
    return NAD1983StatePlaneCaliforniaVIFIPS0406Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneColoradoCentralFIPS0502Feet
   */
  public ProjectionInfo getNAD1983StatePlaneColoradoCentralFIPS0502Feet() {
    return NAD1983StatePlaneColoradoCentralFIPS0502Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneColoradoNorthFIPS0501Feet
   */
  public ProjectionInfo getNAD1983StatePlaneColoradoNorthFIPS0501Feet() {
    return NAD1983StatePlaneColoradoNorthFIPS0501Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneColoradoSouthFIPS0503Feet
   */
  public ProjectionInfo getNAD1983StatePlaneColoradoSouthFIPS0503Feet() {
    return NAD1983StatePlaneColoradoSouthFIPS0503Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneConnecticutFIPS0600Feet
   */
  public ProjectionInfo getNAD1983StatePlaneConnecticutFIPS0600Feet() {
    return NAD1983StatePlaneConnecticutFIPS0600Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneDelawareFIPS0700Feet
   */
  public ProjectionInfo getNAD1983StatePlaneDelawareFIPS0700Feet() {
    return NAD1983StatePlaneDelawareFIPS0700Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneFloridaEastFIPS0901Feet
   */
  public ProjectionInfo getNAD1983StatePlaneFloridaEastFIPS0901Feet() {
    return NAD1983StatePlaneFloridaEastFIPS0901Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneFloridaNorthFIPS0903Feet
   */
  public ProjectionInfo getNAD1983StatePlaneFloridaNorthFIPS0903Feet() {
    return NAD1983StatePlaneFloridaNorthFIPS0903Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneFloridaWestFIPS0902Feet
   */
  public ProjectionInfo getNAD1983StatePlaneFloridaWestFIPS0902Feet() {
    return NAD1983StatePlaneFloridaWestFIPS0902Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneGeorgiaEastFIPS1001Feet
   */
  public ProjectionInfo getNAD1983StatePlaneGeorgiaEastFIPS1001Feet() {
    return NAD1983StatePlaneGeorgiaEastFIPS1001Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneGeorgiaWestFIPS1002Feet
   */
  public ProjectionInfo getNAD1983StatePlaneGeorgiaWestFIPS1002Feet() {
    return NAD1983StatePlaneGeorgiaWestFIPS1002Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneGuamFIPS5400Feet
   */
  public ProjectionInfo getNAD1983StatePlaneGuamFIPS5400Feet() {
    return NAD1983StatePlaneGuamFIPS5400Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneHawaii1FIPS5101Feet
   */
  public ProjectionInfo getNAD1983StatePlaneHawaii1FIPS5101Feet() {
    return NAD1983StatePlaneHawaii1FIPS5101Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneHawaii2FIPS5102Feet
   */
  public ProjectionInfo getNAD1983StatePlaneHawaii2FIPS5102Feet() {
    return NAD1983StatePlaneHawaii2FIPS5102Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneHawaii3FIPS5103Feet
   */
  public ProjectionInfo getNAD1983StatePlaneHawaii3FIPS5103Feet() {
    return NAD1983StatePlaneHawaii3FIPS5103Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneHawaii4FIPS5104Feet
   */
  public ProjectionInfo getNAD1983StatePlaneHawaii4FIPS5104Feet() {
    return NAD1983StatePlaneHawaii4FIPS5104Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneHawaii5FIPS5105Feet
   */
  public ProjectionInfo getNAD1983StatePlaneHawaii5FIPS5105Feet() {
    return NAD1983StatePlaneHawaii5FIPS5105Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneIdahoCentralFIPS1102Feet
   */
  public ProjectionInfo getNAD1983StatePlaneIdahoCentralFIPS1102Feet() {
    return NAD1983StatePlaneIdahoCentralFIPS1102Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneIdahoEastFIPS1101Feet
   */
  public ProjectionInfo getNAD1983StatePlaneIdahoEastFIPS1101Feet() {
    return NAD1983StatePlaneIdahoEastFIPS1101Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneIdahoWestFIPS1103Feet
   */
  public ProjectionInfo getNAD1983StatePlaneIdahoWestFIPS1103Feet() {
    return NAD1983StatePlaneIdahoWestFIPS1103Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneIllinoisEastFIPS1201Feet
   */
  public ProjectionInfo getNAD1983StatePlaneIllinoisEastFIPS1201Feet() {
    return NAD1983StatePlaneIllinoisEastFIPS1201Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneIllinoisWestFIPS1202Feet
   */
  public ProjectionInfo getNAD1983StatePlaneIllinoisWestFIPS1202Feet() {
    return NAD1983StatePlaneIllinoisWestFIPS1202Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneIndianaEastFIPS1301Feet
   */
  public ProjectionInfo getNAD1983StatePlaneIndianaEastFIPS1301Feet() {
    return NAD1983StatePlaneIndianaEastFIPS1301Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneIndianaWestFIPS1302Feet
   */
  public ProjectionInfo getNAD1983StatePlaneIndianaWestFIPS1302Feet() {
    return NAD1983StatePlaneIndianaWestFIPS1302Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneIowaNorthFIPS1401Feet
   */
  public ProjectionInfo getNAD1983StatePlaneIowaNorthFIPS1401Feet() {
    return NAD1983StatePlaneIowaNorthFIPS1401Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneIowaSouthFIPS1402Feet
   */
  public ProjectionInfo getNAD1983StatePlaneIowaSouthFIPS1402Feet() {
    return NAD1983StatePlaneIowaSouthFIPS1402Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneKansasNorthFIPS1501Feet
   */
  public ProjectionInfo getNAD1983StatePlaneKansasNorthFIPS1501Feet() {
    return NAD1983StatePlaneKansasNorthFIPS1501Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneKansasSouthFIPS1502Feet
   */
  public ProjectionInfo getNAD1983StatePlaneKansasSouthFIPS1502Feet() {
    return NAD1983StatePlaneKansasSouthFIPS1502Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneKentuckyFIPS1600Feet
   */
  public ProjectionInfo getNAD1983StatePlaneKentuckyFIPS1600Feet() {
    return NAD1983StatePlaneKentuckyFIPS1600Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneKentuckyNorthFIPS1601Feet
   */
  public ProjectionInfo getNAD1983StatePlaneKentuckyNorthFIPS1601Feet() {
    return NAD1983StatePlaneKentuckyNorthFIPS1601Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneKentuckySouthFIPS1602Feet
   */
  public ProjectionInfo getNAD1983StatePlaneKentuckySouthFIPS1602Feet() {
    return NAD1983StatePlaneKentuckySouthFIPS1602Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneLouisianaNorthFIPS1701Feet
   */
  public ProjectionInfo getNAD1983StatePlaneLouisianaNorthFIPS1701Feet() {
    return NAD1983StatePlaneLouisianaNorthFIPS1701Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneLouisianaSouthFIPS1702Feet
   */
  public ProjectionInfo getNAD1983StatePlaneLouisianaSouthFIPS1702Feet() {
    return NAD1983StatePlaneLouisianaSouthFIPS1702Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMaineEastFIPS1801Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMaineEastFIPS1801Feet() {
    return NAD1983StatePlaneMaineEastFIPS1801Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMaineWestFIPS1802Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMaineWestFIPS1802Feet() {
    return NAD1983StatePlaneMaineWestFIPS1802Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMarylandFIPS1900Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMarylandFIPS1900Feet() {
    return NAD1983StatePlaneMarylandFIPS1900Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMassachusettsIslandFIPS2002Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMassachusettsIslandFIPS2002Feet() {
    return NAD1983StatePlaneMassachusettsIslandFIPS2002Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMassachusettsMainlandFIPS2001Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMassachusettsMainlandFIPS2001Feet() {
    return NAD1983StatePlaneMassachusettsMainlandFIPS2001Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMichiganCentralFIPS2112Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMichiganCentralFIPS2112Feet() {
    return NAD1983StatePlaneMichiganCentralFIPS2112Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMichiganNorthFIPS2111Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMichiganNorthFIPS2111Feet() {
    return NAD1983StatePlaneMichiganNorthFIPS2111Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMichiganSouthFIPS2113Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMichiganSouthFIPS2113Feet() {
    return NAD1983StatePlaneMichiganSouthFIPS2113Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMinnesotaCentralFIPS2202Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMinnesotaCentralFIPS2202Feet() {
    return NAD1983StatePlaneMinnesotaCentralFIPS2202Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMinnesotaNorthFIPS2201Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMinnesotaNorthFIPS2201Feet() {
    return NAD1983StatePlaneMinnesotaNorthFIPS2201Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMinnesotaSouthFIPS2203Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMinnesotaSouthFIPS2203Feet() {
    return NAD1983StatePlaneMinnesotaSouthFIPS2203Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMississippiEastFIPS2301Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMississippiEastFIPS2301Feet() {
    return NAD1983StatePlaneMississippiEastFIPS2301Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMississippiWestFIPS2302Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMississippiWestFIPS2302Feet() {
    return NAD1983StatePlaneMississippiWestFIPS2302Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMissouriCentralFIPS2402Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMissouriCentralFIPS2402Feet() {
    return NAD1983StatePlaneMissouriCentralFIPS2402Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMissouriEastFIPS2401Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMissouriEastFIPS2401Feet() {
    return NAD1983StatePlaneMissouriEastFIPS2401Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMissouriWestFIPS2403Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMissouriWestFIPS2403Feet() {
    return NAD1983StatePlaneMissouriWestFIPS2403Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneMontanaFIPS2500Feet
   */
  public ProjectionInfo getNAD1983StatePlaneMontanaFIPS2500Feet() {
    return NAD1983StatePlaneMontanaFIPS2500Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNebraskaFIPS2600Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNebraskaFIPS2600Feet() {
    return NAD1983StatePlaneNebraskaFIPS2600Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNevadaCentralFIPS2702Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNevadaCentralFIPS2702Feet() {
    return NAD1983StatePlaneNevadaCentralFIPS2702Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNevadaEastFIPS2701Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNevadaEastFIPS2701Feet() {
    return NAD1983StatePlaneNevadaEastFIPS2701Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNevadaWestFIPS2703Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNevadaWestFIPS2703Feet() {
    return NAD1983StatePlaneNevadaWestFIPS2703Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewHampshireFIPS2800Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNewHampshireFIPS2800Feet() {
    return NAD1983StatePlaneNewHampshireFIPS2800Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewJerseyFIPS2900Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNewJerseyFIPS2900Feet() {
    return NAD1983StatePlaneNewJerseyFIPS2900Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewMexicoCentralFIPS3002Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNewMexicoCentralFIPS3002Feet() {
    return NAD1983StatePlaneNewMexicoCentralFIPS3002Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewMexicoEastFIPS3001Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNewMexicoEastFIPS3001Feet() {
    return NAD1983StatePlaneNewMexicoEastFIPS3001Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewMexicoWestFIPS3003Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNewMexicoWestFIPS3003Feet() {
    return NAD1983StatePlaneNewMexicoWestFIPS3003Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewYorkCentralFIPS3102Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNewYorkCentralFIPS3102Feet() {
    return NAD1983StatePlaneNewYorkCentralFIPS3102Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewYorkEastFIPS3101Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNewYorkEastFIPS3101Feet() {
    return NAD1983StatePlaneNewYorkEastFIPS3101Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewYorkLongIslandFIPS3104Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNewYorkLongIslandFIPS3104Feet() {
    return NAD1983StatePlaneNewYorkLongIslandFIPS3104Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNewYorkWestFIPS3103Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNewYorkWestFIPS3103Feet() {
    return NAD1983StatePlaneNewYorkWestFIPS3103Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNorthCarolinaFIPS3200Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNorthCarolinaFIPS3200Feet() {
    return NAD1983StatePlaneNorthCarolinaFIPS3200Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNorthDakotaNorthFIPS3301Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNorthDakotaNorthFIPS3301Feet() {
    return NAD1983StatePlaneNorthDakotaNorthFIPS3301Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneNorthDakotaSouthFIPS3302Feet
   */
  public ProjectionInfo getNAD1983StatePlaneNorthDakotaSouthFIPS3302Feet() {
    return NAD1983StatePlaneNorthDakotaSouthFIPS3302Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneOhioNorthFIPS3401Feet
   */
  public ProjectionInfo getNAD1983StatePlaneOhioNorthFIPS3401Feet() {
    return NAD1983StatePlaneOhioNorthFIPS3401Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneOhioSouthFIPS3402Feet
   */
  public ProjectionInfo getNAD1983StatePlaneOhioSouthFIPS3402Feet() {
    return NAD1983StatePlaneOhioSouthFIPS3402Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneOklahomaNorthFIPS3501Feet
   */
  public ProjectionInfo getNAD1983StatePlaneOklahomaNorthFIPS3501Feet() {
    return NAD1983StatePlaneOklahomaNorthFIPS3501Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneOklahomaSouthFIPS3502Feet
   */
  public ProjectionInfo getNAD1983StatePlaneOklahomaSouthFIPS3502Feet() {
    return NAD1983StatePlaneOklahomaSouthFIPS3502Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneOregonNorthFIPS3601Feet
   */
  public ProjectionInfo getNAD1983StatePlaneOregonNorthFIPS3601Feet() {
    return NAD1983StatePlaneOregonNorthFIPS3601Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneOregonSouthFIPS3602Feet
   */
  public ProjectionInfo getNAD1983StatePlaneOregonSouthFIPS3602Feet() {
    return NAD1983StatePlaneOregonSouthFIPS3602Feet.copy();
  }

  /**
   * @return the NAD1983StatePlanePRVirginIslandsFIPS5200Feet
   */
  public ProjectionInfo getNAD1983StatePlanePRVirginIslandsFIPS5200Feet() {
    return NAD1983StatePlanePRVirginIslandsFIPS5200Feet.copy();
  }

  /**
   * @return the NAD1983StatePlanePennsylvaniaNorthFIPS3701Feet
   */
  public ProjectionInfo getNAD1983StatePlanePennsylvaniaNorthFIPS3701Feet() {
    return NAD1983StatePlanePennsylvaniaNorthFIPS3701Feet.copy();
  }

  /**
   * @return the NAD1983StatePlanePennsylvaniaSouthFIPS3702Feet
   */
  public ProjectionInfo getNAD1983StatePlanePennsylvaniaSouthFIPS3702Feet() {
    return NAD1983StatePlanePennsylvaniaSouthFIPS3702Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneRhodeIslandFIPS3800Feet
   */
  public ProjectionInfo getNAD1983StatePlaneRhodeIslandFIPS3800Feet() {
    return NAD1983StatePlaneRhodeIslandFIPS3800Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneSouthCarolinaFIPS3900Feet
   */
  public ProjectionInfo getNAD1983StatePlaneSouthCarolinaFIPS3900Feet() {
    return NAD1983StatePlaneSouthCarolinaFIPS3900Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneSouthDakotaNorthFIPS4001Feet
   */
  public ProjectionInfo getNAD1983StatePlaneSouthDakotaNorthFIPS4001Feet() {
    return NAD1983StatePlaneSouthDakotaNorthFIPS4001Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneSouthDakotaSouthFIPS4002Feet
   */
  public ProjectionInfo getNAD1983StatePlaneSouthDakotaSouthFIPS4002Feet() {
    return NAD1983StatePlaneSouthDakotaSouthFIPS4002Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneTennesseeFIPS4100Feet
   */
  public ProjectionInfo getNAD1983StatePlaneTennesseeFIPS4100Feet() {
    return NAD1983StatePlaneTennesseeFIPS4100Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneTexasCentralFIPS4203Feet
   */
  public ProjectionInfo getNAD1983StatePlaneTexasCentralFIPS4203Feet() {
    return NAD1983StatePlaneTexasCentralFIPS4203Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneTexasNorthCentralFIPS4202Feet
   */
  public ProjectionInfo getNAD1983StatePlaneTexasNorthCentralFIPS4202Feet() {
    return NAD1983StatePlaneTexasNorthCentralFIPS4202Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneTexasNorthFIPS4201Feet
   */
  public ProjectionInfo getNAD1983StatePlaneTexasNorthFIPS4201Feet() {
    return NAD1983StatePlaneTexasNorthFIPS4201Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneTexasSouthCentralFIPS4204Feet
   */
  public ProjectionInfo getNAD1983StatePlaneTexasSouthCentralFIPS4204Feet() {
    return NAD1983StatePlaneTexasSouthCentralFIPS4204Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneTexasSouthFIPS4205Feet
   */
  public ProjectionInfo getNAD1983StatePlaneTexasSouthFIPS4205Feet() {
    return NAD1983StatePlaneTexasSouthFIPS4205Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneUtahCentralFIPS4302Feet
   */
  public ProjectionInfo getNAD1983StatePlaneUtahCentralFIPS4302Feet() {
    return NAD1983StatePlaneUtahCentralFIPS4302Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneUtahNorthFIPS4301Feet
   */
  public ProjectionInfo getNAD1983StatePlaneUtahNorthFIPS4301Feet() {
    return NAD1983StatePlaneUtahNorthFIPS4301Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneUtahSouthFIPS4303Feet
   */
  public ProjectionInfo getNAD1983StatePlaneUtahSouthFIPS4303Feet() {
    return NAD1983StatePlaneUtahSouthFIPS4303Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneVermontFIPS4400Feet
   */
  public ProjectionInfo getNAD1983StatePlaneVermontFIPS4400Feet() {
    return NAD1983StatePlaneVermontFIPS4400Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneVirginiaNorthFIPS4501Feet
   */
  public ProjectionInfo getNAD1983StatePlaneVirginiaNorthFIPS4501Feet() {
    return NAD1983StatePlaneVirginiaNorthFIPS4501Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneVirginiaSouthFIPS4502Feet
   */
  public ProjectionInfo getNAD1983StatePlaneVirginiaSouthFIPS4502Feet() {
    return NAD1983StatePlaneVirginiaSouthFIPS4502Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWashingtonNorthFIPS4601Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWashingtonNorthFIPS4601Feet() {
    return NAD1983StatePlaneWashingtonNorthFIPS4601Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWashingtonSouthFIPS4602Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWashingtonSouthFIPS4602Feet() {
    return NAD1983StatePlaneWashingtonSouthFIPS4602Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWestVirginiaNorthFIPS4701Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWestVirginiaNorthFIPS4701Feet() {
    return NAD1983StatePlaneWestVirginiaNorthFIPS4701Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWestVirginiaSouthFIPS4702Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWestVirginiaSouthFIPS4702Feet() {
    return NAD1983StatePlaneWestVirginiaSouthFIPS4702Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWisconsinCentralFIPS4802Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWisconsinCentralFIPS4802Feet() {
    return NAD1983StatePlaneWisconsinCentralFIPS4802Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWisconsinNorthFIPS4801Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWisconsinNorthFIPS4801Feet() {
    return NAD1983StatePlaneWisconsinNorthFIPS4801Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWisconsinSouthFIPS4803Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWisconsinSouthFIPS4803Feet() {
    return NAD1983StatePlaneWisconsinSouthFIPS4803Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWyomingEastCentralFIPS4902Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWyomingEastCentralFIPS4902Feet() {
    return NAD1983StatePlaneWyomingEastCentralFIPS4902Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWyomingEastFIPS4901Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWyomingEastFIPS4901Feet() {
    return NAD1983StatePlaneWyomingEastFIPS4901Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWyomingWestCentralFIPS4903Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWyomingWestCentralFIPS4903Feet() {
    return NAD1983StatePlaneWyomingWestCentralFIPS4903Feet.copy();
  }

  /**
   * @return the NAD1983StatePlaneWyomingWestFIPS4904Feet
   */
  public ProjectionInfo getNAD1983StatePlaneWyomingWestFIPS4904Feet() {
    return NAD1983StatePlaneWyomingWestFIPS4904Feet.copy();
  }
}
