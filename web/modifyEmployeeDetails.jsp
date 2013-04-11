<%--

https://code.google.com/p/struts2-jquery/wiki/News
http://jqueryui.com/themeroller/#!zThemeParams=5d00000100f305000000000000003d8888d844329a8dfe02723de3e5700bbb34ecf36ce5959f380e613cafa997b39424a52ffc947ae6386d03dcb468a5a6d1623ac0d0e3e18cdf4fca8f2d1c997c7af2b425437a134e5b366f618af5193c1d8772b526c0c53c8f5528cb98c54b6fe385b01ee1763c1d4611100b242ec920a41206d3963eee0327838569a479d191f5adfb7b3ffee4f7265bdf6e03b09d243b90abaeb609b96eee56cef6cc0633865895078988ec40819895711fa7efb93b110f6e1b1b1557039d6178c8824adfa105fdfe1f7011f178cbd9f593de821bf90d0a04f35b5f6ca2fe4ccb9ee832eb806819e5b9aa863e9b8246d16fb230a7cb628f97fc471014be50dac4872e81d9821984e5cb27e4c8026968a9b1e80247bd040d1ef8e047f0ae98998cf329c56654903d9807c1e8c51dcb22b9bb42ca22a9b30ab0c2ae230b6228bbf56f8dbb62a2e49281585710b64c5e25d34da872f883d0d15a3f9716d9f34e297cc883697927ddea3a4e56a1de81985f7db5ee4e62567eaaedcd8b802411687a738bb9921d9eba82ca5c09d328015ecf731114054d32c5cb2354800f07cf72fe329c69ef9a2b9f897e6a0e64abf6d605c0ce92167ab590565246907805b9d96399a15ff8a61c815970937e0cc48650f5e8bd2de19096fe950d8484dff5f18410d74ffdd71567
 
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<link href="css/style.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="css/style9.css" />
    <link rel="stylesheet" type="text/css" href="css/demo.css" />
    author: Rajeev    
        <link href='http://fonts.googleapis.com/css?family=Terminal+Dosis' rel='stylesheet' type='text/css' /> -->
        <style type="text/css">
<style>

html, body
{
    height: 100%;
}

body
{
    font: 12px 'Lucida Sans Unicode', 'Trebuchet MS', Arial, Helvetica;    
    margin: 0;
    background-color: #d9dee2;
    background-image: url(images/bg.png);    
}

/*--------------------*/

