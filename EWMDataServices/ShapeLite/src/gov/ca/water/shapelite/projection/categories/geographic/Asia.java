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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:04:52 PM
//
// Contributor(s): (Open source contributors should list themselves and their modifications here).
//        Name         |    Date    |        Comment
// --------------------|------------|------------------------------------------------------------
// Ted Dunsford        |   5/3/2010 |  Updated project to DotSpatial.Projection and license to LGPL
// ********************************************************************************************************
package gov.ca.water.shapelite.projection.categories.geographic;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

public class Asia extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo AinelAbd1970;
  private final ProjectionInfo Batavia;
  private final ProjectionInfo BataviaJakarta;
  private final ProjectionInfo Beijing1954;
  private final ProjectionInfo BukitRimpah;
  private final ProjectionInfo DeirezZor;
  private final ProjectionInfo European1950ED77;
  private final ProjectionInfo EuropeanDatum1950;
  private final ProjectionInfo Everest1830;
  private final ProjectionInfo EverestBangladesh;
  private final ProjectionInfo EverestIndiaandNepal;
  private final ProjectionInfo EverestModified;
  private final ProjectionInfo Everestdef1962;
  private final ProjectionInfo Everestdef1967;
  private final ProjectionInfo Everestdef1975;
  private final ProjectionInfo FD1958;
  private final ProjectionInfo Fahud;
  private final ProjectionInfo Gandajika1970;
  private final ProjectionInfo GunungSegara;
  private final ProjectionInfo GunungSegaraJakarta;
  private final ProjectionInfo Hanoi1972;
  private final ProjectionInfo HeratNorth;
  private final ProjectionInfo HongKong1963;
  private final ProjectionInfo HongKong1980;
  private final ProjectionInfo HuTzuShan;
  private final ProjectionInfo IGM1995;
  private final ProjectionInfo IKBD1992;
  private final ProjectionInfo Indian1954;
  private final ProjectionInfo Indian1960;
  private final ProjectionInfo Indian1975;
  private final ProjectionInfo IndonesianDatum1974;
  private final ProjectionInfo Israel;
  private final ProjectionInfo JGD2000;
  private final ProjectionInfo Jordan;
  private final ProjectionInfo Kalianpur1880;
  private final ProjectionInfo Kalianpur1937;
  private final ProjectionInfo Kalianpur1962;
  private final ProjectionInfo Kalianpur1975;
  private final ProjectionInfo Kandawala;
  private final ProjectionInfo Kertau;
  private final ProjectionInfo KoreanDatum1985;
  private final ProjectionInfo KoreanDatum1995;
  private final ProjectionInfo KuwaitOilCompany;
  private final ProjectionInfo KuwaitUtility;
  private final ProjectionInfo Luzon1911;
  private final ProjectionInfo Makassar;
  private final ProjectionInfo MakassarJakarta;
  private final ProjectionInfo Nahrwan1967;
  private final ProjectionInfo NationalGeodeticNetworkKuwait;
  private final ProjectionInfo ObservatorioMeteorologico1965;
  private final ProjectionInfo Oman;
  private final ProjectionInfo Padang1884;
  private final ProjectionInfo Padang1884Jakarta;
  private final ProjectionInfo Palestine1923;
  private final ProjectionInfo Pulkovo1942;
  private final ProjectionInfo Pulkovo1995;
  private final ProjectionInfo QND1995;
  private final ProjectionInfo Qatar;
  private final ProjectionInfo Qatar1948;
  private final ProjectionInfo Rassadiran;
  private final ProjectionInfo Samboja;
  private final ProjectionInfo Segora;
  private final ProjectionInfo Serindung;
  private final ProjectionInfo SouthAsiaSingapore;
  private final ProjectionInfo Timbalai1948;
  private final ProjectionInfo Tokyo;
  private final ProjectionInfo TrucialCoast1948;
  private final ProjectionInfo Xian1980;

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Constructor">
        /// <summary>
  /// Creates a new instance of Asia
  /// </summary>
  public Asia() {
    AinelAbd1970 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Batavia = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    BataviaJakarta = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +pm=106.8077194444444 +no_defs ").orElse(null);
    Beijing1954 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    BukitRimpah = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    DeirezZor = ProjectionInfo.fromProj4String("+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    European1950ED77 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    EuropeanDatum1950 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    EverestBangladesh = ProjectionInfo.fromProj4String("+proj=longlat +a=6377276.345 +b=6356075.41314024 +no_defs ").orElse(null);
    EverestIndiaandNepal = ProjectionInfo.fromProj4String("+proj=longlat +a=6377301.243 +b=6356100.230165384 +no_defs ").orElse(null);
    Everestdef1962 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377301.243 +b=6356100.230165384 +no_defs ").orElse(null);
    Everestdef1967 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=evrstSS +no_defs ").orElse(null);
    Everestdef1975 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377299.151 +b=6356098.145120132 +no_defs ").orElse(null);
    Everest1830 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377299.36 +b=6356098.35162804 +no_defs ").orElse(null);
    EverestModified = ProjectionInfo.fromProj4String("+proj=longlat +a=6377304.063 +b=6356103.041812424 +no_defs ").orElse(null);
    Fahud = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    FD1958 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Gandajika1970 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    GunungSegara = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    GunungSegaraJakarta = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +pm=106.8077194444444 +no_defs ").orElse(null);
    Hanoi1972 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    HeratNorth = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    HongKong1963 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    HongKong1980 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    HuTzuShan = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    IGM1995 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    IKBD1992 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    Indian1954 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377276.345 +b=6356075.41314024 +no_defs ").orElse(null);
    Indian1960 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377276.345 +b=6356075.41314024 +no_defs ").orElse(null);
    Indian1975 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377276.345 +b=6356075.41314024 +no_defs ").orElse(null);
    IndonesianDatum1974 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378160 +b=6356774.50408554 +no_defs ").orElse(null);
    Israel = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    JGD2000 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    Jordan = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Kalianpur1880 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377299.36 +b=6356098.35162804 +no_defs ").orElse(null);
    Kalianpur1937 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377276.345 +b=6356075.41314024 +no_defs ").orElse(null);
    Kalianpur1962 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377301.243 +b=6356100.230165384 +no_defs ").orElse(null);
    Kalianpur1975 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377299.151 +b=6356098.145120132 +no_defs ").orElse(null);
    Kandawala = ProjectionInfo.fromProj4String("+proj=longlat +a=6377276.345 +b=6356075.41314024 +no_defs ").orElse(null);
    Kertau = ProjectionInfo.fromProj4String("+proj=longlat +a=6377304.063 +b=6356103.038993155 +no_defs ").orElse(null);
    KoreanDatum1985 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    KoreanDatum1995 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    KuwaitOilCompany = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    KuwaitUtility = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    Luzon1911 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Makassar = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    MakassarJakarta = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +pm=106.8077194444444 +no_defs ").orElse(null);
    Nahrwan1967 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    NationalGeodeticNetworkKuwait = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    ObservatorioMeteorologico1965 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Oman = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Padang1884 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Padang1884Jakarta = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +pm=106.8077194444444 +no_defs ").orElse(null);
    Palestine1923 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378300.79 +b=6356566.430000036 +no_defs ").orElse(null);
    Pulkovo1942 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    Pulkovo1995 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    Qatar = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Qatar1948 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=helmert +no_defs ").orElse(null);
    QND1995 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Rassadiran = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Samboja = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Segora = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Serindung = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    SouthAsiaSingapore = ProjectionInfo.fromProj4String("+proj=longlat +ellps=fschr60m +no_defs ").orElse(null);
    Timbalai1948 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=evrstSS +no_defs ").orElse(null);
    Tokyo = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    TrucialCoast1948 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=helmert +no_defs ").orElse(null);
    Xian1980 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378140 +b=6356755.288157528 +no_defs ").orElse(null);

    AinelAbd1970.setLatLon(true);
    Batavia.setLatLon(true);
    BataviaJakarta.setLatLon(true);
    Beijing1954.setLatLon(true);
    BukitRimpah.setLatLon(true);
    DeirezZor.setLatLon(true);
    European1950ED77.setLatLon(true);
    EuropeanDatum1950.setLatLon(true);
    EverestBangladesh.setLatLon(true);
    EverestIndiaandNepal.setLatLon(true);
    Everestdef1962.setLatLon(true);
    Everestdef1967.setLatLon(true);
    Everestdef1975.setLatLon(true);
    Everest1830.setLatLon(true);
    EverestModified.setLatLon(true);
    Fahud.setLatLon(true);
    FD1958.setLatLon(true);
    Gandajika1970.setLatLon(true);
    GunungSegara.setLatLon(true);
    GunungSegaraJakarta.setLatLon(true);
    Hanoi1972.setLatLon(true);
    HeratNorth.setLatLon(true);
    HongKong1963.setLatLon(true);
    HongKong1980.setLatLon(true);
    HuTzuShan.setLatLon(true);
    IGM1995.setLatLon(true);
    IKBD1992.setLatLon(true);
    Indian1954.setLatLon(true);
    Indian1960.setLatLon(true);
    Indian1975.setLatLon(true);
    IndonesianDatum1974.setLatLon(true);
    Israel.setLatLon(true);
    JGD2000.setLatLon(true);
    Jordan.setLatLon(true);
    Kalianpur1880.setLatLon(true);
    Kalianpur1937.setLatLon(true);
    Kalianpur1962.setLatLon(true);
    Kalianpur1975.setLatLon(true);
    Kandawala.setLatLon(true);
    Kertau.setLatLon(true);
    KoreanDatum1985.setLatLon(true);
    KoreanDatum1995.setLatLon(true);
    KuwaitOilCompany.setLatLon(true);
    KuwaitUtility.setLatLon(true);
    Luzon1911.setLatLon(true);
    Makassar.setLatLon(true);
    MakassarJakarta.setLatLon(true);
    Nahrwan1967.setLatLon(true);
    NationalGeodeticNetworkKuwait.setLatLon(true);
    ObservatorioMeteorologico1965.setLatLon(true);
    Oman.setLatLon(true);
    Padang1884.setLatLon(true);
    Padang1884Jakarta.setLatLon(true);
    Palestine1923.setLatLon(true);
    Pulkovo1942.setLatLon(true);
    Pulkovo1995.setLatLon(true);
    Qatar.setLatLon(true);
    Qatar1948.setLatLon(true);
    QND1995.setLatLon(true);
    Rassadiran.setLatLon(true);
    Samboja.setLatLon(true);
    Segora.setLatLon(true);
    Serindung.setLatLon(true);
    SouthAsiaSingapore.setLatLon(true);
    Timbalai1948.setLatLon(true);
    Tokyo.setLatLon(true);
    TrucialCoast1948.setLatLon(true);
    Xian1980.setLatLon(true);
    AinelAbd1970.getGeographicInfo().setName("GCS_Ain_el_Abd_1970");
    Batavia.getGeographicInfo().setName("GCS_Batavia");
    BataviaJakarta.getGeographicInfo().setName("GCS_Batavia_Jakarta");
    Beijing1954.getGeographicInfo().setName("GCS_Beijing_1954");
    BukitRimpah.getGeographicInfo().setName("GCS_Bukit_Rimpah");
    DeirezZor.getGeographicInfo().setName("GCS_Deir_ez_Zor");
    European1950ED77.getGeographicInfo().setName("GCS_European_1950_ED77");
    EuropeanDatum1950.getGeographicInfo().setName("GCS_European_1950");
    EverestBangladesh.getGeographicInfo().setName("GCS_Everest_Bangladesh");
    EverestIndiaandNepal.getGeographicInfo().setName("GCS_Everest_India_Nepal");
    Everestdef1962.getGeographicInfo().setName("GCS_Everest_def_1962");
    Everestdef1967.getGeographicInfo().setName("GCS_Everest_def_1967");
    Everestdef1975.getGeographicInfo().setName("GCS_Everest_def_1975");
    Everest1830.getGeographicInfo().setName("GCS_Everest_1830");
    EverestModified.getGeographicInfo().setName("GCS_Everest_Modified");
    Fahud.getGeographicInfo().setName("GCS_Fahud");
    FD1958.getGeographicInfo().setName("GCS_FD_1958");
    Gandajika1970.getGeographicInfo().setName("GCS_Gandajika_1970");
    GunungSegara.getGeographicInfo().setName("GCS_Gunung_Segara");
    GunungSegaraJakarta.getGeographicInfo().setName("GCS_Gunung_Segara_Jakarta");
    Hanoi1972.getGeographicInfo().setName("GCS_Hanoi_1972");
    HeratNorth.getGeographicInfo().setName("GCS_Herat_North");
    HongKong1963.getGeographicInfo().setName("GCS_Hong_Kong_1963");
    HongKong1980.getGeographicInfo().setName("GCS_Hong_Kong_1980");
    HuTzuShan.getGeographicInfo().setName("GCS_Hu_Tzu_Shan");
    IGM1995.getGeographicInfo().setName("GCS_IGM_1995");
    IKBD1992.getGeographicInfo().setName("GCS_IKBD_1992");
    Indian1954.getGeographicInfo().setName("GCS_Indian_1954");
    Indian1960.getGeographicInfo().setName("GCS_Indian_1960");
    Indian1975.getGeographicInfo().setName("GCS_Indian_1975");
    IndonesianDatum1974.getGeographicInfo().setName("GCS_Indonesian_1974");
    Israel.getGeographicInfo().setName("GCS_Israel");
    JGD2000.getGeographicInfo().setName("GCS_JGD_2000");
    Jordan.getGeographicInfo().setName("GCS_Jordan");
    Kalianpur1880.getGeographicInfo().setName("GCS_Kalianpur_1880");
    Kalianpur1937.getGeographicInfo().setName("GCS_Kalianpur_1937");
    Kalianpur1962.getGeographicInfo().setName("GCS_Kalianpur_1962");
    Kalianpur1975.getGeographicInfo().setName("GCS_Kalianpur_1975");
    Kandawala.getGeographicInfo().setName("GCS_Kandawala");
    Kertau.getGeographicInfo().setName("GCS_Kertau");
    KoreanDatum1985.getGeographicInfo().setName("GCS_Korean_Datum_1985");
    KoreanDatum1995.getGeographicInfo().setName("GCS_Korean_Datum_1995");
    KuwaitOilCompany.getGeographicInfo().setName("GCS_Kuwait_Oil_Company");
    KuwaitUtility.getGeographicInfo().setName("GCS_KUDAMS");
    Luzon1911.getGeographicInfo().setName("GCS_Luzon_1911");
    Makassar.getGeographicInfo().setName("GCS_Makassar");
    MakassarJakarta.getGeographicInfo().setName("GCS_Makassar_Jakarta");
    Nahrwan1967.getGeographicInfo().setName("GCS_Nahrwan_1967");
    NationalGeodeticNetworkKuwait.getGeographicInfo().setName("GCS_NGN");
    ObservatorioMeteorologico1965.getGeographicInfo().setName("GCS_Observatorio_Meteorologico_1965");
    Oman.getGeographicInfo().setName("GCS_Oman");
    Padang1884.getGeographicInfo().setName("GCS_Padang_1884");
    Padang1884Jakarta.getGeographicInfo().setName("GCS_Padang_1884_Jakarta");
    Palestine1923.getGeographicInfo().setName("GCS_Palestine_1923");
    Pulkovo1942.getGeographicInfo().setName("GCS_Pulkovo_1942");
    Pulkovo1995.getGeographicInfo().setName("GCS_Pulkovo_1995");
    Qatar.getGeographicInfo().setName("GCS_Qatar");
    Qatar1948.getGeographicInfo().setName("GCS_Qatar_1948");
    QND1995.getGeographicInfo().setName("GCS_QND_1995");
    Rassadiran.getGeographicInfo().setName("GCS_Rassadiran");
    Samboja.getGeographicInfo().setName("GCS_Samboja");
    Segora.getGeographicInfo().setName("GCS_Segora");
    Serindung.getGeographicInfo().setName("GCS_Serindung");
    SouthAsiaSingapore.getGeographicInfo().setName("GCS_South_Asia_Singapore");
    Timbalai1948.getGeographicInfo().setName("GCS_Timbalai_1948");
    Tokyo.getGeographicInfo().setName("GCS_Tokyo");
    TrucialCoast1948.getGeographicInfo().setName("GCS_Trucial_Coast_1948");
    Xian1980.getGeographicInfo().setName("GCS_Xian_1980");

    AinelAbd1970.setName("GCS_Ain_el_Abd_1970");
    Batavia.setName("GCS_Batavia");
    BataviaJakarta.setName("GCS_Batavia_Jakarta");
    Beijing1954.setName("GCS_Beijing_1954");
    BukitRimpah.setName("GCS_Bukit_Rimpah");
    DeirezZor.setName("GCS_Deir_ez_Zor");
    European1950ED77.setName("GCS_European_1950_ED77");
    EuropeanDatum1950.setName("GCS_European_1950");
    EverestBangladesh.setName("GCS_Everest_Bangladesh");
    EverestIndiaandNepal.setName("GCS_Everest_India_Nepal");
    Everestdef1962.setName("GCS_Everest_def_1962");
    Everestdef1967.setName("GCS_Everest_def_1967");
    Everestdef1975.setName("GCS_Everest_def_1975");
    Everest1830.setName("GCS_Everest_1830");
    EverestModified.setName("GCS_Everest_Modified");
    Fahud.setName("GCS_Fahud");
    FD1958.setName("GCS_FD_1958");
    Gandajika1970.setName("GCS_Gandajika_1970");
    GunungSegara.setName("GCS_Gunung_Segara");
    GunungSegaraJakarta.setName("GCS_Gunung_Segara_Jakarta");
    Hanoi1972.setName("GCS_Hanoi_1972");
    HeratNorth.setName("GCS_Herat_North");
    HongKong1963.setName("GCS_Hong_Kong_1963");
    HongKong1980.setName("GCS_Hong_Kong_1980");
    HuTzuShan.setName("GCS_Hu_Tzu_Shan");
    IGM1995.setName("GCS_IGM_1995");
    IKBD1992.setName("GCS_IKBD_1992");
    Indian1954.setName("GCS_Indian_1954");
    Indian1960.setName("GCS_Indian_1960");
    Indian1975.setName("GCS_Indian_1975");
    IndonesianDatum1974.setName("GCS_Indonesian_1974");
    Israel.setName("GCS_Israel");
    JGD2000.setName("GCS_JGD_2000");
    Jordan.setName("GCS_Jordan");
    Kalianpur1880.setName("GCS_Kalianpur_1880");
    Kalianpur1937.setName("GCS_Kalianpur_1937");
    Kalianpur1962.setName("GCS_Kalianpur_1962");
    Kalianpur1975.setName("GCS_Kalianpur_1975");
    Kandawala.setName("GCS_Kandawala");
    Kertau.setName("GCS_Kertau");
    KoreanDatum1985.setName("GCS_Korean_Datum_1985");
    KoreanDatum1995.setName("GCS_Korean_Datum_1995");
    KuwaitOilCompany.setName("GCS_Kuwait_Oil_Company");
    KuwaitUtility.setName("GCS_KUDAMS");
    Luzon1911.setName("GCS_Luzon_1911");
    Makassar.setName("GCS_Makassar");
    MakassarJakarta.setName("GCS_Makassar_Jakarta");
    Nahrwan1967.setName("GCS_Nahrwan_1967");
    NationalGeodeticNetworkKuwait.setName("GCS_NGN");
    ObservatorioMeteorologico1965.setName("GCS_Observatorio_Meteorologico_1965");
    Oman.setName("GCS_Oman");
    Padang1884.setName("GCS_Padang_1884");
    Padang1884Jakarta.setName("GCS_Padang_1884_Jakarta");
    Palestine1923.setName("GCS_Palestine_1923");
    Pulkovo1942.setName("GCS_Pulkovo_1942");
    Pulkovo1995.setName("GCS_Pulkovo_1995");
    Qatar.setName("GCS_Qatar");
    Qatar1948.setName("GCS_Qatar_1948");
    QND1995.setName("GCS_QND_1995");
    Rassadiran.setName("GCS_Rassadiran");
    Samboja.setName("GCS_Samboja");
    Segora.setName("GCS_Segora");
    Serindung.setName("GCS_Serindung");
    SouthAsiaSingapore.setName("GCS_South_Asia_Singapore");
    Timbalai1948.setName("GCS_Timbalai_1948");
    Tokyo.setName("GCS_Tokyo");
    TrucialCoast1948.setName("GCS_Trucial_Coast_1948");
    Xian1980.setName("GCS_Xian_1980");

    AinelAbd1970.getGeographicInfo().getDatum().setName("D_Ain_el_Abd_1970");
    Batavia.getGeographicInfo().getDatum().setName("D_Batavia");
    BataviaJakarta.getGeographicInfo().getDatum().setName("D_Batavia");
    Beijing1954.getGeographicInfo().getDatum().setName("D_Beijing_1954");
    BukitRimpah.getGeographicInfo().getDatum().setName("D_Bukit_Rimpah");
    DeirezZor.getGeographicInfo().getDatum().setName("D_Deir_ez_Zor");
    European1950ED77.getGeographicInfo().getDatum().setName("D_European_1950_ED77");
    EuropeanDatum1950.getGeographicInfo().getDatum().setName("D_European_1950");
    EverestBangladesh.getGeographicInfo().getDatum().setName("D_Everest_Bangladesh");
    EverestIndiaandNepal.getGeographicInfo().getDatum().setName("D_Everest_India_Nepal");
    Everestdef1962.getGeographicInfo().getDatum().setName("D_Everest_Def_1962");
    Everestdef1967.getGeographicInfo().getDatum().setName("D_Everest_Def_1967");
    Everestdef1975.getGeographicInfo().getDatum().setName("D_Everest_Def_1975");
    Everest1830.getGeographicInfo().getDatum().setName("D_Everest_1830");
    EverestModified.getGeographicInfo().getDatum().setName("D_Everest_Modified");
    Fahud.getGeographicInfo().getDatum().setName("D_Fahud");
    FD1958.getGeographicInfo().getDatum().setName("D_FD_1958");
    Gandajika1970.getGeographicInfo().getDatum().setName("D_Gandajika_1970");
    GunungSegara.getGeographicInfo().getDatum().setName("D_Gunung_Segara");
    GunungSegaraJakarta.getGeographicInfo().getDatum().setName("D_Gunung_Segara");
    Hanoi1972.getGeographicInfo().getDatum().setName("D_Hanoi_1972");
    HeratNorth.getGeographicInfo().getDatum().setName("D_Herat_North");
    HongKong1963.getGeographicInfo().getDatum().setName("D_Hong_Kong_1963");
    HongKong1980.getGeographicInfo().getDatum().setName("D_Hong_Kong_1980");
    HuTzuShan.getGeographicInfo().getDatum().setName("D_Hu_Tzu_Shan");
    IGM1995.getGeographicInfo().getDatum().setName("D_IGM_1995");
    IKBD1992.getGeographicInfo().getDatum().setName("D_Iraq_Kuwait_Boundary_1992");
    Indian1954.getGeographicInfo().getDatum().setName("D_Indian_1954");
    Indian1960.getGeographicInfo().getDatum().setName("D_Indian_1960");
    Indian1975.getGeographicInfo().getDatum().setName("D_Indian_1975");
    IndonesianDatum1974.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
    Israel.getGeographicInfo().getDatum().setName("D_Israel");
    JGD2000.getGeographicInfo().getDatum().setName("D_JGD_2000");
    Jordan.getGeographicInfo().getDatum().setName("D_Jordan");
    Kalianpur1880.getGeographicInfo().getDatum().setName("D_Kalianpur_1880");
    Kalianpur1937.getGeographicInfo().getDatum().setName("D_Kalianpur_1937");
    Kalianpur1962.getGeographicInfo().getDatum().setName("D_Kalianpur_1962");
    Kalianpur1975.getGeographicInfo().getDatum().setName("D_Kalianpur_1975");
    Kandawala.getGeographicInfo().getDatum().setName("D_Kandawala");
    Kertau.getGeographicInfo().getDatum().setName("D_Kertau");
    KoreanDatum1985.getGeographicInfo().getDatum().setName("D_Korean_Datum_1985");
    KoreanDatum1995.getGeographicInfo().getDatum().setName("D_Korean_Datum_1995");
    KuwaitOilCompany.getGeographicInfo().getDatum().setName("D_Kuwait_Oil_Company");
    KuwaitUtility.getGeographicInfo().getDatum().setName("D_Kuwait_Utility");
    Luzon1911.getGeographicInfo().getDatum().setName("D_Luzon_1911");
    Makassar.getGeographicInfo().getDatum().setName("D_Makassar");
    MakassarJakarta.getGeographicInfo().getDatum().setName("D_Makassar");
    Nahrwan1967.getGeographicInfo().getDatum().setName("D_Nahrwan_1967");
    NationalGeodeticNetworkKuwait.getGeographicInfo().getDatum().setName("D_NGN");
    ObservatorioMeteorologico1965.getGeographicInfo().getDatum().setName("D_Observatorio_Meteorologico_1965");
    Oman.getGeographicInfo().getDatum().setName("D_Oman");
    Padang1884.getGeographicInfo().getDatum().setName("D_Padang_1884");
    Padang1884Jakarta.getGeographicInfo().getDatum().setName("D_Padang_1884");
    Palestine1923.getGeographicInfo().getDatum().setName("D_Palestine_1923");
    Pulkovo1942.getGeographicInfo().getDatum().setName("D_Pulkovo_1942");
    Pulkovo1995.getGeographicInfo().getDatum().setName("D_Pulkovo_1995");
    Qatar.getGeographicInfo().getDatum().setName("D_Qatar");
    Qatar1948.getGeographicInfo().getDatum().setName("D_Qatar_1948");
    QND1995.getGeographicInfo().getDatum().setName("D_QND_1995");
    Rassadiran.getGeographicInfo().getDatum().setName("D_Rassadiran");
    Samboja.getGeographicInfo().getDatum().setName("D_Samboja");
    Segora.getGeographicInfo().getDatum().setName("D_Segora");
    Serindung.getGeographicInfo().getDatum().setName("D_Serindung");
    SouthAsiaSingapore.getGeographicInfo().getDatum().setName("D_South_Asia_Singapore");
    Timbalai1948.getGeographicInfo().getDatum().setName("D_Timbalai_1948");
    Tokyo.getGeographicInfo().getDatum().setName("D_Tokyo");
    TrucialCoast1948.getGeographicInfo().getDatum().setName("D_Trucial_Coast_1948");
    Xian1980.getGeographicInfo().getDatum().setName("D_Xian_1980");
  }

  //</editor-fold>

  /**
   * @return the AinelAbd1970
   */
  public ProjectionInfo getAinelAbd1970() {
    return AinelAbd1970.copy();
  }

  /**
   * @return the Batavia
   */
  public ProjectionInfo getBatavia() {
    return Batavia.copy();
  }

  /**
   * @return the BataviaJakarta
   */
  public ProjectionInfo getBataviaJakarta() {
    return BataviaJakarta.copy();
  }

  /**
   * @return the Beijing1954
   */
  public ProjectionInfo getBeijing1954() {
    return Beijing1954.copy();
  }

  /**
   * @return the BukitRimpah
   */
  public ProjectionInfo getBukitRimpah() {
    return BukitRimpah.copy();
  }

  /**
   * @return the DeirezZor
   */
  public ProjectionInfo getDeirezZor() {
    return DeirezZor.copy();
  }

  /**
   * @return the European1950ED77
   */
  public ProjectionInfo getEuropean1950ED77() {
    return European1950ED77.copy();
  }

  /**
   * @return the EuropeanDatum1950
   */
  public ProjectionInfo getEuropeanDatum1950() {
    return EuropeanDatum1950.copy();
  }

  /**
   * @return the Everest1830
   */
  public ProjectionInfo getEverest1830() {
    return Everest1830.copy();
  }

  /**
   * @return the EverestBangladesh
   */
  public ProjectionInfo getEverestBangladesh() {
    return EverestBangladesh.copy();
  }

  /**
   * @return the EverestIndiaandNepal
   */
  public ProjectionInfo getEverestIndiaandNepal() {
    return EverestIndiaandNepal.copy();
  }

  /**
   * @return the EverestModified
   */
  public ProjectionInfo getEverestModified() {
    return EverestModified.copy();
  }

  /**
   * @return the Everestdef1962
   */
  public ProjectionInfo getEverestdef1962() {
    return Everestdef1962.copy();
  }

  /**
   * @return the Everestdef1967
   */
  public ProjectionInfo getEverestdef1967() {
    return Everestdef1967.copy();
  }

  /**
   * @return the Everestdef1975
   */
  public ProjectionInfo getEverestdef1975() {
    return Everestdef1975.copy();
  }

  /**
   * @return the FD1958
   */
  public ProjectionInfo getFD1958() {
    return FD1958.copy();
  }

  /**
   * @return the Fahud
   */
  public ProjectionInfo getFahud() {
    return Fahud.copy();
  }

  /**
   * @return the Gandajika1970
   */
  public ProjectionInfo getGandajika1970() {
    return Gandajika1970.copy();
  }

  /**
   * @return the GunungSegara
   */
  public ProjectionInfo getGunungSegara() {
    return GunungSegara.copy();
  }

  /**
   * @return the GunungSegaraJakarta
   */
  public ProjectionInfo getGunungSegaraJakarta() {
    return GunungSegaraJakarta.copy();
  }

  /**
   * @return the Hanoi1972
   */
  public ProjectionInfo getHanoi1972() {
    return Hanoi1972.copy();
  }

  /**
   * @return the HeratNorth
   */
  public ProjectionInfo getHeratNorth() {
    return HeratNorth.copy();
  }

  /**
   * @return the HongKong1963
   */
  public ProjectionInfo getHongKong1963() {
    return HongKong1963.copy();
  }

  /**
   * @return the HongKong1980
   */
  public ProjectionInfo getHongKong1980() {
    return HongKong1980.copy();
  }

  /**
   * @return the HuTzuShan
   */
  public ProjectionInfo getHuTzuShan() {
    return HuTzuShan.copy();
  }

  /**
   * @return the IGM1995
   */
  public ProjectionInfo getIGM1995() {
    return IGM1995.copy();
  }

  /**
   * @return the IKBD1992
   */
  public ProjectionInfo getIKBD1992() {
    return IKBD1992.copy();
  }

  /**
   * @return the Indian1954
   */
  public ProjectionInfo getIndian1954() {
    return Indian1954.copy();
  }

  /**
   * @return the Indian1960
   */
  public ProjectionInfo getIndian1960() {
    return Indian1960.copy();
  }

  /**
   * @return the Indian1975
   */
  public ProjectionInfo getIndian1975() {
    return Indian1975.copy();
  }

  /**
   * @return the IndonesianDatum1974
   */
  public ProjectionInfo getIndonesianDatum1974() {
    return IndonesianDatum1974.copy();
  }

  /**
   * @return the Israel
   */
  public ProjectionInfo getIsrael() {
    return Israel.copy();
  }

  /**
   * @return the JGD2000
   */
  public ProjectionInfo getJGD2000() {
    return JGD2000.copy();
  }

  /**
   * @return the Jordan
   */
  public ProjectionInfo getJordan() {
    return Jordan.copy();
  }

  /**
   * @return the Kalianpur1880
   */
  public ProjectionInfo getKalianpur1880() {
    return Kalianpur1880.copy();
  }

  /**
   * @return the Kalianpur1937
   */
  public ProjectionInfo getKalianpur1937() {
    return Kalianpur1937.copy();
  }

  /**
   * @return the Kalianpur1962
   */
  public ProjectionInfo getKalianpur1962() {
    return Kalianpur1962.copy();
  }

  /**
   * @return the Kalianpur1975
   */
  public ProjectionInfo getKalianpur1975() {
    return Kalianpur1975.copy();
  }

  /**
   * @return the Kandawala
   */
  public ProjectionInfo getKandawala() {
    return Kandawala.copy();
  }

  /**
   * @return the Kertau
   */
  public ProjectionInfo getKertau() {
    return Kertau.copy();
  }

  /**
   * @return the KoreanDatum1985
   */
  public ProjectionInfo getKoreanDatum1985() {
    return KoreanDatum1985.copy();
  }

  /**
   * @return the KoreanDatum1995
   */
  public ProjectionInfo getKoreanDatum1995() {
    return KoreanDatum1995.copy();
  }

  /**
   * @return the KuwaitOilCompany
   */
  public ProjectionInfo getKuwaitOilCompany() {
    return KuwaitOilCompany.copy();
  }

  /**
   * @return the KuwaitUtility
   */
  public ProjectionInfo getKuwaitUtility() {
    return KuwaitUtility.copy();
  }

  /**
   * @return the Luzon1911
   */
  public ProjectionInfo getLuzon1911() {
    return Luzon1911.copy();
  }

  /**
   * @return the Makassar
   */
  public ProjectionInfo getMakassar() {
    return Makassar.copy();
  }

  /**
   * @return the MakassarJakarta
   */
  public ProjectionInfo getMakassarJakarta() {
    return MakassarJakarta.copy();
  }

  /**
   * @return the Nahrwan1967
   */
  public ProjectionInfo getNahrwan1967() {
    return Nahrwan1967.copy();
  }

  /**
   * @return the NationalGeodeticNetworkKuwait
   */
  public ProjectionInfo getNationalGeodeticNetworkKuwait() {
    return NationalGeodeticNetworkKuwait.copy();
  }

  /**
   * @return the ObservatorioMeteorologico1965
   */
  public ProjectionInfo getObservatorioMeteorologico1965() {
    return ObservatorioMeteorologico1965.copy();
  }

  /**
   * @return the Oman
   */
  public ProjectionInfo getOman() {
    return Oman.copy();
  }

  /**
   * @return the Padang1884
   */
  public ProjectionInfo getPadang1884() {
    return Padang1884.copy();
  }

  /**
   * @return the Padang1884Jakarta
   */
  public ProjectionInfo getPadang1884Jakarta() {
    return Padang1884Jakarta.copy();
  }

  /**
   * @return the Palestine1923
   */
  public ProjectionInfo getPalestine1923() {
    return Palestine1923.copy();
  }

  /**
   * @return the Pulkovo1942
   */
  public ProjectionInfo getPulkovo1942() {
    return Pulkovo1942.copy();
  }

  /**
   * @return the Pulkovo1995
   */
  public ProjectionInfo getPulkovo1995() {
    return Pulkovo1995.copy();
  }

  /**
   * @return the QND1995
   */
  public ProjectionInfo getQND1995() {
    return QND1995.copy();
  }

  /**
   * @return the Qatar
   */
  public ProjectionInfo getQatar() {
    return Qatar.copy();
  }

  /**
   * @return the Qatar1948
   */
  public ProjectionInfo getQatar1948() {
    return Qatar1948.copy();
  }

  /**
   * @return the Rassadiran
   */
  public ProjectionInfo getRassadiran() {
    return Rassadiran.copy();
  }

  /**
   * @return the Samboja
   */
  public ProjectionInfo getSamboja() {
    return Samboja.copy();
  }

  /**
   * @return the Segora
   */
  public ProjectionInfo getSegora() {
    return Segora.copy();
  }

  /**
   * @return the Serindung
   */
  public ProjectionInfo getSerindung() {
    return Serindung.copy();
  }

  /**
   * @return the SouthAsiaSingapore
   */
  public ProjectionInfo getSouthAsiaSingapore() {
    return SouthAsiaSingapore.copy();
  }

  /**
   * @return the Timbalai1948
   */
  public ProjectionInfo getTimbalai1948() {
    return Timbalai1948.copy();
  }

  /**
   * @return the Tokyo
   */
  public ProjectionInfo getTokyo() {
    return Tokyo.copy();
  }

  /**
   * @return the TrucialCoast1948
   */
  public ProjectionInfo getTrucialCoast1948() {
    return TrucialCoast1948.copy();
  }

  /**
   * @return the Xian1980
   */
  public ProjectionInfo getXian1980() {
    return Xian1980.copy();
  }
}
