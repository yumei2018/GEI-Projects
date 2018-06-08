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
package gov.ca.water.shapelite.toolbox.tools;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import gov.ca.water.shapelite.toolbox.InputFileParameter;
import gov.ca.water.shapelite.toolbox.OutputFileParameter;
import gov.ca.water.shapelite.toolbox.StringParameter;
import gov.ca.water.shapelite.toolbox.ToolBase;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This tool uses geocoding to assign X and Y values to a specified CSV.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GeocodePoints extends ToolBase {

  /**
   * A delay in milliseconds, to prevent hitting the google web service too
   * quickly.
   */
  private static final int DELAY = 250;

  /**
   * An paramInputFile file parameter.
   */
  private final InputFileParameter paramInputFile;

  /**
   * An paramAddress parameter.
   */
  private final StringParameter paramAddress;

  /**
   * A City parameter for geocoding.
   */
  private final StringParameter paramCity;

  /**
   * An paramApiKey parameter.
   */
  private final StringParameter paramApiKey;

  /**
   * OutputFileParameter paramOutputFile.
   */
  private final OutputFileParameter paramOutputFile;

  /**
   * Creates a new Geocode Points.
   */
  public GeocodePoints() {
    setName("Geocode Points");
    setDescription("Modify Addresses in a CSV to create a CSV with X, Y values.");
    this.setHelpText("This tool reads the Address and City fields in a CSV file"
            + " and creates a new CSV file with an X and Y Field appended.");
    this.setCategory("Analysis");
    this.setHelpImageFilename("resources/Geocode.png");

    paramInputFile = new InputFileParameter();
    paramInputFile.setParameterName("Input CSV");
    paramInputFile.setExtension(".csv");
    paramInputFile.setDescription("The CSV input file with Address and City"
            + " Fields");
    paramInputFile.setHelpText("The input csv file with the address and city"
            + " values.");
    this.getParameters().add(paramInputFile);

    paramAddress = new StringParameter();
    paramAddress.setParameterName("Address");
    paramAddress.setDescription("The String field name of the Address column in the "
            + "CSV file.");
    paramAddress.setHelpText("The String field name of the Address column in "
            + "the CSV file.");
    paramAddress.setParameterText("Address");
    this.getParameters().add(paramAddress);

    paramCity = new StringParameter();
    paramCity.setParameterName("City");
    paramCity.setDescription("The String field name of the City column in the"
            + " CSV file.");
    paramCity.setHelpText("The String field name of the City column in the"
            + " CSV file.");
    paramCity.setParameterText("City");
    this.getParameters().add(paramCity);

    paramApiKey = new StringParameter();
    paramApiKey.setParameterName("Optional Google API Key");
    paramApiKey.setDescription("The optional string api key to use.");
    paramApiKey.setHelpText("The optional string api key you registered with "
            + "google to use.  While not required, requests without the api "
            + "key are limited to 5 per day from the same IP.");
    paramApiKey.setRequired(false);
    this.getParameters().add(paramApiKey);

    paramOutputFile = new OutputFileParameter();
    paramOutputFile.setParameterName("Output CSV File");
    paramOutputFile.setExtension(".csv");
    paramOutputFile.setDescription("The CSV output file that will match the input file"
            + " except have X and Y fields appended");
    paramOutputFile.setHelpText("The CSV output file that will match the input file "
            + "except have X and Y fields appended.");
    this.getParameters().add(paramOutputFile);
  }

  /**
   * Runs the GeocodePoints method in the current thread.
   */
  @Override
  public final void runImmediately() {
    getProgress().create("Geolocate: " + paramInputFile.getParameterName(),
            this.getCancellable());
    try {
      CSVReader reader = new CSVReader(new FileReader(paramInputFile.getParameterText()));
      List<String[]> lines = reader.readAll();
      String[] fields = lines.get(0);
      int addressIndex = Arrays.asList(fields).indexOf(paramAddress.getParameterText());
      int cityIndex = Arrays.asList(fields).indexOf(paramCity.getParameterText());
      String[] outfields = new String[fields.length + 2];
      System.arraycopy(fields, 0, outfields, 0, fields.length);
      outfields[fields.length] = "Latitude";
      outfields[fields.length + 1] = "Longitude";
      int count = lines.size();

      getProgress().start(count);
      try (CSVWriter writer = new CSVWriter(new FileWriter(
              paramOutputFile.getParameterText()))) {

        writer.writeNext(outfields);

        for (int i = 1; i < lines.size(); i++) {
          String[] line = lines.get(i);
          String[] outLine = new String[line.length + 2];
          System.arraycopy(line, 0, outLine, 0, line.length);
          String addressText = line[addressIndex];
          if (addressText == null || addressText.isEmpty()) {
            continue;
          }
          String cityText = line[cityIndex];
          if (cityText == null || cityText.isEmpty()) {
            continue;
          }
          Double lng = null;
          Double lat = null;

          URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="
                  + clean(addressText) + "+" + clean(cityText) + "+" + "CA");
          Scanner s = new Scanner(url.openStream()).useDelimiter("\\A");
          if (!s.hasNext()) {
            continue;
          }
          String text = s.next();
          Gson gson = new GsonBuilder().create();
          Map<?, ?> content = gson.fromJson(text, Map.class);
          if (content.containsKey("results")) {
            Object list = content.get("results");
            if (list instanceof ArrayList<?>) {
              ArrayList<?> items = (ArrayList<?>) content.get("results");
              if (items.size() > 0) {
                Object itemMap = items.get(0);
                if (itemMap instanceof Map<?, ?>) {
                  Map<?, ?> item = (Map<?, ?>) items.get(0);
                  if (item.containsKey("geometry")) {
                    Object geomObject = item.get("geometry");
                    if (geomObject instanceof Map<?, ?>) {
                      Map<?, ?> geom = (Map<?, ?>) item.get("geometry");
                      if (geom.containsKey("location")) {
                        Object locObject = geom.get("location");
                        if (locObject instanceof Map<?, ?>) {
                          Map<?, ?> loc = (Map<?, ?>) locObject;
                          if (loc.containsKey("lng")) {
                            Object lngObject = loc.get("lng");
                            if (lngObject instanceof Double) {
                              lng = (Double) lngObject;
                            }
                          }
                          if (loc.containsKey("lat")) {
                            Object latObject = loc.get("lat");
                            if (latObject instanceof Double) {
                              lat = (Double) loc.get("lat");
                            }

                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          if (lat != null) {
            outLine[line.length] = Double.toString(lat);
          }
          if (lng != null) {
            outLine[line.length + 1] = Double.toString(lng);
          }

          writer.writeNext(outLine);

          try {
            Thread.sleep(DELAY); // sleep 250 ms between calls to limit to 5 / second
          } catch (InterruptedException ex) {
            Logger.getLogger(GeocodePoints.class.getName()).log(
                    Level.SEVERE, ex.getMessage(), ex);
          }

          getProgress().progress(i);
          if (this.isCancelled()) {
            break;
          }
        }
        getMessageHandler().message("Geocode Points.", "Finished exporting points to"
                + paramOutputFile.getParameterText() + ".");
      } catch (IOException ex) {
        Logger.getLogger(GeocodePoints.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
        getMessageHandler().message("Geocode Points", ex.getMessage());
      }

    } catch (FileNotFoundException ex) {
      Logger.getLogger(GeocodePoints.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
      getMessageHandler().message("Geocode Points", ex.getMessage());
    } catch (IOException ex) {
      Logger.getLogger(GeocodePoints.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
      getMessageHandler().message("Geocode Points", ex.getMessage());
    }
    getProgress().finish();
  }

  /**
   * Cleans a string.
   *
   * @param test The string to clean.
   * @return A cleaned string.
   */
  public final String clean(String test) {
    String result = test.replace(" ", "+").replace(",", "+").replace("\n", "");
    return result;
  }

}
