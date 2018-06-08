/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.data;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ColRow {

  /**
   * The number of columns.
   */
  private static final int WIDTH = 256;

  /**
   * The column index.
   */
  private int column;

  /**
   * The row index.
   */
  private int row;

  /**
   * Creates a new instance of this RowCol object with the specified column and row.
   *
   * @param x The column index.
   * @param y The row index.
   */
  public ColRow(int x, int y) {
    this.column = x;
    this.row = y;
  }

  /**
   * Tests equality with the other ColRow object.
   *
   * @param obj The object to compare to.
   * @return Boolean, true if the column and row properties are equal.
   */
  @Override
  public final boolean equals(Object obj) {
    if (obj instanceof ColRow) {
      ColRow other = (ColRow) obj;
      return other.column == column && other.row == row;
    }
    return false;
  }

  /**
   * Gets a hashcode.
   *
   * @return The integer hash code.
   */
  @Override
  public final int hashCode() {
    return row * WIDTH + column;
  }

  /**
   * @return the column
   */
  public final int getColumn() {
    return column;
  }

  /**
   * @param x the column to set
   */
  public final void setColumn(int x) {
    this.column = x;
  }

  /**
   * @return the row
   */
  public final int getRow() {
    return row;
  }

  /**
   * @param y the row to set
   */
  public final void setRow(int y) {
    this.row = y;
  }
}
