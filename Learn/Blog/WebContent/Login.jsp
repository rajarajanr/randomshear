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
<label for="username">Username</label>
<input type="text" name="username"/>
<label for="password">Password</label>
<input type="password" name="password"/>
<input type ="submit" name="button" value="login">
<input type="submit" name="button" value="register">
</form>
</body>
</html>