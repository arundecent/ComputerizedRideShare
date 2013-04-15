package com.crs.service;

import java.util.ArrayList;
import java.util.List;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolMemberForm;




/**
 * This class will contain the schedule logic and also 
 * the functionality involving the grouping and 
 * listing of the carpool members
 * @author mkarun2
 *
 */
public class ScheduleService {

	private CrsDAO dao;
	
	public ScheduleService() {
		this.dao = new CrsDAO();
	}

	public CrsDAO getDao() {
		return dao;
	}

	public void setDao(CrsDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * This method is used to retrieve the list of car pools
	 * which have a free place in them
	 * @return
	 */
	public List<CarPoolMemberForm> getFreeCarpoolList(){
		
		/*
		 * The list of available car pools fetched from the database
		 */
		List<CarPoolMemberForm> carpoolList = new ArrayList<CarPoolMemberForm>();
		carpoolList = this.dao.retrieveAllFreeCarpoolGroups();		
		return carpoolList;
	}
		

}
