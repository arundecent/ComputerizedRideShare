package crp;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.crs.model.EmployeeForm;
import com.crs.service.LoginService;

public class LoginServiceTestCases{

	/**
	 * Check if the login() method returns null,
	 * when empty emailID and password is provided
	 * as input
	 */
	@Test
	public void testLogin() {	
		EmployeeForm employeeForm = new EmployeeForm();
		List list = new LoginService().login(employeeForm);		
		boolean test = (list == null);
		if(test){
			assertTrue("Empty email ID and password! Login failed.",true);
		}else{
			assertTrue("EmailID and password is present",false);
		}
	}
}
