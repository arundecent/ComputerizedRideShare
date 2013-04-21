package com.crs.action;

import static org.junit.Assert.*;
import org.junit.Test;


public class LoginActionTest {


		@Test
	public void testLogin() {
			String check;
			LoginAction tester= new LoginAction();
			check = tester.login();
			assertTrue("login successful", check=="SUCCESS");
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
