<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Emergency</title>
<link href="jspCSS/issueEmergency.css" rel="stylesheet" type="text/css" />
</head>
<body style="text-align: center;">
	<h1 style="color: blue"><i>Issue Emergency</i></h1>
	<s:form action="issueEmergencyConfirm" method="post">
		<h1>Confirm</h1>
		<s:div cssStyle="text-align:center;">
			Are you sure you would like to issue an emergency?<br />
			This will place you into a different available carpool for today.
		</s:div>
		<s:div cssClass="actions">
			<s:submit id="cancel" button="true" align="center" value="Go Back"
				onClick="history.back();return false;" theme="simple" />
			<s:submit id="submit" button="true" align="center"
				value="Issue" theme="simple" />
		</s:div>
	</s:form>
</body>
</html>