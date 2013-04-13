package com.crs.action;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class EmployeeAction extends ActionSupport {


	public String execute() {
		return SUCCESS;
	}
	
	public void validate(){
		System.out.println("hi");
		
	
	}

}