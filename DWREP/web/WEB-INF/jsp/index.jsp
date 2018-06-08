<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome DWR EP project</title>
        <script src="${pageContext.request.contextPath}/resources/js/jquery/jquery-1.11.2.min.js"></script> 
        <script type="text/javascript">window.SERVER_ROOT="${pageContext.request.contextPath}";</script>
        
        <script type="text/javascript">
        $(document).ready(function(){
            var self = this;
            self.exportBtn = $(".export_btn");            

            self.exportBtn.on("click", function() {
                var epId = $("input[name=epId]").val();
                var epNo = $("input[name=epNo]").val();
                alert("epId="+epId);
                alert("epNo="+epNo);
                $.ajax({
                    type:"POST"
                    ,url: window.SERVER_ROOT + "/exportJsonFile"
                    ,data:{epId: epId, epNo: epNo}
                    ,cache:false
                    ,success:function(data,status,jqxhr){
                        console.log(data);
//                        if(typeof(data.epName) === 'undefined'){
//                            alert("Undefined");
//                        }
                        alert("Export Successfully");
                    }
                    ,error:function(xhr, errorType, exception){
                        alert("Export Failed");
                    }
                });                  
            });
        });
        </script> 
    </head>

    <body>
        <div>
            <div><label>EP ID</label><input type="text" id="epId" name="epId"/></div>
            <div><label>EP NO</label><input type="text" id="epNo" name="epNo"/></div>
            <div><button class="export_btn" id="">Export</button></div>
        </div>
    </body>
</html>
   

