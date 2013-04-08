package com.crs.action;

import org.apache.commons.lang.StringUtils;
import com.crs.interfaces.LoginServiceInterface;
import com.crs.model.*;
import com.crs.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<EmployeeForm> {
	
	private EmployeeForm employee = new EmployeeForm();
	LoginServiceInterface loginService = new LoginService();
	
	
	public EmployeeForm getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeForm employee) {
		this.employee = employee;
	}
	
	

	public String login(){
		System.out.println("======In Login Action login========");
		
		EmployeeForm employeeDetails;
		employeeDetails = loginService.login(employee);
		if(employeeDetails != null)
			return SUCCESS;
		else
			return LOGIN;
	}
	
	public String registerNewUser(){
		System.out.println("======In Login Action register========");
		EmployeeForm employeeDetails;
		employeeDetails = loginService.registerNewUser(employee);
		if(employeeDetails != null)
			return SUCCESS;
		else
			return LOGIN;
	}
	
	public void validate(){
		if(StringUtils.isEmpty(employee.getEmailID()))
				addFieldError(employee.getEmailID(), "Email ID cannot be blank");
		if(StringUtils.isEmpty(employee.getPassword()))
				addFieldError(employee.getPassword(), "Password cannot be blank");
	}

	@Override
	public EmployeeForm getModel() {
		// TODO Auto-generated method stub
		return employee;
	}

}
