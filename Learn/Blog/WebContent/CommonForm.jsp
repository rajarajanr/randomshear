
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="struts" uri="/struts-tags"%>
<html>
<body>
	<form action="loginAction" method="get">
		<struts:div cssStyle="text-align:right;">
			<struts:label>
				<struts:property value="%{#session.username}" />
			</struts:label>
			<input type ="submit" name="button" value="logout">
		</struts:div>
	</form>
</body>
</html>