package com.crs.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;


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
	private CarPoolForm carPoolGroup ;
	private CarPoolMemberForm carPoolMember;
	private CrsDAO dao;
	
	public CarPoolForm getCarPoolGroup() {
		return carPoolGroup;
	}

	public void setCarPoolGroup(CarPoolForm carPoolGroup) {
		this.carPoolGroup = carPoolGroup;
	}

	public CarPoolMemberForm getCarPoolMember() {
		return carPoolMember;
	}

	public void setCarPoolMember(CarPoolMemberForm carPoolMember) {
		this.carPoolMember = carPoolMember;
	}

	//salt for the password hash
	private String strSalt;
	
	
	
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
		//PropertyConfigurator.configure("Log4j/log4j.properties");
	}
	
	
	/**
	 * This method is used to employee details after 
	 * user enters his/her login credentials. 
	 * @return EmployeeForm (Employee bean)
	 * @author Subbu/mohan
	 */
	@Override
	public List login(EmployeeForm employee) {
		
				
		if(employee.getEmailID() != null && employee.getPassword() != null){
			EmployeeForm employeeDetails = dao.getLoginRecord(employee);
			
			if(employeeDetails != null){
				//check for user name and password
				if(employee.getEmailID().equals(employeeDetails.getEmailID())){
					
					//check for the password
					String hashPasswordFromDB = employeeDetails.getPassword();
					
					//get the salt for the particular user
					String salt = employeeDetails.getSalt();
					this.setStrSalt(salt);
					
					/*
					 * hash the password entered using the salt generated for that
					 * particular user id
					 */
					String passEntered = this.generateMD5HashForPasswordWithSalt(employee.getPassword());
					
					//check for password match
					if(hashPasswordFromDB.equals(passEntered)){
						System.out.println("Successful Login : " +employeeDetails.getFirstName());
						//carPoolMember.setEmployee(employeeDetails);
						List<CarPoolMemberForm> tempList = new ArrayList<CarPoolMemberForm>();
						carPoolMember = dao.getMemberInfo(employeeDetails.getEmployeeID());
						if(carPoolMember != null){
							carPoolMember.setEmployee(employeeDetails);
							tempList = dao.retrieveMembers(carPoolMember);
							System.out.println("Size of the list : "+tempList.size());
							
						}
						return tempList;
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
	//@Override
	/*public boolean resetPassword(String emailId) {
		
		return false;
	}*/

	/**
	 * This method is used to register a new user which takes the 
	 * employee bean as the input and saves the details into the database
	 * by invoking a method on the dao object.
	 * @return EmployeeForm
	 * @author Subbu/mohan
	 */
	@Override
	public List registerNewUser(EmployeeForm employee) {
		
		List<CarPoolMemberForm> tempList = new ArrayList<CarPoolMemberForm>();
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
		System.out.println("New Employee record inserted");
		
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
			this.createNewMember(carPoolMember);
			
		}
		else{
			/*
			 * New carpool is created and this user is assigned 
			 * to be the driver
			 */
			carPoolMember.setIsDriver(1);
			
			/*
			 *create a new car pool group in carpool table
			 */
			dao.createNewCarPoolGroup();
			
			/*
			 *retrieve the newly created car pool group id 
			 */
			CarPoolForm carPoolFormObj = dao.getLatestCarpoolGroup();
			
			/*
			 * assign the user to the new group creater
			 */
			carPoolMember.setCarpoolID(carPoolFormObj.getCarpoolID());
			this.createNewMember(carPoolMember);
			System.out.println("new user - driver created");
			
		}
		//return carPoolMember;
		
		tempList = dao.retrieveMembers(carPoolMember);
		System.out.println("Size of the list : "+tempList.size());
		return tempList;
	}
	
	public CarPoolMemberForm getCarPoolMemberDetails(){
		return getCarPoolMember();
	}
	
	public CarPoolForm getCarPoolGroupDetails() {
		if(getCarPoolMember() != null){
		carPoolGroup = dao.getCarPoolGroupDetails(getCarPoolMember().getCarpoolID());
		return carPoolGroup;
		}
		else 
			return null;
	}
	
	
	public void createNewMember(CarPoolMemberForm carPoolMember){
		dao.createNewMember(carPoolMember);
		
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
