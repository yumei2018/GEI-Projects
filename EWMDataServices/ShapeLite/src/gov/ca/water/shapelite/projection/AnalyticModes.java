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

import java.util.EnumSet;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public enum AnalyticModes {

    //<editor-fold defaultstate="collapsed" desc="Fields">

    AnalX1Y1(1),
    AnalXpYp(2),
    AnalHk(4),
    AnalConv(8);

    //</editor-fold>

    private final int index;
    
    /**
     * Creates a new instance of the AnalyticModes class.
     */
    private AnalyticModes(int value){
        index = value;
    }
    
    /**
     * Gets the actual index
     * @return 
     */
    public int getIndex(){
        return index;
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
    public static EnumSet<AnalyticModes> getAnalyticModes(int index){
        EnumSet<AnalyticModes> modes = EnumSet.noneOf(AnalyticModes.class);
        for(AnalyticModes mode : AnalyticModes.values())
        {
                int flagValue = mode.index;
                if(( flagValue & index) == flagValue){
                    modes.add(mode);
                }
        }
        
        return modes;
    }
    
    /**
     * Gets the numeric representation from all the entries in the enum set.
     * @param modes
     * @return 
     */
    public static int getValue(EnumSet<AnalyticModes> modes){
        int value = 0;
        for(AnalyticModes mode : AnalyticModes.values()){
            value|=mode.getIndex();
        }
        return value;
    }
    


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
