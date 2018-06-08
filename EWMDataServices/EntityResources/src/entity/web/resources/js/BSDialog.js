var BSDialog = function(cfg){
  BaseApp.call(this);
  var self = this;
  self.validateAttributes(cfg, ["id", "title", "message"]);
  self.$dialog = $("[id='" + cfg.id+ "']");
  if (self.isEmpty(self.$dialog)) {
    self.$dialog = $('<div id="' + cfg.id + '" class="modal fade" role="dialog" data-backdrop="static">\
      <div class="modal-dialog">\
        <div class="modal-content ${DialogClasses}">\
          <div class="modal-header">\
            <button type="button" class="close" data-dismiss="modal">&times;</button>\
            <h4 class="modal-title">' + cfg.title + '</h4>\
          </div>\
          <div class="modal-body">' + cfg.message + '</div>\
          <div class="modal-footer"></div>\
        </div>\
      </div>\
    </div>');
    
    self.$buttons = $([]);
    if (self.isEmpty(cfg.buttons)) {
      cfg.buttons = [{
        id:"closebtn"
        ,handler:function(evt,dialog){
          dialog.hide();
        }
        ,"class":"btn btn-default"
        ,label:"Close"
      }]
    }
    
    var btnCfg = null;
    var $button = null;
    var handler = null;
    for (var i=0;i< cfg.buttons.length;++i) {
      btnCfg = cfg.buttons[i];
      try {
        self.validateAttributes(btnCfg,["id","handler","label"]);
        handler = (function(fn){
          return function(evt) {
            evt.preventDefault();
            fn.call(this, evt, self);
            return false;
          };
        })(btnCfg.handler)
        $button = $("<button />",{
          id:btnCfg.id
          ,html:btnCfg.label
          ,"class":btnCfg["class"]
          ,style:btnCfg.style
        })
        .click(handler).appendTo(self.$dialog.find(".modal-footer"));

        self.$buttons.add($button);
      }
      catch(e) {
        // ignore validations
      }
    }
  }
  
  self.show=function(target){
    self.$dialog.modal("show");
    
    var $target = (target ? $(target) : $("body"));
    $target.append($(".modal-backdrop").css({position: "absolute"}).show());
    $target.append(self.$dialog.css({position: "absolute"}));

    //<editor-fold defaultstate="collapsed" desc="Calculation Visible Position">
    var buffer = 20;
    var docT = $(document).scrollTop();
    var targetT = $target.offset().top;
    var $modalDialog = self.$dialog.find(".modal-dialog");
    var newT = 0;
    if (docT > targetT) {
      newT = docT - targetT;
    }
    $modalDialog.css({top: newT + buffer});
    return self;
  };
  
  self.hide=function(){
    self.$dialog.modal("hide");
    return self;
  }
  
  self.setTitle=function(title) {
    if (title) {
      self.$dialog.find(".modal-title").html(title);
    }
    return self;
  }
  self.setMessage=function(message) {
    if (message) {
      self.$dialog.find(".modal-body").html(message);
    }
    return self;
  }
  self.appendToBody = function(el){
    if(el){
      self.$dialog.find(".modal-body").html("");
      self.$dialog.find(".modal-body").append(el);
    }
  }
}