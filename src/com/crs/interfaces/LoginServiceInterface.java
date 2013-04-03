package com.crs.interfaces;

import com.crs.model.EmployeeForm;

public interface LoginServiceInterface {
	public EmployeeForm login(EmployeeForm employee);
	public EmployeeForm registerNewUser(EmployeeForm employee);
	public boolean resetPassword(String emailId);
}
