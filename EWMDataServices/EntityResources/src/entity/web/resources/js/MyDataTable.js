/**
 *
 * @param {type} cfg
 * @returns {undefined}
 */
var MyDataTable = function(cfg) {
  if (typeof $.fn.DataTable == "undefined") {
    throw "The jQuery DataTable v1.10.10 plugin is not available!";
  }
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
                  || (((o instanceof jQuery) || (typeof o == "string") || (o instanceof Array)) && (o.length == 0))
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
   * @param {type} cfg
   * @returns {BasinRequestTable.self|BasinRequestTable}
   */
  function actionHandler(evt,callback) {
    try {
      if (empty(callback) || (typeof callback != "function")) {
        throw "The record and callback parameters cannot be unassigned! And the callback must be a function!";
      }
      var record = null;
      var row = null;
      if (((row = $(this).parents('tr')).length == 0)
          || (!(record = self.dataTable.row(row).data()))) {
        throw "Cannot retrieve the row data!";
      }
      var scope = null;
      if (!empty(self.listeners)) {
        scope = self.listeners.scope || self;
      }
      else {
        scope = self;
      }
      callback.call(self, evt, record, row);
    }
    catch(e){
      alert(e);
    }
  }

  /**
   *
   * @returns {BasinRequestTable.buildActions.result}
   */
  function buildActions(){
    var result = null;

    if (!empty(self.listeners)) {
      var nonActions = ["onDraw","onDrawn","onDtInit"];
      var action,actionName,actionLabel;
      var actions=[],iconstyles;
      for (var key in self.listeners) {
        if ((self.listeners.hasOwnProperty(key))
            && (typeof self.listeners[key] == "function")
            && (/on[\w]+/i.test(key))
            && (nonActions.indexOf(key) == -1)) {
          actionName=key.replace(/^on/i,"");
          if (cfg.actionNames && cfg.actionNames[key]) {
            actionLabel = cfg.actionNames[key];
          }
          else {
            actionLabel=actionName.match(/[A-Z][a-z]+/g).join(" ");
          }
          if ((self.actionIcons) && (self.actionIcons[key])) {
            iconstyles = "style='background-image: url(\"" + self.actionIcons[key] + "\");"
                    +"background-position: 5px center;"
                    +"background-repeat: no-repeat;"
                    +"background-size: 15px 15px;"
                    +"padding-left: 25px;'";
          }
          else {
            iconstyles = "";
          }
          action="<a " + iconstyles + " href='javascript:void(0);' action='" + actionName.toLowerCase() + "'>";
          action += actionLabel + "</a>";
          actions.push(action);
        }
      }

      if (actions.length > 0) {

        var content = null;
        if (actions.length == 1) {
          content = actions[0];
        }
        else  {
          content = "<ul class='menu'>\
                        <li><span class='actionmenu' style=''></span>\
                          <ul><li>" + actions.join("</li><li>") + "</li></ul>\
                         </li>\
                      </ul>";
        }

        result = {
          data:null
          ,title:"Action" //+ (actions.length > 1 ? "(s)" : "")
          ,defaultContent:content
          ,orderable:false
        };
      }
    }

    return result;
  }

  /**
   *
   * @returns {BasinRequestTable}
   */
  function initActions(){
    if ((!empty(self.dataTable))
        && (!empty(self.listeners))) {
      var action,actionName;
      for (var key in self.listeners) {
        if ((self.listeners.hasOwnProperty(key))
            && (typeof self.listeners[key] == "function")
            && (/on[\w]+/i.test(key))) {
          actionName=key.replace(/^on/i,"");
          action="a[action='" + actionName.toLowerCase() + "']";
          var callback = (function(key){
            return function(evt) {
              actionHandler.call(this,evt,self.listeners[key]);
            }
          })(key);
          self.dataTable.on("click",action,callback);
        }
      }
    }

    return self;
  }

  /**
   *
   * @returns {MyDataTable}
   */
  function renderHelpTips() {
    if (empty(self.dtConfig) || (empty(self.dtConfig.columns))
     || (!(self.dtConfig.columns instanceof Array))
     || (!empty($("img#help-tip-hover")))) {
      return self;
    }
    var columns = self.dtConfig.columns;
    var colList = "";
    var th;
    for (var i=0;i<columns.length;++i){
      if (columns[i].searchable) {
        th = null;
        if (!empty(th = self.tableEl.find("thead th:nth-child(" + (i+1) + ")"))) {
          colList += "<li>" + th.text() + "</li>";
        }
      }
    }

    if(!empty(colList)){
      var searchEl = null;
      if (empty(searchEl = self.tableEl.parent().find("input[type=search]").parent())) {
        return self;
      }
      var label = $("<img/>")
                  .attr({
                    "class":"help"
                    ,id:"help-tip-hover"
                    ,title:""
                    ,style:"margin-left:5px;"
                    ,src:window.SERVER_ROOT+"/resources/images/icons/help.png"})
                  .insertAfter(searchEl);
      var msg = "<span style='font-size:10pt;'>Search typed keywords in the following columns:</span>"
                +"<ul class='tooltip' style='list-style-type:circle;font-size:10pt;padding-left:15px;font-weight:bold;'>"
                + colList + "</ul>";
      label.tooltip({
        content: msg
      });
    }

    return self;
  }

  /**
   *
   * @returns {MyDataTable}
   */
  function renderActionMenu(){
    self.actionMenu = null;
    if (!empty(self.actionMenu = self.tableEl.find("ul.menu"))) {
      self
      .actionMenu
      .children("li")
      .each(function(){
        $(this)
        .mouseenter(function(){
          $(this).children("ul").show().css({display:"inline-block"});
        })
        .mouseleave(function(){
          $(this).children("ul").hide();
        })
        .children("ul").hide();
      });
    }
    return self;
  }

  /**
   *
   * @returns {MyDataTable}
   */
  function onDraw(){
    renderActionMenu();
    renderHelpTips();
    if (typeof self.listeners.onDraw == "function") {
      var args = arguments;
      setTimeout(function(){
        self.listeners.onDraw.apply(self, args);
      },self.eventDelay);
    }
    return self;
  }

  function onDrawn(){
    if (typeof self.listeners.onDrawn == "function") {
      var args = arguments;
      setTimeout(function(){
        self.listeners.onDrawn.apply(self, args);
      },self.eventDelay);
    }
  }

  function addListener(eventName, callback) {
    if (eventName) {
      if (typeof callback == "string") {
        callback = self.listeners[callback];
      }
      if (typeof callback == "function") {
        self.dataTable.on(eventName, function(){
          var args = arguments;
          setTimeout(function(){
            callback.call(self,args);
          },self.eventDelay);
        });
      }
    }
  }

  function initEvents(){
    addListener("error.dt",self.errorHandler);
    addListener("preXhr.dt","beforeQuery");
    addListener("preInit.dt","beforeQuery");
    addListener("draw.dt",onDraw);
    addListener("xhr.dt","ajaxLoad");
  }

  function toNoCacheUrl(url) {
    var result = url;

    if ((result) && (result.indexOf("_dc") == -1)) {
      if (result.indexOf("?") == -1) {
        result += "?_dc=";
      }
      else {
        result += "&_dc=";
      }
      result = result + Math.random();
    }

    return result;
  }

  /**
   *
   * @param {type} cfg
   * @returns {BasinRequestTable.self|BasinRequestTable}
   */
  function init(cfg){
    if (empty(cfg)) {
      throw "The config in self.init(cfg) parameter cannot be unassigned!";
    }
    if (empty(cfg.id)) {
      throw "The table id cannot be unassigned!";
    }

    if (empty(cfg.url) && empty(cfg.ajax)) {
      throw "The config url/ajax cannot be unassigned!";
    }

    if (empty(cfg.columns)) {
      throw "The column configs cannot be unassigned!";
    }
    self.actionIcons = cfg.actionIcons || {};
    delete cfg.actionIcons;
    self.listeners = cfg.listeners || {};
    delete cfg.listeners;

    var columns = cfg.columns;
    var actionColumn = null;
    if ((actionColumn = buildActions())) {
      columns.push(actionColumn);
    }
    delete cfg.columns;

    self.listeners.beforeQuery = function(){};

    if(!empty(cfg.events)) {
      self.listeners.beforeQuery = cfg.events.preInit || cfg.events.preXhr || self.listeners.beforeQuery;

      delete cfg.events;
    }

    self.tableEl = null;

    if ((self.tableEl = $("table#" + cfg.id)).length == 0){
      throw "The table id element does not exists!";
    }

    self.eventDelay = cfg.eventDelay || 1;

    if (cfg.url) {
      cfg.url = toNoCacheUrl(cfg.url);
    }

    if (cfg.ajax && cfg.ajax.url) {
      cfg.ajax.url = toNoCacheUrl(cfg.ajax.url);
    }

    self.dtConfig = {
      processing: typeof cfg.processing == "undefined" ? true : cfg.processing
      ,serverSide: typeof cfg.serverSide == "undefined" ? true : cfg.serverSide
      ,ajax: cfg.url || cfg.ajax
      ,bRetrieve:true
      ,pageLength:cfg.pageSize || 25
      ,columns: columns
      ,dom: cfg.dom || ""
      ,columnDefs: cfg.columnDefs || []
      ,buttons: cfg.buttons || []
      ,select: cfg.select || {}
      ,order: cfg.order || []
      ,info: typeof cfg.info == "undefined" || cfg.info
      ,paging:typeof cfg.paging == "undefined" || cfg.paging
      ,searching:typeof cfg.searching == "undefined" || cfg.searching
      ,lengthChange:typeof cfg.pageSelect == "undefined" || cfg.paging || cfg.pageSelect
      ,drawCallback:onDrawn
      ,bDestroy: cfg.destroyable
      ,rowsGroup: cfg.rowsGroup
      ,language:typeof cfg.language == "undefined" || cfg.language
    };

    if ((self.dtConfig.buttons) && (!self.dtConfig.dom)) {
      self.dtConfig.dom="Bfrtip";
    }

    if (!empty(cfg.defaultSort)) {
      self.dtConfig.order = cfg.defaultSort;
      delete cfg.defaultSort;
    }

    if (cfg.ajax && (cfg.ajax.autoLoad != undefined) && (cfg.ajax.autoLoad == false)) {
      self.dtConfig.deferLoading = 0;
    }
    
    delete cfg.eventDelay;
    delete cfg.order;
    delete cfg.buttons;
    delete cfg.url;
    delete cfg.ajax;
    delete cfg.pageSize;
    delete cfg.paging;
    delete cfg.columnDefs;
    delete cfg.searching;

    self.options = cfg;

    if (self.listeners.beforeDtInit) {
      self.listeners.beforeDtInit.call(self);
    }

    $.fn.dataTable.ext.errMode = 'none';
    self.dataTable = self.tableEl.DataTable(self.dtConfig);

    if (typeof self.listeners.onDtInit == "function") {
      self.listeners.onDtInit.call(self, self.dataTable);
    }

    initEvents();

    initActions();

    return self;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Public Functions">
  /**
   *
   * @param {type} evt
   * @param {type} settings
   * @param {type} technote
   * @param {type} msg
   */
  self.errorHandler = function(evt,settings,technote,msg){
    // var tableEl = this;
  }

  /**
   *
   * @returns {MyDataTable}
   */
  self.refresh=function(){
    if (!empty(self.dataTable)) {
      self.dataTable.ajax.reload(null,false);
    }
    return self;
  }

  self.show=function(){
    var $dtWrapper = null;
    if (($dtWrapper = $(self.dataTable.containers()[0])).length > 0) {
      $dtWrapper.show();
    }
    return self;
  }

  self.hide=function(){
    var $dtWrapper = null;
    if (($dtWrapper = $(self.dataTable.containers()[0])).length > 0) {
      $dtWrapper.hide();
    }
    return self;
  }

  self.getRecordCount=function(){
    return this.dataTable.rows().data().length;
  }

  self.isEmpty=function(){
    return (self.getRecordCount() == 0);
  }
  //</editor-fold>

  /**
   * 
   * @param {type} data
   * @returns {unresolved}
   */
  self.jqueryStringEscape=function(data){
    try{
      return $("<p />", {
        text:data
      })[0].innerHTML;
    }
    catch(e){alert(e);}
  };
  
  /**
   * 
   * @param {type} row
   * @returns {undefined}
   */
  self.untermindatedStringLiteralHandler=function(row){
    try{
      $.each(row,function(k,v){
        if($.type(v) === "string"){
          row[k] = v.toString().replace(/\'/g,"&#39;");
        }
      });
    }catch(e){alert(e);}
  };
  
  /**
   * initialize the configs
   */
  init(cfg);
}