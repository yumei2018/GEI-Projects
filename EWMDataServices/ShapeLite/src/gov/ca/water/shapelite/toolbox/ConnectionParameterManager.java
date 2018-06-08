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

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ConnectionParameterManager {

  private final List<ConnectionParameter> connections;
  private ConnectionParameter selectedConnection;

  Preferences preferences;

  private ConnectionParameterManager() {
    preferences = Preferences.userRoot();
    this.connections = new ArrayList<>();
    String connectionStrings
        = preferences.get("SHAPELITE_DATABASE_CONNECTIONS", "");
    String[] parts = connectionStrings.split("|");
    for (String part : parts) {
      ConnectionParameter param = new ConnectionParameter();
      param.parse(part);
      if (param.getConnectionName() != null) {
        this.connections.add(param);
      }
    }

  }

  public static ConnectionParameterManager getInstance() {
    return ConnectionParameterManagerHolder.INSTANCE;
  }

  public void addConnection(ConnectionParameter param) {
    this.connections.add(param);
    save();
  }

  public void save() {
    StringBuilder sb = new StringBuilder();
    for (ConnectionParameter param : this.connections) {
      sb.append(param.save());
      sb.append("|");
    }
    preferences.put("SHAPELITE_DATABASE_CONNECTIONS", sb.toString());
  }

  /**
   * @return the connectionStrings
   */
  public List<ConnectionParameter> getConnections() {
    return connections;
  }

  /**
   * @return the selectedConnection
   */
  public ConnectionParameter getSelectedConnection() {
    return selectedConnection;
  }

  /**
   * @param selectedConnection the selectedConnection to set
   */
  public void setSelectedConnection(ConnectionParameter selectedConnection) {
    this.selectedConnection = selectedConnection;
  }

  private static class ConnectionParameterManagerHolder {

    private static final ConnectionParameterManager INSTANCE = new ConnectionParameterManager();
  }
}
