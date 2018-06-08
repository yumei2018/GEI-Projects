/*
 * The MIT License
 *
 * Copyright 2015 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.services;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.HasXY;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public interface ElevationService  {

  /**
   * Gets the list of elevations from the start to the end point as CoordZ values.
   * @param start The start point.
   * @param end The end point.
   * @return The List of CoordZ values with Z elevations.
   * @throws ElevationServiceException if there was an error during the process.
   */
  List<CoordZ> getElevations(HasXY start, HasXY end) throws ElevationServiceException;

  /**
   * Gets the list of elevations from the start to the end point as CoordZ values.
   * @param start The start point.
   * @param end The end point.
   * @param buffer The tolerance in feet that points can be off of the line and
   * still returned.
   * @param count The integer count representing the target maximum point return.
   * If more points are found, they will be thinned by number to try to get
   * the best representation along the line.
   * still returned.
   * @return The List of CoordZ values with Z elevations.
   * @throws ElevationServiceException if there was an error during the process.
   */
  List<CoordZ> getElevations(HasXY start, HasXY end, double buffer, int count)
      throws ElevationServiceException;
}
