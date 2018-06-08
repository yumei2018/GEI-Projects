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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:33:59 PM
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
     *  Wisconsin.
     * @author Harold A. Dunsford Jr. Ph.D.
     */
    public class Wisconsin extends CoordinateSystemCategory
    {
        //<editor-fold defaultstate="collapsed" desc="Fields">

        private final ProjectionInfo NAD1983HARNAdjWIAdamsFeet;
      private final ProjectionInfo NAD1983HARNAdjWIAdamsMeters;
      private final ProjectionInfo NAD1983HARNAdjWIAshlandFeet;
      private final ProjectionInfo NAD1983HARNAdjWIAshlandMeters;
      private final ProjectionInfo NAD1983HARNAdjWIBarronFeet;
      private final ProjectionInfo NAD1983HARNAdjWIBarronMeters;
      private final ProjectionInfo NAD1983HARNAdjWIBayfieldFeet;
      private final ProjectionInfo NAD1983HARNAdjWIBayfieldMeters;
      private final ProjectionInfo NAD1983HARNAdjWIBrownFeet;
      private final ProjectionInfo NAD1983HARNAdjWIBrownMeters;
      private final ProjectionInfo NAD1983HARNAdjWIBuffaloFeet;
      private final ProjectionInfo NAD1983HARNAdjWIBuffaloMeters;
      private final ProjectionInfo NAD1983HARNAdjWIBurnettFeet;
      private final ProjectionInfo NAD1983HARNAdjWIBurnettMeters;
      private final ProjectionInfo NAD1983HARNAdjWICalumetFeet;
      private final ProjectionInfo NAD1983HARNAdjWICalumetMeters;
      private final ProjectionInfo NAD1983HARNAdjWIChippewaFeet;
      private final ProjectionInfo NAD1983HARNAdjWIChippewaMeters;
      private final ProjectionInfo NAD1983HARNAdjWIClarkFeet;
      private final ProjectionInfo NAD1983HARNAdjWIClarkMeters;
      private final ProjectionInfo NAD1983HARNAdjWIColumbiaFeet;
      private final ProjectionInfo NAD1983HARNAdjWIColumbiaMeters;
      private final ProjectionInfo NAD1983HARNAdjWICrawfordFeet;
      private final ProjectionInfo NAD1983HARNAdjWICrawfordMeters;
      private final ProjectionInfo NAD1983HARNAdjWIDaneFeet;
      private final ProjectionInfo NAD1983HARNAdjWIDaneMeters;
      private final ProjectionInfo NAD1983HARNAdjWIDodgeFeet;
      private final ProjectionInfo NAD1983HARNAdjWIDodgeMeters;
      private final ProjectionInfo NAD1983HARNAdjWIDoorFeet;
      private final ProjectionInfo NAD1983HARNAdjWIDoorMeters;
      private final ProjectionInfo NAD1983HARNAdjWIDouglasFeet;
      private final ProjectionInfo NAD1983HARNAdjWIDouglasMeters;
      private final ProjectionInfo NAD1983HARNAdjWIDunnFeet;
      private final ProjectionInfo NAD1983HARNAdjWIDunnMeters;
      private final ProjectionInfo NAD1983HARNAdjWIEauClaireFeet;
      private final ProjectionInfo NAD1983HARNAdjWIEauClaireMeters;
      private final ProjectionInfo NAD1983HARNAdjWIFlorenceFeet;
      private final ProjectionInfo NAD1983HARNAdjWIFlorenceMeters;
      private final ProjectionInfo NAD1983HARNAdjWIFondduLacFeet;
      private final ProjectionInfo NAD1983HARNAdjWIFondduLacMeters;
      private final ProjectionInfo NAD1983HARNAdjWIForestFeet;
      private final ProjectionInfo NAD1983HARNAdjWIForestMeters;
      private final ProjectionInfo NAD1983HARNAdjWIGrantFeet;
      private final ProjectionInfo NAD1983HARNAdjWIGrantMeters;
      private final ProjectionInfo NAD1983HARNAdjWIGreenFeet;
      private final ProjectionInfo NAD1983HARNAdjWIGreenLakeFeet;
      private final ProjectionInfo NAD1983HARNAdjWIGreenLakeMeters;
      private final ProjectionInfo NAD1983HARNAdjWIGreenMeters;
      private final ProjectionInfo NAD1983HARNAdjWIIowaFeet;
      private final ProjectionInfo NAD1983HARNAdjWIIowaMeters;
      private final ProjectionInfo NAD1983HARNAdjWIIronFeet;
      private final ProjectionInfo NAD1983HARNAdjWIIronMeters;
      private final ProjectionInfo NAD1983HARNAdjWIJacksonFeet;
      private final ProjectionInfo NAD1983HARNAdjWIJacksonMeters;
      private final ProjectionInfo NAD1983HARNAdjWIJeffersonFeet;
      private final ProjectionInfo NAD1983HARNAdjWIJeffersonMeters;
      private final ProjectionInfo NAD1983HARNAdjWIJuneauFeet;
      private final ProjectionInfo NAD1983HARNAdjWIJuneauMeters;
      private final ProjectionInfo NAD1983HARNAdjWIKenoshaFeet;
      private final ProjectionInfo NAD1983HARNAdjWIKenoshaMeters;
      private final ProjectionInfo NAD1983HARNAdjWIKewauneeFeet;
      private final ProjectionInfo NAD1983HARNAdjWIKewauneeMeters;
      private final ProjectionInfo NAD1983HARNAdjWILaCrosseFeet;
      private final ProjectionInfo NAD1983HARNAdjWILaCrosseMeters;
      private final ProjectionInfo NAD1983HARNAdjWILafayetteFeet;
      private final ProjectionInfo NAD1983HARNAdjWILafayetteMeters;
      private final ProjectionInfo NAD1983HARNAdjWILangladeFeet;
      private final ProjectionInfo NAD1983HARNAdjWILangladeMeters;
      private final ProjectionInfo NAD1983HARNAdjWILincolnFeet;
      private final ProjectionInfo NAD1983HARNAdjWILincolnMeters;
      private final ProjectionInfo NAD1983HARNAdjWIManitowocFeet;
      private final ProjectionInfo NAD1983HARNAdjWIManitowocMeters;
      private final ProjectionInfo NAD1983HARNAdjWIMarathonFeet;
      private final ProjectionInfo NAD1983HARNAdjWIMarathonMeters;
      private final ProjectionInfo NAD1983HARNAdjWIMarinetteFeet;
      private final ProjectionInfo NAD1983HARNAdjWIMarinetteMeters;
      private final ProjectionInfo NAD1983HARNAdjWIMarquetteFeet;
      private final ProjectionInfo NAD1983HARNAdjWIMarquetteMeters;
      private final ProjectionInfo NAD1983HARNAdjWIMenomineeFeet;
      private final ProjectionInfo NAD1983HARNAdjWIMenomineeMeters;
      private final ProjectionInfo NAD1983HARNAdjWIMilwaukeeFeet;
      private final ProjectionInfo NAD1983HARNAdjWIMilwaukeeMeters;
      private final ProjectionInfo NAD1983HARNAdjWIMonroeFeet;
      private final ProjectionInfo NAD1983HARNAdjWIMonroeMeters;
      private final ProjectionInfo NAD1983HARNAdjWIOcontoFeet;
      private final ProjectionInfo NAD1983HARNAdjWIOcontoMeters;
      private final ProjectionInfo NAD1983HARNAdjWIOneidaFeet;
      private final ProjectionInfo NAD1983HARNAdjWIOneidaMeters;
      private final ProjectionInfo NAD1983HARNAdjWIOutagamieFeet;
      private final ProjectionInfo NAD1983HARNAdjWIOutagamieMeters;
      private final ProjectionInfo NAD1983HARNAdjWIOzaukeeFeet;
      private final ProjectionInfo NAD1983HARNAdjWIOzaukeeMeters;
      private final ProjectionInfo NAD1983HARNAdjWIPepinFeet;
      private final ProjectionInfo NAD1983HARNAdjWIPepinMeters;
      private final ProjectionInfo NAD1983HARNAdjWIPierceFeet;
      private final ProjectionInfo NAD1983HARNAdjWIPierceMeters;
      private final ProjectionInfo NAD1983HARNAdjWIPolkFeet;
      private final ProjectionInfo NAD1983HARNAdjWIPolkMeters;
      private final ProjectionInfo NAD1983HARNAdjWIPortageFeet;
      private final ProjectionInfo NAD1983HARNAdjWIPortageMeters;
      private final ProjectionInfo NAD1983HARNAdjWIPriceFeet;
      private final ProjectionInfo NAD1983HARNAdjWIPriceMeters;
      private final ProjectionInfo NAD1983HARNAdjWIRacineFeet;
      private final ProjectionInfo NAD1983HARNAdjWIRacineMeters;
      private final ProjectionInfo NAD1983HARNAdjWIRichlandFeet;
      private final ProjectionInfo NAD1983HARNAdjWIRichlandMeters;
      private final ProjectionInfo NAD1983HARNAdjWIRockFeet;
      private final ProjectionInfo NAD1983HARNAdjWIRockMeters;
      private final ProjectionInfo NAD1983HARNAdjWIRuskFeet;
      private final ProjectionInfo NAD1983HARNAdjWIRuskMeters;
      private final ProjectionInfo NAD1983HARNAdjWISaukFeet;
      private final ProjectionInfo NAD1983HARNAdjWISaukMeters;
      private final ProjectionInfo NAD1983HARNAdjWISawyerFeet;
      private final ProjectionInfo NAD1983HARNAdjWISawyerMeters;
      private final ProjectionInfo NAD1983HARNAdjWIShawanoFeet;
      private final ProjectionInfo NAD1983HARNAdjWIShawanoMeters;
      private final ProjectionInfo NAD1983HARNAdjWISheboyganFeet;
      private final ProjectionInfo NAD1983HARNAdjWISheboyganMeters;
      private final ProjectionInfo NAD1983HARNAdjWIStCroixFeet;
      private final ProjectionInfo NAD1983HARNAdjWIStCroixMeters;
      private final ProjectionInfo NAD1983HARNAdjWITaylorFeet;
      private final ProjectionInfo NAD1983HARNAdjWITaylorMeters;
      private final ProjectionInfo NAD1983HARNAdjWITrempealeauFeet;
      private final ProjectionInfo NAD1983HARNAdjWITrempealeauMeters;
      private final ProjectionInfo NAD1983HARNAdjWIVernonFeet;
      private final ProjectionInfo NAD1983HARNAdjWIVernonMeters;
      private final ProjectionInfo NAD1983HARNAdjWIVilasFeet;
      private final ProjectionInfo NAD1983HARNAdjWIVilasMeters;
      private final ProjectionInfo NAD1983HARNAdjWIWalworthFeet;
      private final ProjectionInfo NAD1983HARNAdjWIWalworthMeters;
      private final ProjectionInfo NAD1983HARNAdjWIWashburnFeet;
      private final ProjectionInfo NAD1983HARNAdjWIWashburnMeters;
      private final ProjectionInfo NAD1983HARNAdjWIWashingtonFeet;
      private final ProjectionInfo NAD1983HARNAdjWIWashingtonMeters;
      private final ProjectionInfo NAD1983HARNAdjWIWaukeshaFeet;
      private final ProjectionInfo NAD1983HARNAdjWIWaukeshaMeters;
      private final ProjectionInfo NAD1983HARNAdjWIWaupacaFeet;
      private final ProjectionInfo NAD1983HARNAdjWIWaupacaMeters;
      private final ProjectionInfo NAD1983HARNAdjWIWausharaFeet;
      private final ProjectionInfo NAD1983HARNAdjWIWausharaMeters;
      private final ProjectionInfo NAD1983HARNAdjWIWinnebagoFeet;
      private final ProjectionInfo NAD1983HARNAdjWIWinnebagoMeters;
      private final ProjectionInfo NAD1983HARNAdjWIWoodFeet;
      private final ProjectionInfo NAD1983HARNAdjWIWoodMeters;

        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Constructor">

        /**
         * Creates a new instance of Wisconsin.
         */
        public Wisconsin()
        {
            NAD1983HARNAdjWIAdamsFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.36666666666667 +lon_0=-90 +k=0.999999 +x_0=147218.6944373889 +y_0=0 +a=6378376.271 +b=6356991.5851403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIAdamsMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.36666666666667 +lon_0=-90 +k=0.999999 +x_0=147218.6944373889 +y_0=0 +a=6378376.271 +b=6356991.5851403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIAshlandFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.70611111111111 +lon_0=-90.62222222222222 +k=0.999997 +x_0=172821.9456438913 +y_0=0 +a=6378471.92 +b=6357087.2341403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIAshlandMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.70611111111111 +lon_0=-90.62222222222222 +k=0.999997 +x_0=172821.9456438913 +y_0=0 +a=6378471.92 +b=6357087.2341403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIBarronFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.13333333333333 +lon_0=-91.84999999999999 +k=0.999996 +x_0=93150 +y_0=0 +a=6378472.931 +b=6357088.2451403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIBarronMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.13333333333333 +lon_0=-91.84999999999999 +k=0.999996 +x_0=93150 +y_0=0 +a=6378472.931 +b=6357088.2451403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIBayfieldFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.41388888888888 +lat_2=46.925 +lat_0=45.33333333333334 +lon_0=-91.15277777777779 +x_0=228600.4572009144 +y_0=0 +a=6378411.351 +b=6357026.6651403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIBayfieldMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.41388888888888 +lat_2=46.925 +lat_0=45.33333333333334 +lon_0=-91.15277777777779 +x_0=228600.4572009144 +y_0=0 +a=6378411.351 +b=6357026.6651403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIBrownFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43 +lon_0=-88 +k=1.000020 +x_0=31599.99998983998 +y_0=4599.989839979679 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIBrownMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43 +lon_0=-88 +k=1.000020 +x_0=31599.99998984 +y_0=4599.98983997968 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIBuffaloFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.48138888888889 +lon_0=-91.79722222222222 +k=1.000000 +x_0=175260.350520701 +y_0=0 +a=6378380.991 +b=6356996.305140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIBuffaloMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.48138888888889 +lon_0=-91.79722222222222 +k=1.000000 +x_0=175260.3505207011 +y_0=0 +a=6378380.991 +b=6356996.305140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIBurnettFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.71388888888889 +lat_2=46.08333333333334 +lat_0=45.36388888888889 +lon_0=-92.45777777777778 +x_0=64008.12801625603 +y_0=0 +a=6378414.96 +b=6357030.2741403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIBurnettMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.71388888888889 +lat_2=46.08333333333334 +lat_0=45.36388888888889 +lon_0=-92.45777777777778 +x_0=64008.12801625604 +y_0=0 +a=6378414.96 +b=6357030.2741403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWICalumetFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.71944444444445 +lon_0=-88.5 +k=0.999996 +x_0=244754.889509779 +y_0=0 +a=6378345.09 +b=6356960.4041403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWICalumetMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.71944444444445 +lon_0=-88.5 +k=0.999996 +x_0=244754.8895097791 +y_0=0 +a=6378345.09 +b=6356960.4041403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIChippewaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.81388888888888 +lat_2=45.14166666666667 +lat_0=44.58111111111111 +lon_0=-91.29444444444444 +x_0=60045.72009144018 +y_0=0 +a=6378412.542 +b=6357027.856140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIChippewaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.81388888888888 +lat_2=45.14166666666667 +lat_0=44.58111111111111 +lon_0=-91.29444444444444 +x_0=60045.72009144019 +y_0=0 +a=6378412.542 +b=6357027.856140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIClarkFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.6 +lon_0=-90.70833333333334 +k=0.999994 +x_0=199949.1998983998 +y_0=0 +a=6378470.401 +b=6357085.7151403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIClarkMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.6 +lon_0=-90.70833333333334 +k=0.999994 +x_0=199949.1998984 +y_0=0 +a=6378470.401 +b=6357085.7151403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIColumbiaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.33333333333334 +lat_2=43.59166666666667 +lat_0=42.45833333333334 +lon_0=-89.39444444444445 +x_0=169164.3383286767 +y_0=0 +a=6378376.331 +b=6356991.645140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIColumbiaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.33333333333334 +lat_2=43.59166666666667 +lat_0=42.45833333333334 +lon_0=-89.39444444444445 +x_0=169164.3383286767 +y_0=0 +a=6378376.331 +b=6356991.645140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWICrawfordFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.05833333333333 +lat_2=43.34166666666667 +lat_0=42.71666666666667 +lon_0=-90.9388888888889 +x_0=113690.6273812548 +y_0=0 +a=6378379.031 +b=6356994.345140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWICrawfordMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.05833333333333 +lat_2=43.34166666666667 +lat_0=42.71666666666667 +lon_0=-90.9388888888889 +x_0=113690.6273812548 +y_0=0 +a=6378379.031 +b=6356994.345140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIDaneFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.90833333333333 +lat_2=43.23055555555555 +lat_0=41.75 +lon_0=-89.42222222222223 +x_0=247193.2943865888 +y_0=0 +a=6378407.621 +b=6357022.935140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIDaneMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.90833333333333 +lat_2=43.23055555555555 +lat_0=41.75 +lon_0=-89.42222222222223 +x_0=247193.2943865888 +y_0=0 +a=6378407.621 +b=6357022.935140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIDodgeFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.47222222222222 +lon_0=-88.77500000000001 +k=0.999997 +x_0=263347.7266954534 +y_0=0 +a=6378376.811 +b=6356992.1251403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIDodgeMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.47222222222222 +lon_0=-88.77500000000001 +k=0.999997 +x_0=263347.7266954534 +y_0=0 +a=6378376.811 +b=6356992.1251403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIDoorFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.4 +lon_0=-87.27222222222223 +k=0.999991 +x_0=158801.1176022352 +y_0=0 +a=6378313.92 +b=6356929.2341403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIDoorMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.4 +lon_0=-87.27222222222223 +k=0.999991 +x_0=158801.1176022352 +y_0=0 +a=6378313.92 +b=6356929.2341403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIDouglasFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.88333333333333 +lon_0=-91.91666666666667 +k=0.999995 +x_0=59131.31826263652 +y_0=0 +a=6378414.93 +b=6357030.2441403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIDouglasMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.88333333333333 +lon_0=-91.91666666666667 +k=0.999995 +x_0=59131.31826263653 +y_0=0 +a=6378414.93 +b=6357030.2441403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIDunnFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.40833333333333 +lon_0=-91.89444444444445 +k=0.999998 +x_0=51816.10363220726 +y_0=0 +a=6378413.021 +b=6357028.3351403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIDunnMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.40833333333333 +lon_0=-91.89444444444445 +k=0.999998 +x_0=51816.10363220727 +y_0=0 +a=6378413.021 +b=6357028.3351403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIEauClaireFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.73055555555555 +lat_2=45.01388888888889 +lat_0=44.04722222222222 +lon_0=-91.28888888888889 +x_0=120091.4401828804 +y_0=0 +a=6378380.381 +b=6356995.6951403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIEauClaireMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.73055555555555 +lat_2=45.01388888888889 +lat_0=44.04722222222222 +lon_0=-91.28888888888889 +x_0=120091.4401828804 +y_0=0 +a=6378380.381 +b=6356995.6951403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIFlorenceFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.43888888888888 +lon_0=-88.14166666666668 +k=0.999993 +x_0=133502.667005334 +y_0=0 +a=6378530.851 +b=6357146.1651403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIFlorenceMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.43888888888888 +lon_0=-88.14166666666668 +k=0.999993 +x_0=133502.667005334 +y_0=0 +a=6378530.851 +b=6357146.1651403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIFondduLacFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.71944444444445 +lon_0=-88.5 +k=0.999996 +x_0=244754.889509779 +y_0=0 +a=6378345.09 +b=6356960.4041403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIFondduLacMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.71944444444445 +lon_0=-88.5 +k=0.999996 +x_0=244754.8895097791 +y_0=0 +a=6378345.09 +b=6356960.4041403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIForestFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.00555555555555 +lon_0=-88.63333333333334 +k=0.999996 +x_0=275844.5516891034 +y_0=0 +a=6378591.521 +b=6357206.8351403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIForestMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.00555555555555 +lon_0=-88.63333333333334 +k=0.999996 +x_0=275844.5516891034 +y_0=0 +a=6378591.521 +b=6357206.8351403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIGrantFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.41111111111111 +lon_0=-90.8 +k=0.999997 +x_0=242316.4846329693 +y_0=0 +a=6378378.881 +b=6356994.1951403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIGrantMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.41111111111111 +lon_0=-90.8 +k=0.999997 +x_0=242316.4846329693 +y_0=0 +a=6378378.881 +b=6356994.1951403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIGreenFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.48611111111111 +lat_2=42.78888888888888 +lat_0=42.225 +lon_0=-89.83888888888889 +x_0=170078.7401574803 +y_0=0 +a=6378408.481 +b=6357023.7951403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIGreenLakeFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.66666666666666 +lat_2=43.94722222222222 +lat_0=43.09444444444445 +lon_0=-89.24166666666667 +x_0=150876.3017526035 +y_0=0 +a=6378375.601 +b=6356990.9151403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIGreenLakeMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.66666666666666 +lat_2=43.94722222222222 +lat_0=43.09444444444445 +lon_0=-89.24166666666667 +x_0=150876.3017526035 +y_0=0 +a=6378375.601 +b=6356990.9151403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIGreenMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.48611111111111 +lat_2=42.78888888888888 +lat_0=42.225 +lon_0=-89.83888888888889 +x_0=170078.7401574803 +y_0=0 +a=6378408.481 +b=6357023.7951403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIIowaFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.53888888888888 +lon_0=-90.16111111111111 +k=0.999997 +x_0=113081.0261620523 +y_0=0 +a=6378408.041 +b=6357023.355140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIIowaMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.53888888888888 +lon_0=-90.16111111111111 +k=0.999997 +x_0=113081.0261620523 +y_0=0 +a=6378408.041 +b=6357023.355140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIIronFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.43333333333333 +lon_0=-90.25555555555556 +k=0.999996 +x_0=220980.4419608839 +y_0=0 +a=6378655.071000001 +b=6357270.385140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIIronMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.43333333333333 +lon_0=-90.25555555555556 +k=0.999996 +x_0=220980.4419608839 +y_0=0 +a=6378655.071000001 +b=6357270.385140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIJacksonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.16388888888888 +lat_2=44.41944444444444 +lat_0=43.79444444444444 +lon_0=-90.73888888888889 +x_0=125882.6517653035 +y_0=0 +a=6378409.151 +b=6357024.4651403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIJacksonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.16388888888888 +lat_2=44.41944444444444 +lat_0=43.79444444444444 +lon_0=-90.73888888888889 +x_0=125882.6517653035 +y_0=0 +a=6378409.151 +b=6357024.4651403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIJeffersonFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.47222222222222 +lon_0=-88.77500000000001 +k=0.999997 +x_0=263347.7266954534 +y_0=0 +a=6378376.811 +b=6356992.1251403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIJeffersonMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.47222222222222 +lon_0=-88.77500000000001 +k=0.999997 +x_0=263347.7266954534 +y_0=0 +a=6378376.811 +b=6356992.1251403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIJuneauFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.36666666666667 +lon_0=-90 +k=0.999999 +x_0=147218.6944373889 +y_0=0 +a=6378376.271 +b=6356991.5851403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIJuneauMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.36666666666667 +lon_0=-90 +k=0.999999 +x_0=147218.6944373889 +y_0=0 +a=6378376.271 +b=6356991.5851403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIKenoshaFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.21666666666667 +lon_0=-87.89444444444445 +k=0.999998 +x_0=185928.3718567437 +y_0=0 +a=6378315.7 +b=6356931.014140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIKenoshaMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.21666666666667 +lon_0=-87.89444444444445 +k=0.999998 +x_0=185928.3718567437 +y_0=0 +a=6378315.7 +b=6356931.014140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIKewauneeFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.26666666666667 +lon_0=-87.55 +k=1.000000 +x_0=79857.75971551942 +y_0=0 +a=6378285.86 +b=6356901.174140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIKewauneeMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.26666666666667 +lon_0=-87.55 +k=1.000000 +x_0=79857.75971551944 +y_0=0 +a=6378285.86 +b=6356901.174140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWILaCrosseFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.45111111111111 +lon_0=-91.31666666666666 +k=0.999994 +x_0=130454.6609093218 +y_0=0 +a=6378379.301 +b=6356994.6151403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWILaCrosseMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.45111111111111 +lon_0=-91.31666666666666 +k=0.999994 +x_0=130454.6609093218 +y_0=0 +a=6378379.301 +b=6356994.6151403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWILafayetteFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.48611111111111 +lat_2=42.78888888888888 +lat_0=42.225 +lon_0=-89.83888888888889 +x_0=170078.7401574803 +y_0=0 +a=6378408.481 +b=6357023.7951403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWILafayetteMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.48611111111111 +lat_2=42.78888888888888 +lat_0=42.225 +lon_0=-89.83888888888889 +x_0=170078.7401574803 +y_0=0 +a=6378408.481 +b=6357023.7951403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWILangladeFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45 +lat_2=45.30833333333333 +lat_0=44.20694444444445 +lon_0=-89.03333333333333 +x_0=198425.1968503937 +y_0=0 +a=6378560.121 +b=6357175.435140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWILangladeMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45 +lat_2=45.30833333333333 +lat_0=44.20694444444445 +lon_0=-89.03333333333333 +x_0=198425.1968503937 +y_0=0 +a=6378560.121 +b=6357175.435140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWILincolnFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.84444444444445 +lon_0=-89.73333333333333 +k=0.999998 +x_0=116129.0322580645 +y_0=0 +a=6378531.821000001 +b=6357147.135140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWILincolnMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.84444444444445 +lon_0=-89.73333333333333 +k=0.999998 +x_0=116129.0322580645 +y_0=0 +a=6378531.821000001 +b=6357147.135140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIManitowocFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.26666666666667 +lon_0=-87.55 +k=1.000000 +x_0=79857.75971551942 +y_0=0 +a=6378285.86 +b=6356901.174140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIManitowocMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.26666666666667 +lon_0=-87.55 +k=1.000000 +x_0=79857.75971551944 +y_0=0 +a=6378285.86 +b=6356901.174140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIMarathonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.74527777777778 +lat_2=45.05638888888888 +lat_0=44.40555555555555 +lon_0=-89.77 +x_0=74676.1493522987 +y_0=0 +a=6378500.6 +b=6357115.9141403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIMarathonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.74527777777778 +lat_2=45.05638888888888 +lat_0=44.40555555555555 +lon_0=-89.77 +x_0=74676.14935229872 +y_0=0 +a=6378500.6 +b=6357115.9141403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIMarinetteFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.69166666666666 +lon_0=-87.71111111111111 +k=0.999986 +x_0=238658.8773177546 +y_0=0 +a=6378376.041 +b=6356991.355140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIMarinetteMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.69166666666666 +lon_0=-87.71111111111111 +k=0.999986 +x_0=238658.8773177547 +y_0=0 +a=6378376.041 +b=6356991.355140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIMarquetteFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.66666666666666 +lat_2=43.94722222222222 +lat_0=43.09444444444445 +lon_0=-89.24166666666667 +x_0=150876.3017526035 +y_0=0 +a=6378375.601 +b=6356990.9151403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIMarquetteMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.66666666666666 +lat_2=43.94722222222222 +lat_0=43.09444444444445 +lon_0=-89.24166666666667 +x_0=150876.3017526035 +y_0=0 +a=6378375.601 +b=6356990.9151403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIMenomineeFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.71666666666667 +lon_0=-88.41666666666667 +k=0.999994 +x_0=105461.0109220218 +y_0=0 +a=6378406.601 +b=6357021.9151403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIMenomineeMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.71666666666667 +lon_0=-88.41666666666667 +k=0.999994 +x_0=105461.0109220219 +y_0=0 +a=6378406.601 +b=6357021.9151403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIMilwaukeeFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.21666666666667 +lon_0=-87.89444444444445 +k=0.999998 +x_0=185928.3718567437 +y_0=0 +a=6378315.7 +b=6356931.014140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIMilwaukeeMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.21666666666667 +lon_0=-87.89444444444445 +k=0.999998 +x_0=185928.3718567437 +y_0=0 +a=6378315.7 +b=6356931.014140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIMonroeFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.83888888888889 +lat_2=44.16111111111111 +lat_0=42.90277777777778 +lon_0=-90.64166666666668 +x_0=204521.2090424181 +y_0=0 +a=6378438.991 +b=6357054.305140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIMonroeMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.83888888888889 +lat_2=44.16111111111111 +lat_0=42.90277777777778 +lon_0=-90.64166666666668 +x_0=204521.2090424181 +y_0=0 +a=6378438.991 +b=6357054.305140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIOcontoFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.39722222222222 +lon_0=-87.90833333333335 +k=0.999991 +x_0=182880.3657607315 +y_0=0 +a=6378345.42 +b=6356960.7341403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIOcontoMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.39722222222222 +lon_0=-87.90833333333335 +k=0.999991 +x_0=182880.3657607315 +y_0=0 +a=6378345.42 +b=6356960.7341403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIOneidaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.56666666666667 +lat_2=45.84166666666667 +lat_0=45.18611111111111 +lon_0=-89.54444444444444 +x_0=70104.14020828041 +y_0=0 +a=6378593.86 +b=6357209.174140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIOneidaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.56666666666667 +lat_2=45.84166666666667 +lat_0=45.18611111111111 +lon_0=-89.54444444444444 +x_0=70104.14020828043 +y_0=0 +a=6378593.86 +b=6357209.174140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIOutagamieFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.71944444444445 +lon_0=-88.5 +k=0.999996 +x_0=244754.889509779 +y_0=0 +a=6378345.09 +b=6356960.4041403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIOutagamieMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.71944444444445 +lon_0=-88.5 +k=0.999996 +x_0=244754.8895097791 +y_0=0 +a=6378345.09 +b=6356960.4041403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIOzaukeeFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.21666666666667 +lon_0=-87.89444444444445 +k=0.999998 +x_0=185928.3718567437 +y_0=0 +a=6378315.7 +b=6356931.014140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIOzaukeeMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.21666666666667 +lon_0=-87.89444444444445 +k=0.999998 +x_0=185928.3718567437 +y_0=0 +a=6378315.7 +b=6356931.014140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIPepinFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.52222222222222 +lat_2=44.75 +lat_0=43.86194444444445 +lon_0=-92.22777777777777 +x_0=167640.3352806706 +y_0=0 +a=6378381.271 +b=6356996.5851403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIPepinMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.52222222222222 +lat_2=44.75 +lat_0=43.86194444444445 +lon_0=-92.22777777777777 +x_0=167640.3352806706 +y_0=0 +a=6378381.271 +b=6356996.5851403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIPierceFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.52222222222222 +lat_2=44.75 +lat_0=43.86194444444445 +lon_0=-92.22777777777777 +x_0=167640.3352806706 +y_0=0 +a=6378381.271 +b=6356996.5851403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIPierceMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.52222222222222 +lat_2=44.75 +lat_0=43.86194444444445 +lon_0=-92.22777777777777 +x_0=167640.3352806706 +y_0=0 +a=6378381.271 +b=6356996.5851403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIPolkFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.66111111111111 +lon_0=-92.63333333333334 +k=1.000000 +x_0=141732.2834645669 +y_0=0 +a=6378413.671 +b=6357028.9851403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIPolkMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.66111111111111 +lon_0=-92.63333333333334 +k=1.000000 +x_0=141732.2834645669 +y_0=0 +a=6378413.671 +b=6357028.9851403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIPortageFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.18333333333333 +lat_2=44.65 +lat_0=43.96666666666667 +lon_0=-89.5 +x_0=56388.11277622555 +y_0=0 +a=6378344.377 +b=6356959.691139228 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIPortageMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.18333333333333 +lat_2=44.65 +lat_0=43.96666666666667 +lon_0=-89.5 +x_0=56388.11277622556 +y_0=0 +a=6378344.377 +b=6356959.691139228 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIPriceFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.55555555555555 +lon_0=-90.48888888888889 +k=0.999998 +x_0=227990.8559817119 +y_0=0 +a=6378563.891 +b=6357179.2051403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIPriceMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.55555555555555 +lon_0=-90.48888888888889 +k=0.999998 +x_0=227990.855981712 +y_0=0 +a=6378563.891 +b=6357179.2051403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIRacineFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.21666666666667 +lon_0=-87.89444444444445 +k=0.999998 +x_0=185928.3718567437 +y_0=0 +a=6378315.7 +b=6356931.014140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIRacineMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.21666666666667 +lon_0=-87.89444444444445 +k=0.999998 +x_0=185928.3718567437 +y_0=0 +a=6378315.7 +b=6356931.014140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIRichlandFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.14166666666667 +lat_2=43.50277777777778 +lat_0=42.11388888888889 +lon_0=-90.43055555555556 +x_0=202387.6047752095 +y_0=0 +a=6378408.091 +b=6357023.4051403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIRichlandMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.14166666666667 +lat_2=43.50277777777778 +lat_0=42.11388888888889 +lon_0=-90.43055555555556 +x_0=202387.6047752096 +y_0=0 +a=6378408.091 +b=6357023.4051403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIRockFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.94444444444444 +lon_0=-89.07222222222222 +k=0.999996 +x_0=146304.2926085852 +y_0=0 +a=6378377.671 +b=6356992.9851403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIRockMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.94444444444444 +lon_0=-89.07222222222222 +k=0.999996 +x_0=146304.2926085852 +y_0=0 +a=6378377.671 +b=6356992.9851403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIRuskFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.91944444444444 +lon_0=-91.06666666666666 +k=0.999997 +x_0=250546.1010922022 +y_0=0 +a=6378472.751 +b=6357088.0651403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIRuskMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.91944444444444 +lon_0=-91.06666666666666 +k=0.999997 +x_0=250546.1010922022 +y_0=0 +a=6378472.751 +b=6357088.0651403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWISaukFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.81944444444445 +lon_0=-89.90000000000001 +k=0.999995 +x_0=185623.5712471425 +y_0=0 +a=6378407.281 +b=6357022.595140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWISaukMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.81944444444445 +lon_0=-89.90000000000001 +k=0.999995 +x_0=185623.5712471425 +y_0=0 +a=6378407.281 +b=6357022.595140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWISawyerFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.71944444444445 +lat_2=46.08055555555556 +lat_0=44.81388888888888 +lon_0=-91.11666666666666 +x_0=216713.2334264669 +y_0=0 +a=6378534.451 +b=6357149.765140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWISawyerMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.71944444444445 +lat_2=46.08055555555556 +lat_0=44.81388888888888 +lon_0=-91.11666666666666 +x_0=216713.2334264669 +y_0=0 +a=6378534.451 +b=6357149.765140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIShawanoFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.03611111111111 +lon_0=-88.60555555555555 +k=0.999990 +x_0=262433.3248666497 +y_0=0 +a=6378406.051 +b=6357021.3651403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIShawanoMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.03611111111111 +lon_0=-88.60555555555555 +k=0.999990 +x_0=262433.3248666498 +y_0=0 +a=6378406.051 +b=6357021.3651403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWISheboyganFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.26666666666667 +lon_0=-87.55 +k=1.000000 +x_0=79857.75971551942 +y_0=0 +a=6378285.86 +b=6356901.174140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWISheboyganMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.26666666666667 +lon_0=-87.55 +k=1.000000 +x_0=79857.75971551944 +y_0=0 +a=6378285.86 +b=6356901.174140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIStCroixFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.03611111111111 +lon_0=-92.63333333333334 +k=0.999995 +x_0=165506.731013462 +y_0=0 +a=6378412.511 +b=6357027.8251403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIStCroixMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.03611111111111 +lon_0=-92.63333333333334 +k=0.999995 +x_0=165506.731013462 +y_0=0 +a=6378412.511 +b=6357027.8251403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWITaylorFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.05555555555555 +lat_2=45.3 +lat_0=44.20833333333334 +lon_0=-90.48333333333333 +x_0=187147.5742951486 +y_0=0 +a=6378532.921 +b=6357148.2351403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWITaylorMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.05555555555555 +lat_2=45.3 +lat_0=44.20833333333334 +lon_0=-90.48333333333333 +x_0=187147.5742951486 +y_0=0 +a=6378532.921 +b=6357148.2351403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWITrempealeauFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.16111111111111 +lon_0=-91.36666666666666 +k=0.999998 +x_0=256946.9138938278 +y_0=0 +a=6378380.091 +b=6356995.4051403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWITrempealeauMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.16111111111111 +lon_0=-91.36666666666666 +k=0.999998 +x_0=256946.9138938278 +y_0=0 +a=6378380.091 +b=6356995.4051403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIVernonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.46666666666667 +lat_2=43.68333333333333 +lat_0=43.14722222222222 +lon_0=-90.78333333333333 +x_0=222504.44500889 +y_0=0 +a=6378408.941 +b=6357024.2551403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIVernonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.46666666666667 +lat_2=43.68333333333333 +lat_0=43.14722222222222 +lon_0=-90.78333333333333 +x_0=222504.44500889 +y_0=0 +a=6378408.941 +b=6357024.2551403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIVilasFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.93055555555555 +lat_2=46.225 +lat_0=45.625 +lon_0=-89.48888888888889 +x_0=134417.0688341377 +y_0=0 +a=6378624.171 +b=6357239.4851403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIVilasMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.93055555555555 +lat_2=46.225 +lat_0=45.625 +lon_0=-89.48888888888889 +x_0=134417.0688341377 +y_0=0 +a=6378624.171 +b=6357239.4851403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIWalworthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.58888888888889 +lat_2=42.75 +lat_0=41.66944444444444 +lon_0=-88.54166666666667 +x_0=232562.8651257302 +y_0=0 +a=6378377.411 +b=6356992.725140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIWalworthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.58888888888889 +lat_2=42.75 +lat_0=41.66944444444444 +lon_0=-88.54166666666667 +x_0=232562.8651257303 +y_0=0 +a=6378377.411 +b=6356992.725140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIWashburnFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.77222222222222 +lat_2=46.15 +lat_0=44.26666666666667 +lon_0=-91.78333333333333 +x_0=234086.8681737363 +y_0=0 +a=6378474.591 +b=6357089.9051403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIWashburnMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.77222222222222 +lat_2=46.15 +lat_0=44.26666666666667 +lon_0=-91.78333333333333 +x_0=234086.8681737364 +y_0=0 +a=6378474.591 +b=6357089.9051403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIWashingtonFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.91805555555555 +lon_0=-88.06388888888888 +k=0.999995 +x_0=120091.4401828804 +y_0=0 +a=6378407.141 +b=6357022.4551403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIWashingtonMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.91805555555555 +lon_0=-88.06388888888888 +k=0.999995 +x_0=120091.4401828804 +y_0=0 +a=6378407.141 +b=6357022.4551403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIWaukeshaFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.56944444444445 +lon_0=-88.22499999999999 +k=0.999997 +x_0=208788.4175768351 +y_0=0 +a=6378376.871 +b=6356992.185140301 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIWaukeshaMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.56944444444445 +lon_0=-88.22499999999999 +k=0.999997 +x_0=208788.4175768352 +y_0=0 +a=6378376.871 +b=6356992.185140301 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIWaupacaFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.42027777777778 +lon_0=-88.81666666666666 +k=0.999996 +x_0=185013.9700279401 +y_0=0 +a=6378375.251 +b=6356990.5651403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIWaupacaMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=43.42027777777778 +lon_0=-88.81666666666666 +k=0.999996 +x_0=185013.9700279401 +y_0=0 +a=6378375.251 +b=6356990.5651403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIWausharaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.975 +lat_2=44.25277777777778 +lat_0=43.70833333333334 +lon_0=-89.24166666666667 +x_0=120091.4401828804 +y_0=0 +a=6378405.971 +b=6357021.2851403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIWausharaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.975 +lat_2=44.25277777777778 +lat_0=43.70833333333334 +lon_0=-89.24166666666667 +x_0=120091.4401828804 +y_0=0 +a=6378405.971 +b=6357021.2851403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIWinnebagoFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.71944444444445 +lon_0=-88.5 +k=0.999996 +x_0=244754.889509779 +y_0=0 +a=6378345.09 +b=6356960.4041403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIWinnebagoMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=42.71944444444445 +lon_0=-88.5 +k=0.999996 +x_0=244754.8895097791 +y_0=0 +a=6378345.09 +b=6356960.4041403 +units=m +no_defs ").orElse(null);
            NAD1983HARNAdjWIWoodFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.18055555555555 +lat_2=44.54444444444444 +lat_0=43.15138888888889 +lon_0=-90 +x_0=208483.6169672339 +y_0=0 +a=6378437.651 +b=6357052.9651403 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNAdjWIWoodMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.18055555555555 +lat_2=44.54444444444444 +lat_0=43.15138888888889 +lon_0=-90 +x_0=208483.616967234 +y_0=0 +a=6378437.651 +b=6357052.9651403 +units=m +no_defs ").orElse(null);

            NAD1983HARNAdjWIAdamsMeters.setName("NAD_1983_HARN_WISCRS_Adams_County_Meters");
            NAD1983HARNAdjWIAdamsFeet.setName("NAD_1983_HARN_WISCRS_Adams_County_Feet");
            NAD1983HARNAdjWIAshlandMeters.setName("NAD_1983_HARN_WISCRS_Ashland_County_Meters");
            NAD1983HARNAdjWIAshlandFeet.setName("NAD_1983_HARN_WISCRS_Ashland_County_Feet");
            NAD1983HARNAdjWIBarronMeters.setName("NAD_1983_HARN_WISCRS_Barron_County_Meters");
            NAD1983HARNAdjWIBarronFeet.setName("NAD_1983_HARN_WISCRS_Barron_County_Feet");
            NAD1983HARNAdjWIBayfieldMeters.setName("NAD_1983_HARN_WISCRS_Bayfield_County_Meters");
            NAD1983HARNAdjWIBayfieldFeet.setName("NAD_1983_HARN_WISCRS_Bayfield_County_Feet");
            NAD1983HARNAdjWIBrownMeters.setName("NAD_1983_HARN_WISCRS_Brown_County_Meters");
            NAD1983HARNAdjWIBrownFeet.setName("NAD_1983_HARN_WISCRS_Brown_County_Feet");
            NAD1983HARNAdjWIBuffaloMeters.setName("NAD_1983_HARN_WISCRS_Buffalo_County_Meters");
            NAD1983HARNAdjWIBuffaloFeet.setName("NAD_1983_HARN_WISCRS_Buffalo_County_Feet");
            NAD1983HARNAdjWIBurnettMeters.setName("NAD_1983_HARN_WISCRS_Burnett_County_Meters");
            NAD1983HARNAdjWIBurnettFeet.setName("NAD_1983_HARN_WISCRS_Burnett_County_Feet");
            NAD1983HARNAdjWICalumetMeters.setName("NAD_1983_HARN_WISCRS_Calumet_County_Meters");
            NAD1983HARNAdjWICalumetFeet.setName("NAD_1983_HARN_WISCRS_Calumet_County_Feet");
            NAD1983HARNAdjWIChippewaMeters.setName("NAD_1983_HARN_WISCRS_Chippewa_County_Meters");
            NAD1983HARNAdjWIChippewaFeet.setName("NAD_1983_HARN_WISCRS_Chippewa_County_Feet");
            NAD1983HARNAdjWIClarkMeters.setName("NAD_1983_HARN_WISCRS_Clark_County_Meters");
            NAD1983HARNAdjWIClarkFeet.setName("NAD_1983_HARN_WISCRS_Clark_County_Feet");
            NAD1983HARNAdjWIColumbiaMeters.setName("NAD_1983_HARN_WISCRS_Columbia_County_Meters");
            NAD1983HARNAdjWIColumbiaFeet.setName("NAD_1983_HARN_WISCRS_Columbia_County_Feet");
            NAD1983HARNAdjWICrawfordMeters.setName("NAD_1983_HARN_WISCRS_Crawford_County_Meters");
            NAD1983HARNAdjWICrawfordFeet.setName("NAD_1983_HARN_WISCRS_Crawford_County_Feet");
            NAD1983HARNAdjWIDaneMeters.setName("NAD_1983_HARN_WISCRS_Dane_County_Meters");
            NAD1983HARNAdjWIDaneFeet.setName("NAD_1983_HARN_WISCRS_Dane_County_Feet");
            NAD1983HARNAdjWIDodgeMeters.setName("NAD_1983_HARN_WISCRS_Dodge_County_Meters");
            NAD1983HARNAdjWIDodgeFeet.setName("NAD_1983_HARN_WISCRS_Dodge_County_Feet");
            NAD1983HARNAdjWIDoorMeters.setName("NAD_1983_HARN_WISCRS_Door_County_Meters");
            NAD1983HARNAdjWIDoorFeet.setName("NAD_1983_HARN_WISCRS_Door_County_Feet");
            NAD1983HARNAdjWIDouglasMeters.setName("NAD_1983_HARN_WISCRS_Douglas_County_Meters");
            NAD1983HARNAdjWIDouglasFeet.setName("NAD_1983_HARN_WISCRS_Douglas_County_Feet");
            NAD1983HARNAdjWIDunnMeters.setName("NAD_1983_HARN_WISCRS_Dunn_County_Meters");
            NAD1983HARNAdjWIDunnFeet.setName("NAD_1983_HARN_WISCRS_Dunn_County_Feet");
            NAD1983HARNAdjWIEauClaireMeters.setName("NAD_1983_HARN_WISCRS_EauClaire_County_Meters");
            NAD1983HARNAdjWIEauClaireFeet.setName("NAD_1983_HARN_WISCRS_EauClaire_County_Feet");
            NAD1983HARNAdjWIFlorenceMeters.setName("NAD_1983_HARN_WISCRS_Florence_County_Meters");
            NAD1983HARNAdjWIFlorenceFeet.setName("NAD_1983_HARN_WISCRS_Florence_County_Feet");
            NAD1983HARNAdjWIFondduLacMeters.setName("NAD_1983_HARN_WISCRS_Fond_du_Lac_County_Meters");
            NAD1983HARNAdjWIFondduLacFeet.setName("NAD_1983_HARN_WISCRS_Fond_du_Lac_County_Feet");
            NAD1983HARNAdjWIForestMeters.setName("NAD_1983_HARN_WISCRS_Forest_County_Meters");
            NAD1983HARNAdjWIForestFeet.setName("NAD_1983_HARN_WISCRS_Forest_County_Feet");
            NAD1983HARNAdjWIGrantMeters.setName("NAD_1983_HARN_WISCRS_Grant_County_Meters");
            NAD1983HARNAdjWIGrantFeet.setName("NAD_1983_HARN_WISCRS_Grant_County_Feet");
            NAD1983HARNAdjWIGreenLakeFeet.setName("NAD_1983_HARN_WISCRS_Green_County_Meters");
            NAD1983HARNAdjWIGreenFeet.setName("NAD_1983_HARN_WISCRS_Green_County_Feet");
            NAD1983HARNAdjWIGreenMeters.setName("NAD_1983_HARN_WISCRS_GreenLake_County_Meters");
            NAD1983HARNAdjWIGreenLakeMeters.setName("NAD_1983_HARN_WISCRS_GreenLake_County_Feet");
            NAD1983HARNAdjWIIowaMeters.setName("NAD_1983_HARN_WISCRS_Iowa_County_Meters");
            NAD1983HARNAdjWIIowaFeet.setName("NAD_1983_HARN_WISCRS_Iowa_County_Feet");
            NAD1983HARNAdjWIIronMeters.setName("NAD_1983_HARN_WISCRS_Iron_County_Meters");
            NAD1983HARNAdjWIIronFeet.setName("NAD_1983_HARN_WISCRS_Iron_County_Feet");
            NAD1983HARNAdjWIJacksonMeters.setName("NAD_1983_HARN_WISCRS_Jackson_County_Meters");
            NAD1983HARNAdjWIJacksonFeet.setName("NAD_1983_HARN_WISCRS_Jackson_County_Feet");
            NAD1983HARNAdjWIJeffersonMeters.setName("NAD_1983_HARN_WISCRS_Jefferson_County_Meters");
            NAD1983HARNAdjWIJeffersonFeet.setName("NAD_1983_HARN_WISCRS_Jefferson_County_Feet");
            NAD1983HARNAdjWIJuneauMeters.setName("NAD_1983_HARN_WISCRS_Juneau_County_Meters");
            NAD1983HARNAdjWIJuneauFeet.setName("NAD_1983_HARN_WISCRS_Juneau_County_Feet");
            NAD1983HARNAdjWIKenoshaMeters.setName("NAD_1983_HARN_WISCRS_Kenosha_County_Meters");
            NAD1983HARNAdjWIKenoshaFeet.setName("NAD_1983_HARN_WISCRS_Kenosha_County_Feet");
            NAD1983HARNAdjWIKewauneeMeters.setName("NAD_1983_HARN_WISCRS_Kewaunee_County_Meters");
            NAD1983HARNAdjWIKewauneeFeet.setName("NAD_1983_HARN_WISCRS_Kewaunee_County_Feet");
            NAD1983HARNAdjWILaCrosseMeters.setName("NAD_1983_HARN_WISCRS_LaCrosse_County_Meters");
            NAD1983HARNAdjWILaCrosseFeet.setName("NAD_1983_HARN_WISCRS_LaCrosse_County_Feet");
            NAD1983HARNAdjWILafayetteMeters.setName("NAD_1983_HARN_WISCRS_Lafayette_County_Meters");
            NAD1983HARNAdjWILafayetteFeet.setName("NAD_1983_HARN_WISCRS_Lafayette_County_Feet");
            NAD1983HARNAdjWILangladeMeters.setName("NAD_1983_HARN_WISCRS_Langlade_County_Meters");
            NAD1983HARNAdjWILangladeFeet.setName("NAD_1983_HARN_WISCRS_Langlade_County_Feet");
            NAD1983HARNAdjWILincolnMeters.setName("NAD_1983_HARN_WISCRS_Lincoln_County_Meters");
            NAD1983HARNAdjWILincolnFeet.setName("NAD_1983_HARN_WISCRS_Lincoln_County_Feet");
            NAD1983HARNAdjWIManitowocMeters.setName("NAD_1983_HARN_WISCRS_Manitowoc_County_Meters");
            NAD1983HARNAdjWIManitowocFeet.setName("NAD_1983_HARN_WISCRS_Manitowoc_County_Feet");
            NAD1983HARNAdjWIMarathonMeters.setName("NAD_1983_HARN_WISCRS_Marathon_County_Meters");
            NAD1983HARNAdjWIMarathonFeet.setName("NAD_1983_HARN_WISCRS_Marathon_County_Feet");
            NAD1983HARNAdjWIMarinetteMeters.setName("NAD_1983_HARN_WISCRS_Marinette_County_Meters");
            NAD1983HARNAdjWIMarinetteFeet.setName("NAD_1983_HARN_WISCRS_Marinette_County_Feet");
            NAD1983HARNAdjWIMarquetteMeters.setName("NAD_1983_HARN_WISCRS_Marquette_County_Meters");
            NAD1983HARNAdjWIMarquetteFeet.setName("NAD_1983_HARN_WISCRS_Marquette_County_Feet");
            NAD1983HARNAdjWIMenomineeMeters.setName("NAD_1983_HARN_WISCRS_Menominee_County_Meters");
            NAD1983HARNAdjWIMenomineeFeet.setName("NAD_1983_HARN_WISCRS_Menominee_County_Feet");
            NAD1983HARNAdjWIMilwaukeeMeters.setName("NAD_1983_HARN_WISCRS_Milwaukee_County_Meters");
            NAD1983HARNAdjWIMilwaukeeFeet.setName("NAD_1983_HARN_WISCRS_Milwaukee_County_Feet");
            NAD1983HARNAdjWIMonroeMeters.setName("NAD_1983_HARN_WISCRS_Monroe_County_Meters");
            NAD1983HARNAdjWIMonroeFeet.setName("NAD_1983_HARN_WISCRS_Monroe_County_Feet");
            NAD1983HARNAdjWIOcontoMeters.setName("NAD_1983_HARN_WISCRS_Oconto_County_Meters");
            NAD1983HARNAdjWIOcontoFeet.setName("NAD_1983_HARN_WISCRS_Oconto_County_Feet");
            NAD1983HARNAdjWIOneidaMeters.setName("NAD_1983_HARN_WISCRS_Oneida_County_Meters");
            NAD1983HARNAdjWIOneidaFeet.setName("NAD_1983_HARN_WISCRS_Oneida_County_Feet");
            NAD1983HARNAdjWIOutagamieMeters.setName("NAD_1983_HARN_WISCRS_Outagamie_County_Meters");
            NAD1983HARNAdjWIOutagamieFeet.setName("NAD_1983_HARN_WISCRS_Outagamie_County_Feet");
            NAD1983HARNAdjWIOzaukeeMeters.setName("NAD_1983_HARN_WISCRS_Ozaukee_County_Meters");
            NAD1983HARNAdjWIOzaukeeFeet.setName("NAD_1983_HARN_WISCRS_Ozaukee_County_Feet");
            NAD1983HARNAdjWIPepinMeters.setName("NAD_1983_HARN_WISCRS_Pepin_County_Meters");
            NAD1983HARNAdjWIPepinFeet.setName("NAD_1983_HARN_WISCRS_Pepin_County_Feet");
            NAD1983HARNAdjWIPierceMeters.setName("NAD_1983_HARN_WISCRS_Pierce_County_Meters");
            NAD1983HARNAdjWIPierceFeet.setName("NAD_1983_HARN_WISCRS_Pierce_County_Feet");
            NAD1983HARNAdjWIPolkMeters.setName("NAD_1983_HARN_WISCRS_Polk_County_Meters");
            NAD1983HARNAdjWIPolkFeet.setName("NAD_1983_HARN_WISCRS_Polk_County_Feet");
            NAD1983HARNAdjWIPortageMeters.setName("NAD_1983_HARN_WISCRS_Portage_County_Meters");
            NAD1983HARNAdjWIPortageFeet.setName("NAD_1983_HARN_WISCRS_Portage_County_Feet");
            NAD1983HARNAdjWIPriceMeters.setName("NAD_1983_HARN_WISCRS_Price_County_Meters");
            NAD1983HARNAdjWIPriceFeet.setName("NAD_1983_HARN_WISCRS_Price_County_Feet");
            NAD1983HARNAdjWIRacineMeters.setName("NAD_1983_HARN_WISCRS_Racine_County_Meters");
            NAD1983HARNAdjWIRacineFeet.setName("NAD_1983_HARN_WISCRS_Racine_County_Feet");
            NAD1983HARNAdjWIRichlandMeters.setName("NAD_1983_HARN_WISCRS_Richland_County_Meters");
            NAD1983HARNAdjWIRichlandFeet.setName("NAD_1983_HARN_WISCRS_Richland_County_Feet");
            NAD1983HARNAdjWIRockMeters.setName("NAD_1983_HARN_WISCRS_Rock_County_Meters");
            NAD1983HARNAdjWIRockFeet.setName("NAD_1983_HARN_WISCRS_Rock_County_Feet");
            NAD1983HARNAdjWIRuskMeters.setName("NAD_1983_HARN_WISCRS_Rusk_County_Meters");
            NAD1983HARNAdjWIRuskFeet.setName("NAD_1983_HARN_WISCRS_Rusk_County_Feet");
            NAD1983HARNAdjWISaukMeters.setName("NAD_1983_HARN_WISCRS_Sauk_County_Meters");
            NAD1983HARNAdjWISaukFeet.setName("NAD_1983_HARN_WISCRS_Sauk_County_Feet");
            NAD1983HARNAdjWISawyerMeters.setName("NAD_1983_HARN_WISCRS_Sawyer_County_Meters");
            NAD1983HARNAdjWISawyerFeet.setName("NAD_1983_HARN_WISCRS_Sawyer_County_Feet");
            NAD1983HARNAdjWIShawanoMeters.setName("NAD_1983_HARN_WISCRS_Shawano_County_Meters");
            NAD1983HARNAdjWIShawanoFeet.setName("NAD_1983_HARN_WISCRS_Shawano_County_Feet");
            NAD1983HARNAdjWISheboyganMeters.setName("NAD_1983_HARN_WISCRS_Sheboygan_County_Meters");
            NAD1983HARNAdjWISheboyganFeet.setName("NAD_1983_HARN_WISCRS_Sheboygan_County_Feet");
            NAD1983HARNAdjWIStCroixMeters.setName("NAD_1983_HARN_WISCRS_St_Croix_County_Meters");
            NAD1983HARNAdjWIStCroixFeet.setName("NAD_1983_HARN_WISCRS_St_Croix_County_Feet");
            NAD1983HARNAdjWITaylorMeters.setName("NAD_1983_HARN_WISCRS_Taylor_County_Meters");
            NAD1983HARNAdjWITaylorFeet.setName("NAD_1983_HARN_WISCRS_Taylor_County_Feet");
            NAD1983HARNAdjWITrempealeauMeters.setName("NAD_1983_HARN_WISCRS_Trempealeau_County_Meters");
            NAD1983HARNAdjWITrempealeauFeet.setName("NAD_1983_HARN_WISCRS_Trempealeau_County_Feet");
            NAD1983HARNAdjWIVernonMeters.setName("NAD_1983_HARN_WISCRS_Vernon_County_Meters");
            NAD1983HARNAdjWIVernonFeet.setName("NAD_1983_HARN_WISCRS_Vernon_County_Feet");
            NAD1983HARNAdjWIVilasMeters.setName("NAD_1983_HARN_WISCRS_Vilas_County_Meters");
            NAD1983HARNAdjWIVilasFeet.setName("NAD_1983_HARN_WISCRS_Vilas_County_Feet");
            NAD1983HARNAdjWIWalworthMeters.setName("NAD_1983_HARN_WISCRS_Walworth_County_Meters");
            NAD1983HARNAdjWIWalworthFeet.setName("NAD_1983_HARN_WISCRS_Walworth_County_Feet");
            NAD1983HARNAdjWIWashburnMeters.setName("NAD_1983_HARN_WISCRS_Washburn_County_Meters");
            NAD1983HARNAdjWIWashburnFeet.setName("NAD_1983_HARN_WISCRS_Washburn_County_Feet");
            NAD1983HARNAdjWIWashingtonMeters.setName("NAD_1983_HARN_WISCRS_Washington_County_Meters");
            NAD1983HARNAdjWIWashingtonFeet.setName("NAD_1983_HARN_WISCRS_Washington_County_Feet");
            NAD1983HARNAdjWIWaukeshaMeters.setName("NAD_1983_HARN_WISCRS_Waukesha_County_Meters");
            NAD1983HARNAdjWIWaukeshaFeet.setName("NAD_1983_HARN_WISCRS_Waukesha_County_Feet");
            NAD1983HARNAdjWIWaupacaMeters.setName("NAD_1983_HARN_WISCRS_Waupaca_County_Meters");
            NAD1983HARNAdjWIWaupacaFeet.setName("NAD_1983_HARN_WISCRS_Waupaca_County_Feet");
            NAD1983HARNAdjWIWausharaMeters.setName("NAD_1983_HARN_WISCRS_Waushara_County_Meters");
            NAD1983HARNAdjWIWausharaFeet.setName("NAD_1983_HARN_WISCRS_Waushara_County_Feet");
            NAD1983HARNAdjWIWinnebagoMeters.setName("NAD_1983_HARN_WISCRS_Winnebago_County_Meters");
            NAD1983HARNAdjWIWinnebagoFeet.setName("NAD_1983_HARN_WISCRS_Winnebago_County_Feet");
            NAD1983HARNAdjWIWoodMeters.setName("NAD_1983_HARN_WISCRS_Wood_County_Meters");
            NAD1983HARNAdjWIWoodFeet.setName("NAD_1983_HARN_WISCRS_Wood_County_Feet");

            NAD1983HARNAdjWIAdamsMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIAdamsFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIAshlandMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIAshlandFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIBarronMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIBarronFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIBayfieldMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIBayfieldFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIBrownMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIBrownFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIBuffaloMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIBuffaloFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIBurnettMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIBurnettFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWICalumetMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWICalumetFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIChippewaMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIChippewaFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIClarkMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIClarkFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIColumbiaMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIColumbiaFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWICrawfordMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWICrawfordFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIDaneMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIDaneFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIDodgeMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIDodgeFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIDoorMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIDoorFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIDouglasMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIDouglasFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIDunnMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIDunnFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIEauClaireMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIEauClaireFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIFlorenceMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIFlorenceFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIFondduLacMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIFondduLacFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIForestMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIForestFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIGrantMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIGrantFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIGreenLakeFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIGreenFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIGreenMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIGreenLakeMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIIowaMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIIowaFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIIronMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIIronFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIJacksonMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIJacksonFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIJeffersonMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIJeffersonFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIJuneauMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIJuneauFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIKenoshaMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIKenoshaFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIKewauneeMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIKewauneeFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWILaCrosseMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWILaCrosseFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWILafayetteMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWILafayetteFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWILangladeMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWILangladeFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWILincolnMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWILincolnFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIManitowocMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIManitowocFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMarathonMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMarathonFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMarinetteMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMarinetteFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMarquetteMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMarquetteFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMenomineeMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMenomineeFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMilwaukeeMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMilwaukeeFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMonroeMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIMonroeFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIOcontoMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIOcontoFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIOneidaMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIOneidaFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIOutagamieMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIOutagamieFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIOzaukeeMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIOzaukeeFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIPepinMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIPepinFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIPierceMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIPierceFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIPolkMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIPolkFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIPortageMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIPortageFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIPriceMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIPriceFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIRacineMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIRacineFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIRichlandMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIRichlandFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIRockMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIRockFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIRuskMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIRuskFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWISaukMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWISaukFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWISawyerMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWISawyerFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIShawanoMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIShawanoFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWISheboyganMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWISheboyganFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIStCroixMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIStCroixFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWITaylorMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWITaylorFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWITrempealeauMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWITrempealeauFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIVernonMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIVernonFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIVilasMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIVilasFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWalworthMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWalworthFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWashburnMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWashburnFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWashingtonMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWashingtonFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWaukeshaMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWaukeshaFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWaupacaMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWaupacaFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWausharaMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWausharaFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWinnebagoMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWinnebagoFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWoodMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjWIWoodFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");

            NAD1983HARNAdjWIAdamsMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIAdamsFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIAshlandMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIAshlandFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIBarronMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIBarronFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIBayfieldMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIBayfieldFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIBrownMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIBrownFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIBuffaloMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIBuffaloFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIBurnettMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIBurnettFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWICalumetMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWICalumetFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIChippewaMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIChippewaFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIClarkMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIClarkFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIColumbiaMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIColumbiaFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWICrawfordMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWICrawfordFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIDaneMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIDaneFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIDodgeMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIDodgeFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIDoorMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIDoorFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIDouglasMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIDouglasFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIDunnMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIDunnFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIEauClaireMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIEauClaireFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIFlorenceMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIFlorenceFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIFondduLacMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIFondduLacFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIForestMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIForestFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIGrantMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIGrantFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIGreenLakeFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIGreenFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIGreenMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIGreenLakeMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIIowaMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIIowaFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIIronMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIIronFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIJacksonMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIJacksonFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIJeffersonMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIJeffersonFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIJuneauMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIJuneauFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIKenoshaMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIKenoshaFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIKewauneeMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIKewauneeFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWILaCrosseMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWILaCrosseFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWILafayetteMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWILafayetteFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWILangladeMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWILangladeFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWILincolnMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWILincolnFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIManitowocMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIManitowocFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMarathonMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMarathonFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMarinetteMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMarinetteFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMarquetteMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMarquetteFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMenomineeMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMenomineeFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMilwaukeeMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMilwaukeeFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMonroeMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIMonroeFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIOcontoMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIOcontoFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIOneidaMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIOneidaFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIOutagamieMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIOutagamieFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIOzaukeeMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIOzaukeeFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIPepinMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIPepinFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIPierceMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIPierceFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIPolkMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIPolkFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIPortageMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIPortageFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIPriceMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIPriceFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIRacineMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIRacineFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIRichlandMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIRichlandFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIRockMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIRockFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIRuskMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIRuskFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWISaukMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWISaukFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWISawyerMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWISawyerFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIShawanoMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIShawanoFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWISheboyganMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWISheboyganFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIStCroixMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIStCroixFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWITaylorMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWITaylorFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWITrempealeauMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWITrempealeauFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIVernonMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIVernonFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIVilasMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIVilasFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWalworthMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWalworthFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWashburnMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWashburnFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWashingtonMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWashingtonFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWaukeshaMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWaukeshaFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWaupacaMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWaupacaFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWausharaMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWausharaFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWinnebagoMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWinnebagoFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWoodMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjWIWoodFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
        }

        //</editor-fold>

  /**
   * @return the NAD1983HARNAdjWIAdamsFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIAdamsFeet() {
    return NAD1983HARNAdjWIAdamsFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIAdamsMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIAdamsMeters() {
    return NAD1983HARNAdjWIAdamsMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIAshlandFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIAshlandFeet() {
    return NAD1983HARNAdjWIAshlandFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIAshlandMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIAshlandMeters() {
    return NAD1983HARNAdjWIAshlandMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBarronFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIBarronFeet() {
    return NAD1983HARNAdjWIBarronFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBarronMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIBarronMeters() {
    return NAD1983HARNAdjWIBarronMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBayfieldFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIBayfieldFeet() {
    return NAD1983HARNAdjWIBayfieldFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBayfieldMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIBayfieldMeters() {
    return NAD1983HARNAdjWIBayfieldMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBrownFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIBrownFeet() {
    return NAD1983HARNAdjWIBrownFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBrownMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIBrownMeters() {
    return NAD1983HARNAdjWIBrownMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBuffaloFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIBuffaloFeet() {
    return NAD1983HARNAdjWIBuffaloFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBuffaloMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIBuffaloMeters() {
    return NAD1983HARNAdjWIBuffaloMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBurnettFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIBurnettFeet() {
    return NAD1983HARNAdjWIBurnettFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBurnettMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIBurnettMeters() {
    return NAD1983HARNAdjWIBurnettMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWICalumetFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWICalumetFeet() {
    return NAD1983HARNAdjWICalumetFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWICalumetMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWICalumetMeters() {
    return NAD1983HARNAdjWICalumetMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIChippewaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIChippewaFeet() {
    return NAD1983HARNAdjWIChippewaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIChippewaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIChippewaMeters() {
    return NAD1983HARNAdjWIChippewaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIClarkFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIClarkFeet() {
    return NAD1983HARNAdjWIClarkFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIClarkMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIClarkMeters() {
    return NAD1983HARNAdjWIClarkMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIColumbiaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIColumbiaFeet() {
    return NAD1983HARNAdjWIColumbiaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIColumbiaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIColumbiaMeters() {
    return NAD1983HARNAdjWIColumbiaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWICrawfordFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWICrawfordFeet() {
    return NAD1983HARNAdjWICrawfordFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWICrawfordMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWICrawfordMeters() {
    return NAD1983HARNAdjWICrawfordMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDaneFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIDaneFeet() {
    return NAD1983HARNAdjWIDaneFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDaneMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIDaneMeters() {
    return NAD1983HARNAdjWIDaneMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDodgeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIDodgeFeet() {
    return NAD1983HARNAdjWIDodgeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDodgeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIDodgeMeters() {
    return NAD1983HARNAdjWIDodgeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDoorFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIDoorFeet() {
    return NAD1983HARNAdjWIDoorFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDoorMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIDoorMeters() {
    return NAD1983HARNAdjWIDoorMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDouglasFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIDouglasFeet() {
    return NAD1983HARNAdjWIDouglasFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDouglasMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIDouglasMeters() {
    return NAD1983HARNAdjWIDouglasMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDunnFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIDunnFeet() {
    return NAD1983HARNAdjWIDunnFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDunnMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIDunnMeters() {
    return NAD1983HARNAdjWIDunnMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIEauClaireFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIEauClaireFeet() {
    return NAD1983HARNAdjWIEauClaireFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIEauClaireMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIEauClaireMeters() {
    return NAD1983HARNAdjWIEauClaireMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIFlorenceFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIFlorenceFeet() {
    return NAD1983HARNAdjWIFlorenceFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIFlorenceMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIFlorenceMeters() {
    return NAD1983HARNAdjWIFlorenceMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIFondduLacFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIFondduLacFeet() {
    return NAD1983HARNAdjWIFondduLacFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIFondduLacMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIFondduLacMeters() {
    return NAD1983HARNAdjWIFondduLacMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIForestFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIForestFeet() {
    return NAD1983HARNAdjWIForestFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIForestMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIForestMeters() {
    return NAD1983HARNAdjWIForestMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIGrantFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIGrantFeet() {
    return NAD1983HARNAdjWIGrantFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIGrantMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIGrantMeters() {
    return NAD1983HARNAdjWIGrantMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIGreenFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIGreenFeet() {
    return NAD1983HARNAdjWIGreenFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIGreenLakeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIGreenLakeFeet() {
    return NAD1983HARNAdjWIGreenLakeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIGreenLakeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIGreenLakeMeters() {
    return NAD1983HARNAdjWIGreenLakeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIGreenMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIGreenMeters() {
    return NAD1983HARNAdjWIGreenMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIIowaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIIowaFeet() {
    return NAD1983HARNAdjWIIowaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIIowaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIIowaMeters() {
    return NAD1983HARNAdjWIIowaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIIronFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIIronFeet() {
    return NAD1983HARNAdjWIIronFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIIronMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIIronMeters() {
    return NAD1983HARNAdjWIIronMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIJacksonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIJacksonFeet() {
    return NAD1983HARNAdjWIJacksonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIJacksonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIJacksonMeters() {
    return NAD1983HARNAdjWIJacksonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIJeffersonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIJeffersonFeet() {
    return NAD1983HARNAdjWIJeffersonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIJeffersonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIJeffersonMeters() {
    return NAD1983HARNAdjWIJeffersonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIJuneauFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIJuneauFeet() {
    return NAD1983HARNAdjWIJuneauFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIJuneauMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIJuneauMeters() {
    return NAD1983HARNAdjWIJuneauMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIKenoshaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIKenoshaFeet() {
    return NAD1983HARNAdjWIKenoshaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIKenoshaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIKenoshaMeters() {
    return NAD1983HARNAdjWIKenoshaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIKewauneeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIKewauneeFeet() {
    return NAD1983HARNAdjWIKewauneeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIKewauneeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIKewauneeMeters() {
    return NAD1983HARNAdjWIKewauneeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILaCrosseFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWILaCrosseFeet() {
    return NAD1983HARNAdjWILaCrosseFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILaCrosseMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWILaCrosseMeters() {
    return NAD1983HARNAdjWILaCrosseMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILafayetteFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWILafayetteFeet() {
    return NAD1983HARNAdjWILafayetteFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILafayetteMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWILafayetteMeters() {
    return NAD1983HARNAdjWILafayetteMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILangladeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWILangladeFeet() {
    return NAD1983HARNAdjWILangladeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILangladeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWILangladeMeters() {
    return NAD1983HARNAdjWILangladeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILincolnFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWILincolnFeet() {
    return NAD1983HARNAdjWILincolnFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILincolnMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWILincolnMeters() {
    return NAD1983HARNAdjWILincolnMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIManitowocFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIManitowocFeet() {
    return NAD1983HARNAdjWIManitowocFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIManitowocMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIManitowocMeters() {
    return NAD1983HARNAdjWIManitowocMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMarathonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIMarathonFeet() {
    return NAD1983HARNAdjWIMarathonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMarathonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIMarathonMeters() {
    return NAD1983HARNAdjWIMarathonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMarinetteFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIMarinetteFeet() {
    return NAD1983HARNAdjWIMarinetteFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMarinetteMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIMarinetteMeters() {
    return NAD1983HARNAdjWIMarinetteMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMarquetteFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIMarquetteFeet() {
    return NAD1983HARNAdjWIMarquetteFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMarquetteMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIMarquetteMeters() {
    return NAD1983HARNAdjWIMarquetteMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMenomineeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIMenomineeFeet() {
    return NAD1983HARNAdjWIMenomineeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMenomineeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIMenomineeMeters() {
    return NAD1983HARNAdjWIMenomineeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMilwaukeeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIMilwaukeeFeet() {
    return NAD1983HARNAdjWIMilwaukeeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMilwaukeeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIMilwaukeeMeters() {
    return NAD1983HARNAdjWIMilwaukeeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMonroeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIMonroeFeet() {
    return NAD1983HARNAdjWIMonroeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMonroeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIMonroeMeters() {
    return NAD1983HARNAdjWIMonroeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOcontoFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIOcontoFeet() {
    return NAD1983HARNAdjWIOcontoFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOcontoMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIOcontoMeters() {
    return NAD1983HARNAdjWIOcontoMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOneidaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIOneidaFeet() {
    return NAD1983HARNAdjWIOneidaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOneidaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIOneidaMeters() {
    return NAD1983HARNAdjWIOneidaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOutagamieFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIOutagamieFeet() {
    return NAD1983HARNAdjWIOutagamieFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOutagamieMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIOutagamieMeters() {
    return NAD1983HARNAdjWIOutagamieMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOzaukeeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIOzaukeeFeet() {
    return NAD1983HARNAdjWIOzaukeeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOzaukeeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIOzaukeeMeters() {
    return NAD1983HARNAdjWIOzaukeeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPepinFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIPepinFeet() {
    return NAD1983HARNAdjWIPepinFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPepinMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIPepinMeters() {
    return NAD1983HARNAdjWIPepinMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPierceFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIPierceFeet() {
    return NAD1983HARNAdjWIPierceFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPierceMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIPierceMeters() {
    return NAD1983HARNAdjWIPierceMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPolkFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIPolkFeet() {
    return NAD1983HARNAdjWIPolkFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPolkMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIPolkMeters() {
    return NAD1983HARNAdjWIPolkMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPortageFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIPortageFeet() {
    return NAD1983HARNAdjWIPortageFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPortageMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIPortageMeters() {
    return NAD1983HARNAdjWIPortageMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPriceFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIPriceFeet() {
    return NAD1983HARNAdjWIPriceFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPriceMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIPriceMeters() {
    return NAD1983HARNAdjWIPriceMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRacineFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIRacineFeet() {
    return NAD1983HARNAdjWIRacineFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRacineMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIRacineMeters() {
    return NAD1983HARNAdjWIRacineMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRichlandFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIRichlandFeet() {
    return NAD1983HARNAdjWIRichlandFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRichlandMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIRichlandMeters() {
    return NAD1983HARNAdjWIRichlandMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRockFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIRockFeet() {
    return NAD1983HARNAdjWIRockFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRockMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIRockMeters() {
    return NAD1983HARNAdjWIRockMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRuskFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIRuskFeet() {
    return NAD1983HARNAdjWIRuskFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRuskMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIRuskMeters() {
    return NAD1983HARNAdjWIRuskMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWISaukFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWISaukFeet() {
    return NAD1983HARNAdjWISaukFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWISaukMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWISaukMeters() {
    return NAD1983HARNAdjWISaukMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWISawyerFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWISawyerFeet() {
    return NAD1983HARNAdjWISawyerFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWISawyerMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWISawyerMeters() {
    return NAD1983HARNAdjWISawyerMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIShawanoFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIShawanoFeet() {
    return NAD1983HARNAdjWIShawanoFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIShawanoMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIShawanoMeters() {
    return NAD1983HARNAdjWIShawanoMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWISheboyganFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWISheboyganFeet() {
    return NAD1983HARNAdjWISheboyganFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWISheboyganMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWISheboyganMeters() {
    return NAD1983HARNAdjWISheboyganMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIStCroixFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIStCroixFeet() {
    return NAD1983HARNAdjWIStCroixFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIStCroixMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIStCroixMeters() {
    return NAD1983HARNAdjWIStCroixMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWITaylorFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWITaylorFeet() {
    return NAD1983HARNAdjWITaylorFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWITaylorMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWITaylorMeters() {
    return NAD1983HARNAdjWITaylorMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWITrempealeauFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWITrempealeauFeet() {
    return NAD1983HARNAdjWITrempealeauFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWITrempealeauMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWITrempealeauMeters() {
    return NAD1983HARNAdjWITrempealeauMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIVernonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIVernonFeet() {
    return NAD1983HARNAdjWIVernonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIVernonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIVernonMeters() {
    return NAD1983HARNAdjWIVernonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIVilasFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIVilasFeet() {
    return NAD1983HARNAdjWIVilasFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIVilasMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIVilasMeters() {
    return NAD1983HARNAdjWIVilasMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWalworthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIWalworthFeet() {
    return NAD1983HARNAdjWIWalworthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWalworthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIWalworthMeters() {
    return NAD1983HARNAdjWIWalworthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWashburnFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIWashburnFeet() {
    return NAD1983HARNAdjWIWashburnFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWashburnMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIWashburnMeters() {
    return NAD1983HARNAdjWIWashburnMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWashingtonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIWashingtonFeet() {
    return NAD1983HARNAdjWIWashingtonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWashingtonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIWashingtonMeters() {
    return NAD1983HARNAdjWIWashingtonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWaukeshaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIWaukeshaFeet() {
    return NAD1983HARNAdjWIWaukeshaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWaukeshaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIWaukeshaMeters() {
    return NAD1983HARNAdjWIWaukeshaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWaupacaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIWaupacaFeet() {
    return NAD1983HARNAdjWIWaupacaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWaupacaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIWaupacaMeters() {
    return NAD1983HARNAdjWIWaupacaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWausharaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIWausharaFeet() {
    return NAD1983HARNAdjWIWausharaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWausharaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIWausharaMeters() {
    return NAD1983HARNAdjWIWausharaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWinnebagoFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIWinnebagoFeet() {
    return NAD1983HARNAdjWIWinnebagoFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWinnebagoMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIWinnebagoMeters() {
    return NAD1983HARNAdjWIWinnebagoMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWoodFeet
   */
  public ProjectionInfo getNAD1983HARNAdjWIWoodFeet() {
    return NAD1983HARNAdjWIWoodFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWoodMeters
   */
  public ProjectionInfo getNAD1983HARNAdjWIWoodMeters() {
    return NAD1983HARNAdjWIWoodMeters.copy();
  }
    }


