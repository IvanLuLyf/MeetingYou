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
							<a href="#">测试页面</a>
						</li>
					</ul>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								测试页面模板
							</h3>
						</div>
						<div class="panel-body">
							<p>Something</p>
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