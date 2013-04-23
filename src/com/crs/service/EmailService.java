
package com.crs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/*public class EmailService implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("SimpleJob says: " + jobKey + " executing at " + new Date());

	}
	
}*/

/**
 * This class is the service which is used 
 * to send email
 * @author Mohan
 *
 */
public class EmailService implements Job{
	
	/**
	 * Default constructor setting this user name, password and email id
	 */
	public EmailService(){
		this.strPassword = "cs442groupb";
		this.strUsername = "crpcs442";
		this.strEmailId = "crpcs442@gmail.com";
		this.dao = new CrsDAO();
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("Email Service sending emails to users: " + jobKey + " executing at " + new Date());
		generateEmailForCarpoolers();

	}
	
	/**
	 * getter method for the user name
	 * @return
	 */
	public String getStrUsername() {
		return strUsername;
	}

	/**
	 * setter method for the user name
	 * @param strUsername
	 */
	public void setStrUsername(String strUsername) {
		this.strUsername = strUsername;
	}

	/**
	 * getter method for the password
	 * @return
	 */
	public String getStrPassword() {
		return strPassword;
	}

	/**
	 * setter method for the password
	 * @param strPassword
	 */
	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}
	
	/**
	 * getter method for the email id
	 * @return
	 */
	public String getStrEmailId() {
		return strEmailId;
	}
	
	public CrsDAO getDao() {
		return dao;
	}

	public void setDao(CrsDAO dao) {
		this.dao = dao;
	}

	public String getSuccessString() {
		return successString;
	}

	public void setSuccessString(String successString) {
		this.successString = successString;
	}

	/**
	 * setter method for email id
	 * @param strEmailId
	 */
	public void setStrEmailId(String strEmailId) {
		this.strEmailId = strEmailId;
	}
	
	/**
	 * This method is a service method used to send email to the respective
	 * email address of the employee
	 * @param strSubject
	 * @param strMessage
	 * @param strToAddress
	 */
	public void sendSimpleMail(String strSubject,String strMessage, String strToAddress)  {
		try{
			
			/*SimpleEmail class is in the apache commons which will be used to send the email*/
			SimpleEmail email = new SimpleEmail();
			
			/*CRP uses the gmail server for the Email Notifications*/
			email.setHostName("smtp.googlemail.com");
			
			/*setting the smtp port*/
			email.setSmtpPort(465);
			
			/*Authentication for the gmail account*/
			email.setAuthenticator(new DefaultAuthenticator("ajaykarthik29","oretension"));
			
			/* accepting the SSL certificate */
			email.setSSLOnConnect(true);
			
			/*Email of CRP Email notification system*/
			email.setFrom(this.getStrEmailId());
			
			/*Email details*/
			email.setSubject(strSubject);
			email.setMsg(strMessage);
			email.addTo(strToAddress);
			
			/*send email*/
			this.successString = email.send();
		}
		catch (EmailException e) {
			System.out.println("The email was not sent!!");
			e.printStackTrace();
		}		
	}
	
	/**
	 * This method is used to retrieve the details of the 
	 * car pool members from the car pool member table, employee table
	 * and get their names so as to send email to the members belonging to
	 * a particular car pool group.
	 * The details of the driver and the passenger will be the content
	 * of this email.
	 */
	@SuppressWarnings("unchecked")
	public void generateEmailForCarpoolers(){
		
		HashMap<Integer,List<EmployeeForm>> passengerMap = new HashMap<Integer, List<EmployeeForm>>();
		
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
								}								
							}
						}   //end while
					}	//end if
				} 	// end if
			}	//end while
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
									sendSimpleMail("CRP - Carpool Details",message,employeePassengerObj.getEmailID());
									
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
				sendSimpleMail("CRP - Carpool Details",message,employeeDriverObj.getEmailID());
				System.out.println("Sent driver email to : " + employeeDriverObj.getFirstName() );
				//System.out.println(message);
				//clear driver message buffer
				driverMessageBuffer.delete(0, driverMessageBuffer.length());
				
			}	// end if 	
			
		} // end  while
		
	}
	
	
	/*=========INSTANCE VARIABLES=============*/
	
	// user name of the CRP Email System
	private String strUsername;
	
	// password of the CRP Email System
	private String strPassword;
	
	// Email ID of the CRP System
	private String strEmailId;
	
	//dao object to retrieve from the database
	private CrsDAO dao;

	//success email send message
	private String successString;
}
