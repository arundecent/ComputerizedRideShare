package com.crs.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class CarpoolMemberAction extends ActionSupport {
	
	// http://localhost:8080/ComputerizedRideShare/myCarpool
	public List<String[]> getCarpoolMembers() {
		//Temporary testing values
		List<String[]> tmp = new ArrayList<String[]>();
		tmp.add(new String[] {"John Doe", "Yes", "111 Main St", "(773)123-1234"});
		tmp.add(new String[] {"Jane Doe", "No", "43 First St", "(773)987-3456"});
		tmp.add(new String[] {"Summer Fall", "No", "23 Halsted St", "(773)546-8766"});
		tmp.add(new String[] {"Who What", "No", "7688 Harrison St", "(773)234-6547"});
		return tmp;
	}
	
	public String cancelPickupConfirm() {
		Boolean canceled = true;
		
		if(canceled)
			return SUCCESS;
		else
			return ERROR;
	}
	
	public String cancelDrivingConfirm() {
		Boolean canceled = true;
		
		if(canceled)
			return SUCCESS;
		else
			return ERROR;
	}
}