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
package gov.ca.water.shapelite;

/**
 * This class serves to link together a part from a shapefile with a
 * PartTreeNode from a part tree.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class PartNodeConnection {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The PartTreeNode that joins the part with the tree.
   */
  private PartTreeNode node;

  /**
   * The actual part from the shapefile.
   */
  private Part part;

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the PartTreeNode that joins the part with the tree.
   *
   * @return the node
   */
  public final PartTreeNode getNode() {
    return node;
  }

  /**
   * Sets the PartTreeNode that joins the part with the tree.
   *
   * @param node the node to set
   */
  public final void setNode(PartTreeNode node) {
    this.node = node;
  }

  /**
   * Gets the actual part from the shapefile.
   *
   * @return the part
   */
  public final Part getPart() {
    return part;
  }

  /**
   * Sets the actual part from the shapefile.
   *
   * @param part the part to set
   */
  public final void setPart(Part part) {
    this.part = part;
  }

  //</editor-fold>
}
