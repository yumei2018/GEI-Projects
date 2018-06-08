package gov.ca.water.math.matrixes;

/**
 * From: Java Number Cruncher
 * The Java Programmer's Guide to Numerical Computation
 * by Ronald Mak 
 * Original Code:  org.jgrasstools.gears.utils.math.matrixes.Matrix @
 * http://grepcode.com/snapshot/repo1.maven.org/maven2/org.jgrasstools/jgt-jgrassgears/0.7.3/
 * @author Modified by J.G. "Koos" Prins"
 *
 * The matrix class 
 */
public class Matrix
{
  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * number of rows
   */
  protected int numRows;
  /**
   * number of columns
   */
  protected int numCols;
  /**
   * 2-d array of  values
   */
  protected double values[][];
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Default constructor.
   */
  protected Matrix() {}
  
  /**
   * Constructor.
   * @param rowCount the number of rows
   * @param colCount the number of columns
   */
  public Matrix(int rowCount, int colCount) {
    this.numRows  = (rowCount > 0) ? rowCount : 1;
    this.numCols  = (colCount > 0) ? colCount : 1;
    this.values = new double[this.numRows][this.numCols];
  }
  
  /**
   * Constructor.
   * @param values the 2-d array of values
   */
  public Matrix(double values[][]) { 
    this.set(values); 
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Get the row count.
   * @return the row count
   */
  public int rowCount() { 
    return this.numRows; 
  }
  
  /**
   * Get the column count.
   * @return the column count
   */
  public int columnCount() { 
    return this.numCols; 
  }
  
  /**
   * Get the value of element [r,c] in the matrix.
   * @param r the row index
   * @param c the column index
   * @return the value
   * @throws numbercruncher.MatrixException for an invalid index
   */
  public double at(int r, int c) throws MatrixException {
    if ((r < 0) || (r >= this.numRows) || (c < 0) || (c >= this.numCols)) {
      throw new MatrixException(MatrixException.INVALID_INDEX);
    }
    
    return this.values[r][c];
  }
  
  /**
   * Get a row of this matrix.
   * @param r the row index
   * @return the row as a row vector
   * @throws numbercruncher.MatrixException for an invalid index
   */
  public RowVector getRow(int r) throws MatrixException {
    if ((r < 0) || (r >= this.numRows)) {
      throw new MatrixException(MatrixException.INVALID_INDEX);
    }
    
    RowVector result = new RowVector(this.numCols);
    for (int c = 0; c < this.numCols; ++c) {
      result.values[0][c] = this.values[r][c];
    }
    
    return result;
  }
  
  /**
   * Get a column of this matrix.
   * @param c the column index
   * @return the column as a column vector
   * @throws numbercruncher.MatrixException for an invalid index
   */
  public ColumnVector getColumn(int c) throws MatrixException {
    if ((c < 0) || (c >= this.numCols)) {
      throw new MatrixException(MatrixException.INVALID_INDEX);
    }
    
    ColumnVector result = new ColumnVector(this.numRows);
    for (int r = 0; r < this.numRows; ++r) {
      result.values[r][0] = this.values[r][c];
    }
    
    return result;
  }
  
  /**
   * Copy the values of this matrix.
   * @return the values
   */
  public double[][] values() { 
    return this.values; 
  }
  
  /**
   * Copy the values of this matrix.
   * @return the copied values
   */
  public double[][] copyValues2D() {
    double result[][] = new double[this.numRows][this.numCols];
    
    for (int r = 0; r < this.numRows; ++r) {
      for (int c = 0; c < this.numCols; ++c) {
        result[r][c] = this.values[r][c];
      }
    }
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Set the value of element [r,c].
   * @param r the row index
   * @param c the column index
   * @param value the value
   * @throws numbercruncher.MatrixException for an invalid index
   */
  public void set(int r, int c, double value) throws MatrixException
  {
    if ((r < 0) || (r >= this.numRows) || (c < 0) || (c >= numCols)) {
      throw new MatrixException(MatrixException.INVALID_INDEX);
    }
    
    this.values[r][c] = value;
  }
  
  /**
   * Set this matrix from a 2-d array of values.
   * If the rows do not have the same length, then the matrix
   * column count is the length of the shortest row.
   * @param values the 2-d array of values
   */
  protected void set(double values[][])
  {
    this.numRows  = values.length;
    this.numCols  = values[0].length;
    this.values = values;
    
    for (int r = 1; r < this.numRows; ++r) {
      this.numCols = Math.min(this.numCols, values[r].length);
    }
  }
  
  /**
   * Set a row of this matrix from a row vector.
   * @param rv the row vector
   * @param r the row index
   * @throws numbercruncher.MatrixException for an invalid index or
   *                                        an invalid vector size
   */
  public void setRow(RowVector rv, int r) throws MatrixException
  {
    if ((r < 0) || (r >= this.numRows)) {
      throw new MatrixException(MatrixException.INVALID_INDEX);
    }
    if (this.numCols != rv.numCols) {
      throw new MatrixException(
              MatrixException.INVALID_DIMENSIONS);
    }
    
    for (int c = 0; c < this.numCols; ++c) {
      this.values[r][c] = rv.values[0][c];
    }
  }
  
  /**
   * Set a column of this matrix from a column vector.
   * @param cv the column vector
   * @param c the column index
   * @throws numbercruncher.MatrixException for an invalid index or
   *                                        an invalid vector size
   */
  public void setColumn(ColumnVector cv, int c)
          throws MatrixException
  {
    if ((c < 0) || (c >= this.numCols)) {
      throw new MatrixException(MatrixException.INVALID_INDEX);
    }
    if (this.numRows != cv.numRows) {
      throw new MatrixException(
              MatrixException.INVALID_DIMENSIONS);
    }
    
    for (int r = 0; r < this.numRows; ++r) {
      this.values[r][c] = cv.values[r][0];
    }
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Matrix Operations">
  /**
   * Return the transpose of this matrix.
   * @return the transposed matrix
   */
  public Matrix transpose()
  {
    double tv[][] = new double[numCols][numRows];  // transposed values
    
    // Set the values of the transpose.
    for (int r = 0; r < numRows; ++r) {
      for (int c = 0; c < numCols; ++c) {
        tv[c][r] = values[r][c];
      }
    }
    
    return new Matrix(tv);
  }
  
  /**
   * Add another matrix to this matrix.
   * @param m the matrix addend
   * @return the sum matrix
   * @throws numbercruncher.MatrixException for invalid size
   */
  public Matrix add(Matrix m) throws MatrixException
  {
    // Validate m's size.
    if ((numRows != m.numRows) && (numCols != m.numCols)) {
      throw new MatrixException(
              MatrixException.INVALID_DIMENSIONS);
    }
    
    double sv[][] = new double[numRows][numCols]; // sum values
    
    // Compute values of the sum.
    for (int r = 0; r < numRows; ++r) {
      for (int c = 0; c < numCols; ++c) {
        sv[r][c] = values[r][c] + m.values[r][c];
      }
    }
    
    return new Matrix(sv);
  }
  
  /**
   * Subtract another matrix from this matrix.
   * @param m the matrix subrrahend
   * @return the difference matrix
   * @throws numbercruncher.MatrixException for invalid size
   */
  public Matrix subtract(Matrix m) throws MatrixException
  {
    // Validate m's size.
    if ((numRows != m.numRows) && (numCols != m.numCols)) {
      throw new MatrixException(
              MatrixException.INVALID_DIMENSIONS);
    }
    
    double dv[][] = new double[numRows][numCols]; // difference values
    
    // Compute values of the difference.
    for (int r = 0; r < numRows; ++r) {
      for (int c = 0; c < numCols; ++c) {
        dv[r][c] = values[r][c] - m.values[r][c];
      }
    }
    
    return new Matrix(dv);
  }
  
  /**
   * Multiply this matrix by a constant.
   * @param k the constant
   * @return the product matrix
   */
  public Matrix multiply(double k)
  {
    double pv[][] = new double[numRows][numCols]; // product values
    
    // Compute values of the product.
    for (int r = 0; r < numRows; ++r) {
      for (int c = 0; c < numCols; ++c) {
        pv[r][c] = k*values[r][c];
      }
    }
    
    return new Matrix(pv);
  }
  
  /**
   * Multiply this matrix by another matrix.
   * @param m the matrix multiplier
   * @return the product matrix
   * @throws numbercruncher.MatrixException for invalid size
   */
  public Matrix multiply(Matrix m) throws MatrixException
  {
    // Validate m's dimensions.
    if (numCols != m.numRows) {
      throw new MatrixException(
              MatrixException.INVALID_DIMENSIONS);
    }
    
    double pv[][] = new double[numRows][m.numCols];  // product values
    
    // Compute values of the product.
    for (int r = 0; r < numRows; ++r) {
      for (int c = 0; c < m.numCols; ++c) {
        double dot = 0;
        for (int k = 0; k < numCols; ++k) {
          dot += values[r][k] * m.values[k][c];
        }
        pv[r][c] = dot;
      }
    }
    
    return new Matrix(pv);
  }
  
  /**
   * Multiply this matrix by a column vector: this*cv
   * @param cv the column vector
   * @return the product column vector
   * @throws numbercruncher.MatrixException for invalid size
   */
  public ColumnVector multiply(ColumnVector cv)
          throws MatrixException
  {
    // Validate cv's size.
    if (numRows != cv.numRows) {
      throw new MatrixException(
              MatrixException.INVALID_DIMENSIONS);
    }
    
    double pv[] = new double[this.numRows];   // product values
    
    // Compute the values of the product.
    for (int r = 0; r < this.numRows; ++r) {
      double dot = 0;
      for (int c = 0; c < this.numCols; ++c) {
        dot += this.values[r][c] * cv.values[c][0];
      }
      pv[r] = dot;
    }
    
    return new ColumnVector(pv);
  }
  
  /**
   * Multiply a row vector by this matrix: rv*this
   * @param rv the row vector
   * @return the product row vector
   * @throws numbercruncher.MatrixException for invalid size
   */
  public RowVector multiply(RowVector rv) throws MatrixException
  {
    // Validate rv's size.
    if (this.numCols != rv.numCols) {
      throw new MatrixException(
              MatrixException.INVALID_DIMENSIONS);
    }
    
    double pv[] = new double[this.numRows];  // product values
    
    // Compute the values of the product.
    for (int c = 0; c < this.numCols; ++c) {
      double dot = 0;
      for (int r = 0; r < this.numRows; ++r) {
        dot += rv.values[0][r] * this.values[r][c];
      }
      pv[c] = dot;
    }
    
    return new RowVector(pv);
  }
  //</editor-fold>
}