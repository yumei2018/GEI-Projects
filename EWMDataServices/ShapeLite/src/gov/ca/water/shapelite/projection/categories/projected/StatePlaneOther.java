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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 5:04:41 PM
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
     *  StatePlaneOther.
     * @author Harold A. Dunsford Jr. Ph.D.
     */
    public class StatePlaneOther extends CoordinateSystemCategory
    {
        //<editor-fold defaultstate="collapsed" desc="Fields">

      private final ProjectionInfo AmericanSamoa1962StatePlaneAmericanSamoaFIPS5300;
      private final ProjectionInfo NAD1983HARNGuamMapGrid;
      private final ProjectionInfo NAD1983HARNUTMZone2S;
      private final ProjectionInfo NADMichiganStatePlaneMichiganCentralFIPS2112;
      private final ProjectionInfo NADMichiganStatePlaneMichiganCentralOldFIPS2102;
      private final ProjectionInfo NADMichiganStatePlaneMichiganEastOldFIPS2101;
      private final ProjectionInfo NADMichiganStatePlaneMichiganNorthFIPS2111;
      private final ProjectionInfo NADMichiganStatePlaneMichiganSouthFIPS2113;
      private final ProjectionInfo NADMichiganStatePlaneMichiganWestOldFIPS2103;
      private final ProjectionInfo OldHawaiianStatePlaneHawaii1FIPS5101;
      private final ProjectionInfo OldHawaiianStatePlaneHawaii2FIPS5102;
      private final ProjectionInfo OldHawaiianStatePlaneHawaii3FIPS5103;
      private final ProjectionInfo OldHawaiianStatePlaneHawaii4FIPS5104;
      private final ProjectionInfo OldHawaiianStatePlaneHawaii5FIPS5105;
      private final ProjectionInfo PuertoRicoStatePlanePuertoRicoFIPS5201;
      private final ProjectionInfo PuertoRicoStatePlaneVirginIslandsStCroixFIPS5202;

        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Constructor">

        /**
         * Creates a new instance of StatePlaneOther.
         */
        public StatePlaneOther()
        {
            AmericanSamoa1962StatePlaneAmericanSamoaFIPS5300 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=-14.26666666666667 +lat_0=-14.26666666666667 +lon_0=-170 +k_0=1 +x_0=152400.3048006096 +y_0=0 +ellps=clrk66 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NAD1983HARNGuamMapGrid = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=13.5 +lon_0=144.75 +k=1.000000 +x_0=100000 +y_0=200000 +ellps=GRS80 +units=m +no_defs ").orElse(null);
            NAD1983HARNUTMZone2S = ProjectionInfo.fromProj4String("+proj=utm +zone=2 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
            NADMichiganStatePlaneMichiganCentralFIPS2112 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=44.18333333333333 +lat_2=45.7 +lat_0=43.31666666666667 +lon_0=-84.33333333333333 +x_0=609601.2192024385 +y_0=0 +a=6378450.047 +b=6356826.620025999 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NADMichiganStatePlaneMichiganCentralOldFIPS2102 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.5 +lon_0=-85.75 +k=0.999909 +x_0=152400.3048006096 +y_0=0 +a=6378450.047 +b=6356826.620025999 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NADMichiganStatePlaneMichiganEastOldFIPS2101 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.5 +lon_0=-83.66666666666667 +k=0.999943 +x_0=152400.3048006096 +y_0=0 +a=6378450.047 +b=6356826.620025999 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NADMichiganStatePlaneMichiganNorthFIPS2111 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=45.48333333333333 +lat_2=47.08333333333334 +lat_0=44.78333333333333 +lon_0=-87 +x_0=609601.2192024385 +y_0=0 +a=6378450.047 +b=6356826.620025999 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NADMichiganStatePlaneMichiganSouthFIPS2113 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=42.1 +lat_2=43.66666666666666 +lat_0=41.5 +lon_0=-84.33333333333333 +x_0=609601.2192024385 +y_0=0 +a=6378450.047 +b=6356826.620025999 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            NADMichiganStatePlaneMichiganWestOldFIPS2103 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=41.5 +lon_0=-88.75 +k=0.999909 +x_0=152400.3048006096 +y_0=0 +a=6378450.047 +b=6356826.620025999 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            OldHawaiianStatePlaneHawaii1FIPS5101 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=18.83333333333333 +lon_0=-155.5 +k=0.999967 +x_0=152400.3048006096 +y_0=0 +ellps=clrk66 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            OldHawaiianStatePlaneHawaii2FIPS5102 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=20.33333333333333 +lon_0=-156.6666666666667 +k=0.999967 +x_0=152400.3048006096 +y_0=0 +ellps=clrk66 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            OldHawaiianStatePlaneHawaii3FIPS5103 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.16666666666667 +lon_0=-158 +k=0.999990 +x_0=152400.3048006096 +y_0=0 +ellps=clrk66 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            OldHawaiianStatePlaneHawaii4FIPS5104 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.83333333333333 +lon_0=-159.5 +k=0.999990 +x_0=152400.3048006096 +y_0=0 +ellps=clrk66 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            OldHawaiianStatePlaneHawaii5FIPS5105 = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=21.66666666666667 +lon_0=-160.1666666666667 +k=1.000000 +x_0=152400.3048006096 +y_0=0 +ellps=clrk66 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            PuertoRicoStatePlanePuertoRicoFIPS5201 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=18.03333333333333 +lat_2=18.43333333333333 +lat_0=17.83333333333333 +lon_0=-66.43333333333334 +x_0=152400.3048006096 +y_0=0 +ellps=clrk66 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
            PuertoRicoStatePlaneVirginIslandsStCroixFIPS5202 = ProjectionInfo.fromProj4String("+proj=lcc +lat_1=18.03333333333333 +lat_2=18.43333333333333 +lat_0=17.83333333333333 +lon_0=-66.43333333333334 +x_0=152400.3048006096 +y_0=30480.06096012193 +ellps=clrk66 +to_meter=0.3048006096012192 +no_defs ").orElse(null);

            AmericanSamoa1962StatePlaneAmericanSamoaFIPS5300.setName("American_Samoa_1962_StatePlane_American_Samoa_FIPS_5300");
            NAD1983HARNGuamMapGrid.setName("NAD_1983_HARN_Guam_Map_Grid");
            NAD1983HARNUTMZone2S.setName("NAD_1983_HARN_UTM_Zone_2S");
            NADMichiganStatePlaneMichiganCentralFIPS2112.setName("NAD_Michigan_StatePlane_Michigan_Central_FIPS_2112");
            NADMichiganStatePlaneMichiganCentralOldFIPS2102.setName("NAD_Michigan_StatePlane_Michigan_Central_Old_FIPS_2102");
            NADMichiganStatePlaneMichiganEastOldFIPS2101.setName("NAD_Michigan_StatePlane_Michigan_East_Old_FIPS_2101");
            NADMichiganStatePlaneMichiganNorthFIPS2111.setName("NAD_Michigan_StatePlane_Michigan_North_FIPS_2111");
            NADMichiganStatePlaneMichiganSouthFIPS2113.setName("NAD_Michigan_StatePlane_Michigan_South_FIPS_2113");
            NADMichiganStatePlaneMichiganWestOldFIPS2103.setName("NAD_Michigan_StatePlane_Michigan_West_Old_FIPS_2103");
            OldHawaiianStatePlaneHawaii1FIPS5101.setName("Old_Hawaiian_StatePlane_Hawaii_1_FIPS_5101");
            OldHawaiianStatePlaneHawaii2FIPS5102.setName("Old_Hawaiian_StatePlane_Hawaii_2_FIPS_5102");
            OldHawaiianStatePlaneHawaii3FIPS5103.setName("Old_Hawaiian_StatePlane_Hawaii_3_FIPS_5103");
            OldHawaiianStatePlaneHawaii4FIPS5104.setName("Old_Hawaiian_StatePlane_Hawaii_4_FIPS_5104");
            OldHawaiianStatePlaneHawaii5FIPS5105.setName("Old_Hawaiian_StatePlane_Hawaii_5_FIPS_5105");
            PuertoRicoStatePlanePuertoRicoFIPS5201.setName("Puerto_Rico_StatePlane_Puerto_Rico_FIPS_5201");
            PuertoRicoStatePlaneVirginIslandsStCroixFIPS5202.setName("Puerto_Rico_StatePlane_Virgin_Islands_St_Croix_FIPS_5202");

            AmericanSamoa1962StatePlaneAmericanSamoaFIPS5300.getGeographicInfo().setName("GCS_American_Samoa_1962");
            NAD1983HARNGuamMapGrid.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NAD1983HARNUTMZone2S.getGeographicInfo().setName("GCS_North_American_1983_HARN");
            NADMichiganStatePlaneMichiganCentralFIPS2112.getGeographicInfo().setName("GCS_North_American_Michigan");
            NADMichiganStatePlaneMichiganCentralOldFIPS2102.getGeographicInfo().setName("GCS_North_American_Michigan");
            NADMichiganStatePlaneMichiganEastOldFIPS2101.getGeographicInfo().setName("GCS_North_American_Michigan");
            NADMichiganStatePlaneMichiganNorthFIPS2111.getGeographicInfo().setName("GCS_North_American_Michigan");
            NADMichiganStatePlaneMichiganSouthFIPS2113.getGeographicInfo().setName("GCS_North_American_Michigan");
            NADMichiganStatePlaneMichiganWestOldFIPS2103.getGeographicInfo().setName("GCS_North_American_Michigan");
            OldHawaiianStatePlaneHawaii1FIPS5101.getGeographicInfo().setName("GCS_Old_Hawaiian");
            OldHawaiianStatePlaneHawaii2FIPS5102.getGeographicInfo().setName("GCS_Old_Hawaiian");
            OldHawaiianStatePlaneHawaii3FIPS5103.getGeographicInfo().setName("GCS_Old_Hawaiian");
            OldHawaiianStatePlaneHawaii4FIPS5104.getGeographicInfo().setName("GCS_Old_Hawaiian");
            OldHawaiianStatePlaneHawaii5FIPS5105.getGeographicInfo().setName("GCS_Old_Hawaiian");
            PuertoRicoStatePlanePuertoRicoFIPS5201.getGeographicInfo().setName("GCS_Puerto_Rico");
            PuertoRicoStatePlaneVirginIslandsStCroixFIPS5202.getGeographicInfo().setName("GCS_Puerto_Rico");

            AmericanSamoa1962StatePlaneAmericanSamoaFIPS5300.getGeographicInfo().getDatum().setName("D_American_Samoa_1962");
            NAD1983HARNGuamMapGrid.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NAD1983HARNUTMZone2S.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
            NADMichiganStatePlaneMichiganCentralFIPS2112.getGeographicInfo().getDatum().setName("D_North_American_Michigan");
            NADMichiganStatePlaneMichiganCentralOldFIPS2102.getGeographicInfo().getDatum().setName("D_North_American_Michigan");
            NADMichiganStatePlaneMichiganEastOldFIPS2101.getGeographicInfo().getDatum().setName("D_North_American_Michigan");
            NADMichiganStatePlaneMichiganNorthFIPS2111.getGeographicInfo().getDatum().setName("D_North_American_Michigan");
            NADMichiganStatePlaneMichiganSouthFIPS2113.getGeographicInfo().getDatum().setName("D_North_American_Michigan");
            NADMichiganStatePlaneMichiganWestOldFIPS2103.getGeographicInfo().getDatum().setName("D_North_American_Michigan");
            OldHawaiianStatePlaneHawaii1FIPS5101.getGeographicInfo().getDatum().setName("D_Old_Hawaiian");
            OldHawaiianStatePlaneHawaii2FIPS5102.getGeographicInfo().getDatum().setName("D_Old_Hawaiian");
            OldHawaiianStatePlaneHawaii3FIPS5103.getGeographicInfo().getDatum().setName("D_Old_Hawaiian");
            OldHawaiianStatePlaneHawaii4FIPS5104.getGeographicInfo().getDatum().setName("D_Old_Hawaiian");
            OldHawaiianStatePlaneHawaii5FIPS5105.getGeographicInfo().getDatum().setName("D_Old_Hawaiian");
            PuertoRicoStatePlanePuertoRicoFIPS5201.getGeographicInfo().getDatum().setName("D_Puerto_Rico");
            PuertoRicoStatePlaneVirginIslandsStCroixFIPS5202.getGeographicInfo().getDatum().setName("D_Puerto_Rico");
        }

        //</editor-fold>

  /**
   * @return the AmericanSamoa1962StatePlaneAmericanSamoaFIPS5300
   */
  public ProjectionInfo getAmericanSamoa1962StatePlaneAmericanSamoaFIPS5300() {
    return AmericanSamoa1962StatePlaneAmericanSamoaFIPS5300.copy();
  }

  /**
   * @return the NAD1983HARNGuamMapGrid
   */
  public ProjectionInfo getNAD1983HARNGuamMapGrid() {
    return NAD1983HARNGuamMapGrid.copy();
  }

  /**
   * @return the NAD1983HARNUTMZone2S
   */
  public ProjectionInfo getNAD1983HARNUTMZone2S() {
    return NAD1983HARNUTMZone2S.copy();
  }

  /**
   * @return the NADMichiganStatePlaneMichiganCentralFIPS2112
   */
  public ProjectionInfo getNADMichiganStatePlaneMichiganCentralFIPS2112() {
    return NADMichiganStatePlaneMichiganCentralFIPS2112.copy();
  }

  /**
   * @return the NADMichiganStatePlaneMichiganCentralOldFIPS2102
   */
  public ProjectionInfo getNADMichiganStatePlaneMichiganCentralOldFIPS2102() {
    return NADMichiganStatePlaneMichiganCentralOldFIPS2102.copy();
  }

  /**
   * @return the NADMichiganStatePlaneMichiganEastOldFIPS2101
   */
  public ProjectionInfo getNADMichiganStatePlaneMichiganEastOldFIPS2101() {
    return NADMichiganStatePlaneMichiganEastOldFIPS2101.copy();
  }

  /**
   * @return the NADMichiganStatePlaneMichiganNorthFIPS2111
   */
  public ProjectionInfo getNADMichiganStatePlaneMichiganNorthFIPS2111() {
    return NADMichiganStatePlaneMichiganNorthFIPS2111.copy();
  }

  /**
   * @return the NADMichiganStatePlaneMichiganSouthFIPS2113
   */
  public ProjectionInfo getNADMichiganStatePlaneMichiganSouthFIPS2113() {
    return NADMichiganStatePlaneMichiganSouthFIPS2113.copy();
  }

  /**
   * @return the NADMichiganStatePlaneMichiganWestOldFIPS2103
   */
  public ProjectionInfo getNADMichiganStatePlaneMichiganWestOldFIPS2103() {
    return NADMichiganStatePlaneMichiganWestOldFIPS2103.copy();
  }

  /**
   * @return the OldHawaiianStatePlaneHawaii1FIPS5101
   */
  public ProjectionInfo getOldHawaiianStatePlaneHawaii1FIPS5101() {
    return OldHawaiianStatePlaneHawaii1FIPS5101.copy();
  }

  /**
   * @return the OldHawaiianStatePlaneHawaii2FIPS5102
   */
  public ProjectionInfo getOldHawaiianStatePlaneHawaii2FIPS5102() {
    return OldHawaiianStatePlaneHawaii2FIPS5102.copy();
  }

  /**
   * @return the OldHawaiianStatePlaneHawaii3FIPS5103
   */
  public ProjectionInfo getOldHawaiianStatePlaneHawaii3FIPS5103() {
    return OldHawaiianStatePlaneHawaii3FIPS5103.copy();
  }

  /**
   * @return the OldHawaiianStatePlaneHawaii4FIPS5104
   */
  public ProjectionInfo getOldHawaiianStatePlaneHawaii4FIPS5104() {
    return OldHawaiianStatePlaneHawaii4FIPS5104.copy();
  }

  /**
   * @return the OldHawaiianStatePlaneHawaii5FIPS5105
   */
  public ProjectionInfo getOldHawaiianStatePlaneHawaii5FIPS5105() {
    return OldHawaiianStatePlaneHawaii5FIPS5105.copy();
  }

  /**
   * @return the PuertoRicoStatePlanePuertoRicoFIPS5201
   */
  public ProjectionInfo getPuertoRicoStatePlanePuertoRicoFIPS5201() {
    return PuertoRicoStatePlanePuertoRicoFIPS5201.copy();
  }

  /**
   * @return the PuertoRicoStatePlaneVirginIslandsStCroixFIPS5202
   */
  public ProjectionInfo getPuertoRicoStatePlaneVirginIslandsStCroixFIPS5202() {
    return PuertoRicoStatePlaneVirginIslandsStCroixFIPS5202.copy();
  }
    }


