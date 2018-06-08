/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global map, esri, google, dojo, mapservers, dijit, layers, getMapserver, layerTabs, geometry, featureLayer, contour, parseFloat, drawLayer */

window.MapLite = {
  _onLoad: [],
  _wellNameList: [],
  onLoad: function (func) {
    this._onLoad.push(func);
  },
  _onWellSelect: [],
  onWellSelect: function (func) {
    this._onWellSelect.push(func);
  }
};

//var iframeMapLite = document.getElementById('iframe').contentWindow.MapLite;

// Example of use as a plugin:
MapLite.onLoad(function () {

  MapLite.addHomeButton();
  MapLite.setPageTitle();

//    MapLite.showWDLWells();
//    MapLite.openSearchWellsWindow();
});
MapLite.onWellSelect(function (r)
{
  MapLite._wellNameList.push(r);
  parent.$("#associated_well_iframe", window.parent.document).dialog('close');
//    MapLite.closeSearchWellsWindow();
//    MapLite.closeSearchResultDialog();
});

require([
  "esri/map",
  "esri/toolbars/draw",
  "esri/geometry/webMercatorUtils",
  "esri/layers/FeatureLayer",
  "esri/request",
  "esri/geometry/Point",
  "esri/geometry/Circle",
  "esri/units",
  "esri/graphic",
  "esri/layers/GraphicsLayer",
  "esri/dijit/HomeButton",
  "esri/tasks/FeatureSet",
  "esri/symbols/SimpleMarkerSymbol",
  "esri/symbols/SimpleLineSymbol",
  "esri/symbols/SimpleFillSymbol",
  "esri/symbols/PictureMarkerSymbol",
  "esri/tasks/GeometryService",
  "esri/tasks/IdentifyTask",
  "esri/tasks/IdentifyParameters",
  "esri/SpatialReference",
  "esri/dijit/BasemapGallery",
  "dojo/parser",
  "dojo/on",
  "dojo/_base/array",
  "dojo/number",
  "dijit/layout/ContentPane",
  "dijit/layout/TabContainer",
  "dijit/registry",
  "esri/graphicsUtils",
  "esri/toolbars/edit",
  "dijit/layout/BorderContainer",
  "dijit/TitlePane",
  "esri/layers/FeatureLayer",
  "esri/tasks/locator",
  window.SERVER_ROOT + "/resources/js/DraggableInfowindow.js",
  "dojo/domReady!"

], function (
        Map,
        Draw,
        WebMercatorUtils,
        FeatureLayer,
        esriRequest,
        Point,
        Circle,
        Units,
        Graphic,
        GraphicsLayer,
        HomeButton,
        FeatureSet,
        SimpleMarkerSymbol,
        SimpleLineSymbol,
        SimpleFillSymbol,
        PictureMarkerSymbol,
        GeometryService,
        IdentifyTask,
        IdentifyParameters,
        SpatialReference,
        BasemapGallery,
        parser,
        on,
        array,
        number,
        ContentPane,
        TabContainer,
        registry,
        graphicsUtils,
        EditGraphic,
        BorderContainer,
        TitlePane,
        FeatureLayer,
        Locator,
        DraggableInfowindow
        ) {
  parser.parse();

  var hideNearbyTab = false;
  var bindMap = false;
  var showtools = false;
  var wellids = [];
  var urlObject = esri.urlToObject(document.location.href);
  if (urlObject.query) {
    if (urlObject.query.bind == "true") {
      bindMap = true;
    }

    if (urlObject.query.wells != undefined)
    {
      var querystring = urlObject.query.wells;
      wellids = querystring.split(",");
    }

    if (urlObject.query.hidenearbywells != undefined)
    {
      if (urlObject.query.hidenearbywells == "true")
      {
        hideNearbyTab = true;
      }
    }

    if (urlObject.query.showtools == "true")
      showtools = true;


  }

  if (showtools)
    $("#toggleToolsBtn").show();
  else
    $("#toggleToolsBtn").hide();

  //If you want to bind the map to that preset area add "?bind" to the end of the url Example: sac1l-jsteng:8081/mapLite/?bind
  if (bindMap)
  {
    //Create a bounding box that doesn't let the user pan/zoom out of it'
    var initialExtent = new esri.geometry.Extent({xmin: -13468932, ymin: 4202562, xmax: -13348467, ymax: 4276324, spatialReference: {wkid: 102100}});
    var maxExtent = new esri.geometry.Extent({xmin: -13533406, ymin: 4161439, xmax: -13292476, ymax: 4308962, spatialReference: {wkid: 102100}});
    map = new Map("map", {
      basemap: "topo",
      minZoom: 11,
      extent: initialExtent
    });
    var goodExtent = initialExtent;
    dojo.connect(map, "onExtentChange", function (newextent) {
      if ((newextent.xmax > maxExtent.xmax + 10000) || (newextent.xmin < maxExtent.xmin - 10000) || (newextent.ymax > maxExtent.ymax + 10000) || (newextent.ymin < maxExtent.ymin - 10000))
      {
        map.setExtent(goodExtent);
      }
      else
      {
        goodExtent = newextent;
      }
    });
  }
  else
  {
    //normal map
    map = new Map("map", {
      basemap: "topo",
      center: [-122.75, 38],
      zoom: 7
    });
  }


  map.tooltip = new MapliteTooltip("tooltip");


  var tabContainer = new dijit.layout.TabContainer({
    id: "resultsTabContainer",
    tabPosition: "top-h",
    style: 'width: 585px; height: 250px; overflow: auto;',
    tabStrip: true,
    doLayout: true
  });
  var resultsDlg = new dijit.Dialog({
    title: "Results",
    id: 'resultsDlg',
    style: 'width: 600px; height: 300px;'
  });
  resultsDlg.addChild(tabContainer);

  map.querytools = new QueryTool();
  initBufferSearch();
  initToolsWindows();
  initUploadFiles();
  hideLeftPanel();
  if (wellids.length > 0)
    map.querytools.getWellsByName(wellids, drawWellsFromURL);
  createSearchByPolygonPanel();

  map.on("update-start", showLoading);
  map.on("update-end", hideLoading);

  //create a feature collection for the flickr photos
  var featureCollection = {
    layerDefinition: null,
    featureSet: {
      features: [],
      geometryType: "esriGeometryPoint"
    }
  };
  featureCollection.layerDefinition = {
    geometryType: "esriGeometryPoint",
    objectIdField: "ObjectID",
    drawingInfo: {
      renderer: {
        type: "simple",
        symbol: {
          type: "esriPMS",
          url: window.SERVER_ROOT + "/resources/images/mapPoint64.png",
          width: 15,
          height: 15
        }
      }
    },
    fields: []
  };

  //create a feature layer based on the feature collection
  featureLayer = new FeatureLayer(featureCollection, {
    id: 'wellLayer'
  });

  //associate the features with the popup on click
  featureLayer.on("click", function (evt) {
    openInfoWindow(evt);
  });

  map.addLayers([featureLayer]);

  function getTitle(graphic) {
    return "<b id='wellTitle'>" + graphic.attributes.title + "</b>";
  }

  //this method shows the points on the map
  function requestSucceeded(jsonObj/*, io*/) {
    //loop through the items and add to the feature layer
    var extent = new esri.geometry.Extent({xmin: -13468932, ymin: 4202562, xmax: -13368467, ymax: 4276324, spatialReference: {wkid: 102100}});
    featureLayer.clear();
    var features = [];
    
    for (var i = 0; i < jsonObj.length; i++) {
      var attr = {};
      var obj = jsonObj[i];
      var well = obj.well;
      var wellid = obj.id;
      var coord = obj.coord.split(" ");
      var x = coord[0].substring(4, coord[0].length - 1);
      var y = coord[1].substring(2, coord[1].length - 1);
      attr["description"] = well;
      attr["title"] = well;
      attr["id"] = wellid;
      var geometry = new Point({x: x, y: y, spatialReference: {wkid: 4326}});
      if (bindMap) {
        if (extent.contains(geometry)) {
          //var graphic = new Graphic(geometry);
          //graphic.setAttributes(attr);
          //features.push(graphic);
          if (obj.well === "04N05E06A001M" || obj.well === "05N05E28L003M" || obj.well === "04N05E14B002M" || obj.well === "04N05E22A001M"
                  || obj.well === "04N06E06N012M" || obj.well === "04N05E01H011M" || obj.well === "04N05E01H012M" || obj.well === "03N05E03J001M"
                  || obj.well === "04N05E33A004M")
          {
            var sym = new PictureMarkerSymbol(window.SERVER_ROOT + "/resources/images/mapPointBlue64.png", 20, 20);
            var graphic = new Graphic(geometry, sym);
          }
          else
            var graphic = new Graphic(geometry);
          graphic.setAttributes(attr);

          features.push(graphic);
        }
      }
      else {
        //var graphic = new Graphic(geometry);
        //graphic.setAttributes(attr);
        //features.push(graphic);
        if (obj.well === "04N05E06A001M" || obj.well === "05N05E28L003M" || obj.well === "04N05E14B002M" || obj.well === "04N05E22A001M"
                || obj.well === "04N06E06N012M" || obj.well === "04N05E01H011M" || obj.well === "04N05E01H012M" || obj.well === "03N05E03J001M"
                || obj.well === "04N05E33A004M")
        {
          var sym = new PictureMarkerSymbol(window.SERVER_ROOT + "/resources/images/mapPointBlue64.png", 20, 20);
          var graphic = new Graphic(geometry, sym);
        }
        else
          var graphic = new Graphic(geometry);
        graphic.setAttributes(attr);

        features.push(graphic);
      }
    }
    $('#loadShapes').hide();
    featureLayer.applyEdits(features, null, null);
    featureLayer.show();
  }

  function requestFailed(error) {
    console.log('failed');
  }



  map.on("load", createToolbar);
  map.on("load", function (e, thismap) {
    map.basemaptoggler = new BasemapToggler("BasemapToggle", "map", map);
    $(map.infoWindow.domNode).addClass('infoWindow');
    DraggableInfowindow.init(map.infoWindow, map);
  });


  //add the basemap gallery, in this case we'll display maps from ArcGIS.com including bing maps
//    var basemapGallery = new BasemapGallery({
//        showArcGISBasemaps: true,
//        map: map
//    }, "basemapGallery");
//    basemapGallery.startup();
//    basemapGallery.on("selection-change",function(){
//        dijit.byId("basemapButtonTitle").toggle();
//    });
//    basemapGallery.on("error", function (msg) {
//        console.log("basemap gallery error:  ", msg);
//    });
  // loop through all dijits, connect onClick event
  // listeners for buttons to activate drawing tools
  registry.forEach(function (d) {
    // d is a reference to a dijit
    // could be a layout container or a button
    if (d.declaredClass === "dijit.form.Button") {
      d.on("click", activateTool);
    }
  });
  //initialize all inputs and tie those html object to functions
  $(document).ready(function () {
//        webshims.setOptions('forms-ext', {types: 'date'});
//        webshims.polyfill('forms forms-ext');
    //for single checks
//        $('input[type="checkbox"]').on('change', function() {
//            $(this).siblings('input[type="checkbox"]').not(this).prop('checked', false);
//        });

    //Add this back if we end up using contours
    $.ajax({
      url: map.querytools.SERVER_ROOT + "/contour/param",
      success: populateBox
    });
    $("#defineArea").click(function () {
      toolbar.activate(esri.toolbars.Draw.FREEHAND_POLYGON);
    });
    $("#clearContour").click(function () {
      removeGraphics(contour);
      contour = [];
    });
    $("#clearGeometry").click(function () {
      map.contourlayer.remove(graphic);
      geometry = undefined;
      removeGraphics(contour);
      contour = [];
    });
    $('#viewWells').click(function () {
      if ($("#wellList").is(':visible')) {
        $("#wellList").hide();
      } else {
        if (geometry !== undefined) {
          var latlnggeoms = WebMercatorUtils.webMercatorToGeographic(geometry);
          $.ajax({
            url: window.SERVER_ROOT + "/contour/getPoints",
            type: "POST",
            data: {
              year: $("#year option:selected").val(),
              season: $("#season option:selected").val(),
              geometry: JSON.stringify(latlnggeoms.rings[0])
            },
            success: showWellList
          });
        }
      }
    });
    $('#viewLegend').click(function () {
      if (contour != []) {
        if ($("#legend").is(':visible')) {
          $("#legend").hide();
        } else {
          if ($.trim($("#legend").html()) !== '') {
            $("#legend").show();
          }
        }
      }
    });
    $('#submitQuery').click(function () {
      //Loading
      if (geometry == undefined) {
        alert("Please define area of interest");
        return;
      }
      if (!($("#year2 option:selected").val() === "" && $("#season2 option:selected").val() === "")) {
        if ($("#year2 option:selected").val() === "") {
          alert("Please select a year in step (3)");
          return;
        }
        if ($("#season2 option:selected").val() === "") {
          alert("Please select a season in step (3)");
          return;
        }
        generateDifferenceContour();
      } else {
        generateSingleContour();
      }
    });



    $("#form").dialog({
      autoOpen: false,
      minWidth: 425,
      minHeight: 350,
      title: 'Create Contours'
    });

    $('#uploadParam').dialog({
      autoOpen: false,
      title: 'Upload'
    });


    $('#searchResultsDialog').dialog({
      autoOpen: false,
      width: 500,
      height: 400,
      title: 'Well Search Results'
    });

    $('#downloadParamContainer').dialog({
      autoOpen: false,
      width: 350,
      title: 'Download'
    });

    $('#graphicsWindow').dialog({
      autoOpen: false,
      width: 350,
      title: 'Graphics'
    });

    $('#measureWindow').dialog({
      autoOpen: false,
      minWidth: 450,
      minHeight: 400,
      title: 'measure'
    });

    $('#surfaceProfileWindow').dialog({
      autoOpen: false,
      minWidth: 500,
      minHeight: 400,
      title: 'Surface Profile'
    });

    $('#searchWellsWindow').dialog({
      autoOpen: true,
      position: {my: "right top", at: "right top"},
      resizable: false,
      width: 360,
      title: 'Search Wells'
    });

    $('#searchWindow').dialog({
      autoOpen: false,
      minWidth: 450,
      minHeight: 400,
      title: 'Search Results'
    });

    $("#legend").css("display", "block");
    $("#legend").hide();
    $("#legend").draggable();

    $('#wellList').css('display', 'block');
    $('#wellList').hide();
    $('#wellList').draggable({
      start: function () {
        // if we're scrolling, don't start and cancel drag
        if ($(this).data("scrolled")) {
          $(this).data("scrolled", false).trigger("mouseup");
          return false;
        }
      }
    }).find("*").andSelf().scroll(function () {
      // bind to the scroll event on current elements, and all children.
      //  we have to bind to all of them, because scroll doesn't propagate.

      //set a scrolled data variable for any parents that are draggable, so they don't drag on scroll.
      $(this).parents(".ui-draggable").data("scrolled", true);
    });

    $('#boxclose').click(function () {
      $('#graphCrossSection').hide();
      map.contourlayer.remove(graphic);
    });

    $("#loadShapes").hide();
  });


  function generateSingleContour() {
    $("#loadShapes").show();
    //Builds the list of wells that the user selected. If empty, use all wells.
    var selectedWells;
    if ($("#wellListBox").children('input').length > 0) {
      selectedWells = [];
      $("#wellListBox").children('input').each(function () {
        if ($(this).prop('checked')) {
          selectedWells.push(this.id);
        }
      });
    }
    var geo = "";
    if (geometry !== undefined) {
      var geom = esri.geometry.webMercatorToGeographic(geometry);
      geo = JSON.stringify(geom.rings[0]);
    }

    showLoading();

    $.ajax({
      url: window.SERVER_ROOT + "/contour/gen",
      type: "POST",
      data: {
        year: $("#year option:selected").val(),
        season: $("#season option:selected").val(),
        shape: geo,
        selectedWells: JSON.stringify(selectedWells)
      },
      success: addContours,
      failure: function () {
        hideLoading();
      }

    });
//        $.ajax({
//            url:"http://localhost:8084/ContourService/contour/getCountour_New",
//            type: "POST",
//            data: {
//                year: $("#year option:selected").val(),
//                season: $("#season option:selected").val(),
//                shape: geo,
//                selectedWells: JSON.stringify(selectedWells)
//            },success: addContours
//        });
  }

  function generateDifferenceContour() {
    $("#loadShapes").show();
    //Builds the list of wells that the user selected. If empty, use all wells.
    var selectedWells;
    if ($("#wellListBox").children('input').length > 0) {
      selectedWells = [];
      $("#wellListBox").children('input').each(function () {
        if ($(this).prop('checked')) {
          selectedWells.push(this.id);
        }
      });
    }
    var geo = "";
    if (geometry !== undefined) {
      var geom = esri.geometry.webMercatorToGeographic(geometry);
      geo = JSON.stringify(geom.rings[0]);
    }
    $.ajax({
      url: window.SERVER_ROOT + "/contour/gen2",
      type: "POST",
      data: {
        year: $("#year option:selected").val(),
        season: $("#season option:selected").val(),
        year2: $("#year2 option:selected").val(),
        season2: $("#season2 option:selected").val(),
        shape: geo,
        selectedWells: JSON.stringify(selectedWells)
      },
      success: addContours
    });
  }

  function populateBox(response) {
    var options = '';
    var options2 = '<option value=""></option>';
    var years = response.substring(1, response.length - 1).split(",");
    for (var i = 0; i < years.length; i++) {
      options = options + '<option value="' + years[i] + '">' + years[i] + '</option>';
    }
    options2 = options2 + options;
    $("select#year").html(options);
    $("select#year2").html(options2);
  }

//    function addPoints(response){
//        var jsonObj = JSON.parse(response);
//        for(var i = 0; i < jsonObj.length; i++){
//            var obj = jsonObj[1];
//            var well = obj.well;
//            var coord = obj.coord.split(" ");
//            var x = coord[0].substring(4, coord[0].length - 1);
//            var y = coord[1].substring(2, coord[1].length - 1);
//
//        }
//
//    }

  function showWellList(jsonObj) {
    requestSucceeded(jsonObj);

    var htmlString = '';
    var wells = [];
    //creates list of wells
    for (var i = 0; i < jsonObj.length; i++) {
      var obj = jsonObj[i];
      var well = obj.well;
      wells.push(well);
    }
    //sorted order
    wells.sort();
    for (var i = 0; i < wells.length; i++) {
      htmlString += "<div style='text-wrap: none; white-space: nowrap;'><input type='checkbox' id='" + wells[i] + "' checked='checked'>" + wells[i] + "</div>";
    }
    $('#wellListBox').html(htmlString);
    $('#wellList').show();
  }

  function addContours(jsonResponse) {

    $("#loadShapes").hide();
    if (jsonResponse) {
//            removeGraphics(contour);
//            contour = [];
      var shapesString = jsonResponse.contour;
      var shapes = shapesString.split("--");
      var multiplier = 255 / shapes.length;
      multiplier = Math.floor(multiplier);
      if (multiplier < 1)
        multiplier = 1;
      for (var j = 0; j < shapes.length; j++) {
        var parts = shapes[j].split("],~[");
        for (var k = 0; k < parts.length; k++) {
          var allCoords = [];
          var shape = parts[k].split("],[");
          for (var i = 0; i < shape.length; i++) {
            var coords = shape[i].split(",");
            var coordPair = [parseFloat(coords[0]), parseFloat(coords[1])];
            allCoords.push(coordPair);
          }
          var myPolygon = {geometry: {rings: [allCoords], spatialReference: {wkid: 4326}}, symbol: {color: [255 - (multiplier * j), 0, 255 - (255 - (multiplier * j)), 0], outline: {color: [255 - (multiplier * j), 0, 255 - (255 - (multiplier * j)), 255], width: 1, type: "esriSLS", style: "esriSLSSolid"}, type: "esriSFS", style: "esriSFSSolid"}};
          var gra = new Graphic(myPolygon);
          contour.push(gra);
          map.contourlayer.add(gra);
        }
      }
      hideLoading();
      //this part creates the legend view
      var indices = jsonResponse.indices;
      var htmlString = '';
      for (var i = 0; i < indices.length; i++)
      {
        var index = indices[i].index;
        var elevation = indices[i].elevation;
        var r = 255 - (12 * index);
        var b = 255 - (255 - (12 * index));
        ;
        var g = 0;
        htmlString += '<div style="text-wrap: none; white-space: nowrap;"><div style="height:10px;width:25px;background-color:' + rgbToHex(r, g, b) + ';display:inline-block"></div> ' + elevation + '</div>';
      }
      $("#legend").html(htmlString);
    } else {
      alert("More wells containing data for the selected time period are required to generate the contour.  Please select more wells.");
    }
  }

  function removeGraphics(shapes) {
    for (var i = 0; i < shapes.length; i++) {
      map.contourlayer.remove(shapes[i]);
    }
  }
  function activateTool() {
    var tool = this.label.toUpperCase().replace(/ /g, "_");
    toolbar.activate(esri.toolbars.Draw.FREEHAND_POLYGON);
//        map.hideZoomSlider();
  }

  function showLeftPanel() {
    var w = $('#leftColumn').show().width();
    $('#map').css({left: w, width: $(window).width() - w});
    map.resize();
    map.reposition();
    dijit.byId("leftColumn").resize();
  }

  function hideLeftPanel() {
    $('#leftColumn').hide();
    $('#map').css({left: 0, width: $(window).width()});
    map.resize();
    map.reposition();
    dijit.byId("leftColumn").resize();
  }

  function createToolbar(themap) {
    map.helpwindow = HelpWindow("helpwin");
    toolbar = new Draw(map);
    toolbar.on("draw-end", addToMap);
    // Finish Loading
    MapLite.openSearchWellsWindow = function () {
      $('#searchWellsWindow').dialog('open');
    };
    MapLite.closeSearchWellsWindow = function () {
      $('#searchWellsWindow').dialog('close');
    };
    MapLite.closeSearchResultDialog = function () {
      $('#searchResultsDialog').dialog('close');
    };
    MapLite.showWDLWells = function () {
      $('#showPoints').click();
    };
    MapLite.hideWDLWells = function () {
      $('#hidePoints').click();
    };
    MapLite.showTools = function () {
      $("#toolsContainer").show();
    };
    MapLite.setPageTitle = function () {
      document.title = "Well Search Tool";
    };
    MapLite.addHomeButton = function ()
    {
      var children = $("#map_zoom_slider").children();
      $("<div id='HomeButton'></div>").insertAfter(children[1]);


      var home = new HomeButton({
        map: map
      }, "HomeButton");
      home.startup();
    }

    MapLite.showLeftPanel = showLeftPanel;
    MapLite.hideLeftPanel = hideLeftPanel;
    triggerListeners('onLoad', MapLite);
    MapLite.onLoad = function (func) {
      func(MapLite);
    };
    MapLite.clearSearchResults = clearWellSearchWin;
  }

  function addToMap(evt) {
    var symbol;
    toolbar.deactivate();
//        map.showZoomSlider();
    switch (evt.geometry.type) {
      case "point":
      case "multipoint":
        symbol = new SimpleMarkerSymbol();
        break;
      case "polyline":
        symbol = new SimpleLineSymbol();
        break;
      default:
        symbol = new SimpleFillSymbol();
        break;
    }
    if (graphic !== undefined) {
      map.contourlayer.remove(graphic);
    }
    graphic = new Graphic(evt.geometry, symbol);
    map.contourlayer.add(graphic);
    geometry = evt.geometry;
  }

  $("#showPoints").click(function () {
    $('#loadShapes').show();
    $.ajax({
      url: window.SERVER_ROOT + "/contour/getAllPoints",
      success: requestSucceeded
    });
  });
  $("#hidePoints").click(function () {
    featureLayer.setVisibility(false);
  });
  $("#downloadWellData").click(function () {
    $("#downloadParamContainer").dialog("open");
  });

  function getSelectedWells(ids) {
    var wellids = "";
    if (ids == null) {
      if (geometry) {
        for (var i = 0; i < featureLayer.graphics.length; i++) {
          if (geometry.contains(new esri.geometry.geographicToWebMercator(featureLayer.graphics[i].geometry))) {
            wellids += featureLayer.graphics[i].attributes.title + ",";
          }
        }
      }
    } else {
      wellids = ids;
    }
    return wellids;
  }

  $("#uploadWellData").click(function () {
    $("#uploadParam").dialog("open");
  });
  $('#uploadParam').ajaxForm({
    url: window.SERVER_ROOT + "/contour/uploadData",
    data: {
      dataType: $("#dataType option:selected").val(),
      dataFormat: $("#dataFormat option:selected").val()
    },
    dataType: "text",
    success: uploaded
  });

  $("#download").on("click", function () {
    $.ajax({
      url: window.SERVER_ROOT + "/contour/downloadData",
      data: {
        names: getSelectedWells(downloadIds),
        range: $(".dateRange").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
        format: $(".downloadFormat").val()
      },
      success: downloaded
    });
  });

  $('#toggleLeftPanel').on('click', function () {
    var btn = $('#toggleLeftPanel button');
    var html = btn.html(), leftArrow = '\u25c0', rightArrow = '\u25b6';
    if (html === leftArrow) {
      hideLeftPanel();
      btn.html(rightArrow);
    }
    else if (html === rightArrow) {
      if (map.layersloaded == false || map.layersloaded == undefined || map.layersloaded == null)
      {
        loadLayers();
        map.layersloaded = true;
      }
      showLeftPanel();
      btn.html(leftArrow);
    }
  });

  function uploaded(response) {
    $("#uploadParam").dialog("close");
  }
  function downloaded(response) {
    window.open(window.SERVER_ROOT + "/files/downloadData?fileName=" + response, "_blank");
    $("#uploadParam").dialog("close");
    downloadIds = null;
  }


  function loadLayers()
  {

    map.visibleLayers = [];
    loadMapFeatureLayers();
    for (var i = 0; i < layerTabs.length; i++)
    {
      var content = getLayerTabHTML(layerTabs[i].id);
      var title = layerTabs[i].title;
      if (title === "Hydrology")
        content += "<div><a href='http://cdec.water.ca.gov/cdecstation' target='_blank'>CDEC Stations</a></div>";
      dijit.byId("leftColumn").addChild(new dijit.layout.ContentPane({title: title, content: content, selected: true, closable: false}));
    }



    $(".slider").slider({min: 0, max: 100, value: 100});
    $(".slider").on("slidechange", function (event, ui) {
      var mid = $(this).attr("mid");
      var lid = $(this).attr("lid");
      var lyr = map.getLayer("featureLayer_" + mid + "_" + lid);
      var opacity = ui.value / 100;
      lyr.setOpacity(opacity);
    });

    $(".layerCheckbox").change(function () {
      var mid = $(this).attr("mid");
      var lid = $(this).attr("lid");
      var lyr = map.getLayer("featureLayer_" + mid + "_" + lid);

      if (!$(this).is(":checked")) {//lyr.visible
        lyr.hide();
        for (var i = 0; i < map.visibleLayers.length; i++) {
          if (map.visibleLayers[i].mid == mid && map.visibleLayers[i].lid == lid) {
            map.visibleLayers.splice(i, 1);
          }
        }
      }
      else {
        lyr.show();
        map.visibleLayers.push({mid: mid, lid: lid});
      }
    });
  }

  function getLayerTabHTML(tabID)
  {
    var content = "";
    for (var i = 0; i < layers.length; i++) {
      if (layers[i].tab == tabID || (tabID == 0 && layers[i].tab == undefined)) {
        var img = "";
        if (layers[i].legendPath) {
          for (var item in layers[i].legendPath) {
            img += "<img src='" + window.SERVER_ROOT + "/resources/images/legends/" + layers[i].legendPath[item]["path"] + "'> " + layers[i].legendPath[item]["label"] + "<br>";
          }
        }


        content += "<div class='layerDiv' mid='" + layers[i].mid + "' lid='" + layers[i].lid + "' >" +
                "<div>" + layers[i].name + "</div>" +
                "<div style='height: 30px;'>" +
                "<input id='" + layers[i].lid + "' " +
                "class='layerCheckbox' " +
                "type='checkbox' " +
                "mid='" + layers[i].mid + "' " +
                "lid='" + layers[i].lid + "'" +
                ">" +
                "<div class='slider' mid='" + layers[i].mid + "' " +
                "lid='" + layers[i].lid + "'>" +
                "</div>" +
                "</div>" +
                "<div style='padding-left: 25px;'>" +
                img +
                "</div>" +
                "</div>";
      }
//            $("#leftColumn").append(content);

    }
    return content;
  }


  function loadMapFeatureLayers()
  {
    for (var i = 0; i < layers.length; i++)
    {
      var mid = layers[i].mid;
      var featurelayer = new esri.layers.FeatureLayer(mapservers[mid].url + "/" + layers[i].lid, {id: "featureLayer_" + layers[i].mid + "_" + layers[i].lid, visible: false});
      map.addLayer(featurelayer);
    }
  }



  function mapClickHandler(e, i)
  {
    if (i == undefined)
      i = 0;


    var visible_lyrs = map.visibleLayers;
    if (!visible_lyrs)
      return;
    if (visible_lyrs.length === 0)
      return;
    var currentLayer = visible_lyrs[i];
    if (currentLayer !== undefined && currentLayer !== null)
    {
      var url = getMapserver(currentLayer.mid).url;
      var identify = new IdentifyTask(url);
      var params = new IdentifyParameters();
      params.geometry = e.mapPoint;
      params.outFields = ["*"];
      params.returnGeometry = false;
      params.layerIds = [currentLayer.lid];
      params.tolerance = 1;
      params.layerOption = IdentifyParameters.LAYER_OPTION_ALL;
      params.mapExtent = map.extent;

      identify.execute(params,
              function (res) {
                addResultToWindow(currentLayer, res);
              },
              function (err) {
              }
      );
    }
    if (i < visible_lyrs.length - 1) {
      i++;
      mapClickHandler(e, i);
    }
  }


  function getVisibleLayers()
  {
    var visibleLayers = [];
    for (var i = 0; i < mapservers.length; i++)
    {
      var mid = mapservers[i].mid;

      var lyr = map.getLayer("mapserver_" + mid);
      for (var j = 0; j < lyr.visibleLayers.length; j++)
      {
        if (lyr.visibleLayers[j] != -1)
        {
          visibleLayers.push({mid: mid, lid: lyr.visibleLayers[j], name: mapservers[i].name});
        }
      }
    }
    return visibleLayers;
  }

  function addResultToWindow(layerInfo, res)
  {
    var content = createResWinContent(res, layerInfo);

    if (content !== undefined && content !== null) {
      var name = getLayerName(layerInfo.mid, layerInfo.lid);
      dijit.byId('resultsTabContainer').addChild(new dijit.layout.ContentPane({title: name, content: content, selected: true, closable: false}));
    }
    dijit.byId('resultsDlg').show();

  }

  function getLayerName(mid, lid) {
    for (var i = 0; i < layers.length; i++) {
      if (layers[i].lid == lid && layers[i].mid == mid) {
        return layers[i].name;
      }
    }
    return "";
  }

  function getLayerInfo(mid, lid) {
    for (var i = 0; i < layers.length; i++) {
      if (layers[i].lid === parseInt(lid) && layers[i].mid === parseInt(mid)) {
        return layers[i];
      }
    }
    return null;
  }

  function clearResultWindow() {
    var children = dijit.byId('resultsTabContainer').getChildren();
    for (var i = 0; i < children.length; i++) {
      dijit.byId('resultsTabContainer').removeChild(children[i]);
    }
  }

  function getMapserver(mid) {
    for (var j = 0; j < mapservers.length; j++) {
      if (mapservers[j].mid == mid)
        return mapservers[j];
    }
    return null;
  }


  map.on("click", function (evt) {
    map.tooltip.hide();
    clearResultWindow();
    mapClickHandler(evt);
  });

  function createResWinContent(res, layerInfo)
  {
    if (res.length == 0)
      return null;

    var lid = layerInfo.lid;
    var mid = layerInfo.mid;
    var layerData = getLayerInfo(mid, lid);
    var hasLink = (layerData.links !== null && layerData.links !== undefined) ? true : false;

    var content = "<table class='resultsTable'><tr>";
    for (var item in res[0].feature.attributes) {
      if (layerInfo.display_columns == undefined) {
        content += "<th>" + item + "</th>";
      }
    }
    content += "</tr>";
    for (var i = 0; i < res.length; i++)
    {
      var outlineLinkPlaced = false;
      content += "<tr>";
      for (item in res[i].feature.attributes)
      {
        // IF column matches column to be replaced by declared link
        if (hasLink)
        {
          var valSet = false;
          for (var j = 0; j < layerData.links.length; j++)
          {
            if (layerData.links[j].column === item)
            {
              var datalink = layerData.links[j].link.replace(/<val>/g, res[i].feature.attributes[item]);
              content += "<td>" + datalink + "</td>";
              valSet = true;
            }
          }
          if (!valSet)
            content += "<td>" + res[i].feature.attributes[item] + "</td>";
        }
        else
          content += "<td>" + res[i].feature.attributes[item] + "</td>";
      }
      content += "</tr>";
    }

    content += "</table>";
    return content;
  }

  function initToolsWindows()
  {
    map.addLayer(esri.layers.GraphicsLayer({id: 'contourLayer'}));
    map.addLayer(esri.layers.GraphicsLayer({id: 'searchResultsLyr'}));
    map.addLayer(esri.layers.GraphicsLayer({id: 'searchResultsLyrWellsInDist'}));
    map.addLayer(esri.layers.GraphicsLayer({id: 'wellFromURLLyr'}));
    map.addLayer(esri.layers.GraphicsLayer({id: 'wellSearchLyr'}));
    map.addLayer(esri.layers.GraphicsLayer({id: 'userGraphicsLyr'}));
    map.addLayer(esri.layers.GraphicsLayer({id: 'measureLayer'}));
    map.addLayer(esri.layers.GraphicsLayer({id: 'surfaceProfileLyr'}));
    map.addLayer(esri.layers.GraphicsLayer({id: 'bufferSearchUtilityLyr'}));


    map.contourlayer = map.getLayer("contourLayer");




    map.surfaceprofiletoolbar = new esri.toolbars.Draw(map);
    map.surfaceprofiletoolbar.on("draw-end", doSurfaceProfile);

    map.measuretoolbar = new esri.toolbars.Draw(map);
    map.measuretoolbar.on("draw-end", measureGeometry);

    map.graphicstoolbar = new esri.toolbars.Draw(map);
    map.graphicstoolbar.on("draw-end", addGraphicToMap);

    map.getLayer("wellFromURLLyr").on("click", function (e) {
      openInfoWindow(e);
    });
    map.getLayer("wellSearchLyr").on("click", function (e) {
      openInfoWindow(e);
    });
    map.getLayer('bufferSearchUtilityLyr').on("mouse-move", function (e) {
      if (e.graphic.attributes.id == "center")
      {
        map.tooltip.setMessage("Drag to re-center search");
        map.tooltip.show();
      }
    });

    map.getLayer('bufferSearchUtilityLyr').on("mouse-out", function (e) {
      if (e.graphic.attributes.id == "center")
      {
        map.tooltip.hide();
      }
    });
    $("#toolsContainer button").click(function (e) {
      $(this).each(function (dis) {
        var btn = $(this).attr("id");
        if (btn !== "defineArea") {
          toolbar.deactivate();
        }
      });
    });

    $("#bufferTextbox").val("5");

    $("#toggleToolsBtn").click(function () {
      $("#toolsContainer").toggle();
    });
    $("#toggleSearchWinBtn").click(function () {
      if (isSearchWellsOpen())
      {
        $('#searchWellsWindow').dialog('close');
      }
      else
      {
        $('#searchWellsWindow').dialog('open');
      }
    });
    $("#toolsContainer").hide();
    $("#clearWellSearch").click(function () {
      clearWellSearchWin();
    });

    $("#selectWellResults").click(function () {
      triggerListeners('onWellSelect', getBufferSearchSelected());
    });

    $("#clearArea").click(function () {
      map.contourlayer.clear();
      toolbar.deactivate();
    });

    $("#graphicsBtn").click(function () {
      $("#graphicsWindow").dialog("open");
    });
    $("#measureBtn").click(function () {
      $("#measureWindow").dialog("open");
    });
    $("#measurement_units").change(changeMeasurementUnits);
    $("#clearMeasureBtn").click(clearMeasureLayer);
    $("#surfaceProfileBtn").click(function () {
      $("#surfaceProfileWindow").dialog("open");
    });

    $("#searchBtn").click(function () {
      $("#searchWindow").dialog("open");
    });

    $("#submitSearch").click(doSearch);
    $("#wellsByNameBtn").click(function () {
      clearWellSearchWin();
      var wellnames = $("#wellsByNameTextbox").val().split(",");
      map.querytools.getWellsByName(wellnames, searchWellsByName);
    });

    $("#downloadWellCSV").click(function () {
      var ids = getCheckedWellIds();
      if (ids.length > 0)
        map.querytools.downloadWellData(ids, downloaded);
      else
        alert("To download a CSV file you must first select at least 1 well. ");
    });

    $("#downloadTimeSeriesCSV").click(function () {
      var ids = getCheckedWellIds();
      if (ids.length > 0)
        map.querytools.downloadTimeSeriesCSV(ids, downloaded);
      else
        alert("To download a CSV file you must first select at least 1 well. ");
    });

    map.querytools.initSearchAutoComplete("#searchtext", doSearch);
    map.querytools.initSearchAutoComplete("#wellsByNameTextbox", function () {
      clearWellSearchWin();
      var wellnames = $("#wellsByNameTextbox").val().split(",");
      map.querytools.getWellsByName(wellnames, searchWellsByName);
    });
    locator = new esri.tasks.Locator("http://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer");
    locatorComplete = dojo.connect(locator, "onAddressToLocationsComplete", function (geocodeResults) {

      if (geocodeResults.length > 0)
      {
        map.getLayer("searchResultsLyr").clear();
        dojo.forEach(geocodeResults, function (geocodeResult, index) {
          //create a random color for the text and marker symbol
          var r = Math.floor(Math.random() * 250);
          var g = Math.floor(Math.random() * 100);
          var b = Math.floor(Math.random() * 100);

          var symbol = new esri.symbol.SimpleMarkerSymbol(esri.symbol.SimpleMarkerSymbol.STYLE_CIRCLE, 20, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([r, g, b, 0.5]), 10), new dojo.Color([r, g, b, 0.9]));
          var pointMeters = esri.geometry.geographicToWebMercator(geocodeResult.location);
          var locationGraphic = new esri.Graphic(pointMeters, symbol);

          var font = new esri.symbol.Font().setSize("10pt").setWeight(esri.symbol.Font.WEIGHT_BOLD);
          var textSymbol = new esri.symbol.TextSymbol((index + 1) + ".) " + geocodeResult.address, font, new dojo.Color([r, g, b, 0.8])).setOffset(5, 15);
          //add the location graphic and text with the address to the map
          map.getLayer("searchResultsLyr").add(locationGraphic);
          map.getLayer("searchResultsLyr").add(new esri.Graphic(pointMeters, textSymbol));
        });
        var ptAttr = geocodeResults[0].location;//geocodeResults[0].attributes;

        map.centerAndZoom(esri.geometry.geographicToWebMercator(ptAttr), 13);
      }
      else
        console.log("Error", "Unable to locate address.");
    });
  }

  function createSearchByPolygonPanel()
  {

    map.polygonsearchtb = new Draw(map);
    map.polygonsearchtb.on("draw-end", function (e) {

      map.tooltip.hide();
      map.polygonsearchtb.deactivate();
      clearWellSearchWin();
      var lyr = map.getLayer('wellSearchLyr');
      var symbol = new esri.symbol.SimpleFillSymbol().setColor([77, 127, 185, 50]).outline.setColor([77, 127, 185], 3);
      var graphic = new Graphic(e.geometry, symbol);
      lyr.add(graphic);
      getWellsInGraphicExtent(e.geometry);
    });

    map.polygonsearchtb.isActive = false;
    $("#selectFreehandPolygon").click(function () {

      map.bufferBtnActive = false;
      map.polygonsearchtb.isActive = !map.polygonsearchtb.isActive;
      if (map.polygonsearchtb.isActive == true)
      {
        map.tooltip.setMessage("Hold down the cursor and drag to draw on the map.");
        map.tooltip.show();
        map.polygonsearchtb.activate(esri.toolbars.Draw.FREEHAND_POLYGON);
      }
      else
      {
        map.tooltip.hide();
        map.polygonsearchtb.deactivate();
      }
    });
    $("#selectRectPolygon").click(function () {

      map.bufferBtnActive = false;
      map.polygonsearchtb.isActive = !map.polygonsearchtb.isActive;
      if (map.polygonsearchtb.isActive == true)
      {
        map.tooltip.setMessage("Hold down the cursor and drag to draw on the map.");
        map.tooltip.show();
        map.polygonsearchtb.activate(esri.toolbars.Draw.RECTANGLE);
      }
      else
      {
        map.tooltip.hide();
        map.polygonsearchtb.deactivate();
      }
    });
  }

  function getWellsInGraphicExtent(geom, memory)
  {
//        clearWellSearchWin();
    var poly = esri.geometry.webMercatorToGeographic(new esri.geometry.Polygon(geom));
    var extent = poly.getExtent();
    var json_extent = extent.toJson();


    $.ajax({
      url: map.querytools.SERVER_ROOT + "/contour/wellsInExtentOfBoundary",
      data: {json: JSON.stringify(json_extent)},
      method: "POST",
      async: false,
      error: function (XHR, textStatus) {
        console.log(window.SERVER_ROOT + "/contour/wellsInExtentOfBoundary call error:" + textStatus);
      },
      success: function (well_points) {
        var wellsWithinDistr = [];
        
        var graphicMap = {};
        var count = 0;
        for (var i = 0; i < well_points.length; i++) {
          var point = new Point(parseFloat(well_points[i].longitude), parseFloat(well_points[i].latitude));
          if (poly.contains(point))
          {
            count++;
            well_points[i].label = count;
            var attr = {title: well_points[i].well_number, label: well_points[i].label, id: well_points[i].id};

            wellsWithinDistr.push(well_points[i]);
            var symbol = newWellHighlightSymbol();
            //Add a text graphic here indicating the
            var graphic = new esri.Graphic(point, symbol);
            graphic.attributes = attr;
            map.getLayer('wellSearchLyr').add(graphic);

            var font = new esri.symbol.Font().setSize("10pt").setWeight(esri.symbol.Font.WEIGHT_BOLD);
            var textSymbol = new esri.symbol.TextSymbol(well_points[i].label, font, new dojo.Color([77, 127, 185])).setOffset(0, -5);

            var graphic2 = new esri.Graphic(point, textSymbol);
            graphic2.attributes = attr;
            map.getLayer('wellSearchLyr').add(graphic2);

//            graphicMap[well_points[i].well_number] = graphic;
            graphicMap[well_points[i].id] = graphic;
          }
        }

        var content = '<table class="resultsTable"><tr>'
                + '<th><input type="checkbox" class="checkAll"/></th>'
                + '<th>#</th>'
//                + '<th>State Well Number</th>'
                + '<th>Local Well Name</th>'
                + '<th>Total Depth(ft)</th>'
                + '<th>Last Measurement Date</th>'
                + '</tr>';
        if (memory)
          content += memory.join('');
        var item, row;
        for (var i = 0; i < wellsWithinDistr.length; i++) {
          item = wellsWithinDistr[i];
          row = '<tr class="dataRow">'
                  + '<td>'
                  + '<input type="checkbox"  '
                  + 'objectid="' + item.id + '"  '
                  + 'sitecode="' + item.site_code + '"  '
                  + 'localwellid="' + item.localwellid + '"  '
                  + 'statewellnum="' + item.well_number + '" '

//ZACK ADDED THIS HERE
//                        +'lastmeasuredate="'+item.lastmeasuredate+'" '
//                        +'firstmeasuredate="'+item.firstmeasuredate+'" '
//                        +'top="'+item.high+'" '
//                        +'bottom="'+item.low+'" '
//                        +'totaldepth="'+item.totaldepth+'" '

                  + 'class="checkRow"/>'
                  + '</td>'
                  + '<td>' + item.label + '</td>'
//                  + '<td class="wellname">' + item.well_number + '</td>'
                  + '<td class="wellname">' + item.localwellid + '</td>'
                  + '<td>' + item.totaldepth + '</td>'
                  + '<td>' + item.lastmeasuredate + '</td>'
                  + '</tr>';
          content += row;
          if (memory !== undefined && memory !== null)
            memory.push(row);
        }
        content += '</table>';
        showWellSearchResults(content, graphicMap, wellsWithinDistr.length);
      }
    });
  }

  function searchWellsByName(well_points)
  {
    var wellsWithinDistr = [];
    var graphicMap = {};
    var count = 0;

    for (var i = 0; i < well_points.length; i++) {
      if (well_points[i].type === 'well')
      {
        var point = new Point(parseFloat(well_points[i].longitude), parseFloat(well_points[i].latitude));
        count++;
        well_points[i].label = count;
        var attr = {title: well_points[i].wellnumber, label: well_points[i].label, id: well_points[i].id};

        wellsWithinDistr.push(well_points[i]);
        var symbol = newWellHighlightSymbol();
        //Add a text graphic here indicating the
        var graphic = new esri.Graphic(point, symbol);
        graphic.attributes = attr;
        map.getLayer('wellSearchLyr').add(graphic);

        var font = new esri.symbol.Font().setSize("10pt").setWeight(esri.symbol.Font.WEIGHT_BOLD);
        var textSymbol = new esri.symbol.TextSymbol(well_points[i].label, font, new dojo.Color([77, 127, 185, 1])).setOffset(0, -5);

        var graphic2 = new esri.Graphic(point, textSymbol);
        graphic2.attributes = attr;
        map.getLayer('wellSearchLyr').add(graphic2);

//        graphicMap[well_points[i].wellnumber] = graphic;
        graphicMap[well_points[i].id] = graphic;
      }
    }
    if (wellsWithinDistr.length > 0)
    {
      var extent = graphicsUtils.graphicsExtent(map.getLayer('wellSearchLyr').graphics);
      map.setExtent(extent, true);

    }

    var content = '<table class="resultsTable"><tr>'
            + '<th><input type="checkbox" class="checkAll"/></th>'
            + '<th>#</th>'
//            + '<th>State Well Number</th>'
            + '<th>Local Well Name</th>'
            + '<th>Total Depth(ft)</th>'
            + '<th>Last Measurement Date</th>'
            + '</tr>';
    var item, row;
    for (var i = 0; i < wellsWithinDistr.length; i++) {
      item = wellsWithinDistr[i];
      row = '<tr class="dataRow">'
              + '<td><input type="checkbox"  '
              + 'objectid="' + item.id + '"  '
              + 'sitecode="' + item.sitecode + '"  '
              + 'localwellid="' + item.localwellid + '"  '
              + 'statewellnum="' + item.wellnumber + '" '

//ZACK ADDED THIS HERE
//                        +'lastmeasuredate="'+item.lastmeasuredate+'" '
//                        +'firstmeasuredate="'+item.firstmeasuredate+'" '
//                        +'top="'+item.high+'" '
//                        +'bottom="'+item.low+'" '
//                        +'totaldepth="'+item.totaldepth+'" '

              + 'class="checkRow"/></td>'
              + '<td>' + item.label + '</td>'
//              + '<td class="wellname">' + item.wellnumber + '</td>'
              + '<td class="wellname">' + item.localwellid + '</td>'
              + '<td>' + item.totaldepth + '</td>'
              + '<td>' + item.lastmeasuredate + '</td>'
              + '</tr>';
      content += row;
    }
    content += '</table>';
    showWellSearchResults(content, graphicMap, wellsWithinDistr.length);
  }
  function initBufferSearch() {
    $('#wellSearchResultsBtn').click(function () {
      $('#searchResultsDialog').dialog('open');
    });
    map.on("click", onMapClick);

    $("#bufferSearchBtn").click(function () {
      map.polygonsearchtb.deactivate();
      if (map.bufferBtnActive == undefined)
        map.bufferBtnActive = false;

      map.bufferBtnActive = !map.bufferBtnActive;
      if (map.bufferBtnActive == true)
      {
        map.tooltip.setMessage("Click on the map to search for nearby wells.");
        map.tooltip.show();
      }
      else
      {
        map.tooltip.hide();
      }
    });
  }

  function isSearchWellsOpen() {
    return $('#searchWellsWindow').dialog('isOpen');
  }

  function onMapClick(evt)
  {
    if (map.bufferBtnActive === true)
    {
      map.bufferBtnActive = false;
      map.querytools.doBufferSearch(evt.mapPoint, showBufferSearchResults);
    }
  }

  function showBufferSearchResults(res, newpoint, distance)
  {
    clearWellSearchWin();
    var circle = new esri.geometry.Circle({
      center: newpoint,
      radius: distance,
      radiusUnit: "esriMiles",
      geodesic: true
    });

    var symbol = new esri.symbol.SimpleFillSymbol().setColor(null).outline.setColor([77, 127, 185], 3);

    var circleGraphic = new esri.Graphic(circle, symbol, {id: 'circle'});
    map.getLayer('bufferSearchUtilityLyr').add(circleGraphic);

    var center = new PictureMarkerSymbol(window.SERVER_ROOT + "/resources/images/bluemarker.png", 23, 35);

    var centergraphic = new Graphic(newpoint, center, {id: 'center'});
    map.getLayer('bufferSearchUtilityLyr').add(centergraphic);

    makeGraphicDraggable(centergraphic, circleGraphic);

    var graphicMap = {};

    for (var i = res.length - 1; i >= 0; i--)
    {
      res[i].label = i + 1;
      var attr = {title: res[i].wellname, label: res[i].label, id: res[i].id};
      var point = new esri.geometry.Point({
        latitude: res[i].latitude,
        longitude: res[i].longitude
      });
      var symbolp = newWellHighlightSymbol();
      var graphic = new esri.Graphic(point, symbolp);
      graphic.attributes = attr;
      map.getLayer('wellSearchLyr').add(graphic);

      var font = new esri.symbol.Font().setSize("10pt").setWeight(esri.symbol.Font.WEIGHT_BOLD);
      var textSymbol = new esri.symbol.TextSymbol(res[i].label, font, new dojo.Color([77, 127, 185])).setOffset(0, -5);

      var graphic2 = new esri.Graphic(point, textSymbol);
      graphic2.attributes = attr;
      map.getLayer('wellSearchLyr').add(graphic2);

//      graphicMap[res[i].wellname] = graphic;
//      alert(res[i].id);
      graphicMap[res[i].id] = graphic;
    }
    addBufferResultsToTable(res, graphicMap);
  }

  function makeGraphicDraggable(centergraphic, circleGraphic)
  {

    var Edit = new EditGraphic(map);
    var Edit2 = new EditGraphic(map, {uniformScaling: true});

    Edit2.deactivate();
    Edit.activate(EditGraphic.MOVE, centergraphic, null);

    Edit.on("graphic-move-stop", function (graphic, transform) {
      map.querytools.doBufferSearch(graphic.graphic.geometry, showBufferSearchResults);
      Edit2.deactivate();
    });


    //////////////////////

    Edit2.activate(EditGraphic.SCALE, circleGraphic, {});
    Edit2.on("scale-stop", function (graphic, info) {
      var polyline = {
        "paths": [[[graphic.graphic.geometry.getCentroid().x, graphic.graphic.geometry.getCentroid().y], [graphic.graphic.geometry.getPoint(0, 0).x, graphic.graphic.geometry.getPoint(0, 0).y]]],
        "spatialReference": {"wkid": 102100}
      };
      polyline = new esri.geometry.Polyline(polyline);
      polyline = esri.geometry.webMercatorToGeographic(polyline);
      var dist = esri.geometry.geodesicLengths([polyline], esri.Units.MILES);
      $("#bufferTextbox").val(Number(dist).toFixed(3));
      Edit2.deactivate();
      map.querytools.doBufferSearch(centergraphic.geometry, showBufferSearchResults);
    });


    $("#map").on("clearMap", function () {
      Edit2.deactivate();
    });
  }

  function newWellFromURLSymbol() {
    return new esri.symbol.SimpleMarkerSymbol(
            esri.symbol.SimpleMarkerSymbol.STYLE_CIRCLE, //shape
            25, //size
            new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([0, 0, 0]), 1), //outline
            new dojo.Color([255, 0, 0, 0.5]), //fill color
            25);//size?
  }

  function newWellHighlightSymbol() {
    return new esri.symbol.SimpleMarkerSymbol(
            esri.symbol.SimpleMarkerSymbol.STYLE_CIRCLE, //shape
            25, //size
            new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([77, 127, 185]), 2.5), //outline rgba(77, 127, 185, 1)
            new dojo.Color([255, 255, 255, 0.5]), //fill color
            25);//size?
  }

  function newWellCheckedHighlightSymbol() {
    return new esri.symbol.SimpleMarkerSymbol(
            esri.symbol.SimpleMarkerSymbol.STYLE_CIRCLE, //shape
            25, //size
            new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([0, 0, 0]), 1), //outline
            new dojo.Color([70, 197, 251, 0.9]), //fill color
            25);//size?
  }

  function showWellSearchResults(content, wellMap, totalwells) {

    $("#wellsFound").html("Wells Found: " + totalwells);
    $("#wellsSelected").html(" Wells Selected: 0");

//        content = "<div>Wells Found: "+totalwells+"</div>"+content;

    if (totalwells != 0)
    {
      $(".lowerhalf").show();
      $('#wellsGridContainer').html(content);
    }
    else
    {
      $('#wellsGridContainer').html("No wells found, [Add a well link will go here]");
    }

    $('#wellsGridContainer .checkAll').change(function (e) {
      $('#wellsGridContainer .checkRow').prop('checked', this.checked);

      var isChecked = this.checked;
      $('#wellsGridContainer .checkRow').each(function () {
        setRowBackground($(this).parent().parent(), isChecked);
      });
      var symbolFunc = this.checked ? newWellCheckedHighlightSymbol : newWellHighlightSymbol;

      $.each(wellMap, function (key, value) {
        value.setSymbol(symbolFunc());
      });

      $("#wellsSelected").html(" Wells Selected: " + $('#wellsGridContainer .checkRow:checked').length);
    });
    $('#wellsGridContainer .dataRow').each(function () {
      var $this = $(this);
      $this.find('.checkRow').change(function (e) {
        var symbolFunc = $(this).prop('checked') ? newWellCheckedHighlightSymbol : newWellHighlightSymbol;
//        wellMap[$this.find('.wellname').text()].setSymbol(symbolFunc());
//        alert($this.find('.checkRow').attr("objectid"));
        wellMap[$this.find('.checkRow').attr("objectid")].setSymbol(symbolFunc());
        setRowBackground($(this).parent().parent(), $(this).prop('checked'));
        $("#wellsSelected").html(" Wells Selected: " + $('#wellsGridContainer .checkRow:checked').length);
      });
    });
    function setRowBackground(row, checked)
    {
      if (checked)
        row.css("background-color", "rgba(70, 197, 251, 0.9)");
      else
        row.css("background-color", "#ffffff");
    }
    function setOutline(color, width) {
      return function () {
//        var graphic = wellMap[$(this).find('.wellname').text()];
        var graphic = wellMap[$(this).find('.checkRow').attr("objectid")];
        graphic.symbol.setOutline(new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color(color), width));
        graphic.setSymbol(graphic.symbol);
      };
    }
    $('#wellsGridContainer .dataRow').hover(setOutline([255, 175, 0], 3), setOutline([77, 127, 185], 2.5));
  }

  function addBufferResultsToTable(res, graphicMap) {
    var content = '<table class="resultsTable"><tr>'
            + '<th><input type="checkbox" class="checkAll"/></th>'
            + '<th>#</th>'
            + '<th>Dist (mi)</th>'
//            + '<th>State Well Number</th>'
            + '<th>Local Well Name</th>'
            + '<th>Total Depth(ft)</th>'
            + '<th>Last Measurement Date</th>'
            + '</tr>';
    var item;
    for (var i = 0; i < res.length; i++) {
      item = res[i];
      content += '<tr class="dataRow">'
              + '<td><input type="checkbox" '
              + 'objectid="' + item.id + '" '
              + 'sitecode="' + item.sitecode + '"  '
              + 'localwellid="' + item.localwellid + '"  '
              + 'statewellnum="' + item.wellname + '" '
//                +'lastmeasuredate="'+item.lastmeasuredate+'" '
//                +'firstmeasuredate="'+item.firstmeasuredate+'" '
//                +'top="'+item.high+'" '
//                +'bottom="'+item.low+'" '
//                +'totaldepth="'+item.totaldepth+'" '
              + 'class="checkRow"/></td>'

//ZACK ADDED THIS HERE

              + '<td>' + item.label + '</td>'
              + '<td>' + item.distance.toFixed(4) + '</td>'
//              + '<td class="wellname">' + item.wellname + '</td>'
              + '<td class="wellname">' + item.localwellid + '</td>'
              + '<td>' + item.totaldepth + '</td>'
              + '<td>' + item.lastmeasuredate + '</td>'
              + '</tr>';
    }
    content += '</table>';
    showWellSearchResults(content, graphicMap, res.length);
  }

  function getBufferSearchSelected() {
    var results = [];
    $('#wellsGridContainer .dataRow').each(function () {
      var $this = $(this);
      var tempObj = new Object();
      if ($this.find('.checkRow').prop('checked'))
      {
        tempObj.stationid = $this.find('.checkRow').attr("objectid");
        tempObj.localwellid = $this.find('.checkRow').attr("localwellid");
        tempObj.statewellnum = $this.find('.checkRow').attr("statewellnum");
        tempObj.sitecode = $this.find('.checkRow').attr("sitecode");
//        alert(tempObj.sitecode);
//        tempObj.lastmeasuredate = $this.find('.checkRow').attr("lastmeasuredate");
//        tempObj.firstmeasuredate = $this.find('.checkRow').attr("firstmeasuredate");
//        tempObj.top_perf = $this.find('.checkRow').attr("top");
//        tempObj.bottom_perf = $this.find('.checkRow').attr("bottom");
//        tempObj.total_depth_ft = $this.find('.checkRow').attr("totaldepth");

        results.push(tempObj);
      }

    });
    console.log(results);
    return results;
  }

  function doSurfaceProfile(evt)
  {
    map.getLayer('surfaceProfileLyr').clear();
    map.surfaceprofiletoolbar.deactivate();
    var geom = evt.geometry;
    // Create an ElevationService.
    var symbol;
    switch (evt.geometry.type) {
      case "point":
      case "multipoint":
        symbol = new SimpleMarkerSymbol();
        break;
      case "polyline":
        symbol = new SimpleLineSymbol();
        break;
      default:
        symbol = new SimpleFillSymbol();
        break;
    }

    var graphic = new Graphic(evt.geometry, symbol);
    map.getLayer("surfaceProfileLyr").add(graphic);



    var elevator = new google.maps.ElevationService();
    chart = new google.visualization.AreaChart(document.getElementById('surfaceProfile_content'));


    var geographicShape = esri.geometry.webMercatorToGeographic(geom);
    var path = [];

    for (var ring in geographicShape.paths) {
      for (var i = 0; i < geographicShape.paths[ring].length; i++) {
        var lng = geographicShape.paths[ring][i][0];
        var lat = geographicShape.paths[ring][i][1];
        path.push(new google.maps.LatLng(lat, lng));
      }
    }


    // Create a PathElevationRequest object using this array.
    // Ask for 256 samples along that path.
    var pathRequest = {
      path: path,
      samples: 100
    };
    // Initiate the path request.
    elevator.getElevationAlongPath(pathRequest, plotElevation);
  }

  // Takes an array of ElevationResult objects, draws the path on the map
  // and plots the elevation profile on a Visualization API ColumnChart.
  function plotElevation(results, status) {

    if (status !== google.maps.ElevationStatus.OK) {
      return;
    }
    var elevations = results;
    // Extract the elevation samples from the returned results
    // and store them in an array of LatLngs.
    var elevationPath = [];
    for (var i = 0; i < results.length; i++) {
      elevationPath.push(elevations[i].location);
    }
    var length = google.maps.geometry.spherical.computeLength(elevationPath);
    var distanceSegment = length / 100;
    // Extract the data from which to populate the chart.
    // Because the samples are equidistant, the 'Sample'
    // column here does double duty as distance along the
    // X axis.
    var data = new google.visualization.DataTable();
    data.addColumn('number', 'Distance');
    data.addColumn('number', 'Elevation');
    for (var i = 0; i < results.length; i++) {
      data.addRow([distanceSegment * i, elevations[i].elevation]);
    }

    // Draw the chart using the data within its DIV.
    chart.draw(data, {
      height: 150,
      legend: 'none',
      titleY: 'Elevation (m)',
      titleX: 'Distance (m)'
    });

  }

  function addMeasureResToWin(geom)
  {
    var areasAndLengthParams = new esri.tasks.AreasAndLengthsParameters();
    areasAndLengthParams.lengthUnit = esri.tasks.GeometryService.UNIT_SQUARE_MILES;
    areasAndLengthParams.areaUnit = esri.tasks.GeometryService.UNIT_SQUARE_MILES;

    map.geometryService.simplify([geom], function (simplifiedGeometries) {
      areasAndLengthParams.polygons = simplifiedGeometries;
      map.geometryService.areasAndLengths(areasAndLengthParams);
    });
  }


  function convertToGmaps(geom)
  {
    var geographicShape = esri.geometry.webMercatorToGeographic(geom);
    var path = [];


    // Construct the polygon.
    var shape;

    var stats = new Object();
    stats.length = 0;
    stats.area = 0;
    switch (geom.type) {
      case "polyline":
        for (var ring in geographicShape.paths)
        {
          for (var i = 0; i < geographicShape.paths[ring].length; i++)
          {
            var lng = geographicShape.paths[ring][i][0];
            var lat = geographicShape.paths[ring][i][1];
            path.push(new google.maps.LatLng(lat, lng));
          }
        }

        stats.length = google.maps.geometry.spherical.computeLength(path);

        break;
      case "polygon":
        for (var ring in geographicShape.rings)
        {
          for (var i = 0; i < geographicShape.rings[ring].length; i++)
          {
            var lng = geographicShape.rings[ring][i][0];
            var lat = geographicShape.rings[ring][i][1];
            path.push(new google.maps.LatLng(lat, lng));
          }
        }

        stats.area = google.maps.geometry.spherical.computeArea(path);
        break;
    }

    return stats;
  }

  function addToMeasureWindow(shapeStats)
  {
    if (shapeStats.length != 0)
    {
      $("#measure_content").append("<div class='measurement_res_box'>Length: <span class='measurement_value' type='length' value='" + shapeStats.length + "'></span><span class='measurement_unit'></span></div>");
    }
    if (shapeStats.area != 0)
    {
      $("#measure_content").append("<div class='measurement_res_box'>Area: <span class='measurement_value'  type='area' value = '" + shapeStats.area + "'></span><span class='measurement_unit'></span> </div>");
    }
    changeMeasurementUnits();
  }

  function changeMeasurementUnits()
  {
    var unit = $("#measurement_units").val();
    $(".measurement_value").each(function (index) {
      var type = $(this).attr("type");
      var meters = $(this).attr("value");
      var output;
      if (type === "area")
      {
        var root_meters = Math.sqrt(meters);
        var ft = root_meters * 3.2808;

        switch (unit)
        {

          case "feet":
            var ftsq = Math.pow(ft, 2);  // a to the power b
            output = ftsq;
            break;
          case "mile":
            output = Math.pow((ft / 5280), 2);
            break;
          case "meter":
            output = meters;
            break;
          case "kilometer":
            output = Math.pow((root_meters / 1000), 2);

            break;
        }
        $(this).text(Number(output).toFixed(3));
      }
      else
      {
        switch (unit)
        {
          case "feet":
            output = meters * 3.2808;
            break;
          case "mile":
            output = meters * 3.2808 / 5280;
            break;
          case "meter":
            output = meters;
            break;
          case "kilometer":
            output = meters / 1000;
            break;
        }
        $(this).text(Number(output).toFixed(3));
      }

    });

    $(".measurement_unit").each(function (index) {
      switch (unit)
      {
        case "feet":
          $(this).text("ft");
          break;
        case "mile":
          $(this).text("mi");
          break;
        case "meter":
          $(this).text("m");
          break;
        case "kilometer":
          $(this).text("km");
          break;
      }
    });
  }

  function clearMeasureLayer()
  {
    map.getLayer('measureLayer').clear();
    $("#measure_content").empty();
  }


  function addGraphicToMap(evt) {
    var symbol;
    map.graphicstoolbar.deactivate();
    map.showZoomSlider();
    switch (evt.geometry.type) {
      case "point":
      case "multipoint":
        symbol = new SimpleMarkerSymbol();
        break;
      case "polyline":
        symbol = new SimpleLineSymbol();
        break;
      default:
        symbol = new SimpleFillSymbol();
        break;
    }

    var graphic = new Graphic(evt.geometry, symbol);
    map.getLayer("userGraphicsLyr").add(graphic);
  }

  function measureGeometry(evt)
  {
    //    map.getLayer("selectionLayer").clear();
    var lyr = map.getLayer("measureLayer");
    //    lyr.clear();
    var symbol;
    switch (evt.geometry.type) {
      case "point":
      case "multipoint":
        symbol = new esri.symbol.SimpleMarkerSymbol();
        break;
      case "polyline":
        symbol = new esri.symbol.SimpleLineSymbol();
        break;
      default:
        symbol = new esri.symbol.SimpleFillSymbol();
        break;
    }
    var graphic = new esri.Graphic(evt.geometry, symbol);
    lyr.add(graphic);
    //use something like mapClickHandler to do queries
    map.measuretoolbar.deactivate();

    var shapeStats = convertToGmaps(evt.geometry);
    addToMeasureWindow(shapeStats);

    //    addMeasureResToWin(evt.geometry);
  }


