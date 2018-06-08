//<editor-fold defaultstate="collapsed" desc="String Prototypes">
String.prototype.format = function (args) {
  var newStr = this;
  for (var key in args) {
    if (!args.hasOwnProperty(key)) {
      continue;
    }
    newStr = newStr.replace("{" + key + "}", args[key]);
  }
  return newStr;
}
//</editor-fold>

var BaseUtil = {
  //<editor-fold defaultstate="collapsed" desc="Url Params">
  urlParam: function(key) {
    var results = new RegExp("[\?&#]" + key + "=([^&#]*)").exec(window.location.href);
    return results ? decodeURIComponent(results[1]) || 0 : null;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="String Trims">
  ,ltrim:function(str, ch) {
    var result = null;
    if (typeof str == "string") {
      if (!ch || !ch.replace(" ","")) {
        ch = "\\s";
      }
      var reStr = "/^[" + ch + "]/g";
      var re = eval(reStr);
      result = str.replace(re,"");
    }
    return result;
  }
  ,rtrim:function(str, ch) {
    var result = null;
    if (typeof str == "string") {
      if (!ch || !ch.replace(" ","")) {
        ch = "\\s";
      }
      var reStr = "/[" + ch + "]$/g";
      var re = eval(reStr);
      result = str.replace(re,"");
    }
    return result;
  }
  ,trim:function(str,ch) {
    return BaseUtil.rtrim(BaseUtil.ltrim(str,ch),ch);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Is Empty">
  ,isEmpty:function(arg) {
    var result = (arg == undefined) || (arg == null);
    
    if (!result) {
      switch(typeof arg) {
        case "function":
          result = false;
          break;
        case "string":
          result = !Boolean(BaseUtil.trim(arg));
          break;
        case "object":
          if ((arg instanceof jQuery)
                  || (arg instanceof Array)
                  || (arg instanceof FileList)
                  || (arg instanceof NodeList)
                  || (arg instanceof HTMLCollection)){
            result = arg.length == 0;
          }
          else {
            result = true;
            for (var i in arg) {
              if (arg.hasOwnProperty(i)) {
                result = false;
                break;
              }
            }
          }
          break;
      }
    }    
    
    return result;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Validate attributes">
  ,validateAttributes:function(as) {
    if (!$.isArray(as)) {
      throw "The required attributes parameter cannot be unassigned!";
    }
    var o = this;
    var a = null;
    for (var i=0;i<as.length;++i) {
      a = as[i];
      if (BaseUtil.isEmpty(o[a])) {
        throw "The " + a + " cannot be unassigned!";
      }
    }
    return true;
  }
  //</editor-fold>
};

var BaseApp = function(){
  var self = this;
  
  //<editor-fold defaultstate="collapsed" desc="String Trims">
  self.ltrim=BaseUtil.ltrim;
  self.rtrim=BaseUtil.rtrim;
  self.trim=BaseUtil.trim;
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Fire Event">
  self.fireEvent=function(event) {
    if (self.listeners && self.listeners[event]) {
      var callback = null;
      var scope = self;
      var listener = null;
      if ((listener = self.listeners[event])) {
        switch(typeof listener) {
          case "function":
            callback = listener;
            break;
          case "object":
            if (typeof listener.handler == "function") {
              callback = listener.handler;
            }
            if (listener.scope) {
              scope = listener.scope;
            }
            break;
        }
      }
      
      if (self.isEmpty(callback)) {
        throw "The event listener is not supported!";
      }
      
      callback.apply(scope,arguments);
    }
  };
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Is Empty">
  self.isEmpty=BaseUtil.isEmpty;
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Validate Attributes">
  self.validateAttributes = function(obj,attrs){
    BaseUtil.validateAttributes.call(obj,attrs);
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Add Listener">
  self.addListener=function(eventName,callback,append){
    if (self.isEmpty(eventName)) {
      throw "The event parameter cannot be unassigned!";
    }
    if (self.isEmpty(callback)) {
      throw "The callback parameter cannot be unassigned!";
    }
    
    var existCallback = null;
    if (self.isEmpty(existCallback = self.listeners[eventName])) {
      self.listeners[eventName] = callback;
    }
    else {
      if (append) {
        self.listeners[eventName] = function(){
          existCallback.call(self, arguments);
          callback.call(self, arguments);
        };
      }
      else {
        self.listeners[eventName] = callback;
      }
    }
  };
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Url Param">
  self.urlParam = BaseUtil.urlParam;
  //</editor-fold>
};