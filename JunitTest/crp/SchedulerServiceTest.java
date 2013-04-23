package crp;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
import com.crs.service.SchedulerService;
/**
 * This class contains all the test cases to check the
 * methods in the SchedulerService.java
 * @author Rajeev
 *
 */
public class SchedulerServiceTest {

	

	@Test
	public void testSchedulerService() {
		fail("Not yet implemented");
	}

	@Test
	public void testCancelCarpoolDrive() throws Exception{
		try{
		
		//@SuppressWarnings("deprecation")
		Date dateJoined= new Date(2013, 2, 12, 10, 23, 34);
		SchedulerService d = new SchedulerService();
		CarPoolMemberForm carp = new CarPoolMemberForm();
		EmployeeForm em = new EmployeeForm();
		carp.setEmployeeID(1);
		carp.setDateJoined(dateJoined);
		carp.setCarpoolID(1000);
		carp.setIsDriver(1);
		carp.setIsPickUp(1);
		carp.setIsTemporary(0);
		carp.getEmployee();
		carp.setEmployee(em);
		em.setAddress("835 S Laflin");
		em.setEmailID("rvisha2@uic.edu");
		em.setEmployeeID(1);
		em.setFirstName("Rajeev Reddy");
		em.setLastName("Vishaka");
		em.setNotifyType(1);
		em.setPassword("password");
		em.setPhoneNo("312-361-4284");
		em.setPoints(10);
	d.cancelCarpoolDrive(carp);
		if(carp.getEmployee().getPoints() > 0)
		{
			assertTrue("Employee can cancel",true);
		}
		else
		{
			assertTrue("option shouldn't be available",false);
		}
		}
		catch (Exception e) {
            System.out.println("Unknown error.");
            e.printStackTrace();
        }
		
	}

	@Test
	public void testNotifyUsersByEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateScheduler() {
		fail("Not yet implemented");
	}

	@Test
	public void testDestroyScheduler() {
		fail("Not yet implemented");
	}

	@Test
	public void testRun() {
		fail("Not yet implemented");
	}

}
