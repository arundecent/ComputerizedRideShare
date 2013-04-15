package com.crs.service;

import java.util.ArrayList;
import java.util.List;

import com.crs.dao.CrsDAO;
import com.crs.model.CarPoolMemberForm;

public class Mainclass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CrsDAO dao = new CrsDAO();
		List<CarPoolMemberForm> list = new ArrayList<CarPoolMemberForm>();
		list = dao.retrieveAllFreeCarpoolGroups();
 	}

}
