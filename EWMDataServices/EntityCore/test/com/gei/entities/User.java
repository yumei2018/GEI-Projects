/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gei.entities;

import com.gei.entities.base.UserBase;
import entity.core.annotation.*;
import java.util.List;

/**
 *
 * @author Charlie Lay
 */
@EntityDef(
  table = @EntityTable(name = "SGMA_BASINMOD_USER")
  ,datasource = @EntityDataSource(name="SgmaDataSource",user = "sgma",password = "sgma#95670",url = "jdbc:oracle:thin:@sac1v-qaqcdb:1521:orcl")
)
public class User extends UserBase<User> {
  @EntityJoin(table = @EntityTable()
    ,column = @EntityColumn(name = "CONTACT_ID",reference = "CONTACT_ID"))
  Contact mContact;
  
  @EntityJoin(table = @EntityTable(name="")
    ,column = @EntityColumn(name="CONTACT_ID",reference = "CONTACT_ID"),lazy=true)
  List<Initrequest> mRequests;
  
  /**
   * 
   * @return 
   */
  public Contact getContact(){
    return this.mContact;
  }
  
  /**
   * 
   * @return 
   */
  public List<Initrequest> getRequests(){
    if (this.mRequests == null) {
      this.loadJoin("mRequests",true);
    }
    return this.mRequests;
  }
}
