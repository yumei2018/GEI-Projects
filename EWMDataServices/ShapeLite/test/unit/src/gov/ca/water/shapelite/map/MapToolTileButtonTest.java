/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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
package gov.ca.water.shapelite.map;

import gov.ca.water.shapelite.map.element.TileButtonMapElement;
import gov.ca.water.shapelite.map.maptool.TileButtonMapTool;
import gov.ca.water.shapelite.map.layer.TileLayer;
import gov.ca.water.shapelite.data.DataSourceTiles;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapToolTileButtonTest {

  /**
   * This tests the tool constructor and some expected default properties.
   */
  @Test
  public final void testConstructor() {
    TileButtonMapTool instance = new TileButtonMapTool();
    TileButtonMapElement button = (TileButtonMapElement) instance.getElements().get(0);
    
  }

  /**
   * Test of getSatelliteLayer method, of class TileButtonMapElement.
   */
  @Test
  public final void testGetSatelliteLayer() {
    System.out.println("getSatelliteLayer");
    TileButtonMapTool instance = new TileButtonMapTool();
    TileLayer expResult = new TileLayer(new DataSourceTiles());
    instance.setSatelliteLayer(expResult);
    TileLayer result = instance.getSatelliteLayer();
    assertEquals(expResult, result);
  }

  /**
   * Test of getStreetLayer method, of class TileButtonMapElement.
   */
  @Test
  public final void testGetStreetLayer() {
    System.out.println("getStreetLayer");
    TileButtonMapTool instance = new TileButtonMapTool();
    TileLayer expResult = new TileLayer(new DataSourceTiles());
    instance.setStreetLayer(expResult);
    TileLayer result = instance.getStreetLayer();
    assertEquals(expResult, result);
  }

  /**
   * Test of getTopoLayer method, of class TileButtonMapElement.
   */
  @Test
  public final void testGetTopoLayer() {
    System.out.println("getTopoLayer");
    TileButtonMapTool instance = new TileButtonMapTool();
    TileLayer expResult = new TileLayer(new DataSourceTiles());
    instance.setTopoLayer(expResult);
    TileLayer result = instance.getTopoLayer();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCurrentMapType method, of class TileButtonMapElement.
   */
  @Test
  public final void testGetCurrentMapType() {
    System.out.println("getCurrentMapType");
    TileButtonMapTool instance = new TileButtonMapTool();
    MapTileType expResult = MapTileType.Topo;
    instance.setCurrentMapType(expResult);
    MapTileType result = instance.getCurrentMapType();
    assertEquals(expResult, result);
  }

}
