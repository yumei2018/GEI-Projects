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

import gov.ca.water.shapelite.projection.transforms.Proj;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GridShift {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private static final Logger LOGGER = Logger.getLogger(GridShift.class.getName());

  private static final double HUGE_VAL = Double.MAX_VALUE;
  private static final int MAX_TRY = 9;
  private static final double TOL = 1E-12;

  //</editor-fold>
  /**
   * Creates a new instance of the GridShift class.
   */
  public GridShift() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
  /// <summary>
  /// Applies either a forward or backward gridshift based on the specified name
  /// </summary>
  /// <param name="names"></param>
  /// <param name="inverse"></param>
  /// <param name="xy"></param>
  /// <param name="startIndex"></param>
  /// <param name="numPoints"></param>
  public static void apply(String[] names, boolean inverse, double[] xy,
      int startIndex, long numPoints) throws ProjectionException {
    for (int i = startIndex; i < numPoints; i++) {
      PhiLam input = new PhiLam();
      PhiLam output = new PhiLam();
      input.setPhi(xy[i * 2 + 1]);
      input.setLambda(xy[i * 2]);
      output.setPhi(HUGE_VAL);
      output.setLambda(HUGE_VAL);
      HashMap<String, NadTable> tables = NadTableManager.getInstance().getTables();
      /* keep trying till we find a Table that works from the ones listed */
      for (String name : names) {
        if (name.startsWith("@")) {
          name = name.substring(1);
        }
        name = name.toLowerCase();
        if (!tables.containsKey(name)) {
          continue;
        }
        NadTable table = tables.get(name);
        boolean found = false;
        // For GSB tables, we need to check for the appropriate sub-table
        if (table.getSubGrids() != null && table.getSubGrids().size() > 1) {
          for (NadTable subGrid : table.getSubGrids()) {
            /* skip tables that don't match our point at all.  */
            double wLam = subGrid.getLowerLeft().getLambda();
            double eLam = wLam + (subGrid.getNumLambdas() - 1) * subGrid.getCellSize().getLambda();
            double sPhi = subGrid.getLowerLeft().getPhi();
            double nPhi = sPhi + (subGrid.getNumPhis() - 1) * subGrid.getCellSize().getLambda();
            if (input.getLambda() < wLam || input.getLambda() > eLam
                || input.getPhi() < sPhi || input.getPhi() > nPhi) {
              continue;
            }
            table = subGrid;
            found = true;
            break;
          }
          if (!found) {
            continue;
          }
        } else {
          /* skip tables that don't match our point at all.  */
          double minLam = table.getLowerLeft().getLambda();
          double maxLam = minLam + (table.getNumLambdas() - 1) * table.getCellSize().getLambda();
          double minPhi = table.getLowerLeft().getPhi();
          double maxPhi = minPhi + (table.getNumPhis() - 1) * table.getCellSize().getLambda();
          if (input.getLambda() < minLam || input.getLambda() > maxLam
              || input.getPhi() < minPhi || input.getPhi() > maxPhi) {
            continue;
          }
        }

        // TO DO: handle child nodes?  Not sure what format would require this
        output = convert(input, inverse, table);
        if (output.getLambda() == HUGE_VAL) {
          LOGGER.log(Level.FINE, "GridShift failed");
          break;
        }
        break;
      }

      if (output.getLambda() == HUGE_VAL) {
        LOGGER.log(Level.FINE, "pj_apply_gridshift(): failed to find a "
            + "grid shift Table for location: ({0}, {1})",
            new Object[]{xy[i * 2] * 180 / Math.PI,
              xy[i * 2 + 1] * 180 / Math.PI});
      } else {
        xy[i * 2] = output.getLambda();
        xy[i * 2 + 1] = output.getPhi();
      }
    }
  }

  /**
   * Converts the value by interpolating from the NAD grid.
   *
   * @param input
   * @param inverse
   * @param table
   * @return
   * @throws ProjectionException
   */
  private static PhiLam convert(PhiLam input, boolean inverse,
      NadTable table) throws ProjectionException {
    if (input.getLambda() == HUGE_VAL) {
      return input;
    }
    // Normalize input to ll origin
    if (!table.isFilled()) {
      table.fillData();
    }
    PhiLam tb = input.copy();
    tb.setLambda(tb.getLambda() - table.getLowerLeft().getLambda());
    tb.setPhi(tb.getPhi() - table.getLowerLeft().getPhi());
    tb.setLambda(Proj.Adjlon(tb.getLambda() - Math.PI) + Math.PI);
    PhiLam t = nadInterpolate(tb, table);
    if (inverse) {
      PhiLam del;
      PhiLam dif = new PhiLam();
      int i = MAX_TRY;
      if (t.getLambda() == HUGE_VAL) {
        return t;
      }
      t.setLambda(tb.getLambda() + t.getLambda());
      t.setPhi(tb.getPhi() - t.getPhi());
      do {
        del = nadInterpolate(t, table);
        /* This case used to return failure, but I have
                 changed it to return the first order approximation
                 of the inverse shift.  This avoids cases where the
                 grid shift *into* this grid came from another grid.
                 While we aren't returning optimally correct results
                 I feel a close result in this case is better than
                 no result.  NFW
                 To demonstrate use -112.5839956 49.4914451 against
                 the NTv2 grid shift file from Canada. */
        if (del.getLambda() == HUGE_VAL) {
          LOGGER.log(Level.SEVERE, "Inverse shift failed.");
          break;
        }
        dif.setLambda(t.getLambda() - del.getLambda() - tb.getLambda());
        t.setLambda(t.getLambda() - dif.getLambda());
        dif.setPhi(t.getPhi() + del.getPhi() - tb.getPhi());
        t.setPhi(t.getPhi() - dif.getPhi());
      } while (i-- > 0 && Math.abs(dif.getLambda()) > TOL
          && Math.abs(dif.getPhi()) > TOL);
      if (i < 0) {
        LOGGER.log(Level.SEVERE, "Inverse shift failed to converge.");
        t.setLambda(HUGE_VAL);
        t.setPhi(HUGE_VAL);
        return t;
      }
      input.setLambda(Proj.Adjlon(t.getLambda() + table.getLowerLeft().getLambda()));
      input.setPhi(t.getPhi() + table.getLowerLeft().getPhi());
    } else if (t.getLambda() == HUGE_VAL) {
      input = t;
    } else {
      input.setLambda(input.getLambda() - t.getLambda());
      input.setPhi(input.getPhi() + t.getPhi());
    }
    return input;
  }

  /**
   * Interpolate values using the nad grid.
   *
   * @param t
   * @param ct
   * @return
   */
  private static PhiLam nadInterpolate(PhiLam t, NadTable ct) {
    PhiLam result = new PhiLam();
    PhiLam remainder = new PhiLam();
    result.setPhi(HUGE_VAL);
    result.setLambda(HUGE_VAL);
    // find indices and normalize by the cell size (so fractions range from 0 to 1)

    t.setLambda(t.getLambda() / ct.getCellSize().getLambda());
    int iLam = (int) Math.floor(t.getLambda());
    t.setPhi(t.getPhi() / ct.getCellSize().getPhi());
    int iPhi = (int) Math.floor(t.getPhi());

    // use the index to determine the remainder
    remainder.setLambda(t.getLambda() - iLam);
    remainder.setPhi(t.getPhi() - iPhi);

    //int offLam = 0; // normally we look to the right and bottom neighbor cells
    //int offPhi = 0;
    //if (remainder.lambda < .5) offLam = -1; // look to cell left of the current cell
    //if (remainder.phi < .5) offPhi = -1; // look to cell above the of the current cell
    //// because the fractional weights are between cells, we need to adjust the
    //// "remainder" so that it is now relative to the center of the top left
    //// cell, taking into account that the definition of the top left cell
    //// depends on whether the original remainder was larger than .5
    //remainder.phi = (remainder.phi > .5) ? remainder.phi - .5 : remainder.phi + .5;
    //remainder.lambda = (remainder.lambda > .5) ? remainder.lambda - .5 : remainder.phi + .5;
    if (iLam < 0) {
      if (iLam == -1 && remainder.getLambda() > 0.99999999999) {
        iLam++;
        remainder.setLambda(0);
      } else {
        return result;
      }
    } else if (iLam + 1 >= ct.getNumLambdas()) {
      if (iLam + 1 == ct.getNumLambdas() && remainder.getLambda() < 1e-11) {
        iLam--;
      } else {
        return result;
      }
    }
    if (iPhi < 0) {
      if (iPhi == -1 && remainder.getPhi() > 0.99999999999) {
        iPhi++;
        remainder.setPhi(0);
      } else {
        return result;
      }
    } else if (iPhi + 1 >= ct.getNumPhis()) {
      if (iPhi + 1 == ct.getNumPhis() && remainder.getPhi() < 1e-11) {
        iPhi--;
        remainder.setPhi(1);
      } else {
        return result;
      }
    }

    PhiLam f00 = getValue(iPhi, iLam, ct);
    PhiLam f01 = getValue(iPhi + 1, iLam, ct);
    PhiLam f10 = getValue(iPhi, iLam + 1, ct);
    PhiLam f11 = getValue(iPhi + 1, iLam + 1, ct);

    // The cell weight is equivalent to the area of a cell sized square centered
    // on the actual point that overlaps with the cell.
    // Since the overlap must add up to 1, any portion that does not overlap
    // on the left must overlap on the right, hence (1-remainder.lambda)
    double m00 = (1 - remainder.getLambda()) * (1 - remainder.getPhi());
    double m01 = (1 - remainder.getLambda()) * remainder.getPhi();
    double m10 = remainder.getLambda() * (1 - remainder.getPhi());
    double m11 = remainder.getLambda() * remainder.getPhi();

    result.setLambda(m00 * f00.getLambda() + m01 * f01.getLambda()
        + m10 * f10.getLambda() + m11 * f11.getLambda());
    result.setPhi(m00 * f00.getPhi() + m01 * f01.getPhi()
        + m10 * f10.getPhi() + m11 * f11.getPhi());

    return result;
  }

  /**
   * Checks the edges to make sure that we are not attempting to interpolate
   * from cells that don't exist.
   *
   * @param iPhi The cell index in the phi direction
   * @param iLam The cell index in the lambda direction
   * @param table The Table with the values
   * @return A PhiLam that has the shift coefficients.
   */
  private static PhiLam getValue(int iPhi, int iLam, NadTable table) {
    if (iPhi < 0) {
      iPhi = 0;
    }
    if (iPhi >= table.getNumPhis()) {
      iPhi = table.getNumPhis() - 1;
    }
    if (iLam < 0) {
      iLam = 0;
    }
    if (iLam >= table.getNumLambdas()) {
      iLam = table.getNumPhis() - 1;
    }
    return table.getCvs()[iPhi][iLam];
  }

}
