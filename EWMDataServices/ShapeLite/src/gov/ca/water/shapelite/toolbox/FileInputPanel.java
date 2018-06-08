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
package gov.ca.water.shapelite.toolbox;

import gov.ca.water.shapelite.FileHelper;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.EventListenerList;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;

/**
 *
 * @beaninfo description: This panel supports typing a file name or browsing to
 * the file.
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class FileInputPanel extends javax.swing.JPanel {

  private JButton button;
  /**
   * Instruction to display only files.
   */
  public static final int FILES_ONLY = 0;
  /**
   * Instruction to display only directories.
   */
  public static final int DIRECTORIES_ONLY = 1;
  /**
   * Instruction to display both files and directories.
   */
  public static final int FILES_AND_DIRECTORIES = 2;
  /**
   * Instruction to display as an open file dialog
   */
  public static final int OPEN = 0;
  /**
   * Instruction to display as a save file dialog
   */
  public static final int SAVE = 1;
  private int openMode;
  private int fileMode;
  private final EventListenerList eventListeners;
  private static final JFileChooser fileChooser = new JFileChooser();
  private boolean ignoreTextChanges;
  private FileFilter filter;

  private String dialogTitle;
  private String fileTypeDesc;
  private String extension;

  //<editor-fold defaultstate="collapsed" desc="FilenameChangedEvent">
  /**
   * Allows a new listener to be added to this object.
   *
   * @param listener
   */
  public void addFilenameChangedListener(FilenameChangedListener listener) {
    FilenameChangedListener[] listeners = eventListeners.getListeners(FilenameChangedListener.class);
    if (!Arrays.asList(listeners).contains(listener)) {
      eventListeners.add(FilenameChangedListener.class, listener);
    }
  }

  /**
   * Removes an existing FilenameChanged listener
   *
   * @param listener
   */
  public void removeFilenameChangedListener(FilenameChangedListener listener) {
    eventListeners.remove(FilenameChangedListener.class, listener);
  }

  /**
   * Fires the event.
   *
   */
  public void fireFilenameChanged() {
    validateFile();
    FilenameChangedListener[] listeners = eventListeners.getListeners(FilenameChangedListener.class);
    FileEvent evt = new FileEvent(this, textFile.getText());
    for (FilenameChangedListener listener : listeners) {
      listener.filenameChanged(evt);
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="ClickEvent">
  private final List<ClickEvent.Listener> clickListeners = new ArrayList<>();

  /**
   * Adds the specified listener to the list of listeners to be notified during
   * an event. If the item is already in the list, it will not be added a second
   * time.
   *
   * @param listener The ClickEvent.Listener to connect.
   */
  public void addClickListener(ClickEvent.Listener listener) {
    if (!clickListeners.contains(listener)) {
      clickListeners.add(listener);
    }
  }

  /**
   * Removes the specified listener from the list if it is in the list.
   *
   * @param listener The ClickEvent.Listener to disconnect.
   */
  public void removeClickListener(ClickEvent.Listener listener) {
    clickListeners.remove(listener);
  }

  /**
   * Fires the Click event and notifies each of the listeners.
   *
   * @param e A {EventType}Event with the source object and any properties
   * associated with this event.
   */
  public void fireClick(ClickEvent e) {
    for (ClickEvent.Listener listener : clickListeners) {
      listener.clicked(e);
    }
  }

  //</editor-fold>
  /**
   * Tests to see if the entered file is valid. If it is, color the text black.
   * Otherwise, color it red.
   */
  private void validateFile() {
    File f = new File(textFile.getText());

    if (this.openMode == OPEN && !f.exists()) {
      textFile.setForeground(Color.RED);
      textFile.setToolTipText("The specified file or directory does not exist.");
      return;
    }
    if (this.getFileMode() == DIRECTORIES_ONLY) {
      if (f.exists() && f.isFile()) {
        textFile.setForeground(Color.RED);
        textFile.setToolTipText("A file was specified where a folder is expected.");
        return;
      }
    }
    if (this.getFileMode() == FILES_ONLY) {
      if (f.exists() && f.isDirectory()) {
        textFile.setForeground(Color.RED);
        textFile.setToolTipText("A folder was specified where a file is expected.");
        return;
      }
    }
    if (this.getFilter() != null && f.exists() && !this.getFilter().accept(f)) {
      textFile.setForeground(Color.RED);
      textFile.setToolTipText("The file does not end with the expected extension, or match the filter conditions.");
      return;
    }
    String ext = this.getExtension();
    if (ext != null && f.exists() && !f.getAbsolutePath().endsWith(ext)) {
      textFile.setForeground(Color.RED);
      textFile.setToolTipText("The file does not end with the expected extension.");
      return;
    }

    textFile.setForeground(Color.BLACK);
    textFile.setToolTipText("Enter a valid filename or directory path.");

  }

  /**
   * Creates new form FileInputPanel
   */
  public FileInputPanel() {
    initComponents();
    eventListeners = new EventListenerList();
    textFile.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        if (ignoreTextChanges) {
          return;
        }
        fireFilenameChanged();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        if (ignoreTextChanges) {
          return;
        }
        fireFilenameChanged();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        if (ignoreTextChanges) {
          return;
        }
        fireFilenameChanged();
      }
    });
    textFile.setTransferHandler(new FileTransferHandler());

  }

  /**
   * Sets the title for this file input panel (that appears in the titled
   * border).
   *
   * @param text
   */
  public void setTitle(String text) {
    Border b = this.getBorder();
    if (b instanceof TitledBorder) {
      ((TitledBorder) this.getBorder()).setTitle(text);
    }
  }

  /**
   * Gets the title.
   *
   * @return
   */
  public String getTitle() {
    Border b = this.getBorder();
    if (b instanceof TitledBorder) {
      return ((TitledBorder) this.getBorder()).getTitle();
    }
    return null;
  }

  /**
   * Initializes the components.
   */
  private void initComponents() {

    textFile = new javax.swing.JTextField();
    buttonBrowse = new javax.swing.JButton();

    setBorder(javax.swing.BorderFactory.createTitledBorder("Input File"));
    setFocusable(false);

    textFile.setToolTipText("Type, or drag the input file here.");
    textFile.setDragEnabled(true);

    buttonBrowse.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gov/ca/water/shapelite/toolbox/resources/File16.png"))); // NOI18N

    buttonBrowse.setToolTipText("Browse for a filename.");
    buttonBrowse.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonBrowseActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(textFile, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonBrowse))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(buttonBrowse))
        .addComponent(textFile, javax.swing.GroupLayout.Alignment.TRAILING)
    );
  }// </editor-fold>//GEN-END:initComponents

  private void buttonBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBrowseActionPerformed

    fileChooser.setFileSelectionMode(this.getFileMode());
    File f = new File(textFile.getText());
    if (dialogTitle != null) {
      fileChooser.setDialogTitle(dialogTitle);
    }
    if (filter != null) {
      fileChooser.setFileFilter(filter);
    } else if (extension != null) {
      fileChooser.setFileFilter(new FileFilter() {

        @Override
        public boolean accept(File f) {
          if (f.isDirectory()) {
            return true;
          }
          return f.getName().endsWith(extension);
        }

        @Override
        public String getDescription() {
          return fileTypeDesc;
        }
      });
    }

    fileChooser.setSelectedFile(f);
    if (this.getOpenMode() == FileInputPanel.OPEN) {

      if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        this.ignoreTextChanges = true;
        this.textFile.setText(fileChooser.getSelectedFile().getAbsolutePath());
        this.ignoreTextChanges = false;
        fireFilenameChanged();
      }
    } else if (this.getOpenMode() == FileInputPanel.SAVE) {
      if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        this.ignoreTextChanges = true;
        this.textFile.setText(fileChooser.getSelectedFile().getAbsolutePath());
        this.ignoreTextChanges = false;
        fireFilenameChanged();
      }
    }

  }//GEN-LAST:event_buttonBrowseActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonBrowse;
  private javax.swing.JTextField textFile;
  // End of variables declaration//GEN-END:variables

  /**
   * Gets an integer which is one of the following, and controls how the dialog
   * is shown:
   * <ul> <li>FileInputPanel.FILES_ONLY = 0</li>
   * <li>FileInputPanel.DIRECTORIES_ONLY = 1</li>
   * <li>FileInputPanel.FILES_AND_DIRECTORIES = 2</li> </ul>
   *
   * @return the fileMode
   */
  public int getFileMode() {
    return fileMode;
  }

  /**
   * Gets an integer which is one of the following, and controls how the dialog
   * is shown:
   * <ul> <li>FileInputPanel.FILES_ONLY = 0</li>
   * <li>FileInputPanel.DIRECTORIES_ONLY = 1</li>
   * <li>FileInputPanel.FILES_AND_DIRECTORIES = 2</li> </ul>
   *
   * @param fileMode The integer representing the file mode.
   */
  public void setFileMode(int fileMode) {
    this.fileMode = fileMode;
  }

  /**
   * Gets the filename
   *
   * @return
   */
  public String getFilename() {
    return textFile.getText();
  }

  /**
   * Sets the filename for this control.
   *
   * @param file
   * @beaninfo attribute: visualUpdate true
   */
  public void setFilename(String file) {
    textFile.setText(file);

  }

  /**
   * This checks the folder of the specified path, looking for existing paths of
   * the same name, and increments a numeric index in parenthesis so that
   * myFile.jpg becomes myFile(1).jpg, preventing overwriting. If the parent
   * folder doesn't exist, it will attempt to be created.
   *
   * @param path The default name without any numbering appended.
   */
  public final void setFilenameNew(String path) {
    File f = new File(path);
    File parent = f.getParentFile();
    if (!parent.exists()) {
      parent.mkdirs();
    }

    int p = path.lastIndexOf(".");
    String base = FileHelper.removeExtension(f.getName());
    String ext = FileHelper.getExtension(f.getName()).orElse(null);
    File[] otherFiles = parent.listFiles();
    int nameindex = 0;
    String defaultName;
    boolean used;
    do {
      used = false;
      defaultName = base;
      if (nameindex > 0) {
        defaultName = defaultName + "(" + nameindex + ")";
      }
      if (ext != null && !ext.isEmpty()) {
        defaultName += "." + ext; // folder names will not add the period or extension
      }
      nameindex++;
      for (File file : otherFiles) {
        if (file.getName().equals(defaultName)) {
          used = true;
          break;
        }
      }
    } while (used);
    textFile.setText(parent.getAbsolutePath() + File.separator + defaultName);
  }

  /**
   * Gets an integer which is one of the following and controls whether the
   * dialog behaves as an open dialog or a save dialog. <ul>
   * <li>FileInputPanel.OPEN = 0</li>
   * <li>FileInputPanel.SAVE = 1</li> </ul>
   *
   * @return the openMode
   */
  public int getOpenMode() {
    return openMode;
  }

  /**
   * Sets an integer which is one of the following and controls whether the
   * dialog behaves as an open dialog or a save dialog. <ul>
   * <li>FileInputPanel.OPEN = 0</li>
   * <li>FileInputPanel.SAVE = 1</li> </ul>
   *
   * @beaninfo attribute: stringKeys new String[] enum: OPEN FileInputPanel.OPEN
   * SAVE FileInputPanel.SAVE description: This property controls whether the
   * open
   * @param openMode the openMode to set
   */
  public void setOpenMode(int openMode) {
    this.openMode = openMode;
  }

  /**
   * @return the dialogTitle
   */
  public String getDialogTitle() {
    return dialogTitle;
  }

  /**
   * @param dialogTitle the dialogTitle to set
   */
  public void setDialogTitle(String dialogTitle) {
    this.dialogTitle = dialogTitle;
  }

  /**
   * @return the fileTypeDesc
   */
  public String getFileTypeDesc() {
    return fileTypeDesc;
  }

  /**
   * @param fileTypeDesc the fileTypeDesc to set
   */
  public void setFileTypeDesc(String fileTypeDesc) {
    this.fileTypeDesc = fileTypeDesc;
  }

  /**
   * @return the extension
   */
  public String getExtension() {
    return extension;
  }

  /**
   * @param extension the extension to set
   */
  public void setExtension(String extension) {
    this.extension = extension;
  }

  /**
   * Overrides the tool tip text to also set the property for the text and
   * browse button.
   *
   * @param toolTipText the toolTipText to set
   */
  @Override
  public void setToolTipText(String toolTipText) {
    this.textFile.setToolTipText(toolTipText);
    this.buttonBrowse.setToolTipText(toolTipText);
    super.setToolTipText(toolTipText);
  }

  /**
   * @return the filter
   */
  public FileFilter getFilter() {
    return filter;
  }

  /**
   * @param filter the filter to set
   */
  public void setFilter(FileFilter filter) {
    this.filter = filter;
  }

  public class FileTransferHandler extends TransferHandler {
// Start and end position in the source text.
    // We need this information when performing a MOVE
    // in order to remove the dragged text from the source.

    Position p0 = null, p1 = null;

    @Override
    public boolean canImport(TransferHandler.TransferSupport info) {
      if (info.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
        return true;
      }
      return info.isDataFlavorSupported(DataFlavor.stringFlavor);
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport info) {

      if (info.isDrop()) {

        // Check for FileList flavor
        if (!info.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
          displayDropLocation("List doesn't accept a drop of this type.");
          return false;
        }

        // Get the fileList that is being dropped.
        Transferable t = info.getTransferable();
        List<File> data = new ArrayList<>();
        try {
          Object content = t.getTransferData(DataFlavor.javaFileListFlavor);
          if (content instanceof List<?>) {
            List<?> list = (List<?>) content;
            for (int i = 0; i < list.size(); i++) {
              Object item = list.get(i);
              if (item instanceof File) {
                data.add((File) item);
              }
            }
          }

        } catch (UnsupportedFlavorException | IOException e) {
          return false;
        }
        File item = data.get(0);
        setFilename(item.getAbsolutePath());
        return true;
      } else if (info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
        try {
          Object data = info.getTransferable().getTransferData(DataFlavor.stringFlavor);
          if (data instanceof String) {
            textFile.replaceSelection((String) data);
            setFilename(textFile.getText());
            return true;
          }

        } catch (UnsupportedFlavorException | IOException ex) {
          Logger.getLogger(FileTransferHandler.class.getName()).log(
              Level.SEVERE, ex.getMessage(), ex);
        }
      }

      return false;
    }

    private void displayDropLocation(String string) {
      System.out.println(string);
    }

    /**
     * These text fields handle both copy and move actions.
     *
     * @param c
     * @return
     */
    @Override
    public int getSourceActions(JComponent c) {
      return COPY_OR_MOVE;
    }

    /**
     * Bundle up the data for export.
     *
     * @param c
     * @return
     */
    @Override
    protected Transferable createTransferable(JComponent c) {
      JTextField source = (JTextField) c;
      int start = source.getSelectionStart();
      int end = source.getSelectionEnd();
      Document doc = source.getDocument();
      if (start == end) {
        return null;
      }
      try {
        p0 = doc.createPosition(start);
        p1 = doc.createPosition(end);
      } catch (BadLocationException e) {
        System.out
            .println("Can't create position - unable to remove text from source.");
      }
      String data = source.getSelectedText();
      return new StringSelection(data);
    }

    /**
     * When the export is complete, remove the old text if the action was a
     * move.
     *
     * @param c
     * @param data
     * @param action
     */
    @Override
    protected void exportDone(JComponent c, Transferable data, int action) {
      if (action != MOVE) {
        return;
      }

      if ((p0 != null) && (p1 != null) && (p0.getOffset() != p1.getOffset())) {
        try {
          JTextComponent tc = (JTextComponent) c;
          tc.getDocument()
              .remove(p0.getOffset(), p1.getOffset() - p0.getOffset());
        } catch (BadLocationException e) {
          System.out.println("Can't remove text from source.");
        }
      }
    }

  }
}
