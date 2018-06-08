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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ObservedList<T> implements List<T> {
  private List<T> layers;
  private EventListenerList listenerList;
  
  public ObservedList(){
    layers = new ArrayList<T>();
    listenerList = new EventListenerList();
  }
  
  /**
   * Adds the listener to this MapContent
   *
   * @param listener
   */
  public void addListener(ObservedListListener listener) {
    getListenerList().add(ObservedListListener.class, listener);
  }

  /**
   * Removes the listener from this object.
   *
   * @param listener
   */
  public void removeListener(ObservedListListener listener) {
    getListenerList().remove(ObservedListListener.class, listener);
  }
  
  
  void fireListChanged() {
    ObservedListEvent<T> evt = new ObservedListEvent<T>(this);
    ObservedListListener[] listeners = getListenerList().getListeners(ObservedListListener.class);
    for (ObservedListListener listener : listeners) {
      listener.listChanged(evt);
    }
  }

  @Override
  public int size() {
    return layers.size();
  }

  @Override
  public boolean isEmpty() {
    return layers.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return layers.contains(o);
  }

  @Override
  public Iterator<T> iterator() {
    return layers.iterator();
  }

  @Override
  public Object[] toArray() {
    return layers.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return layers.toArray(a);
  }

  @Override
  public boolean add(T e) {
    boolean result = layers.add(e);
    if(result){
      fireListChanged();
    }
    return result;
  }

  @Override
  public boolean remove(Object o) {
    boolean result = layers.remove(o);
    if(result){
      fireListChanged();
    }
    return result;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return layers.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    boolean result = layers.addAll(c);
    if(result){
      this.fireListChanged();
    }
    return result;
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    boolean result = layers.addAll(index, c);
    if(result){
      this.fireListChanged();
    }
    return result;
    
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    boolean result = layers.removeAll(c);
    if(result){
      this.fireListChanged();
    }
    return result;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    boolean result = layers.retainAll(c);
    if(result){
      this.fireListChanged();
    }
    return result;
  }
  
  /**
   * Move the <tt>item</tt> from its current location to specified index in the list.
   * Ignored is <tt>toIndex</tt> is out of Bounds[0..this.size], <tt>toIndex</tt> is the
   * item;s current index, or removing the current object form the list failed.
   * @param item the item to move
   * @param toIndex the new index to move to.
   * @return true if the item was moved.
   */
  public boolean move(T item, int toIndex) {
    boolean result = false;
    if ((toIndex >= 0) && (toIndex < this.layers.size())) {
      int itemIdx = this.layers.indexOf(item);
      if ((itemIdx >= 0) && (itemIdx != toIndex)) {
        T removeObj = this.layers.remove(itemIdx);
        if (removeObj != null) {
          this.layers.add(toIndex, removeObj);
          result = true;
        }
      }
    }
    if(result){
      this.fireListChanged();
    }
    return result;
  }

  @Override
  public void clear() {
    layers.clear();
  }

  @Override
  public T get(int index) {
    return layers.get(index);
  }

  @Override
  public T set(int index, T element) {
    T result = layers.set(index, element);
    this.fireListChanged();
    return result;
  }

  @Override
  public void add(int index, T element) {
    layers.add(index, element);
    this.fireListChanged();
  }

  @Override
  public T remove(int index) {
    // To Do: Observer
    T result = layers.remove(index);
    if(result != null){
      this.fireListChanged();
    }
    return result;
  }

  @Override
  public int indexOf(Object o) {
    return layers.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return layers.lastIndexOf(o);
  }

  @Override
  public ListIterator<T> listIterator() {
    return layers.listIterator();
  }

  @Override
  public ListIterator<T> listIterator(int index) {
   return layers.listIterator(index);
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    return layers.subList(fromIndex, toIndex);
  }

  /**
   * @return the listenerList
   */
  public EventListenerList getListenerList() {
    return listenerList;
  }
}
