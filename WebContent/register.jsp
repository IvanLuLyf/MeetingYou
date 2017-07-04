<%@ page language="java" import="java.util.*,meetingmanager.vo.*"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Meeting You</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="js/jquery.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="styles/reg.css" />
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
	<script type="text/javascript">
	//使用ajax方法访问，验证账户名是否存在
	function validate() {
		$.ajax({
			type : "POST",
			url : "ValidateUsernameServlet",
			data : {
				username : $("#username").val()
			},
			success : function(message) {
				var validateMessage = $("#validateMessage");
				var data = JSON.parse(message);
				if (data.flag) {
					$("#username").css({
						color : "green",
						background : "#d6e9c6"
					});
				} else {
					$("#username").css({
						color : "red",
						background : "#ebccd1"
					});
				}

			}
		});
	}

	//验证两次密码是否相同
	function check() {
		if (formReg.firstpassword.value != formReg.secondpassword.value) {
			$("#firstpassword").css({
				color : "red",
				background : "#ebccd1"
			});
			$("#secondpassword").css({
				color : "red",
				background : "#ebccd1"
			});
		} else {
			$("#firstpassword").css({
				color : "green",
				background : "#d6e9c6"
			});
			$("#secondpassword").css({
				color : "green",
				background : "#d6e9c6"
			});
		}
	}
	</script>
</head>
<body>
	<canvas id="background"></canvas>
	<script async type="text/javascript" src="js/background.js"></script>
	<div class="container">
		<form name="formReg" action="RegisterServlet" method="post" class="form-signin">
			<!-- <h2 class="form-signin-heading">员工注册</h2> -->
			<img alt="" src="images/logo.png" width="300px"></img>
			<c:if test="${requestScope.msg!=null}">
			<div id="myAlert" class="alert alert-info">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				<strong>提示信息:</strong>${requestScope.msg}
			</div>
			</c:if>
			
			<label for="username" class="sr-only">账户名</label>
			<input type="text" id="username" name="username"
				maxlength="20" value="${param.username}" onchange="validate()"
				class="form-control" placeholder="账号名" required autofocus>
						
			<label for="password" class="sr-only">密码</label>
			<input type="password" id="firstpassword" name="password" maxlength="20"
				class="form-control" placeholder="请输入6位以上的密码" required>
				
			<label for="password" class="sr-only">确认密码</label>
			<input type="password" id="secondpassword" name="password" maxlength="20" onchange="check()"
				class="form-control" placeholder="确认密码" required>
			
			<label for="name" class="sr-only">姓名</label>
			<input type="text" id="name" name="name" maxlength="20" value="${param.name}"
				class="form-control" placeholder="姓名" required>	
			
			<label for="phone" class="sr-only">联系电话</label>
			<input type="text" id="phone" name="phone" maxlength="20" value="${param.phone}"
				class="form-control" placeholder="联系电话" required>	
			
			<label for="email" class="sr-only">电子邮件</label>
			<input type="email" id="email" name="email" maxlength="20" value="${param.email}"
				class="form-control" placeholder="电子邮件" required>	
			
			<label for="deptid" class="sr-only">所在部门</label>
			<select name="deptid"  class="form-control">
				<c:forEach var="department"
					items="${requestScope.departmentsList}">
					<c:if test="${department.departmentid== param.deptid}">
						<option value="${department.departmentid}" selected>${department.name}</option>
					</c:if>
					<c:if test="${department.departmentid!= param.deptid}">
						<option value="${department.departmentid}">${department.name}</option>
					</c:if>
				</c:forEach>
			</select>
			
			<br>
			
			<button class="btn btn-lg btn-success btn-block" type="submit">注册</button>
			<input type="button" value="登录" class="btn btn-lg btn-info btn-block" onclick="window.location.href='login.jsp'" />
			<input type="reset" class="btn btn-lg btn-default btn-block" value="重置" />
		</form>
	</div>
</body>
</html>