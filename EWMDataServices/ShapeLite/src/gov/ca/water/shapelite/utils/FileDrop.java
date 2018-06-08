package gov.ca.water.shapelite.utils;

import gov.ca.water.shapelite.events.FileDropEvent;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
import java.io.BufferedReader;
import java.io.File;
import java.awt.Component;
import java.awt.datatransfer.Transferable;
import static java.awt.dnd.DnDConstants.ACTION_COPY;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.HierarchyListener;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.border.Border;

/**
 * This class makes it easy to drag and drop files from the operating system to
 * a Java program. Any <tt>java.awt.Component</tt> can be dropped onto, but only
 * <tt>javax.swing.JComponent</tt>s will indicate the drop event with a changed
 * border.
 * <p>
 * To use this class, construct a new <tt>FileDropUtils</tt> by passing it the
 * target component and a <tt>Listener</tt> to receive notification when file(s)
 * have been dropped. Here is an example:
 * <p>
 * <code><pre>
 * JPanel myPanel = new JPanel();
 * new FileDropUtils( myPanel, new FileDropUtils.Listener()
 * {   public void filesDropped( java.io.File[] files )
 * {
 * // handle file drop
 * ...
 * }   // end filesDropped
 * }); // end FileDropUtils.Listener
 * </pre></code>
 * <p>
 * You can specify the border that will appear when files are being dragged by
 * calling the constructor with a <tt>javax.swing.border.Border</tt>. Only
 * <tt>JComponent</tt>s will show any indication with a border.
 * <p>
 * You can turn on some debugging features by passing a <tt>PrintStream</tt>
 * object (such as <tt>System.out</tt>) into the full constructor. A
 * <tt>null</tt>
 * value will result in no extra debugging information being output.
 * <p>
 *
 * <p>
 * I'm releasing this code into the Public Domain. Enjoy.
 * </p>
 * <p>
 * <em>Original author: Robert Harder, rharder@usa.net</em></p>
 * <p>
 * 2007-09-12 Nathan Blomquist -- Linux (KDE/Gnome) support added.</p>
 *
 * @author Robert Harder
 * @author rharder@users.sf.net
 * @version 1.0.1
 */
public class FileDrop {

  /**
   * Default border color.
   */
  static final Color DEFAULT_BORDER_COLOR = new Color(0f, 0f, 1f, 0.25f);

  /**
   * The logger utility for this class.
   */
  private static final Logger LOGGER = Logger.getLogger(FileDrop.class.getName());

  /**
   * The normal border before drag drop indicator was used.
   */
  private transient Border normalBorder;

  /**
   * drop listener.
   */
  private transient DropTargetListener dropListener;

