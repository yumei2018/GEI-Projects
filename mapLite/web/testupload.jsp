<%-- 
    Document   : testupload
    Created on : Dec 17, 2014, 10:37:00 AM
    Author     : ileung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script  src="http://malsup.github.com/jquery.form.js"></script> 
        <script type="text/javascript" src="resources/js/pdf.js"></script>
        <script type="text/javascript" src="resources/js/pdf.worker.js"></script>
        <style>
            #submitForm{
                z-index:1000;
                bottom: 25%;
                padding:10px;
                position: fixed;
                left: 25%;
                /*background: #65a9d7;*/
                background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#65a9d7));
                background: -webkit-linear-gradient(top, #3e779d, #65a9d7);
                background: -moz-linear-gradient(top, #3e779d, #65a9d7);
                background: -ms-linear-gradient(top, #3e779d, #65a9d7);
                background: -o-linear-gradient(top, #3e779d, #65a9d7);
                -webkit-border-radius: 8px;
                -moz-border-radius: 8px;
                border-radius: 8px;
                -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;
                -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;
                box-shadow: rgba(0,0,0,1) 0 1px 0;
                text-shadow: rgba(0,0,0,.4) 0 1px 0;
                color: white;
                font-size: 14px;
                font-family: Georgia, serif;
                text-decoration: none;
                vertical-align: middle;
            }
        </style>
        <title>JSP Page</title>
        <script>
            $(document).ready(function(){
                start = 1;
                canvas = document.getElementById('the-canvas');
                context = canvas.getContext('2d');
                currentPage = 1;
                pages = [];
                
                PDFJS.getDocument('resources/images/Data_Sharing_Disclaimer.pdf').then(function(pdf) {
                    if(currentPage <= pdf.numPages) {
                        getPage();
                    }
                    function getPage(){
                        // Using promise to fetch the page
                        pdf.getPage(currentPage).then(function(page) {
                        var scale = .75;                        
                        var viewport = page.getViewport(scale);

                        //
                        // Prepare canvas using PDF page dimensions
                        //

                        canvas.height = viewport.height;
                        canvas.width = viewport.width;

                        //
                        // Render PDF page into canvas context
                        //
                        var renderContext = {
                          canvasContext: context,
                          viewport: viewport
                        };
                        page.render(renderContext).then(function(){
                            pages.push(canvas.toDataURL());
                            if(currentPage< pdf.numPages){
                                currentPage++;
                                getPage();
                            } 
                        });
                    });
                }
            });

            
 
            $('#submitForm').ajaxForm({
                url: "http://localhost:8080/LidarRequestTool/upload/uploadFile",                    
                dataType: "text"
            });
            
            
        });
        
        function drawPage(index) {
            var img = new Image;
            img.onload = function() {
                /* this will draw the image loaded onto canvas at position 0,0
                   at the optional width and height of the canvas.
                   'this' is current image loaded 
                */
                context.drawImage(this, 0, 0, context.canvas.width, context.canvas.height);
//                    callback();          // invoke callback when we're done
            }
            img.src = pages[index];  // start loading the data-uri as source
        }
        function showPDF(){            
            drawPage(start);
        }
        function showPDFPrev(){
            start++;
            drawPage(start);
        }
        function showPDFNext(){
            start--;
            drawPage(start);
        }

        </script>
    </head>
    <body>
        <!--<form method="POST" action="http://localhost:8082/LidarRequestTool/upload/uploadFile" enctype="multipart/form-data" >-->
        <form id="submitForm" method="POST" action="#" onsubmit="" enctype="multipart/form-data" >
            .shp File:
            <input type="file" name="shp" id="shp" /> <br/>
            .shx File
            <input type="file" name="shx" id="shx" /> <br/>
            .dbf File
            <input type="file" name="dbf" id="dbf" />                         
            </br>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>
        <!--<button value="Submit" onclick="submitFormJQuery()"> Upload></button>-->
        <button id='showpdf' onclick='showPDF()'>Show Disclaimer</button>
        <div id='pdf' style='display: none'>
            <button id='prev' onclick='showPDFPrev()'>Previous</button>
            <button id='next' onclick='showPDFNext()'>Next</button>
            <canvas id="the-canvas" style="border:1px solid black;"/>
        </div>
        
        <!--<iframe src="http://docs.google.com/gview?url=http://localhost:8080/LidarRequestTool/images/Data_Sharing_Disclaimer.pdf" style="width:600px; height:500px;" frameborder="0"></iframe>-->
    </body>
</html>
