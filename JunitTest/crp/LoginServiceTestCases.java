package crp;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
import com.crs.service.LoginService;
import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;

/**
 * This class contains all the test cases to check the
 * methods in the LoginService.java
 * @author Mohan
 *
 */
@SuppressWarnings("rawtypes")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	
	
	
	
	
	/**
	 * Test case to check user registration for Driver.
	 * For this test case to run , in the carpool member table,
	 * each carpool ID, should occur 4 times, -> meaning that there
	 * is no space in every carpool hence create a new one
	 * 
	 */
	@SuppressWarnings("unused")
	@Test
	public void testUserRegistrationAsDriver(){
		EmployeeForm employee = new EmployeeForm();
		EmployeeForm tempEmployee = null;
		CrsDAO dao = new CrsDAO();
		LoginService ls = new LoginService();
		//#{emailID},#{password},#{salt},#{firstName},#{lastName},#{securityQn},#{securityAns},#{phoneNo},#{notifyType},#{address}
		
		employee.setEmailID("mkarun2@uic.edu");
		employee.setSalt(ls.getStrSalt());
		employee.setPassword("mohan");
		employee.setFirstName("Mohan");
		employee.setLastName("Karunakaran");
		employee.setSecurityQn("Where were you born?");
		employee.setSecurityAns("Chennai");
		employee.setNotifyType(0);
		employee.setAddress("1678, W.Taylor St. Chicago - 60607");
		
		List tempList = ls.registerNewUser(employee);
		
		/*
		 * checking if there is an entry made in
		 * the employee table using the getLoginRecord(Employee) method
		 */
		tempEmployee = dao.getLoginRecord(employee);		
		boolean test = (tempEmployee == null);
		
		/*Employee insert into Employee Table was successful*/
		if(!test){
			assertTrue("Employee inserted into Employee Table.",true);
			System.out.println("Employee inserted into Employee Table.");
		}else{
			/*Employee insert into Employee Table was unsuccessful*/
			assertTrue("Employee insertion into Employee Table failed.",false);
			System.out.println("Employee insertion into Employee Table failed.");
		}	
		
		/*
		 * retrieve entry from database to check if an entry has been
		 * made for the employee inserted above
		 */
		CarPoolMemberForm  cpm = dao.getMemberInfo(tempEmployee.getEmployeeID());
				
		/*
		 * Car pool Member Table insert check  and isDriver check
		 * Entry should have been made into the carpool table and the
		 * carpool member table for the driver. Checking the carpool member
		 * table because, an entry in this table with isDriver = 1 for this 
		 * user means, an entry has been made in the carpool table too owing
		 * to the referential integrity database constraint.
		 */
		boolean test2 = (cpm.getIsDriver() == 1);
		
		if(test2){
			assertTrue("User entered as a driver",true);
			System.out.println("User entered as a driver");
		}else{
			/*User was inserted as passenger*/
			assertTrue("User entered as a passenger",false);
			System.out.println("User entered as a passenger");
		}			
	}
	
	/**
	 * Test case to check user registration for passenger.
	 * For this test case, there should be at least 1
	 * entry in the "car pool member" table where a car pool id
	 * occurs less than 4 times -> meaning there a space in the 
	 * car pool group. This depends on the database state.
	 * testUserRegistrationForPassenger and testUserRegistrationAsDriver methods
	 * are inter related
	 * 
	 */
	@SuppressWarnings("unused")
	@Test
	public void testUserRegistrationForPassenger(){
		EmployeeForm employee = new EmployeeForm();
		EmployeeForm tempEmployee = null;
		CrsDAO dao = new CrsDAO();
		LoginService ls = new LoginService();
			
		employee.setEmailID("ntajith@gmail.com");
		employee.setSalt(ls.getStrSalt());
		employee.setPassword("mohan");
		employee.setFirstName("Mohan");
		employee.setLastName("Karunakaran");
		employee.setSecurityQn("Where were you born?");
		employee.setSecurityAns("Chennai");
		employee.setNotifyType(0);
		employee.setAddress("1678, W.Taylor St. Chicago - 60607");
		
		List tempList = ls.registerNewUser(employee);
		
		/*
		 * checking if there is an entry made in
		 * the employee table using the getLoginRecord(Employee) method
		 */
		tempEmployee = dao.getLoginRecord(employee);		
		boolean test = (tempEmployee == null);
		
		/*Employee insert into Employee Table was successful*/
		if(!test){
			assertTrue("Employee inserted into Employee Table.",true);
			System.out.println("Employee inserted into Employee Table.");
		}else{
			/*Employee insert into Employee Table was unsuccessful*/
			assertTrue("Employee insertion into Employee Table failed.",false);
			System.out.println("Employee insertion into Employee Table failed.");
		}	
		
		/*
		 * retrieve entry from database to check if an entry has been
		 * made for the employee inserted above
		 */
		CarPoolMemberForm  cpm = dao.getMemberInfo(tempEmployee.getEmployeeID());
				
		/*Car pool Member Table insert check and isDriver check*/
		boolean test2 = (cpm.getIsDriver() != 1);
		
		if(test2){
			assertTrue("User entered as a passenger",true);
			System.out.println("User entered as a passenger");
		}else{
			/*User was inserted as driver*/
			assertTrue("User entered as a driver",false);
			System.out.println("User entered as a driver");
		}			
	}
	
	/**
	 * For this test case to run, you have to register a user into the
	 * system using this email id - 
	 */
	@Test
	public void testValidLoginCredentials() {	
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setEmailID("mkarun2@uic.edu");
		employeeForm.setPassword("mohan");
		List list = new LoginService().login(employeeForm);	
		boolean test = (list != null);
		if(test){
			assertTrue("EmailID and password combination is correct.",true);
		}else{
			assertTrue("Password invalid",false);
		}
	}
}
