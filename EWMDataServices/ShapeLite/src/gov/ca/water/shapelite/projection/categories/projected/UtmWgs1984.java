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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 5:11:31 PM
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
 * UtmWgs1984
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class UtmWgs1984 extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo WGS1984ComplexUTMZone20N;
  private final ProjectionInfo WGS1984ComplexUTMZone21N;
  private final ProjectionInfo WGS1984ComplexUTMZone22N;
  private final ProjectionInfo WGS1984ComplexUTMZone23N;
  private final ProjectionInfo WGS1984ComplexUTMZone24N;
  private final ProjectionInfo WGS1984ComplexUTMZone25N;
  private final ProjectionInfo WGS1984ComplexUTMZone26N;
  private final ProjectionInfo WGS1984ComplexUTMZone27N;
  private final ProjectionInfo WGS1984ComplexUTMZone28N;
  private final ProjectionInfo WGS1984ComplexUTMZone29N;
  private final ProjectionInfo WGS1984ComplexUTMZone30N;
  private final ProjectionInfo WGS1984UTMZone10N;
  private final ProjectionInfo WGS1984UTMZone10S;
  private final ProjectionInfo WGS1984UTMZone11N;
  private final ProjectionInfo WGS1984UTMZone11S;
  private final ProjectionInfo WGS1984UTMZone12N;
  private final ProjectionInfo WGS1984UTMZone12S;
  private final ProjectionInfo WGS1984UTMZone13N;
  private final ProjectionInfo WGS1984UTMZone13S;
  private final ProjectionInfo WGS1984UTMZone14N;
  private final ProjectionInfo WGS1984UTMZone14S;
  private final ProjectionInfo WGS1984UTMZone15N;
  private final ProjectionInfo WGS1984UTMZone15S;
  private final ProjectionInfo WGS1984UTMZone16N;
  private final ProjectionInfo WGS1984UTMZone16S;
  private final ProjectionInfo WGS1984UTMZone17N;
  private final ProjectionInfo WGS1984UTMZone17S;
  private final ProjectionInfo WGS1984UTMZone18N;
  private final ProjectionInfo WGS1984UTMZone18S;
  private final ProjectionInfo WGS1984UTMZone19N;
  private final ProjectionInfo WGS1984UTMZone19S;
  private final ProjectionInfo WGS1984UTMZone1N;
  private final ProjectionInfo WGS1984UTMZone1S;
  private final ProjectionInfo WGS1984UTMZone20N;
  private final ProjectionInfo WGS1984UTMZone20S;
  private final ProjectionInfo WGS1984UTMZone21N;
  private final ProjectionInfo WGS1984UTMZone21S;
  private final ProjectionInfo WGS1984UTMZone22N;
  private final ProjectionInfo WGS1984UTMZone22S;
  private final ProjectionInfo WGS1984UTMZone23N;
  private final ProjectionInfo WGS1984UTMZone23S;
  private final ProjectionInfo WGS1984UTMZone24N;
  private final ProjectionInfo WGS1984UTMZone24S;
  private final ProjectionInfo WGS1984UTMZone25N;
  private final ProjectionInfo WGS1984UTMZone25S;
  private final ProjectionInfo WGS1984UTMZone26N;
  private final ProjectionInfo WGS1984UTMZone26S;
  private final ProjectionInfo WGS1984UTMZone27N;
  private final ProjectionInfo WGS1984UTMZone27S;
  private final ProjectionInfo WGS1984UTMZone28N;
  private final ProjectionInfo WGS1984UTMZone28S;
  private final ProjectionInfo WGS1984UTMZone29N;
  private final ProjectionInfo WGS1984UTMZone29S;
  private final ProjectionInfo WGS1984UTMZone2N;
  private final ProjectionInfo WGS1984UTMZone2S;
  private final ProjectionInfo WGS1984UTMZone30N;
  private final ProjectionInfo WGS1984UTMZone30S;
  private final ProjectionInfo WGS1984UTMZone31N;
  private final ProjectionInfo WGS1984UTMZone31S;
  private final ProjectionInfo WGS1984UTMZone32N;
  private final ProjectionInfo WGS1984UTMZone32S;
  private final ProjectionInfo WGS1984UTMZone33N;
  private final ProjectionInfo WGS1984UTMZone33S;
  private final ProjectionInfo WGS1984UTMZone34N;
  private final ProjectionInfo WGS1984UTMZone34S;
  private final ProjectionInfo WGS1984UTMZone35N;
  private final ProjectionInfo WGS1984UTMZone35S;
  private final ProjectionInfo WGS1984UTMZone36N;
  private final ProjectionInfo WGS1984UTMZone36S;
  private final ProjectionInfo WGS1984UTMZone37N;
  private final ProjectionInfo WGS1984UTMZone37S;
  private final ProjectionInfo WGS1984UTMZone38N;
  private final ProjectionInfo WGS1984UTMZone38S;
  private final ProjectionInfo WGS1984UTMZone39N;
  private final ProjectionInfo WGS1984UTMZone39S;
  private final ProjectionInfo WGS1984UTMZone3N;
  private final ProjectionInfo WGS1984UTMZone3S;
  private final ProjectionInfo WGS1984UTMZone40N;
  private final ProjectionInfo WGS1984UTMZone40S;
  private final ProjectionInfo WGS1984UTMZone41N;
  private final ProjectionInfo WGS1984UTMZone41S;
  private final ProjectionInfo WGS1984UTMZone42N;
  private final ProjectionInfo WGS1984UTMZone42S;
  private final ProjectionInfo WGS1984UTMZone43N;
  private final ProjectionInfo WGS1984UTMZone43S;
  private final ProjectionInfo WGS1984UTMZone44N;
  private final ProjectionInfo WGS1984UTMZone44S;
  private final ProjectionInfo WGS1984UTMZone45N;
  private final ProjectionInfo WGS1984UTMZone45S;
  private final ProjectionInfo WGS1984UTMZone46N;
  private final ProjectionInfo WGS1984UTMZone46S;
  private final ProjectionInfo WGS1984UTMZone47N;
  private final ProjectionInfo WGS1984UTMZone47S;
  private final ProjectionInfo WGS1984UTMZone48N;
  private final ProjectionInfo WGS1984UTMZone48S;
  private final ProjectionInfo WGS1984UTMZone49N;
  private final ProjectionInfo WGS1984UTMZone49S;
  private final ProjectionInfo WGS1984UTMZone4N;
  private final ProjectionInfo WGS1984UTMZone4S;
  private final ProjectionInfo WGS1984UTMZone50N;
  private final ProjectionInfo WGS1984UTMZone50S;
  private final ProjectionInfo WGS1984UTMZone51N;
  private final ProjectionInfo WGS1984UTMZone51S;
  private final ProjectionInfo WGS1984UTMZone52N;
  private final ProjectionInfo WGS1984UTMZone52S;
  private final ProjectionInfo WGS1984UTMZone53N;
  private final ProjectionInfo WGS1984UTMZone53S;
  private final ProjectionInfo WGS1984UTMZone54N;
  private final ProjectionInfo WGS1984UTMZone54S;
  private final ProjectionInfo WGS1984UTMZone55N;
  private final ProjectionInfo WGS1984UTMZone55S;
  private final ProjectionInfo WGS1984UTMZone56N;
  private final ProjectionInfo WGS1984UTMZone56S;
  private final ProjectionInfo WGS1984UTMZone57N;
  private final ProjectionInfo WGS1984UTMZone57S;
  private final ProjectionInfo WGS1984UTMZone58N;
  private final ProjectionInfo WGS1984UTMZone58S;
  private final ProjectionInfo WGS1984UTMZone59N;
  private final ProjectionInfo WGS1984UTMZone59S;
  private final ProjectionInfo WGS1984UTMZone5N;
  private final ProjectionInfo WGS1984UTMZone5S;
  private final ProjectionInfo WGS1984UTMZone60N;
  private final ProjectionInfo WGS1984UTMZone60S;
  private final ProjectionInfo WGS1984UTMZone6N;
  private final ProjectionInfo WGS1984UTMZone6S;
  private final ProjectionInfo WGS1984UTMZone7N;
  private final ProjectionInfo WGS1984UTMZone7S;
  private final ProjectionInfo WGS1984UTMZone8N;
  private final ProjectionInfo WGS1984UTMZone8S;
  private final ProjectionInfo WGS1984UTMZone9N;
  private final ProjectionInfo WGS1984UTMZone9S;

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Creates a new instance of UtmWgs1984
   */
  public UtmWgs1984() {
    WGS1984ComplexUTMZone20N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984ComplexUTMZone21N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984ComplexUTMZone22N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984ComplexUTMZone23N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984ComplexUTMZone24N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984ComplexUTMZone25N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984ComplexUTMZone26N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984ComplexUTMZone27N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984ComplexUTMZone28N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984ComplexUTMZone29N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984ComplexUTMZone30N = ProjectionInfo.fromProj4String("+ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone10N = ProjectionInfo.fromProj4String("+proj=utm +zone=10 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone10S = ProjectionInfo.fromProj4String("+proj=utm +zone=10 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone11N = ProjectionInfo.fromProj4String("+proj=utm +zone=11 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone11S = ProjectionInfo.fromProj4String("+proj=utm +zone=11 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone12N = ProjectionInfo.fromProj4String("+proj=utm +zone=12 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone12S = ProjectionInfo.fromProj4String("+proj=utm +zone=12 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone13N = ProjectionInfo.fromProj4String("+proj=utm +zone=13 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone13S = ProjectionInfo.fromProj4String("+proj=utm +zone=13 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone14N = ProjectionInfo.fromProj4String("+proj=utm +zone=14 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone14S = ProjectionInfo.fromProj4String("+proj=utm +zone=14 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone15N = ProjectionInfo.fromProj4String("+proj=utm +zone=15 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone15S = ProjectionInfo.fromProj4String("+proj=utm +zone=15 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone16N = ProjectionInfo.fromProj4String("+proj=utm +zone=16 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone16S = ProjectionInfo.fromProj4String("+proj=utm +zone=16 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone17N = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone17S = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone18S = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone19S = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone1N = ProjectionInfo.fromProj4String("+proj=utm +zone=1 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone1S = ProjectionInfo.fromProj4String("+proj=utm +zone=1 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone20S = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone21S = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone22N = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone22S = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone23N = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone23S = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone24N = ProjectionInfo.fromProj4String("+proj=utm +zone=24 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone24S = ProjectionInfo.fromProj4String("+proj=utm +zone=24 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone25N = ProjectionInfo.fromProj4String("+proj=utm +zone=25 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone25S = ProjectionInfo.fromProj4String("+proj=utm +zone=25 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone26N = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone26S = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone27N = ProjectionInfo.fromProj4String("+proj=utm +zone=27 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone27S = ProjectionInfo.fromProj4String("+proj=utm +zone=27 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone28S = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone29S = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone2N = ProjectionInfo.fromProj4String("+proj=utm +zone=2 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone2S = ProjectionInfo.fromProj4String("+proj=utm +zone=2 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone30N = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone30S = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone31N = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone31S = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone32S = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone33S = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone34N = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone34S = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone35N = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone35S = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone36N = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone36S = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone37N = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone37S = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone38S = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone39S = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone3N = ProjectionInfo.fromProj4String("+proj=utm +zone=3 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone3S = ProjectionInfo.fromProj4String("+proj=utm +zone=3 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone40N = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone40S = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone41N = ProjectionInfo.fromProj4String("+proj=utm +zone=41 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone41S = ProjectionInfo.fromProj4String("+proj=utm +zone=41 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone42N = ProjectionInfo.fromProj4String("+proj=utm +zone=42 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone42S = ProjectionInfo.fromProj4String("+proj=utm +zone=42 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone43N = ProjectionInfo.fromProj4String("+proj=utm +zone=43 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone43S = ProjectionInfo.fromProj4String("+proj=utm +zone=43 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone44N = ProjectionInfo.fromProj4String("+proj=utm +zone=44 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone44S = ProjectionInfo.fromProj4String("+proj=utm +zone=44 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone45N = ProjectionInfo.fromProj4String("+proj=utm +zone=45 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone45S = ProjectionInfo.fromProj4String("+proj=utm +zone=45 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone46N = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone46S = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone47N = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone47S = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone48N = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone48S = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone49N = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone49S = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone4N = ProjectionInfo.fromProj4String("+proj=utm +zone=4 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone4S = ProjectionInfo.fromProj4String("+proj=utm +zone=4 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone50N = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone50S = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone51N = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone51S = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone52N = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone52S = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone53N = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone53S = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone54N = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone54S = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone55N = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone55S = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone56N = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone56S = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone57N = ProjectionInfo.fromProj4String("+proj=utm +zone=57 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone57S = ProjectionInfo.fromProj4String("+proj=utm +zone=57 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone58N = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone59N = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone59S = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone5N = ProjectionInfo.fromProj4String("+proj=utm +zone=5 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone5S = ProjectionInfo.fromProj4String("+proj=utm +zone=5 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone60N = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone60S = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone6N = ProjectionInfo.fromProj4String("+proj=utm +zone=6 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone6S = ProjectionInfo.fromProj4String("+proj=utm +zone=6 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone7N = ProjectionInfo.fromProj4String("+proj=utm +zone=7 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone7S = ProjectionInfo.fromProj4String("+proj=utm +zone=7 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone8N = ProjectionInfo.fromProj4String("+proj=utm +zone=8 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone8S = ProjectionInfo.fromProj4String("+proj=utm +zone=8 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone9N = ProjectionInfo.fromProj4String("+proj=utm +zone=9 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
    WGS1984UTMZone9S = ProjectionInfo.fromProj4String("+proj=utm +zone=9 +south +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);

    WGS1984ComplexUTMZone20N.setName("WGS_1984_Complex_UTM_Zone_20N");
    WGS1984ComplexUTMZone21N.setName("WGS_1984_Complex_UTM_Zone_21N");
    WGS1984ComplexUTMZone22N.setName("WGS_1984_Complex_UTM_Zone_22N");
    WGS1984ComplexUTMZone23N.setName("WGS_1984_Complex_UTM_Zone_23N");
    WGS1984ComplexUTMZone24N.setName("WGS_1984_Complex_UTM_Zone_24N");
    WGS1984ComplexUTMZone25N.setName("WGS_1984_Complex_UTM_Zone_25N");
    WGS1984ComplexUTMZone26N.setName("WGS_1984_Complex_UTM_Zone_26N");
    WGS1984ComplexUTMZone27N.setName("WGS_1984_Complex_UTM_Zone_27N");
    WGS1984ComplexUTMZone28N.setName("WGS_1984_Complex_UTM_Zone_28N");
    WGS1984ComplexUTMZone29N.setName("WGS_1984_Complex_UTM_Zone_29N");
    WGS1984ComplexUTMZone30N.setName("WGS_1984_Complex_UTM_Zone_30N");
    WGS1984UTMZone10N.setName("WGS_1984_UTM_Zone_10N");
    WGS1984UTMZone10S.setName("WGS_1984_UTM_Zone_10S");
    WGS1984UTMZone11N.setName("WGS_1984_UTM_Zone_11N");
    WGS1984UTMZone11S.setName("WGS_1984_UTM_Zone_11S");
    WGS1984UTMZone12N.setName("WGS_1984_UTM_Zone_12N");
    WGS1984UTMZone12S.setName("WGS_1984_UTM_Zone_12S");
    WGS1984UTMZone13N.setName("WGS_1984_UTM_Zone_13N");
    WGS1984UTMZone13S.setName("WGS_1984_UTM_Zone_13S");
    WGS1984UTMZone14N.setName("WGS_1984_UTM_Zone_14N");
    WGS1984UTMZone14S.setName("WGS_1984_UTM_Zone_14S");
    WGS1984UTMZone15N.setName("WGS_1984_UTM_Zone_15N");
    WGS1984UTMZone15S.setName("WGS_1984_UTM_Zone_15S");
    WGS1984UTMZone16N.setName("WGS_1984_UTM_Zone_16N");
    WGS1984UTMZone16S.setName("WGS_1984_UTM_Zone_16S");
    WGS1984UTMZone17N.setName("WGS_1984_UTM_Zone_17N");
    WGS1984UTMZone17S.setName("WGS_1984_UTM_Zone_17S");
    WGS1984UTMZone18N.setName("WGS_1984_UTM_Zone_18N");
    WGS1984UTMZone18S.setName("WGS_1984_UTM_Zone_18S");
    WGS1984UTMZone19N.setName("WGS_1984_UTM_Zone_19N");
    WGS1984UTMZone19S.setName("WGS_1984_UTM_Zone_19S");
    WGS1984UTMZone1N.setName("WGS_1984_UTM_Zone_1N");
    WGS1984UTMZone1S.setName("WGS_1984_UTM_Zone_1S");
    WGS1984UTMZone20N.setName("WGS_1984_UTM_Zone_20N");
    WGS1984UTMZone20S.setName("WGS_1984_UTM_Zone_20S");
    WGS1984UTMZone21N.setName("WGS_1984_UTM_Zone_21N");
    WGS1984UTMZone21S.setName("WGS_1984_UTM_Zone_21S");
    WGS1984UTMZone22N.setName("WGS_1984_UTM_Zone_22N");
    WGS1984UTMZone22S.setName("WGS_1984_UTM_Zone_22S");
    WGS1984UTMZone23N.setName("WGS_1984_UTM_Zone_23N");
    WGS1984UTMZone23S.setName("WGS_1984_UTM_Zone_23S");
    WGS1984UTMZone24N.setName("WGS_1984_UTM_Zone_24N");
    WGS1984UTMZone24S.setName("WGS_1984_UTM_Zone_24S");
    WGS1984UTMZone25N.setName("WGS_1984_UTM_Zone_25N");
    WGS1984UTMZone25S.setName("WGS_1984_UTM_Zone_25S");
    WGS1984UTMZone26N.setName("WGS_1984_UTM_Zone_26N");
    WGS1984UTMZone26S.setName("WGS_1984_UTM_Zone_26S");
    WGS1984UTMZone27N.setName("WGS_1984_UTM_Zone_27N");
    WGS1984UTMZone27S.setName("WGS_1984_UTM_Zone_27S");
    WGS1984UTMZone28N.setName("WGS_1984_UTM_Zone_28N");
    WGS1984UTMZone28S.setName("WGS_1984_UTM_Zone_28S");
    WGS1984UTMZone29N.setName("WGS_1984_UTM_Zone_29N");
    WGS1984UTMZone29S.setName("WGS_1984_UTM_Zone_29S");
    WGS1984UTMZone2N.setName("WGS_1984_UTM_Zone_2N");
    WGS1984UTMZone2S.setName("WGS_1984_UTM_Zone_2S");
    WGS1984UTMZone30N.setName("WGS_1984_UTM_Zone_30N");
    WGS1984UTMZone30S.setName("WGS_1984_UTM_Zone_30S");
    WGS1984UTMZone31N.setName("WGS_1984_UTM_Zone_31N");
    WGS1984UTMZone31S.setName("WGS_1984_UTM_Zone_31S");
    WGS1984UTMZone32N.setName("WGS_1984_UTM_Zone_32N");
    WGS1984UTMZone32S.setName("WGS_1984_UTM_Zone_32S");
    WGS1984UTMZone33N.setName("WGS_1984_UTM_Zone_33N");
    WGS1984UTMZone33S.setName("WGS_1984_UTM_Zone_33S");
    WGS1984UTMZone34N.setName("WGS_1984_UTM_Zone_34N");
    WGS1984UTMZone34S.setName("WGS_1984_UTM_Zone_34S");
    WGS1984UTMZone35N.setName("WGS_1984_UTM_Zone_35N");
    WGS1984UTMZone35S.setName("WGS_1984_UTM_Zone_35S");
    WGS1984UTMZone36N.setName("WGS_1984_UTM_Zone_36N");
    WGS1984UTMZone36S.setName("WGS_1984_UTM_Zone_36S");
    WGS1984UTMZone37N.setName("WGS_1984_UTM_Zone_37N");
    WGS1984UTMZone37S.setName("WGS_1984_UTM_Zone_37S");
    WGS1984UTMZone38N.setName("WGS_1984_UTM_Zone_38N");
    WGS1984UTMZone38S.setName("WGS_1984_UTM_Zone_38S");
    WGS1984UTMZone39N.setName("WGS_1984_UTM_Zone_39N");
    WGS1984UTMZone39S.setName("WGS_1984_UTM_Zone_39S");
    WGS1984UTMZone3N.setName("WGS_1984_UTM_Zone_3N");
    WGS1984UTMZone3S.setName("WGS_1984_UTM_Zone_3S");
    WGS1984UTMZone40N.setName("WGS_1984_UTM_Zone_40N");
    WGS1984UTMZone40S.setName("WGS_1984_UTM_Zone_40S");
    WGS1984UTMZone41N.setName("WGS_1984_UTM_Zone_41N");
    WGS1984UTMZone41S.setName("WGS_1984_UTM_Zone_41S");
    WGS1984UTMZone42N.setName("WGS_1984_UTM_Zone_42N");
    WGS1984UTMZone42S.setName("WGS_1984_UTM_Zone_42S");
    WGS1984UTMZone43N.setName("WGS_1984_UTM_Zone_43N");
    WGS1984UTMZone43S.setName("WGS_1984_UTM_Zone_43S");
    WGS1984UTMZone44N.setName("WGS_1984_UTM_Zone_44N");
    WGS1984UTMZone44S.setName("WGS_1984_UTM_Zone_44S");
    WGS1984UTMZone45N.setName("WGS_1984_UTM_Zone_45N");
    WGS1984UTMZone45S.setName("WGS_1984_UTM_Zone_45S");
    WGS1984UTMZone46N.setName("WGS_1984_UTM_Zone_46N");
    WGS1984UTMZone46S.setName("WGS_1984_UTM_Zone_46S");
    WGS1984UTMZone47N.setName("WGS_1984_UTM_Zone_47N");
    WGS1984UTMZone47S.setName("WGS_1984_UTM_Zone_47S");
    WGS1984UTMZone48N.setName("WGS_1984_UTM_Zone_48N");
    WGS1984UTMZone48S.setName("WGS_1984_UTM_Zone_48S");
    WGS1984UTMZone49N.setName("WGS_1984_UTM_Zone_49N");
    WGS1984UTMZone49S.setName("WGS_1984_UTM_Zone_49S");
    WGS1984UTMZone4N.setName("WGS_1984_UTM_Zone_4N");
    WGS1984UTMZone4S.setName("WGS_1984_UTM_Zone_4S");
    WGS1984UTMZone50N.setName("WGS_1984_UTM_Zone_50N");
    WGS1984UTMZone50S.setName("WGS_1984_UTM_Zone_50S");
    WGS1984UTMZone51N.setName("WGS_1984_UTM_Zone_51N");
    WGS1984UTMZone51S.setName("WGS_1984_UTM_Zone_51S");
    WGS1984UTMZone52N.setName("WGS_1984_UTM_Zone_52N");
    WGS1984UTMZone52S.setName("WGS_1984_UTM_Zone_52S");
    WGS1984UTMZone53N.setName("WGS_1984_UTM_Zone_53N");
    WGS1984UTMZone53S.setName("WGS_1984_UTM_Zone_53S");
    WGS1984UTMZone54N.setName("WGS_1984_UTM_Zone_54N");
    WGS1984UTMZone54S.setName("WGS_1984_UTM_Zone_54S");
    WGS1984UTMZone55N.setName("WGS_1984_UTM_Zone_55N");
    WGS1984UTMZone55S.setName("WGS_1984_UTM_Zone_55S");
    WGS1984UTMZone56N.setName("WGS_1984_UTM_Zone_56N");
    WGS1984UTMZone56S.setName("WGS_1984_UTM_Zone_56S");
    WGS1984UTMZone57N.setName("WGS_1984_UTM_Zone_57N");
    WGS1984UTMZone57S.setName("WGS_1984_UTM_Zone_57S");
    WGS1984UTMZone58N.setName("WGS_1984_UTM_Zone_58N");
    WGS1984UTMZone58S.setName("WGS_1984_UTM_Zone_58S");
    WGS1984UTMZone59N.setName("WGS_1984_UTM_Zone_59N");
    WGS1984UTMZone59S.setName("WGS_1984_UTM_Zone_59S");
    WGS1984UTMZone5N.setName("WGS_1984_UTM_Zone_5N");
    WGS1984UTMZone5S.setName("WGS_1984_UTM_Zone_5S");
    WGS1984UTMZone60N.setName("WGS_1984_UTM_Zone_60N");
    WGS1984UTMZone60S.setName("WGS_1984_UTM_Zone_60S");
    WGS1984UTMZone6N.setName("WGS_1984_UTM_Zone_6N");
    WGS1984UTMZone6S.setName("WGS_1984_UTM_Zone_6S");
    WGS1984UTMZone7N.setName("WGS_1984_UTM_Zone_7N");
    WGS1984UTMZone7S.setName("WGS_1984_UTM_Zone_7S");
    WGS1984UTMZone8N.setName("WGS_1984_UTM_Zone_8N");
    WGS1984UTMZone8S.setName("WGS_1984_UTM_Zone_8S");
    WGS1984UTMZone9N.setName("WGS_1984_UTM_Zone_9N");
    WGS1984UTMZone9S.setName("WGS_1984_UTM_Zone_9S");

    WGS1984ComplexUTMZone20N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984ComplexUTMZone21N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984ComplexUTMZone22N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984ComplexUTMZone23N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984ComplexUTMZone24N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984ComplexUTMZone25N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984ComplexUTMZone26N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984ComplexUTMZone27N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984ComplexUTMZone28N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984ComplexUTMZone29N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984ComplexUTMZone30N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone10N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone10S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone11N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone11S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone12N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone12S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone13N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone13S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone14N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone14S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone15N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone15S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone16N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone16S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone17N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone17S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone18N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone18S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone19N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone19S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone1N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone1S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone20N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone20S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone21N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone21S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone22N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone22S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone23N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone23S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone24N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone24S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone25N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone25S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone26N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone26S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone27N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone27S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone28N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone28S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone29N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone29S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone2N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone2S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone30N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone30S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone31N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone31S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone32N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone32S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone33N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone33S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone34N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone34S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone35N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone35S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone36N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone36S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone37N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone37S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone38N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone38S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone39N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone39S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone3N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone3S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone40N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone40S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone41N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone41S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone42N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone42S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone43N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone43S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone44N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone44S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone45N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone45S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone46N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone46S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone47N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone47S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone48N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone48S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone49N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone49S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone4N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone4S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone50N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone50S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone51N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone51S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone52N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone52S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone53N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone53S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone54N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone54S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone55N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone55S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone56N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone56S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone57N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone57S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone58N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone58S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone59N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone59S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone5N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone5S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone60N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone60S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone6N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone6S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone7N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone7S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone8N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone8S.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone9N.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984UTMZone9S.getGeographicInfo().setName("GCS_WGS_1984");

    WGS1984ComplexUTMZone20N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984ComplexUTMZone21N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984ComplexUTMZone22N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984ComplexUTMZone23N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984ComplexUTMZone24N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984ComplexUTMZone25N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984ComplexUTMZone26N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984ComplexUTMZone27N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984ComplexUTMZone28N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984ComplexUTMZone29N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984ComplexUTMZone30N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone10N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone10S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone11N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone11S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone12N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone12S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone13N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone13S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone14N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone14S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone15N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone15S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone16N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone16S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone17N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone17S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone18N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone18S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone19N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone19S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone1N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone1S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone20N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone20S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone21N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone21S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone22N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone22S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone23N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone23S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone24N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone24S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone25N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone25S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone26N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone26S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone27N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone27S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone28N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone28S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone29N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone29S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone2N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone2S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone30N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone30S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone31N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone31S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone32N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone32S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone33N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone33S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone34N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone34S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone35N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone35S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone36N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone36S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone37N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone37S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone38N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone38S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone39N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone39S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone3N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone3S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone40N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone40S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone41N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone41S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone42N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone42S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone43N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone43S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone44N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone44S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone45N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone45S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone46N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone46S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone47N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone47S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone48N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone48S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone49N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone49S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone4N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone4S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone50N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone50S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone51N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone51S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone52N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone52S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone53N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone53S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone54N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone54S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone55N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone55S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone56N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone56S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone57N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone57S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone58N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone58S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone59N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone59S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone5N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone5S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone60N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone60S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone6N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone6S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone7N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone7S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone8N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone8S.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone9N.getGeographicInfo().getDatum().setName("D_WGS_1984");
    WGS1984UTMZone9S.getGeographicInfo().getDatum().setName("D_WGS_1984");
  }

  //</editor-fold>

  /**
   * @return the WGS1984ComplexUTMZone20N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone20N() {
    return WGS1984ComplexUTMZone20N.copy();
  }

  /**
   * @return the WGS1984ComplexUTMZone21N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone21N() {
    return WGS1984ComplexUTMZone21N.copy();
  }

  /**
   * @return the WGS1984ComplexUTMZone22N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone22N() {
    return WGS1984ComplexUTMZone22N.copy();
  }

  /**
   * @return the WGS1984ComplexUTMZone23N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone23N() {
    return WGS1984ComplexUTMZone23N.copy();
  }

  /**
   * @return the WGS1984ComplexUTMZone24N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone24N() {
    return WGS1984ComplexUTMZone24N.copy();
  }

  /**
   * @return the WGS1984ComplexUTMZone25N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone25N() {
    return WGS1984ComplexUTMZone25N.copy();
  }

  /**
   * @return the WGS1984ComplexUTMZone26N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone26N() {
    return WGS1984ComplexUTMZone26N.copy();
  }

  /**
   * @return the WGS1984ComplexUTMZone27N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone27N() {
    return WGS1984ComplexUTMZone27N.copy();
  }

  /**
   * @return the WGS1984ComplexUTMZone28N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone28N() {
    return WGS1984ComplexUTMZone28N.copy();
  }

  /**
   * @return the WGS1984ComplexUTMZone29N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone29N() {
    return WGS1984ComplexUTMZone29N.copy();
  }

  /**
   * @return the WGS1984ComplexUTMZone30N
   */
  public ProjectionInfo getWGS1984ComplexUTMZone30N() {
    return WGS1984ComplexUTMZone30N.copy();
  }

  /**
   * @return the WGS1984UTMZone10N
   */
  public ProjectionInfo getWGS1984UTMZone10N() {
    return WGS1984UTMZone10N.copy();
  }

  /**
   * @return the WGS1984UTMZone10S
   */
  public ProjectionInfo getWGS1984UTMZone10S() {
    return WGS1984UTMZone10S.copy();
  }

  /**
   * @return the WGS1984UTMZone11N
   */
  public ProjectionInfo getWGS1984UTMZone11N() {
    return WGS1984UTMZone11N.copy();
  }

  /**
   * @return the WGS1984UTMZone11S
   */
  public ProjectionInfo getWGS1984UTMZone11S() {
    return WGS1984UTMZone11S.copy();
  }

  /**
   * @return the WGS1984UTMZone12N
   */
  public ProjectionInfo getWGS1984UTMZone12N() {
    return WGS1984UTMZone12N.copy();
  }

  /**
   * @return the WGS1984UTMZone12S
   */
  public ProjectionInfo getWGS1984UTMZone12S() {
    return WGS1984UTMZone12S.copy();
  }

  /**
   * @return the WGS1984UTMZone13N
   */
  public ProjectionInfo getWGS1984UTMZone13N() {
    return WGS1984UTMZone13N.copy();
  }

  /**
   * @return the WGS1984UTMZone13S
   */
  public ProjectionInfo getWGS1984UTMZone13S() {
    return WGS1984UTMZone13S.copy();
  }

  /**
   * @return the WGS1984UTMZone14N
   */
  public ProjectionInfo getWGS1984UTMZone14N() {
    return WGS1984UTMZone14N.copy();
  }

  /**
   * @return the WGS1984UTMZone14S
   */
  public ProjectionInfo getWGS1984UTMZone14S() {
    return WGS1984UTMZone14S.copy();
  }

  /**
   * @return the WGS1984UTMZone15N
   */
  public ProjectionInfo getWGS1984UTMZone15N() {
    return WGS1984UTMZone15N.copy();
  }

  /**
   * @return the WGS1984UTMZone15S
   */
  public ProjectionInfo getWGS1984UTMZone15S() {
    return WGS1984UTMZone15S.copy();
  }

  /**
   * @return the WGS1984UTMZone16N
   */
  public ProjectionInfo getWGS1984UTMZone16N() {
    return WGS1984UTMZone16N.copy();
  }

  /**
   * @return the WGS1984UTMZone16S
   */
  public ProjectionInfo getWGS1984UTMZone16S() {
    return WGS1984UTMZone16S.copy();
  }

  /**
   * @return the WGS1984UTMZone17N
   */
  public ProjectionInfo getWGS1984UTMZone17N() {
    return WGS1984UTMZone17N.copy();
  }

  /**
   * @return the WGS1984UTMZone17S
   */
  public ProjectionInfo getWGS1984UTMZone17S() {
    return WGS1984UTMZone17S.copy();
  }

  /**
   * @return the WGS1984UTMZone18N
   */
  public ProjectionInfo getWGS1984UTMZone18N() {
    return WGS1984UTMZone18N.copy();
  }

  /**
   * @return the WGS1984UTMZone18S
   */
  public ProjectionInfo getWGS1984UTMZone18S() {
    return WGS1984UTMZone18S.copy();
  }

  /**
   * @return the WGS1984UTMZone19N
   */
  public ProjectionInfo getWGS1984UTMZone19N() {
    return WGS1984UTMZone19N.copy();
  }

  /**
   * @return the WGS1984UTMZone19S
   */
  public ProjectionInfo getWGS1984UTMZone19S() {
    return WGS1984UTMZone19S.copy();
  }

  /**
   * @return the WGS1984UTMZone1N
   */
  public ProjectionInfo getWGS1984UTMZone1N() {
    return WGS1984UTMZone1N.copy();
  }

  /**
   * @return the WGS1984UTMZone1S
   */
  public ProjectionInfo getWGS1984UTMZone1S() {
    return WGS1984UTMZone1S.copy();
  }

  /**
   * @return the WGS1984UTMZone20N
   */
  public ProjectionInfo getWGS1984UTMZone20N() {
    return WGS1984UTMZone20N.copy();
  }

  /**
   * @return the WGS1984UTMZone20S
   */
  public ProjectionInfo getWGS1984UTMZone20S() {
    return WGS1984UTMZone20S.copy();
  }

  /**
   * @return the WGS1984UTMZone21N
   */
  public ProjectionInfo getWGS1984UTMZone21N() {
    return WGS1984UTMZone21N.copy();
  }

  /**
   * @return the WGS1984UTMZone21S
   */
  public ProjectionInfo getWGS1984UTMZone21S() {
    return WGS1984UTMZone21S.copy();
  }

  /**
   * @return the WGS1984UTMZone22N
   */
  public ProjectionInfo getWGS1984UTMZone22N() {
    return WGS1984UTMZone22N.copy();
  }

  /**
   * @return the WGS1984UTMZone22S
   */
  public ProjectionInfo getWGS1984UTMZone22S() {
    return WGS1984UTMZone22S.copy();
  }

  /**
   * @return the WGS1984UTMZone23N
   */
  public ProjectionInfo getWGS1984UTMZone23N() {
    return WGS1984UTMZone23N.copy();
  }

  /**
   * @return the WGS1984UTMZone23S
   */
  public ProjectionInfo getWGS1984UTMZone23S() {
    return WGS1984UTMZone23S.copy();
  }

  /**
   * @return the WGS1984UTMZone24N
   */
  public ProjectionInfo getWGS1984UTMZone24N() {
    return WGS1984UTMZone24N.copy();
  }

  /**
   * @return the WGS1984UTMZone24S
   */
  public ProjectionInfo getWGS1984UTMZone24S() {
    return WGS1984UTMZone24S.copy();
  }

  /**
   * @return the WGS1984UTMZone25N
   */
  public ProjectionInfo getWGS1984UTMZone25N() {
    return WGS1984UTMZone25N.copy();
  }

  /**
   * @return the WGS1984UTMZone25S
   */
  public ProjectionInfo getWGS1984UTMZone25S() {
    return WGS1984UTMZone25S.copy();
  }

  /**
   * @return the WGS1984UTMZone26N
   */
  public ProjectionInfo getWGS1984UTMZone26N() {
    return WGS1984UTMZone26N.copy();
  }

  /**
   * @return the WGS1984UTMZone26S
   */
  public ProjectionInfo getWGS1984UTMZone26S() {
    return WGS1984UTMZone26S.copy();
  }

  /**
   * @return the WGS1984UTMZone27N
   */
  public ProjectionInfo getWGS1984UTMZone27N() {
    return WGS1984UTMZone27N.copy();
  }

  /**
   * @return the WGS1984UTMZone27S
   */
  public ProjectionInfo getWGS1984UTMZone27S() {
    return WGS1984UTMZone27S.copy();
  }

  /**
   * @return the WGS1984UTMZone28N
   */
  public ProjectionInfo getWGS1984UTMZone28N() {
    return WGS1984UTMZone28N.copy();
  }

  /**
   * @return the WGS1984UTMZone28S
   */
  public ProjectionInfo getWGS1984UTMZone28S() {
    return WGS1984UTMZone28S.copy();
  }

  /**
   * @return the WGS1984UTMZone29N
   */
  public ProjectionInfo getWGS1984UTMZone29N() {
    return WGS1984UTMZone29N.copy();
  }

  /**
   * @return the WGS1984UTMZone29S
   */
  public ProjectionInfo getWGS1984UTMZone29S() {
    return WGS1984UTMZone29S.copy();
  }

  /**
   * @return the WGS1984UTMZone2N
   */
  public ProjectionInfo getWGS1984UTMZone2N() {
    return WGS1984UTMZone2N.copy();
  }

  /**
   * @return the WGS1984UTMZone2S
   */
  public ProjectionInfo getWGS1984UTMZone2S() {
    return WGS1984UTMZone2S.copy();
  }

  /**
   * @return the WGS1984UTMZone30N
   */
  public ProjectionInfo getWGS1984UTMZone30N() {
    return WGS1984UTMZone30N.copy();
  }

  /**
   * @return the WGS1984UTMZone30S
   */
  public ProjectionInfo getWGS1984UTMZone30S() {
    return WGS1984UTMZone30S.copy();
  }

  /**
   * @return the WGS1984UTMZone31N
   */
  public ProjectionInfo getWGS1984UTMZone31N() {
    return WGS1984UTMZone31N.copy();
  }

  /**
   * @return the WGS1984UTMZone31S
   */
  public ProjectionInfo getWGS1984UTMZone31S() {
    return WGS1984UTMZone31S.copy();
  }

  /**
   * @return the WGS1984UTMZone32N
   */
  public ProjectionInfo getWGS1984UTMZone32N() {
    return WGS1984UTMZone32N.copy();
  }

  /**
   * @return the WGS1984UTMZone32S
   */
  public ProjectionInfo getWGS1984UTMZone32S() {
    return WGS1984UTMZone32S.copy();
  }

  /**
   * @return the WGS1984UTMZone33N
   */
  public ProjectionInfo getWGS1984UTMZone33N() {
    return WGS1984UTMZone33N.copy();
  }

  /**
   * @return the WGS1984UTMZone33S
   */
  public ProjectionInfo getWGS1984UTMZone33S() {
    return WGS1984UTMZone33S.copy();
  }

  /**
   * @return the WGS1984UTMZone34N
   */
  public ProjectionInfo getWGS1984UTMZone34N() {
    return WGS1984UTMZone34N.copy();
  }

  /**
   * @return the WGS1984UTMZone34S
   */
  public ProjectionInfo getWGS1984UTMZone34S() {
    return WGS1984UTMZone34S.copy();
  }

  /**
   * @return the WGS1984UTMZone35N
   */
  public ProjectionInfo getWGS1984UTMZone35N() {
    return WGS1984UTMZone35N.copy();
  }

  /**
   * @return the WGS1984UTMZone35S
   */
  public ProjectionInfo getWGS1984UTMZone35S() {
    return WGS1984UTMZone35S.copy();
  }

  /**
   * @return the WGS1984UTMZone36N
   */
  public ProjectionInfo getWGS1984UTMZone36N() {
    return WGS1984UTMZone36N.copy();
  }

  /**
   * @return the WGS1984UTMZone36S
   */
  public ProjectionInfo getWGS1984UTMZone36S() {
    return WGS1984UTMZone36S.copy();
  }

  /**
   * @return the WGS1984UTMZone37N
   */
  public ProjectionInfo getWGS1984UTMZone37N() {
    return WGS1984UTMZone37N.copy();
  }

  /**
   * @return the WGS1984UTMZone37S
   */
  public ProjectionInfo getWGS1984UTMZone37S() {
    return WGS1984UTMZone37S.copy();
  }

  /**
   * @return the WGS1984UTMZone38N
   */
  public ProjectionInfo getWGS1984UTMZone38N() {
    return WGS1984UTMZone38N.copy();
  }

  /**
   * @return the WGS1984UTMZone38S
   */
  public ProjectionInfo getWGS1984UTMZone38S() {
    return WGS1984UTMZone38S.copy();
  }

  /**
   * @return the WGS1984UTMZone39N
   */
  public ProjectionInfo getWGS1984UTMZone39N() {
    return WGS1984UTMZone39N.copy();
  }

  /**
   * @return the WGS1984UTMZone39S
   */
  public ProjectionInfo getWGS1984UTMZone39S() {
    return WGS1984UTMZone39S.copy();
  }

  /**
   * @return the WGS1984UTMZone3N
   */
  public ProjectionInfo getWGS1984UTMZone3N() {
    return WGS1984UTMZone3N.copy();
  }

  /**
   * @return the WGS1984UTMZone3S
   */
  public ProjectionInfo getWGS1984UTMZone3S() {
    return WGS1984UTMZone3S.copy();
  }

  /**
   * @return the WGS1984UTMZone40N
   */
  public ProjectionInfo getWGS1984UTMZone40N() {
    return WGS1984UTMZone40N.copy();
  }

  /**
   * @return the WGS1984UTMZone40S
   */
  public ProjectionInfo getWGS1984UTMZone40S() {
    return WGS1984UTMZone40S.copy();
  }

  /**
   * @return the WGS1984UTMZone41N
   */
  public ProjectionInfo getWGS1984UTMZone41N() {
    return WGS1984UTMZone41N.copy();
  }

  /**
   * @return the WGS1984UTMZone41S
   */
  public ProjectionInfo getWGS1984UTMZone41S() {
    return WGS1984UTMZone41S.copy();
  }

  /**
   * @return the WGS1984UTMZone42N
   */
  public ProjectionInfo getWGS1984UTMZone42N() {
    return WGS1984UTMZone42N.copy();
  }

  /**
   * @return the WGS1984UTMZone42S
   */
  public ProjectionInfo getWGS1984UTMZone42S() {
    return WGS1984UTMZone42S.copy();
  }

  /**
   * @return the WGS1984UTMZone43N
   */
  public ProjectionInfo getWGS1984UTMZone43N() {
    return WGS1984UTMZone43N.copy();
  }

  /**
   * @return the WGS1984UTMZone43S
   */
  public ProjectionInfo getWGS1984UTMZone43S() {
    return WGS1984UTMZone43S.copy();
  }

  /**
   * @return the WGS1984UTMZone44N
   */
  public ProjectionInfo getWGS1984UTMZone44N() {
    return WGS1984UTMZone44N.copy();
  }

  /**
   * @return the WGS1984UTMZone44S
   */
  public ProjectionInfo getWGS1984UTMZone44S() {
    return WGS1984UTMZone44S.copy();
  }

  /**
   * @return the WGS1984UTMZone45N
   */
  public ProjectionInfo getWGS1984UTMZone45N() {
    return WGS1984UTMZone45N.copy();
  }

  /**
   * @return the WGS1984UTMZone45S
   */
  public ProjectionInfo getWGS1984UTMZone45S() {
    return WGS1984UTMZone45S.copy();
  }

  /**
   * @return the WGS1984UTMZone46N
   */
  public ProjectionInfo getWGS1984UTMZone46N() {
    return WGS1984UTMZone46N.copy();
  }

  /**
   * @return the WGS1984UTMZone46S
   */
  public ProjectionInfo getWGS1984UTMZone46S() {
    return WGS1984UTMZone46S.copy();
  }

  /**
   * @return the WGS1984UTMZone47N
   */
  public ProjectionInfo getWGS1984UTMZone47N() {
    return WGS1984UTMZone47N.copy();
  }

  /**
   * @return the WGS1984UTMZone47S
   */
  public ProjectionInfo getWGS1984UTMZone47S() {
    return WGS1984UTMZone47S.copy();
  }

  /**
   * @return the WGS1984UTMZone48N
   */
  public ProjectionInfo getWGS1984UTMZone48N() {
    return WGS1984UTMZone48N.copy();
  }

  /**
   * @return the WGS1984UTMZone48S
   */
  public ProjectionInfo getWGS1984UTMZone48S() {
    return WGS1984UTMZone48S.copy();
  }

  /**
   * @return the WGS1984UTMZone49N
   */
  public ProjectionInfo getWGS1984UTMZone49N() {
    return WGS1984UTMZone49N.copy();
  }

  /**
   * @return the WGS1984UTMZone49S
   */
  public ProjectionInfo getWGS1984UTMZone49S() {
    return WGS1984UTMZone49S.copy();
  }

  /**
   * @return the WGS1984UTMZone4N
   */
  public ProjectionInfo getWGS1984UTMZone4N() {
    return WGS1984UTMZone4N.copy();
  }

  /**
   * @return the WGS1984UTMZone4S
   */
  public ProjectionInfo getWGS1984UTMZone4S() {
    return WGS1984UTMZone4S.copy();
  }

  /**
   * @return the WGS1984UTMZone50N
   */
  public ProjectionInfo getWGS1984UTMZone50N() {
    return WGS1984UTMZone50N.copy();
  }

  /**
   * @return the WGS1984UTMZone50S
   */
  public ProjectionInfo getWGS1984UTMZone50S() {
    return WGS1984UTMZone50S.copy();
  }

  /**
   * @return the WGS1984UTMZone51N
   */
  public ProjectionInfo getWGS1984UTMZone51N() {
    return WGS1984UTMZone51N.copy();
  }

  /**
   * @return the WGS1984UTMZone51S
   */
  public ProjectionInfo getWGS1984UTMZone51S() {
    return WGS1984UTMZone51S.copy();
  }

  /**
   * @return the WGS1984UTMZone52N
   */
  public ProjectionInfo getWGS1984UTMZone52N() {
    return WGS1984UTMZone52N.copy();
  }

  /**
   * @return the WGS1984UTMZone52S
   */
  public ProjectionInfo getWGS1984UTMZone52S() {
    return WGS1984UTMZone52S.copy();
  }

  /**
   * @return the WGS1984UTMZone53N
   */
  public ProjectionInfo getWGS1984UTMZone53N() {
    return WGS1984UTMZone53N.copy();
  }

  /**
   * @return the WGS1984UTMZone53S
   */
  public ProjectionInfo getWGS1984UTMZone53S() {
    return WGS1984UTMZone53S.copy();
  }

  /**
   * @return the WGS1984UTMZone54N
   */
  public ProjectionInfo getWGS1984UTMZone54N() {
    return WGS1984UTMZone54N.copy();
  }

  /**
   * @return the WGS1984UTMZone54S
   */
  public ProjectionInfo getWGS1984UTMZone54S() {
    return WGS1984UTMZone54S.copy();
  }

  /**
   * @return the WGS1984UTMZone55N
   */
  public ProjectionInfo getWGS1984UTMZone55N() {
    return WGS1984UTMZone55N.copy();
  }

  /**
   * @return the WGS1984UTMZone55S
   */
  public ProjectionInfo getWGS1984UTMZone55S() {
    return WGS1984UTMZone55S.copy();
  }

  /**
   * @return the WGS1984UTMZone56N
   */
  public ProjectionInfo getWGS1984UTMZone56N() {
    return WGS1984UTMZone56N.copy();
  }

  /**
   * @return the WGS1984UTMZone56S
   */
  public ProjectionInfo getWGS1984UTMZone56S() {
    return WGS1984UTMZone56S.copy();
  }

  /**
   * @return the WGS1984UTMZone57N
   */
  public ProjectionInfo getWGS1984UTMZone57N() {
    return WGS1984UTMZone57N.copy();
  }

  /**
   * @return the WGS1984UTMZone57S
   */
  public ProjectionInfo getWGS1984UTMZone57S() {
    return WGS1984UTMZone57S.copy();
  }

  /**
   * @return the WGS1984UTMZone58N
   */
  public ProjectionInfo getWGS1984UTMZone58N() {
    return WGS1984UTMZone58N.copy();
  }

  /**
   * @return the WGS1984UTMZone58S
   */
  public ProjectionInfo getWGS1984UTMZone58S() {
    return WGS1984UTMZone58S.copy();
  }

  /**
   * @return the WGS1984UTMZone59N
   */
  public ProjectionInfo getWGS1984UTMZone59N() {
    return WGS1984UTMZone59N.copy();
  }

  /**
   * @return the WGS1984UTMZone59S
   */
  public ProjectionInfo getWGS1984UTMZone59S() {
    return WGS1984UTMZone59S.copy();
  }

  /**
   * @return the WGS1984UTMZone5N
   */
  public ProjectionInfo getWGS1984UTMZone5N() {
    return WGS1984UTMZone5N.copy();
  }

  /**
   * @return the WGS1984UTMZone5S
   */
  public ProjectionInfo getWGS1984UTMZone5S() {
    return WGS1984UTMZone5S.copy();
  }

  /**
   * @return the WGS1984UTMZone60N
   */
  public ProjectionInfo getWGS1984UTMZone60N() {
    return WGS1984UTMZone60N.copy();
  }

  /**
   * @return the WGS1984UTMZone60S
   */
  public ProjectionInfo getWGS1984UTMZone60S() {
    return WGS1984UTMZone60S.copy();
  }

  /**
   * @return the WGS1984UTMZone6N
   */
  public ProjectionInfo getWGS1984UTMZone6N() {
    return WGS1984UTMZone6N.copy();
  }

  /**
   * @return the WGS1984UTMZone6S
   */
  public ProjectionInfo getWGS1984UTMZone6S() {
    return WGS1984UTMZone6S.copy();
  }

  /**
   * @return the WGS1984UTMZone7N
   */
  public ProjectionInfo getWGS1984UTMZone7N() {
    return WGS1984UTMZone7N.copy();
  }

  /**
   * @return the WGS1984UTMZone7S
   */
  public ProjectionInfo getWGS1984UTMZone7S() {
    return WGS1984UTMZone7S.copy();
  }

  /**
   * @return the WGS1984UTMZone8N
   */
  public ProjectionInfo getWGS1984UTMZone8N() {
    return WGS1984UTMZone8N.copy();
  }

  /**
   * @return the WGS1984UTMZone8S
   */
  public ProjectionInfo getWGS1984UTMZone8S() {
    return WGS1984UTMZone8S.copy();
  }

  /**
   * @return the WGS1984UTMZone9N
   */
  public ProjectionInfo getWGS1984UTMZone9N() {
    return WGS1984UTMZone9N.copy();
  }

  /**
   * @return the WGS1984UTMZone9S
   */
  public ProjectionInfo getWGS1984UTMZone9S() {
    return WGS1984UTMZone9S.copy();
  }
}
