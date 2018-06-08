/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.projection.ProjectionInfo;

/**
 * A buffer distance in feet and a projection to allow calculating the projected
 * buffer distance.
 *
 * @author clay
 */
public final class ProjectedBuffer {

  /**
   * The double buffer distance in feet.
   */
  private double bufferFeet;
  /**
   * The ProjectionInfo defining the projection.
   */
  private ProjectionInfo projection;

  /**
   * Creates a new instance of the ProjectedBuffer class.
   *
   * @param projection The ProjectionInfo defining the spatial reference system.
   * @param bufferFeet The buffer distance in feet.
   */
  public ProjectedBuffer(ProjectionInfo projection, double bufferFeet) {
    this.projection = projection;
    this.bufferFeet = bufferFeet;

  }

  /**
   * Gets the double buffer distance in feet.
   *
   * @return the bufferFeet
   */
  public double getBufferFeet() {
    return bufferFeet;
  }

  /**
   * Gets the projected distance for the buffer specified in feet.
   *
   * @return The double distance in projected units.
   */
  public double getBufferDistance() {
    if (projection != null) {
      return projection.feetToProj(bufferFeet);
    }
    return 0;
  }

  /**
   * Sets the projected distance to use as a buffer, which will modify the
   * bufferFeet of this class.
   *
   * @param projectedDistance Sets the projected Distance.
   */
  public void setBufferDistance(double projectedDistance) {
    if (projection != null) {
      this.bufferFeet = projection.projToFeet(projectedDistance);
    }
  }

  /**
   * Sets the double buffer distance in feet.
   *
   * @param bufferFeet the bufferFeet to set
   */
  public void setBufferFeet(double bufferFeet) {
    this.bufferFeet = bufferFeet;
  }

  /**
   * @return the bufferProjected
   */
  public double getBufferProjected() {
    if (this.projection != null && bufferFeet != 0) {
      return projection.feetToProj(bufferFeet);
    }
    return 0;
  }

  /**
   * Gets the ProjectionInfo defining the projection.
   *
   * @return the projection
   */
  public ProjectionInfo getProjection() {
    return projection;
  }

  /**
   * Sets the ProjectionInfo defining the projection.
   *
   * @param projection the projection to set
   */
  public void setProjection(ProjectionInfo projection) {
    this.projection = projection;
  }
}
