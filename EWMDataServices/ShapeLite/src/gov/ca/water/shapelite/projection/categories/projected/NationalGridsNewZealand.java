package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
/// <summary>
/// NationalGridsNewZealand
/// </summary>

/**
 * National Grid coordinate systems for New Zealand.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class NationalGridsNewZealand extends CoordinateSystemCategory {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private final ProjectionInfo ChathamIslands1979MapGrid;
  private final ProjectionInfo NZGD1949AmuriCircuit;
  private final ProjectionInfo NZGD1949BayofPlentyCircuit;
  private final ProjectionInfo NZGD1949BluffCircuit;
  private final ProjectionInfo NZGD1949BullerCircuit;
  private final ProjectionInfo NZGD1949CollingwoodCircuit;
  private final ProjectionInfo NZGD1949GawlerCircuit;
  private final ProjectionInfo NZGD1949GreyCircuit;
  private final ProjectionInfo NZGD1949HawkesBayCircuit;
  private final ProjectionInfo NZGD1949HokitikaCircuit;
  private final ProjectionInfo NZGD1949JacksonsBayCircuit;
  private final ProjectionInfo NZGD1949KarameaCircuit;
  private final ProjectionInfo NZGD1949LindisPeakCircuit;
  private final ProjectionInfo NZGD1949MarlboroughCircuit;
  private final ProjectionInfo NZGD1949MountEdenCircuit;
  private final ProjectionInfo NZGD1949MountNicholasCircuit;
  private final ProjectionInfo NZGD1949MountPleasantCircuit;
  private final ProjectionInfo NZGD1949MountYorkCircuit;
  private final ProjectionInfo NZGD1949NelsonCircuit;
  private final ProjectionInfo NZGD1949NorthTaieriCircuit;
  private final ProjectionInfo NZGD1949ObservationPointCircuit;
  private final ProjectionInfo NZGD1949OkaritoCircuit;
  private final ProjectionInfo NZGD1949PovertyBayCircuit;
  private final ProjectionInfo NZGD1949TaranakiCircuit;
  private final ProjectionInfo NZGD1949TimaruCircuit;
  private final ProjectionInfo NZGD1949TuhirangiCircuit;
  private final ProjectionInfo NZGD1949UTMZone58S;
  private final ProjectionInfo NZGD1949UTMZone59S;
  private final ProjectionInfo NZGD1949UTMZone60S;
  private final ProjectionInfo NZGD1949WairarapaCircuit;
  private final ProjectionInfo NZGD1949WanganuiCircuit;
  private final ProjectionInfo NZGD1949WellingtonCircuit;
  private final ProjectionInfo NZGD2000AmuriCircuit;
  private final ProjectionInfo NZGD2000BayofPlentyCircuit;
  private final ProjectionInfo NZGD2000BluffCircuit;
  private final ProjectionInfo NZGD2000BullerCircuit;
  private final ProjectionInfo NZGD2000ChathamIslandCircuit;
  private final ProjectionInfo NZGD2000CollingwoodCircuit;
  private final ProjectionInfo NZGD2000GawlerCircuit;
  private final ProjectionInfo NZGD2000GreyCircuit;
  private final ProjectionInfo NZGD2000HawkesBayCircuit;
  private final ProjectionInfo NZGD2000HokitikaCircuit;
  private final ProjectionInfo NZGD2000JacksonsBayCircuit;
  private final ProjectionInfo NZGD2000KarameaCircuit;
  private final ProjectionInfo NZGD2000LindisPeakCircuit;
  private final ProjectionInfo NZGD2000MarlboroughCircuit;
  private final ProjectionInfo NZGD2000MountEdenCircuit;
  private final ProjectionInfo NZGD2000MountNicholasCircuit;
  private final ProjectionInfo NZGD2000MountPleasantCircuit;
  private final ProjectionInfo NZGD2000MountYorkCircuit;
  private final ProjectionInfo NZGD2000NelsonCircuit;
  private final ProjectionInfo NZGD2000NewZealandTransverseMercator;
  private final ProjectionInfo NZGD2000NorthTaieriCircuit;
  private final ProjectionInfo NZGD2000ObservationPointCircuit;
  private final ProjectionInfo NZGD2000OkaritoCircuit;
  private final ProjectionInfo NZGD2000PovertyBayCircuit;
  private final ProjectionInfo NZGD2000TaranakiCircuit;
  private final ProjectionInfo NZGD2000TimaruCircuit;
  private final ProjectionInfo NZGD2000TuhirangiCircuit;
  private final ProjectionInfo NZGD2000UTMZone58S;
  private final ProjectionInfo NZGD2000UTMZone59S;
  private final ProjectionInfo NZGD2000UTMZone60S;
  private final ProjectionInfo NZGD2000WairarapaCircuit;
  private final ProjectionInfo NZGD2000WanganuiCircuit;
  private final ProjectionInfo NZGD2000WellingtonCircuit;
  private final ProjectionInfo NewZealandMapGrid;
  private final ProjectionInfo NewZealandNorthIsland;
  private final ProjectionInfo NewZealandSouthIsland;

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of NationalGridsNewZealand
  /// </summary>
  public NationalGridsNewZealand() {
    ChathamIslands1979MapGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-44 +lon_0=-176.5 +k=0.999600 +x_0=350000 +y_0=650000 +ellps=intl +units=m +no_defs ").orElse(null);
    NewZealandMapGrid = ProjectionInfo.fromProj4String("+proj=nzmg +lat_0=-41 +lon_0=173 +x_0=2510000 +y_0=6023150 +ellps=intl +units=m +no_defs ").orElse(null);
    NewZealandNorthIsland = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-39 +lon_0=175.5 +k=1.000000 +x_0=274319.5243848086 +y_0=365759.3658464114 +ellps=intl +to_meter=0.9143984146160287 +no_defs ").orElse(null);
    NewZealandSouthIsland = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-44 +lon_0=171.5 +k=1.000000 +x_0=457199.2073080143 +y_0=457199.2073080143 +ellps=intl +to_meter=0.9143984146160287 +no_defs ").orElse(null);
    NZGD1949AmuriCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-42.68911658333333 +lon_0=173.0101333888889 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949BayofPlentyCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-37.76124980555556 +lon_0=176.46619725 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949BluffCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-46.60000961111111 +lon_0=168.342872 +k=1.000000 +x_0=300002.66 +y_0=699999.58 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949BullerCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-41.81080286111111 +lon_0=171.5812600555556 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949CollingwoodCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-40.71475905555556 +lon_0=172.6720465 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949GawlerCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-43.74871155555556 +lon_0=171.3607484722222 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949GreyCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-42.33369427777778 +lon_0=171.5497713055556 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949HawkesBayCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-39.65092930555556 +lon_0=176.6736805277778 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949HokitikaCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-42.88632236111111 +lon_0=170.9799935 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949JacksonsBayCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-43.97780288888889 +lon_0=168.606267 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949KarameaCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-41.28991152777778 +lon_0=172.1090281944444 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949LindisPeakCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-44.73526797222222 +lon_0=169.4677550833333 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949MarlboroughCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-41.54448666666666 +lon_0=173.8020741111111 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949MountEdenCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-36.87986527777778 +lon_0=174.7643393611111 +k=0.999900 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949MountNicholasCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-45.13290258333333 +lon_0=168.3986411944444 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949MountPleasantCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-43.59063758333333 +lon_0=172.7271935833333 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949MountYorkCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-45.56372616666666 +lon_0=167.7388617777778 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949NelsonCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-41.27454472222222 +lon_0=173.2993168055555 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949NorthTaieriCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-45.86151336111111 +lon_0=170.2825891111111 +k=0.999960 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949ObservationPointCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-45.81619661111111 +lon_0=170.6285951666667 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949OkaritoCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-43.11012813888889 +lon_0=170.2609258333333 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949PovertyBayCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-38.62470277777778 +lon_0=177.8856362777778 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949TaranakiCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-39.13575830555556 +lon_0=174.22801175 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949TimaruCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-44.40222036111111 +lon_0=171.0572508333333 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949TuhirangiCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-39.51247038888889 +lon_0=175.6400368055556 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949UTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949UTMZone59S = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +south +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949UTMZone60S = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +south +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949WairarapaCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-40.92553263888889 +lon_0=175.6473496666667 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949WanganuiCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-40.24194713888889 +lon_0=175.4880996111111 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD1949WellingtonCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-41.30131963888888 +lon_0=174.7766231111111 +k=1.000000 +x_0=300000 +y_0=700000 +ellps=intl +units=m +no_defs ").orElse(null);
    NZGD2000AmuriCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-42.68888888888888 +lon_0=173.01 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000BayofPlentyCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-37.76111111111111 +lon_0=176.4661111111111 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000BluffCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-46.6 +lon_0=168.3427777777778 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000BullerCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-41.81055555555555 +lon_0=171.5811111111111 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000ChathamIslandCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-44 +lon_0=-176.5 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000CollingwoodCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-40.71472222222223 +lon_0=172.6719444444444 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000GawlerCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-43.74861111111111 +lon_0=171.3605555555555 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000GreyCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-42.33361111111111 +lon_0=171.5497222222222 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000HawkesBayCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-39.65083333333333 +lon_0=176.6736111111111 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000HokitikaCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-42.88611111111111 +lon_0=170.9797222222222 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000JacksonsBayCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-43.97777777777778 +lon_0=168.6061111111111 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000KarameaCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-41.28972222222222 +lon_0=172.1088888888889 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000LindisPeakCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-44.735 +lon_0=169.4675 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000MarlboroughCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-41.54444444444444 +lon_0=173.8019444444444 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000MountEdenCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-36.87972222222222 +lon_0=174.7641666666667 +k=0.999900 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000MountNicholasCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-45.13277777777778 +lon_0=168.3986111111111 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000MountPleasantCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-43.59055555555556 +lon_0=172.7269444444445 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000MountYorkCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-45.56361111111111 +lon_0=167.7386111111111 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000NelsonCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-41.27444444444444 +lon_0=173.2991666666667 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000NewZealandTransverseMercator = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=173 +k=0.999600 +x_0=1600000 +y_0=10000000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000NorthTaieriCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-45.86138888888889 +lon_0=170.2825 +k=0.999960 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000ObservationPointCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-45.81611111111111 +lon_0=170.6283333333333 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000OkaritoCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-43.11 +lon_0=170.2608333333333 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000PovertyBayCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-38.62444444444444 +lon_0=177.8855555555556 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000TaranakiCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-39.13555555555556 +lon_0=174.2277777777778 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000TimaruCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-44.40194444444445 +lon_0=171.0572222222222 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000TuhirangiCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-39.51222222222222 +lon_0=175.64 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000UTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000UTMZone59S = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000UTMZone60S = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000WairarapaCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-40.92527777777777 +lon_0=175.6472222222222 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000WanganuiCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-40.24194444444444 +lon_0=175.4880555555555 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
    NZGD2000WellingtonCircuit = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=-41.3011111111111 +lon_0=174.7763888888889 +k=1.000000 +x_0=400000 +y_0=800000 +ellps=GRS80 +units=m +no_defs ").orElse(null);

    ChathamIslands1979MapGrid.setName("Chatham_Islands_1979_Map_Grid");
    NewZealandMapGrid.setName("GD_1949_New_Zealand_Map_Grid");
    NewZealandNorthIsland.setName("New_Zealand_North_Island");
    NewZealandSouthIsland.setName("New_Zealand_South_Island");
    NZGD1949AmuriCircuit.setName("NZGD_1949_Amuri_Circuit");
    NZGD1949BayofPlentyCircuit.setName("NZGD_1949_Bay_of_Plenty_Circuit");
    NZGD1949BluffCircuit.setName("NZGD_1949_Bluff_Circuit");
    NZGD1949BullerCircuit.setName("NZGD_1949_Buller_Circuit");
    NZGD1949CollingwoodCircuit.setName("NZGD_1949_Collingwood_Circuit");
    NZGD1949GawlerCircuit.setName("NZGD_1949_Gawler_Circuit");
    NZGD1949GreyCircuit.setName("NZGD_1949_Grey_Circuit");
    NZGD1949HawkesBayCircuit.setName("NZGD_1949_Hawkes_Bay_Circuit");
    NZGD1949HokitikaCircuit.setName("NZGD_1949_Hokitika_Circuit");
    NZGD1949JacksonsBayCircuit.setName("NZGD_1949_Jacksons_Bay_Circuit");
    NZGD1949KarameaCircuit.setName("NZGD_1949_Karamea_Circuit");
    NZGD1949LindisPeakCircuit.setName("NZGD_1949_Lindis_Peak_Circuit");
    NZGD1949MarlboroughCircuit.setName("NZGD_1949_Marlborough_Circuit");
    NZGD1949MountEdenCircuit.setName("NZGD_1949_Mount_Eden_Circuit");
    NZGD1949MountNicholasCircuit.setName("NZGD_1949_Mount_Nicholas_Circuit");
    NZGD1949MountPleasantCircuit.setName("NZGD_1949_Mount_Pleasant_Circuit");
    NZGD1949MountYorkCircuit.setName("NZGD_1949_Mount_York_Circuit");
    NZGD1949NelsonCircuit.setName("NZGD_1949_Nelson_Circuit");
    NZGD1949NorthTaieriCircuit.setName("NZGD_1949_North_Taieri_Circuit");
    NZGD1949ObservationPointCircuit.setName("NZGD_1949_Observation_Point_Circuit");
    NZGD1949OkaritoCircuit.setName("NZGD_1949_Okarito_Circuit");
    NZGD1949PovertyBayCircuit.setName("NZGD_1949_Poverty_Bay_Circuit");
    NZGD1949TaranakiCircuit.setName("NZGD_1949_Taranaki_Circuit");
    NZGD1949TimaruCircuit.setName("NZGD_1949_Timaru_Circuit");
    NZGD1949TuhirangiCircuit.setName("NZGD_1949_Tuhirangi_Circuit");
    NZGD1949UTMZone58S.setName("NZGD_1949_UTM_Zone_58S");
    NZGD1949UTMZone59S.setName("NZGD_1949_UTM_Zone_59S");
    NZGD1949UTMZone60S.setName("NZGD_1949_UTM_Zone_60S");
    NZGD1949WairarapaCircuit.setName("NZGD_1949_Wairarapa_Circuit");
    NZGD1949WanganuiCircuit.setName("NZGD_1949_Wanganui_Circuit");
    NZGD1949WellingtonCircuit.setName("NZGD_1949_Wellington_Circuit");
    NZGD2000AmuriCircuit.setName("NZGD_2000_Amuri_Circuit");
    NZGD2000BayofPlentyCircuit.setName("NZGD_2000_Bay_of_Plenty_Circuit");
    NZGD2000BluffCircuit.setName("NZGD_2000_Bluff_Circuit");
    NZGD2000BullerCircuit.setName("NZGD_2000_Buller_Circuit");
    NZGD2000ChathamIslandCircuit.setName("NZGD_2000_Chatham_Island_Circuit");
    NZGD2000CollingwoodCircuit.setName("NZGD_2000_Collingwood_Circuit");
    NZGD2000GawlerCircuit.setName("NZGD_2000_Gawler_Circuit");
    NZGD2000GreyCircuit.setName("NZGD_2000_Grey_Circuit");
    NZGD2000HawkesBayCircuit.setName("NZGD_2000_Hawkes_Bay_Circuit");
    NZGD2000HokitikaCircuit.setName("NZGD_2000_Hokitika_Circuit");
    NZGD2000JacksonsBayCircuit.setName("NZGD_2000_Jacksons_Bay_Circuit");
    NZGD2000KarameaCircuit.setName("NZGD_2000_Karamea_Circuit");
    NZGD2000LindisPeakCircuit.setName("NZGD_2000_Lindis_Peak_Circuit");
    NZGD2000MarlboroughCircuit.setName("NZGD_2000_Marlborough_Circuit");
    NZGD2000MountEdenCircuit.setName("NZGD_2000_Mount_Eden_Circuit");
    NZGD2000MountNicholasCircuit.setName("NZGD_2000_Mount_Nicholas_Circuit");
    NZGD2000MountPleasantCircuit.setName("NZGD_2000_Mount_Pleasant_Circuit");
    NZGD2000MountYorkCircuit.setName("NZGD_2000_Mount_York_Circuit");
    NZGD2000NelsonCircuit.setName("NZGD_2000_Nelson_Circuit");
    NZGD2000NewZealandTransverseMercator.setName("NZGD_2000_New_Zealand_Transverse_Mercator");
    NZGD2000NorthTaieriCircuit.setName("NZGD_2000_North_Taieri_Circuit");
    NZGD2000ObservationPointCircuit.setName("NZGD_2000_Observation_Point_Circuit");
    NZGD2000OkaritoCircuit.setName("NZGD_2000_Okarito_Circuit");
    NZGD2000PovertyBayCircuit.setName("NZGD_2000_Poverty_Bay_Circuit");
    NZGD2000TaranakiCircuit.setName("NZGD_2000_Taranaki_Circuit");
    NZGD2000TimaruCircuit.setName("NZGD_2000_Timaru_Circuit");
    NZGD2000TuhirangiCircuit.setName("NZGD_2000_Tuhirangi_Circuit");
    NZGD2000UTMZone58S.setName("NZGD_2000_UTM_Zone_58S");
    NZGD2000UTMZone59S.setName("NZGD_2000_UTM_Zone_59S");
    NZGD2000UTMZone60S.setName("NZGD_2000_UTM_Zone_60S");
    NZGD2000WairarapaCircuit.setName("NZGD_2000_Wairarapa_Circuit");
    NZGD2000WanganuiCircuit.setName("NZGD_2000_Wanganui_Circuit");
    NZGD2000WellingtonCircuit.setName("NZGD_2000_Wellington_Circuit");
    ChathamIslands1979MapGrid.getGeographicInfo().setName("GCS_Chatham_Islands_1979");
    NewZealandMapGrid.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NewZealandNorthIsland.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NewZealandSouthIsland.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949AmuriCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949BayofPlentyCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949BluffCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949BullerCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949CollingwoodCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949GawlerCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949GreyCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949HawkesBayCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949HokitikaCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949JacksonsBayCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949KarameaCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949LindisPeakCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949MarlboroughCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949MountEdenCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949MountNicholasCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949MountPleasantCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949MountYorkCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949NelsonCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949NorthTaieriCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949ObservationPointCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949OkaritoCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949PovertyBayCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949TaranakiCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949TimaruCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949TuhirangiCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949UTMZone58S.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949UTMZone59S.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949UTMZone60S.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949WairarapaCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949WanganuiCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD1949WellingtonCircuit.getGeographicInfo().setName("GCS_New_Zealand_1949");
    NZGD2000AmuriCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000BayofPlentyCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000BluffCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000BullerCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000ChathamIslandCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000CollingwoodCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000GawlerCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000GreyCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000HawkesBayCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000HokitikaCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000JacksonsBayCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000KarameaCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000LindisPeakCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000MarlboroughCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000MountEdenCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000MountNicholasCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000MountPleasantCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000MountYorkCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000NelsonCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000NewZealandTransverseMercator.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000NorthTaieriCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000ObservationPointCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000OkaritoCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000PovertyBayCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000TaranakiCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000TimaruCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000TuhirangiCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000UTMZone58S.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000UTMZone59S.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000UTMZone60S.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000WairarapaCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000WanganuiCircuit.getGeographicInfo().setName("GCS_NZGD_2000");
    NZGD2000WellingtonCircuit.getGeographicInfo().setName("GCS_NZGD_2000");

    ChathamIslands1979MapGrid.getGeographicInfo().getDatum().setName("D_Chatham_Islands_1979");
    NewZealandMapGrid.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NewZealandNorthIsland.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NewZealandSouthIsland.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949AmuriCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949BayofPlentyCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949BluffCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949BullerCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949CollingwoodCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949GawlerCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949GreyCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949HawkesBayCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949HokitikaCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949JacksonsBayCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949KarameaCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949LindisPeakCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949MarlboroughCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949MountEdenCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949MountNicholasCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949MountPleasantCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949MountYorkCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949NelsonCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949NorthTaieriCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949ObservationPointCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949OkaritoCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949PovertyBayCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949TaranakiCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949TimaruCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949TuhirangiCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949UTMZone58S.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949UTMZone59S.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949UTMZone60S.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949WairarapaCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949WanganuiCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD1949WellingtonCircuit.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
    NZGD2000AmuriCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000BayofPlentyCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000BluffCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000BullerCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000ChathamIslandCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000CollingwoodCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000GawlerCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000GreyCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000HawkesBayCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000HokitikaCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000JacksonsBayCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000KarameaCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000LindisPeakCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000MarlboroughCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000MountEdenCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000MountNicholasCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000MountPleasantCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000MountYorkCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000NelsonCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000NewZealandTransverseMercator.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000NorthTaieriCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000ObservationPointCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000OkaritoCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000PovertyBayCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000TaranakiCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000TimaruCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000TuhirangiCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000UTMZone58S.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000UTMZone59S.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000UTMZone60S.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000WairarapaCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000WanganuiCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
    NZGD2000WellingtonCircuit.getGeographicInfo().getDatum().setName("D_NZGD_2000");
  }

  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * @return the ChathamIslands1979MapGrid
   */
  public ProjectionInfo getChathamIslands1979MapGrid() {
    return ChathamIslands1979MapGrid.copy();
  }

  /**
   * @return the NZGD1949AmuriCircuit
   */
  public ProjectionInfo getNZGD1949AmuriCircuit() {
    return NZGD1949AmuriCircuit.copy();
  }

  /**
   * @return the NZGD1949BayofPlentyCircuit
   */
  public ProjectionInfo getNZGD1949BayofPlentyCircuit() {
    return NZGD1949BayofPlentyCircuit.copy();
  }

  /**
   * @return the NZGD1949BluffCircuit
   */
  public ProjectionInfo getNZGD1949BluffCircuit() {
    return NZGD1949BluffCircuit.copy();
  }

  /**
   * @return the NZGD1949BullerCircuit
   */
  public ProjectionInfo getNZGD1949BullerCircuit() {
    return NZGD1949BullerCircuit.copy();
  }

  /**
   * @return the NZGD1949CollingwoodCircuit
   */
  public ProjectionInfo getNZGD1949CollingwoodCircuit() {
    return NZGD1949CollingwoodCircuit.copy();
  }

  /**
   * @return the NZGD1949GawlerCircuit
   */
  public ProjectionInfo getNZGD1949GawlerCircuit() {
    return NZGD1949GawlerCircuit.copy();
  }

  /**
   * @return the NZGD1949GreyCircuit
   */
  public ProjectionInfo getNZGD1949GreyCircuit() {
    return NZGD1949GreyCircuit.copy();
  }

  /**
   * @return the NZGD1949HawkesBayCircuit
   */
  public ProjectionInfo getNZGD1949HawkesBayCircuit() {
    return NZGD1949HawkesBayCircuit.copy();
  }

  /**
   * @return the NZGD1949HokitikaCircuit
   */
  public ProjectionInfo getNZGD1949HokitikaCircuit() {
    return NZGD1949HokitikaCircuit.copy();
  }

  /**
   * @return the NZGD1949JacksonsBayCircuit
   */
  public ProjectionInfo getNZGD1949JacksonsBayCircuit() {
    return NZGD1949JacksonsBayCircuit.copy();
  }

  /**
   * @return the NZGD1949KarameaCircuit
   */
  public ProjectionInfo getNZGD1949KarameaCircuit() {
    return NZGD1949KarameaCircuit.copy();
  }

  /**
   * @return the NZGD1949LindisPeakCircuit
   */
  public ProjectionInfo getNZGD1949LindisPeakCircuit() {
    return NZGD1949LindisPeakCircuit.copy();
  }

  /**
   * @return the NZGD1949MarlboroughCircuit
   */
  public ProjectionInfo getNZGD1949MarlboroughCircuit() {
    return NZGD1949MarlboroughCircuit.copy();
  }

  /**
   * @return the NZGD1949MountEdenCircuit
   */
  public ProjectionInfo getNZGD1949MountEdenCircuit() {
    return NZGD1949MountEdenCircuit.copy();
  }

  /**
   * @return the NZGD1949MountNicholasCircuit
   */
  public ProjectionInfo getNZGD1949MountNicholasCircuit() {
    return NZGD1949MountNicholasCircuit.copy();
  }

  /**
   * @return the NZGD1949MountPleasantCircuit
   */
  public ProjectionInfo getNZGD1949MountPleasantCircuit() {
    return NZGD1949MountPleasantCircuit.copy();
  }

  /**
   * @return the NZGD1949MountYorkCircuit
   */
  public ProjectionInfo getNZGD1949MountYorkCircuit() {
    return NZGD1949MountYorkCircuit.copy();
  }

  /**
   * @return the NZGD1949NelsonCircuit
   */
  public ProjectionInfo getNZGD1949NelsonCircuit() {
    return NZGD1949NelsonCircuit.copy();
  }

  /**
   * @return the NZGD1949NorthTaieriCircuit
   */
  public ProjectionInfo getNZGD1949NorthTaieriCircuit() {
    return NZGD1949NorthTaieriCircuit.copy();
  }

  /**
   * @return the NZGD1949ObservationPointCircuit
   */
  public ProjectionInfo getNZGD1949ObservationPointCircuit() {
    return NZGD1949ObservationPointCircuit.copy();
  }

  /**
   * @return the NZGD1949OkaritoCircuit
   */
  public ProjectionInfo getNZGD1949OkaritoCircuit() {
    return NZGD1949OkaritoCircuit.copy();
  }

  /**
   * @return the NZGD1949PovertyBayCircuit
   */
  public ProjectionInfo getNZGD1949PovertyBayCircuit() {
    return NZGD1949PovertyBayCircuit.copy();
  }

  /**
   * @return the NZGD1949TaranakiCircuit
   */
  public ProjectionInfo getNZGD1949TaranakiCircuit() {
    return NZGD1949TaranakiCircuit.copy();
  }

  /**
   * @return the NZGD1949TimaruCircuit
   */
  public ProjectionInfo getNZGD1949TimaruCircuit() {
    return NZGD1949TimaruCircuit.copy();
  }

  /**
   * @return the NZGD1949TuhirangiCircuit
   */
  public ProjectionInfo getNZGD1949TuhirangiCircuit() {
    return NZGD1949TuhirangiCircuit.copy();
  }

  /**
   * @return the NZGD1949UTMZone58S
   */
  public ProjectionInfo getNZGD1949UTMZone58S() {
    return NZGD1949UTMZone58S.copy();
  }

  /**
   * @return the NZGD1949UTMZone59S
   */
  public ProjectionInfo getNZGD1949UTMZone59S() {
    return NZGD1949UTMZone59S.copy();
  }

  /**
   * @return the NZGD1949UTMZone60S
   */
  public ProjectionInfo getNZGD1949UTMZone60S() {
    return NZGD1949UTMZone60S.copy();
  }

  /**
   * @return the NZGD1949WairarapaCircuit
   */
  public ProjectionInfo getNZGD1949WairarapaCircuit() {
    return NZGD1949WairarapaCircuit.copy();
  }

  /**
   * @return the NZGD1949WanganuiCircuit
   */
  public ProjectionInfo getNZGD1949WanganuiCircuit() {
    return NZGD1949WanganuiCircuit.copy();
  }

  /**
   * @return the NZGD1949WellingtonCircuit
   */
  public ProjectionInfo getNZGD1949WellingtonCircuit() {
    return NZGD1949WellingtonCircuit.copy();
  }

  /**
   * @return the NZGD2000AmuriCircuit
   */
  public ProjectionInfo getNZGD2000AmuriCircuit() {
    return NZGD2000AmuriCircuit.copy();
  }

  /**
   * @return the NZGD2000BayofPlentyCircuit
   */
  public ProjectionInfo getNZGD2000BayofPlentyCircuit() {
    return NZGD2000BayofPlentyCircuit.copy();
  }

  /**
   * @return the NZGD2000BluffCircuit
   */
  public ProjectionInfo getNZGD2000BluffCircuit() {
    return NZGD2000BluffCircuit.copy();
  }

  /**
   * @return the NZGD2000BullerCircuit
   */
  public ProjectionInfo getNZGD2000BullerCircuit() {
    return NZGD2000BullerCircuit.copy();
  }

  /**
   * @return the NZGD2000ChathamIslandCircuit
   */
  public ProjectionInfo getNZGD2000ChathamIslandCircuit() {
    return NZGD2000ChathamIslandCircuit.copy();
  }

  /**
   * @return the NZGD2000CollingwoodCircuit
   */
  public ProjectionInfo getNZGD2000CollingwoodCircuit() {
    return NZGD2000CollingwoodCircuit.copy();
  }

  /**
   * @return the NZGD2000GawlerCircuit
   */
  public ProjectionInfo getNZGD2000GawlerCircuit() {
    return NZGD2000GawlerCircuit.copy();
  }

  /**
   * @return the NZGD2000GreyCircuit
   */
  public ProjectionInfo getNZGD2000GreyCircuit() {
    return NZGD2000GreyCircuit.copy();
  }

  /**
   * @return the NZGD2000HawkesBayCircuit
   */
  public ProjectionInfo getNZGD2000HawkesBayCircuit() {
    return NZGD2000HawkesBayCircuit.copy();
  }

  /**
   * @return the NZGD2000HokitikaCircuit
   */
  public ProjectionInfo getNZGD2000HokitikaCircuit() {
    return NZGD2000HokitikaCircuit.copy();
  }

  /**
   * @return the NZGD2000JacksonsBayCircuit
   */
  public ProjectionInfo getNZGD2000JacksonsBayCircuit() {
    return NZGD2000JacksonsBayCircuit.copy();
  }

  /**
   * @return the NZGD2000KarameaCircuit
   */
  public ProjectionInfo getNZGD2000KarameaCircuit() {
    return NZGD2000KarameaCircuit.copy();
  }

  /**
   * @return the NZGD2000LindisPeakCircuit
   */
  public ProjectionInfo getNZGD2000LindisPeakCircuit() {
    return NZGD2000LindisPeakCircuit.copy();
  }

  /**
   * @return the NZGD2000MarlboroughCircuit
   */
  public ProjectionInfo getNZGD2000MarlboroughCircuit() {
    return NZGD2000MarlboroughCircuit.copy();
  }

  /**
   * @return the NZGD2000MountEdenCircuit
   */
  public ProjectionInfo getNZGD2000MountEdenCircuit() {
    return NZGD2000MountEdenCircuit.copy();
  }

  /**
   * @return the NZGD2000MountNicholasCircuit
   */
  public ProjectionInfo getNZGD2000MountNicholasCircuit() {
    return NZGD2000MountNicholasCircuit.copy();
  }

  /**
   * @return the NZGD2000MountPleasantCircuit
   */
  public ProjectionInfo getNZGD2000MountPleasantCircuit() {
    return NZGD2000MountPleasantCircuit.copy();
  }

  /**
   * @return the NZGD2000MountYorkCircuit
   */
  public ProjectionInfo getNZGD2000MountYorkCircuit() {
    return NZGD2000MountYorkCircuit.copy();
  }

  /**
   * @return the NZGD2000NelsonCircuit
   */
  public ProjectionInfo getNZGD2000NelsonCircuit() {
    return NZGD2000NelsonCircuit.copy();
  }

  /**
   * @return the NZGD2000NewZealandTransverseMercator
   */
  public ProjectionInfo getNZGD2000NewZealandTransverseMercator() {
    return NZGD2000NewZealandTransverseMercator.copy();
  }

  /**
   * @return the NZGD2000NorthTaieriCircuit
   */
  public ProjectionInfo getNZGD2000NorthTaieriCircuit() {
    return NZGD2000NorthTaieriCircuit.copy();
  }

  /**
   * @return the NZGD2000ObservationPointCircuit
   */
  public ProjectionInfo getNZGD2000ObservationPointCircuit() {
    return NZGD2000ObservationPointCircuit.copy();
  }

  /**
   * @return the NZGD2000OkaritoCircuit
   */
  public ProjectionInfo getNZGD2000OkaritoCircuit() {
    return NZGD2000OkaritoCircuit.copy();
  }

  /**
   * @return the NZGD2000PovertyBayCircuit
   */
  public ProjectionInfo getNZGD2000PovertyBayCircuit() {
    return NZGD2000PovertyBayCircuit.copy();
  }

  /**
   * @return the NZGD2000TaranakiCircuit
   */
  public ProjectionInfo getNZGD2000TaranakiCircuit() {
    return NZGD2000TaranakiCircuit.copy();
  }

  /**
   * @return the NZGD2000TimaruCircuit
   */
  public ProjectionInfo getNZGD2000TimaruCircuit() {
    return NZGD2000TimaruCircuit.copy();
  }

  /**
   * @return the NZGD2000TuhirangiCircuit
   */
  public ProjectionInfo getNZGD2000TuhirangiCircuit() {
    return NZGD2000TuhirangiCircuit.copy();
  }

  /**
   * @return the NZGD2000UTMZone58S
   */
  public ProjectionInfo getNZGD2000UTMZone58S() {
    return NZGD2000UTMZone58S.copy();
  }

  /**
   * @return the NZGD2000UTMZone59S
   */
  public ProjectionInfo getNZGD2000UTMZone59S() {
    return NZGD2000UTMZone59S.copy();
  }

  /**
   * @return the NZGD2000UTMZone60S
   */
  public ProjectionInfo getNZGD2000UTMZone60S() {
    return NZGD2000UTMZone60S.copy();
  }

  /**
   * @return the NZGD2000WairarapaCircuit
   */
  public ProjectionInfo getNZGD2000WairarapaCircuit() {
    return NZGD2000WairarapaCircuit.copy();
  }

  /**
   * @return the NZGD2000WanganuiCircuit
   */
  public ProjectionInfo getNZGD2000WanganuiCircuit() {
    return NZGD2000WanganuiCircuit.copy();
  }

  /**
   * @return the NZGD2000WellingtonCircuit
   */
  public ProjectionInfo getNZGD2000WellingtonCircuit() {
    return NZGD2000WellingtonCircuit.copy();
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

  // </editor-fold>
}
