package com.crs.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.crs.dao.MyBatisConnectionFactory;
import com.crs.model.EmployeeForm;

public class CrsDAO {
	
	private SqlSessionFactory sqlSessionFactory; 
	
	public CrsDAO(){
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}
	
	public EmployeeForm getLoginRecord(EmployeeForm employee){
		int count;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			EmployeeForm employeeDetail = (EmployeeForm) session.selectOne("Employee.getRecord",employee);
			return employeeDetail;
		}finally {
			session.close();
		}
	}
	

}
