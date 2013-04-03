package com.crs.service;

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
		dao.insertEmployeeRecord(employee);
		EmployeeForm employeeDetails = null;
		return employeeDetails;
	}

}
