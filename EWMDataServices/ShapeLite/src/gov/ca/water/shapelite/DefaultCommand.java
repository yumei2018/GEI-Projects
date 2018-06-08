/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite;

/**
 *
 * @author Harold A. Dunsford Jr. Ph.D.
 * @param <E> The argument of the event.
 */
public class DefaultCommand<E> implements Command<E> {

  /**
   * Fires the execute method.  This should be overridden in subclasses.
   * @param arg The arguments to pass to the execute method.
   */
  @Override
  public void execute(E arg) {

  }

}
