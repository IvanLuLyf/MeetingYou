<%@ page language="java"
	import="java.util.*,meetingmanager.vo.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
							<a href="#">个人中心</a> <span class="divider"></span>
						</li>
						<li class="active">
							<a href="#">我的会议</a>
						</li>
					</ul>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								我将参加的会议
							</h3>
						</div>
						<div class="panel-body">
							<table class="table table-responsive table-hover table-striped">
								<thead>
									<tr>
										<th>会议名称</th>
										<th>会议室名称</th>
										<th>会议开始时间</th>
										<th>会议结束时间</th>
										<th>会议预定时间</th>
										<th>预定者</th>
										<th>操作</th>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="item" items="${requestScope.meetings}">
									<tr>
										<td>${item.name}</td>
										<td>${requestScope.meetingrooms[item.roomid].name}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${item.begintime}" /></td>
										<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${item.endtime}" /></td>
										<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${item.reservetime}" /></td>
										<td>${requestScope.users[item.reserverid].name}</td>
										<td>
											<a class="btn btn-info btn-sm" href="meetingdetails.html">查看详情</a>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
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