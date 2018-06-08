/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class NationalGrids extends CoordinateSystemCategory {

  //<editor-fold defaultstate="collapsed" desc="Fields">


        private final ProjectionInfo Abidjan1987TM5NW;
        private final ProjectionInfo AccraGhanaGrid;
        private final ProjectionInfo AccraTM1NW;
        private final ProjectionInfo AinelAbdAramcoLambert;
        private final ProjectionInfo AmericanSamoa1962SamoaLambert;
        private final ProjectionInfo Anguilla1957BritishWestIndiesGrid;
        private final ProjectionInfo Antigua1943BritishWestIndiesGrid;
        private final ProjectionInfo ArgentinaZone1;
        private final ProjectionInfo ArgentinaZone2;
        private final ProjectionInfo ArgentinaZone3;
        private final ProjectionInfo ArgentinaZone4;
        private final ProjectionInfo ArgentinaZone5;
        private final ProjectionInfo ArgentinaZone6;
        private final ProjectionInfo ArgentinaZone7;
        private final ProjectionInfo AustriaFerroCentralZone;
        private final ProjectionInfo AustriaFerroEastZone;
        private final ProjectionInfo AustriaFerroWestZone;
        private final ProjectionInfo BahrainStateGrid;
        private final ProjectionInfo Barbados1938BarbadosGrid;
        private final ProjectionInfo Barbados1938BritishWestIndiesGrid;
        private final ProjectionInfo BataviaNEIEZ;
        private final ProjectionInfo BataviaTM109SE;
        private final ProjectionInfo BelgeLambert1950;
        private final ProjectionInfo BelgeLambert1972;
        private final ProjectionInfo Bermuda2000NationalGrid;
        private final ProjectionInfo Bern1898BernLV03C;
        private final ProjectionInfo BritishNationalGridOSGB36;
        private final ProjectionInfo CH1903LV03;
        private final ProjectionInfo CH1903LV95;
        private final ProjectionInfo CamacupaTM1130SE;
        private final ProjectionInfo CamacupaTM12SE;
        private final ProjectionInfo CarthageTM11NE;
        private final ProjectionInfo CentreFrance;
        private final ProjectionInfo ChosMalal1914Argentina2;
        private final ProjectionInfo ColombiaBogotaZone;
        private final ProjectionInfo ColombiaECentralZone;
        private final ProjectionInfo ColombiaEastZone;
        private final ProjectionInfo ColombiaWestZone;
        private final ProjectionInfo Corse;
        private final ProjectionInfo DHDN3DegreeGaussZone1;
        private final ProjectionInfo DHDN3DegreeGaussZone2;
        private final ProjectionInfo DHDN3DegreeGaussZone3;
        private final ProjectionInfo DHDN3DegreeGaussZone4;
        private final ProjectionInfo DHDN3DegreeGaussZone5;
        private final ProjectionInfo Datum73HayfordGaussIGeoE;
        private final ProjectionInfo Datum73HayfordGaussIPCC;
        private final ProjectionInfo DeirezZorLevantStereographic;
        private final ProjectionInfo DeirezZorLevantZone;
        private final ProjectionInfo DeirezZorSyriaLambert;
        private final ProjectionInfo Dominica1945BritishWestIndiesGrid;
        private final ProjectionInfo Douala1948AOFWest;
        //public final ProjectionInfo DutchRD;
        private final ProjectionInfo ED1950FranceEuroLambert;
        private final ProjectionInfo ED1950TM0N;
        private final ProjectionInfo ED1950TM27;
        private final ProjectionInfo ED1950TM30;
        private final ProjectionInfo ED1950TM33;
        private final ProjectionInfo ED1950TM36;
        private final ProjectionInfo ED1950TM39;
        private final ProjectionInfo ED1950TM42;
        private final ProjectionInfo ED1950TM45;
        private final ProjectionInfo ED1950TM5NE;
        private final ProjectionInfo ED1950Turkey10;
        private final ProjectionInfo ED1950Turkey11;
        private final ProjectionInfo ED1950Turkey12;
        private final ProjectionInfo ED1950Turkey13;
        private final ProjectionInfo ED1950Turkey14;
        private final ProjectionInfo ED1950Turkey15;
        private final ProjectionInfo ED1950Turkey9;
        private final ProjectionInfo ELD1979Libya10;
        private final ProjectionInfo ELD1979Libya11;
        private final ProjectionInfo ELD1979Libya12;
        private final ProjectionInfo ELD1979Libya13;
        private final ProjectionInfo ELD1979Libya5;
        private final ProjectionInfo ELD1979Libya6;
        private final ProjectionInfo ELD1979Libya7;
        private final ProjectionInfo ELD1979Libya8;
        private final ProjectionInfo ELD1979Libya9;
        private final ProjectionInfo ELD1979TM12NE;
        private final ProjectionInfo ETRF1989TMBaltic1993;
        private final ProjectionInfo ETRS1989Kp2000Bornholm;
        private final ProjectionInfo ETRS1989Kp2000Jutland;
        private final ProjectionInfo ETRS1989Kp2000Zealand;
        private final ProjectionInfo ETRS1989PolandCS2000Zone5;
        private final ProjectionInfo ETRS1989PolandCS2000Zone6;
        private final ProjectionInfo ETRS1989PolandCS2000Zone7;
        private final ProjectionInfo ETRS1989PolandCS2000Zone8;
        private final ProjectionInfo ETRS1989PolandCS92;
        private final ProjectionInfo ETRS1989TM30NE;
        private final ProjectionInfo ETRS1989TMBaltic1993;
        private final ProjectionInfo ETRS1989UWPP1992;
        private final ProjectionInfo ETRS1989UWPP2000PAS5;
        private final ProjectionInfo ETRS1989UWPP2000PAS6;
        private final ProjectionInfo ETRS1989UWPP2000PAS7;
        private final ProjectionInfo ETRS1989UWPP2000PAS8;
        private final ProjectionInfo EUREFFINTM35FIN;
        private final ProjectionInfo EgyptBlueBelt;
        private final ProjectionInfo EgyptExtendedPurpleBelt;
        private final ProjectionInfo EgyptPurpleBelt;
        private final ProjectionInfo EgyptRedBelt;
        private final ProjectionInfo Estonia1997EstoniaNationalGrid;
        private final ProjectionInfo EstonianCoordinateSystemof1992;
        //public final ProjectionInfo EverestModified1969RSOMalayaMeters;
        private final ProjectionInfo FD1958Iraq;
        private final ProjectionInfo FinlandZone1;
        private final ProjectionInfo FinlandZone2;
        private final ProjectionInfo FinlandZone3;
        private final ProjectionInfo FinlandZone4;
        private final ProjectionInfo FranceI;
        private final ProjectionInfo FranceII;
        private final ProjectionInfo FranceIII;
        private final ProjectionInfo FranceIV;
        private final ProjectionInfo GermanyZone1;
        private final ProjectionInfo GermanyZone2;
        private final ProjectionInfo GermanyZone3;
        private final ProjectionInfo GermanyZone4;
        private final ProjectionInfo GermanyZone5;
        private final ProjectionInfo GhanaMetreGrid;
        private final ProjectionInfo GreekGrid;
        private final ProjectionInfo Grenada1953BritishWestIndiesGrid;
        private final ProjectionInfo GuernseyGrid;
        private final ProjectionInfo GunungSegaraNEIEZ;
        private final ProjectionInfo HD1972EgysegesOrszagosVetuleti;
        private final ProjectionInfo Hanoi1972GK106NE;
        private final ProjectionInfo Helle1954JanMayenGrid;
        private final ProjectionInfo HitoXVIII1963Argentina2;
        private final ProjectionInfo HongKong1980Grid;
        private final ProjectionInfo IRENET95IrishTranverseMercator;
        private final ProjectionInfo ISN1993Lambert1993;
        private final ProjectionInfo Indian1960TM106NE;
        private final ProjectionInfo IrishNationalGrid;
        private final ProjectionInfo IsraelTMGrid;
        private final ProjectionInfo Jamaica1875OldGrid;
        private final ProjectionInfo JamaicaGrid;
        private final ProjectionInfo JordanJTM;
        private final ProjectionInfo KOCLambert;
        private final ProjectionInfo KUDAMSKTM;
        private final ProjectionInfo KandawalaCeylonBeltIndianYards1937;
        private final ProjectionInfo KandawalaCeylonBeltMeters;
        private final ProjectionInfo KertauRSOMalayaChains;
        private final ProjectionInfo KertauRSOMalayaMeters;
        private final ProjectionInfo KertauSingaporeGrid;
        private final ProjectionInfo Korean1985KoreaCentralBelt;
        private final ProjectionInfo Korean1985KoreaEastBelt;
        private final ProjectionInfo Korean1985KoreaWestBelt;
        private final ProjectionInfo KuwaitOilCoLambert;
        private final ProjectionInfo KuwaitUtilityKTM;
        private final ProjectionInfo LakeMaracaiboGrid;
        private final ProjectionInfo LakeMaracaiboGridM1;
        private final ProjectionInfo LakeMaracaiboGridM3;
        private final ProjectionInfo LakeMaracaiboLaRosaGrid;
        private final ProjectionInfo LietuvosKoordinaciuSistema;
        private final ProjectionInfo LisboaBesselBonne;
        private final ProjectionInfo LisboaHayfordGaussIGeoE;
        private final ProjectionInfo LisboaHayfordGaussIPCC;
        private final ProjectionInfo Locodjo1965TM5NW;
        private final ProjectionInfo Luxembourg1930Gauss;
        private final ProjectionInfo MGI3DegreeGaussZone5;
        private final ProjectionInfo MGI3DegreeGaussZone6;
        private final ProjectionInfo MGI3DegreeGaussZone7;
        private final ProjectionInfo MGI3DegreeGaussZone8;
        private final ProjectionInfo MGIAustriaLambert;
        private final ProjectionInfo MGIBalkans5;
        private final ProjectionInfo MGIBalkans6;
        private final ProjectionInfo MGIBalkans7;
        private final ProjectionInfo MGIBalkans8;
        private final ProjectionInfo MGIM28;
        private final ProjectionInfo MGIM31;
        private final ProjectionInfo MGIM34;
        private final ProjectionInfo MGISloveniaGrid;
        private final ProjectionInfo Madrid1870MadridSpain;
        private final ProjectionInfo MakassarNEIEZ;
        private final ProjectionInfo MonteMarioItaly1;
        private final ProjectionInfo MonteMarioItaly2;
        private final ProjectionInfo MonteMarioRomeItaly1;
        private final ProjectionInfo MonteMarioRomeItaly2;
        private final ProjectionInfo Montserrat1958BritishWestIndiesGrid;
        private final ProjectionInfo MountDillonTobagoGrid;
        private final ProjectionInfo NAD1927CubaNorte;
        private final ProjectionInfo NAD1927CubaSur;
        private final ProjectionInfo NAD1927GuatemalaNorte;
        private final ProjectionInfo NAD1927GuatemalaSur;
        private final ProjectionInfo NAD1927MichiganGeoRefMeters;
        private final ProjectionInfo NAD1927MichiganGeoRefUSfeet;
        private final ProjectionInfo NAD1983HARNGuamMapGrid;
        private final ProjectionInfo NAD1983MichiganGeoRefMeters;
        private final ProjectionInfo NAD1983MichiganGeoRefUSfeet;
        private final ProjectionInfo NAD1983MichiganGeoReferencedMeters;
        private final ProjectionInfo NTFFranceIIIdegrees;
        private final ProjectionInfo NTFFranceIIdegrees;
        private final ProjectionInfo NTFFranceIVdegrees;
        private final ProjectionInfo NTFFranceIdegrees;
        private final ProjectionInfo NewZealandMapGrid;
        private final ProjectionInfo NewZealandNorthIsland;
        private final ProjectionInfo NewZealandSouthIsland;
        private final ProjectionInfo NigeriaEastBelt;
        private final ProjectionInfo NigeriaMidBelt;
        private final ProjectionInfo NigeriaWestBelt;
        private final ProjectionInfo NordAlgerie;
        private final ProjectionInfo NordAlgerieAnciennedegrees;
        private final ProjectionInfo NordAlgerieancienne;
        private final ProjectionInfo NordAlgeriedegrees;
        private final ProjectionInfo NordFrance;
        private final ProjectionInfo NordMaroc;
        private final ProjectionInfo NordMarocdegrees;
        private final ProjectionInfo NordTunisie;
        private final ProjectionInfo NorddeGuerre;
        private final ProjectionInfo OSNI1952IrishNationalGrid;
        private final ProjectionInfo ObservatorioMeteorologico1965MacauGrid;
        private final ProjectionInfo PSAD1956ICNRegional;
        private final ProjectionInfo Palestine1923IsraelCSGrid;
        private final ProjectionInfo Palestine1923PalestineBelt;
        private final ProjectionInfo Palestine1923PalestineGrid;
        private final ProjectionInfo PampadelCastilloArgentina2;
        private final ProjectionInfo PeruCentralZone;
        private final ProjectionInfo PeruEastZone;
        private final ProjectionInfo PeruWestZone;
        private final ProjectionInfo PhilippinesZoneI;
        private final ProjectionInfo PhilippinesZoneII;
        private final ProjectionInfo PhilippinesZoneIII;
        private final ProjectionInfo PhilippinesZoneIV;
        private final ProjectionInfo PhilippinesZoneV;
        private final ProjectionInfo PitondesNeigesTMReunion;
        private final ProjectionInfo PortugueseNationalGrid;
        private final ProjectionInfo Qatar1948QatarGrid;
        private final ProjectionInfo QatarNationalGrid;
        private final ProjectionInfo RGF1993Lambert93;
        private final ProjectionInfo RGNC1991LambertNewCaledonia;
        private final ProjectionInfo RT9025gonWest;
        private final ProjectionInfo RassadiranNakhleTaqi;
        //public final ProjectionInfo Rijksdriehoekstelsel;
        private final ProjectionInfo Roma1940GaussBoagaEst;
        private final ProjectionInfo Roma1940GaussBoagaOvest;
        private final ProjectionInfo SAD1969BrazilPolyconic;
        private final ProjectionInfo SJTSKFerroKrovak;
        private final ProjectionInfo SJTSKFerroKrovakEastNorth;
        private final ProjectionInfo SJTSKKrovak;
        private final ProjectionInfo SJTSKKrovakEastNorth;
        private final ProjectionInfo Sahara;
        private final ProjectionInfo Saharadegrees;
        private final ProjectionInfo SierraLeone1924NewColonyGrid;
        private final ProjectionInfo SierraLeone1924NewWarOfficeGrid;
        private final ProjectionInfo StKitts1955BritishWestIndiesGrid;
        private final ProjectionInfo StLucia1955BritishWestIndiesGrid;
        private final ProjectionInfo StVincent1945BritishWestIndiesGrid;
        //public final ProjectionInfo Stereo1933;
        //public final ProjectionInfo Stereo1970;
        private final ProjectionInfo SudAlgerie;
        private final ProjectionInfo SudAlgerieAncienneDegree;
        private final ProjectionInfo SudAlgeriedegrees;
        private final ProjectionInfo SudFrance;
        private final ProjectionInfo SudMaroc;
        private final ProjectionInfo SudMarocdegrees;
        private final ProjectionInfo SudTunisie;
        private final ProjectionInfo SwedishNationalGrid;
        private final ProjectionInfo TM75IrishGrid;
        private final ProjectionInfo Timbalai1948RSOBorneoChains;
        private final ProjectionInfo Timbalai1948RSOBorneoFeet;
        private final ProjectionInfo Timbalai1948RSOBorneoMeters;
        private final ProjectionInfo Trinidad1903TrinidadGrid;
        private final ProjectionInfo Trinidad1903TrinidadGridFeetClarke;
        private final ProjectionInfo UWPP1992;
        private final ProjectionInfo UWPP2000pas5;
        private final ProjectionInfo UWPP2000pas6;
        private final ProjectionInfo UWPP2000pas7;
        private final ProjectionInfo UWPP2000pas8;
        private final ProjectionInfo WGS1972TM106NE;
        private final ProjectionInfo WGS1984TM116SE;
        private final ProjectionInfo WGS1984TM132SE;
        private final ProjectionInfo WGS1984TM36SE;
        private final ProjectionInfo WGS1984TM6NE;
        private final ProjectionInfo ZanderijSurinameOldTM;
        private final ProjectionInfo ZanderijSurinameTM;
        private final ProjectionInfo ZanderijTM54NW;


 //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Constructor">


        /// <summary>
        /// Creates a new instance of NationalGrids.
        /// </summary>
        public NationalGrids()
        {
            Abidjan1987TM5NW = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-5 +k=0.999600 +x_0=500000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            AccraGhanaGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=4.666666666666667 +lon_0=-1 +k=0.999750 +x_0=274319.7391633579 +y_0=0 +a=6378300 +b=6356751.689189189 +to_meter=0.3047997101815088 +no_defs ").orElse(null);
            AccraTM1NW = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-1 +k=0.999600 +x_0=500000 +y_0=0 +a=6378300 +b=6356751.689189189 +units=m +no_defs ").orElse(null);
            AinelAbdAramcoLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=17 +lat_2=33 +lat_0=25.08951 +lon_0=48 +x_0=0 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            AmericanSamoa1962SamoaLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=-14.26666666666667 +lat_0=-14.26666666666667 +lon_0=-170 +k_0=1 +x_0=152400.3048006096 +y_0=0 +ellps=clrk66 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            Anguilla1957BritishWestIndiesGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-62 +k=0.999500 +x_0=400000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            Antigua1943BritishWestIndiesGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-62 +k=0.999500 +x_0=400000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            ArgentinaZone1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-90 +lon_0=-72 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ArgentinaZone2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-90 +lon_0=-69 +k=1.000000 +x_0=2500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ArgentinaZone3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-90 +lon_0=-66 +k=1.000000 +x_0=3500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ArgentinaZone4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-90 +lon_0=-63 +k=1.000000 +x_0=4500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ArgentinaZone5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-90 +lon_0=-60 +k=1.000000 +x_0=5500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ArgentinaZone6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-90 +lon_0=-57 +k=1.000000 +x_0=6500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ArgentinaZone7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-90 +lon_0=-54 +k=1.000000 +x_0=7500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            AustriaFerroCentralZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=48.66666666666667 +k=1.000000 +x_0=0 +y_0=0 +ellps=bessel +pm=-17.66666666666667 +units=m +no_defs ").orElse(null);
            AustriaFerroEastZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=51.66666666666667 +k=1.000000 +x_0=0 +y_0=0 +ellps=bessel +pm=-17.66666666666667 +units=m +no_defs ").orElse(null);
            AustriaFerroWestZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=45.66666666666667 +k=1.000000 +x_0=0 +y_0=0 +ellps=bessel +pm=-17.66666666666667 +units=m +no_defs ").orElse(null);
            BahrainStateGrid = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=intl +units=m +no_defs ").orElse(null);
            Barbados1938BarbadosGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=13.17638888888889 +lon_0=-59.55972222222222 +k=0.999999 +x_0=30000 +y_0=75000 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            Barbados1938BritishWestIndiesGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-62 +k=0.999500 +x_0=400000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            BataviaNEIEZ = ProjectionInfo.fromProj4String("+proj=merc +lat_ts=4.45405154589751 +lon_0=110 +k=1.000000 +x_0=3900000 +y_0=900000 +ellps=bessel +units=m +no_defs ").orElse(null);
            BataviaTM109SE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=109 +k=0.999600 +x_0=500000 +y_0=10000000 +ellps=bessel +units=m +no_defs ").orElse(null);
            BelgeLambert1950 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=49.83333333333334 +lat_2=51.16666666666666 +lat_0=90 +lon_0=-4.367975 +x_0=150000 +y_0=5400000 +ellps=intl +pm=4.367975 +units=m +no_defs ").orElse(null);
            BelgeLambert1972 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=49.8333339 +lat_2=51.16666733333333 +lat_0=90 +lon_0=4.367486666666666 +x_0=150000.01256 +y_0=5400088.4378 +ellps=intl +units=m +no_defs ").orElse(null);
            Bermuda2000NationalGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=32 +lon_0=-64.75 +k=1.000000 +x_0=550000 +y_0=100000 +ellps=WGS84 +units=m +no_defs ").orElse(null);
            Bern1898BernLV03C = ProjectionInfo.fromProj4String("+proj=somerc +lat_0=46.95240555555556 +lon_0=-7.439583333333333 +x_0=0 +y_0=0 +ellps=bessel +pm=7.439583333333333 +units=m +no_defs ").orElse(null);
            BritishNationalGridOSGB36 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=49 +lon_0=-2 +k=0.9996012717 +x_0=400000 +y_0=-100000 +ellps=airy +datum=OSGB36 +units=m +no_defs ").orElse(null);
            CamacupaTM1130SE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=11.5 +k=0.999600 +x_0=500000 +y_0=10000000 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            CamacupaTM12SE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=12 +k=0.999600 +x_0=500000 +y_0=10000000 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            CarthageTM11NE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=9.900000000000002 +k=0.999600 +x_0=500000 +y_0=0 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            CentreFrance = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.8 +lat_0=46.8 +lon_0=-2.337229166666667 +k_0=0.99987742 +x_0=600000 +y_0=200000 +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667 +units=m +no_defs ").orElse(null);
            CH1903LV03 = ProjectionInfo.fromProj4String("+proj=somerc +lat_0=46.95240555555556 +lon_0=7.439583333333333 +x_0=600000 +y_0=200000 +ellps=bessel +units=m +no_defs ").orElse(null);
            CH1903LV95 = ProjectionInfo.fromProj4String("+proj=somerc +lat_0=46.95240555555556 +lon_0=7.439583333333333 +x_0=2600000 +y_0=1200000 +ellps=bessel +units=m +no_defs ").orElse(null);
            ChosMalal1914Argentina2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-90 +lon_0=-69 +k=1.000000 +x_0=2500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ColombiaBogotaZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=4.599047222222222 +lon_0=-74.08091666666667 +k=1.000000 +x_0=1000000 +y_0=1000000 +ellps=intl +units=m +no_defs ").orElse(null);
            ColombiaEastZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=4.599047222222222 +lon_0=-68.08091666666667 +k=1.000000 +x_0=1000000 +y_0=1000000 +ellps=intl +units=m +no_defs ").orElse(null);
            ColombiaECentralZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=4.599047222222222 +lon_0=-71.08091666666667 +k=1.000000 +x_0=1000000 +y_0=1000000 +ellps=intl +units=m +no_defs ").orElse(null);
            ColombiaWestZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=4.599047222222222 +lon_0=-77.08091666666667 +k=1.000000 +x_0=1000000 +y_0=1000000 +ellps=intl +units=m +no_defs ").orElse(null);
            Corse = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.165 +lat_0=42.165 +lon_0=-2.337229166666667 +k_0=0.99994471 +x_0=234.358 +y_0=185861.369 +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667 +units=m +no_defs ").orElse(null);
            Datum73HayfordGaussIGeoE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=39.66666666666666 +lon_0=-8.131906111111112 +k=1.000000 +x_0=200180.598 +y_0=299913.01 +ellps=intl +units=m +no_defs ").orElse(null);
            Datum73HayfordGaussIPCC = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=39.66666666666666 +lon_0=-8.131906111111112 +k=1.000000 +x_0=180.598 +y_0=-86.99 +ellps=intl +units=m +no_defs ").orElse(null);
            DeirezZorLevantStereographic = ProjectionInfo.fromProj4String("+proj=sterea +lat_0=34.2 +lon_0=39.15 +k=0.9995341 +x_0=0 +y_0=0 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            DeirezZorLevantZone = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.65 +lat_0=34.65 +lon_0=37.35 +k_0=0.9996256 +x_0=300000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            DeirezZorSyriaLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=34.65 +lat_0=34.65 +lon_0=37.35 +k_0=0.9996256 +x_0=300000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            DHDN3DegreeGaussZone1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=3 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            DHDN3DegreeGaussZone2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=6 +k=1.000000 +x_0=2500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            DHDN3DegreeGaussZone3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=9 +k=1.000000 +x_0=3500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            DHDN3DegreeGaussZone4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=12 +k=1.000000 +x_0=4500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            DHDN3DegreeGaussZone5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=1.000000 +x_0=5500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            Dominica1945BritishWestIndiesGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-62 +k=0.999500 +x_0=400000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            Douala1948AOFWest = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=10.5 +k=0.999000 +x_0=1000000 +y_0=1000000 +ellps=intl +units=m +no_defs ").orElse(null);
            //DutchRD = ProjectionInfo.fromProj4String("+proj=sterea +lat_0=52.15616055555555 +lon_0=5.38763888888889 +k=0.999908 +x_0=155000 +y_0=463000 +ellps=bessel +units=m +towgs84=565.2369, 50.0087, 465.658, -0.406857330322398, 0.350732676542563, -1.8703473836068, 4.0812 +no_defs +to +proj=latlong +datum=WGS84 ").orElse(null);
            ED1950FranceEuroLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.8 +lat_0=46.8 +lon_0=2.337229166666667 +k_0=0.99987742 +x_0=600000 +y_0=2200000 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950TM0N = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=0 +k=0.999600 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950TM27 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=27 +k=1.000000 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950TM30 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=30 +k=1.000000 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950TM33 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=33 +k=1.000000 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950TM36 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=36 +k=1.000000 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950TM39 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=39 +k=1.000000 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950TM42 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=42 +k=1.000000 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950TM45 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=45 +k=1.000000 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950TM5NE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=5 +k=0.999600 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950Turkey10 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=30 +k=0.999600 +x_0=10500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950Turkey11 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=33 +k=0.999600 +x_0=11500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950Turkey12 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=36 +k=0.999600 +x_0=12500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950Turkey13 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=39 +k=0.999600 +x_0=13500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950Turkey14 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=42 +k=0.999600 +x_0=14500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950Turkey15 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=45 +k=0.999600 +x_0=15500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ED1950Turkey9 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=27 +k=0.999600 +x_0=9500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            EgyptBlueBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=35 +k=1.000000 +x_0=300000 +y_0=1100000 +ellps=helmert +units=m +no_defs ").orElse(null);
            EgyptExtendedPurpleBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=27 +k=1.000000 +x_0=700000 +y_0=1200000 +ellps=helmert +units=m +no_defs ").orElse(null);
            EgyptPurpleBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=27 +k=1.000000 +x_0=700000 +y_0=200000 +ellps=helmert +units=m +no_defs ").orElse(null);
            EgyptRedBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=30 +lon_0=31 +k=1.000000 +x_0=615000 +y_0=810000 +ellps=helmert +units=m +no_defs ").orElse(null);
            ELD1979Libya10 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=19 +k=0.999900 +x_0=200000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ELD1979Libya11 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=21 +k=0.999900 +x_0=200000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ELD1979Libya12 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=23 +k=0.999900 +x_0=200000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ELD1979Libya13 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=25 +k=0.999900 +x_0=200000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ELD1979Libya5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=9 +k=0.999900 +x_0=200000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ELD1979Libya6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=11 +k=0.999900 +x_0=200000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ELD1979Libya7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=13 +k=0.999900 +x_0=200000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ELD1979Libya8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=0.999900 +x_0=200000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ELD1979Libya9 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=17 +k=0.999900 +x_0=200000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ELD1979TM12NE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=12 +k=0.999600 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            Estonia1997EstoniaNationalGrid = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=58 +lat_2=59.33333333333334 +lat_0=57.51755393055556 +lon_0=24 +x_0=500000 +y_0=6375000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            EstonianCoordinateSystemof1992 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=58 +lat_2=59.33333333333334 +lat_0=57.51755393055556 +lon_0=24 +x_0=500000 +y_0=6375000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRF1989TMBaltic1993 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=0.999600 +x_0=500000 +y_0=0 +ellps=WGS84 +units=m +no_defs ").orElse(null);
            ETRS1989Kp2000Bornholm = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=1.000000 +x_0=900000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989Kp2000Jutland = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=9.5 +k=0.999950 +x_0=200000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989Kp2000Zealand = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=12 +k=0.999950 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989PolandCS2000Zone5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=0.999923 +x_0=5500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989PolandCS2000Zone6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=18 +k=0.999923 +x_0=6500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989PolandCS2000Zone7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=21 +k=0.999923 +x_0=7500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989PolandCS2000Zone8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=0.999923 +x_0=8500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989PolandCS92 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=19 +k=0.999300 +x_0=500000 +y_0=-5300000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989TM30NE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=30 +k=0.999600 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989TMBaltic1993 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=0.999600 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989UWPP1992 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=19 +k=0.999300 +x_0=500000 +y_0=-5300000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989UWPP2000PAS5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=0.999923 +x_0=5500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989UWPP2000PAS6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=18 +k=0.999923 +x_0=6500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989UWPP2000PAS7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=21 +k=0.999923 +x_0=7500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            ETRS1989UWPP2000PAS8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=0.999923 +x_0=8500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            EUREFFINTM35FIN = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            //EverestModified1969RSOMalayaMeters = ProjectionInfo.fromProj4String("+a=6377295.664 +b=6356094.667915204 +units=m +no_defs ").orElse(null);
            FD1958Iraq = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.5 +lat_0=32.5 +lon_0=45 +k_0=0.9987864077700001 +x_0=1500000 +y_0=1166200 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            FinlandZone1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=21 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            FinlandZone2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=1.000000 +x_0=2500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            FinlandZone3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=27 +k=1.000000 +x_0=3500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            FinlandZone4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=30 +k=1.000000 +x_0=4500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            FranceI = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=49.49999999999999 +lat_0=49.49999999999999 +lon_0=-2.337229166666667 +k_0=0.999877341 +x_0=600000 +y_0=1200000 +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667 +units=m +no_defs ").orElse(null);
            FranceII = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.8 +lat_0=46.8 +lon_0=-2.337229166666667 +k_0=0.99987742 +x_0=600000 +y_0=2200000 +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667 +units=m +no_defs ").orElse(null);
            FranceIII = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.09999999999999 +lat_0=44.09999999999999 +lon_0=-2.337229166666667 +k_0=0.999877499 +x_0=600000 +y_0=3200000 +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667 +units=m +no_defs ").orElse(null);
            FranceIV = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.165 +lat_0=42.165 +lon_0=-2.337229166666667 +k_0=0.99994471 +x_0=234.358 +y_0=185861.369 +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667 +units=m +no_defs ").orElse(null);
            GermanyZone1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=3 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            GermanyZone2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=6 +k=1.000000 +x_0=2500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            GermanyZone3 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=9 +k=1.000000 +x_0=3500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            GermanyZone4 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=12 +k=1.000000 +x_0=4500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            GermanyZone5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=1.000000 +x_0=5500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            GhanaMetreGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=4.666666666666667 +lon_0=-1 +k=0.999750 +x_0=274319.51 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            GreekGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=0.999600 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            Grenada1953BritishWestIndiesGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-62 +k=0.999500 +x_0=400000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            GuernseyGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=49.5 +lon_0=-2.416666666666667 +k=0.999997 +x_0=47000 +y_0=50000 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            GunungSegaraNEIEZ = ProjectionInfo.fromProj4String("+proj=merc +lat_ts=4.45405154589751 +lon_0=110 +k=1.000000 +x_0=3900000 +y_0=900000 +ellps=bessel +units=m +no_defs ").orElse(null);
            Hanoi1972GK106NE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=106 +k=1.000000 +x_0=500000 +y_0=0 +ellps=krass +units=m +no_defs ").orElse(null);
            HD1972EgysegesOrszagosVetuleti = ProjectionInfo.fromProj4String("+proj=somerc +lat_0=47.14439372222 +lon_0=19.048571778 +x_0=650000 +y_0=200000 +ellps=GRS67 +units=m +no_defs ").orElse(null);
            Helle1954JanMayenGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-8.5 +k=1.000000 +x_0=50000 +y_0=-7800000 +ellps=intl +units=m +no_defs ").orElse(null);
            HitoXVIII1963Argentina2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-90 +lon_0=-69 +k=1.000000 +x_0=2500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            HongKong1980Grid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=22.31213333333334 +lon_0=114.1785555555556 +k=1.000000 +x_0=836694.0500000001 +y_0=819069.8000000001 +ellps=intl +units=m +no_defs ").orElse(null);
            Indian1960TM106NE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=106 +k=0.999600 +x_0=500000 +y_0=0 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
            IRENET95IrishTranverseMercator = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=53.5 +lon_0=-8 +k=0.999820 +x_0=600000 +y_0=750000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            IrishNationalGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=53.5 +lon_0=-8 +k=1.000035 +x_0=200000 +y_0=250000 +a=6377340.189 +b=6356034.447938534 +units=m +no_defs ").orElse(null);
            ISN1993Lambert1993 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=64.25 +lat_2=65.75 +lat_0=65 +lon_0=-19 +x_0=500000 +y_0=500000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            IsraelTMGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31.73439361111111 +lon_0=35.20451694444445 +k=1.000007 +x_0=219529.584 +y_0=626907.39 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            Jamaica1875OldGrid = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=18 +lat_0=18 +lon_0=-77 +k_0=1 +x_0=550000 +y_0=400000 +a=6378249.138 +b=6356514.959419348 +units=m +no_defs ").orElse(null);
            JamaicaGrid = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=18 +lat_0=18 +lon_0=-77 +k_0=1 +x_0=250000 +y_0=150000 +ellps=clrk66 +units=m +no_defs ").orElse(null);
            JordanJTM = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=37 +k=0.999800 +x_0=500000 +y_0=-3000000 +ellps=intl +units=m +no_defs ").orElse(null);
            KandawalaCeylonBeltIndianYards1937 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=7.000480277777778 +lon_0=80.77171111111112 +k=1.000000 +x_0=160933.56048 +y_0=160933.56048 +a=6377276.345 +b=6356075.41314024 +to_meter=0.91439523 +no_defs ").orElse(null);
            KandawalaCeylonBeltMeters = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=7.000480277777778 +lon_0=80.77171111111112 +k=1.000000 +x_0=160933.56048 +y_0=160933.56048 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
            KertauRSOMalayaChains = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=4 +lonc=102.25 +alpha=323.0257905 +k=0.99984 +x_0=804671.2997750348 +y_0=0 +no_uoff +gamma=323.1301023611111 +a=6377304.063 +b=6356103.038993155 +towgs84=-11,851,5,0,0,0,0 +to_meter=20.11678249437587 +no_defs").orElse(null);
            KertauRSOMalayaMeters = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=4 +lonc=102.25 +alpha=323.0257905 +k=0.99984 +x_0=804670.24 +y_0=0 +a=6377295.664 +b=6356094.667915204 +units=m +no_defs").orElse(null);
            KertauSingaporeGrid = ProjectionInfo.fromProj4String("+proj=cass +lat_0=1.287646666666667 +lon_0=103.8530022222222 +x_0=30000 +y_0=30000 +a=6377304.063 +b=6356103.038993155 +units=m +no_defs ").orElse(null);
            KOCLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.5 +lat_0=32.5 +lon_0=45 +k_0=0.998786407767 +x_0=1500000 +y_0=1166200 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            Korean1985KoreaCentralBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38 +lon_0=127 +k=1.000000 +x_0=200000 +y_0=500000 +ellps=bessel +units=m +no_defs ").orElse(null);
            Korean1985KoreaEastBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38 +lon_0=129 +k=1.000000 +x_0=200000 +y_0=500000 +ellps=bessel +units=m +no_defs ").orElse(null);
            Korean1985KoreaWestBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=38 +lon_0=125 +k=1.000000 +x_0=200000 +y_0=500000 +ellps=bessel +units=m +no_defs ").orElse(null);
            KUDAMSKTM = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=48 +k=0.999600 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            KuwaitOilCoLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.5 +lat_0=32.5 +lon_0=45 +k_0=0.998786407767 +x_0=1500000 +y_0=1166200 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            KuwaitUtilityKTM = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=48 +k=0.999600 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            LakeMaracaiboGrid = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=10.16666666666667 +lat_0=10.16666666666667 +lon_0=-71.60561777777777 +k_0=1 +x_0=200000 +y_0=147315.028 +ellps=intl +units=m +no_defs ").orElse(null);
            LakeMaracaiboGridM1 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=10.16666666666667 +lat_0=10.16666666666667 +lon_0=-71.60561777777777 +k_0=1 +x_0=0 +y_0=-52684.972 +ellps=intl +units=m +no_defs ").orElse(null);
            LakeMaracaiboGridM3 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=10.16666666666667 +lat_0=10.16666666666667 +lon_0=-71.60561777777777 +k_0=1 +x_0=500000 +y_0=447315.028 +ellps=intl +units=m +no_defs ").orElse(null);
            LakeMaracaiboLaRosaGrid = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=10.16666666666667 +lat_0=10.16666666666667 +lon_0=-71.60561777777777 +k_0=1 +x_0=-17044 +y_0=-23139.97 +ellps=intl +units=m +no_defs ").orElse(null);
            LietuvosKoordinaciuSistema = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=0.999800 +x_0=500000 +y_0=0 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            LisboaBesselBonne = ProjectionInfo.fromProj4String("+proj=bonne +lon_0=-8.131906111111112 +lat_1=39.66666666666666 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            LisboaHayfordGaussIGeoE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=39.66666666666666 +lon_0=-8.131906111111112 +k=1.000000 +x_0=200000 +y_0=300000 +ellps=intl +units=m +no_defs ").orElse(null);
            LisboaHayfordGaussIPCC = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=39.66666666666666 +lon_0=-8.131906111111112 +k=1.000000 +x_0=0 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            Locodjo1965TM5NW = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-5 +k=0.999600 +x_0=500000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            Luxembourg1930Gauss = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=49.83333333333334 +lon_0=6.166666666666667 +k=1.000000 +x_0=80000 +y_0=100000 +ellps=intl +units=m +no_defs ").orElse(null);
            Madrid1870MadridSpain = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40 +lat_0=40 +lon_0=3.687938888888889 +k_0=0.9988085293 +x_0=600000 +y_0=600000 +a=6378298.3 +b=6356657.142669561 +pm=-3.687938888888889 +units=m +no_defs ").orElse(null);
            MakassarNEIEZ = ProjectionInfo.fromProj4String("+proj=merc +lat_ts=4.45405154589751 +lon_0=110 +k=1.000000 +x_0=3900000 +y_0=900000 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGI3DegreeGaussZone5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=1.000000 +x_0=5500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGI3DegreeGaussZone6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=18 +k=1.000000 +x_0=6500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGI3DegreeGaussZone7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=21 +k=1.000000 +x_0=7500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGI3DegreeGaussZone8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=1.000000 +x_0=8500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGIAustriaLambert = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46 +lat_2=49 +lat_0=47.5 +lon_0=13.33333333333333 +x_0=400000 +y_0=400000 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGIBalkans5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=0.999900 +x_0=5500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGIBalkans6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=18 +k=0.999900 +x_0=6500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGIBalkans7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=0.999900 +x_0=8500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGIBalkans8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=0.999900 +x_0=8500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGIM28 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=10.33333333333333 +k=1.000000 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGIM31 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=13.33333333333333 +k=1.000000 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGIM34 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=16.33333333333334 +k=1.000000 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MGISloveniaGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=0.999900 +x_0=500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            MonteMarioItaly1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=9 +k=0.999600 +x_0=1500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            MonteMarioItaly2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=0.999600 +x_0=2520000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            MonteMarioRomeItaly1 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-15.90466666333333 +k=0.999600 +x_0=1500000 +y_0=0 +ellps=intl +pm=12.45233333333333 +units=m +no_defs ").orElse(null);
            MonteMarioRomeItaly2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-9.90466666333333 +k=0.999600 +x_0=2520000 +y_0=0 +ellps=intl +pm=12.45233333333333 +units=m +no_defs ").orElse(null);
            Montserrat1958BritishWestIndiesGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-62 +k=0.999500 +x_0=400000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            MountDillonTobagoGrid = ProjectionInfo.fromProj4String("+proj=cass +lat_0=11.25217861111111 +lon_0=-59.68600888888889 +x_0=37718.66154375 +y_0=36209.915082 +a=6378293.639 +b=6356617.98149216 +to_meter=0.2011661949 +no_defs ").orElse(null);
            NAD1927CubaNorte = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=22.35 +lat_0=22.35 +lon_0=-81 +k_0=0.99993602 +x_0=500000 +y_0=280296.016 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927CubaSur = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=20.71666666666667 +lat_0=20.71666666666667 +lon_0=-76.83333333333333 +k_0=0.99994848 +x_0=500000 +y_0=229126.939 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927GuatemalaNorte = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=16.81666666666667 +lat_0=16.81666666666667 +lon_0=-90.33333333333333 +k_0=0.99992226 +x_0=500000 +y_0=292209.579 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927GuatemalaSur = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=14.9 +lat_0=14.9 +lon_0=-90.33333333333333 +k_0=0.99989906 +x_0=500000 +y_0=325992.681 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927MichiganGeoRefMeters = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=45.30916666666666 +lonc=-86 +alpha=337.255555555556 +k=0.9996 +x_0=2546731.496 +y_0=-4354009.816 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927MichiganGeoRefUSfeet = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=45.30916666666666 +lonc=-86 +alpha=337.255555555556 +k=0.9996 +x_0=2546731.495961392 +y_0=-4354009.816002033 +ellps=clrk66 +datum=NAD27 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNGuamMapGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=13.5 +lon_0=144.75 +k=1.000000 +x_0=100000 +y_0=200000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            NAD1983MichiganGeoReferencedMeters = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=45.30916666666666 +lonc=-86 +alpha=337.25556 +k=0.9996 +x_0=2546731.496 +y_0=-4354009.816 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
            NAD1983MichiganGeoRefMeters = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=45.30916666666666 +lonc=-86 +alpha=337.255555555556 +k=0.9996 +x_0=2546731.496 +y_0=-4354009.816 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
            NAD1983MichiganGeoRefUSfeet = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=45.30916666666666 +lonc=-86 +alpha=337.255555555556 +k=0.9996 +x_0=2546731.495961392 +y_0=-4354009.816002033 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NewZealandMapGrid = ProjectionInfo.fromProj4String("+proj=nzmg +lat_0=-41 +lon_0=173 +x_0=2510000 +y_0=6023150 +ellps=intl +units=m +no_defs ").orElse(null);
            NewZealandNorthIsland = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-39 +lon_0=175.5 +k=1.000000 +x_0=274319.5243848086 +y_0=365759.3658464114 +ellps=intl +to_meter=0.9143984146160287 +no_defs ").orElse(null);
            NewZealandSouthIsland = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-44 +lon_0=171.5 +k=1.000000 +x_0=457199.2073080143 +y_0=457199.2073080143 +ellps=intl +to_meter=0.9143984146160287 +no_defs ").orElse(null);
            NigeriaEastBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=4 +lon_0=12.5 +k=0.999750 +x_0=1110369.7 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            NigeriaMidBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=4 +lon_0=8.5 +k=0.999750 +x_0=670553.98 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            NigeriaWestBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=4 +lon_0=4.5 +k=0.999750 +x_0=230738.26 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            NordAlgerie = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36 +lat_0=36 +lon_0=2.7 +k_0=0.999625544 +x_0=500135 +y_0=300090 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            NordAlgerieancienne = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37 +lat_0=37 +lon_0=3 +k_0=0.999625769 +x_0=500000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            NordAlgerieAnciennedegrees = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36 +lat_0=36 +lon_0=2.7 +k_0=0.999625544 +x_0=500000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            NordAlgeriedegrees = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36 +lat_0=36 +lon_0=2.7 +k_0=0.999625544 +x_0=500135 +y_0=300090 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            NorddeGuerre = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=49.49999999999999 +lat_0=49.49999999999999 +lon_0=3.062770833333333 +k_0=0.9995090800000001 +x_0=500000 +y_0=300000 +a=6376523 +b=6355862.933255573 +pm=2.337229166666667 +units=m +no_defs ").orElse(null);
            NordFrance = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=49.49999999999999 +lat_0=49.49999999999999 +lon_0=-2.337229166666667 +k_0=0.999877341 +x_0=600000 +y_0=200000 +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667 +units=m +no_defs ").orElse(null);
            NordMaroc = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37 +lat_0=37 +lon_0=-6 +k_0=0.999625769 +x_0=500000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            NordMarocdegrees = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.3 +lat_0=33.3 +lon_0=-5.4 +k_0=0.999625769 +x_0=500000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            NordTunisie = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=36 +lat_0=36 +lon_0=9.899999999999999 +k_0=0.999625544 +x_0=500000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            NTFFranceIdegrees = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=49.5 +lat_0=49.5 +lon_0=2.337229166666667 +k_0=0.999877341 +x_0=600000 +y_0=1200000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            NTFFranceIIdegrees = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.8 +lat_0=46.8 +lon_0=2.337229166666667 +k_0=0.99987742 +x_0=600000 +y_0=2200000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            NTFFranceIIIdegrees = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.1 +lat_0=44.1 +lon_0=2.337229166666667 +k_0=0.999877499 +x_0=600000 +y_0=3200000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            NTFFranceIVdegrees = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.165 +lat_0=42.165 +lon_0=2.337229166666667 +k_0=0.99994471 +x_0=234.358 +y_0=185861.369 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            ObservatorioMeteorologico1965MacauGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=22.21239722222222 +lon_0=113.5364694444444 +k=1.000000 +x_0=20000 +y_0=20000 +ellps=intl +units=m +no_defs ").orElse(null);
            OSNI1952IrishNationalGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=53.5 +lon_0=-8 +k=1.000035 +x_0=200000 +y_0=250000 +ellps=airy +units=m +no_defs ").orElse(null);
            Palestine1923IsraelCSGrid = ProjectionInfo.fromProj4String("+proj=cass +lat_0=31.73409694444445 +lon_0=35.21208055555556 +x_0=170251.555 +y_0=1126867.909 +a=6378300.79 +b=6356566.430000036 +units=m +no_defs ").orElse(null);
            Palestine1923PalestineBelt = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31.73409694444445 +lon_0=35.21208055555556 +k=1.000000 +x_0=170251.555 +y_0=1126867.909 +a=6378300.79 +b=6356566.430000036 +units=m +no_defs ").orElse(null);
            Palestine1923PalestineGrid = ProjectionInfo.fromProj4String("+proj=cass +lat_0=31.73409694444445 +lon_0=35.21208055555556 +x_0=170251.555 +y_0=126867.909 +a=6378300.79 +b=6356566.430000036 +units=m +no_defs ").orElse(null);
            PampadelCastilloArgentina2 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-90 +lon_0=-69 +k=1.000000 +x_0=2500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            PeruCentralZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-9.5 +lon_0=-76 +k=0.999330 +x_0=720000 +y_0=1039979.159 +ellps=intl +units=m +no_defs ").orElse(null);
            PeruEastZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-9.5 +lon_0=-70.5 +k=0.999530 +x_0=1324000 +y_0=1040084.558 +ellps=intl +units=m +no_defs ").orElse(null);
            PeruWestZone = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-6 +lon_0=-80.5 +k=0.999830 +x_0=222000 +y_0=1426834.743 +ellps=intl +units=m +no_defs ").orElse(null);
            PhilippinesZoneI = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=117 +k=0.999950 +x_0=500000 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
            PhilippinesZoneII = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=119 +k=0.999950 +x_0=500000 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
            PhilippinesZoneIII = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=121 +k=0.999950 +x_0=500000 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
            PhilippinesZoneIV = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=123 +k=0.999950 +x_0=500000 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
            PhilippinesZoneV = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=125 +k=0.999950 +x_0=500000 +y_0=0 +ellps=clrk66 +units=m +no_defs ").orElse(null);
            PitondesNeigesTMReunion = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-21.11666666666667 +lon_0=55.53333333333333 +k=1.000000 +x_0=50000 +y_0=160000 +ellps=intl +units=m +no_defs ").orElse(null);
            PortugueseNationalGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=39.66666666666666 +lon_0=10.13190611111111 +k=1.000000 +x_0=200000 +y_0=300000 +ellps=intl +pm=-9.131906111111112 +units=m +no_defs ").orElse(null);
            PSAD1956ICNRegional = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=3 +lat_2=9 +lat_0=6 +lon_0=-66 +x_0=1000000 +y_0=1000000 +ellps=intl +units=m +no_defs ").orElse(null);
            Qatar1948QatarGrid = ProjectionInfo.fromProj4String("+proj=cass +lat_0=25.38236111111111 +lon_0=50.76138888888889 +x_0=100000 +y_0=100000 +ellps=helmert +units=m +no_defs ").orElse(null);
            QatarNationalGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=24.45 +lon_0=51.21666666666667 +k=0.999990 +x_0=200000 +y_0=300000 +ellps=intl +units=m +no_defs ").orElse(null);
            RassadiranNakhleTaqi = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=27.56882880555555 +lonc=52.60353916666667 +alpha=0.5716611944444444 +k=0.999895934 +x_0=658377.437 +y_0=3044969.194 +ellps=intl +units=m +no_defs ").orElse(null);
            RGF1993Lambert93 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44 +lat_2=49 +lat_0=46.5 +lon_0=3 +x_0=700000 +y_0=6600000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            RGNC1991LambertNewCaledonia = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=-20.66666666666667 +lat_2=-22.33333333333333 +lat_0=-21.5 +lon_0=166 +x_0=400000 +y_0=300000 +ellps=intl +units=m +no_defs ").orElse(null);
            //Rijksdriehoekstelsel = ProjectionInfo.fromProj4String("+ellps=bessel +units=m +no_defs ").orElse(null);
            Roma1940GaussBoagaEst = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=0.999600 +x_0=2520000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            Roma1940GaussBoagaOvest = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=9 +k=0.999600 +x_0=1500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            RT9025gonWest = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15.80827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            SAD1969BrazilPolyconic = ProjectionInfo.fromProj4String("+proj=poly +lat_0=0 +lon_0=-54 +x_0=5000000 +y_0=10000000 +ellps=aust_SA +units=m +no_defs ").orElse(null);
            Sahara = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=29 +lat_0=29 +lon_0=-6 +k_0=0.9996 +x_0=1200000 +y_0=400000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            Saharadegrees = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=26.1 +lat_0=26.1 +lon_0=-5.4 +k_0=0.9996 +x_0=1200000 +y_0=400000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            SierraLeone1924NewColonyGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=6.666666666666667 +lon_0=-12 +k=1.000000 +x_0=152399.8550907544 +y_0=0 +a=6378300 +b=6356751.689189189 +to_meter=0.3047997101815088 +no_defs ").orElse(null);
            SierraLeone1924NewWarOfficeGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=6.666666666666667 +lon_0=-12 +k=1.000000 +x_0=243839.7681452071 +y_0=182879.8261089053 +a=6378300 +b=6356751.689189189 +to_meter=0.3047997101815088 +no_defs ").orElse(null);
            SJTSKFerroKrovak = ProjectionInfo.fromProj4String("+proj=krovak +lat_0=49.5 +lon_0=60.16666666666667 +alpha=30.28813975277778 +k=0.9999 +x_0=0 +y_0=0 +ellps=bessel +pm=-17.66666666666667 +units=m +no_defs ").orElse(null);
            SJTSKFerroKrovakEastNorth = ProjectionInfo.fromProj4String("+proj=krovak +lat_0=49.5 +lon_0=60.16666666666667 +alpha=30.28813975277778 +k=0.9999 +x_0=0 +y_0=0 +ellps=bessel +pm=-17.66666666666667 +units=m +no_defs ").orElse(null);
            SJTSKKrovak = ProjectionInfo.fromProj4String("+proj=krovak +lat_0=49.5 +lon_0=24.83333333333333 +alpha=30.28813975277778 +k=0.9999 +x_0=0 +y_0=0 +ellps=bessel +units=m +no_defs ").orElse(null);
            SJTSKKrovakEastNorth = ProjectionInfo.fromProj4String("+proj=krovak +lat_0=49.5 +lon_0=24.83333333333333 +alpha=30.28813975277778 +k=0.9999 +x_0=0 +y_0=0 +ellps=bessel +units=m +towgs84=570.8,85.7,462.8,4.998,1.587,5.261,3.56 +no_defs ").orElse(null);
            //Stereo1933 = ProjectionInfo.fromProj4String("+ellps=intl +units=m +no_defs ").orElse(null);
            //Stereo1970 = ProjectionInfo.fromProj4String("+ellps=krass +units=m +no_defs ").orElse(null);
            StKitts1955BritishWestIndiesGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-62 +k=0.999500 +x_0=400000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            StLucia1955BritishWestIndiesGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-62 +k=0.999500 +x_0=400000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            StVincent1945BritishWestIndiesGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-62 +k=0.999500 +x_0=400000 +y_0=0 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            SudAlgerie = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.3 +lat_0=33.3 +lon_0=2.7 +k_0=0.999625769 +x_0=500135 +y_0=300090 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            SudAlgerieAncienneDegree = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.3 +lat_0=33.3 +lon_0=2.7 +k_0=0.999625769 +x_0=500000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            SudAlgeriedegrees = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.3 +lat_0=33.3 +lon_0=2.7 +k_0=0.999625769 +x_0=500135 +y_0=300090 +ellps=clrk80 +units=m +no_defs ").orElse(null);
            SudFrance = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.09999999999999 +lat_0=44.09999999999999 +lon_0=-2.337229166666667 +k_0=0.999877499 +x_0=600000 +y_0=200000 +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667 +units=m +no_defs ").orElse(null);
            SudMaroc = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33 +lat_0=33 +lon_0=-6 +k_0=0.9996155960000001 +x_0=500000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            SudMarocdegrees = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=29.7 +lat_0=29.7 +lon_0=-5.4 +k_0=0.9996155960000001 +x_0=500000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            SudTunisie = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=33.3 +lat_0=33.3 +lon_0=9.899999999999999 +k_0=0.999625769 +x_0=500000 +y_0=300000 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
            SwedishNationalGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-20.30827777777778 +k=1.000000 +x_0=1500000 +y_0=0 +ellps=bessel +pm=18.05827777777778 +units=m +no_defs ").orElse(null);
            Timbalai1948RSOBorneoChains = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=4 +lonc=115 +alpha=53.31582047222222 +k=0.99984  +x_0=590476.8714630401 +y_0=442857.653094361 +ellps=evrstSS +to_meter=20.11676512155263 +no_defs ").orElse(null);
            Timbalai1948RSOBorneoFeet = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=4 +lonc=115 +alpha=53.31582047222222 +k=0.99984 +x_0=590476.8727431979 +y_0=442857.6545573985 +ellps=evrstSS +to_meter=0.3047994715386762 +no_defs ").orElse(null);
            Timbalai1948RSOBorneoMeters = ProjectionInfo.fromProj4String("+proj=omerc +lat_0=4 +lonc=115 +alpha=53.31582047222222 +k=0.99984 +x_0=590476.87 +y_0=442857.65 +ellps=evrstSS +units=m +no_defs ").orElse(null);
            TM75IrishGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=53.5 +lon_0=-8 +k=1.000035 +x_0=200000 +y_0=250000 +a=6377340.189 +b=6356034.447938534 +units=m +no_defs ").orElse(null);
            Trinidad1903TrinidadGrid = ProjectionInfo.fromProj4String("+proj=cass +lat_0=10.44166666666667 +lon_0=-61.33333333333334 +x_0=86501.46380700001 +y_0=65379.0133425 +a=6378293.639 +b=6356617.98149216 +to_meter=0.2011661949 +no_defs ").orElse(null);
            Trinidad1903TrinidadGridFeetClarke = ProjectionInfo.fromProj4String("+proj=cass +lat_0=10.44166666666667 +lon_0=-61.33333333333334 +x_0=86501.46380699999 +y_0=65379.0133425 +a=6378293.639 +b=6356617.98149216 +to_meter=0.304797265 +no_defs ").orElse(null);
            UWPP1992 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=19 +k=0.999300 +x_0=500000 +y_0=-5300000 +ellps=WGS84 +units=m +no_defs ").orElse(null);
            UWPP2000pas5 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=15 +k=0.999923 +x_0=5500000 +y_0=0 +ellps=WGS84 +units=m +no_defs ").orElse(null);
            UWPP2000pas6 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=18 +k=0.999923 +x_0=6500000 +y_0=0 +ellps=WGS84 +units=m +no_defs ").orElse(null);
            UWPP2000pas7 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=21 +k=0.999923 +x_0=7500000 +y_0=0 +ellps=WGS84 +units=m +no_defs ").orElse(null);
            UWPP2000pas8 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=0.999923 +x_0=8500000 +y_0=0 +ellps=WGS84 +units=m +no_defs ").orElse(null);
            WGS1972TM106NE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=106 +k=0.999600 +x_0=500000 +y_0=0 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1984TM116SE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=116 +k=0.999600 +x_0=500000 +y_0=10000000 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984TM132SE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=132 +k=0.999600 +x_0=500000 +y_0=10000000 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984TM36SE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=36 +k=0.999600 +x_0=500000 +y_0=10000000 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984TM6NE = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=6 +k=0.999600 +x_0=500000 +y_0=10000000 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            ZanderijSurinameOldTM = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-55.68333333333333 +k=0.999600 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ZanderijSurinameTM = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-55.68333333333333 +k=0.999900 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);
            ZanderijTM54NW = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-54 +k=0.999600 +x_0=500000 +y_0=0 +ellps=intl +units=m +no_defs ").orElse(null);

            Abidjan1987TM5NW.setName("Abidjan_1987_TM_5_NW");
            AccraGhanaGrid.setName("Accra_Ghana_Grid");
            AccraTM1NW.setName("Accra_TM_1_NW");
            AinelAbdAramcoLambert.setName("Ain_el_Abd_Aramco_Lambert");
            AmericanSamoa1962SamoaLambert.setName("Samoa_1962_Samoa_Lambert");
            Anguilla1957BritishWestIndiesGrid.setName("Anguilla_1957_British_West_Indies_Grid");
            Antigua1943BritishWestIndiesGrid.setName("Antigua_1943_British_West_Indies_Grid");
            AustriaFerroCentralZone.setName("Austria_Central_Zone");
            AustriaFerroEastZone.setName("Austria_East_Zone");
            AustriaFerroWestZone.setName("Austria_West_Zone");
            BahrainStateGrid.setName("Bahrain_State_Grid");
            Barbados1938BarbadosGrid.setName("Barbados_1938_Barbados_Grid");
            Barbados1938BritishWestIndiesGrid.setName("Barbados_1938_British_West_Indies_Grid");
            BataviaNEIEZ.setName("Batavia_NEIEZ");
            BataviaTM109SE.setName("Batavia_TM_109_SE");
            BelgeLambert1950.setName("Belge_Lambert_1950");
            BelgeLambert1972.setName("Belge_Lambert_1972");
            Bermuda2000NationalGrid.setName("Bermuda_2000_National_Grid");
            Bern1898BernLV03C.setName("Bern_1898_Bern_LV03C");
            BritishNationalGridOSGB36.setName("British_National_Grid");
            CamacupaTM1130SE.setName("Camacupa_TM_11_30_SE");
            CamacupaTM12SE.setName("Camacupa_TM_12_SE");
            CarthageTM11NE.setName("Carthage_TM_11_NE");
            CentreFrance.setName("Centre_France");
            CH1903LV03.setName("CH1903_LV03");
            CH1903LV95.setName("CH1903+_LV95");
            ColombiaBogotaZone.setName("Colombia_Bogota_Zone");
            ColombiaEastZone.setName("Colombia_East_Zone");
            ColombiaECentralZone.setName("Colombia_East_Central_Zone");
            ColombiaWestZone.setName("Colombia_West_Zone");
            Corse.setName("Corse");
            Datum73HayfordGaussIGeoE.setName("Datum_73_Hayford_Gauss_IGeoE");
            Datum73HayfordGaussIPCC.setName("Datum_73_Hayford_Gauss_IPCC");
            DeirezZorLevantStereographic.setName("Deir_ez_Zor_Levant_Stereographic");
            DeirezZorLevantZone.setName("Deir_ez_Zor_Levant_Zone");
            DeirezZorSyriaLambert.setName("Deir_ez_Zor_Syria_Lambert");
            DHDN3DegreeGaussZone1.setName("DHDN_3_Degree_Gauss_Zone_1");
            DHDN3DegreeGaussZone2.setName("DHDN_3_Degree_Gauss_Zone_2");
            DHDN3DegreeGaussZone3.setName("DHDN_3_Degree_Gauss_Zone_3");
            DHDN3DegreeGaussZone4.setName("DHDN_3_Degree_Gauss_Zone_4");
            DHDN3DegreeGaussZone5.setName("DHDN_3_Degree_Gauss_Zone_5");
            Dominica1945BritishWestIndiesGrid.setName("Dominica_1945_British_West_Indies_Grid");
            Douala1948AOFWest.setName("Douala_1948_AOF_West");
            ED1950FranceEuroLambert.setName("ED_1950_France_EuroLambert");
            ED1950TM0N.setName("ED_1950_TM_0_N");
            ED1950TM27.setName("ED_1950_TM27");
            ED1950TM30.setName("ED_1950_TM30");
            ED1950TM33.setName("ED_1950_TM33");
            ED1950TM36.setName("ED_1950_TM36");
            ED1950TM39.setName("ED_1950_TM39");
            ED1950TM42.setName("ED_1950_TM42");
            ED1950TM45.setName("ED_1950_TM45");
            ED1950TM5NE.setName("ED_1950_TM_5_NE");
            ED1950Turkey10.setName("ED_1950_Turkey_10");
            ED1950Turkey11.setName("ED_1950_Turkey_11");
            ED1950Turkey12.setName("ED_1950_Turkey_12");
            ED1950Turkey13.setName("ED_1950_Turkey_13");
            ED1950Turkey14.setName("ED_1950_Turkey_14");
            ED1950Turkey15.setName("ED_1950_Turkey_15");
            ED1950Turkey9.setName("ED_1950_Turkey_9");
            EgyptBlueBelt.setName("Egypt_Blue_Belt");
            EgyptExtendedPurpleBelt.setName("Egypt_Extended_Purple_Belt");
            EgyptPurpleBelt.setName("Egypt_Purple_Belt");
            EgyptRedBelt.setName("Egypt_Red_Belt");
            ELD1979Libya10.setName("ELD_1979_Libya_10");
            ELD1979Libya11.setName("ELD_1979_Libya_11");
            ELD1979Libya12.setName("ELD_1979_Libya_12");
            ELD1979Libya13.setName("ELD_1979_Libya_13");
            ELD1979Libya5.setName("ELD_1979_Libya_5");
            ELD1979Libya6.setName("ELD_1979_Libya_6");
            ELD1979Libya7.setName("ELD_1979_Libya_7");
            ELD1979Libya8.setName("ELD_1979_Libya_8");
            ELD1979Libya9.setName("ELD_1979_Libya_9");
            ELD1979TM12NE.setName("ELD_1979_TM_12_NE");
            Estonia1997EstoniaNationalGrid.setName("Estonia_1997_Estonia_National_Grid");
            EstonianCoordinateSystemof1992.setName("Estonian_Coordinate_System_of_1992");
            ETRF1989TMBaltic1993.setName("ETRF_1989_TM_Baltic_1993");
            ETRS1989Kp2000Bornholm.setName("ETRS_1989_Kp2000_Bornholm");
            ETRS1989Kp2000Jutland.setName("ETRS_1989_Kp2000_Jutland");
            ETRS1989Kp2000Zealand.setName("ETRS_1989_Kp2000_Zealand");
            ETRS1989PolandCS2000Zone5.setName("ETRS_1989_Poland_CS2000_Zone_5");
            ETRS1989PolandCS2000Zone6.setName("ETRS_1989_Poland_CS2000_Zone_6");
            ETRS1989PolandCS2000Zone7.setName("ETRS_1989_Poland_CS2000_Zone_7");
            ETRS1989PolandCS2000Zone8.setName("ETRS_1989_Poland_CS2000_Zone_8");
            ETRS1989PolandCS92.setName("ETRS_1989_Poland_CS92");
            ETRS1989TM30NE.setName("ETRS_1989_TM_30_NE");
            ETRS1989TMBaltic1993.setName("ETRS_1989_TM_Baltic_1993");
            ETRS1989UWPP1992.setName("ETRS_1989_UWPP_1992");
            ETRS1989UWPP2000PAS5.setName("ETRS_1989_UWPP_2000_PAS_5");
            ETRS1989UWPP2000PAS6.setName("ETRS_1989_UWPP_2000_PAS_6");
            ETRS1989UWPP2000PAS7.setName("ETRS_1989_UWPP_2000_PAS_7");
            ETRS1989UWPP2000PAS8.setName("ETRS_1989_UWPP_2000_PAS_8");
            EUREFFINTM35FIN.setName("EUREF_FIN_TM35FIN");
            //EverestModified1969RSOMalayaMeters.setName("Everest_Modified_1969_RSO_Malaya_Meters");
            FD1958Iraq.setName("FD_1958_Iraq");
            FinlandZone1.setName("Finland_Zone_1");
            FinlandZone2.setName("Finland_Zone_2");
            FinlandZone3.setName("Finland_Zone_3");
            FinlandZone4.setName("Finland_Zone_4");
            FranceI.setName("France_I");
            FranceII.setName("France_II");
            FranceIII.setName("France_III");
            FranceIV.setName("France_IV");
            GermanyZone1.setName("Germany_Zone_1");
            GermanyZone2.setName("Germany_Zone_2");
            GermanyZone3.setName("Germany_Zone_3");
            GermanyZone4.setName("Germany_Zone_4");
            GermanyZone5.setName("Germany_Zone_5");
            GhanaMetreGrid.setName("Ghana_Metre_Grid");
            GreekGrid.setName("Greek_Grid");
            Grenada1953BritishWestIndiesGrid.setName("Grenada_1953_British_West_Indies_Grid");
            GuernseyGrid.setName("Guernsey_Grid");
            GunungSegaraNEIEZ.setName("Gunung_Segara_NEIEZ");
            Hanoi1972GK106NE.setName("Hanoi_1972_GK_106_NE");
            HD1972EgysegesOrszagosVetuleti.setName("HD_1972_Egyseges_Orszagos_Vetuleti");
            Helle1954JanMayenGrid.setName("Helle_1954_Jan_Mayen_Grid");
            HitoXVIII1963Argentina2.setName("Hito_XVIII_1963_Argentina_2");
            HongKong1980Grid.setName("Hong_Kong_1980_Grid");
            Indian1960TM106NE.setName("Indian_1960_TM_106NE");
            IRENET95IrishTranverseMercator.setName("IRENET95_IRISH_Transverse_Mercator");
            IrishNationalGrid.setName("Irish_National_Grid");
            ISN1993Lambert1993.setName("ISN_1993_Lambert_1993");
            IsraelTMGrid.setName("Israel_TM_Grid");
            Jamaica1875OldGrid.setName("Jamaica_1875_Old_Grid");
            JamaicaGrid.setName("Jamaica_Grid");
            JordanJTM.setName("Jordan_JTM");
            KandawalaCeylonBeltIndianYards1937.setName("Kandawala_Ceylon_Belt_Indian_Yards_1937");
            KandawalaCeylonBeltMeters.setName("Kandawala_Ceylon_Belt_Meters");
            KOCLambert.setName("KOC_Lambert");
            Korean1985KoreaCentralBelt.setName("Korean_1985_Korea_Central_Belt");
            Korean1985KoreaEastBelt.setName("Korean_1985_Korea_East_Belt");
            Korean1985KoreaWestBelt.setName("Korean_1985_Korea_West_Belt");
            KUDAMSKTM.setName("KUDAMS_KTM");
            KuwaitOilCoLambert.setName("KOC_Lambert");
            KuwaitUtilityKTM.setName("KUDAMS_KTM");
            LakeMaracaiboGridM1.setName("Lake_Maracaibo_Grid_M1");
            LakeMaracaiboGridM3.setName("Lake_Maracaibo_Grid_M3");
            LakeMaracaiboGrid.setName("Lake_Maracaibo_Grid");
            LakeMaracaiboLaRosaGrid.setName("Lake_Maracaibo_La_Rosa_Grid");
            LietuvosKoordinaciuSistema.setName("Lietuvos_Koordinaciu_Sistema");
            LisboaBesselBonne.setName("Lisboa_Bessel_Bonne");
            LisboaHayfordGaussIGeoE.setName("Lisboa_Hayford_Gauss_IGeoE");
            LisboaHayfordGaussIPCC.setName("Lisboa_Hayford_Gauss_IPCC");
            Locodjo1965TM5NW.setName("Locodjo_1965_TM_5_NW");
            Luxembourg1930Gauss.setName("Luxembourg_1930_Gauss");
            Madrid1870MadridSpain.setName("Madrid_1870_Madrid_Spain");
            MakassarNEIEZ.setName("Makassar_NEIEZ");
            MGI3DegreeGaussZone5.setName("MGI_3_Degree_Gauss_Zone_5");
            MGI3DegreeGaussZone6.setName("MGI_3_Degree_Gauss_Zone_6");
            MGI3DegreeGaussZone7.setName("MGI_3_Degree_Gauss_Zone_7");
            MGI3DegreeGaussZone8.setName("MGI_3_Degree_Gauss_Zone_8");
            MGIAustriaLambert.setName("MGI_Austria_Lambert");
            MGIBalkans5.setName("MGI_Balkans_5");
            MGIBalkans6.setName("MGI_Balkans_6");
            MGIBalkans7.setName("MGI_Balkans_7");
            MGIBalkans8.setName("MGI_Balkans_8");
            MGIM28.setName("MGI_M28");
            MGIM31.setName("MGI_M31");
            MGIM34.setName("MGI_M34");
            MGISloveniaGrid.setName("MGI_Slovenia_Grid");
            MonteMarioItaly1.setName("Monte_Mario_Italy_1");
            MonteMarioItaly2.setName("Monte_Mario_Italy_2");
            MonteMarioRomeItaly1.setName("Monte_Mario_Rome_Italy_1");
            MonteMarioRomeItaly2.setName("Monte_Mario_Rome_Italy_2");
            Montserrat1958BritishWestIndiesGrid.setName("Montserrat_1958_British_West_Indies_Grid");
            MountDillonTobagoGrid.setName("Mount_Dillon_Tobago_Grid");
            NAD1927CubaNorte.setName("NAD_1927_Cuba_Norte");
            NAD1927CubaSur.setName("NAD_1927_Cuba_Sur");
            NAD1927GuatemalaNorte.setName("NAD_1927_Guatemala_Norte");
            NAD1927GuatemalaSur.setName("NAD_1927_Guatemala_Sur");
            NAD1983HARNGuamMapGrid.setName("NAD_1983_HARN_Guam_Map_Grid");
            NewZealandMapGrid.setName("GD_1949_New_Zealand_Map_Grid");
            NewZealandNorthIsland.setName("New_Zealand_North_Island");
            NewZealandSouthIsland.setName("New_Zealand_South_Island");
            NigeriaEastBelt.setName("Nigeria_East_Belt");
            NigeriaMidBelt.setName("Nigeria_Mid_Belt");
            NigeriaWestBelt.setName("Nigeria_West_Belt");
            NordAlgerie.setName("Nord_Algerie_Degree");
            NordAlgerieancienne.setName("Nord_Algerie_Ancienne");
            NordAlgerieAnciennedegrees.setName("Nord_Algerie_Ancienne_Degree");
            NordAlgeriedegrees.setName("Nord_Algerie");
            NorddeGuerre.setName("Nord_de_Guerre");
            NordFrance.setName("Nord_France");
            NordMaroc.setName("Nord_Maroc");
            NordMarocdegrees.setName("Nord_Maroc_Degree");
            NordTunisie.setName("Nord_Tunisie");
            NTFFranceIdegrees.setName("NTF_France_I_degrees");
            NTFFranceIIdegrees.setName("NTF_France_II_degrees");
            NTFFranceIIIdegrees.setName("NTF_France_III_degrees");
            NTFFranceIVdegrees.setName("NTF_France_IV_degrees");
            ObservatorioMeteorologico1965MacauGrid.setName("Observatorio_Meteorologico_1965_Macau_Grid");
            OSNI1952IrishNationalGrid.setName("OSNI_1952_Irish_National_Grid");
            Palestine1923IsraelCSGrid.setName("Palestine_1923_Israel_CS_Grid");
            Palestine1923PalestineBelt.setName("Palestine_1923_Palestine_Belt");
            Palestine1923PalestineGrid.setName("Palestine_1923_Palestine_Grid");
            PampadelCastilloArgentina2.setName("Pampa_del_Castillo_Argentina_2");
            PeruCentralZone.setName("Peru_Central_Zone");
            PeruEastZone.setName("Peru_East_Zone");
            PeruWestZone.setName("Peru_West_Zone");
            PhilippinesZoneI.setName("Philippines_Zone_I");
            PhilippinesZoneII.setName("Philippines_Zone_II");
            PhilippinesZoneIII.setName("Philippines_Zone_III");
            PhilippinesZoneIV.setName("Philippines_Zone_IV");
            PhilippinesZoneV.setName("Philippines_Zone_V");
            PitondesNeigesTMReunion.setName("Piton_des_Neiges_TM_Reunion");
            PortugueseNationalGrid.setName("Portuguese_National_Grid");
            PSAD1956ICNRegional.setName("PSAD_1956_ICN_Regional");
            Qatar1948QatarGrid.setName("Qatar_1948_Qatar_Grid");
            QatarNationalGrid.setName("Qatar_National_Grid");
            RassadiranNakhleTaqi.setName("Rassadiran_Nakhl_e_Taqi");
            RGF1993Lambert93.setName("RGF_1993_Lambert_93");
            RGNC1991LambertNewCaledonia.setName("RGNC_1991_Lambert_New_Caledonia");
            //Rijksdriehoekstelsel.setName("RD_New");
            Roma1940GaussBoagaEst.setName("Roma_1940_Gauss_Boaga_Est");
            Roma1940GaussBoagaOvest.setName("Roma_1940_Gauss_Boaga_Ovest");
            RT9025gonWest.setName("RT90_25_gon_W");
            SAD1969BrazilPolyconic.setName("SAD_1969_Brazil_Polyconic");
            Sahara.setName("Sahara");
            Saharadegrees.setName("Sahara_Degree");
            SierraLeone1924NewColonyGrid.setName("Sierra_Leone_1924_New_Colony_Grid");
            SierraLeone1924NewWarOfficeGrid.setName("Sierra_Leone_1924_New_War_Office_Grid");
            SJTSKFerroKrovak.setName("S-JTSK_Ferro_Krovak");
            SJTSKFerroKrovakEastNorth.setName("S-JTSK_Ferro_Krovak_East_North");
            SJTSKKrovak.setName("S-JTSK_Krovak");
            SJTSKKrovakEastNorth.setName("S-JTSK_Krovak_East_North");
            //Stereo1933.setName("Stereo_33");
            //Stereo1970.setName("Stereo_70");
            StKitts1955BritishWestIndiesGrid.setName("St_Kitts_1955_British_West_Indies_Grid");
            StLucia1955BritishWestIndiesGrid.setName("St_Lucia_1955_British_West_Indies_Grid");
            StVincent1945BritishWestIndiesGrid.setName("St_Vincent_1945_British_West_Indies_Grid");
            SudAlgerie.setName("Sud_Algerie");
            SudAlgerieAncienneDegree.setName("Sud_Algerie_Ancienne_Degree");
            SudAlgeriedegrees.setName("Sud_Algerie_Degree");
            SudFrance.setName("Sud_France");
            SudMaroc.setName("Sud_Maroc");
            SudMarocdegrees.setName("Sud_Maroc_Degree");
            SudTunisie.setName("Sud_Tunisie");
            SwedishNationalGrid.setName("Swedish_National_Grid");
            TM75IrishGrid.setName("TM75_Irish_Grid");
            Trinidad1903TrinidadGrid.setName("Trinidad_1903_Trinidad_Grid_Feet_Clarke");
            Trinidad1903TrinidadGridFeetClarke.setName("Trinidad_1903_Trinidad_Grid");
            UWPP1992.setName("UWPP_1992");
            UWPP2000pas5.setName("UWPP_2000_pas_5");
            UWPP2000pas6.setName("UWPP_2000_pas_6");
            UWPP2000pas7.setName("UWPP_2000_pas_7");
            UWPP2000pas8.setName("UWPP_2000_pas_8");
            WGS1972TM106NE.setName("WGS_1972_BE_TM_106_NE");
            WGS1984TM116SE.setName("WGS_1984_TM_116_SE");
            WGS1984TM132SE.setName("WGS_1984_TM_132_SE");
            WGS1984TM36SE.setName("WGS_1984_TM_36_SE");
            WGS1984TM6NE.setName("WGS_1984_TM_6_NE");
            ZanderijSurinameOldTM.setName("Zanderij_Suriname_Old_TM");
            ZanderijSurinameTM.setName("Zanderij_Suriname_TM");
            ZanderijTM54NW.setName("Zanderij_TM_54_NW");

            Abidjan1987TM5NW.getGeographicInfo().setName("GCS_Abidjan_1987");
            AccraGhanaGrid.getGeographicInfo().setName("GCS_Accra");
            AccraTM1NW.getGeographicInfo().setName("GCS_Accra");
            AinelAbdAramcoLambert.getGeographicInfo().setName("GCS_Ain_el_Abd_1970");
            AmericanSamoa1962SamoaLambert.getGeographicInfo().setName("GCS_American_Samoa_1962");
            Anguilla1957BritishWestIndiesGrid.getGeographicInfo().setName("GCS_Anguilla_1957");
            Antigua1943BritishWestIndiesGrid.getGeographicInfo().setName("GCS_Antigua_1943");
            AustriaFerroCentralZone.getGeographicInfo().setName("GCS_MGI_Ferro");
            AustriaFerroEastZone.getGeographicInfo().setName("GCS_MGI_Ferro");
            AustriaFerroWestZone.getGeographicInfo().setName("GCS_MGI_Ferro");
            BahrainStateGrid.getGeographicInfo().setName("GCS_Ain_el_Abd_1970");
            Barbados1938BarbadosGrid.getGeographicInfo().setName("GCS_Barbados_1938");
            Barbados1938BritishWestIndiesGrid.getGeographicInfo().setName("GCS_Barbados_1938");
            BataviaNEIEZ.getGeographicInfo().setName("GCS_Batavia");
            BataviaTM109SE.getGeographicInfo().setName("GCS_Batavia");
            BelgeLambert1950.getGeographicInfo().setName("GCS_Belge_1950_Brussels");
            BelgeLambert1972.getGeographicInfo().setName("GCS_Belge_1972");
            Bermuda2000NationalGrid.getGeographicInfo().setName("GCS_Bermuda_2000");
            Bern1898BernLV03C.getGeographicInfo().setName("GCS_Bern_1898_Bern");
            BritishNationalGridOSGB36.getGeographicInfo().setName("GCS_OSGB_1936");
            CamacupaTM1130SE.getGeographicInfo().setName("GCS_Camacupa");
            CamacupaTM12SE.getGeographicInfo().setName("GCS_Camacupa");
            CarthageTM11NE.getGeographicInfo().setName("GCS_Carthage");
            CentreFrance.getGeographicInfo().setName("GCS_NTF_Paris");
            CH1903LV03.getGeographicInfo().setName("GCS_CH1903");
            CH1903LV95.getGeographicInfo().setName("GCS_CH1903+");
            ColombiaBogotaZone.getGeographicInfo().setName("GCS_Bogota");
            ColombiaEastZone.getGeographicInfo().setName("GCS_Bogota");
            ColombiaECentralZone.getGeographicInfo().setName("GCS_Bogota");
            ColombiaWestZone.getGeographicInfo().setName("GCS_Bogota");
            Corse.getGeographicInfo().setName("GCS_NTF_Paris");
            Datum73HayfordGaussIGeoE.getGeographicInfo().setName("GCS_Datum_73");
            Datum73HayfordGaussIPCC.getGeographicInfo().setName("GCS_Datum_73");
            DeirezZorLevantStereographic.getGeographicInfo().setName("GCS_Deir_ez_Zor");
            DeirezZorLevantZone.getGeographicInfo().setName("GCS_Deir_ez_Zor");
            DeirezZorSyriaLambert.getGeographicInfo().setName("GCS_Deir_ez_Zor");
            DHDN3DegreeGaussZone1.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
            DHDN3DegreeGaussZone2.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
            DHDN3DegreeGaussZone3.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
            DHDN3DegreeGaussZone4.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
            DHDN3DegreeGaussZone5.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
            Dominica1945BritishWestIndiesGrid.getGeographicInfo().setName("GCS_Dominica_1945");
            Douala1948AOFWest.getGeographicInfo().setName("GCS_Douala_1948");
            ED1950FranceEuroLambert.getGeographicInfo().setName("GCS_European_1950");
            ED1950TM0N.getGeographicInfo().setName("GCS_European_1950");
            ED1950TM27.getGeographicInfo().setName("GCS_European_1950");
            ED1950TM30.getGeographicInfo().setName("GCS_European_1950");
            ED1950TM33.getGeographicInfo().setName("GCS_European_1950");
            ED1950TM36.getGeographicInfo().setName("GCS_European_1950");
            ED1950TM39.getGeographicInfo().setName("GCS_European_1950");
            ED1950TM42.getGeographicInfo().setName("GCS_European_1950");
            ED1950TM45.getGeographicInfo().setName("GCS_European_1950");
            ED1950TM5NE.getGeographicInfo().setName("GCS_European_1950");
            ED1950Turkey10.getGeographicInfo().setName("GCS_European_1950");
            ED1950Turkey11.getGeographicInfo().setName("GCS_European_1950");
            ED1950Turkey12.getGeographicInfo().setName("GCS_European_1950");
            ED1950Turkey13.getGeographicInfo().setName("GCS_European_1950");
            ED1950Turkey14.getGeographicInfo().setName("GCS_European_1950");
            ED1950Turkey15.getGeographicInfo().setName("GCS_European_1950");
            ED1950Turkey9.getGeographicInfo().setName("GCS_European_1950");
            EgyptBlueBelt.getGeographicInfo().setName("GCS_Egypt_1907");
            EgyptExtendedPurpleBelt.getGeographicInfo().setName("GCS_Egypt_1907");
            EgyptPurpleBelt.getGeographicInfo().setName("GCS_Egypt_1907");
            EgyptRedBelt.getGeographicInfo().setName("GCS_Egypt_1907");
            ELD1979Libya10.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
            ELD1979Libya11.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
            ELD1979Libya12.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
            ELD1979Libya13.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
            ELD1979Libya5.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
            ELD1979Libya6.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
            ELD1979Libya7.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
            ELD1979Libya8.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
            ELD1979Libya9.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
            ELD1979TM12NE.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
            Estonia1997EstoniaNationalGrid.getGeographicInfo().setName("GCS_Estonia_1997");
            EstonianCoordinateSystemof1992.getGeographicInfo().setName("GCS_Estonia_1992");
            ETRF1989TMBaltic1993.getGeographicInfo().setName("GCS_ETRF_1989");
            ETRS1989Kp2000Bornholm.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989Kp2000Jutland.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989Kp2000Zealand.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989PolandCS2000Zone5.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989PolandCS2000Zone6.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989PolandCS2000Zone7.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989PolandCS2000Zone8.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989PolandCS92.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989TM30NE.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989TMBaltic1993.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989UWPP1992.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989UWPP2000PAS5.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989UWPP2000PAS6.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989UWPP2000PAS7.getGeographicInfo().setName("GCS_ETRS_1989");
            ETRS1989UWPP2000PAS8.getGeographicInfo().setName("GCS_ETRS_1989");
            EUREFFINTM35FIN.getGeographicInfo().setName("GCS_EUREF_FIN");
            //EverestModified1969RSOMalayaMeters.getGeographicInfo().setName("GCS_Everest_Modified_1969");
            FD1958Iraq.getGeographicInfo().setName("GCS_FD_1958");
            FinlandZone1.getGeographicInfo().setName("GCS_KKJ");
            FinlandZone2.getGeographicInfo().setName("GCS_KKJ");
            FinlandZone3.getGeographicInfo().setName("GCS_KKJ");
            FinlandZone4.getGeographicInfo().setName("GCS_KKJ");
            FranceI.getGeographicInfo().setName("GCS_NTF_Paris");
            FranceII.getGeographicInfo().setName("GCS_NTF_Paris");
            FranceIII.getGeographicInfo().setName("GCS_NTF_Paris");
            FranceIV.getGeographicInfo().setName("GCS_NTF_Paris");
            GermanyZone1.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
            GermanyZone2.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
            GermanyZone3.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
            GermanyZone4.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
            GermanyZone5.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
            GhanaMetreGrid.getGeographicInfo().setName("GCS_Leigon");
            GreekGrid.getGeographicInfo().setName("GCS_GGRS_1987");
            Grenada1953BritishWestIndiesGrid.getGeographicInfo().setName("GCS_Grenada_1953");
            GuernseyGrid.getGeographicInfo().setName("GCS_WGS_1984");
            GunungSegaraNEIEZ.getGeographicInfo().setName("GCS_Gunung_Segara");
            Hanoi1972GK106NE.getGeographicInfo().setName("GCS_Hanoi_1972");
            HD1972EgysegesOrszagosVetuleti.getGeographicInfo().setName("GCS_Hungarian_1972");
            Helle1954JanMayenGrid.getGeographicInfo().setName("GCS_Helle_1954");
            HitoXVIII1963Argentina2.getGeographicInfo().setName("GCS_Hito_XVIII_1963");
            HongKong1980Grid.getGeographicInfo().setName("GCS_Hong_Kong_1980");
            Indian1960TM106NE.getGeographicInfo().setName("GCS_Indian_1960");
            IRENET95IrishTranverseMercator.getGeographicInfo().setName("GCS_IRENET95");
            IrishNationalGrid.getGeographicInfo().setName("GCS_TM65");
            ISN1993Lambert1993.getGeographicInfo().setName("GCS_ISN_1993");
            IsraelTMGrid.getGeographicInfo().setName("GCS_Israel");
            Jamaica1875OldGrid.getGeographicInfo().setName("GCS_Jamaica_1875");
            JamaicaGrid.getGeographicInfo().setName("GCS_Jamaica_1969");
            JordanJTM.getGeographicInfo().setName("GCS_Jordan");
            KandawalaCeylonBeltIndianYards1937.getGeographicInfo().setName("GCS_Kandawala");
            KandawalaCeylonBeltMeters.getGeographicInfo().setName("GCS_Kandawala");
            KOCLambert.getGeographicInfo().setName("GCS_Kuwait_Oil_Company");
            Korean1985KoreaCentralBelt.getGeographicInfo().setName("GCS_Korean_Datum_1985");
            Korean1985KoreaEastBelt.getGeographicInfo().setName("GCS_Korean_Datum_1985");
            Korean1985KoreaWestBelt.getGeographicInfo().setName("GCS_Korean_Datum_1985");
            KUDAMSKTM.getGeographicInfo().setName("GCS_KUDAMS");
            KuwaitOilCoLambert.getGeographicInfo().setName("GCS_Kuwait_Oil_Company");
            KuwaitUtilityKTM.getGeographicInfo().setName("GCS_KUDAMS");
            LakeMaracaiboGridM1.getGeographicInfo().setName("GCS_Lake");
            LakeMaracaiboGridM3.getGeographicInfo().setName("GCS_Lake");
            LakeMaracaiboGrid.getGeographicInfo().setName("GCS_Lake");
            LakeMaracaiboLaRosaGrid.getGeographicInfo().setName("GCS_Lake");
            LietuvosKoordinaciuSistema.getGeographicInfo().setName("GCS_LKS_1994");
            LisboaBesselBonne.getGeographicInfo().setName("GCS_Datum_Lisboa_Bessel");
            LisboaHayfordGaussIGeoE.getGeographicInfo().setName("GCS_Datum_Lisboa_Hayford");
            LisboaHayfordGaussIPCC.getGeographicInfo().setName("GCS_Datum_Lisboa_Hayford");
            Locodjo1965TM5NW.getGeographicInfo().setName("GCS_Locodjo_1965");
            Luxembourg1930Gauss.getGeographicInfo().setName("GCS_Luxembourg_1930");
            Madrid1870MadridSpain.getGeographicInfo().setName("GCS_Madrid_1870_Madrid");
            MakassarNEIEZ.getGeographicInfo().setName("GCS_Makassar");
            MGI3DegreeGaussZone5.getGeographicInfo().setName("GCS_MGI");
            MGI3DegreeGaussZone6.getGeographicInfo().setName("GCS_MGI");
            MGI3DegreeGaussZone7.getGeographicInfo().setName("GCS_MGI");
            MGI3DegreeGaussZone8.getGeographicInfo().setName("GCS_MGI");
            MGIAustriaLambert.getGeographicInfo().setName("GCS_MGI");
            MGIBalkans5.getGeographicInfo().setName("GCS_MGI");
            MGIBalkans6.getGeographicInfo().setName("GCS_MGI");
            MGIBalkans7.getGeographicInfo().setName("GCS_MGI");
            MGIBalkans8.getGeographicInfo().setName("GCS_MGI");
            MGIM28.getGeographicInfo().setName("GCS_MGI");
            MGIM31.getGeographicInfo().setName("GCS_MGI");
            MGIM34.getGeographicInfo().setName("GCS_MGI");
            MGISloveniaGrid.getGeographicInfo().setName("GCS_MGI");
            MonteMarioItaly1.getGeographicInfo().setName("GCS_Monte_Mario");
            MonteMarioItaly2.getGeographicInfo().setName("GCS_Monte_Mario");
            MonteMarioRomeItaly1.getGeographicInfo().setName("GCS_Monte_Mario_Rome");
            MonteMarioRomeItaly2.getGeographicInfo().setName("GCS_Monte_Mario_Rome");
            Montserrat1958BritishWestIndiesGrid.getGeographicInfo().setName("GCS_Montserrat_1958");
            MountDillonTobagoGrid.getGeographicInfo().setName("GCS_Mount_Dillon");
            NAD1927CubaNorte.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927CubaSur.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927GuatemalaNorte.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927GuatemalaSur.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1983HARNGuamMapGrid.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NewZealandMapGrid.getGeographicInfo().setName("GCS_New_Zealand_1949");
            NewZealandNorthIsland.getGeographicInfo().setName("GCS_New_Zealand_1949");
            NewZealandSouthIsland.getGeographicInfo().setName("GCS_New_Zealand_1949");
            NigeriaEastBelt.getGeographicInfo().setName("GCS_Minna");
            NigeriaMidBelt.getGeographicInfo().setName("GCS_Minna");
            NigeriaWestBelt.getGeographicInfo().setName("GCS_Minna");
            NordAlgerie.getGeographicInfo().setName("GCS_Voirol_Unifie_1960_Degree");
            NordAlgerieancienne.getGeographicInfo().setName("GCS_Voirol_1875");
            NordAlgerieAnciennedegrees.getGeographicInfo().setName("GCS_Voirol_1875_Degree");
            NordAlgeriedegrees.getGeographicInfo().setName("GCS_Voirol_Unifie_1960");
            NorddeGuerre.getGeographicInfo().setName("GCS_ATF_Paris");
            NordFrance.getGeographicInfo().setName("GCS_NTF_Paris");
            NordMaroc.getGeographicInfo().setName("GCS_Merchich");
            NordMarocdegrees.getGeographicInfo().setName("GCS_Merchich_Degree");
            NordTunisie.getGeographicInfo().setName("GCS_Carthage");
            NTFFranceIdegrees.getGeographicInfo().setName("GCS_NTF");
            NTFFranceIIdegrees.getGeographicInfo().setName("GCS_NTF");
            NTFFranceIIIdegrees.getGeographicInfo().setName("GCS_NTF");
            NTFFranceIVdegrees.getGeographicInfo().setName("GCS_NTF");
            ObservatorioMeteorologico1965MacauGrid.getGeographicInfo().setName("GCS_Observatorio_Meteorologico_1965");
            OSNI1952IrishNationalGrid.getGeographicInfo().setName("GCS_OSNI_1952");
            Palestine1923IsraelCSGrid.getGeographicInfo().setName("GCS_Palestine_1923");
            Palestine1923PalestineBelt.getGeographicInfo().setName("GCS_Palestine_1923");
            Palestine1923PalestineGrid.getGeographicInfo().setName("GCS_Palestine_1923");
            PampadelCastilloArgentina2.getGeographicInfo().setName("GCS_Pampa_del_Castillo");
            PeruCentralZone.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
            PeruEastZone.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
            PeruWestZone.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
            PhilippinesZoneI.getGeographicInfo().setName("GCS_Luzon_1911");
            PhilippinesZoneII.getGeographicInfo().setName("GCS_Luzon_1911");
            PhilippinesZoneIII.getGeographicInfo().setName("GCS_Luzon_1911");
            PhilippinesZoneIV.getGeographicInfo().setName("GCS_Luzon_1911");
            PhilippinesZoneV.getGeographicInfo().setName("GCS_Luzon_1911");
            PitondesNeigesTMReunion.getGeographicInfo().setName("GCS_Piton_des_Neiges");
            PortugueseNationalGrid.getGeographicInfo().setName("GCS_Lisbon_Lisbon");
            PSAD1956ICNRegional.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
            Qatar1948QatarGrid.getGeographicInfo().setName("GCS_Qatar_1948");
            QatarNationalGrid.getGeographicInfo().setName("GCS_Qatar");
            RassadiranNakhleTaqi.getGeographicInfo().setName("GCS_Rassadiran");
            RGF1993Lambert93.getGeographicInfo().setName("GCS_RGF_1993");
            RGNC1991LambertNewCaledonia.getGeographicInfo().setName("GCS_RGNC_1991");
            //Rijksdriehoekstelsel.getGeographicInfo().setName("GCS_Amersfoort");
            Roma1940GaussBoagaEst.getGeographicInfo().setName("GCS_Roma_1940");
            Roma1940GaussBoagaOvest.getGeographicInfo().setName("GCS_Roma_1940");
            RT9025gonWest.getGeographicInfo().setName("GCS_RT_1990");
            SAD1969BrazilPolyconic.getGeographicInfo().setName("GCS_South_American_1969");
            Sahara.getGeographicInfo().setName("GCS_Merchich");
            Saharadegrees.getGeographicInfo().setName("GCS_Merchich_Degree");
            SierraLeone1924NewColonyGrid.getGeographicInfo().setName("GCS_Sierra_Leone_1924");
            SierraLeone1924NewWarOfficeGrid.getGeographicInfo().setName("GCS_Sierra_Leone_1924");
            SJTSKFerroKrovak.getGeographicInfo().setName("GCS_S_JTSK_Ferro");
            SJTSKFerroKrovakEastNorth.getGeographicInfo().setName("GCS_S_JTSK_Ferro");
            SJTSKKrovak.getGeographicInfo().setName("GCS_S_JTSK");
            SJTSKKrovakEastNorth.getGeographicInfo().setName("GCS_S_JTSK");
            //Stereo1933.getGeographicInfo().setName("GCS_Dealul_Piscului_1933");
            //Stereo1970.getGeographicInfo().setName("GCS_Dealul_Piscului_1970");
            StKitts1955BritishWestIndiesGrid.getGeographicInfo().setName("GCS_St_Kitts_1955");
            StLucia1955BritishWestIndiesGrid.getGeographicInfo().setName("GCS_St_Lucia_1955");
            StVincent1945BritishWestIndiesGrid.getGeographicInfo().setName("GCS_St_Vincent_1945");
            SudAlgerie.getGeographicInfo().setName("GCS_Voirol_Unifie_1960");
            SudAlgerieAncienneDegree.getGeographicInfo().setName("GCS_Voirol_1875_Degree");
            SudAlgeriedegrees.getGeographicInfo().setName("GCS_Voirol_Unifie_1960_Degree");
            SudFrance.getGeographicInfo().setName("GCS_NTF_Paris");
            SudMaroc.getGeographicInfo().setName("GCS_Merchich");
            SudMarocdegrees.getGeographicInfo().setName("GCS_Merchich_Degree");
            SudTunisie.getGeographicInfo().setName("GCS_Carthage");
            SwedishNationalGrid.getGeographicInfo().setName("GCS_RT38_Stockholm");
            TM75IrishGrid.getGeographicInfo().setName("GCS_TM75");
            Trinidad1903TrinidadGrid.getGeographicInfo().setName("GCS_Trinidad_1903");
            Trinidad1903TrinidadGridFeetClarke.getGeographicInfo().setName("GCS_Trinidad_1903");
            UWPP1992.getGeographicInfo().setName("GCS_ETRF_1989");
            UWPP2000pas5.getGeographicInfo().setName("GCS_ETRF_1989");
            UWPP2000pas6.getGeographicInfo().setName("GCS_ETRF_1989");
            UWPP2000pas7.getGeographicInfo().setName("GCS_ETRF_1989");
            UWPP2000pas8.getGeographicInfo().setName("GCS_ETRF_1989");
            WGS1972TM106NE.getGeographicInfo().setName("GCS_WGS_1972_BE");
            WGS1984TM116SE.getGeographicInfo().setName("GCS_WGS_1984");
            WGS1984TM132SE.getGeographicInfo().setName("GCS_WGS_1984");
            WGS1984TM36SE.getGeographicInfo().setName("GCS_WGS_1984");
            WGS1984TM6NE.getGeographicInfo().setName("GCS_WGS_1984");
            ZanderijSurinameOldTM.getGeographicInfo().setName("GCS_Zanderij");
            ZanderijSurinameTM.getGeographicInfo().setName("GCS_Zanderij");
            ZanderijTM54NW.getGeographicInfo().setName("GCS_Zanderij");

            Abidjan1987TM5NW.getGeographicInfo().getDatum().setName("D_Abidjan_1987");
            AccraGhanaGrid.getGeographicInfo().getDatum().setName("D_Accra");
            AccraTM1NW.getGeographicInfo().getDatum().setName("D_Accra");
            AinelAbdAramcoLambert.getGeographicInfo().getDatum().setName("D_Ain_el_Abd_1970");
            AmericanSamoa1962SamoaLambert.getGeographicInfo().getDatum().setName("D_American_Samoa_1962");
            Anguilla1957BritishWestIndiesGrid.getGeographicInfo().getDatum().setName("D_Anguilla_1957");
            Antigua1943BritishWestIndiesGrid.getGeographicInfo().getDatum().setName("D_Antigua_1943");
            AustriaFerroCentralZone.getGeographicInfo().getDatum().setName("D_MGI");
            AustriaFerroEastZone.getGeographicInfo().getDatum().setName("D_MGI");
            AustriaFerroWestZone.getGeographicInfo().getDatum().setName("D_MGI");
            BahrainStateGrid.getGeographicInfo().getDatum().setName("D_Ain_el_Abd_1970");
            Barbados1938BarbadosGrid.getGeographicInfo().getDatum().setName("D_Barbados_1938");
            Barbados1938BritishWestIndiesGrid.getGeographicInfo().getDatum().setName("D_Barbados_1938");
            BataviaNEIEZ.getGeographicInfo().getDatum().setName("D_Batavia");
            BataviaTM109SE.getGeographicInfo().getDatum().setName("D_Batavia");
            BelgeLambert1950.getGeographicInfo().getDatum().setName("D_Belge_1950");
            BelgeLambert1972.getGeographicInfo().getDatum().setName("D_Belge_1972");
            Bermuda2000NationalGrid.getGeographicInfo().getDatum().setName("D_Bermuda_2000");
            Bern1898BernLV03C.getGeographicInfo().getDatum().setName("D_Bern_1898");
            BritishNationalGridOSGB36.getGeographicInfo().getDatum().setName("D_OSGB_1936");
            CamacupaTM1130SE.getGeographicInfo().getDatum().setName("D_Camacupa");
            CamacupaTM12SE.getGeographicInfo().getDatum().setName("D_Camacupa");
            CarthageTM11NE.getGeographicInfo().getDatum().setName("D_Carthage");
            CentreFrance.getGeographicInfo().getDatum().setName("D_NTF");
            CH1903LV03.getGeographicInfo().getDatum().setName("D_CH1903");
            CH1903LV95.getGeographicInfo().getDatum().setName("D_CH1903+");
            ColombiaBogotaZone.getGeographicInfo().getDatum().setName("D_Bogota");
            ColombiaEastZone.getGeographicInfo().getDatum().setName("D_Bogota");
            ColombiaECentralZone.getGeographicInfo().getDatum().setName("D_Bogota");
            ColombiaWestZone.getGeographicInfo().getDatum().setName("D_Bogota");
            Corse.getGeographicInfo().getDatum().setName("D_NTF");
            Datum73HayfordGaussIGeoE.getGeographicInfo().getDatum().setName("D_Datum_73");
            Datum73HayfordGaussIPCC.getGeographicInfo().getDatum().setName("D_Datum_73");
            DeirezZorLevantStereographic.getGeographicInfo().getDatum().setName("D_Deir_ez_Zor");
            DeirezZorLevantZone.getGeographicInfo().getDatum().setName("D_Deir_ez_Zor");
            DeirezZorSyriaLambert.getGeographicInfo().getDatum().setName("D_Deir_ez_Zor");
            DHDN3DegreeGaussZone1.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
            DHDN3DegreeGaussZone2.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
            DHDN3DegreeGaussZone3.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
            DHDN3DegreeGaussZone4.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
            DHDN3DegreeGaussZone5.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
            Dominica1945BritishWestIndiesGrid.getGeographicInfo().getDatum().setName("D_Dominica_1945");
            Douala1948AOFWest.getGeographicInfo().getDatum().setName("D_Douala_1948");
            ED1950FranceEuroLambert.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950TM0N.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950TM27.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950TM30.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950TM33.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950TM36.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950TM39.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950TM42.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950TM45.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950TM5NE.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950Turkey10.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950Turkey11.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950Turkey12.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950Turkey13.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950Turkey14.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950Turkey15.getGeographicInfo().getDatum().setName("D_European_1950");
            ED1950Turkey9.getGeographicInfo().getDatum().setName("D_European_1950");
            EgyptBlueBelt.getGeographicInfo().getDatum().setName("D_Egypt_1907");
            EgyptExtendedPurpleBelt.getGeographicInfo().getDatum().setName("D_Egypt_1907");
            EgyptPurpleBelt.getGeographicInfo().getDatum().setName("D_Egypt_1907");
            EgyptRedBelt.getGeographicInfo().getDatum().setName("D_Egypt_1907");
            ELD1979Libya10.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
            ELD1979Libya11.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
            ELD1979Libya12.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
            ELD1979Libya13.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
            ELD1979Libya5.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
            ELD1979Libya6.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
            ELD1979Libya7.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
            ELD1979Libya8.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
            ELD1979Libya9.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
            ELD1979TM12NE.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
            Estonia1997EstoniaNationalGrid.getGeographicInfo().getDatum().setName("D_Estonia_1997");
            EstonianCoordinateSystemof1992.getGeographicInfo().getDatum().setName("D_Estonia_1992");
            ETRF1989TMBaltic1993.getGeographicInfo().getDatum().setName("D_ETRF_1989");
            ETRS1989Kp2000Bornholm.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989Kp2000Jutland.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989Kp2000Zealand.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989PolandCS2000Zone5.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989PolandCS2000Zone6.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989PolandCS2000Zone7.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989PolandCS2000Zone8.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989PolandCS92.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989TM30NE.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989TMBaltic1993.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989UWPP1992.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989UWPP2000PAS5.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989UWPP2000PAS6.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989UWPP2000PAS7.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            ETRS1989UWPP2000PAS8.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            EUREFFINTM35FIN.getGeographicInfo().getDatum().setName("D_ETRS_1989");
            //EverestModified1969RSOMalayaMeters.getGeographicInfo().getDatum().setName("D_Everest_Modified_1969");
            FD1958Iraq.getGeographicInfo().getDatum().setName("D_FD_1958");
            FinlandZone1.getGeographicInfo().getDatum().setName("D_KKJ");
            FinlandZone2.getGeographicInfo().getDatum().setName("D_KKJ");
            FinlandZone3.getGeographicInfo().getDatum().setName("D_KKJ");
            FinlandZone4.getGeographicInfo().getDatum().setName("D_KKJ");
            FranceI.getGeographicInfo().getDatum().setName("D_NTF");
            FranceII.getGeographicInfo().getDatum().setName("D_NTF");
            FranceIII.getGeographicInfo().getDatum().setName("D_NTF");
            FranceIV.getGeographicInfo().getDatum().setName("D_NTF");
            GermanyZone1.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
            GermanyZone2.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
            GermanyZone3.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
            GermanyZone4.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
            GermanyZone5.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
            GhanaMetreGrid.getGeographicInfo().getDatum().setName("D_Leigon");
            GreekGrid.getGeographicInfo().getDatum().setName("D_GGRS_1987");
            Grenada1953BritishWestIndiesGrid.getGeographicInfo().getDatum().setName("D_Grenada_1953");
            GuernseyGrid.getGeographicInfo().getDatum().setName("D_WGS_1984");
            GunungSegaraNEIEZ.getGeographicInfo().getDatum().setName("D_Gunung_Segara");
            Hanoi1972GK106NE.getGeographicInfo().getDatum().setName("D_Hanoi_1972");
            HD1972EgysegesOrszagosVetuleti.getGeographicInfo().getDatum().setName("D_Hungarian_1972");
            Helle1954JanMayenGrid.getGeographicInfo().getDatum().setName("D_Helle_1954");
            HitoXVIII1963Argentina2.getGeographicInfo().getDatum().setName("D_Hito_XVIII_1963");
            HongKong1980Grid.getGeographicInfo().getDatum().setName("D_Hong_Kong_1980");
            Indian1960TM106NE.getGeographicInfo().getDatum().setName("D_Indian_1960");
            IRENET95IrishTranverseMercator.getGeographicInfo().getDatum().setName("D_IRENET95");
            IrishNationalGrid.getGeographicInfo().getDatum().setName("D_TM65");
            ISN1993Lambert1993.getGeographicInfo().getDatum().setName("D_Islands_Network_1993");
            IsraelTMGrid.getGeographicInfo().getDatum().setName("D_Israel");
            Jamaica1875OldGrid.getGeographicInfo().getDatum().setName("D_Jamaica_1875");
            JamaicaGrid.getGeographicInfo().getDatum().setName("D_Jamaica_1969");
            JordanJTM.getGeographicInfo().getDatum().setName("D_Jordan");
            KandawalaCeylonBeltIndianYards1937.getGeographicInfo().getDatum().setName("D_Kandawala");
            KandawalaCeylonBeltMeters.getGeographicInfo().getDatum().setName("D_Kandawala");
            KOCLambert.getGeographicInfo().getDatum().setName("D_Kuwait_Oil_Company");
            Korean1985KoreaCentralBelt.getGeographicInfo().getDatum().setName("D_Korean_Datum_1985");
            Korean1985KoreaEastBelt.getGeographicInfo().getDatum().setName("D_Korean_Datum_1985");
            Korean1985KoreaWestBelt.getGeographicInfo().getDatum().setName("D_Korean_Datum_1985");
            KUDAMSKTM.getGeographicInfo().getDatum().setName("D_Kuwait_Utility");
            KuwaitOilCoLambert.getGeographicInfo().getDatum().setName("D_Kuwait_Oil_Company");
            KuwaitUtilityKTM.getGeographicInfo().getDatum().setName("D_Kuwait_Utility");
            LakeMaracaiboGridM1.getGeographicInfo().getDatum().setName("D_Lake");
            LakeMaracaiboGridM3.getGeographicInfo().getDatum().setName("D_Lake");
            LakeMaracaiboGrid.getGeographicInfo().getDatum().setName("D_Lake");
            LakeMaracaiboLaRosaGrid.getGeographicInfo().getDatum().setName("D_Lake");
            LietuvosKoordinaciuSistema.getGeographicInfo().getDatum().setName("D_Lithuania_1994");
            LisboaBesselBonne.getGeographicInfo().getDatum().setName("D_Datum_Lisboa_Bessel");
            LisboaHayfordGaussIGeoE.getGeographicInfo().getDatum().setName("D_Datum_Lisboa_Hayford");
            LisboaHayfordGaussIPCC.getGeographicInfo().getDatum().setName("D_Datum_Lisboa_Hayford");
            Locodjo1965TM5NW.getGeographicInfo().getDatum().setName("D_Locodjo_1965");
            Luxembourg1930Gauss.getGeographicInfo().getDatum().setName("D_Luxembourg_1930");
            Madrid1870MadridSpain.getGeographicInfo().getDatum().setName("D_Madrid_1870");
            MakassarNEIEZ.getGeographicInfo().getDatum().setName("D_Makassar");
            MGI3DegreeGaussZone5.getGeographicInfo().getDatum().setName("D_MGI");
            MGI3DegreeGaussZone6.getGeographicInfo().getDatum().setName("D_MGI");
            MGI3DegreeGaussZone7.getGeographicInfo().getDatum().setName("D_MGI");
            MGI3DegreeGaussZone8.getGeographicInfo().getDatum().setName("D_MGI");
            MGIAustriaLambert.getGeographicInfo().getDatum().setName("D_MGI");
            MGIBalkans5.getGeographicInfo().getDatum().setName("D_MGI");
            MGIBalkans6.getGeographicInfo().getDatum().setName("D_MGI");
            MGIBalkans7.getGeographicInfo().getDatum().setName("D_MGI");
            MGIBalkans8.getGeographicInfo().getDatum().setName("D_MGI");
            MGIM28.getGeographicInfo().getDatum().setName("D_MGI");
            MGIM31.getGeographicInfo().getDatum().setName("D_MGI");
            MGIM34.getGeographicInfo().getDatum().setName("D_MGI");
            MGISloveniaGrid.getGeographicInfo().getDatum().setName("D_MGI");
            MonteMarioItaly1.getGeographicInfo().getDatum().setName("D_Monte_Mario");
            MonteMarioItaly2.getGeographicInfo().getDatum().setName("D_Monte_Mario");
            MonteMarioRomeItaly1.getGeographicInfo().getDatum().setName("D_Monte_Mario");
            MonteMarioRomeItaly2.getGeographicInfo().getDatum().setName("D_Monte_Mario");
            Montserrat1958BritishWestIndiesGrid.getGeographicInfo().getDatum().setName("D_Montserrat_1958");
            MountDillonTobagoGrid.getGeographicInfo().getDatum().setName("D_Mount_Dillon");
            NAD1927CubaNorte.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927CubaSur.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927GuatemalaNorte.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927GuatemalaSur.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1983HARNGuamMapGrid.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NewZealandMapGrid.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
            NewZealandNorthIsland.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
            NewZealandSouthIsland.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
            NigeriaEastBelt.getGeographicInfo().getDatum().setName("D_Minna");
            NigeriaMidBelt.getGeographicInfo().getDatum().setName("D_Minna");
            NigeriaWestBelt.getGeographicInfo().getDatum().setName("D_Minna");
            NordAlgerie.getGeographicInfo().getDatum().setName("D_Voirol_Unifie_1960");
            NordAlgerieancienne.getGeographicInfo().getDatum().setName("D_Voirol_1875");
            NordAlgerieAnciennedegrees.getGeographicInfo().getDatum().setName("D_Voirol_1875");
            NordAlgeriedegrees.getGeographicInfo().getDatum().setName("D_Voirol_Unifie_1960");
            NorddeGuerre.getGeographicInfo().getDatum().setName("D_ATF");
            NordFrance.getGeographicInfo().getDatum().setName("D_NTF");
            NordMaroc.getGeographicInfo().getDatum().setName("D_Merchich");
            NordMarocdegrees.getGeographicInfo().getDatum().setName("D_Merchich");
            NordTunisie.getGeographicInfo().getDatum().setName("D_Carthage");
            NTFFranceIdegrees.getGeographicInfo().getDatum().setName("D_NTF");
            NTFFranceIIdegrees.getGeographicInfo().getDatum().setName("D_NTF");
            NTFFranceIIIdegrees.getGeographicInfo().getDatum().setName("D_NTF");
            NTFFranceIVdegrees.getGeographicInfo().getDatum().setName("D_NTF");
            ObservatorioMeteorologico1965MacauGrid.getGeographicInfo().getDatum().setName("D_Observatorio_Meteorologico_1965");
            OSNI1952IrishNationalGrid.getGeographicInfo().getDatum().setName("D_OSNI_1952");
            Palestine1923IsraelCSGrid.getGeographicInfo().getDatum().setName("D_Palestine_1923");
            Palestine1923PalestineBelt.getGeographicInfo().getDatum().setName("D_Palestine_1923");
            Palestine1923PalestineGrid.getGeographicInfo().getDatum().setName("D_Palestine_1923");
            PampadelCastilloArgentina2.getGeographicInfo().getDatum().setName("D_Pampa_del_Castillo");
            PeruCentralZone.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
            PeruEastZone.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
            PeruWestZone.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
            PhilippinesZoneI.getGeographicInfo().getDatum().setName("D_Luzon_1911");
            PhilippinesZoneII.getGeographicInfo().getDatum().setName("D_Luzon_1911");
            PhilippinesZoneIII.getGeographicInfo().getDatum().setName("D_Luzon_1911");
            PhilippinesZoneIV.getGeographicInfo().getDatum().setName("D_Luzon_1911");
            PhilippinesZoneV.getGeographicInfo().getDatum().setName("D_Luzon_1911");
            PitondesNeigesTMReunion.getGeographicInfo().getDatum().setName("D_Piton_des_Neiges");
            PortugueseNationalGrid.getGeographicInfo().getDatum().setName("D_Lisbon");
            PSAD1956ICNRegional.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
            Qatar1948QatarGrid.getGeographicInfo().getDatum().setName("D_Qatar_1948");
            QatarNationalGrid.getGeographicInfo().getDatum().setName("D_Qatar");
            RassadiranNakhleTaqi.getGeographicInfo().getDatum().setName("D_Rassadiran");
            RGF1993Lambert93.getGeographicInfo().getDatum().setName("D_RGF_1993");
            RGNC1991LambertNewCaledonia.getGeographicInfo().getDatum().setName("D_RGNC_1991");
            //Rijksdriehoekstelsel.getGeographicInfo().getDatum().setName("D_Amersfoort");
            Roma1940GaussBoagaEst.getGeographicInfo().getDatum().setName("D_Roma_1940");
            Roma1940GaussBoagaOvest.getGeographicInfo().getDatum().setName("D_Roma_1940");
            RT9025gonWest.getGeographicInfo().getDatum().setName("D_RT_1990");
            SAD1969BrazilPolyconic.getGeographicInfo().getDatum().setName("D_South_American_1969");
            Sahara.getGeographicInfo().getDatum().setName("D_Merchich");
            Saharadegrees.getGeographicInfo().getDatum().setName("D_Merchich");
            SierraLeone1924NewColonyGrid.getGeographicInfo().getDatum().setName("D_Sierra_Leone_1924");
            SierraLeone1924NewWarOfficeGrid.getGeographicInfo().getDatum().setName("D_Sierra_Leone_1924");
            SJTSKFerroKrovak.getGeographicInfo().getDatum().setName("D_S_JTSK");
            SJTSKFerroKrovakEastNorth.getGeographicInfo().getDatum().setName("D_S_JTSK");
            SJTSKKrovak.getGeographicInfo().getDatum().setName("D_S_JTSK");
            SJTSKKrovakEastNorth.getGeographicInfo().getDatum().setName("D_S_JTSK");
            //Stereo1933.getGeographicInfo().getDatum().setName("D_Dealul_Piscului_1933");
            //Stereo1970.getGeographicInfo().getDatum().setName("D_Dealul_Piscului_1970");
            StKitts1955BritishWestIndiesGrid.getGeographicInfo().getDatum().setName("D_St_Kitts_1955");
            StLucia1955BritishWestIndiesGrid.getGeographicInfo().getDatum().setName("D_St_Lucia_1955");
            StVincent1945BritishWestIndiesGrid.getGeographicInfo().getDatum().setName("D_St_Vincent_1945");
            SudAlgerie.getGeographicInfo().getDatum().setName("D_Voirol_Unifie_1960");
            SudAlgerieAncienneDegree.getGeographicInfo().getDatum().setName("D_Voirol_1875");
            SudAlgeriedegrees.getGeographicInfo().getDatum().setName("D_Voirol_Unifie_1960");
            SudFrance.getGeographicInfo().getDatum().setName("D_NTF");
            SudMaroc.getGeographicInfo().getDatum().setName("D_Merchich");
            SudMarocdegrees.getGeographicInfo().getDatum().setName("D_Merchich");
            SudTunisie.getGeographicInfo().getDatum().setName("D_Carthage");
            SwedishNationalGrid.getGeographicInfo().getDatum().setName("D_Stockholm_1938");
            TM75IrishGrid.getGeographicInfo().getDatum().setName("D_TM75");
            Trinidad1903TrinidadGrid.getGeographicInfo().getDatum().setName("D_Trinidad_1903");
            Trinidad1903TrinidadGridFeetClarke.getGeographicInfo().getDatum().setName("D_Trinidad_1903");
            UWPP1992.getGeographicInfo().getDatum().setName("D_ETRF_1989");
            UWPP2000pas5.getGeographicInfo().getDatum().setName("D_ETRF_1989");
            UWPP2000pas6.getGeographicInfo().getDatum().setName("D_ETRF_1989");
            UWPP2000pas7.getGeographicInfo().getDatum().setName("D_ETRF_1989");
            UWPP2000pas8.getGeographicInfo().getDatum().setName("D_ETRF_1989");
            WGS1972TM106NE.getGeographicInfo().getDatum().setName("D_WGS_1972_BE");
            WGS1984TM116SE.getGeographicInfo().getDatum().setName("D_WGS_1984");
            WGS1984TM132SE.getGeographicInfo().getDatum().setName("D_WGS_1984");
            WGS1984TM36SE.getGeographicInfo().getDatum().setName("D_WGS_1984");
            WGS1984TM6NE.getGeographicInfo().getDatum().setName("D_WGS_1984");
            ZanderijSurinameOldTM.getGeographicInfo().getDatum().setName("D_Zanderij");
            ZanderijSurinameTM.getGeographicInfo().getDatum().setName("D_Zanderij");
            ZanderijTM54NW.getGeographicInfo().getDatum().setName("D_Zanderij");
        }

        //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Properties">


  /**
   * @return the Abidjan1987TM5NW
   */
  public ProjectionInfo getAbidjan1987TM5NW() {
    return Abidjan1987TM5NW.copy();
  }

  /**
   * @return the AccraGhanaGrid
   */
  public ProjectionInfo getAccraGhanaGrid() {
    return AccraGhanaGrid.copy();
  }

  /**
   * @return the AccraTM1NW
   */
  public ProjectionInfo getAccraTM1NW() {
    return AccraTM1NW.copy();
  }

  /**
   * @return the AinelAbdAramcoLambert
   */
  public ProjectionInfo getAinelAbdAramcoLambert() {
    return AinelAbdAramcoLambert.copy();
  }

  /**
   * @return the AmericanSamoa1962SamoaLambert
   */
  public ProjectionInfo getAmericanSamoa1962SamoaLambert() {
    return AmericanSamoa1962SamoaLambert.copy();
  }

  /**
   * @return the Anguilla1957BritishWestIndiesGrid
   */
  public ProjectionInfo getAnguilla1957BritishWestIndiesGrid() {
    return Anguilla1957BritishWestIndiesGrid.copy();
  }

  /**
   * @return the Antigua1943BritishWestIndiesGrid
   */
  public ProjectionInfo getAntigua1943BritishWestIndiesGrid() {
    return Antigua1943BritishWestIndiesGrid.copy();
  }

  /**
   * @return the ArgentinaZone1
   */
  public ProjectionInfo getArgentinaZone1() {
    return ArgentinaZone1.copy();
  }

  /**
   * @return the ArgentinaZone2
   */
  public ProjectionInfo getArgentinaZone2() {
    return ArgentinaZone2.copy();
  }

  /**
   * @return the ArgentinaZone3
   */
  public ProjectionInfo getArgentinaZone3() {
    return ArgentinaZone3.copy();
  }

  /**
   * @return the ArgentinaZone4
   */
  public ProjectionInfo getArgentinaZone4() {
    return ArgentinaZone4.copy();
  }

  /**
   * @return the ArgentinaZone5
   */
  public ProjectionInfo getArgentinaZone5() {
    return ArgentinaZone5.copy();
  }

  /**
   * @return the ArgentinaZone6
   */
  public ProjectionInfo getArgentinaZone6() {
    return ArgentinaZone6.copy();
  }

  /**
   * @return the ArgentinaZone7
   */
  public ProjectionInfo getArgentinaZone7() {
    return ArgentinaZone7.copy();
  }

  /**
   * @return the AustriaFerroCentralZone
   */
  public ProjectionInfo getAustriaFerroCentralZone() {
    return AustriaFerroCentralZone.copy();
  }

  /**
   * @return the AustriaFerroEastZone
   */
  public ProjectionInfo getAustriaFerroEastZone() {
    return AustriaFerroEastZone.copy();
  }

  /**
   * @return the AustriaFerroWestZone
   */
  public ProjectionInfo getAustriaFerroWestZone() {
    return AustriaFerroWestZone.copy();
  }

  /**
   * @return the BahrainStateGrid
   */
  public ProjectionInfo getBahrainStateGrid() {
    return BahrainStateGrid.copy();
  }

  /**
   * @return the Barbados1938BarbadosGrid
   */
  public ProjectionInfo getBarbados1938BarbadosGrid() {
    return Barbados1938BarbadosGrid.copy();
  }

  /**
   * @return the Barbados1938BritishWestIndiesGrid
   */
  public ProjectionInfo getBarbados1938BritishWestIndiesGrid() {
    return Barbados1938BritishWestIndiesGrid.copy();
  }

  /**
   * @return the BataviaNEIEZ
   */
  public ProjectionInfo getBataviaNEIEZ() {
    return BataviaNEIEZ.copy();
  }

  /**
   * @return the BataviaTM109SE
   */
  public ProjectionInfo getBataviaTM109SE() {
    return BataviaTM109SE.copy();
  }

  /**
   * @return the BelgeLambert1950
   */
  public ProjectionInfo getBelgeLambert1950() {
    return BelgeLambert1950.copy();
  }

  /**
   * @return the BelgeLambert1972
   */
  public ProjectionInfo getBelgeLambert1972() {
    return BelgeLambert1972.copy();
  }

  /**
   * @return the Bermuda2000NationalGrid
   */
  public ProjectionInfo getBermuda2000NationalGrid() {
    return Bermuda2000NationalGrid.copy();
  }

  /**
   * @return the Bern1898BernLV03C
   */
  public ProjectionInfo getBern1898BernLV03C() {
    return Bern1898BernLV03C.copy();
  }

  /**
   * @return the BritishNationalGridOSGB36
   */
  public ProjectionInfo getBritishNationalGridOSGB36() {
    return BritishNationalGridOSGB36.copy();
  }

  /**
   * @return the CH1903LV03
   */
  public ProjectionInfo getCH1903LV03() {
    return CH1903LV03.copy();
  }

  /**
   * @return the CH1903LV95
   */
  public ProjectionInfo getCH1903LV95() {
    return CH1903LV95.copy();
  }

  /**
   * @return the CamacupaTM1130SE
   */
  public ProjectionInfo getCamacupaTM1130SE() {
    return CamacupaTM1130SE.copy();
  }

  /**
   * @return the CamacupaTM12SE
   */
  public ProjectionInfo getCamacupaTM12SE() {
    return CamacupaTM12SE.copy();
  }

  /**
   * @return the CarthageTM11NE
   */
  public ProjectionInfo getCarthageTM11NE() {
    return CarthageTM11NE.copy();
  }

  /**
   * @return the CentreFrance
   */
  public ProjectionInfo getCentreFrance() {
    return CentreFrance.copy();
  }

  /**
   * @return the ChosMalal1914Argentina2
   */
  public ProjectionInfo getChosMalal1914Argentina2() {
    return ChosMalal1914Argentina2.copy();
  }

  /**
   * @return the ColombiaBogotaZone
   */
  public ProjectionInfo getColombiaBogotaZone() {
    return ColombiaBogotaZone.copy();
  }

  /**
   * @return the ColombiaECentralZone
   */
  public ProjectionInfo getColombiaECentralZone() {
    return ColombiaECentralZone.copy();
  }

  /**
   * @return the ColombiaEastZone
   */
  public ProjectionInfo getColombiaEastZone() {
    return ColombiaEastZone.copy();
  }

  /**
   * @return the ColombiaWestZone
   */
  public ProjectionInfo getColombiaWestZone() {
    return ColombiaWestZone.copy();
  }

  /**
   * @return the Corse
   */
  public ProjectionInfo getCorse() {
    return Corse.copy();
  }

  /**
   * @return the DHDN3DegreeGaussZone1
   */
  public ProjectionInfo getDHDN3DegreeGaussZone1() {
    return DHDN3DegreeGaussZone1.copy();
  }

  /**
   * @return the DHDN3DegreeGaussZone2
   */
  public ProjectionInfo getDHDN3DegreeGaussZone2() {
    return DHDN3DegreeGaussZone2.copy();
  }

  /**
   * @return the DHDN3DegreeGaussZone3
   */
  public ProjectionInfo getDHDN3DegreeGaussZone3() {
    return DHDN3DegreeGaussZone3.copy();
  }

  /**
   * @return the DHDN3DegreeGaussZone4
   */
  public ProjectionInfo getDHDN3DegreeGaussZone4() {
    return DHDN3DegreeGaussZone4.copy();
  }

  /**
   * @return the DHDN3DegreeGaussZone5
   */
  public ProjectionInfo getDHDN3DegreeGaussZone5() {
    return DHDN3DegreeGaussZone5.copy();
  }

  /**
   * @return the Datum73HayfordGaussIGeoE
   */
  public ProjectionInfo getDatum73HayfordGaussIGeoE() {
    return Datum73HayfordGaussIGeoE.copy();
  }

  /**
   * @return the Datum73HayfordGaussIPCC
   */
  public ProjectionInfo getDatum73HayfordGaussIPCC() {
    return Datum73HayfordGaussIPCC.copy();
  }

  /**
   * @return the DeirezZorLevantStereographic
   */
  public ProjectionInfo getDeirezZorLevantStereographic() {
    return DeirezZorLevantStereographic.copy();
  }

  /**
   * @return the DeirezZorLevantZone
   */
  public ProjectionInfo getDeirezZorLevantZone() {
    return DeirezZorLevantZone.copy();
  }

  /**
   * @return the DeirezZorSyriaLambert
   */
  public ProjectionInfo getDeirezZorSyriaLambert() {
    return DeirezZorSyriaLambert.copy();
  }

  /**
   * @return the Dominica1945BritishWestIndiesGrid
   */
  public ProjectionInfo getDominica1945BritishWestIndiesGrid() {
    return Dominica1945BritishWestIndiesGrid.copy();
  }

  /**
   * @return the Douala1948AOFWest
   */
  public ProjectionInfo getDouala1948AOFWest() {
    return Douala1948AOFWest.copy();
  }

  /**
   * @return the ED1950FranceEuroLambert
   */
  public ProjectionInfo getED1950FranceEuroLambert() {
    return ED1950FranceEuroLambert.copy();
  }

  /**
   * @return the ED1950TM0N
   */
  public ProjectionInfo getED1950TM0N() {
    return ED1950TM0N.copy();
  }

  /**
   * @return the ED1950TM27
   */
  public ProjectionInfo getED1950TM27() {
    return ED1950TM27.copy();
  }

  /**
   * @return the ED1950TM30
   */
  public ProjectionInfo getED1950TM30() {
    return ED1950TM30.copy();
  }

  /**
   * @return the ED1950TM33
   */
  public ProjectionInfo getED1950TM33() {
    return ED1950TM33.copy();
  }

  /**
   * @return the ED1950TM36
   */
  public ProjectionInfo getED1950TM36() {
    return ED1950TM36.copy();
  }

  /**
   * @return the ED1950TM39
   */
  public ProjectionInfo getED1950TM39() {
    return ED1950TM39.copy();
  }

  /**
   * @return the ED1950TM42
   */
  public ProjectionInfo getED1950TM42() {
    return ED1950TM42.copy();
  }

  /**
   * @return the ED1950TM45
   */
  public ProjectionInfo getED1950TM45() {
    return ED1950TM45.copy();
  }

  /**
   * @return the ED1950TM5NE
   */
  public ProjectionInfo getED1950TM5NE() {
    return ED1950TM5NE.copy();
  }

  /**
   * @return the ED1950Turkey10
   */
  public ProjectionInfo getED1950Turkey10() {
    return ED1950Turkey10.copy();
  }

  /**
   * @return the ED1950Turkey11
   */
  public ProjectionInfo getED1950Turkey11() {
    return ED1950Turkey11.copy();
  }

  /**
   * @return the ED1950Turkey12
   */
  public ProjectionInfo getED1950Turkey12() {
    return ED1950Turkey12.copy();
  }

  /**
   * @return the ED1950Turkey13
   */
  public ProjectionInfo getED1950Turkey13() {
    return ED1950Turkey13.copy();
  }

  /**
   * @return the ED1950Turkey14
   */
  public ProjectionInfo getED1950Turkey14() {
    return ED1950Turkey14.copy();
  }

  /**
   * @return the ED1950Turkey15
   */
  public ProjectionInfo getED1950Turkey15() {
    return ED1950Turkey15.copy();
  }

  /**
   * @return the ED1950Turkey9
   */
  public ProjectionInfo getED1950Turkey9() {
    return ED1950Turkey9.copy();
  }

  /**
   * @return the ELD1979Libya10
   */
  public ProjectionInfo getELD1979Libya10() {
    return ELD1979Libya10.copy();
  }

  /**
   * @return the ELD1979Libya11
   */
  public ProjectionInfo getELD1979Libya11() {
    return ELD1979Libya11.copy();
  }

  /**
   * @return the ELD1979Libya12
   */
  public ProjectionInfo getELD1979Libya12() {
    return ELD1979Libya12.copy();
  }

  /**
   * @return the ELD1979Libya13
   */
  public ProjectionInfo getELD1979Libya13() {
    return ELD1979Libya13.copy();
  }

  /**
   * @return the ELD1979Libya5
   */
  public ProjectionInfo getELD1979Libya5() {
    return ELD1979Libya5.copy();
  }

  /**
   * @return the ELD1979Libya6
   */
  public ProjectionInfo getELD1979Libya6() {
    return ELD1979Libya6.copy();
  }

  /**
   * @return the ELD1979Libya7
   */
  public ProjectionInfo getELD1979Libya7() {
    return ELD1979Libya7.copy();
  }

  /**
   * @return the ELD1979Libya8
   */
  public ProjectionInfo getELD1979Libya8() {
    return ELD1979Libya8.copy();
  }

  /**
   * @return the ELD1979Libya9
   */
  public ProjectionInfo getELD1979Libya9() {
    return ELD1979Libya9.copy();
  }

  /**
   * @return the ELD1979TM12NE
   */
  public ProjectionInfo getELD1979TM12NE() {
    return ELD1979TM12NE.copy();
  }

  /**
   * @return the ETRF1989TMBaltic1993
   */
  public ProjectionInfo getETRF1989TMBaltic1993() {
    return ETRF1989TMBaltic1993.copy();
  }

  /**
   * @return the ETRS1989Kp2000Bornholm
   */
  public ProjectionInfo getETRS1989Kp2000Bornholm() {
    return ETRS1989Kp2000Bornholm.copy();
  }

  /**
   * @return the ETRS1989Kp2000Jutland
   */
  public ProjectionInfo getETRS1989Kp2000Jutland() {
    return ETRS1989Kp2000Jutland.copy();
  }

  /**
   * @return the ETRS1989Kp2000Zealand
   */
  public ProjectionInfo getETRS1989Kp2000Zealand() {
    return ETRS1989Kp2000Zealand.copy();
  }

  /**
   * @return the ETRS1989PolandCS2000Zone5
   */
  public ProjectionInfo getETRS1989PolandCS2000Zone5() {
    return ETRS1989PolandCS2000Zone5.copy();
  }

  /**
   * @return the ETRS1989PolandCS2000Zone6
   */
  public ProjectionInfo getETRS1989PolandCS2000Zone6() {
    return ETRS1989PolandCS2000Zone6.copy();
  }

  /**
   * @return the ETRS1989PolandCS2000Zone7
   */
  public ProjectionInfo getETRS1989PolandCS2000Zone7() {
    return ETRS1989PolandCS2000Zone7.copy();
  }

  /**
   * @return the ETRS1989PolandCS2000Zone8
   */
  public ProjectionInfo getETRS1989PolandCS2000Zone8() {
    return ETRS1989PolandCS2000Zone8.copy();
  }

  /**
   * @return the ETRS1989PolandCS92
   */
  public ProjectionInfo getETRS1989PolandCS92() {
    return ETRS1989PolandCS92.copy();
  }

  /**
   * @return the ETRS1989TM30NE
   */
  public ProjectionInfo getETRS1989TM30NE() {
    return ETRS1989TM30NE.copy();
  }

  /**
   * @return the ETRS1989TMBaltic1993
   */
  public ProjectionInfo getETRS1989TMBaltic1993() {
    return ETRS1989TMBaltic1993.copy();
  }

  /**
   * @return the ETRS1989UWPP1992
   */
  public ProjectionInfo getETRS1989UWPP1992() {
    return ETRS1989UWPP1992.copy();
  }

  /**
   * @return the ETRS1989UWPP2000PAS5
   */
  public ProjectionInfo getETRS1989UWPP2000PAS5() {
    return ETRS1989UWPP2000PAS5.copy();
  }

  /**
   * @return the ETRS1989UWPP2000PAS6
   */
  public ProjectionInfo getETRS1989UWPP2000PAS6() {
    return ETRS1989UWPP2000PAS6.copy();
  }

  /**
   * @return the ETRS1989UWPP2000PAS7
   */
  public ProjectionInfo getETRS1989UWPP2000PAS7() {
    return ETRS1989UWPP2000PAS7.copy();
  }

  /**
   * @return the ETRS1989UWPP2000PAS8
   */
  public ProjectionInfo getETRS1989UWPP2000PAS8() {
    return ETRS1989UWPP2000PAS8.copy();
  }

  /**
   * @return the EUREFFINTM35FIN
   */
  public ProjectionInfo getEUREFFINTM35FIN() {
    return EUREFFINTM35FIN.copy();
  }

  /**
   * @return the EgyptBlueBelt
   */
  public ProjectionInfo getEgyptBlueBelt() {
    return EgyptBlueBelt.copy();
  }

  /**
   * @return the EgyptExtendedPurpleBelt
   */
  public ProjectionInfo getEgyptExtendedPurpleBelt() {
    return EgyptExtendedPurpleBelt.copy();
  }

  /**
   * @return the EgyptPurpleBelt
   */
  public ProjectionInfo getEgyptPurpleBelt() {
    return EgyptPurpleBelt.copy();
  }

  /**
   * @return the EgyptRedBelt
   */
  public ProjectionInfo getEgyptRedBelt() {
    return EgyptRedBelt.copy();
  }

  /**
   * @return the Estonia1997EstoniaNationalGrid
   */
  public ProjectionInfo getEstonia1997EstoniaNationalGrid() {
    return Estonia1997EstoniaNationalGrid.copy();
  }

  /**
   * @return the EstonianCoordinateSystemof1992
   */
  public ProjectionInfo getEstonianCoordinateSystemof1992() {
    return EstonianCoordinateSystemof1992.copy();
  }

  /**
   * @return the FD1958Iraq
   */
  public ProjectionInfo getFD1958Iraq() {
    return FD1958Iraq.copy();
  }

  /**
   * @return the FinlandZone1
   */
  public ProjectionInfo getFinlandZone1() {
    return FinlandZone1.copy();
  }

  /**
   * @return the FinlandZone2
   */
  public ProjectionInfo getFinlandZone2() {
    return FinlandZone2.copy();
  }

  /**
   * @return the FinlandZone3
   */
  public ProjectionInfo getFinlandZone3() {
    return FinlandZone3.copy();
  }

  /**
   * @return the FinlandZone4
   */
  public ProjectionInfo getFinlandZone4() {
    return FinlandZone4.copy();
  }

  /**
   * @return the FranceI
   */
  public ProjectionInfo getFranceI() {
    return FranceI.copy();
  }

  /**
   * @return the FranceII
   */
  public ProjectionInfo getFranceII() {
    return FranceII.copy();
  }

  /**
   * @return the FranceIII
   */
  public ProjectionInfo getFranceIII() {
    return FranceIII.copy();
  }

  /**
   * @return the FranceIV
   */
  public ProjectionInfo getFranceIV() {
    return FranceIV.copy();
  }

  /**
   * @return the GermanyZone1
   */
  public ProjectionInfo getGermanyZone1() {
    return GermanyZone1.copy();
  }

  /**
   * @return the GermanyZone2
   */
  public ProjectionInfo getGermanyZone2() {
    return GermanyZone2.copy();
  }

  /**
   * @return the GermanyZone3
   */
  public ProjectionInfo getGermanyZone3() {
    return GermanyZone3.copy();
  }

  /**
   * @return the GermanyZone4
   */
  public ProjectionInfo getGermanyZone4() {
    return GermanyZone4.copy();
  }

  /**
   * @return the GermanyZone5
   */
  public ProjectionInfo getGermanyZone5() {
    return GermanyZone5.copy();
  }

  /**
   * @return the GhanaMetreGrid
   */
  public ProjectionInfo getGhanaMetreGrid() {
    return GhanaMetreGrid.copy();
  }

  /**
   * @return the GreekGrid
   */
  public ProjectionInfo getGreekGrid() {
    return GreekGrid.copy();
  }

  /**
   * @return the Grenada1953BritishWestIndiesGrid
   */
  public ProjectionInfo getGrenada1953BritishWestIndiesGrid() {
    return Grenada1953BritishWestIndiesGrid.copy();
  }

  /**
   * @return the GuernseyGrid
   */
  public ProjectionInfo getGuernseyGrid() {
    return GuernseyGrid.copy();
  }

  /**
   * @return the GunungSegaraNEIEZ
   */
  public ProjectionInfo getGunungSegaraNEIEZ() {
    return GunungSegaraNEIEZ.copy();
  }

  /**
   * @return the HD1972EgysegesOrszagosVetuleti
   */
  public ProjectionInfo getHD1972EgysegesOrszagosVetuleti() {
    return HD1972EgysegesOrszagosVetuleti.copy();
  }

  /**
   * @return the Hanoi1972GK106NE
   */
  public ProjectionInfo getHanoi1972GK106NE() {
    return Hanoi1972GK106NE.copy();
  }

  /**
   * @return the Helle1954JanMayenGrid
   */
  public ProjectionInfo getHelle1954JanMayenGrid() {
    return Helle1954JanMayenGrid.copy();
  }

  /**
   * @return the HitoXVIII1963Argentina2
   */
  public ProjectionInfo getHitoXVIII1963Argentina2() {
    return HitoXVIII1963Argentina2.copy();
  }

  /**
   * @return the HongKong1980Grid
   */
  public ProjectionInfo getHongKong1980Grid() {
    return HongKong1980Grid.copy();
  }

  /**
   * @return the IRENET95IrishTranverseMercator
   */
  public ProjectionInfo getIRENET95IrishTranverseMercator() {
    return IRENET95IrishTranverseMercator.copy();
  }

  /**
   * @return the ISN1993Lambert1993
   */
  public ProjectionInfo getISN1993Lambert1993() {
    return ISN1993Lambert1993.copy();
  }

  /**
   * @return the Indian1960TM106NE
   */
  public ProjectionInfo getIndian1960TM106NE() {
    return Indian1960TM106NE.copy();
  }

  /**
   * @return the IrishNationalGrid
   */
  public ProjectionInfo getIrishNationalGrid() {
    return IrishNationalGrid.copy();
  }

  /**
   * @return the IsraelTMGrid
   */
  public ProjectionInfo getIsraelTMGrid() {
    return IsraelTMGrid.copy();
  }

  /**
   * @return the Jamaica1875OldGrid
   */
  public ProjectionInfo getJamaica1875OldGrid() {
    return Jamaica1875OldGrid.copy();
  }

  /**
   * @return the JamaicaGrid
   */
  public ProjectionInfo getJamaicaGrid() {
    return JamaicaGrid.copy();
  }

  /**
   * @return the JordanJTM
   */
  public ProjectionInfo getJordanJTM() {
    return JordanJTM.copy();
  }

  /**
   * @return the KOCLambert
   */
  public ProjectionInfo getKOCLambert() {
    return KOCLambert.copy();
  }

  /**
   * @return the KUDAMSKTM
   */
  public ProjectionInfo getKUDAMSKTM() {
    return KUDAMSKTM.copy();
  }

  /**
   * @return the KandawalaCeylonBeltIndianYards1937
   */
  public ProjectionInfo getKandawalaCeylonBeltIndianYards1937() {
    return KandawalaCeylonBeltIndianYards1937.copy();
  }

  /**
   * @return the KandawalaCeylonBeltMeters
   */
  public ProjectionInfo getKandawalaCeylonBeltMeters() {
    return KandawalaCeylonBeltMeters.copy();
  }

  /**
   * @return the KertauRSOMalayaChains
   */
  public ProjectionInfo getKertauRSOMalayaChains() {
    return KertauRSOMalayaChains.copy();
  }

  /**
   * @return the KertauRSOMalayaMeters
   */
  public ProjectionInfo getKertauRSOMalayaMeters() {
    return KertauRSOMalayaMeters.copy();
  }

  /**
   * @return the KertauSingaporeGrid
   */
  public ProjectionInfo getKertauSingaporeGrid() {
    return KertauSingaporeGrid.copy();
  }

  /**
   * @return the Korean1985KoreaCentralBelt
   */
  public ProjectionInfo getKorean1985KoreaCentralBelt() {
    return Korean1985KoreaCentralBelt.copy();
  }

  /**
   * @return the Korean1985KoreaEastBelt
   */
  public ProjectionInfo getKorean1985KoreaEastBelt() {
    return Korean1985KoreaEastBelt.copy();
  }

  /**
   * @return the Korean1985KoreaWestBelt
   */
  public ProjectionInfo getKorean1985KoreaWestBelt() {
    return Korean1985KoreaWestBelt.copy();
  }

  /**
   * @return the KuwaitOilCoLambert
   */
  public ProjectionInfo getKuwaitOilCoLambert() {
    return KuwaitOilCoLambert.copy();
  }

  /**
   * @return the KuwaitUtilityKTM
   */
  public ProjectionInfo getKuwaitUtilityKTM() {
    return KuwaitUtilityKTM.copy();
  }

  /**
   * @return the LakeMaracaiboGrid
   */
  public ProjectionInfo getLakeMaracaiboGrid() {
    return LakeMaracaiboGrid.copy();
  }

  /**
   * @return the LakeMaracaiboGridM1
   */
  public ProjectionInfo getLakeMaracaiboGridM1() {
    return LakeMaracaiboGridM1.copy();
  }

  /**
   * @return the LakeMaracaiboGridM3
   */
  public ProjectionInfo getLakeMaracaiboGridM3() {
    return LakeMaracaiboGridM3.copy();
  }

  /**
   * @return the LakeMaracaiboLaRosaGrid
   */
  public ProjectionInfo getLakeMaracaiboLaRosaGrid() {
    return LakeMaracaiboLaRosaGrid.copy();
  }

  /**
   * @return the LietuvosKoordinaciuSistema
   */
  public ProjectionInfo getLietuvosKoordinaciuSistema() {
    return LietuvosKoordinaciuSistema.copy();
  }

  /**
   * @return the LisboaBesselBonne
   */
  public ProjectionInfo getLisboaBesselBonne() {
    return LisboaBesselBonne.copy();
  }

  /**
   * @return the LisboaHayfordGaussIGeoE
   */
  public ProjectionInfo getLisboaHayfordGaussIGeoE() {
    return LisboaHayfordGaussIGeoE.copy();
  }

  /**
   * @return the LisboaHayfordGaussIPCC
   */
  public ProjectionInfo getLisboaHayfordGaussIPCC() {
    return LisboaHayfordGaussIPCC.copy();
  }

  /**
   * @return the Locodjo1965TM5NW
   */
  public ProjectionInfo getLocodjo1965TM5NW() {
    return Locodjo1965TM5NW.copy();
  }

  /**
   * @return the Luxembourg1930Gauss
   */
  public ProjectionInfo getLuxembourg1930Gauss() {
    return Luxembourg1930Gauss.copy();
  }

  /**
   * @return the MGI3DegreeGaussZone5
   */
  public ProjectionInfo getMGI3DegreeGaussZone5() {
    return MGI3DegreeGaussZone5.copy();
  }

  /**
   * @return the MGI3DegreeGaussZone6
   */
  public ProjectionInfo getMGI3DegreeGaussZone6() {
    return MGI3DegreeGaussZone6.copy();
  }

  /**
   * @return the MGI3DegreeGaussZone7
   */
  public ProjectionInfo getMGI3DegreeGaussZone7() {
    return MGI3DegreeGaussZone7.copy();
  }

  /**
   * @return the MGI3DegreeGaussZone8
   */
  public ProjectionInfo getMGI3DegreeGaussZone8() {
    return MGI3DegreeGaussZone8.copy();
  }

  /**
   * @return the MGIAustriaLambert
   */
  public ProjectionInfo getMGIAustriaLambert() {
    return MGIAustriaLambert.copy();
  }

  /**
   * @return the MGIBalkans5
   */
  public ProjectionInfo getMGIBalkans5() {
    return MGIBalkans5.copy();
  }

  /**
   * @return the MGIBalkans6
   */
  public ProjectionInfo getMGIBalkans6() {
    return MGIBalkans6.copy();
  }

  /**
   * @return the MGIBalkans7
   */
  public ProjectionInfo getMGIBalkans7() {
    return MGIBalkans7.copy();
  }

  /**
   * @return the MGIBalkans8
   */
  public ProjectionInfo getMGIBalkans8() {
    return MGIBalkans8.copy();
  }

  /**
   * @return the MGIM28
   */
  public ProjectionInfo getMGIM28() {
    return MGIM28.copy();
  }

  /**
   * @return the MGIM31
   */
  public ProjectionInfo getMGIM31() {
    return MGIM31.copy();
  }

  /**
   * @return the MGIM34
   */
  public ProjectionInfo getMGIM34() {
    return MGIM34.copy();
  }

  /**
   * @return the MGISloveniaGrid
   */
  public ProjectionInfo getMGISloveniaGrid() {
    return MGISloveniaGrid.copy();
  }

  /**
   * @return the Madrid1870MadridSpain
   */
  public ProjectionInfo getMadrid1870MadridSpain() {
    return Madrid1870MadridSpain.copy();
  }

  /**
   * @return the MakassarNEIEZ
   */
  public ProjectionInfo getMakassarNEIEZ() {
    return MakassarNEIEZ.copy();
  }

  /**
   * @return the MonteMarioItaly1
   */
  public ProjectionInfo getMonteMarioItaly1() {
    return MonteMarioItaly1.copy();
  }

  /**
   * @return the MonteMarioItaly2
   */
  public ProjectionInfo getMonteMarioItaly2() {
    return MonteMarioItaly2.copy();
  }

  /**
   * @return the MonteMarioRomeItaly1
   */
  public ProjectionInfo getMonteMarioRomeItaly1() {
    return MonteMarioRomeItaly1.copy();
  }

  /**
   * @return the MonteMarioRomeItaly2
   */
  public ProjectionInfo getMonteMarioRomeItaly2() {
    return MonteMarioRomeItaly2.copy();
  }

  /**
   * @return the Montserrat1958BritishWestIndiesGrid
   */
  public ProjectionInfo getMontserrat1958BritishWestIndiesGrid() {
    return Montserrat1958BritishWestIndiesGrid.copy();
  }

  /**
   * @return the MountDillonTobagoGrid
   */
  public ProjectionInfo getMountDillonTobagoGrid() {
    return MountDillonTobagoGrid.copy();
  }

  /**
   * @return the NAD1927CubaNorte
   */
  public ProjectionInfo getNAD1927CubaNorte() {
    return NAD1927CubaNorte.copy();
  }

  /**
   * @return the NAD1927CubaSur
   */
  public ProjectionInfo getNAD1927CubaSur() {
    return NAD1927CubaSur.copy();
  }

  /**
   * @return the NAD1927GuatemalaNorte
   */
  public ProjectionInfo getNAD1927GuatemalaNorte() {
    return NAD1927GuatemalaNorte.copy();
  }

  /**
   * @return the NAD1927GuatemalaSur
   */
  public ProjectionInfo getNAD1927GuatemalaSur() {
    return NAD1927GuatemalaSur.copy();
  }

  /**
   * @return the NAD1927MichiganGeoRefMeters
   */
  public ProjectionInfo getNAD1927MichiganGeoRefMeters() {
    return NAD1927MichiganGeoRefMeters.copy();
  }

  /**
   * @return the NAD1927MichiganGeoRefUSfeet
   */
  public ProjectionInfo getNAD1927MichiganGeoRefUSfeet() {
    return NAD1927MichiganGeoRefUSfeet.copy();
  }

  /**
   * @return the NAD1983HARNGuamMapGrid
   */
  public ProjectionInfo getNAD1983HARNGuamMapGrid() {
    return NAD1983HARNGuamMapGrid.copy();
  }

  /**
   * @return the NAD1983MichiganGeoRefMeters
   */
  public ProjectionInfo getNAD1983MichiganGeoRefMeters() {
    return NAD1983MichiganGeoRefMeters.copy();
  }

  /**
   * @return the NAD1983MichiganGeoRefUSfeet
   */
  public ProjectionInfo getNAD1983MichiganGeoRefUSfeet() {
    return NAD1983MichiganGeoRefUSfeet.copy();
  }

  /**
   * @return the NAD1983MichiganGeoReferencedMeters
   */
  public ProjectionInfo getNAD1983MichiganGeoReferencedMeters() {
    return NAD1983MichiganGeoReferencedMeters.copy();
  }

  /**
   * @return the NTFFranceIIIdegrees
   */
  public ProjectionInfo getNTFFranceIIIdegrees() {
    return NTFFranceIIIdegrees.copy();
  }

  /**
   * @return the NTFFranceIIdegrees
   */
  public ProjectionInfo getNTFFranceIIdegrees() {
    return NTFFranceIIdegrees.copy();
  }

  /**
   * @return the NTFFranceIVdegrees
   */
  public ProjectionInfo getNTFFranceIVdegrees() {
    return NTFFranceIVdegrees.copy();
  }

  /**
   * @return the NTFFranceIdegrees
   */
  public ProjectionInfo getNTFFranceIdegrees() {
    return NTFFranceIdegrees.copy();
  }

  /**
   * @return the NewZealandMapGrid
   */
  public ProjectionInfo getNewZealandMapGrid() {
    return NewZealandMapGrid.copy();
  }

  /**
   * @return the NewZealandNorthIsland
   */
  public ProjectionInfo getNewZealandNorthIsland() {
    return NewZealandNorthIsland.copy();
  }

  /**
   * @return the NewZealandSouthIsland
   */
  public ProjectionInfo getNewZealandSouthIsland() {
    return NewZealandSouthIsland.copy();
  }

  /**
   * @return the NigeriaEastBelt
   */
  public ProjectionInfo getNigeriaEastBelt() {
    return NigeriaEastBelt.copy();
  }

  /**
   * @return the NigeriaMidBelt
   */
  public ProjectionInfo getNigeriaMidBelt() {
    return NigeriaMidBelt.copy();
  }

  /**
   * @return the NigeriaWestBelt
   */
  public ProjectionInfo getNigeriaWestBelt() {
    return NigeriaWestBelt.copy();
  }

  /**
   * @return the NordAlgerie
   */
  public ProjectionInfo getNordAlgerie() {
    return NordAlgerie.copy();
  }

  /**
   * @return the NordAlgerieAnciennedegrees
   */
  public ProjectionInfo getNordAlgerieAnciennedegrees() {
    return NordAlgerieAnciennedegrees.copy();
  }

  /**
   * @return the NordAlgerieancienne
   */
  public ProjectionInfo getNordAlgerieancienne() {
    return NordAlgerieancienne.copy();
  }

  /**
   * @return the NordAlgeriedegrees
   */
  public ProjectionInfo getNordAlgeriedegrees() {
    return NordAlgeriedegrees.copy();
  }

  /**
   * @return the NordFrance
   */
  public ProjectionInfo getNordFrance() {
    return NordFrance.copy();
  }

  /**
   * @return the NordMaroc
   */
  public ProjectionInfo getNordMaroc() {
    return NordMaroc.copy();
  }

  /**
   * @return the NordMarocdegrees
   */
  public ProjectionInfo getNordMarocdegrees() {
    return NordMarocdegrees.copy();
  }

  /**
   * @return the NordTunisie
   */
  public ProjectionInfo getNordTunisie() {
    return NordTunisie.copy();
  }

  /**
   * @return the NorddeGuerre
   */
  public ProjectionInfo getNorddeGuerre() {
    return NorddeGuerre.copy();
  }

  /**
   * @return the OSNI1952IrishNationalGrid
   */
  public ProjectionInfo getOSNI1952IrishNationalGrid() {
    return OSNI1952IrishNationalGrid.copy();
  }

  /**
   * @return the ObservatorioMeteorologico1965MacauGrid
   */
  public ProjectionInfo getObservatorioMeteorologico1965MacauGrid() {
    return ObservatorioMeteorologico1965MacauGrid.copy();
  }

  /**
   * @return the PSAD1956ICNRegional
   */
  public ProjectionInfo getPSAD1956ICNRegional() {
    return PSAD1956ICNRegional.copy();
  }

  /**
   * @return the Palestine1923IsraelCSGrid
   */
  public ProjectionInfo getPalestine1923IsraelCSGrid() {
    return Palestine1923IsraelCSGrid.copy();
  }

  /**
   * @return the Palestine1923PalestineBelt
   */
  public ProjectionInfo getPalestine1923PalestineBelt() {
    return Palestine1923PalestineBelt.copy();
  }

  /**
   * @return the Palestine1923PalestineGrid
   */
  public ProjectionInfo getPalestine1923PalestineGrid() {
    return Palestine1923PalestineGrid.copy();
  }

  /**
   * @return the PampadelCastilloArgentina2
   */
  public ProjectionInfo getPampadelCastilloArgentina2() {
    return PampadelCastilloArgentina2.copy();
  }

  /**
   * @return the PeruCentralZone
   */
  public ProjectionInfo getPeruCentralZone() {
    return PeruCentralZone.copy();
  }

  /**
   * @return the PeruEastZone
   */
  public ProjectionInfo getPeruEastZone() {
    return PeruEastZone.copy();
  }

  /**
   * @return the PeruWestZone
   */
  public ProjectionInfo getPeruWestZone() {
    return PeruWestZone.copy();
  }

  /**
   * @return the PhilippinesZoneI
   */
  public ProjectionInfo getPhilippinesZoneI() {
    return PhilippinesZoneI.copy();
  }

  /**
   * @return the PhilippinesZoneII
   */
  public ProjectionInfo getPhilippinesZoneII() {
    return PhilippinesZoneII.copy();
  }

  /**
   * @return the PhilippinesZoneIII
   */
  public ProjectionInfo getPhilippinesZoneIII() {
    return PhilippinesZoneIII.copy();
  }

  /**
   * @return the PhilippinesZoneIV
   */
  public ProjectionInfo getPhilippinesZoneIV() {
    return PhilippinesZoneIV.copy();
  }

  /**
   * @return the PhilippinesZoneV
   */
  public ProjectionInfo getPhilippinesZoneV() {
    return PhilippinesZoneV.copy();
  }

  /**
   * @return the PitondesNeigesTMReunion
   */
  public ProjectionInfo getPitondesNeigesTMReunion() {
    return PitondesNeigesTMReunion.copy();
  }

  /**
   * @return the PortugueseNationalGrid
   */
  public ProjectionInfo getPortugueseNationalGrid() {
    return PortugueseNationalGrid.copy();
  }

  /**
   * @return the Qatar1948QatarGrid
   */
  public ProjectionInfo getQatar1948QatarGrid() {
    return Qatar1948QatarGrid.copy();
  }

  /**
   * @return the QatarNationalGrid
   */
  public ProjectionInfo getQatarNationalGrid() {
    return QatarNationalGrid.copy();
  }

  /**
   * @return the RGF1993Lambert93
   */
  public ProjectionInfo getRGF1993Lambert93() {
    return RGF1993Lambert93.copy();
  }

  /**
   * @return the RGNC1991LambertNewCaledonia
   */
  public ProjectionInfo getRGNC1991LambertNewCaledonia() {
    return RGNC1991LambertNewCaledonia.copy();
  }

  /**
   * @return the RT9025gonWest
   */
  public ProjectionInfo getRT9025gonWest() {
    return RT9025gonWest.copy();
  }

  /**
   * @return the RassadiranNakhleTaqi
   */
  public ProjectionInfo getRassadiranNakhleTaqi() {
    return RassadiranNakhleTaqi.copy();
  }

  /**
   * @return the Roma1940GaussBoagaEst
   */
  public ProjectionInfo getRoma1940GaussBoagaEst() {
    return Roma1940GaussBoagaEst.copy();
  }

  /**
   * @return the Roma1940GaussBoagaOvest
   */
  public ProjectionInfo getRoma1940GaussBoagaOvest() {
    return Roma1940GaussBoagaOvest.copy();
  }

  /**
   * @return the SAD1969BrazilPolyconic
   */
  public ProjectionInfo getSAD1969BrazilPolyconic() {
    return SAD1969BrazilPolyconic.copy();
  }

  /**
   * @return the SJTSKFerroKrovak
   */
  public ProjectionInfo getSJTSKFerroKrovak() {
    return SJTSKFerroKrovak.copy();
  }

  /**
   * @return the SJTSKFerroKrovakEastNorth
   */
  public ProjectionInfo getSJTSKFerroKrovakEastNorth() {
    return SJTSKFerroKrovakEastNorth.copy();
  }

  /**
   * @return the SJTSKKrovak
   */
  public ProjectionInfo getSJTSKKrovak() {
    return SJTSKKrovak.copy();
  }

  /**
   * @return the SJTSKKrovakEastNorth
   */
  public ProjectionInfo getSJTSKKrovakEastNorth() {
    return SJTSKKrovakEastNorth.copy();
  }

  /**
   * @return the Sahara
   */
  public ProjectionInfo getSahara() {
    return Sahara.copy();
  }

  /**
   * @return the Saharadegrees
   */
  public ProjectionInfo getSaharadegrees() {
    return Saharadegrees.copy();
  }

  /**
   * @return the SierraLeone1924NewColonyGrid
   */
  public ProjectionInfo getSierraLeone1924NewColonyGrid() {
    return SierraLeone1924NewColonyGrid.copy();
  }

  /**
   * @return the SierraLeone1924NewWarOfficeGrid
   */
  public ProjectionInfo getSierraLeone1924NewWarOfficeGrid() {
    return SierraLeone1924NewWarOfficeGrid.copy();
  }

  /**
   * @return the StKitts1955BritishWestIndiesGrid
   */
  public ProjectionInfo getStKitts1955BritishWestIndiesGrid() {
    return StKitts1955BritishWestIndiesGrid.copy();
  }

  /**
   * @return the StLucia1955BritishWestIndiesGrid
   */
  public ProjectionInfo getStLucia1955BritishWestIndiesGrid() {
    return StLucia1955BritishWestIndiesGrid.copy();
  }

  /**
   * @return the StVincent1945BritishWestIndiesGrid
   */
  public ProjectionInfo getStVincent1945BritishWestIndiesGrid() {
    return StVincent1945BritishWestIndiesGrid.copy();
  }

  /**
   * @return the SudAlgerie
   */
  public ProjectionInfo getSudAlgerie() {
    return SudAlgerie.copy();
  }

  /**
   * @return the SudAlgerieAncienneDegree
   */
  public ProjectionInfo getSudAlgerieAncienneDegree() {
    return SudAlgerieAncienneDegree.copy();
  }

  /**
   * @return the SudAlgeriedegrees
   */
  public ProjectionInfo getSudAlgeriedegrees() {
    return SudAlgeriedegrees.copy();
  }

  /**
   * @return the SudFrance
   */
  public ProjectionInfo getSudFrance() {
    return SudFrance.copy();
  }

  /**
   * @return the SudMaroc
   */
  public ProjectionInfo getSudMaroc() {
    return SudMaroc.copy();
  }

  /**
   * @return the SudMarocdegrees
   */
  public ProjectionInfo getSudMarocdegrees() {
    return SudMarocdegrees.copy();
  }

  /**
   * @return the SudTunisie
   */
  public ProjectionInfo getSudTunisie() {
    return SudTunisie.copy();
  }

  /**
   * @return the SwedishNationalGrid
   */
  public ProjectionInfo getSwedishNationalGrid() {
    return SwedishNationalGrid.copy();
  }

  /**
   * @return the TM75IrishGrid
   */
  public ProjectionInfo getTM75IrishGrid() {
    return TM75IrishGrid.copy();
  }

  /**
   * @return the Timbalai1948RSOBorneoChains
   */
  public ProjectionInfo getTimbalai1948RSOBorneoChains() {
    return Timbalai1948RSOBorneoChains.copy();
  }

  /**
   * @return the Timbalai1948RSOBorneoFeet
   */
  public ProjectionInfo getTimbalai1948RSOBorneoFeet() {
    return Timbalai1948RSOBorneoFeet.copy();
  }

  /**
   * @return the Timbalai1948RSOBorneoMeters
   */
  public ProjectionInfo getTimbalai1948RSOBorneoMeters() {
    return Timbalai1948RSOBorneoMeters.copy();
  }

  /**
   * @return the Trinidad1903TrinidadGrid
   */
  public ProjectionInfo getTrinidad1903TrinidadGrid() {
    return Trinidad1903TrinidadGrid.copy();
  }

  /**
   * @return the Trinidad1903TrinidadGridFeetClarke
   */
  public ProjectionInfo getTrinidad1903TrinidadGridFeetClarke() {
    return Trinidad1903TrinidadGridFeetClarke.copy();
  }

  /**
   * @return the UWPP1992
   */
  public ProjectionInfo getUWPP1992() {
    return UWPP1992.copy();
  }

  /**
   * @return the UWPP2000pas5
   */
  public ProjectionInfo getUWPP2000pas5() {
    return UWPP2000pas5.copy();
  }

  /**
   * @return the UWPP2000pas6
   */
  public ProjectionInfo getUWPP2000pas6() {
    return UWPP2000pas6.copy();
  }

  /**
   * @return the UWPP2000pas7
   */
  public ProjectionInfo getUWPP2000pas7() {
    return UWPP2000pas7.copy();
  }

  /**
   * @return the UWPP2000pas8
   */
  public ProjectionInfo getUWPP2000pas8() {
    return UWPP2000pas8.copy();
  }

  /**
   * @return the WGS1972TM106NE
   */
  public ProjectionInfo getWGS1972TM106NE() {
    return WGS1972TM106NE.copy();
  }

  /**
   * @return the WGS1984TM116SE
   */
  public ProjectionInfo getWGS1984TM116SE() {
    return WGS1984TM116SE.copy();
  }

  /**
   * @return the WGS1984TM132SE
   */
  public ProjectionInfo getWGS1984TM132SE() {
    return WGS1984TM132SE.copy();
  }

  /**
   * @return the WGS1984TM36SE
   */
  public ProjectionInfo getWGS1984TM36SE() {
    return WGS1984TM36SE.copy();
  }

  /**
   * @return the WGS1984TM6NE
   */
  public ProjectionInfo getWGS1984TM6NE() {
    return WGS1984TM6NE.copy();
  }

  /**
   * @return the ZanderijSurinameOldTM
   */
  public ProjectionInfo getZanderijSurinameOldTM() {
    return ZanderijSurinameOldTM.copy();
  }

  /**
   * @return the ZanderijSurinameTM
   */
  public ProjectionInfo getZanderijSurinameTM() {
    return ZanderijSurinameTM.copy();
  }

  /**
   * @return the ZanderijTM54NW
   */
  public ProjectionInfo getZanderijTM54NW() {
    return ZanderijTM54NW.copy();
  }

  // </editor-fold>
}
