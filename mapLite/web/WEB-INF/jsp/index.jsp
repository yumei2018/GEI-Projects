<%@include file="templates/header.jsp" %>
<!--<div class="body-content">${bodyMessage}</div>-->



<!-- This is the map component -->
<div data-dojo-type="dijit/layout/BorderContainer"
     data-dojo-props="design:'headline', gutters:false"
     style="height: 100%; margin: 0px; padding: 0px;">
    <div class="center" id="loadingimage">
        <img src="${pageContext.request.contextPath}/resources/images/loading.gif">
    </div>

<!--          -->
    <div id="leftColumn"
          data-dojo-type="dijit/layout/TabContainer"
          data-dojo-props="region:'left'"
         style="width: 340px; height: 100%; padding: 0;">
    </div>

    <div id="map"
         data-dojo-type="dijit/layout/ContentPane"
         data-dojo-props="region:'center'"
         style="padding: 0;">

<!--        <div style="position: absolute; left: 20px; top: 225px; z-index: 999;">
            <div id="basemapButtonTitle" data-dojo-type="dijit/TitlePane"
                 data-dojo-props="title:'', closable:false, open:false">
                <div data-dojo-type="dijit/layout/ContentPane" style="width:380px; height:280px; overflow:auto;">
                    <div id="basemapGallery"></div>
                </div>
            </div>
        </div>-->

        <div id="searchContainer">
            <div class="input-group">
                <input type="text" id="searchtext" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                    <button class="btn btn-default" id="submitSearch" type="button"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                </span>
            </div>
        </div>

        <div id="toggleLeftPanel">
            <button type="button" class="btn btn-default" data-toggle="dropdown" aria-expanded="false">&#9654;</button>
        </div>

<!--        If using contours again, uncomment contours/params ajax call-->
        <div id="toggleToolsBtn">
            <button  type="button" class="btn btn-default" data-toggle="dropdown" aria-expanded="false">
                <span class="glyphicon glyphicon-wrench"></span>
            </button>
        </div>

        <div id="toggleSearchWinBtn">
            <button  type="button" class="btn btn-default" data-toggle="dropdown" aria-expanded="false">
                <span class="glyphicon glyphicon-search"></span>
            </button>
        </div>
<!--        <button id="lidarRequestMenu" onclick="div_show()">Create Contours</button>             -->
    </div>
</div>

<!--New Query popupform using accordian-->
<div id="form">
    <div class="pane">
        <ol>
            <li>Select Wells:
                <button data-dojo-type="dijit/form/Button">Define Area</button>
                <button style='cursor:pointer' id="clearGeometry">Clear Area</button>
            </li>
            <li>
                <span>Select a Year: </span>
                <select id="year" placeholder="Select size...">
                </select>
                <br>
                <span>Select a Season: </span>
                <select id="season" placeholder="Select size...">
                    <option value='Spring'>Spring</option>
                    <option value='Fall'>Fall</option>
                </select>
            </li>
            <li>(Optional) <br>
                <span>Select a Year: </span>
                <select id="year2" placeholder="Select size...">
                </select>
                <br>
                <span>Select a Season: </span>
                <select id="season2" placeholder="Select size...">
                    <option value=''></option>
                    <option value='Spring'>Spring</option>
                    <option value='Fall'>Fall</option>
                </select>
            </li>
        </ol>
        <input type="button" id="viewLegend" value="Legend"/>
        <input type="button" id="viewWells" value="Wells"/>
        <input type="button" id="submitQuery" value="Submit"/>
        <input type="button" id="cancel" value="Cancel" onclick="div_hide()"/>
        <input type="button" id="clearContour" value="Clear"/>
    </div>

</div>
<div id="legend" style="display:none; width:88px; height:auto">

</div>

<!-- Split button -->
<div id="toolsContainer" class="btn-group-vertical" role="group" aria-label="..." style="display: none;">
<!--    <button type="button" id="defineArea"  class="btn btn-default">Define Area</button>
    <button type="button" id="clearArea"  class="btn btn-default">Clear Area</button>-->
