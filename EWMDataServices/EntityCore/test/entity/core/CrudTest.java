/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.core;

import com.gei.entities.Initrequest;
import com.gei.entities.User;
import entity.core.delegate.UpdateDelegate;
import entity.core.filter.*;
import entity.core.filter.ConditionFilter.ConditionOperator;
import static entity.core.filter.ConditionFilter.ConditionOperator.*;
import entity.core.jdbc.ConnectionContext;
import entity.core.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author soi
 */
public class CrudTest {
  @Test
  public void selecttest(){
    User user = new User();
    user.setUserId(ConditionOperator.SQL_EQUAL, 21l).select();
    System.out.println(user.getQuerySuccess());
    System.out.println(user.recordFound());
    System.out.println(user);
    System.out.println(user.getContact());
    List<Initrequest> requests = null;
    if ((requests = user.getRequests()) != null) {
      for (Initrequest ir : user.getRequests()) {
        System.out.println(ir);
      }
    }
    else {
      System.out.println("No requests");
    }
  }
  
  @Test
  public void deletetest(){
    User user = new User();
    user.setUserId(1l).select().delete();
    System.out.println("\n");
    System.out.println("Query success: " + user.getQuerySuccess());
    System.out.println("Record Found: " + user.recordFound());
    System.out.println("User: " + user);
    user.commitTransaction();
  }
  
  @Test
  public void updatetest(){
    User user = new User();
    user.setUserId(ConditionOperator.SQL_EQUAL,1l).select();
    System.out.println("\n");
    System.out.println("User: " + user);
    user
    .setUsername("cla2y")
    .setPassword("test")
    .setUserType("some")
    .setActive(null)
    .setLastloginDatetime(DateUtil.getNowAsTimestamp())
    .setLoginCount(null)
    .setRecoveryHash("yes")
    .setRecoveryDt(DateUtil.getNowAsTimestamp())
    .update();
    System.out.println("Query success: " + user.getQuerySuccess());
    System.out.println("Record Found: " + user.recordFound());
    System.out.println("User: " + user);
    user.commitTransaction();
  }
  
  @Test
  public void inserttest(){
    User user = new User();
//    user.setUserId(ConditionOperator.SQL_EQUAL,1l).select();
    System.out.println("\n");
    System.out.println("User: " + user);
    user
    .setUsername("claytest")
    .setPassword("testtest")
    .setUserType("sometest")
    .setActive(null)
    .setLastloginDatetime(DateUtil.getNowAsTimestamp())
    .setLoginCount(null)
    .setRecoveryHash("yestest")
    .setRecoveryDt(DateUtil.getNowAsTimestamp())
    .insert();
    System.out.println("Query success: " + user.getQuerySuccess());
    System.out.println("Record Found: " + user.recordFound());
    System.out.println("User: " + user);
    user.commitTransaction();
  }
  
  @Test
  public void inserttest2() throws Exception {
    String query = "INSERT INTO TEST_TABLE (COL1,COL4) VALUES (?,?)";
    List<Object> params = new ArrayList<>();
    params.add("CUSTOM2");
    params.add(DateUtil.getNowAsTimestamp());
    java.sql.Connection conn = ConnectionContext.fetchConnection(User.class);
    EntityFacade.executeUpdate(conn, query, params, new UpdateDelegate(query) {
      @Override
      public void handle(Integer i) throws Exception {
        if (i != 1) {
          throw new Exception("Zero or more than one record(s) affected!");
        }
      }
    });
  }
  
  @Test
  public void findall(){
    User user = new User();
    List<User> users = user.findAll(/*ColumnFilter.createInstance("userId")
                                    , ConditionFilter.createInstance("password", ConditionOperator.SQL_EQUAL, "55634844acc7504ab22a671ce98ba85f")/**/);
    for (User u : users) {
      System.out.println(u.toMap());
    }
  }
  
  @Test
  public void equalstest(){
    User user = new User(),user2 = new User();
    user.setUserId(SQL_EQUAL, 1l).select();
    user.setUsername("etst");
    user2.setUserId(SQL_EQUAL, 1l).select();
    System.out.println(user.equals(user2));
  }
  
  @Test
  public void pagetest(){
    User user = new User();
    user.setPassword(SQL_EQUAL, "55634844acc7504ab22a671ce98ba85f");
    List<User> users = user.findPage(1, 2,SortFilter.createInstance("userId"));
    System.out.println("Total Records: " + user.getTotalRecordCount());
    for (User u : users){
      System.out.println(u);
    }
  }
}
