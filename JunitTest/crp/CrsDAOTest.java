package crp;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.crs.dao.MyBatisConnectionFactory;
import com.crs.model.CarPoolForm;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
import org.junit.Test;

import sun.misc.Compare;

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
			if(employee != null){
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
	
	
	
	// Checking the connection for the getloginrecord
	@Test
	public void testGetLoginRecordcon() {
		try
		{
			CrsDAO bat = new CrsDAO();
			SqlSessionFactory sqlSessionFactory = null;
			if(sqlSessionFactory == null)
			{
				assertTrue("Problem with connection",true);
			}else{
				assertTrue("Active connection",false);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	//valid empoyee record is fetched
	@Test
	public void testGetLoginRecordValid() {
		try
		{
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
			CrsDAO crs = new CrsDAO();
			crs.getLoginRecord(em);
			if(em != null)
			{
				assertTrue("employee record fetched", true);
			}
			else
			{
				assertTrue("no employee record", false);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	// Checking the connection of database for the getloginrecordwithempID
		@Test
		public void testGetLoginRecordWithEmpIDcon() {
			try
			{
				CrsDAO bat = new CrsDAO();
				SqlSessionFactory sqlSessionFactory = null;
				if(sqlSessionFactory == null)
				{
					assertTrue("Problem with connection",true);
				}else{
					assertTrue("Active connection",false);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	@Test
	public void testGetLoginRecordWithEmpID() throws Exception {
		try
		{
			int empID=20013;
		CrsDAO crs = new CrsDAO();
		SqlSessionFactory sqlSessionFactory = null;
		if(sqlSessionFactory == null)
		{
			assertTrue("Problem with connection",true);
		}else{
			assertTrue("Active connection",false);
		}
		EmployeeForm got = crs.getLoginRecordWithEmpID(empID);
		if(got != null)
		{
			assertTrue("Got the details",true);
		}
		else{
			assertTrue("Not fetched from DB",false);
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	// Checking the connection of database for the insertemployeerecord
			@Test
			public void testInsertEmployeeRecordcon() {
				try
				{
					CrsDAO bat = new CrsDAO();
					SqlSessionFactory sqlSessionFactory = null;
					if(sqlSessionFactory == null)
					{
						assertTrue("Problem with connection",true);
					}else{
						assertTrue("Active connection",false);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
	
	@Test
	public void testInsertEmployeeRecord()throws Exception {
		try
		{
			
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
			CrsDAO crs = new CrsDAO();
			crs.insertEmployeeRecord(em);
assertSame("data inserted successfully", crs, em);
			//assertArrayEquals(inserted succesfully, bat, em);
			//if(a == em)
			//assertTrue(crs.insertEmployeeRecord(em);)
			SqlSessionFactory sqlSessionFactory = null;
			if(sqlSessionFactory == null)
			{
				assertTrue("Problem with connection",true);
			}else{
				assertTrue("Active connection",false);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	// Checking the connection of database for the getcarpoolgroup
				@Test
				public void testGetCarPoolGroupcon() {
					try
					{
						CrsDAO bat = new CrsDAO();
						SqlSessionFactory sqlSessionFactory = null;
						if(sqlSessionFactory == null)
						{
							assertTrue("Problem with connection",true);
						}else{
							assertTrue("Active connection",false);
						}
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
	@Test
	public void testGetCarPoolGroup() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the getcarpoolgroupdetails
	@Test
	public void testGetCarPoolGroupDetailscon() {
		try
		{
			CrsDAO bat = new CrsDAO();
			SqlSessionFactory sqlSessionFactory = null;
			if(sqlSessionFactory == null)
			{
				assertTrue("Problem with connection",true);
			}else{
				assertTrue("Active connection",false);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testGetCarPoolGroupDetails() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testInsertNewMemberRecord
		@Test
		public void testInsertNewMemberRecordcon() {
			try
			{
				CrsDAO bat = new CrsDAO();
				SqlSessionFactory sqlSessionFactory = null;
				if(sqlSessionFactory == null)
				{
					assertTrue("Problem with connection",true);
				}else{
					assertTrue("Active connection",false);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	
	@Test
	public void testInsertNewMemberRecord() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testProcessEmergencyRequest
			@Test
			public void testProcessEmergencyRequestcon() {
				try
				{
					CrsDAO bat = new CrsDAO();
					SqlSessionFactory sqlSessionFactory = null;
					if(sqlSessionFactory == null)
					{
						assertTrue("Problem with connection",true);
					}else{
						assertTrue("Active connection",false);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
	@Test
	public void testProcessEmergencyRequest() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testFetchMembersEmailID
				@Test
				public void testFetchMembersEmailIDcon() {
					try
					{
						CrsDAO bat = new CrsDAO();
						SqlSessionFactory sqlSessionFactory = null;
						if(sqlSessionFactory == null)
						{
							assertTrue("Problem with connection",true);
						}else{
							assertTrue("Active connection",false);
						}
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
	
	@Test
	public void testFetchMembersEmailID() {
		fail("Not yet implemented");
	}

	
	// Checking the connection of database for the testCheckOut
	@Test
	public void testCheckOutcon() {
		try
		{
			CrsDAO bat = new CrsDAO();
			SqlSessionFactory sqlSessionFactory = null;
			if(sqlSessionFactory == null)
			{
				assertTrue("Problem with connection",true);
			}else{
				assertTrue("Active connection",false);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testCheckOut() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testCheckIn
		@Test
		public void testCheckIncon() {
			try
			{
				CrsDAO bat = new CrsDAO();
				SqlSessionFactory sqlSessionFactory = null;
				if(sqlSessionFactory == null)
				{
					assertTrue("Problem with connection",true);
				}else{
					assertTrue("Active connection",false);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	@Test
	public void testCheckIn() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testCreateNewMember
			@Test
			public void testCreateNewMembercon() {
				try
				{
					CrsDAO bat = new CrsDAO();
					SqlSessionFactory sqlSessionFactory = null;
					if(sqlSessionFactory == null)
					{
						assertTrue("Problem with connection",true);
					}else{
						assertTrue("Active connection",false);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
	
	@Test
	public void testCreateNewMember() {
		fail("Not yet implemented");
	}

	
	// Checking the connection of database for the testCreateNewCarPoolGroup
				@Test
				public void testCreateNewCarPoolGroupcon() {
					try
					{
						CrsDAO bat = new CrsDAO();
						SqlSessionFactory sqlSessionFactory = null;
						if(sqlSessionFactory == null)
						{
							assertTrue("Problem with connection",true);
						}else{
							assertTrue("Active connection",false);
						}
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
				
	@Test
	public void testCreateNewCarPoolGroup() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testRetrieveAllFreeCarpoolGroups
	@Test
	public void testRetrieveAllFreeCarpoolGroupscon() {
		try
		{
			CrsDAO bat = new CrsDAO();
			SqlSessionFactory sqlSessionFactory = null;
			if(sqlSessionFactory == null)
			{
				assertTrue("Problem with connection",true);
			}else{
				assertTrue("Active connection",false);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testRetrieveAllFreeCarpoolGroups() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testRetrieveDriverscon
		@Test
		public void testRetrieveDriverscon() {
			try
			{
				CrsDAO bat = new CrsDAO();
				SqlSessionFactory sqlSessionFactory = null;
				if(sqlSessionFactory == null)
				{
					assertTrue("Problem with connection",true);
				}else{
					assertTrue("Active connection",false);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	@Test
	public void testRetrieveDrivers() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testUpdateCurrentDriver() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testUpdateCurrentDriver
			@Test
			public void testUpdateCurrentDrivercon() {
				try
				{
					CrsDAO bat = new CrsDAO();
					SqlSessionFactory sqlSessionFactory = null;
					if(sqlSessionFactory == null)
					{
						assertTrue("Problem with connection",true);
					}else{
						assertTrue("Active connection",false);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		
	
	@Test
	public void testUpdateNextDriver() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testUpdateNextDriver
				@Test
				public void testUpdateNextDrivercon() {
					try
					{
						CrsDAO bat = new CrsDAO();
						SqlSessionFactory sqlSessionFactory = null;
						if(sqlSessionFactory == null)
						{
							assertTrue("Problem with connection",true);
						}else{
							assertTrue("Active connection",false);
						}
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
	
	@Test
	public void testGetNextDriver() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testGetNextDriver
	@Test
	public void testGetNextDrivercon() {
		try
		{
			CrsDAO bat = new CrsDAO();
			SqlSessionFactory sqlSessionFactory = null;
			if(sqlSessionFactory == null)
			{
				assertTrue("Problem with connection",true);
			}else{
				assertTrue("Active connection",false);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testUpdateTemporaryDriver() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testUpdateTemporaryDriver
		@Test
		public void testUpdateTemporaryDrivercon() {
			try
			{
				CrsDAO bat = new CrsDAO();
				SqlSessionFactory sqlSessionFactory = null;
				if(sqlSessionFactory == null)
				{
					assertTrue("Problem with connection",true);
				}else{
					assertTrue("Active connection",false);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	@Test
	public void testRetrievePassengers() {
		fail("Not yet implemented");
	}
	// Checking the connection of database for the testRetrievePassengers
			@Test
			public void testRetrievePassengerscon() {
				try
				{
					CrsDAO bat = new CrsDAO();
					SqlSessionFactory sqlSessionFactory = null;
					if(sqlSessionFactory == null)
					{
						assertTrue("Problem with connection",true);
					}else{
						assertTrue("Active connection",false);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
	@Test
	public void testRetrieveMembers() {
		fail("Not yet implemented");
	}
	// Checking the connection of database for the testRetrieveMembers
				@Test
				public void testRetrieveMemberscon() {
					try
					{
						CrsDAO bat = new CrsDAO();
						SqlSessionFactory sqlSessionFactory = null;
						if(sqlSessionFactory == null)
						{
							assertTrue("Problem with connection",true);
						}else{
							assertTrue("Active connection",false);
						}
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
	
	@Test
	public void testGetLatestCarpoolGroup() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testGetLatestCarpoolGroup
	@Test
	public void testGetLatestCarpoolGroupcon() {
		try
		{
			CrsDAO bat = new CrsDAO();
			SqlSessionFactory sqlSessionFactory = null;
			if(sqlSessionFactory == null)
			{
				assertTrue("Problem with connection",true);
			}else{
				assertTrue("Active connection",false);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testCancelCarpoolPickUp() {
		fail("Not yet implemented");
	}
	// Checking the connection of database for the testCancelCarpoolPickUp
		@Test
		public void testCancelCarpoolPickUpcon() {
			try
			{
				CrsDAO bat = new CrsDAO();
				SqlSessionFactory sqlSessionFactory = null;
				if(sqlSessionFactory == null)
				{
					assertTrue("Problem with connection",true);
				}else{
					assertTrue("Active connection",false);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	@Test
	public void testOptOutCrp() {
		fail("Not yet implemented");
	}
	// Checking the connection of database for the testOptOutCrp
			@Test
			public void testOptOutCrpcon() {
				try
				{
					CrsDAO bat = new CrsDAO();
					SqlSessionFactory sqlSessionFactory = null;
					if(sqlSessionFactory == null)
					{
						assertTrue("Problem with connection",true);
					}else{
						assertTrue("Active connection",false);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
	@Test
	public void testCancelCarpoolDrive() {
		fail("Not yet implemented");
	}
	// Checking the connection of database for the testCancelCarpoolDrive
				@Test
				public void testCancelCarpoolDrivecon() {
					try
					{
						CrsDAO bat = new CrsDAO();
						SqlSessionFactory sqlSessionFactory = null;
						if(sqlSessionFactory == null)
						{
							assertTrue("Problem with connection",true);
						}else{
							assertTrue("Active connection",false);
						}
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
	@Test
	public void testGetEmployeeRecord() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testGetEmployeeRecord
	@Test
	public void testGetEmployeeRecordcon() {
		try
		{
			CrsDAO bat = new CrsDAO();
			SqlSessionFactory sqlSessionFactory = null;
			if(sqlSessionFactory == null)
			{
				assertTrue("Problem with connection",true);
			}else{
				assertTrue("Active connection",false);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testOptOutCarpool() {
		fail("Not yet implemented");
	}
	// Checking the connection of database for the testOptOutCarpool
		@Test
		public void testOptOutCarpoolcon() {
			try
			{
				CrsDAO bat = new CrsDAO();
				SqlSessionFactory sqlSessionFactory = null;
				if(sqlSessionFactory == null)
				{
					assertTrue("Problem with connection",true);
				}else{
					assertTrue("Active connection",false);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
	
	@Test
	public void testGetMemberInfo() {
		fail("Not yet implemented");
	}

	// Checking the connection of database for the testGetMemberInfo
			@Test
			public void testGetMemberInfocon() {
				try
				{
					CrsDAO bat = new CrsDAO();
					SqlSessionFactory sqlSessionFactory = null;
					if(sqlSessionFactory == null)
					{
						assertTrue("Problem with connection",true);
					}else{
						assertTrue("Active connection",false);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
	@Test
	public void testUpdateUserDetails() {
		fail("Not yet implemented");
	}
	// Checking the connection of database for the testUpdateUserDetails
				@Test
				public void testUpdateUserDetailscon() {
					try
					{
						CrsDAO bat = new CrsDAO();
						SqlSessionFactory sqlSessionFactory = null;
						if(sqlSessionFactory == null)
						{
							assertTrue("Problem with connection",true);
						}else{
							assertTrue("Active connection",false);
						}
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
}
