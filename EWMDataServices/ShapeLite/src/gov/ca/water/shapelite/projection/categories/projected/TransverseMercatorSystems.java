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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 5:06:30 PM
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
     * TransverseMercator.
     * @author Harold A. Dunsford Jr. Ph.D.
     */
    public class TransverseMercatorSystems extends CoordinateSystemCategory
    {
        //<editor-fold defaultstate="collapsed" desc="Fields">

      private final ProjectionInfo WGS1984lo16;
      private final ProjectionInfo WGS1984lo17;
      private final ProjectionInfo WGS1984lo18;
      private final ProjectionInfo WGS1984lo19;
      private final ProjectionInfo WGS1984lo20;
      private final ProjectionInfo WGS1984lo21;
      private final ProjectionInfo WGS1984lo22;
      private final ProjectionInfo WGS1984lo23;
      private final ProjectionInfo WGS1984lo24;
      private final ProjectionInfo WGS1984lo25;
      private final ProjectionInfo WGS1984lo26;
      private final ProjectionInfo WGS1984lo27;
      private final ProjectionInfo WGS1984lo28;
      private final ProjectionInfo WGS1984lo29;
      private final ProjectionInfo WGS1984lo30;
      private final ProjectionInfo WGS1984lo31;
      private final ProjectionInfo WGS1984lo32;
      private final ProjectionInfo WGS1984lo33;

        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Constructor">

        /**
         * Creates a new instance of TransverseMercator.
         */
        public TransverseMercatorSystems()
        {
            WGS1984lo16 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=16 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo17 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=17 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo18 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=18 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo19 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=19 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo20 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=20 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo21 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=21 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo22 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=22 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo23 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=23 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo24 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=24 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo25 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=25 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo26 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=26 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo27 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=27 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo28 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=28 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo29 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=29 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo30 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=30 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo31 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=31 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo32 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=32 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
            WGS1984lo33 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=33 +k=1.000000 +x_0=0 +y_0=0 +ellps=WGS84 +datum=WGS84 +units=m +no_defs ").orElse(null);
        }

        //</editor-fold>

  /**
   * @return the WGS1984lo16
   */
  public ProjectionInfo getWGS1984lo16() {
    return WGS1984lo16.copy();
  }

  /**
   * @return the WGS1984lo17
   */
  public ProjectionInfo getWGS1984lo17() {
    return WGS1984lo17.copy();
  }

  /**
   * @return the WGS1984lo18
   */
  public ProjectionInfo getWGS1984lo18() {
    return WGS1984lo18.copy();
  }

  /**
   * @return the WGS1984lo19
   */
  public ProjectionInfo getWGS1984lo19() {
    return WGS1984lo19.copy();
  }

  /**
   * @return the WGS1984lo20
   */
  public ProjectionInfo getWGS1984lo20() {
    return WGS1984lo20.copy();
  }

  /**
   * @return the WGS1984lo21
   */
  public ProjectionInfo getWGS1984lo21() {
    return WGS1984lo21.copy();
  }

  /**
   * @return the WGS1984lo22
   */
  public ProjectionInfo getWGS1984lo22() {
    return WGS1984lo22.copy();
  }

  /**
   * @return the WGS1984lo23
   */
  public ProjectionInfo getWGS1984lo23() {
    return WGS1984lo23.copy();
  }

  /**
   * @return the WGS1984lo24
   */
  public ProjectionInfo getWGS1984lo24() {
    return WGS1984lo24.copy();
  }

  /**
   * @return the WGS1984lo25
   */
  public ProjectionInfo getWGS1984lo25() {
    return WGS1984lo25.copy();
  }

  /**
   * @return the WGS1984lo26
   */
  public ProjectionInfo getWGS1984lo26() {
    return WGS1984lo26.copy();
  }

  /**
   * @return the WGS1984lo27
   */
  public ProjectionInfo getWGS1984lo27() {
    return WGS1984lo27.copy();
  }

  /**
   * @return the WGS1984lo28
   */
  public ProjectionInfo getWGS1984lo28() {
    return WGS1984lo28.copy();
  }

  /**
   * @return the WGS1984lo29
   */
  public ProjectionInfo getWGS1984lo29() {
    return WGS1984lo29.copy();
  }

  /**
   * @return the WGS1984lo30
   */
  public ProjectionInfo getWGS1984lo30() {
    return WGS1984lo30.copy();
  }

  /**
   * @return the WGS1984lo31
   */
  public ProjectionInfo getWGS1984lo31() {
    return WGS1984lo31.copy();
  }

  /**
   * @return the WGS1984lo32
   */
  public ProjectionInfo getWGS1984lo32() {
    return WGS1984lo32.copy();
  }

  /**
   * @return the WGS1984lo33
   */
  public ProjectionInfo getWGS1984lo33() {
    return WGS1984lo33.copy();
  }
    }


