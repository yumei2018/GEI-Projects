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
package gov.ca.water.shapelite.coordinate;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.EndPointInteraction;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Nullable;
import java.io.Serializable;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ClosestCoord implements Serializable {

  /**
   * Gets an integer HASH multiplier.
   */
  private static final int HASH_STEP = 7000;

  /**
   * The Coordinate retrieved.
   */
  private Coord coord;

  /**
   * The extra information about whether the point is past the start or end of
   * the segment or whether the segment is degenerate.
   */
  private EndPointInteraction interaction;

  /**
   * Creates a default instance of this return type with an empty optional. The
   * interaction is important, because the Coordinate is not null.
   */
  public ClosestCoord() {
    coord = new CoordXY(0, 0);
    interaction = EndPointInteraction.None;
  }

  /**
   * Creates a new instance of the Closest Coord object with the specified
   * parameter.
   *
   * @param coord A Coord class. This cannot be null.
   * @param interaction An interaction defining how the coordinate was gained.
   * If null is passed in, then this will default to EndPointInteraction.None.
   */
  public ClosestCoord(@NonNull Coord coord, EndPointInteraction interaction) {
    if (coord == null) {
      throw new IllegalArgumentException("Coord coord cannot be null.");
    }
    this.coord = coord;
    this.interaction = interaction;
  }

  /**
   * Gets a boolean that is true if the other object is a ClosestCoord object
   * with a matching coordinate and interaction.
   *
   * @param obj The object to compare to.
   * @return Boolean that is true if they are equal.
   */
  @Override
  public final boolean equals(Object obj) {
    if (obj instanceof ClosestCoord) {
      ClosestCoord other = (ClosestCoord) obj;
      return coord.equals(other.coord) && interaction.equals(other.interaction);
    }
    return false;
  }

  /**
   * Generates a hash code that is unique based on the coordinate and the
   * interaction type.
   *
   * @return A hash Code.
   */
  @Override
  public final int hashCode() {
    int result = coord.hashCode();
    result += HASH_STEP * interaction.ordinal();
    return result;
  }

  // <editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the Coordinate retrieved.
   *
   * @return the coord
   */
  public final Coord getCoord() {
    return coord;
  }

  /**
   * Sets the Coordinate retrieved.
   *
   * @param coord the coord to set. This cannot be null.
   */
  public final void setCoord(@NonNull Coord coord) {
    if (coord == null) {
      throw new IllegalArgumentException("Coord coord cannot be null.");
    }
    this.coord = coord;
  }

  /**
   * Gets the extra information about whether the point is past the start or end
   * of the segment or whether the segment is degenerate.
   *
   * @return the interaction
   */
  public final EndPointInteraction getInteraction() {
    return interaction;
  }

  /**
   * Sets the extra information about whether the point is past the start or end
   * of the segment or whether the segment is degenerate.
   *
   * @param interaction the interaction to set. If this is null, it will be
   * replaced by EndPointInteraction.None.
   */
  public final void setInteraction(EndPointInteraction interaction) {
    this.interaction = EndPointInteraction.None;
    if (interaction != null) {
      this.interaction = interaction;
    }
  }

  // </editor-fold>
}
