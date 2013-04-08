package com.crs.service;

import java.util.Date;

import com.crs.dao.CrsDAO;
import com.crs.interfaces.LoginServiceInterface;
import com.crs.model.CarPoolForm;
import com.crs.model.CarPoolMemberForm;
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
		dao.insertEmployeeRecord(employee);
		CarPoolForm carPoolForm = dao.getCarPoolGroup();
		CarPoolMemberForm carPoolMember = new CarPoolMemberForm();
		carPoolMember.setEmployee(employee);
		carPoolMember.setDateJoined(new Date());
		carPoolMember.setIsDriver(0);
		carPoolMember.setIsPickUp(1);
		carPoolMember.setIsTemporary(0);
		if(carPoolForm != null){
			carPoolMember.setCarpoolId(carPoolForm.getCarpoolId());
			createNewMember(carPoolMember);
		}
		else{
			carPoolForm = dao.createNewCarPoolGroup();
			carPoolMember.setCarpoolId(carPoolForm.getCarpoolId());
			createNewMember(carPoolMember);
		}
		System.out.println("Car Pool Group Details : "+carPoolForm.getCarpoolId());
		EmployeeForm employeeDetails = null;
		return employeeDetails;
	}
	
	public void createNewMember(CarPoolMemberForm carPoolMember){
		System.out.println("Creating new member");
		dao.createNewMember(carPoolMember);
	}
}