#modifyEmployeeDetails
{
    background-color: #fff;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#eee));
    background-image: -webkit-linear-gradient(top, #fff, #eee);
    background-image: -moz-linear-gradient(top, #fff, #eee);
    background-image: -ms-linear-gradient(top, #fff, #eee);
    background-image: -o-linear-gradient(top, #fff, #eee);
    background-image: linear-gradient(top, #fff, #eee);  
    height: 280px;
    width: 400px;
    margin: -150px 0 0 -230px;
    padding: 30px;
    position: absolute;
    top: 50%;
    left: 50%;
    z-index: 0;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;  
    -webkit-box-shadow:
          0 0 2px rgba(0, 0, 0, 0.2),
          0 1px 1px rgba(0, 0, 0, .2),
          0 3px 0 #fff,
          0 4px 0 rgba(0, 0, 0, .2),
          0 6px 0 #fff,  
          0 7px 0 rgba(0, 0, 0, .2);
    -moz-box-shadow:
          0 0 2px rgba(0, 0, 0, 0.2),  
          1px 1px   0 rgba(0,   0,   0,   .1),
          3px 3px   0 rgba(255, 255, 255, 1),
          4px 4px   0 rgba(0,   0,   0,   .1),
          6px 6px   0 rgba(255, 255, 255, 1),  
          7px 7px   0 rgba(0,   0,   0,   .1);
    box-shadow:
          0 0 2px rgba(0, 0, 0, 0.2),  
          0 1px 1px rgba(0, 0, 0, .2),
          0 3px 0 #fff,
          0 4px 0 rgba(0, 0, 0, .2),
          0 6px 0 #fff,  
          0 7px 0 rgba(0, 0, 0, .2);
}

#modifyEmployeeDetails:before
{
    content: '';
    position: absolute;
    z-index: -1;
    border: 1px dashed #ccc;
    top: 5px;
    bottom: 5px;
    left: 5px;
    right: 5px;
    -moz-box-shadow: 0 0 0 1px #fff;
    -webkit-box-shadow: 0 0 0 1px #fff;
    box-shadow: 0 0 0 1px #fff;
}

/*--------------------*/

h1
{
    text-shadow: 0 1px 0 rgba(255, 255, 255, .7), 0px 2px 0 rgba(0, 0, 0, .5);
    text-transform: uppercase;
    text-align: center;
    color: #666;
    margin: 0 0 30px 0;
    letter-spacing: 4px;
    font: normal 26px/1 Verdana, Helvetica;
    position: relative;
}

h1:after, h1:before
{
    background-color: #777;
    content: "";
    height: 1px;
    position: absolute;
    top: 15px;
    width: 120px;   
}

h1:after
{ 
    background-image: -webkit-gradient(linear, left top, right top, from(#777), to(#fff));
    background-image: -webkit-linear-gradient(left, #777, #fff);
    background-image: -moz-linear-gradient(left, #777, #fff);
    background-image: -ms-linear-gradient(left, #777, #fff);
    background-image: -o-linear-gradient(left, #777, #fff);
    background-image: linear-gradient(left, #777, #fff);      
    right: 0;
}

h1:before
{
    background-image: -webkit-gradient(linear, right top, left top, from(#777), to(#fff));
    background-image: -webkit-linear-gradient(right, #777, #fff);
    background-image: -moz-linear-gradient(right, #777, #fff);
    background-image: -ms-linear-gradient(right, #777, #fff);
    background-image: -o-linear-gradient(right, #777, #fff);
    background-image: linear-gradient(right, #777, #fff);
    left: 0;
}

/*--------------------*/

fieldset
{
    border: 0;
    padding: 0;
    margin: 0;
}

/*--------------------*/

#inputs input
{
    background: #f1f1f1 url(http://www.red-team-design.com/wp-content/uploads/2011/09/login-sprite.png) no-repeat;
    padding: 15px 15px 15px 30px;
    margin: 0 0 10px 0;
    width: 353px; /* 353 + 2 + 45 = 400 */
    border: 1px solid #ccc;
    -moz-border-radius: 5px;
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
    -webkit-box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
    box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
}

#userName
{
    background-position: 5px -2px !important;
}
#employeeId
{
    background-position: 5px -2px !important;
}

#firstName
{
    background-position: 5px -2px !important;
}
#lastName
{
    background-position: 5px -2px !important;
}

#securityQn
{
    background-position: 5px -2px !important;
}

#securityAn
{
    background-position: 5px -2px !important;
}

#password
{
    background-position: 5px -52px !important;
}

#address
{
    background-position: 5px -52px !important;
}


#inputs input:focus
{
    background-color: #fff;
    border-color: #e8c291;
    outline: none;
    -moz-box-shadow: 0 0 0 1px #e8c291 inset;
    -webkit-box-shadow: 0 0 0 1px #e8c291 inset;
    box-shadow: 0 0 0 1px #e8c291 inset;
}

/*--------------------*/
#actions
{
    margin: 25px 0 0 0;
}

#submit
{		
    background-color: #ffb94b;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#fddb6f), to(#ffb94b));
    background-image: -webkit-linear-gradient(top, #fddb6f, #ffb94b);
    background-image: -moz-linear-gradient(top, #fddb6f, #ffb94b);
    background-image: -ms-linear-gradient(top, #fddb6f, #ffb94b);
    background-image: -o-linear-gradient(top, #fddb6f, #ffb94b);
    background-image: linear-gradient(top, #fddb6f, #ffb94b);
    
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    
    text-shadow: 0 1px 0 rgba(255,255,255,0.5);
    
     -moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3) inset;
     -webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3) inset;
     box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3) inset;    
    
    border-width: 1px;
    border-style: solid;
    border-color: #d69e31 #e3a037 #d5982d #e3a037;

    float: left;
    height: 35px;
    padding: 0;
    width: 120px;
    cursor: pointer;
    font: bold 15px Arial, Helvetica;
    color: #8f5a0a;
}

#submit:hover,#submit:focus
{		
    background-color: #fddb6f;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#ffb94b), to(#fddb6f));
    background-image: -webkit-linear-gradient(top, #ffb94b, #fddb6f);
    background-image: -moz-linear-gradient(top, #ffb94b, #fddb6f);
    background-image: -ms-linear-gradient(top, #ffb94b, #fddb6f);
    background-image: -o-linear-gradient(top, #ffb94b, #fddb6f);
    background-image: linear-gradient(top, #ffb94b, #fddb6f);
}	

#submit:active
{		
    outline: none;
   
     -moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
     -webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
     box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;		
}

#submit::-moz-focus-inner
{
  border: none;
}

#actions a
{
    color: #3151A2;    
    float: right;
    line-height: 35px;
    margin-left: 10px;
}

/*--------------------*/

#message
{
    display: block;
    text-align: left; 
     
    position: relative;
    top: 3px;
    color: #FF0000;
    /*z-index:9999;*/
}

</style>
<sj:head jqueryui="true" jquerytheme="blitzer"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Account Details</title>
</head>
<body>
	<font color="blue"><center><h1><i><u>Modify your details</u></i></h1></center></font>
	<s:form action="modifyEmployeeDetails" method="post">
	<fieldset id="inputs">
		<s:hidden id="employeeId" key="employee.employeeID" dataType="Integer" label="Employee id "></s:hidden>
		
		<%-- New password is optional, but if filled in must match confirmPassword --%>
		
		<s:password id="password" key="employee.password" label="New Password"></s:password>
		<s:password id="confirmPassword" key="employee.confirmPassword" label="Confirm Password"></s:password>
		<s:textfield id="securityQn" key="employee.securityQn" label="Security Question "></s:textfield>
		<s:textfield id="securityAn" key="employee.securityAn" label="Security Answer "></s:textfield>
		<sj:textfield id="phoneNo" key="employee.phoneNo" label="Phone Number "></sj:textfield>
		<sj:radio list="{'Email', 'Mobile'}" label="Notify Type " key="employee.notifyType"></sj:radio>
		<s:textfield id="address" key="employee.address" label="Address "></s:textfield>
		</fieldset>
		<fieldset id="actions">
			<s:submit id="submit" button="true" align="center"/>
    	</fieldset>
	</s:form>
</body>
</html>