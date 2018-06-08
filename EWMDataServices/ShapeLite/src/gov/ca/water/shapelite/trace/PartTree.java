/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.trace;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Part;
import gov.ca.water.shapelite.Shape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hdunsford
 */
public class PartTree {

  /**
   * An epsilon distance for counting points as the same.
   */
  public static final double SMALL_DISTANCE = 0.0001;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The starting point for the line extraction.
   */
  private Coord source;
  /**
   * The ending point for the line extraction.
   */
  private Coord target;
  /**
   * The PartNode that acts as the root node, or the start of the tree.
   */
  private final PartNode root;

  /**
   * The list of all the part nodes in the tree, regardless of their anchoring.
   */
  private List<PartNode> allNodes;
  /**
   * A boolean that if true will include target and source in the returned line,
   * even if target and source are not on the line.
   *
   */
  private boolean includingEndpoints;

  //</editor-fold>
  /**
   * Creates a new instance of the PartTree class.
   *
   * @param source The starting point for the line to extract. This does not
   * have to be exactly on line shapes.
   * @param target The ending point for hte line to extract. This does not have
   * to be exactly on the line shapes.
   */
  public PartTree(Coord source, Coord target) {
    this.source = source;
    this.target = target;
    allNodes = new ArrayList<>();
    // Since the start coordinate could be a branch point, reflect this by
    // creating a root node that is only one coordinate long.
    Part rootPart = new Part();
    rootPart.getCoordinates().add(source);
    root = new PartNode(rootPart);
    allNodes.add(root);
    includingEndpoints = false;
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * This only cares about the network joining the start to the end. Shapes are
   * added until the target is reached.
   *
   * @param shapes the list of shapes to add to the tree to be considered as
   * part of the line.
   */
  public final void addShapes(List<Shape> shapes) {
    List<Part> allParts = new ArrayList<>();
    for (Shape shape : shapes) {
      for (Part prt : shape.getParts()) {
        allParts.add(prt);
      }
    }

    Double closestDist = null;
    Part targPart = null;
    for (Part part : allParts) {
      double dist = part.distance(this.target).get();
      if (closestDist == null || dist < closestDist) {
        targPart = part;
        closestDist = dist;
      }
    }

    while (!allParts.isEmpty()) {
      PartLink bestLink;
      if (allNodes.size() == 1) {
        bestLink = getLink(allParts, false);
      } else {
        bestLink = getLink(allParts, true);
        // If no endpoints touch, find the closest part
        // to the tree using the closest possible route,
        // even if it goes off line.
        if (bestLink.getDistance() > SMALL_DISTANCE) {
          bestLink = getLink(allParts, false);
        }
      }
      allParts.remove(bestLink.getFreePart());
      addPartLink(bestLink);
      if (bestLink.getFreePart().equals(targPart)) {
        // don't bother adding any more to the tree once we have included
        // the target part in the tree.
        break;
      }
    }
  }

  /**
   * This method will return the shortest path from the source to the target.
   *
   * @return the list of coordinates for the path.
   */
  public final List<Coord> getPath() {
    // In tree, we find the closest to the target and work backwards.
    Double closestDistance = null;
    PartNode targetNode = null;
    for (PartNode node : allNodes) {
      Double dist = node.getPart().distance(target).get();
      if (closestDistance == null || dist < closestDistance) {
        targetNode = node;
        closestDistance = dist;
      }
    }
    List<Coord> coords = new ArrayList<>();
    if (includingEndpoints) {
      coords.add(target);
    }

    if (targetNode != null) {
      if (targetNode.getPart().getCoordinates().size() == 1) {
        coords.add(0, targetNode.getStart());
      } else {
        Coord c = targetNode.getPart().closestPointTo(target).get();
        List<Part> items = targetNode.getPart().split(c, false);
        if (items.size() > 1) {
          List<Coord> parentCoords = items.get(0).getCoordinates();
          if (targetNode.isReversed()) {
            parentCoords = items.get(1).getCoordinates();
            Collections.reverse(parentCoords);
          }
          coords.addAll(0, parentCoords);
        }
        if (items.size() == 1) {
          List<Coord> targCoords = new ArrayList<>(targetNode.getPart().getCoordinates());
          if (targetNode.isReversed()) {
            Collections.reverse(targCoords);
          }
          if (c.equals(targCoords.get(0))) {
            // closest point was start point. just use start point.
            coords.add(0, targetNode.getStart());
          } else if (c.equals(targCoords.get(targCoords.size() - 1))) {
            coords.addAll(0, targCoords);
          }
        }
      }

      PartNode parent = targetNode.getParent();
      while (parent != null) {
        List<Coord> parentCoords = new ArrayList<>(parent.getPart().getCoordinates());
        if (parent.isReversed()) {
          Collections.reverse(parentCoords);
        }
        coords.addAll(0, parentCoords);
        parent = parent.getParent();
      }

    }
    if (!this.isIncludingEndpoints()) {
      coords.remove(0);
    }
    return coords;
  }

  /**
   * Gets the complete description of the link between the specified part and
   * the current tree. Only endpoints are considered, in hopes of finding a way
   * to add the part to the tree by joining the endpoint someplace that it
   * touches.
   *
   * @param part The Part to find a link to.
   * @return the PartLink, showing the part and the connecting segment.
   */
  public final PartLink endpointLink(Part part) {
    double shortestDistance = Double.MAX_VALUE;
    PartLink result = null;
    List<PartLink> touching = new ArrayList<>();
    for (PartNode node : allNodes) {
      PartLink link = node.endpointLink(part);
      double dist = link.getDistance();
      if (dist < SMALL_DISTANCE) {
        touching.add(link);
      }
      if (touching.isEmpty()) {
        if (dist < shortestDistance) {
          shortestDistance = dist;
          result = link;
        }
      }

    }
    if (touching.isEmpty()) {
      return result;
    }

    // Sometimes there is more than one way to connect the network.
    // In those cases, preferentially connect each piece to the
    // point in the network closest to the root.
    double treeLength = Double.MAX_VALUE;
    for (PartLink link : touching) {
      if (link.getTreePartNode().getLength() < treeLength) {
        result = link;
        treeLength = link.getTreePartNode().treeLength();
      }
    }
    return result;

  }

  /**
   * Similar to the endpointLink result, but instead completely describes the
   * connection of the closest segment connecting any position on the existing
   * tree to any position on the other part.
   *
   * @param part The part to find the shortest connection to.
   * @return The PartLink that lists the part and the segment connection.
   */
  public final PartLink shortestLink(Part part) {
    double shortestDistance = Double.MAX_VALUE;
    PartLink result = null;
    for (PartNode node : allNodes) {
      PartLink link = node.shortestLink(part);
      double dist = link.getDistance();
      if (dist < shortestDistance) {
        shortestDistance = dist;
        result = link;
      }
    }
    return result;
  }

  /**
   * Gets the shortest distance as measured by the actual parts. This takes
   * longer to calculate, but allows for joins that are not exclusively at the
   * endpoints.
   *
   * @param part The part to get the distance to.
   * @return The double distance to the specified part.
   */
  public final double partDistance(Part part) {
    double shortestDistance = Double.MAX_VALUE;
    for (PartNode node : allNodes) {
      double dist = node.getPart().distance(part).get();
      if (dist < shortestDistance) {
        shortestDistance = dist;
      }
    }
    return shortestDistance;
  }

  /**
   * This finds the PartLink describing the physical connection between one of
   * the existing PartNodes in the tree and one of the parts in the specified
   * list of parts.
   *
   * @param parts the List of parts to consider
   * @param endpoint Boolean, if true, then only the endpoints of both the
   * existing tree parts and the potential new part are considered.
   * @return Either the first instance of a zero distance, or else the closest
   * link.
   */
  private PartLink getLink(List<Part> parts, boolean endpoint) {
    PartLink result = null;

    for (Part part : parts) {
      PartLink link;
      if (endpoint) {
        link = endpointLink(part);
      } else {
        link = shortestLink(part);
      }
      if (link != null) {
        if (link.getDistance() == 0) {
          return link;
        } else {
          if (result == null || link.getDistance() < result.getDistance()) {
            result = link;
          }
        }
      }

    }
    return result;
  }

  /**
   * Adds the part preferably to the part of the tree that it actually touches.
   * If it doesn't touch, then add to the closest spot. If it touches in
   * multiple places, add to the place with the shortest distance to the source.
   *
   * @param link the description of how the part should be connected.
   */
  private void addPartLink(PartLink link) {
    link.connect(allNodes);

  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the starting point for the line extraction.
   *
   * @return the source
   */
  public final Coord getSource() {
    return source;
  }

  /**
   * Sets the starting point for the line extraction.
   *
   * @param source the source to set
   */
  public final void setSource(Coord source) {
    this.source = source;
  }

  /**
   * Gets the ending point for the line extraction.
   *
   * @return the target
   */
  public final Coord getTarget() {
    return target;
  }

  /**
   * Sets the ending point for the line extraction.
   *
   * @param target the target to set
   */
  public final void setTarget(Coord target) {
    this.target = target;
  }

  /**
   * Gets the list of all the part nodes in the tree, regardless of their
   * anchoring.
   *
   * @return the allNodes
   */
  public final List<PartNode> getAllNodes() {
    return allNodes;
  }

  /**
   * Sets the list of all the part nodes in the tree, regardless of their
   * anchoring.
   *
   * @param allNodes the allNodes to set
   */
  public final void setAllNodes(List<PartNode> allNodes) {
    this.allNodes = allNodes;
  }

  /**
   * Gets a boolean that if true will include target and source in the returned
   * line, even if target and source are not on the line.
   *
   * @return the includingEndpoints
   */
  public final boolean isIncludingEndpoints() {
    return includingEndpoints;
  }

  /**
   * Sets a boolean that if true will include target and source in the returned
   * line, even if target and source are not on the line.
   *
   * @param includeEndpoints the includingEndpoints to set
   */
  public final void setIncludingEndpoints(boolean includeEndpoints) {
    this.includingEndpoints = includeEndpoints;
  }

  //</editor-fold>
}
