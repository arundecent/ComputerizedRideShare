package crp;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolForm;
import com.crs.model.CarPoolMemberForm;
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
		CrsDAO crs = new CrsDAO();
		EmployeeForm em = new EmployeeForm();
		em.setEmployeeID(20013);
		em.setAddress("835 S Laflin");
		em.setEmailID("rvisha2@uic.edu");
		em.setEmployeeID(1);
		em.setFirstName("Rajeev Reddy");
		em.setLastName("Vishaka");
		em.setNotifyType(1);
		em.setPassword("password");
		em.setPhoneNo("312-361-4284");
		em.setPoints(10);
		dao.insertEmployeeRecord(em);
        
        //To test whether insert was done/
        EmployeeForm tempEmployee = dao.getLoginRecord(em);
        boolean test = (tempEmployee == null );
        if(test){
                assertTrue("Employee record was not inserted.", false);
        }else{
                assertTrue("Employee record was inserted", true);
        }
		//EmployeeForm tempEmployee = crs.insertEmployeeRecord(em);
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
/*
 * testing insertion of new members record
 */
	@Test
	public void testInsertNewMemberRecord() {
		CarPoolMemberForm details = new CarPoolMemberForm();
		details.setCarpoolID(2000);
		Date dateJoined= new Date(2013, 2, 12, 10, 23, 34);
		details.setDateJoined(dateJoined);
		details.setIsDriver(1);
		details.setIsTemporary(0);
		details.setEmployeeID(2003);
		details.setIsPickUp(1);
		dao.insertNewMemberRecord(details);
		CarPoolForm car = dao.getCarPoolGroupDetails(2000);
		boolean test = (car == null );
		if(test){
			assertTrue("No carpool group inserted", false);
		}else{
			assertTrue("inserted data is retrieved", true);
		}
	}
/*
 * testing emergency request
 */
	@Test
	public void testProcessEmergencyRequest() {
		CarPoolMemberForm carform = new CarPoolMemberForm();
		carform.setCarpoolID(4009);
		dao.processEmergencyRequest(carform);
		CarPoolForm car = dao.getCarPoolGroupDetails(4009);
		 boolean test = (car == null );
        if(test){
                assertTrue("Employee record was not inserted.", false);
        }else{
                assertTrue("Employee record was inserted", true);
        }
	}
/*
 * checking memeber fetching using email ID
 */
	@Test
	public void testFetchMembersEmailID() {
		CarPoolMemberForm carform = new CarPoolMemberForm();
		carform.setCarpoolID(1234);
		List car = dao.fetchMembersEmailID(1234);
		boolean test = (car == null );
		if(test){
			assertTrue("No carpool group retireved", false);
		}else{
			assertTrue("Carpool group retrieved", true);
		}	
	}
/*
 * Inserting and updating into carpool and group.checking Checkout
 */
	@Test
	public void testCheckOut() {
		EmployeeForm employee = new EmployeeForm();
		CarPoolMemberForm car = new CarPoolMemberForm();
		employee.setEmployeeID(200013);
		car.setCarpoolID(5009);
		dao.checkOut(1234, 200013);
		CarPoolForm ca = dao.getCarPoolGroupDetails(5009);
		boolean test = (ca == null );
		if(test){
			assertTrue("No carpool group retireved", false);
		}else{
			assertTrue("Carpool group retrieved", true);
		}	
	}
/*
 * testing checkin
 */
	@Test
	public void testCheckIn() {
		CarPoolMemberForm car = new CarPoolMemberForm();
		car.setCarpoolID(5009);
		dao.checkIn(5009);
		CarPoolForm ca = dao.getCarPoolGroupDetails(5009);
		boolean test = (ca == null );
		if(test){
			assertTrue("No carpool group retireved", false);
		}else{
			assertTrue("Carpool group retrieved", true);
		}	
	}
