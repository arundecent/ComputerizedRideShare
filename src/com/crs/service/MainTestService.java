package com.crs.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolMemberForm;


public class MainTestService implements Job{
	private CrsDAO dao;
	
	public CrsDAO getDao() {
		return dao;
	}

	public void setDao(CrsDAO dao) {
		this.dao = dao;
	}

	public MainTestService() {
		// TODO Auto-generated constructor stub
		this.dao = new CrsDAO();
	}	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("Driver change Service : " + jobKey + " executing at " + new Date());
		changeDriverEveryMonday();
		
	}
	
	public void changeDriverEveryMonday(){
		//This local variable is used to store the member details of a particular car pool
				ArrayList<CarPoolMemberForm> carpoolDriverList = new ArrayList<CarPoolMemberForm>();		
				CarPoolMemberForm nextDriver = new CarPoolMemberForm();
				//This local variable is used to store the passenger details of the members 
				//belonging to the same car pool as a particular driver
				List<CarPoolMemberForm> carpoolMembersList = new ArrayList<CarPoolMemberForm>();
				
				//Iterate over the driver List
				Iterator<CarPoolMemberForm> itDriver = null;
				
				//Iterator for the passenger list
				Iterator<CarPoolMemberForm> itPassengers = null;
				
				//drivers in each car pool group retrieved from the database
				carpoolDriverList = dao.retrieveDrivers();
				if((!carpoolDriverList.isEmpty()) && (carpoolDriverList != null)){
					itDriver = carpoolDriverList.iterator();
					while(itDriver.hasNext()){
						
						//retrieve the passenger list belonging to the group in which the 
						//particular driver belongs using the car pool id
						int currentDriver = itDriver.next().getEmployeeID();
						nextDriver = dao.getNextDriver(currentDriver, 0);
						dao.updateCurrentDriver(currentDriver);
						if(nextDriver != null){ //All the members have driven . Go back to the first one.
							dao.updateNextDriver(nextDriver.getEmployeeID());
						}
						else{
							nextDriver = dao.getNextDriver(currentDriver, 1); 
							dao.updateNextDriver(nextDriver.getEmployeeID());
						}
					}
					
					System.out.println("Assigned next drivers for all groups");
				}
	}

	/**
	 * @param args
	 */

}
