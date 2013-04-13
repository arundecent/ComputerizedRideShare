package com.crs.action;

import org.apache.commons.lang.StringUtils;
import com.crs.model.*;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class EmployeeAction extends ActionSupport implements ModelDriven<EmployeeForm> {
	private EmployeeForm employee = new EmployeeForm();

	public String execute() {

		return SUCCESS;
	}
	
	public void validate(){
		System.out.println("hi");
		
	
	}

	@Override
	public EmployeeForm getModel() {
		// TODO Auto-generated method stub
		return employee;
	}

}