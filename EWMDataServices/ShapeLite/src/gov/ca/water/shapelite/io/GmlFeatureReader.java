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

import gov.ca.water.shapelite.Feature;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class GmlFeatureReader implements Iterable<Feature> {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  final GmlParser parser;
  Exception lastException;
  InputStream stream;
  Thread backgroundThread;
  Feature currentFeature;
  Feature nextFeature;

  //</editor-fold>
  /**
   * Creates a new instance of the GmlFeatureReader class.
   */
  public GmlFeatureReader() {
    parser = new GmlParser(new FeatureHandler() {
      
      @Override
      public void featureCreated(Feature f) {
        setNext(f);
      }
    });
  }

  /**
   * This method defines the stream and opens the reader on a background thread.
   *
   * @param gmlData
   */
  public void open(InputStream gmlData) {
    stream = gmlData;
    if (stream == null) {
      return;
    }
    backgroundThread = new Thread(new Runnable() {
      
      @Override
      public void run() {
        openBackground();
      }
    });
    backgroundThread.start();
  }
  
  private void openBackground() {
    try {
      SAXParserFactory spf = SAXParserFactory.newInstance();
      SAXParser saxParser = spf.newSAXParser();
      saxParser.parse(stream, parser);
      
    } catch (ParserConfigurationException | SAXException | IOException ex) {
      lastException = ex;
      Logger.getLogger(GmlFeatureReader.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
    }
  }
  
  public synchronized void setNext(Feature f) {
    nextFeature = f;
    this.notify();
    parser.setSuspended(true);
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
  @Override
  public Iterator<Feature> iterator() {
    return new FeatureIterator();
  }
  
  public class FeatureIterator implements Iterator<Feature> {
    
    boolean started = false;
    
    FeatureIterator() {
      
    }
    
    @Override
    public boolean hasNext() {
      if (parser.isEndOfDocument()) {
        return false;
      }
      if (nextFeature == null) {
        // we need to wait
        synchronized (this) {
          try {
            this.wait();
          } catch (InterruptedException ex) {
            Logger.getLogger(FeatureIterator.class.getName()).log(
                    Level.SEVERE, ex.getMessage(), ex);
          }
        }
      }
      return nextFeature != null || !started;
    }
    
    @Override
    public Feature next() {
      if (nextFeature == null) {
        try {
          // we need to wait
          synchronized (this) {
            this.wait();
          }
        } catch (InterruptedException ex) {
          Logger.getLogger(FeatureIterator.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);
        }
      }
      currentFeature = nextFeature;
      nextFeature = null;
      synchronized (parser) {
        parser.setSuspended(false);
        parser.notify();
      }
      
      return currentFeature;
    }
    
    @Override
    public void remove() {
    }
    
  }
  
}
