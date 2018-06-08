<%-- 
    Document   : addresssearch
    Created on : Aug 31, 2015, 2:20:05 PM
    Author     : zwaller
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">window.SERVER_ROOT = "${pageContext.request.contextPath}"</script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/mapServerList.js"></script>
        <script src="https://js.arcgis.com/3.11/"></script>
        <script src="${pageContext.request.contextPath}/resources/js/addresssearch.js"></script>
        
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/bootstrap-3.3.5-dist/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="https://js.arcgis.com/3.11/dijit/themes/claro/claro.css">    
        <link rel="stylesheet" href="https://js.arcgis.com/3.11/esri/css/esri.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addresssearch.css" type="text/css">
        <title>Address Search</title>
    </head>
    <body>
        <div id="leftcol">
            <div id ="resultsarea">
            </div>
        </div>
        <div id="rightcol">
            <div id='map'>
                
            </div>
        </div>
    </body>
</html>
