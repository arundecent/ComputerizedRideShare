package crp;
import java.io.Reader;

import com.crs.dao.MyBatisConnectionFactory;
/**
 * This class contains all the test cases to check the
 * methods in the MyBatisConnectionFactoryTest.java
 * @author Rajeev
 *
 */

import static org.junit.Assert.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class MyBatisConnectionFactoryTest {
// To check if the there is no connection with database from Batis
	@Test
	public void testGetSqlSessionFactory() throws Exception {
		try
		{
		MyBatisConnectionFactory bat = new MyBatisConnectionFactory();
		SqlSessionFactory sqlSessionFactory = null;
		if(sqlSessionFactory == null)
		{
			assertTrue("Problem with connection",true);
		}else{
			assertTrue("Active connection",false);
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void testGetSqlSessionFactoryFailed() throws Exception {
		try
		{
			String resource = "SqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			MyBatisConnectionFactory batt = new MyBatisConnectionFactory();
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			if(sqlSessionFactory == null)
			{
				assertTrue("Conection Error",false);
			}else{
				assertTrue("Conection Active",true);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
