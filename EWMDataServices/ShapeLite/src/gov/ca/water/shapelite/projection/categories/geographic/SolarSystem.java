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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:16:32 PM
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
/// SolarSystem
/// </summary>

public class SolarSystem extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo Adrastea2000;
  private final ProjectionInfo Amalthea2000;
  private final ProjectionInfo Ananke2000;
  private final ProjectionInfo Ariel2000;
  private final ProjectionInfo Atlas2000;
  private final ProjectionInfo Belinda2000;
  private final ProjectionInfo Bianca2000;
  private final ProjectionInfo Callisto2000;
  private final ProjectionInfo Calypso2000;
  private final ProjectionInfo Carme2000;
  private final ProjectionInfo Charon2000;
  private final ProjectionInfo Cordelia2000;
  private final ProjectionInfo Cressida2000;
  private final ProjectionInfo Deimos2000;
  private final ProjectionInfo Desdemona2000;
  private final ProjectionInfo Despina2000;
  private final ProjectionInfo Dione2000;
  private final ProjectionInfo Elara2000;
  private final ProjectionInfo Enceladus2000;
  private final ProjectionInfo Epimetheus2000;
  private final ProjectionInfo Europa2000;
  private final ProjectionInfo Galatea2000;
  private final ProjectionInfo Ganymede2000;
  private final ProjectionInfo Helene2000;
  private final ProjectionInfo Himalia2000;
  private final ProjectionInfo Hyperion2000;
  private final ProjectionInfo Iapetus2000;
  private final ProjectionInfo Io2000;
  private final ProjectionInfo Janus2000;
  private final ProjectionInfo Juliet2000;
  private final ProjectionInfo Jupiter2000;
  private final ProjectionInfo Larissa2000;
  private final ProjectionInfo Leda2000;
  private final ProjectionInfo Lysithea2000;
  private final ProjectionInfo Mars1979;
  private final ProjectionInfo Mars2000;
  private final ProjectionInfo Mercury2000;
  private final ProjectionInfo Metis2000;
  private final ProjectionInfo Mimas2000;
  private final ProjectionInfo Miranda2000;
  private final ProjectionInfo Moon2000;
  private final ProjectionInfo Naiad2000;
  private final ProjectionInfo Neptune2000;
  private final ProjectionInfo Nereid2000;
  private final ProjectionInfo Oberon2000;
  private final ProjectionInfo Ophelia2000;
  private final ProjectionInfo Pan2000;
  private final ProjectionInfo Pandora2000;
  private final ProjectionInfo Pasiphae2000;
  private final ProjectionInfo Phobos2000;
  private final ProjectionInfo Phoebe2000;
  private final ProjectionInfo Pluto2000;
  private final ProjectionInfo Portia2000;
  private final ProjectionInfo Prometheus2000;
  private final ProjectionInfo Proteus2000;
  private final ProjectionInfo Puck2000;
  private final ProjectionInfo Rhea2000;
  private final ProjectionInfo Rosalind2000;
  private final ProjectionInfo Saturn2000;
  private final ProjectionInfo Sinope2000;
  private final ProjectionInfo Telesto2000;
  private final ProjectionInfo Tethys2000;
  private final ProjectionInfo Thalassa2000;
  private final ProjectionInfo Thebe2000;
  private final ProjectionInfo Titan2000;
  private final ProjectionInfo Titania2000;
  private final ProjectionInfo Triton2000;
  private final ProjectionInfo Umbriel2000;
  private final ProjectionInfo Uranus2000;
  private final ProjectionInfo Venus1985;
  private final ProjectionInfo Venus2000;

        //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of SolarSystem
   */
  public SolarSystem() {
    Adrastea2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=8200 +b=8200 +no_defs ").orElse(null);
    Amalthea2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=83500 +b=83500 +no_defs ").orElse(null);
    Ananke2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=10000 +b=10000 +no_defs ").orElse(null);
    Ariel2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=578900 +b=578900 +no_defs ").orElse(null);
    Atlas2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=16000 +b=16000 +no_defs ").orElse(null);
    Belinda2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=33000 +b=33000 +no_defs ").orElse(null);
    Bianca2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=21000 +b=21000 +no_defs ").orElse(null);
    Callisto2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=2409300 +b=2409300 +no_defs ").orElse(null);
    Calypso2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=9500 +b=9500 +no_defs ").orElse(null);
    Carme2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=15000 +b=15000 +no_defs ").orElse(null);
    Charon2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=593000 +b=593000 +no_defs ").orElse(null);
    Cordelia2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=13000 +b=13000 +no_defs ").orElse(null);
    Cressida2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=31000 +b=31000 +no_defs ").orElse(null);
    Deimos2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=6200 +b=6200 +no_defs ").orElse(null);
    Desdemona2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=27000 +b=27000 +no_defs ").orElse(null);
    Despina2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=74000 +b=74000 +no_defs ").orElse(null);
    Dione2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=560000 +b=560000 +no_defs ").orElse(null);
    Elara2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=40000 +b=40000 +no_defs ").orElse(null);
    Enceladus2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=249400 +b=249400 +no_defs ").orElse(null);
    Epimetheus2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=59500 +b=59500 +no_defs ").orElse(null);
    Europa2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=1562090 +b=1562090 +no_defs ").orElse(null);
    Galatea2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=79000 +b=79000 +no_defs ").orElse(null);
    Ganymede2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=2632345 +b=2632345 +no_defs ").orElse(null);
    Helene2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=17500 +b=700.0000000000046 +no_defs ").orElse(null);
    Himalia2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=85000 +b=85000 +no_defs ").orElse(null);
    Hyperion2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=133000 +b=133000 +no_defs ").orElse(null);
    Iapetus2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=718000 +b=718000 +no_defs ").orElse(null);
    Io2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=1821460 +b=1821460 +no_defs ").orElse(null);
    Janus2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=888000 +b=888000 +no_defs ").orElse(null);
    Juliet2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=42000 +b=42000 +no_defs ").orElse(null);
    Jupiter2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=71492000 +b=66853999.99999999 +no_defs ").orElse(null);
    Larissa2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=104000 +b=89000 +no_defs ").orElse(null);
    Leda2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=5000 +b=5000 +no_defs ").orElse(null);
    Lysithea2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=12000 +b=12000 +no_defs ").orElse(null);
    Mars1979 = ProjectionInfo.fromProj4String("+proj=longlat +a=3393400 +b=3375730 +no_defs ").orElse(null);
    Mars2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=3396190 +b=3376200 +no_defs ").orElse(null);
    Mercury2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=2439700 +b=2439700 +no_defs ").orElse(null);
    Metis2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=30000 +b=20000 +no_defs ").orElse(null);
    Mimas2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=1986300 +b=1986300 +no_defs ").orElse(null);
    Miranda2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=235800 +b=235800 +no_defs ").orElse(null);
    Moon2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=1737400 +b=1737400 +no_defs ").orElse(null);
    Naiad2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=29000 +b=29000 +no_defs ").orElse(null);
    Neptune2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=24764000 +b=24341000 +no_defs ").orElse(null);
    Nereid2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=170000 +b=170000 +no_defs ").orElse(null);
    Oberon2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=761400 +b=761400 +no_defs ").orElse(null);
    Ophelia2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=15000 +b=15000 +no_defs ").orElse(null);
    Pan2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=10000 +b=10000 +no_defs ").orElse(null);
    Pandora2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=41900 +b=41900 +no_defs ").orElse(null);
    Pasiphae2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=18000 +b=18000 +no_defs ").orElse(null);
    Phobos2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=11100 +b=11100 +no_defs ").orElse(null);
    Phoebe2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=110000 +b=110000 +no_defs ").orElse(null);
    Pluto2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=1195000 +b=1195000 +no_defs ").orElse(null);
    Portia2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=54000 +b=54000 +no_defs ").orElse(null);
    Prometheus2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=50100 +b=50100 +no_defs ").orElse(null);
    Proteus2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=208000 +b=208000 +no_defs ").orElse(null);
    Puck2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=77000 +b=77000 +no_defs ").orElse(null);
    Rhea2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=764000 +b=764000 +no_defs ").orElse(null);
    Rosalind2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=27000 +b=27000 +no_defs ").orElse(null);
    Saturn2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=60268000 +b=54364000 +no_defs ").orElse(null);
    Sinope2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=14000 +b=14000 +no_defs ").orElse(null);
    Telesto2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=11000 +b=11000 +no_defs ").orElse(null);
    Tethys2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=529800 +b=529800 +no_defs ").orElse(null);
    Thalassa2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=40000 +b=40000 +no_defs ").orElse(null);
    Thebe2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=49300 +b=49300 +no_defs ").orElse(null);
    Titan2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=2575000 +b=2575000 +no_defs ").orElse(null);
    Titania2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=788900 +b=788900 +no_defs ").orElse(null);
    Triton2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=1352600 +b=1352600 +no_defs ").orElse(null);
    Umbriel2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=584700 +b=584700 +no_defs ").orElse(null);
    Uranus2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=25559000 +b=24973000 +no_defs ").orElse(null);
    Venus1985 = ProjectionInfo.fromProj4String("+proj=longlat +a=6051000 +b=6051000 +no_defs ").orElse(null);
    Venus2000 = ProjectionInfo.fromProj4String("+proj=longlat +a=6051800 +b=6051800 +no_defs ").orElse(null);

    Adrastea2000.setLatLon(true);
    Amalthea2000.setLatLon(true);
    Ananke2000.setLatLon(true);
    Ariel2000.setLatLon(true);
    Atlas2000.setLatLon(true);
    Belinda2000.setLatLon(true);
    Bianca2000.setLatLon(true);
    Callisto2000.setLatLon(true);
    Calypso2000.setLatLon(true);
    Carme2000.setLatLon(true);
    Charon2000.setLatLon(true);
    Cordelia2000.setLatLon(true);
    Cressida2000.setLatLon(true);
    Deimos2000.setLatLon(true);
    Desdemona2000.setLatLon(true);
    Despina2000.setLatLon(true);
    Dione2000.setLatLon(true);
    Elara2000.setLatLon(true);
    Enceladus2000.setLatLon(true);
    Epimetheus2000.setLatLon(true);
    Europa2000.setLatLon(true);
    Galatea2000.setLatLon(true);
    Ganymede2000.setLatLon(true);
    Helene2000.setLatLon(true);
    Himalia2000.setLatLon(true);
    Hyperion2000.setLatLon(true);
    Iapetus2000.setLatLon(true);
    Io2000.setLatLon(true);
    Janus2000.setLatLon(true);
    Juliet2000.setLatLon(true);
    Jupiter2000.setLatLon(true);
    Larissa2000.setLatLon(true);
    Leda2000.setLatLon(true);
    Lysithea2000.setLatLon(true);
    Mars1979.setLatLon(true);
    Mars2000.setLatLon(true);
    Mercury2000.setLatLon(true);
    Metis2000.setLatLon(true);
    Mimas2000.setLatLon(true);
    Miranda2000.setLatLon(true);
    Moon2000.setLatLon(true);
    Naiad2000.setLatLon(true);
    Neptune2000.setLatLon(true);
    Nereid2000.setLatLon(true);
    Oberon2000.setLatLon(true);
    Ophelia2000.setLatLon(true);
    Pan2000.setLatLon(true);
    Pandora2000.setLatLon(true);
    Pasiphae2000.setLatLon(true);
    Phobos2000.setLatLon(true);
    Phoebe2000.setLatLon(true);
    Pluto2000.setLatLon(true);
    Portia2000.setLatLon(true);
    Prometheus2000.setLatLon(true);
    Proteus2000.setLatLon(true);
    Puck2000.setLatLon(true);
    Rhea2000.setLatLon(true);
    Rosalind2000.setLatLon(true);
    Saturn2000.setLatLon(true);
    Sinope2000.setLatLon(true);
    Telesto2000.setLatLon(true);
    Tethys2000.setLatLon(true);
    Thalassa2000.setLatLon(true);
    Thebe2000.setLatLon(true);
    Titan2000.setLatLon(true);
    Titania2000.setLatLon(true);
    Triton2000.setLatLon(true);
    Umbriel2000.setLatLon(true);
    Uranus2000.setLatLon(true);
    Venus1985.setLatLon(true);
    Venus2000.setLatLon(true);

    Adrastea2000.getGeographicInfo().setName("GCS_Adrastea_2000");
    Amalthea2000.getGeographicInfo().setName("GCS_Amalthea_2000");
    Ananke2000.getGeographicInfo().setName("GCS_Ananke_2000");
    Ariel2000.getGeographicInfo().setName("GCS_Ariel_2000");
    Atlas2000.getGeographicInfo().setName("GCS_Atlas_2000");
    Belinda2000.getGeographicInfo().setName("GCS_Belinda_2000");
    Bianca2000.getGeographicInfo().setName("GCS_Bianca_2000");
    Callisto2000.getGeographicInfo().setName("GCS_Callisto_2000");
    Calypso2000.getGeographicInfo().setName("GCS_Calypso_2000");
    Carme2000.getGeographicInfo().setName("GCS_Carme_2000");
    Charon2000.getGeographicInfo().setName("GCS_Charon_2000");
    Cordelia2000.getGeographicInfo().setName("GCS_Cordelia_2000");
    Cressida2000.getGeographicInfo().setName("GCS_Cressida_2000");
    Deimos2000.getGeographicInfo().setName("GCS_Deimos_2000");
    Desdemona2000.getGeographicInfo().setName("GCS_Desdemona_2000");
    Despina2000.getGeographicInfo().setName("GCS_Despina_2000");
    Dione2000.getGeographicInfo().setName("GCS_Dione_2000");
    Elara2000.getGeographicInfo().setName("GCS_Elara_2000");
    Enceladus2000.getGeographicInfo().setName("GCS_Enceladus_2000");
    Epimetheus2000.getGeographicInfo().setName("GCS_Epimetheus_2000");
    Europa2000.getGeographicInfo().setName("GCS_Europa_2000");
    Galatea2000.getGeographicInfo().setName("GCS_Galatea_2000");
    Ganymede2000.getGeographicInfo().setName("GCS_Ganymede_2000");
    Helene2000.getGeographicInfo().setName("GCS_Helene_2000");
    Himalia2000.getGeographicInfo().setName("GCS_Himalia_2000");
    Hyperion2000.getGeographicInfo().setName("GCS_Hyperion_2000");
    Iapetus2000.getGeographicInfo().setName("GCS_Iapetus_2000");
    Io2000.getGeographicInfo().setName("GCS_Io_2000");
    Janus2000.getGeographicInfo().setName("GCS_Janus_2000");
    Juliet2000.getGeographicInfo().setName("GCS_Juliet_2000");
    Jupiter2000.getGeographicInfo().setName("GCS_Jupiter_2000");
    Larissa2000.getGeographicInfo().setName("GCS_Larissa_2000");
    Leda2000.getGeographicInfo().setName("GCS_Leda_2000");
    Lysithea2000.getGeographicInfo().setName("GCS_Lysithea_2000");
    Mars1979.getGeographicInfo().setName("GCS_Mars_1979");
    Mars2000.getGeographicInfo().setName("GCS_Mars_2000");
    Mercury2000.getGeographicInfo().setName("GCS_Mercury_2000");
    Metis2000.getGeographicInfo().setName("GCS_Metis_2000");
    Mimas2000.getGeographicInfo().setName("GCS_Mimas_2000");
    Miranda2000.getGeographicInfo().setName("GCS_Miranda_2000");
    Moon2000.getGeographicInfo().setName("GCS_Moon_2000");
    Naiad2000.getGeographicInfo().setName("GCS_Naiad_2000");
    Neptune2000.getGeographicInfo().setName("GCS_Neptune_2000");
    Nereid2000.getGeographicInfo().setName("GCS_Nereid_2000");
    Oberon2000.getGeographicInfo().setName("GCS_Oberon_2000");
    Ophelia2000.getGeographicInfo().setName("GCS_Ophelia_2000");
    Pan2000.getGeographicInfo().setName("GCS_Pan_2000");
    Pandora2000.getGeographicInfo().setName("GCS_Pandora_2000");
    Pasiphae2000.getGeographicInfo().setName("GCS_Pasiphae_2000");
    Phobos2000.getGeographicInfo().setName("GCS_Phobos_2000");
    Phoebe2000.getGeographicInfo().setName("GCS_Phoebe_2000");
    Pluto2000.getGeographicInfo().setName("GCS_Pluto_2000");
    Portia2000.getGeographicInfo().setName("GCS_Portia_2000");
    Prometheus2000.getGeographicInfo().setName("GCS_Prometheus_2000");
    Proteus2000.getGeographicInfo().setName("GCS_Proteus_2000");
    Puck2000.getGeographicInfo().setName("GCS_Puck_2000");
    Rhea2000.getGeographicInfo().setName("GCS_Rhea_2000");
    Rosalind2000.getGeographicInfo().setName("GCS_Rosalind_2000");
    Saturn2000.getGeographicInfo().setName("GCS_Saturn_2000");
    Sinope2000.getGeographicInfo().setName("GCS_Sinope_2000");
    Telesto2000.getGeographicInfo().setName("GCS_Telesto_2000");
    Tethys2000.getGeographicInfo().setName("GCS_Tethys_2000");
    Thalassa2000.getGeographicInfo().setName("GCS_Thalassa_2000");
    Thebe2000.getGeographicInfo().setName("GCS_Thebe_2000");
    Titan2000.getGeographicInfo().setName("GCS_Titan_2000");
    Titania2000.getGeographicInfo().setName("GCS_Titania_2000");
    Triton2000.getGeographicInfo().setName("GCS_Triton_2000");
    Umbriel2000.getGeographicInfo().setName("GCS_Umbriel_2000");
    Uranus2000.getGeographicInfo().setName("GCS_Uranus_2000");
    Venus1985.getGeographicInfo().setName("GCS_Venus_1985");
    Venus2000.getGeographicInfo().setName("GCS_Venus_2000");

    Adrastea2000.setName("GCS_Adrastea_2000");
    Amalthea2000.setName("GCS_Amalthea_2000");
    Ananke2000.setName("GCS_Ananke_2000");
    Ariel2000.setName("GCS_Ariel_2000");
    Atlas2000.setName("GCS_Atlas_2000");
    Belinda2000.setName("GCS_Belinda_2000");
    Bianca2000.setName("GCS_Bianca_2000");
    Callisto2000.setName("GCS_Callisto_2000");
    Calypso2000.setName("GCS_Calypso_2000");
    Carme2000.setName("GCS_Carme_2000");
    Charon2000.setName("GCS_Charon_2000");
    Cordelia2000.setName("GCS_Cordelia_2000");
    Cressida2000.setName("GCS_Cressida_2000");
    Deimos2000.setName("GCS_Deimos_2000");
    Desdemona2000.setName("GCS_Desdemona_2000");
    Despina2000.setName("GCS_Despina_2000");
    Dione2000.setName("GCS_Dione_2000");
    Elara2000.setName("GCS_Elara_2000");
    Enceladus2000.setName("GCS_Enceladus_2000");
    Epimetheus2000.setName("GCS_Epimetheus_2000");
    Europa2000.setName("GCS_Europa_2000");
    Galatea2000.setName("GCS_Galatea_2000");
    Ganymede2000.setName("GCS_Ganymede_2000");
    Helene2000.setName("GCS_Helene_2000");
    Himalia2000.setName("GCS_Himalia_2000");
    Hyperion2000.setName("GCS_Hyperion_2000");
    Iapetus2000.setName("GCS_Iapetus_2000");
    Io2000.setName("GCS_Io_2000");
    Janus2000.setName("GCS_Janus_2000");
    Juliet2000.setName("GCS_Juliet_2000");
    Jupiter2000.setName("GCS_Jupiter_2000");
    Larissa2000.setName("GCS_Larissa_2000");
    Leda2000.setName("GCS_Leda_2000");
    Lysithea2000.setName("GCS_Lysithea_2000");
    Mars1979.setName("GCS_Mars_1979");
    Mars2000.setName("GCS_Mars_2000");
    Mercury2000.setName("GCS_Mercury_2000");
    Metis2000.setName("GCS_Metis_2000");
    Mimas2000.setName("GCS_Mimas_2000");
    Miranda2000.setName("GCS_Miranda_2000");
    Moon2000.setName("GCS_Moon_2000");
    Naiad2000.setName("GCS_Naiad_2000");
    Neptune2000.setName("GCS_Neptune_2000");
    Nereid2000.setName("GCS_Nereid_2000");
    Oberon2000.setName("GCS_Oberon_2000");
    Ophelia2000.setName("GCS_Ophelia_2000");
    Pan2000.setName("GCS_Pan_2000");
    Pandora2000.setName("GCS_Pandora_2000");
    Pasiphae2000.setName("GCS_Pasiphae_2000");
    Phobos2000.setName("GCS_Phobos_2000");
    Phoebe2000.setName("GCS_Phoebe_2000");
    Pluto2000.setName("GCS_Pluto_2000");
    Portia2000.setName("GCS_Portia_2000");
    Prometheus2000.setName("GCS_Prometheus_2000");
    Proteus2000.setName("GCS_Proteus_2000");
    Puck2000.setName("GCS_Puck_2000");
    Rhea2000.setName("GCS_Rhea_2000");
    Rosalind2000.setName("GCS_Rosalind_2000");
    Saturn2000.setName("GCS_Saturn_2000");
    Sinope2000.setName("GCS_Sinope_2000");
    Telesto2000.setName("GCS_Telesto_2000");
    Tethys2000.setName("GCS_Tethys_2000");
    Thalassa2000.setName("GCS_Thalassa_2000");
    Thebe2000.setName("GCS_Thebe_2000");
    Titan2000.setName("GCS_Titan_2000");
    Titania2000.setName("GCS_Titania_2000");
    Triton2000.setName("GCS_Triton_2000");
    Umbriel2000.setName("GCS_Umbriel_2000");
    Uranus2000.setName("GCS_Uranus_2000");
    Venus1985.setName("GCS_Venus_1985");
    Venus2000.setName("GCS_Venus_2000");

    Adrastea2000.getGeographicInfo().getDatum().setName("D_Adrastea_2000");
    Amalthea2000.getGeographicInfo().getDatum().setName("D_Amalthea_2000");
    Ananke2000.getGeographicInfo().getDatum().setName("D_Ananke_2000");
    Ariel2000.getGeographicInfo().getDatum().setName("D_Ariel_2000");
    Atlas2000.getGeographicInfo().getDatum().setName("D_Atlas_2000");
    Belinda2000.getGeographicInfo().getDatum().setName("D_Belinda_2000");
    Bianca2000.getGeographicInfo().getDatum().setName("D_Bianca_2000");
    Callisto2000.getGeographicInfo().getDatum().setName("D_Callisto_2000");
    Calypso2000.getGeographicInfo().getDatum().setName("D_Calypso_2000");
    Carme2000.getGeographicInfo().getDatum().setName("D_Carme_2000");
    Charon2000.getGeographicInfo().getDatum().setName("D_Charon_2000");
    Cordelia2000.getGeographicInfo().getDatum().setName("D_Cordelia_2000");
    Cressida2000.getGeographicInfo().getDatum().setName("D_Cressida_2000");
    Deimos2000.getGeographicInfo().getDatum().setName("D_Deimos_2000");
    Desdemona2000.getGeographicInfo().getDatum().setName("D_Desdemona_2000");
    Despina2000.getGeographicInfo().getDatum().setName("D_Despina_2000");
    Dione2000.getGeographicInfo().getDatum().setName("D_Dione_2000");
    Elara2000.getGeographicInfo().getDatum().setName("D_Elara_2000");
    Enceladus2000.getGeographicInfo().getDatum().setName("D_Enceladus_2000");
    Epimetheus2000.getGeographicInfo().getDatum().setName("D_Epimetheus_2000");
    Europa2000.getGeographicInfo().getDatum().setName("D_Europa_2000");
    Galatea2000.getGeographicInfo().getDatum().setName("D_Galatea_2000");
    Ganymede2000.getGeographicInfo().getDatum().setName("D_Ganymede_2000");
    Helene2000.getGeographicInfo().getDatum().setName("D_Helene_2000");
    Himalia2000.getGeographicInfo().getDatum().setName("D_Himalia_2000");
    Hyperion2000.getGeographicInfo().getDatum().setName("D_Hyperion_2000");
    Iapetus2000.getGeographicInfo().getDatum().setName("D_Iapetus_2000");
    Io2000.getGeographicInfo().getDatum().setName("D_Io_2000");
    Janus2000.getGeographicInfo().getDatum().setName("D_Janus_2000");
    Juliet2000.getGeographicInfo().getDatum().setName("D_Juliet_2000");
    Jupiter2000.getGeographicInfo().getDatum().setName("D_Jupiter_2000");
    Larissa2000.getGeographicInfo().getDatum().setName("D_Larissa_2000");
    Leda2000.getGeographicInfo().getDatum().setName("D_Leda_2000");
    Lysithea2000.getGeographicInfo().getDatum().setName("D_Lysithea_2000");
    Mars1979.getGeographicInfo().getDatum().setName("D_Mars_1979");
    Mars2000.getGeographicInfo().getDatum().setName("D_Mars_2000");
    Mercury2000.getGeographicInfo().getDatum().setName("D_Mercury_2000");
    Metis2000.getGeographicInfo().getDatum().setName("D_Metis_2000");
    Mimas2000.getGeographicInfo().getDatum().setName("D_Mimas_2000");
    Miranda2000.getGeographicInfo().getDatum().setName("D_Miranda_2000");
    Moon2000.getGeographicInfo().getDatum().setName("D_Moon_2000");
    Naiad2000.getGeographicInfo().getDatum().setName("D_Naiad_2000");
    Neptune2000.getGeographicInfo().getDatum().setName("D_Neptune_2000");
    Nereid2000.getGeographicInfo().getDatum().setName("D_Nereid_2000");
    Oberon2000.getGeographicInfo().getDatum().setName("D_Oberon_2000");
    Ophelia2000.getGeographicInfo().getDatum().setName("D_Ophelia_2000");
    Pan2000.getGeographicInfo().getDatum().setName("D_Pan_2000");
    Pandora2000.getGeographicInfo().getDatum().setName("D_Pandora_2000");
    Pasiphae2000.getGeographicInfo().getDatum().setName("D_Pasiphae_2000");
    Phobos2000.getGeographicInfo().getDatum().setName("D_Phobos_2000");
    Phoebe2000.getGeographicInfo().getDatum().setName("D_Phoebe_2000");
    Pluto2000.getGeographicInfo().getDatum().setName("D_Pluto_2000");
    Portia2000.getGeographicInfo().getDatum().setName("D_Portia_2000");
    Prometheus2000.getGeographicInfo().getDatum().setName("D_Prometheus_2000");
    Proteus2000.getGeographicInfo().getDatum().setName("D_Proteus_2000");
    Puck2000.getGeographicInfo().getDatum().setName("D_Puck_2000");
    Rhea2000.getGeographicInfo().getDatum().setName("D_Rhea_2000");
    Rosalind2000.getGeographicInfo().getDatum().setName("D_Rosalind_2000");
    Saturn2000.getGeographicInfo().getDatum().setName("D_Saturn_2000");
    Sinope2000.getGeographicInfo().getDatum().setName("D_Sinope_2000");
    Telesto2000.getGeographicInfo().getDatum().setName("D_Telesto_2000");
    Tethys2000.getGeographicInfo().getDatum().setName("D_Tethys_2000");
    Thalassa2000.getGeographicInfo().getDatum().setName("D_Thalassa_2000");
    Thebe2000.getGeographicInfo().getDatum().setName("D_Thebe_2000");
    Titan2000.getGeographicInfo().getDatum().setName("D_Titan_2000");
    Titania2000.getGeographicInfo().getDatum().setName("D_Titania_2000");
    Triton2000.getGeographicInfo().getDatum().setName("D_Triton_2000");
    Umbriel2000.getGeographicInfo().getDatum().setName("D_Umbriel_2000");
    Uranus2000.getGeographicInfo().getDatum().setName("D_Uranus_2000");
    Venus1985.getGeographicInfo().getDatum().setName("D_Venus_1985");
    Venus2000.getGeographicInfo().getDatum().setName("D_Venus_2000");
  }

  //</editor-fold>

  /**
   * @return the Adrastea2000
   */
  public ProjectionInfo getAdrastea2000() {
    return Adrastea2000.copy();
  }

  /**
   * @return the Amalthea2000
   */
  public ProjectionInfo getAmalthea2000() {
    return Amalthea2000.copy();
  }

  /**
   * @return the Ananke2000
   */
  public ProjectionInfo getAnanke2000() {
    return Ananke2000.copy();
  }

  /**
   * @return the Ariel2000
   */
  public ProjectionInfo getAriel2000() {
    return Ariel2000.copy();
  }

  /**
   * @return the Atlas2000
   */
  public ProjectionInfo getAtlas2000() {
    return Atlas2000.copy();
  }

  /**
   * @return the Belinda2000
   */
  public ProjectionInfo getBelinda2000() {
    return Belinda2000.copy();
  }

  /**
   * @return the Bianca2000
   */
  public ProjectionInfo getBianca2000() {
    return Bianca2000.copy();
  }

  /**
   * @return the Callisto2000
   */
  public ProjectionInfo getCallisto2000() {
    return Callisto2000.copy();
  }

  /**
   * @return the Calypso2000
   */
  public ProjectionInfo getCalypso2000() {
    return Calypso2000.copy();
  }

  /**
   * @return the Carme2000
   */
  public ProjectionInfo getCarme2000() {
    return Carme2000.copy();
  }

  /**
   * @return the Charon2000
   */
  public ProjectionInfo getCharon2000() {
    return Charon2000.copy();
  }

  /**
   * @return the Cordelia2000
   */
  public ProjectionInfo getCordelia2000() {
    return Cordelia2000.copy();
  }

  /**
   * @return the Cressida2000
   */
  public ProjectionInfo getCressida2000() {
    return Cressida2000.copy();
  }

  /**
   * @return the Deimos2000
   */
  public ProjectionInfo getDeimos2000() {
    return Deimos2000.copy();
  }

  /**
   * @return the Desdemona2000
   */
  public ProjectionInfo getDesdemona2000() {
    return Desdemona2000.copy();
  }

  /**
   * @return the Despina2000
   */
  public ProjectionInfo getDespina2000() {
    return Despina2000.copy();
  }

  /**
   * @return the Dione2000
   */
  public ProjectionInfo getDione2000() {
    return Dione2000.copy();
  }

  /**
   * @return the Elara2000
   */
  public ProjectionInfo getElara2000() {
    return Elara2000.copy();
  }

  /**
   * @return the Enceladus2000
   */
  public ProjectionInfo getEnceladus2000() {
    return Enceladus2000.copy();
  }

  /**
   * @return the Epimetheus2000
   */
  public ProjectionInfo getEpimetheus2000() {
    return Epimetheus2000.copy();
  }

  /**
   * @return the Europa2000
   */
  public ProjectionInfo getEuropa2000() {
    return Europa2000.copy();
  }

  /**
   * @return the Galatea2000
   */
  public ProjectionInfo getGalatea2000() {
    return Galatea2000.copy();
  }

  /**
   * @return the Ganymede2000
   */
  public ProjectionInfo getGanymede2000() {
    return Ganymede2000.copy();
  }

  /**
   * @return the Helene2000
   */
  public ProjectionInfo getHelene2000() {
    return Helene2000.copy();
  }

  /**
   * @return the Himalia2000
   */
  public ProjectionInfo getHimalia2000() {
    return Himalia2000.copy();
  }

  /**
   * @return the Hyperion2000
   */
  public ProjectionInfo getHyperion2000() {
    return Hyperion2000.copy();
  }

  /**
   * @return the Iapetus2000
   */
  public ProjectionInfo getIapetus2000() {
    return Iapetus2000.copy();
  }

  /**
   * @return the Io2000
   */
  public ProjectionInfo getIo2000() {
    return Io2000.copy();
  }

  /**
   * @return the Janus2000
   */
  public ProjectionInfo getJanus2000() {
    return Janus2000.copy();
  }

  /**
   * @return the Juliet2000
   */
  public ProjectionInfo getJuliet2000() {
    return Juliet2000.copy();
  }

  /**
   * @return the Jupiter2000
   */
  public ProjectionInfo getJupiter2000() {
    return Jupiter2000.copy();
  }

  /**
   * @return the Larissa2000
   */
  public ProjectionInfo getLarissa2000() {
    return Larissa2000.copy();
  }

  /**
   * @return the Leda2000
   */
  public ProjectionInfo getLeda2000() {
    return Leda2000.copy();
  }

  /**
   * @return the Lysithea2000
   */
  public ProjectionInfo getLysithea2000() {
    return Lysithea2000.copy();
  }

  /**
   * @return the Mars1979
   */
  public ProjectionInfo getMars1979() {
    return Mars1979.copy();
  }

  /**
   * @return the Mars2000
   */
  public ProjectionInfo getMars2000() {
    return Mars2000.copy();
  }

  /**
   * @return the Mercury2000
   */
  public ProjectionInfo getMercury2000() {
    return Mercury2000.copy();
  }

  /**
   * @return the Metis2000
   */
  public ProjectionInfo getMetis2000() {
    return Metis2000.copy();
  }

  /**
   * @return the Mimas2000
   */
  public ProjectionInfo getMimas2000() {
    return Mimas2000.copy();
  }

  /**
   * @return the Miranda2000
   */
  public ProjectionInfo getMiranda2000() {
    return Miranda2000.copy();
  }

  /**
   * @return the Moon2000
   */
  public ProjectionInfo getMoon2000() {
    return Moon2000.copy();
  }

  /**
   * @return the Naiad2000
   */
  public ProjectionInfo getNaiad2000() {
    return Naiad2000.copy();
  }

  /**
   * @return the Neptune2000
   */
  public ProjectionInfo getNeptune2000() {
    return Neptune2000.copy();
  }

  /**
   * @return the Nereid2000
   */
  public ProjectionInfo getNereid2000() {
    return Nereid2000.copy();
  }

  /**
   * @return the Oberon2000
   */
  public ProjectionInfo getOberon2000() {
    return Oberon2000.copy();
  }

  /**
   * @return the Ophelia2000
   */
  public ProjectionInfo getOphelia2000() {
    return Ophelia2000.copy();
  }

  /**
   * @return the Pan2000
   */
  public ProjectionInfo getPan2000() {
    return Pan2000.copy();
  }

  /**
   * @return the Pandora2000
   */
  public ProjectionInfo getPandora2000() {
    return Pandora2000.copy();
  }

  /**
   * @return the Pasiphae2000
   */
  public ProjectionInfo getPasiphae2000() {
    return Pasiphae2000.copy();
  }

  /**
   * @return the Phobos2000
   */
  public ProjectionInfo getPhobos2000() {
    return Phobos2000.copy();
  }

  /**
   * @return the Phoebe2000
   */
  public ProjectionInfo getPhoebe2000() {
    return Phoebe2000.copy();
  }

  /**
   * @return the Pluto2000
   */
  public ProjectionInfo getPluto2000() {
    return Pluto2000.copy();
  }

  /**
   * @return the Portia2000
   */
  public ProjectionInfo getPortia2000() {
    return Portia2000.copy();
  }

  /**
   * @return the Prometheus2000
   */
  public ProjectionInfo getPrometheus2000() {
    return Prometheus2000.copy();
  }

  /**
   * @return the Proteus2000
   */
  public ProjectionInfo getProteus2000() {
    return Proteus2000.copy();
  }

  /**
   * @return the Puck2000
   */
  public ProjectionInfo getPuck2000() {
    return Puck2000.copy();
  }

  /**
   * @return the Rhea2000
   */
  public ProjectionInfo getRhea2000() {
    return Rhea2000.copy();
  }

  /**
   * @return the Rosalind2000
   */
  public ProjectionInfo getRosalind2000() {
    return Rosalind2000.copy();
  }

  /**
   * @return the Saturn2000
   */
  public ProjectionInfo getSaturn2000() {
    return Saturn2000.copy();
  }

  /**
   * @return the Sinope2000
   */
  public ProjectionInfo getSinope2000() {
    return Sinope2000.copy();
  }

  /**
   * @return the Telesto2000
   */
  public ProjectionInfo getTelesto2000() {
    return Telesto2000.copy();
  }

  /**
   * @return the Tethys2000
   */
  public ProjectionInfo getTethys2000() {
    return Tethys2000.copy();
  }

  /**
   * @return the Thalassa2000
   */
  public ProjectionInfo getThalassa2000() {
    return Thalassa2000.copy();
  }

  /**
   * @return the Thebe2000
   */
  public ProjectionInfo getThebe2000() {
    return Thebe2000.copy();
  }

  /**
   * @return the Titan2000
   */
  public ProjectionInfo getTitan2000() {
    return Titan2000.copy();
  }

  /**
   * @return the Titania2000
   */
  public ProjectionInfo getTitania2000() {
    return Titania2000.copy();
  }

  /**
   * @return the Triton2000
   */
  public ProjectionInfo getTriton2000() {
    return Triton2000.copy();
  }

  /**
   * @return the Umbriel2000
   */
  public ProjectionInfo getUmbriel2000() {
    return Umbriel2000.copy();
  }

  /**
   * @return the Uranus2000
   */
  public ProjectionInfo getUranus2000() {
    return Uranus2000.copy();
  }

  /**
   * @return the Venus1985
   */
  public ProjectionInfo getVenus1985() {
    return Venus1985.copy();
  }

  /**
   * @return the Venus2000
   */
  public ProjectionInfo getVenus2000() {
    return Venus2000.copy();
  }
}
