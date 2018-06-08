package gov.ca.water.math.matrixes;

import gov.ca.water.common.io.DataEntry;

/**
 * From: Java Number Cruncher The Java Programmer's Guide to Numerical Computation by
 * Ronald Mak 
 * Original Code: org.jgrasstools.gears.utils.math.matrixes.LinearSystem @
 * http://grepcode.com/snapshot/repo1.maven.org/maven2/org.jgrasstools/jgt-jgrassgears/0.7.3/
 * @author Modified by J.G. "Koos" Prins"
 *
 * Solve a system of linear equations using LU decomposition.
 */
public class LinearSystem extends SquareMatrix {
  
  //<editor-fold defaultstate="collapsed" desc="Protected Fields">
  /**
   * decomposed matrix A = LU
   */
  protected SquareMatrix LU;
  /**
   * row index permutation vector
   */
  protected int permutation[];
  /**
   * row exchange count
   */
  protected int exchangeCount;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Constructor.
   *
   * @param n the number of rows = the number of columns
   */
  public LinearSystem(int n) {
    super(n);
    this.reset();
  }

  /**
   * Constructor.
   *
   * @param values the array of values
   */
  public LinearSystem(double values[][]) {
    super(values);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Set the values of the matrix.
   *
   * @param values the 2-d array of values
   */
  protected void set(double values[][]) {
    super.set(values);
    this.reset();
  }
  
  /**
   * Set the value of element [r,c] in the matrix.
   *
   * @param r the row index, 0..numRows
   * @param c the column index, 0..numRows
   * @param value the value
   * @throws matrix.MatrixException for invalid index
   */
  @Override
  public void set(int r, int c, double value) throws MatrixException {
    super.set(r, c, value);
    this.reset();
  }
  
  /**
   * Set a row of this matrix from a row vector.
   *
   * @param rv the row vector
   * @param r the row index
   * @throws matrix.MatrixException for an invalid index or an invalid vector size
   */
  @Override
  public void setRow(RowVector rv, int r) throws MatrixException {
    super.setRow(rv, r);
    this.reset();
  }
  
  /**
   * Set a column of this matrix from a column vector.
   *
   * @param cv the column vector
   * @param c the column index
   * @throws matrix.MatrixException for an invalid index or an invalid vector size
   */
  @Override
  public void setColumn(ColumnVector cv, int c) throws MatrixException {
    super.setColumn(cv, c);
    this.reset();
  }
  
  /**
   * Reset. Invalidate LU and the permutation vector.
   */
  protected void reset() {
    this.LU = null;
    this.permutation = null;
    this.exchangeCount = 0;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Matric Operations">
  /**
   * Solve Ax = b for x using the Gaussian elimination algorithm.
   *
   * @param b the right-hand-side column vector
   * @param improve true to improve the solution
   * @return the solution column vector
   * @throws matrix.MatrixException if an error occurred
   */
  public ColumnVector solve(ColumnVector b, boolean improve) throws MatrixException {
    // Validate b's size.
    if (b.numRows != numRows) {
      throw new MatrixException(MatrixException.INVALID_DIMENSIONS);
    }

    decompose();

    // Solve Ly = b for y by forward substitution.
    // Solve Ux = y for x by back substitution.
    ColumnVector y = forwardSubstitution(b);
    ColumnVector result = backSubstitution(y);

    // Improve and return x.
    if (improve) {
      improve(b, result);
    }
    return result;
  }

  /**
   * Compute the upper triangular matrix U and lower triangular matrix L such that A =
   * L*U. Store L and U together in matrix LU. Compute the permutation vector permutation
   * of the row indices.
   * @throws matrix.MatrixException for a zero row or a singular matrix
   */
  protected void decompose() throws MatrixException {
    // Return if the decomposition is valid.
    if (this.LU != null) {
      return;
    }

    // Create a new LU matrix and permutation vector.
    // LU is initially just a copy of the values of this system.
    this.LU = new SquareMatrix(this.copyValues2D());
    this.permutation = new int[this.numRows];

    double scales[] = new double[this.numRows];

    // Loop to initialize the permutation vector and scales.
    for (int r = 0; r < this.numRows; ++r) {
      this.permutation[r] = r;     // initially no row exchanges

      // Find the largest row element.
      double largestRowElmt = 0;
      for (int c = 0; c < this.numRows; ++c) {
        double elmt = Math.abs(this.LU.at(r, c));
        if (largestRowElmt < elmt) {
          largestRowElmt = elmt;
        }
      }

      // Set the scaling factor for row equilibration.
      if (largestRowElmt != 0) {
        scales[r] = 1 / largestRowElmt;
      } else {
        throw new MatrixException(MatrixException.ZERO_ROW);
      }
    }

    // Do forward elimination with scaled partial row pivoting.
    forwardElimination(scales);

    // Check bottom right element of the permuted matrix.
    if (this.LU.at(this.permutation[numRows - 1], this.numRows - 1) == 0) {
      throw new MatrixException(MatrixException.SINGULAR);
    }
  }

  /**
   * Do forward elimination with scaled partial row pivoting.
   * @parm scales the scaling vector
   * @throws matrix.MatrixException for a singular matrix
   */
  private void forwardElimination(double scales[]) throws MatrixException {
    // Loop once per pivot row 0..numRows-1.
    for (int rPivot = 0; rPivot < this.numRows - 1; ++rPivot) {
      double largestScaledElmt = 0;
      int rLargest = 0;

      // Starting from the pivot row rPivot, look down
      // column rPivot to find the largest scaled element.
      for (int r = rPivot; r < this.numRows; ++r) {

        // Use the permuted row index.
        int pr = this.permutation[r];
        double absElmt = Math.abs(this.LU.at(pr, rPivot));
        double scaledElmt = absElmt * scales[pr];

        if (largestScaledElmt < scaledElmt) {

          // The largest scaled element and
          // its row index.
          largestScaledElmt = scaledElmt;
          rLargest = r;
        }
      }

      // Is the matrix singular?
      if (largestScaledElmt == 0) {
        throw new MatrixException(MatrixException.SINGULAR);
      }

      // Exchange rows if necessary to choose the best
      // pivot element by making its row the pivot row.
      if (rLargest != rPivot) {
        int temp = this.permutation[rPivot];
        this.permutation[rPivot] = this.permutation[rLargest];
        this.permutation[rLargest] = temp;

        ++this.exchangeCount;
      }

      // Use the permuted pivot row index.
      int prPivot = this.permutation[rPivot];
      double pivotElmt = this.LU.at(prPivot, rPivot);

      // Do the elimination below the pivot row.
      for (int r = rPivot + 1; r < this.numRows; ++r) {

        // Use the permuted row index.
        int pr = this.permutation[r];
        double multiple = this.LU.at(pr, rPivot) / pivotElmt;

        // Set the multiple into matrix L.
        this.LU.set(pr, rPivot, multiple);

        // Eliminate an unknown from matrix U.
        if (multiple != 0) {
          for (int c = rPivot + 1; c < this.numCols; ++c) {
            double elmt = LU.at(pr, c);

            // Subtract the multiple of the pivot row.
            elmt -= multiple * this.LU.at(prPivot, c);
            this.LU.set(pr, c, elmt);
          }
        }
      }
    }
  }

  /**
   * Solve Ly = b for y by forward substitution.
   * @param b the column vector b
   * @return the column vector y
   * @throws matrix.MatrixException if an error occurred
   */
  private ColumnVector forwardSubstitution(ColumnVector b) throws MatrixException {
    ColumnVector result = new ColumnVector(this.numRows);

    // Do forward substitution.
    for (int r = 0; r < this.numRows; ++r) {
      int pr = this.permutation[r];     // permuted row index
      double dot = 0;
      for (int c = 0; c < r; ++c) {
        dot += this.LU.at(pr, c) * result.at(c);
      }
      result.set(r, b.at(pr) - dot);
    }

    return result;
  }

  /**
   * Solve Ux = y for x by back substitution.
   * @param y the column vector y
   * @return the solution column vector x
   * @throws matrix.MatrixException if an error occurred
   */
  private ColumnVector backSubstitution(ColumnVector y) throws MatrixException {
    ColumnVector result = new ColumnVector(this.numRows);

    // Do back substitution.
    for (int r = this.numRows - 1; r >= 0; --r) {
      int pr = this.permutation[r];     // permuted row index
      double dot = 0;
      for (int c = r + 1; c < this.numRows; ++c) {
        dot += this.LU.at(pr, c) * result.at(c);
      }
      result.set(r, (y.at(r) - dot) / this.LU.at(pr, r));
    }

    return result;
  }

  /**
   * Iteratively improve the solution x to machine accuracy.
   * @param b the right-hand side column vector
   * @param x the improved solution column vector
   * @throws matrix.MatrixException if failed to converge
   */
  private void improve(ColumnVector b, ColumnVector x) throws MatrixException {
    // Find the largest x element.
    double largestX = 0;
    for (int r = 0; r < this.numRows; ++r) {
      double absX = Math.abs(x.values[r][0]);
      if (largestX < absX) {
        largestX = absX;
      }
    }

    // Is x already as good as possible?
    if (largestX == 0) {
      return;
    }

    ColumnVector residuals = new ColumnVector(this.numRows);

    // Iterate to improve x.
    for (int iter = 0; iter < DataEntry.MAX_ITER; ++iter) {

      // Compute residuals = b - Ax.
      // Must use double precision!
      for (int r = 0; r < this.numRows; ++r) {
        double dot = 0;
        double row[] = values[r];
        for (int c = 0; c < this.numRows; ++c) {
          double elmt = at(r, c);
          dot += elmt * x.at(c);        // dbl.prec. *
        }
        double value = b.at(r) - dot;   // dbl.prec. -
        residuals.set(r, value);
      }

      // Solve Az = residuals for z.
      ColumnVector z = solve(residuals, false);

      // Set x = x + z.
      // Find largest the largest difference.
      double largestDiff = 0;
      for (int r = 0; r < this.numRows; ++r) {
        double oldX = x.at(r);
        x.set(r, oldX + z.at(r));

        double diff = Math.abs(x.at(r) - oldX);
        if (largestDiff < diff) {
          largestDiff = diff;
        }
      }

      // Is any further improvement possible?
      if (largestDiff < largestX * DataEntry.TOLERANCE) {
        return;
      }
    }

    // Failed to converge because A is nearly singular.
    throw new MatrixException(MatrixException.NO_CONVERGENCE);
  }
  //</editor-fold>
}