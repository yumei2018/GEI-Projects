/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gei.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author ileung
 */
@Controller
public class IndexController extends MultiActionController
{
    @RequestMapping(value="/",method=RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("PageTitle", "Well Search Tool");
        mv.addObject("bodyMessage", "Hello World!");
        mv.addObject("buttonStyle", "background: #65a9d7;\n" +
"                background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#65a9d7));\n" +
"                background: -webkit-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                background: -moz-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                background: -ms-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                background: -o-linear-gradient(top, #3e779d, #65a9d7);\n" +
"                padding: 5px 10px;\n" +
"                -webkit-border-radius: 8px;\n" +
"                -moz-border-radius: 8px;\n" +
"                border-radius: 8px;\n" +
"                -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;\n" +
"                -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;\n" +
"                box-shadow: rgba(0,0,0,1) 0 1px 0;\n" +
"                text-shadow: rgba(0,0,0,.4) 0 1px 0;\n" +
"                color: white;\n" +
"                font-size: 14px;\n" +
"                font-family: Georgia, serif;\n" +
"                text-decoration: none;\n" +
"                vertical-align: middle;");
        return mv;
    }
    
    @RequestMapping("/addresssearch")
    public ModelAndView addresssearch(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView("addresssearch");
        mv.addObject("PageTitle", "Well Search Tool");
        mv.addObject("bodyMessage", "Hello World!");
     
        return mv;
    }
    
    public ModelAndView saverecord(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView("index");
        
        return mv;
    }
    
    public void doseomthig(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.getWriter().write("<table><tr><td></td></tr></table>");
    }
    
    public String dosomething2(HttpServletRequest request, HttpServletResponse response)
    {
        return "index";
    }
}
