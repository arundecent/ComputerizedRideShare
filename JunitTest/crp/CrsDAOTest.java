package crp;

import static org.junit.Assert.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.crs.dao.MyBatisConnectionFactory;
import com.crs.model.CarPoolForm;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
import org.junit.Test;
import com.crs.dao.CrsDAO;
/**
 * This class contains all the test cases to check the
 * methods in the CrsDAOTest.java
 * @author Rajeev
 *
 */
public class CrsDAOTest {
// Testinng if the conection is established or not.already done in MyBatisconnectionfactory.java class
	@Test
	public void testCrsDAO() {
		fail("already implemented in MyBatisconnectionfactory.java class ");
	}
// checking if no employee record fetched
	@Test
	public void testGetLoginRecord() {
		try
		{
			EmployeeForm employee = null;
			CrsDAO crs = new CrsDAO();
			crs.getLoginRecord(employee);
			if(employee != null)
			{
				assertTrue("employee recoed fetched", false);
			}
			else
			{
				assertTrue("no employee record", true);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	//valid empoyee record is fetched
	/*@Test
	public void testGetLoginRecordValid() {
		try
		{
			EmployeeForm employee = 
			CrsDAO crs = new CrsDAO();
			crs.getLoginRecord(employee);
			if(employee != null)
			{
				assertTrue("employee recoed fetched", false);
			}
			else
			{
				assertTrue("no employee record", true);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
*/
	@Test
	public void testGetLoginRecordWithEmpID() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertEmployeeRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCarPoolGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCarPoolGroupDetails() {
		fail("Not yet implemented");
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
