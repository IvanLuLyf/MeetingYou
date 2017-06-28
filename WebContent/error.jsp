<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	String msg=(String)request.getParameter("msg");
	String errorText = "";
	
	if(msg.equals("0")){
		errorText = "正在审核，请耐心等待。";
	}else if(msg.equals("2")){
		errorText = "审核未通过，请核实后重新注册。";
	}else if(msg.equals("3")){
		errorText = "用户名或密码错误，请重试。";
	}
%>
Message: <%=errorText %>
</body>
</html>