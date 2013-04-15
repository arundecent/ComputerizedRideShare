package com.crs.action;

import java.util.ArrayList;
import java.util.List;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolForm;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;
import com.crs.service.LoginService;
import com.crs.service.ScheduleService;
import com.opensymphony.xwork2.ActionSupport;

public class CarpoolMemberAction extends ActionSupport {
	
	CrsDAO dao;
	private CarPoolMemberForm carPoolMember = new CarPoolMemberForm();
	private CarPoolForm carPoolGroup = new CarPoolForm();
	private int carpoolGroupID;
	private List<CarPoolMemberForm> availableCarpoolList; 
	List<CarPoolMemberForm> memberList = new ArrayList<CarPoolMemberForm>();
	LoginService svc = new LoginService();
	private ScheduleService schServiceObj;
	
	
	
	public ScheduleService getSchServiceObj() {
		return schServiceObj;
	}

	public void setSchServiceObj(ScheduleService schServiceObj) {
		this.schServiceObj = schServiceObj;
	}

	public List<CarPoolMemberForm> getAvailableCarpoolList() {
		return availableCarpoolList;
	}

	public void setAvailableCarpoolList(List<CarPoolMemberForm> availableCarpoolList) {
		this.availableCarpoolList = availableCarpoolList;
	}

	public LoginService getSvc() {
		return svc;
	}

	public void setSvc(LoginService svc) {
		this.svc = svc;
	}
	
	
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

	/**
	 * Default Constructor for this class
	 */
	public CarpoolMemberAction() {
		// TODO Auto-generated constructor stub
		this.dao = new CrsDAO();
		this.availableCarpoolList = new ArrayList<CarPoolMemberForm>();
		this.schServiceObj = new ScheduleService();
	}
	
	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
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
	
	/**
	 * This method is used when the user clicks opt out of car pool
	 * meaning, the user wants to leave a particular car pool to which he/she
	 * belongs. The record from the car pool member table is deleted
	 * and the redirected to another page where the
	 * list of available car pools will be displayed
	 * @return
	 */
	public String optOutCarpool(){
		System.out.println("Opting out "+carpoolGroupID+"========"+getEmployeeID());
		
		List<CarPoolMemberForm> tempCarpoolList = new ArrayList<CarPoolMemberForm>();
		
		/*
		 *call to remove the employee record
		 */
		dao.optOutCarpool(getEmployeeID());
		
		/*
		 * retrieve the employee details into the value stack
		 */
		EmployeeForm employeeDetails = dao.getLoginRecordWithEmpID(getEmployeeID());
		carPoolMember.setEmployee(employeeDetails);
		
		/*
		 * retrieve the list of the car pool with free space available
		 */
		if(this.schServiceObj.getFreeCarpoolList().size() != 0){
			this.setAvailableCarpoolList(this.schServiceObj.getFreeCarpoolList());
		}else{
			this.setAvailableCarpoolList(tempCarpoolList);
		}
		
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

	public String optOutCrp(){
		dao.optOutCrp(getEmployeeID());
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
		System.out.println("Cancelling car pool drive");
		EmployeeForm employeeDetails = dao.getLoginRecordWithEmpID(getEmployeeID());
		carPoolMember = dao.getMemberInfo(getEmployeeID());
		carPoolMember.setEmployee(employeeDetails);
		carPoolMember.setEmployeeID(getEmployeeID());
		carPoolGroup.setCarpoolID(carpoolGroupID);
		if((carPoolMember.getEmployee().getPoints()-3) <= 0)
			return ERROR;
		else{
			dao.cancelCarpoolDrive(carPoolMember);
			CarPoolMemberForm nextDriver = dao.getNextDriver(getEmployeeID(), 0);
			if(nextDriver != null){
				dao.updateTemporaryDriver(nextDriver.getEmployeeID());
			}
			else{
				nextDriver = dao.getNextDriver(getEmployeeID(), 1); 
				dao.updateTemporaryDriver(nextDriver.getEmployeeID());
			}
			String message = "Car Pool Driver has cancelled his drive for the day";
			//notifyUsersByEmail(message, carPoolMember);
			return SUCCESS;
		}
		}
}