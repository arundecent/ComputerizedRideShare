<%--

https://code.google.com/p/struts2-jquery/wiki/News
http://jqueryui.com/themeroller/#!zThemeParams=5d00000100f305000000000000003d8888d844329a8dfe02723de3e5700bbb34ecf36ce5959f380e613cafa997b39424a52ffc947ae6386d03dcb468a5a6d1623ac0d0e3e18cdf4fca8f2d1c997c7af2b425437a134e5b366f618af5193c1d8772b526c0c53c8f5528cb98c54b6fe385b01ee1763c1d4611100b242ec920a41206d3963eee0327838569a479d191f5adfb7b3ffee4f7265bdf6e03b09d243b90abaeb609b96eee56cef6cc0633865895078988ec40819895711fa7efb93b110f6e1b1b1557039d6178c8824adfa105fdfe1f7011f178cbd9f593de821bf90d0a04f35b5f6ca2fe4ccb9ee832eb806819e5b9aa863e9b8246d16fb230a7cb628f97fc471014be50dac4872e81d9821984e5cb27e4c8026968a9b1e80247bd040d1ef8e047f0ae98998cf329c56654903d9807c1e8c51dcb22b9bb42ca22a9b30ab0c2ae230b6228bbf56f8dbb62a2e49281585710b64c5e25d34da872f883d0d15a3f9716d9f34e297cc883697927ddea3a4e56a1de81985f7db5ee4e62567eaaedcd8b802411687a738bb9921d9eba82ca5c09d328015ecf731114054d32c5cb2354800f07cf72fe329c69ef9a2b9f897e6a0e64abf6d605c0ce92167ab590565246907805b9d96399a15ff8a61c815970937e0cc48650f5e8bd2de19096fe950d8484dff5f18410d74ffdd71567
 
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
</script>
<title>Car Pool Ride Share Program</title>
<link href="jspCSS/rideshareRequestCSS.css" rel="stylesheet"
	type="text/css" />
<sj:head jqueryui="true" jquerytheme="blitzer" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>
	<h1 style="color: blue">
		<i>SDNU Computerized Ride Share System</i> 
	</h1>
	<h4 style="color: red" align="center">
		<a href="login.jsp">Logout</a>
	</h4>
	<br/>
	<br/>
	<br/>
	<br/>
	<h3 align="center">
		Welcome , <s:property value="carPoolMember.employee.firstName"/>
	</h3>
	<div id="register">
		<s:div id="buttonGroup">
			<s:div cssStyle="text-align:center;">
				<s:form action="optOutCrp" method="post" theme="simple">
					<s:hidden key="carpoolGroupID" value="%{carPoolMember.carpoolID}"/>
					<s:hidden key="employeeID" value="%{carPoolMember.employee.employeeID}"/>
					<s:submit id="submit" button="true" align="center"
						value="Opt Out CRP" />
				</s:form>
				<s:form id="optOutCarpoolForm" action="optOutCarpool" method="post" theme="simple">
					<s:hidden key="carpoolGroupID" value="%{carPoolMember.carpoolID}"/>
					<s:hidden key="employeeID" value="%{carPoolMember.employee.employeeID}"/>
					<s:submit id="submit" button="true" value="Opt Out Carpool" onClick="if(confirm('Do you want to confirm opting out of carpool group ? ')){form.action='optOutCarpool';}else{return false;}"/>
				</s:form>
				<s:form action="modifyEmployeeDetails" method="post" theme="simple">
					<s:submit id="submit" button="true" value="Edit My Details" />
					<s:hidden key="employeeID" value="%{carPoolMember.employee.employeeID}"/>
				</s:form>
			</s:div>
			<!--<s:set name="driver" value="carPoolMember.isDriver"/>-->
			<s:div cssStyle="text-align:center;">
				<s:if test="%{carPoolMember.isDriver==1}">
					<!--<s:set name="checkedIn" value="carPoolGroup.atWork"/>-->
					<s:form action="cancelDriving" method="post" theme="simple">
							<s:hidden key="carpoolGroupID" value="%{carPoolMember.carpoolID}"/>
							<s:hidden key="employeeID" value="%{carPoolMember.employee.employeeID}"/>
							<s:submit id="submit" button="true" value="Cancel Driving" onClick="if(confirm('Do you want to confirm cancel driving ? ')){form.action='cancelDriving';}else{return false;}"/>
					</s:form>
					<s:if test="%{carPoolGroup.atWork==0}">
						<s:form action="checkin" method="post" theme="simple">
							<s:hidden key="carpoolGroupID" value="%{carPoolMember.carpoolID}"/>
							<s:hidden key="employeeID" value="%{carPoolMember.employee.employeeID}"/>
							<s:submit id="submit" button="true" value="Checkin" />
						</s:form>
					</s:if>
					<s:else>
						<s:form action="checkout" method="post" theme="simple">
							<s:hidden key="carpoolGroupID" value="%{carPoolMember.carpoolID}"/>
							<s:hidden key="employeeID" value="%{carPoolMember.employee.employeeID}"/>
							<s:submit id="submit" button="true" value="Checkout"/>
						</s:form>
					</s:else>
				</s:if>
				<s:else>
					<s:if test="%{carPoolGroup.atWork==0}">
						<s:form action="cancelPickup" method="post" theme="simple">
							<s:hidden key="carpoolGroupID" value="%{carPoolMember.carpoolID}"/>
							<s:hidden key="employeeID" value="%{carPoolMember.employee.employeeID}"/>
							<s:submit id="submit" button="true" value="Cancel Pickup" cssStyle="margin-left:65px;" onClick="if(confirm('Do you want to confirm cancel pickup ? ')){form.action='cancelPickup';}else{return false;}"/>
						</s:form>
					</s:if>
				</s:else>
				<s:if test="%{carPoolGroup.atWork==1}">
					<s:form action="issueEmergency" method="post" theme="simple">
						<s:hidden key="carpoolGroupID" value="%{carPoolMember.carpoolID}"/>
						<s:hidden key="employeeID" value="%{carPoolMember.employee.employeeID}"/>
						<s:submit id="submit" button="true" value="Emergency" />
					</s:form>
				</s:if>
			</s:div>
		</s:div><br />
		<h2 style="color: blue;">Ride Share Group</h2>
		<%--
	
	To see this part you must visit:
	http://localhost:8080/ComputerizedRideShare/myCarpool
	
	--%>
		<table border="0" cellspacing="0" cellpadding="1" width="100%"
			style="text-align: center;">
			<tr>
				<th>Name</th>
				<th>Is Driver</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Group ID</th>
			</tr>
			<s:iterator value="memberList" status="memListStatus">
				<s:if test="#memListStatus.odd == true">
					 <tr>
						<td style="background: lightgrey"><s:property value="employee.firstName" /></td>
						<td style="background: lightgrey"><s:property value="isDriver" /></td>
						<td style="background: lightgrey"><s:property value="employee.address" /></td>
						<td style="background: lightgrey"><s:property value="employee.phoneNo" /></td>
						<td style="background: lightgrey"><s:property value="carpoolID" /></td>
					</tr>
				</s:if>
				<s:else>
					 <tr>
						<td><s:property value="employee.firstName" /></td>
						<td><s:property value="isDriver" /></td>
						<td><s:property value="employee.address" /></td>
						<td><s:property value="employee.phoneNo" /></td>
						<td><s:property value="carpoolID" /></td>
					</tr>
				</s:else>
			</s:iterator>
		</table>
	</div>
</body>
</html>