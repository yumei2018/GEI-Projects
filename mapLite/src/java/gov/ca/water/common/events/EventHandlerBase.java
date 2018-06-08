package gov.ca.water.common.events;

import gov.ca.water.common.io.DataEntry;
import gov.ca.water.common.reflection.ReflectionInfo;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class acts as delegate for any class that sends events. Through
 * the delegate event listeners can add (and remove) themselves from the listener's
 * queue.  A listener can be added multiple times for registering the listener for
 * multiple events.  When the delegate owner fires the event, the listener's onEvent
 * method will only be called if the listener has been registered for the event.
 * @author kprins
 */
public abstract class EventHandlerBase<TInfo extends EventInfo> implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Static Method/Fields">
  /**
   * Protected Static Logger object for logging errors, warnings, and info messages.
   */
  protected static final Logger logger = 
          Logger.getLogger(EventHandlerBase.class.getName());
  
  /**
   * Called to get the EventInfo class used by the EventHandlerDelegate
   * @return the generically referenced class
   */
  @SuppressWarnings("unchecked")
  public static <TEvent extends EventInfo> Class<TEvent>
          getEventInfoClass(Class<EventHandlerBase<TEvent>> handlerClass) {
    Class<TEvent> result = null;
    result = (Class<TEvent>)
         ReflectionInfo.getGenericClass(EventHandlerBase.class, handlerClass, 0);
    if (result == null) {
      result = (Class<TEvent>) EventInfo.class;
    }
    return result;
  }
      
  /**
   * Called to get the listener's PUBLIC Event Method (i.e., a method with parameter types
   * {Object, eventInfoClass} - e.g., onChildChange(Object Sender, EventInfo eventInfo))
   * @param listener this listener
   * @param methodName the event method name
   * @param eventInfoClass the EventHandler's EventInfo class
   * @return the method.
   * @exception IllegalArgumentException if any of the input parameters are unassigned or
   * if the method is not supported be the listener (i.e., the method and it parameter
   * types must be correctly defined).
   */
  @SuppressWarnings({"unchecked","rawtypes"})
  public static Method getEventMethod(Object listener, String methodName,
          Class<? extends EventInfo> eventInfoClass) {
    Method result = null;
    try {
      if (listener == null) {
        throw new Exception("The Listener instance reference is unassigned.");
      }
      methodName = DataEntry.cleanString(methodName);
      if (methodName == null) {
        throw new Exception("The Event Method's name is undefined.");
      }
      
      if (eventInfoClass == null) {
        throw new Exception("The eventhandler's EventInfo class is undefined.");
      }
      
      Class[] parTypes = new Class[]{Object.class, eventInfoClass};
      Class declaringClass = listener.getClass();
      while ((declaringClass != null) && (!Object.class.equals(declaringClass))) {
        try {
          result = declaringClass.getMethod(methodName, parTypes);
        } catch (Exception inExp) {
          result = null;
        }
        if (result != null) {
          break;
        }
        declaringClass = declaringClass.getSuperclass();
      }
    } catch (Exception exp) {
      logger.log(Level.WARNING, "ReflectionInfo.getEventMethod Error:\n {0}",
              exp.getMessage());
      throw new IllegalArgumentException(exp.getMessage());
    }
    return result;
  }  
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Private Class ListenerInfo">
  /**
   * A private class for storing the Listener Info
   */
  private class ListenerInfo implements Serializable {
    public final Object listener;
    public final Method method;
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Public Constructor
     */
    public ListenerInfo(Object listener, Method method) {
      this.listener = listener;
      this.method = method;
    }
    
    // </editor-fold>
    /**
     * {@inheritDoc}
     * <p>OVERRIDE: Return true if obj != null and ((obj = this.listener) or
     * ((obj instanceof ListenerInfo) and (obj.listener = this.listner)))</p>
     */
    @Override
    @SuppressWarnings({"unchecked","rawtypes"})
    public boolean equals(Object obj) {
      boolean result = (obj != null);
      if (result) {
        if (obj instanceof EventHandlerBase.ListenerInfo) {
          EventHandlerBase.ListenerInfo other =
                  (EventHandlerBase.ListenerInfo) obj;
          result = (this.listener == other.listener);
        } else {
          result = (this.listener == obj);
        }
      }
      return result;
    }
    
    /**
     * {@inheritDoc}
     * <p>OVERRIDE: Return a HashCode on this.listener</p>
     */
    @Override
    public int hashCode() {
      int hash = 7;
      hash = 97 * hash + Objects.hashCode(this.listener);
      return hash;
    }
    
    /**
     * {@inheritDoc}
     * <p>OVERRIDE: Return {this.listener.class.simpleName}.{this.method.name}</p>
     */
    @Override
    public String toString() {
      return this.listener.getClass().getSimpleName() + "."
              + this.method.getName();
    }
  }
  //</editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Private Fields">
  /**
   * A List for maintaining the delegates ListenerInfo
   */
  private List<ListenerInfo> listeners;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Constructore/Destructor">
  /**
   * Public constructor with a reference to the EventSende owner.
   * @param pOwner
   */
  protected EventHandlerBase() {
    this.listeners = null;
  }

  /**
   * OVERRIDE: dispose local resources when disposing
   * @throws Throwable
   */
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    if (this.listeners != null) {
      this.listeners.clear();
      this.listeners = null;
    }
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Public Methods">
  /**
   * FINAL: Get the Entity's Class
   * @return Class<V>
   */
  @SuppressWarnings({"unchecked","rawtypes"})
  public final Class<TInfo> getEventInfoClass() {
    Class thisClasss = this.getClass();
    Class<TInfo> result = EventHandlerBase.getEventInfoClass(thisClasss);
    result = 
           ReflectionInfo.getGenericClass(EventHandlerBase.class, thisClasss, 0);
    if (result == null) {
      result = (Class<TInfo>) EventInfo.class;
    }
    return result;
  }
  
  /**
   * Add an new event listener with a specified Event Method - referenced by name. This
   * method will call {@linkplain ReflectionInfo#getEventMethod(java.lang.Object, 
   * java.lang.String, java.lang.Class) ReflectionInfo.getEventMethod} to retrieve the
   * method and then call {@linkplain #add(java.lang.Object, java.lang.reflect.Method) 
   * Overload 2} to handle the process.
   * @param listener the event listener to add
   * @param methodName the name of the listener-method that should be called to handle 
   * the event.
   */
  public void add(Object listener, String methodName) {
    try {
      Class<TInfo> infoClass = this.getEventInfoClass();
      Method eventMethod = 
                        EventHandlerBase.getEventMethod(listener, methodName, infoClass);
      if (eventMethod == null) {
        throw new Exception("The Event Method[" + methodName + "] is invalid.");
      }
      
      this.add(listener, eventMethod);
    } catch (Exception exp) {
      throw new IllegalArgumentException(this.getClass().getSimpleName()
              + ".add Error:\n " + exp.getMessage());
    }
  }
  
  /**
   * Add an new event listener with a specified Event Method. It will verify the listener
   * has not already be added and that the Method is assigned and required a parameter
   * of type {@linkplain #getEventInfoClass() this.eventClass}.
   * @param listener the event listener to add
   * @param eventMethod the listener-method that should be called to handle 
   * the event.
   */
  public void add(Object listener, Method eventMethod) {
    try {
      if (listener == null) {
        throw new Exception("The Event Listener reference is unassigned.");
      }
      
      if (eventMethod == null) {
        throw new Exception("The Event Method reference is unassigned.");
      }
      
      if (!eventMethod.getDeclaringClass().isAssignableFrom(listener.getClass())) {
        throw new Exception("Method[" + eventMethod.getName() + "] is not supported by "
                + "Class[" + listener.getClass().getSimpleName() + "].");
      }
      Class[] parTypes = eventMethod.getParameterTypes();
      Class<TInfo> infoClass = this.getEventInfoClass();
      if ((parTypes == null) || (parTypes.length != 2) || 
              (!Object.class.isAssignableFrom(parTypes[0])) || 
              (!infoClass.equals(parTypes[1]))) {
         throw new Exception("Method[" + eventMethod.getName() + "] does not support "
                + "ParameterTypes[Object," + infoClass.getSimpleName() + "].");
      }
      
      ListenerInfo info = new ListenerInfo(listener, eventMethod);
      if (this.listeners == null) {
        this.listeners = new ArrayList<>();
        this.listeners.add(info);
      } else if (!this.listeners.contains(info)) {
        this.listeners.add(info);
      }
    } catch (Exception exp) {
      throw new IllegalArgumentException(this.getClass().getSimpleName()
              + ".add Error:\n " + exp.getMessage());
    }
  }

  /**
   * Remove the Listener for the EventHandler's EventListener's list. Ignored if the
   * listener is undefined.
   * @param listener the event listener to remove
   */
  public void remove(Object listener) {
    if ((listener != null) && (this.listeners != null) && (!this.listeners.isEmpty())) {
      for (ListenerInfo info : listeners) {
        if (info.equals(listener)) {
          this.listeners.remove(info);
          break;
        }
      }
    }
  }
  
  /**
   * Called to clear the eventHandler - call its finalize method/
   */
  public void clear() {
    try {
      this.finalize();
    } catch(Throwable e) {}
  }

  /**
   * <p>Called by the owner of the event Handler to fire the event (eEvent) notifying all
   * the event listeners by calling the listener's assigned event method and passing the
   * assign eventInfo to the listener. It will call the listeners in the order they were
   * added to to the list and terminate the process if the eventInfo return handled (i.e.,
   * its isHandled state is set).</p>
   * <p><b>NOTE:</b> The process is skipped id the sender=null </p>
   * NOTE: ignored if pSender or not the Delegates owner or e = null.
   * @param pSender IEventSender
   * @param eventInfo the EventInfo to pass to the listener
   */
  public void fireEvent(Object sender, TInfo eventInfo) {
    if ((sender == null) || (eventInfo == null)
            || (this.listeners == null) || (this.listeners.isEmpty())) {
      return;
    }

    Object[] args = new Object[]{sender, eventInfo};
    for (ListenerInfo info : this.listeners) {
      try {
        info.method.invoke(info.listener, args);
      } catch (Exception exp) {
        logger.log(Level.WARNING, "{0}.fireEvent Error:\n {1}",
                new Object[]{this.getClass().getSimpleName(), exp.getMessage()});
      }
      
      if (eventInfo.isHandled()) {
        break;
      }
    }
  }
  // </editor-fold>
}
