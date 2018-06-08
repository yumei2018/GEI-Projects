/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.dwr.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.gei.entities.EpApplication;
import com.gei.facades.EpApplicationFacade;
import com.gei.utils.ServletRequestUtil;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author ymei
 */
@Controller
public class IndexController extends MultiActionController{
    @Autowired
    ApplicationContext appContext;
    
    @RequestMapping(value="/",method=RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }  
    
    @RequestMapping("/exportJsonFile")
    public void exportJsonFile(HttpServletRequest request, HttpServletResponse response) throws IOException, JsonMappingException, JsonGenerationException
    {
        ServletRequestUtil requestUtil = new ServletRequestUtil(request);
        EpApplicationFacade eaf = (EpApplicationFacade)appContext.getBean(EpApplicationFacade.class.getSimpleName());
        String epNo = requestUtil.getString("epNo");
        List<EpApplication> eps = new ArrayList();
        
        if (!requestUtil.getString("epId").isEmpty()){
            EpApplication ep = eaf.find(requestUtil.getInt("epId"));
            if (ep != null){
                eps.add(ep);
            }
        } else if(!requestUtil.getString("epNo").isEmpty()){
            if (requestUtil.getString("wc").isEmpty()){
                eps = eaf.select("SELECT * FROM EP_APPLICATION WHERE EP_NO='"+epNo+"'", com.gei.entities.EpApplication.class);
            } else {
                eps = eaf.select("SELECT * FROM EP_APPLICATION WHERE EP_NO LIKE '"+epNo+"%'", com.gei.entities.EpApplication.class);
            }
        }        
        
        ObjectMapper mapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        mapper.getSerializationConfig().setDateFormat(dateFormat);
        // Remove NULL attributes
//        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
        // convert ep object to json string, and save to a file
//        mapper.writeValue(new File("c:\\tmp\\dwr_ep.json"), eps); 
        String path = request.getServletContext().getRealPath("WEB-INF");
        mapper.writeValue(new File(path+"/export/dwr_ep.json"), eps); 
        
        //Write back to response
        response.getWriter().write(mapper.writeValueAsString(eps));
    }
}
