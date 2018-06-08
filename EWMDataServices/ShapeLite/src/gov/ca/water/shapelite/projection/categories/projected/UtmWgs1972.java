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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 5:10:15 PM
//
// Contributor(s): (Open source contributors should list themselves and their modifications here).
//        Name         |    Date    |        Comment
// --------------------|------------|------------------------------------------------------------
// Ted Dunsford        |   5/3/2010 |  Updated project to DotSpatial.Projection and license to LGPL
// ********************************************************************************************************


package gov.ca.water.shapelite.projection.categories.projected;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;
    /// <summary>
    /// UtmWgs1972
    /// </summary>
    public class UtmWgs1972 extends CoordinateSystemCategory
    {
        //<editor-fold defaultstate="collapsed" desc="Fields">

        private final ProjectionInfo WGS1972UTMZone10N;
        private final ProjectionInfo WGS1972UTMZone10S;
        private final ProjectionInfo WGS1972UTMZone11N;
        private final ProjectionInfo WGS1972UTMZone11S;
        private final ProjectionInfo WGS1972UTMZone12N;
        private final ProjectionInfo WGS1972UTMZone12S;
        private final ProjectionInfo WGS1972UTMZone13N;
        private final ProjectionInfo WGS1972UTMZone13S;
        private final ProjectionInfo WGS1972UTMZone14N;
        private final ProjectionInfo WGS1972UTMZone14S;
        private final ProjectionInfo WGS1972UTMZone15N;
        private final ProjectionInfo WGS1972UTMZone15S;
        private final ProjectionInfo WGS1972UTMZone16N;
        private final ProjectionInfo WGS1972UTMZone16S;
        private final ProjectionInfo WGS1972UTMZone17N;
        private final ProjectionInfo WGS1972UTMZone17S;
        private final ProjectionInfo WGS1972UTMZone18N;
        private final ProjectionInfo WGS1972UTMZone18S;
        private final ProjectionInfo WGS1972UTMZone19N;
        private final ProjectionInfo WGS1972UTMZone19S;
        private final ProjectionInfo WGS1972UTMZone1N;
        private final ProjectionInfo WGS1972UTMZone1S;
        private final ProjectionInfo WGS1972UTMZone20N;
        private final ProjectionInfo WGS1972UTMZone20S;
        private final ProjectionInfo WGS1972UTMZone21N;
        private final ProjectionInfo WGS1972UTMZone21S;
        private final ProjectionInfo WGS1972UTMZone22N;
        private final ProjectionInfo WGS1972UTMZone22S;
        private final ProjectionInfo WGS1972UTMZone23N;
        private final ProjectionInfo WGS1972UTMZone23S;
        private final ProjectionInfo WGS1972UTMZone24N;
        private final ProjectionInfo WGS1972UTMZone24S;
        private final ProjectionInfo WGS1972UTMZone25N;
        private final ProjectionInfo WGS1972UTMZone25S;
        private final ProjectionInfo WGS1972UTMZone26N;
        private final ProjectionInfo WGS1972UTMZone26S;
        private final ProjectionInfo WGS1972UTMZone27N;
        private final ProjectionInfo WGS1972UTMZone27S;
        private final ProjectionInfo WGS1972UTMZone28N;
        private final ProjectionInfo WGS1972UTMZone28S;
        private final ProjectionInfo WGS1972UTMZone29N;
        private final ProjectionInfo WGS1972UTMZone29S;
        private final ProjectionInfo WGS1972UTMZone2N;
        private final ProjectionInfo WGS1972UTMZone2S;
        private final ProjectionInfo WGS1972UTMZone30N;
        private final ProjectionInfo WGS1972UTMZone30S;
        private final ProjectionInfo WGS1972UTMZone31N;
        private final ProjectionInfo WGS1972UTMZone31S;
        private final ProjectionInfo WGS1972UTMZone32N;
        private final ProjectionInfo WGS1972UTMZone32S;
        private final ProjectionInfo WGS1972UTMZone33N;
        private final ProjectionInfo WGS1972UTMZone33S;
        private final ProjectionInfo WGS1972UTMZone34N;
        private final ProjectionInfo WGS1972UTMZone34S;
        private final ProjectionInfo WGS1972UTMZone35N;
        private final ProjectionInfo WGS1972UTMZone35S;
        private final ProjectionInfo WGS1972UTMZone36N;
        private final ProjectionInfo WGS1972UTMZone36S;
        private final ProjectionInfo WGS1972UTMZone37N;
        private final ProjectionInfo WGS1972UTMZone37S;
        private final ProjectionInfo WGS1972UTMZone38N;
        private final ProjectionInfo WGS1972UTMZone38S;
        private final ProjectionInfo WGS1972UTMZone39N;
        private final ProjectionInfo WGS1972UTMZone39S;
        private final ProjectionInfo WGS1972UTMZone3N;
        private final ProjectionInfo WGS1972UTMZone3S;
        private final ProjectionInfo WGS1972UTMZone40N;
        private final ProjectionInfo WGS1972UTMZone40S;
        private final ProjectionInfo WGS1972UTMZone41N;
        private final ProjectionInfo WGS1972UTMZone41S;
        private final ProjectionInfo WGS1972UTMZone42N;
        private final ProjectionInfo WGS1972UTMZone42S;
        private final ProjectionInfo WGS1972UTMZone43N;
        private final ProjectionInfo WGS1972UTMZone43S;
        private final ProjectionInfo WGS1972UTMZone44N;
        private final ProjectionInfo WGS1972UTMZone44S;
        private final ProjectionInfo WGS1972UTMZone45N;
        private final ProjectionInfo WGS1972UTMZone45S;
        private final ProjectionInfo WGS1972UTMZone46N;
        private final ProjectionInfo WGS1972UTMZone46S;
        private final ProjectionInfo WGS1972UTMZone47N;
        private final ProjectionInfo WGS1972UTMZone47S;
        private final ProjectionInfo WGS1972UTMZone48N;
        private final ProjectionInfo WGS1972UTMZone48S;
        private final ProjectionInfo WGS1972UTMZone49N;
        private final ProjectionInfo WGS1972UTMZone49S;
        private final ProjectionInfo WGS1972UTMZone4N;
        private final ProjectionInfo WGS1972UTMZone4S;
        private final ProjectionInfo WGS1972UTMZone50N;
        private final ProjectionInfo WGS1972UTMZone50S;
        private final ProjectionInfo WGS1972UTMZone51N;
        private final ProjectionInfo WGS1972UTMZone51S;
        private final ProjectionInfo WGS1972UTMZone52N;
        private final ProjectionInfo WGS1972UTMZone52S;
        private final ProjectionInfo WGS1972UTMZone53N;
        private final ProjectionInfo WGS1972UTMZone53S;
        private final ProjectionInfo WGS1972UTMZone54N;
        private final ProjectionInfo WGS1972UTMZone54S;
        private final ProjectionInfo WGS1972UTMZone55N;
        private final ProjectionInfo WGS1972UTMZone55S;
        private final ProjectionInfo WGS1972UTMZone56N;
        private final ProjectionInfo WGS1972UTMZone56S;
        private final ProjectionInfo WGS1972UTMZone57N;
        private final ProjectionInfo WGS1972UTMZone57S;
        private final ProjectionInfo WGS1972UTMZone58N;
        private final ProjectionInfo WGS1972UTMZone58S;
        private final ProjectionInfo WGS1972UTMZone59N;
        private final ProjectionInfo WGS1972UTMZone59S;
        private final ProjectionInfo WGS1972UTMZone5N;
        private final ProjectionInfo WGS1972UTMZone5S;
        private final ProjectionInfo WGS1972UTMZone60N;
        private final ProjectionInfo WGS1972UTMZone60S;
        private final ProjectionInfo WGS1972UTMZone6N;
        private final ProjectionInfo WGS1972UTMZone6S;
        private final ProjectionInfo WGS1972UTMZone7N;
        private final ProjectionInfo WGS1972UTMZone7S;
        private final ProjectionInfo WGS1972UTMZone8N;
        private final ProjectionInfo WGS1972UTMZone8S;
        private final ProjectionInfo WGS1972UTMZone9N;
        private final ProjectionInfo WGS1972UTMZone9S;

        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Constructor">

        /// <summary>
        /// Creates a new instance of UtmWgs1972
        /// </summary>
        public UtmWgs1972()
        {
            WGS1972UTMZone10N = ProjectionInfo.fromProj4String("+proj=utm +zone=10 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone10S = ProjectionInfo.fromProj4String("+proj=utm +zone=10 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone11N = ProjectionInfo.fromProj4String("+proj=utm +zone=11 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone11S = ProjectionInfo.fromProj4String("+proj=utm +zone=11 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone12N = ProjectionInfo.fromProj4String("+proj=utm +zone=12 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone12S = ProjectionInfo.fromProj4String("+proj=utm +zone=12 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone13N = ProjectionInfo.fromProj4String("+proj=utm +zone=13 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone13S = ProjectionInfo.fromProj4String("+proj=utm +zone=13 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone14N = ProjectionInfo.fromProj4String("+proj=utm +zone=14 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone14S = ProjectionInfo.fromProj4String("+proj=utm +zone=14 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone15N = ProjectionInfo.fromProj4String("+proj=utm +zone=15 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone15S = ProjectionInfo.fromProj4String("+proj=utm +zone=15 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone16N = ProjectionInfo.fromProj4String("+proj=utm +zone=16 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone16S = ProjectionInfo.fromProj4String("+proj=utm +zone=16 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone17N = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone17S = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone18S = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone19S = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone1N = ProjectionInfo.fromProj4String("+proj=utm +zone=1 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone1S = ProjectionInfo.fromProj4String("+proj=utm +zone=1 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone20S = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone21S = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone22N = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone22S = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone23N = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone23S = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone24N = ProjectionInfo.fromProj4String("+proj=utm +zone=24 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone24S = ProjectionInfo.fromProj4String("+proj=utm +zone=24 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone25N = ProjectionInfo.fromProj4String("+proj=utm +zone=25 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone25S = ProjectionInfo.fromProj4String("+proj=utm +zone=25 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone26N = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone26S = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone27N = ProjectionInfo.fromProj4String("+proj=utm +zone=27 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone27S = ProjectionInfo.fromProj4String("+proj=utm +zone=27 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone28S = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone29S = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone2N = ProjectionInfo.fromProj4String("+proj=utm +zone=2 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone2S = ProjectionInfo.fromProj4String("+proj=utm +zone=2 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone30N = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone30S = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone31N = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone31S = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone32S = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone33S = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone34N = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone34S = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone35N = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone35S = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone36N = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone36S = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone37N = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone37S = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone38S = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone39S = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone3N = ProjectionInfo.fromProj4String("+proj=utm +zone=3 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone3S = ProjectionInfo.fromProj4String("+proj=utm +zone=3 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone40N = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone40S = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone41N = ProjectionInfo.fromProj4String("+proj=utm +zone=41 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone41S = ProjectionInfo.fromProj4String("+proj=utm +zone=41 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone42N = ProjectionInfo.fromProj4String("+proj=utm +zone=42 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone42S = ProjectionInfo.fromProj4String("+proj=utm +zone=42 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone43N = ProjectionInfo.fromProj4String("+proj=utm +zone=43 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone43S = ProjectionInfo.fromProj4String("+proj=utm +zone=43 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone44N = ProjectionInfo.fromProj4String("+proj=utm +zone=44 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone44S = ProjectionInfo.fromProj4String("+proj=utm +zone=44 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone45N = ProjectionInfo.fromProj4String("+proj=utm +zone=45 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone45S = ProjectionInfo.fromProj4String("+proj=utm +zone=45 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone46N = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone46S = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone47N = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone47S = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone48N = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone48S = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone49N = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone49S = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone4N = ProjectionInfo.fromProj4String("+proj=utm +zone=4 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone4S = ProjectionInfo.fromProj4String("+proj=utm +zone=4 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone50N = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone50S = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone51N = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone51S = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone52N = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone52S = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone53N = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone53S = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone54N = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone54S = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone55N = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone55S = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone56N = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone56S = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone57N = ProjectionInfo.fromProj4String("+proj=utm +zone=57 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone57S = ProjectionInfo.fromProj4String("+proj=utm +zone=57 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone58N = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone59N = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone59S = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone5N = ProjectionInfo.fromProj4String("+proj=utm +zone=5 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone5S = ProjectionInfo.fromProj4String("+proj=utm +zone=5 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone60N = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone60S = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone6N = ProjectionInfo.fromProj4String("+proj=utm +zone=6 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone6S = ProjectionInfo.fromProj4String("+proj=utm +zone=6 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone7N = ProjectionInfo.fromProj4String("+proj=utm +zone=7 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone7S = ProjectionInfo.fromProj4String("+proj=utm +zone=7 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone8N = ProjectionInfo.fromProj4String("+proj=utm +zone=8 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone8S = ProjectionInfo.fromProj4String("+proj=utm +zone=8 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone9N = ProjectionInfo.fromProj4String("+proj=utm +zone=9 +ellps=WGS72 +units=m +no_defs ").orElse(null);
            WGS1972UTMZone9S = ProjectionInfo.fromProj4String("+proj=utm +zone=9 +south +ellps=WGS72 +units=m +no_defs ").orElse(null);

            WGS1972UTMZone10N.setName("WGS_1972_UTM_Zone_10N");
            WGS1972UTMZone10S.setName("WGS_1972_UTM_Zone_10S");
            WGS1972UTMZone11N.setName("WGS_1972_UTM_Zone_11N");
            WGS1972UTMZone11S.setName("WGS_1972_UTM_Zone_11S");
            WGS1972UTMZone12N.setName("WGS_1972_UTM_Zone_12N");
            WGS1972UTMZone12S.setName("WGS_1972_UTM_Zone_12S");
            WGS1972UTMZone13N.setName("WGS_1972_UTM_Zone_13N");
            WGS1972UTMZone13S.setName("WGS_1972_UTM_Zone_13S");
            WGS1972UTMZone14N.setName("WGS_1972_UTM_Zone_14N");
            WGS1972UTMZone14S.setName("WGS_1972_UTM_Zone_14S");
            WGS1972UTMZone15N.setName("WGS_1972_UTM_Zone_15N");
            WGS1972UTMZone15S.setName("WGS_1972_UTM_Zone_15S");
            WGS1972UTMZone16N.setName("WGS_1972_UTM_Zone_16N");
            WGS1972UTMZone16S.setName("WGS_1972_UTM_Zone_16S");
            WGS1972UTMZone17N.setName("WGS_1972_UTM_Zone_17N");
            WGS1972UTMZone17S.setName("WGS_1972_UTM_Zone_17S");
            WGS1972UTMZone18N.setName("WGS_1972_UTM_Zone_18N");
            WGS1972UTMZone18S.setName("WGS_1972_UTM_Zone_18S");
            WGS1972UTMZone19N.setName("WGS_1972_UTM_Zone_19N");
            WGS1972UTMZone19S.setName("WGS_1972_UTM_Zone_19S");
            WGS1972UTMZone1N.setName("WGS_1972_UTM_Zone_1N");
            WGS1972UTMZone1S.setName("WGS_1972_UTM_Zone_1S");
            WGS1972UTMZone20N.setName("WGS_1972_UTM_Zone_20N");
            WGS1972UTMZone20S.setName("WGS_1972_UTM_Zone_20S");
            WGS1972UTMZone21N.setName("WGS_1972_UTM_Zone_21N");
            WGS1972UTMZone21S.setName("WGS_1972_UTM_Zone_21S");
            WGS1972UTMZone22N.setName("WGS_1972_UTM_Zone_22N");
            WGS1972UTMZone22S.setName("WGS_1972_UTM_Zone_22S");
            WGS1972UTMZone23N.setName("WGS_1972_UTM_Zone_23N");
            WGS1972UTMZone23S.setName("WGS_1972_UTM_Zone_23S");
            WGS1972UTMZone24N.setName("WGS_1972_UTM_Zone_24N");
            WGS1972UTMZone24S.setName("WGS_1972_UTM_Zone_24S");
            WGS1972UTMZone25N.setName("WGS_1972_UTM_Zone_25N");
            WGS1972UTMZone25S.setName("WGS_1972_UTM_Zone_25S");
            WGS1972UTMZone26N.setName("WGS_1972_UTM_Zone_26N");
            WGS1972UTMZone26S.setName("WGS_1972_UTM_Zone_26S");
            WGS1972UTMZone27N.setName("WGS_1972_UTM_Zone_27N");
            WGS1972UTMZone27S.setName("WGS_1972_UTM_Zone_27S");
            WGS1972UTMZone28N.setName("WGS_1972_UTM_Zone_28N");
            WGS1972UTMZone28S.setName("WGS_1972_UTM_Zone_28S");
            WGS1972UTMZone29N.setName("WGS_1972_UTM_Zone_29N");
            WGS1972UTMZone29S.setName("WGS_1972_UTM_Zone_29S");
            WGS1972UTMZone2N.setName("WGS_1972_UTM_Zone_2N");
            WGS1972UTMZone2S.setName("WGS_1972_UTM_Zone_2S");
            WGS1972UTMZone30N.setName("WGS_1972_UTM_Zone_30N");
            WGS1972UTMZone30S.setName("WGS_1972_UTM_Zone_30S");
            WGS1972UTMZone31N.setName("WGS_1972_UTM_Zone_31N");
            WGS1972UTMZone31S.setName("WGS_1972_UTM_Zone_31S");
            WGS1972UTMZone32N.setName("WGS_1972_UTM_Zone_32N");
            WGS1972UTMZone32S.setName("WGS_1972_UTM_Zone_32S");
            WGS1972UTMZone33N.setName("WGS_1972_UTM_Zone_33N");
            WGS1972UTMZone33S.setName("WGS_1972_UTM_Zone_33S");
            WGS1972UTMZone34N.setName("WGS_1972_UTM_Zone_34N");
            WGS1972UTMZone34S.setName("WGS_1972_UTM_Zone_34S");
            WGS1972UTMZone35N.setName("WGS_1972_UTM_Zone_35N");
            WGS1972UTMZone35S.setName("WGS_1972_UTM_Zone_35S");
            WGS1972UTMZone36N.setName("WGS_1972_UTM_Zone_36N");
            WGS1972UTMZone36S.setName("WGS_1972_UTM_Zone_36S");
            WGS1972UTMZone37N.setName("WGS_1972_UTM_Zone_37N");
            WGS1972UTMZone37S.setName("WGS_1972_UTM_Zone_37S");
            WGS1972UTMZone38N.setName("WGS_1972_UTM_Zone_38N");
            WGS1972UTMZone38S.setName("WGS_1972_UTM_Zone_38S");
            WGS1972UTMZone39N.setName("WGS_1972_UTM_Zone_39N");
            WGS1972UTMZone39S.setName("WGS_1972_UTM_Zone_39S");
            WGS1972UTMZone3N.setName("WGS_1972_UTM_Zone_3N");
            WGS1972UTMZone3S.setName("WGS_1972_UTM_Zone_3S");
            WGS1972UTMZone40N.setName("WGS_1972_UTM_Zone_40N");
            WGS1972UTMZone40S.setName("WGS_1972_UTM_Zone_40S");
            WGS1972UTMZone41N.setName("WGS_1972_UTM_Zone_41N");
            WGS1972UTMZone41S.setName("WGS_1972_UTM_Zone_41S");
            WGS1972UTMZone42N.setName("WGS_1972_UTM_Zone_42N");
            WGS1972UTMZone42S.setName("WGS_1972_UTM_Zone_42S");
            WGS1972UTMZone43N.setName("WGS_1972_UTM_Zone_43N");
            WGS1972UTMZone43S.setName("WGS_1972_UTM_Zone_43S");
            WGS1972UTMZone44N.setName("WGS_1972_UTM_Zone_44N");
            WGS1972UTMZone44S.setName("WGS_1972_UTM_Zone_44S");
            WGS1972UTMZone45N.setName("WGS_1972_UTM_Zone_45N");
            WGS1972UTMZone45S.setName("WGS_1972_UTM_Zone_45S");
            WGS1972UTMZone46N.setName("WGS_1972_UTM_Zone_46N");
            WGS1972UTMZone46S.setName("WGS_1972_UTM_Zone_46S");
            WGS1972UTMZone47N.setName("WGS_1972_UTM_Zone_47N");
            WGS1972UTMZone47S.setName("WGS_1972_UTM_Zone_47S");
            WGS1972UTMZone48N.setName("WGS_1972_UTM_Zone_48N");
            WGS1972UTMZone48S.setName("WGS_1972_UTM_Zone_48S");
            WGS1972UTMZone49N.setName("WGS_1972_UTM_Zone_49N");
            WGS1972UTMZone49S.setName("WGS_1972_UTM_Zone_49S");
            WGS1972UTMZone4N.setName("WGS_1972_UTM_Zone_4N");
            WGS1972UTMZone4S.setName("WGS_1972_UTM_Zone_4S");
            WGS1972UTMZone50N.setName("WGS_1972_UTM_Zone_50N");
            WGS1972UTMZone50S.setName("WGS_1972_UTM_Zone_50S");
            WGS1972UTMZone51N.setName("WGS_1972_UTM_Zone_51N");
            WGS1972UTMZone51S.setName("WGS_1972_UTM_Zone_51S");
            WGS1972UTMZone52N.setName("WGS_1972_UTM_Zone_52N");
            WGS1972UTMZone52S.setName("WGS_1972_UTM_Zone_52S");
            WGS1972UTMZone53N.setName("WGS_1972_UTM_Zone_53N");
            WGS1972UTMZone53S.setName("WGS_1972_UTM_Zone_53S");
            WGS1972UTMZone54N.setName("WGS_1972_UTM_Zone_54N");
            WGS1972UTMZone54S.setName("WGS_1972_UTM_Zone_54S");
            WGS1972UTMZone55N.setName("WGS_1972_UTM_Zone_55N");
            WGS1972UTMZone55S.setName("WGS_1972_UTM_Zone_55S");
            WGS1972UTMZone56N.setName("WGS_1972_UTM_Zone_56N");
            WGS1972UTMZone56S.setName("WGS_1972_UTM_Zone_56S");
            WGS1972UTMZone57N.setName("WGS_1972_UTM_Zone_57N");
            WGS1972UTMZone57S.setName("WGS_1972_UTM_Zone_57S");
            WGS1972UTMZone58N.setName("WGS_1972_UTM_Zone_58N");
            WGS1972UTMZone58S.setName("WGS_1972_UTM_Zone_58S");
            WGS1972UTMZone59N.setName("WGS_1972_UTM_Zone_59N");
            WGS1972UTMZone59S.setName("WGS_1972_UTM_Zone_59S");
            WGS1972UTMZone5N.setName("WGS_1972_UTM_Zone_5N");
            WGS1972UTMZone5S.setName("WGS_1972_UTM_Zone_5S");
            WGS1972UTMZone60N.setName("WGS_1972_UTM_Zone_60N");
            WGS1972UTMZone60S.setName("WGS_1972_UTM_Zone_60S");
            WGS1972UTMZone6N.setName("WGS_1972_UTM_Zone_6N");
            WGS1972UTMZone6S.setName("WGS_1972_UTM_Zone_6S");
            WGS1972UTMZone7N.setName("WGS_1972_UTM_Zone_7N");
            WGS1972UTMZone7S.setName("WGS_1972_UTM_Zone_7S");
            WGS1972UTMZone8N.setName("WGS_1972_UTM_Zone_8N");
            WGS1972UTMZone8S.setName("WGS_1972_UTM_Zone_8S");
            WGS1972UTMZone9N.setName("WGS_1972_UTM_Zone_9N");
            WGS1972UTMZone9S.setName("WGS_1972_UTM_Zone_9S");

            WGS1972UTMZone10N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone10S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone11N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone11S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone12N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone12S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone13N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone13S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone14N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone14S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone15N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone15S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone16N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone16S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone17N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone17S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone18N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone18S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone19N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone19S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone1N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone1S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone20N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone20S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone21N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone21S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone22N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone22S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone23N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone23S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone24N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone24S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone25N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone25S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone26N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone26S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone27N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone27S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone28N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone28S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone29N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone29S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone2N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone2S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone30N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone30S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone31N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone31S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone32N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone32S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone33N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone33S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone34N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone34S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone35N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone35S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone36N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone36S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone37N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone37S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone38N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone38S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone39N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone39S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone3N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone3S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone40N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone40S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone41N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone41S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone42N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone42S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone43N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone43S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone44N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone44S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone45N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone45S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone46N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone46S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone47N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone47S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone48N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone48S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone49N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone49S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone4N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone4S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone50N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone50S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone51N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone51S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone52N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone52S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone53N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone53S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone54N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone54S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone55N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone55S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone56N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone56S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone57N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone57S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone58N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone58S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone59N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone59S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone5N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone5S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone60N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone60S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone6N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone6S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone7N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone7S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone8N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone8S.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone9N.getGeographicInfo().setName("GCS_WGS_1972");
            WGS1972UTMZone9S.getGeographicInfo().setName("GCS_WGS_1972");

            WGS1972UTMZone10N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone10S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone11N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone11S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone12N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone12S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone13N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone13S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone14N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone14S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone15N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone15S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone16N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone16S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone17N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone17S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone18N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone18S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone19N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone19S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone1N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone1S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone20N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone20S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone21N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone21S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone22N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone22S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone23N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone23S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone24N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone24S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone25N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone25S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone26N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone26S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone27N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone27S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone28N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone28S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone29N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone29S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone2N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone2S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone30N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone30S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone31N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone31S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone32N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone32S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone33N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone33S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone34N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone34S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone35N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone35S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone36N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone36S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone37N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone37S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone38N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone38S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone39N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone39S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone3N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone3S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone40N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone40S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone41N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone41S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone42N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone42S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone43N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone43S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone44N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone44S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone45N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone45S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone46N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone46S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone47N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone47S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone48N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone48S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone49N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone49S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone4N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone4S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone50N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone50S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone51N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone51S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone52N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone52S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone53N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone53S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone54N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone54S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone55N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone55S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone56N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone56S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone57N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone57S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone58N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone58S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone59N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone59S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone5N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone5S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone60N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone60S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone6N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone6S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone7N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone7S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone8N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone8S.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone9N.getGeographicInfo().getDatum().setName("D_WGS_1972");
            WGS1972UTMZone9S.getGeographicInfo().getDatum().setName("D_WGS_1972");
        }

        //</editor-fold>

  /**
   * @return the WGS1972UTMZone10N
   */
  public ProjectionInfo getWGS1972UTMZone10N() {
    return WGS1972UTMZone10N.copy();
  }

  /**
   * @return the WGS1972UTMZone10S
   */
  public ProjectionInfo getWGS1972UTMZone10S() {
    return WGS1972UTMZone10S.copy();
  }

  /**
   * @return the WGS1972UTMZone11N
   */
  public ProjectionInfo getWGS1972UTMZone11N() {
    return WGS1972UTMZone11N.copy();
  }

  /**
   * @return the WGS1972UTMZone11S
   */
  public ProjectionInfo getWGS1972UTMZone11S() {
    return WGS1972UTMZone11S.copy();
  }

  /**
   * @return the WGS1972UTMZone12N
   */
  public ProjectionInfo getWGS1972UTMZone12N() {
    return WGS1972UTMZone12N.copy();
  }

  /**
   * @return the WGS1972UTMZone12S
   */
  public ProjectionInfo getWGS1972UTMZone12S() {
    return WGS1972UTMZone12S.copy();
  }

  /**
   * @return the WGS1972UTMZone13N
   */
  public ProjectionInfo getWGS1972UTMZone13N() {
    return WGS1972UTMZone13N.copy();
  }

  /**
   * @return the WGS1972UTMZone13S
   */
  public ProjectionInfo getWGS1972UTMZone13S() {
    return WGS1972UTMZone13S.copy();
  }

  /**
   * @return the WGS1972UTMZone14N
   */
  public ProjectionInfo getWGS1972UTMZone14N() {
    return WGS1972UTMZone14N.copy();
  }

  /**
   * @return the WGS1972UTMZone14S
   */
  public ProjectionInfo getWGS1972UTMZone14S() {
    return WGS1972UTMZone14S.copy();
  }

  /**
   * @return the WGS1972UTMZone15N
   */
  public ProjectionInfo getWGS1972UTMZone15N() {
    return WGS1972UTMZone15N.copy();
  }

  /**
   * @return the WGS1972UTMZone15S
   */
  public ProjectionInfo getWGS1972UTMZone15S() {
    return WGS1972UTMZone15S.copy();
  }

  /**
   * @return the WGS1972UTMZone16N
   */
  public ProjectionInfo getWGS1972UTMZone16N() {
    return WGS1972UTMZone16N.copy();
  }

  /**
   * @return the WGS1972UTMZone16S
   */
  public ProjectionInfo getWGS1972UTMZone16S() {
    return WGS1972UTMZone16S.copy();
  }

  /**
   * @return the WGS1972UTMZone17N
   */
  public ProjectionInfo getWGS1972UTMZone17N() {
    return WGS1972UTMZone17N.copy();
  }

  /**
   * @return the WGS1972UTMZone17S
   */
  public ProjectionInfo getWGS1972UTMZone17S() {
    return WGS1972UTMZone17S.copy();
  }

  /**
   * @return the WGS1972UTMZone18N
   */
  public ProjectionInfo getWGS1972UTMZone18N() {
    return WGS1972UTMZone18N.copy();
  }

  /**
   * @return the WGS1972UTMZone18S
   */
  public ProjectionInfo getWGS1972UTMZone18S() {
    return WGS1972UTMZone18S.copy();
  }

  /**
   * @return the WGS1972UTMZone19N
   */
  public ProjectionInfo getWGS1972UTMZone19N() {
    return WGS1972UTMZone19N.copy();
  }

  /**
   * @return the WGS1972UTMZone19S
   */
  public ProjectionInfo getWGS1972UTMZone19S() {
    return WGS1972UTMZone19S.copy();
  }

  /**
   * @return the WGS1972UTMZone1N
   */
  public ProjectionInfo getWGS1972UTMZone1N() {
    return WGS1972UTMZone1N.copy();
  }

  /**
   * @return the WGS1972UTMZone1S
   */
  public ProjectionInfo getWGS1972UTMZone1S() {
    return WGS1972UTMZone1S.copy();
  }

  /**
   * @return the WGS1972UTMZone20N
   */
  public ProjectionInfo getWGS1972UTMZone20N() {
    return WGS1972UTMZone20N.copy();
  }

  /**
   * @return the WGS1972UTMZone20S
   */
  public ProjectionInfo getWGS1972UTMZone20S() {
    return WGS1972UTMZone20S.copy();
  }

  /**
   * @return the WGS1972UTMZone21N
   */
  public ProjectionInfo getWGS1972UTMZone21N() {
    return WGS1972UTMZone21N.copy();
  }

  /**
   * @return the WGS1972UTMZone21S
   */
  public ProjectionInfo getWGS1972UTMZone21S() {
    return WGS1972UTMZone21S.copy();
  }

  /**
   * @return the WGS1972UTMZone22N
   */
  public ProjectionInfo getWGS1972UTMZone22N() {
    return WGS1972UTMZone22N.copy();
  }

  /**
   * @return the WGS1972UTMZone22S
   */
  public ProjectionInfo getWGS1972UTMZone22S() {
    return WGS1972UTMZone22S.copy();
  }

  /**
   * @return the WGS1972UTMZone23N
   */
  public ProjectionInfo getWGS1972UTMZone23N() {
    return WGS1972UTMZone23N.copy();
  }

  /**
   * @return the WGS1972UTMZone23S
   */
  public ProjectionInfo getWGS1972UTMZone23S() {
    return WGS1972UTMZone23S.copy();
  }

  /**
   * @return the WGS1972UTMZone24N
   */
  public ProjectionInfo getWGS1972UTMZone24N() {
    return WGS1972UTMZone24N.copy();
  }

  /**
   * @return the WGS1972UTMZone24S
   */
  public ProjectionInfo getWGS1972UTMZone24S() {
    return WGS1972UTMZone24S.copy();
  }

  /**
   * @return the WGS1972UTMZone25N
   */
  public ProjectionInfo getWGS1972UTMZone25N() {
    return WGS1972UTMZone25N.copy();
  }

  /**
   * @return the WGS1972UTMZone25S
   */
  public ProjectionInfo getWGS1972UTMZone25S() {
    return WGS1972UTMZone25S.copy();
  }

  /**
   * @return the WGS1972UTMZone26N
   */
  public ProjectionInfo getWGS1972UTMZone26N() {
    return WGS1972UTMZone26N.copy();
  }

  /**
   * @return the WGS1972UTMZone26S
   */
  public ProjectionInfo getWGS1972UTMZone26S() {
    return WGS1972UTMZone26S.copy();
  }

  /**
   * @return the WGS1972UTMZone27N
   */
  public ProjectionInfo getWGS1972UTMZone27N() {
    return WGS1972UTMZone27N.copy();
  }

  /**
   * @return the WGS1972UTMZone27S
   */
  public ProjectionInfo getWGS1972UTMZone27S() {
    return WGS1972UTMZone27S.copy();
  }

  /**
   * @return the WGS1972UTMZone28N
   */
  public ProjectionInfo getWGS1972UTMZone28N() {
    return WGS1972UTMZone28N.copy();
  }

  /**
   * @return the WGS1972UTMZone28S
   */
  public ProjectionInfo getWGS1972UTMZone28S() {
    return WGS1972UTMZone28S.copy();
  }

  /**
   * @return the WGS1972UTMZone29N
   */
  public ProjectionInfo getWGS1972UTMZone29N() {
    return WGS1972UTMZone29N.copy();
  }

  /**
   * @return the WGS1972UTMZone29S
   */
  public ProjectionInfo getWGS1972UTMZone29S() {
    return WGS1972UTMZone29S.copy();
  }

  /**
   * @return the WGS1972UTMZone2N
   */
  public ProjectionInfo getWGS1972UTMZone2N() {
    return WGS1972UTMZone2N.copy();
  }

  /**
   * @return the WGS1972UTMZone2S
   */
  public ProjectionInfo getWGS1972UTMZone2S() {
    return WGS1972UTMZone2S.copy();
  }

  /**
   * @return the WGS1972UTMZone30N
   */
  public ProjectionInfo getWGS1972UTMZone30N() {
    return WGS1972UTMZone30N.copy();
  }

  /**
   * @return the WGS1972UTMZone30S
   */
  public ProjectionInfo getWGS1972UTMZone30S() {
    return WGS1972UTMZone30S.copy();
  }

  /**
   * @return the WGS1972UTMZone31N
   */
  public ProjectionInfo getWGS1972UTMZone31N() {
    return WGS1972UTMZone31N.copy();
  }

  /**
   * @return the WGS1972UTMZone31S
   */
  public ProjectionInfo getWGS1972UTMZone31S() {
    return WGS1972UTMZone31S.copy();
  }

  /**
   * @return the WGS1972UTMZone32N
   */
  public ProjectionInfo getWGS1972UTMZone32N() {
    return WGS1972UTMZone32N.copy();
  }

  /**
   * @return the WGS1972UTMZone32S
   */
  public ProjectionInfo getWGS1972UTMZone32S() {
    return WGS1972UTMZone32S.copy();
  }

  /**
   * @return the WGS1972UTMZone33N
   */
  public ProjectionInfo getWGS1972UTMZone33N() {
    return WGS1972UTMZone33N.copy();
  }

  /**
   * @return the WGS1972UTMZone33S
   */
  public ProjectionInfo getWGS1972UTMZone33S() {
    return WGS1972UTMZone33S.copy();
  }

  /**
   * @return the WGS1972UTMZone34N
   */
  public ProjectionInfo getWGS1972UTMZone34N() {
    return WGS1972UTMZone34N.copy();
  }

  /**
   * @return the WGS1972UTMZone34S
   */
  public ProjectionInfo getWGS1972UTMZone34S() {
    return WGS1972UTMZone34S.copy();
  }

  /**
   * @return the WGS1972UTMZone35N
   */
  public ProjectionInfo getWGS1972UTMZone35N() {
    return WGS1972UTMZone35N.copy();
  }

  /**
   * @return the WGS1972UTMZone35S
   */
  public ProjectionInfo getWGS1972UTMZone35S() {
    return WGS1972UTMZone35S.copy();
  }

  /**
   * @return the WGS1972UTMZone36N
   */
  public ProjectionInfo getWGS1972UTMZone36N() {
    return WGS1972UTMZone36N.copy();
  }

  /**
   * @return the WGS1972UTMZone36S
   */
  public ProjectionInfo getWGS1972UTMZone36S() {
    return WGS1972UTMZone36S.copy();
  }

  /**
   * @return the WGS1972UTMZone37N
   */
  public ProjectionInfo getWGS1972UTMZone37N() {
    return WGS1972UTMZone37N.copy();
  }

  /**
   * @return the WGS1972UTMZone37S
   */
  public ProjectionInfo getWGS1972UTMZone37S() {
    return WGS1972UTMZone37S.copy();
  }

  /**
   * @return the WGS1972UTMZone38N
   */
  public ProjectionInfo getWGS1972UTMZone38N() {
    return WGS1972UTMZone38N.copy();
  }

  /**
   * @return the WGS1972UTMZone38S
   */
  public ProjectionInfo getWGS1972UTMZone38S() {
    return WGS1972UTMZone38S.copy();
  }

  /**
   * @return the WGS1972UTMZone39N
   */
  public ProjectionInfo getWGS1972UTMZone39N() {
    return WGS1972UTMZone39N.copy();
  }

  /**
   * @return the WGS1972UTMZone39S
   */
  public ProjectionInfo getWGS1972UTMZone39S() {
    return WGS1972UTMZone39S.copy();
  }

  /**
   * @return the WGS1972UTMZone3N
   */
  public ProjectionInfo getWGS1972UTMZone3N() {
    return WGS1972UTMZone3N.copy();
  }

  /**
   * @return the WGS1972UTMZone3S
   */
  public ProjectionInfo getWGS1972UTMZone3S() {
    return WGS1972UTMZone3S.copy();
  }

  /**
   * @return the WGS1972UTMZone40N
   */
  public ProjectionInfo getWGS1972UTMZone40N() {
    return WGS1972UTMZone40N.copy();
  }

  /**
   * @return the WGS1972UTMZone40S
   */
  public ProjectionInfo getWGS1972UTMZone40S() {
    return WGS1972UTMZone40S.copy();
  }

  /**
   * @return the WGS1972UTMZone41N
   */
  public ProjectionInfo getWGS1972UTMZone41N() {
    return WGS1972UTMZone41N.copy();
  }

  /**
   * @return the WGS1972UTMZone41S
   */
  public ProjectionInfo getWGS1972UTMZone41S() {
    return WGS1972UTMZone41S.copy();
  }

  /**
   * @return the WGS1972UTMZone42N
   */
  public ProjectionInfo getWGS1972UTMZone42N() {
    return WGS1972UTMZone42N.copy();
  }

  /**
   * @return the WGS1972UTMZone42S
   */
  public ProjectionInfo getWGS1972UTMZone42S() {
    return WGS1972UTMZone42S.copy();
  }

  /**
   * @return the WGS1972UTMZone43N
   */
  public ProjectionInfo getWGS1972UTMZone43N() {
    return WGS1972UTMZone43N.copy();
  }

  /**
   * @return the WGS1972UTMZone43S
   */
  public ProjectionInfo getWGS1972UTMZone43S() {
    return WGS1972UTMZone43S.copy();
  }

  /**
   * @return the WGS1972UTMZone44N
   */
  public ProjectionInfo getWGS1972UTMZone44N() {
    return WGS1972UTMZone44N.copy();
  }

  /**
   * @return the WGS1972UTMZone44S
   */
  public ProjectionInfo getWGS1972UTMZone44S() {
    return WGS1972UTMZone44S.copy();
  }

  /**
   * @return the WGS1972UTMZone45N
   */
  public ProjectionInfo getWGS1972UTMZone45N() {
    return WGS1972UTMZone45N.copy();
  }

  /**
   * @return the WGS1972UTMZone45S
   */
  public ProjectionInfo getWGS1972UTMZone45S() {
    return WGS1972UTMZone45S.copy();
  }

  /**
   * @return the WGS1972UTMZone46N
   */
  public ProjectionInfo getWGS1972UTMZone46N() {
    return WGS1972UTMZone46N.copy();
  }

  /**
   * @return the WGS1972UTMZone46S
   */
  public ProjectionInfo getWGS1972UTMZone46S() {
    return WGS1972UTMZone46S.copy();
  }

  /**
   * @return the WGS1972UTMZone47N
   */
  public ProjectionInfo getWGS1972UTMZone47N() {
    return WGS1972UTMZone47N.copy();
  }

  /**
   * @return the WGS1972UTMZone47S
   */
  public ProjectionInfo getWGS1972UTMZone47S() {
    return WGS1972UTMZone47S.copy();
  }

  /**
   * @return the WGS1972UTMZone48N
   */
  public ProjectionInfo getWGS1972UTMZone48N() {
    return WGS1972UTMZone48N.copy();
  }

  /**
   * @return the WGS1972UTMZone48S
   */
  public ProjectionInfo getWGS1972UTMZone48S() {
    return WGS1972UTMZone48S.copy();
  }

  /**
   * @return the WGS1972UTMZone49N
   */
  public ProjectionInfo getWGS1972UTMZone49N() {
    return WGS1972UTMZone49N.copy();
  }

  /**
   * @return the WGS1972UTMZone49S
   */
  public ProjectionInfo getWGS1972UTMZone49S() {
    return WGS1972UTMZone49S.copy();
  }

  /**
   * @return the WGS1972UTMZone4N
   */
  public ProjectionInfo getWGS1972UTMZone4N() {
    return WGS1972UTMZone4N.copy();
  }

  /**
   * @return the WGS1972UTMZone4S
   */
  public ProjectionInfo getWGS1972UTMZone4S() {
    return WGS1972UTMZone4S.copy();
  }

  /**
   * @return the WGS1972UTMZone50N
   */
  public ProjectionInfo getWGS1972UTMZone50N() {
    return WGS1972UTMZone50N.copy();
  }

  /**
   * @return the WGS1972UTMZone50S
   */
  public ProjectionInfo getWGS1972UTMZone50S() {
    return WGS1972UTMZone50S.copy();
  }

  /**
   * @return the WGS1972UTMZone51N
   */
  public ProjectionInfo getWGS1972UTMZone51N() {
    return WGS1972UTMZone51N.copy();
  }

  /**
   * @return the WGS1972UTMZone51S
   */
  public ProjectionInfo getWGS1972UTMZone51S() {
    return WGS1972UTMZone51S.copy();
  }

  /**
   * @return the WGS1972UTMZone52N
   */
  public ProjectionInfo getWGS1972UTMZone52N() {
    return WGS1972UTMZone52N.copy();
  }

  /**
   * @return the WGS1972UTMZone52S
   */
  public ProjectionInfo getWGS1972UTMZone52S() {
    return WGS1972UTMZone52S.copy();
  }

  /**
   * @return the WGS1972UTMZone53N
   */
  public ProjectionInfo getWGS1972UTMZone53N() {
    return WGS1972UTMZone53N.copy();
  }

  /**
   * @return the WGS1972UTMZone53S
   */
  public ProjectionInfo getWGS1972UTMZone53S() {
    return WGS1972UTMZone53S.copy();
  }

  /**
   * @return the WGS1972UTMZone54N
   */
  public ProjectionInfo getWGS1972UTMZone54N() {
    return WGS1972UTMZone54N.copy();
  }

  /**
   * @return the WGS1972UTMZone54S
   */
  public ProjectionInfo getWGS1972UTMZone54S() {
    return WGS1972UTMZone54S.copy();
  }

  /**
   * @return the WGS1972UTMZone55N
   */
  public ProjectionInfo getWGS1972UTMZone55N() {
    return WGS1972UTMZone55N.copy();
  }

  /**
   * @return the WGS1972UTMZone55S
   */
  public ProjectionInfo getWGS1972UTMZone55S() {
    return WGS1972UTMZone55S.copy();
  }

  /**
   * @return the WGS1972UTMZone56N
   */
  public ProjectionInfo getWGS1972UTMZone56N() {
    return WGS1972UTMZone56N.copy();
  }

  /**
   * @return the WGS1972UTMZone56S
   */
  public ProjectionInfo getWGS1972UTMZone56S() {
    return WGS1972UTMZone56S.copy();
  }

  /**
   * @return the WGS1972UTMZone57N
   */
  public ProjectionInfo getWGS1972UTMZone57N() {
    return WGS1972UTMZone57N.copy();
  }

  /**
   * @return the WGS1972UTMZone57S
   */
  public ProjectionInfo getWGS1972UTMZone57S() {
    return WGS1972UTMZone57S.copy();
  }

  /**
   * @return the WGS1972UTMZone58N
   */
  public ProjectionInfo getWGS1972UTMZone58N() {
    return WGS1972UTMZone58N.copy();
  }

  /**
   * @return the WGS1972UTMZone58S
   */
  public ProjectionInfo getWGS1972UTMZone58S() {
    return WGS1972UTMZone58S.copy();
  }

  /**
   * @return the WGS1972UTMZone59N
   */
  public ProjectionInfo getWGS1972UTMZone59N() {
    return WGS1972UTMZone59N.copy();
  }

  /**
   * @return the WGS1972UTMZone59S
   */
  public ProjectionInfo getWGS1972UTMZone59S() {
    return WGS1972UTMZone59S.copy();
  }

  /**
   * @return the WGS1972UTMZone5N
   */
  public ProjectionInfo getWGS1972UTMZone5N() {
    return WGS1972UTMZone5N.copy();
  }

  /**
   * @return the WGS1972UTMZone5S
   */
  public ProjectionInfo getWGS1972UTMZone5S() {
    return WGS1972UTMZone5S.copy();
  }

  /**
   * @return the WGS1972UTMZone60N
   */
  public ProjectionInfo getWGS1972UTMZone60N() {
    return WGS1972UTMZone60N.copy();
  }

  /**
   * @return the WGS1972UTMZone60S
   */
  public ProjectionInfo getWGS1972UTMZone60S() {
    return WGS1972UTMZone60S.copy();
  }

  /**
   * @return the WGS1972UTMZone6N
   */
  public ProjectionInfo getWGS1972UTMZone6N() {
    return WGS1972UTMZone6N.copy();
  }

  /**
   * @return the WGS1972UTMZone6S
   */
  public ProjectionInfo getWGS1972UTMZone6S() {
    return WGS1972UTMZone6S.copy();
  }

  /**
   * @return the WGS1972UTMZone7N
   */
  public ProjectionInfo getWGS1972UTMZone7N() {
    return WGS1972UTMZone7N.copy();
  }

  /**
   * @return the WGS1972UTMZone7S
   */
  public ProjectionInfo getWGS1972UTMZone7S() {
    return WGS1972UTMZone7S.copy();
  }

  /**
   * @return the WGS1972UTMZone8N
   */
  public ProjectionInfo getWGS1972UTMZone8N() {
    return WGS1972UTMZone8N.copy();
  }

  /**
   * @return the WGS1972UTMZone8S
   */
  public ProjectionInfo getWGS1972UTMZone8S() {
    return WGS1972UTMZone8S.copy();
  }

  /**
   * @return the WGS1972UTMZone9N
   */
  public ProjectionInfo getWGS1972UTMZone9N() {
    return WGS1972UTMZone9N.copy();
  }

  /**
   * @return the WGS1972UTMZone9S
   */
  public ProjectionInfo getWGS1972UTMZone9S() {
    return WGS1972UTMZone9S.copy();
  }
    }


