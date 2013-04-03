<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head jqueryui="true" jquerytheme="ui-darkness"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<s:form action="register" method="post">
		<sj:textfield id="employeeId" key="employee.employeeId" label="Employee id "></sj:textfield>
		<sj:textfield id="userName" key="employee.emailID" label="Email id "></sj:textfield>
		<s:password id="password" key="employee.password" label="Password"></s:password>
		<sj:textfield id="salt" key="employee.salt" label="Salt "></sj:textfield>
		<sj:textfield id="firstName" key="employee.firstName" label="First Name "></sj:textfield>
		<sj:textfield id="lastName" key="employee.lastName" label="Last Name "></sj:textfield>
		<sj:textfield id="securityQn" key="employee.securityQn" label="Security Question "></sj:textfield>
		<sj:textfield id="phoneNo" key="employee.phoneNo" label="Phone Number "></sj:textfield>
		<s:combobox list="{'Email', 'Mobile'}" label="Notify Type "	headerKey="-1" key="employee.notifyType"></s:combobox>
		<sj:textfield id="address" key="employee.address" label="Address "></sj:textfield>
		<sj:datepicker id="dateJoined" accesskey="employee.dateJoined" label="Date Joined "></sj:datepicker>
		<sj:textfield id="points" key="employee.points" label="Points "></sj:textfield>
		<sj:submit align="center"/>
	</s:form>
</body>
</html>