package com.crs.action;

import java.util.*;

import org.apache.commons.lang.StringUtils;

import com.crs.dao.CrsDAO;
import com.crs.interfaces.LoginServiceInterface;
import com.crs.model.*;
import com.crs.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.Validation;

public class LoginAction extends ActionSupport implements ModelDriven<EmployeeForm> {

	private EmployeeForm employee = new EmployeeForm();
	private CarPoolMemberForm carPoolMember = new CarPoolMemberForm();
	private CarPoolForm carPoolGroup = new CarPoolForm();
	LoginServiceInterface loginService = new LoginService();
	List<CarPoolMemberForm> memberList = new ArrayList<CarPoolMemberForm>();
	CrsDAO dao;
	
	public LoginAction() {
		// TODO Auto-generated constructor stub
		this.dao = new CrsDAO();
	}
	
	public CrsDAO getDao() {
		return dao;
	}

	public void setDao(CrsDAO dao) {
		this.dao = dao;
	}

	public CarPoolForm getCarPoolGroup() {
		return carPoolGroup;
	}

	public void setCarPoolGroup(CarPoolForm carPoolGroup) {
		this.carPoolGroup = carPoolGroup;
	}
	
	public List<CarPoolMemberForm> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<CarPoolMemberForm> memberList) {
		this.memberList = memberList;
	}

	public EmployeeForm getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeForm employee) {
		this.employee = employee;
	}
	
	public CarPoolMemberForm getCarPoolMember() {
		return carPoolMember;
	}

	public void setCarPoolMember(CarPoolMemberForm carPoolMember) {
		this.carPoolMember = carPoolMember;
	}

	public String login() {
		System.out.println("======In Login Action login========");

		//EmployeeForm employeeDetails;
		memberList = loginService.login(employee);
		setCarPoolMember(loginService.getCarPoolMemberDetails());
		setCarPoolGroup(loginService.getCarPoolGroupDetails());
		//System.out.println("Member Details ===== "+getCarPoolMember().getIsDriver());
		//System.out.println("Group Details ===== "+getCarPoolGroup().getAtWork());
		if (memberList.size() != 0)
			return SUCCESS;
		else
			return LOGIN;
	}

	/**
	 * This method is called when the user clicks on the 
	 * submit to register into the car pool system.
	 * Call to this method is configured in struts.xml
	 * @return
	 */
	public String registerNewUser() {
		System.out.println("======In Login Action register========");
	
		employee.setDateJoined(new Date());
		employee.setPoints(10);
		if (employee.getNotifyTypeStr().equals("Email"))
			employee.setNotifyType(0);
		else
			employee.setNotifyType(1);
		memberList = loginService.registerNewUser(employee);
		if (memberList.size() != 0)
			return SUCCESS;
		else
			return ERROR;
	}
	
	public String checkOut(){
		System.out.println("Checking out "+carPoolGroup.getCarpoolID());
		dao.checkOut(carPoolGroup.getCarpoolID(), employee.getEmployeeID());
		return SUCCESS;
	}
	
	public String checkIn(){
		System.out.println("Checking In "+carPoolGroup.getCarpoolID());
		dao.checkIn(carPoolGroup.getCarpoolID());
		return SUCCESS;
	}

	@Override
	public EmployeeForm getModel() {
		// TODO Auto-generated method stub
		return employee;
	}

	
	public String modifyEmployeeDetails(){
		
		System.out.println("in modifyemployeedetails in login action" + employee.getEmployeeID());
		LoginService ls = new LoginService();
		ls.saveDetails(employee);		
		return SUCCESS;
	}

}
