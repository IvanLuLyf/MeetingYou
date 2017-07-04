<%@ page import="java.text.*"%>
<%@ page language="java"
	import="java.util.*,meetingmanager.vo.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Meeting You</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="js/jquery.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">

	    var xmlHttp;

        function createXMLHttpRequest() {
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } 
            else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();                
            }
        }

        function showUsers() {
            createXMLHttpRequest();
       		var deptid=document.getElementById("selDepartments").value;   	
       		var url = "ViewUserByDeptidServlet?deptid=" + escape(deptid);           
            xmlHttp.open("GET", url, true);     
            xmlHttp.onreadystatechange = showUsersCallback;
            xmlHttp.send(null);
        }

        function showUsersCallback() {
           clearUsers();
           var selUnselectedUsers=document.getElementById("selUnselectedUsers");
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                	console.log(xmlHttp.responseText);
                	console.log(xmlHttp.responseXML);
                    var elements = xmlHttp.responseXML.getElementsByTagName("option");
                    console.log(elements);
                    for (var i = 0; i < elements.length; i++) {
	                    var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
	                    console.log(elements[i].getElementsByTagName("text")[0]);
	                    var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;                
	                    selUnselectedUsers.options.add(new Option(text,value));
                    }       
                }
            }
              
        }
        
        function clearUsers(){
         	document.getElementById("selUnselectedUsers").options.length=0;
        }
        
         function selectUsers(){
         		var selUnselectedUsers=document.getElementById("selUnselectedUsers");
         		var selSelectedUsers=document.getElementById("selSelectedUsers");     
                for(var i=selUnselectedUsers.options.length-1;i>=0;i--){
                    if (selUnselectedUsers.options[i].selected){
                        var opt=new Option(selUnselectedUsers.options[i].text,selUnselectedUsers.options[i].value);
                        opt.selected=true;
                        selSelectedUsers.options.add(opt);
                        selUnselectedUsers.options.remove(i);
                    }
                }
            }
        
        function deselectUsers(){
        		var selUnselectedUsers=document.getElementById("selUnselectedUsers");
         		var selSelectedUsers=document.getElementById("selSelectedUsers");     
                for(var i=selSelectedUsers.options.length-1;i>=0;i--){
                    if (selSelectedUsers.options[i].selected){
                        selUnselectedUsers.options.add(new Option(selSelectedUsers.options[i].text,selSelectedUsers.options[i].value));
                        selSelectedUsers.options.remove(i);
                    }
                }
                setSelected();
            }     
             
        function setSelected(){
         		var selSelectedUsers=document.getElementById("selSelectedUsers");     
                for(var i=0;i<selSelectedUsers.options.length;i++){
                    selSelectedUsers.options[i].selected=true;
                }
        }
        
        function refreshRooms(){
            createXMLHttpRequest();   
	        var begintime=document.getElementById("begintime").value;   	
	        var endtime=document.getElementById("endtime").value;  
	        
	       	var url = "RefreshRoomsServlet?begintime=" + escape(begintime)+"&endtime="+escape(endtime);           
	        xmlHttp.open("GET", url, true);     
	        xmlHttp.onreadystatechange = refreshRoomsCallback;
	        xmlHttp.send(null);
        }
        
         function refreshRoomsCallback() {
  		   clearMeetingRooms(); 
           var roomid=document.getElementById("roomid");
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                    var elements = xmlHttp.responseXML.getElementsByTagName("option");                      
                    for (var i = 0; i < elements.length; i++) {
	                    var value = elements[i].getElementsByTagName("value")[0].firstChild.nodeValue;
	                    var text = elements[i].getElementsByTagName("text")[0].firstChild.nodeValue;                
	                    roomid.options.add(new Option(text,value),i+1);
                    }       
                }
            }
        
        }
        
        function clearMeetingRooms(){
         	document.getElementById("roomid").options.length=1;
        }
        
        
        function checkTime(){
        	var txtBegintime = document.getElementById("begintime");
        	var txtEndtime = document.getElementById("endtime");
        	var beginTimestamp = new Date(txtBegintime.value).getTime();
        	var endTimestamp = new Date(txtEndtime.value).getTime();
            if (endTimestamp<=beginTimestamp) {
                classes = txtEndtime.parentNode.getAttribute("class");
                txtEndtime.parentNode.setAttribute("class", classes + " has-error");
            }else{
                classes = txtEndtime.parentNode.getAttribute("class");
                classes = classes.replace(" has-error","");
                txtEndtime.parentNode.setAttribute("class", classes);
            }
        }
	</script>
