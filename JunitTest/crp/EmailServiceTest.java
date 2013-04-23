package crp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolMemberForm;
import com.crs.service.EmailService;

public class EmailServiceTest {

	@Test
	public void testSendEmail() {
		String strSubject = "Message from CRPS";
		String strMessage = "This is a test case for the CRP Email System";
		String toAddress = "mkarun2@uic.edu";
		
		EmailService emailService = new EmailService();
		emailService.sendSimpleMail(strSubject, strMessage, toAddress);
		
		if(emailService.getSuccessString() != null){
			assertTrue("Email was sent successfully.",true);
		}else{
			assertTrue("Email was not sent.",false);
		}		
	}
	
	@Test
	public void testRetrieveDriverList(){
		CrsDAO dao = new CrsDAO();
		ArrayList<CarPoolMemberForm> carpoolDriverList = new ArrayList<CarPoolMemberForm>();	
		carpoolDriverList = dao.retrieveDrivers();
		
		if(carpoolDriverList != null){
			assertTrue("Driver List has been retrieved",true);
		}else{
			assertTrue("Driver List not retreived",false);
		}
	}
	
	@Test
	public void testGenerateEmailToCarpoolers(){
		EmailService emailServiceObj = new EmailService();
		emailServiceObj.generateEmailForCarpoolers();
		
	}

}
