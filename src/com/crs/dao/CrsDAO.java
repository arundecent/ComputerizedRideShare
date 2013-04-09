package com.crs.dao;

/**
 * Ibatis persistence framework imports
 */
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import com.crs.dao.MyBatisConnectionFactory;
import com.crs.model.CarPoolForm;
import com.crs.model.CarPoolMemberForm;

/*
 * Employee Bean import
 */

import com.crs.model.EmployeeForm;

/**
 * This class is the Data Access Object class
 * for which will be used to make all the direct database 
 * transactions using ibatis/mybatis
 * @author Subbu
 *
 */
public class CrsDAO {
	
	//The database session
	private SqlSessionFactory sqlSessionFactory; 
	
	public CrsDAO(){
		/*
		 * making a connection instance with the database using ibatis/mybatis
		 */		
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}
	
	/**
	 * This method is used to obtain a employee record using his/her
	 * EmployeeID.
	 * @param employee
	 * @return EmployeeForm
	 */
	public EmployeeForm getLoginRecord(EmployeeForm employee){
		//opens the database connection instance
		SqlSession session = sqlSessionFactory.openSession();
		try{
			EmployeeForm employeeDetail = (EmployeeForm) session.selectOne("Employee.getRecord",employee);
			return employeeDetail;
		}finally {
			session.close();
		}
	}
	
	/**
	 * This method is used to insert a record into the 
	 * Employee table as a result of new user registration
	 * @param employee
	 */
	public void insertEmployeeRecord(EmployeeForm employee){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.insert("Employee.insertRecord",employee);
		}finally {
			session.close();
		}
	}
	
	public CarPoolForm getCarPoolGroup(){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			CarPoolForm carPoolGroup = session.selectOne("Employee.getCarPoolGroup");
			return carPoolGroup;
		}finally {
			session.close();
		}
		 
	}
	
	public void createNewMember(CarPoolMemberForm carPoolMember){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			 session.insert("Employee.createNewMember",carPoolMember);
		}finally {
			session.close();
		}
	}
	
	public CarPoolForm createNewCarPoolGroup(){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			CarPoolForm carPoolGroup = session.selectOne("Employee.getCarPoolGroup");
			return carPoolGroup;
		}finally {
			session.close();
		}
	}

}
