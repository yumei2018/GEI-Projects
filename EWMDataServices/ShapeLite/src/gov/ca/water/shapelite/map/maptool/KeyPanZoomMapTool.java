/*
 * The MIT License
 *
 * Copyright 2012 hdunsford.
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
package gov.ca.water.shapelite.map.maptool;

import gov.ca.water.shapelite.events.MapEventMouse;
import gov.ca.water.shapelite.events.MapEventFocus;
import gov.ca.water.shapelite.events.MapEventKey;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.map.MapPanel;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class KeyPanZoomMapTool extends MapTool {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  
  
  /**
   * A boolean that is true if the down button is being pressed
   */
  boolean down;

  /**
   * A boolean that is true if the left button is being pressed
   */
  boolean left;
  
  /*
   * The MapPanel that stores information about the map control this tool belongs to.
   */
  MapPanel map;
  
  /**
   * A timer that allows for repeat panning if a button is held down.
   */
  Timer moveTimer;
  
  /**
   * A boolean that is true if the right button is being pressed.
   */
  boolean right;
  
  /**
   * A boolean that is true if the up button is being pressed
   */
  boolean up;
  
  //</editor-fold>

  /**
   * Creates a new instance of the MapToolKeyPanZoom class.
   */
  public KeyPanZoomMapTool() {
    this.setName("keyboard");
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  
  /**
   * This ensures that the move timer is canceled when the focus is lost.
   * @param e 
   */
  @Override
  public void focusLost(MapEventFocus e) {
    if(moveTimer != null){
      moveTimer.cancel();
    }
  }

  /**
   * Occurs when a key is pressed while the map has the focus.
   * @param e The MapEventKey with information about the key or keys that are being 
   * pressed.
   */
  @Override
  public void keyPressed(MapEventKey e) {
    map = e.getMap();
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      if (up) {
        return;
      }
      if (moveTimer != null) {
        moveTimer.cancel();
      }
      up = true;
      down = false;
      left = false;
      right = false;
      moveTimer = new Timer();
      moveTimer.scheduleAtFixedRate(new PanUp(), 0, 100);
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      if (down) {
        return;
      }
      if (moveTimer != null) {
        moveTimer.cancel();
      }
      up = false;
      left = false;
      right = false;
      down = true;
      moveTimer = new Timer();
      moveTimer.scheduleAtFixedRate(new PanDown(), 0, 100);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      if (right) {
        return;
      }
      if (moveTimer != null) {
        moveTimer.cancel();
      }
      up = false;
      left = false;
      right = true;
      down = false;
      moveTimer = new Timer();
      moveTimer.scheduleAtFixedRate(new PanRight(), 0, 100);
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      if (left) {
        return;
      }
      if (moveTimer != null) {
        moveTimer.cancel();
      }
      up = false;
      left = true;
      right = false;
      down = false;
      moveTimer = new Timer();
      moveTimer.scheduleAtFixedRate(new PanLeft(), 0, 100);
    }
    // If they hit the plus button, zoom in.
    if(e.getKeyCode() == KeyEvent.VK_ADD || (e.getKeyCode() == KeyEvent.VK_EQUALS 
            && e.isShiftDown())){
      int level = e.getMap().getContent().getClosestLevel();
      level = level + 1;
      if(level > 18){
        level = 18;
      }
      e.getMap().getContent().setLevel(level);
      e.getMap().getContent().paintImmediately();
      e.getMap().repaint();
    }
    
    // If they hit the minus button, zoom out.
    if(e.getKeyCode() == KeyEvent.VK_SUBTRACT || e.getKeyCode() == KeyEvent.VK_MINUS){
      int level = e.getMap().getContent().getClosestLevel();
      level = level - 1;
      if( level < 0){
        level = 0;
      }
      e.getMap().getContent().setLevel(level);
      e.getMap().getContent().paintImmediately();
      e.getMap().repaint();
    }

  }

  /**
   * Occurs when a key is released while the map has focus.
   * @param e A MapEventKey argument that has information about the Key pressed
   * an the Map control.
   */
  @Override
  public void keyReleased(MapEventKey e) {
    if (up) {
      if (e.getKeyCode() == KeyEvent.VK_UP) {
        if (moveTimer != null) {
          moveTimer.cancel();
        }
        up = false;
      }
    }
    if (down) {
      if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        if (moveTimer != null) {
          moveTimer.cancel();
        }
        down = false;
      }
    }
    if (left) {
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        if (moveTimer != null) {
          moveTimer.cancel();
        }
        left = false;
      }
    }
    if (right) {
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        if (moveTimer != null) {
          moveTimer.cancel();
        }
        right = false;
      }
    }
    
  }

  /**
   * This ensures that the mouse gets focus when the user moves their mouse over the
   * map.  
   * @param e A MapEventMouse argument with information about the map and the 
   * mouse condition.
   */
  @Override
  public void mouseMoved(MapEventMouse e) {
    e.getMap().requestFocus();
    // the map can also get focus through the use of the tab keys, but this will
    // ensure that the map has focus once the user moves their mouse on the control.
  }

  /**
   * Pans the map to the South when the timer ticks.
   */
  private class PanDown extends TimerTask {

    @Override
    public void run() {
      Envelope env = map.getContent().getEnvelopeMercator();
      double h = env.getHeight() / 10;
      double top = env.getMax().getY();
      double bottom = env.getMin().getY();
      env.getMax().setY(top - h);
      env.getMin().setY(bottom - h);
      map.getContent().setEnvelope(env);
      map.getContent().paintImmediately();
      map.repaint();
    }
  }
  
  /**
   * Pans the map to the West when the timer ticks.
   */
  private class PanLeft extends TimerTask {

    @Override
    public void run() {
      Envelope env = map.getContent().getEnvelopeMercator();
      double w = env.getWidth() / 10;
      double right = env.getMax().getX();
      double left = env.getMin().getX();
      env.getMax().setX(right - w);
      env.getMin().setX(left - w);
      map.getContent().setEnvelope(env);
      map.getContent().paintImmediately();
      map.repaint();
    }
  }
  
  /**
   * Pans the map to the East when the timer ticks.
   */
  private class PanRight extends TimerTask {

    @Override
    public void run() {
      Envelope env = map.getContent().getEnvelopeMercator();
      double w = env.getWidth() / 10;
      double right = env.getMax().getX();
      double left = env.getMin().getX();
      env.getMax().setX(right + w);
      env.getMin().setX(left + w);
      map.getContent().setEnvelope(env);
      map.getContent().paintImmediately();
      map.repaint();
    }
  }
  
  /**
   * Pans the map to the North when the timer ticks.
   */
  private class PanUp extends TimerTask {
    @Override
    public void run() {
      Envelope env = map.getContent().getEnvelopeMercator();
      double h = env.getHeight() / 10;
      double top = env.getMax().getY();
      double bottom = env.getMin().getY();
      env.getMax().setY(top + h);
      env.getMin().setY(bottom + h);
      map.getContent().setEnvelope(env);
      map.getContent().paintImmediately();
      map.repaint();
    }
  }
  
  //</editor-fold>
}