<!--    <button type="button" id="downloadWellData" class="btn btn-default">Download Data</button>--> 
<!--    <button type="button" id="uploadWellData" class="btn btn-default">Upload Data</button>-->
    <button type="button" id="showPoints" class="btn btn-default">Show WDL Wells</button>
    <button type="button" id="hidePoints" class="btn btn-default">Hide WDL Wells</button>
    <button type="button" id="graphicsBtn" class="btn btn-default">Graphics</button>
    <button type="button" id="measureBtn" class="btn btn-default">Measure</button>
    <button type="button" id="surfaceProfileBtn" class="btn btn-default">Surface Profile</button>
    <button type="button" id="searchBtn" class="btn btn-default">Search Results</button>
    <button type="button" id="lidarRequestMenu" onclick="div_show()" class="btn btn-default">Create Contours</button>
</div>

<!-- This is the loading screen element -->
<!--<div class="center" id="loadShapes">
    <div class="bouncywrap">

        <div class="dotcon dc1">
        <div class="dot"></div>
        </div>

        <div class="dotcon dc2">
        <div class="dot"></div>
        </div>

        <div class="dotcon dc3">
        <div class="dot"></div>
        </div>

    </div>
</div>-->

<!-- This is the action menu -->

<!--This is the button for downloading data.  It appears when a valid region has been selected -->
<!--<div>
    <button id="downloadButton" onclick="downloadTiles()"> Download</button>
</div>-->

<!-- This is the form for selecting what type of file to download-->
<!--<form id="fileTypeSelection">
    <label for="fileType">Select a file type</label>
        <select name="files" id="files">
            <option value="lidarFormat">LiDAR</option>
            <option value="shapefileFormat">Shapefile</option>
        </select>
    <button id="submitClick" onclick="loadData();">Ok</button>
    <button id="submitCancel"onclick ="cancel();">Cancel</button>
</form>-->

<!-- This is the form for uploading shp, shx, and dbf files -->
 <form id="uploadParam" method="POST" action="#" onsubmit="" enctype="multipart/form-data" style="display: none">
    <div style="">Upload Data</div>
    <div>1. Select the file on your hard drive</div>
    <input type="file" name="excel" id="excel" multiple="multiple"/>
    <div>2. Select Data Type</div>
    <select id="dataType" style="color:black">
        <option>Water Level</option>
        <option>Water Quality</option>
        <option>Well Log</option>
    </select>
    <br>
    <div>3. Select data file format</div>
    <select id="dataFormat" style="color:black">
        <option>Text</option>
        <option>CSV</option>
    </select><br>

    <input type="submit" value="Upload" name="upload" id="upload" style="color:#000000"/>
<!--    <button type="reset" onclick = "cancelUpload()" style="color:#000000">Cancel</button>-->
</form>

<div id="searchResultsDialog" style="display: none">
<!--    <div id="wellsGridContainer"></div>
    <div id="selectWellResults" style="display: inline;">
        <button type="button" class="btn btn-default" data-toggle="dropdown" aria-expanded="false">
            <span>Select</span>
        </button>
    </div>-->
</div>

