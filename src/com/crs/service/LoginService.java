package com.crs.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.crs.dao.CrsDAO;
import com.crs.interfaces.LoginServiceInterface;
import com.crs.model.CarPoolForm;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
/*import statements for the MD5 hash function*/
/*Apache code library for Password Hashing*/


/**
 * This class will contain all the service methods relating to
 * the login and registration usecases of the CRP.
 * @author subbu/mohan
 *
 */
public class LoginService implements LoginServiceInterface{
	
	//CrsDAO to make database transactions
	private CrsDAO dao;
	
	//salt for the password hash
	private String strSalt;
	
	/*
	 * Log file for this class
	 */
	static Logger logger = Logger.getLogger(LoginService.class);
	
	
	public String getStrSalt() {
		return strSalt;
	}

	public void setStrSalt(String strSalt) {
		this.strSalt = strSalt;
	}

	/**
	 * Getter method for CrsDAO instance variable 
	 * @return CrsDAO
	 */
	public CrsDAO getDao() {
		return dao;
	}

	/**
	 * Setter method for CrsDAO
	 * @param dao
	 */
	public void setDao(CrsDAO dao) {
		this.dao = dao;
	}

	/**
	 * Default Constructor used to instantiate the 
	 * data access object 
	 */
	public LoginService(){
		this.dao = new CrsDAO();
		this.strSalt = this.generateSalt();
		PropertyConfigurator.configure("Log4j/log4j.properties");
	}
	
	
	/**
	 * This method is used to employee details after 
	 * user enters his/her login credentials. 
	 * @return EmployeeForm (Employee bean)
	 * @author Subbu @author login check made by mohan
	 */
	@Override
	public EmployeeForm login(EmployeeForm employee) {
		logger.info("starting to execute Employee Login");
		
		if(employee.getEmailID() != null && employee.getPassword() != null){
			EmployeeForm employeeDetails = dao.getLoginRecord(employee);
			
			if(employeeDetails != null){
				//check for username and password
				if(employee.getEmailID().equals(employeeDetails.getEmailID())){
					//check for the password
					String hashPasswordFromDB = employeeDetails.getPassword();
					String salt = employeeDetails.getSalt();
					this.setStrSalt(salt);
					String passEntered = this.generateMD5HashForPasswordWithSalt(employee.getPassword());
					
					if(hashPasswordFromDB.equals(passEntered)){
						System.out.println("Result : " +employeeDetails.getFirstName());
						return employeeDetails;
					}
					else{
						return null;
					}
				}
				else{
					return null;
				}
			}
			else{
				return null;
			}
		}else{
			return null;
		}		
	}

	/**
	 * This method is used to the reset the password 
	 * for the user
	 * @return boolean depending on a successful reset or failure
	 * @author Subbu
	 */
	@Override
	public boolean resetPassword(String emailId) {
		
		return false;
	}

	/**
	 * This method is used to register a new user which takes the 
	 * employee bean as the input and saves the details into the database
	 * by invoking a method on the dao object.
	 * @return EmployeeForm
	 * @author Subbu
	 */
	@Override
	public EmployeeForm registerNewUser(EmployeeForm employee) {
		logger.info("In Login Service  executing method Register New User");
		
		/*
		 *Setting the salt used for this employee
		 *A random salt is generated for each user for
		 *the security of the application. A particular salt will
		 *be used to check the login for a particular user
		 */
		employee.setSalt(this.getStrSalt());
		
		/*
		 * hashed password should be set to the employee object before database save.
		 */
		employee.setPassword(this.generateMD5HashForPasswordWithSalt(employee.getPassword()));
		
		/*
		 * saving the new user details to the database using dao
		 */
		dao.insertEmployeeRecord(employee);		
		logger.debug("Employee details inserted into Employee Table");
		
		/*
		 * Getting the car pool with the free places
		 */
		CarPoolForm carPoolForm = dao.getCarPoolGroup();
		
		/*
		 * Create a new record for the user
		 */
		CarPoolMemberForm carPoolMember = new CarPoolMemberForm();
		carPoolMember.setEmployee(employee);
		carPoolMember.setDateJoined(new Date());
		carPoolMember.setIsPickUp(1);
		carPoolMember.setIsTemporary(0);
			
		/*
		 * if a carPoolForm object is returned, then
		 * there is a place in an already existing car pool
		 * group.
		 * If a null is returned, create a new group for
		 * the user and make him as the driver
		 */
		if(carPoolForm != null){
			/*
			 * The user will become a passenger in an already
			 * existing carpool
			 */
			carPoolMember.setIsDriver(0);
			carPoolMember.setCarpoolID(carPoolForm.getCarpoolID());
			createNewMember(carPoolMember);
			logger.debug("Calling function to create a new carpool passenger in the db");
		}
		else{
			/*
			 * New carpool is created and this user is assigned 
			 * to be the driver
			 */
			carPoolMember.setIsDriver(1);
			carPoolForm = dao.createNewCarPoolGroup();
			carPoolMember.setCarpoolID(carPoolForm.getCarpoolID());
			createNewMember(carPoolMember);
			logger.debug("Calling function to create a new carpool driver in the db");
		}
		
		logger.info("Car Pool Group Assigned Details : "+carPoolForm.getCarpoolID());
		EmployeeForm employeeDetails = null;
		return employeeDetails;
	}
	
	
	public void createNewMember(CarPoolMemberForm carPoolMember){
		dao.createNewMember(carPoolMember);
		logger.info("created a new carpool member");
	}
	/**
	 * This method is used to generate the hashing of password without salt
	 * @param strPassword
	 * @return String
	 * @author mohan
	 */
	public String generateMD5HashForPassword(String strPassword){
		if(strPassword == null){
			return "null";
		}
		else {
			//Apache's built in MD5 password hashing function.
			return DigestUtils.md5Hex( strPassword ).toString();				
		}
	}
	
	/**
	 * This method is used to generate the hashing of password with salt
	 * retrieved from the database
	 * @param strPassword
	 * @return String
	 * @author mohan
	 */
	public String generateMD5HashForPasswordWithSalt(String strPassword){
		if(strPassword == null){
			return "null";
		}
		else {
			String strHashedPassword = null;						
			String strSaltPassword = strPassword + this.getStrSalt();
			
			try {
				//MessageDigest Object for the hashing algorithm MD5
		        MessageDigest messageDigestObj = MessageDigest.getInstance("MD5");	
		        
		        //conversion to bytes
		        messageDigestObj.update(strSaltPassword.getBytes(), 0, strSaltPassword.length());
		        
		        //hexadecimal conversion
		        strHashedPassword = new BigInteger(1, messageDigestObj.digest()).toString(16);
			}
			catch ( NoSuchAlgorithmException e){
				e.printStackTrace();
			}
			
			return strHashedPassword;
		}
	}
	
	/**
	 * Generate a secure random salt
	 * @return
	 */
	public String generateSalt(){
		String salt = null;
		SecureRandom random = new SecureRandom();
		salt = new BigInteger(130, random).toString(32);		
		return salt;
	}
}
