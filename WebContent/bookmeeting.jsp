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
    <script type="application/javascript">
            function employee(employeeid, employeename){
                this.employeeid = employeeid;
                this.employeename = employeename;
            }
            function department(departmentid, departmentname, employees){
                this.departmentid = departmentid;
                this.departmentname = departmentname;
                this.employees = employees;
            }
            var data = new Array(
                new department(1, "技术部", new Array(
                    new employee(1001, "a00"), new employee(1002, "a01"), new employee(1003, "a02"), new employee(1004, "a03"))),
                new department(2, "销售部", new Array(
                    new employee(2001, "b00"), new employee(2002, "b01"), new employee(2003, "b02"), new employee(2004, "b03"))),
                new department(3, "市场部", new Array(
                    new employee(3001, "c00"), new employee(3002, "c01"), new employee(3003, "c02"), new employee(3004, "c03"))),
                new department(4, "行政部", new Array(
                    new employee(4001, "d00"), new employee(4002, "d01"), new employee(4003, "d02"), new employee(4004, "d03"))));
            
            var selDepartments;
            var selEmployees;
            var selSelectedEmployees;
            
            function body_load(){
                selDepartments = document.getElementById("selDepartments");
                selEmployees = document.getElementById("selEmployees");
                selSelectedEmployees = document.getElementById("selSelectedEmployees");
                
                for(var i=0;i<data.length;i++){
                    var dep = document.createElement("option");
                    dep.value = data[i].departmentid;
                    dep.text = data[i].departmentname;
                    selDepartments.appendChild(dep);
                }
                
                fillEmployees();
            }
            
            function fillEmployees(){
                clearList(selEmployees);
                var departmentid = selDepartments.options[selDepartments.selectedIndex].value;
                var employees;
                for(var i=0;i<data.length;i++){
                    if (departmentid == data[i].departmentid){
                        employees = data[i].employees;
                        break;
                    }
                }
                for(i=0;i<employees.length;i++){
                    var emp = document.createElement("option");
                    emp.value = employees[i].employeeid;
                    emp.text = employees[i].employeename;
                    selEmployees.appendChild(emp);
                }
            }
            
            function clearList(list){
                while(list.childElementCount > 0){
                    list.removeChild(list.lastChild);
                }
            }
            
            function selectEmployees(){
                for(var i=0;i<selEmployees.options.length;i++){
                    if (selEmployees.options[i].selected){
                        addEmployee(selEmployees.options[i]);
                        selEmployees.options[i].selected = false;
                    }
                }
            }
            
            function deSelectEmployees(){
                var elementsToRemoved = new Array();
                var options = selSelectedEmployees.options;
                for(var i=0;i<options.length;i++){
                    if (options[i].selected){
                        elementsToRemoved.push(options[i]);
                    }
                }
                for(i=0;i<elementsToRemoved.length;i++){
                    selSelectedEmployees.removeChild(elementsToRemoved[i]);
                }
            }
            
            function addEmployee(optEmployee){
                var options = selSelectedEmployees.options;
                var i = 0;
                var insertIndex = -1;
                while(i < options.length){
                    if (optEmployee.value == options[i].value){
                        return;
                    } else if (optEmployee.value < options[i].value) {
                        insertIndex = i;
                        break;
                    }
                    i++;
                }
                var opt = document.createElement("option");
                opt.value = optEmployee.value;
                opt.text = optEmployee.text;
                
                if (insertIndex == -1){
                    selSelectedEmployees.appendChild(opt);
                } else {
                    selSelectedEmployees.insertBefore(opt, options[insertIndex]);
                }
            }            
	</script>
</head>
<body onload="body_load()">
    <div class="container">
		<div class="row">
			<div class="col-xs-12">
			<%@include file="header.jsp"%>
			<div class="row">
				<div class="col-xs-2">
				<%@include file="sidebar.jsp" %>
				</div>
				<div class="col-xs-10">
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
							<form class="form-horizontal" role="form" action="AddMeetingRoomServlet" method="post">
								<div class="form-group">
									<label class="control-label col-lg-2" for="meetingname">会议名称</label>
									<div class="col-lg-5">
									<input id="meetingname" name="meetingname" type="text" placeholder="" maxlength="10" class="form-control" required/>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2" for="capacity">预计参加人数</label>
									<div class="col-lg-5">
									<input id="capacity" name="capacity" type="text" placeholder="" maxlength="20" class="form-control" required/>
									</div>
								</div>
										
								<div class="form-group">
									<label class="control-label col-lg-2" for="startdate">预计开始时间</label>
									<div class="col-lg-3">
									<input id="startdate" name="startdate" type="date" placeholder="" class="form-control" required/>
									</div>
									<div class="col-lg-2">
									<input id="starttime" name="starttime" type="time" placeholder="" class="form-control" required/>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2" for="enddate">预计结束时间</label>
									<div class="col-lg-3">
									<input id="enddate" name="enddate" type="date" placeholder="" class="form-control" required/>
									</div>
									<div class="col-lg-2">
									<input id="endtime" name="endtime" type="time" placeholder="" class="form-control" required/>
									</div>
								</div>
										
								<div class="form-group">
									<label class="control-label col-lg-2" for="capacity">会议室名称</label>
									<div class="col-lg-5">
									<select id="roomid" name="roomid" class="form-control" required>    
										<option value="1">第一会议室</option>
										<option value="2">第二会议室</option>
										<option value="3">第三会议室</option>
                                     </select>
									</div>
								</div>		

								<div class="form-group">
									<label class="control-label col-lg-2" for="description">会议说明</label>
									<div class="col-lg-5">
									<textarea class="form-control" id="description" name="description" maxlength="200" rows="5" cols="60" placeholder="200字以内的文字描述"></textarea>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-lg-2" for="description">选择参会人员</label>
									<div class="col-lg-2">
										<select id="selDepartments" class="form-control" onchange="fillEmployees()">
                                        </select>
                                        <br>
                                        <select id="selEmployees" class="form-control" style="height:25%;" multiple="true">
                                        </select>
									</div>
									<div class="col-lg-1">
									    <input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                                        <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
									</div>
									<div class="col-lg-2">
										<select id="selSelectedEmployees" class="form-control" style="height:32%;" multiple="true">
                                        </select>
									</div>									
								</div>
																														
								<div class="form-group">
									<label class="control-label col-lg-2"></label>
									<div class="col-lg-3">
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