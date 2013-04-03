<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Computerised Ride Share Login</title>
<link href="css/ui-darkness/jquery-ui-1.10.2.custom.css" rel="stylesheet">
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/jquery-ui-1.10.2.custom.js"></script>
</head>
<body>
	<s:form action="login" method="post">
		<s:textfield id="userName" key="employee.emailID" label="Email id "></s:textfield>
		<s:password id="password" key="employee.password" label="Password"></s:password>
		<s:submit align="center"/>
	</s:form>
	<s:form action="register" method="post">
		<s:label label="New User ? "><a href="createNewUser.jsp">Sign Up</a></s:label>
	</s:form>
</body>
</html>