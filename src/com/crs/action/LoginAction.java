package com.crs.action;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import com.crs.interfaces.LoginServiceInterface;
import com.crs.model.*;
import com.crs.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.Validation;

public class LoginAction extends ActionSupport implements ModelDriven<EmployeeForm> {

	private EmployeeForm employee = new EmployeeForm();
	private CarPoolMemberForm carPoolMember = new CarPoolMemberForm();
	LoginServiceInterface loginService = new LoginService();

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
		carPoolMember = loginService.login(employee);
		System.out.println("Details : "+carPoolMember.getCarpoolID()+" == "+carPoolMember.getEmployee().getFirstName());
		if (carPoolMember != null)
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
		carPoolMember = loginService.registerNewUser(employee);
		if (carPoolMember != null)
			return SUCCESS;
		else
			return ERROR;
	}

	@Override
	public EmployeeForm getModel() {
		// TODO Auto-generated method stub
		return employee;
	}

}
/*
 * ======= package com.crs.action;
 * 
 * import org.apache.commons.lang.StringUtils; import
 * com.crs.interfaces.LoginServiceInterface; import com.crs.model.EmployeeForm;
 * import com.crs.service.LoginService; import
 * com.opensymphony.xwork2.ActionSupport; import
 * com.opensymphony.xwork2.ModelDriven;
 * 
 * 
 * @SuppressWarnings("serial") public class LoginAction extends ActionSupport
 * implements ModelDriven<EmployeeForm> {
 * 
 * private EmployeeForm employee = new EmployeeForm(); LoginServiceInterface
 * loginService = new LoginService();
 * 
 * public EmployeeForm getEmployee() { return employee; }
 * 
 * public void setEmployee(EmployeeForm employee) { this.employee = employee; }
 * 
 * public String login(){
 * System.out.println("======In Login Action login========");
 * 
 * EmployeeForm employeeDetails; employeeDetails = loginService.login(employee);
 * if(employeeDetails != null) return SUCCESS; else return LOGIN; }
 * 
 * public String registerNewUser(){
 * System.out.println("======In Login Action register========"); EmployeeForm
 * employeeDetails; employeeDetails = loginService.registerNewUser(employee);
 * if(employeeDetails != null) return SUCCESS; else return LOGIN; }
 * 
 * public void validate(){ if(StringUtils.isEmpty(employee.getEmailID()))
 * addFieldError(employee.getEmailID(), "Email ID is a required field");
 * if(StringUtils.isEmpty(employee.getPassword()))
 * addFieldError(employee.getPassword(), "Password is a required field"); }
 * 
 * @Override public EmployeeForm getModel() { return employee; }
 * 
 * } >>>>>>> 0b4de134922b56ebf242e047140c14585971127e
 */