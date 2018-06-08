package gov.ca.water.entity.ewm.collection;

import entity.core.EntityCollection;
import entity.core.filter.ConditionFilter;
import entity.core.util.EntityUtil;
import gov.ca.water.entity.ewm.Organization;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author clay
 */
public class OrganizationCollection extends EntityCollection<Organization> {
  
  //<editor-fold defaultstate="expanded" desc="Private Properties">

  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  public OrganizationCollection() {
    super(Organization.class);
  }
  //</editor-fold>
  
//  public Organization getOrganization(Long orgId) {
//    Organization result = null;
//    if (!this.isEmpty()){
//      for (Organization o : this){
//        if (Objects.equals(o.getOrgId(),orgId)){
//          result = o;
//          break;
//        }
//      }
//    }
//    return result;
//  }  
  
  public OrganizationCollection loadById(Long orgId){
    String query = "SELECT * FROM " + EntityUtil.getTableName(Organization.class) +
      " WHERE ORG_ID=? ";
    return this.runQuery(query, Arrays.asList(orgId));
  }
  
}