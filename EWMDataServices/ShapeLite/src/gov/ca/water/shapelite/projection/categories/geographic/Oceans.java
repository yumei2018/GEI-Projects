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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:15:48 PM
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
/// Oceans
/// </summary>

public class Oceans extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo AlaskanIslands;
  private final ProjectionInfo AmericanSamoa1962;
  private final ProjectionInfo Anguilla1957;
  private final ProjectionInfo Anna1Astro1965;
  private final ProjectionInfo Antigua1943;
  private final ProjectionInfo AscensionIsland1958;
  private final ProjectionInfo AstroBeaconE1945;
  private final ProjectionInfo AstroDOS714;
  private final ProjectionInfo AstronomicalStation1952;
  private final ProjectionInfo AzoresCentral1948;
  private final ProjectionInfo AzoresCentral1995;
  private final ProjectionInfo AzoresOccidental1939;
  private final ProjectionInfo AzoresOriental1940;
  private final ProjectionInfo AzoresOriental1995;
  private final ProjectionInfo BabSouth;
  private final ProjectionInfo Barbados;
  private final ProjectionInfo Barbados1938;
  private final ProjectionInfo BellevueIGN;
  private final ProjectionInfo Bermuda1957;
  private final ProjectionInfo Bermuda2000;
  private final ProjectionInfo CSG1967;
  private final ProjectionInfo CantonAstro1966;
  private final ProjectionInfo ChathamIslandAstro1971;
  private final ProjectionInfo Combani1950;
  private final ProjectionInfo DOS1968;
  private final ProjectionInfo Dominica1945;
  private final ProjectionInfo EasterIsland1967;
  private final ProjectionInfo FortDesaix;
  private final ProjectionInfo FortMarigot;
  private final ProjectionInfo FortThomas1955;
  private final ProjectionInfo GUX1Astro;
  private final ProjectionInfo Gan1970;
  private final ProjectionInfo GraciosaBaseSW1948;
  private final ProjectionInfo GrandComoros;
  private final ProjectionInfo Grenada1953;
  private final ProjectionInfo Guam1963;
  private final ProjectionInfo Hjorsey1955;
  private final ProjectionInfo IGN53Mare;
  private final ProjectionInfo IGN56Lifou;
  private final ProjectionInfo IGN72GrandeTerre;
  private final ProjectionInfo IGN72NukuHiva;
  private final ProjectionInfo ISTS061Astro1968;
  private final ProjectionInfo ISTS073Astro1969;
  private final ProjectionInfo Jamaica1875;
  private final ProjectionInfo Jamaica1969;
  private final ProjectionInfo JohnstonIsland1961;
  private final ProjectionInfo K01949;
  private final ProjectionInfo KerguelenIsland1949;
  private final ProjectionInfo KusaieAstro1951;
  private final ProjectionInfo LC5Astro1961;
  private final ProjectionInfo MOP78;
  private final ProjectionInfo Madeira1936;
  private final ProjectionInfo Mahe1971;
  private final ProjectionInfo Majuro;
  private final ProjectionInfo MidwayAstro1961;
  private final ProjectionInfo Montserrat1958;
  private final ProjectionInfo NEA74Noumea;
  private final ProjectionInfo ObservMeteorologico1939;
  private final ProjectionInfo OldHawaiian;
  private final ProjectionInfo PicodeLasNieves;
  private final ProjectionInfo PitcairnAstro1967;
  private final ProjectionInfo PitondesNeiges;
  private final ProjectionInfo Pohnpei;
  private final ProjectionInfo PortoSanto1936;
  private final ProjectionInfo PortoSanto1995;
  private final ProjectionInfo PuertoRico;
  private final ProjectionInfo RGFG1995;
  private final ProjectionInfo RGNC1991;
  private final ProjectionInfo RGR1992;
  private final ProjectionInfo RRAF1991;
  private final ProjectionInfo Reunion;
  private final ProjectionInfo ST71Belep;
  private final ProjectionInfo ST84IledesPins;
  private final ProjectionInfo ST87Ouvea;
  private final ProjectionInfo SaintPierreetMiquelon1950;
  private final ProjectionInfo SainteAnne;
  private final ProjectionInfo SantoDOS1965;
  private final ProjectionInfo SaoBraz;
  private final ProjectionInfo SapperHill1943;
  private final ProjectionInfo SelvagemGrande1938;
  private final ProjectionInfo StKitts1955;
  private final ProjectionInfo StLucia1955;
  private final ProjectionInfo StVincent1945;
  private final ProjectionInfo Tahaa;
  private final ProjectionInfo Tahiti;
  private final ProjectionInfo TernIslandAstro1961;
  private final ProjectionInfo TristanAstro1968;
  private final ProjectionInfo VitiLevu1916;
  private final ProjectionInfo WakeEniwetok1960;
  private final ProjectionInfo WakeIslandAstro1952;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of Oceans
   */
  public Oceans() {
    AlaskanIslands = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    AmericanSamoa1962 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Anguilla1957 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Anna1Astro1965 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=aust_SA +no_defs ").orElse(null);
    Antigua1943 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    AscensionIsland1958 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    AstroBeaconE1945 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    AstroDOS714 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    AstronomicalStation1952 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    AzoresCentral1948 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    AzoresCentral1995 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    AzoresOccidental1939 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    AzoresOriental1940 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    AzoresOriental1995 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    BabSouth = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Barbados = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Barbados1938 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    BellevueIGN = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Bermuda1957 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Bermuda2000 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    CantonAstro1966 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    ChathamIslandAstro1971 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Combani1950 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    CSG1967 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Dominica1945 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    DOS1968 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    EasterIsland1967 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    FortDesaix = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    FortMarigot = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    FortThomas1955 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Gan1970 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    GraciosaBaseSW1948 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    GrandComoros = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Grenada1953 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Guam1963 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    GUX1Astro = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Hjorsey1955 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    IGN53Mare = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    IGN56Lifou = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    IGN72GrandeTerre = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    IGN72NukuHiva = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    ISTS061Astro1968 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    ISTS073Astro1969 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Jamaica1875 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378249.138 +b=6356514.959419348 +no_defs ").orElse(null);
    Jamaica1969 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    JohnstonIsland1961 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    K01949 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    KerguelenIsland1949 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    KusaieAstro1951 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    LC5Astro1961 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Madeira1936 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Mahe1971 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Majuro = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    MidwayAstro1961 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Montserrat1958 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    MOP78 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    NEA74Noumea = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    ObservMeteorologico1939 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    OldHawaiian = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    PicodeLasNieves = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    PitcairnAstro1967 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    PitondesNeiges = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Pohnpei = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    PortoSanto1936 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    PortoSanto1995 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    PuertoRico = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Reunion = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    RGFG1995 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    RGNC1991 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    RGR1992 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    RRAF1991 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    SaintPierreetMiquelon1950 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    SainteAnne = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    SantoDOS1965 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    SaoBraz = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    SapperHill1943 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    SelvagemGrande1938 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    StKitts1955 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    StLucia1955 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    StVincent1945 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    ST71Belep = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    ST84IledesPins = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    ST87Ouvea = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Tahaa = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Tahiti = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    TernIslandAstro1961 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    TristanAstro1968 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    VitiLevu1916 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    WakeIslandAstro1952 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    WakeEniwetok1960 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378270 +b=6356794.343434343 +no_defs ").orElse(null);

    AlaskanIslands.setLatLon(true);
    AmericanSamoa1962.setLatLon(true);
    Anguilla1957.setLatLon(true);
    Anna1Astro1965.setLatLon(true);
    Antigua1943.setLatLon(true);
    AscensionIsland1958.setLatLon(true);
    AstroBeaconE1945.setLatLon(true);
    AstroDOS714.setLatLon(true);
    AstronomicalStation1952.setLatLon(true);
    AzoresCentral1948.setLatLon(true);
    AzoresCentral1995.setLatLon(true);
    AzoresOccidental1939.setLatLon(true);
    AzoresOriental1940.setLatLon(true);
    AzoresOriental1995.setLatLon(true);
    BabSouth.setLatLon(true);
    Barbados.setLatLon(true);
    Barbados1938.setLatLon(true);
    BellevueIGN.setLatLon(true);
    Bermuda1957.setLatLon(true);
    Bermuda2000.setLatLon(true);
    CantonAstro1966.setLatLon(true);
    ChathamIslandAstro1971.setLatLon(true);
    Combani1950.setLatLon(true);
    CSG1967.setLatLon(true);
    Dominica1945.setLatLon(true);
    DOS1968.setLatLon(true);
    EasterIsland1967.setLatLon(true);
    FortDesaix.setLatLon(true);
    FortMarigot.setLatLon(true);
    FortThomas1955.setLatLon(true);
    Gan1970.setLatLon(true);
    GraciosaBaseSW1948.setLatLon(true);
    GrandComoros.setLatLon(true);
    Grenada1953.setLatLon(true);
    Guam1963.setLatLon(true);
    GUX1Astro.setLatLon(true);
    Hjorsey1955.setLatLon(true);
    IGN53Mare.setLatLon(true);
    IGN56Lifou.setLatLon(true);
    IGN72GrandeTerre.setLatLon(true);
    IGN72NukuHiva.setLatLon(true);
    ISTS061Astro1968.setLatLon(true);
    ISTS073Astro1969.setLatLon(true);
    Jamaica1875.setLatLon(true);
    Jamaica1969.setLatLon(true);
    JohnstonIsland1961.setLatLon(true);
    K01949.setLatLon(true);
    KerguelenIsland1949.setLatLon(true);
    KusaieAstro1951.setLatLon(true);
    LC5Astro1961.setLatLon(true);
    Madeira1936.setLatLon(true);
    Mahe1971.setLatLon(true);
    Majuro.setLatLon(true);
    MidwayAstro1961.setLatLon(true);
    Montserrat1958.setLatLon(true);
    MOP78.setLatLon(true);
    NEA74Noumea.setLatLon(true);
    ObservMeteorologico1939.setLatLon(true);
    OldHawaiian.setLatLon(true);
    PicodeLasNieves.setLatLon(true);
    PitcairnAstro1967.setLatLon(true);
    PitondesNeiges.setLatLon(true);
    Pohnpei.setLatLon(true);
    PortoSanto1936.setLatLon(true);
    PortoSanto1995.setLatLon(true);
    PuertoRico.setLatLon(true);
    Reunion.setLatLon(true);
    RGFG1995.setLatLon(true);
    RGNC1991.setLatLon(true);
    RGR1992.setLatLon(true);
    RRAF1991.setLatLon(true);
    SaintPierreetMiquelon1950.setLatLon(true);
    SainteAnne.setLatLon(true);
    SantoDOS1965.setLatLon(true);
    SaoBraz.setLatLon(true);
    SapperHill1943.setLatLon(true);
    SelvagemGrande1938.setLatLon(true);
    StKitts1955.setLatLon(true);
    StLucia1955.setLatLon(true);
    StVincent1945.setLatLon(true);
    ST71Belep.setLatLon(true);
    ST84IledesPins.setLatLon(true);
    ST87Ouvea.setLatLon(true);
    Tahaa.setLatLon(true);
    Tahiti.setLatLon(true);
    TernIslandAstro1961.setLatLon(true);
    TristanAstro1968.setLatLon(true);
    VitiLevu1916.setLatLon(true);
    WakeIslandAstro1952.setLatLon(true);
    WakeEniwetok1960.setLatLon(true);

    AlaskanIslands.getGeographicInfo().setName("GCS_Alaskan_Islands");
    AmericanSamoa1962.getGeographicInfo().setName("GCS_American_Samoa_1962");
    Anguilla1957.getGeographicInfo().setName("GCS_Anguilla_1957");
    Anna1Astro1965.getGeographicInfo().setName("GCS_Anna_1_1965");
    Antigua1943.getGeographicInfo().setName("GCS_Antigua_1943");
    AscensionIsland1958.getGeographicInfo().setName("GCS_Ascension_Island_1958");
    AstroBeaconE1945.getGeographicInfo().setName("GCS_Beacon_E_1945");
    AstroDOS714.getGeographicInfo().setName("GCS_DOS_71_4");
    AstronomicalStation1952.getGeographicInfo().setName("GCS_Astro_1952");
    AzoresCentral1948.getGeographicInfo().setName("GCS_Azores_Central_1948");
    AzoresCentral1995.getGeographicInfo().setName("GCS_Azores_Central_1995");
    AzoresOccidental1939.getGeographicInfo().setName("GCS_Azores_Occidental_1939");
    AzoresOriental1940.getGeographicInfo().setName("GCS_Azores_Oriental_1940");
    AzoresOriental1995.getGeographicInfo().setName("GCS_Azores_Oriental_1995");
    BabSouth.getGeographicInfo().setName("GCS_Bab_South");
    Barbados.getGeographicInfo().setName("GCS_Barbados");
    Barbados1938.getGeographicInfo().setName("GCS_Barbados_1938");
    BellevueIGN.getGeographicInfo().setName("GCS_Bellevue_IGN");
    Bermuda1957.getGeographicInfo().setName("GCS_Bermuda_1957");
    Bermuda2000.getGeographicInfo().setName("GCS_Bermuda_2000");
    CantonAstro1966.getGeographicInfo().setName("GCS_Canton_1966");
    ChathamIslandAstro1971.getGeographicInfo().setName("GCS_Chatham_Island_1971");
    Combani1950.getGeographicInfo().setName("GCS_Combani_1950");
    CSG1967.getGeographicInfo().setName("GCS_CSG_1967");
    Dominica1945.getGeographicInfo().setName("GCS_Dominica_1945");
    DOS1968.getGeographicInfo().setName("GCS_DOS_1968");
    EasterIsland1967.getGeographicInfo().setName("GCS_Easter_Island_1967");
    FortDesaix.getGeographicInfo().setName("GCS_Fort_Desaix");
    FortMarigot.getGeographicInfo().setName("GCS_Fort_Marigot");
    FortThomas1955.getGeographicInfo().setName("GCS_Fort_Thomas_1955");
    Gan1970.getGeographicInfo().setName("GCS_Gan_1970");
    GraciosaBaseSW1948.getGeographicInfo().setName("GCS_Graciosa_Base_SW_1948");
    GrandComoros.getGeographicInfo().setName("GCS_Grand_Comoros");
    Grenada1953.getGeographicInfo().setName("GCS_Grenada_1953");
    Guam1963.getGeographicInfo().setName("GCS_Guam_1963");
    GUX1Astro.getGeographicInfo().setName("GCS_GUX_1");
    Hjorsey1955.getGeographicInfo().setName("GCS_Hjorsey_1955");
    IGN53Mare.getGeographicInfo().setName("GCS_IGN53_Mare");
    IGN56Lifou.getGeographicInfo().setName("GCS_IGN56_Lifou");
    IGN72GrandeTerre.getGeographicInfo().setName("GCS_IGN72_Grande_Terre");
    IGN72NukuHiva.getGeographicInfo().setName("GCS_IGN72_Nuku_Hiva");
    ISTS061Astro1968.getGeographicInfo().setName("GCS_ISTS_061_1968");
    ISTS073Astro1969.getGeographicInfo().setName("GCS_ISTS_073_1969");
    Jamaica1875.getGeographicInfo().setName("GCS_Jamaica_1875");
    Jamaica1969.getGeographicInfo().setName("GCS_Jamaica_1969");
    JohnstonIsland1961.getGeographicInfo().setName("GCS_Johnston_Island_1961");
    K01949.getGeographicInfo().setName("GCS_K0_1949");
    KerguelenIsland1949.getGeographicInfo().setName("GCS_Kerguelen_Island_1949");
    KusaieAstro1951.getGeographicInfo().setName("GCS_Kusaie_1951");
    LC5Astro1961.getGeographicInfo().setName("GCS_LC5_1961");
    Madeira1936.getGeographicInfo().setName("GCS_Madeira_1936");
    Mahe1971.getGeographicInfo().setName("GCS_Mahe_1971");
    Majuro.getGeographicInfo().setName("GCS_Majuro");
    MidwayAstro1961.getGeographicInfo().setName("GCS_Midway_1961");
    Montserrat1958.getGeographicInfo().setName("GCS_Montserrat_1958");
    MOP78.getGeographicInfo().setName("GCS_MOP78");
    NEA74Noumea.getGeographicInfo().setName("GCS_NEA74_Noumea");
    ObservMeteorologico1939.getGeographicInfo().setName("GCS_Observ_Meteorologico_1939");
    OldHawaiian.getGeographicInfo().setName("GCS_Old_Hawaiian");
    PicodeLasNieves.getGeographicInfo().setName("GCS_Pico_de_Las_Nieves");
    PitcairnAstro1967.getGeographicInfo().setName("GCS_Pitcairn_1967");
    PitondesNeiges.getGeographicInfo().setName("GCS_Piton_des_Neiges");
    Pohnpei.getGeographicInfo().setName("GCS_Pohnpei");
    PortoSanto1936.getGeographicInfo().setName("GCS_Porto_Santo_1936");
    PortoSanto1995.getGeographicInfo().setName("GCS_Porto_Santo_1995");
    PuertoRico.getGeographicInfo().setName("GCS_Puerto_Rico");
    Reunion.getGeographicInfo().setName("GCS_Reunion");
    RGFG1995.getGeographicInfo().setName("GCS_RGFG_1995");
    RGNC1991.getGeographicInfo().setName("GCS_RGNC_1991");
    RGR1992.getGeographicInfo().setName("GCS_RGR_1992");
    RRAF1991.getGeographicInfo().setName("GCS_RRAF_1991");
    SaintPierreetMiquelon1950.getGeographicInfo().setName("GCS_Saint_Pierre_et_Miquelon_1950");
    SainteAnne.getGeographicInfo().setName("GCS_Sainte_Anne");
    SantoDOS1965.getGeographicInfo().setName("GCS_Santo_DOS_1965");
    SaoBraz.getGeographicInfo().setName("GCS_Sao_Braz");
    SapperHill1943.getGeographicInfo().setName("GCS_Sapper_Hill_1943");
    SelvagemGrande1938.getGeographicInfo().setName("GCS_Selvagem_Grande_1938");
    StKitts1955.getGeographicInfo().setName("GCS_St_Kitts_1955");
    StLucia1955.getGeographicInfo().setName("GCS_St_Lucia_1955");
    StVincent1945.getGeographicInfo().setName("GCS_St_Vincent_1945");
    ST71Belep.getGeographicInfo().setName("GCS_ST71_Belep");
    ST84IledesPins.getGeographicInfo().setName("GCS_ST84_Ile_des_Pins");
    ST87Ouvea.getGeographicInfo().setName("GCS_ST87_Ouvea");
    Tahaa.getGeographicInfo().setName("GCS_Tahaa");
    Tahiti.getGeographicInfo().setName("GCS_Tahiti");
    TernIslandAstro1961.getGeographicInfo().setName("GCS_Tern_Island_1961");
    TristanAstro1968.getGeographicInfo().setName("GCS_Tristan_1968");
    VitiLevu1916.getGeographicInfo().setName("GCS_Viti_Levu_1916");
    WakeIslandAstro1952.getGeographicInfo().setName("GCS_Wake_Island_1952");
    WakeEniwetok1960.getGeographicInfo().setName("GCS_Wake_Eniwetok_1960");

    AlaskanIslands.setName("GCS_Alaskan_Islands");
    AmericanSamoa1962.setName("GCS_American_Samoa_1962");
    Anguilla1957.setName("GCS_Anguilla_1957");
    Anna1Astro1965.setName("GCS_Anna_1_1965");
    Antigua1943.setName("GCS_Antigua_1943");
    AscensionIsland1958.setName("GCS_Ascension_Island_1958");
    AstroBeaconE1945.setName("GCS_Beacon_E_1945");
    AstroDOS714.setName("GCS_DOS_71_4");
    AstronomicalStation1952.setName("GCS_Astro_1952");
    AzoresCentral1948.setName("GCS_Azores_Central_1948");
    AzoresCentral1995.setName("GCS_Azores_Central_1995");
    AzoresOccidental1939.setName("GCS_Azores_Occidental_1939");
    AzoresOriental1940.setName("GCS_Azores_Oriental_1940");
    AzoresOriental1995.setName("GCS_Azores_Oriental_1995");
    BabSouth.setName("GCS_Bab_South");
    Barbados.setName("GCS_Barbados");
    Barbados1938.setName("GCS_Barbados_1938");
    BellevueIGN.setName("GCS_Bellevue_IGN");
    Bermuda1957.setName("GCS_Bermuda_1957");
    Bermuda2000.setName("GCS_Bermuda_2000");
    CantonAstro1966.setName("GCS_Canton_1966");
    ChathamIslandAstro1971.setName("GCS_Chatham_Island_1971");
    Combani1950.setName("GCS_Combani_1950");
    CSG1967.setName("GCS_CSG_1967");
    Dominica1945.setName("GCS_Dominica_1945");
    DOS1968.setName("GCS_DOS_1968");
    EasterIsland1967.setName("GCS_Easter_Island_1967");
    FortDesaix.setName("GCS_Fort_Desaix");
    FortMarigot.setName("GCS_Fort_Marigot");
    FortThomas1955.setName("GCS_Fort_Thomas_1955");
    Gan1970.setName("GCS_Gan_1970");
    GraciosaBaseSW1948.setName("GCS_Graciosa_Base_SW_1948");
    GrandComoros.setName("GCS_Grand_Comoros");
    Grenada1953.setName("GCS_Grenada_1953");
    Guam1963.setName("GCS_Guam_1963");
    GUX1Astro.setName("GCS_GUX_1");
    Hjorsey1955.setName("GCS_Hjorsey_1955");
    IGN53Mare.setName("GCS_IGN53_Mare");
    IGN56Lifou.setName("GCS_IGN56_Lifou");
    IGN72GrandeTerre.setName("GCS_IGN72_Grande_Terre");
    IGN72NukuHiva.setName("GCS_IGN72_Nuku_Hiva");
    ISTS061Astro1968.setName("GCS_ISTS_061_1968");
    ISTS073Astro1969.setName("GCS_ISTS_073_1969");
    Jamaica1875.setName("GCS_Jamaica_1875");
    Jamaica1969.setName("GCS_Jamaica_1969");
    JohnstonIsland1961.setName("GCS_Johnston_Island_1961");
    K01949.setName("GCS_K0_1949");
    KerguelenIsland1949.setName("GCS_Kerguelen_Island_1949");
    KusaieAstro1951.setName("GCS_Kusaie_1951");
    LC5Astro1961.setName("GCS_LC5_1961");
    Madeira1936.setName("GCS_Madeira_1936");
    Mahe1971.setName("GCS_Mahe_1971");
    Majuro.setName("GCS_Majuro");
    MidwayAstro1961.setName("GCS_Midway_1961");
    Montserrat1958.setName("GCS_Montserrat_1958");
    MOP78.setName("GCS_MOP78");
    NEA74Noumea.setName("GCS_NEA74_Noumea");
    ObservMeteorologico1939.setName("GCS_Observ_Meteorologico_1939");
    OldHawaiian.setName("GCS_Old_Hawaiian");
    PicodeLasNieves.setName("GCS_Pico_de_Las_Nieves");
    PitcairnAstro1967.setName("GCS_Pitcairn_1967");
    PitondesNeiges.setName("GCS_Piton_des_Neiges");
    Pohnpei.setName("GCS_Pohnpei");
    PortoSanto1936.setName("GCS_Porto_Santo_1936");
    PortoSanto1995.setName("GCS_Porto_Santo_1995");
    PuertoRico.setName("GCS_Puerto_Rico");
    Reunion.setName("GCS_Reunion");
    RGFG1995.setName("GCS_RGFG_1995");
    RGNC1991.setName("GCS_RGNC_1991");
    RGR1992.setName("GCS_RGR_1992");
    RRAF1991.setName("GCS_RRAF_1991");
    SaintPierreetMiquelon1950.setName("GCS_Saint_Pierre_et_Miquelon_1950");
    SainteAnne.setName("GCS_Sainte_Anne");
    SantoDOS1965.setName("GCS_Santo_DOS_1965");
    SaoBraz.setName("GCS_Sao_Braz");
    SapperHill1943.setName("GCS_Sapper_Hill_1943");
    SelvagemGrande1938.setName("GCS_Selvagem_Grande_1938");
    StKitts1955.setName("GCS_St_Kitts_1955");
    StLucia1955.setName("GCS_St_Lucia_1955");
    StVincent1945.setName("GCS_St_Vincent_1945");
    ST71Belep.setName("GCS_ST71_Belep");
    ST84IledesPins.setName("GCS_ST84_Ile_des_Pins");
    ST87Ouvea.setName("GCS_ST87_Ouvea");
    Tahaa.setName("GCS_Tahaa");
    Tahiti.setName("GCS_Tahiti");
    TernIslandAstro1961.setName("GCS_Tern_Island_1961");
    TristanAstro1968.setName("GCS_Tristan_1968");
    VitiLevu1916.setName("GCS_Viti_Levu_1916");
    WakeIslandAstro1952.setName("GCS_Wake_Island_1952");
    WakeEniwetok1960.setName("GCS_Wake_Eniwetok_1960");

    AlaskanIslands.getGeographicInfo().getDatum().setName("D_Alaskan_Islands");
    AmericanSamoa1962.getGeographicInfo().getDatum().setName("D_American_Samoa_1962");
    Anguilla1957.getGeographicInfo().getDatum().setName("D_Anguilla_1957");
    Anna1Astro1965.getGeographicInfo().getDatum().setName("D_Anna_1_1965");
    Antigua1943.getGeographicInfo().getDatum().setName("D_Antigua_1943");
    AscensionIsland1958.getGeographicInfo().getDatum().setName("D_Ascension_Island_1958");
    AstroBeaconE1945.getGeographicInfo().getDatum().setName("D_Beacon_E_1945");
    AstroDOS714.getGeographicInfo().getDatum().setName("D_DOS_71_4");
    AstronomicalStation1952.getGeographicInfo().getDatum().setName("D_Astro_1952");
    AzoresCentral1948.getGeographicInfo().getDatum().setName("D_Azores_Central_Islands_1948");
    AzoresCentral1995.getGeographicInfo().getDatum().setName("D_Azores_Central_Islands_1995");
    AzoresOccidental1939.getGeographicInfo().getDatum().setName("D_Azores_Occidental_Islands_1939");
    AzoresOriental1940.getGeographicInfo().getDatum().setName("D_Azores_Oriental_Islands_1940");
    AzoresOriental1995.getGeographicInfo().getDatum().setName("D_Azores_Oriental_Islands_1995");
    BabSouth.getGeographicInfo().getDatum().setName("D_Bab_South");
    Barbados.getGeographicInfo().getDatum().setName("D_Barbados");
    Barbados1938.getGeographicInfo().getDatum().setName("D_Barbados_1938");
    BellevueIGN.getGeographicInfo().getDatum().setName("D_Bellevue_IGN");
    Bermuda1957.getGeographicInfo().getDatum().setName("D_Bermuda_1957");
    Bermuda2000.getGeographicInfo().getDatum().setName("D_Bermuda_2000");
    CantonAstro1966.getGeographicInfo().getDatum().setName("D_Canton_1966");
    ChathamIslandAstro1971.getGeographicInfo().getDatum().setName("D_Chatham_Island_1971");
    Combani1950.getGeographicInfo().getDatum().setName("D_Combani_1950");
    CSG1967.getGeographicInfo().getDatum().setName("D_CSG_1967");
    Dominica1945.getGeographicInfo().getDatum().setName("D_Dominica_1945");
    DOS1968.getGeographicInfo().getDatum().setName("D_DOS_1968");
    EasterIsland1967.getGeographicInfo().getDatum().setName("D_Easter_Island_1967");
    FortDesaix.getGeographicInfo().getDatum().setName("D_Fort_Desaix");
    FortMarigot.getGeographicInfo().getDatum().setName("D_Fort_Marigot");
    FortThomas1955.getGeographicInfo().getDatum().setName("D_Fort_Thomas_1955");
    Gan1970.getGeographicInfo().getDatum().setName("D_Gan_1970");
    GraciosaBaseSW1948.getGeographicInfo().getDatum().setName("D_Graciosa_Base_SW_1948");
    GrandComoros.getGeographicInfo().getDatum().setName("D_Grand_Comoros");
    Grenada1953.getGeographicInfo().getDatum().setName("D_Grenada_1953");
    Guam1963.getGeographicInfo().getDatum().setName("D_Guam_1963");
    GUX1Astro.getGeographicInfo().getDatum().setName("D_GUX_1");
    Hjorsey1955.getGeographicInfo().getDatum().setName("D_Hjorsey_1955");
    IGN53Mare.getGeographicInfo().getDatum().setName("D_IGN53_Mare");
    IGN56Lifou.getGeographicInfo().getDatum().setName("D_IGN56_Lifou");
    IGN72GrandeTerre.getGeographicInfo().getDatum().setName("D_IGN72_Grande_Terre");
    IGN72NukuHiva.getGeographicInfo().getDatum().setName("D_IGN72_Nuku_Hiva");
    ISTS061Astro1968.getGeographicInfo().getDatum().setName("D_ISTS_061_1968");
    ISTS073Astro1969.getGeographicInfo().getDatum().setName("D_ISTS_073_1969");
    Jamaica1875.getGeographicInfo().getDatum().setName("D_Jamaica_1875");
    Jamaica1969.getGeographicInfo().getDatum().setName("D_Jamaica_1969");
    JohnstonIsland1961.getGeographicInfo().getDatum().setName("D_Johnston_Island_1961");
    K01949.getGeographicInfo().getDatum().setName("D_K0_1949");
    KerguelenIsland1949.getGeographicInfo().getDatum().setName("D_Kerguelen_Island_1949");
    KusaieAstro1951.getGeographicInfo().getDatum().setName("D_Kusaie_1951");
    LC5Astro1961.getGeographicInfo().getDatum().setName("D_LC5_1961");
    Madeira1936.getGeographicInfo().getDatum().setName("D_Madeira_1936");
    Mahe1971.getGeographicInfo().getDatum().setName("D_Mahe_1971");
    Majuro.getGeographicInfo().getDatum().setName("D_Majuro");
    MidwayAstro1961.getGeographicInfo().getDatum().setName("D_Midway_1961");
    Montserrat1958.getGeographicInfo().getDatum().setName("D_Montserrat_1958");
    MOP78.getGeographicInfo().getDatum().setName("D_MOP78");
    NEA74Noumea.getGeographicInfo().getDatum().setName("D_NEA74_Noumea");
    ObservMeteorologico1939.getGeographicInfo().getDatum().setName("D_Observ_Meteorologico_1939");
    OldHawaiian.getGeographicInfo().getDatum().setName("D_Old_Hawaiian");
    PicodeLasNieves.getGeographicInfo().getDatum().setName("D_Pico_de_Las_Nieves");
    PitcairnAstro1967.getGeographicInfo().getDatum().setName("D_Pitcairn_1967");
    PitondesNeiges.getGeographicInfo().getDatum().setName("D_Piton_des_Neiges");
    Pohnpei.getGeographicInfo().getDatum().setName("D_Pohnpei");
    PortoSanto1936.getGeographicInfo().getDatum().setName("D_Porto_Santo_1936");
    PortoSanto1995.getGeographicInfo().getDatum().setName("D_Porto_Santo_1995");
    PuertoRico.getGeographicInfo().getDatum().setName("D_Puerto_Rico");
    Reunion.getGeographicInfo().getDatum().setName("D_Reunion");
    RGFG1995.getGeographicInfo().getDatum().setName("D_RGFG_1995");
    RGNC1991.getGeographicInfo().getDatum().setName("D_RGNC_1991");
    RGR1992.getGeographicInfo().getDatum().setName("D_RGR_1992");
    RRAF1991.getGeographicInfo().getDatum().setName("D_RRAF_1991");
    SaintPierreetMiquelon1950.getGeographicInfo().getDatum().setName("D_Saint_Pierre_et_Miquelon_1950");
    SainteAnne.getGeographicInfo().getDatum().setName("D_Sainte_Anne");
    SantoDOS1965.getGeographicInfo().getDatum().setName("D_Santo_DOS_1965");
    SaoBraz.getGeographicInfo().getDatum().setName("D_Sao_Braz");
    SapperHill1943.getGeographicInfo().getDatum().setName("D_Sapper_Hill_1943");
    SelvagemGrande1938.getGeographicInfo().getDatum().setName("D_Selvagem_Grande_1938");
    StKitts1955.getGeographicInfo().getDatum().setName("D_St_Kitts_1955");
    StLucia1955.getGeographicInfo().getDatum().setName("D_St_Lucia_1955");
    StVincent1945.getGeographicInfo().getDatum().setName("D_St_Vincent_1945");
    ST71Belep.getGeographicInfo().getDatum().setName("D_ST71_Belep");
    ST84IledesPins.getGeographicInfo().getDatum().setName("D_ST84_Ile_des_Pins");
    ST87Ouvea.getGeographicInfo().getDatum().setName("D_ST87_Ouvea");
    Tahaa.getGeographicInfo().getDatum().setName("D_Tahaa");
    Tahiti.getGeographicInfo().getDatum().setName("D_Tahiti");
    TernIslandAstro1961.getGeographicInfo().getDatum().setName("D_Tern_Island_1961");
    TristanAstro1968.getGeographicInfo().getDatum().setName("D_Tristan_1968");
    VitiLevu1916.getGeographicInfo().getDatum().setName("D_Viti_Levu_1916");
    WakeIslandAstro1952.getGeographicInfo().getDatum().setName("D_Wake_Island_1952");
    WakeEniwetok1960.getGeographicInfo().getDatum().setName("D_Wake_Eniwetok_1960");
  }

  //</editor-fold>

  /**
   * @return the AlaskanIslands
   */
  public ProjectionInfo getAlaskanIslands() {
    return AlaskanIslands.copy();
  }

  /**
   * @return the AmericanSamoa1962
   */
  public ProjectionInfo getAmericanSamoa1962() {
    return AmericanSamoa1962.copy();
  }

  /**
   * @return the Anguilla1957
   */
  public ProjectionInfo getAnguilla1957() {
    return Anguilla1957.copy();
  }

  /**
   * @return the Anna1Astro1965
   */
  public ProjectionInfo getAnna1Astro1965() {
    return Anna1Astro1965.copy();
  }

  /**
   * @return the Antigua1943
   */
  public ProjectionInfo getAntigua1943() {
    return Antigua1943.copy();
  }

  /**
   * @return the AscensionIsland1958
   */
  public ProjectionInfo getAscensionIsland1958() {
    return AscensionIsland1958.copy();
  }

  /**
   * @return the AstroBeaconE1945
   */
  public ProjectionInfo getAstroBeaconE1945() {
    return AstroBeaconE1945.copy();
  }

  /**
   * @return the AstroDOS714
   */
  public ProjectionInfo getAstroDOS714() {
    return AstroDOS714.copy();
  }

  /**
   * @return the AstronomicalStation1952
   */
  public ProjectionInfo getAstronomicalStation1952() {
    return AstronomicalStation1952.copy();
  }

  /**
   * @return the AzoresCentral1948
   */
  public ProjectionInfo getAzoresCentral1948() {
    return AzoresCentral1948.copy();
  }

  /**
   * @return the AzoresCentral1995
   */
  public ProjectionInfo getAzoresCentral1995() {
    return AzoresCentral1995.copy();
  }

  /**
   * @return the AzoresOccidental1939
   */
  public ProjectionInfo getAzoresOccidental1939() {
    return AzoresOccidental1939.copy();
  }

  /**
   * @return the AzoresOriental1940
   */
  public ProjectionInfo getAzoresOriental1940() {
    return AzoresOriental1940.copy();
  }

  /**
   * @return the AzoresOriental1995
   */
  public ProjectionInfo getAzoresOriental1995() {
    return AzoresOriental1995.copy();
  }

  /**
   * @return the BabSouth
   */
  public ProjectionInfo getBabSouth() {
    return BabSouth.copy();
  }

  /**
   * @return the Barbados
   */
  public ProjectionInfo getBarbados() {
    return Barbados.copy();
  }

  /**
   * @return the Barbados1938
   */
  public ProjectionInfo getBarbados1938() {
    return Barbados1938.copy();
  }

  /**
   * @return the BellevueIGN
   */
  public ProjectionInfo getBellevueIGN() {
    return BellevueIGN.copy();
  }

  /**
   * @return the Bermuda1957
   */
  public ProjectionInfo getBermuda1957() {
    return Bermuda1957.copy();
  }

  /**
   * @return the Bermuda2000
   */
  public ProjectionInfo getBermuda2000() {
    return Bermuda2000.copy();
  }

  /**
   * @return the CSG1967
   */
  public ProjectionInfo getCSG1967() {
    return CSG1967.copy();
  }

  /**
   * @return the CantonAstro1966
   */
  public ProjectionInfo getCantonAstro1966() {
    return CantonAstro1966.copy();
  }

  /**
   * @return the ChathamIslandAstro1971
   */
  public ProjectionInfo getChathamIslandAstro1971() {
    return ChathamIslandAstro1971.copy();
  }

  /**
   * @return the Combani1950
   */
  public ProjectionInfo getCombani1950() {
    return Combani1950.copy();
  }

  /**
   * @return the DOS1968
   */
  public ProjectionInfo getDOS1968() {
    return DOS1968.copy();
  }

  /**
   * @return the Dominica1945
   */
  public ProjectionInfo getDominica1945() {
    return Dominica1945.copy();
  }

  /**
   * @return the EasterIsland1967
   */
  public ProjectionInfo getEasterIsland1967() {
    return EasterIsland1967.copy();
  }

  /**
   * @return the FortDesaix
   */
  public ProjectionInfo getFortDesaix() {
    return FortDesaix.copy();
  }

  /**
   * @return the FortMarigot
   */
  public ProjectionInfo getFortMarigot() {
    return FortMarigot.copy();
  }

  /**
   * @return the FortThomas1955
   */
  public ProjectionInfo getFortThomas1955() {
    return FortThomas1955.copy();
  }

  /**
   * @return the GUX1Astro
   */
  public ProjectionInfo getGUX1Astro() {
    return GUX1Astro.copy();
  }

  /**
   * @return the Gan1970
   */
  public ProjectionInfo getGan1970() {
    return Gan1970.copy();
  }

  /**
   * @return the GraciosaBaseSW1948
   */
  public ProjectionInfo getGraciosaBaseSW1948() {
    return GraciosaBaseSW1948.copy();
  }

  /**
   * @return the GrandComoros
   */
  public ProjectionInfo getGrandComoros() {
    return GrandComoros.copy();
  }

  /**
   * @return the Grenada1953
   */
  public ProjectionInfo getGrenada1953() {
    return Grenada1953.copy();
  }

  /**
   * @return the Guam1963
   */
  public ProjectionInfo getGuam1963() {
    return Guam1963.copy();
  }

  /**
   * @return the Hjorsey1955
   */
  public ProjectionInfo getHjorsey1955() {
    return Hjorsey1955.copy();
  }

  /**
   * @return the IGN53Mare
   */
  public ProjectionInfo getIGN53Mare() {
    return IGN53Mare.copy();
  }

  /**
   * @return the IGN56Lifou
   */
  public ProjectionInfo getIGN56Lifou() {
    return IGN56Lifou.copy();
  }

  /**
   * @return the IGN72GrandeTerre
   */
  public ProjectionInfo getIGN72GrandeTerre() {
    return IGN72GrandeTerre.copy();
  }

  /**
   * @return the IGN72NukuHiva
   */
  public ProjectionInfo getIGN72NukuHiva() {
    return IGN72NukuHiva.copy();
  }

  /**
   * @return the ISTS061Astro1968
   */
  public ProjectionInfo getISTS061Astro1968() {
    return ISTS061Astro1968.copy();
  }

  /**
   * @return the ISTS073Astro1969
   */
  public ProjectionInfo getISTS073Astro1969() {
    return ISTS073Astro1969.copy();
  }

  /**
   * @return the Jamaica1875
   */
  public ProjectionInfo getJamaica1875() {
    return Jamaica1875.copy();
  }

  /**
   * @return the Jamaica1969
   */
  public ProjectionInfo getJamaica1969() {
    return Jamaica1969.copy();
  }

  /**
   * @return the JohnstonIsland1961
   */
  public ProjectionInfo getJohnstonIsland1961() {
    return JohnstonIsland1961.copy();
  }

  /**
   * @return the K01949
   */
  public ProjectionInfo getK01949() {
    return K01949.copy();
  }

  /**
   * @return the KerguelenIsland1949
   */
  public ProjectionInfo getKerguelenIsland1949() {
    return KerguelenIsland1949.copy();
  }

  /**
   * @return the KusaieAstro1951
   */
  public ProjectionInfo getKusaieAstro1951() {
    return KusaieAstro1951.copy();
  }

  /**
   * @return the LC5Astro1961
   */
  public ProjectionInfo getLC5Astro1961() {
    return LC5Astro1961.copy();
  }

  /**
   * @return the MOP78
   */
  public ProjectionInfo getMOP78() {
    return MOP78.copy();
  }

  /**
   * @return the Madeira1936
   */
  public ProjectionInfo getMadeira1936() {
    return Madeira1936.copy();
  }

  /**
   * @return the Mahe1971
   */
  public ProjectionInfo getMahe1971() {
    return Mahe1971.copy();
  }

  /**
   * @return the Majuro
   */
  public ProjectionInfo getMajuro() {
    return Majuro.copy();
  }

  /**
   * @return the MidwayAstro1961
   */
  public ProjectionInfo getMidwayAstro1961() {
    return MidwayAstro1961.copy();
  }

  /**
   * @return the Montserrat1958
   */
  public ProjectionInfo getMontserrat1958() {
    return Montserrat1958.copy();
  }

  /**
   * @return the NEA74Noumea
   */
  public ProjectionInfo getNEA74Noumea() {
    return NEA74Noumea.copy();
  }

  /**
   * @return the ObservMeteorologico1939
   */
  public ProjectionInfo getObservMeteorologico1939() {
    return ObservMeteorologico1939.copy();
  }

  /**
   * @return the OldHawaiian
   */
  public ProjectionInfo getOldHawaiian() {
    return OldHawaiian.copy();
  }

  /**
   * @return the PicodeLasNieves
   */
  public ProjectionInfo getPicodeLasNieves() {
    return PicodeLasNieves.copy();
  }

  /**
   * @return the PitcairnAstro1967
   */
  public ProjectionInfo getPitcairnAstro1967() {
    return PitcairnAstro1967.copy();
  }

  /**
   * @return the PitondesNeiges
   */
  public ProjectionInfo getPitondesNeiges() {
    return PitondesNeiges.copy();
  }

  /**
   * @return the Pohnpei
   */
  public ProjectionInfo getPohnpei() {
    return Pohnpei.copy();
  }

  /**
   * @return the PortoSanto1936
   */
  public ProjectionInfo getPortoSanto1936() {
    return PortoSanto1936.copy();
  }

  /**
   * @return the PortoSanto1995
   */
  public ProjectionInfo getPortoSanto1995() {
    return PortoSanto1995.copy();
  }

  /**
   * @return the PuertoRico
   */
  public ProjectionInfo getPuertoRico() {
    return PuertoRico.copy();
  }

  /**
   * @return the RGFG1995
   */
  public ProjectionInfo getRGFG1995() {
    return RGFG1995.copy();
  }

  /**
   * @return the RGNC1991
   */
  public ProjectionInfo getRGNC1991() {
    return RGNC1991.copy();
  }

  /**
   * @return the RGR1992
   */
  public ProjectionInfo getRGR1992() {
    return RGR1992.copy();
  }

  /**
   * @return the RRAF1991
   */
  public ProjectionInfo getRRAF1991() {
    return RRAF1991.copy();
  }

  /**
   * @return the Reunion
   */
  public ProjectionInfo getReunion() {
    return Reunion.copy();
  }

  /**
   * @return the ST71Belep
   */
  public ProjectionInfo getST71Belep() {
    return ST71Belep.copy();
  }

  /**
   * @return the ST84IledesPins
   */
  public ProjectionInfo getST84IledesPins() {
    return ST84IledesPins.copy();
  }

  /**
   * @return the ST87Ouvea
   */
  public ProjectionInfo getST87Ouvea() {
    return ST87Ouvea.copy();
  }

  /**
   * @return the SaintPierreetMiquelon1950
   */
  public ProjectionInfo getSaintPierreetMiquelon1950() {
    return SaintPierreetMiquelon1950.copy();
  }

  /**
   * @return the SainteAnne
   */
  public ProjectionInfo getSainteAnne() {
    return SainteAnne.copy();
  }

  /**
   * @return the SantoDOS1965
   */
  public ProjectionInfo getSantoDOS1965() {
    return SantoDOS1965.copy();
  }

  /**
   * @return the SaoBraz
   */
  public ProjectionInfo getSaoBraz() {
    return SaoBraz.copy();
  }

  /**
   * @return the SapperHill1943
   */
  public ProjectionInfo getSapperHill1943() {
    return SapperHill1943.copy();
  }

  /**
   * @return the SelvagemGrande1938
   */
  public ProjectionInfo getSelvagemGrande1938() {
    return SelvagemGrande1938.copy();
  }

  /**
   * @return the StKitts1955
   */
  public ProjectionInfo getStKitts1955() {
    return StKitts1955.copy();
  }

  /**
   * @return the StLucia1955
   */
  public ProjectionInfo getStLucia1955() {
    return StLucia1955.copy();
  }

  /**
   * @return the StVincent1945
   */
  public ProjectionInfo getStVincent1945() {
    return StVincent1945.copy();
  }

  /**
   * @return the Tahaa
   */
  public ProjectionInfo getTahaa() {
    return Tahaa.copy();
  }

  /**
   * @return the Tahiti
   */
  public ProjectionInfo getTahiti() {
    return Tahiti.copy();
  }

  /**
   * @return the TernIslandAstro1961
   */
  public ProjectionInfo getTernIslandAstro1961() {
    return TernIslandAstro1961.copy();
  }

  /**
   * @return the TristanAstro1968
   */
  public ProjectionInfo getTristanAstro1968() {
    return TristanAstro1968.copy();
  }

  /**
   * @return the VitiLevu1916
   */
  public ProjectionInfo getVitiLevu1916() {
    return VitiLevu1916.copy();
  }

  /**
   * @return the WakeEniwetok1960
   */
  public ProjectionInfo getWakeEniwetok1960() {
    return WakeEniwetok1960.copy();
  }

  /**
   * @return the WakeIslandAstro1952
   */
  public ProjectionInfo getWakeIslandAstro1952() {
    return WakeIslandAstro1952.copy();
  }
}
