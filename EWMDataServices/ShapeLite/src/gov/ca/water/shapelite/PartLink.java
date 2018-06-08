/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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
package gov.ca.water.shapelite;

import java.io.Serializable;

/**
 * A class for joining Parts to a part tree.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartLink implements Comparable<PartLink>, Serializable {

  /**
   * The Part of the shape.
   */
  private Part part;

  /**
   * The Double distance from this part to the parent.
   */
  private Double distance;

  /**
   * A boolean that is true if the end of this part is closer to the parent part
   * than the start of this part.
   */
  private boolean flipped;

  /**
   * The node for this part in the tree that links the parts together.
   */
  private PartTreeNode anchor;

  /**
   * Creates a new instance of a PartLink class that joins a part with a
   * distance and determines if a part needs flipping.
   *
   * @param part The part associated with this link.
   * @param dist The distance between this part and the tree.
   * @param flip True if the coordinates need to be flipped.
   */
  public PartLink(Part part, double dist, boolean flip) {
    this.part = part;
    this.distance = dist;
    this.flipped = flip;
  }

  /**
   * Compares the distance of this partlink to another so that they can be
   * sorted by distance.
   *
   * @param o The other partlink to compare to.
   * @return A 1 if this distance is greater, 0 if they are equal, and -1 if
   * this distance is less than the other link.
   */
  @Override
  public final int compareTo(PartLink o) {
    return this.distance.compareTo(o.distance);
  }

  /**
   * @return the part
   */
  public final Part getPart() {
    return part;
  }

  /**
   * @param part the part to set
   */
  public final void setPart(Part part) {
    this.part = part;
  }

  /**
   * @return the distance
   */
  public final Double getDistance() {
    return distance;
  }

  /**
   * @param distance the distance to set
   */
  public final void setDistance(Double distance) {
    this.distance = distance;
  }

  /**
   * @return the anchor
   */
  public final PartTreeNode getAnchor() {
    return anchor;
  }

  /**
   * @param anchor the anchor to set
   */
  public final void setAnchor(PartTreeNode anchor) {
    this.anchor = anchor;
  }

  /**
   * @return the flipped
   */
  public final boolean isFlipped() {
    return flipped;
  }

  /**
   * @param flipped the flipped to set
   */
  public final void setFlipped(boolean flipped) {
    this.flipped = flipped;
  }

}
