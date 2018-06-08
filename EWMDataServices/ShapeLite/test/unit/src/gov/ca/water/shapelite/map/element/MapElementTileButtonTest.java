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
package gov.ca.water.shapelite.map.element;

import gov.ca.water.shapelite.map.layer.TileLayer;
import gov.ca.water.shapelite.data.DataSourceTiles;
import gov.ca.water.shapelite.map.MapTileType;
import gov.ca.water.shapelite.map.layer.tile.SatelliteMapLayer;
import gov.ca.water.shapelite.map.layer.tile.StreetMapLayer;
import gov.ca.water.shapelite.map.layer.tile.TopoMapLayer;
import java.awt.Image;
import java.awt.image.BufferedImage;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class MapElementTileButtonTest {

  /**
   * The icon size to use for test images.
   */
  private static final int ICON_SIZE = 32;

  @Test
  public final void testNext() {
    TileButtonMapElement instance = new TileButtonMapElement();
    instance.setStreetLayer(new StreetMapLayer());
    instance.setSatelliteLayer(new SatelliteMapLayer());
    instance.setTopoLayer(new TopoMapLayer());
    MapTileType expResult = MapTileType.Satellite;
    MapTileType result = instance.next();
    assertEquals(expResult, result);
    instance.setCurrentMapType(result);
    expResult = MapTileType.Topo;
    result = instance.next();
    assertEquals(expResult, result);
    instance.setCurrentMapType(result);
    expResult = MapTileType.StreetMap;
    result = instance.next();
    assertEquals(expResult, result);

  }
  
  

  /**
   * Tests that setting the layer affects the visibility of the three layers in
   * the expected way.
   */
  @Test
  public final void testSetLayer() {
    System.out.println("getSatelliteLayer");
    TileButtonMapElement instance = new TileButtonMapElement();
    TileLayer street = new TileLayer(new DataSourceTiles());
    TileLayer satellite = new TileLayer(new DataSourceTiles());
    TileLayer topo = new TileLayer(new DataSourceTiles());
    instance.setStreetLayer(street);
    instance.setSatelliteLayer(satellite);
    instance.setTopoLayer(topo);
    instance.setLayer();
    Assert.assertTrue(street.isVisible());
    Assert.assertFalse(satellite.isVisible());
    Assert.assertFalse(topo.isVisible());
    instance.setCurrentMapType(MapTileType.Satellite);
    instance.setLayer();
    Assert.assertFalse(street.isVisible());
    Assert.assertTrue(satellite.isVisible());
    Assert.assertFalse(topo.isVisible());
    instance.setCurrentMapType(MapTileType.Topo);
    instance.setLayer();
    Assert.assertFalse(street.isVisible());
    Assert.assertFalse(satellite.isVisible());
    Assert.assertTrue(topo.isVisible());
  }

  /**
   * Test of getSatelliteImage method, of class TileButtonMapElement.
   */
  @Test
  public final void testGetSatelliteImage() {
    System.out.println("getSatelliteImage");
    TileButtonMapElement instance = new TileButtonMapElement();
    Image expResult = new BufferedImage(ICON_SIZE, ICON_SIZE,
        BufferedImage.TYPE_INT_ARGB);
    instance.setSatelliteImage(expResult);
    Image result = instance.getSatelliteImage();
    assertEquals(expResult, result);
  }

  /**
   * Test of getStreetMapImage method, of class TileButtonMapElement.
   */
  @Test
  public final void testGetStreetMapImage() {
    System.out.println("getStreetMapImage");
    TileButtonMapElement instance = new TileButtonMapElement();
    Image expResult = new BufferedImage(ICON_SIZE, ICON_SIZE,
        BufferedImage.TYPE_INT_ARGB);
    instance.setStreetMapImage(expResult);
    Image result = instance.getStreetMapImage();
    assertEquals(expResult, result);
  }

  /**
   * Test of getTopoImage method, of class TileButtonMapElement.
   */
  @Test
  public final void testGetTopoImage() {
    System.out.println("getTopoImage");
    TileButtonMapElement instance = new TileButtonMapElement();
    Image expResult = new BufferedImage(ICON_SIZE, ICON_SIZE,
        BufferedImage.TYPE_INT_ARGB);
    instance.setTopoImage(expResult);
    Image result = instance.getTopoImage();
    assertEquals(expResult, result);
  }

  /**
   * Test of getSatelliteLayer method, of class TileButtonMapElement.
   */
  @Test
  public final void testGetSatelliteLayer() {
    System.out.println("getSatelliteLayer");
    TileButtonMapElement instance = new TileButtonMapElement();
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
    TileButtonMapElement instance = new TileButtonMapElement();
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
    TileButtonMapElement instance = new TileButtonMapElement();
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
    TileButtonMapElement instance = new TileButtonMapElement();
    MapTileType expResult = MapTileType.Topo;
    instance.setCurrentMapType(expResult);
    MapTileType result = instance.getCurrentMapType();
    assertEquals(expResult, result);
  }
}
