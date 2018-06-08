/*
 * The MIT License
 *
 * Copyright 2014 hdunsford.
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
package gov.ca.water.shapelite.projection;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public abstract class NadTable {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * Converts degree values into radians.
   */
  public static final double DEG_TO_RAD = Math.PI / 180;

  /**
   * I think this converts micro-seconds of arc to radians.
   */
  public static final double USEC_TO_RAD = 4.848136811095359935899141023e-12;

  /**
   * The delta lambda and delta phi for a single cell.
   */
  private PhiLam cellSize;

  /**
   * The set of conversion matrix coefficients for lambda.
   */
  private PhiLam[][] cvs;

  /**
   * The long data offset.
   */
  private long dataOffset;

  /**
   * A Boolean that is true if an embedded projection table exists.
   */
  private boolean fileIsEmbedded;
  /**
   * A boolean that is true if the table is filled.
   */
  private boolean filled;
  /**
   * An enumeration specifying the format of grid shift table.
   */
  private GridShiftTableFormat format;

  /**
   * A String path representing the grid file path.
   */
  private String gridFilePath;

  /**
   * The lower left coordinate.
   */
  private PhiLam lowerLeft;

  /**
   * The String manifest resource.
   */
  private String manifestResourceString;

  /**
   * The character based id for this record.
   */
  private String name;

  /**
   * The total count of coordinates in the lambda direction.
   */
  private int numLambdas;

  /**
   * The total count of coordinates in the phi direction.
   */
  private int numPhis;

  /**
   * The list of NadTables that act as sub-grids.
   */
  private List<NadTable> subGrids;

  //</editor-fold>
  /**
   * Creates a blank nad Table.
   */
  public NadTable() {
    subGrids = new ArrayList<>();
  }

  /**
   * This initializes a new table with the assumption that the offset is 0.
   *
   * @param resourceLocation The resource location
   */
  public NadTable(String resourceLocation) {
    this(resourceLocation, 0);
  }

  /**
   * This initializes a new table with the assumption that the offset is 0.
   *
   * @param resourceLocation The resource location
   * @param embedded Indicates if grid file is in embedded resource or external
   * file
   */
  public NadTable(String resourceLocation, boolean embedded) {
    this(resourceLocation, 0, embedded);
  }

  /**
   * This initializes a new table with the assumption that the offset needs to
   * be specified because in gsb files, more than one table is listed, and this
   * is a subtable.
   *
   * @param resourceLocation The resource location
   * @param offset The offset marking the start of the header in the file
   */
  public NadTable(String resourceLocation, long offset) {
    this(resourceLocation, offset, true);
  }

  /**
   * This initializes a new table with the assumption that the offset needs to
   * be specified because in gsb files, more than one table is listed, and this
   * is a subtable. This also allows specifying if the grid file is included as
   * an embedded resource.
   *
   * @param location The resource (or file) location
   * @param offset The offset marking the start of the header in the file
   * @param embedded Indicates if embedded resource or external file
   */
  public NadTable(String location, long offset, boolean embedded) {
    subGrids = new ArrayList<NadTable>() {
    };

    if (embedded) {
      manifestResourceString = location;
    } else {
      gridFilePath = location;
    }
    fileIsEmbedded = embedded;
    dataOffset = offset;
  }
  //<editor-fold defaultstate="collapsed" desc="Methods">

  /**
   * Given the resource setup, this causes the file to read the header.
   */
  public abstract void readHeader();

  /**
   * This fills the actual table data.
   *
   * @throws ProjectionException if there was an error reading the data.
   */
  public abstract void fillData() throws ProjectionException;

  /**
   * This method parses the extension of a resource location or path and creates
   * the new NadTable type.
   *
   * @param resourceLocation The String path of the resource.
   * @return A NadTable.
   */
  public static NadTable fromSourceName(String resourceLocation) {
    return fromSourceName(resourceLocation, true);
  }

  /**
   * This method parses the extension of a resource location or path and creates
   * the new NadTable type.
   *
   * @param location THe String resourceLocation.
   * @param embedded Boolean, true if the resource is embedded.
   * @return A NadTable.
   */
  public static NadTable fromSourceName(String location, boolean embedded) {
    NadTable result = null;
    int p = location.indexOf(".");
    if (p > -1) {
      String ext = location.substring(p).toLowerCase();
      switch (ext) {
        case ".lla":
          result = new NadTableLla(location, embedded);
          break;
        case ".gsb":
          //result = new GsbNadTable(location, 0, embedded);
          break;
        case ".dat":
          // result = new DatNadTable(location, embedded);
          break;
        case ".los":
          // result = new LasLosNadTable(location, embedded);
          break;
      }
    }

    if (result != null) {
      result.readHeader();
    }
    return result;
  }

  //</editor-fold>
  /**
   * Reads a double in BigEndian format (consistent with ntv1 and ntv2 formats.)
   *
   * @param br The binary reader
   * @return The double value parsed from the binary in big endian byte order.
   */
  protected static double readBigDouble(DataInputStream br) throws IOException {
    byte[] temp = new byte[8];
    br.read(temp, 0, 8);
    ByteBuffer wrapper = ByteBuffer.wrap(temp);
    wrapper.order(ByteOrder.BIG_ENDIAN);
    double value = wrapper.getDouble();
    return value;
  }

  /**
   * Gets the double value from the specified position in the byte array Using
   * BigEndian format.
   *
   * @param array
   * @param offset
   * @return
   */
  protected double getBigDouble(byte[] array, int offset) {
    byte[] temp = new byte[8];
    System.arraycopy(array, offset, temp, 0, 8);
    ByteBuffer wrapper = ByteBuffer.wrap(temp);
    wrapper.order(ByteOrder.BIG_ENDIAN);
    double value = wrapper.getDouble();
    return value;
  }

  /**
   * Get the stream to the grid
   *
   * @return
   */
  protected InputStream getStream() {
    // Currently only the embedded ones are supported
    return this.getClass().getResourceAsStream("resources/" + manifestResourceString);
  }

  /**
   * Gets the angular cell size in radians
   *
   * @return the cellSize
   */
  public PhiLam getCellSize() {
    return cellSize;
  }

  /**
   * Sets the angular cell size in radians
   *
   * @param cellSize the cellSize to set
   */
  public void setCellSize(PhiLam cellSize) {
    this.cellSize = cellSize;
  }

  /**
   * Gets the array of lambda coefficients organized in a spatial Table (phi
   * major)
   *
   * @return the cvs
   */
  public PhiLam[][] getCvs() {
    return cvs;
  }

  /**
   * Sets the array of lambda coefficients organized in a spatial Table (phi
   * major)
   *
   * @param cvs the cvs to set
   */
  public void setCvs(PhiLam[][] cvs) {
    this.cvs = cvs;
  }

  /**
   * Gets the long integer data offset where the stream should skip to to begin
   * reading data
   *
   * @return the dataOffset
   */
  public long getDataOffset() {
    return dataOffset;
  }

  /**
   * Sets the long integer data offset where the stream should skip to to begin
   * reading data
   *
   * @param dataOffset the dataOffset to set
   */
  public void setDataOffset(long dataOffset) {
    this.dataOffset = dataOffset;
  }

  /**
   * True if grid file is an embedded resource
   *
   * @return the fileIsEmbedded
   */
  public boolean isFileIsEmbedded() {
    return fileIsEmbedded;
  }

  /**
   * True if grid file is an embedded resource
   *
   * @param fileIsEmbedded the fileIsEmbedded to set
   */
  public void setFileIsEmbedded(boolean fileIsEmbedded) {
    this.fileIsEmbedded = fileIsEmbedded;
  }

  /**
   * Gets a boolean indicating whether or not the values have been filled.
   *
   * @return the filled
   */
  public boolean isFilled() {
    return filled;
  }

  /**
   * Sets a boolean indicating whether or not the values have been filled.
   *
   * @param filled the filled to set
   */
  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  /**
   * Gets the format being used.
   *
   * @return the format
   */
  public GridShiftTableFormat getFormat() {
    return format;
  }

  /**
   * Sets the format being used.
   *
   * @param format the format to set
   */
  public void setFormat(GridShiftTableFormat format) {
    this.format = format;
  }

  /**
   * If FileIsEmbedded is false, this contains the full path to the grid file
   *
   * @return the gridFilePath
   */
  public String getGridFilePath() {
    return gridFilePath;
  }

  /**
   * If FileIsEmbedded is false, this contains the full path to the grid file
   *
   * @param gridFilePath the gridFilePath to set
   */
  public void setGridFilePath(String gridFilePath) {
    this.gridFilePath = gridFilePath;
  }

  /**
   * Gets the lower left corner in radians
   *
   * @return the lowerLeft
   */
  public PhiLam getLowerLeft() {
    return lowerLeft;
  }

  /**
   * Sets the lower left corner in radians
   *
   * @param lowerLeft the lowerLeft to set
   */
  public void setLowerLeft(PhiLam lowerLeft) {
    this.lowerLeft = lowerLeft;
  }

  /**
   * Gets the location this table should look for source data.
   *
   * @return the manifestResourceString
   */
  public String getManifestResourceString() {
    return manifestResourceString;
  }

  /**
   * Sets the location this table should look for source data.
   *
   * @param manifestResourceString the manifestResourceString to set
   */
  public void setManifestResourceString(String manifestResourceString) {
    this.manifestResourceString = manifestResourceString;
  }

  /**
   * Gets the string name for this record
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the string name for this record
   *
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the integer count of lambda coefficients
   *
   * @return the numLambdas
   */
  public int getNumLambdas() {
    return numLambdas;
  }

  /**
   * Sets the integer count of lambda coefficients
   *
   * @param numLambdas the numLambdas to set
   */
  public void setNumLambdas(int numLambdas) {
    this.numLambdas = numLambdas;
  }

  /**
   * Gets the integer count of phi coefficients
   *
   * @return the numPhis
   */
  public int getNumPhis() {
    return numPhis;
  }

  /**
   * Sets the integer count of phi coefficients
   *
   * @param numPhis the numPhis to set
   */
  public void setNumPhis(int numPhis) {
    this.numPhis = numPhis;
  }

  /**
   * These represent smaller, higher resolution subgrids that should fall within
   * the extents of the larger grid. If this list exists, and there is a fit
   * here, it should be used in preference to the low-resolution main grid.
   *
   * @return the subGrids
   */
  public List<NadTable> getSubGrids() {
    return subGrids;
  }

  /**
   * These represent smaller, higher resolution subgrids that should fall within
   * the extents of the larger grid. If this list exists, and there is a fit
   * here, it should be used in preference to the low-resolution main grid.
   *
   * @param subGrids the subGrids to set
   */
  public void setSubGrids(List<NadTable> subGrids) {
    this.subGrids = subGrids;
  }
}
