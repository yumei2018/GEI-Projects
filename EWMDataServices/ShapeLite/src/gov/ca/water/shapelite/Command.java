/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite;

/**
 * 
 * @author Harold A. Dunsford Jr. Ph.D.
 * @param <E>
 */
public interface Command<E> {
  /**
   * Execute.
   * @param arg The Argument to execute.
   */
  void execute(E arg);
}
