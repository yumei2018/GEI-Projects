/*
 * The MIT License
 *
 * Copyright 2016 rmarquez.
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

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.DefaultEnvelope;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.Vector;
import gov.ca.water.shapelite.analysis.DirectionalPoint;
import gov.ca.water.shapelite.coordinate.CoordBase;
import gov.ca.water.shapelite.coordinate.CoordM;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.coordinate.CoordZ;
import gov.ca.water.shapelite.coordinate.ObservableCoord;
import gov.ca.water.shapelite.coordinate.ObservableCoordM;
import gov.ca.water.shapelite.coordinate.ObservableCoordXY;
import gov.ca.water.shapelite.coordinate.ObservableCoordZ;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * A utility class for supporting json-serialization of common shapelite classes
 * such as Envelope and Coord.
 *
 * @author rmarquez
 */
public final class GsonUtils {

  

  /**
   * Constructor not provided for utility class.
   */
  private GsonUtils() {
  }

  /**
   * Returns an instance of a Coord-supported TypeAdapterFactory which can be
   * registered in GsonBuilder. Register this TypeAdapterFactory when
   * serializing objects which contain coords in the child tree.
   * <p>
   * This implementation currently registers the following subtypes of Coord:
   * <ul>
   * <li> CoordBase</li>
   * <li> CoordM</li>
   * <li> CoordXY</li>
   * <li> CoordZ</li>
   * <li> ObservableCoord</li>
   * <li> ObservableCoordM</li>
   * <li> ObservableCoordXY</li>
   * <li> ObservableCoordZ</li>
   * <li> DirectionalPoint</li>
   * <li> Vector </li>
   * </ul>
   * Additional implementations of the Coord class can be added dynamically by 
   * supplying them them as arguments.
   * </p>
   * <p>
   * <strong> Example usage </strong>: <br>
   * Gson gson = new GsonBuilder()    <br>
   * .setPrettyPrinting() <br>
   * .registerTypeAdapterFactory(<strong>
   * GsonUtils.getCoordTypeAdapter()</strong>) <br>
   * .create(); <br>
   * MyObject myObject = gson.fromJson(jsonString, MyObject.class)
   *
   * </p>
   *
   *
   * @param coordClasses (Optional Arguments) Custom implementations of Coord
   * class.
   * @return TypeAdapterFactory with registered subtypes of Coord
   * @see GsonBuilder
   * @see TypeAdapterFactory
   * @see GsonUtils.getEnvelopeTypeAdapter
   */
  @SafeVarargs // There is no explicit casting in this method. 
  public static final TypeAdapterFactory getCoordTypeAdapter(
          Class<? extends Coord>... coordClasses) {
    
    RuntimeTypeAdapterFactory<Coord> adapter
            = RuntimeTypeAdapterFactory
            .of(Coord.class)
            .registerSubtype(CoordBase.class)
            .registerSubtype(CoordM.class)
            .registerSubtype(CoordXY.class)
            .registerSubtype(CoordZ.class)
            .registerSubtype(ObservableCoordM.class)
            .registerSubtype(ObservableCoordXY.class)
            .registerSubtype(ObservableCoordZ.class)
            .registerSubtype(DirectionalPoint.class)
            .registerSubtype(Vector.class);
    
    if (coordClasses != null) {
      for (Class<? extends Coord> clazz : coordClasses) {
        adapter.registerSubtype(clazz);
      }
    }
    return adapter;
  }

  /**
   * Returns an instance of a Envelope-supported TypeAdapterFactory which can be
   * registered in GsonBuilder. Register this TypeAdapterFactory when
   * serializing objects which contain envelopes in the child tree.
   * <p>
   * This implementation currently registers the following subtypes of
   * Envelope:
   * <ul>
   * <li> DefaultEnvelope </li>
   * </ul>
   * Additional implementations can be added dynamically by supplying them as
   * arguments.
   * </p>
   * <p>
   * <strong> Example usage </strong>: <br>
   * Gson gson = new GsonBuilder()    <br>
   * .setPrettyPrinting() <br>
   * .registerTypeAdapterFactory(<strong>
   * GsonUtils.getEnvelopeTypeAdapter()</strong>) <br>
   * .create(); <br>
   * MyObject myObject = gson.fromJson(jsonString, MyObject.class)
   *
   * </p>
   *
   *
   * @param envelopeClasses (Optional Arguments) Custom implementations of Coord
   * class.
   * @return TypeAdapterFactory with registered subtypes of Coord
   * @see GsonBuilder
   * @see TypeAdapterFactory
   * @see GsonUtils.getCoordTypeAdapter
   */
  @SafeVarargs // There is no explicit casting in this method. 
  public static final TypeAdapterFactory getEnvelopeTypeAdapter(
          Class<? extends Envelope>... envelopeClasses) {

    RuntimeTypeAdapterFactory<Envelope> adapter
            = RuntimeTypeAdapterFactory
            .of(Envelope.class)
            .registerSubtype(DefaultEnvelope.class)
            .registerSubtype(Envelope.class);

    if (envelopeClasses != null) {
      for (Class<? extends Envelope> clazz : envelopeClasses) {
        adapter.registerSubtype(clazz);
      }
    }
    return adapter;
  }
  
  
  /**
   * Registers type adapter for serialization of Coord and ObservableCoord 
   * interfaces. 
   * 
   * @param gsonBuilder 
   */
  public static void registerCoordTypeAdapter(GsonBuilder gsonBuilder) {
    gsonBuilder.registerTypeAdapter(ObservableCoord.class, new JsonDeserializer<ObservableCoord>() {
      @Override
      public ObservableCoord deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) 
              throws JsonParseException {
        JsonElement x = je.getAsJsonObject().get("x"); 
        JsonElement y = je.getAsJsonObject().get("y");
        return new ObservableCoordXY(x.getAsDouble(), y.getAsDouble());
      }
    }); 
  }
}
