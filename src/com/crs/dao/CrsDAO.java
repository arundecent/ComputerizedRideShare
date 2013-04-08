package com.crs.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.crs.dao.MyBatisConnectionFactory;
import com.crs.model.CarPoolForm;
import com.crs.model.CarPoolMemberForm;
import com.crs.model.EmployeeForm;

public class CrsDAO {
	
	private SqlSessionFactory sqlSessionFactory; 
	
	public CrsDAO(){
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}
	
	public EmployeeForm getLoginRecord(EmployeeForm employee){
		SqlSession session = sqlSessionFactory.openSession();
		try{
			EmployeeForm employeeDetail = (EmployeeForm) session.selectOne("Employee.getRecord",employee);
			return employeeDetail;
		}finally {
			session.close();
		}
	}
	
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
