package com.crs.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.crs.dao.MyBatisConnectionFactory;
import com.crs.model.CarPoolForm;
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
			session.selectOne("Employee.insertRecord",employee);
		}finally {
			session.close();
		}
	}

}