//    function initSearchAutoComplete(textid,func)
//    {
//        $( textid ).autocomplete({
//            source: function( request, response ) {
//                var value = $(textid).val().trim();
////                value = value.replace(/\s/g, '');
//                $.ajax({
//                    url: window.SERVER_ROOT+"/contour/autoComplete",
//                    data: {
//                        param: value
//                    },
//                    success: function( data ) {
//                        response( JSON.parse(data));
//                    }
//                });
//            },
//            minLength: 3,
//            select: function( event, ui ) {
//                $(textid).val(ui.item.value);
//                func();
//            }
//        });
//    }

  function showLoading()
  {
    $("#loadingimage").show();
  }
  function hideLoading() {
    $("#loadingimage").hide();
  }
  function toggleWellsInDistrict(event) {
//        console.log(event.data.value);
  }

  function doSearch()
  {
    clearWellSearchWin();
    //lat/lng
    var latLngRegEx = new RegExp(/^((\-?\d+(\.\d+)?)[ ]*[,]\s*(\-?\d+(\.\d+)?)){1,}$/);
    var value = $("#searchtext").val().trim();

    var explodedVal = value.split(",");
    for (var item in explodedVal)
    {
      explodedVal[item] = explodedVal[item].trim();
    }
    $("#resultspanel").html('<div id="wellResults"></div>'
            + '<div id="districtResults"></div>'
            + '<div id="notFoundResults"></div>'
            );
    $.ajax({
      url: map.querytools.SERVER_ROOT + "/contour/getWellsByNames"
      , data: {wellnumbers: JSON.stringify(explodedVal)}
      , success: function (data)
      {
        var wellsWithinDistr = [];
        map.getLayer("searchResultsLyr").clear();
        if (data.length !== 0) //Show results as wells
        {
          $("#searchWindow").dialog("open");
          var wells = [];
          var district = [];
          var notFound = [];
          for (var item in data)
          {
            if (data[item].type == "well") {
              wells.push(data[item]);
            }
            else if (data[item].type == "district") {
              district.push(data[item]);
            }
          }

          $("#resultspanel").html('<div id="wellResults"></div>'
                  + '<div id="districtResults"></div>'
                  + '<div id="notFoundResults"></div>'
                  );


          var resCount = 0;
          if (wells.length != 0)     //show results for wells
          {

            var wellsWithinDistr = [];
            var well_points = data;
            var graphicMap = {};
            var count = 0;
            for (var i = 0; i < well_points.length; i++) {
              well_points[i].well_number = well_points[i].wellnumber;
              var point = new Point(parseFloat(well_points[i].longitude), parseFloat(well_points[i].latitude));
              count++;
              well_points[i].label = count;
              var attr = {title: well_points[i].well_number, label: well_points[i].label, id: well_points[i].id};

              wellsWithinDistr.push(well_points[i]);
              var symbol = newWellHighlightSymbol();
              //Add a text graphic here indicating the
              var graphic = new esri.Graphic(point, symbol);
              graphic.attributes = attr;
              map.getLayer('wellSearchLyr').add(graphic);

              var font = new esri.symbol.Font().setSize("10pt").setWeight(esri.symbol.Font.WEIGHT_BOLD);
              var textSymbol = new esri.symbol.TextSymbol(well_points[i].label, font, new dojo.Color([77, 127, 185])).setOffset(0, -5);

              var graphic2 = new esri.Graphic(point, textSymbol);
              graphic2.attributes = attr;
              map.getLayer('wellSearchLyr').add(graphic2);

//              graphicMap[well_points[i].well_number] = graphic;
              graphicMap[well_points[i].id] = graphic;
            }

            var content = '<table class="resultsTable"><tr>'
                    + '<th><input type="checkbox" class="checkAll"/></th>'
                    + '<th>#</th>'
//                    + '<th>State Well Number</th>'
                    + '<th>Local Well Name</th>'
                    + '<th>Total Depth(ft)</th>'
                    + '<th>Last Measurement Date</th>'
                    + '</tr>';
            var item, row;
            for (var i = 0; i < wellsWithinDistr.length; i++) {
              item = wellsWithinDistr[i];
              row = '<tr class="dataRow">'
                      + '<td>'
                      + '<input type="checkbox"  '
                      + 'objectid="' + item.id + '"  '
                      + 'sitecode="' + item.sitecode + '"  '
                      + 'localwellid="' + item.localwellid + '"  '
                      + 'statewellnum="' + item.wellnumber + '" '

                      //ZACK ADDED THIS HERE
//                                            +'lastmeasuredate="'+item.lastmeasuredate+'" '
//                                            +'firstmeasuredate="'+item.firstmeasuredate+'" '
//                                            +'top="'+item.high+'" '
//                                            +'bottom="'+item.low+'" '
//                                            +'totaldepth="'+item.totaldepth+'" '

                      + 'class="checkRow"/>'
                      + '</td>'
                      + '<td>' + item.label + '</td>'
//                      + '<td class="wellname">' + item.well_number + '</td>'
                      + '<td class="wellname">' + item.localwellid + '</td>'
                      + '<td>' + item.totaldepth + '</td>'
                      + '<td>' + item.measuredate + '</td>'
                      + '</tr>';
              content += row;

            }
            content += '</table>';
//                            showWellSearchResults(content, graphicMap);
            $("#wellResults").html(content);
          }
          else
          {
            $("#wellResults").html("");
          }

          if (district.length != 0) //show results for water districts
          {
            var html = "<h4>Water Districts Found</h4>";
            html += "<table>";
            html += "<tr><th></th><th>Water District</th></tr>";

            for (var item in district)
            {
              resCount++;
              html += "<tr><td>" + resCount + "</td><td>" + district[item].district + "</td></tr>";


              var geom = JSON.parse(district[item].esri_json);
              var poly = new esri.geometry.Polygon(geom);
              var extent = poly.getExtent();
              var json_extent = extent.toJson();

              $.ajax({
                url: window.SERVER_ROOT + "/contour/wellsInExtentOfBoundary",
                data: {json: JSON.stringify(json_extent)},
                method: "POST",
                async: false,
                error: function (XHR, textStatus) {
                  console.log(window.SERVER_ROOT + "/contour/wellsInExtentOfBoundary call error:" + textStatus);
                },
                success: function (well_points) {

                  for (var item in well_points) {
                    var point = new Point(parseFloat(well_points[item].longitude), parseFloat(well_points[item].latitude));
                    if (poly.contains(point))
                    {
                      wellsWithinDistr.push(well_points[item]);
                    }
                  }
                  if (wellsWithinDistr.length > 0) {

                    html += "</table>";
                    html += "<div id='scrollableWellDiv'>";
                    html += "<button id='toggleWellsInDistrict'>Show Wells</button>";
                    html += "<table id='wellsWithinDistrictTable'>"
                            + "<h4>Wells Within District Found</h4>"
                            + "<th>#</th>"
                            + "<th>Well Number</th>"
                            + "<th>Site Code</th>";


                    for (var item in wellsWithinDistr) {
                      html += "<tr><td>" + (+item + +1) + "</td>"
                              + "<td>" + wellsWithinDistr[item].well_number + "</td>"
                              + "<td>" + wellsWithinDistr[item].site_code + "</td></tr>";
                    }
                    html += "</table></div>";
                  } else {
                    html += "</table>";
                  }

                }
              });
              var symbol = new esri.symbol.SimpleFillSymbol();
              var graphic = new esri.Graphic(poly, symbol);
              map.getLayer("searchResultsLyr").add(graphic);

//                            var font = new esri.symbol.Font().setSize("12pt").setWeight(esri.symbol.Font.WEIGHT_BOLD);
//                            var textSymbol = new esri.symbol.TextSymbol(res[i].label, font, new dojo.Color([252, 0, 0, 1])).setOffset(0, -5);
//                            map.getLayer("searchResultsLyr").add(graphic);
            }

            $("#districtResults").html(html);
            $("#toggleWellsInDistrict").click(function (event) {
              if ($("#toggleWellsInDistrict").html() === "Show Wells") {
                $("#toggleWellsInDistrict").html("Hide Wells");
                map.getLayer("searchResultsLyrWellsInDist").clear();
                for (var item in wellsWithinDistr) {

                  var point = new Point(parseFloat(wellsWithinDistr[item].longitude), parseFloat(wellsWithinDistr[item].latitude));
                  var symbol = new SimpleMarkerSymbol();
                  var graphic = new Graphic(point, symbol);
                  map.getLayer("searchResultsLyrWellsInDist").add(graphic);
                }
              } else {
                $("#toggleWellsInDistrict").html("Show Wells");
                map.getLayer("searchResultsLyrWellsInDist").clear();
              }
            });
          }
          else
          {
            $("#districtResults").html("");
          }

          if (explodedVal.length !== district.length + wells.length) //if there were query params that were not found
          {
            for (var i in explodedVal)
            {
              var found = false;
              for (var x in data)
              {
                if (data[x].type == "well")
                {
                  if (data[x].wellnumber == explodedVal[i] || data[x].sitecode == explodedVal[i])
                  {
                    found = true;
                  }
                }
                else if (data[x].type === "district")
                {
                  if (data[x].district === explodedVal[i])
                  {
                    found = true;
                  }
                }
              }
              if (!found)
                notFound.push(explodedVal[i]);

              $("#notFoundResults").html("No results found for params: " + notFound.join(" | "));
            }


            //zoom to extent


          }


        }
        else
        {
          value = value.replace(/\s/g, '');
          if (latLngRegEx.test(value)) //show results as lat/lng points
          {
            if (explodedVal.length % 2 == 0)
            {
              for (var i = 0; i < explodedVal.length; i += 2)
              {
                var point = new esri.geometry.Point({
                  latitude: explodedVal[i],
                  longitude: explodedVal[i + 1]
                });
                var symbolp = new esri.symbol.SimpleMarkerSymbol();
                var graph = new esri.Graphic(point, symbolp);
                map.getLayer("searchResultsLyr").add(graph);
              }
            }
            else
            {
              console.log("Check formatting of latlng points.");
            }
          }
          else
          {
            //Do agency name search here

            //Normal Address Search here
            getAddressLocator();
          }
        }
        var resultGraphs = map.getLayer("searchResultsLyr").graphics;
        if (resultGraphs.length > 0)
        {
          var extGraphics = esri.graphicsExtent(resultGraphs);
          map.setExtent(extGraphics, true);
        }

      }
      , error: function (r, o)
      {
        console.log("failed search");
        getAddressLocator();

      }
    });
  }


  function getAddressLocator()
  {
    var options = {
      address: {
        SingleLine: $("#searchtext").val()
      },
      outFields: ["*"]
    };
    //optionally return the out fields if you need to calculate the extent of the geocoded point
    locator.addressToLocations(options);
  }


  $('#submitShape').ajaxForm({
    url: map.querytools.SERVER_ROOT + "/contour/uploadFile",
    dataType: "json",
    success: uploaded
  });

  function getWellsInMultipleGraphics(graphics) {
    var memory = [];
    for (var i = 0; i < graphics.length; i++)
      getWellsInGraphicExtent(graphics[i].geometry, memory);
  }
  function uploaded(shapes) {
    var graphics = [];
    clearWellSearchWin();
    for (var j = 0; j < shapes.length; j++) {
      if (shapes[j].length === 0)
        continue;

      var myPolygon = {geometry: {rings: shapes, type: "polygon", spatialReference: {wkid: 102100}}, symbol: {color: [77, 127, 185, 50], outline: {color: [77, 127, 185], width: 1, type: "esriSLS", style: "esriSLSSolid"}, type: "esriSFS", style: "esriSFSSolid"}};
      var graphic = new Graphic(myPolygon);
      graphics.push(graphic);
      map.getLayer('wellSearchLyr').add(graphic);
    }
    var extent = graphicsUtils.graphicsExtent(map.getLayer('wellSearchLyr').graphics);
    map.setExtent(extent, true);
    getWellsInMultipleGraphics(graphics);
  }

  function triggerListeners(name, a, b, c) {
    name = '_' + name;
    for (var i = 0; i < MapLite[name].length; i++)
      try {
        MapLite[name][i](a, b, c);
      } catch (ex) {
        console.log(ex);
      }
  }

  function openInfoWindow(evt, distance)
  {
    if (evt.graphic.attributes == undefined || evt.graphic.geometry == undefined)
      return;
    if (distance == undefined)
    {
      $("#distance").val(1);
      distance = 1;
    }

    var drawTabs = false;
    if (dijit.byId("infoTabContainer") == undefined)
      drawTabs = true;
    else {
      $('#baseinfoDiv').empty();
      $('#nearestwellscontent').empty();
      $('#wsegraph').empty();
    }

    var chosenWell = evt.graphic.attributes.id;
    var chosenWellName = evt.graphic.attributes.title;
    map.infoWindow.setTitle(evt.graphic.attributes.title);
    map.infoWindow.resize(600, 500);
    
    if (hideNearbyTab == true)
    {
      var tabpane = "<div id='leftCol' style='float:left;width: 40%;'><div id='infoTabContainer'></div></div>";
      var helperspan = "<div style='font-size: 8px; margin-left: 70px;'>Mouse left Click and drag to zoom in and double click to zoom full.</div>";
      var graphpane = "<div id='rightCol' style='float:right;width:60%;height: 250px;'><div id='wsegraph' style='width: 100%;height: 90%;'></div>" + helperspan + "</div>";
      var highlightwellbtn = "<button class='highlightwellbtn'>Select</button>";
      var button = "<button type='button' onClick=\"download('";

      button = button + "')\">Download</button>";
      if (drawTabs)
      {
        map.infoWindow.setContent(tabpane + highlightwellbtn + graphpane);
        map.infoWindow.show(evt.mapPoint);
        DraggableInfowindow.setScreenPoint(evt.mapPoint, true);
        var tc = new dijit.layout.TabContainer({
          tabPosition: "top-h",
          tabStrip: true,
          doLayout: true
        }, "infoTabContainer");
        tc.startup();
        var metapane = new dijit.layout.ContentPane(
                {
                  title: "Base Info", //: "+chosenWell,
                  content: "<div id='baseinfoDiv' style='height:200px;'></div>", //
                  selected: false,
                  closable: false
                });

        tc.addChild(metapane);
      }
      else
      {
        map.infoWindow.show(evt.mapPoint);
        DraggableInfowindow.setScreenPoint(evt.mapPoint, true);
      }

      $("#distance").off("change");
      $("#distance").change(function (e) {
        openInfoWindow(evt, $("#distance option:selected").val());
      });

      $(".highlightwellbtn").off();
      $(".highlightwellbtn").on("click", function () {
        highlightwellintable(chosenWell);
        map.infoWindow.hide();
      });
      map.querytools.getWellInfoById(chosenWell, baseinfo);
      map.querytools.getWellMeasurements([chosenWell], [chosenWellName], drawWSEgraph);

      DraggableInfowindow.setScreenPoint(evt.mapPoint, true);
    }
    else
    {
      $.ajax({
        url: map.querytools.SERVER_ROOT + "/contour/nearestWells",
        data: {
          lat: evt.graphic.geometry.getLatitude(),
          lng: evt.graphic.geometry.getLongitude(),
          wellNumber: chosenWell,
          distance: distance
        },
        success: function (res) {
          var tabpane = "<div id='leftCol' style='float:left;width: 40%;'><div id='infoTabContainer'></div></div>";
          var helperspan = "<div style='font-size: 8px; margin-left: 70px;'>Mouse left Click and drag to zoom in and double click to zoom full.</div>";
          var graphpane = "<div id='rightCol' style='float:right;width:60%;height: 250px;'><div id='wsegraph' style='width: 100%;height: 90%;'></div>" + helperspan + "</div>";
          var highlightwellbtn = "<button class='highlightwellbtn'>Select</button>";

          var selectDropdown = "Distance (Miles):<br><select id='distance' style='width:50%'><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select><br>";
          var contentdiv = "<div id='nearestwellscontent'></div>";
          var checkboxes = "";
          var button = "<button type='button' onClick=\"download('";
          var wells = [];
          res.forEach(function (value) {
            wells.push(value.wellname);
            if (value.id === chosenWell) {
              checkboxes += "<input type='checkbox' objectid='" + value.id + "' id='" + value.wellname + "' checked='checked'>" + value.wellname + "<br>";
            } else {
              checkboxes += "<input type='checkbox' objectid='" + value.id + "' id='" + value.wellname + "'>" + value.wellname + "<br>";
            }
            button = button + value.wellname + ",";
          });

          button = button + "')\">Download</button>";
          if (drawTabs)
          {
            map.infoWindow.setContent(tabpane + highlightwellbtn + graphpane);
            map.infoWindow.show(evt.mapPoint);
            DraggableInfowindow.setScreenPoint(evt.mapPoint, true);
            var tc = new dijit.layout.TabContainer({
              tabPosition: "top-h",
              tabStrip: true,
              doLayout: true
            }, "infoTabContainer");
            tc.startup();
            var metapane = new dijit.layout.ContentPane(
                    {
                      title: "Base Info", //: "+chosenWell,
                      content: "<div id='baseinfoDiv' style='height:200px;'></div>", //
                      selected: false,
                      closable: false
                    });
            var nearestwellspane = new dijit.layout.ContentPane(
                    {
                      title: "Nearest Wells", //: "+chosenWell,
                      content: "<div id='nearestwellsDiv' style='height:200px;'>" + selectDropdown + contentdiv + "</div>", //style='height: 100%;'
                      selected: true,
                      closable: false
                    });
            //htmlString +testString
            tc.addChild(metapane);
            tc.addChild(nearestwellspane);  //Commented out because client doesn't want this
          }
          else
          {
            map.infoWindow.show(evt.mapPoint);
            DraggableInfowindow.setScreenPoint(evt.mapPoint, true);
          }



          $("#distance").off("change");
          $("#distance").change(function (e) {
            openInfoWindow(evt, $("#distance option:selected").val());
//                    regenerateInfoWindow(evt, $("#distance option:selected").val());
          });

          $(".highlightwellbtn").off();
          $(".highlightwellbtn").on("click", function () {
            highlightwellintable(chosenWell);
            map.infoWindow.hide();
          });
          $("#nearestwellscontent").html(checkboxes);
          map.querytools.getWellInfoById(chosenWell, baseinfo);
          map.querytools.getWellMeasurements([chosenWell], [chosenWellName], drawWSEgraph);


          for (var i = 0; i < wells.length; i++) {
            $("#" + wells[i]).change(function () {
              var selectedWells = [];
              var wellnames = [];
              $("#nearestwellscontent input:checked").each(function () {
                selectedWells.push(Number($(this).attr("objectid")));
                wellnames.push($(this).attr("id"));
              });
              map.querytools.getWellMeasurements(selectedWells, wellnames, drawWSEgraph);
            });
          }
        }
      });
    }
  }
  function highlightwellintable(chosenWell)
  {
    $('#wellsGridContainer .dataRow').each(function () {
      var $this = $(this);   
//      alert($this.find('.checkRow').attr("objectid"));
      if (chosenWell == $this.find('.checkRow').attr("objectid"))
      {
        var cb = $this.find('.checkRow');
        $(cb).trigger("click");
      }
    });
  }

  function getCheckedWellIds()    //downloadWellCSV
  {
    var ids = [];
    $('#wellsGridContainer .dataRow').each(function () {
      var $this = $(this);
      if ($this.find('.checkRow').prop('checked'))
        ids.push($this.find('.checkRow').attr("objectid"));
    });
    return ids;
  }

