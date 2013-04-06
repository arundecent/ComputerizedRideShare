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

public class LoginService implements LoginServiceInterface{
	
	CrsDAO dao = new CrsDAO();
	@Override
	public EmployeeForm login(EmployeeForm employee) {
		System.out.println("In Login Service");
		
		EmployeeForm employeeDetails = dao.getLoginRecord(employee);
		System.out.println("Result : " +employeeDetails.getFirstName());
		return employeeDetails;
	}

	@Override
	public boolean resetPassword(String emailId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EmployeeForm registerNewUser(EmployeeForm employee) {
		System.out.println("In Login Service Register New User");
		
		//hashed password should be set to the employee object before database save.
		employee.setPassword(this.generateMD5HashForPasswordWithSalt(employee.getPassword()));
		
		dao.insertEmployeeRecord(employee);
		EmployeeForm employeeDetails = null;
		return employeeDetails;
	}
	
	/**
	 * This method is used to generate the hashing of password without salt
	 * @param strPassword
	 * @return
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
	 * @return
	 */
	public String generateMD5HashForPasswordWithSalt(String strPassword){
		if(strPassword == null){
			return "null";
		}
		else {
			String strHashedPassword = null;			
			//@subbu - i am not able to understand how this mybatis is working 
			// and how to do database manipulations.. an introduction from u will be useful
			//this salt should come from the database. i have just hard coded for work purpose
			String strSalt = "#00jlasmdio2oj093-4923u8968912@$@4&#%^$*";
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
