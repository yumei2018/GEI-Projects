/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function downloadTiles(){
    $("#fileTypeSelection").css("display", "block");
    $("#fileTypeSelection").show();
}
function div_show(){
    $( "#form" ).dialog( "open" );
//    $("#popupForm").css("display", "block");
//    $("#popupForm").show();
//    $("#form").css("display", "block");
//    $("#form").show();
}
function div_hide(){
    $("#popupForm").css("display", "none");
    $("#popupForm").hide();

    $( "#form" ).dialog( "close" );
//    $("#form").css("display", "block");
//    $("#form").hide();
    $("#wellList").css("display", "block");
    $("#wellList").hide();
}
//submits the request form
function div_submit(){

}

function cancel(){
    if(downloadTile){
        $("#fileTypeSelection").css("display", "none");
        $("#fileTypeSelection").hide();
        map.graphics.remove(selectedTile);
    } else if(downloadExtent){
        $("#fileTypeSelection").css("display", "none");
        $("#fileTypeSelection").hide();
        map.graphics.remove(graphic);
    } else if(downloadUsrShape){
        $("#fileTypeSelection").css("display", "none");
        $("#fileTypeSelection").hide();
        map.graphics.remove(graphic);
    }
    $("#downloadButton").hide();
}

function cancelUpload2(){
    var input = $("#shp");
    input.replaceWith(input.val('').clone(true));
}
function cancelUpload(){
    var input = $("#upload");
    input.replaceWith(input.val('').clone(true));
}
function cancelDownload(){
    $( "#downloadParamContainter" ).dialog( "close" );
}
function newPage(response){
    var wnd = window.open("about:blank", "", "_blank");
    var page = "<html><head></head><body>" + response + " </body></html>"
    wnd.document.write(page);
}


    function drawWSEgraph(response,wellnum)
    {
        var graphTitle;
        if(typeof wellnum == 'string'){
            graphTitle = wellnum + " WSE";
        } else {
            graphTitle = "WSE";
        }
        var res = response;
        if ('' + res === res)
          JSON.parse(response);
        var colors = [];
        for(var i = 0; i < res.numberOfColumns; i++){

        }
        var $graph = $('#wsegraph').empty();
        
        if (res.data.trim().trim().match(/\r?\n/g)) {
          graph = new Dygraph($graph[0],
          res.data,
          {
              axes:{
                  x: {
                      pixelsPerLabel:35
                  }
              },
              drawGrid: true,
              strokeWidth:2,
              ylabel: 'Elevation (Ft)',
              xlabel: 'Date',
              labelsDivStyles: {
                  'text-align': 'right',
                  'background':'none'
              },
              animatedZooms: true,
              title: graphTitle,
              connectSeparatedPoints: true
          });
        }
        else {
          $graph.append($('<div style="color: red; text-align: center; padding-top: 80px;">No Data Found</div>'));
        }
    }
    function download(results){
        downloadIds = results;
//        $.ajax({
//            url: "/ContourTool/contour/downloadData",
//            data: {
//                "names" : results
//            },
//            success: function(response){
//                window.open("ContourTool/files/downloadData?fileName=" + response);
//            }
//        });
        $( "#downloadParamContainter" ).dialog( "open" );
    }

    //Color converter code
    function componentToHex(c) {
        var hex = c.toString(16);
        return hex.length == 1 ? "0" + hex : hex;
    }

    function rgbToHex(r, g, b) {
        return "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
    }

//angular.module('ui.bootstrap.lidar', ['ui.bootstrap']);
//angular.module('ui.bootstrap.lidar').controller('DropdownCtrl', function ($scope, $log) {
//  $scope.items = [
//    'The first choice!',
//    'And another choice for you.',
//    'but wait! A third!'
//  ];
//
//  $scope.status = {
//    isopen: false
//  };
//
//  $scope.toggled = function(open) {
//    $log.log('Dropdown is now: ', open);
//  };
//
//  $scope.toggleDropdown = function($event) {
//    $event.preventDefault();
//    $event.stopPropagation();
//    $scope.status.isopen = !$scope.status.isopen;
//  };
//});