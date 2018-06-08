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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 5:07:15 PM
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
     * UtmNad1927
     * @author Harold A. Dunsford Jr. Ph.D.
     */
    public class UtmNad1927 extends CoordinateSystemCategory
    {
        //<editor-fold defaultstate="collapsed" desc="Fields">

      private final ProjectionInfo NAD1927UTMZone10N;
      private final ProjectionInfo NAD1927UTMZone11N;
      private final ProjectionInfo NAD1927UTMZone12N;
      private final ProjectionInfo NAD1927UTMZone13N;
      private final ProjectionInfo NAD1927UTMZone14N;
      private final ProjectionInfo NAD1927UTMZone15N;
      private final ProjectionInfo NAD1927UTMZone16N;
      private final ProjectionInfo NAD1927UTMZone17N;
      private final ProjectionInfo NAD1927UTMZone18N;
      private final ProjectionInfo NAD1927UTMZone19N;
      private final ProjectionInfo NAD1927UTMZone1N;
      private final ProjectionInfo NAD1927UTMZone20N;
      private final ProjectionInfo NAD1927UTMZone21N;
      private final ProjectionInfo NAD1927UTMZone22N;
      private final ProjectionInfo NAD1927UTMZone2N;
      private final ProjectionInfo NAD1927UTMZone3N;
      private final ProjectionInfo NAD1927UTMZone4N;
      private final ProjectionInfo NAD1927UTMZone59N;
      private final ProjectionInfo NAD1927UTMZone5N;
      private final ProjectionInfo NAD1927UTMZone60N;
      private final ProjectionInfo NAD1927UTMZone6N;
      private final ProjectionInfo NAD1927UTMZone7N;
      private final ProjectionInfo NAD1927UTMZone8N;
      private final ProjectionInfo NAD1927UTMZone9N;

        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Constructor">

        /**
         * Creates a new instance of UtmNad1927.
         */
        public UtmNad1927()
        {
            NAD1927UTMZone10N = ProjectionInfo.fromProj4String("+proj=utm +zone=10 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone11N = ProjectionInfo.fromProj4String("+proj=utm +zone=11 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone12N = ProjectionInfo.fromProj4String("+proj=utm +zone=12 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone13N = ProjectionInfo.fromProj4String("+proj=utm +zone=13 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone14N = ProjectionInfo.fromProj4String("+proj=utm +zone=14 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone15N = ProjectionInfo.fromProj4String("+proj=utm +zone=15 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone16N = ProjectionInfo.fromProj4String("+proj=utm +zone=16 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone17N = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone1N = ProjectionInfo.fromProj4String("+proj=utm +zone=1 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone22N = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone2N = ProjectionInfo.fromProj4String("+proj=utm +zone=2 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone3N = ProjectionInfo.fromProj4String("+proj=utm +zone=3 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone4N = ProjectionInfo.fromProj4String("+proj=utm +zone=4 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone59N = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone5N = ProjectionInfo.fromProj4String("+proj=utm +zone=5 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone60N = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone6N = ProjectionInfo.fromProj4String("+proj=utm +zone=6 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone7N = ProjectionInfo.fromProj4String("+proj=utm +zone=7 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone8N = ProjectionInfo.fromProj4String("+proj=utm +zone=8 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);
            NAD1927UTMZone9N = ProjectionInfo.fromProj4String("+proj=utm +zone=9 +ellps=clrk66 +datum=NAD27 +units=m +no_defs ").orElse(null);

            NAD1927UTMZone10N.setName("NAD_1927_UTM_Zone_10N");
            NAD1927UTMZone11N.setName("NAD_1927_UTM_Zone_11N");
            NAD1927UTMZone12N.setName("NAD_1927_UTM_Zone_12N");
            NAD1927UTMZone13N.setName("NAD_1927_UTM_Zone_13N");
            NAD1927UTMZone14N.setName("NAD_1927_UTM_Zone_14N");
            NAD1927UTMZone15N.setName("NAD_1927_UTM_Zone_15N");
            NAD1927UTMZone16N.setName("NAD_1927_UTM_Zone_16N");
            NAD1927UTMZone17N.setName("NAD_1927_UTM_Zone_17N");
            NAD1927UTMZone18N.setName("NAD_1927_UTM_Zone_18N");
            NAD1927UTMZone19N.setName("NAD_1927_UTM_Zone_19N");
            NAD1927UTMZone1N.setName("NAD_1927_UTM_Zone_1N");
            NAD1927UTMZone20N.setName("NAD_1927_UTM_Zone_20N");
            NAD1927UTMZone21N.setName("NAD_1927_UTM_Zone_21N");
            NAD1927UTMZone22N.setName("NAD_1927_UTM_Zone_22N");
            NAD1927UTMZone2N.setName("NAD_1927_UTM_Zone_2N");
            NAD1927UTMZone3N.setName("NAD_1927_UTM_Zone_3N");
            NAD1927UTMZone4N.setName("NAD_1927_UTM_Zone_4N");
            NAD1927UTMZone59N.setName("NAD_1927_UTM_Zone_59N");
            NAD1927UTMZone5N.setName("NAD_1927_UTM_Zone_5N");
            NAD1927UTMZone60N.setName("NAD_1927_UTM_Zone_60N");
            NAD1927UTMZone6N.setName("NAD_1927_UTM_Zone_6N");
            NAD1927UTMZone7N.setName("NAD_1927_UTM_Zone_7N");
            NAD1927UTMZone8N.setName("NAD_1927_UTM_Zone_8N");
            NAD1927UTMZone9N.setName("NAD_1927_UTM_Zone_9N");

            NAD1927UTMZone10N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone11N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone12N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone13N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone14N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone15N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone16N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone17N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone18N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone19N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone1N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone20N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone21N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone22N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone2N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone3N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone4N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone59N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone5N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone60N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone6N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone7N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone8N.getGeographicInfo().setName("GCS_North_American_1927");
            NAD1927UTMZone9N.getGeographicInfo().setName("GCS_North_American_1927");

            NAD1927UTMZone10N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone11N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone12N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone13N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone14N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone15N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone16N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone17N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone18N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone19N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone1N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone20N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone21N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone22N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone2N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone3N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone4N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone59N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone5N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone60N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone6N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone7N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone8N.getGeographicInfo().getDatum().setName("D_North_American_1927");
            NAD1927UTMZone9N.getGeographicInfo().getDatum().setName("D_North_American_1927");
        }

        //</editor-fold>

  /**
   * @return the NAD1927UTMZone10N
   */
  public ProjectionInfo getNAD1927UTMZone10N() {
    return NAD1927UTMZone10N.copy();
  }

  /**
   * @return the NAD1927UTMZone11N
   */
  public ProjectionInfo getNAD1927UTMZone11N() {
    return NAD1927UTMZone11N.copy();
  }

  /**
   * @return the NAD1927UTMZone12N
   */
  public ProjectionInfo getNAD1927UTMZone12N() {
    return NAD1927UTMZone12N.copy();
  }

  /**
   * @return the NAD1927UTMZone13N
   */
  public ProjectionInfo getNAD1927UTMZone13N() {
    return NAD1927UTMZone13N.copy();
  }

  /**
   * @return the NAD1927UTMZone14N
   */
  public ProjectionInfo getNAD1927UTMZone14N() {
    return NAD1927UTMZone14N.copy();
  }

  /**
   * @return the NAD1927UTMZone15N
   */
  public ProjectionInfo getNAD1927UTMZone15N() {
    return NAD1927UTMZone15N.copy();
  }

  /**
   * @return the NAD1927UTMZone16N
   */
  public ProjectionInfo getNAD1927UTMZone16N() {
    return NAD1927UTMZone16N.copy();
  }

  /**
   * @return the NAD1927UTMZone17N
   */
  public ProjectionInfo getNAD1927UTMZone17N() {
    return NAD1927UTMZone17N.copy();
  }

  /**
   * @return the NAD1927UTMZone18N
   */
  public ProjectionInfo getNAD1927UTMZone18N() {
    return NAD1927UTMZone18N.copy();
  }

  /**
   * @return the NAD1927UTMZone19N
   */
  public ProjectionInfo getNAD1927UTMZone19N() {
    return NAD1927UTMZone19N.copy();
  }

  /**
   * @return the NAD1927UTMZone1N
   */
  public ProjectionInfo getNAD1927UTMZone1N() {
    return NAD1927UTMZone1N.copy();
  }

  /**
   * @return the NAD1927UTMZone20N
   */
  public ProjectionInfo getNAD1927UTMZone20N() {
    return NAD1927UTMZone20N.copy();
  }

  /**
   * @return the NAD1927UTMZone21N
   */
  public ProjectionInfo getNAD1927UTMZone21N() {
    return NAD1927UTMZone21N.copy();
  }

  /**
   * @return the NAD1927UTMZone22N
   */
  public ProjectionInfo getNAD1927UTMZone22N() {
    return NAD1927UTMZone22N.copy();
  }

  /**
   * @return the NAD1927UTMZone2N
   */
  public ProjectionInfo getNAD1927UTMZone2N() {
    return NAD1927UTMZone2N.copy();
  }

  /**
   * @return the NAD1927UTMZone3N
   */
  public ProjectionInfo getNAD1927UTMZone3N() {
    return NAD1927UTMZone3N.copy();
  }

  /**
   * @return the NAD1927UTMZone4N
   */
  public ProjectionInfo getNAD1927UTMZone4N() {
    return NAD1927UTMZone4N.copy();
  }

  /**
   * @return the NAD1927UTMZone59N
   */
  public ProjectionInfo getNAD1927UTMZone59N() {
    return NAD1927UTMZone59N.copy();
  }

  /**
   * @return the NAD1927UTMZone5N
   */
  public ProjectionInfo getNAD1927UTMZone5N() {
    return NAD1927UTMZone5N.copy();
  }

  /**
   * @return the NAD1927UTMZone60N
   */
  public ProjectionInfo getNAD1927UTMZone60N() {
    return NAD1927UTMZone60N.copy();
  }

  /**
   * @return the NAD1927UTMZone6N
   */
  public ProjectionInfo getNAD1927UTMZone6N() {
    return NAD1927UTMZone6N.copy();
  }

  /**
   * @return the NAD1927UTMZone7N
   */
  public ProjectionInfo getNAD1927UTMZone7N() {
    return NAD1927UTMZone7N.copy();
  }

  /**
   * @return the NAD1927UTMZone8N
   */
  public ProjectionInfo getNAD1927UTMZone8N() {
    return NAD1927UTMZone8N.copy();
  }

  /**
   * @return the NAD1927UTMZone9N
   */
  public ProjectionInfo getNAD1927UTMZone9N() {
    return NAD1927UTMZone9N.copy();
  }
    }


