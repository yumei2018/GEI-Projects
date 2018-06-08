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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 5:03:19 PM
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
/// StatePlaneNad1983HarnFeet
/// </summary>

public class StatePlaneNad1983HarnFeet extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo NAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaIFIPS0401Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaVFIPS0405Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneColoradoCentralFIPS0502Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneColoradoNorthFIPS0501Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneColoradoSouthFIPS0503Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneConnecticutFIPS0600Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneDelawareFIPS0700Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneFloridaEastFIPS0901Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneFloridaNorthFIPS0903Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneFloridaWestFIPS0902Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneHawaii1FIPS5101Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneHawaii2FIPS5102Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneHawaii3FIPS5103Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneHawaii4FIPS5104Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneHawaii5FIPS5105Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneIdahoCentralFIPS1102Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneIdahoEastFIPS1101Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneIdahoWestFIPS1103Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneIndianaEastFIPS1301Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneIndianaWestFIPS1302Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneKentuckySouthFIPS1602Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneMarylandFIPS1900Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneMississippiEastFIPS2301Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneMississippiWestFIPS2302Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneMontanaFIPS2500FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneNewYorkEastFIPS3101Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneNewYorkWestFIPS3103Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneTennesseeFIPS4100Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneTexasCentralFIPS4203Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneTexasNorthFIPS4201Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneTexasSouthFIPS4205Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl;
  private final ProjectionInfo NAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet;
  private final ProjectionInfo NAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet;

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of StatePlaneNad1983HarnFeet
   */
  public StatePlaneNad1983HarnFeet() {
    NAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-111.9166666666667 +k=0.999900 +x_0=213360 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-110.1666666666667 +k=0.999900 +x_0=213360 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-113.75 +k=0.999933 +x_0=213360 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaIFIPS0401Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40 +lat_2=41.66666666666666 +lat_0=39.33333333333334 +lon_0=-122 +x_0=2000000 +y_0=500000.0000000001 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.33333333333334 +lat_2=39.83333333333334 +lat_0=37.66666666666666 +lon_0=-122 +x_0=2000000 +y_0=500000.0000000001 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.06666666666667 +lat_2=38.43333333333333 +lat_0=36.5 +lon_0=-120.5 +x_0=2000000 +y_0=500000.0000000001 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36 +lat_2=37.25 +lat_0=35.33333333333334 +lon_0=-119 +x_0=2000000 +y_0=500000.0000000001 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaVFIPS0405Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.03333333333333 +lat_2=35.46666666666667 +lat_0=33.5 +lon_0=-118 +x_0=2000000 +y_0=500000.0000000001 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.78333333333333 +lat_2=33.88333333333333 +lat_0=32.16666666666666 +lon_0=-116.25 +x_0=2000000 +y_0=500000.0000000001 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneColoradoCentralFIPS0502Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.45 +lat_2=39.75 +lat_0=37.83333333333334 +lon_0=-105.5 +x_0=914401.8288999999 +y_0=304800.6096 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneColoradoNorthFIPS0501Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.71666666666667 +lat_2=40.78333333333333 +lat_0=39.33333333333334 +lon_0=-105.5 +x_0=914401.8288999999 +y_0=304800.6096 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneColoradoSouthFIPS0503Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.23333333333333 +lat_2=38.43333333333333 +lat_0=36.66666666666666 +lon_0=-105.5 +x_0=914401.8288999999 +y_0=304800.6096 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneConnecticutFIPS0600Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.2 +lat_2=41.86666666666667 +lat_0=40.83333333333334 +lon_0=-72.75 +x_0=304800.6096 +y_0=152400.3048 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneDelawareFIPS0700Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38 +lon_0=-75.41666666666667 +k=0.999995 +x_0=199999.9999999999 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneFloridaEastFIPS0901Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=24.33333333333333 +lon_0=-81 +k=0.999941 +x_0=199999.9999999999 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneFloridaNorthFIPS0903Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=29.58333333333333 +lat_2=30.75 +lat_0=29 +lon_0=-84.5 +x_0=600000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneFloridaWestFIPS0902Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=24.33333333333333 +lon_0=-82 +k=0.999941 +x_0=199999.9999999999 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-82.16666666666667 +k=0.999900 +x_0=199999.9999999999 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=-84.16666666666667 +k=0.999900 +x_0=699999.9999999999 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneHawaii1FIPS5101Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=18.83333333333333 +lon_0=-155.5 +k=0.999967 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneHawaii2FIPS5102Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=20.33333333333333 +lon_0=-156.6666666666667 +k=0.999967 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneHawaii3FIPS5103Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.16666666666667 +lon_0=-158 +k=0.999990 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneHawaii4FIPS5104Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.83333333333333 +lon_0=-159.5 +k=0.999990 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneHawaii5FIPS5105Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.66666666666667 +lon_0=-160.1666666666667 +k=1.000000 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIdahoCentralFIPS1102Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-114 +k=0.999947 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIdahoEastFIPS1101Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-112.1666666666667 +k=0.999947 +x_0=199999.9999999999 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIdahoWestFIPS1103Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.66666666666666 +lon_0=-115.75 +k=0.999933 +x_0=799999.9999999998 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIndianaEastFIPS1301Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=37.5 +lon_0=-85.66666666666667 +k=0.999967 +x_0=99999.99999999999 +y_0=250000 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneIndianaWestFIPS1302Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=37.5 +lon_0=-87.08333333333333 +k=0.999967 +x_0=900000 +y_0=250000 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.96666666666667 +lat_2=38.96666666666667 +lat_0=37.5 +lon_0=-84.25 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneKentuckySouthFIPS1602Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36.73333333333333 +lat_2=37.93333333333333 +lat_0=36.33333333333334 +lon_0=-85.75 +x_0=500000.0000000001 +y_0=500000.0000000001 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMarylandFIPS1900Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.3 +lat_2=39.45 +lat_0=37.66666666666666 +lon_0=-77 +x_0=399999.9999999999 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.28333333333333 +lat_2=41.48333333333333 +lat_0=41 +lon_0=-70.5 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=41.71666666666667 +lat_2=42.68333333333333 +lat_0=41 +lon_0=-71.5 +x_0=199999.9999999999 +y_0=750000 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.18333333333333 +lat_2=45.7 +lat_0=43.31666666666667 +lon_0=-84.36666666666666 +x_0=6000000 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.48333333333333 +lat_2=47.08333333333334 +lat_0=44.78333333333333 +lon_0=-87 +x_0=7999999.999999998 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.1 +lat_2=43.66666666666666 +lat_0=41.5 +lon_0=-84.36666666666666 +x_0=3999999.999999999 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMississippiEastFIPS2301Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=29.5 +lon_0=-88.83333333333333 +k=0.999950 +x_0=300000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMississippiWestFIPS2302Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=29.5 +lon_0=-90.33333333333333 +k=0.999950 +x_0=699999.9999999999 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneMontanaFIPS2500FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45 +lat_2=49 +lat_0=44.25 +lon_0=-109.5 +x_0=600000 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-106.25 +k=0.999900 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-104.3333333333333 +k=0.999909 +x_0=165000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-107.8333333333333 +k=0.999917 +x_0=829999.9999999998 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40 +lon_0=-76.58333333333333 +k=0.999938 +x_0=250000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewYorkEastFIPS3101Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38.83333333333334 +lon_0=-74.5 +k=0.999900 +x_0=150000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.66666666666666 +lat_2=41.03333333333333 +lat_0=40.16666666666666 +lon_0=-74 +x_0=300000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNewYorkWestFIPS3103Feet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=40 +lon_0=-78.58333333333333 +k=0.999938 +x_0=350000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.43333333333333 +lat_2=48.73333333333333 +lat_0=47 +lon_0=-100.5 +x_0=600000 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.18333333333333 +lat_2=47.48333333333333 +lat_0=45.66666666666666 +lon_0=-100.5 +x_0=600000 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=35.56666666666667 +lat_2=36.76666666666667 +lat_0=35 +lon_0=-98 +x_0=600000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.93333333333333 +lat_2=35.23333333333333 +lat_0=33.33333333333334 +lon_0=-98 +x_0=600000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.33333333333334 +lat_2=46 +lat_0=43.66666666666666 +lon_0=-120.5 +x_0=2500000 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.33333333333334 +lat_2=44 +lat_0=41.66666666666666 +lon_0=-120.5 +x_0=1500000 +y_0=0 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTennesseeFIPS4100Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=35.25 +lat_2=36.41666666666666 +lat_0=34.33333333333334 +lon_0=-86 +x_0=600000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTexasCentralFIPS4203Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=30.11666666666667 +lat_2=31.88333333333333 +lat_0=29.66666666666667 +lon_0=-100.3333333333333 +x_0=699999.9999999999 +y_0=3000000 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.13333333333333 +lat_2=33.96666666666667 +lat_0=31.66666666666667 +lon_0=-98.5 +x_0=600000 +y_0=2000000 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTexasNorthFIPS4201Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.65 +lat_2=36.18333333333333 +lat_0=34 +lon_0=-101.5 +x_0=199999.9999999999 +y_0=999999.9999999999 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=28.38333333333333 +lat_2=30.28333333333333 +lat_0=27.83333333333333 +lon_0=-99 +x_0=600000 +y_0=3999999.999999999 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneTexasSouthFIPS4205Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26.16666666666667 +lat_2=27.83333333333333 +lat_0=25.66666666666667 +lon_0=-98.5 +x_0=300000 +y_0=4999999.999999998 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.01666666666667 +lat_2=40.65 +lat_0=38.33333333333334 +lon_0=-111.5 +x_0=499999.9999999998 +y_0=2000000 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.71666666666667 +lat_2=41.78333333333333 +lat_0=40.33333333333334 +lon_0=-111.5 +x_0=499999.9999999998 +y_0=999999.9999999999 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.21666666666667 +lat_2=38.35 +lat_0=36.66666666666666 +lon_0=-111.5 +x_0=499999.9999999998 +y_0=3000000 +ellps=GRS80 +to_meter=0.3048 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=38.03333333333333 +lat_2=39.2 +lat_0=37.66666666666666 +lon_0=-78.5 +x_0=3499999.999999998 +y_0=2000000 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36.76666666666667 +lat_2=37.96666666666667 +lat_0=36.33333333333334 +lon_0=-78.5 +x_0=3499999.999999998 +y_0=999999.9999999999 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.5 +lat_2=48.73333333333333 +lat_0=47 +lon_0=-120.8333333333333 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.83333333333334 +lat_2=47.33333333333334 +lat_0=45.33333333333334 +lon_0=-120.5 +x_0=500000.0000000001 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.25 +lat_2=45.5 +lat_0=43.83333333333334 +lon_0=-90 +x_0=600000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.56666666666667 +lat_2=46.76666666666667 +lat_0=45.16666666666666 +lon_0=-90 +x_0=600000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
    NAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.73333333333333 +lat_2=44.06666666666667 +lat_0=42 +lon_0=-90 +x_0=600000 +y_0=0 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);

    NAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl.setName("NAD_1983_HARN_StatePlane_Arizona_Central_FIPS_0202_Feet_Intl");
    NAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl.setName("NAD_1983_HARN_StatePlane_Arizona_East_FIPS_0201_Feet_Intl");
    NAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl.setName("NAD_1983_HARN_StatePlane_Arizona_West_FIPS_0203_Feet_Intl");
    NAD1983HARNStatePlaneCaliforniaIFIPS0401Feet.setName("NAD_1983_HARN_StatePlane_California_I_FIPS_0401_Feet");
    NAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet.setName("NAD_1983_HARN_StatePlane_California_II_FIPS_0402_Feet");
    NAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet.setName("NAD_1983_HARN_StatePlane_California_III_FIPS_0403_Feet");
    NAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet.setName("NAD_1983_HARN_StatePlane_California_IV_FIPS_0404_Feet");
    NAD1983HARNStatePlaneCaliforniaVFIPS0405Feet.setName("NAD_1983_HARN_StatePlane_California_V_FIPS_0405_Feet");
    NAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet.setName("NAD_1983_HARN_StatePlane_California_VI_FIPS_0406_Feet");
    NAD1983HARNStatePlaneColoradoCentralFIPS0502Feet.setName("NAD_1983_HARN_StatePlane_Colorado_Central_FIPS_0502_Feet");
    NAD1983HARNStatePlaneColoradoNorthFIPS0501Feet.setName("NAD_1983_HARN_StatePlane_Colorado_North_FIPS_0501_Feet");
    NAD1983HARNStatePlaneColoradoSouthFIPS0503Feet.setName("NAD_1983_HARN_StatePlane_Colorado_South_FIPS_0503_Feet");
    NAD1983HARNStatePlaneConnecticutFIPS0600Feet.setName("NAD_1983_HARN_StatePlane_Connecticut_FIPS_0600_Feet");
    NAD1983HARNStatePlaneDelawareFIPS0700Feet.setName("NAD_1983_HARN_StatePlane_Delaware_FIPS_0700_Feet");
    NAD1983HARNStatePlaneFloridaEastFIPS0901Feet.setName("NAD_1983_HARN_StatePlane_Florida_East_FIPS_0901_Feet");
    NAD1983HARNStatePlaneFloridaNorthFIPS0903Feet.setName("NAD_1983_HARN_StatePlane_Florida_North_FIPS_0903_Feet");
    NAD1983HARNStatePlaneFloridaWestFIPS0902Feet.setName("NAD_1983_HARN_StatePlane_Florida_West_FIPS_0902_Feet");
    NAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet.setName("NAD_1983_HARN_StatePlane_Georgia_East_FIPS_1001_Feet");
    NAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet.setName("NAD_1983_HARN_StatePlane_Georgia_West_FIPS_1002_Feet");
    NAD1983HARNStatePlaneHawaii1FIPS5101Feet.setName("NAD_1983_HARN_StatePlane_Hawaii_1_FIPS_5101_Feet");
    NAD1983HARNStatePlaneHawaii2FIPS5102Feet.setName("NAD_1983_HARN_StatePlane_Hawaii_2_FIPS_5102_Feet");
    NAD1983HARNStatePlaneHawaii3FIPS5103Feet.setName("NAD_1983_HARN_StatePlane_Hawaii_3_FIPS_5103_Feet");
    NAD1983HARNStatePlaneHawaii4FIPS5104Feet.setName("NAD_1983_HARN_StatePlane_Hawaii_4_FIPS_5104_Feet");
    NAD1983HARNStatePlaneHawaii5FIPS5105Feet.setName("NAD_1983_HARN_StatePlane_Hawaii_5_FIPS_5105_Feet");
    NAD1983HARNStatePlaneIdahoCentralFIPS1102Feet.setName("NAD_1983_HARN_StatePlane_Idaho_Central_FIPS_1102_Feet");
    NAD1983HARNStatePlaneIdahoEastFIPS1101Feet.setName("NAD_1983_HARN_StatePlane_Idaho_East_FIPS_1101_Feet");
    NAD1983HARNStatePlaneIdahoWestFIPS1103Feet.setName("NAD_1983_HARN_StatePlane_Idaho_West_FIPS_1103_Feet");
    NAD1983HARNStatePlaneIndianaEastFIPS1301Feet.setName("NAD_1983_HARN_StatePlane_Indiana_East_FIPS_1301_Feet");
    NAD1983HARNStatePlaneIndianaWestFIPS1302Feet.setName("NAD_1983_HARN_StatePlane_Indiana_West_FIPS_1302_Feet");
    NAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet.setName("NAD_1983_HARN_StatePlane_Kentucky_North_FIPS_1601_Feet");
    NAD1983HARNStatePlaneKentuckySouthFIPS1602Feet.setName("NAD_1983_HARN_StatePlane_Kentucky_South_FIPS_1602_Feet");
    NAD1983HARNStatePlaneMarylandFIPS1900Feet.setName("NAD_1983_HARN_StatePlane_Maryland_FIPS_1900_Feet");
    NAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet.setName("NAD_1983_HARN_StatePlane_Massachusetts_Island_FIPS_2002_Feet");
    NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet.setName("NAD_1983_HARN_StatePlane_Massachusetts_Mainland_FIPS_2001_Feet");
    NAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl.setName("NAD_1983_HARN_StatePlane_Michigan_Central_FIPS_2112_Feet_Intl");
    NAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl.setName("NAD_1983_HARN_StatePlane_Michigan_North_FIPS_2111_Feet_Intl");
    NAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl.setName("NAD_1983_HARN_StatePlane_Michigan_South_FIPS_2113_Feet_Intl");
    NAD1983HARNStatePlaneMississippiEastFIPS2301Feet.setName("NAD_1983_HARN_StatePlane_Mississippi_East_FIPS_2301_Feet");
    NAD1983HARNStatePlaneMississippiWestFIPS2302Feet.setName("NAD_1983_HARN_StatePlane_Mississippi_West_FIPS_2302_Feet");
    NAD1983HARNStatePlaneMontanaFIPS2500FeetIntl.setName("NAD_1983_HARN_StatePlane_Montana_FIPS_2500_Feet_Intl");
    NAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet.setName("NAD_1983_HARN_StatePlane_New_Mexico_Central_FIPS_3002_Feet");
    NAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet.setName("NAD_1983_HARN_StatePlane_New_Mexico_East_FIPS_3001_Feet");
    NAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet.setName("NAD_1983_HARN_StatePlane_New_Mexico_West_FIPS_3003_Feet");
    NAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet.setName("NAD_1983_HARN_StatePlane_New_York_Central_FIPS_3102_Feet");
    NAD1983HARNStatePlaneNewYorkEastFIPS3101Feet.setName("NAD_1983_HARN_StatePlane_New_York_East_FIPS_3101_Feet");
    NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet.setName("NAD_1983_HARN_StatePlane_New_York_Long_Island_FIPS_3104_Feet");
    NAD1983HARNStatePlaneNewYorkWestFIPS3103Feet.setName("NAD_1983_HARN_StatePlane_New_York_West_FIPS_3103_Feet");
    NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl.setName("NAD_1983_HARN_StatePlane_North_Dakota_North_FIPS_3301_Feet_Intl");
    NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl.setName("NAD_1983_HARN_StatePlane_North_Dakota_South_FIPS_3302_Feet_Intl");
    NAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet.setName("NAD_1983_HARN_StatePlane_Oklahoma_North_FIPS_3501_Feet");
    NAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet.setName("NAD_1983_HARN_StatePlane_Oklahoma_South_FIPS_3502_Feet");
    NAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl.setName("NAD_1983_HARN_StatePlane_Oregon_North_FIPS_3601_Feet_Intl");
    NAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl.setName("NAD_1983_HARN_StatePlane_Oregon_South_FIPS_3602_Feet_Intl");
    NAD1983HARNStatePlaneTennesseeFIPS4100Feet.setName("NAD_1983_HARN_StatePlane_Tennessee_FIPS_4100_Feet");
    NAD1983HARNStatePlaneTexasCentralFIPS4203Feet.setName("NAD_1983_HARN_StatePlane_Texas_Central_FIPS_4203_Feet");
    NAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet.setName("NAD_1983_HARN_StatePlane_Texas_North_Central_FIPS_4202_Feet");
    NAD1983HARNStatePlaneTexasNorthFIPS4201Feet.setName("NAD_1983_HARN_StatePlane_Texas_North_FIPS_4201_Feet");
    NAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet.setName("NAD_1983_HARN_StatePlane_Texas_South_Central_FIPS_4204_Feet");
    NAD1983HARNStatePlaneTexasSouthFIPS4205Feet.setName("NAD_1983_HARN_StatePlane_Texas_South_FIPS_4205_Feet");
    NAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl.setName("NAD_1983_HARN_StatePlane_Utah_Central_FIPS_4302_Feet_Intl");
    NAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl.setName("NAD_1983_HARN_StatePlane_Utah_North_FIPS_4301_Feet_Intl");
    NAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl.setName("NAD_1983_HARN_StatePlane_Utah_South_FIPS_4303_Feet_Intl");
    NAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet.setName("NAD_1983_HARN_StatePlane_Virginia_North_FIPS_4501_Feet");
    NAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet.setName("NAD_1983_HARN_StatePlane_Virginia_South_FIPS_4502_Feet");
    NAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet.setName("NAD_1983_HARN_StatePlane_Washington_North_FIPS_4601_Feet");
    NAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet.setName("NAD_1983_HARN_StatePlane_Washington_South_FIPS_4602_Feet");
    NAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet.setName("NAD_1983_HARN_StatePlane_Wisconsin_Central_FIPS_4802_Feet");
    NAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet.setName("NAD_1983_HARN_StatePlane_Wisconsin_North_FIPS_4801_Feet");
    NAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet.setName("NAD_1983_HARN_StatePlane_Wisconsin_South_FIPS_4803_Feet");

    NAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIFIPS0401Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaVFIPS0405Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoCentralFIPS0502Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoNorthFIPS0501Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoSouthFIPS0503Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneConnecticutFIPS0600Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneDelawareFIPS0700Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaEastFIPS0901Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaNorthFIPS0903Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaWestFIPS0902Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii1FIPS5101Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii2FIPS5102Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii3FIPS5103Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii4FIPS5104Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii5FIPS5105Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoCentralFIPS1102Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoEastFIPS1101Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoWestFIPS1103Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIndianaEastFIPS1301Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneIndianaWestFIPS1302Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneKentuckySouthFIPS1602Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMarylandFIPS1900Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMississippiEastFIPS2301Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMississippiWestFIPS2302Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneMontanaFIPS2500FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkEastFIPS3101Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkWestFIPS3103Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTennesseeFIPS4100Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasCentralFIPS4203Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasNorthFIPS4201Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasSouthFIPS4205Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet.getGeographicInfo().setName("GCS_North_American_1983_HARN");

    NAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIFIPS0401Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaVFIPS0405Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoCentralFIPS0502Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoNorthFIPS0501Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneColoradoSouthFIPS0503Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneConnecticutFIPS0600Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneDelawareFIPS0700Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaEastFIPS0901Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaNorthFIPS0903Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneFloridaWestFIPS0902Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii1FIPS5101Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii2FIPS5102Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii3FIPS5103Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii4FIPS5104Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneHawaii5FIPS5105Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoCentralFIPS1102Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoEastFIPS1101Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIdahoWestFIPS1103Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIndianaEastFIPS1301Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneIndianaWestFIPS1302Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneKentuckySouthFIPS1602Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMarylandFIPS1900Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMississippiEastFIPS2301Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMississippiWestFIPS2302Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneMontanaFIPS2500FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkEastFIPS3101Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNewYorkWestFIPS3103Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTennesseeFIPS4100Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasCentralFIPS4203Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasNorthFIPS4201Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneTexasSouthFIPS4205Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
    NAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
  }

  //</editor-fold>

  /**
   * @return the NAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl() {
    return NAD1983HARNStatePlaneArizonaCentralFIPS0202FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl() {
    return NAD1983HARNStatePlaneArizonaEastFIPS0201FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl() {
    return NAD1983HARNStatePlaneArizonaWestFIPS0203FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaIFIPS0401Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaIFIPS0401Feet() {
    return NAD1983HARNStatePlaneCaliforniaIFIPS0401Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet() {
    return NAD1983HARNStatePlaneCaliforniaIIFIPS0402Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet() {
    return NAD1983HARNStatePlaneCaliforniaIIIFIPS0403Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet() {
    return NAD1983HARNStatePlaneCaliforniaIVFIPS0404Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaVFIPS0405Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaVFIPS0405Feet() {
    return NAD1983HARNStatePlaneCaliforniaVFIPS0405Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet() {
    return NAD1983HARNStatePlaneCaliforniaVIFIPS0406Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneColoradoCentralFIPS0502Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneColoradoCentralFIPS0502Feet() {
    return NAD1983HARNStatePlaneColoradoCentralFIPS0502Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneColoradoNorthFIPS0501Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneColoradoNorthFIPS0501Feet() {
    return NAD1983HARNStatePlaneColoradoNorthFIPS0501Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneColoradoSouthFIPS0503Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneColoradoSouthFIPS0503Feet() {
    return NAD1983HARNStatePlaneColoradoSouthFIPS0503Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneConnecticutFIPS0600Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneConnecticutFIPS0600Feet() {
    return NAD1983HARNStatePlaneConnecticutFIPS0600Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneDelawareFIPS0700Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneDelawareFIPS0700Feet() {
    return NAD1983HARNStatePlaneDelawareFIPS0700Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneFloridaEastFIPS0901Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneFloridaEastFIPS0901Feet() {
    return NAD1983HARNStatePlaneFloridaEastFIPS0901Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneFloridaNorthFIPS0903Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneFloridaNorthFIPS0903Feet() {
    return NAD1983HARNStatePlaneFloridaNorthFIPS0903Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneFloridaWestFIPS0902Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneFloridaWestFIPS0902Feet() {
    return NAD1983HARNStatePlaneFloridaWestFIPS0902Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet() {
    return NAD1983HARNStatePlaneGeorgiaEastFIPS1001Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet() {
    return NAD1983HARNStatePlaneGeorgiaWestFIPS1002Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneHawaii1FIPS5101Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneHawaii1FIPS5101Feet() {
    return NAD1983HARNStatePlaneHawaii1FIPS5101Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneHawaii2FIPS5102Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneHawaii2FIPS5102Feet() {
    return NAD1983HARNStatePlaneHawaii2FIPS5102Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneHawaii3FIPS5103Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneHawaii3FIPS5103Feet() {
    return NAD1983HARNStatePlaneHawaii3FIPS5103Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneHawaii4FIPS5104Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneHawaii4FIPS5104Feet() {
    return NAD1983HARNStatePlaneHawaii4FIPS5104Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneHawaii5FIPS5105Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneHawaii5FIPS5105Feet() {
    return NAD1983HARNStatePlaneHawaii5FIPS5105Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIdahoCentralFIPS1102Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIdahoCentralFIPS1102Feet() {
    return NAD1983HARNStatePlaneIdahoCentralFIPS1102Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIdahoEastFIPS1101Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIdahoEastFIPS1101Feet() {
    return NAD1983HARNStatePlaneIdahoEastFIPS1101Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIdahoWestFIPS1103Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIdahoWestFIPS1103Feet() {
    return NAD1983HARNStatePlaneIdahoWestFIPS1103Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIndianaEastFIPS1301Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIndianaEastFIPS1301Feet() {
    return NAD1983HARNStatePlaneIndianaEastFIPS1301Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneIndianaWestFIPS1302Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneIndianaWestFIPS1302Feet() {
    return NAD1983HARNStatePlaneIndianaWestFIPS1302Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet() {
    return NAD1983HARNStatePlaneKentuckyNorthFIPS1601Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneKentuckySouthFIPS1602Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneKentuckySouthFIPS1602Feet() {
    return NAD1983HARNStatePlaneKentuckySouthFIPS1602Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMarylandFIPS1900Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMarylandFIPS1900Feet() {
    return NAD1983HARNStatePlaneMarylandFIPS1900Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet() {
    return NAD1983HARNStatePlaneMassachusettsIslandFIPS2002Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet() {
    return NAD1983HARNStatePlaneMassachusettsMainlandFIPS2001Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl() {
    return NAD1983HARNStatePlaneMichiganCentralFIPS2112FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl() {
    return NAD1983HARNStatePlaneMichiganNorthFIPS2111FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl() {
    return NAD1983HARNStatePlaneMichiganSouthFIPS2113FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMississippiEastFIPS2301Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMississippiEastFIPS2301Feet() {
    return NAD1983HARNStatePlaneMississippiEastFIPS2301Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMississippiWestFIPS2302Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMississippiWestFIPS2302Feet() {
    return NAD1983HARNStatePlaneMississippiWestFIPS2302Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneMontanaFIPS2500FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneMontanaFIPS2500FeetIntl() {
    return NAD1983HARNStatePlaneMontanaFIPS2500FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet() {
    return NAD1983HARNStatePlaneNewMexicoCentralFIPS3002Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet() {
    return NAD1983HARNStatePlaneNewMexicoEastFIPS3001Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet() {
    return NAD1983HARNStatePlaneNewMexicoWestFIPS3003Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet() {
    return NAD1983HARNStatePlaneNewYorkCentralFIPS3102Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewYorkEastFIPS3101Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewYorkEastFIPS3101Feet() {
    return NAD1983HARNStatePlaneNewYorkEastFIPS3101Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet() {
    return NAD1983HARNStatePlaneNewYorkLongIslandFIPS3104Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNewYorkWestFIPS3103Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNewYorkWestFIPS3103Feet() {
    return NAD1983HARNStatePlaneNewYorkWestFIPS3103Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl() {
    return NAD1983HARNStatePlaneNorthDakotaNorthFIPS3301FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl() {
    return NAD1983HARNStatePlaneNorthDakotaSouthFIPS3302FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet() {
    return NAD1983HARNStatePlaneOklahomaNorthFIPS3501Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet() {
    return NAD1983HARNStatePlaneOklahomaSouthFIPS3502Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl() {
    return NAD1983HARNStatePlaneOregonNorthFIPS3601FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl() {
    return NAD1983HARNStatePlaneOregonSouthFIPS3602FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTennesseeFIPS4100Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTennesseeFIPS4100Feet() {
    return NAD1983HARNStatePlaneTennesseeFIPS4100Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTexasCentralFIPS4203Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTexasCentralFIPS4203Feet() {
    return NAD1983HARNStatePlaneTexasCentralFIPS4203Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet() {
    return NAD1983HARNStatePlaneTexasNorthCentralFIPS4202Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTexasNorthFIPS4201Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTexasNorthFIPS4201Feet() {
    return NAD1983HARNStatePlaneTexasNorthFIPS4201Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet() {
    return NAD1983HARNStatePlaneTexasSouthCentralFIPS4204Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneTexasSouthFIPS4205Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneTexasSouthFIPS4205Feet() {
    return NAD1983HARNStatePlaneTexasSouthFIPS4205Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl() {
    return NAD1983HARNStatePlaneUtahCentralFIPS4302FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl() {
    return NAD1983HARNStatePlaneUtahNorthFIPS4301FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl
   */
  public ProjectionInfo getNAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl() {
    return NAD1983HARNStatePlaneUtahSouthFIPS4303FeetIntl.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet() {
    return NAD1983HARNStatePlaneVirginiaNorthFIPS4501Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet() {
    return NAD1983HARNStatePlaneVirginiaSouthFIPS4502Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet() {
    return NAD1983HARNStatePlaneWashingtonNorthFIPS4601Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet() {
    return NAD1983HARNStatePlaneWashingtonSouthFIPS4602Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet() {
    return NAD1983HARNStatePlaneWisconsinCentralFIPS4802Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet() {
    return NAD1983HARNStatePlaneWisconsinNorthFIPS4801Feet.copy();
  }

  /**
   * @return the NAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet
   */
  public ProjectionInfo getNAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet() {
    return NAD1983HARNStatePlaneWisconsinSouthFIPS4803Feet.copy();
  }
}
