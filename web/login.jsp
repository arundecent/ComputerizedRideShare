<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html>
<html>
<head>
<title>Computerized RideShare Program Login   </title>
<link href="jspCSS/loginCSS.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<h1 style="color: blue"><i>SDNU Computerized RideShare Program</i></h1>
	<s:form action="login" method="post">
		<h1>login</h1>
		<fieldset id="inputs">
			<s:textfield name= "emailID" key="employee.emailID" label="Email id "></s:textfield>
			<s:password name="password" id="password" key="employee.password" label="Password"></s:password>
		<!--fieldset id="actions"-->
			<s:submit id="submit" button="true" align="center" />
			<a href="createNewUser.jsp">Register</a>
		</fieldset>
	</s:form>
</body>
</html>


