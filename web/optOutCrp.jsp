<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html>

<html>
<head>
<title>Leave Carpool System</title>
<link href="jspCSS/cancelAccountCSS.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<h1 style="color: blue">
		<i>Cancel CRP Account</i>
	</h1>
	<s:form action="optOutCrpConfirm" method="post">
		<h1>Confirm</h1>
		<s:div cssStyle="text-align:center;">
			Are you sure you want to leave the system?
		</s:div>
		<s:div cssClass="actions">
			<s:submit id="cancel" button="true" align="center" value="Go Back"
				onClick="history.back();return false;" theme="simple" />
			<s:submit id="submit" button="true" align="center"
				value="Leave System" theme="simple" />
		</s:div>
	</s:form>
</body>
</html>