</head>
<body>
    <div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
			<%@include file="header.jsp"%>
			<div class="row">
				<div class="hidden-xs col-sm-4 col-md-2">
				<%@include file="sidebar.jsp" %>
				</div>
				<div class="col-xs-12 col-sm-8 col-md-10">
					<ul class="breadcrumb">
						<li>
							<a href="#">Meeting You</a> <span class="divider"></span>
						</li>
						<li>
							<a href="#">会议预定</a> <span class="divider"></span>
						</li>
						<li class="active">
							<a href="#">预定会议</a>
						</li>
					</ul>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								会议信息
							</h3>
						</div>
						<div class="panel-body">
							<c:if test="${requestScope.msg!=null}">
							<div id="myAlert" class="alert alert-danger">
								<a href="#" class="close" data-dismiss="alert">&times;</a>
								<strong>提示信息:</strong>${requestScope.msg}
							</div>
							</c:if>
							<form class="form-horizontal" role="form" action="BookMeetingServlet?code=add" method="post">
								<div class="form-group">
									<label class="control-label col-lg-2" for="meetingname">会议名称</label>
									<div class="col-lg-4">
									<input id="meetingname" name="meetingname" type="text" placeholder="" maxlength="10" class="form-control" required/>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2" for="capacity">预计参加人数</label>
									<div class="col-lg-4">
									<input id="capacity" name="capacity" type="text" placeholder="" maxlength="20" class="form-control" required/>
									</div>
								</div>
 								<% String strTime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()) + "T" + new SimpleDateFormat("hh:mm").format(Calendar.getInstance().getTime()); %>
								<div class="form-group">
									<label class="control-label col-lg-2" for="begintime">预计开始时间</label>
									<div class="col-lg-4">
									<input id="begintime" name="begintime" type="datetime-local" placeholder="例如2017-06-30 05:20" value="<%=strTime %>" class="form-control" required/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2" for="endtime">预计结束时间</label>
									<div class="col-lg-4">
									<input id="endtime" name="endtime" type="datetime-local" onchange="checkTime()" placeholder="例如2017-06-30 05:20" value="<%=strTime %>" class="form-control" required/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-lg-2" for="capacity">会议室名称</label>
									<div class="col-lg-4">
									<select id="roomid" name="roomid" onfocus="refreshRooms()" class="form-control" required>
										<option>请选择会议室</option>
										<c:forEach var="room" items="${requestScope.roomsList}">
										<option value="${room.roomid}">${room.name}</option>
										</c:forEach>
                                    </select>
									</div>
								</div>		

								<div class="form-group">
									<label class="control-label col-lg-2" for="description">会议说明</label>
									<div class="col-lg-4">
									<textarea class="form-control" id="description" name="description" maxlength="200" rows="5" cols="60" placeholder="200字以内的文字描述"></textarea>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-lg-2" for="description">选择参会人员</label>
									<div class="col-lg-3">
										<select id="selDepartments" class="form-control" onchange="showUsers()">
											<option>请选择部门</option>
											<c:forEach var="item" items="${requestScope.departmentsList}">
											<option value="${item.departmentid}">${item.name}</option>
											</c:forEach>
                                        </select>
                                        <br>
                                        <select id="selUnselectedUsers" class="form-control" style="height:25%;" multiple="true">
                                        </select>
									</div>
									<div class="col-lg-1">
									    <input type="button" class="btn btn-success btn-block" value="+" onclick="selectUsers()"/>
                                        <input type="button" class="btn btn-danger btn-block" value="-" onclick="deselectUsers()"/>
									</div>
									<div class="col-lg-3">
										<select id="selSelectedUsers" name="selSelectedUsers" class="form-control" style="height:32%;" multiple="true" required>
                                        </select>
									</div>									
								</div>
																														
								<div class="form-group">
									<label class="control-label col-lg-2"></label>
									<div class="col-lg-2">
									<button class="btn btn-success btn-block" type="submit">预定会议</button>
									</div>
									<div class="col-lg-2">
									<button class="btn btn-default btn-block" type="reset">重置</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>