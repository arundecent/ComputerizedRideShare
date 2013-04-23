package crp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
import com.crs.service.EmailService;

/**
 * Test cases for the EmailService.java. This test class
 * handles the white box and the black box testing,
 * path and branch testing.
 * @author mkarun2
 *
 */
public class EmailServiceTest {

	/**
	 * This test case tests whether a mail is being
	 * successfully sent using the message id which
	 * the Apache API return on successful sending of
	 * an email message
	 */
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
	
	
	/**
	 * This method is used to test the retrieval of the drivers
	 * from the database which is essential for the retrieval
	 * of the passengers from the database
	 */
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
	
	/**
	 * Black box test case for generating emails and sending
	 * to the carpoolers
	 */
	@Test
	public void testGenerateEmailToCarpoolersBlackBox(){
		EmailService emailServiceObj = new EmailService();
		emailServiceObj.generateEmailForCarpoolers();		
	}
	
	/**
	 * This test method does assertions on each branch of 
	 * the function and finally sends emails to the 
	 * passengers and the drivers.
	 */
	@Test
	public void testGenerateEmailToCarpoolersWhiteBox(){
		
		HashMap<Integer,List<EmployeeForm>> passengerMap = new HashMap<Integer, List<EmployeeForm>>();		
		EmailService emailServiceObj = new EmailService();
		CrsDAO dao = new CrsDAO();
		
		//This local variable is used to store the member details of a particular car pool
		ArrayList<CarPoolMemberForm> carpoolDriverList = new ArrayList<CarPoolMemberForm>();		
		
		//This local variable is used to store the passenger details of the members 
		//belonging to the same car pool as a particular driver
		List<CarPoolMemberForm> carpoolMembersList = new ArrayList<CarPoolMemberForm>();
		
		//Iterate over the driver List
		Iterator<CarPoolMemberForm> itDriver = null;
		
		//Iterator for the passenger list
		Iterator<CarPoolMemberForm> itPassengers = null;
		
		//drivers in each car pool group retrieved from the database
		carpoolDriverList = dao.retrieveDrivers();
		
		if(carpoolDriverList!= null && carpoolDriverList.size() > 0){
			assertTrue("Driver List has been retreived",true);
		}else{
			fail("Driver List not retrieved");
		}
		
		int carpoolID = 0;
		CarPoolMemberForm objCarpoolMemberForm = new CarPoolMemberForm();
		
		StringBuffer messageBuffer = new StringBuffer();
		StringBuffer driverMessageBuffer = new StringBuffer();
		String message = null;
		EmployeeForm employeeDriverObj;
		
		if((!carpoolDriverList.isEmpty()) && (carpoolDriverList != null)){
			itDriver = carpoolDriverList.iterator();
			while(itDriver.hasNext()){
				
				//retrieve the passenger list belonging to the group in which the 
				//particular driver belongs using the car pool id
				carpoolID = itDriver.next().getCarpoolID();
				if(carpoolID > 0){
					
					//retrieve the passenger list from the database using the car pool id
					carpoolMembersList = dao.retrievePassengers(carpoolID);
					
					if(carpoolMembersList!= null && carpoolMembersList.size() > 0){
						assertTrue("Passenger List has been retreived for each driver",true);
					}
					
					//if there are members returned
					if((!carpoolMembersList.isEmpty()) && (carpoolMembersList!= null)){
											
						itPassengers = carpoolMembersList.iterator();
						while(itPassengers.hasNext()){
							
							//have to do this next() only once for each iteration
							objCarpoolMemberForm = itPassengers.next();
							
							if(objCarpoolMemberForm != null){
								if(objCarpoolMemberForm.getEmployeeList() != null){
									//car pool id,passenger list details
									passengerMap.put(carpoolID, objCarpoolMemberForm.getEmployeeList());
									
									if(passengerMap!= null && passengerMap.size() > 0){
										assertTrue("Passenger Map has been updated for each driver",true);
									}
								}								
							}
						}   //end while
					}	//end if
				} 	// end if
			}	//end while
		}else{
			assertTrue("Driver List failue",false);
		}
		
		
		//for each driver, get the passengers in his/her list
		itDriver = carpoolDriverList.iterator();
		Date todayDate = new Date();
		
		while(itDriver.hasNext()){
			
			//get each driver object
			objCarpoolMemberForm = itDriver.next();			
			
			//check if the driver object has details
			if (objCarpoolMemberForm != null) {
				
				//get the car pool id of driver
				carpoolID = objCarpoolMemberForm.getCarpoolID();
				
				//get the driver details from the database using the persistence class
				employeeDriverObj = dao.getEmployeeRecord(objCarpoolMemberForm.getEmployeeID());
				
				//check if the driver has details or not
				if(employeeDriverObj != null){
					
					//generate the driver email message
					driverMessageBuffer.append("Hello " + employeeDriverObj.getFirstName() + " " + employeeDriverObj.getLastName() + ",\n");
					driverMessageBuffer.append("You are the driver for "+todayDate+" belonging to CRPID = "+carpoolID+"\n\n");
					driverMessageBuffer.append("Please find the passenger details below : \n\n");
				
					//get the list of passengers in the car pool from the passenger hash map
					List<EmployeeForm> passList = passengerMap.get(carpoolID);
					
					//check if the list retrieved is not empty
					if(passList != null)
					{
						//for each employee send an email
						for (EmployeeForm employeePassengerObj : passList) {
							
							//check if passenger has details
							if (employeePassengerObj != null) {
																
								//check if driver has details
								if(employeeDriverObj != null){
									
									//generate the email content for the passengers and send mail to each passenger
									messageBuffer.append("Hello " + employeePassengerObj.getFirstName() + " "+ employeePassengerObj.getLastName() + ",\n");
									messageBuffer.append("You belong to the car pool driven by " + employeeDriverObj.getFirstName() + " " + employeeDriverObj.getLastName()+"\n");
									messageBuffer.append("Please find the contact details below : \n");
									messageBuffer.append("Driver Phone Number: " + employeeDriverObj.getPhoneNo() + "\n");
									messageBuffer.append("Driver Email: " + employeeDriverObj.getEmailID() + "\n");
									messageBuffer.append("\n\nThank you, \nRegards, \nComputerized Rideshare Program \n");
									message = new String(messageBuffer);
									emailServiceObj.sendSimpleMail("CRP - Carpool Details",message,employeePassengerObj.getEmailID());
									if(emailServiceObj.getSuccessString() != null){
										assertTrue("Email to passengers have been sent.",true);
									}else{
										assertTrue("Email to driver was sent.",false);
									}
									System.out.println("Sent passenger email to: " + employeePassengerObj.getFirstName() + " " + 
														employeePassengerObj.getLastName());
									
									//clear the passenger message buffer
									messageBuffer.delete(0, messageBuffer.length());
								
									//generate the driver email content with the list of passengers
									driverMessageBuffer.append(employeePassengerObj.getFirstName() + " "+ employeePassengerObj.getLastName() + "\n");
									driverMessageBuffer.append("Phone Number: " + employeePassengerObj.getPhoneNo() + "\n");
									driverMessageBuffer.append("Email: " + employeePassengerObj.getEmailID() + "\n\n");									
								} 
							}
						} // end for each
					} // end if passList null
										
				} // end check driver null
				
				//send mail to the driver
				driverMessageBuffer.append("\n\nThank you, \nRegards, \nComputerized Rideshare Program \n");
				message = new String(driverMessageBuffer);
				emailServiceObj.sendSimpleMail("CRP - Carpool Details",message,employeeDriverObj.getEmailID());
				if(emailServiceObj.getSuccessString() != null){
					assertTrue("Email to drivers have been sent.",true);
				}else{
					assertTrue("Email to drivers was not sent.",false);
				}
				System.out.println("Sent driver email to : " + employeeDriverObj.getFirstName() );
				//System.out.println(message);
				//clear driver message buffer
				driverMessageBuffer.delete(0, driverMessageBuffer.length());
				
			}	// end if 	
			
		} // end  while
	}

}
