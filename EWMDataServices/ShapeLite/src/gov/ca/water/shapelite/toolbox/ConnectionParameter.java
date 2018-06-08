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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 */
public class ConnectionParameter extends Parameter {

    private static final Logger LOGGER = Logger.getLogger(ConnectionParameter.class.getName());
    
    private String connectionName;
    private String userName;
    private String password;
    private String hostname;
    private String port;
    private String sid;
    private ParameterStatus currentStatus;
    
   
    @Override
    public ParameterPanel createPanel() {
        return new ConnectionParameterPanel(this);
    }
    
    public String save(){
        return connectionName + ";" + userName + ";" + password + ";" + hostname + ";" + port + ";" + sid;
    }
    
    /**
     * Parses this parameter value from a single, semi-colon delimited value.
     * @param semicolonDelimited 
     */
    public void parse(String semicolonDelimited){
        String[] parts = semicolonDelimited.split(";", -1);
        connectionName = getPart(parts, 0);
        userName = getPart(parts, 1);
        password = getPart(parts, 2);
        hostname = getPart(parts, 3);
        port = getPart(parts, 4);
        sid = getPart(parts, 5);
        
    }
    
    private String getPart(String[] parts, int index){
        if(parts.length > index){
            return parts[index];
        }
        return null;
    }

    @Override
    public ParameterStatus getStatus() {
        if(this.currentStatus != null){
            return this.currentStatus;
        }
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@" + hostname + ":" + port + ":" + sid, userName, password);
            con.close();
            this.currentStatus = ParameterStatus.Good;
        }catch(ClassNotFoundException | SQLException ex){
            this.setValidationMessage(ex.getMessage());
            this.currentStatus = ParameterStatus.Error;
        }
        return this.currentStatus;        
    }
    
     /**
     * Creates a connection to the database.
     * @return 
     */
    public Connection getConnection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@" + hostname + ":" + port + ":" + sid, userName, password);
            return con;
        }catch(ClassNotFoundException | SQLException ex){
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }
    
    
    @Override
    public String toString(){
        return this.connectionName;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Properties">
   
   

    /**
     * @return the connectionName
     */
    public String getConnectionName() {
        return connectionName;
    }

    /**
     * @param connectionName the connectionName to set
     */
    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
    }
    
    
     // </editor-fold>
    
    
   
    
}
