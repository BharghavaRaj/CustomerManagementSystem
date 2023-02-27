<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<h1>Customer Login Page</h1>
<form action="logCustomer" method="post">		
	
	<label for="tbEmail">
		Email: 	<input type="email" name="tbEmail" id="tbEmail" value="<%=request.getParameter("tbEmail")%>"/>
	</label>
	<br>
	<label for="tbPass">
		Password: <input type="password" name="tbPass" id="tbPass" value="<%=request.getParameter("tbPass")%>"/>	
	</label>
	<br>
		<input type="submit" value="Login"/>
</form>
</body>
</html>