/*
 * creating new member insertion testing
 */
	@Test
	public void testCreateNewMember() {
		CarPoolMemberForm details = new CarPoolMemberForm();
		details.setCarpoolID(5000);
		Date dateJoined= new Date(2013, 2, 12, 10, 23, 34);
		details.setDateJoined(dateJoined);
		details.setIsDriver(1);
		details.setIsTemporary(0);
		details.setEmployeeID(2001);
		details.setIsPickUp(1);
		dao.createNewMember(details);
		CarPoolForm ca = dao.getCarPoolGroupDetails(5000);
		boolean test = (ca == null );
		if(test){
			assertTrue("No carpool group retireved", false);
		}else{
			assertTrue("Carpool group retrieved", true);
		}	
	}
/*
 * creating a new carpool group
 * depends on the car pool ID given
 */
	@Test
	public void testCreateNewCarPoolGroup() {
		CarPoolForm car = new CarPoolForm();
		car.setAtWork(0);
		car.setCarpoolID(5009);
		Date dateCreated= new Date(2013, 2, 12, 10, 23, 34);
		car.setDateCreated(dateCreated);
		dao.createNewCarPoolGroup();
		CarPoolForm ca = dao.getCarPoolGroupDetails(5009);
		boolean test = (ca == null );
		if(test){
			assertTrue("No carpool group retireved", false);
		}else{
			assertTrue("Carpool group retrieved", true);
		}
	}

	@Test
	public void testRetrieveAllFreeCarpoolGroups() {
		List<CarPoolMemberForm> carpoolList  = new ArrayList<CarPoolMemberForm>();
		//carpoolList.add(1000, null);
		
		//List<Object> tempList = new ArrayList<Object>();
		//Iterator<Object> it = {5009,5010};
		
	}

	@Test
	public void testRetrieveDrivers() {
		fail("Not yet implemented");
	}
/*
 * updating the curent driver
 */
	@Test
	public void testUpdateCurrentDriver() {
		CrsDAO crs =  new CrsDAO();
		crs.updateCurrentDriver(20013);
		ArrayList<CarPoolMemberForm> arr = new ArrayList<CarPoolMemberForm>();
		boolean test = (arr == null );
		if(test){
			assertTrue("No drivers retireved", false);
		}else{
			assertTrue("drivers retrieved", true);
		}
	}

	@Test
	public void testUpdateNextDriver() {
		CrsDAO crs =  new CrsDAO();
		crs.updateNextDriver(20013);
		ArrayList<CarPoolMemberForm> arr = new ArrayList<CarPoolMemberForm>();
		boolean test = (arr == null );
		if(test){
			assertTrue("No drivers updated", false);
		}else{
			assertTrue("drivers retrieved", true);
		}
	}

	@Test
	public void testGetNextDriver() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateTemporaryDriver() {
		fail("Not yet implemented");
	}
/*
 * retrieving all the passengers \
 *
 */
	@Test
	public void testRetrievePassengers() {
		List<Object> tempList = new ArrayList<Object>();;
		CarPoolMemberForm car = new CarPoolMemberForm();
		car.setCarpoolID(5009);
		Date dateJoined= new Date(2013, 2, 12, 10, 23, 34);
		car.setDateJoined(dateJoined);
		car.setEmployeeID(20015);
		car.setIsDriver(1);
		car.setIsPickUp(1);
		car.setIsTemporary(1);
		dao.retrievePassengers(5009);
		CarPoolForm ca = dao.getCarPoolGroupDetails(5009);
		boolean test = (ca == null );
		if(test){
			assertTrue("no passengers retreived", false);
		}else{
			assertTrue("passengers retrieved", true);
		}
		
	}
