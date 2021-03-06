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

import gov.ca.water.shapelite.toolbox.resources.ToolboxImages;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ConnectionDialog extends javax.swing.JPanel {

  /**
   * Creates new form ConnectionDialog
   */
  public ConnectionDialog() {
    initComponents();
  }

  public void setParameter(ConnectionParameter parameter) {
    updateGui(parameter);
  }

  private void updateGui(ConnectionParameter parameter) {
    textConnectionName.setText(parameter.getConnectionName());
    textHostName.setText(parameter.getHostname());
    textPassword.setText(parameter.getPassword());
    textPort.setText(parameter.getPort());
    textSID.setText(parameter.getSid());
    textUserName.setText(parameter.getUserName());

  }

  public ConnectionParameter getParameter() {
    ConnectionParameter result = new ConnectionParameter();
    result.setConnectionName(textConnectionName.getText());
    result.setHostname(textHostName.getText());
    result.setPassword(new String(textPassword.getPassword()));
    result.setPort(textPort.getText());
    result.setSid(textSID.getText());
    result.setUserName(textUserName.getText());
    return result;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    labelConnectionName = new javax.swing.JLabel();
    labelUserName = new javax.swing.JLabel();
    labelPassword = new javax.swing.JLabel();
    labelHostname = new javax.swing.JLabel();
    labelPort = new javax.swing.JLabel();
    labelSID = new javax.swing.JLabel();
    textConnectionName = new javax.swing.JTextField();
    textUserName = new javax.swing.JTextField();
    textHostName = new javax.swing.JTextField();
    textPort = new javax.swing.JTextField();
    textSID = new javax.swing.JTextField();
    textPassword = new javax.swing.JPasswordField();
    labelStatus = new javax.swing.JLabel();
    buttonTestConnection = new javax.swing.JButton();
    jScrollPane2 = new javax.swing.JScrollPane();
    textConnectionMessage = new javax.swing.JTextArea();

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    labelConnectionName.setText("Connection Name");

    labelUserName.setText("Username");

    labelPassword.setText("Password");

    labelHostname.setText("Hostname");

    labelPort.setText("Port");

    labelSID.setText("SID");

    labelStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource(
        "/gov/ca/water/shapelite/toolbox/resources/StatusWarning.png")));

    buttonTestConnection.setText("Test Connection");
    buttonTestConnection.addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonTestConnectionActionPerformed(evt);
      }
    });

    textConnectionMessage.setColumns(20);
    textConnectionMessage.setRows(5);
    jScrollPane2.setViewportView(textConnectionMessage);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(labelConnectionName)
                .addComponent(labelUserName)
                .addComponent(labelPassword)
                .addComponent(labelHostname)
                .addComponent(labelPort)
                .addComponent(labelSID)
                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(textConnectionName)
                .addComponent(textUserName)
                .addComponent(textHostName)
                .addComponent(textPort)
                .addComponent(textSID)
                .addComponent(textPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(buttonTestConnection)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
        .addComponent(jScrollPane2)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelConnectionName)
                .addComponent(textConnectionName,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelUserName)
                .addComponent(textUserName,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelPassword)
                .addComponent(textPassword,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelHostname)
                .addComponent(textHostName, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelPort)
                .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelSID)
                .addComponent(textSID, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE,
                    41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonTestConnection))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE,
                143, Short.MAX_VALUE))
    );
  }

  private void buttonTestConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTestConnectionActionPerformed
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      String connectionText = "jdbc:oracle:thin:@" + this.textHostName.getText() + ":" + this.textPort.getText() + ":" + this.textSID.getText();
      Connection con = DriverManager.getConnection(connectionText, this.textUserName.getText(), new String(this.textPassword.getPassword()));
      con.close();
      this.labelStatus.setIcon(new ImageIcon(ToolboxImages.getInstance().get("StatusGood.png")));
      this.textConnectionMessage.setText("Connected.");
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ConnectionDialog.class.getName()).log(
          Level.SEVERE, ex.getMessage(), ex);
      this.labelStatus.setIcon(new ImageIcon(ToolboxImages.getInstance().get("StatusError.png")));
      this.textConnectionMessage.setText(ex.getMessage());
    } catch (SQLException ex) {
      Logger.getLogger(ConnectionDialog.class.getName()).log(
          Level.SEVERE, ex.getMessage(), ex);
    }
  }//GEN-LAST:event_buttonTestConnectionActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonTestConnection;
  private javax.swing.JLabel labelConnectionName;
  private javax.swing.JLabel labelUserName;
  private javax.swing.JLabel labelPassword;
  private javax.swing.JLabel labelHostname;
  private javax.swing.JLabel labelPort;
  private javax.swing.JLabel labelSID;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JLabel labelStatus;
  private javax.swing.JTextArea textConnectionMessage;
  private javax.swing.JTextField textConnectionName;
  private javax.swing.JTextField textHostName;
  private javax.swing.JPasswordField textPassword;
  private javax.swing.JTextField textPort;
  private javax.swing.JTextField textSID;
  private javax.swing.JTextField textUserName;
  // End of variables declaration//GEN-END:variables
}
