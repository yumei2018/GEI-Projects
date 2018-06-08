package gov.ca.water.math.matrixes;

/**
 * From: Java Number Cruncher
 * The Java Programmer's Guide to Numerical Computation
 * by Ronald Mak 
 * Original Code: org.jgrasstools.gears.utils.math.matrixes.RowVector @
 * http://grepcode.com/snapshot/repo1.maven.org/maven2/org.jgrasstools/jgt-jgrassgears/0.7.3/
 * @author Modified by J.G. "Koos" Prins"
 *
 */
public class RowVector extends Matrix {
    
  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Constructor.
   * @param n the number of elements
   */
  public RowVector(int n) { super(1, n); }
  
  /**
   * Constructor.
   * @param values the array of values
   */
  public RowVector(double values[]) { set(values); }
  
  /**
   * Constructor.
   * @param m the matrix (only the first row used)
   */
  private RowVector(Matrix m) { set(m); }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Getters">
  /**
   * Return the row vector's size.
   */
  public int size() { 
    return this.numCols; 
  }
  
  /**
   * Copy the values of this matrix.
   * @return the copied values
   */
  public double[] copyValues1D() {
    double result[] = new double[this.numCols];
    
    for (int c = 0; c < this.numCols; ++c) {
      result[c] = this.values[0][c];
    }
    
    return result;
  }
  
  /**
   * Return the i'th value of the vector.
   * @param i the index
   * @return the value
   */
  public double at(int i) { 
    return this.values[0][i]; 
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Setters">
  /**
   * Set this row vector from a matrix. Only the first row is used.
   * @param m the matrix
   */
  private void set(Matrix m) {
    this.numRows  = 1;
    this.numCols  = m.numCols;
    this.values = m.values;
  }
  
  /**
   * Set this row vector from an array of values.
   * @param values the array of values
   */
  protected void set(double values[]) {
    this.numRows  = 1;
    this.numCols  = values.length;
    this.values = new double[1][];
    
    this.values[0] = values;
  }
  
  /**
   * Set the i'th value of the vector.
   * @param i the index
   * @param value the value
   */
  public void set(int i, double value) { 
    this.values[0][i] = value; 
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Vector Operations">
  /**
   * Add another row vector to this row vector.
   * @param rv the other row vector
   * @return the sum row vector
   * @throws numbercruncher.MatrixException for invalid size
   */
  public RowVector add(RowVector rv) throws MatrixException {
    return new RowVector(super.add(rv));
  }
  
  /**
   * Subtract another row vector from this row vector.
   * @param rv the other row vector
   * @return the sum row vector
   * @throws numbercruncher.MatrixException for invalid size
   */
  public RowVector subtract(RowVector rv) throws MatrixException {
    return new RowVector(super.subtract(rv));
  }
  
  /**
   * Compute the Euclidean norm.
   * @return the norm
   */
  public double norm() {
    double t = 0;
    for (int c = 0; c < this.numCols; ++c) {
      double v = this.values[0][c];
      t += v*v;
    }
    
    return Math.sqrt(t);
  }
  
  /**
   * Print the vector values.
   */
  public void print() {
    for (int c = 0; c < this.numCols; ++c) {
      System.out.print("  " + this.values[0][c]);
    }
    System.out.println();
  }
  //</editor-fold>
}