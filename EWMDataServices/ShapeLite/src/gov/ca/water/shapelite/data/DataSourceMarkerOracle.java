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
package gov.ca.water.shapelite.data;


import gov.ca.water.shapelite.data.marker.PointMarker;
import gov.ca.water.shapelite.events.PointMarkerEvent;
import gov.ca.water.shapelite.data.dataset.PointDataset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.EventListenerList;
import gov.ca.water.shapelite.Coord;
import gov.ca.water.shapelite.coordinate.CoordXY;
import gov.ca.water.shapelite.Envelope;
import gov.ca.water.shapelite.NonNull;
import gov.ca.water.shapelite.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class allows a storing the information necessary to retrieve point
 * coordinates from an oracle database, with a name field to label the markers.
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class DataSourceMarkerOracle extends DataSource<PointDataset> {

  /**
   * The LOGGER utility.
   */
  private static final Logger LOGGER =
          Logger.getLogger(DataSourceMarkerOracle.class.getName());

  //<editor-fold defaultstate="collapsed" desc="Fields">
  /**
   * The String name of the column that stores the latitude or Northing values.
   */
  private String latitudeField;

  /**
   * The list of event listeners that fire when the markers are selected, or the
   * selection is inverted.
   */
  private final EventListenerList listeners;

  /**
   * The String name of the column that stores the longitude or Easting values.
   */
  private String longitudeField;

  /**
   * The String name of the column that stores the identifier for each shape.
   */
  private String nameField;

  /**
   * The String password used to access the oracle database.
   */
  private String password;

  /**
   * The String port number that is the four digit port used to connect to
   * oracle.
   */
  private String portNumber;

  /**
   * The String name of the server address or localhost where the oracle
   * database is hosted.
   */
  private String serverName;

  /**
   * The String service ID which identifies the scope of data tables to access
   * within oracle.
   */
  private String sid;

  /**
   * The String table in the oracle database to connect to that stores the
   * coordinate information.
   */
  private String table;

  /**
   * The String username to use to connect to the database.
   */
  private String username;

  /**
   * The filter for the SQL WHERE expression.
   */
  private String where;

  //</editor-fold>
  /**
   * Creates a new instance of the DataSourceMarkerOracle.
   */
  public DataSourceMarkerOracle() {
    nameField = "NAME";
    longitudeField = "LONGITUDE";
    latitudeField = "LATITUDE";
    sid = "XE";
    portNumber = "1521";
    serverName = "localhost";
    username = "RAIN";
    password = "rain";
    table = "CITIES";
    listeners = new EventListenerList();
  }

  //<editor-fold defaultstate="collapsed" desc="Events">
  /**
   * @param listener The listener to add.
   */
  public final void addMarkerSelectListener(PointMarkerEvent.Listener listener) {

    PointMarkerEvent.Listener[] array
            = listeners.getListeners(PointMarkerEvent.Listener.class);
    List<PointMarkerEvent.Listener> list = Arrays.asList(array);
    if (!list.contains(listener)) {
      this.listeners.add(PointMarkerEvent.Listener.class, listener);
    }
  }

  /**
   * Remove the specified listener.
   *
   * @param listener The listener to remove.
   */
  public final void removeMarkerSelectListener(PointMarkerEvent.Listener listener) {
    this.listeners.remove(PointMarkerEvent.Listener.class, listener);
  }

  /**
   * Fires the event for when markers are selected.
   *
   * @param event The Event object showing the markers.
   */
  public final void fireMarkersSelected(PointMarkerEvent event) {
    PointMarkerEvent.Listener[] list
            = listeners.getListeners(PointMarkerEvent.Listener.class);
    for (PointMarkerEvent.Listener listener : list) {
      listener.markerSelectionChanged(event);
    }
  }

  /**
   * Fires the event for when the selection was inverted.
   *
   * @param event THe EventObject showing the markers.
   */
  public final void fireSelectionInverted(PointMarkerEvent event) {
    PointMarkerEvent.Listener[] list
            = listeners.getListeners(PointMarkerEvent.Listener.class);
    for (PointMarkerEvent.Listener listener : list) {
      listener.markerSelectionChanged(event);
    }
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  /**
   * Gets the entire dataset into memory at once as a DatasetMarker. This should
   * be used with caution because the DataSource may be too large to fully
   * contain in memory.
   *
   * @return The DatasetMarker with the markers loaded into memory.
   */
  @Override
  public final Optional<PointDataset> getDataset() {
    List<PointMarker> markers = this.getMarkers();
    PointDataset result = new PointDataset(markers);
    return Optional.of(result);
  }

  /**
   * Gets the dataset made up of all the markers from this data source that are
   * within the envelope.
   *
   * @param env The Envelope defining the geographic boundaries in whatever
   * coordinates the data is in. So if the data is in WGS84, this envelope would
   * be in WGS84 as well.
   * @return The DatasetMarker
   */
  public final Optional<PointDataset> getDataset(Envelope env) {
    String oldWhere = where;
    String filter = where;
    if (filter != null && !filter.isEmpty()) {
      filter += " AND " + longitudeField;
    } else {
      filter = longitudeField;
    }
    filter += " > " + env.getMin().getX() + " AND " + longitudeField + " < "
            + env.getMax().getX();
    filter += " AND " + latitudeField + " > " + env.getMin().getY() + " AND "
            + latitudeField + " < " + env.getMax().getY();
    where = filter;
    Optional<PointDataset> result = this.getDataset();
    where = oldWhere;
    return result;
  }

  /**
   * Gets the entire set of markers from this data source loaded into memory at
   * once as a list of markers. Care should be taken since the data source might
   * be too large to fit in memory.
   *
   * @return The list of markers on this data source.
   */
  @NonNull
  private List<PointMarker> getMarkers() {
    List<PointMarker> result = new ArrayList<>();

    Connection con;
    try {
      String driverName = "oracle.jdbc.driver.OracleDriver";
      Class.forName(driverName);
      String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber
              + ":" + sid;
      con = DriverManager.getConnection(url, username, password);
      Statement stmt = con.createStatement();
      String query = "SELECT " + nameField + ", " + longitudeField + ", "
              + latitudeField + " FROM " + table;
      if (this.where != null) {
        query += " WHERE " + where;
      }
      ResultSet rset = stmt.executeQuery(query);
      while (rset.next()) {
        PointMarker m = new PointMarker();
        m.setName(rset.getString(nameField));
        Coord c = new CoordXY(rset.getDouble(longitudeField),
                rset.getDouble(latitudeField));
        m.setCoordinate(c);
        result.add(m);
      }
    } catch (ClassNotFoundException | SQLException e) {
      LOGGER.log(Level.SEVERE, e.getMessage(), e);
    }
    return result;
  }

  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Properties">
  /**
   * Gets the String name of the column that stores the latitude values.
   *
   * @return the latitudeField
   */
  public final String getLatitudeField() {
    return latitudeField;
  }

  /**
   * Sets the String name of the column that stores the latitude values.
   *
   * @param latitudeField the latitudeField to set
   */
  public final void setLatitudeField(String latitudeField) {
    this.latitudeField = latitudeField;
  }

  /**
   * Gets the String name of the column that stores the longitude or Easting
   * values.
   *
   * @return the longitudeField
   */
  public final String getLongitudeField() {
    return longitudeField;
  }

  /**
   * Sets the String name of the column that stores the longitude or Easting
   * values.
   *
   * @param longitudeField the longitudeField to set
   */
  public final void setLongitudeField(String longitudeField) {
    this.longitudeField = longitudeField;
  }

  /**
   * Gets the String name of the column that stores the identifier for each
   * shape.
   *
   * @return the nameField
   */
  public final String getNameField() {
    return nameField;
  }

  /**
   * Sets the String name of the column that stores the identifier for each
   * shape.
   *
   * @param nameField the nameField to set
   */
  public final void setNameField(String nameField) {
    this.nameField = nameField;
  }

  /**
   * Gets the String password used to access the oracle database.
   *
   * @return the password
   */
  public final String getPassword() {
    return password;
  }

  /**
   * Sets the String password used to access the oracle database.
   *
   * @param password the password to set
   */
  public final void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the String port number that is the four digit port used to connect to
   * oracle.
   *
   * @return the portNumber
   */
  public final String getPortNumber() {
    return portNumber;
  }

  /**
   * Sets the String port number that is the four digit port used to connect to
   * oracle.
   *
   * @param portNumber the portNumber to set
   */
  public final void setPortNumber(String portNumber) {
    this.portNumber = portNumber;
  }

  /**
   * Gets the String name of the server address or localhost where the oracle
   * database is hosted.
   *
   * @return the serverName
   */
  public final String getServerName() {
    return serverName;
  }

  /**
   * Sets the String name of the server address or localhost where the oracle
   * database is hosted.
   *
   * @param serverName the serverName to set
   */
  public final void setServerName(String serverName) {
    this.serverName = serverName;
  }

  /**
   * Gets the String service ID which identifies the scope of data tables to
   * access within oracle.
   *
   * @return the sid
   */
  public final String getSid() {
    return sid;
  }

  /**
   * Sets the String service ID which identifies the scope of data tables to
   * access within oracle.
   *
   * @param sid the sid to set
   */
  public final void setSid(String sid) {
    this.sid = sid;
  }

  /**
   * Gets the String table in the oracle database to connect to that stores the
   * coordinate information.
   *
   * @return the table
   */
  public final String getTable() {
    return table;
  }

  /**
   * Sets the String table in the oracle database to connect to that stores the
   * coordinate information.
   *
   * @param table the table to set
   */
  public final void setTable(String table) {
    this.table = table;
  }

  /**
   * Gets the String username to use to connect to the database.
   *
   * @return the username
   */
  public final String getUsername() {
    return username;
  }

  /**
   * Sets the String username to use to connect to the database.
   *
   * @param username the username to set
   */
  public final void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the filter for the SQL WHERE expression.
   *
   * @return the where
   */
  public final String getWhere() {
    return where;
  }

  /**
   * Sets the filter for the SQL WHERE expression.
   *
   * @param where the where to set
   */
  public final void setWhere(String where) {
    this.where = where;
  }

  //</editor-fold>
}
