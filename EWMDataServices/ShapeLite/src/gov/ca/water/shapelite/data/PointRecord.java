/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.data;

import gov.ca.water.shapelite.coordinate.CoordZ;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointRecord {

  /**
   * A scale factor used by point records to store integer values.
   */
  private static final double SCALE_FACTOR = 100.0;

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The integer index in the master LAS file.
   */
  private int id;
  /**
   * The integer X coordinate, multiplied by 100.
   */
  private int x;
  /**
   * The integer y coordinate, multiplied by 100.
   */
  private int y;
  /**
   * The integer z coordinate, multiplied by 100.
   */
  private int z;

  //</editor-fold>
  /**
   * Creates a new instance of the PointRecord class.
   */
  public PointRecord() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
  /**
   * Gets The integer X coordinate, multiplied by 100.
   *
   * @return the x
   */
  public final int getX() {
    return x;
  }

  /**
   * Sets The integer X coordinate, multiplied by 100.
   *
   * @param x the x to set
   */
  public final void setX(int x) {
    this.x = x;
  }

  /**
   * Gets the integer y coordinate, multiplied by 100.
   *
   * @return the y
   */
  public final int getY() {
    return y;
  }

  /**
   * Sets the integer y coordinate, multiplied by 100.
   *
   * @param y the y to set
   */
  public final void setY(int y) {
    this.y = y;
  }

  /**
   * Gets the integer z coordinate, multiplied by 100.
   *
   * @return the z
   */
  public final int getZ() {
    return z;
  }

  /**
   * Sets the integer z coordinate, multiplied by 100.
   *
   * @param z the z to set
   */
  public final void setZ(int z) {
    this.z = z;
  }

  /**
   * Gets the integer index in the master LAS file.
   *
   * @return the id
   */
  public final int getId() {
    return id;
  }

  /**
   * Sets the integer index in the master LAS file.
   *
   * @param id the id to set
   */
  public final void setId(int id) {
    this.id = id;
  }

  /**
   * Converts the point record into a coordinate by dividing by the scale
   * factor and returning the CoordZ in UTM Foot coordinates.
   *
   * @return The UTM Foot coordinates.
   */
  public final CoordZ toCoord() {
    CoordZ result = new CoordZ();
    result.setX(x / SCALE_FACTOR);
    result.setY(y / SCALE_FACTOR);
    result.setZ(z / SCALE_FACTOR);
    return result;
  }

}
