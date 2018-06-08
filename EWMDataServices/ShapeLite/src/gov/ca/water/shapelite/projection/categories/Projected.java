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

import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.projected.NorthAmerica;
import gov.ca.water.shapelite.projection.categories.projected.Africa;
import gov.ca.water.shapelite.projection.categories.projected.Asia;
import gov.ca.water.shapelite.projection.categories.projected.Europe;
import gov.ca.water.shapelite.projection.categories.projected.GaussKrugerBeijing1954;
import gov.ca.water.shapelite.projection.categories.projected.GaussKrugerOther;
import gov.ca.water.shapelite.projection.categories.projected.GaussKrugerPulkovo1942;
import gov.ca.water.shapelite.projection.categories.projected.GaussKrugerPulkovo1995;
import gov.ca.water.shapelite.projection.categories.projected.KrugerXian1980;
import gov.ca.water.shapelite.projection.categories.projected.Minnesota;
import gov.ca.water.shapelite.projection.categories.projected.Nad1983IntlFeet;
import gov.ca.water.shapelite.projection.categories.projected.NationalGrids;
import gov.ca.water.shapelite.projection.categories.projected.NationalGridsAustralia;
import gov.ca.water.shapelite.projection.categories.projected.NationalGridsCanada;
import gov.ca.water.shapelite.projection.categories.projected.NationalGridsIndia;
import gov.ca.water.shapelite.projection.categories.projected.NationalGridsJapan;
import gov.ca.water.shapelite.projection.categories.projected.NationalGridsNewZealand;
import gov.ca.water.shapelite.projection.categories.projected.NationalGridsNorway;
import gov.ca.water.shapelite.projection.categories.projected.NationalGridsSweden;
import gov.ca.water.shapelite.projection.categories.projected.Polar;
import gov.ca.water.shapelite.projection.categories.projected.SouthAmerica;
import gov.ca.water.shapelite.projection.categories.projected.SpheroidBased;
import gov.ca.water.shapelite.projection.categories.projected.StatePlaneNad1927;
import gov.ca.water.shapelite.projection.categories.projected.StatePlaneNad1983;
import gov.ca.water.shapelite.projection.categories.projected.StatePlaneNad1983Feet;
import gov.ca.water.shapelite.projection.categories.projected.StatePlaneNad1983Harn;
import gov.ca.water.shapelite.projection.categories.projected.StatePlaneNad1983HarnFeet;
import gov.ca.water.shapelite.projection.categories.projected.StatePlaneOther;
import gov.ca.water.shapelite.projection.categories.projected.StateSystems;
import gov.ca.water.shapelite.projection.categories.projected.TransverseMercatorSystems;
import gov.ca.water.shapelite.projection.categories.projected.UtmNad1927;
import gov.ca.water.shapelite.projection.categories.projected.UtmNad1983;
import gov.ca.water.shapelite.projection.categories.projected.UtmOther;
import gov.ca.water.shapelite.projection.categories.projected.UtmWgs1972;
import gov.ca.water.shapelite.projection.categories.projected.UtmWgs1984;
import gov.ca.water.shapelite.projection.categories.projected.Wisconsin;
import gov.ca.water.shapelite.projection.categories.projected.World;
import gov.ca.water.shapelite.projection.categories.projected.WorldSpheroid;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Projected {


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
    * Holder class for allowing lazy loading of the GaussKrugerBeijing1954 
    */
   private static class GaussKrugerBeijing1954Holder{
       private static final GaussKrugerBeijing1954 instance = new GaussKrugerBeijing1954();
   }

   /**
    * Gets the GaussKrugerBeijing1954 Projections.
    * @return 
    */
   public GaussKrugerBeijing1954 getGaussKrugerBeijing1954(){
       return GaussKrugerBeijing1954Holder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the GaussKrugerOther 
    */
   private static class GaussKrugerOtherHolder{
       private static final GaussKrugerOther instance = new GaussKrugerOther();
   }

   /**
    * Gets the GaussKrugerOther Projections.
    * @return 
    */
   public GaussKrugerOther getGaussKrugerOther(){
       return GaussKrugerOtherHolder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the GaussKrugerPulkovo1942 
    */
   private static class GaussKrugerPulkovo1942Holder{
       private static final GaussKrugerPulkovo1942 instance = new GaussKrugerPulkovo1942();
   }

   /**
    * Gets the GaussKrugerPulkovo1942 Projections.
    * @return 
    */
   public GaussKrugerPulkovo1942 getGaussKrugerPulkovo1942(){
       return GaussKrugerPulkovo1942Holder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the GaussKrugerPulkovo1995 
    */
   private static class GaussKrugerPulkovo1995Holder{
       private static final GaussKrugerPulkovo1995 instance = new GaussKrugerPulkovo1995();
   }

   /**
    * Gets the GaussKrugerPulkovo1995 Projections.
    * @return 
    */
   public GaussKrugerPulkovo1995 getGaussKrugerPulkovo1995(){
       return GaussKrugerPulkovo1995Holder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the KrugerXian1980 
    */
   private static class KrugerXian1980Holder{
       private static final KrugerXian1980 instance = new KrugerXian1980();
   }

   /**
    * Gets the KrugerXian1980 Projections.
    * @return 
    */
   public KrugerXian1980 getKrugerXian1980(){
       return KrugerXian1980Holder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the Minnesota 
    */
   private static class MinnesotaHolder{
       private static final Minnesota instance = new Minnesota();
   }

   /**
    * Gets the Minnesota Projections.
    * @return 
    */
   public Minnesota getMinnesota(){
       return MinnesotaHolder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the Nad1983IntlFeet 
    */
   private static class Nad1983IntlFeetHolder{
       private static final Nad1983IntlFeet instance = new Nad1983IntlFeet();
   }

   /**
    * Gets the Nad1983IntlFeet Projections.
    * @return 
    */
   public Nad1983IntlFeet getNad1983IntlFeet(){
       return Nad1983IntlFeetHolder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the NationalGrids 
    */
   private static class NationalGridsHolder{
       private static final NationalGrids instance = new NationalGrids();
   }

   /**
    * Gets the NationalGrids Projections.
    * @return 
    */
   public NationalGrids getNationalGrids(){
       return NationalGridsHolder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the NationalGridsAustralia 
    */
   private static class NationalGridsAustraliaHolder{
       private static final NationalGridsAustralia instance = new NationalGridsAustralia();
   }

   /**
    * Gets the NationalGridsAustralia Projections.
    * @return 
    */
   public NationalGridsAustralia getNationalGridsAustralia(){
       return NationalGridsAustraliaHolder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the NationalGridsCanada 
    */
   private static class NationalGridsCanadaHolder{
       private static final NationalGridsCanada instance = new NationalGridsCanada();
   }

   /**
    * Gets the NationalGridsCanada Projections.
    * @return 
    */
   public NationalGridsCanada getNationalGridsCanada(){
       return NationalGridsCanadaHolder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the NationalGridsIndia 
    */
   private static class NationalGridsIndiaHolder{
       private static final NationalGridsIndia instance = new NationalGridsIndia();
   }

   /**
    * Gets the NationalGridsIndia Projections.
    * @return 
    */
   public NationalGridsIndia getNationalGridsIndia(){
       return NationalGridsIndiaHolder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the NationalGridsJapan 
    */
   private static class NationalGridsJapanHolder{
       private static final NationalGridsJapan instance = new NationalGridsJapan();
   }

   /**
    * Gets the NationalGridsJapan Projections.
    * @return 
    */
   public NationalGridsJapan getNationalGridsJapan(){
       return NationalGridsJapanHolder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the NationalGridsNewZealand 
    */
   private static class NationalGridsNewZealandHolder{
       private static final NationalGridsNewZealand instance = new NationalGridsNewZealand();
   }

   /**
    * Gets the NationalGridsNewZealand Projections.
    * @return 
    */
   public NationalGridsNewZealand getNationalGridsNewZealand(){
       return NationalGridsNewZealandHolder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the NationalGridsNorway 
    */
   private static class NationalGridsNorwayHolder{
       private static final NationalGridsNorway instance = new NationalGridsNorway();
   }

   /**
    * Gets the NationalGridsNorway Projections.
    * @return 
    */
   public NationalGridsNorway getNationalGridsNorway(){
       return NationalGridsNorwayHolder.instance;
   }
   
       /**
    * Holder class for allowing lazy loading of the NationalGridsSweden 
    */
   private static class NationalGridsSwedenHolder{
       private static final NationalGridsSweden instance = new NationalGridsSweden();
   }

   /**
    * Gets the NationalGridsSweden Projections.
    * @return 
    */
   public NationalGridsSweden getNationalGridsSweden(){
       return NationalGridsSwedenHolder.instance;
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
   
       /**
    * Holder class for allowing lazy loading of the Polar 
    */
   private static class PolarHolder{
       private static final Polar instance = new Polar();
   }

   /**
    * Gets the Polar Projections.
    * @return 
    */
   public Polar getPolar(){
       return PolarHolder.instance;
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
    * Holder class for allowing lazy loading of the StatePlaneNad127 
    */
   private static class StatePlaneNad1927Holder{
       private static final StatePlaneNad1927 instance = new StatePlaneNad1927();
   }

   /**
    * Gets the StatePlaneNad127 Projections.
    * @return 
    */
   public StatePlaneNad1927 getStatePlaneNad1927(){
       return StatePlaneNad1927Holder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the StatePlaneNad1983 
    */
   private static class StatePlaneNad1983Holder{
       private static final StatePlaneNad1983 instance = new StatePlaneNad1983();
   }

   /**
    * Gets the StatePlaneNad1983 Projections.
    * @return 
    */
   public StatePlaneNad1983 getStatePlaneNad1983(){
       return StatePlaneNad1983Holder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the StatePlaneNad1983Feet 
    */
   private static class StatePlaneNad1983FeetHolder{
       private static final StatePlaneNad1983Feet instance = new StatePlaneNad1983Feet();
   }

   /**
    * Gets the StatePlaneNad1983Feet Projections.
    * @return 
    */
   public StatePlaneNad1983Feet getStatePlaneNad1983Feet(){
       return StatePlaneNad1983FeetHolder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the StatePlaneNad1983Harn 
    */
   private static class StatePlaneNad1983HarnHolder{
       private static final StatePlaneNad1983Harn instance = new StatePlaneNad1983Harn();
   }

   /**
    * Gets the StatePlaneNad1983Harn Projections.
    * @return 
    */
   public StatePlaneNad1983Harn getStatePlaneNad1983Harn(){
       return StatePlaneNad1983HarnHolder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the StatePlaneNad1983HarnFeet 
    */
   private static class StatePlaneNad1983HarnFeetHolder{
       private static final StatePlaneNad1983HarnFeet instance = new StatePlaneNad1983HarnFeet();
   }

   /**
    * Gets the StatePlaneNad1983HarnFeet Projections.
    * @return 
    */
   public StatePlaneNad1983HarnFeet getStatePlaneNad1983HarnFeet(){
       return StatePlaneNad1983HarnFeetHolder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the StatePlaneOther 
    */
   private static class StatePlaneOtherHolder{
       private static final StatePlaneOther instance = new StatePlaneOther();
   }

   /**
    * Gets the StatePlaneOther Projections.
    * @return 
    */
   public StatePlaneOther getStatePlaneOther(){
       return StatePlaneOtherHolder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the TransverseMercatorSystems 
    */
   private static class TransverseMercatorSystemsHolder{
       private static final TransverseMercatorSystems instance = new TransverseMercatorSystems();
   }

   /**
    * Gets the TransverseMercatorSystems Projections.
    * @return 
    */
   public TransverseMercatorSystems getTransverseMercatorSystems(){
       return TransverseMercatorSystemsHolder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the UtmNad1927 
    */
   private static class UtmNad1927Holder{
       private static final UtmNad1927 instance = new UtmNad1927();
   }

   /**
    * Gets the UtmNad1927 Projections.
    * @return 
    */
   public UtmNad1927 getUtmNad1927(){
       return UtmNad1927Holder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the UtmNad1983 
    */
   private static class UtmNad1983Holder{
       private static final UtmNad1983 instance = new UtmNad1983();
   }

   /**
    * Gets the UtmNad1983 Projections.
    * @return 
    */
   public UtmNad1983 getUtmNad1983(){
       return UtmNad1983Holder.instance;
   }    /**
    * Holder class for allowing lazy loading of the UtmOther 
    */
   private static class UtmOtherHolder{
       private static final UtmOther instance = new UtmOther();
   }

   /**
    * Gets the UtmOther Projections.
    * @return 
    */
   public UtmOther getUtmOther(){
       return UtmOtherHolder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the UtmWgs1972 
    */
   private static class UtmWgs1972Holder{
       private static final UtmWgs1972 instance = new UtmWgs1972();
   }

   /**
    * Gets the UtmWgs1972 Projections.
    * @return 
    */
   public UtmWgs1972 getUtmWgs1972(){
       return UtmWgs1972Holder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the UtmWgs1984 
    */
   private static class UtmWgs1984Holder{
       private static final UtmWgs1984 instance = new UtmWgs1984();
   }

   /**
    * Gets the UtmWgs1984 Projections.
    * @return 
    */
   public UtmWgs1984 getUtmWgs1984(){
       return UtmWgs1984Holder.instance;
   }
       /**
    * Holder class for allowing lazy loading of the Wisconsin 
    */
   private static class WisconsinHolder{
       private static final Wisconsin instance = new Wisconsin();
   }

   /**
    * Gets the Wisconsin Projections.
    * @return 
    */
   public Wisconsin getWisconsin(){
       return WisconsinHolder.instance;
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
    * Holder class for allowing lazy loading of the WorldSpheroid 
    */
   private static class WorldSpheroidHolder{
       private static final WorldSpheroid instance = new WorldSpheroid();
   }

   /**
    * Gets the WorldSpheroid Projections.
    * @return 
    */
   public WorldSpheroid getWorldSpheroid(){
       return WorldSpheroidHolder.instance;
   }
   
   /**
    * Holder class for allowing lazy loading of the WorldSpheroid 
    */
   private static class StateSystemsHolder{
       private static final StateSystems instance = new StateSystems();
   }

   /**
    * Gets the WorldSpheroid Projections.
    * @return 
    */
   public StateSystems getStateSystems(){
       return StateSystemsHolder.instance;
   }
   
   
   /**
    * Gets all the projected 
    * @return 
    */
   public List<CoordinateSystemCategory> getAll(){
       List<CoordinateSystemCategory> result = new ArrayList<>();
       result.add(getAfrica());
       result.add(getAsia());
       result.add(getEurope());
       result.add(getGaussKrugerBeijing1954());
       result.add(getGaussKrugerOther());
       result.add(getGaussKrugerPulkovo1942());
       result.add(getGaussKrugerPulkovo1995());
       result.add(getKrugerXian1980());
       result.add(getMinnesota());
       result.add(getNad1983IntlFeet());
       result.add(getNationalGrids());
       result.add(getNationalGridsAustralia());
       result.add(getNationalGridsCanada());
       result.add(getNationalGridsIndia());
       result.add(getNationalGridsJapan());
       result.add(getNationalGridsNewZealand());
       
       result.add(getNationalGridsNorway());
       result.add(getNationalGridsSweden());
       result.add(getNorthAmerica());
       result.add(getPolar());
       result.add(getSouthAmerica());
       result.add(getSpheroidBased());
       result.add(getStatePlaneNad1927());
       result.add(getStatePlaneNad1983());
       result.add(getStatePlaneNad1983Feet());
       result.add(getStatePlaneNad1983Harn());
       result.add(getStatePlaneNad1983HarnFeet());
       result.add(getStatePlaneOther());
       result.add(getTransverseMercatorSystems());
       result.add(getUtmNad1927());
       result.add(getUtmNad1983());
       result.add(getUtmOther());
       result.add(getUtmWgs1972());
       result.add(getUtmWgs1984());
       result.add(getWisconsin());
       result.add(getWorld());
       result.add(getWorldSpheroid());
       
       
       
       
       
       
       
       
       
       
       return result;
   }
    
}
