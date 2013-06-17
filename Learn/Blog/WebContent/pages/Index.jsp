<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<H1>hello world from struts 2</H1>
<form action ="hello">
<label for="name">please enter your name</label>
<input type="text" name="name"/>
<input type ="submit" value="Say hello">
</form>
</body>
</html>