<!-- This is the form for download parameters-->
<div id="downloadParamContainer">
    <form id="downloadParam" method="POST" action="#" onsubmit="" enctype="multipart/form-data">
        <div style="">Download Data</div>
        <div>1. Select Date Rance</div>
        <div>
            <input type="checkbox" class="dateRange" value="all"/> All Records <br>
            <input type="checkbox" class="dateRange" value="dates"/>
            <div style="position:relative;top:-17px;left:20px">
                <input type="date" name="startDate" id="startDate" style="height:22px;width:115px;"/> <span style="position:relative;right:33px">Start Date</span><br>
                <input type="date" name="endDate" id="endDate" style="height:22px;width:115px;" /> <span style="left:-33px;position:relative">End Date</span>
            </div>
        </div>
        <div>2. Select Output Format</div>
        <div>
            <input type="checkbox" class="downloadFormat" value="excel"/> <span>Excel</span><br>
            <input type="checkbox" class="downloadFormat" value="word"/> <span>Word</span><br>
            <input type="checkbox" class="downloadFormat" value="csv"/> <span>CSV</span><br>
            <input type="checkbox" class="downloadFormat" value="xml"/> <span>XML</span><br>
            <input type="checkbox" class="downloadFormat" value="pdf"/> <span>PDF</span>
        </div>
        <br>
        <!--<input type="submit" value="Download" name="download" id="download" style="color:#000000"/>-->
        <button type="reset" value="Download" name="download" id="download" style="color:#000000">Download</button>
        <button type="reset" onclick = "cancelDownload()" style="color:#000000">Cancel</button>
    </form>
</div>
<!--This form is for selecting which wells to contour and show on map-->
<div id="wellList" style="display: none">
    <div id="wellListBox" style="height:300px;width:190px;border:1px solid #ccc;font:16px/26px Georgia, Garamond, Serif;overflow:auto; padding: 10px;">
    </div>
</div>

<div id="graphicsWindow">
    <div>
        <div class="btn-group" role="group" aria-label="...">
            <button class="btn btn-default" onclick="map.graphicstoolbar.activate(esri.toolbars.Draw.LINE)">Line</button>
            <button class="btn btn-default" onclick="map.graphicstoolbar.activate(esri.toolbars.Draw.POLYLINE)">Polyline</button>
            <button class="btn btn-default" onclick="map.graphicstoolbar.activate(esri.toolbars.Draw.FREEHAND_POLYLINE)">Freehand Polyline</button>
            <button class="btn btn-default" onclick="map.getLayer('userGraphicsLyr').clear();">Clear All</button>
        </div>
    </div>
</div>
<div id="measureWindow">
    <div class="btn-group" role="group" aria-label="...">
        <button class="btn btn-default" onclick="map.measuretoolbar.activate(esri.toolbars.Draw.LINE)">Line</button>
        <button class="btn btn-default" onclick="map.measuretoolbar.activate(esri.toolbars.Draw.POLYLINE)">Polyline</button>
        <button class="btn btn-default" onclick="map.measuretoolbar.activate(esri.toolbars.Draw.FREEHAND_POLYLINE)">Freehand Polyline</button>
        <button id="clearMeasureBtn" class="btn btn-default">Clear All</button>
    </div>
    <div>
        <select id="measurement_units">
            <option value="meter">Meters</option>
            <option value="kilometer">Kilometers</option>
            <option value="feet">Feet</option>
            <option value="mile">Miles</option>
        </select>
        <div id="measure_content"></div>
    </div>
</div>

<div id="searchWindow">
    <div id="resultspanel"></div>
</div>


<div id="surfaceProfileWindow">
    <div class="btn-group" role="group" aria-label="...">
        <button class="btn btn-default" onclick="map.surfaceprofiletoolbar.activate(esri.toolbars.Draw.LINE)">Line</button>
        <button class="btn btn-default" onclick="map.surfaceprofiletoolbar.activate(esri.toolbars.Draw.POLYLINE)">Polyline</button>
        <button class="btn btn-default" onclick="map.surfaceprofiletoolbar.activate(esri.toolbars.Draw.FREEHAND_POLYLINE)">Freehand Polyline</button>
        <button class="btn btn-default" onclick="map.getLayer('surfaceProfileLyr').clear();">Clear All</button>
    </div>
    <div id="surfaceProfile_content">

    </div>
</div>


