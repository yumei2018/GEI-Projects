/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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
package gov.ca.water.shapelite;

import gov.ca.water.shapelite.io.ShapefileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ShapefileReaders {

  private static ShapefileReaders instance;
  private ShapefileReader riverMilesUtm;
  private ShapefileReader riverUtm;
  private ShapefileReader river;
  private ShapefileReader riverMiles;
  private ShapefileReader leveeLinesUtm;
  private ShapefileReader leveeStartpointsUtm;
  private ShapefileReader nat1Utm;

  /**
   * Singleton pattern, so constructor is private.
   */
  private ShapefileReaders()
  {

  }

  public static ShapefileReaders instance()
  {
    if(instance == null)
    {
      instance = new ShapefileReaders();
    }
    return instance;
  }


  private ShapefileReader getReader(String name) throws IOException
  {
    String shp = "data/" +name + ".shp";
    String shx = "data/" + name + ".shx";
    String dbf = "data/" + name + ".dbf";
    InputStream shpStream = this.getClass().getResourceAsStream(shp);
    InputStream shxStream = this.getClass().getResourceAsStream(shx);
    InputStream dbfStream = this.getClass().getResourceAsStream(dbf);
    ShapefileReader reader = new ShapefileReader();
    reader.open(shpStream, shxStream, dbfStream);
    return reader;
  }

  /**
   * @return the riverMilesUtm
   */
  public ShapefileReader getRiverMilesUtm() {
    if(riverMilesUtm == null)
    {
      try {
        riverMilesUtm = getReader("RiverMiles_UTM");
      } catch (IOException ex) {
        Logger.getLogger(ShapefileReaders.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return riverMilesUtm;
  }

  /**
   * @return the riverUtm
   */
  public ShapefileReader getRiverUtm() {
    if(riverUtm == null)
    {
      try {
        riverUtm = getReader("river_UTM");
      } catch (IOException ex) {
        Logger.getLogger(ShapefileReaders.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return riverUtm;
  }

  /**
   * @return the river
   */
  public ShapefileReader getRiver(){
    if(river == null)
    {
      try {
        river = getReader("River");
      } catch (IOException ex) {
        Logger.getLogger(ShapefileReaders.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return river;
  }

  /**
   * @return the riverMiles
   */
  public ShapefileReader getRiverMiles() {
    if(riverMiles == null)
    {
      try {
        riverMiles = getReader("RiverMiles");
      } catch (IOException ex) {
        Logger.getLogger(ShapefileReaders.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return riverMiles;
  }

  /**
   * @return the leveeLinesUtm
   */
  public ShapefileReader getLeveeLinesUtm() {
    if(leveeLinesUtm == null)
    {
      try {
        leveeLinesUtm = getReader("Levee_Lines_UTM");
      } catch (IOException ex) {
        Logger.getLogger(ShapefileReaders.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return leveeLinesUtm;
  }

  /**
   * @return the leveeStartpointsUtm
   */
  public ShapefileReader getLeveeStartpointsUtm() {
    if(leveeStartpointsUtm == null)
    {
      try {
        leveeStartpointsUtm = getReader("Levee_Startpoints_UTM");
      } catch (IOException ex) {
        Logger.getLogger(ShapefileReaders.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return leveeStartpointsUtm;
  }

  /**
   * @return the leveeStartpointsUtm
   */
  public ShapefileReader getNat1Utm() {
    if(nat1Utm == null)
    {
      try {
        nat1Utm = getReader("Nat1_UTM");
      } catch (IOException ex) {
        Logger.getLogger(ShapefileReaders.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return nat1Utm;
  }

}
