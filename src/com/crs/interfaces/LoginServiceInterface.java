package com.crs.interfaces;

import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;

public interface LoginServiceInterface {
	public CarPoolMemberForm login(EmployeeForm employee);
	public CarPoolMemberForm registerNewUser(EmployeeForm employee);
	public boolean resetPassword(String emailId);
}
