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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:14:40 PM
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
/// NorthAmerica
/// </summary>

public class NorthAmerica extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo ATS1977;
  private final ProjectionInfo AlaskanIslands;
  private final ProjectionInfo AmericanSamoa1962;
  private final ProjectionInfo Ammassalik1958;
  private final ProjectionInfo Barbados;
  private final ProjectionInfo Bermuda1957;
  private final ProjectionInfo Bermuda2000;
  private final ProjectionInfo CapeCanaveral;
  private final ProjectionInfo Guam1963;
  private final ProjectionInfo Helle1954;
  private final ProjectionInfo Jamaica1875;
  private final ProjectionInfo Jamaica1969;
  private final ProjectionInfo NAD1927CGQ77;
  private final ProjectionInfo NAD1927Definition1976;
  private final ProjectionInfo NADMichigan;
  private final ProjectionInfo NorthAmerican1983CSRS98;
  private final ProjectionInfo NorthAmerican1983HARN;
  private final ProjectionInfo NorthAmericanDatum1927;
  private final ProjectionInfo NorthAmericanDatum1983;
  private final ProjectionInfo OldHawaiian;
  private final ProjectionInfo PuertoRico;
  private final ProjectionInfo Qornoq;
  private final ProjectionInfo Qornoq1927;
  private final ProjectionInfo Scoresbysund1952;
  private final ProjectionInfo StGeorgeIsland;
  private final ProjectionInfo StLawrenceIsland;
  private final ProjectionInfo StPaulIsland;

        //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of NorthAmerica
   */
  public NorthAmerica() {
    AlaskanIslands = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    AmericanSamoa1962 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Ammassalik1958 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    ATS1977 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378135 +b=6356750.304921594 +no_defs ").orElse(null);
    Barbados = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk80 +no_defs ").orElse(null);
    Bermuda1957 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Bermuda2000 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS84 +no_defs ").orElse(null);
    CapeCanaveral = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Guam1963 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Helle1954 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Jamaica1875 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378249.138 +b=6356514.959419348 +no_defs ").orElse(null);
    Jamaica1969 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    NAD1927CGQ77 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    NAD1927Definition1976 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    NADMichigan = ProjectionInfo.fromProj4String("+proj=longlat +a=6378450.047 +b=6356826.620025999 +no_defs ").orElse(null);
    NorthAmerican1983CSRS98 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    NorthAmerican1983HARN = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    NorthAmericanDatum1927 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +datum=NAD27 +no_defs ").orElse(null);
    NorthAmericanDatum1983 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +datum=NAD83 +no_defs ").orElse(null);
    OldHawaiian = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    PuertoRico = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    Qornoq = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Qornoq1927 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Scoresbysund1952 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    StGeorgeIsland = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    StLawrenceIsland = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);
    StPaulIsland = ProjectionInfo.fromProj4String("+proj=longlat +ellps=clrk66 +no_defs ").orElse(null);

    AlaskanIslands.setLatLon(true);
    AmericanSamoa1962.setLatLon(true);
    Ammassalik1958.setLatLon(true);
    ATS1977.setLatLon(true);
    Barbados.setLatLon(true);
    Bermuda1957.setLatLon(true);
    Bermuda2000.setLatLon(true);
    CapeCanaveral.setLatLon(true);
    Guam1963.setLatLon(true);
    Helle1954.setLatLon(true);
    Jamaica1875.setLatLon(true);
    Jamaica1969.setLatLon(true);
    NAD1927CGQ77.setLatLon(true);
    NAD1927Definition1976.setLatLon(true);
    NADMichigan.setLatLon(true);
    NorthAmerican1983CSRS98.setLatLon(true);
    NorthAmerican1983HARN.setLatLon(true);
    NorthAmericanDatum1927.setLatLon(true);
    NorthAmericanDatum1983.setLatLon(true);
    OldHawaiian.setLatLon(true);
    PuertoRico.setLatLon(true);
    Qornoq.setLatLon(true);
    Qornoq1927.setLatLon(true);
    Scoresbysund1952.setLatLon(true);
    StGeorgeIsland.setLatLon(true);
    StLawrenceIsland.setLatLon(true);
    StPaulIsland.setLatLon(true);

    AlaskanIslands.getGeographicInfo().setName("GCS_Alaskan_Islands");
    AmericanSamoa1962.getGeographicInfo().setName("GCS_American_Samoa_1962");
    Ammassalik1958.getGeographicInfo().setName("GCS_Ammassalik_1958");
    ATS1977.getGeographicInfo().setName("GCS_ATS_1977");
    Barbados.getGeographicInfo().setName("GCS_Barbados");
    Bermuda1957.getGeographicInfo().setName("GCS_Bermuda_1957");
    Bermuda2000.getGeographicInfo().setName("GCS_Bermuda_2000");
    CapeCanaveral.getGeographicInfo().setName("GCS_Cape_Canaveral");
    Helle1954.getGeographicInfo().setName("GCS_Helle_1954");
    Guam1963.getGeographicInfo().setName("GCS_Guam_1963");
    Jamaica1875.getGeographicInfo().setName("GCS_Jamaica_1875");
    Jamaica1969.getGeographicInfo().setName("GCS_Jamaica_1969");
    NAD1927CGQ77.getGeographicInfo().setName("GCS_NAD_1927_CGQ77");
    NAD1927Definition1976.getGeographicInfo().setName("GCS_NAD_1927_Definition_1976");
    NADMichigan.getGeographicInfo().setName("GCS_North_American_Michigan");
    NorthAmerican1983CSRS98.getGeographicInfo().setName("GCS_North_American_1983_CSRS98");
    NorthAmerican1983HARN.getGeographicInfo().setName("GCS_North_American_1983_HARN");
    NorthAmericanDatum1927.getGeographicInfo().setName("GCS_North_American_1927");
    NorthAmericanDatum1983.getGeographicInfo().setName("GCS_North_American_1983");
    OldHawaiian.getGeographicInfo().setName("GCS_Old_Hawaiian");
    PuertoRico.getGeographicInfo().setName("GCS_Puerto_Rico");
    Qornoq1927.getGeographicInfo().setName("GCS_Qornoq_1927");
    Scoresbysund1952.getGeographicInfo().setName("GCS_Scoresbysund_1952");
    StGeorgeIsland.getGeographicInfo().setName("GCS_St_George_Island");
    StLawrenceIsland.getGeographicInfo().setName("GCS_St_Lawrence_Island");
    StPaulIsland.getGeographicInfo().setName("GCS_St_Paul_Island");

    AlaskanIslands.setName("GCS_Alaskan_Islands");
    AmericanSamoa1962.setName("GCS_American_Samoa_1962");
    Ammassalik1958.setName("GCS_Ammassalik_1958");
    ATS1977.setName("GCS_ATS_1977");
    Barbados.setName("GCS_Barbados");
    Bermuda1957.setName("GCS_Bermuda_1957");
    Bermuda2000.setName("GCS_Bermuda_2000");
    CapeCanaveral.setName("GCS_Cape_Canaveral");
    Helle1954.setName("GCS_Helle_1954");
    Guam1963.setName("GCS_Guam_1963");
    Jamaica1875.setName("GCS_Jamaica_1875");
    Jamaica1969.setName("GCS_Jamaica_1969");
    NAD1927CGQ77.setName("GCS_NAD_1927_CGQ77");
    NAD1927Definition1976.setName("GCS_NAD_1927_Definition_1976");
    NADMichigan.setName("GCS_North_American_Michigan");
    NorthAmerican1983CSRS98.setName("GCS_North_American_1983_CSRS98");
    NorthAmerican1983HARN.setName("GCS_North_American_1983_HARN");
    NorthAmericanDatum1927.setName("GCS_North_American_1927");
    NorthAmericanDatum1983.setName("GCS_North_American_1983");
    OldHawaiian.setName("GCS_Old_Hawaiian");
    PuertoRico.setName("GCS_Puerto_Rico");
    Qornoq1927.setName("GCS_Qornoq_1927");
    Scoresbysund1952.setName("GCS_Scoresbysund_1952");
    StGeorgeIsland.setName("GCS_St_George_Island");
    StLawrenceIsland.setName("GCS_St_Lawrence_Island");
    StPaulIsland.setName("GCS_St_Paul_Island");

  }

        //</editor-fold>

  /**
   * @return the ATS1977
   */
  public ProjectionInfo getATS1977() {
    return ATS1977.copy();
  }

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
   * @return the Ammassalik1958
   */
  public ProjectionInfo getAmmassalik1958() {
    return Ammassalik1958.copy();
  }

  /**
   * @return the Barbados
   */
  public ProjectionInfo getBarbados() {
    return Barbados.copy();
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
   * @return the CapeCanaveral
   */
  public ProjectionInfo getCapeCanaveral() {
    return CapeCanaveral.copy();
  }

  /**
   * @return the Guam1963
   */
  public ProjectionInfo getGuam1963() {
    return Guam1963.copy();
  }

  /**
   * @return the Helle1954
   */
  public ProjectionInfo getHelle1954() {
    return Helle1954.copy();
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
   * @return the NAD1927CGQ77
   */
  public ProjectionInfo getNAD1927CGQ77() {
    return NAD1927CGQ77.copy();
  }

  /**
   * @return the NAD1927Definition1976
   */
  public ProjectionInfo getNAD1927Definition1976() {
    return NAD1927Definition1976.copy();
  }

  /**
   * @return the NADMichigan
   */
  public ProjectionInfo getNADMichigan() {
    return NADMichigan.copy();
  }

  /**
   * @return the NorthAmerican1983CSRS98
   */
  public ProjectionInfo getNorthAmerican1983CSRS98() {
    return NorthAmerican1983CSRS98.copy();
  }

  /**
   * @return the NorthAmerican1983HARN
   */
  public ProjectionInfo getNorthAmerican1983HARN() {
    return NorthAmerican1983HARN.copy();
  }

  /**
   * @return the NorthAmericanDatum1927
   */
  public ProjectionInfo getNorthAmericanDatum1927() {
    return NorthAmericanDatum1927.copy();
  }

  /**
   * @return the NorthAmericanDatum1983
   */
  public ProjectionInfo getNorthAmericanDatum1983() {
    return NorthAmericanDatum1983.copy();
  }

  /**
   * @return the OldHawaiian
   */
  public ProjectionInfo getOldHawaiian() {
    return OldHawaiian.copy();
  }

  /**
   * @return the PuertoRico
   */
  public ProjectionInfo getPuertoRico() {
    return PuertoRico.copy();
  }

  /**
   * @return the Qornoq
   */
  public ProjectionInfo getQornoq() {
    return Qornoq.copy();
  }

  /**
   * @return the Qornoq1927
   */
  public ProjectionInfo getQornoq1927() {
    return Qornoq1927.copy();
  }

  /**
   * @return the Scoresbysund1952
   */
  public ProjectionInfo getScoresbysund1952() {
    return Scoresbysund1952.copy();
  }

  /**
   * @return the StGeorgeIsland
   */
  public ProjectionInfo getStGeorgeIsland() {
    return StGeorgeIsland.copy();
  }

  /**
   * @return the StLawrenceIsland
   */
  public ProjectionInfo getStLawrenceIsland() {
    return StLawrenceIsland.copy();
  }

  /**
   * @return the StPaulIsland
   */
  public ProjectionInfo getStPaulIsland() {
    return StPaulIsland.copy();
  }
}
