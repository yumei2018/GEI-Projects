/*
 * The MIT License
 *
 * Copyright 2015 hdunsford.
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
package gov.ca.water.shapelite.toolbox;

import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.dialog.MessageHandler;
import gov.ca.water.shapelite.dialog.MessageHandlerConsole;
import gov.ca.water.shapelite.events.ToolFinishedEvent;
import gov.ca.water.shapelite.progress.Cancellable;
import gov.ca.water.shapelite.progress.ProgressCountableCancellable;
import gov.ca.water.shapelite.progress.ProgressCountableCancellableConsole;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public abstract class ToolBase implements Tool, Runnable {

  /**
   * Milliseconds per hour.
   */
  private static final int MS_PER_HOUR = 3600000;

  /**
   * Milliseconds per minute.
   */
  private static final int MS_PER_MINUTE = 60000;

  /**
   * Milliseconds per second.
   */
  private static final int MS_PER_SECOND = 1000;

  /**
   * The String name of this tool to distinguish it from other tools.
   */
  private String name;

  /**
   * The String description that describes this tool.
   */
  private String description;

  /**
   * The help text to display for this tool.
   */
  private String helpText;

  /**
   * An icon representation to use for this tool in the toolbox control. By
   * default this will be the hammer.
   */
  private BufferedImage icon;

  /**
   * The list of parameters that control the functioning of this tool.
   */
  private List<Parameter> parameters;

  /**
   * The String category.
   */
  private String category;

  /**
   * The String filename for the help file image.
   */
  private String helpImageFilename;

  /**
   * The actual buffered image to use for the help panel.
   */
  private BufferedImage helpImage;

  /**
   * A default Cancellable interface that simply changes the cancelled property.
   */
  private Cancellable cancellable;

  /**
   * A Progress implementation.
   */
  private ProgressCountableCancellable progress;

  /**
   * Boolean, true if the default Cancellable has been called.
   */
  private boolean cancelled;

  /**
   * The Date and time that the tool began.
   */
  private Date startTime;

  /**
   * A boolean that indicates whether or not the tool will show a message dialog
   * after finishing. This defaults to true.
   */
  private boolean finishDialogVisible;

  /**
   * A boolean that, if false, means the tool should not be run.
   */
  private boolean enabled;

  /**
   * A boolean that, if true, means that the tool should skip through output
   * files until it finds the latest unfinished output file.
   */
  private boolean resumable;

  /**
   * The Message handler for showing alert messages. By default this writes the
   * message to the console, but can be overridden with IDE specific approaches.
   */
  private MessageHandler messageHandler;

  /**
   * Creates an instance of the default values.
   */
  protected ToolBase() {
    name = "Tool";
    description = "A Tool";
    helpText = "A Tool for the toolbox";
    parameters = new ArrayList<>();
    category = "Analysis";
    cancellable = new Cancellable() {
      @Override
      public boolean cancel() {
        cancelled = true;
        return true;
      }
    };
    finishDialogVisible = true;
    enabled = true;
    resumable = true;
    progress = new ProgressCountableCancellableConsole();
    messageHandler = new MessageHandlerConsole();
  }

  //<editor-fold defaultstate="collapsed" desc="ToolFinishedEvent">
  /**
   * The listener list for the finished event.
   */
  private final List<ToolFinishedEvent.Listener> toolFinishedListeners = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The ToolFinishedEvent.Listener to connect.
   */
  @Override
  public final void addToolFinishedListener(ToolFinishedEvent.Listener listener) {
    if (!toolFinishedListeners.contains(listener)) {
      toolFinishedListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The ToolFinishedEvent.Listener to disconnect.
   */
  @Override
  public final void removeToolFinishedListener(ToolFinishedEvent.Listener listener) {
    toolFinishedListeners.remove(listener);
  }

  /**
   * Fires the ToolFinished event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  private void fireToolFinished(ToolFinishedEvent e) {
    for (ToolFinishedEvent.Listener listener : toolFinishedListeners) {
      listener.toolFinished(e);
    }
  }

  //</editor-fold>
  /**
   * The toString method is used to show this tool in the tree.
   *
   * @return The String name for this tool.
   */
  @Override
  public final String toString() {
    return this.name;
  }

  /**
   * Loads any image resources as a separate method.
   */
  @Override
  public final void loadImages() {
    InputStream stream = this.getClass().getResourceAsStream(
        "/gov/ca/water/shapelite/toolbox/resources/Hammer.png");
    if (stream != null) {
      try {
        this.icon = ImageIO.read(stream);
      } catch (IOException ex) {
        Logger.getLogger(ToolBase.class.getName()).log(
            Level.SEVERE, ex.getMessage(), ex);
      }
    }
    if (this.helpImageFilename != null) {
      stream = this.getClass().getResourceAsStream(this.helpImageFilename);
      if (stream != null) {
        try {
          this.helpImage = ImageIO.read(stream);
        } catch (IOException ex) {
          Logger.getLogger(ToolBase.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
        }
      }
    }
    for (Parameter param : this.getParameters()) {
      param.loadImages();
    }
  }

  /**
   * Gets the String name of this tool to distinguish it from other tools.
   *
   * @return the name
   */
  @Override
  public final String getName() {
    return name;
  }

  /**
   * Gets The String description that describes this tool.
   *
   * @return the description
   */
  @Override
  public final String getDescription() {
    return description;
  }

  /**
   * Gets the help text to display for this tool.
   *
   * @return the helpText
   */
  @Override
  public final String getHelpText() {
    return helpText;
  }

  /**
   * Gets an icon representation to use for this tool in the toolbox control. By
   * default this will be the hammer.
   *
   * @return the getIcon
   */
  @Override
  public final BufferedImage getIcon() {
    return icon;
  }

  /**
   * Gets the list of parameters that control the functioning of this tool.
   *
   * @return the parameters
   */
  @Override
  public final List<Parameter> getParameters() {
    return parameters;
  }

  /**
   * @return the category
   */
  @Override
  public final String getCategory() {
    return category;
  }

  /**
   * Gets the String filename for the help file image.
   *
   * @return the helpImage
   */
  @Override
  public final BufferedImage getHelpImage() {
    return helpImage;
  }

  /**
   * Sets the String name of this tool to distinguish it from other tools.
   *
   * @param name the name to set
   */
  protected final void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the String description that describes this tool.
   *
   * @param description the description to set
   */
  protected final void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the String help text to show in the help panel for the entire tool.
   *
   * @param helpText the helpText to set
   */
  protected final void setHelpText(String helpText) {
    this.helpText = helpText;
  }

  /**
   * Sets the image used for this tools icon in the toolbox.
   *
   * @param icon the getIcon to set
   */
  protected final void setIcon(BufferedImage icon) {
    this.icon = icon;
  }

  /**
   * Sets the list of parameters that control the functioning of this tool.
   *
   * @param parameters the parameters to set
   */
  protected final void setParameters(List<Parameter> parameters) {
    this.parameters = parameters;
  }

  /**
   * @param category the category to set
   */
  protected final void setCategory(String category) {
    this.category = category;
  }

  /**
   * Sets the String filename for the help file image.
   *
   * @param helpImage the helpImage to set
   */
  protected final void setHelpImage(BufferedImage helpImage) {
    this.helpImage = helpImage;
  }

  /**
   * Gets the String filename for the help image.
   *
   * @return the helpImageFilename
   */
  public final String getHelpImageFilename() {
    return helpImageFilename;
  }

  /**
   * Sets the string filename for the help image.
   *
   * @param helpImageFilename the helpImageFilename to set
   */
  public final void setHelpImageFilename(String helpImageFilename) {
    this.helpImageFilename = helpImageFilename;
  }

  /**
   * Runs the process on a background thread.
   */
  @Override
  public void run() {
    setup();
    new Thread(new Runnable() {

      @Override
      public void run() {
        doRun();

      }
    }).start();
  }

  /**
   * Override this for any setup that must happen on the main thread, before the
   * actual background thread starts.
   */
  public void setup() {
    startTime = new Date();
    System.out.println("Starting " + this.getName()
        + " at " + startTime.toString());
  }

  /**
   * Finish any content that should involve the gui.
   */
  protected void finish() {
    Date end = new Date();
    System.out.println("Finished " + this.getName() + " at " + end.toString());
    if (startTime != null) {
      long tms = end.getTime() - startTime.getTime();
      String elapsed = "";
      if (tms > MS_PER_HOUR) {
        elapsed += tms / MS_PER_HOUR + " hours, ";
        tms = tms % MS_PER_HOUR;
      }
      if (tms > MS_PER_MINUTE) {
        elapsed += tms / MS_PER_MINUTE + " minutes, ";
        tms = tms % MS_PER_MINUTE;
      }
      if (tms > MS_PER_SECOND) {
        elapsed += tms / MS_PER_SECOND + " seconds, ";
        tms = tms % MS_PER_SECOND;
      }
      elapsed += tms + " milliseconds";

      System.out.println("Elapsed time: " + elapsed);
    }
    if (finishDialogVisible) {
      if (getMessageHandler() != null) {
        getMessageHandler().message("Process Finished", "Finished "
            + this.getName() + " " + end.toString() + ".");
      }
    }
    fireToolFinished(new ToolFinishedEvent(this, this));

  }

  /**
   * Private method to start the process.
   */
  private void doRun() {
    cancelled = false;
    runImmediately();
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        finish();
      }
    });
  }

  /**
   * This method should be overridden in child classes.
   */
  public abstract void runImmediately();

  /**
   * Gets a default Cancellable interface that simply changes the cancelled
 property.
   *
   * @return the cancellable
   */
  public final Cancellable getCancellable() {
    return cancellable;
  }

  /**
   * Sets the Cancellable interface to use for progress.
   *
   * @param cancellable the cancellable to set
   */
  public final void setCancellable(Cancellable cancellable) {
    this.cancellable = cancellable;
  }

  /**
   * Boolean true if the default Cancellable has been called.
   *
   * @return the cancelled
   */
  public final boolean isCancelled() {
    return cancelled;
  }

  /**
   * @param canceled the cancelled to set
   */
  public final void setCancelled(boolean canceled) {
    this.cancelled = canceled;
  }

  @Override
  public void panelOpened() {

  }

  /**
   * @return the startTime
   */
  public final Date getStartTime() {
    return startTime;
  }

  /**
   * Gets a boolean that indicates whether or not the tool will show a message
   * dialog after finishing. This defaults to true.
   *
   * @return the finishDialogVisible
   */
  public final boolean isFinishDialogVisible() {
    return finishDialogVisible;
  }

  /**
   * Sets a boolean that indicates whether or not the tool will show a message
   * dialog after finishing. This defaults to true.
   *
   * @param finishDialogVisible the finishDialogVisible to set
   */
  public final void setFinishDialogVisible(boolean finishDialogVisible) {
    this.finishDialogVisible = finishDialogVisible;
  }

  /**
   * Gets a boolean that if false, means the tool should not be run.
   *
   * @return the enabled
   */
  @Override
  public final boolean isEnabled() {
    return enabled;
  }

  /**
   * Sets a boolean that if false, means the tool should not be run.
   *
   * @param enabled the enabled to set
   */
  @Override
  public final void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * Gets boolean that, if true, means that the tool should skip through output
   * files until it finds the latest unfinished output file.
   *
   * @return the resumable
   */
  @Override
  public final boolean isResumable() {
    return resumable;
  }

  /**
   * Sets boolean that, if true, means that the tool should skip through output
   * files until it finds the latest unfinished output file.
   *
   * @param resumable the resumable to set
   */
  @Override
  public final void setResumable(boolean resumable) {
    this.resumable = resumable;
  }

  /**
   * Gets a Progress implementation.
   *
   * @return the progress
   */
  @Override
  public final ProgressCountableCancellable getProgress() {
    return progress;
  }

  /**
   * Sets a Progress implementation.
   *
   * @param progress the progress to set
   */
  @Override
  public final void setProgress(@NonNull ProgressCountableCancellable progress) {
    if (progress == null) {
      throw new IllegalArgumentException("Parameter progress cannot be null.");
    }
    this.progress = progress;
  }

  /**
   * Gets the Message handler for showing alert messages. By default this writes
   * the message to the console, but can be overridden with IDE specific
   * approaches.
   *
   * @return the messageHandler
   */
  public final MessageHandler getMessageHandler() {
    return messageHandler;
  }

  /**
   * Sets the Message handler for showing alert messages. By default this writes
   * the message to the console, but can be overridden with IDE specific
   * approaches.
   *
   * @param messageHandler the messageHandler to set
   */
  public final void setMessageHandler(@NonNull MessageHandler messageHandler) {
    if (messageHandler == null) {
      throw new IllegalArgumentException("Argument messageHandler cannot "
          + "be null.");
    }
    this.messageHandler = messageHandler;
  }

}
