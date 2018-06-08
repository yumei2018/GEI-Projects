/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.ca.water.entity;

/**
 *
 * @author schen
 */
public class Wrapper {
  
  private final String tableName;
  private final String columnName;
  
  public Wrapper(String tableName, String columnName){
    this.tableName = tableName;
    this.columnName = columnName;
  }
  
  public String getTableName() { 
    return this.tableName; 
  }
  
  public String getColumnName() { 
    return this.columnName; 
  }
}
