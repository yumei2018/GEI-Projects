/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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
package gov.ca.water.shapelite.analysis;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Segment;
import gov.ca.water.shapelite.segment.DefaultSegment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartIntersectorLine implements PartIntersector {

  /**
   * This will crop the coordinates. If both the shape and envelope have Z
   * values then the Z envelope values will be considered for cropping. If the
   * envelope does not have z, but this shape does have z, then the z values
   * will be preserved, but only x and y are considered for cropping.
   *
   * @param envelope The envelope to use for the intersection calculation.
   * @return The Part created by the intersection. This is optional if no
   * coordinates are contained in the envelope.
   */
  @Override
  public final List<Part> intersection(Part original, Envelope envelope) {
    List<Part> result = new ArrayList<>();
    Part currentPart = null;
    Boolean previousInside = null;
    Coord previous = null;
    for (Coord coord : original.getCoordinates()) {
      if (envelope.containsPoint(coord)) {
        if (currentPart != null) {
          if (previousInside) {
            currentPart.getCoordinates().add(coord.copy());
          } else {
            // previous outside, current inside.  New Part needed.
            Segment seg = new DefaultSegment(previous, coord);
            Optional<Segment> cropped = seg.intersection(envelope);
            if (cropped.isPresent()) {
              currentPart = new Part();
              result.add(currentPart);
              currentPart.getCoordinates().add(cropped.get().getP1());
              currentPart.getCoordinates().add(cropped.get().getP2());
            }
          }
        } else {
          currentPart = new Part(); // If first point is inside
          result.add(currentPart);
          if (previous == null) {
            currentPart.getCoordinates().add(coord.copy());
          }else{
            Segment seg = new DefaultSegment(previous, coord);
            Optional<Segment> cropped = seg.intersection(envelope);
            if (cropped.isPresent()) {
              // should always be true.
              currentPart.getCoordinates().add(cropped.get().getP1());
              currentPart.getCoordinates().add(cropped.get().getP2());
            }
          }

        }
        previousInside = true;
      } else {
        if (previous != null) {
          // if previous was inside, we definitely get a part, but even
          // if the previous was not inside we might get a part.
          Segment seg = new DefaultSegment(previous, coord);
          Optional<Segment> cropped = seg.intersection(envelope);
          if (cropped.isPresent()) {
            if (currentPart == null) {
              currentPart = new Part();
              result.add(currentPart);
            }
            Optional<Coord> end = currentPart.end();
            if (!end.isPresent()) {
              currentPart.getCoordinates().add(cropped.get().getP1());
            } else {
              if (!end.equals(cropped.get().getP1())) {
                currentPart = new Part();
                result.add(currentPart);
                currentPart.getCoordinates().add(cropped.get().getP1());
              }
            }

            currentPart.getCoordinates().add(cropped.get().getP2());
          }
        }

        // two outside points can still cross inside.
        previousInside = false;
      }
      previous = coord;
    }
    return result;
  }
}
