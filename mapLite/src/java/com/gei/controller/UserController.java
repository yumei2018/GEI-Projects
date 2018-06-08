/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.controller;

import com.gei.entities.AdminReview;
import com.gei.entities.UserRequest;
import com.gei.facades.AdminReviewFacade;
import com.gei.facades.UserRequestFacade;
import com.gei.tools.ExcelUpdater;
import com.gei.tools.FetchInfo;
import com.gei.tools.FetchLidarTiles;
import com.gei.tools.PdfGenerator;
import gov.ca.water.shapelite.Coord;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialClob;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.openide.util.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 * This class is for requesting data using the form
 * @author ileung
 */
@Controller
@RequestMapping("/requestInfo")
public class UserController extends MultiActionController{
    
    @Autowired
    ApplicationContext appContext;
    private String county, river, levee;
    private List<String> finalTiles = new ArrayList<>();
    private List<String> initialTiles = new ArrayList<>();
    private List<String> orthoTiles = new ArrayList<>();
    private List<String> bathyTiles = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
    
    @RequestMapping("/fetch")
    public void list(HttpServletResponse response, HttpServletRequest request) throws SQLException, IOException{
        String type = request.getParameter("activeLidarShape");
        Clob geo = new SerialClob(request.getParameter("geometry").toCharArray());
        String geoType = request.getParameter("geoType");
        List<Coord> coords = parseGeometry(geo, geoType);
        FetchLidarTiles fl = new FetchLidarTiles();
        List<String> coordList = fl.getLidarTiles(coords, type);
        String namesList = "";
        for(String names : coordList) {
            namesList = namesList + names + "<br>";
        }
        response.getWriter().print(namesList);
    }
    
    @RequestMapping("/sendRequest")
    public void sendRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ParseException, EmailException, MessagingException{
        UserRequestFacade urf =  (UserRequestFacade)appContext.getBean(UserRequestFacade.class.getSimpleName());
        UserRequest req = new UserRequest();
        req.setFirstName(request.getParameter("firstName"));
        req.setLastName(request.getParameter("lastName"));
        req.setUserOrganization(request.getParameter("organization"));
        req.setEmail(request.getParameter("email"));
        req.setPhoneNumber(request.getParameter("phoneNumber"));        
        req.setCity(request.getParameter("city"));
        req.setState(request.getParameter("state"));
        req.setZip(request.getParameter("zip"));        
        req.setAddress(request.getParameter("address"));
        req.setGeometryType(request.getParameter("geometryType"));        
        req.setGeometry(new SerialClob(request.getParameter("geometry").toCharArray()));        
        req.setProjectTitle(request.getParameter("projectTitle"));
        req.setProjectLocation(request.getParameter("location"));
        req.setReason(new SerialClob(request.getParameter("reason").toCharArray()));
        String date = request.getParameter("requestDate");       
        if(request.getParameter("requestDate") != null ){
            req.setRequestDate(sdf.parse(request.getParameter("requestDate")));
        }             
        urf.create(req);
        response.getWriter().print(req.getRequestId());  
    }
    
