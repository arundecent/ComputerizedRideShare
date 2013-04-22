package crp;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.crs.model.EmployeeForm;
import com.crs.service.LoginService;

/**
 * This class contains all the test cases to check the
 * methods in the LoginService.java
 * @author Mohan
 *
 */
@SuppressWarnings("rawtypes")
public class LoginServiceTestCases{

	/**
	 * Check if the login() method returns null,
	 * when empty emailID and password is provided
	 * as input
	 */	
	@Test
	public void testLoginEmptyEmailIDAndPassword() {	
		EmployeeForm employeeForm = new EmployeeForm();
		List list = new LoginService().login(employeeForm);		
		boolean test = (list == null);
		if(test){
			assertTrue("Empty email ID and password! Login failed.",true);
		}else{
			assertTrue("EmailID and password is present",false);
		}
	}
	
	
	/**
	 * Check if invalid user login credentials returns correctly
	 * - null
	 */
	@Test
	public void testLoginInvalidEmailIDAndPasswordCombination() {	
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setEmailID("abc@gmail.com");
		employeeForm.setPassword("password");
		List list = new LoginService().login(employeeForm);	
		boolean test = (list == null);
		if(test){
			assertTrue("Invalid email ID and password! Login failed.",true);
		}else{
			assertTrue("EmailID and password combination is correct.",false);
		}
	}
	
	/**
	 * For this test case to run, you have to register a user into the
	 * system using this email id - asdas@gmail.com
	 * and password - "mohan"
	 */
	@Test
	public void testLoginInvalidPassword() {	
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setEmailID("asdas@gmail.com");
		employeeForm.setPassword("password");
		List list = new LoginService().login(employeeForm);	
		boolean test = (list == null);
		if(test){
			assertTrue("Password invalid",true);
		}else{
			assertTrue("EmailID and password combination is correct.",false);
		}
	}
	
	/**
	 * For this test case to run, you have to register a user into the
	 * system using this email id - 
	 */
	@Test
	public void testLoginValidLoginCredentials() {	
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setEmailID("asdas@gmail.com");
		employeeForm.setPassword("mohan");
		List list = new LoginService().login(employeeForm);	
		boolean test = (list != null);
		if(test){
			assertTrue("EmailID and password combination is correct.",true);
		}else{
			assertTrue("Password invalid",false);
		}
	}
	
	/**
	 * Check if null is returned for empty password string. No hash function 
	 * has to be called
	 */
	@Test
	public void testMD5PasswordGenerationNullCheck() {	
		String strPassword = null;
		String md5String = new LoginService().generateMD5HashForPassword(strPassword);
		boolean test = (md5String.equals("null"));
		if(test){
			assertTrue("No Hashed password generated.",true);
			System.out.println("No Hashed password generated.");
		}else{
			assertTrue("Hashed password generated => " + md5String,false);
			System.out.println("Hashed password generated => " + md5String);
		}
	}
	
	/**
	 * Working md5 hash function
	 */
	@Test
	public void testMD5PasswordGeneration() {	
		String strPassword = "mohan";
		String md5String = new LoginService().generateMD5HashForPassword(strPassword);
		
		boolean test = (md5String.equals("null"));
		if(!test){
			assertTrue("Hashed password generated => " + md5String,true);		
			System.out.println("Hashed password generated => " + md5String);
			
		}else{
			assertTrue("No Hashed password generated.",false);
			System.out.println("No Hashed password generated.");			
		}
	}
	
	
	/**
	 * Check if null is returned for empty password string using salt method. No hash function 
	 * has to be called
	 */
	@Test
	public void testMD5PasswordGenerationNullCheckUsingSaltMethod() {	
		String strPassword = null;
		String md5String = new LoginService().generateMD5HashForPasswordWithSalt(strPassword);
		boolean test = (md5String.equals("null"));
		if(test){
			assertTrue("No Hashed password generated.",true);
			System.out.println("No Hashed password generated.");
		}else{
			assertTrue("Hashed password generated => " + md5String,false);
			System.out.println("Hashed password generated => " + md5String);
		}
	}
	
	/**
	 * Working md5 hash function using salt method
	 */
	@Test
	public void testMD5PasswordGenerationUsingSaltMethod() {	
		String strPassword = "mohan";
		String md5String = new LoginService().generateMD5HashForPasswordWithSalt(strPassword);
		
		boolean test = (md5String.equals("null"));
		if(!test){
			assertTrue("Hashed password generated => " + md5String,true);		
			System.out.println("Hashed password generated => " + md5String);
			
		}else{
			assertTrue("No Hashed password generated.",false);
			System.out.println("No Hashed password generated.");			
		}
	}
	
	/**
	 * This test is to check if random salt is generated for each execution 
	 * of this method
	 */
	@Test
	public void testSaltGeneration(){
		String strSalt = new LoginService().generateSalt();
		boolean test = (strSalt.equals("null"));
		if(!test){
			
			assertTrue("Salt1 generated.",true);
			System.out.println("Salt1 generated.");	
			
			String strSalt2 = new LoginService().generateSalt();
			boolean test2 = (strSalt.equals("null"));
			boolean test3 = (strSalt.equals(strSalt2));
			
			/*
			 *Since strSalt and strSalt2 are not equal
			 *for each execution of the method, the salt
			 *String is randomly generated
			 */
			if(!(test2) && !(test3)){
				assertTrue("Random salt generated.",true);
				System.out.println("Random salt generated.");	
			}
			
		}else{
			assertTrue("Random Salt not generated",false);		
			System.out.println("Random Salt not generated");
		}
	}
	
}
