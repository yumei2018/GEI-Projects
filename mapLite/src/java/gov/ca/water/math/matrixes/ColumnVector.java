package gov.ca.water.math.matrixes;

/**
 * From: Java Number Cruncher
 * The Java Programmer's Guide to Numerical Computation
 * by Ronald Mak 
 * Original Code:  org.jgrasstools.gears.utils.math.matrixes.ColumnVector @
 * http://grepcode.com/snapshot/repo1.maven.org/maven2/org.jgrasstools/jgt-jgrassgears/0.7.3/
 * @author Modified by J.G. "Koos" Prins"
 *
 * A column vector.
 */
public class ColumnVector extends Matrix {
    
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Constructor.
   * @param n the number of elements
   */
  public ColumnVector(int n) { 
    super(n, 1); 
  }
  
  /**
   * Constructor.
   * @param values the array of values
   */
  public ColumnVector(double values[]) { 
    this.set(values); 
  }
  
  /**
   * Constructor.
   * @param m the matrix (only the first column used)
   */
  private ColumnVector(Matrix m) { 
    this.set(m); 
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Return this column vector's size.
   */
  public int size() { 
    return this.numRows; 
  }
  
  /**
   * Return the i'th value of the vector.
   * @param i the index
   * @return the value
   */
  public double at(int i) { 
    return this.values[i][0]; 
  }
  
  /**
   * Copy the values of this matrix.
   * @return the copied values
   */
  public double[] copyValues1D() {
    double result[] = new double[this.numRows];
    
    for (int r = 0; r < this.numRows; ++r) {
      result[r] = this.values[r][0];
    }
    
    return result;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Set this column vector from a matrix.
   * Only the first column is used.
   * @param m the matrix
   */
  private void set(Matrix m) {
    this.numRows  = m.numRows;
    this.numCols  = 1;
    this.values = m.values;
  }
  
  /**
   * Set this column vector from an array of values.
   * @param values the array of values
   */
  protected void set(double values[]) {
    this.numRows  = values.length;
    this.numCols  = 1;
    this.values = new double[this.numRows][1];
    
    for (int r = 0; r < this.numRows; ++r) {
      this.values[r][0] = values[r];
    }
  }
  
  /**
   * Set the value of the i'th element.
   * @param i the index
   * @param value the value
   */
  public void set(int i, double value) { 
    this.values[i][0] = value; 
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Vector operations">
  /**
   * Add another column vector to this column vector.
   * @param cv the other column vector
   * @return the sum column vector
   * @throws numbercruncher.MatrixException for invalid size
   */
  public ColumnVector add(ColumnVector cv) throws MatrixException {
    return new ColumnVector(super.add(cv));
  }
  
  /**
   * Subtract another column vector from this column vector.
   * @param cv the other column vector
   * @return the sum column vector
   * @throws numbercruncher.MatrixException for invalid size
   */
  public ColumnVector subtract(ColumnVector cv) throws MatrixException {
    return new ColumnVector(super.subtract(cv));
  }
  
  /**
   * Compute the Euclidean norm.
   * @return the norm
   */
  public double norm() {
    double t = 0;
    
    for (int r = 0; r < this.numRows; ++r) {
      double v = this.values[r][0];
      t += v*v;
    }
    
    return Math.sqrt(t);
  }
  
  /**
   * Print the vector values.
   */
  public void print() {
    for (int r = 0; r < this.numRows; ++r) {
      System.out.print("  " + this.values[r][0]);
    }
    System.out.println();
  }
  //</editor-fold>
}