//    function getWellsByName (wellids,func)
//    {
//        $.ajax({
//            url: window.SERVER_ROOT+"/contour/getWellsByNames"
//            ,data: {wellnumbers: JSON.stringify(wellids)}
//            ,success: func
//        });
//    }

  function drawWellsFromURL(well_points)
  {
    var graphicMap = {};
    for (var i = 0; i < well_points.length; i++) {
      well_points[i].well_number = well_points[i].wellnumber;
      var point = new Point(parseFloat(well_points[i].longitude), parseFloat(well_points[i].latitude));
      well_points[i].label = well_points[i].well_number;
      var attr = {title: well_points[i].well_number, label: well_points[i].label, id: well_points[i].id};

      var symbol = newWellFromURLSymbol();
      //Add a text graphic here indicating the
      var graphic = new esri.Graphic(point, symbol);
      graphic.attributes = attr;
      map.getLayer('wellFromURLLyr').add(graphic);

      var font = new esri.symbol.Font().setSize("10pt").setWeight(esri.symbol.Font.WEIGHT_BOLD);
      var textSymbol = new esri.symbol.TextSymbol(well_points[i].label, font, new dojo.Color([0, 0, 0, 1])).setOffset(0, 15);

      var graphic2 = new esri.Graphic(point, textSymbol);
      graphic2.attributes = attr;
      map.getLayer('wellFromURLLyr').add(graphic2);
    }
  }

  function initUploadFiles()
  {
    $("#shp").change(function () {
      $("#submitShape").submit();
    });
  }

  function clearWellSearchWin()
  {
    map.tooltip.hide();
    map.bufferBtnActive = false;
    map.polygonsearchtb.isActive = false;
    map.polygonsearchtb.deactivate();
    $("#wellsFound").html("Wells Found: 0");
    $("#wellsSelected").html(" Wells Selected: 0");
    map.getLayer('wellSearchLyr').clear();
    map.getLayer('bufferSearchUtilityLyr').clear();
    $('#wellsGridContainer').html("");
    $("#map").trigger("clearMap");
    $(".lowerhalf").hide();
  }

