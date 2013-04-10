package com.crs.model;
import java.util.Date;
import java.util.List;

public class CarPoolMemberForm {
	private EmployeeForm employee;
	private List<EmployeeForm> employeeList;
	
	private int carpoolID;
	private int isTemporary;
	private int isPickUp;
	private int isDriver;
	private Date dateJoined;
	private int employeeID;
	
	
	public List<EmployeeForm> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<EmployeeForm> employeeList) {
		this.employeeList = employeeList;
	}
	
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	public EmployeeForm getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeForm employee) {
		this.employee = employee;
	}
	
	public int getCarpoolID() {
		return carpoolID;
	}
	public void setCarpoolID(int carpoolID) {
		this.carpoolID = carpoolID;
	}
	public int getIsTemporary() {
		return isTemporary;
	}
	public void setIsTemporary(int isTemporary) {
		this.isTemporary = isTemporary;
	}
	public int getIsPickUp() {
		return isPickUp;
	}
	public void setIsPickUp(int isPickUp) {
		this.isPickUp = isPickUp;
	}
	public int getIsDriver() {
		return isDriver;
	}
	public void setIsDriver(int isDriver) {
		this.isDriver = isDriver;
	}
	public Date getDateJoined() {
		return dateJoined;
	}
	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

}
