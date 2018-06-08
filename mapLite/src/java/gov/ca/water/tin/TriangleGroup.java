/*
 * The MIT License
 *
 * Copyright 2013 hdunsford.
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
package gov.ca.water.tin;

import com.vividsolutions.jts.geom.Coordinate;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.utils.Conversion;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A TriangleGroup contains a list of triangles build from a list of vertices.
 * @author hdunsford
 */
public class TriangleGroup implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Static Logger">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger logger = Logger.getLogger(TriangleGroup.class.getName());
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * The name of the Group
   */
  private String name;
  /**
   * A common color for rendering the groups
   */
  private Color color;
  /**
   * The list for vertices used to construct the triangles
   */
  private List<Coord> vertices;
  /**
   * the list of triangles
   */
  private List<Triangle> triangles;
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Parameterless Constructor
   */
  public TriangleGroup() {
    this.name = null;
    this.color = null;
    this.vertices = new ArrayList<>();
    this.triangles = new ArrayList<>();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Methods">
  /**
   * Called to initiate the Group's triangles form the assigned list of vertices.
   */
  private void onInitTriangles() {
    this.triangles.clear();
    if (this.vertices.isEmpty()) {
      return;
    }
    
    try {
      List<Coordinate> coords = Conversion.toJtsCoords(this.vertices);
      Analysis analysis = new Analysis();
      this.triangles = analysis.getTriangles(coords);
    } catch (Exception exp) {
      logger.log(Level.WARNING, "{0}.onInitTriangles Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    } finally {
      this.onSyncTriangleIndices();
    }
  }
  
  /**
   * Call to sync the indices of the Triangles with the Group's vertices. If a vertex is
   * not found in the list, it will be added.
   */
  private void onSyncTriangleIndices() {
    if (this.triangles.isEmpty()) {
      return;
    }
    try {
      for (Triangle triangle : this.triangles) {
        int iA = this.vertices.indexOf(triangle.a);
        if (iA < 0) {
          if (this.vertices.add(triangle.a)) {
            iA = this.vertices.indexOf(triangle.a);
          }
        }
        int iB = this.vertices.indexOf(triangle.b);
        if (iB < 0) {
          if (this.vertices.add(triangle.b)) {
            iB = this.vertices.indexOf(triangle.b);
          }
        }
        int iC = this.vertices.indexOf(triangle.c);
        if (iC < 0) {
          if (this.vertices.add(triangle.c)) {
            iC = this.vertices.indexOf(triangle.c);
          }
        }
        triangle.setIndices(iA, iB, iC);
      }
    } catch (Exception exp) {
      logger.log(Level.WARNING, "{0}.onSyncTriangleIndices Error:\n {1}",
              new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * Get the group's name
   * @return the assigned value
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Set the group's name
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Get the group's color
   * @return the assigned color (can be null)
   */
  public Color getColor() {
    return color;
  }
  
  /**
   * Set the group's color
   * @param color the new color to set
   */
  public void setColor(Color color) {
    this.color = color;
  }
  
  /**
   * Get the list of vertices used in constructing the triangle grid
   * @return the list of vertices
   */
  public List<Coord> getVertices() {
    return vertices;
  }
  
  /**
   * Set the list of vertices used in constructing the triangle grid and call
   * {@linkplain #onInitTriangles() this.onInitTriangles} to initiate the triangles
   * and sync it with the list of vertices
   * @param vertices the vertices to set
   */
  public void setVertices(List<Coord> vertices) {
    this.triangles.clear();
    if ((vertices == null) || (vertices.isEmpty())) {
      this.vertices.clear();
    } else {
      this.vertices.addAll(vertices);
      this.onInitTriangles();
    }
  }
  
  /**
   * Get the Group's list of Triangle
   * @return the current list of triangles
   */
  public List<Triangle> getTriangles() {
    return triangles;
  }
  
  /**
   * Get the Group's list of Triangle. It also initiates the list of vertices that
   * define the triangles by calling {@linkplain #onSyncTriangleIndices()
   * this.onSyncTriangleIndices} to sync the Triangle with the indices of its
   * coordinates within the list vertices and add vertices as needed.
   * @param triangles the triangles to set
   */
  public void setTriangles(List<Triangle> triangles) {
    this.vertices.clear();
    if ((triangles == null) || (triangles.isEmpty())) {
      this.triangles.clear();
    } else {
      this.triangles.addAll(triangles);
      this.onSyncTriangleIndices();
    }
  }
  
  /**
   * @return the vertexCount
   */
  public int getVertexCount() {
    return (this.vertices == null)? 0: this.vertices.size();
  }
  
  /**
   * @return the triangleCount
   */
  public int getTriangleCount() {
    return (this.triangles == null)? 0: this.triangles.size();
  }
  //</editor-fold>
}
