package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 * The KugerXian1980 Projections.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class KrugerXian1980 extends CoordinateSystemCategory {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private final ProjectionInfo Xian19803DegreeGKCM102E;
  private final ProjectionInfo Xian19803DegreeGKCM105E;
  private final ProjectionInfo Xian19803DegreeGKCM108E;
  private final ProjectionInfo Xian19803DegreeGKCM111E;
  private final ProjectionInfo Xian19803DegreeGKCM114E;
  private final ProjectionInfo Xian19803DegreeGKCM117E;
  private final ProjectionInfo Xian19803DegreeGKCM120E;
  private final ProjectionInfo Xian19803DegreeGKCM123E;
  private final ProjectionInfo Xian19803DegreeGKCM126E;
  private final ProjectionInfo Xian19803DegreeGKCM129E;
  private final ProjectionInfo Xian19803DegreeGKCM132E;
  private final ProjectionInfo Xian19803DegreeGKCM135E;
  private final ProjectionInfo Xian19803DegreeGKCM75E;
  private final ProjectionInfo Xian19803DegreeGKCM78E;
  private final ProjectionInfo Xian19803DegreeGKCM81E;
  private final ProjectionInfo Xian19803DegreeGKCM84E;
  private final ProjectionInfo Xian19803DegreeGKCM87E;
  private final ProjectionInfo Xian19803DegreeGKCM90E;
  private final ProjectionInfo Xian19803DegreeGKCM93E;
  private final ProjectionInfo Xian19803DegreeGKCM96E;
  private final ProjectionInfo Xian19803DegreeGKCM99E;
  private final ProjectionInfo Xian19803DegreeGKZone25;
  private final ProjectionInfo Xian19803DegreeGKZone26;
  private final ProjectionInfo Xian19803DegreeGKZone27;
  private final ProjectionInfo Xian19803DegreeGKZone28;
  private final ProjectionInfo Xian19803DegreeGKZone29;
  private final ProjectionInfo Xian19803DegreeGKZone30;
  private final ProjectionInfo Xian19803DegreeGKZone31;
  private final ProjectionInfo Xian19803DegreeGKZone32;
  private final ProjectionInfo Xian19803DegreeGKZone33;
  private final ProjectionInfo Xian19803DegreeGKZone34;
  private final ProjectionInfo Xian19803DegreeGKZone35;
  private final ProjectionInfo Xian19803DegreeGKZone36;
  private final ProjectionInfo Xian19803DegreeGKZone37;
  private final ProjectionInfo Xian19803DegreeGKZone38;
  private final ProjectionInfo Xian19803DegreeGKZone39;
  private final ProjectionInfo Xian19803DegreeGKZone40;
  private final ProjectionInfo Xian19803DegreeGKZone41;
  private final ProjectionInfo Xian19803DegreeGKZone42;
  private final ProjectionInfo Xian19803DegreeGKZone43;
  private final ProjectionInfo Xian19803DegreeGKZone44;
  private final ProjectionInfo Xian19803DegreeGKZone45;
  private final ProjectionInfo Xian1980GKCM105E;
  private final ProjectionInfo Xian1980GKCM111E;
  private final ProjectionInfo Xian1980GKCM117E;
  private final ProjectionInfo Xian1980GKCM123E;
  private final ProjectionInfo Xian1980GKCM129E;
  private final ProjectionInfo Xian1980GKCM135E;
  private final ProjectionInfo Xian1980GKCM75E;
  private final ProjectionInfo Xian1980GKCM81E;
  private final ProjectionInfo Xian1980GKCM87E;
  private final ProjectionInfo Xian1980GKCM93E;
  private final ProjectionInfo Xian1980GKCM99E;
  private final ProjectionInfo Xian1980GKZone13;
  private final ProjectionInfo Xian1980GKZone14;
  private final ProjectionInfo Xian1980GKZone15;
  private final ProjectionInfo Xian1980GKZone16;
  private final ProjectionInfo Xian1980GKZone17;
  private final ProjectionInfo Xian1980GKZone18;
  private final ProjectionInfo Xian1980GKZone19;
  private final ProjectionInfo Xian1980GKZone20;
  private final ProjectionInfo Xian1980GKZone21;
  private final ProjectionInfo Xian1980GKZone22;
  private final ProjectionInfo Xian1980GKZone23;

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /// <summary>
  /// Creates a new instance of KrugerZian1980
  /// </summary>
  public KrugerXian1980() {
    Xian19803DegreeGKCM102E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=102 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM105E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=105 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM108E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=108 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM111E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=111 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM114E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=114 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM117E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=117 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM120E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=120 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM123E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=123 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM126E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=126 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM129E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=129 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM132E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=132 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM135E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=135 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM75E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=75 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM78E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=78 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM81E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=81 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM84E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=84 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM87E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=87 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM90E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=90 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM93E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=93 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM96E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=96 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKCM99E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=99 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone25 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=75 +k=1.000000 +x_0=25500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone26 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=78 +k=1.000000 +x_0=26500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone27 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=81 +k=1.000000 +x_0=27500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone28 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=84 +k=1.000000 +x_0=28500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone29 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=87 +k=1.000000 +x_0=29500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone30 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=90 +k=1.000000 +x_0=30500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone31 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=93 +k=1.000000 +x_0=31500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone32 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=96 +k=1.000000 +x_0=32500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone33 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=99 +k=1.000000 +x_0=33500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone34 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=102 +k=1.000000 +x_0=34500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone35 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=105 +k=1.000000 +x_0=35500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone36 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=108 +k=1.000000 +x_0=36500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone37 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=111 +k=1.000000 +x_0=37500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone38 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=114 +k=1.000000 +x_0=38500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone39 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=117 +k=1.000000 +x_0=39500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone40 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=120 +k=1.000000 +x_0=40500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone41 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=123 +k=1.000000 +x_0=41500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone42 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=126 +k=1.000000 +x_0=42500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone43 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=129 +k=1.000000 +x_0=43500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone44 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=132 +k=1.000000 +x_0=44500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian19803DegreeGKZone45 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=135 +k=1.000000 +x_0=45500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM105E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=105 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM111E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=111 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM117E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=117 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM123E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=123 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM129E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=129 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM135E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=135 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM75E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=75 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM81E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=81 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM87E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=87 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM93E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=93 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKCM99E = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=99 +k=1.000000 +x_0=500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone13 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=75 +k=1.000000 +x_0=13500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone14 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=81 +k=1.000000 +x_0=14500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone15 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=87 +k=1.000000 +x_0=15500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone16 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=93 +k=1.000000 +x_0=16500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone17 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=99 +k=1.000000 +x_0=17500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone18 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=105 +k=1.000000 +x_0=18500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone19 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=111 +k=1.000000 +x_0=19500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone20 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=117 +k=1.000000 +x_0=20500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone21 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=123 +k=1.000000 +x_0=21500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone22 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=129 +k=1.000000 +x_0=22500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);
    Xian1980GKZone23 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=135 +k=1.000000 +x_0=23500000 +y_0=0 +a=6378140 +b=6356755.288157528 +units=m +no_defs ").orElse(null);

    Xian19803DegreeGKCM102E.setName("Xian_1980_3_Degree_GK_CM_102E");
    Xian19803DegreeGKCM105E.setName("Xian_1980_3_Degree_GK_CM_105E");
    Xian19803DegreeGKCM108E.setName("Xian_1980_3_Degree_GK_CM_108E");
    Xian19803DegreeGKCM111E.setName("Xian_1980_3_Degree_GK_CM_111E");
    Xian19803DegreeGKCM114E.setName("Xian_1980_3_Degree_GK_CM_114E");
    Xian19803DegreeGKCM117E.setName("Xian_1980_3_Degree_GK_CM_117E");
    Xian19803DegreeGKCM120E.setName("Xian_1980_3_Degree_GK_CM_120E");
    Xian19803DegreeGKCM123E.setName("Xian_1980_3_Degree_GK_CM_123E");
    Xian19803DegreeGKCM126E.setName("Xian_1980_3_Degree_GK_CM_126E");
    Xian19803DegreeGKCM129E.setName("Xian_1980_3_Degree_GK_CM_129E");
    Xian19803DegreeGKCM132E.setName("Xian_1980_3_Degree_GK_CM_132E");
    Xian19803DegreeGKCM135E.setName("Xian_1980_3_Degree_GK_CM_135E");
    Xian19803DegreeGKCM75E.setName("Xian_1980_3_Degree_GK_CM_75E");
    Xian19803DegreeGKCM78E.setName("Xian_1980_3_Degree_GK_CM_78E");
    Xian19803DegreeGKCM81E.setName("Xian_1980_3_Degree_GK_CM_81E");
    Xian19803DegreeGKCM84E.setName("Xian_1980_3_Degree_GK_CM_84E");
    Xian19803DegreeGKCM87E.setName("Xian_1980_3_Degree_GK_CM_87E");
    Xian19803DegreeGKCM90E.setName("Xian_1980_3_Degree_GK_CM_90E");
    Xian19803DegreeGKCM93E.setName("Xian_1980_3_Degree_GK_CM_93E");
    Xian19803DegreeGKCM96E.setName("Xian_1980_3_Degree_GK_CM_96E");
    Xian19803DegreeGKCM99E.setName("Xian_1980_3_Degree_GK_CM_99E");
    Xian19803DegreeGKZone25.setName("Xian_1980_3_Degree_GK_Zone_25");
    Xian19803DegreeGKZone26.setName("Xian_1980_3_Degree_GK_Zone_26");
    Xian19803DegreeGKZone27.setName("Xian_1980_3_Degree_GK_Zone_27");
    Xian19803DegreeGKZone28.setName("Xian_1980_3_Degree_GK_Zone_28");
    Xian19803DegreeGKZone29.setName("Xian_1980_3_Degree_GK_Zone_29");
    Xian19803DegreeGKZone30.setName("Xian_1980_3_Degree_GK_Zone_30");
    Xian19803DegreeGKZone31.setName("Xian_1980_3_Degree_GK_Zone_31");
    Xian19803DegreeGKZone32.setName("Xian_1980_3_Degree_GK_Zone_32");
    Xian19803DegreeGKZone33.setName("Xian_1980_3_Degree_GK_Zone_33");
    Xian19803DegreeGKZone34.setName("Xian_1980_3_Degree_GK_Zone_34");
    Xian19803DegreeGKZone35.setName("Xian_1980_3_Degree_GK_Zone_35");
    Xian19803DegreeGKZone36.setName("Xian_1980_3_Degree_GK_Zone_36");
    Xian19803DegreeGKZone37.setName("Xian_1980_3_Degree_GK_Zone_37");
    Xian19803DegreeGKZone38.setName("Xian_1980_3_Degree_GK_Zone_38");
    Xian19803DegreeGKZone39.setName("Xian_1980_3_Degree_GK_Zone_39");
    Xian19803DegreeGKZone40.setName("Xian_1980_3_Degree_GK_Zone_40");
    Xian19803DegreeGKZone41.setName("Xian_1980_3_Degree_GK_Zone_41");
    Xian19803DegreeGKZone42.setName("Xian_1980_3_Degree_GK_Zone_42");
    Xian19803DegreeGKZone43.setName("Xian_1980_3_Degree_GK_Zone_43");
    Xian19803DegreeGKZone44.setName("Xian_1980_3_Degree_GK_Zone_44");
    Xian19803DegreeGKZone45.setName("Xian_1980_3_Degree_GK_Zone_45");
    Xian1980GKCM105E.setName("Xian_1980_GK_CM_105E");
    Xian1980GKCM111E.setName("Xian_1980_GK_CM_111E");
    Xian1980GKCM117E.setName("Xian_1980_GK_CM_117E");
    Xian1980GKCM123E.setName("Xian_1980_GK_CM_123E");
    Xian1980GKCM129E.setName("Xian_1980_GK_CM_129E");
    Xian1980GKCM135E.setName("Xian_1980_GK_CM_135E");
    Xian1980GKCM75E.setName("Xian_1980_GK_CM_75E");
    Xian1980GKCM81E.setName("Xian_1980_GK_CM_81E");
    Xian1980GKCM87E.setName("Xian_1980_GK_CM_87E");
    Xian1980GKCM93E.setName("Xian_1980_GK_CM_93E");
    Xian1980GKCM99E.setName("Xian_1980_GK_CM_99E");
    Xian1980GKZone13.setName("Xian_1980_GK_Zone_13");
    Xian1980GKZone14.setName("Xian_1980_GK_Zone_14");
    Xian1980GKZone15.setName("Xian_1980_GK_Zone_15");
    Xian1980GKZone16.setName("Xian_1980_GK_Zone_16");
    Xian1980GKZone17.setName("Xian_1980_GK_Zone_17");
    Xian1980GKZone18.setName("Xian_1980_GK_Zone_18");
    Xian1980GKZone19.setName("Xian_1980_GK_Zone_19");
    Xian1980GKZone20.setName("Xian_1980_GK_Zone_20");
    Xian1980GKZone21.setName("Xian_1980_GK_Zone_21");
    Xian1980GKZone22.setName("Xian_1980_GK_Zone_22");
    Xian1980GKZone23.setName("Xian_1980_GK_Zone_23");

    Xian19803DegreeGKCM102E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM105E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM108E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM111E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM114E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM117E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM120E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM123E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM126E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM129E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM132E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM135E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM75E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM78E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM81E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM84E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM87E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM90E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM93E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM96E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKCM99E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone25.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone26.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone27.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone28.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone29.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone30.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone31.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone32.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone33.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone34.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone35.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone36.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone37.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone38.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone39.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone40.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone41.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone42.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone43.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone44.getGeographicInfo().setName("GCS_Xian_1980");
    Xian19803DegreeGKZone45.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM105E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM111E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM117E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM123E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM129E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM135E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM75E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM81E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM87E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM93E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKCM99E.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone13.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone14.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone15.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone16.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone17.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone18.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone19.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone20.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone21.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone22.getGeographicInfo().setName("GCS_Xian_1980");
    Xian1980GKZone23.getGeographicInfo().setName("GCS_Xian_1980");

    Xian19803DegreeGKCM102E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM105E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM108E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM111E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM114E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM117E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM120E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM123E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM126E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM129E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM132E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM135E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM75E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM78E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM81E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM84E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM87E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM90E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM93E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM96E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKCM99E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone25.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone26.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone27.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone28.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone29.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone30.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone31.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone32.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone33.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone34.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone35.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone36.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone37.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone38.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone39.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone40.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone41.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone42.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone43.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone44.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian19803DegreeGKZone45.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM105E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM111E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM117E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM123E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM129E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM135E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM75E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM81E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM87E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM93E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKCM99E.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone13.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone14.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone15.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone16.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone17.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone18.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone19.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone20.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone21.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone22.getGeographicInfo().getDatum().setName("D_Xian_1980");
    Xian1980GKZone23.getGeographicInfo().getDatum().setName("D_Xian_1980");
  }

  //</editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Properties">

  /**
   * Gets the Xian19803DegreeGKCM102E projection.
   * @return the Xian19803DegreeGKCM102E.
   */
  public ProjectionInfo getXian19803DegreeGKCM102E() {
    return Xian19803DegreeGKCM102E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM105E.
   */
  public ProjectionInfo getXian19803DegreeGKCM105E() {
    return Xian19803DegreeGKCM105E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM108E.
   */
  public ProjectionInfo getXian19803DegreeGKCM108E() {
    return Xian19803DegreeGKCM108E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM111E.
   */
  public ProjectionInfo getXian19803DegreeGKCM111E() {
    return Xian19803DegreeGKCM111E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM114E.
   */
  public ProjectionInfo getXian19803DegreeGKCM114E() {
    return Xian19803DegreeGKCM114E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM117E.
   */
  public ProjectionInfo getXian19803DegreeGKCM117E() {
    return Xian19803DegreeGKCM117E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM120E.
   */
  public ProjectionInfo getXian19803DegreeGKCM120E() {
    return Xian19803DegreeGKCM120E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM123E.
   */
  public ProjectionInfo getXian19803DegreeGKCM123E() {
    return Xian19803DegreeGKCM123E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM126E.
   */
  public ProjectionInfo getXian19803DegreeGKCM126E() {
    return Xian19803DegreeGKCM126E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM129E.
   */
  public ProjectionInfo getXian19803DegreeGKCM129E() {
    return Xian19803DegreeGKCM129E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM132E.
   */
  public ProjectionInfo getXian19803DegreeGKCM132E() {
    return Xian19803DegreeGKCM132E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM135E.
   */
  public ProjectionInfo getXian19803DegreeGKCM135E() {
    return Xian19803DegreeGKCM135E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM75E.
   */
  public ProjectionInfo getXian19803DegreeGKCM75E() {
    return Xian19803DegreeGKCM75E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM78E.
   */
  public ProjectionInfo getXian19803DegreeGKCM78E() {
    return Xian19803DegreeGKCM78E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM81E.
   */
  public ProjectionInfo getXian19803DegreeGKCM81E() {
    return Xian19803DegreeGKCM81E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM84E.
   */
  public ProjectionInfo getXian19803DegreeGKCM84E() {
    return Xian19803DegreeGKCM84E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM87E.
   */
  public ProjectionInfo getXian19803DegreeGKCM87E() {
    return Xian19803DegreeGKCM87E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM90E.
   */
  public ProjectionInfo getXian19803DegreeGKCM90E() {
    return Xian19803DegreeGKCM90E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM93E.
   */
  public ProjectionInfo getXian19803DegreeGKCM93E() {
    return Xian19803DegreeGKCM93E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM96E.
   */
  public ProjectionInfo getXian19803DegreeGKCM96E() {
    return Xian19803DegreeGKCM96E.copy();
  }

  /**
   * @return the Xian19803DegreeGKCM99E.
   */
  public ProjectionInfo getXian19803DegreeGKCM99E() {
    return Xian19803DegreeGKCM99E.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone25.
   */
  public ProjectionInfo getXian19803DegreeGKZone25() {
    return Xian19803DegreeGKZone25.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone26.
   */
  public ProjectionInfo getXian19803DegreeGKZone26() {
    return Xian19803DegreeGKZone26.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone27.
   */
  public ProjectionInfo getXian19803DegreeGKZone27() {
    return Xian19803DegreeGKZone27.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone28.
   */
  public ProjectionInfo getXian19803DegreeGKZone28() {
    return Xian19803DegreeGKZone28.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone29.
   */
  public ProjectionInfo getXian19803DegreeGKZone29() {
    return Xian19803DegreeGKZone29.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone30.
   */
  public ProjectionInfo getXian19803DegreeGKZone30() {
    return Xian19803DegreeGKZone30.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone31.
   */
  public ProjectionInfo getXian19803DegreeGKZone31() {
    return Xian19803DegreeGKZone31.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone32.
   */
  public ProjectionInfo getXian19803DegreeGKZone32() {
    return Xian19803DegreeGKZone32.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone33.
   */
  public ProjectionInfo getXian19803DegreeGKZone33() {
    return Xian19803DegreeGKZone33.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone34.
   */
  public ProjectionInfo getXian19803DegreeGKZone34() {
    return Xian19803DegreeGKZone34.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone35
   */
  public ProjectionInfo getXian19803DegreeGKZone35() {
    return Xian19803DegreeGKZone35.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone36
   */
  public ProjectionInfo getXian19803DegreeGKZone36() {
    return Xian19803DegreeGKZone36.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone37
   */
  public ProjectionInfo getXian19803DegreeGKZone37() {
    return Xian19803DegreeGKZone37.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone38
   */
  public ProjectionInfo getXian19803DegreeGKZone38() {
    return Xian19803DegreeGKZone38.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone39
   */
  public ProjectionInfo getXian19803DegreeGKZone39() {
    return Xian19803DegreeGKZone39.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone40
   */
  public ProjectionInfo getXian19803DegreeGKZone40() {
    return Xian19803DegreeGKZone40.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone41
   */
  public ProjectionInfo getXian19803DegreeGKZone41() {
    return Xian19803DegreeGKZone41.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone42
   */
  public ProjectionInfo getXian19803DegreeGKZone42() {
    return Xian19803DegreeGKZone42.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone43
   */
  public ProjectionInfo getXian19803DegreeGKZone43() {
    return Xian19803DegreeGKZone43.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone44
   */
  public ProjectionInfo getXian19803DegreeGKZone44() {
    return Xian19803DegreeGKZone44.copy();
  }

  /**
   * @return the Xian19803DegreeGKZone45
   */
  public ProjectionInfo getXian19803DegreeGKZone45() {
    return Xian19803DegreeGKZone45.copy();
  }

  /**
   * @return the Xian1980GKCM105E
   */
  public ProjectionInfo getXian1980GKCM105E() {
    return Xian1980GKCM105E.copy();
  }

  /**
   * @return the Xian1980GKCM111E
   */
  public ProjectionInfo getXian1980GKCM111E() {
    return Xian1980GKCM111E.copy();
  }

  /**
   * @return the Xian1980GKCM117E
   */
  public ProjectionInfo getXian1980GKCM117E() {
    return Xian1980GKCM117E.copy();
  }

  /**
   * @return the Xian1980GKCM123E
   */
  public ProjectionInfo getXian1980GKCM123E() {
    return Xian1980GKCM123E.copy();
  }

  /**
   * @return the Xian1980GKCM129E
   */
  public ProjectionInfo getXian1980GKCM129E() {
    return Xian1980GKCM129E.copy();
  }

  /**
   * @return the Xian1980GKCM135E
   */
  public ProjectionInfo getXian1980GKCM135E() {
    return Xian1980GKCM135E.copy();
  }

  /**
   * @return the Xian1980GKCM75E
   */
  public ProjectionInfo getXian1980GKCM75E() {
    return Xian1980GKCM75E.copy();
  }

  /**
   * @return the Xian1980GKCM81E
   */
  public ProjectionInfo getXian1980GKCM81E() {
    return Xian1980GKCM81E.copy();
  }

  /**
   * @return the Xian1980GKCM87E
   */
  public ProjectionInfo getXian1980GKCM87E() {
    return Xian1980GKCM87E.copy();
  }

  /**
   * @return the Xian1980GKCM93E
   */
  public ProjectionInfo getXian1980GKCM93E() {
    return Xian1980GKCM93E.copy();
  }

  /**
   * @return the Xian1980GKCM99E
   */
  public ProjectionInfo getXian1980GKCM99E() {
    return Xian1980GKCM99E.copy();
  }

  /**
   * @return the Xian1980GKZone13
   */
  public ProjectionInfo getXian1980GKZone13() {
    return Xian1980GKZone13.copy();
  }

  /**
   * @return the Xian1980GKZone14
   */
  public ProjectionInfo getXian1980GKZone14() {
    return Xian1980GKZone14.copy();
  }

  /**
   * @return the Xian1980GKZone15
   */
  public ProjectionInfo getXian1980GKZone15() {
    return Xian1980GKZone15.copy();
  }

  /**
   * @return the Xian1980GKZone16
   */
  public ProjectionInfo getXian1980GKZone16() {
    return Xian1980GKZone16.copy();
  }

  /**
   * @return the Xian1980GKZone17
   */
  public ProjectionInfo getXian1980GKZone17() {
    return Xian1980GKZone17.copy();
  }

  /**
   * @return the Xian1980GKZone18
   */
  public ProjectionInfo getXian1980GKZone18() {
    return Xian1980GKZone18.copy();
  }

  /**
   * @return the Xian1980GKZone19
   */
  public ProjectionInfo getXian1980GKZone19() {
    return Xian1980GKZone19.copy();
  }

  /**
   * @return the Xian1980GKZone20
   */
  public ProjectionInfo getXian1980GKZone20() {
    return Xian1980GKZone20.copy();
  }

  /**
   * @return the Xian1980GKZone21
   */
  public ProjectionInfo getXian1980GKZone21() {
    return Xian1980GKZone21.copy();
  }

  /**
   * @return the Xian1980GKZone22
   */
  public ProjectionInfo getXian1980GKZone22() {
    return Xian1980GKZone22.copy();
  }

  /**
   * @return the Xian1980GKZone23
   */
  public ProjectionInfo getXian1980GKZone23() {
    return Xian1980GKZone23.copy();
  }

  // </editor-fold>
}
