package com.crs.model;
import java.util.Date;


public class CarPoolForm {
	int carpoolId;
	Date dateCreated;
	int atWork;
	int noOfMembers;
	
	public int getNoOfMembers() {
		return noOfMembers;
	}
	public void setNoOfMembers(int noOfMembers) {
		this.noOfMembers = noOfMembers;
	}
	public int getCarpoolId() {
		return carpoolId;
	}
	public void setCarpoolId(int carpoolId) {
		this.carpoolId = carpoolId;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public int getAtWork() {
		return atWork;
	}
	public void setAtWork(int atWork) {
		this.atWork = atWork;
	}
}
