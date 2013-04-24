package crp;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolForm;
import com.crs.model.EmployeeForm;


/**
 * This class contains all the test cases to check the methods in the
 * CrsDAOTest.java
 * @author Rajeev / Mohan
 */
public class CrsDAOTest {
		
	private CrsDAO dao;	
	
	public CrsDAOTest(){
		dao = new CrsDAO();		
	}
	
	/**
	 * Check for the database connection
	 */
	@BeforeClass
	public static void setUp() {		
		
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(MyBatisConnectionFactoryTest.class);
		if(result != null){
			assertTrue("Database is connected", true);
		}else{
			assertTrue("Database is not connected", false);
			//System.exit(0);
		}
	}
	
	/**
	 * Test case for getting the login record
	 * using employee email id.
	 */
	@Test
	public void testGetLoginRecord(){
		EmployeeForm employee = new EmployeeForm();
		employee.setEmailID("mkarun2@uic.edu");
		EmployeeForm tempEmployee = dao.getLoginRecord(employee);
		boolean test = (tempEmployee == null );
		if(test){
			assertTrue("Employee record not received or employee not present in the database", false);
		}else{
			assertTrue("Employee record received", true);
		}
	}
	/**
	 * Test case for getting the login record
	 * using employee Employee ID.
	 */

	@Test
	public void testGetLoginRecordWithEmpID() {
		EmployeeForm employee = new EmployeeForm();
		employee.setEmployeeID(20013);
		EmployeeForm tempEmployee = dao.getLoginRecordWithEmpID(20013);
		boolean test = (tempEmployee == null );
		if(test){
			assertTrue("Employee record not received or employee not present in the database", false);
		}else{
			assertTrue("Employee record received", true);
		}
	}

	@Test
	public void testInsertEmployeeRecord() {
		EmployeeForm employee = new EmployeeForm();
		employee.setEmployeeID(20013);
		//void tempEmployee = dao.insertEmployeeRecord(employee);
	}
/*
 * getting carpool details
 */
	@Test
	public void testGetCarPoolGroup() {
		//CarPoolForm carPoolForm = new CarPoolForm();
		CarPoolForm car = dao.getCarPoolGroup();
		boolean test = (car == null );
		if(test){
			assertTrue("No carpool group retireved", false);
		}else{
			assertTrue("Carpool group retrieved", true);
		}
	}

	
	/*
	 * Getting carpool details using carpool ID
	 */
	@Test
	public void testGetCarPoolGroupDetails() {
		CarPoolForm carform = new CarPoolForm();
		carform.setCarpoolID(1234);
		CarPoolForm car = dao.getCarPoolGroupDetails(1234);
		boolean test = (car == null );
		if(test){
			assertTrue("No carpool group retireved", false);
		}else{
			assertTrue("Carpool group retrieved", true);
		}	
	}

	@Test
	public void testInsertNewMemberRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testProcessEmergencyRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchMembersEmailID() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckOut() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckIn() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNewMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateNewCarPoolGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveAllFreeCarpoolGroups() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveDrivers() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCurrentDriver() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateNextDriver() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNextDriver() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateTemporaryDriver() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrievePassengers() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveMembers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLatestCarpoolGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testCancelCarpoolPickUp() {
		fail("Not yet implemented");
	}

	@Test
	public void testOptOutCrp() {
		fail("Not yet implemented");
	}

	@Test
	public void testCancelCarpoolDrive() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEmployeeRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testOptOutCarpool() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMemberInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserDetails() {
		fail("Not yet implemented");
	}

}
