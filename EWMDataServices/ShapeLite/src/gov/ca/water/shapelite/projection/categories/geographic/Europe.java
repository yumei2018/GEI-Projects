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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:08:55 PM
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
/// Europe
/// </summary>

public class Europe extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo ATFParis;
  private final ProjectionInfo Albanian1987;
  private final ProjectionInfo Belge1950Brussels;
  private final ProjectionInfo Belge1972;
  private final ProjectionInfo Bern1898;
  private final ProjectionInfo Bern1898Bern;
  private final ProjectionInfo Bern1938;
  private final ProjectionInfo CH1903;
  private final ProjectionInfo Datum73;
  private final ProjectionInfo DatumLisboaBessel;
  private final ProjectionInfo DatumLisboaHayford;
  private final ProjectionInfo DealulPiscului1933Romania;
  private final ProjectionInfo DealulPiscului1970Romania;
  private final ProjectionInfo DeutscheHauptdreiecksnetz;
  private final ProjectionInfo DutchRD;
  private final ProjectionInfo ETRF1989;
  private final ProjectionInfo ETRS1989;
  private final ProjectionInfo EUREFFIN;
  private final ProjectionInfo Estonia1937;
  private final ProjectionInfo Estonia1992;
  private final ProjectionInfo Estonia1997;
  private final ProjectionInfo European1979;
  private final ProjectionInfo EuropeanDatum1950;
  private final ProjectionInfo EuropeanDatum1987;
  private final ProjectionInfo Greek;
  private final ProjectionInfo GreekAthens;
  private final ProjectionInfo GreekGeodeticRefSystem1987;
  private final ProjectionInfo Hermannskogel;
  private final ProjectionInfo Hjorsey1955;
  private final ProjectionInfo HungarianDatum1972;
  private final ProjectionInfo IRENET95;
  private final ProjectionInfo ISN1993;
  private final ProjectionInfo Kartastokoordinaattijarjestelma;
  private final ProjectionInfo LKS1992;
  private final ProjectionInfo LKS1994;
  private final ProjectionInfo Lisbon;
  private final ProjectionInfo Lisbon1890;
  private final ProjectionInfo Lisbon1890Lisbon;
  private final ProjectionInfo LisbonLisbon;
  private final ProjectionInfo Luxembourg1930;
  private final ProjectionInfo MGIFerro;
  private final ProjectionInfo Madrid1870Madrid;
  private final ProjectionInfo MilitarGeographischeInstitut;
  private final ProjectionInfo MonteMario;
  private final ProjectionInfo MonteMarioRome;
  private final ProjectionInfo NGO1948;
  private final ProjectionInfo NGO1948Oslo;
  private final ProjectionInfo NTFParis;
  private final ProjectionInfo NorddeGuerreParis;
  private final ProjectionInfo NouvelleTriangulationFrancaise;
  private final ProjectionInfo OSGB1936;
  private final ProjectionInfo OSGB1970SN;
  private final ProjectionInfo OSNI1952;
  private final ProjectionInfo OSSN1980;
  private final ProjectionInfo Pulkovo1942;
  private final ProjectionInfo Pulkovo1942Adj1958;
  private final ProjectionInfo Pulkovo1942Adj1983;
  private final ProjectionInfo Pulkovo1995;
  private final ProjectionInfo Qornoq;
  private final ProjectionInfo RGF1993;
  private final ProjectionInfo RT1990;
  private final ProjectionInfo RT38;
  private final ProjectionInfo RT38Stockholm;
  private final ProjectionInfo ReseauNationalBelge1950;
  private final ProjectionInfo ReseauNationalBelge1972;
  private final ProjectionInfo Reykjavik1900;
  private final ProjectionInfo Roma1940;
  private final ProjectionInfo S42Hungary;
  private final ProjectionInfo SJTSK;
  private final ProjectionInfo SWEREF99;
  private final ProjectionInfo SwissTRF1995;
  private final ProjectionInfo TM65;
  private final ProjectionInfo TM75;

        //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of Europe
   */
  public Europe() {
    Albanian1987 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    ATFParis = ProjectionInfo.fromProj4String("+proj=longlat +a=6376523 +b=6355862.933255573 +pm=2.337229166666667 +no_defs ").orElse(null);
    Belge1950Brussels = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +pm=4.367975 +no_defs ").orElse(null);
    Belge1972 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Bern1898 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Bern1898Bern = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +pm=7.439583333333333 +no_defs ").orElse(null);
    Bern1938 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    CH1903 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Datum73 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    DatumLisboaBessel = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    DatumLisboaHayford = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    DealulPiscului1933Romania = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    DealulPiscului1970Romania = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    DeutscheHauptdreiecksnetz = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    //DutchRD = ProjectionInfo.fromProj4String("+proj=sterea +lat_0=52.15616055999986 +lon_0=5.387638888999871 +k=0.999908 +x_0=155000 +y_0=463000 +ellps=bessel +units=m +no_defs").orElse(null);
    DutchRD = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Estonia1937 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Estonia1992 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    Estonia1997 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    ETRF1989 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    ETRS1989 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    EUREFFIN = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    European1979 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    EuropeanDatum1950 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    EuropeanDatum1987 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Greek = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    GreekAthens = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +pm=23.7163375 +no_defs ").orElse(null);
    GreekGeodeticRefSystem1987 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    Hermannskogel = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Hjorsey1955 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    HungarianDatum1972 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS67 +no_defs ").orElse(null);
    IRENET95 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    ISN1993 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    Kartastokoordinaattijarjestelma = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Lisbon = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    LisbonLisbon = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +pm=-9.131906111111112 +no_defs ").orElse(null);
    Lisbon1890 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    Lisbon1890Lisbon = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +pm=-9.131906111111112 +no_defs ").orElse(null);
    LKS1992 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    LKS1994 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    Luxembourg1930 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Madrid1870Madrid = ProjectionInfo.fromProj4String("+proj=longlat +a=6378298.3 +b=6356657.142669561 +pm=-3.687938888888889 +no_defs ").orElse(null);
    MGIFerro = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +pm=-17.66666666666667 +no_defs ").orElse(null);
    MilitarGeographischeInstitut = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    MonteMario = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    MonteMarioRome = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +pm=12.45233333333333 +no_defs ").orElse(null);
    NGO1948 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377492.018 +b=6356173.508712696 +no_defs ").orElse(null);
    NGO1948Oslo = ProjectionInfo.fromProj4String("+proj=longlat +a=6377492.018 +b=6356173.508712696 +pm=10.72291666666667 +no_defs ").orElse(null);
    NorddeGuerreParis = ProjectionInfo.fromProj4String("+proj=longlat +a=6376523 +b=6355862.933255573 +pm=2.337229166666667 +no_defs ").orElse(null);
    NouvelleTriangulationFrancaise = ProjectionInfo.fromProj4String("+proj=longlat +a=6378249.2 +b=6356514.999904194 +no_defs ").orElse(null);
    NTFParis = ProjectionInfo.fromProj4String("+proj=longlat +a=6378249.2 +b=6356514.999904194 +pm=2.337229166666667 +no_defs ").orElse(null);
    OSSN1980 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=airy +no_defs ").orElse(null);
    OSGB1936 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=airy +no_defs ").orElse(null);
    OSGB1970SN = ProjectionInfo.fromProj4String("+proj=longlat +ellps=airy +no_defs ").orElse(null);
    OSNI1952 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=airy +no_defs ").orElse(null);
    Pulkovo1942 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    Pulkovo1942Adj1958 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    Pulkovo1942Adj1983 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    Pulkovo1995 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    Qornoq = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    ReseauNationalBelge1950 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    ReseauNationalBelge1972 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Reykjavik1900 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377019.27 +b=6355762.5391 +no_defs ").orElse(null);
    RGF1993 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    Roma1940 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    RT1990 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    RT38 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    RT38Stockholm = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +pm=18.05827777777778 +no_defs ").orElse(null);
    S42Hungary = ProjectionInfo.fromProj4String("+proj=longlat +ellps=krass +no_defs ").orElse(null);
    SJTSK = ProjectionInfo.fromProj4String("+proj=longlat +ellps=bessel +no_defs ").orElse(null);
    SWEREF99 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    SwissTRF1995 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    TM65 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377340.189 +b=6356034.447938534 +no_defs ").orElse(null);
    TM75 = ProjectionInfo.fromProj4String("+proj=longlat +a=6377340.189 +b=6356034.447938534 +no_defs ").orElse(null);

    Albanian1987.setLatLon(true);
    ATFParis.setLatLon(true);
    Belge1950Brussels.setLatLon(true);
    Belge1972.setLatLon(true);
    Bern1898.setLatLon(true);
    Bern1898Bern.setLatLon(true);
    Bern1938.setLatLon(true);
    CH1903.setLatLon(true);
    Datum73.setLatLon(true);
    DatumLisboaBessel.setLatLon(true);
    DatumLisboaHayford.setLatLon(true);
    DealulPiscului1933Romania.setLatLon(true);
    DealulPiscului1970Romania.setLatLon(true);
    DeutscheHauptdreiecksnetz.setLatLon(true);
    DutchRD.setLatLon(true);
    DutchRD.setLatLon(true);
    Estonia1937.setLatLon(true);
    Estonia1992.setLatLon(true);
    Estonia1997.setLatLon(true);
    ETRF1989.setLatLon(true);
    ETRS1989.setLatLon(true);
    EUREFFIN.setLatLon(true);
    European1979.setLatLon(true);
    EuropeanDatum1950.setLatLon(true);
    EuropeanDatum1987.setLatLon(true);
    Greek.setLatLon(true);
    GreekAthens.setLatLon(true);
    GreekGeodeticRefSystem1987.setLatLon(true);
    Hermannskogel.setLatLon(true);
    Hjorsey1955.setLatLon(true);
    HungarianDatum1972.setLatLon(true);
    IRENET95.setLatLon(true);
    ISN1993.setLatLon(true);
    Kartastokoordinaattijarjestelma.setLatLon(true);
    Lisbon.setLatLon(true);
    LisbonLisbon.setLatLon(true);
    Lisbon1890.setLatLon(true);
    Lisbon1890Lisbon.setLatLon(true);
    LKS1992.setLatLon(true);
    LKS1994.setLatLon(true);
    Luxembourg1930.setLatLon(true);
    Madrid1870Madrid.setLatLon(true);
    MGIFerro.setLatLon(true);
    MilitarGeographischeInstitut.setLatLon(true);
    MonteMario.setLatLon(true);
    MonteMarioRome.setLatLon(true);
    NGO1948.setLatLon(true);
    NGO1948Oslo.setLatLon(true);
    NorddeGuerreParis.setLatLon(true);
    NouvelleTriangulationFrancaise.setLatLon(true);
    NTFParis.setLatLon(true);
    OSSN1980.setLatLon(true);
    OSGB1936.setLatLon(true);
    OSGB1970SN.setLatLon(true);
    OSNI1952.setLatLon(true);
    Pulkovo1942.setLatLon(true);
    Pulkovo1942Adj1958.setLatLon(true);
    Pulkovo1942Adj1983.setLatLon(true);
    Pulkovo1995.setLatLon(true);
    Qornoq.setLatLon(true);
    ReseauNationalBelge1950.setLatLon(true);
    ReseauNationalBelge1972.setLatLon(true);
    Reykjavik1900.setLatLon(true);
    RGF1993.setLatLon(true);
    Roma1940.setLatLon(true);
    RT1990.setLatLon(true);
    RT38.setLatLon(true);
    RT38Stockholm.setLatLon(true);
    S42Hungary.setLatLon(true);
    SJTSK.setLatLon(true);
    SWEREF99.setLatLon(true);
    SwissTRF1995.setLatLon(true);
    TM65.setLatLon(true);
    TM75.setLatLon(true);

    Albanian1987.getGeographicInfo().setName("GCS_Albanian_1987");
    ATFParis.getGeographicInfo().setName("GCS_ATF_Paris");
    Belge1950Brussels.getGeographicInfo().setName("GCS_Belge_1950_Brussels");
    Belge1972.getGeographicInfo().setName("GCS_Belge_1972");
    Bern1898.getGeographicInfo().setName("GCS_Bern_1898");
    Bern1898Bern.getGeographicInfo().setName("GCS_Bern_1898_Bern");
    Bern1938.getGeographicInfo().setName("GCS_Bern_1938");
    CH1903.getGeographicInfo().setName("GCS_CH1903");
    Datum73.getGeographicInfo().setName("GCS_Datum_73");
    DatumLisboaBessel.getGeographicInfo().setName("GCS_Datum_Lisboa_Bessel");
    DatumLisboaHayford.getGeographicInfo().setName("GCS_Datum_Lisboa_Hayford");
    DealulPiscului1933Romania.getGeographicInfo().setName("GCS_Dealul_Piscului_1933");
    DealulPiscului1970Romania.getGeographicInfo().setName("GCS_Dealul_Piscului_1970");
    DeutscheHauptdreiecksnetz.getGeographicInfo().setName("GCS_Deutsches_Hauptdreiecksnetz");
    Estonia1937.getGeographicInfo().setName("GCS_Estonia_1937");
    Estonia1992.getGeographicInfo().setName("GCS_Estonia_1992");
    Estonia1997.getGeographicInfo().setName("GCS_Estonia_1997");
    ETRF1989.getGeographicInfo().setName("GCS_ETRF_1989");
    ETRS1989.getGeographicInfo().setName("GCS_ETRS_1989");
    EUREFFIN.getGeographicInfo().setName("GCS_EUREF_FIN");
    European1979.getGeographicInfo().setName("GCS_European_1979");
    EuropeanDatum1950.getGeographicInfo().setName("GCS_European_1950");
    EuropeanDatum1987.getGeographicInfo().setName("GCS_European_1987");
    Greek.getGeographicInfo().setName("GCS_Greek");
    GreekAthens.getGeographicInfo().setName("GCS_Greek_Athens");
    GreekGeodeticRefSystem1987.getGeographicInfo().setName("GCS_GGRS_1987");
    Hermannskogel.getGeographicInfo().setName("GCS_Hermannskogel");
    Hjorsey1955.getGeographicInfo().setName("GCS_Hjorsey_1955");
    HungarianDatum1972.getGeographicInfo().setName("GCS_Hungarian_1972");
    IRENET95.getGeographicInfo().setName("GCS_IRENET95");
    ISN1993.getGeographicInfo().setName("GCS_ISN_1993");
    Kartastokoordinaattijarjestelma.getGeographicInfo().setName("GCS_KKJ");
    Lisbon.getGeographicInfo().setName("GCS_Lisbon");
    LisbonLisbon.getGeographicInfo().setName("GCS_Lisbon_Lisbon");
    Lisbon1890.getGeographicInfo().setName("GCS_Lisbon_1890");
    Lisbon1890Lisbon.getGeographicInfo().setName("GCS_Lisbon_1890_Lisbon");
    LKS1992.getGeographicInfo().setName("GCS_LKS_1992");
    LKS1994.getGeographicInfo().setName("GCS_LKS_1994");
    Luxembourg1930.getGeographicInfo().setName("GCS_Luxembourg_1930");
    Madrid1870Madrid.getGeographicInfo().setName("GCS_Madrid_1870_Madrid");
    MGIFerro.getGeographicInfo().setName("GCS_MGI_Ferro");
    MilitarGeographischeInstitut.getGeographicInfo().setName("GCS_MGI");
    MonteMario.getGeographicInfo().setName("GCS_Monte_Mario");
    MonteMarioRome.getGeographicInfo().setName("GCS_Monte_Mario_Rome");
    NGO1948.getGeographicInfo().setName("GCS_NGO_1948");
    NGO1948Oslo.getGeographicInfo().setName("GCS_NGO_1948_Oslo");
    NorddeGuerreParis.getGeographicInfo().setName("GCS_Nord_de_Guerre_Paris");
    NouvelleTriangulationFrancaise.getGeographicInfo().setName("GCS_NTF");
    NTFParis.getGeographicInfo().setName("GCS_NTF_Paris");
    OSSN1980.getGeographicInfo().setName("GCS_OS_SN_1980");
    OSGB1936.getGeographicInfo().setName("GCS_OSGB_1936");
    OSGB1970SN.getGeographicInfo().setName("GCS_OSGB_1970_SN");
    OSNI1952.getGeographicInfo().setName("GCS_OSNI_1952");
    Pulkovo1942.getGeographicInfo().setName("GCS_Pulkovo_1942");
    Pulkovo1942Adj1958.getGeographicInfo().setName("GCS_Pulkovo_1942_Adj_1958");
    Pulkovo1942Adj1983.getGeographicInfo().setName("GCS_Pulkovo_1942_Adj_1983");
    Pulkovo1995.getGeographicInfo().setName("GCS_Pulkovo_1995");
    Qornoq.getGeographicInfo().setName("GCS_Qornoq");
    ReseauNationalBelge1950.getGeographicInfo().setName("GCS_Belge_1950");
    ReseauNationalBelge1972.getGeographicInfo().setName("GCS_Belge_1972");
    Reykjavik1900.getGeographicInfo().setName("GCS_Reykjavik_1900");
    RGF1993.getGeographicInfo().setName("GCS_RGF_1993");
    Roma1940.getGeographicInfo().setName("GCS_Roma_1940");
    RT1990.getGeographicInfo().setName("GCS_RT_1990");
    RT38.getGeographicInfo().setName("GCS_RT38");
    RT38Stockholm.getGeographicInfo().setName("GCS_RT38_Stockholm");
    S42Hungary.getGeographicInfo().setName("GCS_S42_Hungary");
    SJTSK.getGeographicInfo().setName("GCS_S_JTSK");
    SWEREF99.getGeographicInfo().setName("GCS_SWEREF99");
    SwissTRF1995.getGeographicInfo().setName("GCS_Swiss_TRF_1995");
    TM65.getGeographicInfo().setName("GCS_TM65");
    TM75.getGeographicInfo().setName("GCS_TM75");

    Albanian1987.setName("GCS_Albanian_1987");
    ATFParis.setName("GCS_ATF_Paris");
    Belge1950Brussels.setName("GCS_Belge_1950_Brussels");
    Belge1972.setName("GCS_Belge_1972");
    Bern1898.setName("GCS_Bern_1898");
    Bern1898Bern.setName("GCS_Bern_1898_Bern");
    Bern1938.setName("GCS_Bern_1938");
    CH1903.setName("GCS_CH1903");
    Datum73.setName("GCS_Datum_73");
    DatumLisboaBessel.setName("GCS_Datum_Lisboa_Bessel");
    DatumLisboaHayford.setName("GCS_Datum_Lisboa_Hayford");
    DealulPiscului1933Romania.setName("GCS_Dealul_Piscului_1933");
    DealulPiscului1970Romania.setName("GCS_Dealul_Piscului_1970");
    DeutscheHauptdreiecksnetz.setName("GCS_Deutsches_Hauptdreiecksnetz");
    Estonia1937.setName("GCS_Estonia_1937");
    Estonia1992.setName("GCS_Estonia_1992");
    Estonia1997.setName("GCS_Estonia_1997");
    ETRF1989.setName("GCS_ETRF_1989");
    ETRS1989.setName("GCS_ETRS_1989");
    EUREFFIN.setName("GCS_EUREF_FIN");
    European1979.setName("GCS_European_1979");
    EuropeanDatum1950.setName("GCS_European_1950");
    EuropeanDatum1987.setName("GCS_European_1987");
    Greek.setName("GCS_Greek");
    GreekAthens.setName("GCS_Greek_Athens");
    GreekGeodeticRefSystem1987.setName("GCS_GGRS_1987");
    Hermannskogel.setName("GCS_Hermannskogel");
    Hjorsey1955.setName("GCS_Hjorsey_1955");
    HungarianDatum1972.setName("GCS_Hungarian_1972");
    IRENET95.setName("GCS_IRENET95");
    ISN1993.setName("GCS_ISN_1993");
    Kartastokoordinaattijarjestelma.setName("GCS_KKJ");
    Lisbon.setName("GCS_Lisbon");
    LisbonLisbon.setName("GCS_Lisbon_Lisbon");
    Lisbon1890.setName("GCS_Lisbon_1890");
    Lisbon1890Lisbon.setName("GCS_Lisbon_1890_Lisbon");
    LKS1992.setName("GCS_LKS_1992");
    LKS1994.setName("GCS_LKS_1994");
    Luxembourg1930.setName("GCS_Luxembourg_1930");
    Madrid1870Madrid.setName("GCS_Madrid_1870_Madrid");
    MGIFerro.setName("GCS_MGI_Ferro");
    MilitarGeographischeInstitut.setName("GCS_MGI");
    MonteMario.setName("GCS_Monte_Mario");
    MonteMarioRome.setName("GCS_Monte_Mario_Rome");
    NGO1948.setName("GCS_NGO_1948");
    NGO1948Oslo.setName("GCS_NGO_1948_Oslo");
    NorddeGuerreParis.setName("GCS_Nord_de_Guerre_Paris");
    NouvelleTriangulationFrancaise.setName("GCS_NTF");
    NTFParis.setName("GCS_NTF_Paris");
    OSSN1980.setName("GCS_OS_SN_1980");
    OSGB1936.setName("GCS_OSGB_1936");
    OSGB1970SN.setName("GCS_OSGB_1970_SN");
    OSNI1952.setName("GCS_OSNI_1952");
    Pulkovo1942.setName("GCS_Pulkovo_1942");
    Pulkovo1942Adj1958.setName("GCS_Pulkovo_1942_Adj_1958");
    Pulkovo1942Adj1983.setName("GCS_Pulkovo_1942_Adj_1983");
    Pulkovo1995.setName("GCS_Pulkovo_1995");
    Qornoq.setName("GCS_Qornoq");
    ReseauNationalBelge1950.setName("GCS_Belge_1950");
    ReseauNationalBelge1972.setName("GCS_Belge_1972");
    Reykjavik1900.setName("GCS_Reykjavik_1900");
    RGF1993.setName("GCS_RGF_1993");
    Roma1940.setName("GCS_Roma_1940");
    RT1990.setName("GCS_RT_1990");
    RT38.setName("GCS_RT38");
    RT38Stockholm.setName("GCS_RT38_Stockholm");
    S42Hungary.setName("GCS_S42_Hungary");
    SJTSK.setName("GCS_S_JTSK");
    SWEREF99.setName("GCS_SWEREF99");
    SwissTRF1995.setName("GCS_Swiss_TRF_1995");
    TM65.setName("GCS_TM65");
    TM75.setName("GCS_TM75");

    Albanian1987.getGeographicInfo().getDatum().setName("D_Albanian_1987");
    ATFParis.getGeographicInfo().getDatum().setName("D_ATF");
    Belge1950Brussels.getGeographicInfo().getDatum().setName("D_Belge_1950");
    Belge1972.getGeographicInfo().getDatum().setName("D_Belge_1972");
    Bern1898.getGeographicInfo().getDatum().setName("D_Bern_1898");
    Bern1898Bern.getGeographicInfo().getDatum().setName("D_Bern_1898");
    Bern1938.getGeographicInfo().getDatum().setName("D_Bern_1938");
    CH1903.getGeographicInfo().getDatum().setName("D_CH1903");
    Datum73.getGeographicInfo().getDatum().setName("D_Datum_73");
    DatumLisboaBessel.getGeographicInfo().getDatum().setName("D_Datum_Lisboa_Bessel");
    DatumLisboaHayford.getGeographicInfo().getDatum().setName("D_Datum_Lisboa_Hayford");
    DealulPiscului1933Romania.getGeographicInfo().getDatum().setName("D_Dealul_Piscului_1933");
    DealulPiscului1970Romania.getGeographicInfo().getDatum().setName("D_Dealul_Piscului_1970");
    DeutscheHauptdreiecksnetz.getGeographicInfo().getDatum().setName("D_Deutsches_Hauptdreiecksnetz");
    Estonia1937.getGeographicInfo().getDatum().setName("D_Estonia_1937");
    Estonia1992.getGeographicInfo().getDatum().setName("D_Estonia_1992");
    Estonia1997.getGeographicInfo().getDatum().setName("D_Estonia_1997");
    ETRF1989.getGeographicInfo().getDatum().setName("D_ETRF_1989");
    ETRS1989.getGeographicInfo().getDatum().setName("D_ETRS_1989");
    EUREFFIN.getGeographicInfo().getDatum().setName("D_ETRS_1989");
    European1979.getGeographicInfo().getDatum().setName("D_European_1979");
    EuropeanDatum1950.getGeographicInfo().getDatum().setName("D_European_1950");
    EuropeanDatum1987.getGeographicInfo().getDatum().setName("D_European_1987");
    Greek.getGeographicInfo().getDatum().setName("D_Greek");
    GreekAthens.getGeographicInfo().getDatum().setName("D_Greek");
    GreekGeodeticRefSystem1987.getGeographicInfo().getDatum().setName("D_GGRS_1987");
    Hermannskogel.getGeographicInfo().getDatum().setName("D_Hermannskogel");
    Hjorsey1955.getGeographicInfo().getDatum().setName("D_Hjorsey_1955");
    HungarianDatum1972.getGeographicInfo().getDatum().setName("D_Hungarian_1972");
    IRENET95.getGeographicInfo().getDatum().setName("D_IRENET95");
    ISN1993.getGeographicInfo().getDatum().setName("D_Islands_Network_1993");
    Kartastokoordinaattijarjestelma.getGeographicInfo().getDatum().setName("D_KKJ");
    Lisbon.getGeographicInfo().getDatum().setName("D_Lisbon");
    LisbonLisbon.getGeographicInfo().getDatum().setName("D_Lisbon");
    Lisbon1890.getGeographicInfo().getDatum().setName("D_Lisbon_1890");
    Lisbon1890Lisbon.getGeographicInfo().getDatum().setName("D_Lisbon_1890");
    LKS1992.getGeographicInfo().getDatum().setName("D_Latvia_1992");
    LKS1994.getGeographicInfo().getDatum().setName("D_Lithuania_1994");
    Luxembourg1930.getGeographicInfo().getDatum().setName("D_Luxembourg_1930");
    Madrid1870Madrid.getGeographicInfo().getDatum().setName("D_Madrid_1870");
    MGIFerro.getGeographicInfo().getDatum().setName("D_MGI");
    MilitarGeographischeInstitut.getGeographicInfo().getDatum().setName("D_MGI");
    MonteMario.getGeographicInfo().getDatum().setName("D_Monte_Mario");
    MonteMarioRome.getGeographicInfo().getDatum().setName("D_Monte_Mario");
    NGO1948.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NGO1948Oslo.getGeographicInfo().getDatum().setName("D_NGO_1948");
    NorddeGuerreParis.getGeographicInfo().getDatum().setName("D_Nord_de_Guerre");
    NouvelleTriangulationFrancaise.getGeographicInfo().getDatum().setName("D_NTF");
    NTFParis.getGeographicInfo().getDatum().setName("D_NTF");
    OSSN1980.getGeographicInfo().getDatum().setName("D_OS_SN_1980");
    OSGB1936.getGeographicInfo().getDatum().setName("D_OSGB_1936");
    OSGB1970SN.getGeographicInfo().getDatum().setName("D_OSGB_1970_SN");
    OSNI1952.getGeographicInfo().getDatum().setName("D_OSNI_1952");
    Pulkovo1942.getGeographicInfo().getDatum().setName("D_Pulkovo_1942");
    Pulkovo1942Adj1958.getGeographicInfo().getDatum().setName("D_Pulkovo_1942_Adj_1958");
    Pulkovo1942Adj1983.getGeographicInfo().getDatum().setName("D_Pulkovo_1942_Adj_1983");
    Pulkovo1995.getGeographicInfo().getDatum().setName("D_Pulkovo_1995");
    Qornoq.getGeographicInfo().getDatum().setName("D_Qornoq");
    ReseauNationalBelge1950.getGeographicInfo().getDatum().setName("D_Belge_1950");
    ReseauNationalBelge1972.getGeographicInfo().getDatum().setName("D_Belge_1972");
    Reykjavik1900.getGeographicInfo().getDatum().setName("D_Reykjavik_1900");
    RGF1993.getGeographicInfo().getDatum().setName("D_RGF_1993");
    Roma1940.getGeographicInfo().getDatum().setName("D_Roma_1940");
    RT1990.getGeographicInfo().getDatum().setName("D_RT_1990");
    RT38.getGeographicInfo().getDatum().setName("D_Stockholm_1938");
    RT38Stockholm.getGeographicInfo().getDatum().setName("D_Stockholm_1938");
    S42Hungary.getGeographicInfo().getDatum().setName("D_S42_Hungary");
    SJTSK.getGeographicInfo().getDatum().setName("D_S_JTSK");
    SWEREF99.getGeographicInfo().getDatum().setName("D_SWEREF99");
    SwissTRF1995.getGeographicInfo().getDatum().setName("D_Swiss_TRF_1995");
    TM65.getGeographicInfo().getDatum().setName("D_TM65");
    TM75.getGeographicInfo().getDatum().setName("D_TM75");
  }

  //</editor-fold>

  /**
   * @return the ATFParis
   */
  public ProjectionInfo getATFParis() {
    return ATFParis.copy();
  }

  /**
   * @return the Albanian1987
   */
  public ProjectionInfo getAlbanian1987() {
    return Albanian1987.copy();
  }

  /**
   * @return the Belge1950Brussels
   */
  public ProjectionInfo getBelge1950Brussels() {
    return Belge1950Brussels.copy();
  }

  /**
   * @return the Belge1972
   */
  public ProjectionInfo getBelge1972() {
    return Belge1972.copy();
  }

  /**
   * @return the Bern1898
   */
  public ProjectionInfo getBern1898() {
    return Bern1898.copy();
  }

  /**
   * @return the Bern1898Bern
   */
  public ProjectionInfo getBern1898Bern() {
    return Bern1898Bern.copy();
  }

  /**
   * @return the Bern1938
   */
  public ProjectionInfo getBern1938() {
    return Bern1938.copy();
  }

  /**
   * @return the CH1903
   */
  public ProjectionInfo getCH1903() {
    return CH1903.copy();
  }

  /**
   * @return the Datum73
   */
  public ProjectionInfo getDatum73() {
    return Datum73.copy();
  }

  /**
   * @return the DatumLisboaBessel
   */
  public ProjectionInfo getDatumLisboaBessel() {
    return DatumLisboaBessel.copy();
  }

  /**
   * @return the DatumLisboaHayford
   */
  public ProjectionInfo getDatumLisboaHayford() {
    return DatumLisboaHayford.copy();
  }

  /**
   * @return the DealulPiscului1933Romania
   */
  public ProjectionInfo getDealulPiscului1933Romania() {
    return DealulPiscului1933Romania.copy();
  }

  /**
   * @return the DealulPiscului1970Romania
   */
  public ProjectionInfo getDealulPiscului1970Romania() {
    return DealulPiscului1970Romania.copy();
  }

  /**
   * @return the DeutscheHauptdreiecksnetz
   */
  public ProjectionInfo getDeutscheHauptdreiecksnetz() {
    return DeutscheHauptdreiecksnetz.copy();
  }

  /**
   * @return the DutchRD
   */
  public ProjectionInfo getDutchRD() {
    return DutchRD.copy();
  }

  /**
   * @return the ETRF1989
   */
  public ProjectionInfo getETRF1989() {
    return ETRF1989.copy();
  }

  /**
   * @return the ETRS1989
   */
  public ProjectionInfo getETRS1989() {
    return ETRS1989.copy();
  }

  /**
   * @return the EUREFFIN
   */
  public ProjectionInfo getEUREFFIN() {
    return EUREFFIN.copy();
  }

  /**
   * @return the Estonia1937
   */
  public ProjectionInfo getEstonia1937() {
    return Estonia1937.copy();
  }

  /**
   * @return the Estonia1992
   */
  public ProjectionInfo getEstonia1992() {
    return Estonia1992.copy();
  }

  /**
   * @return the Estonia1997
   */
  public ProjectionInfo getEstonia1997() {
    return Estonia1997.copy();
  }

  /**
   * @return the European1979
   */
  public ProjectionInfo getEuropean1979() {
    return European1979.copy();
  }

  /**
   * @return the EuropeanDatum1950
   */
  public ProjectionInfo getEuropeanDatum1950() {
    return EuropeanDatum1950.copy();
  }

  /**
   * @return the EuropeanDatum1987
   */
  public ProjectionInfo getEuropeanDatum1987() {
    return EuropeanDatum1987.copy();
  }

  /**
   * @return the Greek
   */
  public ProjectionInfo getGreek() {
    return Greek.copy();
  }

  /**
   * @return the GreekAthens
   */
  public ProjectionInfo getGreekAthens() {
    return GreekAthens.copy();
  }

  /**
   * @return the GreekGeodeticRefSystem1987
   */
  public ProjectionInfo getGreekGeodeticRefSystem1987() {
    return GreekGeodeticRefSystem1987.copy();
  }

  /**
   * @return the Hermannskogel
   */
  public ProjectionInfo getHermannskogel() {
    return Hermannskogel.copy();
  }

  /**
   * @return the Hjorsey1955
   */
  public ProjectionInfo getHjorsey1955() {
    return Hjorsey1955.copy();
  }

  /**
   * @return the HungarianDatum1972
   */
  public ProjectionInfo getHungarianDatum1972() {
    return HungarianDatum1972.copy();
  }

  /**
   * @return the IRENET95
   */
  public ProjectionInfo getIRENET95() {
    return IRENET95.copy();
  }

  /**
   * @return the ISN1993
   */
  public ProjectionInfo getISN1993() {
    return ISN1993.copy();
  }

  /**
   * @return the Kartastokoordinaattijarjestelma
   */
  public ProjectionInfo getKartastokoordinaattijarjestelma() {
    return Kartastokoordinaattijarjestelma.copy();
  }

  /**
   * @return the LKS1992
   */
  public ProjectionInfo getLKS1992() {
    return LKS1992.copy();
  }

  /**
   * @return the LKS1994
   */
  public ProjectionInfo getLKS1994() {
    return LKS1994.copy();
  }

  /**
   * @return the Lisbon
   */
  public ProjectionInfo getLisbon() {
    return Lisbon.copy();
  }

  /**
   * @return the Lisbon1890
   */
  public ProjectionInfo getLisbon1890() {
    return Lisbon1890.copy();
  }

  /**
   * @return the Lisbon1890Lisbon
   */
  public ProjectionInfo getLisbon1890Lisbon() {
    return Lisbon1890Lisbon.copy();
  }

  /**
   * @return the LisbonLisbon
   */
  public ProjectionInfo getLisbonLisbon() {
    return LisbonLisbon.copy();
  }

  /**
   * @return the Luxembourg1930
   */
  public ProjectionInfo getLuxembourg1930() {
    return Luxembourg1930.copy();
  }

  /**
   * @return the MGIFerro
   */
  public ProjectionInfo getMGIFerro() {
    return MGIFerro.copy();
  }

  /**
   * @return the Madrid1870Madrid
   */
  public ProjectionInfo getMadrid1870Madrid() {
    return Madrid1870Madrid.copy();
  }

  /**
   * @return the MilitarGeographischeInstitut
   */
  public ProjectionInfo getMilitarGeographischeInstitut() {
    return MilitarGeographischeInstitut.copy();
  }

  /**
   * @return the MonteMario
   */
  public ProjectionInfo getMonteMario() {
    return MonteMario.copy();
  }

  /**
   * @return the MonteMarioRome
   */
  public ProjectionInfo getMonteMarioRome() {
    return MonteMarioRome.copy();
  }

  /**
   * @return the NGO1948
   */
  public ProjectionInfo getNGO1948() {
    return NGO1948.copy();
  }

  /**
   * @return the NGO1948Oslo
   */
  public ProjectionInfo getNGO1948Oslo() {
    return NGO1948Oslo.copy();
  }

  /**
   * @return the NTFParis
   */
  public ProjectionInfo getNTFParis() {
    return NTFParis.copy();
  }

  /**
   * @return the NorddeGuerreParis
   */
  public ProjectionInfo getNorddeGuerreParis() {
    return NorddeGuerreParis.copy();
  }

  /**
   * @return the NouvelleTriangulationFrancaise
   */
  public ProjectionInfo getNouvelleTriangulationFrancaise() {
    return NouvelleTriangulationFrancaise.copy();
  }

  /**
   * @return the OSGB1936
   */
  public ProjectionInfo getOSGB1936() {
    return OSGB1936.copy();
  }

  /**
   * @return the OSGB1970SN
   */
  public ProjectionInfo getOSGB1970SN() {
    return OSGB1970SN.copy();
  }

  /**
   * @return the OSNI1952
   */
  public ProjectionInfo getOSNI1952() {
    return OSNI1952.copy();
  }

  /**
   * @return the OSSN1980
   */
  public ProjectionInfo getOSSN1980() {
    return OSSN1980.copy();
  }

  /**
   * @return the Pulkovo1942
   */
  public ProjectionInfo getPulkovo1942() {
    return Pulkovo1942.copy();
  }

  /**
   * @return the Pulkovo1942Adj1958
   */
  public ProjectionInfo getPulkovo1942Adj1958() {
    return Pulkovo1942Adj1958.copy();
  }

  /**
   * @return the Pulkovo1942Adj1983
   */
  public ProjectionInfo getPulkovo1942Adj1983() {
    return Pulkovo1942Adj1983.copy();
  }

  /**
   * @return the Pulkovo1995
   */
  public ProjectionInfo getPulkovo1995() {
    return Pulkovo1995.copy();
  }

  /**
   * @return the Qornoq
   */
  public ProjectionInfo getQornoq() {
    return Qornoq.copy();
  }

  /**
   * @return the RGF1993
   */
  public ProjectionInfo getRGF1993() {
    return RGF1993.copy();
  }

  /**
   * @return the RT1990
   */
  public ProjectionInfo getRT1990() {
    return RT1990.copy();
  }

  /**
   * @return the RT38
   */
  public ProjectionInfo getRT38() {
    return RT38.copy();
  }

  /**
   * @return the RT38Stockholm
   */
  public ProjectionInfo getRT38Stockholm() {
    return RT38Stockholm.copy();
  }

  /**
   * @return the ReseauNationalBelge1950
   */
  public ProjectionInfo getReseauNationalBelge1950() {
    return ReseauNationalBelge1950.copy();
  }

  /**
   * @return the ReseauNationalBelge1972
   */
  public ProjectionInfo getReseauNationalBelge1972() {
    return ReseauNationalBelge1972.copy();
  }

  /**
   * @return the Reykjavik1900
   */
  public ProjectionInfo getReykjavik1900() {
    return Reykjavik1900.copy();
  }

  /**
   * @return the Roma1940
   */
  public ProjectionInfo getRoma1940() {
    return Roma1940.copy();
  }

  /**
   * @return the S42Hungary
   */
  public ProjectionInfo getS42Hungary() {
    return S42Hungary.copy();
  }

  /**
   * @return the SJTSK
   */
  public ProjectionInfo getSJTSK() {
    return SJTSK.copy();
  }

  /**
   * @return the SWEREF99
   */
  public ProjectionInfo getSWEREF99() {
    return SWEREF99.copy();
  }

  /**
   * @return the SwissTRF1995
   */
  public ProjectionInfo getSwissTRF1995() {
    return SwissTRF1995.copy();
  }

  /**
   * @return the TM65
   */
  public ProjectionInfo getTM65() {
    return TM65.copy();
  }

  /**
   * @return the TM75
   */
  public ProjectionInfo getTM75() {
    return TM75.copy();
  }
}
