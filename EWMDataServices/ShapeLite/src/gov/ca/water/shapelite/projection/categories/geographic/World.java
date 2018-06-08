/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gov.ca.water.shapelite.projection.categories.geographic;

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class World extends CoordinateSystemCategory {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private final ProjectionInfo GRS1980;
  private final ProjectionInfo ITRF1988;
  private final ProjectionInfo ITRF1989;
  private final ProjectionInfo ITRF1990;
  private final ProjectionInfo ITRF1991;
  private final ProjectionInfo ITRF1992;
  private final ProjectionInfo ITRF1993;
  private final ProjectionInfo ITRF1994;
  private final ProjectionInfo ITRF1996;
  private final ProjectionInfo ITRF1997;
  private final ProjectionInfo ITRF2000;
  private final ProjectionInfo NSWC9Z2;
  private final ProjectionInfo WGS1966;
  private final ProjectionInfo WGS1972;
  private final ProjectionInfo WGS1972TBE;
  private final ProjectionInfo WGS1984;

  //</editor-fold>
  public World() {
    GRS1980 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    GRS1980.setLatLon(true);
    GRS1980.getGeographicInfo().setName("GCS_GRS_1980");
    GRS1980.setName("GCS_GRS_1980");
    GRS1980.getGeographicInfo().getDatum().setName("D_GRS_1980");

    ITRF1988 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    ITRF1988.setLatLon(true);
    ITRF1988.getGeographicInfo().setName("GCS_ITRF_1988");
    ITRF1988.setName("GCS_ITRF_1988");
    ITRF1988.getGeographicInfo().getDatum().setName("D_ITRF_1988");

    ITRF1989 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    ITRF1989.setLatLon(true);
    ITRF1989.getGeographicInfo().setName("GCS_ITRF_1989");
    ITRF1989.setName("GCS_ITRF_1989");
    ITRF1989.getGeographicInfo().getDatum().setName("D_ITRF_1989");

    ITRF1990 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    ITRF1990.setLatLon(true);
    ITRF1990.getGeographicInfo().setName("GCS_ITRF_1990");
    ITRF1990.setName("GCS_ITRF_1990");
    ITRF1990.getGeographicInfo().getDatum().setName("D_ITRF_1990");

    ITRF1991 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    ITRF1991.setLatLon(true);
    ITRF1991.getGeographicInfo().setName("GCS_ITRF_1991");
    ITRF1991.setName("GCS_ITRF_1991");
    ITRF1991.getGeographicInfo().getDatum().setName("D_ITRF_1991");

    ITRF1992 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    ITRF1992.setLatLon(true);
    ITRF1992.getGeographicInfo().setName("GCS_ITRF_1992");
    ITRF1992.setName("GCS_ITRF_1992");
    ITRF1992.getGeographicInfo().getDatum().setName("D_ITRF_1992");

    ITRF1993 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    ITRF1993.setLatLon(true);
    ITRF1993.getGeographicInfo().setName("GCS_ITRF_1993");
    ITRF1993.setName("GCS_ITRF_1993");
    ITRF1993.getGeographicInfo().getDatum().setName("D_ITRF_1993");

    ITRF1994 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    ITRF1994.setLatLon(true);
    ITRF1994.getGeographicInfo().setName("GCS_ITRF_1994");
    ITRF1994.setName("GCS_ITRF_1994");
    ITRF1994.getGeographicInfo().getDatum().setName("D_ITRF_1994");

    ITRF1996 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    ITRF1996.setLatLon(true);
    ITRF1996.getGeographicInfo().setName("GCS_ITRF_1996");
    ITRF1996.setName("GCS_ITRF_1996");
    ITRF1996.getGeographicInfo().getDatum().setName("D_ITRF_1996");

    ITRF1997 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    ITRF1997.setLatLon(true);
    ITRF1997.getGeographicInfo().setName("GCS_ITRF_1997");
    ITRF1997.setName("GCS_ITRF_1997");
    ITRF1997.getGeographicInfo().setName("D_ITRF_1997");

    ITRF2000 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=GRS80 +no_defs ").orElse(null);;
    ITRF2000.setLatLon(true);
    ITRF2000.getGeographicInfo().setName("GCS_ITRF_2000");
    ITRF2000.setName("GCS_ITRF_2000");
    ITRF2000.getGeographicInfo().getDatum().setName("D_ITRF_2000");

    NSWC9Z2 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS66 +no_defs ").orElse(null);;
    NSWC9Z2.setLatLon(true);
    NSWC9Z2.getGeographicInfo().setName("GCS_NSWC_9Z_2");
    NSWC9Z2.setName("GCS_NSWC_9Z_2");
    NSWC9Z2.getGeographicInfo().getDatum().setName("D_NSWC_9Z_2");

    WGS1966 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS66 +no_defs ").orElse(null);;
    WGS1966.setLatLon(true);
    WGS1966.getGeographicInfo().setName("GCS_WGS_1966");
    WGS1966.setName("GCS_WGS_1966");
    WGS1966.getGeographicInfo().getDatum().setName("D_WGS_1996");

    WGS1972 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS72 +no_defs ").orElse(null);;
    WGS1972.setLatLon(true);
    WGS1972.getGeographicInfo().setName("GCS_WGS_1972");
    WGS1972.setName("GCS_WGS_1972");
    WGS1972.getGeographicInfo().getDatum().setName("D_WGS_1972");

    WGS1972TBE = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS72 +no_defs ").orElse(null);;
    WGS1972TBE.setLatLon(true);
    WGS1972TBE.getGeographicInfo().setName("GCS_WGS_1972_BE");
    WGS1972TBE.setName("GCS_WGS_1972_BE");
    WGS1972TBE.getGeographicInfo().getDatum().setName("D_WGS_1972_BE");

    WGS1984 = ProjectionInfo.fromProj4String("+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs ").orElse(null);;
    WGS1984.setLatLon(true);
    WGS1984.getGeographicInfo().setName("GCS_WGS_1984");
    WGS1984.setName("GCS_WGS_1984");
    WGS1984.getGeographicInfo().getDatum().setName("D_WGS_1984");
  }

  /**
   * @return the GRS1980
   */
  public ProjectionInfo getGRS1980() {
    return GRS1980.copy();
  }

  /**
   * @return the ITRF1988
   */
  public ProjectionInfo getITRF1988() {
    return ITRF1988.copy();
  }

  /**
   * @return the ITRF1989
   */
  public ProjectionInfo getITRF1989() {
    return ITRF1989.copy();
  }

  /**
   * @return the ITRF1990
   */
  public ProjectionInfo getITRF1990() {
    return ITRF1990.copy();
  }

  /**
   * @return the ITRF1991
   */
  public ProjectionInfo getITRF1991() {
    return ITRF1991.copy();
  }

  /**
   * @return the ITRF1992
   */
  public ProjectionInfo getITRF1992() {
    return ITRF1992.copy();
  }

  /**
   * @return the ITRF1993
   */
  public ProjectionInfo getITRF1993() {
    return ITRF1993.copy();
  }

  /**
   * @return the ITRF1994
   */
  public ProjectionInfo getITRF1994() {
    return ITRF1994.copy();
  }

  /**
   * @return the ITRF1996
   */
  public ProjectionInfo getITRF1996() {
    return ITRF1996.copy();
  }

  /**
   * @return the ITRF1997
   */
  public ProjectionInfo getITRF1997() {
    return ITRF1997.copy();
  }

  /**
   * @return the ITRF2000
   */
  public ProjectionInfo getITRF2000() {
    return ITRF2000.copy();
  }

  /**
   * @return the NSWC9Z2
   */
  public ProjectionInfo getNSWC9Z2() {
    return NSWC9Z2.copy();
  }

  /**
   * @return the WGS1966
   */
  public ProjectionInfo getWGS1966() {
    return WGS1966.copy();
  }

  /**
   * @return the WGS1972
   */
  public ProjectionInfo getWGS1972() {
    return WGS1972.copy();
  }

  /**
   * @return the WGS1972TBE
   */
  public ProjectionInfo getWGS1972TBE() {
    return WGS1972TBE.copy();
  }

  /**
   * @return the WGS1984
   */
  public ProjectionInfo getWGS1984() {
    return WGS1984.copy();
  }

}
