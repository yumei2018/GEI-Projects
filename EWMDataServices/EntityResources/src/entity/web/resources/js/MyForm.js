/**
 *
 * @param {type} cfg
 * @returns {undefined}
 */
var MyForm = function(cfg){
  var self = this;

  //<editor-fold defaultstate="collapsed" desc="Private Functions">
  /**
   *
   * @param {type} evt
   * @param {type} callback
   * @returns {undefined}
   */
  function empty(o) {
    var result = (o == null)
                  || (typeof o == "undefined")
                  || ((((typeof jQuery != "undefined") && (o instanceof jQuery))
                  || (typeof o == "string") || (o instanceof Array)) && (o.length == 0))
                  || ((typeof o == "number") && (isNaN(o)));
    if ((!result) && (typeof o == "object")) {
      result = true;
      for (var i in o) {
        if (o.hasOwnProperty(i)) {
          result = false;
          break;
        }
      }
    }
    return result;
  }

  /**
   *
   * @returns {MyForm}
   */
  function initFields(){
    if (empty(self.formEl)) {
      throw "The formEl cannot be unassigned!";
    }

    self.fields = {};

    self.formEl
    .find("input:not([type=submit]):not([type=button]),textarea,select")
    .each(function(){
      var key = null;
      var fields = null;
      if (this.name) {
        key = this.name;
        fields = self.formEl.find("[name='" + this.name + "']");
      }
      else if (this.id) {
        key = this.id;
        fields = $(this);
      }
      if (key && fields) {
        self.fields[key] = fields;
      }
    });

    self.submitBtn = null;

    if (empty(self.submitBtn = self.formEl.find("input[type=submit], button[type=submit]"))) {
      self.submitBtn = $("<input />",{type:"submit",value:"Submit"}).appendTo(self.formEl);
    }

//    self.submitBtn.attr("onclick","return false;");
    self.submitBtn.click(function(evt){
      evt.preventDefault();
      var btn = this;
      var response = {
        callback: function() {
          if (btn.name && !self.fields[btn.name]) {
            if (self.isFileUpload()) {
              self.fields[btn.name] = $('<input type="hidden" name="' + btn.name + '" value="' + btn.value + '"/>');
              self.submit();
              self.fields[btn.name] = null;
            }
            else {
              var data = self.formEl.serialize();
              data += '&' + btn.name + '=' + btn.value;
              self.submit(data);
            }
          }
          else {
            self.submit();
          }
        }
      };
      self.fireEvent('onSubmitClick', btn, response);
      if (!response.cancel && response.callback) {
        response.callback();
      }
      return false;
    });

    return self.fireEvent("onInitFields");
  }

  /**
   *
   * @returns {MyForm}
   */
  function initForm() {
    self.formEl = null;
    if (empty((self.formEl = $("form[id='" + cfg.id + "']")))) {
      throw "The form element with the config id " + cfg.id + " does not exists!";
    }
    self.formEl.find("input,select").keypress(function(evt){
      return evt.keyCode != 13;
    });
    initFields();
    return self.fireEvent("onInitForm");
  }

  /**
   *
   * @param {type} data
   * @returns {undefined|MyForm.self|MyForm}
   */
  function loadData(data){
    if (empty(data) || empty(self.fields)) {
      return;
    }

    for (var i in data) {
      if ((data.hasOwnProperty(i))
          && (!empty(self.fields[i]))) {
        self.fields[i].val(data[i]);
      }
    }

    return self.fireEvent("onLoadForm",data);
  }

  /**
   *
   * @param {type} cfg
   * @returns {MyForm}
   */
  function init(cfg){
    if (empty(cfg)) {
      throw "The config parameter cannot be unassigned!";
    }
    if (empty(cfg.id)) {
      throw "The config id property cannot be unassigend!";
    }

    self.listeners = cfg.listeners || {};
    self.listeners.onError = self.listeners.onError || function(e){alert(e);}
    self.validate = cfg.validate || function(){};
    self.loadForm = cfg.loadForm || loadData;
    self.asyncSubmit = typeof cfg.asyncSubmit == "undefined" || cfg.asyncSubmit;
    initForm(cfg);

    return self.fireEvent("onInit");
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Functions">
  /**
   *
   * @param {type} eventName
   * @returns {undefined}
   */
  self.fireEvent=function(eventName){
    var callback = null;
    if ((!empty(eventName))
        && (!empty(self.listeners))
        && (callback = self.listeners[eventName])
        && (typeof callback == "function")) {
      var args = [];
      for (var i=1;i<arguments.length;++i) {
        args.push(arguments[i]);
      }
      var scope = self.listeners.scope || self;
      callback.apply(scope,args);
    }
    return self;
  }

  /**
   *
   * @returns {MyForm.self|MyForm}
   */
  self.submit=function(data){
    var success = false;
    
    if (empty(self.formEl)) {
      throw "The formEl cannot be unassigned!";
    }
    var url = null;
    if (empty(url = self.formEl.attr("action"))) {
      throw "The form action cannot be unassigned!";
    }
    try {

      self.fireEvent("beforeSubmit");

      self.validate();

      var ajaxConfig = {
        url:url
        ,dataType:"json"
        ,method:"POST"
        ,async:self.asyncSubmit
        ,timeout:0
        ,success:function(response,jqxhr,opts) {
          try {
            if (!response.success) {
              throw response.error || "The system cannot process the submission. Please \
                                    contact the System Adminstrator to resolve the issue.";
            }
            success = true;
            self.fireEvent("onSuccess",response);
          }
          catch(e){
//            throw e;
            self.fireEvent("onSubmitError",e,response);
          }
        }
        ,error:function(r){
//          throw r.responseText;
          self.fireEvent("onSubmitError",r.responseText);
        }
      };

      if (empty(data)) {
        if (self.isFileUpload()) {
          data = new FormData();
          $.each(self.fields, function(k, v) {
            if ($(this).is("[type=file]")) {
              var key = $(this).attr("name");
              $.each($(this)[0].files,function(k2,v2){
                data.append(key+"."+k2,v2);
              });
            }
            else {
              data.append(k,$(v).val());
            }
          });

          ajaxConfig.cache=false;
          ajaxConfig.processData=false; // Don't process the files
          ajaxConfig.contentType=false; // Set content type to false as jQuery will tell the server its a query string request
        }
        else {
          data = self.formEl.serialize();
        }
      }

      ajaxConfig.data = data;
      self.fireEvent("preAjax",ajaxConfig);
      setTimeout(function(){
        $.ajax(ajaxConfig);
      },1);
    }
    catch(e) {
      self.fireEvent("onError",e);
    }

    return success;
  }

  self.getForm = function(){
    return self.formEl;
  }

  self.isFileUpload=function(){
    return self.formEl.attr("enctype") == "multipart/form-data";
  }
  
  self.clearForm = function(){
    for (var i in this.fields) {
      if (!this.fields.hasOwnProperty(i)) {
        continue;
      }
      if (this.fields[i].is("input[type='radio'],input[type='checkbox']")) {
        this.fields[i].prop("checked", false);
      }
      else {
        this.fields[i].val("");
      }
    }

    self.fireEvent("onClearForm");
    
    return self;
  }
  //</editor-fold>

  init(cfg);
}