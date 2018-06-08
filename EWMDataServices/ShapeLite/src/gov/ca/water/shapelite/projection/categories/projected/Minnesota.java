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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:32:25 PM
//
// Contributor(s): (Open source contributors should list themselves and their modifications here).
//        Name         |    Date    |        Comment
// --------------------|------------|------------------------------------------------------------
// Jason Dick          |  12/5/2011 |  Corrected parameters for all counties.
// Ted Dunsford        |   5/3/2010 |  Updated project to DotSpatial.Projection and license to LGPL
// ********************************************************************************************************

package gov.ca.water.shapelite.projection.categories.projected;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
    /// <summary>
    /// Not sure why we have all these county based Minnesota and Wisconsin projections
    /// </summary>
    public class Minnesota extends CoordinateSystemCategory
    {
        //<editor-fold defaultstate="collapsed" desc="Fields">

        private final ProjectionInfo NAD1983HARNAdjMNAitkinFeet;
        private final ProjectionInfo NAD1983HARNAdjMNAitkinMeters;
        private final ProjectionInfo NAD1983HARNAdjMNAnokaFeet;
        private final ProjectionInfo NAD1983HARNAdjMNAnokaMeters;
        private final ProjectionInfo NAD1983HARNAdjMNBeckerFeet;
        private final ProjectionInfo NAD1983HARNAdjMNBeckerMeters;
        private final ProjectionInfo NAD1983HARNAdjMNBeltramiNorthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNBeltramiNorthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNBeltramiSouthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNBeltramiSouthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNBentonFeet;
        private final ProjectionInfo NAD1983HARNAdjMNBentonMeters;
        private final ProjectionInfo NAD1983HARNAdjMNBigStoneFeet;
        private final ProjectionInfo NAD1983HARNAdjMNBigStoneMeters;
        private final ProjectionInfo NAD1983HARNAdjMNBlueEarthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNBlueEarthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNBrownFeet;
        private final ProjectionInfo NAD1983HARNAdjMNBrownMeters;
        private final ProjectionInfo NAD1983HARNAdjMNCarltonFeet;
        private final ProjectionInfo NAD1983HARNAdjMNCarltonMeters;
        private final ProjectionInfo NAD1983HARNAdjMNCarverFeet;
        private final ProjectionInfo NAD1983HARNAdjMNCarverMeters;
        private final ProjectionInfo NAD1983HARNAdjMNCassNorthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNCassNorthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNCassSouthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNCassSouthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNChippewaFeet;
        private final ProjectionInfo NAD1983HARNAdjMNChippewaMeters;
        private final ProjectionInfo NAD1983HARNAdjMNChisagoFeet;
        private final ProjectionInfo NAD1983HARNAdjMNChisagoMeters;
        private final ProjectionInfo NAD1983HARNAdjMNClayFeet;
        private final ProjectionInfo NAD1983HARNAdjMNClayMeters;
        private final ProjectionInfo NAD1983HARNAdjMNClearwaterFeet;
        private final ProjectionInfo NAD1983HARNAdjMNClearwaterMeters;
        private final ProjectionInfo NAD1983HARNAdjMNCookNorthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNCookNorthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNCookSouthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNCookSouthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNCottonwoodFeet;
        private final ProjectionInfo NAD1983HARNAdjMNCottonwoodMeters;
        private final ProjectionInfo NAD1983HARNAdjMNCrowWingFeet;
        private final ProjectionInfo NAD1983HARNAdjMNCrowWingMeters;
        private final ProjectionInfo NAD1983HARNAdjMNDakotaFeet;
        private final ProjectionInfo NAD1983HARNAdjMNDakotaMeters;
        private final ProjectionInfo NAD1983HARNAdjMNDodgeFeet;
        private final ProjectionInfo NAD1983HARNAdjMNDodgeMeters;
        private final ProjectionInfo NAD1983HARNAdjMNDouglasFeet;
        private final ProjectionInfo NAD1983HARNAdjMNDouglasMeters;
        private final ProjectionInfo NAD1983HARNAdjMNFaribaultFeet;
        private final ProjectionInfo NAD1983HARNAdjMNFaribaultMeters;
        private final ProjectionInfo NAD1983HARNAdjMNFillmoreFeet;
        private final ProjectionInfo NAD1983HARNAdjMNFillmoreMeters;
        private final ProjectionInfo NAD1983HARNAdjMNFreebornFeet;
        private final ProjectionInfo NAD1983HARNAdjMNFreebornMeters;
        private final ProjectionInfo NAD1983HARNAdjMNGoodhueFeet;
        private final ProjectionInfo NAD1983HARNAdjMNGoodhueMeters;
        private final ProjectionInfo NAD1983HARNAdjMNGrantFeet;
        private final ProjectionInfo NAD1983HARNAdjMNGrantMeters;
        private final ProjectionInfo NAD1983HARNAdjMNHennepinFeet;
        private final ProjectionInfo NAD1983HARNAdjMNHennepinMeters;
        private final ProjectionInfo NAD1983HARNAdjMNHoustonFeet;
        private final ProjectionInfo NAD1983HARNAdjMNHoustonMeters;
        private final ProjectionInfo NAD1983HARNAdjMNHubbardFeet;
        private final ProjectionInfo NAD1983HARNAdjMNHubbardMeters;
        private final ProjectionInfo NAD1983HARNAdjMNIsantiFeet;
        private final ProjectionInfo NAD1983HARNAdjMNIsantiMeters;
        private final ProjectionInfo NAD1983HARNAdjMNItascaNorthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNItascaNorthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNItascaSouthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNItascaSouthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNJacksonFeet;
        private final ProjectionInfo NAD1983HARNAdjMNJacksonMeters;
        private final ProjectionInfo NAD1983HARNAdjMNKanabecFeet;
        private final ProjectionInfo NAD1983HARNAdjMNKanabecMeters;
        private final ProjectionInfo NAD1983HARNAdjMNKandiyohiFeet;
        private final ProjectionInfo NAD1983HARNAdjMNKandiyohiMeters;
        private final ProjectionInfo NAD1983HARNAdjMNKittsonFeet;
        private final ProjectionInfo NAD1983HARNAdjMNKittsonMeters;
        private final ProjectionInfo NAD1983HARNAdjMNKoochichingFeet;
        private final ProjectionInfo NAD1983HARNAdjMNKoochichingMeters;
        private final ProjectionInfo NAD1983HARNAdjMNLacQuiParleFeet;
        private final ProjectionInfo NAD1983HARNAdjMNLacQuiParleMeters;
        private final ProjectionInfo NAD1983HARNAdjMNLakeFeet;
        private final ProjectionInfo NAD1983HARNAdjMNLakeMeters;
        private final ProjectionInfo NAD1983HARNAdjMNLakeoftheWoodsNorthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNLakeoftheWoodsNorthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNLakeoftheWoodsSouthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNLakeoftheWoodsSouthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNLeSueurFeet;
        private final ProjectionInfo NAD1983HARNAdjMNLeSueurMeters;
        private final ProjectionInfo NAD1983HARNAdjMNLincolnFeet;
        private final ProjectionInfo NAD1983HARNAdjMNLincolnMeters;
        private final ProjectionInfo NAD1983HARNAdjMNLyonFeet;
        private final ProjectionInfo NAD1983HARNAdjMNLyonMeters;
        private final ProjectionInfo NAD1983HARNAdjMNMahnomenFeet;
        private final ProjectionInfo NAD1983HARNAdjMNMahnomenMeters;
        private final ProjectionInfo NAD1983HARNAdjMNMarshallFeet;
        private final ProjectionInfo NAD1983HARNAdjMNMarshallMeters;
        private final ProjectionInfo NAD1983HARNAdjMNMartinFeet;
        private final ProjectionInfo NAD1983HARNAdjMNMartinMeters;
        private final ProjectionInfo NAD1983HARNAdjMNMcLeodFeet;
        private final ProjectionInfo NAD1983HARNAdjMNMcLeodMeters;
        private final ProjectionInfo NAD1983HARNAdjMNMeekerFeet;
        private final ProjectionInfo NAD1983HARNAdjMNMeekerMeters;
        private final ProjectionInfo NAD1983HARNAdjMNMilleLacsFeet;
        private final ProjectionInfo NAD1983HARNAdjMNMilleLacsMeters;
        private final ProjectionInfo NAD1983HARNAdjMNMorrisonFeet;
        private final ProjectionInfo NAD1983HARNAdjMNMorrisonMeters;
        private final ProjectionInfo NAD1983HARNAdjMNMowerFeet;
        private final ProjectionInfo NAD1983HARNAdjMNMowerMeters;
        private final ProjectionInfo NAD1983HARNAdjMNMurrayFeet;
        private final ProjectionInfo NAD1983HARNAdjMNMurrayMeters;
        private final ProjectionInfo NAD1983HARNAdjMNNicolletFeet;
        private final ProjectionInfo NAD1983HARNAdjMNNicolletMeters;
        private final ProjectionInfo NAD1983HARNAdjMNNoblesFeet;
        private final ProjectionInfo NAD1983HARNAdjMNNoblesMeters;
        private final ProjectionInfo NAD1983HARNAdjMNNormanFeet;
        private final ProjectionInfo NAD1983HARNAdjMNNormanMeters;
        private final ProjectionInfo NAD1983HARNAdjMNOlmstedFeet;
        private final ProjectionInfo NAD1983HARNAdjMNOlmstedMeters;
        private final ProjectionInfo NAD1983HARNAdjMNOttertailFeet;
        private final ProjectionInfo NAD1983HARNAdjMNOttertailMeters;
        private final ProjectionInfo NAD1983HARNAdjMNPenningtonFeet;
        private final ProjectionInfo NAD1983HARNAdjMNPenningtonMeters;
        private final ProjectionInfo NAD1983HARNAdjMNPineFeet;
        private final ProjectionInfo NAD1983HARNAdjMNPineMeters;
        private final ProjectionInfo NAD1983HARNAdjMNPipestoneFeet;
        private final ProjectionInfo NAD1983HARNAdjMNPipestoneMeters;
        private final ProjectionInfo NAD1983HARNAdjMNPolkFeet;
        private final ProjectionInfo NAD1983HARNAdjMNPolkMeters;
        private final ProjectionInfo NAD1983HARNAdjMNPopeFeet;
        private final ProjectionInfo NAD1983HARNAdjMNPopeMeters;
        private final ProjectionInfo NAD1983HARNAdjMNRamseyFeet;
        private final ProjectionInfo NAD1983HARNAdjMNRamseyMeters;
        private final ProjectionInfo NAD1983HARNAdjMNRedLakeFeet;
        private final ProjectionInfo NAD1983HARNAdjMNRedLakeMeters;
        private final ProjectionInfo NAD1983HARNAdjMNRedwoodFeet;
        private final ProjectionInfo NAD1983HARNAdjMNRedwoodMeters;
        private final ProjectionInfo NAD1983HARNAdjMNRenvilleFeet;
        private final ProjectionInfo NAD1983HARNAdjMNRenvilleMeters;
        private final ProjectionInfo NAD1983HARNAdjMNRiceFeet;
        private final ProjectionInfo NAD1983HARNAdjMNRiceMeters;
        private final ProjectionInfo NAD1983HARNAdjMNRockFeet;
        private final ProjectionInfo NAD1983HARNAdjMNRockMeters;
        private final ProjectionInfo NAD1983HARNAdjMNRoseauFeet;
        private final ProjectionInfo NAD1983HARNAdjMNRoseauMeters;
        private final ProjectionInfo NAD1983HARNAdjMNScottFeet;
        private final ProjectionInfo NAD1983HARNAdjMNScottMeters;
        private final ProjectionInfo NAD1983HARNAdjMNSherburneFeet;
        private final ProjectionInfo NAD1983HARNAdjMNSherburneMeters;
        private final ProjectionInfo NAD1983HARNAdjMNSibleyFeet;
        private final ProjectionInfo NAD1983HARNAdjMNSibleyMeters;
        private final ProjectionInfo NAD1983HARNAdjMNStLouisCentralFeet;
        private final ProjectionInfo NAD1983HARNAdjMNStLouisCentralMeters;
        private final ProjectionInfo NAD1983HARNAdjMNStLouisNorthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNStLouisNorthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNStLouisSouthFeet;
        private final ProjectionInfo NAD1983HARNAdjMNStLouisSouthMeters;
        private final ProjectionInfo NAD1983HARNAdjMNStearnsFeet;
        private final ProjectionInfo NAD1983HARNAdjMNStearnsMeters;
        private final ProjectionInfo NAD1983HARNAdjMNSteeleFeet;
        private final ProjectionInfo NAD1983HARNAdjMNSteeleMeters;
        private final ProjectionInfo NAD1983HARNAdjMNStevensFeet;
        private final ProjectionInfo NAD1983HARNAdjMNStevensMeters;
        private final ProjectionInfo NAD1983HARNAdjMNSwiftFeet;
        private final ProjectionInfo NAD1983HARNAdjMNSwiftMeters;
        private final ProjectionInfo NAD1983HARNAdjMNToddFeet;
        private final ProjectionInfo NAD1983HARNAdjMNToddMeters;
        private final ProjectionInfo NAD1983HARNAdjMNTraverseFeet;
        private final ProjectionInfo NAD1983HARNAdjMNTraverseMeters;
        private final ProjectionInfo NAD1983HARNAdjMNWabashaFeet;
        private final ProjectionInfo NAD1983HARNAdjMNWabashaMeters;
        private final ProjectionInfo NAD1983HARNAdjMNWadenaFeet;
        private final ProjectionInfo NAD1983HARNAdjMNWadenaMeters;
        private final ProjectionInfo NAD1983HARNAdjMNWasecaFeet;
        private final ProjectionInfo NAD1983HARNAdjMNWasecaMeters;
        private final ProjectionInfo NAD1983HARNAdjMNWashingtonFeet;
        private final ProjectionInfo NAD1983HARNAdjMNWashingtonMeters;
        private final ProjectionInfo NAD1983HARNAdjMNWatonwanFeet;
        private final ProjectionInfo NAD1983HARNAdjMNWatonwanMeters;
        private final ProjectionInfo NAD1983HARNAdjMNWilkinFeet;
        private final ProjectionInfo NAD1983HARNAdjMNWilkinMeters;
        private final ProjectionInfo NAD1983HARNAdjMNWinonaFeet;
        private final ProjectionInfo NAD1983HARNAdjMNWinonaMeters;
        private final ProjectionInfo NAD1983HARNAdjMNWrightFeet;
        private final ProjectionInfo NAD1983HARNAdjMNWrightMeters;
        private final ProjectionInfo NAD1983HARNAdjMNYellowMedicineFeet;
        private final ProjectionInfo NAD1983HARNAdjMNYellowMedicineMeters;

        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Constructor">

        /// <summary>
        /// Creates a new instance of Minnesota
        /// </summary>
        public Minnesota()
        {
            NAD1983HARNAdjMNAitkinFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=46.1541666666667 +lon_0=-93.4325 +k=1.000059152669 +x_0=152409.3196 +y_0=30481.8640 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNAitkinMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=46.1541666666667 +lon_0=-93.4325 +k=1.000059152669 +x_0=152409.3196 +y_0=30481.8640 +ellps=GRS80 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNAnokaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.0666666666667 +lat_2=45.3666666666667 +lat_0=45.0352777777778 +lon_0=-93.2666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378418.941 +b=6357033.310 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNAnokaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.0666666666667 +lat_2=45.3666666666667 +lat_0=45.0352777777778 +lon_0=-93.2666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378418.941 +b=6357033.310 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNBeckerFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.7833333333333 +lat_2=47.0833333333333 +lat_0=46.7177777777778 +lon_0=-95.6833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378586.581 +b=6357200.388 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNBeckerMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.7833333333333 +lat_2=47.0833333333333 +lat_0=46.7177777777778 +lon_0=-95.6833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378586.581 +b=6357200.388 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNBeltramiNorthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48.1166666666667 +lat_2=48.4666666666667 +lat_0=48.02 +lon_0=-95.0166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378505.809 +b=6357119.886 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNBeltramiNorthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48.1166666666667 +lat_2=48.4666666666667 +lat_0=48.02 +lon_0=-95.0166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378505.809 +b=6357119.886 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNBeltramiSouthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.5 +lat_2=47.9166666666667 +lat_0=47.4125 +lon_0=-94.85 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378544.823 +b=6357158.770 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNBeltramiSouthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.5 +lat_2=47.9166666666667 +lat_0=47.4125 +lon_0=-94.85 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378544.823 +b=6357158.770 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNBentonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.5833333333333 +lat_2=45.7833333333333 +lat_0=45.5591666666667 +lon_0=-94.05 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378490.569 +b=6357104.698 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNBentonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.5833333333333 +lat_2=45.7833333333333 +lat_0=45.5591666666667 +lon_0=-94.05 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378490.569 +b=6357104.698 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNBigStoneFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.2166666666667 +lat_2=45.5333333333333 +lat_0=45.1522222222222 +lon_0=-96.05 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378470.757 +b=6357084.952 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNBigStoneMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.2166666666667 +lat_2=45.5333333333333 +lat_0=45.1522222222222 +lon_0=-96.05 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378470.757 +b=6357084.952 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNBlueEarthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9333333333333 +lat_2=44.3666666666667 +lat_0=43.8480555555556 +lon_0=-94.2666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378403.701 +b=6357018.121 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNBlueEarthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9333333333333 +lat_2=44.3666666666667 +lat_0=43.8480555555556 +lon_0=-94.2666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378403.701 +b=6357018.121 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNBrownFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.1666666666667 +lat_2=44.4666666666667 +lat_0=44.1080555555556 +lon_0=-94.7333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378434.181 +b=6357048.499 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNBrownMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.1666666666667 +lat_2=44.4666666666667 +lat_0=44.1080555555556 +lon_0=-94.7333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378434.181 +b=6357048.499 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNCarltonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.4666666666667 +lat_2=46.7333333333333 +lat_0=46.4172222222222 +lon_0=-92.6833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378454.907 +b=6357069.155 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNCarltonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.4666666666667 +lat_2=46.7333333333333 +lat_0=46.4172222222222 +lon_0=-92.6833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378454.907 +b=6357069.155 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNCarverFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.6833333333333 +lat_2=44.9 +lat_0=44.6397222222222 +lon_0=-93.7666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378400.653 +b=6357015.083 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNCarverMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.6833333333333 +lat_2=44.9 +lat_0=44.6397222222222 +lon_0=-93.7666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378400.653 +b=6357015.083 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNCassNorthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.9166666666667 +lat_2=47.3166666666667 +lat_0=46.8036111111111 +lon_0=-94.2166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378567.378 +b=6357181.249 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNCassNorthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.9166666666667 +lat_2=47.3166666666667 +lat_0=46.8036111111111 +lon_0=-94.2166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378567.378 +b=6357181.249 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNCassSouthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.2666666666667 +lat_2=46.7333333333333 +lat_0=46.1563888888889 +lon_0=-94.4666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378546.957 +b=6357160.896 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNCassSouthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.2666666666667 +lat_2=46.7333333333333 +lat_0=46.1563888888889 +lon_0=-94.4666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378546.957 +b=6357160.896 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNChippewaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.8333333333333 +lat_2=45.2 +lat_0=44.7527777777778 +lon_0=-95.85 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378476.853 +b=6357091.028 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNChippewaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.8333333333333 +lat_2=45.2 +lat_0=44.7527777777778 +lon_0=-95.85 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378476.853 +b=6357091.028 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNChisagoFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.3333333333333 +lat_2=45.6666666666667 +lat_0=45.2963888888889 +lon_0=-93.0833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378411.321 +b=6357025.715 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNChisagoMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.3333333333333 +lat_2=45.6666666666667 +lat_0=45.2963888888889 +lon_0=-93.0833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378411.321 +b=6357025.715 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNClayFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=46.63 +lon_0=-96.7 +k=1.000045317862 +x_0=152407.2110 +y_0=30481.4423 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNClayMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=46.63 +lon_0=-96.7 +k=1.000045317862 +x_0=152407.2110 +y_0=30481.4423 +ellps=GRS80 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNClearwaterFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=47.1516666666667 +lon_0=-95.3758333333333 +k=1.000072505661 +x_0=152411.3547 +y_0=30482.2708 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNClearwaterMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=47.1516666666667 +lon_0=-95.3758333333333 +k=1.000072505661 +x_0=152411.3547 +y_0=30482.2708 +ellps=GRS80 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNCookNorthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.9333333333333 +lat_2=48.1666666666667 +lat_0=47.8833333333333 +lon_0=-90.25 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378647.541 +b=6357261.143 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNCookNorthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.9333333333333 +lat_2=48.1666666666667 +lat_0=47.8833333333333 +lon_0=-90.25 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378647.541 +b=6357261.143 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNCookSouthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.55 +lat_2=47.8166666666667 +lat_0=47.4388888888889 +lon_0=-90.25 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378647.541 +b=6357261.143 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNCookSouthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.55 +lat_2=47.8166666666667 +lat_0=47.4388888888889 +lon_0=-90.25 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378647.541 +b=6357261.143 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNCottonwoodFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9 +lat_2=44.1666666666667 +lat_0=43.8480555555556 +lon_0=-94.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378514.953 +b=6357129.000 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNCottonwoodMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9 +lat_2=44.1666666666667 +lat_0=43.8480555555556 +lon_0=-94.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378514.953 +b=6357129.000 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNCrowWingFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.2666666666667 +lat_2=46.7333333333333 +lat_0=46.1563888888889 +lon_0=-94.4666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378546.957 +b=6357160.896 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNCrowWingMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.2666666666667 +lat_2=46.7333333333333 +lat_0=46.1563888888889 +lon_0=-94.4666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378546.957 +b=6357160.896 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNDakotaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.5166666666667 +lat_2=44.9166666666667 +lat_0=44.4719444444444 +lon_0=-93.3166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378421.989 +b=6357036.347 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNDakotaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.5166666666667 +lat_2=44.9166666666667 +lat_0=44.4719444444444 +lon_0=-93.3166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378421.989 +b=6357036.347 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNDodgeFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.8833333333333 +lat_2=44.1333333333333 +lat_0=43.8338888888889 +lon_0=-92.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378481.425 +b=6357095.584 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNDodgeMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.8833333333333 +lat_2=44.1333333333333 +lat_0=43.8338888888889 +lon_0=-92.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378481.425 +b=6357095.584 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNDouglasFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.8 +lat_2=46.05 +lat_0=45.7588888888889 +lon_0=-96.05 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378518.001 +b=6357132.038 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNDouglasMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.8 +lat_2=46.05 +lat_0=45.7588888888889 +lon_0=-96.05 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378518.001 +b=6357132.038 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNFaribaultFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-93.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378521.049 +b=6357135.075 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNFaribaultMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-93.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378521.049 +b=6357135.075 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNFillmoreFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.55 +lat_2=43.8 +lat_0=43.5 +lon_0=-92.0833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378464.661 +b=6357078.876 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNFillmoreMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.55 +lat_2=43.8 +lat_0=43.5 +lon_0=-92.0833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378464.661 +b=6357078.876 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNFreebornFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-93.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378521.049 +b=6357135.075 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNFreebornMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-93.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378521.049 +b=6357135.075 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNGoodhueFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.3 +lat_2=44.6666666666667 +lat_0=44.1947222222222 +lon_0=-93.1333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378434.181 +b=6357048.499 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNGoodhueMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.3 +lat_2=44.6666666666667 +lat_0=44.1947222222222 +lon_0=-93.1333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378434.181 +b=6357048.499 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNGrantFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.8 +lat_2=46.05 +lat_0=45.7588888888889 +lon_0=-96.05 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378518.001 +b=6357132.038 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNGrantMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.8 +lat_2=46.05 +lat_0=45.7588888888889 +lon_0=-96.05 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378518.001 +b=6357132.038 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNHennepinFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.8833333333333 +lat_2=45.1333333333333 +lat_0=44.7911111111111 +lon_0=-93.3833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378418.941 +b=6357033.310 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNHennepinMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.8833333333333 +lat_2=45.1333333333333 +lat_0=44.7911111111111 +lon_0=-93.3833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378418.941 +b=6357033.310 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNHoustonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-91.4666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378436.619 +b=6357050.928 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNHoustonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-91.4666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378436.619 +b=6357050.928 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNHubbardFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=46.8036111111111 +lon_0=-94.9205555555556 +k=1.000071553661 +x_0=152411.2097 +y_0=30482.2416 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNHubbardMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=46.8036111111111 +lon_0=-94.9205555555556 +k=1.000071553661 +x_0=152411.2097 +y_0=30482.2416 +ellps=GRS80 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNIsantiFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.3333333333333 +lat_2=45.6666666666667 +lat_0=45.2963888888889 +lon_0=-93.0833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378411.321 +b=6357025.715 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNIsantiMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.3333333333333 +lat_2=45.6666666666667 +lat_0=45.2963888888889 +lon_0=-93.0833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378411.321 +b=6357025.715 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNItascaNorthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.5666666666667 +lat_2=47.8166666666667 +lat_0=47.5 +lon_0=-93.7333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378574.389 +b=6357188.237 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNItascaNorthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.5666666666667 +lat_2=47.8166666666667 +lat_0=47.5 +lon_0=-93.7333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378574.389 +b=6357188.237 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNItascaSouthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.0833333333333 +lat_2=47.4166666666667 +lat_0=47.0263888888889 +lon_0=-93.7333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378574.389 +b=6357188.237 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNItascaSouthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.0833333333333 +lat_2=47.4166666666667 +lat_0=47.0263888888889 +lon_0=-93.7333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378574.389 +b=6357188.237 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNJacksonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-93.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378521.049 +b=6357135.075 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNJacksonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-93.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378521.049 +b=6357135.075 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNKanabecFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.8166666666667 +lat_2=46.3333333333333 +lat_0=45.73 +lon_0=-92.9 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378472.281 +b=6357086.471 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNKanabecMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.8166666666667 +lat_2=46.3333333333333 +lat_0=45.73 +lon_0=-92.9 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378472.281 +b=6357086.471 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNKandiyohiFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.9666666666667 +lat_2=45.3333333333333 +lat_0=44.8913888888889 +lon_0=-94.75 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378498.189 +b=6357112.292 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNKandiyohiMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.9666666666667 +lat_2=45.3333333333333 +lat_0=44.8913888888889 +lon_0=-94.75 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378498.189 +b=6357112.292 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNKittsonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48.6 +lat_2=48.9333333333333 +lat_0=48.5438888888889 +lon_0=-96.15 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378449.421 +b=6357063.688 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNKittsonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48.6 +lat_2=48.9333333333333 +lat_0=48.5438888888889 +lon_0=-96.15 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378449.421 +b=6357063.688 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNKoochichingFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48 +lat_2=48.6166666666667 +lat_0=47.8458333333333 +lon_0=-93.75 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378525.621 +b=6357139.632 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNKoochichingMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48 +lat_2=48.6166666666667 +lat_0=47.8458333333333 +lon_0=-93.75 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378525.621 +b=6357139.632 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNLacQuiParleFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.8333333333333 +lat_2=45.2 +lat_0=44.7527777777778 +lon_0=-95.85 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378476.853 +b=6357091.028 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNLacQuiParleMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.8333333333333 +lat_2=45.2 +lat_0=44.7527777777778 +lon_0=-95.85 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378476.853 +b=6357091.028 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNLakeFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=47.0666666666667 +lon_0=-91.4091666666667 +k=1.000075844621 +x_0=152411.8636 +y_0=30482.3728 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNLakeMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=47.0666666666667 +lon_0=-91.4091666666667 +k=1.000075844621 +x_0=152411.8636 +y_0=30482.3728 +ellps=GRS80 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNLakeoftheWoodsNorthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=49.1833333333333 +lat_2=49.3333333333333 +lat_0=49.15 +lon_0=-94.9833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378466.185 +b=6357080.395 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNLakeoftheWoodsNorthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=49.1833333333333 +lat_2=49.3333333333333 +lat_0=49.15 +lon_0=-94.9833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378466.185 +b=6357080.395 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNLakeoftheWoodsSouthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48.45 +lat_2=48.8833333333333 +lat_0=48.3661111111111 +lon_0=-94.8833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378496.665 +b=6357110.773 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNLakeoftheWoodsSouthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48.45 +lat_2=48.8833333333333 +lat_0=48.3661111111111 +lon_0=-94.8833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378496.665 +b=6357110.773 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNLeSueurFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.3 +lat_2=44.6666666666667 +lat_0=44.1947222222222 +lon_0=-93.1333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378434.181 +b=6357048.499 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNLeSueurMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.3 +lat_2=44.6666666666667 +lat_0=44.1947222222222 +lon_0=-93.1333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378434.181 +b=6357048.499 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNLincolnFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.2833333333333 +lat_2=44.6166666666667 +lat_0=44.1966666666667 +lon_0=-96.2666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378643.579 +b=6357257.195 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNLincolnMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.2833333333333 +lat_2=44.6166666666667 +lat_0=44.1966666666667 +lon_0=-96.2666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378643.579 +b=6357257.195 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNLyonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.25 +lat_2=44.5833333333333 +lat_0=44.1955555555556 +lon_0=-95.85 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378559.758 +b=6357173.655 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNLyonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.25 +lat_2=44.5833333333333 +lat_0=44.1955555555556 +lon_0=-95.85 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378559.758 +b=6357173.655 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNMcLeodFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.5333333333333 +lat_2=44.9166666666667 +lat_0=44.4561111111111 +lon_0=-94.6333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378414.369 +b=6357028.753 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNMcLeodMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.5333333333333 +lat_2=44.9166666666667 +lat_0=44.4561111111111 +lon_0=-94.6333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378414.369 +b=6357028.753 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNMahnomenFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.2 +lat_2=47.45 +lat_0=47.1516666666667 +lon_0=-95.8166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378586.581 +b=6357200.388 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNMahnomenMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.2 +lat_2=47.45 +lat_0=47.1516666666667 +lon_0=-95.8166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378586.581 +b=6357200.388 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNMarshallFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48.2333333333333 +lat_2=48.4833333333333 +lat_0=48.1730555555556 +lon_0=-96.3833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378441.801 +b=6357056.093 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNMarshallMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48.2333333333333 +lat_2=48.4833333333333 +lat_0=48.1730555555556 +lon_0=-96.3833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378441.801 +b=6357056.093 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNMartinFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-93.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378521.049 +b=6357135.075 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNMartinMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-93.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378521.049 +b=6357135.075 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNMeekerFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.9666666666667 +lat_2=45.3333333333333 +lat_0=44.8913888888889 +lon_0=-94.75 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378498.189 +b=6357112.292 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNMeekerMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.9666666666667 +lat_2=45.3333333333333 +lat_0=44.8913888888889 +lon_0=-94.75 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378498.189 +b=6357112.292 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNMilleLacsFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.5588888888889 +lon_0=-93.6205555555556 +k=1.000054146138 +x_0=152408.5566 +y_0=30481.7112 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNMilleLacsMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=45.5588888888889 +lon_0=-93.6205555555556 +k=1.000054146138 +x_0=152408.5566 +y_0=30481.7112 +ellps=GRS80 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNMorrisonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.85 +lat_2=46.2666666666667 +lat_0=45.7738888888889 +lon_0=-94.2 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378502.761 +b=6357116.849 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNMorrisonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.85 +lat_2=46.2666666666667 +lat_0=45.7738888888889 +lon_0=-94.2 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378502.761 +b=6357116.849 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNMowerFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-93.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378521.049 +b=6357135.075 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNMowerMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-93.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378521.049 +b=6357135.075 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNMurrayFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9166666666667 +lat_2=44.1666666666667 +lat_0=43.8480555555556 +lon_0=-95.7666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378617.061 +b=6357230.765 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNMurrayMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9166666666667 +lat_2=44.1666666666667 +lat_0=43.8480555555556 +lon_0=-95.7666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378617.061 +b=6357230.765 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNNicolletFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9333333333333 +lat_2=44.3666666666667 +lat_0=43.8480555555556 +lon_0=-94.2666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378403.701 +b=6357018.121 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNNicolletMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9333333333333 +lat_2=44.3666666666667 +lat_0=43.8480555555556 +lon_0=-94.2666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378403.701 +b=6357018.121 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNNoblesFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-95.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378624.681 +b=6357238.360 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNNoblesMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-95.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378624.681 +b=6357238.360 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNNormanFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.2 +lat_2=47.45 +lat_0=47.1505555555556 +lon_0=-96.45 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378468.623 +b=6357082.825 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNNormanMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.2 +lat_2=47.45 +lat_0=47.1505555555556 +lon_0=-96.45 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378468.623 +b=6357082.825 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNOlmstedFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.8833333333333 +lat_2=44.1333333333333 +lat_0=43.8338888888889 +lon_0=-92.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378481.425 +b=6357095.584 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNOlmstedMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.8833333333333 +lat_2=44.1333333333333 +lat_0=43.8338888888889 +lon_0=-92.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378481.425 +b=6357095.584 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNOttertailFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.1833333333333 +lat_2=46.65 +lat_0=46.1063888888889 +lon_0=-95.7166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378525.621 +b=6357139.632 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNOttertailMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.1833333333333 +lat_2=46.65 +lat_0=46.1063888888889 +lon_0=-95.7166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378525.621 +b=6357139.632 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNPenningtonFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.6 +lat_2=48.0833333333333 +lat_0=47.4988888888889 +lon_0=-96.3666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378445.763 +b=6357060.042 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNPenningtonMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.6 +lat_2=48.0833333333333 +lat_0=47.4988888888889 +lon_0=-96.3666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378445.763 +b=6357060.042 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNPineFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.8166666666667 +lat_2=46.3333333333333 +lat_0=45.73 +lon_0=-92.9 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378472.281 +b=6357086.471 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNPineMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.8166666666667 +lat_2=46.3333333333333 +lat_0=45.73 +lon_0=-92.9 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378472.281 +b=6357086.471 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNPipestoneFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.8833333333333 +lat_2=44.15 +lat_0=43.8491666666667 +lon_0=-96.25 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378670.401 +b=6357283.927 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNPipestoneMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.8833333333333 +lat_2=44.15 +lat_0=43.8491666666667 +lon_0=-96.25 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378670.401 +b=6357283.927 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNPolkFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.6 +lat_2=48.0833333333333 +lat_0=47.4988888888889 +lon_0=-96.3666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378445.763 +b=6357060.042 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNPolkMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.6 +lat_2=48.0833333333333 +lat_0=47.4988888888889 +lon_0=-96.3666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378445.763 +b=6357060.042 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNPopeFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.35 +lat_2=45.7 +lat_0=45.2827777777778 +lon_0=-95.15 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378502.761 +b=6357116.849 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNPopeMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.35 +lat_2=45.7 +lat_0=45.2827777777778 +lon_0=-95.15 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378502.761 +b=6357116.849 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNRamseyFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.8833333333333 +lat_2=45.1333333333333 +lat_0=44.7911111111111 +lon_0=-93.3833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378418.941 +b=6357033.310 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNRamseyMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.8833333333333 +lat_2=45.1333333333333 +lat_0=44.7911111111111 +lon_0=-93.3833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378418.941 +b=6357033.310 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNRedLakeFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.6 +lat_2=48.0833333333333 +lat_0=47.4988888888889 +lon_0=-96.3666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378445.763 +b=6357060.042 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNRedLakeMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.6 +lat_2=48.0833333333333 +lat_0=47.4988888888889 +lon_0=-96.3666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378445.763 +b=6357060.042 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNRedwoodFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.2666666666667 +lat_2=44.5666666666667 +lat_0=44.1947222222222 +lon_0=-95.2333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378438.753 +b=6357053.055 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNRedwoodMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.2666666666667 +lat_2=44.5666666666667 +lat_0=44.1947222222222 +lon_0=-95.2333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378438.753 +b=6357053.055 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNRenvilleFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.5333333333333 +lat_2=44.9166666666667 +lat_0=44.4561111111111 +lon_0=-94.6333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378414.369 +b=6357028.753 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNRenvilleMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.5333333333333 +lat_2=44.9166666666667 +lat_0=44.4561111111111 +lon_0=-94.6333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378414.369 +b=6357028.753 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNRiceFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.3 +lat_2=44.6666666666667 +lat_0=44.1947222222222 +lon_0=-93.1333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378434.181 +b=6357048.499 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNRiceMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.3 +lat_2=44.6666666666667 +lat_0=44.1947222222222 +lon_0=-93.1333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378434.181 +b=6357048.499 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNRockFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-95.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378624.681 +b=6357238.360 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNRockMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.5666666666667 +lat_2=43.8 +lat_0=43.5 +lon_0=-95.95 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378624.681 +b=6357238.360 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNRoseauFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48.6 +lat_2=48.9333333333333 +lat_0=48.5438888888889 +lon_0=-96.15 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378449.421 +b=6357063.688 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNRoseauMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=48.6 +lat_2=48.9333333333333 +lat_0=48.5438888888889 +lon_0=-96.15 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378449.421 +b=6357063.688 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNStLouisNorthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.9833333333333 +lat_2=48.5333333333333 +lat_0=47.8333333333333 +lon_0=-92.45 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378543.909 +b=6357157.859 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNStLouisNorthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.9833333333333 +lat_2=48.5333333333333 +lat_0=47.8333333333333 +lon_0=-92.45 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378543.909 +b=6357157.859 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNStLouisCentralFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.3333333333333 +lat_2=47.75 +lat_0=47.25 +lon_0=-92.45 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378605.783 +b=6357219.525 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNStLouisCentralMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.3333333333333 +lat_2=47.75 +lat_0=47.25 +lon_0=-92.45 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378605.783 +b=6357219.525 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNStLouisSouthFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.7833333333333 +lat_2=47.1333333333333 +lat_0=46.65 +lon_0=-92.45 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378540.861 +b=6357154.821 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNStLouisSouthMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.7833333333333 +lat_2=47.1333333333333 +lat_0=46.65 +lon_0=-92.45 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378540.861 +b=6357154.821 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNScottFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.5166666666667 +lat_2=44.9166666666667 +lat_0=44.4719444444444 +lon_0=-93.3166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378421.989 +b=6357036.347 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNScottMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.5166666666667 +lat_2=44.9166666666667 +lat_0=44.4719444444444 +lon_0=-93.3166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378421.989 +b=6357036.347 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNSherburneFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.0333333333333 +lat_2=45.4666666666667 +lat_0=44.9775 +lon_0=-93.8833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378443.325 +b=6357057.612 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNSherburneMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.0333333333333 +lat_2=45.4666666666667 +lat_0=44.9775 +lon_0=-93.8833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378443.325 +b=6357057.612 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNSibleyFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.5333333333333 +lat_2=44.9166666666667 +lat_0=44.4561111111111 +lon_0=-94.6333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378414.369 +b=6357028.753 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNSibleyMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.5333333333333 +lat_2=44.9166666666667 +lat_0=44.4561111111111 +lon_0=-94.6333333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378414.369 +b=6357028.753 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNStearnsFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.35 +lat_2=45.7 +lat_0=45.2827777777778 +lon_0=-95.15 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378502.761 +b=6357116.849 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNStearnsMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.35 +lat_2=45.7 +lat_0=45.2827777777778 +lon_0=-95.15 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378502.761 +b=6357116.849 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNSteeleFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.8833333333333 +lat_2=44.1333333333333 +lat_0=43.8338888888889 +lon_0=-92.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378481.425 +b=6357095.584 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNSteeleMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.8833333333333 +lat_2=44.1333333333333 +lat_0=43.8338888888889 +lon_0=-92.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378481.425 +b=6357095.584 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNStevensFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.35 +lat_2=45.7 +lat_0=45.2827777777778 +lon_0=-95.15 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378502.761 +b=6357116.849 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNStevensMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.35 +lat_2=45.7 +lat_0=45.2827777777778 +lon_0=-95.15 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378502.761 +b=6357116.849 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNSwiftFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.2166666666667 +lat_2=45.5333333333333 +lat_0=45.1522222222222 +lon_0=-96.05 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378470.757 +b=6357084.952 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNSwiftMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.2166666666667 +lat_2=45.5333333333333 +lat_0=45.1522222222222 +lon_0=-96.05 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378470.757 +b=6357084.952 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNToddFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.8666666666667 +lat_2=46.2833333333333 +lat_0=45.7733333333333 +lon_0=-94.9 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378548.481 +b=6357162.415 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNToddMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.8666666666667 +lat_2=46.2833333333333 +lat_0=45.7733333333333 +lon_0=-94.9 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378548.481 +b=6357162.415 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNTraverseFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.6333333333333 +lat_2=45.9666666666667 +lat_0=45.5855555555556 +lon_0=-96.55 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378463.746 +b=6357077.964 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNTraverseMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.6333333333333 +lat_2=45.9666666666667 +lat_0=45.5855555555556 +lon_0=-96.55 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378463.746 +b=6357077.964 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNWabashaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.15 +lat_2=44.4166666666667 +lat_0=44.1069444444444 +lon_0=-92.2666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378426.561 +b=6357040.904 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNWabashaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.15 +lat_2=44.4166666666667 +lat_0=44.1069444444444 +lon_0=-92.2666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378426.561 +b=6357040.904 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNWadenaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.2666666666667 +lat_2=46.7333333333333 +lat_0=46.1563888888889 +lon_0=-94.4666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378546.957 +b=6357160.896 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNWadenaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.2666666666667 +lat_2=46.7333333333333 +lat_0=46.1563888888889 +lon_0=-94.4666666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378546.957 +b=6357160.896 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNWasecaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.8833333333333 +lat_2=44.1333333333333 +lat_0=43.8338888888889 +lon_0=-92.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378481.425 +b=6357095.584 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNWasecaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.8833333333333 +lat_2=44.1333333333333 +lat_0=43.8338888888889 +lon_0=-92.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378481.425 +b=6357095.584 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNWashingtonFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.7458333333333 +lon_0=-92.8333333333333 +k=1.000039836799 +x_0=152406.3759 +y_0=30481.2751 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNWashingtonMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=44.7458333333333 +lon_0=-92.8333333333333 +k=1.000039836799 +x_0=152406.3759 +y_0=30481.2751 +ellps=GRS80 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNWatonwanFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9 +lat_2=44.1666666666667 +lat_0=43.8480555555556 +lon_0=-94.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378514.953 +b=6357129.000 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNWatonwanMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9 +lat_2=44.1666666666667 +lat_0=43.8480555555556 +lon_0=-94.9166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378514.953 +b=6357129.000 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNWilkinFeet = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=46.0216666666667 +lon_0=-96.5244444444444 +k=1.000048901066 +x_0=152407.7567 +y_0=30481.5511 +ellps=GRS80 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNWilkinMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=46.0216666666667 +lon_0=-96.5244444444444 +k=1.000048901066 +x_0=152407.7567 +y_0=30481.5511 +ellps=GRS80 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNWinonaFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9 +lat_2=44.1333333333333 +lat_0=43.8472222222222 +lon_0=-91.6166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378453.688 +b=6357067.940 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNWinonaMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=43.9 +lat_2=44.1333333333333 +lat_0=43.8472222222222 +lon_0=-91.6166666666667 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378453.688 +b=6357067.940 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNWrightFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.0333333333333 +lat_2=45.4666666666667 +lat_0=44.9775 +lon_0=-93.8833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378443.325 +b=6357057.612 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNWrightMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.0333333333333 +lat_2=45.4666666666667 +lat_0=44.9775 +lon_0=-93.8833333333333 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378443.325 +b=6357057.612 +units=m +no_defs").orElse(null);
            NAD1983HARNAdjMNYellowMedicineFeet = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.6666666666667 +lat_2=44.95 +lat_0=44.5416666666667 +lon_0=-95.9 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378530.193 +b=6357144.189 +to_meter=0.3048006096012192 +no_defs").orElse(null);
            NAD1983HARNAdjMNYellowMedicineMeters = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.6666666666667 +lat_2=44.95 +lat_0=44.5416666666667 +lon_0=-95.9 +x_0=152400.3048006096 +y_0=30480.06096012192 +a=6378530.193 +b=6357144.189 +units=m +no_defs").orElse(null);

            NAD1983HARNAdjMNAitkinFeet.setName("NAD_1983_HARN_Adj_MN_Aitkin_Feet");
            NAD1983HARNAdjMNAitkinMeters.setName("NAD_1983_HARN_Adj_MN_Aitkin_Meters");
            NAD1983HARNAdjMNAnokaFeet.setName("NAD_1983_HARN_Adj_MN_Anoka_Feet");
            NAD1983HARNAdjMNAnokaMeters.setName("NAD_1983_HARN_Adj_MN_Anoka_Meters");
            NAD1983HARNAdjMNBeckerFeet.setName("NAD_1983_HARN_Adj_MN_Becker_Feet");
            NAD1983HARNAdjMNBeckerMeters.setName("NAD_1983_HARN_Adj_MN_Becker_Meters");
            NAD1983HARNAdjMNBeltramiNorthFeet.setName("NAD_1983_HARN_Adj_MN_Beltrami_North_Feet");
            NAD1983HARNAdjMNBeltramiNorthMeters.setName("NAD_1983_HARN_Adj_MN_Beltrami_North_Meters");
            NAD1983HARNAdjMNBeltramiSouthFeet.setName("NAD_1983_HARN_Adj_MN_Beltrami_South_Feet");
            NAD1983HARNAdjMNBeltramiSouthMeters.setName("NAD_1983_HARN_Adj_MN_Beltrami_South_Meters");
            NAD1983HARNAdjMNBentonFeet.setName("NAD_1983_HARN_Adj_MN_Benton_Feet");
            NAD1983HARNAdjMNBentonMeters.setName("NAD_1983_HARN_Adj_MN_Benton_Meters");
            NAD1983HARNAdjMNBigStoneFeet.setName("NAD_1983_HARN_Adj_MN_Big_Stone_Feet");
            NAD1983HARNAdjMNBigStoneMeters.setName("NAD_1983_HARN_Adj_MN_Big_Stone_Meters");
            NAD1983HARNAdjMNBlueEarthFeet.setName("NAD_1983_HARN_Adj_MN_Blue_Earth_Feet");
            NAD1983HARNAdjMNBlueEarthMeters.setName("NAD_1983_HARN_Adj_MN_Blue_Earth_Meters");
            NAD1983HARNAdjMNBrownFeet.setName("NAD_1983_HARN_Adj_MN_Brown_Feet");
            NAD1983HARNAdjMNBrownMeters.setName("NAD_1983_HARN_Adj_MN_Brown_Meters");
            NAD1983HARNAdjMNCarltonFeet.setName("NAD_1983_HARN_Adj_MN_Carlton_Feet");
            NAD1983HARNAdjMNCarltonMeters.setName("NAD_1983_HARN_Adj_MN_Carlton_Meters");
            NAD1983HARNAdjMNCarverFeet.setName("NAD_1983_HARN_Adj_MN_Carver_Feet");
            NAD1983HARNAdjMNCarverMeters.setName("NAD_1983_HARN_Adj_MN_Carver_Meters");
            NAD1983HARNAdjMNCassNorthFeet.setName("NAD_1983_HARN_Adj_MN_Cass_North_Feet");
            NAD1983HARNAdjMNCassNorthMeters.setName("NAD_1983_HARN_Adj_MN_Cass_North_Meters");
            NAD1983HARNAdjMNCassSouthFeet.setName("NAD_1983_HARN_Adj_MN_Cass_South_Feet");
            NAD1983HARNAdjMNCassSouthMeters.setName("NAD_1983_HARN_Adj_MN_Cass_South_Meters");
            NAD1983HARNAdjMNChippewaFeet.setName("NAD_1983_HARN_Adj_MN_Chippewa_Feet");
            NAD1983HARNAdjMNChippewaMeters.setName("NAD_1983_HARN_Adj_MN_Chippewa_Meters");
            NAD1983HARNAdjMNChisagoFeet.setName("NAD_1983_HARN_Adj_MN_Chisago_Feet");
            NAD1983HARNAdjMNChisagoMeters.setName("NAD_1983_HARN_Adj_MN_Chisago_Meters");
            NAD1983HARNAdjMNClayFeet.setName("NAD_1983_HARN_Adj_MN_Clay_Feet");
            NAD1983HARNAdjMNClayMeters.setName("NAD_1983_HARN_Adj_MN_Clay_Meters");
            NAD1983HARNAdjMNClearwaterFeet.setName("NAD_1983_HARN_Adj_MN_Clearwater_Feet");
            NAD1983HARNAdjMNClearwaterMeters.setName("NAD_1983_HARN_Adj_MN_Clearwater_Meters");
            NAD1983HARNAdjMNCookNorthFeet.setName("NAD_1983_HARN_Adj_MN_Cook_North_Feet");
            NAD1983HARNAdjMNCookNorthMeters.setName("NAD_1983_HARN_Adj_MN_Cook_North_Meters");
            NAD1983HARNAdjMNCookSouthFeet.setName("NAD_1983_HARN_Adj_MN_Cook_South_Feet");
            NAD1983HARNAdjMNCookSouthMeters.setName("NAD_1983_HARN_Adj_MN_Cook_South_Meters");
            NAD1983HARNAdjMNCottonwoodFeet.setName("NAD_1983_HARN_Adj_MN_Cottonwood_Feet");
            NAD1983HARNAdjMNCottonwoodMeters.setName("NAD_1983_HARN_Adj_MN_Cottonwood_Meters");
            NAD1983HARNAdjMNCrowWingFeet.setName("NAD_1983_HARN_Adj_MN_Crow_Wing_Feet");
            NAD1983HARNAdjMNCrowWingMeters.setName("NAD_1983_HARN_Adj_MN_Crow_Wing_Meters");
            NAD1983HARNAdjMNDakotaFeet.setName("NAD_1983_HARN_Adj_MN_Dakota_Feet");
            NAD1983HARNAdjMNDakotaMeters.setName("NAD_1983_HARN_Adj_MN_Dakota_Meters");
            NAD1983HARNAdjMNDodgeFeet.setName("NAD_1983_HARN_Adj_MN_Dodge_Feet");
            NAD1983HARNAdjMNDodgeMeters.setName("NAD_1983_HARN_Adj_MN_Dodge_Meters");
            NAD1983HARNAdjMNDouglasFeet.setName("NAD_1983_HARN_Adj_MN_Douglas_Feet");
            NAD1983HARNAdjMNDouglasMeters.setName("NAD_1983_HARN_Adj_MN_Douglas_Meters");
            NAD1983HARNAdjMNFaribaultFeet.setName("NAD_1983_HARN_Adj_MN_Faribault_Feet");
            NAD1983HARNAdjMNFaribaultMeters.setName("NAD_1983_HARN_Adj_MN_Faribault_Meters");
            NAD1983HARNAdjMNFillmoreFeet.setName("NAD_1983_HARN_Adj_MN_Fillmore_Feet");
            NAD1983HARNAdjMNFillmoreMeters.setName("NAD_1983_HARN_Adj_MN_Fillmore_Meters");
            NAD1983HARNAdjMNFreebornFeet.setName("NAD_1983_HARN_Adj_MN_Freeborn_Feet");
            NAD1983HARNAdjMNFreebornMeters.setName("NAD_1983_HARN_Adj_MN_Freeborn_Meters");
            NAD1983HARNAdjMNGoodhueFeet.setName("NAD_1983_HARN_Adj_MN_Goodhue_Feet");
            NAD1983HARNAdjMNGoodhueMeters.setName("NAD_1983_HARN_Adj_MN_Goodhue_Meters");
            NAD1983HARNAdjMNGrantFeet.setName("NAD_1983_HARN_Adj_MN_Grant_Feet");
            NAD1983HARNAdjMNGrantMeters.setName("NAD_1983_HARN_Adj_MN_Grant_Meters");
            NAD1983HARNAdjMNHennepinFeet.setName("NAD_1983_HARN_Adj_MN_Hennepin_Feet");
            NAD1983HARNAdjMNHennepinMeters.setName("NAD_1983_HARN_Adj_MN_Hennepin_Meters");
            NAD1983HARNAdjMNHoustonFeet.setName("NAD_1983_HARN_Adj_MN_Houston_Feet");
            NAD1983HARNAdjMNHoustonMeters.setName("NAD_1983_HARN_Adj_MN_Houston_Meters");
            NAD1983HARNAdjMNHubbardFeet.setName("NAD_1983_HARN_Adj_MN_Hubbard_Feet");
            NAD1983HARNAdjMNHubbardMeters.setName("NAD_1983_HARN_Adj_MN_Hubbard_Meters");
            NAD1983HARNAdjMNIsantiFeet.setName("NAD_1983_HARN_Adj_MN_Isanti_Feet");
            NAD1983HARNAdjMNIsantiMeters.setName("NAD_1983_HARN_Adj_MN_Isanti_Meters");
            NAD1983HARNAdjMNItascaNorthFeet.setName("NAD_1983_HARN_Adj_MN_Itasca_North_Feet");
            NAD1983HARNAdjMNItascaNorthMeters.setName("NAD_1983_HARN_Adj_MN_Itasca_North_Meters");
            NAD1983HARNAdjMNItascaSouthFeet.setName("NAD_1983_HARN_Adj_MN_Itasca_South_Feet");
            NAD1983HARNAdjMNItascaSouthMeters.setName("NAD_1983_HARN_Adj_MN_Itasca_South_Meters");
            NAD1983HARNAdjMNJacksonFeet.setName("NAD_1983_HARN_Adj_MN_Jackson_Feet");
            NAD1983HARNAdjMNJacksonMeters.setName("NAD_1983_HARN_Adj_MN_Jackson_Meters");
            NAD1983HARNAdjMNKanabecFeet.setName("NAD_1983_HARN_Adj_MN_Kanabec_Feet");
            NAD1983HARNAdjMNKanabecMeters.setName("NAD_1983_HARN_Adj_MN_Kanabec_Meters");
            NAD1983HARNAdjMNKandiyohiFeet.setName("NAD_1983_HARN_Adj_MN_Kandiyohi_Feet");
            NAD1983HARNAdjMNKandiyohiMeters.setName("NAD_1983_HARN_Adj_MN_Kandiyohi_Meters");
            NAD1983HARNAdjMNKittsonFeet.setName("NAD_1983_HARN_Adj_MN_Kittson_Feet");
            NAD1983HARNAdjMNKittsonMeters.setName("NAD_1983_HARN_Adj_MN_Kittson_Meters");
            NAD1983HARNAdjMNKoochichingFeet.setName("NAD_1983_HARN_Adj_MN_Koochiching_Feet");
            NAD1983HARNAdjMNKoochichingMeters.setName("NAD_1983_HARN_Adj_MN_Koochiching_Meters");
            NAD1983HARNAdjMNLacQuiParleFeet.setName("NAD_1983_HARN_Adj_MN_Lac_Qui_Parle_Feet");
            NAD1983HARNAdjMNLacQuiParleMeters.setName("NAD_1983_HARN_Adj_MN_Lac_Qui_Parle_Meters");
            NAD1983HARNAdjMNLakeFeet.setName("NAD_1983_HARN_Adj_MN_Lake_Feet");
            NAD1983HARNAdjMNLakeMeters.setName("NAD_1983_HARN_Adj_MN_Lake_Meters");
            NAD1983HARNAdjMNLakeoftheWoodsNorthFeet.setName("NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_North_Feet");
            NAD1983HARNAdjMNLakeoftheWoodsNorthMeters.setName("NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_North_Meters");
            NAD1983HARNAdjMNLakeoftheWoodsSouthFeet.setName("NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_South_Feet");
            NAD1983HARNAdjMNLakeoftheWoodsSouthMeters.setName("NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_South_Meters");
            NAD1983HARNAdjMNLeSueurFeet.setName("NAD_1983_HARN_Adj_MN_Le_Sueur_Feet");
            NAD1983HARNAdjMNLeSueurMeters.setName("NAD_1983_HARN_Adj_MN_Le_Sueur_Meters");
            NAD1983HARNAdjMNLincolnFeet.setName("NAD_1983_HARN_Adj_MN_Lincoln_Feet");
            NAD1983HARNAdjMNLincolnMeters.setName("NAD_1983_HARN_Adj_MN_Lincoln_Meters");
            NAD1983HARNAdjMNLyonFeet.setName("NAD_1983_HARN_Adj_MN_Lyon_Feet");
            NAD1983HARNAdjMNLyonMeters.setName("NAD_1983_HARN_Adj_MN_Lyon_Meters");
            NAD1983HARNAdjMNMahnomenFeet.setName("NAD_1983_HARN_Adj_MN_Mahnomen_Feet");
            NAD1983HARNAdjMNMahnomenMeters.setName("NAD_1983_HARN_Adj_MN_Mahnomen_Meters");
            NAD1983HARNAdjMNMarshallFeet.setName("NAD_1983_HARN_Adj_MN_Marshall_Feet");
            NAD1983HARNAdjMNMarshallMeters.setName("NAD_1983_HARN_Adj_MN_Marshall_Meters");
            NAD1983HARNAdjMNMartinFeet.setName("NAD_1983_HARN_Adj_MN_Martin_Feet");
            NAD1983HARNAdjMNMartinMeters.setName("NAD_1983_HARN_Adj_MN_Martin_Meters");
            NAD1983HARNAdjMNMcLeodFeet.setName("NAD_1983_HARN_Adj_MN_McLeod_Feet");
            NAD1983HARNAdjMNMcLeodMeters.setName("NAD_1983_HARN_Adj_MN_McLeod_Meters");
            NAD1983HARNAdjMNMeekerFeet.setName("NAD_1983_HARN_Adj_MN_Meeker_Feet");
            NAD1983HARNAdjMNMeekerMeters.setName("NAD_1983_HARN_Adj_MN_Meeker_Meters");
            NAD1983HARNAdjMNMilleLacsFeet.setName("NAD_1983_HARN_Adj_MN_Mille_Lacs_Feet");
            NAD1983HARNAdjMNMilleLacsMeters.setName("NAD_1983_HARN_Adj_MN_Mille_Lacs_Meters");
            NAD1983HARNAdjMNMorrisonFeet.setName("NAD_1983_HARN_Adj_MN_Morrison_Feet");
            NAD1983HARNAdjMNMorrisonMeters.setName("NAD_1983_HARN_Adj_MN_Morrison_Meters");
            NAD1983HARNAdjMNMowerFeet.setName("NAD_1983_HARN_Adj_MN_Mower_Feet");
            NAD1983HARNAdjMNMowerMeters.setName("NAD_1983_HARN_Adj_MN_Mower_Meters");
            NAD1983HARNAdjMNMurrayFeet.setName("NAD_1983_HARN_Adj_MN_Murray_Feet");
            NAD1983HARNAdjMNMurrayMeters.setName("NAD_1983_HARN_Adj_MN_Murray_Meters");
            NAD1983HARNAdjMNNicolletFeet.setName("NAD_1983_HARN_Adj_MN_Nicollet_Feet");
            NAD1983HARNAdjMNNicolletMeters.setName("NAD_1983_HARN_Adj_MN_Nicollet_Meters");
            NAD1983HARNAdjMNNoblesFeet.setName("NAD_1983_HARN_Adj_MN_Nobles_Feet");
            NAD1983HARNAdjMNNoblesMeters.setName("NAD_1983_HARN_Adj_MN_Nobles_Meters");
            NAD1983HARNAdjMNNormanFeet.setName("NAD_1983_HARN_Adj_MN_Norman_Feet");
            NAD1983HARNAdjMNNormanMeters.setName("NAD_1983_HARN_Adj_MN_Norman_Meters");
            NAD1983HARNAdjMNOlmstedFeet.setName("NAD_1983_HARN_Adj_MN_Olmsted_Feet");
            NAD1983HARNAdjMNOlmstedMeters.setName("NAD_1983_HARN_Adj_MN_Olmsted_Meters");
            NAD1983HARNAdjMNOttertailFeet.setName("NAD_1983_HARN_Adj_MN_Ottertail_Feet");
            NAD1983HARNAdjMNOttertailMeters.setName("NAD_1983_HARN_Adj_MN_Ottertail_Meters");
            NAD1983HARNAdjMNPenningtonFeet.setName("NAD_1983_HARN_Adj_MN_Pennington_Feet");
            NAD1983HARNAdjMNPenningtonMeters.setName("NAD_1983_HARN_Adj_MN_Pennington_Meters");
            NAD1983HARNAdjMNPineFeet.setName("NAD_1983_HARN_Adj_MN_Pine_Feet");
            NAD1983HARNAdjMNPineMeters.setName("NAD_1983_HARN_Adj_MN_Pine_Meters");
            NAD1983HARNAdjMNPipestoneFeet.setName("NAD_1983_HARN_Adj_MN_Pipestone_Feet");
            NAD1983HARNAdjMNPipestoneMeters.setName("NAD_1983_HARN_Adj_MN_Pipestone_Meters");
            NAD1983HARNAdjMNPolkFeet.setName("NAD_1983_HARN_Adj_MN_Polk_Feet");
            NAD1983HARNAdjMNPolkMeters.setName("NAD_1983_HARN_Adj_MN_Polk_Meters");
            NAD1983HARNAdjMNPopeFeet.setName("NAD_1983_HARN_Adj_MN_Pope_Feet");
            NAD1983HARNAdjMNPopeMeters.setName("NAD_1983_HARN_Adj_MN_Pope_Meters");
            NAD1983HARNAdjMNRamseyFeet.setName("NAD_1983_HARN_Adj_MN_Ramsey_Feet");
            NAD1983HARNAdjMNRamseyMeters.setName("NAD_1983_HARN_Adj_MN_Ramsey_Meters");
            NAD1983HARNAdjMNRedLakeFeet.setName("NAD_1983_HARN_Adj_MN_Red_Lake_Feet");
            NAD1983HARNAdjMNRedLakeMeters.setName("NAD_1983_HARN_Adj_MN_Red_Lake_Meters");
            NAD1983HARNAdjMNRedwoodFeet.setName("NAD_1983_HARN_Adj_MN_Redwood_Feet");
            NAD1983HARNAdjMNRedwoodMeters.setName("NAD_1983_HARN_Adj_MN_Redwood_Meters");
            NAD1983HARNAdjMNRenvilleFeet.setName("NAD_1983_HARN_Adj_MN_Renville_Feet");
            NAD1983HARNAdjMNRenvilleMeters.setName("NAD_1983_HARN_Adj_MN_Renville_Meters");
            NAD1983HARNAdjMNRiceFeet.setName("NAD_1983_HARN_Adj_MN_Rice_Feet");
            NAD1983HARNAdjMNRiceMeters.setName("NAD_1983_HARN_Adj_MN_Rice_Meters");
            NAD1983HARNAdjMNRockFeet.setName("NAD_1983_HARN_Adj_MN_Rock_Feet");
            NAD1983HARNAdjMNRockMeters.setName("NAD_1983_HARN_Adj_MN_Rock_Meters");
            NAD1983HARNAdjMNRoseauFeet.setName("NAD_1983_HARN_Adj_MN_Roseau_Feet");
            NAD1983HARNAdjMNRoseauMeters.setName("NAD_1983_HARN_Adj_MN_Roseau_Meters");
            NAD1983HARNAdjMNScottFeet.setName("NAD_1983_HARN_Adj_MN_Scott_Feet");
            NAD1983HARNAdjMNScottMeters.setName("NAD_1983_HARN_Adj_MN_Scott_Meters");
            NAD1983HARNAdjMNSherburneFeet.setName("NAD_1983_HARN_Adj_MN_Sherburne_Feet");
            NAD1983HARNAdjMNSherburneMeters.setName("NAD_1983_HARN_Adj_MN_Sherburne_Meters");
            NAD1983HARNAdjMNSibleyFeet.setName("NAD_1983_HARN_Adj_MN_Sibley_Feet");
            NAD1983HARNAdjMNSibleyMeters.setName("NAD_1983_HARN_Adj_MN_Sibley_Meters");
            NAD1983HARNAdjMNStearnsFeet.setName("NAD_1983_HARN_Adj_MN_Stearns_Feet");
            NAD1983HARNAdjMNStearnsMeters.setName("NAD_1983_HARN_Adj_MN_Stearns_Meters");
            NAD1983HARNAdjMNSteeleFeet.setName("NAD_1983_HARN_Adj_MN_Steele_Feet");
            NAD1983HARNAdjMNSteeleMeters.setName("NAD_1983_HARN_Adj_MN_Steele_Meters");
            NAD1983HARNAdjMNStevensFeet.setName("NAD_1983_HARN_Adj_MN_Stevens_Feet");
            NAD1983HARNAdjMNStevensMeters.setName("NAD_1983_HARN_Adj_MN_Stevens_Meters");
            NAD1983HARNAdjMNStLouisCentralFeet.setName("NAD_1983_HARN_Adj_MN_St_Louis_Central_Feet");
            NAD1983HARNAdjMNStLouisCentralMeters.setName("NAD_1983_HARN_Adj_MN_St_Louis_Central_Meters");
            NAD1983HARNAdjMNStLouisNorthFeet.setName("NAD_1983_HARN_Adj_MN_St_Louis_North_Feet");
            NAD1983HARNAdjMNStLouisNorthMeters.setName("NAD_1983_HARN_Adj_MN_St_Louis_North_Meters");
            NAD1983HARNAdjMNStLouisSouthFeet.setName("NAD_1983_HARN_Adj_MN_St_Louis_South_Feet");
            NAD1983HARNAdjMNStLouisSouthMeters.setName("NAD_1983_HARN_Adj_MN_St_Louis_South_Meters");
            NAD1983HARNAdjMNSwiftFeet.setName("NAD_1983_HARN_Adj_MN_Swift_Feet");
            NAD1983HARNAdjMNSwiftMeters.setName("NAD_1983_HARN_Adj_MN_Swift_Meters");
            NAD1983HARNAdjMNToddFeet.setName("NAD_1983_HARN_Adj_MN_Todd_Feet");
            NAD1983HARNAdjMNToddMeters.setName("NAD_1983_HARN_Adj_MN_Todd_Meters");
            NAD1983HARNAdjMNTraverseFeet.setName("NAD_1983_HARN_Adj_MN_Traverse_Feet");
            NAD1983HARNAdjMNTraverseMeters.setName("NAD_1983_HARN_Adj_MN_Traverse_Meters");
            NAD1983HARNAdjMNWabashaFeet.setName("NAD_1983_HARN_Adj_MN_Wabasha_Feet");
            NAD1983HARNAdjMNWabashaMeters.setName("NAD_1983_HARN_Adj_MN_Wabasha_Meters");
            NAD1983HARNAdjMNWadenaFeet.setName("NAD_1983_HARN_Adj_MN_Wadena_Feet");
            NAD1983HARNAdjMNWadenaMeters.setName("NAD_1983_HARN_Adj_MN_Wadena_Meters");
            NAD1983HARNAdjMNWasecaFeet.setName("NAD_1983_HARN_Adj_MN_Waseca_Feet");
            NAD1983HARNAdjMNWasecaMeters.setName("NAD_1983_HARN_Adj_MN_Waseca_Meters");
            NAD1983HARNAdjMNWashingtonFeet.setName("NAD_1983_HARN_Adj_MN_Washington_Feet");
            NAD1983HARNAdjMNWashingtonMeters.setName("NAD_1983_HARN_Adj_MN_Washington_Meters");
            NAD1983HARNAdjMNWatonwanFeet.setName("NAD_1983_HARN_Adj_MN_Watonwan_Feet");
            NAD1983HARNAdjMNWatonwanMeters.setName("NAD_1983_HARN_Adj_MN_Watonwan_Meters");
            NAD1983HARNAdjMNWilkinFeet.setName("NAD_1983_HARN_Adj_MN_Wilkin_Feet");
            NAD1983HARNAdjMNWilkinMeters.setName("NAD_1983_HARN_Adj_MN_Wilkin_Meters");
            NAD1983HARNAdjMNWinonaFeet.setName("NAD_1983_HARN_Adj_MN_Winona_Feet");
            NAD1983HARNAdjMNWinonaMeters.setName("NAD_1983_HARN_Adj_MN_Winona_Meters");
            NAD1983HARNAdjMNWrightFeet.setName("NAD_1983_HARN_Adj_MN_Wright_Feet");
            NAD1983HARNAdjMNWrightMeters.setName("NAD_1983_HARN_Adj_MN_Wright_Meters");
            NAD1983HARNAdjMNYellowMedicineFeet.setName("NAD_1983_HARN_Adj_MN_Yellow_Medicine_Feet");
            NAD1983HARNAdjMNYellowMedicineMeters.setName("NAD_1983_HARN_Adj_MN_Yellow_Medicine_Meters");

            NAD1983HARNAdjMNAitkinFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNAitkinMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNAnokaFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Anoka");
            NAD1983HARNAdjMNAnokaMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Anoka");
            NAD1983HARNAdjMNBeckerFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Becker");
            NAD1983HARNAdjMNBeckerMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Becker");
            NAD1983HARNAdjMNBeltramiNorthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Beltrami_North");
            NAD1983HARNAdjMNBeltramiNorthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Beltrami_North");
            NAD1983HARNAdjMNBeltramiSouthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Beltrami_South");
            NAD1983HARNAdjMNBeltramiSouthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Beltrami_South");
            NAD1983HARNAdjMNBentonFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Benton");
            NAD1983HARNAdjMNBentonMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Benton");
            NAD1983HARNAdjMNBigStoneFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Big_Stone");
            NAD1983HARNAdjMNBigStoneMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Big_Stone");
            NAD1983HARNAdjMNBlueEarthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Blue_Earth");
            NAD1983HARNAdjMNBlueEarthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Blue_Earth");
            NAD1983HARNAdjMNBrownFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Brown");
            NAD1983HARNAdjMNBrownMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Brown");
            NAD1983HARNAdjMNCarltonFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Carlton");
            NAD1983HARNAdjMNCarltonMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Carlton");
            NAD1983HARNAdjMNCarverFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Carver");
            NAD1983HARNAdjMNCarverMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Carver");
            NAD1983HARNAdjMNCassNorthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cass_North");
            NAD1983HARNAdjMNCassNorthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cass_North");
            NAD1983HARNAdjMNCassSouthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cass_South");
            NAD1983HARNAdjMNCassSouthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cass_South");
            NAD1983HARNAdjMNChippewaFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Chippewa");
            NAD1983HARNAdjMNChippewaMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Chippewa");
            NAD1983HARNAdjMNChisagoFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Chisago");
            NAD1983HARNAdjMNChisagoMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Chisago");
            NAD1983HARNAdjMNClayFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNClayMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNClearwaterFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNClearwaterMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNCookNorthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cook_North");
            NAD1983HARNAdjMNCookNorthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cook_North");
            NAD1983HARNAdjMNCookSouthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cook_South");
            NAD1983HARNAdjMNCookSouthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cook_South");
            NAD1983HARNAdjMNCottonwoodFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cottonwood");
            NAD1983HARNAdjMNCottonwoodMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cottonwood");
            NAD1983HARNAdjMNCrowWingFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Crow_Wing");
            NAD1983HARNAdjMNCrowWingMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Crow_Wing");
            NAD1983HARNAdjMNDakotaFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Dakota");
            NAD1983HARNAdjMNDakotaMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Dakota");
            NAD1983HARNAdjMNDodgeFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Dodge");
            NAD1983HARNAdjMNDodgeMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Dodge");
            NAD1983HARNAdjMNDouglasFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Douglas");
            NAD1983HARNAdjMNDouglasMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Douglas");
            NAD1983HARNAdjMNFaribaultFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Faribault");
            NAD1983HARNAdjMNFaribaultMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Faribault");
            NAD1983HARNAdjMNFillmoreFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Fillmore");
            NAD1983HARNAdjMNFillmoreMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Fillmore");
            NAD1983HARNAdjMNFreebornFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Freeborn");
            NAD1983HARNAdjMNFreebornMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Freeborn");
            NAD1983HARNAdjMNGoodhueFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Goodhue");
            NAD1983HARNAdjMNGoodhueMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Goodhue");
            NAD1983HARNAdjMNGrantFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Grant");
            NAD1983HARNAdjMNGrantMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Grant");
            NAD1983HARNAdjMNHennepinFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Hennepin");
            NAD1983HARNAdjMNHennepinMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Hennepin");
            NAD1983HARNAdjMNHoustonFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Houston");
            NAD1983HARNAdjMNHoustonMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Houston");
            NAD1983HARNAdjMNHubbardFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNHubbardMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNIsantiFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Isanti");
            NAD1983HARNAdjMNIsantiMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Isanti");
            NAD1983HARNAdjMNItascaNorthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Itasca_North");
            NAD1983HARNAdjMNItascaNorthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Itasca_North");
            NAD1983HARNAdjMNItascaSouthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Itasca_South");
            NAD1983HARNAdjMNItascaSouthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Itasca_South");
            NAD1983HARNAdjMNJacksonFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Jackson");
            NAD1983HARNAdjMNJacksonMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Jackson");
            NAD1983HARNAdjMNKanabecFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Kanabec");
            NAD1983HARNAdjMNKanabecMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Kanabec");
            NAD1983HARNAdjMNKandiyohiFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Kandiyohi");
            NAD1983HARNAdjMNKandiyohiMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Kandiyohi");
            NAD1983HARNAdjMNKittsonFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Kittson");
            NAD1983HARNAdjMNKittsonMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Kittson");
            NAD1983HARNAdjMNKoochichingFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Koochiching");
            NAD1983HARNAdjMNKoochichingMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Koochiching");
            NAD1983HARNAdjMNLacQuiParleFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lac_Qui_Parle");
            NAD1983HARNAdjMNLacQuiParleMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lac_Qui_Parle");
            NAD1983HARNAdjMNLakeFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNLakeMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNLakeoftheWoodsNorthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_North");
            NAD1983HARNAdjMNLakeoftheWoodsNorthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_North");
            NAD1983HARNAdjMNLakeoftheWoodsSouthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_South");
            NAD1983HARNAdjMNLakeoftheWoodsSouthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_South");
            NAD1983HARNAdjMNLeSueurFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Le_Sueur");
            NAD1983HARNAdjMNLeSueurMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Le_Sueur");
            NAD1983HARNAdjMNLincolnFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lincoln");
            NAD1983HARNAdjMNLincolnMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lincoln");
            NAD1983HARNAdjMNLyonFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lyon");
            NAD1983HARNAdjMNLyonMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lyon");
            NAD1983HARNAdjMNMahnomenFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Mahnomen");
            NAD1983HARNAdjMNMahnomenMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Mahnomen");
            NAD1983HARNAdjMNMarshallFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Marshall");
            NAD1983HARNAdjMNMarshallMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Marshall");
            NAD1983HARNAdjMNMartinFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Martin");
            NAD1983HARNAdjMNMartinMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Martin");
            NAD1983HARNAdjMNMcLeodFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_McLeod");
            NAD1983HARNAdjMNMcLeodMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_McLeod");
            NAD1983HARNAdjMNMeekerFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Meeker");
            NAD1983HARNAdjMNMeekerMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Meeker");
            NAD1983HARNAdjMNMilleLacsFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNMilleLacsMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNMorrisonFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Morrison");
            NAD1983HARNAdjMNMorrisonMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Morrison");
            NAD1983HARNAdjMNMowerFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Mower");
            NAD1983HARNAdjMNMowerMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Mower");
            NAD1983HARNAdjMNMurrayFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Murray");
            NAD1983HARNAdjMNMurrayMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Murray");
            NAD1983HARNAdjMNNicolletFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Nicollet");
            NAD1983HARNAdjMNNicolletMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Nicollet");
            NAD1983HARNAdjMNNoblesFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Nobles");
            NAD1983HARNAdjMNNoblesMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Nobles");
            NAD1983HARNAdjMNNormanFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Norman");
            NAD1983HARNAdjMNNormanMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Norman");
            NAD1983HARNAdjMNOlmstedFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Olmsted");
            NAD1983HARNAdjMNOlmstedMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Olmsted");
            NAD1983HARNAdjMNOttertailFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Ottertail");
            NAD1983HARNAdjMNOttertailMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Ottertail");
            NAD1983HARNAdjMNPenningtonFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pennington");
            NAD1983HARNAdjMNPenningtonMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pennington");
            NAD1983HARNAdjMNPineFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pine");
            NAD1983HARNAdjMNPineMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pine");
            NAD1983HARNAdjMNPipestoneFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pipestone");
            NAD1983HARNAdjMNPipestoneMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pipestone");
            NAD1983HARNAdjMNPolkFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Polk");
            NAD1983HARNAdjMNPolkMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Polk");
            NAD1983HARNAdjMNPopeFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pope");
            NAD1983HARNAdjMNPopeMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pope");
            NAD1983HARNAdjMNRamseyFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Ramsey");
            NAD1983HARNAdjMNRamseyMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Ramsey");
            NAD1983HARNAdjMNRedLakeFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Red_Lake");
            NAD1983HARNAdjMNRedLakeMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Red_Lake");
            NAD1983HARNAdjMNRedwoodFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Redwood");
            NAD1983HARNAdjMNRedwoodMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Redwood");
            NAD1983HARNAdjMNRenvilleFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Renville");
            NAD1983HARNAdjMNRenvilleMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Renville");
            NAD1983HARNAdjMNRiceFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Rice");
            NAD1983HARNAdjMNRiceMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Rice");
            NAD1983HARNAdjMNRockFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Rock");
            NAD1983HARNAdjMNRockMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Rock");
            NAD1983HARNAdjMNRoseauFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Roseau");
            NAD1983HARNAdjMNRoseauMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Roseau");
            NAD1983HARNAdjMNScottFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Scott");
            NAD1983HARNAdjMNScottMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Scott");
            NAD1983HARNAdjMNSherburneFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Sherburne");
            NAD1983HARNAdjMNSherburneMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Sherburne");
            NAD1983HARNAdjMNSibleyFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Sibley");
            NAD1983HARNAdjMNSibleyMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Sibley");
            NAD1983HARNAdjMNStearnsFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Stearns");
            NAD1983HARNAdjMNStearnsMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Stearns");
            NAD1983HARNAdjMNSteeleFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Steele");
            NAD1983HARNAdjMNSteeleMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Steele");
            NAD1983HARNAdjMNStevensFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Stevens");
            NAD1983HARNAdjMNStevensMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Stevens");
            NAD1983HARNAdjMNStLouisCentralFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_Central");
            NAD1983HARNAdjMNStLouisCentralMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_Central");
            NAD1983HARNAdjMNStLouisNorthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_North");
            NAD1983HARNAdjMNStLouisNorthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_North");
            NAD1983HARNAdjMNStLouisSouthFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_South");
            NAD1983HARNAdjMNStLouisSouthMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_South");
            NAD1983HARNAdjMNSwiftFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Swift");
            NAD1983HARNAdjMNSwiftMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Swift");
            NAD1983HARNAdjMNToddFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Todd");
            NAD1983HARNAdjMNToddMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Todd");
            NAD1983HARNAdjMNTraverseFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Traverse");
            NAD1983HARNAdjMNTraverseMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Traverse");
            NAD1983HARNAdjMNWabashaFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Wabasha");
            NAD1983HARNAdjMNWabashaMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Wabasha");
            NAD1983HARNAdjMNWadenaFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Wadena");
            NAD1983HARNAdjMNWadenaMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Wadena");
            NAD1983HARNAdjMNWasecaFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Waseca");
            NAD1983HARNAdjMNWasecaMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Waseca");
            NAD1983HARNAdjMNWashingtonFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNWashingtonMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNWatonwanFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Watonwan");
            NAD1983HARNAdjMNWatonwanMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Watonwan");
            NAD1983HARNAdjMNWilkinFeet.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNWilkinMeters.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNAdjMNWinonaFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Winona");
            NAD1983HARNAdjMNWinonaMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Winona");
            NAD1983HARNAdjMNWrightFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Wright");
            NAD1983HARNAdjMNWrightMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Wright");
            NAD1983HARNAdjMNYellowMedicineFeet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Yellow_Medicine");
            NAD1983HARNAdjMNYellowMedicineMeters.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Yellow_Medicine");

            NAD1983HARNAdjMNAitkinFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNAitkinMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNAnokaFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Anoka");
            NAD1983HARNAdjMNAnokaMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Anoka");
            NAD1983HARNAdjMNBeckerFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Becker");
            NAD1983HARNAdjMNBeckerMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Becker");
            NAD1983HARNAdjMNBeltramiNorthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Beltrami_North");
            NAD1983HARNAdjMNBeltramiNorthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Beltrami_North");
            NAD1983HARNAdjMNBeltramiSouthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Beltrami_South");
            NAD1983HARNAdjMNBeltramiSouthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Beltrami_South");
            NAD1983HARNAdjMNBentonFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Benton");
            NAD1983HARNAdjMNBentonMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Benton");
            NAD1983HARNAdjMNBigStoneFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Big_Stone");
            NAD1983HARNAdjMNBigStoneMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Big_Stone");
            NAD1983HARNAdjMNBlueEarthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Blue_Earth");
            NAD1983HARNAdjMNBlueEarthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Blue_Earth");
            NAD1983HARNAdjMNBrownFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Brown");
            NAD1983HARNAdjMNBrownMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Brown");
            NAD1983HARNAdjMNCarltonFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Carlton");
            NAD1983HARNAdjMNCarltonMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Carlton");
            NAD1983HARNAdjMNCarverFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Carver");
            NAD1983HARNAdjMNCarverMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Carver");
            NAD1983HARNAdjMNCassNorthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cass_North");
            NAD1983HARNAdjMNCassNorthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cass_North");
            NAD1983HARNAdjMNCassSouthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cass_South");
            NAD1983HARNAdjMNCassSouthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cass_South");
            NAD1983HARNAdjMNChippewaFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Chippewa");
            NAD1983HARNAdjMNChippewaMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Chippewa");
            NAD1983HARNAdjMNChisagoFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Chisago");
            NAD1983HARNAdjMNChisagoMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Chisago");
            NAD1983HARNAdjMNClayFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNClayMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNClearwaterFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNClearwaterMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNCookNorthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cook_North");
            NAD1983HARNAdjMNCookNorthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cook_North");
            NAD1983HARNAdjMNCookSouthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cook_South");
            NAD1983HARNAdjMNCookSouthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cook_South");
            NAD1983HARNAdjMNCottonwoodFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cottonwood");
            NAD1983HARNAdjMNCottonwoodMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cottonwood");
            NAD1983HARNAdjMNCrowWingFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Crow_Wing");
            NAD1983HARNAdjMNCrowWingMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Crow_Wing");
            NAD1983HARNAdjMNDakotaFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Dakota");
            NAD1983HARNAdjMNDakotaMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Dakota");
            NAD1983HARNAdjMNDodgeFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Dodge");
            NAD1983HARNAdjMNDodgeMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Dodge");
            NAD1983HARNAdjMNDouglasFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Douglas");
            NAD1983HARNAdjMNDouglasMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Douglas");
            NAD1983HARNAdjMNFaribaultFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Faribault");
            NAD1983HARNAdjMNFaribaultMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Faribault");
            NAD1983HARNAdjMNFillmoreFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Fillmore");
            NAD1983HARNAdjMNFillmoreMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Fillmore");
            NAD1983HARNAdjMNFreebornFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Freeborn");
            NAD1983HARNAdjMNFreebornMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Freeborn");
            NAD1983HARNAdjMNGoodhueFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Goodhue");
            NAD1983HARNAdjMNGoodhueMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Goodhue");
            NAD1983HARNAdjMNGrantFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Grant");
            NAD1983HARNAdjMNGrantMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Grant");
            NAD1983HARNAdjMNHennepinFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Hennepin");
            NAD1983HARNAdjMNHennepinMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Hennepin");
            NAD1983HARNAdjMNHoustonFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Houston");
            NAD1983HARNAdjMNHoustonMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Houston");
            NAD1983HARNAdjMNHubbardFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNHubbardMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNIsantiFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Isanti");
            NAD1983HARNAdjMNIsantiMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Isanti");
            NAD1983HARNAdjMNItascaNorthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Itasca_North");
            NAD1983HARNAdjMNItascaNorthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Itasca_North");
            NAD1983HARNAdjMNItascaSouthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Itasca_South");
            NAD1983HARNAdjMNItascaSouthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Itasca_South");
            NAD1983HARNAdjMNJacksonFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Jackson");
            NAD1983HARNAdjMNJacksonMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Jackson");
            NAD1983HARNAdjMNKanabecFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Kanabec");
            NAD1983HARNAdjMNKanabecMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Kanabec");
            NAD1983HARNAdjMNKandiyohiFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Kandiyohi");
            NAD1983HARNAdjMNKandiyohiMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Kandiyohi");
            NAD1983HARNAdjMNKittsonFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Kittson");
            NAD1983HARNAdjMNKittsonMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Kittson");
            NAD1983HARNAdjMNKoochichingFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Koochiching");
            NAD1983HARNAdjMNKoochichingMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Koochiching");
            NAD1983HARNAdjMNLacQuiParleFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lac_Qui_Parle");
            NAD1983HARNAdjMNLacQuiParleMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lac_Qui_Parle");
            NAD1983HARNAdjMNLakeFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNLakeMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNLakeoftheWoodsNorthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_North");
            NAD1983HARNAdjMNLakeoftheWoodsNorthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_North");
            NAD1983HARNAdjMNLakeoftheWoodsSouthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_South");
            NAD1983HARNAdjMNLakeoftheWoodsSouthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_South");
            NAD1983HARNAdjMNLeSueurFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Le_Sueur");
            NAD1983HARNAdjMNLeSueurMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Le_Sueur");
            NAD1983HARNAdjMNLincolnFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lincoln");
            NAD1983HARNAdjMNLincolnMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lincoln");
            NAD1983HARNAdjMNLyonFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lyon");
            NAD1983HARNAdjMNLyonMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lyon");
            NAD1983HARNAdjMNMahnomenFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Mahnomen");
            NAD1983HARNAdjMNMahnomenMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Mahnomen");
            NAD1983HARNAdjMNMarshallFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Marshall");
            NAD1983HARNAdjMNMarshallMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Marshall");
            NAD1983HARNAdjMNMartinFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Martin");
            NAD1983HARNAdjMNMartinMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Martin");
            NAD1983HARNAdjMNMcLeodFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_McLeod");
            NAD1983HARNAdjMNMcLeodMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_McLeod");
            NAD1983HARNAdjMNMeekerFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Meeker");
            NAD1983HARNAdjMNMeekerMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Meeker");
            NAD1983HARNAdjMNMilleLacsFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNMilleLacsMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNMorrisonFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Morrison");
            NAD1983HARNAdjMNMorrisonMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Morrison");
            NAD1983HARNAdjMNMowerFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Mower");
            NAD1983HARNAdjMNMowerMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Mower");
            NAD1983HARNAdjMNMurrayFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Murray");
            NAD1983HARNAdjMNMurrayMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Murray");
            NAD1983HARNAdjMNNicolletFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Nicollet");
            NAD1983HARNAdjMNNicolletMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Nicollet");
            NAD1983HARNAdjMNNoblesFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Nobles");
            NAD1983HARNAdjMNNoblesMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Nobles");
            NAD1983HARNAdjMNNormanFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Norman");
            NAD1983HARNAdjMNNormanMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Norman");
            NAD1983HARNAdjMNOlmstedFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Olmsted");
            NAD1983HARNAdjMNOlmstedMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Olmsted");
            NAD1983HARNAdjMNOttertailFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Ottertail");
            NAD1983HARNAdjMNOttertailMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Ottertail");
            NAD1983HARNAdjMNPenningtonFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pennington");
            NAD1983HARNAdjMNPenningtonMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pennington");
            NAD1983HARNAdjMNPineFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pine");
            NAD1983HARNAdjMNPineMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pine");
            NAD1983HARNAdjMNPipestoneFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pipestone");
            NAD1983HARNAdjMNPipestoneMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pipestone");
            NAD1983HARNAdjMNPolkFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Polk");
            NAD1983HARNAdjMNPolkMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Polk");
            NAD1983HARNAdjMNPopeFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pope");
            NAD1983HARNAdjMNPopeMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pope");
            NAD1983HARNAdjMNRamseyFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Ramsey");
            NAD1983HARNAdjMNRamseyMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Ramsey");
            NAD1983HARNAdjMNRedLakeFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Red_Lake");
            NAD1983HARNAdjMNRedLakeMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Red_Lake");
            NAD1983HARNAdjMNRedwoodFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Redwood");
            NAD1983HARNAdjMNRedwoodMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Redwood");
            NAD1983HARNAdjMNRenvilleFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Renville");
            NAD1983HARNAdjMNRenvilleMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Renville");
            NAD1983HARNAdjMNRiceFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Rice");
            NAD1983HARNAdjMNRiceMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Rice");
            NAD1983HARNAdjMNRockFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Rock");
            NAD1983HARNAdjMNRockMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Rock");
            NAD1983HARNAdjMNRoseauFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Roseau");
            NAD1983HARNAdjMNRoseauMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Roseau");
            NAD1983HARNAdjMNScottFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Scott");
            NAD1983HARNAdjMNScottMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Scott");
            NAD1983HARNAdjMNSherburneFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Sherburne");
            NAD1983HARNAdjMNSherburneMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Sherburne");
            NAD1983HARNAdjMNSibleyFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Sibley");
            NAD1983HARNAdjMNSibleyMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Sibley");
            NAD1983HARNAdjMNStearnsFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Stearns");
            NAD1983HARNAdjMNStearnsMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Stearns");
            NAD1983HARNAdjMNSteeleFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Steele");
            NAD1983HARNAdjMNSteeleMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Steele");
            NAD1983HARNAdjMNStevensFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Stevens");
            NAD1983HARNAdjMNStevensMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Stevens");
            NAD1983HARNAdjMNStLouisCentralFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_St_Louis_Central");
            NAD1983HARNAdjMNStLouisCentralMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_St_Louis_Central");
            NAD1983HARNAdjMNStLouisNorthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_St_Louis_North");
            NAD1983HARNAdjMNStLouisNorthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_St_Louis_North");
            NAD1983HARNAdjMNStLouisSouthFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_St_Louis_South");
            NAD1983HARNAdjMNStLouisSouthMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_St_Louis_South");
            NAD1983HARNAdjMNSwiftFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Swift");
            NAD1983HARNAdjMNSwiftMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Swift");
            NAD1983HARNAdjMNToddFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Todd");
            NAD1983HARNAdjMNToddMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Todd");
            NAD1983HARNAdjMNTraverseFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Traverse");
            NAD1983HARNAdjMNTraverseMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Traverse");
            NAD1983HARNAdjMNWabashaFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Wabasha");
            NAD1983HARNAdjMNWabashaMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Wabasha");
            NAD1983HARNAdjMNWadenaFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Wadena");
            NAD1983HARNAdjMNWadenaMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Wadena");
            NAD1983HARNAdjMNWasecaFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Waseca");
            NAD1983HARNAdjMNWasecaMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Waseca");
            NAD1983HARNAdjMNWashingtonFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNWashingtonMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNWatonwanFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Watonwan");
            NAD1983HARNAdjMNWatonwanMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Watonwan");
            NAD1983HARNAdjMNWilkinFeet.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNWilkinMeters.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNAdjMNWinonaFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Winona");
            NAD1983HARNAdjMNWinonaMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Winona");
            NAD1983HARNAdjMNWrightFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Wright");
            NAD1983HARNAdjMNWrightMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Wright");
            NAD1983HARNAdjMNYellowMedicineFeet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Yellow_Medicine");
            NAD1983HARNAdjMNYellowMedicineMeters.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Yellow_Medicine");
        }

        //</editor-fold>

  /**
   * @return the NAD1983HARNAdjMNAitkinFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNAitkinFeet() {
    return NAD1983HARNAdjMNAitkinFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNAitkinMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNAitkinMeters() {
    return NAD1983HARNAdjMNAitkinMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNAnokaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNAnokaFeet() {
    return NAD1983HARNAdjMNAnokaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNAnokaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNAnokaMeters() {
    return NAD1983HARNAdjMNAnokaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBeckerFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNBeckerFeet() {
    return NAD1983HARNAdjMNBeckerFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBeckerMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNBeckerMeters() {
    return NAD1983HARNAdjMNBeckerMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBeltramiNorthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNBeltramiNorthFeet() {
    return NAD1983HARNAdjMNBeltramiNorthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBeltramiNorthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNBeltramiNorthMeters() {
    return NAD1983HARNAdjMNBeltramiNorthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBeltramiSouthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNBeltramiSouthFeet() {
    return NAD1983HARNAdjMNBeltramiSouthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBeltramiSouthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNBeltramiSouthMeters() {
    return NAD1983HARNAdjMNBeltramiSouthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBentonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNBentonFeet() {
    return NAD1983HARNAdjMNBentonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBentonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNBentonMeters() {
    return NAD1983HARNAdjMNBentonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBigStoneFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNBigStoneFeet() {
    return NAD1983HARNAdjMNBigStoneFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBigStoneMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNBigStoneMeters() {
    return NAD1983HARNAdjMNBigStoneMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBlueEarthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNBlueEarthFeet() {
    return NAD1983HARNAdjMNBlueEarthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBlueEarthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNBlueEarthMeters() {
    return NAD1983HARNAdjMNBlueEarthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBrownFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNBrownFeet() {
    return NAD1983HARNAdjMNBrownFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBrownMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNBrownMeters() {
    return NAD1983HARNAdjMNBrownMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCarltonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNCarltonFeet() {
    return NAD1983HARNAdjMNCarltonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCarltonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNCarltonMeters() {
    return NAD1983HARNAdjMNCarltonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCarverFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNCarverFeet() {
    return NAD1983HARNAdjMNCarverFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCarverMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNCarverMeters() {
    return NAD1983HARNAdjMNCarverMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCassNorthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNCassNorthFeet() {
    return NAD1983HARNAdjMNCassNorthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCassNorthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNCassNorthMeters() {
    return NAD1983HARNAdjMNCassNorthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCassSouthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNCassSouthFeet() {
    return NAD1983HARNAdjMNCassSouthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCassSouthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNCassSouthMeters() {
    return NAD1983HARNAdjMNCassSouthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNChippewaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNChippewaFeet() {
    return NAD1983HARNAdjMNChippewaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNChippewaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNChippewaMeters() {
    return NAD1983HARNAdjMNChippewaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNChisagoFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNChisagoFeet() {
    return NAD1983HARNAdjMNChisagoFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNChisagoMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNChisagoMeters() {
    return NAD1983HARNAdjMNChisagoMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNClayFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNClayFeet() {
    return NAD1983HARNAdjMNClayFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNClayMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNClayMeters() {
    return NAD1983HARNAdjMNClayMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNClearwaterFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNClearwaterFeet() {
    return NAD1983HARNAdjMNClearwaterFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNClearwaterMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNClearwaterMeters() {
    return NAD1983HARNAdjMNClearwaterMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCookNorthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNCookNorthFeet() {
    return NAD1983HARNAdjMNCookNorthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCookNorthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNCookNorthMeters() {
    return NAD1983HARNAdjMNCookNorthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCookSouthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNCookSouthFeet() {
    return NAD1983HARNAdjMNCookSouthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCookSouthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNCookSouthMeters() {
    return NAD1983HARNAdjMNCookSouthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCottonwoodFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNCottonwoodFeet() {
    return NAD1983HARNAdjMNCottonwoodFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCottonwoodMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNCottonwoodMeters() {
    return NAD1983HARNAdjMNCottonwoodMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCrowWingFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNCrowWingFeet() {
    return NAD1983HARNAdjMNCrowWingFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCrowWingMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNCrowWingMeters() {
    return NAD1983HARNAdjMNCrowWingMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNDakotaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNDakotaFeet() {
    return NAD1983HARNAdjMNDakotaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNDakotaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNDakotaMeters() {
    return NAD1983HARNAdjMNDakotaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNDodgeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNDodgeFeet() {
    return NAD1983HARNAdjMNDodgeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNDodgeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNDodgeMeters() {
    return NAD1983HARNAdjMNDodgeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNDouglasFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNDouglasFeet() {
    return NAD1983HARNAdjMNDouglasFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNDouglasMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNDouglasMeters() {
    return NAD1983HARNAdjMNDouglasMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNFaribaultFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNFaribaultFeet() {
    return NAD1983HARNAdjMNFaribaultFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNFaribaultMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNFaribaultMeters() {
    return NAD1983HARNAdjMNFaribaultMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNFillmoreFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNFillmoreFeet() {
    return NAD1983HARNAdjMNFillmoreFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNFillmoreMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNFillmoreMeters() {
    return NAD1983HARNAdjMNFillmoreMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNFreebornFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNFreebornFeet() {
    return NAD1983HARNAdjMNFreebornFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNFreebornMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNFreebornMeters() {
    return NAD1983HARNAdjMNFreebornMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNGoodhueFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNGoodhueFeet() {
    return NAD1983HARNAdjMNGoodhueFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNGoodhueMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNGoodhueMeters() {
    return NAD1983HARNAdjMNGoodhueMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNGrantFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNGrantFeet() {
    return NAD1983HARNAdjMNGrantFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNGrantMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNGrantMeters() {
    return NAD1983HARNAdjMNGrantMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNHennepinFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNHennepinFeet() {
    return NAD1983HARNAdjMNHennepinFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNHennepinMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNHennepinMeters() {
    return NAD1983HARNAdjMNHennepinMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNHoustonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNHoustonFeet() {
    return NAD1983HARNAdjMNHoustonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNHoustonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNHoustonMeters() {
    return NAD1983HARNAdjMNHoustonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNHubbardFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNHubbardFeet() {
    return NAD1983HARNAdjMNHubbardFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNHubbardMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNHubbardMeters() {
    return NAD1983HARNAdjMNHubbardMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNIsantiFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNIsantiFeet() {
    return NAD1983HARNAdjMNIsantiFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNIsantiMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNIsantiMeters() {
    return NAD1983HARNAdjMNIsantiMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNItascaNorthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNItascaNorthFeet() {
    return NAD1983HARNAdjMNItascaNorthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNItascaNorthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNItascaNorthMeters() {
    return NAD1983HARNAdjMNItascaNorthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNItascaSouthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNItascaSouthFeet() {
    return NAD1983HARNAdjMNItascaSouthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNItascaSouthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNItascaSouthMeters() {
    return NAD1983HARNAdjMNItascaSouthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNJacksonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNJacksonFeet() {
    return NAD1983HARNAdjMNJacksonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNJacksonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNJacksonMeters() {
    return NAD1983HARNAdjMNJacksonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKanabecFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNKanabecFeet() {
    return NAD1983HARNAdjMNKanabecFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKanabecMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNKanabecMeters() {
    return NAD1983HARNAdjMNKanabecMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKandiyohiFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNKandiyohiFeet() {
    return NAD1983HARNAdjMNKandiyohiFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKandiyohiMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNKandiyohiMeters() {
    return NAD1983HARNAdjMNKandiyohiMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKittsonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNKittsonFeet() {
    return NAD1983HARNAdjMNKittsonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKittsonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNKittsonMeters() {
    return NAD1983HARNAdjMNKittsonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKoochichingFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNKoochichingFeet() {
    return NAD1983HARNAdjMNKoochichingFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKoochichingMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNKoochichingMeters() {
    return NAD1983HARNAdjMNKoochichingMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLacQuiParleFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNLacQuiParleFeet() {
    return NAD1983HARNAdjMNLacQuiParleFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLacQuiParleMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNLacQuiParleMeters() {
    return NAD1983HARNAdjMNLacQuiParleMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLakeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNLakeFeet() {
    return NAD1983HARNAdjMNLakeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLakeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNLakeMeters() {
    return NAD1983HARNAdjMNLakeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLakeoftheWoodsNorthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNLakeoftheWoodsNorthFeet() {
    return NAD1983HARNAdjMNLakeoftheWoodsNorthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLakeoftheWoodsNorthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNLakeoftheWoodsNorthMeters() {
    return NAD1983HARNAdjMNLakeoftheWoodsNorthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLakeoftheWoodsSouthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNLakeoftheWoodsSouthFeet() {
    return NAD1983HARNAdjMNLakeoftheWoodsSouthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLakeoftheWoodsSouthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNLakeoftheWoodsSouthMeters() {
    return NAD1983HARNAdjMNLakeoftheWoodsSouthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLeSueurFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNLeSueurFeet() {
    return NAD1983HARNAdjMNLeSueurFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLeSueurMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNLeSueurMeters() {
    return NAD1983HARNAdjMNLeSueurMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLincolnFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNLincolnFeet() {
    return NAD1983HARNAdjMNLincolnFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLincolnMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNLincolnMeters() {
    return NAD1983HARNAdjMNLincolnMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLyonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNLyonFeet() {
    return NAD1983HARNAdjMNLyonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLyonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNLyonMeters() {
    return NAD1983HARNAdjMNLyonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMahnomenFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNMahnomenFeet() {
    return NAD1983HARNAdjMNMahnomenFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMahnomenMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNMahnomenMeters() {
    return NAD1983HARNAdjMNMahnomenMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMarshallFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNMarshallFeet() {
    return NAD1983HARNAdjMNMarshallFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMarshallMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNMarshallMeters() {
    return NAD1983HARNAdjMNMarshallMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMartinFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNMartinFeet() {
    return NAD1983HARNAdjMNMartinFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMartinMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNMartinMeters() {
    return NAD1983HARNAdjMNMartinMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMcLeodFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNMcLeodFeet() {
    return NAD1983HARNAdjMNMcLeodFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMcLeodMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNMcLeodMeters() {
    return NAD1983HARNAdjMNMcLeodMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMeekerFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNMeekerFeet() {
    return NAD1983HARNAdjMNMeekerFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMeekerMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNMeekerMeters() {
    return NAD1983HARNAdjMNMeekerMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMilleLacsFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNMilleLacsFeet() {
    return NAD1983HARNAdjMNMilleLacsFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMilleLacsMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNMilleLacsMeters() {
    return NAD1983HARNAdjMNMilleLacsMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMorrisonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNMorrisonFeet() {
    return NAD1983HARNAdjMNMorrisonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMorrisonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNMorrisonMeters() {
    return NAD1983HARNAdjMNMorrisonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMowerFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNMowerFeet() {
    return NAD1983HARNAdjMNMowerFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMowerMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNMowerMeters() {
    return NAD1983HARNAdjMNMowerMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMurrayFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNMurrayFeet() {
    return NAD1983HARNAdjMNMurrayFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMurrayMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNMurrayMeters() {
    return NAD1983HARNAdjMNMurrayMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNNicolletFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNNicolletFeet() {
    return NAD1983HARNAdjMNNicolletFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNNicolletMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNNicolletMeters() {
    return NAD1983HARNAdjMNNicolletMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNNoblesFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNNoblesFeet() {
    return NAD1983HARNAdjMNNoblesFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNNoblesMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNNoblesMeters() {
    return NAD1983HARNAdjMNNoblesMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNNormanFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNNormanFeet() {
    return NAD1983HARNAdjMNNormanFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNNormanMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNNormanMeters() {
    return NAD1983HARNAdjMNNormanMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNOlmstedFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNOlmstedFeet() {
    return NAD1983HARNAdjMNOlmstedFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNOlmstedMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNOlmstedMeters() {
    return NAD1983HARNAdjMNOlmstedMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNOttertailFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNOttertailFeet() {
    return NAD1983HARNAdjMNOttertailFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNOttertailMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNOttertailMeters() {
    return NAD1983HARNAdjMNOttertailMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPenningtonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNPenningtonFeet() {
    return NAD1983HARNAdjMNPenningtonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPenningtonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNPenningtonMeters() {
    return NAD1983HARNAdjMNPenningtonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPineFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNPineFeet() {
    return NAD1983HARNAdjMNPineFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPineMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNPineMeters() {
    return NAD1983HARNAdjMNPineMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPipestoneFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNPipestoneFeet() {
    return NAD1983HARNAdjMNPipestoneFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPipestoneMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNPipestoneMeters() {
    return NAD1983HARNAdjMNPipestoneMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPolkFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNPolkFeet() {
    return NAD1983HARNAdjMNPolkFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPolkMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNPolkMeters() {
    return NAD1983HARNAdjMNPolkMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPopeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNPopeFeet() {
    return NAD1983HARNAdjMNPopeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPopeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNPopeMeters() {
    return NAD1983HARNAdjMNPopeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRamseyFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNRamseyFeet() {
    return NAD1983HARNAdjMNRamseyFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRamseyMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNRamseyMeters() {
    return NAD1983HARNAdjMNRamseyMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRedLakeFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNRedLakeFeet() {
    return NAD1983HARNAdjMNRedLakeFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRedLakeMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNRedLakeMeters() {
    return NAD1983HARNAdjMNRedLakeMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRedwoodFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNRedwoodFeet() {
    return NAD1983HARNAdjMNRedwoodFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRedwoodMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNRedwoodMeters() {
    return NAD1983HARNAdjMNRedwoodMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRenvilleFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNRenvilleFeet() {
    return NAD1983HARNAdjMNRenvilleFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRenvilleMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNRenvilleMeters() {
    return NAD1983HARNAdjMNRenvilleMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRiceFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNRiceFeet() {
    return NAD1983HARNAdjMNRiceFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRiceMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNRiceMeters() {
    return NAD1983HARNAdjMNRiceMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRockFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNRockFeet() {
    return NAD1983HARNAdjMNRockFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRockMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNRockMeters() {
    return NAD1983HARNAdjMNRockMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRoseauFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNRoseauFeet() {
    return NAD1983HARNAdjMNRoseauFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRoseauMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNRoseauMeters() {
    return NAD1983HARNAdjMNRoseauMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNScottFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNScottFeet() {
    return NAD1983HARNAdjMNScottFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNScottMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNScottMeters() {
    return NAD1983HARNAdjMNScottMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSherburneFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNSherburneFeet() {
    return NAD1983HARNAdjMNSherburneFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSherburneMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNSherburneMeters() {
    return NAD1983HARNAdjMNSherburneMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSibleyFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNSibleyFeet() {
    return NAD1983HARNAdjMNSibleyFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSibleyMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNSibleyMeters() {
    return NAD1983HARNAdjMNSibleyMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStLouisCentralFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNStLouisCentralFeet() {
    return NAD1983HARNAdjMNStLouisCentralFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStLouisCentralMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNStLouisCentralMeters() {
    return NAD1983HARNAdjMNStLouisCentralMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStLouisNorthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNStLouisNorthFeet() {
    return NAD1983HARNAdjMNStLouisNorthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStLouisNorthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNStLouisNorthMeters() {
    return NAD1983HARNAdjMNStLouisNorthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStLouisSouthFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNStLouisSouthFeet() {
    return NAD1983HARNAdjMNStLouisSouthFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStLouisSouthMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNStLouisSouthMeters() {
    return NAD1983HARNAdjMNStLouisSouthMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStearnsFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNStearnsFeet() {
    return NAD1983HARNAdjMNStearnsFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStearnsMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNStearnsMeters() {
    return NAD1983HARNAdjMNStearnsMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSteeleFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNSteeleFeet() {
    return NAD1983HARNAdjMNSteeleFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSteeleMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNSteeleMeters() {
    return NAD1983HARNAdjMNSteeleMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStevensFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNStevensFeet() {
    return NAD1983HARNAdjMNStevensFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStevensMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNStevensMeters() {
    return NAD1983HARNAdjMNStevensMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSwiftFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNSwiftFeet() {
    return NAD1983HARNAdjMNSwiftFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSwiftMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNSwiftMeters() {
    return NAD1983HARNAdjMNSwiftMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNToddFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNToddFeet() {
    return NAD1983HARNAdjMNToddFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNToddMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNToddMeters() {
    return NAD1983HARNAdjMNToddMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNTraverseFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNTraverseFeet() {
    return NAD1983HARNAdjMNTraverseFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNTraverseMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNTraverseMeters() {
    return NAD1983HARNAdjMNTraverseMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWabashaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNWabashaFeet() {
    return NAD1983HARNAdjMNWabashaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWabashaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNWabashaMeters() {
    return NAD1983HARNAdjMNWabashaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWadenaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNWadenaFeet() {
    return NAD1983HARNAdjMNWadenaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWadenaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNWadenaMeters() {
    return NAD1983HARNAdjMNWadenaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWasecaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNWasecaFeet() {
    return NAD1983HARNAdjMNWasecaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWasecaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNWasecaMeters() {
    return NAD1983HARNAdjMNWasecaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWashingtonFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNWashingtonFeet() {
    return NAD1983HARNAdjMNWashingtonFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWashingtonMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNWashingtonMeters() {
    return NAD1983HARNAdjMNWashingtonMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWatonwanFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNWatonwanFeet() {
    return NAD1983HARNAdjMNWatonwanFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWatonwanMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNWatonwanMeters() {
    return NAD1983HARNAdjMNWatonwanMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWilkinFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNWilkinFeet() {
    return NAD1983HARNAdjMNWilkinFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWilkinMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNWilkinMeters() {
    return NAD1983HARNAdjMNWilkinMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWinonaFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNWinonaFeet() {
    return NAD1983HARNAdjMNWinonaFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWinonaMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNWinonaMeters() {
    return NAD1983HARNAdjMNWinonaMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWrightFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNWrightFeet() {
    return NAD1983HARNAdjMNWrightFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWrightMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNWrightMeters() {
    return NAD1983HARNAdjMNWrightMeters.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNYellowMedicineFeet
   */
  public ProjectionInfo getNAD1983HARNAdjMNYellowMedicineFeet() {
    return NAD1983HARNAdjMNYellowMedicineFeet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNYellowMedicineMeters
   */
  public ProjectionInfo getNAD1983HARNAdjMNYellowMedicineMeters() {
    return NAD1983HARNAdjMNYellowMedicineMeters.copy();
  }
    }


