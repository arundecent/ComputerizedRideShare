package crp;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.crs.model.EmployeeForm;
import com.crs.service.LoginService;


/**
 * This class contains all the test cases for
 * the LoginService.java
 * @author mkarun2
 *
 */
@SuppressWarnings("rawtypes")
public class LoginServiceTestCases{

	/**
	 * Check if the login() method returns null,
	 * when empty emailID and password are not
	 * provided
	 */
	
	@Test
	public void testLoginNullCredentials() {	
		EmployeeForm employeeForm = new EmployeeForm();
		List list = new LoginService().login(employeeForm);		
		boolean test = (list == null);
		if(test){
			assertTrue("Empty email ID and password! Login failed.",true);
		}else{
			assertTrue("EmailID and password is present",false);
		}
	}
	
	@Test
	public void testLoginInvalidCredentials(){
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setEmailID("");
	}
}
