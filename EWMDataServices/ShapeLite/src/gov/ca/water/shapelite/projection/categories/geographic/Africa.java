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
package gov.ca.water.shapelite.projection.categories.geographic;

import gov.ca.water.shapelite.projection.ProjectionException;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Africa extends CoordinateSystemCategory {

  //<editor-fold defaultstate="collapsed" desc="Names">

  // Abidjan1987
  // Accra
  // Adindan
  // Afgooye
  // Agadez
  // AinelAbd1970
  // Arc1950
  // Arc1960
  // AyabelleLighthouse
  // Beduaram
  // Bissau
  // Camacupa
  // Cape
  // Carthage
  // CarthageParis
  // Carthagedegrees
  // Conakry1905
  // CotedIvoire
  // Dabola
  // Douala
  // Douala1948
  // Egypt1907
  // Egypt1930
  // EuropeanDatum1950
  // EuropeanLibyanDatum1979
  // Garoua
  // Hartebeesthoek1994
  // Kousseri
  // KuwaitOilCompany
  // KuwaitUtility
  // Leigon
  // Liberia1964
  // Locodjo1965
  // Lome
  // Madzansua
  // Mahe1971
  // Malongo1987
  // Manoca
  // Manoca1962
  // Massawa
  // Merchich
  // Merchichdegrees
  // Mhast
  // Minna
  // Moznet
  // Mporaloko
  // Nahrwan1967
  // NationalGeodeticNetworkKuwait
  // NordSahara1959
  // NordSahara1959Paris
  // Observatario
  // Oman
  // PDO1993
  // Palestine1923
  // Point58
  // PointeNoire
  // Qatar
  // Qatar1948
  // Schwarzeck
  // SierraLeone1924
  // SierraLeone1960
  // SierraLeone1968
  // SouthYemen
  // Sudan
  // Tananarive1925
  // Tananarive1925Paris
  // Tete
  // TrucialCoast1948
  // Voirol1875
  // Voirol1875Paris
  // Voirol1875degrees
  // VoirolUnifie1960
  // VoirolUnifie1960Paris
  // VoirolUnifie1960degrees
  // YemenNGN1996
  // yoff



    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">

  /**
   * The Abidjan1987 Coordinate System.
   */
  private final ProjectionInfo Abidjan1987;
    private final ProjectionInfo Accra;
    private final ProjectionInfo Adindan;
    private final ProjectionInfo Afgooye;
    private final ProjectionInfo Agadez;
    private final ProjectionInfo AinelAbd1970;
    private final ProjectionInfo Arc1950;
    private final ProjectionInfo Arc1960;
    private final ProjectionInfo AyabelleLighthouse;
    private final ProjectionInfo Beduaram;
    private final ProjectionInfo Bissau;
    private final ProjectionInfo Camacupa;
    private final ProjectionInfo Cape;
    private final ProjectionInfo Carthage;
    private final ProjectionInfo CarthageParis;
    private final ProjectionInfo Carthagedegrees;
    private final ProjectionInfo Conakry1905;
    private final ProjectionInfo CotedIvoire;
    private final ProjectionInfo Dabola;
    private final ProjectionInfo Douala;
    private final ProjectionInfo Douala1948;
    private final ProjectionInfo Egypt1907;
    private final ProjectionInfo Egypt1930;
    private final ProjectionInfo EuropeanDatum1950;
    private final ProjectionInfo EuropeanLibyanDatum1979;
    private final ProjectionInfo Garoua;
    private final ProjectionInfo Hartebeesthoek1994;
    private final ProjectionInfo Kousseri;
    private final ProjectionInfo KuwaitOilCompany;
    private final ProjectionInfo KuwaitUtility;
    private final ProjectionInfo Leigon;
    private final ProjectionInfo Liberia1964;
    private final ProjectionInfo Locodjo1965;
    private final ProjectionInfo Lome;
    private final ProjectionInfo Madzansua;
    private final ProjectionInfo Mahe1971;
    private final ProjectionInfo Malongo1987;
    private final ProjectionInfo Manoca;
    private final ProjectionInfo Manoca1962;
    private final ProjectionInfo Massawa;
    private final ProjectionInfo Merchich;
    private final ProjectionInfo Merchichdegrees;
    private final ProjectionInfo Mhast;
    private final ProjectionInfo Minna;
    private final ProjectionInfo Moznet;
    private final ProjectionInfo Mporaloko;
    private final ProjectionInfo Nahrwan1967;
    private final ProjectionInfo NationalGeodeticNetworkKuwait;
    private final ProjectionInfo NordSahara1959;
    private final ProjectionInfo NordSahara1959Paris;
    private final ProjectionInfo Observatario;
    private final ProjectionInfo Oman;
    private final ProjectionInfo PDO1993;
    private final ProjectionInfo Palestine1923;
    private final ProjectionInfo Point58;
    private final ProjectionInfo PointeNoire;
    private final ProjectionInfo Qatar;
    private final ProjectionInfo Qatar1948;
    private final ProjectionInfo Schwarzeck;
    private final ProjectionInfo SierraLeone1924;
    private final ProjectionInfo SierraLeone1960;
    private final ProjectionInfo SierraLeone1968;
    private final ProjectionInfo SouthYemen;
    private final ProjectionInfo Sudan;
    private final ProjectionInfo Tananarive1925;
    private final ProjectionInfo Tananarive1925Paris;
    private final ProjectionInfo Tete;
    private final ProjectionInfo TrucialCoast1948;
    private final ProjectionInfo Voirol1875;
    private final ProjectionInfo Voirol1875Paris;
    private final ProjectionInfo Voirol1875degrees;
    private final ProjectionInfo VoirolUnifie1960;
    private final ProjectionInfo VoirolUnifie1960Paris;
    private final ProjectionInfo VoirolUnifie1960degrees;
    private final ProjectionInfo YemenNGN1996;
    private final ProjectionInfo yoff;

    //</editor-fold>

  /**
   * Creates a new instance of the Africa class.
   */
  public Africa() {
    Abidjan1987 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Accra = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378300 +b=6356751.689189189 +no_defs ").orElse(null);
    Adindan = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Afgooye = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=krass +no_defs ").orElse(null);
    Agadez = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    AinelAbd1970 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Arc1950 = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.145 +b=6356514.966395495 +no_defs ").orElse(null);
    Arc1960 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    AyabelleLighthouse = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Beduaram = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Bissau = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Camacupa = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Cape = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.145 +b=6356514.966395495 +no_defs ").orElse(null);
    Carthage = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Carthagedegrees = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    CarthageParis = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667"
            + " +no_defs ").orElse(null);
    Conakry1905 = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    CotedIvoire = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Dabola = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Douala = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Douala1948 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Egypt1907 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=helmert +no_defs ").orElse(null);
    Egypt1930 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +no_defs ").orElse(null);
    EuropeanDatum1950 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +no_defs ").orElse(null);
    EuropeanLibyanDatum1979 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Garoua = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Hartebeesthoek1994 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    Kousseri = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    KuwaitOilCompany = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    KuwaitUtility = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    Leigon = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Liberia1964 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Locodjo1965 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Lome = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Madzansua = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Mahe1971 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Malongo1987 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Manoca = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Manoca1962 = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Massawa = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Merchich = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Merchichdegrees = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Mhast = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Minna = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Moznet = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    Mporaloko = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Nahrwan1967 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    NationalGeodeticNetworkKuwait = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    NordSahara1959 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    NordSahara1959Paris = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +pm=2.337229166666667 +no_defs ").orElse(null);
    Observatario = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Oman = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Palestine1923 = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378300.79 +b=6356566.430000036 +no_defs ").orElse(null);
    PDO1993 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Point58 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    PointeNoire = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Qatar = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Qatar1948 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=helmert +no_defs ").orElse(null);
    Schwarzeck = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=bess_nam +no_defs ").orElse(null);
    SierraLeone1924 = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378300 +b=6356751.689189189 +no_defs ").orElse(null);
    SierraLeone1960 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    SierraLeone1968 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    SouthYemen = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=krass +no_defs ").orElse(null);
    Sudan = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Tananarive1925 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Tananarive1925Paris = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=intl +pm=2.337229166666667 +no_defs ").orElse(null);
    Tete = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    TrucialCoast1948 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=helmert +no_defs ").orElse(null);
    Voirol1875 = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Voirol1875degrees = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    Voirol1875Paris = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667"
            + " +no_defs ").orElse(null);
    VoirolUnifie1960 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    VoirolUnifie1960degrees = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    VoirolUnifie1960Paris = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=clrk80 +pm=2.337229166666667 +no_defs ").orElse(null);
    YemenNGN1996 = ProjectionInfo.fromProj4String(
        "+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    yoff = ProjectionInfo.fromProj4String(
        "+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);

    makeGeographic();
    setGeographicNames();
    setProjectionNames();
    setDatumNames();
  }

  /**
   * Assigns a name to the datum for each class.  This is not part of the
   * proj4 definition.
   */
  private void setDatumNames() {
    Abidjan1987.getGeographicInfo().getDatum().setName("D_Abidjan_1987");
    Accra.getGeographicInfo().getDatum().setName("D_Accra");
    Adindan.getGeographicInfo().getDatum().setName("D_Adindan");
    Afgooye.getGeographicInfo().getDatum().setName("D_Afgooye");
    Agadez.getGeographicInfo().getDatum().setName("D_Agadez");
    AinelAbd1970.getGeographicInfo().getDatum().setName("D_Ain_el_Abd_1970");
    Arc1950.getGeographicInfo().getDatum().setName("D_Arc_1950");
    Arc1960.getGeographicInfo().getDatum().setName("D_Arc_1960");
    AyabelleLighthouse.getGeographicInfo().getDatum().setName("D_Ayabelle");
    Beduaram.getGeographicInfo().getDatum().setName("D_Beduaram");
    Bissau.getGeographicInfo().getDatum().setName("D_Bissau");
    Camacupa.getGeographicInfo().getDatum().setName("D_Camacupa");
    Cape.getGeographicInfo().getDatum().setName("D_Cape");
    Carthage.getGeographicInfo().getDatum().setName("D_Carthage");
    Carthagedegrees.getGeographicInfo().getDatum().setName("D_Carthage");
    CarthageParis.getGeographicInfo().getDatum().setName("D_Carthage");
    Conakry1905.getGeographicInfo().getDatum().setName("D_Conakry_1905");
    CotedIvoire.getGeographicInfo().getDatum().setName("D_Cote_d_Ivoire");
    Dabola.getGeographicInfo().getDatum().setName("D_Dabola");
    Douala.getGeographicInfo().getDatum().setName("D_Douala");
    Douala1948.getGeographicInfo().getDatum().setName("D_Douala_1948");
    Egypt1907.getGeographicInfo().getDatum().setName("D_Egypt_1907");
    Egypt1930.getGeographicInfo().getDatum().setName("D_Egypt_1930");
    EuropeanDatum1950.getGeographicInfo().getDatum().setName("D_European_1950");
    EuropeanLibyanDatum1979.getGeographicInfo().getDatum().setName(
        "D_European_Libyan_1979");
    Garoua.getGeographicInfo().getDatum().setName("D_Garoua");
    Hartebeesthoek1994.getGeographicInfo().getDatum().setName(
        "D_Hartebeesthoek_1994");
    Kousseri.getGeographicInfo().getDatum().setName("D_Kousseri");
    KuwaitOilCompany.getGeographicInfo().getDatum().setName("D_Kuwait_Oil_Company");
    KuwaitUtility.getGeographicInfo().getDatum().setName("D_Kuwait_Utility");
    Leigon.getGeographicInfo().getDatum().setName("D_Leigon");
    Liberia1964.getGeographicInfo().getDatum().setName("D_Liberia_1964");
    Locodjo1965.getGeographicInfo().getDatum().setName("D_Locodjo_1965");
    Lome.getGeographicInfo().getDatum().setName("D_Lome");
    Madzansua.getGeographicInfo().getDatum().setName("D_Madzansua");
    Mahe1971.getGeographicInfo().getDatum().setName("D_Mahe_1971");
    Malongo1987.getGeographicInfo().getDatum().setName("D_Malongo_1987");
    Manoca.getGeographicInfo().getDatum().setName("D_Manoca");
    Manoca1962.getGeographicInfo().getDatum().setName("D_Manoca_1962");
    Massawa.getGeographicInfo().getDatum().setName("D_Massawa");
    Merchich.getGeographicInfo().getDatum().setName("D_Merchich");
    Merchichdegrees.getGeographicInfo().getDatum().setName("D_Merchich");
    Mhast.getGeographicInfo().getDatum().setName("D_Mhast_Offshore");
    Minna.getGeographicInfo().getDatum().setName("D_Minna");
    Moznet.getGeographicInfo().getDatum().setName("D_Moznet");
    Nahrwan1967.getGeographicInfo().getDatum().setName("D_Nahrwan_1967");
    NationalGeodeticNetworkKuwait.getGeographicInfo().getDatum().setName("D_NGN");
    NordSahara1959.getGeographicInfo().getDatum().setName("D_Nord_Sahara_1959");
    NordSahara1959Paris.getGeographicInfo().getDatum().setName("D_Nord_Sahara_1959");
    Observatario.getGeographicInfo().getDatum().setName("D_Observatario");
    Oman.getGeographicInfo().getDatum().setName("D_Oman");
    Palestine1923.getGeographicInfo().getDatum().setName("D_Palestine_1923");
    PDO1993.getGeographicInfo().getDatum().setName("D_PDO_1993");
    Point58.getGeographicInfo().getDatum().setName("D_Point_58");
    PointeNoire.getGeographicInfo().getDatum().setName("D_Pointe_Noire");
    Qatar.getGeographicInfo().getDatum().setName("D_Qatar");
    Qatar1948.getGeographicInfo().getDatum().setName("D_Qatar_1948");
    Schwarzeck.getGeographicInfo().getDatum().setName("D_Schwarzeck");
    SierraLeone1924.getGeographicInfo().getDatum().setName("D_Sierra_Leone_1924");
    SierraLeone1960.getGeographicInfo().getDatum().setName("D_Sierra_Leone_1960");
    SierraLeone1968.getGeographicInfo().getDatum().setName("D_Sierra_Leone_1968");
    SouthYemen.getGeographicInfo().getDatum().setName("D_South_Yemen");
    Sudan.getGeographicInfo().getDatum().setName("D_Sudan");
    Tananarive1925.getGeographicInfo().getDatum().setName("D_Tananarive_1925");
    Tananarive1925Paris.getGeographicInfo().getDatum().setName("D_Tananarive_1925");
    Tete.getGeographicInfo().getDatum().setName("D_Tete");
    TrucialCoast1948.getGeographicInfo().getDatum().setName("D_Trucial_Coast_1948");
    Voirol1875.getGeographicInfo().getDatum().setName("D_Voirol_1875");
    Voirol1875degrees.getGeographicInfo().getDatum().setName("D_Voirol_1875");
    Voirol1875Paris.getGeographicInfo().getDatum().setName("D_Voirol_1875");
    VoirolUnifie1960.getGeographicInfo().getDatum().setName("D_Voirol_Unifie_1960");
    VoirolUnifie1960degrees.getGeographicInfo().getDatum().setName(
        "D_Voirol_Unifie_1960");
    VoirolUnifie1960Paris.getGeographicInfo().getDatum().setName("D_Voirol_Unifie_1960");
    YemenNGN1996.getGeographicInfo().getDatum().setName("D_Yemen_NGN_1996");
    yoff.getGeographicInfo().getDatum().setName("D_Yoff");
  }

  private void setProjectionNames() {
    Abidjan1987.setName("GCS_Abidjan_1987");
    Accra.setName("GCS_Accra");
    Adindan.setName("GCS_Adindan");
    Afgooye.setName("GCS_Afgooye");
    Agadez.setName("GCS_Agadez");
    AinelAbd1970.setName("GCS_Ain_el_Abd_1970");
    Arc1950.setName("GCS_Arc_1950");
    Arc1960.setName("GCS_Arc_1960");
    AyabelleLighthouse.setName("GCS_Ayabelle");
    Beduaram.setName("GCS_Beduaram");
    Bissau.setName("GCS_Bissau");
    Camacupa.setName("GCS_Camacupa");
    Cape.setName("GCS_Cape");
    Carthage.setName("GCS_Carthage");
    Carthagedegrees.setName("GCS_Carthage_Degree");
    CarthageParis.setName("GCS_Carthage_Paris");
    Conakry1905.setName("GCS_Conakry_1905");
    CotedIvoire.setName("GCS_Cote_d_Ivoire");
    Dabola.setName("GCS_Dabola");
    Douala.setName("GCS_Douala");
    Douala1948.setName("GCS_Douala_1948");
    Egypt1907.setName("GCS_Egypt_1907");
    Egypt1930.setName("GCS_Egypt_1930");
    EuropeanDatum1950.setName("GCS_European_1950");
    EuropeanLibyanDatum1979.setName("GCS_European_Libyan_Datum_1979");
    Garoua.setName("GCS_Garoua");
    Hartebeesthoek1994.setName("GCS_Hartebeesthoek_1994");
    Kousseri.setName("GCS_Kousseri");
    KuwaitOilCompany.setName("GCS_Kuwait_Oil_Company");
    KuwaitUtility.setName("GCS_KUDAMS");
    Leigon.setName("GCS_Leigon");
    Liberia1964.setName("GCS_Liberia_1964");
    Locodjo1965.setName("GCS_Locodjo_1965");
    Lome.setName("GCS_Lome");
    Madzansua.setName("GCS_Madzansua");
    Mahe1971.setName("GCS_Mahe_1971");
    Malongo1987.setName("GCS_Malongo_1987");
    Manoca.setName("GCS_Manoca");
    Manoca1962.setName("GCS_Manoca_1962");
    Massawa.setName("GCS_Massawa");
    Merchich.setName("GCS_Merchich");
    Merchichdegrees.setName("GCS_Merchich_Degree");
    Mhast.setName("GCS_Mhast_Offshore");
    Minna.setName("GCS_Minna");
    Moznet.setName("GCS_Moznet");
    Nahrwan1967.setName("GCS_Nahrwan_1967");
    NationalGeodeticNetworkKuwait.setName("GCS_NGN");
    NordSahara1959.setName("GCS_Nord_Sahara_1959");
    NordSahara1959Paris.setName("GCS_Nord_Sahara_1959_Paris");
    Observatario.setName("GCS_Observatario");
    Oman.setName("GCS_Oman");
    Palestine1923.setName("GCS_Palestine_1923");
    PDO1993.setName("GCS_PDO_1993");
    Point58.setName("GCS_Point_58");
    PointeNoire.setName("GCS_Pointe_Noire");
    Qatar.setName("GCS_Qatar");
    Qatar1948.setName("GCS_Qatar_1948");
    Schwarzeck.setName("GCS_Schwarzeck");
    SierraLeone1924.setName("GCS_Sierra_Leone_1924");
    SierraLeone1960.setName("GCS_Sierra_Leone_1960");
    SierraLeone1968.setName("GCS_Sierra_Leone_1968");
    SouthYemen.setName("GCS_South_Yemen");
    Sudan.setName("GCS_Sudan");
    Tananarive1925.setName("GCS_Tananarive_1925");
    Tananarive1925Paris.setName("GCS_Tananarive_1925_Paris");
    Tete.setName("GCS_Tete");
    TrucialCoast1948.setName("GCS_Trucial_Coast_1948");
    Voirol1875.setName("GCS_Voirol_1875");
    Voirol1875degrees.setName("GCS_Voirol_1875_Degree");
    Voirol1875Paris.setName("GCS_Voirol_1875_Paris");
    VoirolUnifie1960.setName("GCS_Voirol_Unifie_1960");
    VoirolUnifie1960degrees.setName("GCS_Voirol_Unifie_1960_Degree");
    VoirolUnifie1960Paris.setName("GCS_Voirol_Unifie_1960_Paris");
    YemenNGN1996.setName("GCS_Yemen_NGN_1996");
    yoff.setName("GCS_Yoff");
  }

  private void setGeographicNames() {
    Abidjan1987.getGeographicInfo().setName("GCS_Abidjan_1987");
    Accra.getGeographicInfo().setName("GCS_Accra");
    Adindan.getGeographicInfo().setName("GCS_Adindan");
    Afgooye.getGeographicInfo().setName("GCS_Afgooye");
    Agadez.getGeographicInfo().setName("GCS_Agadez");
    AinelAbd1970.getGeographicInfo().setName("GCS_Ain_el_Abd_1970");
    Arc1950.getGeographicInfo().setName("GCS_Arc_1950");
    Arc1960.getGeographicInfo().setName("GCS_Arc_1960");
    AyabelleLighthouse.getGeographicInfo().setName("GCS_Ayabelle");
    Beduaram.getGeographicInfo().setName("GCS_Beduaram");
    Bissau.getGeographicInfo().setName("GCS_Bissau");
    Camacupa.getGeographicInfo().setName("GCS_Camacupa");
    Cape.getGeographicInfo().setName("GCS_Cape");
    Carthage.getGeographicInfo().setName("GCS_Carthage");
    Carthagedegrees.getGeographicInfo().setName("GCS_Carthage_Degree");
    CarthageParis.getGeographicInfo().setName("GCS_Carthage_Paris");
    Conakry1905.getGeographicInfo().setName("GCS_Conakry_1905");
    CotedIvoire.getGeographicInfo().setName("GCS_Cote_d_Ivoire");
    Dabola.getGeographicInfo().setName("GCS_Dabola");
    Douala.getGeographicInfo().setName("GCS_Douala");
    Douala1948.getGeographicInfo().setName("GCS_Douala_1948");
    Egypt1907.getGeographicInfo().setName("GCS_Egypt_1907");
    Egypt1930.getGeographicInfo().setName("GCS_Egypt_1930");
    EuropeanDatum1950.getGeographicInfo().setName("GCS_European_1950");
    EuropeanLibyanDatum1979.getGeographicInfo().setName(
        "GCS_European_Libyan_Datum_1979");
    Garoua.getGeographicInfo().setName("GCS_Garoua");
    Hartebeesthoek1994.getGeographicInfo().setName("GCS_Hartebeesthoek_1994");
    Kousseri.getGeographicInfo().setName("GCS_Kousseri");
    KuwaitOilCompany.getGeographicInfo().setName("GCS_Kuwait_Oil_Company");
    KuwaitUtility.getGeographicInfo().setName("GCS_KUDAMS");
    Leigon.getGeographicInfo().setName("GCS_Leigon");
    Liberia1964.getGeographicInfo().setName("GCS_Liberia_1964");
    Locodjo1965.getGeographicInfo().setName("GCS_Locodjo_1965");
    Lome.getGeographicInfo().setName("GCS_Lome");
    Madzansua.getGeographicInfo().setName("GCS_Madzansua");
    Mahe1971.getGeographicInfo().setName("GCS_Mahe_1971");
    Malongo1987.getGeographicInfo().setName("GCS_Malongo_1987");
    Manoca.getGeographicInfo().setName("GCS_Manoca");
    Manoca1962.getGeographicInfo().setName("GCS_Manoca_1962");
    Massawa.getGeographicInfo().setName("GCS_Massawa");
    Merchich.getGeographicInfo().setName("GCS_Merchich");
    Merchichdegrees.getGeographicInfo().setName("GCS_Merchich_Degree");
    Mhast.getGeographicInfo().setName("GCS_Mhast_Offshore");
    Minna.getGeographicInfo().setName("GCS_Minna");
    Moznet.getGeographicInfo().setName("GCS_Moznet");
    Nahrwan1967.getGeographicInfo().setName("GCS_Nahrwan_1967");
    NationalGeodeticNetworkKuwait.getGeographicInfo().setName("GCS_NGN");
    NordSahara1959.getGeographicInfo().setName("GCS_Nord_Sahara_1959");
    NordSahara1959Paris.getGeographicInfo().setName("GCS_Nord_Sahara_1959_Paris");
    Observatario.getGeographicInfo().setName("GCS_Observatario");
    Oman.getGeographicInfo().setName("GCS_Oman");
    Palestine1923.getGeographicInfo().setName("GCS_Palestine_1923");
    PDO1993.getGeographicInfo().setName("GCS_PDO_1993");
    Point58.getGeographicInfo().setName("GCS_Point_58");
    PointeNoire.getGeographicInfo().setName("GCS_Pointe_Noire");
    Qatar.getGeographicInfo().setName("GCS_Qatar");
    Qatar1948.getGeographicInfo().setName("GCS_Qatar_1948");
    Schwarzeck.getGeographicInfo().setName("GCS_Schwarzeck");
    SierraLeone1924.getGeographicInfo().setName("GCS_Sierra_Leone_1924");
    SierraLeone1960.getGeographicInfo().setName("GCS_Sierra_Leone_1960");
    SierraLeone1968.getGeographicInfo().setName("GCS_Sierra_Leone_1968");
    SouthYemen.getGeographicInfo().setName("GCS_South_Yemen");
    Sudan.getGeographicInfo().setName("GCS_Sudan");
    Tananarive1925.getGeographicInfo().setName("GCS_Tananarive_1925");
    Tananarive1925Paris.getGeographicInfo().setName("GCS_Tananarive_1925_Paris");
    Tete.getGeographicInfo().setName("GCS_Tete");
    TrucialCoast1948.getGeographicInfo().setName("GCS_Trucial_Coast_1948");
    Voirol1875.getGeographicInfo().setName("GCS_Voirol_1875");
    Voirol1875degrees.getGeographicInfo().setName("GCS_Voirol_1875_Degree");
    Voirol1875Paris.getGeographicInfo().setName("GCS_Voirol_1875_Paris");
    VoirolUnifie1960.getGeographicInfo().setName("GCS_Voirol_Unifie_1960");
    VoirolUnifie1960degrees.getGeographicInfo().setName(
        "GCS_Voirol_Unifie_1960_Degree");
    VoirolUnifie1960Paris.getGeographicInfo().setName("GCS_Voirol_Unifie_1960_Paris");
    YemenNGN1996.getGeographicInfo().setName("GCS_Yemen_NGN_1996");
    yoff.getGeographicInfo().setName("GCS_Yoff");
  }

  private void makeGeographic() {
    Abidjan1987.setLatLon(true);
    Accra.setLatLon(true);
    Adindan.setLatLon(true);
    Afgooye.setLatLon(true);
    Agadez.setLatLon(true);
    AinelAbd1970.setLatLon(true);
    Arc1950.setLatLon(true);
    Arc1960.setLatLon(true);
    AyabelleLighthouse.setLatLon(true);
    Beduaram.setLatLon(true);
    Bissau.setLatLon(true);
    Camacupa.setLatLon(true);
    Cape.setLatLon(true);
    Carthage.setLatLon(true);
    Carthagedegrees.setLatLon(true);
    CarthageParis.setLatLon(true);
    Conakry1905.setLatLon(true);
    CotedIvoire.setLatLon(true);
    Dabola.setLatLon(true);
    Douala.setLatLon(true);
    Douala1948.setLatLon(true);
    Egypt1907.setLatLon(true);
    Egypt1930.setLatLon(true);
    EuropeanDatum1950.setLatLon(true);
    EuropeanLibyanDatum1979.setLatLon(true);
    Garoua.setLatLon(true);
    Hartebeesthoek1994.setLatLon(true);
    Kousseri.setLatLon(true);
    KuwaitOilCompany.setLatLon(true);
    KuwaitUtility.setLatLon(true);
    Leigon.setLatLon(true);
    Liberia1964.setLatLon(true);
    Locodjo1965.setLatLon(true);
    Lome.setLatLon(true);
    Madzansua.setLatLon(true);
    Mahe1971.setLatLon(true);
    Malongo1987.setLatLon(true);
    Manoca.setLatLon(true);
    Manoca1962.setLatLon(true);
    Massawa.setLatLon(true);
    Merchich.setLatLon(true);
    Merchichdegrees.setLatLon(true);
    Mhast.setLatLon(true);
    Minna.setLatLon(true);
    Moznet.setLatLon(true);
    Mporaloko.setLatLon(true);
    Nahrwan1967.setLatLon(true);
    NationalGeodeticNetworkKuwait.setLatLon(true);
    NordSahara1959.setLatLon(true);
    NordSahara1959Paris.setLatLon(true);
    Observatario.setLatLon(true);
    Oman.setLatLon(true);
    Palestine1923.setLatLon(true);
    PDO1993.setLatLon(true);
    Point58.setLatLon(true);
    PointeNoire.setLatLon(true);
    Qatar.setLatLon(true);
    Qatar1948.setLatLon(true);
    Schwarzeck.setLatLon(true);
    SierraLeone1924.setLatLon(true);
    SierraLeone1960.setLatLon(true);
    SierraLeone1968.setLatLon(true);
    SouthYemen.setLatLon(true);
    Sudan.setLatLon(true);
    Tananarive1925.setLatLon(true);
    Tananarive1925Paris.setLatLon(true);
    Tete.setLatLon(true);
    TrucialCoast1948.setLatLon(true);
    Voirol1875.setLatLon(true);
    Voirol1875degrees.setLatLon(true);
    Voirol1875Paris.setLatLon(true);
    VoirolUnifie1960.setLatLon(true);
    VoirolUnifie1960degrees.setLatLon(true);
    VoirolUnifie1960Paris.setLatLon(true);
    YemenNGN1996.setLatLon(true);
    yoff.setLatLon(true);
  }



    //<editor-fold defaultstate="collapsed" desc="Methods">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Properties">
    //</editor-fold>

  /**
   * Gets the Abidjan1987 projected coordinate system.
   * @return the Abidjan1987 coordinate system.
   */
  public final ProjectionInfo getAbidjan1987() {
    return Abidjan1987.copy();
  }

  /**
   * @return the Accra
   */
  public ProjectionInfo getAccra() {
    return Accra.copy();
  }

  /**
   * @return the Adindan
   */
  public ProjectionInfo getAdindan() {
    return Adindan.copy();
  }

  /**
   * @return the Afgooye
   */
  public ProjectionInfo getAfgooye() {
    return Afgooye.copy();
  }

  /**
   * @return the Agadez
   */
  public ProjectionInfo getAgadez() {
    return Agadez.copy();
  }

  /**
   * @return the AinelAbd1970
   */
  public ProjectionInfo getAinelAbd1970() {
    return AinelAbd1970.copy();
  }

  /**
   * @return the Arc1950
   */
  public ProjectionInfo getArc1950() {
    return Arc1950.copy();
  }

  /**
   * @return the Arc1960
   */
  public ProjectionInfo getArc1960() {
    return Arc1960.copy();
  }

  /**
   * @return the AyabelleLighthouse
   */
  public ProjectionInfo getAyabelleLighthouse() {
    return AyabelleLighthouse.copy();
  }

  /**
   * @return the Beduaram
   */
  public ProjectionInfo getBeduaram() {
    return Beduaram.copy();
  }

  /**
   * @return the Bissau
   */
  public ProjectionInfo getBissau() {
    return Bissau.copy();
  }

  /**
   * @return the Camacupa
   */
  public ProjectionInfo getCamacupa() {
    return Camacupa.copy();
  }

  /**
   * @return the Cape
   */
  public ProjectionInfo getCape() {
    return Cape.copy();
  }

  /**
   * @return the Carthage
   */
  public ProjectionInfo getCarthage() {
    return Carthage.copy();
  }

  /**
   * @return the CarthageParis
   */
  public ProjectionInfo getCarthageParis() {
    return CarthageParis.copy();
  }

  /**
   * @return the Carthagedegrees
   */
  public ProjectionInfo getCarthagedegrees() {
    return Carthagedegrees.copy();
  }

  /**
   * @return the Conakry1905
   */
  public ProjectionInfo getConakry1905() {
    return Conakry1905.copy();
  }

  /**
   * @return the CotedIvoire
   */
  public ProjectionInfo getCotedIvoire() {
    return CotedIvoire.copy();
  }

  /**
   * @return the Dabola
   */
  public ProjectionInfo getDabola() {
    return Dabola.copy();
  }

  /**
   * @return the Douala
   */
  public ProjectionInfo getDouala() {
    return Douala.copy();
  }

  /**
   * @return the Douala1948
   */
  public ProjectionInfo getDouala1948() {
    return Douala1948.copy();
  }

  /**
   * @return the Egypt1907
   */
  public ProjectionInfo getEgypt1907() {
    return Egypt1907.copy();
  }

  /**
   * @return the Egypt1930
   */
  public ProjectionInfo getEgypt1930() {
    return Egypt1930.copy();
  }

  /**
   * @return the EuropeanDatum1950
   */
  public ProjectionInfo getEuropeanDatum1950() {
    return EuropeanDatum1950.copy();
  }

  /**
   * @return the EuropeanLibyanDatum1979
   */
  public ProjectionInfo getEuropeanLibyanDatum1979() {
    return EuropeanLibyanDatum1979.copy();
  }

  /**
   * @return the Garoua
   */
  public ProjectionInfo getGaroua() {
    return Garoua.copy();
  }

  /**
   * @return the Hartebeesthoek1994
   */
  public ProjectionInfo getHartebeesthoek1994() {
    return Hartebeesthoek1994.copy();
  }

  /**
   * @return the Kousseri
   */
  public ProjectionInfo getKousseri() {
    return Kousseri.copy();
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
   * @return the Leigon
   */
  public ProjectionInfo getLeigon() {
    return Leigon.copy();
  }

  /**
   * @return the Liberia1964
   */
  public ProjectionInfo getLiberia1964() {
    return Liberia1964.copy();
  }

  /**
   * @return the Locodjo1965
   */
  public ProjectionInfo getLocodjo1965() {
    return Locodjo1965.copy();
  }

  /**
   * @return the Lome
   */
  public ProjectionInfo getLome() {
    return Lome.copy();
  }

  /**
   * @return the Madzansua
   */
  public ProjectionInfo getMadzansua() {
    return Madzansua.copy();
  }

  /**
   * @return the Mahe1971
   */
  public ProjectionInfo getMahe1971() {
    return Mahe1971.copy();
  }

  /**
   * @return the Malongo1987
   */
  public ProjectionInfo getMalongo1987() {
    return Malongo1987.copy();
  }

  /**
   * @return the Manoca
   */
  public ProjectionInfo getManoca() {
    return Manoca.copy();
  }

  /**
   * @return the Manoca1962
   */
  public ProjectionInfo getManoca1962() {
    return Manoca1962.copy();
  }

  /**
   * @return the Massawa
   */
  public ProjectionInfo getMassawa() {
    return Massawa.copy();
  }

  /**
   * @return the Merchich
   */
  public ProjectionInfo getMerchich() {
    return Merchich.copy();
  }

  /**
   * @return the Merchichdegrees
   */
  public ProjectionInfo getMerchichdegrees() {
    return Merchichdegrees.copy();
  }

  /**
   * @return the Mhast
   */
  public ProjectionInfo getMhast() {
    return Mhast.copy();
  }

  /**
   * @return the Minna
   */
  public ProjectionInfo getMinna() {
    return Minna.copy();
  }

  /**
   * @return the Moznet
   */
  public ProjectionInfo getMoznet() {
    return Moznet.copy();
  }

  /**
   * @return the Mporaloko
   */
  public ProjectionInfo getMporaloko() {
    return Mporaloko.copy();
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
   * @return the NordSahara1959
   */
  public ProjectionInfo getNordSahara1959() {
    return NordSahara1959.copy();
  }

  /**
   * @return the NordSahara1959Paris
   */
  public ProjectionInfo getNordSahara1959Paris() {
    return NordSahara1959Paris.copy();
  }

  /**
   * @return the Observatario
   */
  public ProjectionInfo getObservatario() {
    return Observatario.copy();
  }

  /**
   * @return the Oman
   */
  public ProjectionInfo getOman() {
    return Oman.copy();
  }

  /**
   * @return the PDO1993
   */
  public ProjectionInfo getPDO1993() {
    return PDO1993.copy();
  }

  /**
   * @return the Palestine1923
   */
  public ProjectionInfo getPalestine1923() {
    return Palestine1923.copy();
  }

  /**
   * @return the Point58
   */
  public ProjectionInfo getPoint58() {
    return Point58.copy();
  }

  /**
   * @return the PointeNoire
   */
  public ProjectionInfo getPointeNoire() {
    return PointeNoire.copy();
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
   * @return the Schwarzeck
   */
  public ProjectionInfo getSchwarzeck() {
    return Schwarzeck.copy();
  }

  /**
   * @return the SierraLeone1924
   */
  public ProjectionInfo getSierraLeone1924() {
    return SierraLeone1924.copy();
  }

  /**
   * @return the SierraLeone1960
   */
  public ProjectionInfo getSierraLeone1960() {
    return SierraLeone1960.copy();
  }

  /**
   * @return the SierraLeone1968
   */
  public ProjectionInfo getSierraLeone1968() {
    return SierraLeone1968.copy();
  }

  /**
   * @return the SouthYemen
   */
  public ProjectionInfo getSouthYemen() {
    return SouthYemen.copy();
  }

  /**
   * @return the Sudan
   */
  public ProjectionInfo getSudan() {
    return Sudan.copy();
  }

  /**
   * @return the Tananarive1925
   */
  public ProjectionInfo getTananarive1925() {
    return Tananarive1925.copy();
  }

  /**
   * @return the Tananarive1925Paris
   */
  public ProjectionInfo getTananarive1925Paris() {
    return Tananarive1925Paris.copy();
  }

  /**
   * @return the Tete
   */
  public ProjectionInfo getTete() {
    return Tete.copy();
  }

  /**
   * @return the TrucialCoast1948
   */
  public ProjectionInfo getTrucialCoast1948() {
    return TrucialCoast1948.copy();
  }

  /**
   * @return the Voirol1875
   */
  public ProjectionInfo getVoirol1875() {
    return Voirol1875.copy();
  }

  /**
   * @return the Voirol1875Paris
   */
  public ProjectionInfo getVoirol1875Paris() {
    return Voirol1875Paris.copy();
  }

  /**
   * @return the Voirol1875degrees
   */
  public ProjectionInfo getVoirol1875degrees() {
    return Voirol1875degrees.copy();
  }

  /**
   * @return the VoirolUnifie1960
   */
  public ProjectionInfo getVoirolUnifie1960() {
    return VoirolUnifie1960.copy();
  }

  /**
   * @return the VoirolUnifie1960Paris
   */
  public ProjectionInfo getVoirolUnifie1960Paris() {
    return VoirolUnifie1960Paris.copy();
  }

  /**
   * @return the VoirolUnifie1960degrees
   */
  public ProjectionInfo getVoirolUnifie1960degrees() {
    return VoirolUnifie1960degrees.copy();
  }

  /**
   * @return the YemenNGN1996
   */
  public ProjectionInfo getYemenNGN1996() {
    return YemenNGN1996.copy();
  }

  /**
   * @return the yoff
   */
  public ProjectionInfo getYoff() {
    return yoff.copy();
  }
}
