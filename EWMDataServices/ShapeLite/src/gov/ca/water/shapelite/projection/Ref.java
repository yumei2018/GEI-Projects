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
public class Ref<T> {

    //<editor-fold defaultstate="collapsed" desc="Fields">

    private T value;


    //</editor-fold>

    /**
     * Creates a new instance of the Ref class.
     */
    public Ref(){
  
    }
    public Ref(T value){
        this.value = value;
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

    /**
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

}
