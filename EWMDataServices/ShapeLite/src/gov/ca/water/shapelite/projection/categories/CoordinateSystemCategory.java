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
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CoordinateSystemCategory {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  private final List<String> names;

  private String categoryName;

  //</editor-fold>
  /**
   * Creates a new instance of the CoordinateSystemCategory class.
   */
  public CoordinateSystemCategory() {
    Field[] fields = this.getClass().getDeclaredFields();
    names = new ArrayList<>();
    for (Field field : fields) {
      if ("names".equals(field.getName())) {
        continue;
      }
      if (Modifier.isPrivate(field.getModifiers())) {
        continue; // static variables can be ignored during copy
      }
      if (Modifier.isProtected(field.getModifiers())) {
        continue; // static variables can be ignored during copy
      }
      names.add(field.getName());
    }
  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  ProjectionInfo getProjectionInfo(String name) {

    Field[] fields = this.getClass().getDeclaredFields();
    for (Field field : fields) {
      if ("names".equals(field.getName())) {
        continue;
      }
      if (Modifier.isPrivate(field.getModifiers())) {
        continue; // static variables can be ignored during copy
      }
      if (Modifier.isProtected(field.getModifiers())) {
        continue; // static variables can be ignored during copy
      }
      if (field.getName().equals(name)) {
        Object obj = null;
        try {
          obj = field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
          Logger.getLogger(CoordinateSystemCategory.class.getName()).log(
                  Level.SEVERE, ex.getMessage(), ex);
        }
        if (obj instanceof ProjectionInfo) {
          return (ProjectionInfo) obj;
        }
      }
    }
    return null;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  //</editor-fold>
  /**
   * @return the names
   */
  public List<String> getNames() {
    return names;
  }

  /**
   * Gets the list containing copies of the master projections.
   *
   * @return
   */
  public List<ProjectionInfo> getAll() {
    List<ProjectionInfo> result = new ArrayList<>();
    Field[] fields = this.getClass().getDeclaredFields();
    for (Field field : fields) {
      if ("names".equals(field.getName())) {
        continue;
      }
      if (Modifier.isPrivate(field.getModifiers())) {
        continue; // static variables can be ignored during copy
      }
      if (Modifier.isProtected(field.getModifiers())) {
        continue; // static variables can be ignored during copy
      }
      Object obj = null;
      try {
        obj = field.get(this);
      } catch (IllegalArgumentException | IllegalAccessException ex) {
        Logger.getLogger(CoordinateSystemCategory.class.getName()).log(
                Level.SEVERE, ex.getMessage(), ex);
      }
      if (obj instanceof ProjectionInfo) {
        ProjectionInfo proj = (ProjectionInfo) obj;
        result.add(proj.copy());
      }
    }
    return result;
  }

  /**
   * By default, this is named for the class name. It can be overridden by
   * setting the categoryName.
   *
   * @return the categoryName
   */
  public String getCategoryName() {
    if (categoryName == null) {
      return this.getClass().getSimpleName();
    }
    return categoryName;
  }

  /**
   * @param categoryName the categoryName to set
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

}
