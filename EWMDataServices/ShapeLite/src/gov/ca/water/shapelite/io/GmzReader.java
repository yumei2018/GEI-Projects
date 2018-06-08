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

package gov.ca.water.shapelite.io;

import gov.ca.water.shapelite.FeatureSet;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * A gmz is a set of geographic markup language files that have been zipped.  This
 * is different from the KML that google earth uses, and is more ogc.  This tool was
 * originally designed to read the gmz files inculded in HEC RAS models.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GmzReader {

    //<editor-fold defaultstate="collapsed" desc="Fields">

   


    //</editor-fold>

    /**
     * Creates a new instance of the GmlReader class.
     */
    public GmzReader(){
  
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
  
    /**
     * The file should be a gmz file.  If it is a gmz file, it is possible for
     * multiple featuresets to be generated.
     * @param gmzFile 
     */
    public void open(String gmzFile) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException{
        
        ZipFile zipFile = new ZipFile(gmzFile);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        List<FeatureSet> featureSets = new ArrayList<>();
        while(entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            InputStream stream = zipFile.getInputStream(entry);
            if(entry.getName().endsWith(".gml")){
                List<FeatureSet> features = GmlReader.readAll(stream);
            }
        }
         
    }
    
    
    
    


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
  


    //</editor-fold>

}
