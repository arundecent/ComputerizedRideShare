package com.crs.interfaces;

import java.util.List;

import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;

public interface LoginServiceInterface {
	public List login(EmployeeForm employee);
	public List registerNewUser(EmployeeForm employee);
	//public boolean resetPassword(String emailId);
}
