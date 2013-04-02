package com.crs.model;
import java.util.Date;

public class CarPoolMemberForm {
	EmployeeForm employee;
	int carpoolId;
	int isTemporary;
	int isPickUp;
	int isDriver;
	Date dateJoined;
	
	public EmployeeForm getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeForm employee) {
		this.employee = employee;
	}
	public int getCarpoolId() {
		return carpoolId;
	}
	public void setCarpoolId(int carpoolId) {
		this.carpoolId = carpoolId;
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