//    function getWellInfoById(chosenwell,func)
//    {
//        $.ajax({
//            url : window.SERVER_ROOT+"/contour/getWellById",
//            data : {
//                wellid: chosenwell
//            },
//            success: function(results){
//                var res = JSON.parse(results);
//                func(res);
//            }
//        });
//    }



  function baseinfo(res)
  {
    if (res.length > 0)
    {
      var html = '<table class="generictable">';
      var row = res[0];
      html += "<tr><td>Station ID: </td><td>" + row["id"] + "</td></tr>";
      html += "<tr><td>State Well Number: </td><td>" + row["statewellnum"] + "</td></tr>";
      html += "<tr><td>Site Code:</td><td>" + row["sitecode"] + "</td></tr>";
      html += "<tr><td>Local Well Name:</td><td>" + row["localwellid"] + "</td></tr>";
      html += "<tr><td>Well Depth (ft):</td><td>" + row["totaldepth"] + "</td></tr>";
      html += "<tr><td>Top Perforation (ft bgs):</td><td>" + row["high"] + "</td></tr>";
      html += "<tr><td>Bottom Perforation (ft bgs):</td><td>" + row["low"] + "</td></tr>";
      html += "<tr><td>First Measure Date:</td><td>" + row["firstmeasuredate"] + "</td></tr>";
      html += "<tr><td>Last Measure Date:</td><td>" + row["lastmeasuredate"] + "</td></tr>";
      html += "<tr><td>Well Location Description:</td><td>" + row["welllocation"] + "</td></tr>";
      html += '</table>';

      $("#baseinfoDiv").html(html);
    }
  }


  //This object is to hold generic ajax calls that are frequently used to get well data
  //

  function QueryTool()
  {
    this.SERVER_ROOT = '/EWMDataServicesWeb/service/wtims';
    this.getWellInfoById = function (chosenwell, func)
    {
      $.ajax({
        url: this.SERVER_ROOT + "/contour/getWellById",
        data: {
          wellid: chosenwell
        },
        success: function (res) {
          func(res);
        }
      });
    };
    this.getWellsByName = function (wellids, func)
    {
      $.ajax({
        url: this.SERVER_ROOT + "/contour/getWellsByNames"
        , data: {wellnumbers: JSON.stringify(wellids)}
        , success: func
      });
    };

    this.initSearchAutoComplete = function (textid, func)
    {
      var self = this;
      $(textid).autocomplete({
        source: function (request, response) {
          var value = $(textid).val().trim();
          //                value = value.replace(/\s/g, '');
          $.ajax({
            url: self.SERVER_ROOT + "/contour/autoComplete",
            data: {
              param: value
            },
            success: function (data) {
              response(data);
            }
          });
        },
        minLength: 3,
        select: function (event, ui) {
          $(textid).val(ui.item.value);
          func();
        }
      });
    };

    this.doBufferSearch = function (newpoint, func)
    {
      var lat = newpoint.getLatitude();
      var lng = newpoint.getLongitude();

      var radius = $("#bufferTextbox").val();
      var distance = 3;
      if (!isNaN(radius) && radius != "")
        distance = Number(radius);

      $.ajax({
        url: this.SERVER_ROOT + "/contour/getBufferedPoints",
        data: {
          lat: lat,
          lng: lng,
          distance: distance * 5280
        },
        success: function (results) {
          func(results, newpoint, distance);
        }
      });
    };

    this.getWellMeasurements = function (wellid, wellnames, func)
    {
      for (var i = 0; i < wellnames.length; i++)
      {
        if (wellnames[i] == null || wellnames[i] == undefined)
          wellnames[i] = "";
      }
      $.ajax({
        url: this.SERVER_ROOT + "/contour/getTimeSeries",
        data: {
          "wellNumbers": JSON.stringify(wellid),
          "wellNames": JSON.stringify(wellnames)
        },
        success: function (response) {
          func(response, wellid);
        }
      });
    };

    this.downloadWellData = function (wellids, func)
    {
      var data = '?wellids=' + encodeURIComponent(JSON.stringify(wellids));
      window.open(this.SERVER_ROOT + '/contour/downloadWellCSV' + data, '_blank');
//      $.ajax({
//        url: this.SERVER_ROOT + "/contour/downloadWellCSV",
//        data: {
//          "wellids": JSON.stringify(wellids)
//        },
//        success: function (response) {
//          func(response);
//        }
//      });
    };

    this.downloadTimeSeriesCSV = function (wellids, func)
    {
      var data = '?wellids=' + encodeURIComponent(JSON.stringify(wellids));
      window.open(this.SERVER_ROOT + '/contour/downloadTimeSeriesCSV' + data, '_blank');
//      $.ajax({
//        url: this.SERVER_ROOT + "/contour/downloadTimeSeriesCSV",
//        data: {
//          "wellids": JSON.stringify(wellids)
//        },
//        success: function (response) {
//          func(response);
//        }
//      });
    };
  }

  function MapliteTooltip(id) {
    var jqueryid = "#" + id;
    this.init = function () {
      $(document).on('mousemove', function (e) {
        $(jqueryid).css({
          left: e.pageX + 10,
          top: e.pageY + 15
        });
      });
    }
    this.show = function () {
      $(jqueryid).show();
    }
    this.hide = function () {
      $(jqueryid).hide();
    }
    this.setMessage = function (message)
    {
      $(jqueryid).html(message);
    }

    this.init();
    this.hide();
  }

  function HelpWindow(id)
  {
    var jqueryid = "#" + id;
    var contentid = jqueryid + "Content";
    this.init = function () {
      $("body").append("<div id='" + id + "' class='helpWindow'><div id='" + id + "Content'></div></div>");
      $(jqueryid).dialog({
        autoOpen: true,
        position: {my: "top+50", at: "center top"},
        resizable: false,
        width: 360,
        title: 'Help'
      });
      this.initEvents();
    };
    this.show = function () {
      $(jqueryid).dialog("open");
    };
    this.hide = function () {
      $(jqueryid).dialog("close");
    };
    this.setCustomContent = function (message)
    {
      $(contentid).html(message);
    };
    this.setPremadeContent = function (type)
    {
      switch (type)
      {
        case "text":
          var content = "Type well identifier or partial identifier into the window, accepts:" +
                  "<ul>" +
                  "<li>Local Well Name</li>" +
                  "<li>State Well Number</li>" +
                  "<li>Well ID</li>" +
                  "<li>Site Code</li>" +
                  "</ul>";

          this.setCustomContent(content);
          break;
        case "buffer":
          var content = "How to use buffer search<br>" +
                  "<p>Step 1: (Optional) Set search radius in text box if needed. Default is 5 miles</p>" +
                  "<p>Step 2: Click search button</p>" +
                  "<p>Step 3: Click on map to search for wells near that point</p>" +
                  "<p>Step 4: (Optional) Drag the blue bubble to re-center or drag the border to change the radius as needed</p>";


          this.setCustomContent(content);
          break;
        case "draw":
          var content = "How to search by shape<br>" +
                  "<p>Step 1: Click 'Freehand' or 'Extent' to select selection tool.</p>" +
                  "<p>Step 2: Drag cursor on map to search for wells within the shape drawn</p>";

          this.setCustomContent(content);
          break;
        case "upload":
          var content = "How to use file upload search<br>" +
                  "<p>Step 1: Click 'Browse...' button</p>" +
                  "<p>Step 2: Navigate to folder where the zipped shapefile is located</p>" +
                  "<p>Step 3: Select the file and click 'Open'</p>" +
                  "<p><a href='" + window.SERVER_ROOT + "/resources/sample_polygon_1.zip' target='_blank'>Sample Shapefile</a></p>";
          this.setCustomContent(content);
          break;
        default:
          this.setCustomContent("Error: Invalid content type selected.");
      }
    };
    this.initEvents = function () {
      $(".helpbox").click(function (e) {
        var type = $(this).attr("helptype");
        if (type != null || type != undefined)
        {
          setPremadeContent(type);
          show();
        }
      });
    };

    this.init();
    this.hide();
  }

});
