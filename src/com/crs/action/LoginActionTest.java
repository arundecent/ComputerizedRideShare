/*package com.crs.action;

import static org.junit.Assert.*;
import org.junit.Test;


public class LoginActionTest {


		@Test
	public void testLogin() {
			request.setParameter("employee.EmailID", "rvisha2@uic.edu");
	    	setParameter("employee.Password", "rajeevreddy");
	    	String check;
			LoginAction tester= new LoginAction();
			memberList = loginService.login(employee);
			check = tester.login();
			assertTrue("login successful", memberList.size() == 1);
			assertFalse("not satisfied", check=="ERROR");
	}

	@Test
	public void testRegisterNewUser() {
		String check;
		LoginAction tester= new LoginAction();
		check = tester.registerNewUser();
		assertTrue("set carpool member and group", check=="SUCCESS");
		
	}

	

	@Test
	public void testModifyEmployeeDetails() {
		String check;
		LoginAction tester = new LoginAction();
		check= tester.modifyEmployeeDetails();
		assertTrue("page to modify details", check=="SUCCESS");
	}

}
*/