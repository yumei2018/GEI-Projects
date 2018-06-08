/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author ileung
 */
@Controller
@RequestMapping("/sendEmail")
public class SendMail extends MultiActionController {
    @RequestMapping("/send")
    public void sendMailAdmin(HttpServletRequest request, HttpServletResponse response) throws EmailException{
//        String htmlEmailTemplate = 
//                "<div id=\"popupForm\">\n" +
//                "    <label id=\"step1\">LiDAR Data Request" + 
//                "       <label>(Request ID: " + ", Request Date: " + ")</label><br> " +                
//                "    <form class=\"form\" id=\"form\">\n" +                
//                "            <div id=\"contactInformation\">Contact Information</div>\n" +
//                "            <table id=\"userData\">\n" +
//                "                <tr class=\"tableRow\">\n" +
//                "                    <th>Organization</th><td colspan='5'>" + "</td>\n" +
//                "                </tr>\n" +
//                "                <tr class=\"tableRow\">\n" +
//                "                    <th>First Name</th><td colspan='2'>" + "</td>\n" +
//                "                    <th>Last Name</th><td colspan='2'>" + "</td>\n" +
//                "                </tr>\n" +
//                "                <tr class=\"tableRow\">\n" +
//                "                    <th>Email</th><td colspan='2'>" + "</td>\n" +
//                "                    <th>Phone Number</th><td colspan='2'>" + "</td>\n" +
//                "                </tr> \n" +
//                "                <tr class=\"tableRow\">\n" +
//                "                    <th>Address</th><td colspan='5'>" + "</td>\n" +
//                "                </tr>\n" +
//                "                <tr class=\"tableRow\">\n" +
//                "                    <th>City</th><td>" + "</td>\n" +
//                "                    <th>State</th><td >" + "</td>\n" +
//                "                    <th>Zip</th><td>" + "</td>\n" +
//                "                </tr>\n" +
//                "            </table>     \n" +
//                "            <div id=\"projectInformation\">Project Information</div>\n" +
//                "            <table id=\"userData\">\n" +
//                "                <tr class=\"tableRow\">\n" +
//                "                    <th>Title</th><td colspan='5'>" + "</td>\n" +
//                "                </tr>\n" +
//                "                <tr class=\"tableRow\">                    \n" +
//                "                    <th>Location</th><td colspan='2'>" + "</td>\n" +
//                "                </tr>\n" +
//                "                <tr class=\"tableRow\">\n" +
//                "                    <th>Reason for LiDAR</th><td colspan='5'>" + "</td>\n" +
//                "                </tr>\n" +
//                "            </table>\n" +                
//                "            \n" +
//                "            <div>\n" +
//                "                \n" +
//                "            </div>\n" +
//                "        </div>\n" +
//                "    </form>\n" +
//                "</div>"
//                ;
        String htmlEmailTemplate = "<!DOCTYPE html  >\n" +
"<html>\n" +
"<head>\n" +
"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"	<meta name=\"viewport\" content=\"width=device-width\"/>\n" +
"	<style>\n" +
"\n" +
"#popupForm{\n" +
"                position: absolute;\n" +
"                top: 10px;\n" +
"                left: 10px;                \n" +
"                background-color: #FFFFFF;\n" +
"                display: table;\n" +
"                width: 550px;\n" +
"            }\n" +
"#title{\n" +
"                margin:2px;\n" +
"                width: auto;\n" +
"                height: auto;\n" +
"                background: #65a9d7;\n" +
"                background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#65a9d7));\n" +
"                background: -webkit-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                background: -moz-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                background: -ms-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                background: -o-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                padding: 0px 10px;\n" +
"                -webkit-border-radius: 8px;\n" +
"                -moz-border-radius: 8px;\n" +
"                border-radius: 8px;\n" +
"                -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;\n" +
"                -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;\n" +
"                box-shadow: rgba(0,0,0,1) 0 1px 0;\n" +
"                text-shadow: rgba(0,0,0,.4) 0 1px 0;\n" +
"                color: black;\n" +
"                font-size: 20px;\n" +
"                font-family: Georgia, serif;\n" +
"                text-decoration: none;\n" +
"                vertical-align: middle;   \n" +
"				display: block;				\n" +
"            } \n" +
"#title{\n" +
"	color: white;			\n" +
"	}\n" +
"#form{\n" +
"                display:block;\n" +
"                top: 0px;\n" +
"                float: left;\n" +
"                width: 550px;\n" +
"                background-color: #FFFFFF;                \n" +
"                z-index: 10000;\n" +
"                padding:0px;\n" +
"                margin:0px;\n" +
"                border:none;\n" +
"            }\n" +
"#contactInformation, #projectInformation{\n" +
"                margin:2px;\n" +
"                width: 30%;\n" +
"                height: auto;\n" +
"                background: #65a9d7;\n" +
"                background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#65a9d7));\n" +
"                background: -webkit-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                background: -moz-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                background: -ms-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                background: -o-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                padding: 0px 10px;\n" +
"                -webkit-border-radius: 8px;\n" +
"                -moz-border-radius: 8px;\n" +
"                border-radius: 8px;\n" +
"                -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;\n" +
"                -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;\n" +
"                box-shadow: rgba(0,0,0,1) 0 1px 0;\n" +
"                text-shadow: rgba(0,0,0,.4) 0 1px 0;\n" +
"                color: white;\n" +
"                font-size: 18px;\n" +
"                font-family: Georgia, serif;\n" +
"                text-decoration: none;\n" +
"                vertical-align: middle;       \n" +
"            }            \n" +
"	</style>\n" +
"</head>\n" +
"<body>\n" +
"	<div id=  \"popupForm\"  >  \n" +
"                     \n" +
"							<label id=\"title\">LiDAR Data Request <br>(Request ID:  , Request Date:  )\n" +
"							</label><br>                  \n" +
"							<form class= \"form\"   id=  \"form\"  >                  \n" +
"								<div id=  \"contactInformation\"  >Contact Information</div>  \n" +
"									<table id=  \"userData\"  >  \n" +
"										<tr class=  \"tableRow\"  >  \n" +
"											<th>Organization</th><td colspan='5'> </td>  \n" +
"										</tr>  \n" +
"										<tr class=  \"tableRow\"  >  \n" +
"											<th>First Name</th><td colspan='2'> </td>  \n" +
"											<th>Last Name</th><td colspan='2'> </td>  \n" +
"										</tr>  \n" +
"										<tr class=  tableRow  >  \n" +
"											<th>Email</th><td colspan='2'> </td>  \n" +
"											<th>Phone Number</th><td colspan='2'> </td>  \n" +
"										 </tr>   \n" +
"										 <tr class=  \"tableRow\"  >  \n" +
"											 <th>Address</th><td colspan='5'> </td>  \n" +
"										 </tr>  \n" +
"										 <tr class=  \"tableRow\"  >  \n" +
"											 <th>City</th><td> </td>  \n" +
"											 <th>State</th><td > </td>  \n" +
"											 <th>Zip</th><td> </td>  \n" +
"										 </tr>  \n" +
"									 </table>       \n" +
"									 <div id=  \"projectInformation\"  >Project Information</div>  \n" +
"									 <table id=  \"userData\"  >  \n" +
"										 <tr class=  \"tableRow\"  >  \n" +
"											 <th>Title</th><td colspan='5'> </td>  \n" +
"										 </tr>  \n" +
"										 <tr class=  \"tableRow\"  >                      \n" +
"											 <th>Location</th><td colspan='2'> </td>  \n" +
"										 </tr>  \n" +
"										 <tr class=  \"tableRow\"  >  \n" +
"											 <th>Reason for LiDAR</th><td colspan='5'> </td>  \n" +
"										 </tr>  \n" +
"									 </table>                  \n" +
"									   \n" +
"									 <div>  \n" +
"										   \n" +
"									 </div>  \n" +
"							 </div>  \n" +
"						 </form>  \n" +
"					 </div> \n" +
"</body>\n" +
"</html>";
        HtmlEmail email = new ImageHtmlEmail();
        email.setHostName("smtp.office365.com");
        email.setAuthentication("ileung@geiconsultants.com", "Intern2013." );        
        email.getMailSession().getProperties().put("mail.smtp.auth", "true");
        email.getMailSession().getProperties().put("mail.debug", "true");
        email.getMailSession().getProperties().put("mail.smtp.port", "587");
        email.getMailSession().getProperties().put("mail.smtp.socketFactory.port", "587");        
        email.getMailSession().getProperties().put("mail.smtp.socketFactory.fallback", "false");
        email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");        
//        email.addTo(request.getParameter("email"), request.getParameter("firstName") + " " + request.getParameter("lastName"));
        email.setFrom("ileung@geiconsultants.com", "Isaac Leung");
        email.addTo("stratelec1@yahoo.com", "Isaac Leung");        
        email.setHtmlMsg(htmlEmailTemplate);
        email.setTextMsg("Your email client does not support  HTML messages");        
        email.send();
    }   
}