    @RequestMapping("/sendEmails")
    public void sendEmails(HttpServletRequest request, HttpServletResponse response) throws IOException, MalformedURLException, SQLException, EmailException, MessagingException{
        AdminReviewFacade arf = (AdminReviewFacade)appContext.getBean(AdminReviewFacade.class.getSimpleName());        
        AdminReview adm = new AdminReview();
        UserRequestFacade urf = (UserRequestFacade)appContext.getBean(UserRequestFacade.class.getSimpleName());
        UserRequest req = urf.findValue((Long.parseLong(request.getParameter("id"))));          
        Double xmin = Double.parseDouble(request.getParameter("xmin"));
        Double xmax = Double.parseDouble(request.getParameter("xmax"));
        Double ymin = Double.parseDouble(request.getParameter("ymin"));
        Double ymax = Double.parseDouble(request.getParameter("ymax"));
        String[] shapes = request.getParameter("activeLidarShape").replace("[\"", "").replace("\"]","").replace("\"", "").split(",");       
        BufferedImage img = getMap(xmin, xmax, ymin, ymax, req.getGeometry(), req.getGeometryType(), shapes); 
        sendMailUser(req, img); 
        
        //creates the admin entry
        String finalNames= "";
        String initialNames= "";
        String orthoNames= "";
        String bathyNames= "";
        if(!finalTiles.isEmpty()) {
            for(String tile : finalTiles) {
                finalNames = finalNames + tile + "<br>";
            }
        } 
        if(!initialTiles.isEmpty()) {
            for(String tile : initialTiles) {
                initialNames = initialNames + tile + "<br>";
            }
        } 
        if(!orthoTiles.isEmpty()) {
            for(String tile : orthoTiles) {
                orthoNames = orthoNames + tile + "<br>";
            }
        } 
        if(!bathyTiles.isEmpty()) {
            for(String tile : bathyTiles) {
                bathyNames = bathyNames + tile + "<br>";
            }
        } 
        adm.setRequestId(req.getRequestId());
        adm.setFinalLidarList(finalNames.replace("<br>", ","));
        adm.setInitialLidarList(initialNames.replace("<br>", ","));
        adm.setOrthoAerialList(orthoNames.replace("<br>", ","));
        adm.setBathyFieldList(bathyNames.replace("<br>", ","));
        adm.setStatus("Unprocessed");
        adm.setUserRequest(req);
        arf.create(adm);        
        sendMailAdmin(req, img, finalNames, initialNames, orthoNames, bathyNames);
        ExcelUpdater.updateExcel(adm);    
    }
    
    @RequestMapping("/agreement")
    public void genAgreement(HttpServletRequest request, HttpServletResponse response) throws IOException{
        UserRequestFacade urf = (UserRequestFacade)appContext.getBean(UserRequestFacade.class.getSimpleName());
        UserRequest req = urf.findValue((Long.parseLong(request.getParameter("id"))));        
        String name = req.getFirstName() + " " + req.getLastName();
        String agency = req.getUserOrganization();
        String title = req.getProjectTitle();
        Date date = req.getRequestDate();        
        String pdfName = new PdfGenerator().generatePDF(name, title, agency, date);
        response.getWriter().print(pdfName);  
    }
    
    public void sendMailAdmin(UserRequest entity, BufferedImage img, String finalNames, String initialNames, String orthoNames, String bathyNames) throws EmailException, IOException, MessagingException{
        File file = new File("image.png");
        ImageIO.write(img, "png", file);
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
                + "	<label id=\"title\">LiDAR Data Request <br>(Request ID:  " + entity.getRequestId() + ", Request Date:  " + entity.getRequestDate() + ")\n"
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
                + "	<th>Reason for LiDAR</th><td colspan='5'>" + parseClob(entity.getReason()) + "</td>  \n"
                + "	</tr>  \n"
                + "	</table>                  \n"
                + "	\n"
                + "	<div>  \n"
                + "	\n"
                + "	</div>  \n"
                + "	</div>  \n"
                + "     <div id=\"requestInformation\" style=\"background-color:#f57c70\">  \n";
        
        String requestedTiles = "";
        if(!finalNames.equals("")) {
            requestedTiles = requestedTiles + "<label><b><u>Final Post-Processed LiDAR File Name List:</u></b></label><br>" + finalNames + "\n";
        }
        if(!initialNames.equals("")) {
            requestedTiles = requestedTiles + "<label><b><u>Initial Post-Processed LiDAR File Name List:</u></b></label><br>" + initialNames + "\n";
        }
        if(!orthoNames.equals("")){
            requestedTiles = requestedTiles + "<label><b><u>Orthophoto Aerial Imagery File Name List:</u></b></label><br>" + orthoNames + "\n";
        }
        if(!bathyNames.equals("")){
            requestedTiles = requestedTiles + "<label><b><u>Bathymetric and Field Surveys File Name List:</u></b></label><br>" + bathyNames + "\n";
        }
        requestedTiles = requestedTiles 
                + "         <label><b><u>Request Area Info</b></u></label><br>\n" 
                + "         <label>Acerage: </label><br>" 
                + "         <label>County: </label>" + county + "<br>" 
                + "         <label>River: </label>" + river + "<br>" 
                + "         <label>Levee: </label>" + levee + "<br>" 
                + "         \n" 
                + "     </div> "                
                + "	</form>  \n"
                + "	</div> \n"
                + "</body>\n"
                + "</html>";
        htmlEmailTemplate = htmlEmailTemplate + requestedTiles;
        HtmlEmail email = new ImageHtmlEmail();        
        email.setHostName("smtp.office365.com");
        email.setAuthentication("ileung@geiconsultants.com", "Intern2014." );        
        email.getMailSession().getProperties().put("mail.smtp.auth", "true");
        email.getMailSession().getProperties().put("mail.debug", "true");
        email.getMailSession().getProperties().put("mail.smtp.port", "587");
        email.getMailSession().getProperties().put("mail.smtp.socketFactory.port", "587");        
        email.getMailSession().getProperties().put("mail.smtp.socketFactory.fallback", "false");
        email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");        
//        email.addTo(request.getParameter("email"), request.getParameter("firstName") + " " + request.getParameter("lastName"));
        email.setFrom("ileung@geiconsultants.com", "Isaac Leung");
        email.addTo("stratelec1@gmail.com", "Isaac Leung");
        email.addTo("stratelec1@yahoo.com", "Isaac Leung");
        email.addTo("ileung@geiconsultants.com", "Isaac Leung");
        email.setHtmlMsg(htmlEmailTemplate);        
        String cid = email.embed(file);
        email.setTextMsg("Your email client does not support  HTML messages");        
        email.send();
    }
    
