package crp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runners.MethodSorters;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolForm;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
import com.crs.service.LoginService;


/**
 * This class contains all the test cases to check the methods in the
 * CrsDAOTest.java 
 * @author Rajeev / Mohan
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CrsDAOTest {
		
	private CrsDAO dao;	
	
	public CrsDAOTest(){
		dao = new CrsDAO();		
	}
	
	/**
	 * Check for the database connection
	 *  This is being implemented with before tag.
	 */
	@BeforeClass
	public static void asetUp() {		
		
		JUnitCore junit = new JUnitCore();
		Result result = junit.run(MyBatisConnectionFactoryTest.class);
		if(result != null){
			assertTrue("Database is connected", true);
		}else{
			assertTrue("Database is not connected", false);
			//System.exit(0);
		}
	}
	
	
	@Test
	public void testAInsertEmployeeRecord() {
		CrsDAO crs = new CrsDAO();
		LoginService ls = new LoginService();
		EmployeeForm em = new EmployeeForm();
		
		em.setEmployeeID(20003);
		em.setAddress("835 S Laflin");
		em.setEmailID("rvisha2@uic.edu");
		em.setFirstName("Rajeev Reddy");
		em.setSalt(ls.generateSalt());
		em.setLastName("Vishaka");
		em.setSecurityAns("answer");
		em.setSecurityQn("Questions");
		em.setNotifyType(0);
		em.setPassword(ls.generateMD5HashForPasswordWithSalt("password"));
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
	}
	
	/**
	 * Test case for getting the login record
	 * using employee email id.
	 *   
	 */
	@Test
	public void testBGetLoginRecord(){
		EmployeeForm employee = new EmployeeForm();
		employee.setEmailID("rvisha2@uic.edu");
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
	public void testcGetLoginRecordWithEmpID() {

		EmployeeForm tempEmployee = dao.getLoginRecordWithEmpID(20061);
		boolean test = (tempEmployee == null );
		if(test){
			assertTrue("Employee record not received or employee not present in the database", false);
		}else{
			assertTrue("Employee record received", true);
		}
	}

	
/*
 * getting carpool details
 */
	@Test
	public void testdGetCarPoolGroup() {
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

		CarPoolForm car = dao.getCarPoolGroupDetails(5025);
		boolean test = (car == null );
		if(test){
			assertTrue("No carpool group retireved", false);
		}else{
			assertTrue("Carpool group retrieved", true);
		}	
	}


/*
 * checking member fetching using email ID
 */
	@Test
	public void testFetchMembersEmailID() {

		List car = dao.fetchMembersEmailID(5025);
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
		dao.checkOut(5025, 20061);
		CarPoolForm ca = dao.getCarPoolGroupDetails(5025);
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

		dao.checkIn(5025);
		CarPoolForm ca = dao.getCarPoolGroupDetails(5025);
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
		dao.createNewCarPoolGroup();
		CarPoolForm ca = dao.getCarPoolGroupDetails(5026);
		boolean test = (ca == null );
		if(test){
			assertTrue("No carpool group retireved", false);
		}else{
			assertTrue("Carpool group retrieved", true);
		}
	}

	/**
	 * Retreive all free carpool groups
	 *   
	 */
	@Test
	public void testRetrieveAllFreeCarpoolGroups() {
		List<CarPoolMemberForm> listCarpools;
		
		listCarpools = dao.retrieveAllFreeCarpoolGroups();
		boolean test = (listCarpools != null);
		if(test){
			
			boolean test2 = (listCarpools.size() > 0);
			if(test2){
				assertTrue("Free carpools groups retrieved", true);
			}else{
				assertTrue("There are no free carpool groups", true);
			}
		}else{
			assertTrue("Free carpools groups not retrieved.", false);
		}
		
	}

	/**
	 * retrieve drivers list
	 *   
	 */
	@Test
	public void testRetrieveDrivers() {
		ArrayList<CarPoolMemberForm> listDriver;
		listDriver = dao.retrieveDrivers();
		boolean test = (listDriver != null);
		if(test){
			boolean test2 = (listDriver.size() > 0);
			if(test2){
				assertTrue("Drivers retrieved", true);
			}else{
				assertTrue("There are no drivers", true);
			}
		}else{
			assertTrue("Drivers not retrieved. Exception", false);
		}
	}
	
	
	/*
	 * updating the curent driver
	 *   
	 */
	@Test
	public void testBBUpdateCurrentDriver() {

		CarPoolMemberForm cm = dao.getMemberInfo(20063);		
		if (cm != null) {
			dao.updateCurrentDriver(20063);
			cm = dao.getMemberInfo(20063);
			boolean test = (cm == null );
			if(test){
				assertTrue("No drivers updated", false);
			}else{
				
				int isDriver = cm.getIsDriver();
				boolean test2 = (isDriver == 1);
				if(test2){
					assertTrue("drivers updated", true);
				}else{
					assertTrue("driver not updated", false);
				}
			}
		}else {
			assertTrue("member not present", false);
		}
	}

	/**
	 * update next driver
	 *   
	 */
	@Test
	public void testBBUpdateNextDriver() {
	
		CarPoolMemberForm cm = dao.getMemberInfo(20063);
		if (cm != null) {
			dao.updateNextDriver(20063);
			cm = dao.getMemberInfo(20063);

			boolean test = (cm == null);
			if (test) {
				assertTrue("No drivers updated", false);
			} else {

				int isDriver = cm.getIsDriver();
				boolean test2 = (isDriver == 1);
				if (test2) {
					assertTrue("drivers updated", true);
				} else {
					assertTrue("driver not updated", false);
				}
			}
		} else {
			assertTrue("member not present", false);
		}
	}

	/**
	 * Update temporary driver
	 *   
	 */
	@Test
	public void testBBUpdateTemporaryDriver() {
		
		CarPoolMemberForm cm = dao.getMemberInfo(20062);
		if (cm != null) {
			dao.updateTemporaryDriver(20062);
			cm = dao.getMemberInfo(20062);

			boolean test = (cm == null);
			if (test) {
				assertTrue("No drivers updated", false);
			} else {

				int isDriver = cm.getIsDriver();
				int isTemp = cm.getIsTemporary();
				boolean test2 = ((isDriver == 1) && (isTemp == 1));
				if (test2) {
					assertTrue("temporary driver updated", true);
				} else {
					assertTrue("temporary driver not updated correctly", false);
				}
			}
		} else {
			assertTrue("member not present", false);
		}
	}
	
/*
 * retrieving all the passengers \
 *
 */
	@Test
	public void testBBBRetrievePassengers() {
		List<Object> tempList = new ArrayList<Object>();;

		tempList = dao.retrievePassengers(5025);

		boolean test = (tempList.size() == 0 );
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
		car.setCarpoolID(5025);
		Date dateJoined= new Date(2013, 2, 12, 10, 23, 34);
		car.setDateJoined(dateJoined);
		car.setEmployeeID(20015);
		car.setIsDriver(1);
		car.setIsPickUp(1);
		car.setIsTemporary(1);
		tempList = dao.retrieveMembers(car);

		boolean test = (tempList.size() == 0 );
		if(test){
			assertTrue("no passengers retreived", false);
		}else{
			assertTrue("passengers retrieved", true);
		}
	}

	/**
	 * get the newly created carpool group
	 *   
	 */
	@Test
	public void testGetLatestCarpoolGroup() {
		
		CarPoolForm cm = dao.getLatestCarpoolGroup();
		boolean test = (cm == null);
		if(test){
			assertTrue("latest carpool group not retrieved", false);
		}else{
			assertTrue("latest carpool group retrieved", true);
		}
	}
	
/*
 * testing cancel pickup
 */
	@Test
	public void testCancelCarpoolPickUp() {
		CarPoolMemberForm car = new CarPoolMemberForm();
		car.setCarpoolID(5025);
		Date dateJoined= new Date(2013, 2, 12, 10, 23, 34);
		car.setDateJoined(dateJoined);
		car.setEmployeeID(20065);
		car.setIsDriver(1);
		car.setIsPickUp(1);
		car.setIsTemporary(1);
		dao.cancelCarpoolPickUp(car);
		CarPoolMemberForm ca = dao.getMemberInfo(20065);
		boolean test = (ca != null && (ca.getIsPickUp() == 0) );
		if(test){
			assertTrue("Cancel Unsuccessful", false);
		}else{
			assertTrue("Cancelled!!", true);
		}
	}
	
/*
 * checking opting out of CRP
 */
	@Test
	public void testOptOutCrp() {
		EmployeeForm em = new EmployeeForm();
		em.setEmployeeID(20063);
		dao.optOutCrp(20063);
		EmployeeForm tempEmployee = dao.getEmployeeRecord(20063);
        boolean test = (tempEmployee == null );
        if(test){
                assertTrue("Employee record was deleted", true);
        }else{
                assertTrue("Employee record is still present", false);
        }
	}

	/**
	 * cancel car pool group
	 *   
	 */
	@Test
	public void testCancelCarpoolDrive() {
		CarPoolMemberForm carPoolMember = new CarPoolMemberForm();
		carPoolMember.setEmployeeID(20062);
		int beforePoints = 0;
		CarPoolMemberForm cm = dao.getMemberInfo(20062);
		EmployeeForm em = dao.getLoginRecordWithEmpID(20062);
		
		if(em != null){
			beforePoints = em.getPoints();
		}
		
		if (cm != null) {
			
			/*cancelled the driver*/
			dao.cancelCarpoolDrive(carPoolMember);
			
			/*testing if done correctly*/
			cm = dao.getMemberInfo(20062);
			em = dao.getLoginRecordWithEmpID(20062);
			
			if (cm != null && em != null) {
				
				int isDriver = cm.getIsDriver();
				int isTemp = cm.getIsTemporary();
				int points = em.getPoints();
				boolean test = ((isDriver == 0) && (isTemp == 2) && (points == (beforePoints - 3)));
				if(test){
					assertTrue("cancel driver update done successfully", true);
				}else{
					assertTrue("cancel driver update was not correctly done", false);
				}
				
			} else {
				assertTrue("member not present", false);
			}
		} else {
			assertTrue("member not present", false);
		}
		
	}
/*
 * fetching employee details with empid
 */
	@Test
	public void testGetEmployeeRecord() {

		EmployeeForm tempEmployee = dao.getEmployeeRecord(20061);
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
		em.setEmployeeID(20062);
		dao.optOutCarpool(20062);
		
		CarPoolForm ca = dao.getCarPoolGroupDetails(20062);
		
		boolean test = (ca == null );
        if(test){
                assertTrue("Opted out", true);
        }else{
                assertTrue("Employee record is present. Unsuccessful", false);
        }
	}

	@Test
	public void testGetMemberInfo() {
		CarPoolMemberForm em = null;

		em =dao.getMemberInfo(20061);
		
		boolean test = (em == null );
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
		LoginService ls = new LoginService();
		EmployeeForm em = new EmployeeForm();
		EmployeeForm em1 = new EmployeeForm();
		
		em.setEmployeeID(20061);
		em.setAddress("835 S Laflin");
		em.setEmailID("mkarun2@uic.edu");
		em.setFirstName("Rajeev Reddy");
		em.setSalt(ls.generateSalt());
		em.setLastName("Vishaka");
		em.setSecurityAns("answer");
		em.setSecurityQn("Questions");
		em.setNotifyType(0);
		em.setPassword(ls.generateMD5HashForPasswordWithSalt("password"));
		em.setPhoneNo("312-361-4284");
		em.setPoints(10);
		dao.updateUserDetails(em);
		em1.setEmailID("mkarun2@uic.edu");
		em1.setPassword("mohan");  // previously set password
		List list = new LoginService().login(em1);
		boolean test = (list == null );
        if(test){
                assertTrue("Employee record was updated successfully", true);
        }else{
                assertTrue("Employee record was not updated", false);
        }
		
	}

}
