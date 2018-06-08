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

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ProjectionException extends Exception {

    /**
     * Gets the array of error messages according to the Proj4 numbering.
     */
    private static final String[] errors = {
        "No arguments in initialization list", //1
        "No options found in 'init' file",
        "No colon in init= string",
        "Projection not named",
        "UnknownProjectionId",
        "Effective eccentricity = 1",
        "Unkown unit conversion ID",
        "Invalid boolean parameter argument",
        "The elliptical parameter ellps= was unkown",
        "The reciprocal flattening (1/f) = 0",
        "The Latitude was greater than 90 or less than -90",
        "The square of eccentricity cannot be a negative value",
        "The given semi-major axis was 0 or not given.",
        "The Latitude or Longitude exceeded limits",
        "The cartesian X or Y coordinate was invalid",
        "The Degree Minute Second value is formed imporperly",
        "The inverse meridinal distance was non-convergent",
        "The inverse Phi2 value was non-converngent",
        "The ArcCosign or ArcSign value was too large",
        "Tolerance Condition Error",
        "The conic lat_1 value cannot be its -lat_2",
        "Latitude 1 cannot be greater than or equal to 90",
        "Latitude 2 cannot be equal to 0",
        "The lat_ts parameter cannot be greater than or equal to 90",
        "There was no distance between the control points",
        "Projection not selected to be rotated",
        "W <= 0 or M <= 0",
        "lsat not in 1-5 range",
        "Path not in range",
        "H was negative or 0",
        "K was less than 0",
        "The central latitude was 0 or 90 or alpha = 90",
        "Lat_1 = lat_2 or lat_1 = 0 or lat_2 = 90",
        "Elliptical Usage Required",
        "The UTM zone parameter was invalid",
        "The arguments were out of range for Tcheby eval",
        "Projection Not Found",
        "Failed to load NAD27-83 correction file",
        "Both n and m must be specified and > 0",
        "N<= 0, n > 1 or not specified",
        "lat_1 or lat_2 not specified",
        "|lat_1| =|lat_2|",
        "lat_0 is pi/2 from mean lat",
        "Failed to parse coordinate system definition",
        "Geocentric transformation missing z or ellips",
        "Uknown prime meridian conversion ID"
    };

    /**
     * Creates a new instance of <code>ProjectionException</code> without a
     * detail message.
     */
    public ProjectionException() {
    }

    /**
     * Constructs an instance of <code>ProjectionException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public ProjectionException(String message) {
        super(message);
    }

    /**
     * Constructs an instance of <code>ProjectionException</code> with the
     * specified <code>Throwable</code> cause.
     *
     * @param cause the original source of the message.
     */
    public ProjectionException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    /**
     * Constructs an instance of <code>ProjectionException</code> with the
     * specified message and <code>Throwable</code> cause.
     *
     * @param message the detail message.
     * @param cause the original source of the message.
     */
    public ProjectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a ProjectionException with the specified index.
     * @param index
     */
    public ProjectionException(int index) {
        super(errors[index - 1]);
    }

}
