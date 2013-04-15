package com.crs.action;

import java.util.ArrayList;
import com.crs.dao.CrsDAO;
import java.util.List;

import com.crs.model.CarPoolForm;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
import com.crs.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;

public class CarpoolMemberAction extends ActionSupport {
	
	CrsDAO dao;
	private CarPoolMemberForm carPoolMember = new CarPoolMemberForm();
	private CarPoolForm carPoolGroup = new CarPoolForm();
	private int carpoolGroupID;
	List<CarPoolMemberForm> memberList = new ArrayList<CarPoolMemberForm>();
	LoginService svc = new LoginService();
	private int employeeID;
	
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public List<CarPoolMemberForm> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<CarPoolMemberForm> memberList) {
		this.memberList = memberList;
	}
	
	public int getCarpoolGroupID() {
		return carpoolGroupID;
	}

	public void setCarpoolGroupID(int carpoolGroupID) {
		System.out.println("Setting value : "+carpoolGroupID);
		this.carpoolGroupID = carpoolGroupID;
	}

	public CarPoolMemberForm getCarPoolMember() {
		return carPoolMember;
	}

	public void setCarPoolMember(CarPoolMemberForm carPoolMember) {
		this.carPoolMember = carPoolMember;
	}

	public CarPoolForm getCarPoolGroup() {
		return carPoolGroup;
	}

	public void setCarPoolGroup(CarPoolForm carPoolGroup) {
		this.carPoolGroup = carPoolGroup;
	}

	public CrsDAO getDao() {
		return dao;
	}

	public void setDao(CrsDAO dao) {
		this.dao = dao;
	}

	public CarpoolMemberAction() {
		// TODO Auto-generated constructor stub
		this.dao = new CrsDAO();
	}
	
	public String checkOut(){
		System.out.println("Checking out Member Action :"+carpoolGroupID+"======"+getEmployeeID());
		dao.checkOut(carpoolGroupID,getEmployeeID());
		EmployeeForm employeeDetails = dao.getLoginRecordWithEmpID(getEmployeeID());
		System.out.println("Employee Name :"+employeeDetails.getFirstName());
		carPoolMember = dao.getMemberInfo(getEmployeeID());
		carPoolMember.setEmployee(employeeDetails);
		carPoolGroup.setCarpoolID(carpoolGroupID);
		carPoolGroup.setAtWork(0);
		setMemberList(dao.retrieveMembers(carPoolMember));
		return SUCCESS;
	}
	
	public String checkIn(){
		System.out.println("Checking In "+carpoolGroupID+"========"+getEmployeeID());
		dao.checkIn(carpoolGroupID);
		EmployeeForm employeeDetails = dao.getLoginRecordWithEmpID(getEmployeeID());
		carPoolMember = dao.getMemberInfo(getEmployeeID());
		carPoolMember.setEmployee(employeeDetails);
		carPoolGroup.setCarpoolID(carpoolGroupID);
		carPoolGroup.setAtWork(1);
		setMemberList(dao.retrieveMembers(carPoolMember));
		return SUCCESS;
	}
	
	public String optOutCarpool(){
		System.out.println("Opting out "+carpoolGroupID+"========"+getEmployeeID());
		dao.optOutCarpool(getEmployeeID());
		EmployeeForm employeeDetails = dao.getLoginRecordWithEmpID(getEmployeeID());
		carPoolMember.setEmployee(employeeDetails);
		return SUCCESS;
	}
	
	public String cancelPickupConfirm() {
			System.out.println("Cancelling car pool request");
			EmployeeForm employeeDetails = dao.getLoginRecordWithEmpID(getEmployeeID());
			carPoolMember = dao.getMemberInfo(getEmployeeID());
			carPoolMember.setEmployee(employeeDetails);
			carPoolMember.setEmployeeID(getEmployeeID());
			carPoolGroup.setCarpoolID(carpoolGroupID);
			dao.cancelCarpoolPickUp(carPoolMember);
			return SUCCESS;
	}
	
	public String confirmEmergency() {
		Boolean switchedCarpool = true;

		if(switchedCarpool)
			return SUCCESS;
		else
			return ERROR;
	}
	
	public String cancelDrivingConfirm() {
		Boolean canceled = true;
		
		if(canceled)
			return SUCCESS;
		else
			return ERROR;
	}
}