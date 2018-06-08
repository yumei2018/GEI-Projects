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
package gov.ca.water.shapelite.map;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The Legend control is a representation of the layers, allowing the layers to 
 * be shown in a sequence.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class Legend extends JPanel{
   private MapContent content;

   /**
    * This method controls the drawing of the legend control.  Each layer is drawn
    * as a line.  This may no longer be in use, as it does not seem to use the 
    * LegendContent or support point graphics or symbolizers.
    * @param g The Graphics object.
    */
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    for(int i = 0; i < content.getLayers().size(); i++){
      String text = content.getLayers().get(i).getName();
      g.setColor(Color.WHITE);
      int x = 10;
      int y = 20*i;
      g.fillRect(x, y + 4, 12, 12);
      g.draw3DRect(x, y + 4, 12, 12, false);
      g.setColor(Color.BLACK);
      if(content.getLayers().get(i).isVisible()){
        g.drawLine(x, y+8, x + 5, y+16);
        g.drawLine(x + 5, y+16, x + 16, y+4);
      }
      g.drawString(text, 30, i*20);
    }
  }
  
  
   
   
   
}
