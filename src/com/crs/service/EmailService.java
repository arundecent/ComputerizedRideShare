package com.crs.service;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


/**
 * This class is the service which is used 
 * to send email to
 * @author Mohan
 *
 */
public class EmailService {
	
	/**
	 * Default constructor setting this user name, password and email id
	 */
	public EmailService(){
		this.strPassword = "cs442groupb";
		this.strUsername = "crpcs442";
		this.strEmailId = "crpcs442@gmail.com";
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
			email.setAuthenticator(new DefaultAuthenticator(this.getStrUsername(),this.getStrPassword()));
			
			/* accepting the SSL certificate */
			email.setSSLOnConnect(true);
			
			/*Email of CRP Email notification system*/
			email.setFrom(this.getStrEmailId());
			
			/*Email details*/
			email.setSubject(strSubject);
			email.setMsg(strMessage);
			email.addTo(strToAddress);
			
			/*send email*/
			email.send();
		}
		catch (EmailException e) {
			System.out.println("The email was not sent!!");
			e.printStackTrace();
		}
		
	}
	
	// user name of the CRP Email System
	private String strUsername;
	
	// password of the CRP Email System
	private String strPassword;
	
	// Email ID of the CRP System
	private String strEmailId;

	
}