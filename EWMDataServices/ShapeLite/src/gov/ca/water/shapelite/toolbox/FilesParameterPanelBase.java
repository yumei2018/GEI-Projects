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
package gov.ca.water.shapelite.toolbox;

import gov.ca.water.shapelite.toolbox.resources.ToolboxImages;
import java.awt.Component;
import java.io.File;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public abstract class FilesParameterPanelBase extends ParameterPanel {

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
  public int openMode;
  public int fileMode;
  private static final JFileChooser fileChooser = new JFileChooser();
  private boolean ignoreTextChanges;
  private FileFilter filter;

  private String dialogTitle;
  private String fileTypeDesc;
  private String extension;

  /**
   * Red error light.
   */
  private static final ImageIcon error
      = new ImageIcon(ToolboxImages.getInstance().get("StatusError.png"));
  /**
   * Yellow warning light.
   */
  private static final ImageIcon warning
      = new ImageIcon(ToolboxImages.getInstance().get("StatusWarning.png"));
  /**
   * Green good light.
   */
  private static final ImageIcon good
      = new ImageIcon(ToolboxImages.getInstance().get("StatusGood.png"));

  private int editRow;
  JTextField editBox;

  /**
   * This is the listener that causes changes to happen after editing.
   */
  private final DocumentListener editDocumentListener = new DocumentListener() {

    @Override
    public void insertUpdate(DocumentEvent e) {
      updateStatus();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
      updateStatus();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
      updateStatus();
    }
  };

  /**
   * The cell editor for the table.
   */
  class StatusTextEditor extends DefaultCellEditor {

    /**
     * For the text field, this ensures that the editBox is used.
     *
     * @param textField the JTextField being edited.
     */
    public StatusTextEditor(JTextField textField) {
      super(textField);
    }

    @Override
    public Component getComponent() {
      return editBox;
    }

  }

  /**
   * An Icon renderer for the table.
   */
  class IconRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table,
        Object value, boolean isSelected, boolean hasFocus,
        int row, int column) {
      Component comp = super.getTableCellRendererComponent(table,
          value, isSelected, hasFocus, row, column);
      if (value instanceof Icon) {
        if (comp instanceof JLabel) {
          JLabel label = (JLabel) comp;
          label.setText(null);
          label.setIcon((Icon) value);
        }
      }
      return comp;
    }

  }

  /**
   * Creates new form InputFileParameterPanel.
   */
  public FilesParameterPanelBase() {
    initComponents();
    tablePaths.getColumnModel().getColumn(0).setCellRenderer(new IconRenderer());

    editBox = new JTextField();
    editBox.getDocument().addDocumentListener(editDocumentListener);
    StatusTextEditor editor = new StatusTextEditor(editBox);

    tablePaths.getColumnModel().getColumn(1).setCellEditor(editor);
  }

  /**
   * This method is called if the filename has been changed.
   */
  protected final void updateParameter() {
    if (getParameter() == null) {
      return;
    }
    updateStatus();
    setToolTip(getParameter().getValidationMessage());
  }

  /**
   * Updates the status light to reflect the most recent text.
   */
  protected final void updateStatus() {
    PathsParameter p = (PathsParameter) this.getParameter();
    DefaultTableModel model = (DefaultTableModel) tablePaths.getModel();
    if (tablePaths.isEditing()) {
      if (editBox != null && editRow >= 0) {
        String path = editBox.getText();
        ImageIcon icon = null;
        switch (p.getStatus(path)) {
          case Error:
            icon = error;
            break;
          case Warning:
            icon = warning;
            break;
          case Good:
            icon = good;
            break;
          default:
          // do nothing;
        }
        model.setValueAt(icon, editRow, 0);
        setPathsFromTable(editRow);
      }
    }

  }

  /**
   * This updates the parameter to reflect text edits.
   *
   * @param row The integer row to set the value for.
   */
  private void setPathsFromTable(int row) {
    PathsParameter p = (PathsParameter) this.getParameter();
    List<String> paths = p.getPaths();
    if (paths.size() > row) {
      paths.set(row, editBox.getText());
    } else if (paths.isEmpty() && row == 0) {
      paths.add(row, editBox.getText());
    }
    p.setPaths(paths);
  }

  /**
   * Sets the tool tip for this parameter panel.
   *
   * @param text The String tool tip text to set.
   */
  protected void setToolTip(String text) {
    this.setToolTipText(text);
  }


  /**
   * Initialize components.
   */
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    tablePaths = new javax.swing.JTable();
    buttonAdd = new javax.swing.JButton();
    buttonDelete = new javax.swing.JButton();
    buttonMoveUp = new javax.swing.JButton();
    buttonMoveDown = new javax.swing.JButton();

    setBorder(javax.swing.BorderFactory.createTitledBorder("Input Folders:"));
    setMaximumSize(new java.awt.Dimension(32767, 130));
    setMinimumSize(new java.awt.Dimension(200, 0));
    setName("");

    tablePaths.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][]{
          {null, null},
          {null, null},
          {null, null},
          {null, null}
        },
        new String[]{
          "Status", "Input File"
        }
    ) {
      Class<?>[] types = new Class<?>[]{
        java.lang.Object.class, java.lang.String.class
      };

      public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex];
      }
    });
    tablePaths.setRowHeight(22);
    tablePaths.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane1.setViewportView(tablePaths);
    if (tablePaths.getColumnModel().getColumnCount() > 0) {
      tablePaths.getColumnModel().getColumn(0).setMinWidth(50);
      tablePaths.getColumnModel().getColumn(0).setPreferredWidth(50);
      tablePaths.getColumnModel().getColumn(0).setMaxWidth(50);
      tablePaths.getColumnModel().getColumn(0).setHeaderValue("Status");
      tablePaths.getColumnModel().getColumn(1).setHeaderValue("Input File");
    }

    buttonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gov/ca/water/shapelite/toolbox/resources/Add10.png")));
    buttonAdd.setToolTipText("Add Path");
    buttonAdd.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonAddActionPerformed(evt);
      }
    });

    buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gov/ca/water/shapelite/toolbox/resources/Subtract10.png")));

    buttonDelete.setToolTipText("Remove Path");
    buttonDelete.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonDeleteActionPerformed(evt);
      }
    });

    buttonMoveUp.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gov/ca/water/shapelite/toolbox/resources/up10.png")));

    buttonMoveUp.setToolTipText("Move Up");
    buttonMoveUp.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonMoveUpActionPerformed(evt);
      }
    });

    buttonMoveDown.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gov/ca/water/shapelite/toolbox/resources/down10.png")));

    buttonMoveDown.setToolTipText("Move Down");
    buttonMoveDown.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonMoveDownActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jScrollPane1, DEFAULT_SIZE,
                383, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(buttonAdd, Alignment.TRAILING, PREFERRED_SIZE, 34,
                    PREFERRED_SIZE)
                .addComponent(buttonDelete, Alignment.TRAILING, PREFERRED_SIZE,
                    34, PREFERRED_SIZE)
                .addComponent(buttonMoveUp, Alignment.TRAILING, PREFERRED_SIZE,
                    34, PREFERRED_SIZE)
                .addComponent(buttonMoveDown, Alignment.TRAILING, PREFERRED_SIZE,
                    34, PREFERRED_SIZE)))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addComponent(buttonAdd)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonDelete)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonMoveUp)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonMoveDown))
    );
  }

  /**
   * Gets an integer which is one of the following, and controls how the dialog
   * is shown. Valid values are:
   * <ul> <li>FileInputPanel.FILES_ONLY = 0</li>
   * <li>FileInputPanel.DIRECTORIES_ONLY = 1</li>
   * <li>FileInputPanel.FILES_AND_DIRECTORIES = 2</li> </ul>
   *
   * @return the fileMode
   */
  public final int getFileMode() {
    return fileMode;
  }

  /**
   * Gets an integer which is one of the following, and controls how the dialog
   * is shown. Valid values are:
   * <ul> <li>FileInputPanel.FILES_ONLY = 0</li>
   * <li>FileInputPanel.DIRECTORIES_ONLY = 1</li>
   * <li>FileInputPanel.FILES_AND_DIRECTORIES = 2</li> </ul>
   *
   * @param fileMode The integer representing the file mode.
   */
  public final void setFileMode(int fileMode) {
    this.fileMode = fileMode;
  }

  /**
   * Sets the string title on the border of this component.
   *
   * @param title The String title.
   */
  public final void setTitle(String title) {
    TitledBorder border = (TitledBorder) this.getBorder();
    border.setTitle(title);
  }

  /**
   * Gets the string title that borders this parameter.
   *
   * @return The String title.
   */
  public final String getTitle() {
    TitledBorder border = (TitledBorder) this.getBorder();
    return border.getTitle();
  }

  private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
    fileChooser.setFileSelectionMode(this.getFileMode());
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
    if (this.getOpenMode() == FileInputPanel.OPEN) {
      if (fileChooser.showOpenDialog(this)
          == JFileChooser.APPROVE_OPTION) {
        String path = fileChooser.getSelectedFile().getAbsolutePath();
        PathsParameter p = (PathsParameter) this.getParameter();
        p.add(path);
      }
    } else if (this.getOpenMode() == FileInputPanel.SAVE) {
      if (fileChooser.showSaveDialog(this)
          == JFileChooser.APPROVE_OPTION) {
        String path = fileChooser.getSelectedFile().getAbsolutePath();
        PathsParameter p = (PathsParameter) this.getParameter();
        p.add(path);
      }
    }
    updateTable();
  }//GEN-LAST:event_buttonAddActionPerformed

  private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
    int row = tablePaths.getSelectedRow();
    if (row > -1) {
      int modRow = tablePaths.convertRowIndexToModel(row);
      PathsParameter p = (PathsParameter) this.getParameter();
      p.removeAt(modRow);
      updateTable();
    }
  }//GEN-LAST:event_buttonDeleteActionPerformed

  private void buttonMoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMoveUpActionPerformed
    int row = tablePaths.getSelectedRow();
    PathsParameter p = (PathsParameter) this.getParameter();
    List<String> paths = p.getPaths();
    if (row > 0 && row < paths.size()) {
      int modRow = tablePaths.convertRowIndexToModel(row);
      String item = paths.remove(modRow);
      paths.add(modRow - 1, item);
      updateTable();
    }
  }//GEN-LAST:event_buttonMoveUpActionPerformed

  private void buttonMoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMoveDownActionPerformed
    int row = tablePaths.getSelectedRow();
    PathsParameter p = (PathsParameter) this.getParameter();
    List<String> paths = p.getPaths();
    if (row > -1 && row < paths.size() - 1) {
      int modRow = tablePaths.convertRowIndexToModel(row);
      String item = paths.remove(modRow);
      paths.add(modRow + 1, item);
      updateTable();
    }
  }//GEN-LAST:event_buttonMoveDownActionPerformed

  /**
   * This method refreshes the table to match the current parameter conditions.
   */
  protected final void updateTable() {
    InputFoldersParameter p = (InputFoldersParameter) this.getParameter();
    DefaultTableModel model = (DefaultTableModel) tablePaths.getModel();
    model.setRowCount(0);
    for (String path : p.getPaths()) {
      ImageIcon icon = null;
      switch (p.getStatus(path)) {
        case Error:
          icon = error;
          break;
        case Warning:
          icon = warning;
          break;
        case Good:
          icon = good;
          break;
        default:
        // do nothing
      }
      model.addRow(new Object[]{icon, path});
    }
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonAdd;
  private javax.swing.JButton buttonDelete;
  private javax.swing.JButton buttonMoveDown;
  private javax.swing.JButton buttonMoveUp;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable tablePaths;
  // End of variables declaration//GEN-END:variables

  /**
   * @return the openMode
   */
  public int getOpenMode() {
    return openMode;
  }

  /**
   * @param openMode the openMode to set
   */
  public void setOpenMode(int openMode) {
    this.openMode = openMode;
  }
}
