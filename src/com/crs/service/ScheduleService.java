package com.crs.service;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolForm;
import com.crs.model.EmployeeForm;


/**
 * This class will be used to schedule the users into 
 * groups of car pool
 * @author mohan/<other authors mention names>
 *
 */
public class ScheduleService {

	/**
	 * default constructor
	 */
	public ScheduleService(){
		this.dao = new CrsDAO();
		this.emailServiceObj = new EmailService();
	}
	
	/**
	 * getter method for DAO
	 * @return CrsDAO
	 */
	public CrsDAO getDao() {
		return dao;
	}

	/**
	 * setter method for dao
	 * @param dao
	 */
	public void setDao(CrsDAO dao) {
		this.dao = dao;
	}

	/**
	 * getter method for email service object
	 * @return EmailService
	 */
	public EmailService getEmailServiceObj() {
		return emailServiceObj;
	}

	/**
	 * setter method for the email service object
	 * @param emailServiceObj
	 */
	public void setEmailServiceObj(EmailService emailServiceObj) {
		this.emailServiceObj = emailServiceObj;
	}
	
	/**
	 * This method is used to allocate a carpool group for a 
	 * particular employee whose details are contained in the 
	 * employee bean passed as parameter
	 * @param objEmployeeForm
	 */
	public void allocateCarpoolGroup(EmployeeForm objEmployeeForm){
		
	}
	
	/**
	 * This method will be used by the allocateCarpoolGroup method to
	 * schedule the ride share among the group of car poolers
	 */
	public void scheduleRideShare(){
		
	}
	
	/**
	 * This method is used to regroup the car pool groups to handle the
	 * certain exception conditions
	 */
	public void reGroup(){
		
	}
	
	/**
	 * This method is used by the passengers of a particular car pool
	 * group who wants to cancel their trip pick up.
	 * @param objEmployeeForm
	 * @return boolean
	 */
	public boolean cancelPickUp(EmployeeForm objEmployeeForm){
		return false;
	}
	
	/**
	 * This method is used by the driver allocated for the particular day, of
	 * a particular car pool , who wants to cancel his driving chance for the day
	 * @param objEmployeeForm
	 * @return
	 */
	public boolean cancelDrive (EmployeeForm objEmployeeForm){
		return false;
	}
	
	/**
	 *
	 */
	public void removeTemporaryMember(){
		
	}
	
	/**
	 * 
	 * @return CarPoolForm
	 */
	public CarPoolForm searchGroup(){
		CarPoolForm objCarpoolForm = new CarPoolForm();
		return objCarpoolForm;
	}
	
	/**
	 * This method is used when an employee/ car pool member wants to 
	 * leave the car pool group
	 * @param objEmployeeForm
	 * @return boolean
	 */
	public boolean leaveGroup(EmployeeForm objEmployeeForm){
		return false;
	}
	
	//database access object
	private CrsDAO dao;
	
	//email service object to send email
	private EmailService emailServiceObj;
	
}
