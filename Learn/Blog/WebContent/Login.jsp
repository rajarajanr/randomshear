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
<struts:div cssStyle="width: 32px; padding-left: 38%;padding-top: 12%;">
<struts:form action ="loginAction" method="get" cssStyle="background-color:#68EEB7;padding-right: 40px;padding-left: 40px;padding-top: 20px;padding-bottom: 20px;margin-bottom: -20px;margin-top: -20px;margin-left: -40px;">
<struts:label for="username" value="Username" cssStyle="text-transform: uppercase;font-family: Times New Roman;font-size: 16px;"/>
<struts:textfield name="username" cssStyle="font-family: Times New Roman;font-size: 16px;width: 160px;height: 24px;"/>
<struts:label for="password" value="Password" cssStyle="text-transform: uppercase;font-family: Times New Roman;font-size: 16px;"/>
<struts:password name="password" cssStyle="width: 160px;height: 24px;"/>
<struts:submit name="button" value="Login" cssStyle="position: relative;left: -99px;top: 29px;"/>
<struts:submit name="button" value="Register"/>
</struts:form>
</struts:div>
</body>
</html>