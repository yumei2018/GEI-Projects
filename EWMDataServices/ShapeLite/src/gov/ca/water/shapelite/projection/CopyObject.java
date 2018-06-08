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

import gov.ca.water.shapelite.NonNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class CopyObject implements Cloneable, Serializable {

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The logger for this class.
   */
  private static final Logger LOGGER
          = Logger.getLogger(CopyObject.class.getName());

  //</editor-fold>
  /**
   * Creates a new instance of the CopyObject class.
   */
  public CopyObject() {

  }

  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Copies the specific object. This should only be implemented by cloneable
   * objects.
   *
   * @return A CopyObject duplicate of this object.
   */
  public CopyObject copy() {
    try {
      return (CopyObject) clone();
    } catch (CloneNotSupportedException ex) {
      Logger.getLogger(CopyObject.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
    }
    throw new IllegalStateException("Could not clone.");
  }

  /**
   * Overrides standard cloning to invoke the copy process.
   *
   * @return
   * @throws CloneNotSupportedException
   */
  @Override
  protected final Object clone() throws CloneNotSupportedException {
    CopyObject copy = (CopyObject) super.clone();
    copy.copyFields(this);
    return copy;
  }

  /**
   * Copies items recursively to this object. This should not be null.
   *
   * @param original THe original item to copy values from.
   */
  public void copyProperties(@NonNull CopyObject original) {
    if (original == null) {
      boolean stop = true;
    }
    Field[] fields = this.getClass().getDeclaredFields();
    List<Field> otherFields = new ArrayList<>(
            Arrays.asList(original.getClass().getDeclaredFields()));
    for (Field field : fields) {
      if (Modifier.isStatic(field.getModifiers())) {
        continue; // static variables can be ignored during copy
      }
      if (Modifier.isTransient(field.getModifiers())) {
        continue; // ignore any transient properties.
      }

      boolean found = false;
      for (Field f : otherFields) {
        if (f.getName().equals(field.getName())) {
          found = true;
        }
      }
      if (!found) {
        continue;
      }

      try {
        field.setAccessible(true);
        Object obj = field.get(original);
        ShallowCopy an = field.getAnnotation(ShallowCopy.class);
        if (an != null) {

          field.set(this, obj);
          continue;
        }
        if (obj instanceof CopyObject) {
          CopyObject c = (CopyObject) obj;
          field.set(this, c.copy());
        } else {
          field.set(this, obj);
        }
      } catch (IllegalArgumentException | IllegalAccessException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
  }

  /**
   * This method copies the fields using reflection, and will implement the
   * proper cloning on all CopyObject fields that do not implement the
   * ShallowCopy annotation.
   *
   * @param original The original object to copy values from.
   */
  public void copyFields(CopyObject original) {
    onCopyFields(original);
  }

  /**
   * overrideable implementation for copying fields. This automatically forces
   * any CopyObject fields to implement their copy methods recursively unless
   * those fields have the ShallowCopy annotation.
   *
   * @param original
   */
  protected void onCopyFields(CopyObject original) {
    Field[] fields = this.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      if (Modifier.isStatic(field.getModifiers())) {
        continue; // static variables can be ignored during copy
      }
      ShallowCopy an = field.getAnnotation(ShallowCopy.class);
      if (an != null) {
        continue; // clone already created a shallow copy.
      }
      try {
        Object obj = field.get(original);
        if (obj instanceof CopyObject) {
          CopyObject c = (CopyObject) obj;
          field.set(this, c.copy());
        }
      } catch (IllegalArgumentException | IllegalAccessException ex) {
        LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
      }

    }
  }

  //</editor-fold>
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
}
