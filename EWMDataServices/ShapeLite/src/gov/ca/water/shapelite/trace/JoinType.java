/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.shapelite.trace;

/**
 *
 * @author hdunsford
 */
public enum JoinType {
  /**
   * Start of one feature to the start of the other.
   */
  StartStart,
  /**
   * Start of one to the end of the other.
   */
  StartEnd,
  /**
   * The end of the first to the start of the other.
   */
  EndStart,
  /**
   * The end of the first to the end of the second.
   */
  EndEnd
}
