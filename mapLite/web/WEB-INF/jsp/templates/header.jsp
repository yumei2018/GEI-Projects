<%--
    Document   : header
    Created on : Nov 4, 2014, 10:54:38 AM
    Author     : ileung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  ng-app="ui.bootstrap.lidar">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
        <title>${PageTitle}</title>
        <div class="center">
            Loading...
        </div>
        <base href="${pageContext.request.contextPath}">
         <script type="text/javascript">window.SERVER_ROOT = "${pageContext.request.contextPath}"</script>


<!--        <script src="${pageContext.request.contextPath}/resources/js/js-webshim/minified/polyfiller.js"></script>-->
<!--        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>-->
<!--        <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>-->
<!--        <script src="//code.jquery.com/jquery-1.10.2.js"></script>-->
<!--        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.js"></script>-->
<!--        <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.12.0.js"></script>-->
<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pdf.js"></script>-->
<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pdf.worker.js"></script>-->



        <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/dygraph-combined.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/mapServerList.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/ToggleBasemap.js"></script>
        <script src="https://js.arcgis.com/3.16/"></script>
        <script src="${pageContext.request.contextPath}/resources/js/map.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/bootstrap-3.3.5-dist/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" href="https://js.arcgis.com/3.16/dijit/themes/claro/claro.css">
        <link rel="stylesheet" href="https://js.arcgis.com/3.16/esri/css/esri.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" type="text/css">

<!--        Used for google charts-->
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAUOdz1-mbDnrP2QpajfKnhHic_6NM2Aeg&libraries=drawing,geometry"></script>
        <script type="text/javascript">// Load the Visualization API and the columnchart package.
            google.load('visualization', '1', {packages: ['corechart']});
        </script>
       <script>
            var map, featureLayer,
                toolbar,
                symbol,
                geomTask,
                //global graphic
                graphic,
                //global geometry
                geometry,
                //global extents
                extents,
                //county shapefile graphic
                county = [],
                //river shapefile graphic
                river=[],
                //levee shapefile graphic
                levee=[],
                //holds the download ids
                downloadIds = null,
                //holds the contour
                contour = [],
                //the shapefile to query the request
                activeLidarShape,
                activeLidarShapes=[],
                downloadTile = false,
                downloadExtent = false;
            var mapx, mapy, selectedTile, getCrossSection = false, downloadUsrShape = false;
        </script>
    </head>
    <body class="claro">