<div id="searchWellsWindow">
  <div class="row upperhalf step_header">Step 1: Select well using option below.</div>
    <div class='row upperhalf'>
        <table id='polygonSearchTable'>
            <tr>
                <td>
                    <div style="display: inline;">Option 1: ID Search </div><span helptype ="text" class="glyphicon glyphicon-question-sign helpbox"></span>
                    <div style="display: inline;float:right;">
                        <input type="text" id="wellsByNameTextbox" placeholder="Well ID Search..."> <button class="btn btn-default blue" id="wellsByNameBtn">Search</button>
                    </div>

                </td>
            </tr>
            <tr>
                <td>
                    <div style="display: inline;">Option 2: Buffer Search </div>
                    <div style="display: inline; float:right;">
                        <input type="text" id="bufferTextbox"> (mi) <button class="btn btn-default blue" id="bufferSearchBtn">Search</button>
                    </div>
                    <span helptype ="buffer" class="glyphicon glyphicon-question-sign helpbox"></span>
                </td>
            </tr>
            <tr>
                <td>
                    <div style="display: inline;margin-top:10px;">Option 3: Draw a Shape <span helptype ="draw" class="glyphicon glyphicon-question-sign helpbox"></span></div>
                    <div style="margin-left: 17px;float:right;" class="btn-group" role="group" aria-label="...">
                        <button id="selectFreehandPolygon" class="btn btn-default blue">Freehand</button>
                        <button id="selectRectPolygon" class="btn btn-default blue">Extent</button>
                    </div>

                </td>
            </tr>
            <tr>
                <td>
                    Option 4: Upload a Zipped Shapefile <span helptype ="upload" class="glyphicon glyphicon-question-sign helpbox"></span>
                    <form id="submitShape" method="POST" action="#" onsubmit="" enctype="multipart/form-data">
                        <select name ="projectionType" id="projectionType" style="color:#000000; display: none;">
                            <option value="PRJ">Get Projection Info from .prj File</option>
                            <option value="WGS84"> WGS84</option>
                            <option value="webmerc">Web Mercator</option>
                            <option value="UTMFoot">UTM Foot</option>
                        </select>

                        <input type="file" style="float: left;" name="shp" id="shp" accept="application/zip"/>
                        <div class="btn-group" style="float: right; position: relative; bottom: 11px;" role="group" aria-label="...">
                            <input type="submit" id="upload2" name="upload" value="Upload" id="upload" style="color:#000000" class="btn btn-default blue"/>
                            <button onclick = "cancelUpload2()" type="button" style="color:#000000" class="btn btn-default blue">Cancel</button>
                        </div>

                    </form>

                </td>
            </tr>
        </table>
    </div>
    <div class="row lowerhalf step_header">Step 2: Select wells by clicking checkbox.</div>
    <div class='row lowerhalf'><span id="wellsFound"></span><span id="wellsSelected"></span></div>
    <div id="wellsGridContainer" class='row lowerhalf'></div>
    <div class="row lowerhalf step_header">Step 3: Click select button to complete.</div>
    <div id="tablefooter" class='row lowerhalf'>

            <button id="selectWellResults" type="button" class="btn btn-default blue col-xs-4 " data-toggle="dropdown" aria-expanded="false">
                <span>Select</span>
            </button>

            <button id="clearWellSearch" type="button" class="btn btn-default blue col-xs-4" data-toggle="dropdown" aria-expanded="false">
                <span>Clear Search</span>
            </button>

            <div id="downloaddropdown" class="btn-group col-xs-4">
            <button type="button" class="btn btn-default dropdown-toggle col-xs-12 blue" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Download
            </button>
            <ul class="dropdown-menu">
                <li><span id="downloadWellCSV">Station Info</span></li>
                <li><span id="downloadTimeSeriesCSV">Water Levels</span></li>
            </ul>
            </div>
    </div>



</div>

<div id="tooltip"></div>




<!--This is the div for the sharing aggreement -->
<!-- <div id='pdf'>
    <button id='prev'>Previous</button>
    <button id='next'>Next</button>
    <button id='accept'>Accept</button>
    <canvas id="the-canvas" style="border:1px solid black;"/>

</div>-->
<%@include file="templates/footer.jsp" %>
