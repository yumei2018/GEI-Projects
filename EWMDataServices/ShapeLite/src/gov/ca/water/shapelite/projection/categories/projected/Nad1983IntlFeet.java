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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 5:01:00 PM
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
    /// Nad1983IntlFeet
    /// </summary>
    public class Nad1983IntlFeet extends CoordinateSystemCategory
    {
        //<editor-fold defaultstate="collapsed" desc="Fields">

      private final ProjectionInfo NAD1983StatePlaneArizonaCentralFIPS0202FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneArizonaEastFIPS0201FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneArizonaWestFIPS0203FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneMichiganCentralFIPS2112FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneMichiganNorthFIPS2111FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneMichiganSouthFIPS2113FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneMontanaFIPS2500FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneNorthDakotaNorthFIPS3301FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneNorthDakotaSouthFIPS3302FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneOregonNorthFIPS3601FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneOregonSouthFIPS3602FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneSouthCarolinaFIPS3900FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneUtahCentralFIPS4302FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneUtahNorthFIPS4301FeetIntl;
      private final ProjectionInfo NAD1983StatePlaneUtahSouthFIPS4303FeetIntl;

        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Constructor">

        /// <summary>
        /// Creates a new instance of Nad1983IntlFeet
        /// </summary>
        public Nad1983IntlFeet()
        {
            NAD1983StatePlaneArizonaCentralFIPS0202FeetIntl = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-111.9166666666667 +k=0.999900 +x_0=213360 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneArizonaEastFIPS0201FeetIntl = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-110.1666666666667 +k=0.999900 +x_0=213360 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneArizonaWestFIPS0203FeetIntl = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=31 +lon_0=-113.75 +k=0.999933 +x_0=213360 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneMichiganCentralFIPS2112FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.18333333333333 +lat_2=45.7 +lat_0=43.31666666666667 +lon_0=-84.36666666666666 +x_0=6000000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneMichiganNorthFIPS2111FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.48333333333333 +lat_2=47.08333333333334 +lat_0=44.78333333333333 +lon_0=-87 +x_0=7999999.999999998 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneMichiganSouthFIPS2113FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.1 +lat_2=43.66666666666666 +lat_0=41.5 +lon_0=-84.36666666666666 +x_0=3999999.999999999 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneMontanaFIPS2500FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45 +lat_2=49 +lat_0=44.25 +lon_0=-109.5 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneNorthDakotaNorthFIPS3301FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=47.43333333333333 +lat_2=48.73333333333333 +lat_0=47 +lon_0=-100.5 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneNorthDakotaSouthFIPS3302FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=46.18333333333333 +lat_2=47.48333333333333 +lat_0=45.66666666666666 +lon_0=-100.5 +x_0=600000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneOregonNorthFIPS3601FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.33333333333334 +lat_2=46 +lat_0=43.66666666666666 +lon_0=-120.5 +x_0=2500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneOregonSouthFIPS3602FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.33333333333334 +lat_2=44 +lat_0=41.66666666666666 +lon_0=-120.5 +x_0=1500000 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneSouthCarolinaFIPS3900FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=32.5 +lat_2=34.83333333333334 +lat_0=31.83333333333333 +lon_0=-81 +x_0=609600 +y_0=0 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneUtahCentralFIPS4302FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=39.01666666666667 +lat_2=40.65 +lat_0=38.33333333333334 +lon_0=-111.5 +x_0=499999.9999999998 +y_0=2000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneUtahNorthFIPS4301FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=40.71666666666667 +lat_2=41.78333333333333 +lat_0=40.33333333333334 +lon_0=-111.5 +x_0=499999.9999999998 +y_0=999999.9999999999 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
          NAD1983StatePlaneUtahSouthFIPS4303FeetIntl = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=37.21666666666667 +lat_2=38.35 +lat_0=36.66666666666666 +lon_0=-111.5 +x_0=499999.9999999998 +y_0=3000000 +ellps=GRS80 +datum=NAD83 +to_meter=0.3048 +no_defs ").orElse(null);
        }

        //</editor-fold>

  /**
   * @return the NAD1983StatePlaneArizonaCentralFIPS0202FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneArizonaCentralFIPS0202FeetIntl() {
    return NAD1983StatePlaneArizonaCentralFIPS0202FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneArizonaEastFIPS0201FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneArizonaEastFIPS0201FeetIntl() {
    return NAD1983StatePlaneArizonaEastFIPS0201FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneArizonaWestFIPS0203FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneArizonaWestFIPS0203FeetIntl() {
    return NAD1983StatePlaneArizonaWestFIPS0203FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneMichiganCentralFIPS2112FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneMichiganCentralFIPS2112FeetIntl() {
    return NAD1983StatePlaneMichiganCentralFIPS2112FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneMichiganNorthFIPS2111FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneMichiganNorthFIPS2111FeetIntl() {
    return NAD1983StatePlaneMichiganNorthFIPS2111FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneMichiganSouthFIPS2113FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneMichiganSouthFIPS2113FeetIntl() {
    return NAD1983StatePlaneMichiganSouthFIPS2113FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneMontanaFIPS2500FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneMontanaFIPS2500FeetIntl() {
    return NAD1983StatePlaneMontanaFIPS2500FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneNorthDakotaNorthFIPS3301FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneNorthDakotaNorthFIPS3301FeetIntl() {
    return NAD1983StatePlaneNorthDakotaNorthFIPS3301FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneNorthDakotaSouthFIPS3302FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneNorthDakotaSouthFIPS3302FeetIntl() {
    return NAD1983StatePlaneNorthDakotaSouthFIPS3302FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneOregonNorthFIPS3601FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneOregonNorthFIPS3601FeetIntl() {
    return NAD1983StatePlaneOregonNorthFIPS3601FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneOregonSouthFIPS3602FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneOregonSouthFIPS3602FeetIntl() {
    return NAD1983StatePlaneOregonSouthFIPS3602FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneSouthCarolinaFIPS3900FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneSouthCarolinaFIPS3900FeetIntl() {
    return NAD1983StatePlaneSouthCarolinaFIPS3900FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneUtahCentralFIPS4302FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneUtahCentralFIPS4302FeetIntl() {
    return NAD1983StatePlaneUtahCentralFIPS4302FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneUtahNorthFIPS4301FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneUtahNorthFIPS4301FeetIntl() {
    return NAD1983StatePlaneUtahNorthFIPS4301FeetIntl.copy();
  }

  /**
   * @return the NAD1983StatePlaneUtahSouthFIPS4303FeetIntl
   */
  public ProjectionInfo getNAD1983StatePlaneUtahSouthFIPS4303FeetIntl() {
    return NAD1983StatePlaneUtahSouthFIPS4303FeetIntl.copy();
  }


    }


