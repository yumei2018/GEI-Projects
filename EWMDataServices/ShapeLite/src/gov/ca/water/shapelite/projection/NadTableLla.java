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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class NadTableLla extends NadTable {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private static final Logger LOGGER = Logger.getLogger(NadTableLla.class.getName());

  //</editor-fold>
  /**
   * Creates a new instance of the NatTableLla class.
   *
   * @param resourceLocation The embedded resource name
   */
  public NadTableLla(String resourceLocation) {
    super(resourceLocation);
    this.setFormat(GridShiftTableFormat.LLA);
  }

  /**
   * Creates a new instance of the NatTableLla class.
   *
   * @param resourceLocation The embedded resource name or external relative
   * path.
   * @param embedded
   */
  public NadTableLla(String resourceLocation, boolean embedded) {
    super(resourceLocation, embedded);
    this.setFormat(GridShiftTableFormat.LLA);
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
  @Override
  public void readHeader() {
    try {
      String numText;
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getStream()))) {
        this.setName(reader.readLine());
        numText = reader.readLine();
      } catch (IOException ex) {
        return;
      }

      numText = numText.trim();
      String[] values = numText.split("\\s+");
      setNumLambdas(Integer.parseInt(values[0]));
      setNumPhis(Integer.parseInt(values[1]));
      PhiLam ll = new PhiLam();
      ll.setLambda(USLocale.parseDouble(values[3]) * DEG_TO_RAD);
      ll.setPhi(USLocale.parseDouble(values[5]) * DEG_TO_RAD);
      this.setLowerLeft(ll);
      PhiLam cs = new PhiLam();
      cs.setLambda(USLocale.parseDouble(values[4]) * DEG_TO_RAD);
      cs.setPhi(USLocale.parseDouble(values[6]) * DEG_TO_RAD);
      this.setCellSize(cs);
      this.setFilled(false);
    } catch (ParseException ex) {
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

  /**
   * Fills the data from the lla file.
   *
   * @throws ProjectionException if there is an error parsing the values.
   */
  @Override
  public void fillData() throws ProjectionException {
    String numText;
    List<String> values = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(this.getStream()))) {
      reader.readLine(); // skip name
      reader.readLine(); // skip header
      String line;
      while ((line = reader.readLine()) != null) {
        String[] lineValues = line.split(":|\\s+");
        List<String> tempList = new ArrayList<>(Arrays.asList(lineValues));

        for (String value : tempList) {
          if (!value.isEmpty()) {
            values.add(value); // a temp
          }
        }
      }
      numText = reader.readLine();
    } catch (IOException ex) {
      return;
    }
    //char[] separators = new[] { ' ', ',', ':', (char)10 };
    //String[] values = numText.Split(separators, StringSplitOptions.RemoveEmptyEntries);
    int p = 0;

    int numPhis = getNumPhis();
    int numLambdas = getNumLambdas();
    PhiLam[][] cvs = new PhiLam[numPhis][];
    for (int i = 0; i < numPhis; i++) {
      try {
        cvs[i] = new PhiLam[numLambdas];
        for (int j = 0; j < numLambdas; j++) {
          cvs[i][j] = new PhiLam();
        }
        int iCheck = USLocale.parseInteger(values.get(p));
        if (iCheck != i) {
          throw new ProjectionException("There was an indexing problem reading "
              + this.getName());
        }
        p++;
        double lam = USLocale.parseLong(values.get(p)) * USEC_TO_RAD;
        cvs[i][0].setLambda(lam);
        p++;
        double phi = USLocale.parseLong(values.get(p)) * USEC_TO_RAD;
        cvs[i][0].setPhi(phi);
        p++;
        for (int j = 1; j < getNumLambdas(); j++) {
          lam += USLocale.parseLong(values.get(p)) * USEC_TO_RAD;
          cvs[i][j].setLambda(lam);
          p++;
          phi += USLocale.parseLong(values.get(p)) * USEC_TO_RAD;
          cvs[i][j].setPhi(phi);
          p++;
        }
      } catch (ParseException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
    setCvs(cvs);
    setFilled(true);
  }
}
