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

import java.util.ArrayList;
import java.util.List;

/**
 * This is a single node in the data structure that organizes parts so that they
 * can be used to calculate distances. A part is a single, continuous set of
 * vertices. So a part is guaranteed not to include any breaks or shapes that
 * can't be drawn by a single connected line.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartTreeNode implements Comparable<PartTreeNode> {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The space between the parent node and this node.
   */
  private double gap;

  /**
   * The list of child PartTreeNode nodes that attach to the END of this part.
   */
  private List<PartTreeNode> nodes;

  /**
   * The parent part tree node that distance calculations begin from.
   */
  private PartTreeNode parent;

  /**
   * The parent tree containing this part.
   */
  private PartTree parentTree;

  /**
   * The actual geometry information for the part associated with this node.
   */
  private final Part part;

  //</editor-fold>
  /**
   * Creates a PartTreeNode that is associated with the specified part.
   *
   * @param part The part represented by this node. If part is null, part will
   * become an empty part instead.
   */
  public PartTreeNode(@Nullable Part part) {
    nodes = new ArrayList<>();
    if (part == null) {
      this.part = new Part();
    } else {
      this.part = part;
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Gets the last Coordinate in this part.
   *
   * @return The last Coordinate in this part.
   */
  public final Optional<Coord> end() {
    if (getPart() == null) {
      return Optional.empty();
    }
    return part.end();
  }

  /**
   * Gets the start distance for this part plus the length of this part.
   *
   * @return The double distance to the last coordinate.
   */
  public final double endDistance() {
    return startDistance() + length();
  }

  /**
   * Get a boolean that is true if either the start or end coordinate of this
   * part touches the specified part.
   *
   * @param part The part to test.
   * @return Boolean that is true if the parts touch.
   */
  public final boolean endTouches(Part part) {
    return (endTouchesEnd(part) || endTouchesStart(part));
  }

  /**
   * Gets a boolean that is true if this part's last Coordinate is within
   * PartTree.EPSILON of the other part's last coordinate.
   *
   * @param part The part to compare this part to. If part is null, or the
   * coordinates don't exist in either part, this returns false.
   * @return Boolean, true if the last coordinates intersect.
   */
  public final boolean endTouchesEnd(@Nullable Part part) {
    if (part == null) {
      return false;
    }
    if (end().isPresent() && part.end().isPresent()) {
      return end().get().distance(part.end().get()) < parentTree.getEpsilon();
    }
    return false;
  }

  /**
   * Gets a boolean that is true if the end of this coordinate touches the
   * starting point of the specified part.
   *
   * @param part The part to compare to this part.
   * @return Boolean that is true if the end of this coordinate touches the
   * starting point of the specified part.
   */
  public final boolean endTouchesStart(@Nullable Part part) {
    if (part == null) {
      return false;
    }
    if (end().isPresent() && part.start().isPresent()) {
      return end().get().distance(part.start().get()) < parentTree.getEpsilon();
    }
    return false;
  }

  /**
   * Gets the length of the geometry of this part.
   *
   * @return The length of this part.
   */
  public final double length() {
    if (part == null) {
      return 0;
    }
    return part.length();
  }

  /**
   * Gets the first Coordinate in this part.
   *
   * @return The first Coordinate of this part.
   */
  public final Optional<Coord> start() {
    return part.start();
  }

  /**
   * Gets the double distance calculated through the tree from the start of the
   * first segment, through all the parent segments, to this node.
   *
   * @return the double start distance.
   */
  public final double startDistance() {
    double result = 0;
    if (parent != null) {
      result = parent.startDistance() + parent.length() + gap;
    }
    return result;
  }

  /**
   * Compares the end distance on this part to the end distance of another part
   * so that parts can be ordered according to the "shortest" path.
   *
   * @param o The PartTreeNode to compare to.
   * @return An integer that is 1 if the distance to the end of this path is
   * greater, 0 if it is the same, and -1 if it is less than the distance to the
   * end of the other path.
   */
  @Override
  public final int compareTo(PartTreeNode o) {
    return Double.compare(endDistance(), o.endDistance());
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the space between the parent node and this node.
   *
   * @return the gap
   */
  public final double getGap() {
    return gap;
  }

  /**
   * Sets the space between the parent node and this node.
   *
   * @param gap the gap to set
   */
  public final void setGap(double gap) {
    this.gap = gap;
  }

  /**
   * Gets the list of child PartTreeNode nodes that attach to the END of this
   * part.
   *
   * @return the nodes
   */
  public final List<PartTreeNode> getNodes() {
    return nodes;
  }

  /**
   * Sets the list of child PartTreeNode nodes that attach to the END of this
   * part.
   *
   * @param nodes the nodes to set
   */
  public final void setNodes(List<PartTreeNode> nodes) {
    this.nodes = nodes;
  }

  /**
   * Gets the parent part tree node that distance calculations begin from.
   *
   * @return the parent
   */
  public final PartTreeNode getParent() {
    return parent;
  }

  /**
   * Sets the parent part tree node that distance calculations begin from.
   *
   * @param parent the parent to set
   */
  public final void setParent(PartTreeNode parent) {
    this.parent = parent;
  }

  /**
   * Gets the parent tree containing this part.
   *
   * @return the parentTree
   */
  public final PartTree getParentTree() {
    return parentTree;
  }

  /**
   * Sets the parent tree containing this part.
   *
   * @param parentTree the parentTree to set
   */
  public final void setParentTree(PartTree parentTree) {
    this.parentTree = parentTree;
  }

  /**
   * Gets the actual geometry information for the part associated with this
   * node.
   *
   * @return the part
   */
  public final Part getPart() {
    return part;
  }

  //</editor-fold>
}
