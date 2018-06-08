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

package gov.ca.water.shapelite.projection.transforms;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides access to a single shared instance of TransformManager.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class TransformManager {

    private static final Logger LOGGER = Logger.getLogger(TransformManager.class.getName());
    private final List<Transformable> transforms;
     
    //<editor-fold defaultstate="collapsed" desc="Singleton">

    /**
     * Creates a new instance of the TransformManager class.  This is private as part of the
     * singleton pattern.
     */
    private TransformManager() {
        transforms = new ArrayList<>();
        transforms.add(new Aitoff());
        transforms.add(new AlbersConicEqualArea());
        transforms.add(new AlbersEqualArea());
        transforms.add(new AzimuthalEquidistant());
        transforms.add(new BipolarObliqueConformalConic());
        transforms.add(new Bonne());
        transforms.add(new Cassini());
        transforms.add(new CrasterParabolic());
        transforms.add(new CylindricalEqualArea());
        transforms.add(new Eckert1());
        transforms.add(new Eckert2());
        transforms.add(new Eckert3());
        transforms.add(new Eckert4());
        transforms.add(new Eckert5());
        transforms.add(new Eckert6());
        transforms.add(new EquidistantConic());
        transforms.add(new EquidistantCylindrical());
        transforms.add(new Foucaut());
        transforms.add(new GallStereographic());
        transforms.add(new GaussKruger());
        transforms.add(new GeneralSinusoidal());
        transforms.add(new GeoStationarySatellite());
        transforms.add(new Gnomonic());
        transforms.add(new GoodeHomolosine());
        transforms.add(new HammerAitoff());
        transforms.add(new HotineObliqueMercatorAzimuthCenter());
        transforms.add(new HotineObliqueMercatorAzimuthNaturalOrigin());
        transforms.add(new Kavraisky5());
        transforms.add(new Kavraisky7());
        transforms.add(new Krovak());
        transforms.add(new LambertAzimuthalEqualArea());
        transforms.add(new LambertConformalConic());
        transforms.add(new LambertEqualAreaConic());
        transforms.add(new LongLat());
        transforms.add(new Loximuthal());
        transforms.add(new McBrydeThomasFlatPolarSine());
        transforms.add(new Mercator());
        transforms.add(new MercatorAuxiliarySphere());
        transforms.add(new MillerCylindrical());
        transforms.add(new Mollweide());
        transforms.add(new NewZealandMapGrid());
        transforms.add(new ObliqueCylindricalEqualArea());
        transforms.add(new ObliqueMercator());
        transforms.add(new ObliqueStereographicAlternative());
        transforms.add(new Orthographic());
        transforms.add(new Polyconic());
        transforms.add(new PutinsP1());
        transforms.add(new QuarticAuthalic());
        transforms.add(new Robinson());
        transforms.add(new Sinusoidal());
        transforms.add(new Stereographic());
        transforms.add(new StereographicNorthPole());
        transforms.add(new SwissObliqueMercator());
        transforms.add(new TransverseMercator());
        transforms.add(new TwoPointEquidistant());
        transforms.add(new UniversalPolarStereographic());
        transforms.add(new UniversalTransverseMercator());
        transforms.add(new VanderGrinten1());
        transforms.add(new Wagner4());
        transforms.add(new Wagner5());
        transforms.add(new Wagner6());
        transforms.add(new Winkel1());
        transforms.add(new Winkel2());
        transforms.add(new WinkelTripel());
    }

    /**
     * Gets the single shared static instance of the TransformManager class.
     */
    public static TransformManager getInstance() {
        return TransformManagerHolder.INSTANCE;
    }

    

    /**
     * Gets the static internal class that is part of the Singleton Pattern in Java.
     */
    private static class TransformManagerHolder {
        private static final TransformManager INSTANCE = new TransformManager();
    }

    //</editor-fold>
  
    //<editor-fold defaultstate="collapsed" desc="Methods">

    public Transformable getProj4(String name){
        for(Transformable transform : transforms){
            if(transform.getProj4Name() == null){
                boolean stop = true;
            }
            if(transform.getProj4Name().equals(name)){
                return transform.copy();
            }
            if(transform.getProj4Aliases() == null){
                continue;
            }
            for(String alias : transform.getProj4Aliases()){
                if(alias.equals(name)){
                    Transformable copy = transform.copy();
                    copy.setProj4Name(name);
                }
            }
        }
        LOGGER.log(Level.SEVERE, String.format("Could not find transform with proj4 name: {0}.", name));
        return null;
    }
    
    /**
     * Gets the matching transform, and in cases where an alias is used, ensures that the name
     * of the returned transform now matches the alias.
     * @param name
     * @return 
     */
    public Transformable getESRI(String name){
        for(Transformable transform : transforms){
            if(transform.getEsriName().equals(name)){
                return transform.copy();
            }
            if(transform.getEsriAliases() == null){
                continue;
            }
            for(String alias : transform.getEsriAliases()){
                if(alias.equals(name)){
                    Transformable copy = transform.copy();
                    copy.setEsriName(name);
                }
            }
        }
        LOGGER.log(Level.SEVERE, String.format("Could not find transform with proj4 name: {0}.", name));
        return null;
    }
    
    
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Properties">

  
    /**
     * @return the transforms
     */
    public List<Transformable> getTransforms() {
        return transforms;
    }
    
    //</editor-fold>
 }
