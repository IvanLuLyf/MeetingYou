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
							<a href="#">查看会议室</a>
						</li>
					</ul>
					<c:if test="${requestScope.roomsList!=null}">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">
									所有部门
								</h3>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>门牌编号</th>
											<th>会议室名称</th>
											<th>容纳人数</th>
											<th>当前状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<c:forEach var="item" items="${requestScope.roomsList}">
										<tr>
											<td>${item.number}</td>
											<td>${item.name}</td>
											<td>${item.capacity}</td>
											<c:if test="${item.status==1}"><td><span class="label label-success">启用</span></td></c:if>
											<c:if test="${item.status==0}"><td><span class="label label-warning">停用</span></td></c:if>
											<c:if test="${item.status==-1}"><td><span class="label label-danger">删除</span></td></c:if>											
											<td><a class="btn btn-info btn-sm" href="ViewOneMeetingRoomServlet?roomid=${item.roomid}">查看详情</a></td>
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