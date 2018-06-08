/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.core.listener;

import entity.core.jdbc.ConnectionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Charlie Lay
 */
public class MySessionListener implements HttpSessionListener {

  @Override
  public void sessionCreated(HttpSessionEvent hse) {
    // do nothing
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent hse) {
    ConnectionContext.getInstance().closeSessionConnections(hse.getSession().getId());
  }
}
