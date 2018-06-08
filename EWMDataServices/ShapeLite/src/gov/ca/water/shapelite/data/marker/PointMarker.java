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
package gov.ca.water.shapelite.data.marker;

import gov.ca.water.shapelite.symbology.PointSymbolizer;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Shape;
import java.awt.Color;

/**
 * This class organizes a point feature on the map, combining the symbology, the
 * coordinate location, a name, popup text and other content. This is not called
 * a "Point" because it does not have the topological functions for a point.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PointMarker extends FeatureMarker<PointSymbolizer> {


  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Creates a new marker with a default symbolizer.
   */
  public PointMarker() {
    super(new Feature(new Shape(new CoordXY())));
  }

  /**
   * Creates a new marker based on properties of other point marker.
   */
  public PointMarker(PointMarker other) {
    super(other.getFeature(), other.getSymbolizer());
    this.setId(other.getId());
    this.setName(other.getName());
    this.setLabel(other.getLabel());
    this.setPopupText(other.getPopupText());
    this.setSelected(other.isSelected());
    this.setTag(other.getTag());
  }


  /**
   * Creates a new marker with the specified coordinate as the location. The
   * name will be null.
   *
   * @param coord The coordinate to add.
   */
  public PointMarker(Coord coord) {
    super(new Feature(new Shape(coord)));
  }

  /**
   * Creates a new marker with the specified coordinate as the location and the
   * specified name as the popup text.
   *
   * @param coord The Coord coordinate in geographic values.
   * @param name The String to use as the popup text for this marker.
   */
  public PointMarker(Coord coord, String name) {
    super(new Feature(new Shape(coord)));
    this.setName(name);
  }

  /**
   * Creates a new marker with the specified coordinate as the location and the
   * specified name as the popup text.
   *
   * @param coord The Coord coordinate in geographic values.
   * @param name The String to use as the popup text for this marker.
   * @param fillColor This will automatically create a completely new symbolizer
   * for each point. This is inefficient for datasets with very large numbers of
   * points.
   */
  public PointMarker(Coord coord, String name, Color fillColor) {
    super(new Feature(new Shape(coord)), new PointSymbolizer());
    this.setName(name);
    this.setSymbolizer(new PointSymbolizer(fillColor));
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param shape the point shape containing the Marker's coordinates
   * @param name the Marker's name.
   */
  public PointMarker(Shape shape, String name) {
    super(new Feature(shape));
    this.setName(name);
  }

  /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param feature the point shape containing the Marker's coordinates.
   */
  public PointMarker(Feature feature) {
    super(feature);
  }

   /**
   * Creates a new instance of a MarkerPath based on a shape and a name.
   *
   * @param feature the point shape containing the Marker's coordinates.
   * @param name the Marker's name.
   */
  public PointMarker(Feature feature, String name) {
    super(feature);
    this.setName(name);
  }


  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the geographic coordinate in latitude, longitude (WGS84).
   *
   * @return the coordinate.
   */
  public final Coord getCoordinate() {
    return super.getFeature().getShape().first();
  }

  /**
   * Sets the geographic coordinate in latitude, longitude (WGS84).
   *
   * @param coordinate the coordinate to set.
   */
  public final void setCoordinate(Coord coordinate) {
    super.getFeature().getShape().setX(coordinate.getX());
    super.getFeature().getShape().setY(coordinate.getY());
  }
  //</editor-fold>
  
  
    
  
  
}
