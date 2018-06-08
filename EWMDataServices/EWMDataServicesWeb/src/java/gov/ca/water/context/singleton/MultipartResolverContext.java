/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.ca.water.context.singleton;

import entity.core.util.WebUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 *
 * @author Charlie Lay
 */
public class MultipartResolverContext implements Serializable {

  //<editor-fold defaultstate="collapsed" desc="Private Properties">
  /**
   * 
   */
  private static MultipartResolverContext mCtx;
  
  /**
   * 
   */
  private CommonsMultipartResolver mResolver;
  //</editor-fold>
    
  //<editor-fold defaultstate="collapsed" desc="Constructor(s)">
  /**
   * 
   */
  public MultipartResolverContext(){
    this.initResolver();
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Finalize Override">
  @Override
  protected void finalize() throws Throwable {
    super.finalize(); //To change body of generated methods, choose Tools | Templates.
    this.mResolver = null;
    MultipartResolverContext.mCtx = null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Private Initialize Multipart Resolver">
  /**
   * 
   * @return 
   */
  private MultipartResolverContext initResolver(){
    HttpServletRequest request = null;
    ServletContext context = null;
    if (((request = WebUtil.getRequest()) != null)
        && ((context = request.getServletContext()) != null)) {
      if (this.mResolver == null) {
        this.mResolver = new CommonsMultipartResolver(context);
      }
    }
    
    if (this.mResolver == null){
      throw new NullPointerException("The spring multipart resolver cannot be unassigned!");
    }
    
    return this;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Static Get Instance">
  /**
   * 
   * @return 
   */
  public static MultipartResolverContext getInstance(){
    if (MultipartResolverContext.mCtx == null) {
      MultipartResolverContext.mCtx = new MultipartResolverContext();
    }
    return MultipartResolverContext.mCtx;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Public Getters">
  /**
   * 
   * @param paramName
   * @return MultipartFile
   */
  public MultipartFile getMultipartFile(String paramName){
    MultipartFile result = null;
    MultipartHttpServletRequest mprequest = null;
    if (((mprequest = this.getMultipartRequest()) != null)) {
      result = mprequest.getFile(paramName);
    }
    return result;
  }
  
  /**
   * 
   * @param paramName
   * @return List<MultipartFile>
   */
  public List<MultipartFile> getMultipartFiles(String paramName){
    List<MultipartFile> result = null;
    MultipartHttpServletRequest mprequest = null;
    if (((mprequest = this.getMultipartRequest()) != null)) {
      result = mprequest.getFiles(paramName);
    }
    return result;
  }
  
  /**
   * 
   * @return Collection<MultipartFile>
   */
  public Collection<MultipartFile> getMultipartFiles(){
    Collection<MultipartFile> result = null;
    MultipartHttpServletRequest mprequest = null;
    if (((mprequest = this.getMultipartRequest()) != null)
        && ((mprequest.getFileMap().size() > 0))) {
      result = mprequest.getFileMap().values();
    }
    return result;
  }

  /**
   * 
   * @return MultipartHttpServletRequest
   */
  public MultipartHttpServletRequest getMultipartRequest(){
    if (this.mResolver == null){
      throw new NullPointerException("The spring multipart resolver cannot be unassigned!");
    }
    HttpServletRequest request = null;
    MultipartHttpServletRequest result = null;
    if (((request = WebUtil.getRequest())!=null)
        && ((result = this.mResolver.resolveMultipart(request)) == null)) {
      throw new NullPointerException("The http request is not a multipart request!");
    }
    return result;
  }
  //</editor-fold>
}
