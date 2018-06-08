package com.gei.entities;

import com.gei.entities.base.ContactBase;
import entity.core.annotation.EntityDataSource;
import entity.core.annotation.EntityDef;
import entity.core.annotation.EntityTable;

/**
 * This class is auto generated
 */
@EntityDef(
  table = @EntityTable(name = "SGMA_BASINMOD_CONTACT")
  ,datasource = @EntityDataSource(name="SgmaDataSource",user = "sgma",password = "sgma#95670",url = "jdbc:oracle:thin:@sac1v-qaqcdb:1521:orcl")
)
public class Contact extends ContactBase<Contact> {
  /**
   * CREATE CUSTOM BUSINESS LOGIC METHODS HERE
   */
}
  