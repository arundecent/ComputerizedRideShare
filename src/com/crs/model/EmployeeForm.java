package com.crs.model;

import java.util.Date;

public class EmployeeForm {
	private int employeeID;
	private String emailID;
	private String password;
	private String salt;
	private String firstName;
	private String lastName;
	private String securityQn;
	private String securityAns;
	private String phoneNo;
	private String notifyTypeStr;
	private Integer notifyType;
	private String address;
	private Date dateJoined;
	private int points;
	
	public String getNotifyTypeStr() {
		return notifyTypeStr;
	}
	public void setNotifyTypeStr(String notifyTypeStr) {
		this.notifyTypeStr = notifyTypeStr;
	}
	
	public String getSecurityAns() {
		return securityAns;
	}
	public void setSecurityAns(String securityAns) {
		this.securityAns = securityAns;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSecurityQn() {
		return securityQn;
	}
	public void setSecurityQn(String securityQn) {
		this.securityQn = securityQn;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Integer getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(Integer notifyType) {
		if(notifyType != null){
			this.notifyType = notifyType;
		}else{
			this.notifyType = Integer.parseInt("0");
		}
		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDateJoined() {
		return dateJoined;
	}
	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
