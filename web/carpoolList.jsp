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
					<th></th>
				</tr>
				
				<s:if test="%{getAvailableCarpoolList().isEmpty()}">
					No free carpool available.
					<a href="rideshareRequest.jsp">Go back</a>
  				</s:if>
  				<s:else>
  					<s:iterator value="availableCarpoolList" status="availableCarpoolListStatus">
						<s:if test="#availableCarpoolListStatus.odd == true">
							<tr>
								<td style="background: lightgrey"><s:property value="carpoolID" /></td>
								<td style="background: lightgrey">
									<s:form action="joinCarpool" method="post" theme="simple">
										<s:hidden key="carpoolGroupID" value="%{carpoolID}"/>
										<s:hidden key="employeeID" value="%{carPoolMember.employee.employeeID}"/>
										<s:submit id="submit" button="true" value="Join" />
									</s:form>
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td><s:property value="carpoolID" /></td>
								<td>
									<s:form action="joinCarpool" method="post" theme="simple">
										<s:hidden key="carpoolGroupID" value="%{carpoolID}" />
										<s:hidden key="employeeID" value="%{carPoolMember.employee.employeeID}"/>
										<s:submit id="submit" button="true" value="Join" />
									</s:form>
								</td>
							</tr>
						</s:else>
					</s:iterator>
				</s:else>
			
			</table>
		</s:div>
	</s:form>
</body>
</html>