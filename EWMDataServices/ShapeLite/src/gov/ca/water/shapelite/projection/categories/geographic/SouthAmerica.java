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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:18:17 PM
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
/// SouthAmerica
/// </summary>

public class SouthAmerica extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo Aratu;
  private final ProjectionInfo Bogota;
  private final ProjectionInfo BogotaBogota;
  private final ProjectionInfo CampoInchauspe;
  private final ProjectionInfo ChosMalal1914;
  private final ProjectionInfo Chua;
  private final ProjectionInfo CorregoAlegre;
  private final ProjectionInfo GuyaneFrancaise;
  private final ProjectionInfo HitoXVIII1963;
  private final ProjectionInfo LaCanoa;
  private final ProjectionInfo Lake;
  private final ProjectionInfo LomaQuintana;
  private final ProjectionInfo MountDillon;
  private final ProjectionInfo Naparima1955;
  private final ProjectionInfo Naparima1972;
  private final ProjectionInfo POSGAR;
  private final ProjectionInfo POSGAR1998;
  private final ProjectionInfo PampadelCastillo;
  private final ProjectionInfo ProvisionalSouthAmer;
  private final ProjectionInfo REGVEN;
  private final ProjectionInfo SIRGAS;
  private final ProjectionInfo SapperHill1943;
  private final ProjectionInfo SouthAmericanDatum1969;
  private final ProjectionInfo Trinidad1903;
  private final ProjectionInfo Yacare;
  private final ProjectionInfo Zanderij;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of SouthAmerica
   */
  public SouthAmerica() {
    Aratu = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Bogota = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    BogotaBogota = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +pm=-74.08091666666667 +no_defs ").orElse(null);
    CampoInchauspe = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    ChosMalal1914 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Chua = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    CorregoAlegre = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    GuyaneFrancaise = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    HitoXVIII1963 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    LaCanoa = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Lake = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    LomaQuintana = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    MountDillon = ProjectionInfo.fromProj4String("+proj=longlat +a=6378293.639 +b=6356617.98149216 +no_defs ").orElse(null);
    Naparima1955 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Naparima1972 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    PampadelCastillo = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    POSGAR = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    POSGAR1998 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    ProvisionalSouthAmer = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    REGVEN = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    SapperHill1943 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    SIRGAS = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    SouthAmericanDatum1969 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=aust_SA +no_defs ").orElse(null);
    Trinidad1903 = ProjectionInfo.fromProj4String("+proj=longlat +a=6378293.639 +b=6356617.98149216 +no_defs ").orElse(null);
    Yacare = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);
    Zanderij = ProjectionInfo.fromProj4String("+proj=longlat +ellps=intl +no_defs ").orElse(null);

    Aratu.setLatLon(true);
    Bogota.setLatLon(true);
    BogotaBogota.setLatLon(true);
    CampoInchauspe.setLatLon(true);
    ChosMalal1914.setLatLon(true);
    Chua.setLatLon(true);
    CorregoAlegre.setLatLon(true);
    GuyaneFrancaise.setLatLon(true);
    HitoXVIII1963.setLatLon(true);
    LaCanoa.setLatLon(true);
    Lake.setLatLon(true);
    LomaQuintana.setLatLon(true);
    MountDillon.setLatLon(true);
    Naparima1955.setLatLon(true);
    Naparima1972.setLatLon(true);
    PampadelCastillo.setLatLon(true);
    POSGAR.setLatLon(true);
    POSGAR1998.setLatLon(true);
    ProvisionalSouthAmer.setLatLon(true);
    REGVEN.setLatLon(true);
    SapperHill1943.setLatLon(true);
    SIRGAS.setLatLon(true);
    SouthAmericanDatum1969.setLatLon(true);
    Trinidad1903.setLatLon(true);
    Yacare.setLatLon(true);
    Zanderij.setLatLon(true);

    Aratu.getGeographicInfo().setName("GCS_Aratu");
    Bogota.getGeographicInfo().setName("GCS_Bogota");
    BogotaBogota.getGeographicInfo().setName("GCS_Bogota_Bogota");
    CampoInchauspe.getGeographicInfo().setName("GCS_Campo_Inchauspe");
    ChosMalal1914.getGeographicInfo().setName("GCS_Chos_Malal_1914");
    Chua.getGeographicInfo().setName("GCS_Chua");
    CorregoAlegre.getGeographicInfo().setName("GCS_Corrego_Alegre");
    GuyaneFrancaise.getGeographicInfo().setName("GCS_Guyane_Francaise");
    HitoXVIII1963.getGeographicInfo().setName("GCS_Hito_XVIII_1963");
    LaCanoa.getGeographicInfo().setName("GCS_La_Canoa");
    Lake.getGeographicInfo().setName("GCS_Lake");
    LomaQuintana.getGeographicInfo().setName("GCS_Loma_Quintana");
    MountDillon.getGeographicInfo().setName("GCS_Mount_Dillon");
    Naparima1955.getGeographicInfo().setName("GCS_Naparima_1955");
    Naparima1972.getGeographicInfo().setName("GCS_Naparima_1972");
    PampadelCastillo.getGeographicInfo().setName("GCS_Pampa_del_Castillo");
    POSGAR.getGeographicInfo().setName("GCS_POSGAR");
    POSGAR1998.getGeographicInfo().setName("GCS_POSGAR_1998");
    ProvisionalSouthAmer.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
    REGVEN.getGeographicInfo().setName("GCS_REGVEN");
    SapperHill1943.getGeographicInfo().setName("GCS_Sapper_Hill_1943");
    SIRGAS.getGeographicInfo().setName("GCS_SIRGAS");
    SouthAmericanDatum1969.getGeographicInfo().setName("GCS_South_American_1969");
    Trinidad1903.getGeographicInfo().setName("GCS_Trinidad_1903");
    Yacare.getGeographicInfo().setName("GCS_Yacare");
    Zanderij.getGeographicInfo().setName("GCS_Zanderij");

    Aratu.setName("GCS_Aratu");
    Bogota.setName("GCS_Bogota");
    BogotaBogota.setName("GCS_Bogota_Bogota");
    CampoInchauspe.setName("GCS_Campo_Inchauspe");
    ChosMalal1914.setName("GCS_Chos_Malal_1914");
    Chua.setName("GCS_Chua");
    CorregoAlegre.setName("GCS_Corrego_Alegre");
    GuyaneFrancaise.setName("GCS_Guyane_Francaise");
    HitoXVIII1963.setName("GCS_Hito_XVIII_1963");
    LaCanoa.setName("GCS_La_Canoa");
    Lake.setName("GCS_Lake");
    LomaQuintana.setName("GCS_Loma_Quintana");
    MountDillon.setName("GCS_Mount_Dillon");
    Naparima1955.setName("GCS_Naparima_1955");
    Naparima1972.setName("GCS_Naparima_1972");
    PampadelCastillo.setName("GCS_Pampa_del_Castillo");
    POSGAR.setName("GCS_POSGAR");
    POSGAR1998.setName("GCS_POSGAR_1998");
    ProvisionalSouthAmer.setName("GCS_Provisional_S_American_1956");
    REGVEN.setName("GCS_REGVEN");
    SapperHill1943.setName("GCS_Sapper_Hill_1943");
    SIRGAS.setName("GCS_SIRGAS");
    SouthAmericanDatum1969.setName("GCS_South_American_1969");
    Trinidad1903.setName("GCS_Trinidad_1903");
    Yacare.setName("GCS_Yacare");
    Zanderij.setName("GCS_Zanderij");

    Aratu.getGeographicInfo().getDatum().setName("D_Aratu");
    Bogota.getGeographicInfo().getDatum().setName("D_Bogota");
    BogotaBogota.getGeographicInfo().getDatum().setName("D_Bogota");
    CampoInchauspe.getGeographicInfo().getDatum().setName("D_Campo_Inchauspe");
    ChosMalal1914.getGeographicInfo().getDatum().setName("D_Chos_Malal_1914");
    Chua.getGeographicInfo().getDatum().setName("D_Chua");
    CorregoAlegre.getGeographicInfo().getDatum().setName("D_Corrego_Alegre");
    GuyaneFrancaise.getGeographicInfo().getDatum().setName("D_Guyane_Francaise");
    HitoXVIII1963.getGeographicInfo().getDatum().setName("D_Hito_XVIII_1963");
    LaCanoa.getGeographicInfo().getDatum().setName("D_La_Canoa");
    Lake.getGeographicInfo().getDatum().setName("D_Lake");
    LomaQuintana.getGeographicInfo().getDatum().setName("D_Loma_Quintana");
    MountDillon.getGeographicInfo().getDatum().setName("D_Mount_Dillon");
    Naparima1955.getGeographicInfo().getDatum().setName("D_Naparima_1955");
    Naparima1972.getGeographicInfo().getDatum().setName("D_Naparima_1972");
    PampadelCastillo.getGeographicInfo().getDatum().setName("D_Pampa_del_Castillo");
    POSGAR.getGeographicInfo().getDatum().setName("D_POSGAR");
    POSGAR1998.getGeographicInfo().getDatum().setName("D_POSGAR_1998");
    ProvisionalSouthAmer.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
    REGVEN.getGeographicInfo().getDatum().setName("D_REGVEN");
    SapperHill1943.getGeographicInfo().getDatum().setName("D_Sapper_Hill_1943");
    SIRGAS.getGeographicInfo().getDatum().setName("D_SIRGAS");
    SouthAmericanDatum1969.getGeographicInfo().getDatum().setName("D_South_American_1969");
    Trinidad1903.getGeographicInfo().getDatum().setName("D_Trinidad_1903");
    Yacare.getGeographicInfo().getDatum().setName("D_Yacare");
    Zanderij.getGeographicInfo().getDatum().setName("D_Zanderij");
  }

  //</editor-fold>
  /**
   * @return the Aratu
   */
  public ProjectionInfo getAratu() {
    return Aratu.copy();
  }

  /**
   * @return the Bogota
   */
  public ProjectionInfo getBogota() {
    return Bogota.copy();
  }

  /**
   * @return the BogotaBogota
   */
  public ProjectionInfo getBogotaBogota() {
    return BogotaBogota.copy();
  }

  /**
   * @return the CampoInchauspe
   */
  public ProjectionInfo getCampoInchauspe() {
    return CampoInchauspe.copy();
  }

  /**
   * @return the ChosMalal1914
   */
  public ProjectionInfo getChosMalal1914() {
    return ChosMalal1914.copy();
  }

  /**
   * @return the Chua
   */
  public ProjectionInfo getChua() {
    return Chua.copy();
  }

  /**
   * @return the CorregoAlegre
   */
  public ProjectionInfo getCorregoAlegre() {
    return CorregoAlegre.copy();
  }

  /**
   * @return the GuyaneFrancaise
   */
  public ProjectionInfo getGuyaneFrancaise() {
    return GuyaneFrancaise.copy();
  }

  /**
   * @return the HitoXVIII1963
   */
  public ProjectionInfo getHitoXVIII1963() {
    return HitoXVIII1963.copy();
  }

  /**
   * @return the LaCanoa
   */
  public ProjectionInfo getLaCanoa() {
    return LaCanoa.copy();
  }

  /**
   * @return the Lake
   */
  public ProjectionInfo getLake() {
    return Lake.copy();
  }

  /**
   * @return the LomaQuintana
   */
  public ProjectionInfo getLomaQuintana() {
    return LomaQuintana.copy();
  }

  /**
   * @return the MountDillon
   */
  public ProjectionInfo getMountDillon() {
    return MountDillon.copy();
  }

  /**
   * @return the Naparima1955
   */
  public ProjectionInfo getNaparima1955() {
    return Naparima1955.copy();
  }

  /**
   * @return the Naparima1972
   */
  public ProjectionInfo getNaparima1972() {
    return Naparima1972.copy();
  }

  /**
   * @return the POSGAR
   */
  public ProjectionInfo getPOSGAR() {
    return POSGAR.copy();
  }

  /**
   * @return the POSGAR1998
   */
  public ProjectionInfo getPOSGAR1998() {
    return POSGAR1998.copy();
  }

  /**
   * @return the PampadelCastillo
   */
  public ProjectionInfo getPampadelCastillo() {
    return PampadelCastillo.copy();
  }

  /**
   * @return the ProvisionalSouthAmer
   */
  public ProjectionInfo getProvisionalSouthAmer() {
    return ProvisionalSouthAmer.copy();
  }

  /**
   * @return the REGVEN
   */
  public ProjectionInfo getREGVEN() {
    return REGVEN.copy();
  }

  /**
   * @return the SIRGAS
   */
  public ProjectionInfo getSIRGAS() {
    return SIRGAS.copy();
  }

  /**
   * @return the SapperHill1943
   */
  public ProjectionInfo getSapperHill1943() {
    return SapperHill1943.copy();
  }

  /**
   * @return the SouthAmericanDatum1969
   */
  public ProjectionInfo getSouthAmericanDatum1969() {
    return SouthAmericanDatum1969.copy();
  }

  /**
   * @return the Trinidad1903
   */
  public ProjectionInfo getTrinidad1903() {
    return Trinidad1903.copy();
  }

  /**
   * @return the Yacare
   */
  public ProjectionInfo getYacare() {
    return Yacare.copy();
  }

  /**
   * @return the Zanderij
   */
  public ProjectionInfo getZanderij() {
    return Zanderij.copy();
  }
}
