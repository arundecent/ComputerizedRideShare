package com.crs.action;

import java.util.Date;

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

	public String login() {
		System.out.println("======In Login Action login========");

		EmployeeForm employeeDetails;
		employeeDetails = loginService.login(employee);
		if (employeeDetails != null)
			return SUCCESS;
		else
			return LOGIN;
	}

	public String registerNewUser() {
		System.out.println("======In Login Action register========");
		validate();
		employee.setDateJoined(new Date());
		employee.setPoints(10);
		if (employee.getNotifyTypeStr().equals("Email"))
			employee.setNotifyType(0);
		else
			employee.setNotifyType(1);
		EmployeeForm employeeDetails;
		employeeDetails = loginService.registerNewUser(employee);
		if (employeeDetails != null)
			return SUCCESS;
		else
			return LOGIN;
	}

	public void validate() {

		if (StringUtils.isEmpty(employee.getEmailID())) {
			addFieldError("emailID", "Username (Email ID) is required");
		} 
		if (StringUtils.isEmpty(employee.getPassword())) {
			addFieldError("password", "Password is required.");
		}
		
		if(StringUtils.isEmpty(employee.getFirstName())){
			addFieldError("firstName", "First Name is required.");
		}
		if(StringUtils.isEmpty(employee.getLastName())){
			addFieldError("lastName", "Last Name is required.");
		}
		if(StringUtils.isEmpty(employee.getSecurityQn())){
			addFieldError("securityQn", "Security Question is required.");
		}
		if(StringUtils.isEmpty(employee.getSecurityAns())){
			addFieldError("securityAns", "Security Answer is required.");
		}
		if(StringUtils.isEmpty(employee.getPhoneNo())){
			addFieldError("phoneNo", "Phone Number is required.");
		}
		if(StringUtils.isEmpty(employee.getNotifyTypeStr())){
			addFieldError("notifyType", "Notify Type is required.");
		}
		if(StringUtils.isEmpty(employee.getAddress())){
			addFieldError("address", "Address is required.");
		}
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