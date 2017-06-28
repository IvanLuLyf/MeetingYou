<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Meeting You</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="styles/login.css" />
<style>
	body, html {
		position: absolute;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;

		margin: 0;
		padding: 0;
	}
	#background {
		position: fixed;
		top: 0;
		left: 0;

		z-index: -100;
	}
</style>
</head>
<body>
	<canvas id="background"></canvas>
	<script async type="text/javascript" src="js/background.js"></script>
	<div class="container">
		<form action="LoginServlet" method="post" class="form-signin">
			<img alt="" src="images/logo.png" width="300px"></img>
			<c:if test="${requestScope.msg!=null}">
			<div id="myAlert" class="alert alert-info">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				<strong>提示信息:</strong>${requestScope.msg}
			</div>
			</c:if>
			<label for="username" class="sr-only">账号名</label>
			<input type="text" id="username" name="username" class="form-control" placeholder="账号名" required autofocus>
			<label for="password" class="sr-only">密码</label>
			<input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
			<label for="timelength">登录时长</label>
			<select id="timelength" name="timelength" class="form-control">
				<option value="0">每次需要登入</option>
				<option value="10">10天内</option>
				<option value="30">30天内</option>
			</select>
			<br>
			<button class="btn btn-lg btn-success btn-block" type="submit">登录</button>
			<input type="button" value="注册" class="btn btn-lg btn-info btn-block" onclick="window.location.href='ViewAllDepartmentsServlet?code=register'" />
			<input type="button" value="返回" class="btn btn-lg btn-default btn-block" onclick="window.history.back();" />
		</form>
	</div>
</body>
</html>