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
<sj:head jqueryui="true" jquerytheme="blitzer" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Account Details</title>
<link href="jspCSS/modifyCSS.css" rel="stylesheet" type="text/css" />
</head>
<body style="text-align: center;">
	<h1 style="color: blue">
		<i>Computerized RideShare Program Login</i>
	</h1>
	<s:form action="modifyEmployeeDetailsSubmit" method="post">
		<fieldset id="inputs">
			<s:hidden id="employeeID" key="employee.employeeID"	dataType="Integer" label="Employee id "></s:hidden>
			<s:password name="password" id="password" key="employee.password" label="New Password"></s:password>
			<s:textfield id="securityQn" name="securityQn" key="employee.securityQn" value="%{employee.securityQn}"	label="Security Question "></s:textfield>
			<s:textfield id="securityAns" name="securityAns" key="employee.securityAns"	value="%{employee.securityAns}" label="Security Answer "></s:textfield>
			<sj:textfield id="phoneNo" name="phoneNo" key="employee.phoneNo" value="%{employee.phoneNo}"	label="Phone Number "></sj:textfield>
			<sj:radio list="{'Email', 'Mobile'}" name="notifyTypeStr" label="Notify Type "	value="Email"  key="employee.notifyTypeStr"></sj:radio>
			<s:textfield id="address" name="address" key="employee.address" value="%{employee.address}" label="Address "></s:textfield>
		</fieldset>
		<fieldset id="action">
			<s:submit id="submit" button="true" align="center" />
			<s:submit id="cancel" button="true" align="center" value="Go Back"
				onClick="history.back();return false;" />
		</fieldset>
	</s:form>
</body>
</html>