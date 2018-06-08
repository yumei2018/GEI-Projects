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
package gov.ca.water.shapelite.raster;

import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.data.dataset.RasterDataset;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * http://edndoc.esri.com/arcobjects/9.2/net/shared/geoprocessing/spatial_analyst_tools/how_hillshade_works.htm
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class HillShade {

    /**
     * Calculates a buffered Image representing the hill shade from the
     * specified elevations.
     *
     * @param elevationValues The y-major jagged array of double values
     * representing elevations.
     * @param lightAltitude The angle in degrees above the horizon.
     * @param lightAzimuth The angle in degrees (0 is north, 90 east etc.)
     * @param zFactor The conversion from elevation units to x,y units. For feet
     * to decimal degrees, this would be .000003
     * @return A BufferedImage showing the hillshade.
     */
    public static BufferedImage createHillshade(RasterDataset raster, int band, double lightAltitude, double lightAzimuth, double zFactor) {
        double zenithDeg = 90 - lightAltitude;
        double zenithRad = zenithDeg * Math.PI / 180;
        double azimuthMath = 360 - lightAzimuth + 90;
        double cellsize = raster.getEnvelopeMercator().getWidth() / raster.getWidth();
        double[][] data = raster.getBands().get(0).getData();
        if (azimuthMath > 360) {
            azimuthMath = azimuthMath - 360;
        }
        double azimuthRad = azimuthMath * Math.PI / 180;
        BufferedImage result = new BufferedImage(raster.getWidth(), raster.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 1; y < raster.getHeight() - 1; y++) {
            for (int x = 1; x < raster.getWidth() - 1; x++) {
               // a b c
                // d e f
                // g h i
                double a = data[y - 1][x - 1];
                double b = data[y - 1][x];
                double c = data[y - 1][x + 1];
                double d = data[y][x - 1];
                double e = data[y][x];
                double f = data[y][x + 1];
                double g = data[y + 1][x - 1];
                double h = data[y + 1][x];
                double i = data[y + 1][x + 1];

                double dz_dx = ((c + 2 * f + i) - (a + 2 * d + g)) / (8 * cellsize);
                double dz_dy = ((g + 2 * h + i) - (a + 2 * b + c)) / (8 * cellsize);
                double slopeRad = Math.atan(zFactor * Math.sqrt(dz_dx * dz_dx + dz_dy * dz_dy));
                double aspectRad = 0;
                if (dz_dx != 0) {
                    aspectRad = Math.atan2(dz_dy, -dz_dx);
                    if (aspectRad < 0) {
                        aspectRad = Math.PI * 2 + aspectRad;
                    }
                } else {
                    if (dz_dy > 0) {
                        aspectRad = Math.PI / 2;
                    } else if (dz_dy < 0) {
                        aspectRad = Math.PI * 2 - Math.PI / 2;
                    }
                }

                double hillshade = 255 * ((Math.cos(zenithRad) * Math.cos(slopeRad))
                        + (Math.sin(zenithRad) * Math.sin(slopeRad) * Math.cos(azimuthRad - aspectRad)));
                int hs = (int) Math.round(hillshade);
                if (hs > 255) {
                    hs = 255;
                }
                if (hs < 0) {
                    hs = 0;
                }
                Color col = new Color(hs, hs, hs, 255);
                result.setRGB(x, y, col.getRGB());

            }
        }
        return result;

    }

    /**
     * Calculates a buffered Image representing the hill shade from the
     * specified elevations. This will define the lighting based on transparency
     * with black and white, allowing grays to become transparent.
     *
     * @param raster
     * @param band
     * @param lightAltitude The angle in degrees above the horizon.
     * @param lightAzimuth The angle in degrees (0 is north, 90 east etc.)
     * @param zFactor The conversion from elevation units to x,y units. For feet
     * to decimal degrees, this would be .000003
     * @return A BufferedImage showing the hillshade.
     */
    public static BufferedImage createHillshadeTransparent(RasterDataset raster, int band, double lightAltitude, double lightAzimuth, double zFactor) {
        double zenithDeg = 90 - lightAltitude;
        double zenithRad = zenithDeg * Math.PI / 180;
        double azimuthMath = 360 - lightAzimuth + 90;
        double cellsize = raster.getEnvelopeMercator().getWidth() / raster.getWidth();
        double[][] data = raster.getBands().get(0).getData();
        if (azimuthMath > 360) {
            azimuthMath = azimuthMath - 360;
        }
        double azimuthRad = azimuthMath * Math.PI / 180;
        BufferedImage result = new BufferedImage(raster.getWidth(), raster.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < raster.getHeight(); y++) {
            for (int x = 0; x < raster.getWidth(); x++) {
               // a b c
                // d e f
                // g h i
                double e = data[y][x];
                double a = e;
                double b = e;
                double c = e;
                double d = e;
                double f = e;
                double g = e;
                double h = e;
                double i = e;

                if (y > 0) {
                    if (x > 0) {
                        a = data[y - 1][x - 1];
                    }
                    b = data[y - 1][x];
                    if (x < 255) {
                        c = data[y - 1][x + 1];
                    }
                }
                if(x > 0){
                    d = data[y][x - 1];
                }
                if(x < 255){
                    f = data[y][x + 1];
                }


                if (y < 255) {
                    if (x > 0) {
                        g = data[y + 1][x - 1];
                    }

                    h = data[y + 1][x];
                    if (x < 255) {
                        i = data[y + 1][x + 1];
                    }

                }

                double dz_dx = ((c + 2 * f + i) - (a + 2 * d + g)) / (8 * cellsize);
                double dz_dy = ((g + 2 * h + i) - (a + 2 * b + c)) / (8 * cellsize);
                double slopeRad = Math.atan(zFactor * Math.sqrt(dz_dx * dz_dx + dz_dy * dz_dy));
                double aspectRad = 0;
                if (dz_dx != 0) {
                    aspectRad = Math.atan2(dz_dy, -dz_dx);
                    if (aspectRad < 0) {
                        aspectRad = Math.PI * 2 + aspectRad;
                    }
                } else {
                    if (dz_dy > 0) {
                        aspectRad = Math.PI / 2;
                    } else if (dz_dy < 0) {
                        aspectRad = Math.PI * 2 - Math.PI / 2;
                    }
                }

                double hillshade = 255 * ((Math.cos(zenithRad) * Math.cos(slopeRad))
                        + (Math.sin(zenithRad) * Math.sin(slopeRad) * Math.cos(azimuthRad - aspectRad)));
                int hs = (int) Math.round(hillshade);
                if (hs > 255) {
                    hs = 255;
                }
                if (hs < 0) {
                    hs = 0;
                }
                int col = 0;
                if (hs > 128) {
                    col = 255;
                }
                int alpha = Math.abs(128 - hs);

                Color color = new Color(col, col, col, alpha);
                result.setRGB(x, y, color.getRGB());
                

            }
        }
        return result;

    }

    /**
     * Calculates a buffered Image representing the hill shade from the
     * specified elevations. This will define the lighting based on transparency
     * with black and white, allowing grays to become transparent.
     *
     * @param rasters a 3x3 matrix of rasters [y][x] order.  Some of these may be null, but the center one must not be.
     * the edges from each of other rasters are used only for calculating what the illiumination values should be on the edges.
     * @param lightAltitude The angle in degrees above the horizon.
     * @param lightAzimuth The angle in degrees (0 is north, 90 east etc.)
     * @param zFactor The conversion from elevation units to x,y units. For feet
     * to decimal degrees, this would be .000003
     * @return A BufferedImage showing the hillshade.
     */
    public static BufferedImage createHillshadeTransparent(RasterDataset[][] rasters, double lightAltitude, double lightAzimuth, double zFactor) {
        double zenithDeg = 90 - lightAltitude;
        double zenithRad = zenithDeg * Math.PI / 180;
        double azimuthMath = 360 - lightAzimuth + 90;
        if(rasters.length != 3){
            return null;
        }
        if(rasters[0].length != 3 || rasters[1].length != 3 || rasters[2].length != 3){
            return null;
        }
        RasterDataset raster = rasters[1][1];
        if(raster == null){
            return null;
        }
        double cellsize = raster.getEnvelopeMercator().getWidth() / raster.getWidth();
        double[][] data = raster.getBands().get(0).getData();
        if (azimuthMath > 360) {
            azimuthMath = azimuthMath - 360;
        }
        double azimuthRad = azimuthMath * Math.PI / 180;
        BufferedImage result = new BufferedImage(raster.getWidth(), raster.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < raster.getHeight(); y++) {
            for (int x = 0; x < raster.getWidth(); x++) {
                // a b c
                // d e f
                // g h i

                // Get value will also check neighboring rasters for the edge cells.
                double a = getValue(x-1, y-1, rasters);
                double b = getValue(x, y-1, rasters);
                double c = getValue(x+1, y-1, rasters);
                double d = getValue(x-1, y, rasters);
                double e = getValue(x, y, rasters);
                double f = getValue(x+1, y, rasters);
                double g = getValue(x-1, y+1, rasters);
                double h = getValue(x, y+1, rasters);
                double i = getValue(x+1, y+1, rasters);

                double dz_dx = ((c + 2 * f + i) - (a + 2 * d + g)) / (8 * cellsize);
                double dz_dy = ((g + 2 * h + i) - (a + 2 * b + c)) / (8 * cellsize);
                double slopeRad = Math.atan(zFactor * Math.sqrt(dz_dx * dz_dx + dz_dy * dz_dy));
                double aspectRad = 0;
                if (dz_dx != 0) {
                    aspectRad = Math.atan2(dz_dy, -dz_dx);
                    if (aspectRad < 0) {
                        aspectRad = Math.PI * 2 + aspectRad;
                    }
                } else {
                    if (dz_dy > 0) {
                        aspectRad = Math.PI / 2;
                    } else if (dz_dy < 0) {
                        aspectRad = Math.PI * 2 - Math.PI / 2;
                    }
                }

                double hillshade = 255 * ((Math.cos(zenithRad) * Math.cos(slopeRad))
                        + (Math.sin(zenithRad) * Math.sin(slopeRad) * Math.cos(azimuthRad - aspectRad)));
                int hs = (int) Math.round(hillshade);
                if (hs > 255) {
                    hs = 255;
                }
                if (hs < 0) {
                    hs = 0;
                }
                int col = 0;
                if (hs > 128) {
                    col = 255;
                }
                int alpha = Math.abs(128 - hs);

                Color color = new Color(col, col, col, alpha);
                result.setRGB(x, y, color.getRGB());

            }
        }
        return result;

    }

    private static double getValue(int x, int y, RasterDataset[][] rasters){
        if(x >= 0 && x < 256 && y >= 0 && y < 256){
            return rasters[1][1].getData()[y][x];
        }
        int rx = 1;
        int ry = 1;
        int u = x;
        int v = y;
        if(x < 0){
            u = 256+x;
            rx = 0;
        }
        if(x > 255){
            u = x - 256;
            rx = 2;
        }
        if(y < 0){
            v = 256+y;
            ry = 0;
        }
        if(y > 255){
            v = y-256;
            ry = 2;
        }
        if(rasters[ry][rx] != null){
            return rasters[ry][rx].getData()[v][u];
        }
        // the requested edge raster was null, return the closest value in raster.
        u = x;
        v = y;
        if(x > 255){
            u = 255;
        }
        if(x < 0){
            u = 0;
        }
        if(y > 255){
            v = 255;
        }
        if( y < 0){
            v = 0;
        }
        return rasters[1][1].getData()[v][u];
    }
}