    public void sendMailUser(UserRequest entity, BufferedImage img) throws EmailException, IOException, MessagingException{
        File file = new File("image.png");
        ImageIO.write(img, "png", file);
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
                + "	<label id=\"title\">LiDAR Data Request <br>(Request ID:  " + entity.getRequestId() + ", Request Date:  " + entity.getRequestDate() + ")\n"
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
                + "	<th>Reason for LiDAR</th><td colspan='5'>" + parseClob(entity.getReason())+ "</td>  \n"
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
//        email.addTo(request.getParameter("email"), request.getParameter("firstName") + " " + request.getParameter("lastName"));
        email.setFrom("ileung@geiconsultants.com", "Isaac Leung");
        email.addTo("stratelec1@gmail.com", "Isaac Leung");
        email.addTo("stratelec1@yahoo.com", "Isaac Leung");
        email.addTo("ileung@geiconsultants.com", "Isaac Leung");
        email.setHtmlMsg(htmlEmailTemplate);        
        String cid = email.embed(file);
        email.setTextMsg("Your email client does not support  HTML messages");        
        email.send();
    }   
        
    public BufferedImage getMap(Double xmin, Double xmax, Double ymin, Double ymax, Clob geometry, String geoType, String[] lidarShape) throws MalformedURLException, IOException, SQLException{
        String url = "http://services.arcgisonline.com/arcgis/rest/services/World_Topo_Map/MapServer/export?bbox=" + (xmin - 500) + "," + (ymin - 500) + "," + (xmax + 500) + "," + (ymax + 500)
                +"&bboxSR=&layers=&layerDefs=&size=&imageSR=&format=png&transparent=false&dpi=&time=&layerTimeOptions=&dynamicLayers=&gdbVersion=&mapScale=&f=image";
        URLConnection connection = new URL(url).openConnection();
        InputStream stream = connection.getInputStream();
        BufferedImage im = ImageIO.read(stream);
//        BufferedImage im = trim(img);
        List<Coord> coords =  parseGeometry(geometry, geoType);
        FetchLidarTiles fetchLidar = new FetchLidarTiles();
        for(String shapeType : lidarShape){
            switch(shapeType) {
                case "lidarDwrOrtho" :
                    orthoTiles = fetchLidar.getLidarTiles(coords, shapeType);
                    break;
                case "lidarDwrInitial" :
                    initialTiles = fetchLidar.getLidarTiles(coords, shapeType);
                    break;
                case "lidarDwrFinal" :
                    finalTiles = fetchLidar.getLidarTiles(coords, shapeType);
                    break;
                case "bathymetric" :
                    bathyTiles = fetchLidar.getLidarTiles(coords, shapeType);
                    break;
            }            
        }
        FetchInfo fetchInfo = new FetchInfo();
        county = fetchInfo.fetchInfo(coords, "county");
        levee = fetchInfo.fetchLevee(coords, "levee");
        river = fetchInfo.fetchRiver(coords, "river");
        addDrawnLayer(im, coords, xmin -500, xmax + 500, ymin - 500, ymax + 500);
        return im;
        
    }
    
