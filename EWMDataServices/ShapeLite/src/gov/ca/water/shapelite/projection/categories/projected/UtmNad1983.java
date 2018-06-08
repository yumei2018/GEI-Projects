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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 5:08:05 PM
//
// Contributor(s): (Open source contributors should list themselves and their modifications here).
//        Name         |    Date    |        Comment
// --------------------|------------|------------------------------------------------------------
// Ted Dunsford        |   5/3/2010 |  Updated project to DotSpatial.Projection and license to LGPL
// ********************************************************************************************************
package gov.ca.water.shapelite.projection.categories.projected;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 * UtmNad1983
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class UtmNad1983 extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo NAD1983UTMZone10N;
  private final ProjectionInfo NAD1983UTMZone10S;
  private final ProjectionInfo NAD1983UTMZone11N;
  private final ProjectionInfo NAD1983UTMZone11S;
  private final ProjectionInfo NAD1983UTMZone12N;
  private final ProjectionInfo NAD1983UTMZone12S;
  private final ProjectionInfo NAD1983UTMZone13N;
  private final ProjectionInfo NAD1983UTMZone13S;
  private final ProjectionInfo NAD1983UTMZone14N;
  private final ProjectionInfo NAD1983UTMZone14S;
  private final ProjectionInfo NAD1983UTMZone15N;
  private final ProjectionInfo NAD1983UTMZone15S;
  private final ProjectionInfo NAD1983UTMZone16N;
  private final ProjectionInfo NAD1983UTMZone16S;
  private final ProjectionInfo NAD1983UTMZone17N;
  private final ProjectionInfo NAD1983UTMZone17S;
  private final ProjectionInfo NAD1983UTMZone18N;
  private final ProjectionInfo NAD1983UTMZone18S;
  private final ProjectionInfo NAD1983UTMZone19N;
  private final ProjectionInfo NAD1983UTMZone19S;
  private final ProjectionInfo NAD1983UTMZone1N;
  private final ProjectionInfo NAD1983UTMZone1S;
  private final ProjectionInfo NAD1983UTMZone20N;
  private final ProjectionInfo NAD1983UTMZone20S;
  private final ProjectionInfo NAD1983UTMZone21N;
  private final ProjectionInfo NAD1983UTMZone21S;
  private final ProjectionInfo NAD1983UTMZone22N;
  private final ProjectionInfo NAD1983UTMZone22S;
  private final ProjectionInfo NAD1983UTMZone23N;
  private final ProjectionInfo NAD1983UTMZone23S;
  private final ProjectionInfo NAD1983UTMZone24N;
  private final ProjectionInfo NAD1983UTMZone24S;
  private final ProjectionInfo NAD1983UTMZone25N;
  private final ProjectionInfo NAD1983UTMZone25S;
  private final ProjectionInfo NAD1983UTMZone26N;
  private final ProjectionInfo NAD1983UTMZone26S;
  private final ProjectionInfo NAD1983UTMZone27N;
  private final ProjectionInfo NAD1983UTMZone27S;
  private final ProjectionInfo NAD1983UTMZone28N;
  private final ProjectionInfo NAD1983UTMZone28S;
  private final ProjectionInfo NAD1983UTMZone29N;
  private final ProjectionInfo NAD1983UTMZone29S;
  private final ProjectionInfo NAD1983UTMZone2N;
  private final ProjectionInfo NAD1983UTMZone2S;
  private final ProjectionInfo NAD1983UTMZone30N;
  private final ProjectionInfo NAD1983UTMZone30S;
  private final ProjectionInfo NAD1983UTMZone31N;
  private final ProjectionInfo NAD1983UTMZone31S;
  private final ProjectionInfo NAD1983UTMZone32N;
  private final ProjectionInfo NAD1983UTMZone32S;
  private final ProjectionInfo NAD1983UTMZone33N;
  private final ProjectionInfo NAD1983UTMZone33S;
  private final ProjectionInfo NAD1983UTMZone34N;
  private final ProjectionInfo NAD1983UTMZone34S;
  private final ProjectionInfo NAD1983UTMZone35N;
  private final ProjectionInfo NAD1983UTMZone35S;
  private final ProjectionInfo NAD1983UTMZone36N;
  private final ProjectionInfo NAD1983UTMZone36S;
  private final ProjectionInfo NAD1983UTMZone37N;
  private final ProjectionInfo NAD1983UTMZone37S;
  private final ProjectionInfo NAD1983UTMZone38N;
  private final ProjectionInfo NAD1983UTMZone38S;
  private final ProjectionInfo NAD1983UTMZone39N;
  private final ProjectionInfo NAD1983UTMZone39S;
  private final ProjectionInfo NAD1983UTMZone3N;
  private final ProjectionInfo NAD1983UTMZone3S;
  private final ProjectionInfo NAD1983UTMZone40N;
  private final ProjectionInfo NAD1983UTMZone40S;
  private final ProjectionInfo NAD1983UTMZone41N;
  private final ProjectionInfo NAD1983UTMZone41S;
  private final ProjectionInfo NAD1983UTMZone42N;
  private final ProjectionInfo NAD1983UTMZone42S;
  private final ProjectionInfo NAD1983UTMZone43N;
  private final ProjectionInfo NAD1983UTMZone43S;
  private final ProjectionInfo NAD1983UTMZone44N;
  private final ProjectionInfo NAD1983UTMZone44S;
  private final ProjectionInfo NAD1983UTMZone45N;
  private final ProjectionInfo NAD1983UTMZone45S;
  private final ProjectionInfo NAD1983UTMZone46N;
  private final ProjectionInfo NAD1983UTMZone46S;
  private final ProjectionInfo NAD1983UTMZone47N;
  private final ProjectionInfo NAD1983UTMZone47S;
  private final ProjectionInfo NAD1983UTMZone48N;
  private final ProjectionInfo NAD1983UTMZone48S;
  private final ProjectionInfo NAD1983UTMZone49N;
  private final ProjectionInfo NAD1983UTMZone49S;
  private final ProjectionInfo NAD1983UTMZone4N;
  private final ProjectionInfo NAD1983UTMZone4S;
  private final ProjectionInfo NAD1983UTMZone50N;
  private final ProjectionInfo NAD1983UTMZone50S;
  private final ProjectionInfo NAD1983UTMZone51N;
  private final ProjectionInfo NAD1983UTMZone51S;
  private final ProjectionInfo NAD1983UTMZone52N;
  private final ProjectionInfo NAD1983UTMZone52S;
  private final ProjectionInfo NAD1983UTMZone53N;
  private final ProjectionInfo NAD1983UTMZone53S;
  private final ProjectionInfo NAD1983UTMZone54N;
  private final ProjectionInfo NAD1983UTMZone54S;
  private final ProjectionInfo NAD1983UTMZone55N;
  private final ProjectionInfo NAD1983UTMZone55S;
  private final ProjectionInfo NAD1983UTMZone56N;
  private final ProjectionInfo NAD1983UTMZone56S;
  private final ProjectionInfo NAD1983UTMZone57N;
  private final ProjectionInfo NAD1983UTMZone57S;
  private final ProjectionInfo NAD1983UTMZone58N;
  private final ProjectionInfo NAD1983UTMZone58S;
  private final ProjectionInfo NAD1983UTMZone59N;
  private final ProjectionInfo NAD1983UTMZone59S;
  private final ProjectionInfo NAD1983UTMZone5N;
  private final ProjectionInfo NAD1983UTMZone5S;
  private final ProjectionInfo NAD1983UTMZone60N;
  private final ProjectionInfo NAD1983UTMZone60S;
  private final ProjectionInfo NAD1983UTMZone6N;
  private final ProjectionInfo NAD1983UTMZone6S;
  private final ProjectionInfo NAD1983UTMZone7N;
  private final ProjectionInfo NAD1983UTMZone7S;
  private final ProjectionInfo NAD1983UTMZone8N;
  private final ProjectionInfo NAD1983UTMZone8S;
  private final ProjectionInfo NAD1983UTMZone9N;
  private final ProjectionInfo NAD1983UTMZone9S;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of UtmNad1983.
   */
  public UtmNad1983() {
    NAD1983UTMZone1N = ProjectionInfo.fromProj4String("+proj=utm +zone=1 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone2N = ProjectionInfo.fromProj4String("+proj=utm +zone=2 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone3N = ProjectionInfo.fromProj4String("+proj=utm +zone=3 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone4N = ProjectionInfo.fromProj4String("+proj=utm +zone=4 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone5N = ProjectionInfo.fromProj4String("+proj=utm +zone=5 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone6N = ProjectionInfo.fromProj4String("+proj=utm +zone=6 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone7N = ProjectionInfo.fromProj4String("+proj=utm +zone=7 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone8N = ProjectionInfo.fromProj4String("+proj=utm +zone=8 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone9N = ProjectionInfo.fromProj4String("+proj=utm +zone=9 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone10N = ProjectionInfo.fromProj4String("+proj=utm +zone=10 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone11N = ProjectionInfo.fromProj4String("+proj=utm +zone=11 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone12N = ProjectionInfo.fromProj4String("+proj=utm +zone=12 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone13N = ProjectionInfo.fromProj4String("+proj=utm +zone=13 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone14N = ProjectionInfo.fromProj4String("+proj=utm +zone=14 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone15N = ProjectionInfo.fromProj4String("+proj=utm +zone=15 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone16N = ProjectionInfo.fromProj4String("+proj=utm +zone=16 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone17N = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone22N = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone23N = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone24N = ProjectionInfo.fromProj4String("+proj=utm +zone=24 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone25N = ProjectionInfo.fromProj4String("+proj=utm +zone=25 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone26N = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone27N = ProjectionInfo.fromProj4String("+proj=utm +zone=27 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone30N = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone31N = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone34N = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone35N = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone36N = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone37N = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone40N = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone41N = ProjectionInfo.fromProj4String("+proj=utm +zone=41 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone42N = ProjectionInfo.fromProj4String("+proj=utm +zone=42 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone43N = ProjectionInfo.fromProj4String("+proj=utm +zone=43 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone44N = ProjectionInfo.fromProj4String("+proj=utm +zone=44 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone45N = ProjectionInfo.fromProj4String("+proj=utm +zone=45 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone46N = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone47N = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone48N = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone49N = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone50N = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone51N = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone52N = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone53N = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone54N = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone55N = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone56N = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone57N = ProjectionInfo.fromProj4String("+proj=utm +zone=57 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone58N = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone59N = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone60N = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);

    NAD1983UTMZone1S = ProjectionInfo.fromProj4String("+proj=utm +zone=1 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone2S = ProjectionInfo.fromProj4String("+proj=utm +zone=2 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone3S = ProjectionInfo.fromProj4String("+proj=utm +zone=3 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone4S = ProjectionInfo.fromProj4String("+proj=utm +zone=4 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone5S = ProjectionInfo.fromProj4String("+proj=utm +zone=5 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone6S = ProjectionInfo.fromProj4String("+proj=utm +zone=6 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone7S = ProjectionInfo.fromProj4String("+proj=utm +zone=7 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone8S = ProjectionInfo.fromProj4String("+proj=utm +zone=8 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone9S = ProjectionInfo.fromProj4String("+proj=utm +zone=9 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone10S = ProjectionInfo.fromProj4String("+proj=utm +zone=10 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone11S = ProjectionInfo.fromProj4String("+proj=utm +zone=11 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone12S = ProjectionInfo.fromProj4String("+proj=utm +zone=12 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone13S = ProjectionInfo.fromProj4String("+proj=utm +zone=13 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone14S = ProjectionInfo.fromProj4String("+proj=utm +zone=14 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone15S = ProjectionInfo.fromProj4String("+proj=utm +zone=15 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone16S = ProjectionInfo.fromProj4String("+proj=utm +zone=16 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone17S = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone18S = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone19S = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone20S = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone21S = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone22S = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone23S = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone24S = ProjectionInfo.fromProj4String("+proj=utm +zone=24 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone25S = ProjectionInfo.fromProj4String("+proj=utm +zone=25 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone26S = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone27S = ProjectionInfo.fromProj4String("+proj=utm +zone=27 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone28S = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone29S = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone30S = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone31S = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone32S = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone33S = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone34S = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone35S = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone36S = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone37S = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone38S = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone39S = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone40S = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone41S = ProjectionInfo.fromProj4String("+proj=utm +zone=41 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone42S = ProjectionInfo.fromProj4String("+proj=utm +zone=42 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone43S = ProjectionInfo.fromProj4String("+proj=utm +zone=43 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone44S = ProjectionInfo.fromProj4String("+proj=utm +zone=44 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone45S = ProjectionInfo.fromProj4String("+proj=utm +zone=45 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone46S = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone47S = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone48S = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone49S = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone50S = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone51S = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone52S = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone53S = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone54S = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone55S = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone56S = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone57S = ProjectionInfo.fromProj4String("+proj=utm +zone=57 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone59S = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);
    NAD1983UTMZone60S = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +south +ellps=GRS80 +datum=NAD83 +units=m +no_defs ").orElse(null);

    NAD1983UTMZone1N.setName("NAD_1983_UTM_Zone_1N");
    NAD1983UTMZone2N.setName("NAD_1983_UTM_Zone_D1983UTMZone2N");
    NAD1983UTMZone3N.setName("NAD_1983_UTM_Zone_D1983UTMZone3N");
    NAD1983UTMZone4N.setName("NAD_1983_UTM_Zone_D1983UTMZone4N");
    NAD1983UTMZone5N.setName("NAD_1983_UTM_Zone_D1983UTMZone5N");
    NAD1983UTMZone6N.setName("NAD_1983_UTM_Zone_D1983UTMZone6N");
    NAD1983UTMZone7N.setName("NAD_1983_UTM_Zone_D1983UTMZone7N");
    NAD1983UTMZone8N.setName("NAD_1983_UTM_Zone_D1983UTMZone8N");
    NAD1983UTMZone9N.setName("NAD_1983_UTM_Zone_D1983UTMZone9N");
    NAD1983UTMZone10N.setName("NAD_1983_UTM_Zone_D1983UTMZone10N");
    NAD1983UTMZone11N.setName("NAD_1983_UTM_Zone_D1983UTMZone11N");
    NAD1983UTMZone12N.setName("NAD_1983_UTM_Zone_D1983UTMZone12N");
    NAD1983UTMZone13N.setName("NAD_1983_UTM_Zone_D1983UTMZone13N");
    NAD1983UTMZone14N.setName("NAD_1983_UTM_Zone_D1983UTMZone14N");
    NAD1983UTMZone15N.setName("NAD_1983_UTM_Zone_D1983UTMZone15N");
    NAD1983UTMZone16N.setName("NAD_1983_UTM_Zone_D1983UTMZone16N");
    NAD1983UTMZone17N.setName("NAD_1983_UTM_Zone_D1983UTMZone17N");
    NAD1983UTMZone18N.setName("NAD_1983_UTM_Zone_D1983UTMZone18N");
    NAD1983UTMZone19N.setName("NAD_1983_UTM_Zone_D1983UTMZone19N");
    NAD1983UTMZone20N.setName("NAD_1983_UTM_Zone_D1983UTMZone20N");
    NAD1983UTMZone21N.setName("NAD_1983_UTM_Zone_D1983UTMZone21N");
    NAD1983UTMZone22N.setName("NAD_1983_UTM_Zone_D1983UTMZone22N");
    NAD1983UTMZone23N.setName("NAD_1983_UTM_Zone_D1983UTMZone23N");
    NAD1983UTMZone24N.setName("NAD_1983_UTM_Zone_D1983UTMZone24N");
    NAD1983UTMZone25N.setName("NAD_1983_UTM_Zone_D1983UTMZone25N");
    NAD1983UTMZone26N.setName("NAD_1983_UTM_Zone_D1983UTMZone26N");
    NAD1983UTMZone27N.setName("NAD_1983_UTM_Zone_D1983UTMZone27N");
    NAD1983UTMZone28N.setName("NAD_1983_UTM_Zone_D1983UTMZone28N");
    NAD1983UTMZone29N.setName("NAD_1983_UTM_Zone_D1983UTMZone29N");
    NAD1983UTMZone30N.setName("NAD_1983_UTM_Zone_D1983UTMZone30N");
    NAD1983UTMZone31N.setName("NAD_1983_UTM_Zone_D1983UTMZone31N");
    NAD1983UTMZone32N.setName("NAD_1983_UTM_Zone_D1983UTMZone32N");
    NAD1983UTMZone33N.setName("NAD_1983_UTM_Zone_D1983UTMZone33N");
    NAD1983UTMZone34N.setName("NAD_1983_UTM_Zone_D1983UTMZone34N");
    NAD1983UTMZone35N.setName("NAD_1983_UTM_Zone_D1983UTMZone35N");
    NAD1983UTMZone36N.setName("NAD_1983_UTM_Zone_D1983UTMZone36N");
    NAD1983UTMZone37N.setName("NAD_1983_UTM_Zone_D1983UTMZone37N");
    NAD1983UTMZone38N.setName("NAD_1983_UTM_Zone_D1983UTMZone38N");
    NAD1983UTMZone39N.setName("NAD_1983_UTM_Zone_D1983UTMZone39N");
    NAD1983UTMZone40N.setName("NAD_1983_UTM_Zone_D1983UTMZone40N");
    NAD1983UTMZone41N.setName("NAD_1983_UTM_Zone_D1983UTMZone41N");
    NAD1983UTMZone42N.setName("NAD_1983_UTM_Zone_D1983UTMZone42N");
    NAD1983UTMZone43N.setName("NAD_1983_UTM_Zone_D1983UTMZone43N");
    NAD1983UTMZone44N.setName("NAD_1983_UTM_Zone_D1983UTMZone44N");
    NAD1983UTMZone45N.setName("NAD_1983_UTM_Zone_D1983UTMZone45N");
    NAD1983UTMZone46N.setName("NAD_1983_UTM_Zone_D1983UTMZone46N");
    NAD1983UTMZone47N.setName("NAD_1983_UTM_Zone_D1983UTMZone47N");
    NAD1983UTMZone48N.setName("NAD_1983_UTM_Zone_D1983UTMZone48N");
    NAD1983UTMZone49N.setName("NAD_1983_UTM_Zone_D1983UTMZone49N");
    NAD1983UTMZone50N.setName("NAD_1983_UTM_Zone_D1983UTMZone50N");
    NAD1983UTMZone51N.setName("NAD_1983_UTM_Zone_D1983UTMZone51N");
    NAD1983UTMZone52N.setName("NAD_1983_UTM_Zone_D1983UTMZone52N");
    NAD1983UTMZone53N.setName("NAD_1983_UTM_Zone_D1983UTMZone53N");
    NAD1983UTMZone54N.setName("NAD_1983_UTM_Zone_D1983UTMZone54N");
    NAD1983UTMZone55N.setName("NAD_1983_UTM_Zone_D1983UTMZone55N");
    NAD1983UTMZone56N.setName("NAD_1983_UTM_Zone_D1983UTMZone56N");
    NAD1983UTMZone57N.setName("NAD_1983_UTM_Zone_D1983UTMZone57N");
    NAD1983UTMZone58N.setName("NAD_1983_UTM_Zone_D1983UTMZone58N");
    NAD1983UTMZone59N.setName("NAD_1983_UTM_Zone_D1983UTMZone59N");
    NAD1983UTMZone60N.setName("NAD_1983_UTM_Zone_D1983UTMZone60N");
    NAD1983UTMZone1S.setName("NAD_1983_UTM_Zone_D1983UTMZone1S");
    NAD1983UTMZone2S.setName("NAD_1983_UTM_Zone_D1983UTMZone2S");
    NAD1983UTMZone3S.setName("NAD_1983_UTM_Zone_D1983UTMZone3S");
    NAD1983UTMZone4S.setName("NAD_1983_UTM_Zone_D1983UTMZone4S");
    NAD1983UTMZone5S.setName("NAD_1983_UTM_Zone_D1983UTMZone5S");
    NAD1983UTMZone6S.setName("NAD_1983_UTM_Zone_D1983UTMZone6S");
    NAD1983UTMZone7S.setName("NAD_1983_UTM_Zone_D1983UTMZone7S");
    NAD1983UTMZone8S.setName("NAD_1983_UTM_Zone_D1983UTMZone8S");
    NAD1983UTMZone9S.setName("NAD_1983_UTM_Zone_D1983UTMZone9S");
    NAD1983UTMZone10S.setName("NAD_1983_UTM_Zone_D1983UTMZone10S");
    NAD1983UTMZone11S.setName("NAD_1983_UTM_Zone_D1983UTMZone11S");
    NAD1983UTMZone12S.setName("NAD_1983_UTM_Zone_D1983UTMZone12S");
    NAD1983UTMZone13S.setName("NAD_1983_UTM_Zone_D1983UTMZone13S");
    NAD1983UTMZone14S.setName("NAD_1983_UTM_Zone_D1983UTMZone14S");
    NAD1983UTMZone15S.setName("NAD_1983_UTM_Zone_D1983UTMZone15S");
    NAD1983UTMZone16S.setName("NAD_1983_UTM_Zone_D1983UTMZone16S");
    NAD1983UTMZone17S.setName("NAD_1983_UTM_Zone_D1983UTMZone17S");
    NAD1983UTMZone18S.setName("NAD_1983_UTM_Zone_D1983UTMZone18S");
    NAD1983UTMZone19S.setName("NAD_1983_UTM_Zone_D1983UTMZone19S");
    NAD1983UTMZone20S.setName("NAD_1983_UTM_Zone_D1983UTMZone20S");
    NAD1983UTMZone21S.setName("NAD_1983_UTM_Zone_D1983UTMZone21S");
    NAD1983UTMZone22S.setName("NAD_1983_UTM_Zone_D1983UTMZone22S");
    NAD1983UTMZone23S.setName("NAD_1983_UTM_Zone_D1983UTMZone23S");
    NAD1983UTMZone24S.setName("NAD_1983_UTM_Zone_D1983UTMZone24S");
    NAD1983UTMZone25S.setName("NAD_1983_UTM_Zone_D1983UTMZone25S");
    NAD1983UTMZone26S.setName("NAD_1983_UTM_Zone_D1983UTMZone26S");
    NAD1983UTMZone27S.setName("NAD_1983_UTM_Zone_D1983UTMZone27S");
    NAD1983UTMZone28S.setName("NAD_1983_UTM_Zone_D1983UTMZone28S");
    NAD1983UTMZone29S.setName("NAD_1983_UTM_Zone_D1983UTMZone29S");
    NAD1983UTMZone30S.setName("NAD_1983_UTM_Zone_D1983UTMZone30S");
    NAD1983UTMZone31S.setName("NAD_1983_UTM_Zone_D1983UTMZone31S");
    NAD1983UTMZone32S.setName("NAD_1983_UTM_Zone_D1983UTMZone32S");
    NAD1983UTMZone33S.setName("NAD_1983_UTM_Zone_D1983UTMZone33S");
    NAD1983UTMZone34S.setName("NAD_1983_UTM_Zone_D1983UTMZone34S");
    NAD1983UTMZone35S.setName("NAD_1983_UTM_Zone_D1983UTMZone35S");
    NAD1983UTMZone36S.setName("NAD_1983_UTM_Zone_D1983UTMZone36S");
    NAD1983UTMZone37S.setName("NAD_1983_UTM_Zone_D1983UTMZone37S");
    NAD1983UTMZone38S.setName("NAD_1983_UTM_Zone_D1983UTMZone38S");
    NAD1983UTMZone39S.setName("NAD_1983_UTM_Zone_D1983UTMZone39S");
    NAD1983UTMZone40S.setName("NAD_1983_UTM_Zone_D1983UTMZone40S");
    NAD1983UTMZone41S.setName("NAD_1983_UTM_Zone_D1983UTMZone41S");
    NAD1983UTMZone42S.setName("NAD_1983_UTM_Zone_D1983UTMZone42S");
    NAD1983UTMZone43S.setName("NAD_1983_UTM_Zone_D1983UTMZone43S");
    NAD1983UTMZone44S.setName("NAD_1983_UTM_Zone_D1983UTMZone44S");
    NAD1983UTMZone45S.setName("NAD_1983_UTM_Zone_D1983UTMZone45S");
    NAD1983UTMZone46S.setName("NAD_1983_UTM_Zone_D1983UTMZone46S");
    NAD1983UTMZone47S.setName("NAD_1983_UTM_Zone_D1983UTMZone47S");
    NAD1983UTMZone48S.setName("NAD_1983_UTM_Zone_D1983UTMZone48S");
    NAD1983UTMZone49S.setName("NAD_1983_UTM_Zone_D1983UTMZone49S");
    NAD1983UTMZone50S.setName("NAD_1983_UTM_Zone_D1983UTMZone50S");
    NAD1983UTMZone51S.setName("NAD_1983_UTM_Zone_D1983UTMZone51S");
    NAD1983UTMZone52S.setName("NAD_1983_UTM_Zone_D1983UTMZone52S");
    NAD1983UTMZone53S.setName("NAD_1983_UTM_Zone_D1983UTMZone53S");
    NAD1983UTMZone54S.setName("NAD_1983_UTM_Zone_D1983UTMZone54S");
    NAD1983UTMZone55S.setName("NAD_1983_UTM_Zone_D1983UTMZone55S");
    NAD1983UTMZone56S.setName("NAD_1983_UTM_Zone_D1983UTMZone56S");
    NAD1983UTMZone57S.setName("NAD_1983_UTM_Zone_D1983UTMZone57S");
    NAD1983UTMZone58S.setName("NAD_1983_UTM_Zone_D1983UTMZone58S");
    NAD1983UTMZone59S.setName("NAD_1983_UTM_Zone_D1983UTMZone59S");
    NAD1983UTMZone60S.setName("NAD_1983_UTM_Zone_D1983UTMZone60S");

    NAD1983UTMZone1N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone2N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone3N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone4N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone5N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone6N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone7N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone8N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone9N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone10N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone11N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone12N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone13N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone14N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone15N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone16N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone17N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone18N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone19N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone20N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone21N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone22N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone23N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone24N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone25N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone26N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone27N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone28N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone29N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone30N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone31N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone32N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone33N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone34N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone35N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone36N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone37N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone38N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone39N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone40N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone41N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone42N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone43N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone44N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone45N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone46N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone47N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone48N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone49N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone50N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone51N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone52N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone53N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone54N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone55N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone56N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone57N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone58N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone59N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone60N.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone1S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone2S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone3S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone4S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone5S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone6S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone7S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone8S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone9S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone10S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone11S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone12S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone13S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone14S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone15S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone16S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone17S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone18S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone19S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone20S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone21S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone22S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone23S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone24S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone25S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone26S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone27S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone28S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone29S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone30S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone31S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone32S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone33S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone34S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone35S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone36S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone37S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone38S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone39S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone40S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone41S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone42S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone43S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone44S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone45S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone46S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone47S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone48S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone49S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone50S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone51S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone52S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone53S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone54S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone55S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone56S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone57S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone58S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone59S.getGeographicInfo().setName("GCS_North_American_1983");
    NAD1983UTMZone60S.getGeographicInfo().setName("GCS_North_American_1983");

    NAD1983UTMZone1N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone2N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone3N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone4N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone5N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone6N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone7N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone8N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone9N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone10N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone11N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone12N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone13N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone14N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone15N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone16N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone17N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone18N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone19N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone20N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone21N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone22N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone23N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone24N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone25N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone26N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone27N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone28N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone29N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone30N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone31N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone32N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone33N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone34N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone35N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone36N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone37N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone38N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone39N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone40N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone41N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone42N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone43N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone44N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone45N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone46N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone47N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone48N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone49N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone50N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone51N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone52N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone53N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone54N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone55N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone56N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone57N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone58N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone59N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone60N.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone1S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone2S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone3S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone4S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone5S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone6S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone7S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone8S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone9S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone10S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone11S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone12S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone13S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone14S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone15S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone16S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone17S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone18S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone19S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone20S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone21S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone22S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone23S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone24S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone25S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone26S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone27S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone28S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone29S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone30S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone31S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone32S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone33S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone34S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone35S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone36S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone37S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone38S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone39S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone40S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone41S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone42S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone43S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone44S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone45S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone46S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone47S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone48S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone49S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone50S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone51S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone52S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone53S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone54S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone55S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone56S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone57S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone58S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone59S.getGeographicInfo().getDatum().setName("D_North_American_1983");
    NAD1983UTMZone60S.getGeographicInfo().getDatum().setName("D_North_American_1983");
  }

  //</editor-fold>
  /**
   * @return the NAD1983UTMZone10N
   */
  public ProjectionInfo getNAD1983UTMZone10N() {
    return NAD1983UTMZone10N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone10S
   */
  public ProjectionInfo getNAD1983UTMZone10S() {
    return NAD1983UTMZone10S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone11N
   */
  public ProjectionInfo getNAD1983UTMZone11N() {
    return NAD1983UTMZone11N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone11S
   */
  public ProjectionInfo getNAD1983UTMZone11S() {
    return NAD1983UTMZone11S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone12N
   */
  public ProjectionInfo getNAD1983UTMZone12N() {
    return NAD1983UTMZone12N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone12S
   */
  public ProjectionInfo getNAD1983UTMZone12S() {
    return NAD1983UTMZone12S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone13N
   */
  public ProjectionInfo getNAD1983UTMZone13N() {
    return NAD1983UTMZone13N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone13S
   */
  public ProjectionInfo getNAD1983UTMZone13S() {
    return NAD1983UTMZone13S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone14N
   */
  public ProjectionInfo getNAD1983UTMZone14N() {
    return NAD1983UTMZone14N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone14S
   */
  public ProjectionInfo getNAD1983UTMZone14S() {
    return NAD1983UTMZone14S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone15N
   */
  public ProjectionInfo getNAD1983UTMZone15N() {
    return NAD1983UTMZone15N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone15S
   */
  public ProjectionInfo getNAD1983UTMZone15S() {
    return NAD1983UTMZone15S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone16N
   */
  public ProjectionInfo getNAD1983UTMZone16N() {
    return NAD1983UTMZone16N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone16S
   */
  public ProjectionInfo getNAD1983UTMZone16S() {
    return NAD1983UTMZone16S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone17N
   */
  public ProjectionInfo getNAD1983UTMZone17N() {
    return NAD1983UTMZone17N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone17S
   */
  public ProjectionInfo getNAD1983UTMZone17S() {
    return NAD1983UTMZone17S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone18N
   */
  public ProjectionInfo getNAD1983UTMZone18N() {
    return NAD1983UTMZone18N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone18S
   */
  public ProjectionInfo getNAD1983UTMZone18S() {
    return NAD1983UTMZone18S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone19N
   */
  public ProjectionInfo getNAD1983UTMZone19N() {
    return NAD1983UTMZone19N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone19S
   */
  public ProjectionInfo getNAD1983UTMZone19S() {
    return NAD1983UTMZone19S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone1N
   */
  public ProjectionInfo getNAD1983UTMZone1N() {
    return NAD1983UTMZone1N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone1S
   */
  public ProjectionInfo getNAD1983UTMZone1S() {
    return NAD1983UTMZone1S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone20N
   */
  public ProjectionInfo getNAD1983UTMZone20N() {
    return NAD1983UTMZone20N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone20S
   */
  public ProjectionInfo getNAD1983UTMZone20S() {
    return NAD1983UTMZone20S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone21N
   */
  public ProjectionInfo getNAD1983UTMZone21N() {
    return NAD1983UTMZone21N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone21S
   */
  public ProjectionInfo getNAD1983UTMZone21S() {
    return NAD1983UTMZone21S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone22N
   */
  public ProjectionInfo getNAD1983UTMZone22N() {
    return NAD1983UTMZone22N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone22S
   */
  public ProjectionInfo getNAD1983UTMZone22S() {
    return NAD1983UTMZone22S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone23N
   */
  public ProjectionInfo getNAD1983UTMZone23N() {
    return NAD1983UTMZone23N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone23S
   */
  public ProjectionInfo getNAD1983UTMZone23S() {
    return NAD1983UTMZone23S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone24N
   */
  public ProjectionInfo getNAD1983UTMZone24N() {
    return NAD1983UTMZone24N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone24S
   */
  public ProjectionInfo getNAD1983UTMZone24S() {
    return NAD1983UTMZone24S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone25N
   */
  public ProjectionInfo getNAD1983UTMZone25N() {
    return NAD1983UTMZone25N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone25S
   */
  public ProjectionInfo getNAD1983UTMZone25S() {
    return NAD1983UTMZone25S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone26N
   */
  public ProjectionInfo getNAD1983UTMZone26N() {
    return NAD1983UTMZone26N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone26S
   */
  public ProjectionInfo getNAD1983UTMZone26S() {
    return NAD1983UTMZone26S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone27N
   */
  public ProjectionInfo getNAD1983UTMZone27N() {
    return NAD1983UTMZone27N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone27S
   */
  public ProjectionInfo getNAD1983UTMZone27S() {
    return NAD1983UTMZone27S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone28N
   */
  public ProjectionInfo getNAD1983UTMZone28N() {
    return NAD1983UTMZone28N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone28S
   */
  public ProjectionInfo getNAD1983UTMZone28S() {
    return NAD1983UTMZone28S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone29N
   */
  public ProjectionInfo getNAD1983UTMZone29N() {
    return NAD1983UTMZone29N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone29S
   */
  public ProjectionInfo getNAD1983UTMZone29S() {
    return NAD1983UTMZone29S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone2N
   */
  public ProjectionInfo getNAD1983UTMZone2N() {
    return NAD1983UTMZone2N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone2S
   */
  public ProjectionInfo getNAD1983UTMZone2S() {
    return NAD1983UTMZone2S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone30N
   */
  public ProjectionInfo getNAD1983UTMZone30N() {
    return NAD1983UTMZone30N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone30S
   */
  public ProjectionInfo getNAD1983UTMZone30S() {
    return NAD1983UTMZone30S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone31N
   */
  public ProjectionInfo getNAD1983UTMZone31N() {
    return NAD1983UTMZone31N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone31S
   */
  public ProjectionInfo getNAD1983UTMZone31S() {
    return NAD1983UTMZone31S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone32N
   */
  public ProjectionInfo getNAD1983UTMZone32N() {
    return NAD1983UTMZone32N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone32S
   */
  public ProjectionInfo getNAD1983UTMZone32S() {
    return NAD1983UTMZone32S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone33N
   */
  public ProjectionInfo getNAD1983UTMZone33N() {
    return NAD1983UTMZone33N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone33S
   */
  public ProjectionInfo getNAD1983UTMZone33S() {
    return NAD1983UTMZone33S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone34N
   */
  public ProjectionInfo getNAD1983UTMZone34N() {
    return NAD1983UTMZone34N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone34S
   */
  public ProjectionInfo getNAD1983UTMZone34S() {
    return NAD1983UTMZone34S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone35N
   */
  public ProjectionInfo getNAD1983UTMZone35N() {
    return NAD1983UTMZone35N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone35S
   */
  public ProjectionInfo getNAD1983UTMZone35S() {
    return NAD1983UTMZone35S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone36N
   */
  public ProjectionInfo getNAD1983UTMZone36N() {
    return NAD1983UTMZone36N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone36S
   */
  public ProjectionInfo getNAD1983UTMZone36S() {
    return NAD1983UTMZone36S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone37N
   */
  public ProjectionInfo getNAD1983UTMZone37N() {
    return NAD1983UTMZone37N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone37S
   */
  public ProjectionInfo getNAD1983UTMZone37S() {
    return NAD1983UTMZone37S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone38N
   */
  public ProjectionInfo getNAD1983UTMZone38N() {
    return NAD1983UTMZone38N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone38S
   */
  public ProjectionInfo getNAD1983UTMZone38S() {
    return NAD1983UTMZone38S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone39N
   */
  public ProjectionInfo getNAD1983UTMZone39N() {
    return NAD1983UTMZone39N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone39S
   */
  public ProjectionInfo getNAD1983UTMZone39S() {
    return NAD1983UTMZone39S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone3N
   */
  public ProjectionInfo getNAD1983UTMZone3N() {
    return NAD1983UTMZone3N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone3S
   */
  public ProjectionInfo getNAD1983UTMZone3S() {
    return NAD1983UTMZone3S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone40N
   */
  public ProjectionInfo getNAD1983UTMZone40N() {
    return NAD1983UTMZone40N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone40S
   */
  public ProjectionInfo getNAD1983UTMZone40S() {
    return NAD1983UTMZone40S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone41N
   */
  public ProjectionInfo getNAD1983UTMZone41N() {
    return NAD1983UTMZone41N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone41S
   */
  public ProjectionInfo getNAD1983UTMZone41S() {
    return NAD1983UTMZone41S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone42N
   */
  public ProjectionInfo getNAD1983UTMZone42N() {
    return NAD1983UTMZone42N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone42S
   */
  public ProjectionInfo getNAD1983UTMZone42S() {
    return NAD1983UTMZone42S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone43N
   */
  public ProjectionInfo getNAD1983UTMZone43N() {
    return NAD1983UTMZone43N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone43S
   */
  public ProjectionInfo getNAD1983UTMZone43S() {
    return NAD1983UTMZone43S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone44N
   */
  public ProjectionInfo getNAD1983UTMZone44N() {
    return NAD1983UTMZone44N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone44S
   */
  public ProjectionInfo getNAD1983UTMZone44S() {
    return NAD1983UTMZone44S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone45N
   */
  public ProjectionInfo getNAD1983UTMZone45N() {
    return NAD1983UTMZone45N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone45S
   */
  public ProjectionInfo getNAD1983UTMZone45S() {
    return NAD1983UTMZone45S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone46N
   */
  public ProjectionInfo getNAD1983UTMZone46N() {
    return NAD1983UTMZone46N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone46S
   */
  public ProjectionInfo getNAD1983UTMZone46S() {
    return NAD1983UTMZone46S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone47N
   */
  public ProjectionInfo getNAD1983UTMZone47N() {
    return NAD1983UTMZone47N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone47S
   */
  public ProjectionInfo getNAD1983UTMZone47S() {
    return NAD1983UTMZone47S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone48N
   */
  public ProjectionInfo getNAD1983UTMZone48N() {
    return NAD1983UTMZone48N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone48S
   */
  public ProjectionInfo getNAD1983UTMZone48S() {
    return NAD1983UTMZone48S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone49N
   */
  public ProjectionInfo getNAD1983UTMZone49N() {
    return NAD1983UTMZone49N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone49S
   */
  public ProjectionInfo getNAD1983UTMZone49S() {
    return NAD1983UTMZone49S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone4N
   */
  public ProjectionInfo getNAD1983UTMZone4N() {
    return NAD1983UTMZone4N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone4S
   */
  public ProjectionInfo getNAD1983UTMZone4S() {
    return NAD1983UTMZone4S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone50N
   */
  public ProjectionInfo getNAD1983UTMZone50N() {
    return NAD1983UTMZone50N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone50S
   */
  public ProjectionInfo getNAD1983UTMZone50S() {
    return NAD1983UTMZone50S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone51N
   */
  public ProjectionInfo getNAD1983UTMZone51N() {
    return NAD1983UTMZone51N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone51S
   */
  public ProjectionInfo getNAD1983UTMZone51S() {
    return NAD1983UTMZone51S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone52N
   */
  public ProjectionInfo getNAD1983UTMZone52N() {
    return NAD1983UTMZone52N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone52S
   */
  public ProjectionInfo getNAD1983UTMZone52S() {
    return NAD1983UTMZone52S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone53N
   */
  public ProjectionInfo getNAD1983UTMZone53N() {
    return NAD1983UTMZone53N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone53S
   */
  public ProjectionInfo getNAD1983UTMZone53S() {
    return NAD1983UTMZone53S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone54N
   */
  public ProjectionInfo getNAD1983UTMZone54N() {
    return NAD1983UTMZone54N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone54S
   */
  public ProjectionInfo getNAD1983UTMZone54S() {
    return NAD1983UTMZone54S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone55N
   */
  public ProjectionInfo getNAD1983UTMZone55N() {
    return NAD1983UTMZone55N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone55S
   */
  public ProjectionInfo getNAD1983UTMZone55S() {
    return NAD1983UTMZone55S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone56N
   */
  public ProjectionInfo getNAD1983UTMZone56N() {
    return NAD1983UTMZone56N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone56S
   */
  public ProjectionInfo getNAD1983UTMZone56S() {
    return NAD1983UTMZone56S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone57N
   */
  public ProjectionInfo getNAD1983UTMZone57N() {
    return NAD1983UTMZone57N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone57S
   */
  public ProjectionInfo getNAD1983UTMZone57S() {
    return NAD1983UTMZone57S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone58N
   */
  public ProjectionInfo getNAD1983UTMZone58N() {
    return NAD1983UTMZone58N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone58S
   */
  public ProjectionInfo getNAD1983UTMZone58S() {
    return NAD1983UTMZone58S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone59N
   */
  public ProjectionInfo getNAD1983UTMZone59N() {
    return NAD1983UTMZone59N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone59S
   */
  public ProjectionInfo getNAD1983UTMZone59S() {
    return NAD1983UTMZone59S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone5N
   */
  public ProjectionInfo getNAD1983UTMZone5N() {
    return NAD1983UTMZone5N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone5S
   */
  public ProjectionInfo getNAD1983UTMZone5S() {
    return NAD1983UTMZone5S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone60N
   */
  public ProjectionInfo getNAD1983UTMZone60N() {
    return NAD1983UTMZone60N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone60S
   */
  public ProjectionInfo getNAD1983UTMZone60S() {
    return NAD1983UTMZone60S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone6N
   */
  public ProjectionInfo getNAD1983UTMZone6N() {
    return NAD1983UTMZone6N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone6S
   */
  public ProjectionInfo getNAD1983UTMZone6S() {
    return NAD1983UTMZone6S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone7N
   */
  public ProjectionInfo getNAD1983UTMZone7N() {
    return NAD1983UTMZone7N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone7S
   */
  public ProjectionInfo getNAD1983UTMZone7S() {
    return NAD1983UTMZone7S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone8N
   */
  public ProjectionInfo getNAD1983UTMZone8N() {
    return NAD1983UTMZone8N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone8S
   */
  public ProjectionInfo getNAD1983UTMZone8S() {
    return NAD1983UTMZone8S.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone9N
   */
  public ProjectionInfo getNAD1983UTMZone9N() {
    return NAD1983UTMZone9N.copy().copy();
  }

  /**
   * @return the NAD1983UTMZone9S
   */
  public ProjectionInfo getNAD1983UTMZone9S() {
    return NAD1983UTMZone9S.copy().copy();
  }
}
