package com.crs.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatis Connection Factory, which reads the configuration data from a XML file.
 * 
 * Reference - 
 * 
 * http://loianegroner.com (English)
 * 
 */

public class MyBatisConnectionFactory {

	private static SqlSessionFactory sqlSessionFactory;

	static {

		try {

			String resource = "SqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);

			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
		}

		catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}
		catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {

		return sqlSessionFactory;
	}

}
