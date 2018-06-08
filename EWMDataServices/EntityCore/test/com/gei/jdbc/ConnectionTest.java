/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gei.jdbc;

import com.gei.entities.User;
import entity.core.jdbc.ConnectionContext;
import java.sql.Connection;
import org.junit.Test;

/**
 *
 * @author soi
 */
public class ConnectionTest {
  @Test
  public void conntest() throws Exception{
    Connection conn = ConnectionContext.fetchConnection(User.class);
    
    System.out.println(conn);
  }
}
