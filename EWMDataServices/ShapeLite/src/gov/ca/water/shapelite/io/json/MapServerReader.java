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

import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Feature;
import gov.ca.water.shapelite.FeatureSet;
import gov.ca.water.shapelite.Optional;
import gov.ca.water.shapelite.ShapefileException;
import gov.ca.water.shapelite.io.ShapefileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public final class MapServerReader {

  /**
   * The data access class that abstracts out retrieving the actual JSON
   * strings.
   */
  private JsonSource source;

  /**
   * Gets or sets the envelope to use to restrict the query.
   */
  private Envelope envelope;

  /**
   * The well known ID of the geographic reference for the envelope. By default
   * this is 4326, or WGS84 latitude and longitude.
   */
  private String envelopeWkid;

  /**
   * Private constructor for utility class.
   *
   * @param urlAddress The base address of the ESRI MapServer.
   */
  public MapServerReader(String urlAddress) {
    source = new JsonSource(urlAddress);
    envelopeWkid = "4326";
  }

  /**
   * Exports the layer from the specified url to a zipped shapefile format
   * written directly to the stream.
   *
   * @param layerId The integer layer id.
   * @param stream The OutputStream.
   * @throws java.net.MalformedURLException If the URL is invalid.
   * @throws gov.ca.water.shapelite.ShapefileException If the result information
   * could not be compiled correctly into a shapefile.
   * @throws IOException if there was an error writing to the stream.
   */
  public void export(int layerId, OutputStream stream)
      throws MalformedURLException, IOException, ShapefileException {
    JsonFeatureSetFactory factory = new JsonFeatureSetFactory();
    Optional<String> maybeDef = source.getDefinition(layerId);
    if (!maybeDef.isPresent()) {
      return;
    }
    EsriLayerDef def = factory.getDef(maybeDef.get());
    Optional<String> maybeCount = source.getFeatureCount(layerId);
    int count = 0;
    if (maybeCount.isPresent()) {
      count = factory.getFeatureCount(maybeCount.get());
    }
    String filter = "";
    filter += "&returnZ=" + def.getHasZ();
    filter += "&returnM=" + def.getHasM();
    filter += "&outFields=" + def.getFieldNames();
    filter += "&f=pjson"; // JSON format.
    if (envelope != null) {
      filter += "geometry=%7B%0D%0A\"xmin\"+%3A+" + envelope.getMin().getX()
          + "%2C+\"ymin\"+%3A+" + envelope.getMin().getY() + "%2C+%0D%0A\"xmax\"+%3A+"
          + envelope.getMax().getX() + "%2C+\"ymax\"+%3A+" + envelope.getMax().getY()
          + "32%2C%0D%0A\"spatialReference\"+%3A+%7B\"wkid\"+%3A+" + envelopeWkid
          + "%7D%0D%0A%7D&geometryType=esriGeometryEnvelope";
    }

    FeatureSet fs = null;
    if (count < def.getMaxRecordCount() || def.getMaxRecordCount() == 0) {
      Optional<String> maybeFeatureSet = source.getFeatureSet(layerId, filter);
      if (maybeFeatureSet.isPresent()) {
        String jsonText = maybeFeatureSet.get();
        fs = getFeatureSet(def, jsonText, factory);
      }
    } else {

      int size = def.getMaxRecordCount();
      boolean first = true;
      if (def.getField("FID").isPresent()) {
        for (int iChunk = 0; iChunk <= Math.ceil(count / size); iChunk++) {
          int start = iChunk * size;
          int end = (iChunk + 1) * size;
          Optional<String> maybeFeatureSet = source.getFeatureSet(layerId,
              filter, start, end);
          if (maybeFeatureSet.isPresent()) {
            String jsonText = maybeFeatureSet.get();

            FeatureSet chunk = getFeatureSet(def, jsonText, factory);
            if (first) {
              fs = chunk;
            } else {
              for (Feature feature : chunk.getFeatures()) {
                if (fs != null) {
                  fs.getFeatures().add(feature);
                }
              }
            }
            first = false;
          }
        }
      }

    }
    if (fs != null) {
      ShapefileWriter writer = new ShapefileWriter();
      writer.write(fs, stream);
    }
  }

  /**
   * Gets the FeatureSet for the specified jsonFeatures using the layer def and
   * the specified factory.
   *
   * @param def EsriLayerDef object defining the layer definition.
   * @param jsonFeatures The Json string defining the Features to extract.
   * @param factory A JsonFeatureSetFactory to create the JsonFeatureSet.
   * @return A FeatureSet created from the Json feature set.
   */
  private FeatureSet getFeatureSet(EsriLayerDef def, String jsonFeatures,
      JsonFeatureSetFactory factory) {
    FeatureSet fs = new FeatureSet();
    switch (def.getGeometryType()) {
      case esriGeometryPoint:
        JsonFeatureSet<JsonPoint> jsonPoint = factory.getPoints(jsonFeatures);
        fs = jsonPoint.toFeatureSet(def.getHasM(), def.getHasZ());
        break;
      case esriGeometryLine:
        JsonFeatureSet<JsonLine> jsonLine = factory.getLines(jsonFeatures);
        fs = jsonLine.toFeatureSet(def.getHasM(), def.getHasZ());
        break;
      case esriGeometryMultipoint:
        JsonFeatureSet<JsonMultiPoint> jsonMp = factory.getMultiPoints(jsonFeatures);
        fs = jsonMp.toFeatureSet(def.getHasM(), def.getHasZ());
        break;
      case esriGeometryPolygon:
        JsonFeatureSet<JsonPolygon> jsonPg = factory.getPolygons(jsonFeatures);
        fs = jsonPg.toFeatureSet(def.getHasM(), def.getHasZ());
        break;
      default:
        throw new IllegalArgumentException("Geometry Type: "
            + def.getGeometryType() + " is not supported.");
      // do nothing.
    }
    return fs;
  }

  /**
   * @return the source
   */
  public JsonSource getSource() {
    return source;
  }

  /**
   * @param source the source to set
   */
  public void setSource(JsonSource source) {
    this.source = source;
  }

  /**
   * @return the envelope
   */
  public Envelope getEnvelope() {
    return envelope;
  }

  /**
   * @param envelope the envelope to set
   */
  public void setEnvelope(Envelope envelope) {
    this.envelope = envelope;
  }

  /**
   * @return the envelopeWkid
   */
  public String getEnvelopeWkid() {
    return envelopeWkid;
  }

  /**
   * @param envelopeWkid the envelopeWkid to set
   */
  public void setEnvelopeWkid(String envelopeWkid) {
    this.envelopeWkid = envelopeWkid;
  }

}
