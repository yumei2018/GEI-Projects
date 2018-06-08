/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.gsa;

import gov.ca.water.shapelite.FeatureSet;
import java.util.List;

/**
 * This class holds the output results from the GSA utility operations.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class UpdateResults {

  // <editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The FeatureSet that stores intersections between the new gsa area and any
   * other GSA areas from other applications.
   */
  private FeatureSet gsaIntersections;

  /**
   * The FeatureSet that stores intersections between the specified service area
   * and any other GSA service area polygons.
   */
  private FeatureSet serviceIntersections;

  /**
   * The FeatureSet that stores the areas where the new GSA area is outside of
   * the specified GSA service area.
   */
  private FeatureSet gsaAreasOutsideServiceAreas;

  /**
   * The intersections of the gsa by BASIN ID. (Has both basin and GSA
   * attributes).
   */
  private FeatureSet gsaBasinIntersections;

  /**
   * The list of names of the counties that intersect with the specified GSA
   * area.
   */
  private List<String> gsaCountyNames;

  /**
   * The list of basin ID values that intersect with the specified GSA area.
   */
  private List<String> gsaBasinIds;

  /**
   * The list of GSA Id's that overlap with the master GSA.
   */
  private List<String> overlappingGsaIds;

  /**
   * The FeatureSet of all the GSA shapes for all applications.
   */
  private FeatureSet combinedGSA;

  /**
   * The FeatureSet of all the Service Area shapes for applications.
   */
  private FeatureSet combinedServiceAreas;

  /**
   * The original GSA areas, projected to WGS84.
   */
  private FeatureSet gsaWGS84;



  // </editor-fold>
  /**
   * Creates a new instance of the Update Results.
   */
  public UpdateResults() {
    combinedServiceAreas = new FeatureSet();
    combinedGSA = new FeatureSet();
    gsaBasinIntersections = new FeatureSet();
    gsaIntersections = new FeatureSet();
    serviceIntersections = new FeatureSet();
    gsaAreasOutsideServiceAreas = new FeatureSet();
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the FeatureSet that stores intersections between the new gsa area and
   * any other GSA areas from other applications.
   *
   * @return the gsaIntersections
   */
  public final FeatureSet getGsaIntersections() {
    return gsaIntersections;
  }

  /**
   * Sets the FeatureSet that stores intersections between the new gsa area and
   * any other GSA areas from other applications.
   *
   * @param gsaIntersections the gsaIntersections to set
   */
  public final void setGsaIntersections(FeatureSet gsaIntersections) {
    this.gsaIntersections = gsaIntersections;
  }

  /**
   * Gets the FeatureSet that stores intersections between the specified service
   * area and any other GSA service area polygons.
   *
   * @return the serviceIntersections
   */
  public final FeatureSet getServiceIntersections() {
    return serviceIntersections;
  }

  /**
   * Sets the FeatureSet that stores intersections between the specified service
   * area and any other GSA service area polygons.
   *
   * @param serviceIntersections the serviceIntersections to set
   */
  public final void setServiceIntersections(FeatureSet serviceIntersections) {
    this.serviceIntersections = serviceIntersections;
  }

  /**
   * Gets the FeatureSet that stores the areas where the new GSA area is outside
   * of the specified GSA service area.
   *
   * @return the gsaAreasOutsideServiceAreas
   */
  public final FeatureSet getGsaAreasOutsideServiceAreas() {
    return gsaAreasOutsideServiceAreas;
  }

  /**
   * Sets the FeatureSet that stores the areas where the new GSA area is outside
   * of the specified GSA service area.
   *
   * @param gsaAreasOutsideServiceAreas the gsaAreasOutsideServiceAreas to set
   */
  public final void setGsaAreasOutsideServiceAreas(
      FeatureSet gsaAreasOutsideServiceAreas) {
    this.gsaAreasOutsideServiceAreas = gsaAreasOutsideServiceAreas;
  }

  /**
   * Gets the list of names of the counties that intersect with the specified
   * GSA area.
   *
   * @return the gsaCountyNames
   */
  public final List<String> getGsaCountyNames() {
    return gsaCountyNames;
  }

  /**
   * Sets the list of names of the counties that intersect with the specified
   * GSA area.
   *
   * @param gsaCountyNames the gsaCountyNames to set
   */
  public final void setGsaCountyNames(List<String> gsaCountyNames) {
    this.gsaCountyNames = gsaCountyNames;
  }

  /**
   * Gets the list of basin ID values that intersect with the specified GSA
   * area.
   *
   * @return the gsaBasinIds
   */
  public final List<String> getGsaBasinIds() {
    return gsaBasinIds;
  }

  /**
   * Sets the list of basin ID values that intersect with the specified GSA
   * area.
   *
   * @param gsaBasinIds the gsaBasinIds to set
   */
  public final void setGsaBasinIds(List<String> gsaBasinIds) {
    this.gsaBasinIds = gsaBasinIds;
  }

  /**
   * Gets the intersections of the gsa by BASIN ID. (Has both basin and GSA
   * attributes).
   *
   * @return the gsaBasinIntersections
   */
  public final FeatureSet getGsaBasinIntersections() {
    return gsaBasinIntersections;
  }

  /**
   * Sets the intersections of the gsa by BASIN ID. (Has both basin and GSA
   * attributes).
   *
   * @param gsaBasinIntersections the gsaBasinIntersections to set
   */
  public final void setGsaBasinIntersections(FeatureSet gsaBasinIntersections) {
    this.gsaBasinIntersections = gsaBasinIntersections;
  }

  // </editor-fold>
  /**
   * Gets the FeatureSet of all the GSA shapes for all applications.
   *
   * @return the combinedGSA
   */
  public final FeatureSet getCombinedGSA() {
    return combinedGSA;
  }

  /**
   * Sets the FeatureSet of all the GSA shapes for all applications.
   *
   * @param combinedGSA the combinedGSA to set
   */
  public final void setCombinedGSA(FeatureSet combinedGSA) {
    this.combinedGSA = combinedGSA;
  }

  /**
   * Gets the FeatureSet of all the Service Area shapes for applications.
   *
   * @return the combinedServiceAreas
   */
  public final FeatureSet getCombinedServiceAreas() {
    return combinedServiceAreas;
  }

  /**
   * Sets the FeatureSet of all the Service Area shapes for applications.
   *
   * @param combinedServiceAreas the combinedServiceAreas to set
   */
  public final void setCombinedServiceAreas(FeatureSet combinedServiceAreas) {
    this.combinedServiceAreas = combinedServiceAreas;
  }

  /**
   * @return the gsaWGS84
   */
  public final FeatureSet getGsaWGS84() {
    return gsaWGS84;
  }

  /**
   * @param gsaWGS84 the gsaWGS84 to set
   */
  public final void setGsaWGS84(FeatureSet gsaWGS84) {
    this.gsaWGS84 = gsaWGS84;
  }

  /**
   * @return the overlappingGsaIds
   */
  public final List<String> getOverlappingGsaIds() {
    return overlappingGsaIds;
  }

  /**
   * @param overlappingGsaIds the overlappingGsaIds to set
   */
  public final void setOverlappingGsaIds(List<String> overlappingGsaIds) {
    this.overlappingGsaIds = overlappingGsaIds;
  }


}
