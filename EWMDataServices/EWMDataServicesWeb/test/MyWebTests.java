
import entity.core.delegate.JsonDelegate;
import entity.core.filter.ConditionFilter;
//import gov.ca.water.entity.User;
//import gov.ca.water.entity.collection.UserCollection;
import org.json.JSONObject;
import org.junit.Test;

/**
 *
 * @author clay
 */
public class MyWebTests {
  
  @Test
  public void jsonDelegateTest(){
//    User u = new User();
//    u.setUserId(ConditionFilter.ConditionOperator.SQL_EQUAL, 1).select();
//    System.out.println(u.toJson(new JsonDelegate() {
//      @Override
//      public void handle(JSONObject json) throws Exception {
//        User u = this.getListener();
//        json.put("profile",u.getContact().toJson());
//      }
//    }).toString(4));
  }
  
  @Test
  public void jsonDelegateCollectionTest(){
//    UserCollection u = new UserCollection();
//    u.findAll(ConditionFilter.createInstance("userId", ConditionFilter.ConditionOperator.SQL_LT, 3));
//    System.out.println(u.toJson(new JsonDelegate() {
//      @Override
//      public void handle(JSONObject json) throws Exception {
//        User u = this.getListener();
//        json.put("profile", u.getContact().toJson());
//      }
//    }).toString(4));
  }
}
