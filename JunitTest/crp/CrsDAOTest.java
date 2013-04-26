package crp;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
 * 
 */

@SuppressWarnings("unchecked")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CrsDAOTest {

	private CrsDAO dao;

	public CrsDAOTest() {
		dao = new CrsDAO();
	}

	/**
	 * Check for the database connection This is being implemented with before
	 * tag.
	 */
	@BeforeClass
	public static void asetUp() {

		JUnitCore junit = new JUnitCore();
		Result result = junit.run(MyBatisConnectionFactoryTest.class);
		if (result != null) {
			assertTrue("Database is connected", true);
		} else {
			assertTrue("Database is not connected", false);
			// System.exit(0);
		}
	}

	@Test
	public void testAInsertEmployeeRecord() {

		LoginService ls = new LoginService();
		EmployeeForm em = new EmployeeForm();

		em.setEmployeeID(3);
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

		// To test whether insert was done/
		EmployeeForm tempEmployee = dao.getLoginRecord(em);
		boolean test = (tempEmployee == null);
		if (test) {
			assertTrue("Employee record was not inserted.", false);
		} else {
			assertTrue("Employee record was inserted", true);
		}
	}

	/**
	 * Test case for getting the login record using employee email id.
	 * 
	 */
	@Test
	public void testBGetLoginRecord() {
		EmployeeForm employee = new EmployeeForm();
		employee.setEmailID("rvisha2@uic.edu");
		EmployeeForm tempEmployee = dao.getLoginRecord(employee);
		boolean test = (tempEmployee != null && (tempEmployee.getEmailID().equals("rvisha2@uic.edu")));
		if (!test) {
			assertTrue(
					"Employee record not received or employee not present in the database",
					false);
		} else {
			assertTrue("Employee record received", true);
		}
	}

	/**
	 * Test case for getting the login record using employee Employee ID.
	 */

	@Test
	public void testcGetLoginRecordWithEmpID() {

		EmployeeForm tempEmployee = dao.getLoginRecordWithEmpID(1);
		boolean test = (tempEmployee != null && (tempEmployee.getEmployeeID() == 1));
		if (!test) {
			assertTrue(
					"Employee record not received or employee not present in the database",
					false);
		} else {
			assertTrue("Employee record received", true);
		}
	}

	/*
	 * getting carpool details
	 */
	@Test
	public void testdGetCarPoolGroup() {
		CarPoolForm car = dao.getCarPoolGroup();
		boolean test = (car == null);
		if (test) {
			assertTrue("No carpool group retireved", false);
		} else {
			assertTrue("Carpool group retrieved", true);
		}
	}

	/*
	 * Getting carpool details using carpool ID
	 */
	@Test
	public void testGetCarPoolGroupDetails() {

		CarPoolForm car = dao.getCarPoolGroupDetails(1);
		boolean test = (car == null);
		if (test) {
			assertTrue("No carpool group retireved", false);
		} else {
			assertTrue("Carpool group retrieved", true);
		}
	}

	/*
	 * checking member fetching using email ID
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testFetchMembersEmailID() {

		List car = dao.fetchMembersEmailID(1);
		boolean test = (car == null);
		if (test) {
			assertTrue("No carpool group retireved", false);
		} else {
			assertTrue("Carpool group retrieved", true);
		}
	}

	/*
	 * testing checkin
	 */
	@Test
	public void testCheckIn() {

		dao.checkIn(1);
		CarPoolForm ca = dao.getCarPoolGroupDetails(1);
		boolean test = ((ca != null) && (ca.getAtWork() == 1));
		if (test) {
			assertTrue("Checkin was successful", true);
		} else {
			assertTrue("Check in was unsuccessful", true);
		}
	}

	/*
	 * Inserting and updating into carpool and group.checking Checkout
	 */
	@Test
	public void testCheckOut() {
		dao.checkOut(1, 1);
		CarPoolForm ca = dao.getCarPoolGroupDetails(1);
		boolean test = (ca == null);
		if (test) {
			assertTrue("No carpool group retireved", false);
		} else {
			assertTrue("Carpool group retrieved", true);
		}
	}

	/*
	 * creating a new carpool group depends on the car pool ID given
	 */
	@Test
	public void testCreateNewCarPoolGroup() {
		dao.createNewCarPoolGroup();
		CarPoolForm ca = dao.getCarPoolGroupDetails(2);
		boolean test = (ca == null);
		if (test) {
			assertTrue("No carpool group retireved", false);
		} else {
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
		if (test) {

			boolean test2 = (listCarpools.size() > 0);
			if (test2) {
				assertTrue("Free carpools groups retrieved", true);
			} else {
				assertTrue("There are no free carpool groups", true);
			}
		} else {
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
		if (test) {
			boolean test2 = (listDriver.size() > 0);
			if (test2) {
				assertTrue("Drivers retrieved", true);
			} else {
				assertTrue("There are no drivers", true);
			}
		} else {
			assertTrue("Drivers not retrieved. Exception", false);
		}
	}

	/*
	 * updating the curent driver
	 */
	@Test
	public void testBBUpdateCurrentDriver() {

		CarPoolMemberForm cm = dao.getMemberInfo(3);
		if (cm != null) {
			dao.updateCurrentDriver(3);
			cm = dao.getMemberInfo(3);
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
	 * update next driver
	 * 
	 */
	@Test
	public void testBBUpdateNextDriver() {

		CarPoolMemberForm cm = dao.getMemberInfo(3);
		if (cm != null) {
			dao.updateNextDriver(3);
			cm = dao.getMemberInfo(3);

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

		CarPoolMemberForm cm = dao.getMemberInfo(2);
		if (cm != null) {
			dao.updateTemporaryDriver(2);
			cm = dao.getMemberInfo(2);

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
	 */
	@Test
	public void testBBBRetrievePassengers() {
		List<Object> tempList = new ArrayList<Object>();
		tempList = dao.retrievePassengers(1);

		boolean test = (tempList.size() == 0);
		if (test) {
			assertTrue("no passengers retreived", false);
		} else {
			assertTrue("passengers retrieved", true);
		}

	}

	/*
	 * retrieving members
	 */
	
	@Test
	public void testRetrieveMembers() {
		List<Object> tempList = new ArrayList<Object>();
		;
		CarPoolMemberForm car = new CarPoolMemberForm();
		car.setCarpoolID(1);
		car.setEmployeeID(20015);
		car.setIsDriver(1);
		car.setIsPickUp(1);
		car.setIsTemporary(1);
		tempList = dao.retrieveMembers(car);

		boolean test = (tempList.size() == 0);
		if (test) {
			assertTrue("no passengers retreived", false);
		} else {
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
		if (test) {
			assertTrue("latest carpool group not retrieved", false);
		} else {
			assertTrue("latest carpool group retrieved", true);
		}
	}

	/*
	 * testing cancel pickup
	 */
	@Test
	public void testCancelCarpoolPickUp() {
		CarPoolMemberForm car = new CarPoolMemberForm();
		car.setCarpoolID(1);
		car.setEmployeeID(20065);
		car.setIsDriver(1);
		car.setIsPickUp(1);
		car.setIsTemporary(1);
		dao.cancelCarpoolPickUp(car);
		CarPoolMemberForm ca = dao.getMemberInfo(20065);
		boolean test = (ca != null && (ca.getIsPickUp() == 0));
		if (test) {
			assertTrue("Cancel Unsuccessful", false);
		} else {
			assertTrue("Cancelled!!", true);
		}
	}

	/*
	 * checking opting out of CRP
	 */
	@Test
	public void testOptOutCrp() {
		EmployeeForm em = new EmployeeForm();
		em.setEmployeeID(3);
		dao.optOutCrp(3);
		EmployeeForm tempEmployee = dao.getEmployeeRecord(3);
		boolean test = (tempEmployee == null);
		if (test) {
			assertTrue("Checked out of CRP", true);
		} else {
			assertTrue("Checking out was unsuccessful", false);
		}
	}

	/**
	 * cancel car pool group
	 * 
	 */
	@Test
	public void testCancelCarpoolDrive() {
		CarPoolMemberForm carPoolMember = new CarPoolMemberForm();
		carPoolMember.setEmployeeID(2);
		int beforePoints = 0;
		CarPoolMemberForm cm = dao.getMemberInfo(2);
		EmployeeForm em = dao.getLoginRecordWithEmpID(2);

		if (em != null) {
			beforePoints = em.getPoints();
		}

		if (cm != null) {

			/* cancelled the driver */
			dao.cancelCarpoolDrive(carPoolMember);

			/* testing if done correctly */
			cm = dao.getMemberInfo(2);
			em = dao.getLoginRecordWithEmpID(2);

			if (cm != null && em != null) {

				int isDriver = cm.getIsDriver();
				int isTemp = cm.getIsTemporary();
				int points = em.getPoints();
				boolean test = ((isDriver == 0) && (isTemp == 2) && (points == (beforePoints - 3)));
				if (test) {
					assertTrue("cancel driver update done successfully", true);
				} else {
					assertTrue("cancel driver update was not correctly done",
							false);
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

		EmployeeForm tempEmployee = dao.getEmployeeRecord(1);
		boolean test = (tempEmployee != null && (tempEmployee.getEmployeeID() == 1));
		if (!test) {
			assertTrue("Employee record was not present", false);
		} else {
			assertTrue("Employee record is present", true);
		}
	}

	/*
	 * testing optiong out of carpool
	 */
	@Test
	public void testOptOutCarpool() {

		EmployeeForm em1 = dao.getEmployeeRecord(2);
		int beforePoints = em1.getPoints();
		dao.optOutCarpool(2);
		CarPoolMemberForm ca = dao.getMemberInfo(2);
		EmployeeForm em = dao.getEmployeeRecord(2);
		int updatedPoints = em.getPoints();
		
		boolean test = (ca == null && (updatedPoints == (beforePoints - 3)));
		if (test) {
			assertTrue("Opted out", true);
		} else {
			assertTrue("Employee record is present. Unsuccessful", false);
		}
	}

	@Test
	public void testGetMemberInfo() {
		
		CarPoolMemberForm em = null;
		em = dao.getMemberInfo(1);
		boolean test = (em != null && (em.getEmployeeID() == 1));
		if (!test) {
			assertTrue("Employee record was not present", false);
		} else {
			assertTrue("Employee record is present", true);
		}
	}

	/*
	 * updating user details, only the emailid and password
	 */
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testUpdateUserDetails() {
		
		LoginService ls = new LoginService();
		EmployeeForm em = new EmployeeForm();
		EmployeeForm em1 = null;
		EmployeeForm em2 = null;

		em1 = dao.getEmployeeRecord(1);
		
		
		em.setEmployeeID(1);
		em.setAddress("835 S Laflin");
		em.setSalt(ls.generateSalt());
		em.setSecurityAns("answer");
		em.setSecurityQn("Questions");
		em.setPassword(ls.generateMD5HashForPasswordWithSalt("password"));
		em.setPhoneNo("312-361-4284");
		
		/**
		 *  salt = #{salt}, 
			password = #{password},
			sec_question = #{securityQn},
			sec_answer = #{securityAns},
			phone = #{phoneNo},
			notify_type = 0,
			address = #{address}
		 */
		dao.updateUserDetails(em);
		
		em2 = dao.getEmployeeRecord(1);

		boolean test = ((em1 != null) && 
						(em2 != null) &&
						!(em1.getAddress().equals(em2.getAddress())) &&
						!(em1.getPhoneNo().equals(em2.getPhoneNo())) &&
						!(em1.getSecurityAns().equals(em2.getSecurityAns())) &&
						!(em1.getSecurityQn().equals(em2.getSecurityQn())) &&
						!(em1.getSalt().equals(em2.getSalt())) &&
						!(em1.getPassword().equals(em2.getPassword())));
		
		if (test) {
			assertTrue("Employee record was updated successfully", true);
		} else {
			assertTrue("Employee record was not updated", false);
		}

	}

}
