<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Opt Out</title>
<link href="jspCSS/cancelPickup.css" rel="stylesheet" type="text/css" />
</head>
<body style="text-align: center;">
	<h1 style="color: blue">
		<i>Cancel Passenger Pick up</i>
	</h1>
	<s:form action="cancelPickupConfirm" method="post">
		<h1>Confirm</h1>
		<s:div cssStyle="text-align:center;">
			Are you sure you want to cancel being picked up today?
		</s:div>
		<s:div cssClass="actions">
			<s:submit id="cancel" button="true" align="center" value="Go Back"
				onClick="history.back();return false;" theme="simple" />
			<s:submit id="submit" button="true" align="center"
				value="Cancel Pickup" theme="simple" />
		</s:div>
	</s:form>
</body>
</html>