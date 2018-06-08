/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function BasemapToggler(id,render_to,map)
{
    var self = this;
    var gallery = new esri.dijit.BasemapGallery({map:map, showArcGISBasemaps: true});
    var jq_id = null;
    var current = 0;
    var extra = [
//        {
//            url: "http://services.arcgisonline.com/ArcGIS/rest/services/USA_Topo_Maps/MapServer/",
//            title:"Put a title here",
//            thumbnailUrl:"http://www.arcgis.com/sharing/rest/content/items/5d2bfa736f8448b3a1708e1f6be23eed/info/thumbnail/temposm.jpg"
//        }
    ];
    this.constructor = function()
    {
        addElements();
        gallery.on("load",function(){
            addExtraBasemaps();
            initThumbnail(current++);
        });
        
        jq_id.on("click",function(){setThumbnail(current++);});
    };
    
    function addElements()
    {
        $("#"+render_to).append('<div id="'+id+'"><div id="basemap_thumbnail"></div><div id="basemap_label"></div></div>');
        jq_id = $("#"+id);
        jq_id.css({
            "position": "absolute",
            
            "min-width": "75px",
            "max-width": "100px",
            "padding": "3px",
            "background-color": "#ffffff",
            "bottom":"30px",
            "left": "30px",
            "z-index": "50",
            "border":"1px solid",
            "border-radius":"3px",
            "font-size": "10px"
        });
        
        $("#basemap_thumbnail").css({
            "width": "100%",
            "height": "50px"
        });
        $("#basemap_label").css({
            "text-align":"center"
        });
    };
    
    function addExtraBasemaps ()
    {
        for (var i=0; i<extra.length; i++)
        {
            var newlyr = extra[i];
            var layer = new esri.dijit.BasemapLayer({
                url: newlyr.url
            });

            var basemap = new esri.dijit.Basemap({
                layers:[layer],
                title:newlyr.title,
                thumbnailUrl:newlyr.thumbnailUrl
            });
            gallery.add(basemap);
        }
    }
    function initThumbnail(current){
        var next = gallery.basemaps[(current+1)%gallery.basemaps.length];
        $("#basemap_thumbnail").css({
            "background-image" : "url('"+next.thumbnailUrl+"')",
            "background-position" : "center"
        });
        $("#basemap_label").html(next.title);
    }
    function setThumbnail(current){
        var curr = gallery.basemaps[current%gallery.basemaps.length];
        var next = gallery.basemaps[(current+1)%gallery.basemaps.length];
        gallery.select(curr.id);
        $("#basemap_thumbnail").css({
            "background-image" : "url('"+next.thumbnailUrl+"')",
            "background-position" : "center"
        });
        
        $("#basemap_label").html(next.title);
    }
    
    this.constructor();
}