/*
 * retrieving members 
 */
	@Test
	public void testRetrieveMembers() {
		List<Object> tempList = new ArrayList<Object>();;
		CarPoolMemberForm car = new CarPoolMemberForm();
		car.setCarpoolID(5009);
		Date dateJoined= new Date(2013, 2, 12, 10, 23, 34);
		car.setDateJoined(dateJoined);
		car.setEmployeeID(20015);
		car.setIsDriver(1);
		car.setIsPickUp(1);
		car.setIsTemporary(1);
		dao.retrieveMembers(car);
		CarPoolForm ca = dao.getCarPoolGroupDetails(5009);
		boolean test = (ca == null );
		if(test){
			assertTrue("no passengers retreived", false);
		}else{
			assertTrue("passengers retrieved", true);
		}
	}

	@Test
	public void testGetLatestCarpoolGroup() {
		fail("Not yet implemented");
	}
/*
 * testing cancel pickup
 */
	@Test
	public void testCancelCarpoolPickUp() {
		CarPoolMemberForm car = new CarPoolMemberForm();
		car.setCarpoolID(5009);
		Date dateJoined= new Date(2013, 2, 12, 10, 23, 34);
		car.setDateJoined(dateJoined);
		car.setEmployeeID(20015);
		car.setIsDriver(1);
		car.setIsPickUp(1);
		car.setIsTemporary(1);
		dao.cancelCarpoolPickUp(car);
		CarPoolForm ca = dao.getCarPoolGroupDetails(5009);
		boolean test = (ca == null );
		if(test){
			assertTrue("no passengers retreived", false);
		}else{
			assertTrue("passengers retrieved", true);
		}
	}
/*
 * checking opting out of CRP
 */
	@Test
	public void testOptOutCrp() {
		EmployeeForm em = new EmployeeForm();
		em.setEmployeeID(20015);
		dao.optOutCrp(20015);
		EmployeeForm tempEmployee = dao.getEmployeeRecord(20015);
        boolean test = (tempEmployee == null );
        if(test){
                assertTrue("Employee record was deleted", true);
        }else{
                assertTrue("Employee record is still present", false);
        }
	}

	@Test
	public void testCancelCarpoolDrive() {
		fail("Not yet implemented");
	}
/*
 * fetching employee details with empid
 */
	@Test
	public void testGetEmployeeRecord() {
		EmployeeForm em = new EmployeeForm();
		em.setEmployeeID(20015);
		EmployeeForm tempEmployee = dao.getEmployeeRecord(20015);
		boolean test = (tempEmployee == null );
        if(test){
                assertTrue("Employee record was not present", false);
        }else{
                assertTrue("Employee record is present", true);
        }
	}
/*
 * testing optiong out of carpool
 */
	@Test
	public void testOptOutCarpool() {
		EmployeeForm em = new EmployeeForm();
		em.setEmployeeID(20015);
		dao.optOutCarpool(20015);
		
		CarPoolForm ca = dao.getCarPoolGroupDetails(5009);
		
		boolean test = (ca == null );
        if(test){
                assertTrue("Employee record was not present", false);
        }else{
                assertTrue("Employee record is present", true);
        }
	}

	@Test
	public void testGetMemberInfo() {
		EmployeeForm em = new EmployeeForm();
		em.setEmployeeID(20015);
		dao.getMemberInfo(20015);
CarPoolForm ca = dao.getCarPoolGroupDetails(5009);
		
		boolean test = (ca == null );
        if(test){
                assertTrue("Employee record was not present", false);
        }else{
                assertTrue("Employee record is present", true);
        }
	}
/*
 * updating user details
 */
	@Test
	public void testUpdateUserDetails() {
		EmployeeForm em = new EmployeeForm();
		em.setAddress("835 S Laflin");
		em.setEmailID("rvisha2@uic.edu");
		em.setEmployeeID(1);
		em.setFirstName("Rajeev Reddy");
		em.setLastName("Vishaka");
		em.setNotifyType(1);
		em.setPassword("password");
		em.setPhoneNo("312-361-4284");
		em.setPoints(10);
		dao.updateUserDetails(em);
		EmployeeForm e = dao.getEmployeeRecord(20015);
		boolean test = (e == null );
        if(test){
                assertTrue("Employee record was not present", false);
        }else{
                assertTrue("Employee record is present", true);
        }
		
	}

}
