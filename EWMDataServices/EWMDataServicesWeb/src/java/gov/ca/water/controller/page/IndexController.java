package gov.ca.water.controller.page;

import gov.ca.water.context.session.UserContext;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author clay
 */
@Controller
@RequestMapping
public class IndexController {
  
//  @RequestMapping
//  public String indexPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
//    String result = null;
//    try {
//      if (UserContext.getInstance().isLoggedIn()) {
//        result = "index";
//      }
//      else {
//        result = "welcome";
//      }
//    }
//    catch(Exception ex) {
//      ex.printStackTrace();
//      response.sendError(response.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
//    }
//    return result;
//  }
  
  //<editor-fold defaultstate="collapsed" desc="Error Handling Page">
//  @RequestMapping("/error/{errorCode}")
//  public String index(@PathVariable("errorCode") Integer errorCode, Exception ex,ModelMap map,HttpServletRequest request,HttpServletResponse response) {
//    String error = (String) request.getAttribute("javax.servlet.error.message");
//    if (StringUtils.isEmpty(error)) {
//      switch (errorCode){
//        case HttpServletResponse.SC_NOT_FOUND:
//          error="The requested page does not exists!";
//          break;
//        case HttpServletResponse.SC_BAD_REQUEST:
//          error="The requested page url is not valid!";
//          break;
//      }
//    }
//    map.addAttribute("error", error);
//    return "error";
//  }
  //</editor-fold>
}
