<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login/Register</title>
</head>
<body>
<H1>Login Page</H1>
<form action ="loginAction" method="get">
<struts:label for="username" value="Username"/>
<struts:textfield name="username"/>
<struts:label for="password" value="Password"/>
<struts:password name="password"/>
<struts:submit type ="submit" name="button" value="Login" align="left"/>
<struts:submit name="button" value="Register" align="left"/>
</form>
</body>
</html>