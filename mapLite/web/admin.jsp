<%-- 
    Document   : admin
    Created on : Dec 1, 2014, 12:48:39 PM
    Author     : ileung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/plug-ins/9dcbecd42ad/integration/jqueryui/dataTables.jqueryui.js" type="text/javascript"></script>
        <script src="http://localhost/web/Scripts/jExpand.js" type="text/javascript"></script>
        
        <link rel="stylesheet" href="//cdn.datatables.net/plug-ins/9dcbecd42ad/integration/jqueryui/dataTables.jqueryui.css" type="text/css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" type="text/css">
        <title>JSP Page</title>
        <script>
            
            $(document).ready(function() {    
                $('#table-records').DataTable({"dom": '<"toolbar">frtip'});                
                $("div.toolbar").html('<button onclick = "approve()">Approve</button><button onclick = "reject()">Reject</button>');
                loadData();                
                $('#table-records tbody').on('click','tr',function(){
                   $(this).toggleClass('selected'); 
                });                    
            } );
            
            function callback(response){
//                $('#data').html(response);
                var responseArray = response.split("_");
                for(var i = 1; i < responseArray.length; i++){
                    var objects = responseArray[i].split("=");
                    $('#table-records').dataTable().fnAddData([objects[0], objects[1], objects[2], objects[3], objects[4], objects[5]]);
                }
                $('#table').css("display","block");
                
                
                
            }
            
            function reject(){
                var ids = "";
                var table = $('#table-records').DataTable();
                $('#table-records tbody tr.selected').each(function(){
                   ids = ids + $(this).find('td:first').html() + ",";                   
                });                
                var comments = prompt("Additional comments?","");
                var command = "http://localhost:8080/LidarRequestTool/" + "requests/reject?ids=" + ids + "&comments=" + comments;                
                $.ajax({url:command, success: loadData});
            }
            
            function approve(){
                var ids = "";
                var table = $('#table-records').DataTable();
                $('#table-records tbody tr.selected').each(function(){
                   ids = ids + $(this).find('td:first').html() + ",";                   
                });
                var comments = prompt("Additional comments?","");
                var command = "http://localhost:8080/LidarRequestTool/" + "requests/approve?ids=" + ids + "&comments=" + comments;
                $.ajax({url:command, success: loadData});
                
            }
                        
            function loadData(){
                $('#table-records').dataTable().fnClearTable();
                var command = "http://localhost:8080/LidarRequestTool/" + "requests/loadAdmin";
                $.ajax({url:command, success: callback});                
            }
             $('#example tbody').on('click', 'td.details-control', function () {
                var tr = $(this).parents('tr');
                var row = table.row( tr ); 
                var stop = true;
             });
        </script>
    </head>
    <body id = "table" style="display:none">
        <table id="table-records" class="display" cellspacing="0" width="100%" height="100%">
        <thead>
            <tr>
                <th>Request ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Status</th>
                <th>Comments</th>
                <th>Lidar List</th>
            </tr>
        </thead>
 
        <tfoot>
            <tr>
                <th>Request ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Status</th>
                <th>Comments</th>
                <th>Lidar List</th>
            </tr>
        </tfoot>
 
        <tbody id="data">
            
            
        </tbody>
    </table>
    </body>
</html>
