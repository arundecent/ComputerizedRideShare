<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>SDNU Computerized Ride Share System</title>
<link href="jspCSS/carpoolList.css" rel="stylesheet" type="text/css" />
</head>
<body style="text-align: center;">
	<h1 style="color: blue">
		<i>SDNU Computerized Ride Share System</i>
	</h1>
	<s:form action="carpoolList" method="post">
		<h1>Join carpool</h1>
		<s:div cssClass="list">
			<table border="0" cellspacing="0" cellpadding="1" width="100%"
			style="text-align: center;">
				<tr>
					<th>Carpool ID</th>
					<th>Member Count</th>
					<th>At Work</th>
					<th></th>
				</tr>
				<%-- <s:iterator value="memberList" status="memListStatus">
				<s:if test="#memListStatus.odd == true"> --%>
					<tr>
						<td style="background: lightgrey">5001</td>
						<td style="background: lightgrey">3</td>
						<td style="background: lightgrey">Yes</td>
						<td style="background: lightgrey">
							<s:form action="joinCarpool" method="post" theme="simple">
								<s:hidden name="carpoolId" value="5001" />
								<s:submit id="submit" button="true" value="Join" />
							</s:form>
						</td>
					</tr>
				<%-- </s:if>
				<s:else> --%>
					<tr>
						<td>5002</td>
						<td>2</td>
						<td>No</td>
						<td>
							<s:form action="joinCarpool" method="post" theme="simple">
								<s:hidden name="carpoolId" value="5002" />
								<s:submit id="submit" button="true" value="Join" />
							</s:form>
						</td>
					</tr>
				<%-- </s:else> --%>
			<%-- </s:iterator> --%>
			</table>
		</s:div>
	</s:form>
</body>
</html>