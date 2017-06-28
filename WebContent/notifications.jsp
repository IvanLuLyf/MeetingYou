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
							<a href="#">个人中心</a> <span class="divider"></span>
						</li>
						<li class="active">
							<a href="#">最新通知</a>
						</li>
					</ul>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								未来7天我要参加的会议
							</h3>
						</div>
						<div class="panel-body">
							<table class="table table-responsive table-hover table-striped">
								<thead>
									<tr>
										<th>会议名称</th>
										<th>会议室</th>
										<th>起始时间</th>
										<th>结束时间</th>
										<th>操作</th>
									</tr>
								</thead>
								
								<tbody>
									<tr>
										<td>三季度销售总结会</td>
										<td>第一会议室</td>
										<td>2013-11-20 9：00</td>
										<td>2013-11-20 11：00</td>
										<td>
											<a class="clickbutton" href="meetingdetails.html">查看详情</a>
										</td>
									</tr>
									<tr>
										<td>与Google合作推广Android技术培训会议</td>
										<td>第三会议室</td>
										<td>2013-11-18 9：00</td>
										<td>2013-11-18 11：00</td>
										<td>
											<a class="clickbutton" href="meetingdetails.html">查看详情</a>
										</td>
									</tr>
									<c:forEach var="usr" items="${requestScope.usersList}">
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
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								已取消的会议
							</h3>
						</div>
						<div class="panel-body">
							<table class="table table-responsive table-hover table-striped">
								<thead>
									<tr>
				                        <th>会议名称</th>
				                        <th>会议室</th>
				                        <th>起始时间</th>
				                        <th>结束时间</th>
				                        <th>取消原因</th>
				                        <th>操作</th>
									</tr>
								</thead>
								<tbody>
				                    <tr>
				                        <td>三季度销售总结会</td>
				                        <td>第一会议室</td>
				                        <td>2013-11-20 9：00</td>
				                        <td>2013-11-20 11：00</td>
				                        <td>人员出差</td>
				                        <td>
				                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
				                        </td>
				                    </tr>
				                    <tr>
				                        <td>与Google合作推广Android技术培训会议</td>
				                        <td>第三会议室</td>
				                        <td>2013-11-18 9：00</td>
				                        <td>2013-11-18 11：00</td>
				                        <td>人员出差</td>
				                        <td>
				                            <a class="clickbutton" href="meetingdetails.html">查看详情</a>
				                        </td>
				                    </tr>
									<c:forEach var="usr" items="${requestScope.usersList}">
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