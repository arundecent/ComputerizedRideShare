<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<link href="jspCSS/errorCSS.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<h1 style="color: blue">
		<i>Error</i>
	</h1>
	<s:div id="error">
		<s:div cssStyle="text-align:center;margin-top:25%;font-size:16px;">Uh-oh, Seems you have encountered an error</s:div>
		<s:div cssStyle="text-align:center;">
			<s:a href="javascript:history.back();">Go Back</s:a>
		</s:div>
	</s:div>
</body>
</html>