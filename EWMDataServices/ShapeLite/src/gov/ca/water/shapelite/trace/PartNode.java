/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.trace;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Segment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hdunsford
 */
public class PartNode {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private Coord start;
  private Coord end;
  private Part part;
  private PartNode parent;
  private List<PartNode> endNodes;
  private Double length;
  private boolean reversed;
  private double gap; // the gap between the parent node and this node

  //</editor-fold>
  /**
   * Creates a new instance of the PartNode class.
   *
   * @param part The part to use to create this part node.
   */
  public PartNode(Part part) {
    this.part = part;
    this.start = part.start().get();
    this.end = part.end().get();
    this.length = part.length();
    endNodes = new ArrayList<>();
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Gets the double tree length as the sum of parts to this part.
   *
   * @return
   */
  public double treeLength() {
    double d = this.part.length() + this.gap;
    PartNode currentParent = this.parent;
    while (currentParent != null) {
      d += currentParent.length + currentParent.gap;
      currentParent = currentParent.getParent();
    }
    return d;
  }

  @Override
  public String toString() {
    if (part != null && part.getCoordinates() != null) {
      return super.toString() + " (" + part.getCoordinates().size() + ")";
    }
    return super.toString();
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
  /**
   * @return the start
   */
  public Coord getStart() {
    return start;
  }

  /**
   * @param start the start to set
   */
  public void setStart(Coord start) {
    this.start = start;
  }

  /**
   * @return the end
   */
  public Coord getEnd() {
    return end;
  }

  /**
   * @param end the end to set
   */
  public void setEnd(Coord end) {
    this.end = end;
  }

  /**
   * @return the part
   */
  public Part getPart() {
    return part;
  }

  /**
   * @param part the part to set
   */
  public void setPart(Part part) {
    this.part = part;
  }

  /**
   * @return the parent
   */
  public PartNode getParent() {
    return parent;
  }

  /**
   * @param parent the parent to set
   */
  public void setParent(PartNode parent) {
    this.parent = parent;
  }

  /**
   * @return the length
   */
  public Double getLength() {
    return length;
  }

  /**
   * @param length the length to set
   */
  public void setLength(Double length) {
    this.length = length;
  }

  /**
   * @return the reversed
   */
  public boolean isReversed() {
    return reversed;
  }

  /**
   * @param reversed the reversed to set
   */
  public void setReversed(boolean reversed) {
    if (reversed != this.reversed) {
      Coord temp = start;
      start = end;
      end = temp;
    }
    this.reversed = reversed;
  }

  /**
   * Gets the minimum distance between the envelope and the start or end point
   * for this node. Intermediate points are not considered.
   *
   * @param bounds
   * @return
   */
  public double distance(Envelope bounds) {
    return bounds.distance(end);
  }

  /**
   * The other part can be flipped, but can only be attached to the end of this
   * part. Consider the
   *
   * @param other
   * @return
   */
  public double distance(Part other) {
    double es = this.end.distance(other.start().get());
    double ee = this.end.distance(other.end().get());
    return Math.min(es, ee);
  }

  /**
   * The other part can be flipped, but can only be attached to the end of this
   * part, and only the endpoints of the other part are considered for joining.
   *
   * @param other
   * @return
   */
  public PartLink endpointLink(Part other) {
    PartLink result = new PartLink();
    result.setTreePartNode(this);
    result.setTreePartCoord(end);
    result.setFreePart(other);
    double es = this.end.distance(other.start().get());
    double ee = this.end.distance(other.end().get());
    if (es < ee) {
      result.setFreePartCoord(other.start().get());
      result.setDistance(es);
    } else {
      result.setFreePartCoord(other.end().get());
      result.setDistance(ee);
    }
    return result;
  }

  /**
   * The other part can be flipped, but can only be attached to the end of this
   * part, and only the endpoints of the other part are considered for joining.
   *
   * @param other
   * @return
   */
  public PartLink shortestLink(Part other) {
    PartLink result = new PartLink();
    result.setTreePartNode(this);
    result.setFreePart(other);
    Segment link = this.part.shortestLink(other).get();
    result.setTreePartCoord(link.getP1());
    result.setFreePartCoord(link.getP2());
    result.setDistance(link.length());
    return result;
  }

  /**
   * Gets the shortest distance between the end points of the two parts,
   * regardless of orientation.
   *
   * @param other
   * @return
   */
  public double distance(PartNode other) {
    double ss = this.start.distance(other.start);
    double se = this.start.distance(other.end);
    double es = this.end.distance(other.start);
    double ee = this.end.distance(other.end);
    double min = ss;
    if (se < min) {
      min = se;
    }
    if (es < min) {
      min = es;
    }
    if (ee < min) {
      min = ee;
    }
    return min;
  }

  /**
   * When adding to the root, you may have a closest point that is not on one
   * end or the other.
   *
   * @param other
   * @return the List of PartNodes that are added.
   */
  public List<PartNode> appendToRoot(PartNode other) {
    List<PartNode> result = new ArrayList<>();
    Coord c = other.getPart().closestPointTo(this.end).get();
    List<Coord> coords = other.getPart().getCoordinates();
    int imax = coords.size() - 1;
    if (c.equals(other.start) || c.equals(other.end)) {
      if (c.equals(other.end)) {
        other.setReversed(true);
      }
      other.setGap(this.end.distance(c));
      other.setParent(this);
      this.endNodes.add(other);
      result.add(other);
    } else {
      // split at the closest point.
      List<Part> parts = other.getPart().split(c, true);
      for (Part prt : parts) {
        PartNode subNode = new PartNode(prt);
        endNodes.add(subNode);
        subNode.setParent(this);
        result.add(subNode);
      }
    }
    return result;
  }

  /**
   * This will consider appending the other node either to the end location of
   * this part. It also ensures that the part is directed the same way. So that
   * the "end" point of this part should match the "start" node of the attached
   * part.
   *
   * @param other
   */
  public void append(PartNode other) {
    double es = this.end.distance(other.start);
    double ee = this.end.distance(other.end);
    this.endNodes.add(other);
    if (ee < es) {
      other.setReversed(true);
    }
    other.setParent(this);
    other.setGap(Math.min(es, ee));

  }

  /**
   * @return the endNodes
   */
  public List<PartNode> getEndNodes() {
    return endNodes;
  }

  /**
   * @param endNodes the endNodes to set
   */
  public void setEndNodes(List<PartNode> endNodes) {
    this.endNodes = endNodes;
  }

  /**
   * @return the gap
   */
  public double getGap() {
    return gap;
  }

  /**
   * @param gap the gap to set
   */
  public void setGap(double gap) {
    this.gap = gap;
  }

}
