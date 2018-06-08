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
// The Initial Developer of this Original Code is Ted Dunsford. Created 8/14/2009 4:07:32 PM
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
/// CountySystems
/// </summary>

public class CountySystems extends CoordinateSystemCategory {
  //<editor-fold defaultstate="collapsed" desc="Fields">

  private final ProjectionInfo NAD1983HARNAdjMNAnoka;
  private final ProjectionInfo NAD1983HARNAdjMNBecker;
  private final ProjectionInfo NAD1983HARNAdjMNBeltramiNorth;
  private final ProjectionInfo NAD1983HARNAdjMNBeltramiSouth;
  private final ProjectionInfo NAD1983HARNAdjMNBenton;
  private final ProjectionInfo NAD1983HARNAdjMNBigStone;
  private final ProjectionInfo NAD1983HARNAdjMNBlueEarth;
  private final ProjectionInfo NAD1983HARNAdjMNBrown;
  private final ProjectionInfo NAD1983HARNAdjMNCarlton;
  private final ProjectionInfo NAD1983HARNAdjMNCarver;
  private final ProjectionInfo NAD1983HARNAdjMNCassNorth;
  private final ProjectionInfo NAD1983HARNAdjMNCassSouth;
  private final ProjectionInfo NAD1983HARNAdjMNChippewa;
  private final ProjectionInfo NAD1983HARNAdjMNChisago;
  private final ProjectionInfo NAD1983HARNAdjMNCookNorth;
  private final ProjectionInfo NAD1983HARNAdjMNCookSouth;
  private final ProjectionInfo NAD1983HARNAdjMNCottonwood;
  private final ProjectionInfo NAD1983HARNAdjMNCrowWing;
  private final ProjectionInfo NAD1983HARNAdjMNDakota;
  private final ProjectionInfo NAD1983HARNAdjMNDodge;
  private final ProjectionInfo NAD1983HARNAdjMNDouglas;
  private final ProjectionInfo NAD1983HARNAdjMNFaribault;
  private final ProjectionInfo NAD1983HARNAdjMNFillmore;
  private final ProjectionInfo NAD1983HARNAdjMNFreeborn;
  private final ProjectionInfo NAD1983HARNAdjMNGoodhue;
  private final ProjectionInfo NAD1983HARNAdjMNGrant;
  private final ProjectionInfo NAD1983HARNAdjMNHennepin;
  private final ProjectionInfo NAD1983HARNAdjMNHouston;
  private final ProjectionInfo NAD1983HARNAdjMNIsanti;
  private final ProjectionInfo NAD1983HARNAdjMNItascaNorth;
  private final ProjectionInfo NAD1983HARNAdjMNItascaSouth;
  private final ProjectionInfo NAD1983HARNAdjMNJackson;
  private final ProjectionInfo NAD1983HARNAdjMNKanabec;
  private final ProjectionInfo NAD1983HARNAdjMNKandiyohi;
  private final ProjectionInfo NAD1983HARNAdjMNKittson;
  private final ProjectionInfo NAD1983HARNAdjMNKoochiching;
  private final ProjectionInfo NAD1983HARNAdjMNLacQuiParle;
  private final ProjectionInfo NAD1983HARNAdjMNLakeoftheWoodsNorth;
  private final ProjectionInfo NAD1983HARNAdjMNLakeoftheWoodsSouth;
  private final ProjectionInfo NAD1983HARNAdjMNLeSueur;
  private final ProjectionInfo NAD1983HARNAdjMNLincoln;
  private final ProjectionInfo NAD1983HARNAdjMNLyon;
  private final ProjectionInfo NAD1983HARNAdjMNMahnomen;
  private final ProjectionInfo NAD1983HARNAdjMNMarshall;
  private final ProjectionInfo NAD1983HARNAdjMNMartin;
  private final ProjectionInfo NAD1983HARNAdjMNMcLeod;
  private final ProjectionInfo NAD1983HARNAdjMNMeeker;
  private final ProjectionInfo NAD1983HARNAdjMNMorrison;
  private final ProjectionInfo NAD1983HARNAdjMNMower;
  private final ProjectionInfo NAD1983HARNAdjMNMurray;
  private final ProjectionInfo NAD1983HARNAdjMNNicollet;
  private final ProjectionInfo NAD1983HARNAdjMNNobles;
  private final ProjectionInfo NAD1983HARNAdjMNNorman;
  private final ProjectionInfo NAD1983HARNAdjMNOlmsted;
  private final ProjectionInfo NAD1983HARNAdjMNOttertail;
  private final ProjectionInfo NAD1983HARNAdjMNPennington;
  private final ProjectionInfo NAD1983HARNAdjMNPine;
  private final ProjectionInfo NAD1983HARNAdjMNPipestone;
  private final ProjectionInfo NAD1983HARNAdjMNPolk;
  private final ProjectionInfo NAD1983HARNAdjMNPope;
  private final ProjectionInfo NAD1983HARNAdjMNRamsey;
  private final ProjectionInfo NAD1983HARNAdjMNRedLake;
  private final ProjectionInfo NAD1983HARNAdjMNRedwood;
  private final ProjectionInfo NAD1983HARNAdjMNRenville;
  private final ProjectionInfo NAD1983HARNAdjMNRice;
  private final ProjectionInfo NAD1983HARNAdjMNRock;
  private final ProjectionInfo NAD1983HARNAdjMNRoseau;
  private final ProjectionInfo NAD1983HARNAdjMNScott;
  private final ProjectionInfo NAD1983HARNAdjMNSherburne;
  private final ProjectionInfo NAD1983HARNAdjMNSibley;
  private final ProjectionInfo NAD1983HARNAdjMNStLouisCentral;
  private final ProjectionInfo NAD1983HARNAdjMNStLouisNorth;
  private final ProjectionInfo NAD1983HARNAdjMNStLouisSouth;
  private final ProjectionInfo NAD1983HARNAdjMNStearns;
  private final ProjectionInfo NAD1983HARNAdjMNSteele;
  private final ProjectionInfo NAD1983HARNAdjMNStevens;
  private final ProjectionInfo NAD1983HARNAdjMNSwift;
  private final ProjectionInfo NAD1983HARNAdjMNTodd;
  private final ProjectionInfo NAD1983HARNAdjMNTraverse;
  private final ProjectionInfo NAD1983HARNAdjMNWabasha;
  private final ProjectionInfo NAD1983HARNAdjMNWadena;
  private final ProjectionInfo NAD1983HARNAdjMNWaseca;
  private final ProjectionInfo NAD1983HARNAdjMNWatonwan;
  private final ProjectionInfo NAD1983HARNAdjMNWinona;
  private final ProjectionInfo NAD1983HARNAdjMNWright;
  private final ProjectionInfo NAD1983HARNAdjMNYellowMedicine;
  private final ProjectionInfo NAD1983HARNAdjWIAdams;
  private final ProjectionInfo NAD1983HARNAdjWIAshland;
  private final ProjectionInfo NAD1983HARNAdjWIBarron;
  private final ProjectionInfo NAD1983HARNAdjWIBayfield;
  private final ProjectionInfo NAD1983HARNAdjWIBrown;
  private final ProjectionInfo NAD1983HARNAdjWIBuffalo;
  private final ProjectionInfo NAD1983HARNAdjWIBurnett;
  private final ProjectionInfo NAD1983HARNAdjWICalumet;
  private final ProjectionInfo NAD1983HARNAdjWIChippewa;
  private final ProjectionInfo NAD1983HARNAdjWIClark;
  private final ProjectionInfo NAD1983HARNAdjWIColumbia;
  private final ProjectionInfo NAD1983HARNAdjWICrawford;
  private final ProjectionInfo NAD1983HARNAdjWIDane;
  private final ProjectionInfo NAD1983HARNAdjWIDodge;
  private final ProjectionInfo NAD1983HARNAdjWIDoor;
  private final ProjectionInfo NAD1983HARNAdjWIDouglas;
  private final ProjectionInfo NAD1983HARNAdjWIDunn;
  private final ProjectionInfo NAD1983HARNAdjWIEauClaire;
  private final ProjectionInfo NAD1983HARNAdjWIFlorence;
  private final ProjectionInfo NAD1983HARNAdjWIFondduLac;
  private final ProjectionInfo NAD1983HARNAdjWIForest;
  private final ProjectionInfo NAD1983HARNAdjWIGrant;
  private final ProjectionInfo NAD1983HARNAdjWIGreen;
  private final ProjectionInfo NAD1983HARNAdjWIGreenLake;
  private final ProjectionInfo NAD1983HARNAdjWIIowa;
  private final ProjectionInfo NAD1983HARNAdjWIIron;
  private final ProjectionInfo NAD1983HARNAdjWIJackson;
  private final ProjectionInfo NAD1983HARNAdjWIJefferson;
  private final ProjectionInfo NAD1983HARNAdjWIJuneau;
  private final ProjectionInfo NAD1983HARNAdjWIKenosha;
  private final ProjectionInfo NAD1983HARNAdjWIKewaunee;
  private final ProjectionInfo NAD1983HARNAdjWILaCrosse;
  private final ProjectionInfo NAD1983HARNAdjWILafayette;
  private final ProjectionInfo NAD1983HARNAdjWILanglade;
  private final ProjectionInfo NAD1983HARNAdjWILincoln;
  private final ProjectionInfo NAD1983HARNAdjWIManitowoc;
  private final ProjectionInfo NAD1983HARNAdjWIMarathon;
  private final ProjectionInfo NAD1983HARNAdjWIMarinette;
  private final ProjectionInfo NAD1983HARNAdjWIMarquette;
  private final ProjectionInfo NAD1983HARNAdjWIMenominee;
  private final ProjectionInfo NAD1983HARNAdjWIMilwaukee;
  private final ProjectionInfo NAD1983HARNAdjWIMonroe;
  private final ProjectionInfo NAD1983HARNAdjWIOconto;
  private final ProjectionInfo NAD1983HARNAdjWIOneida;
  private final ProjectionInfo NAD1983HARNAdjWIOutagamie;
  private final ProjectionInfo NAD1983HARNAdjWIOzaukee;
  private final ProjectionInfo NAD1983HARNAdjWIPepin;
  private final ProjectionInfo NAD1983HARNAdjWIPierce;
  private final ProjectionInfo NAD1983HARNAdjWIPolk;
  private final ProjectionInfo NAD1983HARNAdjWIPortage;
  private final ProjectionInfo NAD1983HARNAdjWIPrice;
  private final ProjectionInfo NAD1983HARNAdjWIRacine;
  private final ProjectionInfo NAD1983HARNAdjWIRichland;
  private final ProjectionInfo NAD1983HARNAdjWIRock;
  private final ProjectionInfo NAD1983HARNAdjWIRusk;
  private final ProjectionInfo NAD1983HARNAdjWISauk;
  private final ProjectionInfo NAD1983HARNAdjWISawyer;
  private final ProjectionInfo NAD1983HARNAdjWIShawano;
  private final ProjectionInfo NAD1983HARNAdjWISheboygan;
  private final ProjectionInfo NAD1983HARNAdjWIStCroix;
  private final ProjectionInfo NAD1983HARNAdjWITaylor;
  private final ProjectionInfo NAD1983HARNAdjWITrempealeau;
  private final ProjectionInfo NAD1983HARNAdjWIVernon;
  private final ProjectionInfo NAD1983HARNAdjWIVilas;
  private final ProjectionInfo NAD1983HARNAdjWIWalworth;
  private final ProjectionInfo NAD1983HARNAdjWIWashburn;
  private final ProjectionInfo NAD1983HARNAdjWIWashington;
  private final ProjectionInfo NAD1983HARNAdjWIWaukesha;
  private final ProjectionInfo NAD1983HARNAdjWIWaupaca;
  private final ProjectionInfo NAD1983HARNAdjWIWaushara;
  private final ProjectionInfo NAD1983HARNAdjWIWinnebago;
  private final ProjectionInfo NAD1983HARNAdjWIWood;

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Constructor">
        /// <summary>
  /// Creates a new instance of CountySystems
  /// </summary>
  public CountySystems() {
    NAD1983HARNAdjMNAnoka = ProjectionInfo.fromProj4String("+proj=longlat +a=6378418.941 +b=6357033.309845551 +no_defs ").orElse(null);
    NAD1983HARNAdjMNBecker = ProjectionInfo.fromProj4String("+proj=longlat +a=6378586.581 +b=6357200.387780368 +no_defs ").orElse(null);
    NAD1983HARNAdjMNBeltramiNorth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378505.809 +b=6357119.886593593 +no_defs ").orElse(null);
    NAD1983HARNAdjMNBeltramiSouth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378544.823 +b=6357158.769787037 +no_defs ").orElse(null);
    NAD1983HARNAdjMNBenton = ProjectionInfo.fromProj4String("+proj=longlat +a=6378490.569 +b=6357104.697690427 +no_defs ").orElse(null);
    NAD1983HARNAdjMNBigStone = ProjectionInfo.fromProj4String("+proj=longlat +a=6378470.757 +b=6357084.952116313 +no_defs ").orElse(null);
    NAD1983HARNAdjMNBlueEarth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378403.701 +b=6357018.120942386 +no_defs ").orElse(null);
    NAD1983HARNAdjMNBrown = ProjectionInfo.fromProj4String("+proj=longlat +a=6378434.181 +b=6357048.498748716 +no_defs ").orElse(null);
    NAD1983HARNAdjMNCarlton = ProjectionInfo.fromProj4String("+proj=longlat +a=6378454.907 +b=6357069.155258362 +no_defs ").orElse(null);
    NAD1983HARNAdjMNCarver = ProjectionInfo.fromProj4String("+proj=longlat +a=6378400.653 +b=6357015.083161753 +no_defs ").orElse(null);
    NAD1983HARNAdjMNCassNorth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378567.378 +b=6357181.249164391 +no_defs ").orElse(null);
    NAD1983HARNAdjMNCassSouth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378546.957 +b=6357160.89663214 +no_defs ").orElse(null);
    NAD1983HARNAdjMNChippewa = ProjectionInfo.fromProj4String("+proj=longlat +a=6378476.853 +b=6357091.027677579 +no_defs ").orElse(null);
    NAD1983HARNAdjMNChisago = ProjectionInfo.fromProj4String("+proj=longlat +a=6378411.321000001 +b=6357025.715393969 +no_defs ").orElse(null);
    NAD1983HARNAdjMNCookNorth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378647.541 +b=6357261.14339303 +no_defs ").orElse(null);
    NAD1983HARNAdjMNCookSouth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378647.541 +b=6357261.14339303 +no_defs ").orElse(null);
    NAD1983HARNAdjMNCottonwood = ProjectionInfo.fromProj4String("+proj=longlat +a=6378514.953 +b=6357128.999935492 +no_defs ").orElse(null);
    NAD1983HARNAdjMNCrowWing = ProjectionInfo.fromProj4String("+proj=longlat +a=6378546.957 +b=6357160.89663214 +no_defs ").orElse(null);
    NAD1983HARNAdjMNDakota = ProjectionInfo.fromProj4String("+proj=longlat +a=6378421.989 +b=6357036.347626184 +no_defs ").orElse(null);
    NAD1983HARNAdjMNDodge = ProjectionInfo.fromProj4String("+proj=longlat +a=6378481.425 +b=6357095.584348529 +no_defs ").orElse(null);
    NAD1983HARNAdjMNDouglas = ProjectionInfo.fromProj4String("+proj=longlat +a=6378518.001 +b=6357132.037716125 +no_defs ").orElse(null);
    NAD1983HARNAdjMNFaribault = ProjectionInfo.fromProj4String("+proj=longlat +a=6378521.049 +b=6357135.075496757 +no_defs ").orElse(null);
    NAD1983HARNAdjMNFillmore = ProjectionInfo.fromProj4String("+proj=longlat +a=6378464.661 +b=6357078.876555047 +no_defs ").orElse(null);
    NAD1983HARNAdjMNFreeborn = ProjectionInfo.fromProj4String("+proj=longlat +a=6378521.049 +b=6357135.075496757 +no_defs ").orElse(null);
    NAD1983HARNAdjMNGoodhue = ProjectionInfo.fromProj4String("+proj=longlat +a=6378434.181 +b=6357048.498748716 +no_defs ").orElse(null);
    NAD1983HARNAdjMNGrant = ProjectionInfo.fromProj4String("+proj=longlat +a=6378518.001 +b=6357132.037716125 +no_defs ").orElse(null);
    NAD1983HARNAdjMNHennepin = ProjectionInfo.fromProj4String("+proj=longlat +a=6378418.941 +b=6357033.309845551 +no_defs ").orElse(null);
    NAD1983HARNAdjMNHouston = ProjectionInfo.fromProj4String("+proj=longlat +a=6378436.619 +b=6357050.928574564 +no_defs ").orElse(null);
    NAD1983HARNAdjMNIsanti = ProjectionInfo.fromProj4String("+proj=longlat +a=6378411.321000001 +b=6357025.715393969 +no_defs ").orElse(null);
    NAD1983HARNAdjMNItascaNorth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378574.389 +b=6357188.236657837 +no_defs ").orElse(null);
    NAD1983HARNAdjMNItascaSouth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378574.389 +b=6357188.236657837 +no_defs ").orElse(null);
    NAD1983HARNAdjMNJackson = ProjectionInfo.fromProj4String("+proj=longlat +a=6378521.049 +b=6357135.075496757 +no_defs ").orElse(null);
    NAD1983HARNAdjMNKanabec = ProjectionInfo.fromProj4String("+proj=longlat +a=6378472.281 +b=6357086.47100663 +no_defs ").orElse(null);
    NAD1983HARNAdjMNKandiyohi = ProjectionInfo.fromProj4String("+proj=longlat +a=6378498.189 +b=6357112.29214201 +no_defs ").orElse(null);
    NAD1983HARNAdjMNKittson = ProjectionInfo.fromProj4String("+proj=longlat +a=6378449.421 +b=6357063.687651882 +no_defs ").orElse(null);
    NAD1983HARNAdjMNKoochiching = ProjectionInfo.fromProj4String("+proj=longlat +a=6378525.621 +b=6357139.632167708 +no_defs ").orElse(null);
    NAD1983HARNAdjMNLacQuiParle = ProjectionInfo.fromProj4String("+proj=longlat +a=6378476.853 +b=6357091.027677579 +no_defs ").orElse(null);
    NAD1983HARNAdjMNLakeoftheWoodsNorth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378466.185 +b=6357080.395445363 +no_defs ").orElse(null);
    NAD1983HARNAdjMNLakeoftheWoodsSouth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378496.665 +b=6357110.773251694 +no_defs ").orElse(null);
    NAD1983HARNAdjMNLeSueur = ProjectionInfo.fromProj4String("+proj=longlat +a=6378434.181 +b=6357048.498748716 +no_defs ").orElse(null);
    NAD1983HARNAdjMNLincoln = ProjectionInfo.fromProj4String("+proj=longlat +a=6378643.579 +b=6357257.194676865 +no_defs ").orElse(null);
    NAD1983HARNAdjMNLyon = ProjectionInfo.fromProj4String("+proj=longlat +a=6378559.758 +b=6357173.65471281 +no_defs ").orElse(null);
    NAD1983HARNAdjMNMahnomen = ProjectionInfo.fromProj4String("+proj=longlat +a=6378586.581 +b=6357200.387780368 +no_defs ").orElse(null);
    NAD1983HARNAdjMNMarshall = ProjectionInfo.fromProj4String("+proj=longlat +a=6378441.801 +b=6357056.093200299 +no_defs ").orElse(null);
    NAD1983HARNAdjMNMartin = ProjectionInfo.fromProj4String("+proj=longlat +a=6378521.049 +b=6357135.075496757 +no_defs ").orElse(null);
    NAD1983HARNAdjMNMcLeod = ProjectionInfo.fromProj4String("+proj=longlat +a=6378414.369 +b=6357028.753174601 +no_defs ").orElse(null);
    NAD1983HARNAdjMNMeeker = ProjectionInfo.fromProj4String("+proj=longlat +a=6378498.189 +b=6357112.29214201 +no_defs ").orElse(null);
    NAD1983HARNAdjMNMorrison = ProjectionInfo.fromProj4String("+proj=longlat +a=6378502.761 +b=6357116.84881296 +no_defs ").orElse(null);
    NAD1983HARNAdjMNMower = ProjectionInfo.fromProj4String("+proj=longlat +a=6378521.049 +b=6357135.075496757 +no_defs ").orElse(null);
    NAD1983HARNAdjMNMurray = ProjectionInfo.fromProj4String("+proj=longlat +a=6378617.061 +b=6357230.765586698 +no_defs ").orElse(null);
    NAD1983HARNAdjMNNicollet = ProjectionInfo.fromProj4String("+proj=longlat +a=6378403.701 +b=6357018.120942386 +no_defs ").orElse(null);
    NAD1983HARNAdjMNNobles = ProjectionInfo.fromProj4String("+proj=longlat +a=6378624.681 +b=6357238.360038281 +no_defs ").orElse(null);
    NAD1983HARNAdjMNNorman = ProjectionInfo.fromProj4String("+proj=longlat +a=6378468.623 +b=6357082.825271211 +no_defs ").orElse(null);
    NAD1983HARNAdjMNOlmsted = ProjectionInfo.fromProj4String("+proj=longlat +a=6378481.425 +b=6357095.584348529 +no_defs ").orElse(null);
    NAD1983HARNAdjMNOttertail = ProjectionInfo.fromProj4String("+proj=longlat +a=6378525.621 +b=6357139.632167708 +no_defs ").orElse(null);
    NAD1983HARNAdjMNPennington = ProjectionInfo.fromProj4String("+proj=longlat +a=6378445.763 +b=6357060.041916464 +no_defs ").orElse(null);
    NAD1983HARNAdjMNPine = ProjectionInfo.fromProj4String("+proj=longlat +a=6378472.281 +b=6357086.47100663 +no_defs ").orElse(null);
    NAD1983HARNAdjMNPipestone = ProjectionInfo.fromProj4String("+proj=longlat +a=6378670.401 +b=6357283.926747777 +no_defs ").orElse(null);
    NAD1983HARNAdjMNPolk = ProjectionInfo.fromProj4String("+proj=longlat +a=6378445.763 +b=6357060.041916464 +no_defs ").orElse(null);
    NAD1983HARNAdjMNPope = ProjectionInfo.fromProj4String("+proj=longlat +a=6378502.761 +b=6357116.84881296 +no_defs ").orElse(null);
    NAD1983HARNAdjMNRamsey = ProjectionInfo.fromProj4String("+proj=longlat +a=6378418.941 +b=6357033.309845551 +no_defs ").orElse(null);
    NAD1983HARNAdjMNRedLake = ProjectionInfo.fromProj4String("+proj=longlat +a=6378445.763 +b=6357060.041916464 +no_defs ").orElse(null);
    NAD1983HARNAdjMNRedwood = ProjectionInfo.fromProj4String("+proj=longlat +a=6378438.753 +b=6357053.055419666 +no_defs ").orElse(null);
    NAD1983HARNAdjMNRenville = ProjectionInfo.fromProj4String("+proj=longlat +a=6378414.369 +b=6357028.753174601 +no_defs ").orElse(null);
    NAD1983HARNAdjMNRice = ProjectionInfo.fromProj4String("+proj=longlat +a=6378434.181 +b=6357048.498748716 +no_defs ").orElse(null);
    NAD1983HARNAdjMNRock = ProjectionInfo.fromProj4String("+proj=longlat +a=6378624.681 +b=6357238.360038281 +no_defs ").orElse(null);
    NAD1983HARNAdjMNRoseau = ProjectionInfo.fromProj4String("+proj=longlat +a=6378449.421 +b=6357063.687651882 +no_defs ").orElse(null);
    NAD1983HARNAdjMNScott = ProjectionInfo.fromProj4String("+proj=longlat +a=6378421.989 +b=6357036.347626184 +no_defs ").orElse(null);
    NAD1983HARNAdjMNSherburne = ProjectionInfo.fromProj4String("+proj=longlat +a=6378443.325 +b=6357057.612090616 +no_defs ").orElse(null);
    NAD1983HARNAdjMNSibley = ProjectionInfo.fromProj4String("+proj=longlat +a=6378414.369 +b=6357028.753174601 +no_defs ").orElse(null);
    NAD1983HARNAdjMNStLouisCentral = ProjectionInfo.fromProj4String("+proj=longlat +a=6378605.783 +b=6357219.525399698 +no_defs ").orElse(null);
    NAD1983HARNAdjMNStLouisNorth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378543.909 +b=6357157.858851505 +no_defs ").orElse(null);
    NAD1983HARNAdjMNStLouisSouth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378540.861 +b=6357154.821070872 +no_defs ").orElse(null);
    NAD1983HARNAdjMNStearns = ProjectionInfo.fromProj4String("+proj=longlat +a=6378502.761 +b=6357116.84881296 +no_defs ").orElse(null);
    NAD1983HARNAdjMNSteele = ProjectionInfo.fromProj4String("+proj=longlat +a=6378481.425 +b=6357095.584348529 +no_defs ").orElse(null);
    NAD1983HARNAdjMNStevens = ProjectionInfo.fromProj4String("+proj=longlat +a=6378502.761 +b=6357116.84881296 +no_defs ").orElse(null);
    NAD1983HARNAdjMNSwift = ProjectionInfo.fromProj4String("+proj=longlat +a=6378470.757 +b=6357084.952116313 +no_defs ").orElse(null);
    NAD1983HARNAdjMNTodd = ProjectionInfo.fromProj4String("+proj=longlat +a=6378548.481 +b=6357162.415522455 +no_defs ").orElse(null);
    NAD1983HARNAdjMNTraverse = ProjectionInfo.fromProj4String("+proj=longlat +a=6378463.746 +b=6357077.964622869 +no_defs ").orElse(null);
    NAD1983HARNAdjMNWabasha = ProjectionInfo.fromProj4String("+proj=longlat +a=6378426.561 +b=6357040.904297134 +no_defs ").orElse(null);
    NAD1983HARNAdjMNWadena = ProjectionInfo.fromProj4String("+proj=longlat +a=6378546.957 +b=6357160.89663214 +no_defs ").orElse(null);
    NAD1983HARNAdjMNWaseca = ProjectionInfo.fromProj4String("+proj=longlat +a=6378481.425 +b=6357095.584348529 +no_defs ").orElse(null);
    NAD1983HARNAdjMNWatonwan = ProjectionInfo.fromProj4String("+proj=longlat +a=6378514.953 +b=6357128.999935492 +no_defs ").orElse(null);
    NAD1983HARNAdjMNWinona = ProjectionInfo.fromProj4String("+proj=longlat +a=6378453.688 +b=6357067.940345438 +no_defs ").orElse(null);
    NAD1983HARNAdjMNWright = ProjectionInfo.fromProj4String("+proj=longlat +a=6378443.325 +b=6357057.612090616 +no_defs ").orElse(null);
    NAD1983HARNAdjMNYellowMedicine = ProjectionInfo.fromProj4String("+proj=longlat +a=6378530.193 +b=6357144.188838657 +no_defs ").orElse(null);
    NAD1983HARNAdjWIAdams = ProjectionInfo.fromProj4String("+proj=longlat +a=6378376.271 +b=6356991.5851403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIAshland = ProjectionInfo.fromProj4String("+proj=longlat +a=6378471.92 +b=6357087.2341403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIBarron = ProjectionInfo.fromProj4String("+proj=longlat +a=6378472.931 +b=6357088.2451403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIBayfield = ProjectionInfo.fromProj4String("+proj=longlat +a=6378411.351 +b=6357026.6651403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIBrown = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);
    NAD1983HARNAdjWIBuffalo = ProjectionInfo.fromProj4String("+proj=longlat +a=6378380.991 +b=6356996.305140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIBurnett = ProjectionInfo.fromProj4String("+proj=longlat +a=6378414.96 +b=6357030.2741403 +no_defs ").orElse(null);
    NAD1983HARNAdjWICalumet = ProjectionInfo.fromProj4String("+proj=longlat +a=6378345.09 +b=6356960.4041403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIChippewa = ProjectionInfo.fromProj4String("+proj=longlat +a=6378412.542 +b=6357027.856140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIClark = ProjectionInfo.fromProj4String("+proj=longlat +a=6378470.401 +b=6357085.7151403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIColumbia = ProjectionInfo.fromProj4String("+proj=longlat +a=6378376.331 +b=6356991.645140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWICrawford = ProjectionInfo.fromProj4String("+proj=longlat +a=6378379.031 +b=6356994.345140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIDane = ProjectionInfo.fromProj4String("+proj=longlat +a=6378407.621 +b=6357022.935140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIDodge = ProjectionInfo.fromProj4String("+proj=longlat +a=6378376.811 +b=6356992.1251403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIDoor = ProjectionInfo.fromProj4String("+proj=longlat +a=6378313.92 +b=6356929.2341403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIDouglas = ProjectionInfo.fromProj4String("+proj=longlat +a=6378414.93 +b=6357030.2441403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIDunn = ProjectionInfo.fromProj4String("+proj=longlat +a=6378413.021 +b=6357028.3351403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIEauClaire = ProjectionInfo.fromProj4String("+proj=longlat +a=6378380.381 +b=6356995.6951403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIFlorence = ProjectionInfo.fromProj4String("+proj=longlat +a=6378530.851 +b=6357146.1651403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIFondduLac = ProjectionInfo.fromProj4String("+proj=longlat +a=6378345.09 +b=6356960.4041403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIForest = ProjectionInfo.fromProj4String("+proj=longlat +a=6378591.521 +b=6357206.8351403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIGrant = ProjectionInfo.fromProj4String("+proj=longlat +a=6378378.881 +b=6356994.1951403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIGreen = ProjectionInfo.fromProj4String("+proj=longlat +a=6378408.481 +b=6357023.7951403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIGreenLake = ProjectionInfo.fromProj4String("+proj=longlat +a=6378375.601 +b=6356990.9151403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIIowa = ProjectionInfo.fromProj4String("+proj=longlat +a=6378408.041 +b=6357023.355140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIIron = ProjectionInfo.fromProj4String("+proj=longlat +a=6378655.071000001 +b=6357270.385140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIJackson = ProjectionInfo.fromProj4String("+proj=longlat +a=6378409.151 +b=6357024.4651403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIJefferson = ProjectionInfo.fromProj4String("+proj=longlat +a=6378376.811 +b=6356992.1251403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIJuneau = ProjectionInfo.fromProj4String("+proj=longlat +a=6378376.271 +b=6356991.5851403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIKenosha = ProjectionInfo.fromProj4String("+proj=longlat +a=6378315.7 +b=6356931.014140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIKewaunee = ProjectionInfo.fromProj4String("+proj=longlat +a=6378285.86 +b=6356901.174140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWILaCrosse = ProjectionInfo.fromProj4String("+proj=longlat +a=6378379.301 +b=6356994.6151403 +no_defs ").orElse(null);
    NAD1983HARNAdjWILafayette = ProjectionInfo.fromProj4String("+proj=longlat +a=6378408.481 +b=6357023.7951403 +no_defs ").orElse(null);
    NAD1983HARNAdjWILanglade = ProjectionInfo.fromProj4String("+proj=longlat +a=6378560.121 +b=6357175.435140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWILincoln = ProjectionInfo.fromProj4String("+proj=longlat +a=6378531.821000001 +b=6357147.135140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIManitowoc = ProjectionInfo.fromProj4String("+proj=longlat +a=6378285.86 +b=6356901.174140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIMarathon = ProjectionInfo.fromProj4String("+proj=longlat +a=6378500.6 +b=6357115.9141403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIMarinette = ProjectionInfo.fromProj4String("+proj=longlat +a=6378376.041 +b=6356991.355140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIMarquette = ProjectionInfo.fromProj4String("+proj=longlat +a=6378375.601 +b=6356990.9151403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIMenominee = ProjectionInfo.fromProj4String("+proj=longlat +a=6378406.601 +b=6357021.9151403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIMilwaukee = ProjectionInfo.fromProj4String("+proj=longlat +a=6378315.7 +b=6356931.014140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIMonroe = ProjectionInfo.fromProj4String("+proj=longlat +a=6378438.991 +b=6357054.305140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIOconto = ProjectionInfo.fromProj4String("+proj=longlat +a=6378345.42 +b=6356960.7341403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIOneida = ProjectionInfo.fromProj4String("+proj=longlat +a=6378593.86 +b=6357209.174140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIOutagamie = ProjectionInfo.fromProj4String("+proj=longlat +a=6378345.09 +b=6356960.4041403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIOzaukee = ProjectionInfo.fromProj4String("+proj=longlat +a=6378315.7 +b=6356931.014140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIPepin = ProjectionInfo.fromProj4String("+proj=longlat +a=6378381.271 +b=6356996.5851403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIPierce = ProjectionInfo.fromProj4String("+proj=longlat +a=6378381.271 +b=6356996.5851403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIPolk = ProjectionInfo.fromProj4String("+proj=longlat +a=6378413.671 +b=6357028.9851403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIPortage = ProjectionInfo.fromProj4String("+proj=longlat +a=6378344.377 +b=6356959.691139228 +no_defs ").orElse(null);
    NAD1983HARNAdjWIPrice = ProjectionInfo.fromProj4String("+proj=longlat +a=6378563.891 +b=6357179.2051403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIRacine = ProjectionInfo.fromProj4String("+proj=longlat +a=6378315.7 +b=6356931.014140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIRichland = ProjectionInfo.fromProj4String("+proj=longlat +a=6378408.091 +b=6357023.4051403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIRock = ProjectionInfo.fromProj4String("+proj=longlat +a=6378377.671 +b=6356992.9851403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIRusk = ProjectionInfo.fromProj4String("+proj=longlat +a=6378472.751 +b=6357088.0651403 +no_defs ").orElse(null);
    NAD1983HARNAdjWISauk = ProjectionInfo.fromProj4String("+proj=longlat +a=6378407.281 +b=6357022.595140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWISawyer = ProjectionInfo.fromProj4String("+proj=longlat +a=6378534.451 +b=6357149.765140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIShawano = ProjectionInfo.fromProj4String("+proj=longlat +a=6378406.051 +b=6357021.3651403 +no_defs ").orElse(null);
    NAD1983HARNAdjWISheboygan = ProjectionInfo.fromProj4String("+proj=longlat +a=6378285.86 +b=6356901.174140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIStCroix = ProjectionInfo.fromProj4String("+proj=longlat +a=6378412.511 +b=6357027.8251403 +no_defs ").orElse(null);
    NAD1983HARNAdjWITaylor = ProjectionInfo.fromProj4String("+proj=longlat +a=6378532.921 +b=6357148.2351403 +no_defs ").orElse(null);
    NAD1983HARNAdjWITrempealeau = ProjectionInfo.fromProj4String("+proj=longlat +a=6378380.091 +b=6356995.4051403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIVernon = ProjectionInfo.fromProj4String("+proj=longlat +a=6378408.941 +b=6357024.2551403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIVilas = ProjectionInfo.fromProj4String("+proj=longlat +a=6378624.171 +b=6357239.4851403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIWalworth = ProjectionInfo.fromProj4String("+proj=longlat +a=6378377.411 +b=6356992.725140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIWashburn = ProjectionInfo.fromProj4String("+proj=longlat +a=6378474.591 +b=6357089.9051403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIWashington = ProjectionInfo.fromProj4String("+proj=longlat +a=6378407.141 +b=6357022.4551403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIWaukesha = ProjectionInfo.fromProj4String("+proj=longlat +a=6378376.871 +b=6356992.185140301 +no_defs ").orElse(null);
    NAD1983HARNAdjWIWaupaca = ProjectionInfo.fromProj4String("+proj=longlat +a=6378375.251 +b=6356990.5651403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIWaushara = ProjectionInfo.fromProj4String("+proj=longlat +a=6378405.971 +b=6357021.2851403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIWinnebago = ProjectionInfo.fromProj4String("+proj=longlat +a=6378345.09 +b=6356960.4041403 +no_defs ").orElse(null);
    NAD1983HARNAdjWIWood = ProjectionInfo.fromProj4String("+proj=longlat +a=6378437.651 +b=6357052.9651403 +no_defs ").orElse(null);

    NAD1983HARNAdjMNAnoka.setLatLon(true);
    NAD1983HARNAdjMNBecker.setLatLon(true);
    NAD1983HARNAdjMNBeltramiNorth.setLatLon(true);
    NAD1983HARNAdjMNBeltramiSouth.setLatLon(true);
    NAD1983HARNAdjMNBenton.setLatLon(true);
    NAD1983HARNAdjMNBigStone.setLatLon(true);
    NAD1983HARNAdjMNBlueEarth.setLatLon(true);
    NAD1983HARNAdjMNBrown.setLatLon(true);
    NAD1983HARNAdjMNCarlton.setLatLon(true);
    NAD1983HARNAdjMNCarver.setLatLon(true);
    NAD1983HARNAdjMNCassNorth.setLatLon(true);
    NAD1983HARNAdjMNCassSouth.setLatLon(true);
    NAD1983HARNAdjMNChippewa.setLatLon(true);
    NAD1983HARNAdjMNChisago.setLatLon(true);
    NAD1983HARNAdjMNCookNorth.setLatLon(true);
    NAD1983HARNAdjMNCookSouth.setLatLon(true);
    NAD1983HARNAdjMNCottonwood.setLatLon(true);
    NAD1983HARNAdjMNCrowWing.setLatLon(true);
    NAD1983HARNAdjMNDakota.setLatLon(true);
    NAD1983HARNAdjMNDodge.setLatLon(true);
    NAD1983HARNAdjMNDouglas.setLatLon(true);
    NAD1983HARNAdjMNFaribault.setLatLon(true);
    NAD1983HARNAdjMNFillmore.setLatLon(true);
    NAD1983HARNAdjMNFreeborn.setLatLon(true);
    NAD1983HARNAdjMNGoodhue.setLatLon(true);
    NAD1983HARNAdjMNGrant.setLatLon(true);
    NAD1983HARNAdjMNHennepin.setLatLon(true);
    NAD1983HARNAdjMNHouston.setLatLon(true);
    NAD1983HARNAdjMNIsanti.setLatLon(true);
    NAD1983HARNAdjMNItascaNorth.setLatLon(true);
    NAD1983HARNAdjMNItascaSouth.setLatLon(true);
    NAD1983HARNAdjMNJackson.setLatLon(true);
    NAD1983HARNAdjMNKanabec.setLatLon(true);
    NAD1983HARNAdjMNKandiyohi.setLatLon(true);
    NAD1983HARNAdjMNKittson.setLatLon(true);
    NAD1983HARNAdjMNKoochiching.setLatLon(true);
    NAD1983HARNAdjMNLacQuiParle.setLatLon(true);
    NAD1983HARNAdjMNLakeoftheWoodsNorth.setLatLon(true);
    NAD1983HARNAdjMNLakeoftheWoodsSouth.setLatLon(true);
    NAD1983HARNAdjMNLeSueur.setLatLon(true);
    NAD1983HARNAdjMNLincoln.setLatLon(true);
    NAD1983HARNAdjMNLyon.setLatLon(true);
    NAD1983HARNAdjMNMahnomen.setLatLon(true);
    NAD1983HARNAdjMNMarshall.setLatLon(true);
    NAD1983HARNAdjMNMartin.setLatLon(true);
    NAD1983HARNAdjMNMcLeod.setLatLon(true);
    NAD1983HARNAdjMNMeeker.setLatLon(true);
    NAD1983HARNAdjMNMorrison.setLatLon(true);
    NAD1983HARNAdjMNMower.setLatLon(true);
    NAD1983HARNAdjMNMurray.setLatLon(true);
    NAD1983HARNAdjMNNicollet.setLatLon(true);
    NAD1983HARNAdjMNNobles.setLatLon(true);
    NAD1983HARNAdjMNNorman.setLatLon(true);
    NAD1983HARNAdjMNOlmsted.setLatLon(true);
    NAD1983HARNAdjMNOttertail.setLatLon(true);
    NAD1983HARNAdjMNPennington.setLatLon(true);
    NAD1983HARNAdjMNPine.setLatLon(true);
    NAD1983HARNAdjMNPipestone.setLatLon(true);
    NAD1983HARNAdjMNPolk.setLatLon(true);
    NAD1983HARNAdjMNPope.setLatLon(true);
    NAD1983HARNAdjMNRamsey.setLatLon(true);
    NAD1983HARNAdjMNRedLake.setLatLon(true);
    NAD1983HARNAdjMNRedwood.setLatLon(true);
    NAD1983HARNAdjMNRenville.setLatLon(true);
    NAD1983HARNAdjMNRice.setLatLon(true);
    NAD1983HARNAdjMNRock.setLatLon(true);
    NAD1983HARNAdjMNRoseau.setLatLon(true);
    NAD1983HARNAdjMNScott.setLatLon(true);
    NAD1983HARNAdjMNSherburne.setLatLon(true);
    NAD1983HARNAdjMNSibley.setLatLon(true);
    NAD1983HARNAdjMNStLouisCentral.setLatLon(true);
    NAD1983HARNAdjMNStLouisNorth.setLatLon(true);
    NAD1983HARNAdjMNStLouisSouth.setLatLon(true);
    NAD1983HARNAdjMNStearns.setLatLon(true);
    NAD1983HARNAdjMNSteele.setLatLon(true);
    NAD1983HARNAdjMNStevens.setLatLon(true);
    NAD1983HARNAdjMNSwift.setLatLon(true);
    NAD1983HARNAdjMNTodd.setLatLon(true);
    NAD1983HARNAdjMNTraverse.setLatLon(true);
    NAD1983HARNAdjMNWabasha.setLatLon(true);
    NAD1983HARNAdjMNWadena.setLatLon(true);
    NAD1983HARNAdjMNWaseca.setLatLon(true);
    NAD1983HARNAdjMNWatonwan.setLatLon(true);
    NAD1983HARNAdjMNWinona.setLatLon(true);
    NAD1983HARNAdjMNWright.setLatLon(true);
    NAD1983HARNAdjMNYellowMedicine.setLatLon(true);
    NAD1983HARNAdjWIAdams.setLatLon(true);
    NAD1983HARNAdjWIAshland.setLatLon(true);
    NAD1983HARNAdjWIBarron.setLatLon(true);
    NAD1983HARNAdjWIBayfield.setLatLon(true);
    NAD1983HARNAdjWIBrown.setLatLon(true);
    NAD1983HARNAdjWIBuffalo.setLatLon(true);
    NAD1983HARNAdjWIBurnett.setLatLon(true);
    NAD1983HARNAdjWICalumet.setLatLon(true);
    NAD1983HARNAdjWIChippewa.setLatLon(true);
    NAD1983HARNAdjWIClark.setLatLon(true);
    NAD1983HARNAdjWIColumbia.setLatLon(true);
    NAD1983HARNAdjWICrawford.setLatLon(true);
    NAD1983HARNAdjWIDane.setLatLon(true);
    NAD1983HARNAdjWIDodge.setLatLon(true);
    NAD1983HARNAdjWIDoor.setLatLon(true);
    NAD1983HARNAdjWIDouglas.setLatLon(true);
    NAD1983HARNAdjWIDunn.setLatLon(true);
    NAD1983HARNAdjWIEauClaire.setLatLon(true);
    NAD1983HARNAdjWIFlorence.setLatLon(true);
    NAD1983HARNAdjWIFondduLac.setLatLon(true);
    NAD1983HARNAdjWIForest.setLatLon(true);
    NAD1983HARNAdjWIGrant.setLatLon(true);
    NAD1983HARNAdjWIGreen.setLatLon(true);
    NAD1983HARNAdjWIGreenLake.setLatLon(true);
    NAD1983HARNAdjWIIowa.setLatLon(true);
    NAD1983HARNAdjWIIron.setLatLon(true);
    NAD1983HARNAdjWIJackson.setLatLon(true);
    NAD1983HARNAdjWIJefferson.setLatLon(true);
    NAD1983HARNAdjWIJuneau.setLatLon(true);
    NAD1983HARNAdjWIKenosha.setLatLon(true);
    NAD1983HARNAdjWIKewaunee.setLatLon(true);
    NAD1983HARNAdjWILaCrosse.setLatLon(true);
    NAD1983HARNAdjWILafayette.setLatLon(true);
    NAD1983HARNAdjWILanglade.setLatLon(true);
    NAD1983HARNAdjWILincoln.setLatLon(true);
    NAD1983HARNAdjWIManitowoc.setLatLon(true);
    NAD1983HARNAdjWIMarathon.setLatLon(true);
    NAD1983HARNAdjWIMarinette.setLatLon(true);
    NAD1983HARNAdjWIMarquette.setLatLon(true);
    NAD1983HARNAdjWIMenominee.setLatLon(true);
    NAD1983HARNAdjWIMilwaukee.setLatLon(true);
    NAD1983HARNAdjWIMonroe.setLatLon(true);
    NAD1983HARNAdjWIOconto.setLatLon(true);
    NAD1983HARNAdjWIOneida.setLatLon(true);
    NAD1983HARNAdjWIOutagamie.setLatLon(true);
    NAD1983HARNAdjWIOzaukee.setLatLon(true);
    NAD1983HARNAdjWIPepin.setLatLon(true);
    NAD1983HARNAdjWIPierce.setLatLon(true);
    NAD1983HARNAdjWIPolk.setLatLon(true);
    NAD1983HARNAdjWIPortage.setLatLon(true);
    NAD1983HARNAdjWIPrice.setLatLon(true);
    NAD1983HARNAdjWIRacine.setLatLon(true);
    NAD1983HARNAdjWIRichland.setLatLon(true);
    NAD1983HARNAdjWIRock.setLatLon(true);
    NAD1983HARNAdjWIRusk.setLatLon(true);
    NAD1983HARNAdjWISauk.setLatLon(true);
    NAD1983HARNAdjWISawyer.setLatLon(true);
    NAD1983HARNAdjWIShawano.setLatLon(true);
    NAD1983HARNAdjWISheboygan.setLatLon(true);
    NAD1983HARNAdjWIStCroix.setLatLon(true);
    NAD1983HARNAdjWITaylor.setLatLon(true);
    NAD1983HARNAdjWITrempealeau.setLatLon(true);
    NAD1983HARNAdjWIVernon.setLatLon(true);
    NAD1983HARNAdjWIVilas.setLatLon(true);
    NAD1983HARNAdjWIWalworth.setLatLon(true);
    NAD1983HARNAdjWIWashburn.setLatLon(true);
    NAD1983HARNAdjWIWashington.setLatLon(true);
    NAD1983HARNAdjWIWaukesha.setLatLon(true);
    NAD1983HARNAdjWIWaupaca.setLatLon(true);
    NAD1983HARNAdjWIWaushara.setLatLon(true);
    NAD1983HARNAdjWIWinnebago.setLatLon(true);
    NAD1983HARNAdjWIWood.setLatLon(true);

    NAD1983HARNAdjMNAnoka.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Anoka");
    NAD1983HARNAdjMNBecker.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Becker");
    NAD1983HARNAdjMNBeltramiNorth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Beltrami_North");
    NAD1983HARNAdjMNBeltramiSouth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Beltrami_South");
    NAD1983HARNAdjMNBenton.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Benton");
    NAD1983HARNAdjMNBigStone.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Big_Stone");
    NAD1983HARNAdjMNBlueEarth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Blue_Earth");
    NAD1983HARNAdjMNBrown.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Brown");
    NAD1983HARNAdjMNCarlton.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Carlton");
    NAD1983HARNAdjMNCarver.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Carver");
    NAD1983HARNAdjMNCassNorth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cass_North");
    NAD1983HARNAdjMNCassSouth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cass_South");
    NAD1983HARNAdjMNChippewa.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Chippewa");
    NAD1983HARNAdjMNChisago.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Chisago");
    NAD1983HARNAdjMNCookNorth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cook_North");
    NAD1983HARNAdjMNCookSouth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cook_South");
    NAD1983HARNAdjMNCottonwood.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Cottonwood");
    NAD1983HARNAdjMNCrowWing.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Crow_Wing");
    NAD1983HARNAdjMNDakota.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Dakota");
    NAD1983HARNAdjMNDodge.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Dodge");
    NAD1983HARNAdjMNDouglas.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Douglas");
    NAD1983HARNAdjMNFaribault.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Faribault");
    NAD1983HARNAdjMNFillmore.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Fillmore");
    NAD1983HARNAdjMNFreeborn.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Freeborn");
    NAD1983HARNAdjMNGoodhue.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Goodhue");
    NAD1983HARNAdjMNGrant.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Grant");
    NAD1983HARNAdjMNHennepin.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Hennepin");
    NAD1983HARNAdjMNHouston.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Houston");
    NAD1983HARNAdjMNIsanti.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Isanti");
    NAD1983HARNAdjMNItascaNorth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Itasca_North");
    NAD1983HARNAdjMNItascaSouth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Itasca_South");
    NAD1983HARNAdjMNJackson.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Jackson");
    NAD1983HARNAdjMNKanabec.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Kanabec");
    NAD1983HARNAdjMNKandiyohi.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Kandiyohi");
    NAD1983HARNAdjMNKittson.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Kittson");
    NAD1983HARNAdjMNKoochiching.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Koochiching");
    NAD1983HARNAdjMNLacQuiParle.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lac_Qui_Parle");
    NAD1983HARNAdjMNLakeoftheWoodsNorth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_North");
    NAD1983HARNAdjMNLakeoftheWoodsSouth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_South");
    NAD1983HARNAdjMNLeSueur.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Le_Sueur");
    NAD1983HARNAdjMNLincoln.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lincoln");
    NAD1983HARNAdjMNLyon.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Lyon");
    NAD1983HARNAdjMNMahnomen.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Mahnomen");
    NAD1983HARNAdjMNMarshall.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Marshall");
    NAD1983HARNAdjMNMartin.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Martin");
    NAD1983HARNAdjMNMcLeod.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_McLeod");
    NAD1983HARNAdjMNMeeker.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Meeker");
    NAD1983HARNAdjMNMorrison.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Morrison");
    NAD1983HARNAdjMNMower.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Mower");
    NAD1983HARNAdjMNMurray.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Murray");
    NAD1983HARNAdjMNNicollet.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Nicollet");
    NAD1983HARNAdjMNNobles.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Nobles");
    NAD1983HARNAdjMNNorman.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Norman");
    NAD1983HARNAdjMNOlmsted.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Olmsted");
    NAD1983HARNAdjMNOttertail.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Ottertail");
    NAD1983HARNAdjMNPennington.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pennington");
    NAD1983HARNAdjMNPine.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pine");
    NAD1983HARNAdjMNPipestone.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pipestone");
    NAD1983HARNAdjMNPolk.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Polk");
    NAD1983HARNAdjMNPope.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Pope");
    NAD1983HARNAdjMNRamsey.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Ramsey");
    NAD1983HARNAdjMNRedLake.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Red_Lake");
    NAD1983HARNAdjMNRedwood.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Redwood");
    NAD1983HARNAdjMNRenville.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Renville");
    NAD1983HARNAdjMNRice.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Rice");
    NAD1983HARNAdjMNRock.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Rock");
    NAD1983HARNAdjMNRoseau.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Roseau");
    NAD1983HARNAdjMNScott.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Scott");
    NAD1983HARNAdjMNSherburne.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Sherburne");
    NAD1983HARNAdjMNSibley.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Sibley");
    NAD1983HARNAdjMNStLouisCentral.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_Central");
    NAD1983HARNAdjMNStLouisNorth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_North");
    NAD1983HARNAdjMNStLouisSouth.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_South");
    NAD1983HARNAdjMNStearns.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Stearns");
    NAD1983HARNAdjMNSteele.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Steele");
    NAD1983HARNAdjMNStevens.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Stevens");
    NAD1983HARNAdjMNSwift.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Swift");
    NAD1983HARNAdjMNTodd.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Todd");
    NAD1983HARNAdjMNTraverse.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Traverse");
    NAD1983HARNAdjMNWabasha.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Wabasha");
    NAD1983HARNAdjMNWadena.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Wadena");
    NAD1983HARNAdjMNWaseca.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Waseca");
    NAD1983HARNAdjMNWatonwan.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Watonwan");
    NAD1983HARNAdjMNWinona.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Winona");
    NAD1983HARNAdjMNWright.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Wright");
    NAD1983HARNAdjMNYellowMedicine.getGeographicInfo().setName("GCS_NAD_1983_HARN_Adj_MN_Yellow_Medicine");

    NAD1983HARNAdjMNAnoka.setName("GCS_NAD_1983_HARN_Adj_MN_Anoka");
    NAD1983HARNAdjMNBecker.setName("GCS_NAD_1983_HARN_Adj_MN_Becker");
    NAD1983HARNAdjMNBeltramiNorth.setName("GCS_NAD_1983_HARN_Adj_MN_Beltrami_North");
    NAD1983HARNAdjMNBeltramiSouth.setName("GCS_NAD_1983_HARN_Adj_MN_Beltrami_South");
    NAD1983HARNAdjMNBenton.setName("GCS_NAD_1983_HARN_Adj_MN_Benton");
    NAD1983HARNAdjMNBigStone.setName("GCS_NAD_1983_HARN_Adj_MN_Big_Stone");
    NAD1983HARNAdjMNBlueEarth.setName("GCS_NAD_1983_HARN_Adj_MN_Blue_Earth");
    NAD1983HARNAdjMNBrown.setName("GCS_NAD_1983_HARN_Adj_MN_Brown");
    NAD1983HARNAdjMNCarlton.setName("GCS_NAD_1983_HARN_Adj_MN_Carlton");
    NAD1983HARNAdjMNCarver.setName("GCS_NAD_1983_HARN_Adj_MN_Carver");
    NAD1983HARNAdjMNCassNorth.setName("GCS_NAD_1983_HARN_Adj_MN_Cass_North");
    NAD1983HARNAdjMNCassSouth.setName("GCS_NAD_1983_HARN_Adj_MN_Cass_South");
    NAD1983HARNAdjMNChippewa.setName("GCS_NAD_1983_HARN_Adj_MN_Chippewa");
    NAD1983HARNAdjMNChisago.setName("GCS_NAD_1983_HARN_Adj_MN_Chisago");
    NAD1983HARNAdjMNCookNorth.setName("GCS_NAD_1983_HARN_Adj_MN_Cook_North");
    NAD1983HARNAdjMNCookSouth.setName("GCS_NAD_1983_HARN_Adj_MN_Cook_South");
    NAD1983HARNAdjMNCottonwood.setName("GCS_NAD_1983_HARN_Adj_MN_Cottonwood");
    NAD1983HARNAdjMNCrowWing.setName("GCS_NAD_1983_HARN_Adj_MN_Crow_Wing");
    NAD1983HARNAdjMNDakota.setName("GCS_NAD_1983_HARN_Adj_MN_Dakota");
    NAD1983HARNAdjMNDodge.setName("GCS_NAD_1983_HARN_Adj_MN_Dodge");
    NAD1983HARNAdjMNDouglas.setName("GCS_NAD_1983_HARN_Adj_MN_Douglas");
    NAD1983HARNAdjMNFaribault.setName("GCS_NAD_1983_HARN_Adj_MN_Faribault");
    NAD1983HARNAdjMNFillmore.setName("GCS_NAD_1983_HARN_Adj_MN_Fillmore");
    NAD1983HARNAdjMNFreeborn.setName("GCS_NAD_1983_HARN_Adj_MN_Freeborn");
    NAD1983HARNAdjMNGoodhue.setName("GCS_NAD_1983_HARN_Adj_MN_Goodhue");
    NAD1983HARNAdjMNGrant.setName("GCS_NAD_1983_HARN_Adj_MN_Grant");
    NAD1983HARNAdjMNHennepin.setName("GCS_NAD_1983_HARN_Adj_MN_Hennepin");
    NAD1983HARNAdjMNHouston.setName("GCS_NAD_1983_HARN_Adj_MN_Houston");
    NAD1983HARNAdjMNIsanti.setName("GCS_NAD_1983_HARN_Adj_MN_Isanti");
    NAD1983HARNAdjMNItascaNorth.setName("GCS_NAD_1983_HARN_Adj_MN_Itasca_North");
    NAD1983HARNAdjMNItascaSouth.setName("GCS_NAD_1983_HARN_Adj_MN_Itasca_South");
    NAD1983HARNAdjMNJackson.setName("GCS_NAD_1983_HARN_Adj_MN_Jackson");
    NAD1983HARNAdjMNKanabec.setName("GCS_NAD_1983_HARN_Adj_MN_Kanabec");
    NAD1983HARNAdjMNKandiyohi.setName("GCS_NAD_1983_HARN_Adj_MN_Kandiyohi");
    NAD1983HARNAdjMNKittson.setName("GCS_NAD_1983_HARN_Adj_MN_Kittson");
    NAD1983HARNAdjMNKoochiching.setName("GCS_NAD_1983_HARN_Adj_MN_Koochiching");
    NAD1983HARNAdjMNLacQuiParle.setName("GCS_NAD_1983_HARN_Adj_MN_Lac_Qui_Parle");
    NAD1983HARNAdjMNLakeoftheWoodsNorth.setName("GCS_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_North");
    NAD1983HARNAdjMNLakeoftheWoodsSouth.setName("GCS_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_South");
    NAD1983HARNAdjMNLeSueur.setName("GCS_NAD_1983_HARN_Adj_MN_Le_Sueur");
    NAD1983HARNAdjMNLincoln.setName("GCS_NAD_1983_HARN_Adj_MN_Lincoln");
    NAD1983HARNAdjMNLyon.setName("GCS_NAD_1983_HARN_Adj_MN_Lyon");
    NAD1983HARNAdjMNMahnomen.setName("GCS_NAD_1983_HARN_Adj_MN_Mahnomen");
    NAD1983HARNAdjMNMarshall.setName("GCS_NAD_1983_HARN_Adj_MN_Marshall");
    NAD1983HARNAdjMNMartin.setName("GCS_NAD_1983_HARN_Adj_MN_Martin");
    NAD1983HARNAdjMNMcLeod.setName("GCS_NAD_1983_HARN_Adj_MN_McLeod");
    NAD1983HARNAdjMNMeeker.setName("GCS_NAD_1983_HARN_Adj_MN_Meeker");
    NAD1983HARNAdjMNMorrison.setName("GCS_NAD_1983_HARN_Adj_MN_Morrison");
    NAD1983HARNAdjMNMower.setName("GCS_NAD_1983_HARN_Adj_MN_Mower");
    NAD1983HARNAdjMNMurray.setName("GCS_NAD_1983_HARN_Adj_MN_Murray");
    NAD1983HARNAdjMNNicollet.setName("GCS_NAD_1983_HARN_Adj_MN_Nicollet");
    NAD1983HARNAdjMNNobles.setName("GCS_NAD_1983_HARN_Adj_MN_Nobles");
    NAD1983HARNAdjMNNorman.setName("GCS_NAD_1983_HARN_Adj_MN_Norman");
    NAD1983HARNAdjMNOlmsted.setName("GCS_NAD_1983_HARN_Adj_MN_Olmsted");
    NAD1983HARNAdjMNOttertail.setName("GCS_NAD_1983_HARN_Adj_MN_Ottertail");
    NAD1983HARNAdjMNPennington.setName("GCS_NAD_1983_HARN_Adj_MN_Pennington");
    NAD1983HARNAdjMNPine.setName("GCS_NAD_1983_HARN_Adj_MN_Pine");
    NAD1983HARNAdjMNPipestone.setName("GCS_NAD_1983_HARN_Adj_MN_Pipestone");
    NAD1983HARNAdjMNPolk.setName("GCS_NAD_1983_HARN_Adj_MN_Polk");
    NAD1983HARNAdjMNPope.setName("GCS_NAD_1983_HARN_Adj_MN_Pope");
    NAD1983HARNAdjMNRamsey.setName("GCS_NAD_1983_HARN_Adj_MN_Ramsey");
    NAD1983HARNAdjMNRedLake.setName("GCS_NAD_1983_HARN_Adj_MN_Red_Lake");
    NAD1983HARNAdjMNRedwood.setName("GCS_NAD_1983_HARN_Adj_MN_Redwood");
    NAD1983HARNAdjMNRenville.setName("GCS_NAD_1983_HARN_Adj_MN_Renville");
    NAD1983HARNAdjMNRice.setName("GCS_NAD_1983_HARN_Adj_MN_Rice");
    NAD1983HARNAdjMNRock.setName("GCS_NAD_1983_HARN_Adj_MN_Rock");
    NAD1983HARNAdjMNRoseau.setName("GCS_NAD_1983_HARN_Adj_MN_Roseau");
    NAD1983HARNAdjMNScott.setName("GCS_NAD_1983_HARN_Adj_MN_Scott");
    NAD1983HARNAdjMNSherburne.setName("GCS_NAD_1983_HARN_Adj_MN_Sherburne");
    NAD1983HARNAdjMNSibley.setName("GCS_NAD_1983_HARN_Adj_MN_Sibley");
    NAD1983HARNAdjMNStLouisCentral.setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_Central");
    NAD1983HARNAdjMNStLouisNorth.setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_North");
    NAD1983HARNAdjMNStLouisSouth.setName("GCS_NAD_1983_HARN_Adj_MN_St_Louis_South");
    NAD1983HARNAdjMNStearns.setName("GCS_NAD_1983_HARN_Adj_MN_Stearns");
    NAD1983HARNAdjMNSteele.setName("GCS_NAD_1983_HARN_Adj_MN_Steele");
    NAD1983HARNAdjMNStevens.setName("GCS_NAD_1983_HARN_Adj_MN_Stevens");
    NAD1983HARNAdjMNSwift.setName("GCS_NAD_1983_HARN_Adj_MN_Swift");
    NAD1983HARNAdjMNTodd.setName("GCS_NAD_1983_HARN_Adj_MN_Todd");
    NAD1983HARNAdjMNTraverse.setName("GCS_NAD_1983_HARN_Adj_MN_Traverse");
    NAD1983HARNAdjMNWabasha.setName("GCS_NAD_1983_HARN_Adj_MN_Wabasha");
    NAD1983HARNAdjMNWadena.setName("GCS_NAD_1983_HARN_Adj_MN_Wadena");
    NAD1983HARNAdjMNWaseca.setName("GCS_NAD_1983_HARN_Adj_MN_Waseca");
    NAD1983HARNAdjMNWatonwan.setName("GCS_NAD_1983_HARN_Adj_MN_Watonwan");
    NAD1983HARNAdjMNWinona.setName("GCS_NAD_1983_HARN_Adj_MN_Winona");
    NAD1983HARNAdjMNWright.setName("GCS_NAD_1983_HARN_Adj_MN_Wright");
    NAD1983HARNAdjMNYellowMedicine.setName("GCS_NAD_1983_HARN_Adj_MN_Yellow_Medicine");

    NAD1983HARNAdjMNAnoka.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Anoka");
    NAD1983HARNAdjMNBecker.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Becker");
    NAD1983HARNAdjMNBeltramiNorth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Beltrami_North");
    NAD1983HARNAdjMNBeltramiSouth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Beltrami_South");
    NAD1983HARNAdjMNBenton.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Benton");
    NAD1983HARNAdjMNBigStone.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Big_Stone");
    NAD1983HARNAdjMNBlueEarth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Blue_Earth");
    NAD1983HARNAdjMNBrown.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Brown");
    NAD1983HARNAdjMNCarlton.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Carlton");
    NAD1983HARNAdjMNCarver.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Carver");
    NAD1983HARNAdjMNCassNorth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cass_North");
    NAD1983HARNAdjMNCassSouth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cass_South");
    NAD1983HARNAdjMNChippewa.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Chippewa");
    NAD1983HARNAdjMNChisago.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Chisago");
    NAD1983HARNAdjMNCookNorth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cook_North");
    NAD1983HARNAdjMNCookSouth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cook_South");
    NAD1983HARNAdjMNCottonwood.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Cottonwood");
    NAD1983HARNAdjMNCrowWing.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Crow_Wing");
    NAD1983HARNAdjMNDakota.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Dakota");
    NAD1983HARNAdjMNDodge.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Dodge");
    NAD1983HARNAdjMNDouglas.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Douglas");
    NAD1983HARNAdjMNFaribault.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Faribault");
    NAD1983HARNAdjMNFillmore.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Fillmore");
    NAD1983HARNAdjMNFreeborn.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Freeborn");
    NAD1983HARNAdjMNGoodhue.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Goodhue");
    NAD1983HARNAdjMNGrant.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Grant");
    NAD1983HARNAdjMNHennepin.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Hennepin");
    NAD1983HARNAdjMNHouston.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Houston");
    NAD1983HARNAdjMNIsanti.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Isanti");
    NAD1983HARNAdjMNItascaNorth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Itasca_North");
    NAD1983HARNAdjMNItascaSouth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Itasca_South");
    NAD1983HARNAdjMNJackson.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Jackson");
    NAD1983HARNAdjMNKanabec.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Kanabec");
    NAD1983HARNAdjMNKandiyohi.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Kandiyohi");
    NAD1983HARNAdjMNKittson.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Kittson");
    NAD1983HARNAdjMNKoochiching.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Koochiching");
    NAD1983HARNAdjMNLacQuiParle.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lac_Qui_Parle");
    NAD1983HARNAdjMNLakeoftheWoodsNorth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_North");
    NAD1983HARNAdjMNLakeoftheWoodsSouth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lake_of_the_Woods_South");
    NAD1983HARNAdjMNLeSueur.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Le_Sueur");
    NAD1983HARNAdjMNLincoln.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lincoln");
    NAD1983HARNAdjMNLyon.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Lyon");
    NAD1983HARNAdjMNMahnomen.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Mahnomen");
    NAD1983HARNAdjMNMarshall.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Marshall");
    NAD1983HARNAdjMNMartin.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Martin");
    NAD1983HARNAdjMNMcLeod.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_McLeod");
    NAD1983HARNAdjMNMeeker.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Meeker");
    NAD1983HARNAdjMNMorrison.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Morrison");
    NAD1983HARNAdjMNMower.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Mower");
    NAD1983HARNAdjMNMurray.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Murray");
    NAD1983HARNAdjMNNicollet.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Nicollet");
    NAD1983HARNAdjMNNobles.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Nobles");
    NAD1983HARNAdjMNNorman.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Norman");
    NAD1983HARNAdjMNOlmsted.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Olmsted");
    NAD1983HARNAdjMNOttertail.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Ottertail");
    NAD1983HARNAdjMNPennington.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pennington");
    NAD1983HARNAdjMNPine.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pine");
    NAD1983HARNAdjMNPipestone.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pipestone");
    NAD1983HARNAdjMNPolk.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Polk");
    NAD1983HARNAdjMNPope.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Pope");
    NAD1983HARNAdjMNRamsey.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Ramsey");
    NAD1983HARNAdjMNRedLake.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Red_Lake");
    NAD1983HARNAdjMNRedwood.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Redwood");
    NAD1983HARNAdjMNRenville.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Renville");
    NAD1983HARNAdjMNRice.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Rice");
    NAD1983HARNAdjMNRock.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Rock");
    NAD1983HARNAdjMNRoseau.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Roseau");
    NAD1983HARNAdjMNScott.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Scott");
    NAD1983HARNAdjMNSherburne.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Sherburne");
    NAD1983HARNAdjMNSibley.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Sibley");
    NAD1983HARNAdjMNStLouisCentral.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_St_Louis_Central");
    NAD1983HARNAdjMNStLouisNorth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_St_Louis_North");
    NAD1983HARNAdjMNStLouisSouth.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_St_Louis_South");
    NAD1983HARNAdjMNStearns.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Stearns");
    NAD1983HARNAdjMNSteele.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Steele");
    NAD1983HARNAdjMNStevens.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Stevens");
    NAD1983HARNAdjMNSwift.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Swift");
    NAD1983HARNAdjMNTodd.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Todd");
    NAD1983HARNAdjMNTraverse.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Traverse");
    NAD1983HARNAdjMNWabasha.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Wabasha");
    NAD1983HARNAdjMNWadena.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Wadena");
    NAD1983HARNAdjMNWaseca.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Waseca");
    NAD1983HARNAdjMNWatonwan.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Watonwan");
    NAD1983HARNAdjMNWinona.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Winona");
    NAD1983HARNAdjMNWright.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Wright");
    NAD1983HARNAdjMNYellowMedicine.getGeographicInfo().getDatum().setName("D_NAD_1983_HARN_Adj_MN_Yellow_Medicine");
  }

  //</editor-fold>

  /**
   * @return the NAD1983HARNAdjMNAnoka
   */
  public ProjectionInfo getNAD1983HARNAdjMNAnoka() {
    return NAD1983HARNAdjMNAnoka.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBecker
   */
  public ProjectionInfo getNAD1983HARNAdjMNBecker() {
    return NAD1983HARNAdjMNBecker.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBeltramiNorth
   */
  public ProjectionInfo getNAD1983HARNAdjMNBeltramiNorth() {
    return NAD1983HARNAdjMNBeltramiNorth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBeltramiSouth
   */
  public ProjectionInfo getNAD1983HARNAdjMNBeltramiSouth() {
    return NAD1983HARNAdjMNBeltramiSouth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBenton
   */
  public ProjectionInfo getNAD1983HARNAdjMNBenton() {
    return NAD1983HARNAdjMNBenton.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBigStone
   */
  public ProjectionInfo getNAD1983HARNAdjMNBigStone() {
    return NAD1983HARNAdjMNBigStone.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBlueEarth
   */
  public ProjectionInfo getNAD1983HARNAdjMNBlueEarth() {
    return NAD1983HARNAdjMNBlueEarth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNBrown
   */
  public ProjectionInfo getNAD1983HARNAdjMNBrown() {
    return NAD1983HARNAdjMNBrown.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCarlton
   */
  public ProjectionInfo getNAD1983HARNAdjMNCarlton() {
    return NAD1983HARNAdjMNCarlton.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCarver
   */
  public ProjectionInfo getNAD1983HARNAdjMNCarver() {
    return NAD1983HARNAdjMNCarver.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCassNorth
   */
  public ProjectionInfo getNAD1983HARNAdjMNCassNorth() {
    return NAD1983HARNAdjMNCassNorth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCassSouth
   */
  public ProjectionInfo getNAD1983HARNAdjMNCassSouth() {
    return NAD1983HARNAdjMNCassSouth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNChippewa
   */
  public ProjectionInfo getNAD1983HARNAdjMNChippewa() {
    return NAD1983HARNAdjMNChippewa.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNChisago
   */
  public ProjectionInfo getNAD1983HARNAdjMNChisago() {
    return NAD1983HARNAdjMNChisago.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCookNorth
   */
  public ProjectionInfo getNAD1983HARNAdjMNCookNorth() {
    return NAD1983HARNAdjMNCookNorth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCookSouth
   */
  public ProjectionInfo getNAD1983HARNAdjMNCookSouth() {
    return NAD1983HARNAdjMNCookSouth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCottonwood
   */
  public ProjectionInfo getNAD1983HARNAdjMNCottonwood() {
    return NAD1983HARNAdjMNCottonwood.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNCrowWing
   */
  public ProjectionInfo getNAD1983HARNAdjMNCrowWing() {
    return NAD1983HARNAdjMNCrowWing.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNDakota
   */
  public ProjectionInfo getNAD1983HARNAdjMNDakota() {
    return NAD1983HARNAdjMNDakota.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNDodge
   */
  public ProjectionInfo getNAD1983HARNAdjMNDodge() {
    return NAD1983HARNAdjMNDodge.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNDouglas
   */
  public ProjectionInfo getNAD1983HARNAdjMNDouglas() {
    return NAD1983HARNAdjMNDouglas.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNFaribault
   */
  public ProjectionInfo getNAD1983HARNAdjMNFaribault() {
    return NAD1983HARNAdjMNFaribault.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNFillmore
   */
  public ProjectionInfo getNAD1983HARNAdjMNFillmore() {
    return NAD1983HARNAdjMNFillmore.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNFreeborn
   */
  public ProjectionInfo getNAD1983HARNAdjMNFreeborn() {
    return NAD1983HARNAdjMNFreeborn.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNGoodhue
   */
  public ProjectionInfo getNAD1983HARNAdjMNGoodhue() {
    return NAD1983HARNAdjMNGoodhue.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNGrant
   */
  public ProjectionInfo getNAD1983HARNAdjMNGrant() {
    return NAD1983HARNAdjMNGrant.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNHennepin
   */
  public ProjectionInfo getNAD1983HARNAdjMNHennepin() {
    return NAD1983HARNAdjMNHennepin.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNHouston
   */
  public ProjectionInfo getNAD1983HARNAdjMNHouston() {
    return NAD1983HARNAdjMNHouston.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNIsanti
   */
  public ProjectionInfo getNAD1983HARNAdjMNIsanti() {
    return NAD1983HARNAdjMNIsanti.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNItascaNorth
   */
  public ProjectionInfo getNAD1983HARNAdjMNItascaNorth() {
    return NAD1983HARNAdjMNItascaNorth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNItascaSouth
   */
  public ProjectionInfo getNAD1983HARNAdjMNItascaSouth() {
    return NAD1983HARNAdjMNItascaSouth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNJackson
   */
  public ProjectionInfo getNAD1983HARNAdjMNJackson() {
    return NAD1983HARNAdjMNJackson.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKanabec
   */
  public ProjectionInfo getNAD1983HARNAdjMNKanabec() {
    return NAD1983HARNAdjMNKanabec.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKandiyohi
   */
  public ProjectionInfo getNAD1983HARNAdjMNKandiyohi() {
    return NAD1983HARNAdjMNKandiyohi.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKittson
   */
  public ProjectionInfo getNAD1983HARNAdjMNKittson() {
    return NAD1983HARNAdjMNKittson.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNKoochiching
   */
  public ProjectionInfo getNAD1983HARNAdjMNKoochiching() {
    return NAD1983HARNAdjMNKoochiching.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLacQuiParle
   */
  public ProjectionInfo getNAD1983HARNAdjMNLacQuiParle() {
    return NAD1983HARNAdjMNLacQuiParle.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLakeoftheWoodsNorth
   */
  public ProjectionInfo getNAD1983HARNAdjMNLakeoftheWoodsNorth() {
    return NAD1983HARNAdjMNLakeoftheWoodsNorth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLakeoftheWoodsSouth
   */
  public ProjectionInfo getNAD1983HARNAdjMNLakeoftheWoodsSouth() {
    return NAD1983HARNAdjMNLakeoftheWoodsSouth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLeSueur
   */
  public ProjectionInfo getNAD1983HARNAdjMNLeSueur() {
    return NAD1983HARNAdjMNLeSueur.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLincoln
   */
  public ProjectionInfo getNAD1983HARNAdjMNLincoln() {
    return NAD1983HARNAdjMNLincoln.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNLyon
   */
  public ProjectionInfo getNAD1983HARNAdjMNLyon() {
    return NAD1983HARNAdjMNLyon.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMahnomen
   */
  public ProjectionInfo getNAD1983HARNAdjMNMahnomen() {
    return NAD1983HARNAdjMNMahnomen.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMarshall
   */
  public ProjectionInfo getNAD1983HARNAdjMNMarshall() {
    return NAD1983HARNAdjMNMarshall.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMartin
   */
  public ProjectionInfo getNAD1983HARNAdjMNMartin() {
    return NAD1983HARNAdjMNMartin.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMcLeod
   */
  public ProjectionInfo getNAD1983HARNAdjMNMcLeod() {
    return NAD1983HARNAdjMNMcLeod.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMeeker
   */
  public ProjectionInfo getNAD1983HARNAdjMNMeeker() {
    return NAD1983HARNAdjMNMeeker.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMorrison
   */
  public ProjectionInfo getNAD1983HARNAdjMNMorrison() {
    return NAD1983HARNAdjMNMorrison.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMower
   */
  public ProjectionInfo getNAD1983HARNAdjMNMower() {
    return NAD1983HARNAdjMNMower.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNMurray
   */
  public ProjectionInfo getNAD1983HARNAdjMNMurray() {
    return NAD1983HARNAdjMNMurray.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNNicollet
   */
  public ProjectionInfo getNAD1983HARNAdjMNNicollet() {
    return NAD1983HARNAdjMNNicollet.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNNobles
   */
  public ProjectionInfo getNAD1983HARNAdjMNNobles() {
    return NAD1983HARNAdjMNNobles.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNNorman
   */
  public ProjectionInfo getNAD1983HARNAdjMNNorman() {
    return NAD1983HARNAdjMNNorman.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNOlmsted
   */
  public ProjectionInfo getNAD1983HARNAdjMNOlmsted() {
    return NAD1983HARNAdjMNOlmsted.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNOttertail
   */
  public ProjectionInfo getNAD1983HARNAdjMNOttertail() {
    return NAD1983HARNAdjMNOttertail.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPennington
   */
  public ProjectionInfo getNAD1983HARNAdjMNPennington() {
    return NAD1983HARNAdjMNPennington.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPine
   */
  public ProjectionInfo getNAD1983HARNAdjMNPine() {
    return NAD1983HARNAdjMNPine.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPipestone
   */
  public ProjectionInfo getNAD1983HARNAdjMNPipestone() {
    return NAD1983HARNAdjMNPipestone.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPolk
   */
  public ProjectionInfo getNAD1983HARNAdjMNPolk() {
    return NAD1983HARNAdjMNPolk.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNPope
   */
  public ProjectionInfo getNAD1983HARNAdjMNPope() {
    return NAD1983HARNAdjMNPope.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRamsey
   */
  public ProjectionInfo getNAD1983HARNAdjMNRamsey() {
    return NAD1983HARNAdjMNRamsey.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRedLake
   */
  public ProjectionInfo getNAD1983HARNAdjMNRedLake() {
    return NAD1983HARNAdjMNRedLake.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRedwood
   */
  public ProjectionInfo getNAD1983HARNAdjMNRedwood() {
    return NAD1983HARNAdjMNRedwood.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRenville
   */
  public ProjectionInfo getNAD1983HARNAdjMNRenville() {
    return NAD1983HARNAdjMNRenville.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRice
   */
  public ProjectionInfo getNAD1983HARNAdjMNRice() {
    return NAD1983HARNAdjMNRice.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRock
   */
  public ProjectionInfo getNAD1983HARNAdjMNRock() {
    return NAD1983HARNAdjMNRock.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNRoseau
   */
  public ProjectionInfo getNAD1983HARNAdjMNRoseau() {
    return NAD1983HARNAdjMNRoseau.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNScott
   */
  public ProjectionInfo getNAD1983HARNAdjMNScott() {
    return NAD1983HARNAdjMNScott.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSherburne
   */
  public ProjectionInfo getNAD1983HARNAdjMNSherburne() {
    return NAD1983HARNAdjMNSherburne.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSibley
   */
  public ProjectionInfo getNAD1983HARNAdjMNSibley() {
    return NAD1983HARNAdjMNSibley.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStLouisCentral
   */
  public ProjectionInfo getNAD1983HARNAdjMNStLouisCentral() {
    return NAD1983HARNAdjMNStLouisCentral.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStLouisNorth
   */
  public ProjectionInfo getNAD1983HARNAdjMNStLouisNorth() {
    return NAD1983HARNAdjMNStLouisNorth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStLouisSouth
   */
  public ProjectionInfo getNAD1983HARNAdjMNStLouisSouth() {
    return NAD1983HARNAdjMNStLouisSouth.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStearns
   */
  public ProjectionInfo getNAD1983HARNAdjMNStearns() {
    return NAD1983HARNAdjMNStearns.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSteele
   */
  public ProjectionInfo getNAD1983HARNAdjMNSteele() {
    return NAD1983HARNAdjMNSteele.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNStevens
   */
  public ProjectionInfo getNAD1983HARNAdjMNStevens() {
    return NAD1983HARNAdjMNStevens.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNSwift
   */
  public ProjectionInfo getNAD1983HARNAdjMNSwift() {
    return NAD1983HARNAdjMNSwift.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNTodd
   */
  public ProjectionInfo getNAD1983HARNAdjMNTodd() {
    return NAD1983HARNAdjMNTodd.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNTraverse
   */
  public ProjectionInfo getNAD1983HARNAdjMNTraverse() {
    return NAD1983HARNAdjMNTraverse.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWabasha
   */
  public ProjectionInfo getNAD1983HARNAdjMNWabasha() {
    return NAD1983HARNAdjMNWabasha.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWadena
   */
  public ProjectionInfo getNAD1983HARNAdjMNWadena() {
    return NAD1983HARNAdjMNWadena.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWaseca
   */
  public ProjectionInfo getNAD1983HARNAdjMNWaseca() {
    return NAD1983HARNAdjMNWaseca.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWatonwan
   */
  public ProjectionInfo getNAD1983HARNAdjMNWatonwan() {
    return NAD1983HARNAdjMNWatonwan.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWinona
   */
  public ProjectionInfo getNAD1983HARNAdjMNWinona() {
    return NAD1983HARNAdjMNWinona.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNWright
   */
  public ProjectionInfo getNAD1983HARNAdjMNWright() {
    return NAD1983HARNAdjMNWright.copy();
  }

  /**
   * @return the NAD1983HARNAdjMNYellowMedicine
   */
  public ProjectionInfo getNAD1983HARNAdjMNYellowMedicine() {
    return NAD1983HARNAdjMNYellowMedicine.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIAdams
   */
  public ProjectionInfo getNAD1983HARNAdjWIAdams() {
    return NAD1983HARNAdjWIAdams.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIAshland
   */
  public ProjectionInfo getNAD1983HARNAdjWIAshland() {
    return NAD1983HARNAdjWIAshland.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBarron
   */
  public ProjectionInfo getNAD1983HARNAdjWIBarron() {
    return NAD1983HARNAdjWIBarron.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBayfield
   */
  public ProjectionInfo getNAD1983HARNAdjWIBayfield() {
    return NAD1983HARNAdjWIBayfield.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBrown
   */
  public ProjectionInfo getNAD1983HARNAdjWIBrown() {
    return NAD1983HARNAdjWIBrown.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBuffalo
   */
  public ProjectionInfo getNAD1983HARNAdjWIBuffalo() {
    return NAD1983HARNAdjWIBuffalo.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIBurnett
   */
  public ProjectionInfo getNAD1983HARNAdjWIBurnett() {
    return NAD1983HARNAdjWIBurnett.copy();
  }

  /**
   * @return the NAD1983HARNAdjWICalumet
   */
  public ProjectionInfo getNAD1983HARNAdjWICalumet() {
    return NAD1983HARNAdjWICalumet.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIChippewa
   */
  public ProjectionInfo getNAD1983HARNAdjWIChippewa() {
    return NAD1983HARNAdjWIChippewa.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIClark
   */
  public ProjectionInfo getNAD1983HARNAdjWIClark() {
    return NAD1983HARNAdjWIClark.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIColumbia
   */
  public ProjectionInfo getNAD1983HARNAdjWIColumbia() {
    return NAD1983HARNAdjWIColumbia.copy();
  }

  /**
   * @return the NAD1983HARNAdjWICrawford
   */
  public ProjectionInfo getNAD1983HARNAdjWICrawford() {
    return NAD1983HARNAdjWICrawford.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDane
   */
  public ProjectionInfo getNAD1983HARNAdjWIDane() {
    return NAD1983HARNAdjWIDane.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDodge
   */
  public ProjectionInfo getNAD1983HARNAdjWIDodge() {
    return NAD1983HARNAdjWIDodge.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDoor
   */
  public ProjectionInfo getNAD1983HARNAdjWIDoor() {
    return NAD1983HARNAdjWIDoor.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDouglas
   */
  public ProjectionInfo getNAD1983HARNAdjWIDouglas() {
    return NAD1983HARNAdjWIDouglas.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIDunn
   */
  public ProjectionInfo getNAD1983HARNAdjWIDunn() {
    return NAD1983HARNAdjWIDunn.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIEauClaire
   */
  public ProjectionInfo getNAD1983HARNAdjWIEauClaire() {
    return NAD1983HARNAdjWIEauClaire.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIFlorence
   */
  public ProjectionInfo getNAD1983HARNAdjWIFlorence() {
    return NAD1983HARNAdjWIFlorence.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIFondduLac
   */
  public ProjectionInfo getNAD1983HARNAdjWIFondduLac() {
    return NAD1983HARNAdjWIFondduLac.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIForest
   */
  public ProjectionInfo getNAD1983HARNAdjWIForest() {
    return NAD1983HARNAdjWIForest.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIGrant
   */
  public ProjectionInfo getNAD1983HARNAdjWIGrant() {
    return NAD1983HARNAdjWIGrant.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIGreen
   */
  public ProjectionInfo getNAD1983HARNAdjWIGreen() {
    return NAD1983HARNAdjWIGreen.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIGreenLake
   */
  public ProjectionInfo getNAD1983HARNAdjWIGreenLake() {
    return NAD1983HARNAdjWIGreenLake.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIIowa
   */
  public ProjectionInfo getNAD1983HARNAdjWIIowa() {
    return NAD1983HARNAdjWIIowa.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIIron
   */
  public ProjectionInfo getNAD1983HARNAdjWIIron() {
    return NAD1983HARNAdjWIIron.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIJackson
   */
  public ProjectionInfo getNAD1983HARNAdjWIJackson() {
    return NAD1983HARNAdjWIJackson.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIJefferson
   */
  public ProjectionInfo getNAD1983HARNAdjWIJefferson() {
    return NAD1983HARNAdjWIJefferson.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIJuneau
   */
  public ProjectionInfo getNAD1983HARNAdjWIJuneau() {
    return NAD1983HARNAdjWIJuneau.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIKenosha
   */
  public ProjectionInfo getNAD1983HARNAdjWIKenosha() {
    return NAD1983HARNAdjWIKenosha.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIKewaunee
   */
  public ProjectionInfo getNAD1983HARNAdjWIKewaunee() {
    return NAD1983HARNAdjWIKewaunee.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILaCrosse
   */
  public ProjectionInfo getNAD1983HARNAdjWILaCrosse() {
    return NAD1983HARNAdjWILaCrosse.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILafayette
   */
  public ProjectionInfo getNAD1983HARNAdjWILafayette() {
    return NAD1983HARNAdjWILafayette.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILanglade
   */
  public ProjectionInfo getNAD1983HARNAdjWILanglade() {
    return NAD1983HARNAdjWILanglade.copy();
  }

  /**
   * @return the NAD1983HARNAdjWILincoln
   */
  public ProjectionInfo getNAD1983HARNAdjWILincoln() {
    return NAD1983HARNAdjWILincoln.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIManitowoc
   */
  public ProjectionInfo getNAD1983HARNAdjWIManitowoc() {
    return NAD1983HARNAdjWIManitowoc.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMarathon
   */
  public ProjectionInfo getNAD1983HARNAdjWIMarathon() {
    return NAD1983HARNAdjWIMarathon.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMarinette
   */
  public ProjectionInfo getNAD1983HARNAdjWIMarinette() {
    return NAD1983HARNAdjWIMarinette.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMarquette
   */
  public ProjectionInfo getNAD1983HARNAdjWIMarquette() {
    return NAD1983HARNAdjWIMarquette.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMenominee
   */
  public ProjectionInfo getNAD1983HARNAdjWIMenominee() {
    return NAD1983HARNAdjWIMenominee.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMilwaukee
   */
  public ProjectionInfo getNAD1983HARNAdjWIMilwaukee() {
    return NAD1983HARNAdjWIMilwaukee.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIMonroe
   */
  public ProjectionInfo getNAD1983HARNAdjWIMonroe() {
    return NAD1983HARNAdjWIMonroe.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOconto
   */
  public ProjectionInfo getNAD1983HARNAdjWIOconto() {
    return NAD1983HARNAdjWIOconto.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOneida
   */
  public ProjectionInfo getNAD1983HARNAdjWIOneida() {
    return NAD1983HARNAdjWIOneida.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOutagamie
   */
  public ProjectionInfo getNAD1983HARNAdjWIOutagamie() {
    return NAD1983HARNAdjWIOutagamie.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIOzaukee
   */
  public ProjectionInfo getNAD1983HARNAdjWIOzaukee() {
    return NAD1983HARNAdjWIOzaukee.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPepin
   */
  public ProjectionInfo getNAD1983HARNAdjWIPepin() {
    return NAD1983HARNAdjWIPepin.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPierce
   */
  public ProjectionInfo getNAD1983HARNAdjWIPierce() {
    return NAD1983HARNAdjWIPierce.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPolk
   */
  public ProjectionInfo getNAD1983HARNAdjWIPolk() {
    return NAD1983HARNAdjWIPolk.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPortage
   */
  public ProjectionInfo getNAD1983HARNAdjWIPortage() {
    return NAD1983HARNAdjWIPortage.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIPrice
   */
  public ProjectionInfo getNAD1983HARNAdjWIPrice() {
    return NAD1983HARNAdjWIPrice.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRacine
   */
  public ProjectionInfo getNAD1983HARNAdjWIRacine() {
    return NAD1983HARNAdjWIRacine.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRichland
   */
  public ProjectionInfo getNAD1983HARNAdjWIRichland() {
    return NAD1983HARNAdjWIRichland.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRock
   */
  public ProjectionInfo getNAD1983HARNAdjWIRock() {
    return NAD1983HARNAdjWIRock.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIRusk
   */
  public ProjectionInfo getNAD1983HARNAdjWIRusk() {
    return NAD1983HARNAdjWIRusk.copy();
  }

  /**
   * @return the NAD1983HARNAdjWISauk
   */
  public ProjectionInfo getNAD1983HARNAdjWISauk() {
    return NAD1983HARNAdjWISauk.copy();
  }

  /**
   * @return the NAD1983HARNAdjWISawyer
   */
  public ProjectionInfo getNAD1983HARNAdjWISawyer() {
    return NAD1983HARNAdjWISawyer.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIShawano
   */
  public ProjectionInfo getNAD1983HARNAdjWIShawano() {
    return NAD1983HARNAdjWIShawano.copy();
  }

  /**
   * @return the NAD1983HARNAdjWISheboygan
   */
  public ProjectionInfo getNAD1983HARNAdjWISheboygan() {
    return NAD1983HARNAdjWISheboygan.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIStCroix
   */
  public ProjectionInfo getNAD1983HARNAdjWIStCroix() {
    return NAD1983HARNAdjWIStCroix.copy();
  }

  /**
   * @return the NAD1983HARNAdjWITaylor
   */
  public ProjectionInfo getNAD1983HARNAdjWITaylor() {
    return NAD1983HARNAdjWITaylor.copy();
  }

  /**
   * @return the NAD1983HARNAdjWITrempealeau
   */
  public ProjectionInfo getNAD1983HARNAdjWITrempealeau() {
    return NAD1983HARNAdjWITrempealeau.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIVernon
   */
  public ProjectionInfo getNAD1983HARNAdjWIVernon() {
    return NAD1983HARNAdjWIVernon.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIVilas
   */
  public ProjectionInfo getNAD1983HARNAdjWIVilas() {
    return NAD1983HARNAdjWIVilas.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWalworth
   */
  public ProjectionInfo getNAD1983HARNAdjWIWalworth() {
    return NAD1983HARNAdjWIWalworth.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWashburn
   */
  public ProjectionInfo getNAD1983HARNAdjWIWashburn() {
    return NAD1983HARNAdjWIWashburn.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWashington
   */
  public ProjectionInfo getNAD1983HARNAdjWIWashington() {
    return NAD1983HARNAdjWIWashington.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWaukesha
   */
  public ProjectionInfo getNAD1983HARNAdjWIWaukesha() {
    return NAD1983HARNAdjWIWaukesha.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWaupaca
   */
  public ProjectionInfo getNAD1983HARNAdjWIWaupaca() {
    return NAD1983HARNAdjWIWaupaca.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWaushara
   */
  public ProjectionInfo getNAD1983HARNAdjWIWaushara() {
    return NAD1983HARNAdjWIWaushara.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWinnebago
   */
  public ProjectionInfo getNAD1983HARNAdjWIWinnebago() {
    return NAD1983HARNAdjWIWinnebago.copy();
  }

  /**
   * @return the NAD1983HARNAdjWIWood
   */
  public ProjectionInfo getNAD1983HARNAdjWIWood() {
    return NAD1983HARNAdjWIWood.copy();
  }
}
