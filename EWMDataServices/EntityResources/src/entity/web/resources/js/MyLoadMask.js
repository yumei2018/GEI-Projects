var MyLoadMask = function(message){
  if (!message) {
    message = "Proccessing...";
  }
  var $maskDiv = null;
  if (($maskDiv = $("div.modal#LoadMask")).length == 0) {
    $maskDiv = $('\
            <div id="LoadMask" class="modal fade" role="dialog" data-backdrop="static">\
              <div class="modal-dialog">\
                <div class="modal-content">\
                  <div class="modal-header">\
                    <h4 class="modal-title">'
                    + message
                    + '</h4>\
                  </div>\
                  <div class="modal-body">\
                    <div class="progress">\
                      <div class="progress-bar progress-bar-striped active" role="progressbar"\
                        aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:100%">\
                        </div>\
                    </div>\
                  </div>\
                </div>\
              </div>\
            </div>');
  }
  return {
    show: function(message,target) {
      if ((message != undefined) && (message != null)) {
        $maskDiv.find(".modal-header .modal-title").html(message);
      }
      $maskDiv.modal();
        
      var $target = (target ? $(target) : $("body"));
      $target.append($(".modal-backdrop").css({position:"absolute"}).show());
      $target.append($maskDiv.css({position:"absolute",top:0}));
      
      var docT = $(document).scrollTop();
      var targetT = $target.offset().top;
      var winH = $(window).height();
      var buffer = winH/3;
      var $modalDialog = $maskDiv.find(".modal-dialog");
      var newT = 0;
      if (docT > targetT) {  
        newT = docT - targetT;
      }
      $modalDialog.css({top: newT + buffer});
    }
    ,hide: function () {
      $maskDiv.modal('hide');
    }
    ,getMaskDiv:function(){
      return $maskDiv;
    }
  };
}