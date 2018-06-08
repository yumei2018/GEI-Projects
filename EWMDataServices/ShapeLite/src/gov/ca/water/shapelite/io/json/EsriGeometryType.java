/*
 * The MIT License
 *
 * Copyright 2016 Harold A. Dunsford Jr. Ph.D..
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
package gov.ca.water.shapelite.io.json;

/**
 * Only Point, Multipoint, Polyline, and Polygon are supported for shapefile
 * conversion.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum EsriGeometryType {
  /**
   * A geometry of unknown type.
   */
  esriGeometryNull,
  /**
   * A single zero dimensional geometry.
   */
  esriGeometryPoint,
  /**
   * An ordered collection of points.
   */
  esriGeometryMultipoint,
  /**
   * A straight line segment between two points.
   */
  esriGeometryLine,
  /**
   * A portion of the boundary of a circle.
   */
  esriGeometryCicularArc,
  /**
   * A portion of the boundary of an ellipse.
   */
  esriGeometryEllipticArc,
  /**
   * A third degree bezier curve (four control points).
   */
  esriGeometryBesier3Curve,
  /**
   * A connected sequence of segments.
   */
  esriGeometryPath,
  /**
   * An ordered collection of paths.
   */
  esriGeometryPolyline,
  /**
   * An area bounded by one closed path.
   */
  esriGeometryRing,
  /**
   * A collection of rings ordered by their containment relationship.
   */
  esriGeometryPolygon,
  /**
   * A rectangle indicating the spatial extent of another geometry.
   */
  esriGeometryEnvelope,
  /**
   * Any of the geometry coclass types.
   */
  esriGeometryAny,
  /**
   * A collection of geometries of arbitrary type.
   */
  esriGeometryBag,
  /**
   * A collection of surface patches.
   */
  esriGeometryMultiPatch,
  /**
   * A surface patch of triangles defined by three consecutive points.
   */
  esriGeometryTriangleStrip,
  /**
   * A surface patch of triangles defined by the first point and two consecutive
   * points.
   */
  esriGeometryTriangleFan,
  /**
   * An infinite, one-directional line extending from an origin point.
   */
  esriGeometryRay,
  /**
   * A complete 3 dimensional sphere.
   */
  esriGeometrySphere,
  /**
   * A surface patch of triangles defined by non-overlapping sets of three
   * consecutive points each.
   */
  esriGeometryTriangles
}
