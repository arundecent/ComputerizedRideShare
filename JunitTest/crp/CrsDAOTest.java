package crp;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.crs.dao.CrsDAO;
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
	
}
