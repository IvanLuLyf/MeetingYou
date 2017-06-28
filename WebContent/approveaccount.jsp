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
</head>
<body>
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
							<a href="#">人员管理</a> <span class="divider"></span>
						</li>
						<li class="active">
							<a href="#">注册审批</a>
						</li>
					</ul>
					<div class="panel panel-info">
					<c:if test="${requestScope.usersList!=null}">
						<div class="panel-heading">
							<h3 class="panel-title">
								所有待审批注册信息
							</h3>
						</div>
						<div class="panel-body">
							<table class="table table-responsive table-hover table-striped">
								<thead>
									<tr>
										<th>姓名</th>
										<th>账户名</th>
										<th>联系电话</th>
										<th>电子邮件</th>
										<th>操作</th>
									</tr>
								</thead>
								<c:forEach var="usr" items="${requestScope.usersList}">
								<tbody>
									<tr>
										<td>${usr.name}</td>
										<td>${usr.username}</td>
										<td>${usr.phone }</td>
										<td>${usr.email }</td>
										<td><button type="button" class="btn btn-sm btn-success"
											onclick="window.location.href='ApproveServlet?uid=${usr.uid}&oper=yes'">
												<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 通过
											</button>
											<button type="button" class="btn btn-sm btn-danger"
											onclick="window.location.href='ApproveServlet?uid=${usr.uid}&oper=no'">
												<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 不通过
											</button>
										</td>
									</tr>
								</tbody>
								</c:forEach>
							</table>
						</div>
					</c:if>		
					</div>
				</div>
			</div>
			<%@include file="footer.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>