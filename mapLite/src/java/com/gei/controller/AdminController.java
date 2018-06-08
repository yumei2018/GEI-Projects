/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.controller;

import com.gei.entities.AdminReview;
import com.gei.entities.UserRequest;
import com.gei.facades.AdminReviewFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author ileung
 */
@Controller
@RequestMapping("/requests")
public class AdminController extends MultiActionController{
    @Autowired
    ApplicationContext appContext;
    
    @RequestMapping("/loadAdminJson")
    public void getRequestsJson(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ParseException, EmailException, MessagingException{
        AdminReviewFacade arf = (AdminReviewFacade)appContext.getBean(AdminReviewFacade.class.getSimpleName());
        List<AdminReview> dataList = arf.findAll();       
        String jsonData = "{\"data\":[";
        
        for(AdminReview data : dataList){
            String finalFiles = ""; 
            if(data.getFinalLidarList() != null) {
                finalFiles = data.getFinalLidarList().replace(",", "<br>");
            }
            String initialFiles = "";
            if(data.getInitialLidarList() != null) {
                initialFiles = data.getInitialLidarList().replace(",", "<br>");
            }
            String orthoFiles = "";
            if(data.getOrthoAerialList()!= null) {
                orthoFiles = data.getOrthoAerialList().replace(",", "<br>");
            }
            String bathyFiles = "";
            if(data.getBathyFieldList()!= null) {
                bathyFiles = data.getBathyFieldList().replace(",", "<br>");
            }
            jsonData = jsonData + "{\"requestid\":\"" + data.getRequestId() +  "\",\"firstName\":\"" + data.getUserRequest().getFirstName() + "\",\"lastName\":\"" + data.getUserRequest().getLastName() + 
                    "\",\"status\":\"" + data.getStatus() + "\",\"comments\":\"" + data.getComments() + "\",\"finalFiles\":\"" + finalFiles + "\",\"initialFiles\":\"" + initialFiles
                     + "\",\"orthoFiles\":\"" + orthoFiles + "\",\"bathyFiles\":\"" + bathyFiles + "\"},";
        }     
        jsonData = jsonData.substring(0,jsonData.length()-1);
        jsonData = jsonData + "]}";   
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create(); 
        jsonData = gson.toJson(jsonData);
        jsonData = jsonData.substring(1, jsonData.length()-1).replace("\\\"","\"");
        response.getWriter().write(jsonData);
    }
    
    @RequestMapping("/loadAdmin")
    public void getRequests(HttpServletRequest request, HttpServletResponse response) throws IOException{
        AdminReviewFacade arf = (AdminReviewFacade)appContext.getBean(AdminReviewFacade.class.getSimpleName());
        List<AdminReview> dataList = arf.findAll();       
        String jsonData = "{\"data\":[";
        
        for(AdminReview data : dataList){
            String lidarFiles = "";
            if(data.getLidarList() != null) {
                lidarFiles = data.getLidarList().replace(",", "<br>");
            }

            String line = "_" + data.getRequestId() + "=" + data.getUserRequest().getFirstName() + "=" + data.getUserRequest().getLastName() + "=" + data.getStatus()
                     + "=" + data.getComments() + "=" + lidarFiles;           
            response.getWriter().write(line);
        }     
    }
    
    @RequestMapping("/approve")
    public void approve(HttpServletRequest request, HttpServletResponse response) throws EmailException{
        AdminReviewFacade arf = (AdminReviewFacade)appContext.getBean(AdminReviewFacade.class.getSimpleName());        
        String[] ids = request.getParameter("ids").split(",");
        String comment = request.getParameter("comments");
        for(int i = 0; i < ids.length; i++){
            Long id = Long.parseLong(ids[i]);
            AdminReview data = arf.findValue(id);
            data.setStatus("Approved");
            data.setComments(comment);
            arf.edit(data);
            sendNotification(data, "approved");
        }                
    }
    @RequestMapping("/reject")
    public void reject(HttpServletRequest request, HttpServletResponse response) throws EmailException{
        AdminReviewFacade arf = (AdminReviewFacade)appContext.getBean(AdminReviewFacade.class.getSimpleName());        
        String[] ids = request.getParameter("ids").split(",");
        String comment = request.getParameter("comments");
        for(int i = 0; i < ids.length; i++){
            Long id = Long.parseLong(ids[i]);
            AdminReview data = arf.findValue(id);
            data.setStatus("Rejected");
            data.setComments(comment);
            arf.edit(data);
            sendNotification(data, "rejected");
        }        
    }
    
    public void sendNotification(AdminReview data, String status) throws EmailException{
        data.getUserRequest().getEmail();
        UserRequest entity = data.getUserRequest();
        String htmlEmailTemplate = "<!DOCTYPE html  >\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                + "<meta name=\"viewport\" content=\"width=device-width\"/>\n"
                + "<style>\n"
                + "\n"
                + "#popupForm{position: absolute; top: 10px; left: 10px; background-color: #FFFFFF; display: table; width: 550px;}\n"
                + "#title{margin:2px;width: auto;height: auto;background: #65a9d7;\n"
                + "background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#65a9d7));\n"
                + "background: -webkit-linear-gradient(top, #3e779d, #65a9d7);\n"
                + "background: -moz-linear-gradient(top, #3e779d, #65a9d7);\n"
                + "background: -ms-linear-gradient(top, #3e779d, #65a9d7);\n"
                + "background: -o-linear-gradient(top, #3e779d, #65a9d7);\n"
                + "padding: 0px 10px;\n"
                + "-webkit-border-radius: 8px;\n"
                + "-moz-border-radius: 8px;\n"
                + "border-radius: 8px;\n"
                + "-webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;\n"
                + "-moz-box-shadow: rgba(0,0,0,1) 0 1px 0;\n"
                + "box-shadow: rgba(0,0,0,1) 0 1px 0;\n"
                + "text-shadow: rgba(0,0,0,.4) 0 1px 0;\n"
                + "color: black;\n"
                + "font-size: 20px;\n"
                + "font-family: Georgia, serif;\n"
                + "text-decoration: none;\n"
                + "vertical-align: middle;\n"
                + "display: block;\n"
                + "} \n"
                + "#title{color: white;}\n"
                + "#form{display:block;top: 0px;float: left;width: 550px;background-color: #FFFFFF;\n"
                + "z-index: 10000;padding:0px;margin:0px;border:none;}\n"
                + "#contactInformation, #projectInformation{\n"
                + "                margin:2px;\n"
                + "                width: 30%;\n"
                + "                height: auto;\n"
                + "                background: #65a9d7;\n"
                + "                background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#65a9d7));\n"
                + "                background: -webkit-linear-gradient(top, #3e779d, #65a9d7);\n"
                + "                background: -moz-linear-gradient(top, #3e779d, #65a9d7);\n"
                + "                background: -ms-linear-gradient(top, #3e779d, #65a9d7);\n"
                + "                background: -o-linear-gradient(top, #3e779d, #65a9d7);\n"
                + "                padding: 0px 10px;\n"
                + "                -webkit-border-radius: 8px;\n"
                + "                -moz-border-radius: 8px;\n"
                + "                border-radius: 8px;\n"
                + "                -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;\n"
                + "                -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;\n"
                + "                box-shadow: rgba(0,0,0,1) 0 1px 0;\n"
                + "                text-shadow: rgba(0,0,0,.4) 0 1px 0;\n"
                + "                color: white;\n"
                + "                font-size: 18px;\n"
                + "                font-family: Georgia, serif;\n"
                + "                text-decoration: none;\n"
                + "                vertical-align: middle;\n"
                + "            }            \n"
                + "                 #img{\n"
                + "                 position: absolute;\n"
                + "                 left: 325px;\n"
                + "                 top: 96px;\n"
                + "                 }"
                + "	</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "	<div id=  \"popupForm\"  >  \n"
                + "                     \n"
//                + "     <label> Your request has been: " + status; + "</label>"
                + "	<label id=\"title\">Your request has been: " + status + "<br>LiDAR Data Request <br>(Request ID:  " + entity.getRequestId() + ", Request Date:  " + entity.getRequestDate() + ")\n"
                + "	</label><br>                  \n"
                + "	<form class= \"form\"   id=  \"form\"  >                  \n"
                + "	<div id=  \"contactInformation\"  >Contact Information</div>  \n"
                + "	<table id=  \"userData\"  >  \n"
                + "	<tr class=  \"tableRow\"  >  \n"
                + "	<th>Organization</th><td colspan='5'>" + entity.getUserOrganization() + "</td>  \n"
                + "	</tr>  \n"
                + "	<tr class=  \"tableRow\"  >  \n"
                + "	<th>First Name</th><td colspan='2'>" + entity.getFirstName() + "</td>  \n"
                + "	<th>Last Name</th><td colspan='2'>" + entity.getLastName() + "</td>  \n"
                + "	</tr>  \n"
                + "	<tr class=  tableRow  >  \n"
                + "	<th>Email</th><td colspan='2'>" + entity.getEmail() + "</td>  \n"
                + "	<th>Phone Number</th><td colspan='2'>" + entity.getPhoneNumber() + "</td>  \n"
                + "	</tr>   \n"
                + "	<tr class=  \"tableRow\"  >  \n"
                + "	<th>Address</th><td colspan='5'>" + entity.getAddress() + "</td>  \n"
                + "	</tr>  \n"
                + "	<tr class=  \"tableRow\"  >  \n"
                + "	<th>City</th><td>" + entity.getCity() + "</td>  \n"
                + "	<th>State</th><td >" + entity.getState() + "</td>  \n"
                + "	<th>Zip</th><td>" + entity.getZip() + "</td>  \n"
                + "	</tr>  \n"
                + "	</table>       \n"
                + "	<div id=  \"projectInformation\"  >Project Information</div>  \n"
                + "	<table id=  \"userData\"  >  \n"
                + "	<tr class=  \"tableRow\"  >  \n"
                + "	<th>Title</th><td colspan='5'>" + entity.getProjectTitle() + "</td>  \n"
                + "	</tr>  \n"
                + "	<tr class=  \"tableRow\"  >                      \n"
                + "	<th>Location</th><td colspan='2'>" + entity.getProjectLocation() + "</td>  \n"
                + "	</tr>  \n"
                + "	<tr class=  \"tableRow\"  >  \n"
                + "	<th>Reason for LiDAR</th><td colspan='5'>" + entity.getUserOrganization() + "</td>  \n"
                + "	</tr>  \n"
                + "	</table>                  \n"
//                + "     <img id =\"img\" src = \"data:image/png;base64," + result + "\"/>"
                + "	\n"
                + "	<div>  \n"
                + "	\n"
                + "	</div>  \n"
                + "	</div>  \n"
                + "	</form>  \n"
                + "	</div> \n"
                + "</body>\n"
                + "</html>";
        
        HtmlEmail email = new ImageHtmlEmail();        
        email.setHostName("smtp.office365.com");
        email.setAuthentication("ileung@geiconsultants.com", "Intern2014." );        
        email.getMailSession().getProperties().put("mail.smtp.auth", "true");
        email.getMailSession().getProperties().put("mail.debug", "true");
        email.getMailSession().getProperties().put("mail.smtp.port", "587");
        email.getMailSession().getProperties().put("mail.smtp.socketFactory.port", "587");        
        email.getMailSession().getProperties().put("mail.smtp.socketFactory.fallback", "false");
        email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");       
        email.setSubject("Status of LiDAR Data Request");
//        email.addTo(request.getParameter("email"), request.getParameter("firstName") + " " + request.getParameter("lastName"));
        email.setFrom("ileung@geiconsultants.com", "Isaac Leung");
        email.addTo(entity.getEmail(), "Isaac Leung"); 
        email.addTo("ileung@geiconsultants.com", "Isaac Leung");
        email.setHtmlMsg(htmlEmailTemplate);                
        email.setTextMsg("Your email client does not support  HTML messages");        
        email.send();        
    }
    
    @RequestMapping("/genBatchFile")
    public void generateBatchFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long requestId = Long.parseLong(request.getParameter("id"));
        String type = request.getParameter("type");
        String dest = request.getParameter("dest");
        File file = null;        
        AdminReviewFacade arf = (AdminReviewFacade)appContext.getBean(AdminReviewFacade.class.getSimpleName());
        AdminReview adm;
        adm = arf.findValue(requestId);
        List<String> list = null;
        switch(type){
            case "final" :
                list = parseString(adm.getFinalLidarList());
                file = new File("C:\\Users\\ileung\\Documents\\Test2\\Files\\export\\Final_LiDAR_" + requestId + ".bat");
                break;
            case "initial" : 
                list = parseString(adm.getInitialLidarList());
                file = new File("C:\\Users\\ileung\\Documents\\Test2\\Files\\export\\Initial_LiDAR_" + requestId + ".bat");
                break;
            case "ortho" :
                list = parseString(adm.getOrthoAerialList());
                file = new File("C:\\Users\\ileung\\Documents\\Test2\\Files\\export\\OrthoPhoto_Aerial_Imagery_" + requestId + ".bat");
                break;
            case "bathy" :
                list = parseString(adm.getBathyFieldList());
                file = new File("C:\\Users\\ileung\\Documents\\Test2\\Files\\export\\Bathymetric_" + requestId + ".bat");
                break;
        } 
        if(file != null) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for(String fileName : list){
                bw.write(dest + File.separator + fileName);
                bw.newLine();
            }
            bw.close();
        }
    }
    
    public List<String> parseString(String files) {
        List<String> l = new ArrayList<>();
        String[] fileList = files.split(",");
        for(int i = 0; i < fileList.length; i++){
            l.add(fileList[i]);
        }
        return l;
    }
}
