<%-- 
    Document   : TestForm
    Created on : Dec 29, 2014, 12:54:44 PM
    Author     : ileung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
        <title>JSP Page</title>
        <style>
            .accordion {
                margin:1em 0
            }
            .accordion h3 {
                background:url('http://www.epa.gov/region1/images2/misc/accordion-default.jpg') no-repeat right top #559b6a;
                color:#fff;
                cursor:pointer;
                margin:0;
                padding:4px 10px;
                width: 560px;
            }
            .accordion h3.current {
                background:url('http://www.epa.gov/region1/images2/misc/accordion-active.jpg') no-repeat right top #4289aa;
                cursor:default;
                width: 560px;
            }
            .accordion div.pane {
                padding:5px 10px
            }
            #step1, #step2, #step3{
                margin:2px;
                width: auto;
                height: auto;
                background: #65a9d7;
                background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#65a9d7));
                background: -webkit-linear-gradient(top, #3e779d, #65a9d7);
                background: -moz-linear-gradient(top, #3e779d, #65a9d7);
                background: -ms-linear-gradient(top, #3e779d, #65a9d7);
                background: -o-linear-gradient(top, #3e779d, #65a9d7);
                padding: 0px 10px;
                -webkit-border-radius: 8px;
                -moz-border-radius: 8px;
                border-radius: 8px;
                -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;
                -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;
                box-shadow: rgba(0,0,0,1) 0 1px 0;
                text-shadow: rgba(0,0,0,.4) 0 1px 0;
                color: white;
                font-size: 20px;
                font-family: Georgia, serif;
                text-decoration: none;
                vertical-align: middle;                
            }  
            
            #text1{
                position: inherit;
                left: 20px;
                top: -22px;                
            }
            #text2{
                position:fixed;
                left: 42px;
                top: 111px;                
            }
            #text3{
                position:fixed;
                left: 42px;
                top: 129px;                
            }

        </style>
        <script>
            $(function () {
                //  Accordion Panels
                $(".accordion div").hide();   
                $(".accordion h3").click(function () {
                    $(this).next(".pane").slideToggle().siblings(".pane:visible").slideUp();
                    $(this).toggleClass("current");
                    $(this).siblings("h3").removeClass("current");
                });
            });
        </script>
    </head>
    <body>
        <div class="accordion">
            <h3>Step 1: Select Area of Interest</h3>
            <div class="pane">
                <!--<div class="buttonType" data-dojo-type="dijit/layout/ContentPane">-->
                       <button data-dojo-type="dijit/form/Button">Extent</button>
                       <button data-dojo-type="dijit/form/Button">Polygon</button>
                       <button data-dojo-type="dijit/form/Button">Freehand Polygon</button>        
                       <button id="uploadShapeFile" data-dojo-type="dijit/form/Button">Upload ShapeFile</button>
                <!--</div>-->
            </div>
            <h3>Step 2: Select Product Type</h3>

            <div class="pane">                
                <span class="roundedOne"  style="display: inline-flex">               
                    <input type="checkbox" value="None" id="ortho" name="check">                    
                    <span>Orthophoto Aerial Imagery</span>
                </span>
                <br>
                <span class="roundedOne2">
                    <input type="checkbox" value="None" id="initLidar" name="check" />                    
                    <span>Initial Post-Processed LiDAR</span>
                </span>
                <br>
                <span class="roundedOne3">
                    <input type="checkbox" value="None" id="finalLidar" name="check" />                   
                    <span>Final Post-Processed LiDAR</span>
                </span>    
                <br>
                <span class="roundedOne3">
                    <input type="checkbox" value="None" id="bathymetric" name="check" />                    
                    <span>Bathymetric and Field Surveys</span>
                </span>                                               
            </div>
            <h3>Step 3: Enter Contact Information</h3>

            <div class="pane">                        
                <span id="contactInformation">Contact Information</span>
                <table id="userData">
                    <tr class="tableRow">
                        <th>Organization</th><td colspan='5'><input class="fullRow" type="text" id="organizationField" placeholder=""/></td>
                    </tr>
                    <tr class="tableRow">
                        <th>First Name</th><td colspan='2'><input class="fullRow" type="text" id="firstNameField" placeholder=""/></td>
                        <th>Last Name</th><td colspan='2'><input class="fullRow" type="text" id="lastNameField" placeholder=""/></td>
                    </tr>
                    <tr class="tableRow">
                        <th>Email</th><td colspan='2'><input class="fullRow" type="text" id="emailField" placeholder=""/></td>
                        <th>Phone Number</th><td colspan='2'><input class="fullRow" type="text" id="phoneNumberField" placeholder=""/></td>                                        
                    </tr> 
                    <tr class="tableRow">
                        <th>Address</th><td colspan='5'><input class="fullRow" type="text" id="addressField" placeholder=""/></td>
                    </tr>
                    <tr class="tableRow">
                        <th>City</th><td><input class="fullRow" type="text" id="cityField" placeholder=""/></td>
                        <th>State</th><td ><input class="fullRow" size='2' type="text" id="stateField" placeholder=""/></td>
                        <th>Zip</th><td><input class="fullRow" type="text" id="zipField" placeholder=""/></td>
                    </tr>
                </table>     
                <span id="projectInformation">Project Information</span>
                <table id="userData">
                    <tr class="tableRow">
                        <th>Title</th><td colspan='5'><input class="fullRow" type="text" id="titleField" placeholder=""/></td>
                    </tr>
                    <tr class="tableRow">                    
                        <th>Location</th><td colspan='2'><input class="fullRow" type="text" id="locationField" placeholder=""/></td>
                    </tr>
                    <tr class="tableRow">
                        <th>Reason for LiDAR</th><td colspan='5'><input class="fullRow" type="text" id="reasonField" placeholder=""/></td>                    
                    </tr>
                </table>
                <input type="button" id="submit" value="Submit" onclick="div_submit()"/></label>
                <input type="button" id="cancel" value="Cancel" onclick="div_hide()"/></label> 
            </div>
        </div>
    </body>
</html>
