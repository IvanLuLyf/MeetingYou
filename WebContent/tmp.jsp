<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="TestServlet" method="post">
	<select name = "test" multiple style="height:200px;width:100px;">
		<option value="10">1</option>
		<option value="20">2</option>
		<option value="30">3</option>
		<option value="40">4</option>		
		<option value="50">5</option>
		<option value="60">6</option>		
		
	</select>
	<input name ="hi" type="text" />
	<input type="submit" value="sub"/>
</form>
</body>
</html>