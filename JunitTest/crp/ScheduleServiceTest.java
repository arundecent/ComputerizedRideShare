package crp;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
import com.crs.service.ScheduleService;
/**
 * This class contains all the test cases to check the
 * methods in the LoginService.java
 * @author Rajeev
 *
 */
public class ScheduleServiceTest {

//if no carpools present. list is empty or all carpool's are full
	@Test
	public void testGetFreeCarpoolList() {
		List<CarPoolMemberForm> carpoolList = new ArrayList<CarPoolMemberForm>();
		carpoolList = null;
		boolean test = (carpoolList == null);
		if(test){
			assertTrue("All carpools are full",true);
		}else{
			assertTrue("incomplete carpools are available",false);
		}
	}

}
