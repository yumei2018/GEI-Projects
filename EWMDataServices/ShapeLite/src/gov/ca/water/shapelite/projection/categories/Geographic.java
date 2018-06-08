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

package gov.ca.water.shapelite.projection.categories;

import gov.ca.water.shapelite.projection.categories.geographic.Africa;
import gov.ca.water.shapelite.projection.categories.geographic.Antarctica;
import gov.ca.water.shapelite.projection.categories.geographic.Asia;
import gov.ca.water.shapelite.projection.categories.geographic.Australia;
import gov.ca.water.shapelite.projection.categories.geographic.CountySystems;
import gov.ca.water.shapelite.projection.categories.geographic.Europe;
import gov.ca.water.shapelite.projection.categories.geographic.NorthAmerica;
import gov.ca.water.shapelite.projection.categories.geographic.Oceans;
import gov.ca.water.shapelite.projection.categories.geographic.SolarSystem;
import gov.ca.water.shapelite.projection.categories.geographic.SouthAmerica;
import gov.ca.water.shapelite.projection.categories.geographic.SpheroidBased;
import gov.ca.water.shapelite.projection.categories.geographic.World;
import java.util.ArrayList;
import java.util.List;

/**
 * This is initialization on demand, but is not strictly limited as a singleton.
 * Different instances can be created, rather than just using the instance 
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Geographic {

   public Geographic(){
       
   }
   
   /**
    * Holder class for allowing lazy loading of the Africa 
    */
   private static class AfricaHolder{
       private static final Africa instance = new Africa();
   }

   /**
    * Gets the Africa Projections.
    * @return 
    */
   public Africa getAfrica(){
       return AfricaHolder.instance;
   }
   
   /**
    * Holder class for allowing lazy loading of the Antarctica 
    */
   private static class AntarcticaHolder{
       private static final Antarctica instance = new Antarctica();
   }

   /**
    * Gets the Antarctica Projections.
    * @return 
    */
   public Antarctica getAntarctica(){
       return AntarcticaHolder.instance;
   }
   
   /**
    * Holder class for allowing lazy loading of the Asia 
    */
   private static class AsiaHolder{
       private static final Asia instance = new Asia();
   }

   /**
    * Gets the Asia Projections.
    * @return 
    */
   public Asia getAsia(){
       return AsiaHolder.instance;
   }
   
   /**
    * Holder class for allowing lazy loading of the Australia 
    */
   private static class AustraliaHolder{
       private static final Australia instance = new Australia();
   }

   /**
    * Gets the Australia Projections.
    * @return 
    */
   public Australia getAustralia(){
       return AustraliaHolder.instance;
   }
   
   
   
   /**
    * Holder class for allowing lazy loading of the CountySystems 
    */
   private static class CountySystemsHolder{
       private static final CountySystems instance = new CountySystems();
   }

   /**
    * Gets the CountySystems Projections.
    * @return 
    */
   public CountySystems getCountySystems(){
       return CountySystemsHolder.instance;
   }
   
   
   /**
    * Holder class for allowing lazy loading of the Europe 
    */
   private static class EuropeHolder{
       private static final Europe instance = new Europe();
   }

   /**
    * Gets the Europe Projections.
    * @return 
    */
   public Europe getEurope(){
       return EuropeHolder.instance;
   }
   
   
   /**
    * Holder class for allowing lazy loading of the Oceans 
    */
   private static class OceansHolder{
       private static final Oceans instance = new Oceans();
   }

   /**
    * Gets the Oceans Projections.
    * @return 
    */
   public Oceans getOceans(){
       return OceansHolder.instance;
   }
   
   
   /**
    * Holder class for allowing lazy loading of the SolarSystem 
    */
   private static class SolarSystemHolder{
       private static final SolarSystem instance = new SolarSystem();
   }

   /**
    * Gets the SolarSystem Projections.
    * @return 
    */
   public SolarSystem getSolarSystem(){
       return SolarSystemHolder.instance;
   }
   
   /**
    * Holder class for allowing lazy loading of the SouthAmerica 
    */
   private static class SouthAmericaHolder{
       private static final SouthAmerica instance = new SouthAmerica();
   }

   /**
    * Gets the SouthAmerica Projections.
    * @return 
    */
   public SouthAmerica getSouthAmerica(){
       return SouthAmericaHolder.instance;
   }
   
   /**
    * Holder class for allowing lazy loading of the SpheroidBased 
    */
   private static class SpheroidBasedHolder{
       private static final SpheroidBased instance = new SpheroidBased();
   }

   /**
    * Gets the SpheroidBased Projections.
    * @return 
    */
   public SpheroidBased getSpheroidBased(){
       return SpheroidBasedHolder.instance;
   }
   
   /**
    * Holder class for allowing lazy loading of the World 
    */
   private static class WorldHolder{
       private static final World instance = new World();
   }

   /**
    * Gets the World Projections.
    * @return 
    */
   public World getWorld(){
       return WorldHolder.instance;
   }
   
   
   /**
    * Holder class for allowing lazy loading of the NorthAmerica 
    */
   private static class NorthAmericaHolder{
       private static final NorthAmerica instance = new NorthAmerica();
   }

   /**
    * Gets the NorthAmerica Projections.
    * @return 
    */
   public NorthAmerica getNorthAmerica(){
       return NorthAmericaHolder.instance;
   }
   

   
   /***
    * Instantiates and returns all of the Geographic Coordinate Systems.  Coordinate
    * systems categories accessed from the Geographic getters are lazy loaded,
    * but getAll forces all of the categories to load into memory before returning
    * the static ProjectionInfo instances on each.
    * @return 
    */
   public List<CoordinateSystemCategory> getAll(){
       List<CoordinateSystemCategory> result = new ArrayList<>();
       result.add(getAfrica());
       result.add(getAntarctica());
       result.add(getAsia());
       result.add(getAustralia());
       result.add(getCountySystems());
       result.add(getEurope());
       result.add(getNorthAmerica());
       result.add(getOceans());
       result.add(getSolarSystem());
       result.add(getSouthAmerica());
       result.add(getSpheroidBased());
       result.add(getWorld());
       return result;
   }

    

}
