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
							<a href="#">个人中心</a> <span class="divider"></span>
						</li>
						<li class="active">
							<a href="#">修改密码</a>
						</li>
					</ul>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								修改密码
							</h3>
						</div>
						<div class="panel-body">
							<c:if test="${requestScope.err_msg!=null}">
							<div id="myAlert" class="alert alert-danger">
								<a href="#" class="close" data-dismiss="alert">&times;</a>
								<strong>提示信息:</strong>${requestScope.err_msg}
							</div>
							</c:if>
							<c:if test="${requestScope.ok_msg!=null}">
							<div id="myAlert" class="alert alert-success">
								<a href="#" class="close" data-dismiss="alert">&times;</a>
								<strong>提示信息:</strong>${requestScope.ok_msg}
							</div>
							</c:if>							
							<form class="form-horizontal" role="form" action="ChangePasswordServlet" method="post">
								<div class="form-group">
									<label class="control-label col-lg-2" for="porigin">旧密码</label>
									<div class="col-lg-4">
									<input id="porigin" name="porigin" type="password" class="form-control" required/>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2" for="pnew">新密码</label>
									<div class="col-lg-4">
									<input id="pnew" name="pnew" type="password" class="form-control" required/>
									</div>
								</div>
										
								<div class="form-group">
									<label class="control-label col-lg-2" for="pcomfirm">确认新密码</label>
									<div class="col-lg-4">
									<input id="pcomfirm" name="pcomfirm" type="password" class="form-control" required/>
									</div>
								</div>
																
								<div class="form-group">
									<label class="control-label col-lg-2"></label>
									<div class="col-lg-2">
									<button class="btn btn-success btn-block" type="submit">修改</button>
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