    public BufferedImage trim(BufferedImage img) {
        int width  = getTrimmedWidth(img);
        int height = getTrimmedHeight(img);

        BufferedImage newImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = newImg.createGraphics();
        g.drawImage( img, 0, 0, null );
        img = newImg;
        return img;
    }
    private int getTrimmedWidth(BufferedImage img) {
        int height       = img.getHeight();
        int width        = img.getWidth();
        int trimmedWidth = 0;

        for(int i = 0; i < height; i++) {
            for(int j = width - 1; j >= 0; j--) {
                if(img.getRGB(j, i) != Color.WHITE.getRGB() &&
                        j > trimmedWidth) {
                    trimmedWidth = j;
                    break;
                }
            }
        }

        return trimmedWidth;
    }

    private int getTrimmedHeight(BufferedImage img) {
        int width         = img.getWidth();
        int height        = img.getHeight();
        int trimmedHeight = 0;

        for(int i = 0; i < width; i++) {
            for(int j = height - 1; j >= 0; j--) {
                if(img.getRGB(i, j) != Color.WHITE.getRGB() &&
                        j > trimmedHeight) {
                    trimmedHeight = j;
                    break;
                }
            }
        }

        return trimmedHeight;
    }
    
    /**
     * Parses the clob stored in the database and returns a Sring
     * @param clob
     * @return 
     */
    public String parseClob(Clob clob){
        if(clob == null) {
            return ""; 
        }
        try {
            BufferedReader br = new BufferedReader(clob.getCharacterStream());
            StringBuilder sb = new StringBuilder();
            String line;
            while( (line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString().trim();
        } catch (IOException | SQLException ex) {
            Exceptions.printStackTrace(ex);            
            return null;
        }
    }
    
    public List<Coord> parseGeometry(Clob geo, String geoType) throws SQLException, IOException{
        List<Coord> coords = new ArrayList<>();
        String string = geo.toString();        
        BufferedReader br = new BufferedReader(geo.getCharacterStream());        
        StringBuilder sb = new StringBuilder();
        String line;
        while( (line = br.readLine()) != null) {
            sb.append(line);
        }
        String geometry = sb.toString().trim();        
        String[] values = geometry.replaceAll("[\\[\\]]","").split(",");        
        if(geoType.equals("extent")) {
            Double xmin = Double.parseDouble(values[0]);
            Double ymin = Double.parseDouble(values[1]);
            Double xmax = Double.parseDouble(values[2]);
            Double ymax = Double.parseDouble(values[3]);
            coords.add(new Coord(xmin,ymin));
            coords.add(new Coord(xmin,ymax));
            coords.add(new Coord(xmax,ymax));
            coords.add(new Coord(xmax,ymin));
        } else {
            for(int i = 0; i < values.length/2; i++) {
                coords.add(new Coord(Double.parseDouble(values[2*i]), Double.parseDouble(values[2*i + 1])));
            }
        }
        return coords;
    }
    
    public BufferedImage addDrawnLayer(BufferedImage img, List<Coord> coords, Double xmin, Double xmax, Double ymin, Double ymax) {
        int[] xVals = new int[coords.size()];
        int[] yVals = new int[coords.size()];
        for(int i = 0; i < coords.size(); i++){
            Double x = (coords.get(i).X - xmin) * (img.getWidth()/(xmax-xmin));
            Double y = (ymax - coords.get(i).Y) * (img.getWidth()/(ymax-ymin));
            xVals[i] = x.intValue();
            yVals[i] = y.intValue();
        }
        Polygon p = new Polygon(xVals, yVals, coords.size());
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.drawPolygon(p);
        g.setColor(new Color(0, 0, 0, (float) 0.2));
        g.fillPolygon(p);
        
        return img;
    }
}
