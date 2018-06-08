/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.trace;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hdunsford
 */
public class PartLink {

  private PartNode treePartNode;
  private Coord treePartCoord;
  private Part freePart;
  private Coord freePartCoord;
  private double distance;

  /**
   * @return the treePartCoord
   */
  public Coord getTreePartCoord() {
    return treePartCoord;
  }

  /**
   * @param treePartCoord the treePartCoord to set
   */
  public void setTreePartCoord(Coord treePartCoord) {
    this.treePartCoord = treePartCoord;
  }

  /**
   * @return the freePart
   */
  public Part getFreePart() {
    return freePart;
  }

  /**
   * @param freePart the freePart to set
   */
  public void setFreePart(Part freePart) {
    this.freePart = freePart;
  }

  /**
   * @return the freePartCoord
   */
  public Coord getFreePartCoord() {
    return freePartCoord;
  }

  /**
   * @param freePartCoord the freePartCoord to set
   */
  public void setFreePartCoord(Coord freePartCoord) {
    this.freePartCoord = freePartCoord;
  }

  /**
   * @return the treePartNode
   */
  public PartNode getTreePartNode() {
    return treePartNode;
  }

  /**
   * @param treePartNode the treePartNode to set
   */
  public void setTreePartNode(PartNode treePartNode) {
    this.treePartNode = treePartNode;
  }

  /**
   * @return the distance
   */
  public double getDistance() {
    return distance;
  }

  /**
   * @param distance the distance to set
   */
  public void setDistance(double distance) {
    this.distance = distance;
  }

  /**
   * Implements the link by adding the new part to the shape. This may involve
   * splitting one or both of the parts involved if the coordinate doesn't match
   * an endpoint.
   *
   * @param allNodes The global list of connected part nodes to modify by making
   * connections.
   */
  public void connect(List<PartNode> allNodes) {
    int index = this.treePartNode.getPart().indexOfClosest(treePartCoord).get();
    int numPts = this.treePartNode.getPart().getCoordinates().size();
    Coord treeIndexCoord = this.treePartNode.getPart().getCoordinates().get(index);
    int freeIndex = this.freePart.indexOfClosest(freePartCoord).get();
    Coord freeIndexCoord = this.freePart.getCoordinates().get(freeIndex);
    PartNode parent = this.treePartNode;
    int parentSize = this.treePartNode.getPart().getCoordinates().size();
    if ((index == numPts - 1 && treePartCoord.equals(treeIndexCoord)) || parentSize == 1) {
      // no modification of parent is necessary, do nothing

    } else if (index == 0 && treePartCoord.equals(treeIndexCoord)) {
      // no splitting of parent tree but restructure tree so that
      // we can create a part if necessary representing the first point

      // create a part from the first point.
      Part startPoint = new Part(this.treePartNode.getStart());
      parent = new PartNode(startPoint);
      PartNode grandpa = treePartNode.getParent();
      grandpa.getEndNodes().remove(treePartNode);
      grandpa.append(parent);
      allNodes.add(parent);

    } else {

      // Splitting parent tree is necessary
      PartNode grandpa = treePartNode.getParent();
      allNodes.remove(treePartNode);
      grandpa.getEndNodes().remove(treePartNode);
      if (treePartNode.isReversed()) {
        Collections.reverse(treePartNode.getPart().getCoordinates());
      }
      List<Part> parentParts = treePartNode.getPart().split(this.treePartCoord, false);
      if (parentParts.size() == 1) {
        if (treePartCoord.equals(treePartNode.getPart().start())) {
          // use first coord as parent
          Part start = new Part(treePartNode.getPart().start().get());

          parent = new PartNode(start);
          allNodes.add(parent);
          grandpa.append(parent);
          PartNode child = new PartNode(parentParts.get(0));
          parent.append(child);
          allNodes.add(child);
        } else {
          parent = new PartNode(parentParts.get(0));
          grandpa.append(parent);
          allNodes.add(parent);
        }
      } else {

        parent = new PartNode(parentParts.get(0));
        grandpa.append(parent);
        allNodes.add(parent);
        PartNode parentBranch = new PartNode(parentParts.get(1));
        parent.append(parentBranch);
        allNodes.add(parentBranch);
      }
    }

    // Now that the parent is set up correctly, handle the child.
    int freeNumPts = this.freePart.getCoordinates().size();
    if ((freeIndex == 0 || freeIndex == freeNumPts - 1) && freeIndexCoord.equals(this.freePartCoord)) {
      // just append the part as a new node.
      PartNode addedNode = new PartNode(freePart);
      parent.append(addedNode);
      allNodes.add(addedNode);
    } else {
      // split the new part
      List<Part> parts = freePart.split(freePartCoord, true);
      for (Part addedPart : parts) {
        PartNode addedNode = new PartNode(addedPart);
        parent.append(addedNode);
        allNodes.add(addedNode);
      }
    }
  }

}
