/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

define(["dojo/dnd/Moveable", 
  "dojo/on",
  "esri/geometry/screenUtils"
  ],
  function (Moveable, 
            on,
            screenUtils) {
    var centerpoint = null;
    var screenpoint = null;
    var infonode = null;
    function init(infowindow, map)
    {
      var svg = '<svg height="1" width="1" id="mySVG">'
        +'<polygon points="" style="fill:#F7F7F7;stroke:#555555;stroke-width:.5" />'
//          +'<line x1="0" y1="0" x2="0" y2="0" style="stroke:#555555;stroke-width:5" />'
      +'</svg>';
//      $("body").append(svg);
      infonode = infowindow.domNode;
      $("#map").after(svg);
      var dnd = new Moveable(infonode,{handle: $(infonode).find(".titlePane")[0]});
      on(dnd, "Move", function (e,pos,something) {
        //set the screen point here and when updatingPointer check if this is set.
        updatePointer();
      });
      
      map.on("zoom-start",function(e){
        $("#mySVG").hide();
      });
      map.on ("pan-start",function(e){
        $("#mySVG").hide();
      });
      map.on ("mouse-drag-start",function(e){
        $("#mySVG").hide();
      });
      map.on("extent-change",function(e){
        $("#mySVG").hide();
        if (infowindow.isShowing)
        {
          setTimeout(updatePointer,100);
        }
      });
      
      map.on("click",function(){
//        $("#mySVG").hide();
      });

      infowindow.on("show",function(){
        $("#mySVG").hide();
        updatePointer();
        $("#mySVG").show();
      });
      
      infowindow.on("hide",function(){
        $("#mySVG").hide();
      });
      
//      setInterval(function(){ 
//        if(infowindow.isShowing)
//        {
//          updatePointer();
//        }}, 500);
      
    }
    
    function updatePointer()
    {
      var screen = getScreenPoint();
      var center = getCenterPoint();
      var info = getInfowindowPoint();
      var linewidth = 20;
      if (screen != null && center != null)
      {
        var x = info.x ;
        var y = info.y ;
        var placeAt = calcLine();
        var draw = null;
        var screenstr = screen.x+","+screen.y;
        if(placeAt.bottom)
        {
          y = y +.5;
          draw = screenstr+' '+(x+(info.width/2)+linewidth)+","+(y+info.height)+' '+(x+(info.width/2)-linewidth)+","+(y+info.height);
        }
        else if(placeAt.top)
        {
          draw = screenstr+' '+(x+(info.width/2)+linewidth)+","+(y)+' '+(x+(info.width/2)-linewidth)+","+(y);
        }
        else if(placeAt.left)
        {
          x = x - .5;
          draw = screenstr+' '+(x)+","+(y+(info.height/2)+linewidth)+' '+(x)+","+(y+(info.height/2)-linewidth);
        }
        else if(placeAt.right)
        {
          x = x + .5;
          draw = screenstr+' '+(x+info.width)+","+(y+(info.height/2)+linewidth)+' '+(x+info.width)+","+(y+(info.height/2)-linewidth);
        }
        
        if (draw != null)
        {
          $("#mySVG polygon").attr("points", draw);
          $("#mySVG").show();
        }
        else
        {
          $("#mySVG").hide();
        }
        
      }
      else
      {
        $("#mySVG").hide();
      }
      
    }
    
    function getHorizontal()
    {
      var left = false;
      var middle = false;
      var right = false;
      var screen = getScreenPoint();
      var info = getInfowindowPoint();
      if(screen.x<info.x)
      {
        left = true;
      }
      else if(screen.x>(info.x+info.width))
      {
        right = true;
      }
      else
      {
        middle = true;
      }
      return{
        left:left,
        right: right,
        middle: middle
      }
    }
    function getVertical()
    {
      var top = false;
      var middle = false;
      var bottom = false;
      var screen = getScreenPoint();
      var info = getInfowindowPoint();
      if(screen.y<info.y)
      {
        top = true;
      }
      else if(screen.y>(info.y+info.height))
      {
        bottom = true;
      }
      else
      {
        middle = true;
      }
      return{
        top:top,
        bottom: bottom,
        middle: middle
      }
    }
    function calcLine()
    {
      var screen = getScreenPoint();
      var info = getInfowindowPoint();
      var width = infonode.scrollWidth;
      var height = infonode.scrollHeight;
      var vertical = getVertical();
      var horizontal = getHorizontal();
      var placeAt = {
        top:false,
        bottom: false,
        left: false,
        right: false
      };
      if (vertical.top && horizontal.middle){
        placeAt.top = true;
      } 
      else if(vertical.middle && horizontal.middle){
       //don't draw if it's under info win
//        placeAt.top = true;
      } 
      else if (vertical.bottom && horizontal.middle){
        placeAt.bottom = true;
      }
//-------------------------------------------------------------------  
      if (horizontal.left && vertical.middle){
        placeAt.left = true;
      } 
      else if(horizontal.middle && vertical.middle){
        //don't draw if it's under info win
//        placeAt.left = true;
      } 
      else if (horizontal.right && vertical.middle){
        placeAt.right = true;
      }
  
//-------------------------------------------------------------------
      
      if (horizontal.left && vertical.top) //top left
      {
        if (info.x-screen.x<info.y - screen.y)
           placeAt.top = true;
        else
           placeAt.left = true;
        
      }
      else if (horizontal.left && vertical.bottom)  //bottom left
      {
        if (info.x-screen.x<screen.y - info.y)
          placeAt.left = true;
        else
          placeAt.bottom = true;
        
      }
      else if (horizontal.right && vertical.top) //top right
      {
        if (screen.x - info.x<info.y - screen.y)
          placeAt.top = true;
        else
           placeAt.right= true;
      }
      else if (horizontal.right && vertical.bottom)//bottom right
      {
        if (screen.x-info.x<screen.y - info.y)
          placeAt.bottom = true;
        else
          placeAt.right = true;
      }
      
      
      
      return placeAt;
    }
    
    function getScreenPoint()
    { 
      if (screenpoint != null)
      {
        return screenUtils.toScreenGeometry(window.map.extent,window.map.width,window.map.height,screenpoint);
      }
      return null;
    }
    
    function getCenterPoint()
    {
      if (infonode != null)
      {
        var node = getInfowindowPoint();
        return {
          x:node.x+(node.width/2),
          y:node.y+(node.height/2)
        }
      }
      return null;
    }
    
    function getInfowindowPoint()
    {
      if (infonode != null)
      {
        var div = $(".infoWindow .esriPopupWrapper");

        var infowidth = $(".infoWindow .esriPopupWrapper").width();
        var infoheight = $(".infoWindow .esriPopupWrapper").height()
        var returnobj = {
          height:infoheight,
          width: infowidth,
          x:$(".infoWindow").position().left + div.position().left,
          y:$(".infoWindow").position().top + div.position().top
        }
        return returnobj;
      }
      return null;
    }
    function setScreenPoint(point,redraw)
    {
      $("#mySVG").hide();
      screenpoint = point;
      if (redraw != undefined && redraw == true)
      {
        updatePointer();
      }
       
    }
    return {
      init:init,
      setScreenPoint: setScreenPoint
    };
});
