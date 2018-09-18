<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="meetingmanager.vo.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Meeting You</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="js/jquery.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
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
							<a href="#">人员管理</a> <span class="divider"></span>
						</li>
						<li class="active">
							<a href="#">部门管理</a>
						</li>
					</ul>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								人员管理
							</h3>
						</div>
						<div class="panel-body">
							<form action="AddDeleteDepartmentServlet" method="post" class="form-horizontal">
								<fieldset>
									<legend>添加部门</legend>
									部门名称：
									<div class="row">
										<div class="col-md-6">
										<input type="text" name="departmentname" class="form-control" maxlength="20" style="width:100%" required/>
										</div>
										<input type="hidden" name="code" value="add">
										<div class="col-md-6">
										<input type="submit" class="btn btn-info" value="添加" />
										</div>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
					<c:if test="${requestScope.departmentsList!=null}">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">
									所有部门
								</h3>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
								<table class="table table-responsive table-hover table-striped">
									<thead>
										<tr>
											<th>部门编号</th>
											<th>部门名称</th>
											<th>操作</th>
										</tr>
									</thead>
									<c:forEach var="item" items="${requestScope.departmentsList}">
										<tr>
											<td>${item.departmentid }</td>
											<td>${item.name }</td>
											<td>
												<button type="button" class="btn btn-sm btn-danger"
												onclick="window.location.href='AddDeleteDepartmentServlet?code=delete&departmentid=${item.departmentid}'">
													<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除
												</button>
											</td>
										</tr>
									</c:forEach>
								</table>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
			<%@include file="footer.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>