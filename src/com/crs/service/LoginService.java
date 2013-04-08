package com.crs.service;

/*import statements for the MD5 hash function*/
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*Apache code library for Password Hashing*/
import org.apache.commons.codec.digest.DigestUtils;

import com.crs.dao.CrsDAO;
import com.crs.interfaces.LoginServiceInterface;
import com.crs.model.EmployeeForm;

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
		dao = new CrsDAO();
		strSalt = "#00jlasmdio2oj093-4923u8968912@$@4&#%^$*";
	}
	
	
	/**
	 * This method is used to employee details after 
	 * user enters his/her login credentials. 
	 * @return EmployeeForm (Empployee bean)
	 * @author Subbu
	 */
	@Override
	public EmployeeForm login(EmployeeForm employee) {
		System.out.println("In Login Service");
		
		EmployeeForm employeeDetails = dao.getLoginRecord(employee);
	//	System.out.println("Result : " +employeeDetails.getFirstName());
		return employeeDetails;
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
		System.out.println("In Login Service Register New User");
		employee.setSalt(this.getStrSalt());
		//hashed password should be set to the employee object before database save.
		employee.setPassword(this.generateMD5HashForPasswordWithSalt(employee.getPassword()));
		
		//saving the details to the database using dao
		dao.insertEmployeeRecord(employee);
		EmployeeForm employeeDetails = null;
		return employeeDetails;
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
			String strSaltPassword = strPassword + strSalt;
			
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

}
