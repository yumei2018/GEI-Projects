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

import gov.ca.water.shapelite.coordinate.ClosestCoord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * The PartTree contains the business logic of organizing linear parts that may
 * be out of order, or reversed, so that they can be iterated across
 * sequentially for calculating distances.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartTree {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The distance used to determine when two coordinates are distinct locations.
   */
  private final double epsilon;

  /**
   * The root, or base PartTreeNode that serves as the starting point.
   */
  private PartTreeNode root;

  /**
   * The preferred starting location for the entire tree. As parts are
   * organized, this point will be used to pick the starting segment.
   */
  private Coord start;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * This creates a default part Tree with an Epsilon of 1, where epsilon is the
   * maximum distance two points can be from one another and be considered
   * equal.
   */
  public PartTree() {
    //freeParts = new ArrayList<Part>();
    epsilon = 1;
  }

  /**
   * This creates a part tree where the distance between points being considered
   * to touch is specified by epsilon.
   *
   * @param epsilon The smallest separation that should be considered the same
   * position.
   */
  public PartTree(double epsilon) {
    //freeParts = new ArrayList<Part>();
    this.epsilon = epsilon;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Adds the specified shape to the tree. This will add all of the parts from
   * the specified shape into the tree for analysis.
   *
   * @param shape The shape object that contains all of the parts. If this is
   * null, or a NullShape, then this does nothing.
   * @param start The starting point for the shape. If this is null
   * this does nothing.
   */
  public final void add(Shape shape, Coord start) {
    if (shape == null || shape.isEmpty()) {
      return;
    }
    if (start == null) {
      return;
    }
    List<Part> parts = new ArrayList<>();
    parts.addAll(shape.getParts());
    PartLink link = getClosestLink(parts, start);
    parts.remove(link.getPart());
    if (link.isFlipped()) {
      link.getPart().reverse();
    }
    root = new PartTreeNode(link.getPart());
    root.setParentTree(this);

    while (parts.size() > 0) {
      List<PartLink> links = new ArrayList<>();
      List<PartTreeNode> nodes = this.getConnectedNodes();
      for (PartTreeNode node : nodes) {
        Optional<Coord> target = node.end();
        if (target.isPresent()) {
          PartLink l = getClosestLink(parts, target.get());
          links.add(l);
          l.setAnchor(node);
        }
      }
      Collections.sort(links);
      link = links.get(0);
      if (link.isFlipped()) {
        link.getPart().reverse();
      }
      PartTreeNode tail = new PartTreeNode(link.getPart());
      tail.setParent(link.getAnchor());
      tail.setParentTree(this);
      tail.setGap(link.getDistance());
      link.getAnchor().getNodes().add(tail);

      parts.remove(link.getPart());
    }

  }

  /**
   * Gets the closest PartTreeNode in the current tree to the specified
   * location.
   *
   * @param location The coordinate to get the location for.
   * @return A PartTreeNode object.
   */
  public final PartTreeNode closestNode(Coord location) {
    List<PartTreeNode> nodes = getConnectedNodes();
    double mindistance = Double.MAX_VALUE;
    PartTreeNode result = null;
    for (PartTreeNode node : nodes) {
      Optional<Double> dist = node.getPart().distance(location);
      if (dist.isPresent()) {
        if (dist.get() < mindistance) {
          mindistance = dist.get();
          result = node;
        }
      }

    }
    return result;
  }

  /**
   * Calculates the distance in whatever units the coordinates are in. So if the
   * system is UTM, the distance units are in meters.
   *
   * @param location The GIS coordinate to find the distance to.
   * @return The double distance in GIS units (like meters for UTM).
   */
  public final double distanceAlong(Coord location) {
    PartTreeNode node = closestNode(location);
    double minDistance = Double.MAX_VALUE;
    Segment closestSeg = null;
    double segDistance = node.startDistance();
    double tail = 0;
    for (Segment seg : node.getPart().getSegments()) {
      double dist = seg.distanceTo(location);
      if (dist < minDistance) {
        // If we skipped any segments, we have to add them back in now
        segDistance += tail;
        tail = 0;

        // If the previous segment was the closest, we now have to add
        // its length
        if (closestSeg != null) {
          segDistance += closestSeg.length();
        }
        minDistance = dist;
        closestSeg = seg;
      } else {
        tail += seg.length();
      }
    }
    if (closestSeg != null) {
      ClosestCoord cp = closestSeg.closestPointTo(location);
      double partialDistance = cp.getCoord().distance(closestSeg.getP1());
      segDistance += partialDistance;
    }
    return segDistance;
  }

  /**
   * Calculates the distance in whatever units the coordinates are in. So if the
   * system is UTM, the distance units are in meters.
   *
   * @param location The Coordinate location to look for the distance to.
   * @param maxSeg the maximum segment index that will be considered.
   * @return The double distance in GIS units (like meters for UTM).
   */
  public final double distanceAlong(Coord location, int maxSeg) {
    PartTreeNode node = closestNode(location);
    double minDistance = Double.MAX_VALUE;
    Segment closestSeg = null;
    double segDistance = node.startDistance();
    double tail = 0;
    int iSeg = 0;
    for (Segment seg : node.getPart().getSegments()) {
      double cl = 0;
      if (closestSeg != null) {
        cl = closestSeg.length();
      }
      if (iSeg == maxSeg) {
        return segDistance + cl + tail;
      }
      double dist = seg.distanceTo(location);
      if (dist < minDistance) {
        // If we skipped any segments, we have to add them back in now
        segDistance += tail;
        tail = 0;

        // If the previous segment was the closest, we now have
        // to add its length
        if (closestSeg != null) {
          segDistance += closestSeg.length();
        }

        minDistance = dist;
        closestSeg = seg;
      } else {
        tail += seg.length();
      }
      iSeg++;
    }
    if (closestSeg != null) {
      ClosestCoord cp = closestSeg.closestPointTo(location);
      double partialDistance = cp.getCoord().distance(closestSeg.getP1());
      segDistance += partialDistance;
    }
    return segDistance;
  }

  /**
   * Gets the closest physical distance to the specified location. This ignores
   * any free nodes.
   *
   * @param location The coordinate to get the distance to.
   * @return The double precision distance.
   */
  public final double distanceTo(Coord location) {
    List<PartTreeNode> nodes = this.getConnectedNodes();
    double minDistance = Double.MAX_VALUE;
    for (PartTreeNode node : nodes) {

      Optional<Double> dist = node.getPart().distance(location);
      if (dist.isPresent()) {
        if (dist.get() < minDistance) {
          minDistance = dist.get();
        }
      }

    }
    return minDistance;
  }

  /**
   * Given the list of parts and a target coordinate, this will create a
   * PartLink that joins the parts to the specified coordinate.
   *
   * @param parts the parts to link.
   * @param target The target coordinate.
   * @return The PartLink that contains information about the distance, and
   * whether or not the part should be reversed.
   */
  private PartLink getClosestLink(List<Part> parts, Coord target) {
    double minDist = Double.MAX_VALUE;
    boolean needsFlip = false;
    Part closest = null;
    for (Part part : parts) {
      Optional<Coord> startCoord = part.start();
      Optional<Coord> end = part.end();
      if (startCoord.isPresent() && end.isPresent()) {
        double startDist = startCoord.get().distance(target);
        double endDist = end.get().distance(target);
        double dist = Math.min(startDist, endDist);
        if (dist < minDist) {
          closest = part;
          minDist = dist;
          needsFlip = endDist < startDist;
        }
      }

    }
    return new PartLink(closest, minDist, needsFlip);
  }

  /**
   * Gets the list of connected nodes in the PartTree.
   *
   * @return the list of connected nodes in the part tree.
   */
  public final List<PartTreeNode> getConnectedNodes() {
    List<PartTreeNode> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Stack<PartTreeNode> unfinished = new Stack<>();
    unfinished.push(root);
    while (unfinished.size() > 0) {
      PartTreeNode current = unfinished.pop();
      for (PartTreeNode node : current.getNodes()) {
        unfinished.push(node);
      }
      result.add(current);
    }
    Collections.sort(result);
    return result;
  }

  /**
   * Gets the distance to the longest segment. The units will depend on the
   * coordinate system. If it is UTM, for instance, the distance is meters.
   *
   * @return The double length.
   */
  public final double length() {
    List<PartTreeNode> nodes = getConnectedNodes();
    double maxLength = 0;
    for (PartTreeNode node : nodes) {
      if (node.endDistance() > maxLength) {
        maxLength = node.endDistance();
      }
    }
    return maxLength;
  }

  /**
   * Gets the list of partTreeNodes that would have mile markers at the
   * specified distance. This is a list because forks in rivers might cause the
   * same mile distance to appear on separate paths.
   *
   * @param distance The double precision distance.
   * @return The List of all the PartTreeNode objects that contain a point that
   * satisfies the distance.
   */
  public final List<PartTreeNode> nodesAt(double distance) {
    List<PartTreeNode> result = new ArrayList<>();
    Stack<PartTreeNode> nodes = new Stack<>();
    nodes.push(root);
    while (nodes.size() > 0) {
      PartTreeNode current = nodes.pop();
      double startDist = current.startDistance();
      if (distance >= startDist) {
        if (distance <= startDist + current.length()) {
          result.add(current);
        }
        if (distance > startDist + current.length()) {
          for (PartTreeNode node : current.getNodes()) {
            nodes.push(node);
          }
        }
      }
    }
    return result;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the root, or base PartTreeNode that serves as the starting point.
   *
   * @return the root PartTreeNode.
   */
  public final PartTreeNode getRoot() {
    return root;
  }

  /**
   * Sets the root, or base PartTreeNode that serves as the starting point.
   *
   * @param root the root PartTreeNode to set.
   */
  public final void setRoot(PartTreeNode root) {
    this.root = root;
  }

  /**
   * Gets the preferred starting location for the entire tree. As parts are
   * organized, this point will be used to pick the starting segment.
   *
   * @return the startCoord Coordinate.
   */
  public final Coord getStart() {
    return start;
  }

  /**
   * Sets the preferred starting location for the entire tree. As parts are
   * organized, this point will be used to pick the starting segment.
   *
   * @param start the startCoord Coordinate to set.
   */
  public final void setStart(Coord start) {
    this.start = start;
  }

  /**
   * @return the epsilon
   */
  public final double getEpsilon() {
    return epsilon;
  }

  //</editor-fold>
}