  /**
   * Full constructor with a specified border and debugging optionally turned
   * on. With Debugging turned on, more status messages will be displayed to
   * <tt>out</tt>. A common way to use this constructor is with
   * <tt>System.out</tt> or <tt>System.err</tt>. A <tt>null</tt> value for the
   * parameter <tt>out</tt> will result in no debugging output.
   *
   * @param c Component on which files will be dropped.
   * @param dragBorder Border to use on <tt>JComponent</tt> when dragging
   * occurs.
   * @param recursive Recursively set children as drop targets.
   * @param listener Listens for <tt>filesDropped</tt>.
   * @since 1.0
   */
  public FileDrop(
      final Component c,
      final Border dragBorder,
      final boolean recursive,
      final FileDropEvent.Listener listener) {

    if (FileDropUtils.supportsDnD()) {   // Make a drop listener
      dropListener = new DropTargetListener() {
        @Override
        public void dragEnter(DropTargetDragEvent evt) {
          LOGGER.info("FileDrop: dragEnter event.");

          // Is this an acceptable drag event?
          if (FileDropUtils.isDragOk(evt)) {
            // If it's a Swing component, set its border
            if (c instanceof javax.swing.JComponent) {
              javax.swing.JComponent jc = (javax.swing.JComponent) c;
              normalBorder = jc.getBorder();
              LOGGER.log(Level.INFO, "FileDrop: normal border saved.");
              jc.setBorder(dragBorder);
              LOGGER.log(Level.INFO, "FileDrop: drag border set.");
            }   // end if: JComponent

            // Acknowledge that it's okay to enter
            //evt.acceptDrag( java.awt.dnd.DnDConstants.ACTION_COPY_OR_MOVE );
            evt.acceptDrag(java.awt.dnd.DnDConstants.ACTION_COPY);
            LOGGER.info("FileDrop: event accepted.");

          } else {   // Reject the drag event
            evt.rejectDrag();
            LOGGER.info("FileDrop: event rejected.");
          }   // end else: drag not ok
        }   // end dragEnter

        @Override
        public void dragOver(DropTargetDragEvent evt) {
          // over the drag target.
        }

        @Override
        public void drop(DropTargetDropEvent evt) {
          LOGGER.info("FileDrop: drop event.");
          try {   // Get whatever was dropped
            Transferable tr = evt.getTransferable();

            // Is it a file list?
            if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
              // Say we'll take it.
              //evt.acceptDrop ( java.awt.dnd.DnDConstants.ACTION_COPY_OR_MOVE );
              evt.acceptDrop(java.awt.dnd.DnDConstants.ACTION_COPY);
              LOGGER.info("FileDrop: file list accepted.");

              // Get a useful list
              Object fileListObject = tr.getTransferData(DataFlavor.javaFileListFlavor);
              if (fileListObject instanceof List<?>) {
                List<?> fileList = (List<?>) fileListObject;
                // Convert list to array
                List<File> files = new ArrayList<>();
                for (Object item : fileList) {
                  if (item instanceof File) {
                    files.add((File) item);
                  }
                }

                // Alert listener to drop.
                if (listener != null) {
                  listener.filesDropped(new FileDropEvent(this, files));
                }
              }

              // Mark that drop is completed.
              evt.getDropTargetContext().dropComplete(true);
              LOGGER.info("FileDrop: drop complete.");
            } else {
              // Thanks, Nathan!
              // BEGIN 2007-09-12 Nathan Blomquist -- Linux (KDE/Gnome) support added.
              DataFlavor[] flavors = tr.getTransferDataFlavors();
              boolean handled = false;
              for (DataFlavor flavor : flavors) {
                if (flavor.isRepresentationClassReader()) {
                  // Say we'll take it.
                  //evt.acceptDrop ( java.awt.dnd.DnDConstants.ACTION_COPY_OR_MOVE );
                  evt.acceptDrop(java.awt.dnd.DnDConstants.ACTION_COPY);
                  LOGGER.info("FileDrop: reader accepted.");
                  Reader reader = flavor.getReaderForText(tr);
                  BufferedReader br = new BufferedReader(reader);
                  if (listener != null) {
                    List<File> files = FileDropUtils.createFileArray(br);
                    listener.filesDropped(new FileDropEvent(this, files));
                  }
                  // Mark that drop is completed.
                  evt.getDropTargetContext().dropComplete(true);
                  LOGGER.info("FileDrop: drop complete.");
                  handled = true;
                  break;
                }
              }
              if (!handled) {
                LOGGER.info("FileDrop: not a file list or reader - abort.");
                evt.rejectDrop();
              }
              // END 2007-09-12 Nathan Blomquist -- Linux (KDE/Gnome) support added.
            }   // end else: not a file list
          } catch (IOException io) {
            LOGGER.info("FileDrop: IOException - abort:");
            evt.rejectDrop();
          } catch (java.awt.datatransfer.UnsupportedFlavorException ufe) {
            LOGGER.log(Level.SEVERE, ufe.getMessage(), ufe);
            evt.rejectDrop();
          } finally {
            // If it's a Swing component, reset its border
            if (c instanceof javax.swing.JComponent) {
              javax.swing.JComponent jc = (javax.swing.JComponent) c;
              jc.setBorder(normalBorder);
              LOGGER.info("FileDrop: normal border restored.");
            }   // end if: JComponent
          }   // end finally
        }   // end drop

        @Override
        public void dragExit(DropTargetEvent evt) {
          LOGGER.info("FileDrop: dragExit event.");
          // If it's a Swing component, reset its border
          if (c instanceof JComponent) {
            javax.swing.JComponent jc = (JComponent) c;
            jc.setBorder(normalBorder);
            LOGGER.info("FileDrop: normal border restored.");
          }   // end if: JComponent
        }   // end dragExit

        @Override
        public void dropActionChanged(DropTargetDragEvent evt) {
          LOGGER.info("FileDrop: dropActionChanged event.");
          // Is this an acceptable drag event?
          if (FileDropUtils.isDragOk(evt)) {
            evt.acceptDrag(ACTION_COPY);
            LOGGER.info("FileDrop: event accepted.");
          } else {
            evt.rejectDrag();
            LOGGER.info("FileDrop: event rejected.");
          }   // end else: drag not ok
        }   // end dropActionChanged
      }; // end DropTargetListener

      // Make the component (and possibly children) drop targets
      makeDropTarget(c, recursive);
    } else {
      LOGGER.info("FileDrop: Drag and drop is not supported with this JVM");
    }   // end else: does not support DnD
  }   // end constructor

  /**
   * Creates the DropTarget object.
   *
   * @param c Component
   * @param recursive Boolean recursive.
   */
  private void makeDropTarget(final Component c, boolean recursive) {
    final DropTarget dt = new DropTarget();
    try {
      dt.addDropTargetListener(dropListener);
    } catch (java.util.TooManyListenersException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }   // end catch

    // Listen for hierarchy changes and remove the drop target when the parent gets cleared out.
    c.addHierarchyListener(new HierarchyListener() {

      @Override
      public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
//        LOGGER.log(Level.INFO, "FileDrop: Hierarchy changed.");
        java.awt.Component parent = c.getParent();
        if (parent == null) {
          c.setDropTarget(null);
//          LOGGER.log(Level.INFO, "FileDrop: Drop target cleared from component.");
        } else {
          new DropTarget(c, dropListener);
//          LOGGER.log(Level.INFO, "FileDrop: Drop target added to component.");
        }   // end else: parent not null
      }   // end hierarchyChanged
    }); // end hierarchy listener
    if (c.getParent() != null) {
      new DropTarget(c, dropListener);
    }

    if (recursive && (c instanceof java.awt.Container)) {
      // Get the container
      java.awt.Container cont = (java.awt.Container) c;

      // Get it's components
      java.awt.Component[] comps = cont.getComponents();

      // Set it's components as listeners also
      for (Component comp : comps) {
        makeDropTarget(comp, recursive);
      }
    }   // end if: recursively set components as listener
  }   // end dropListener

}
