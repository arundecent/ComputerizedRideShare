package crp;

import static org.junit.Assert.assertTrue;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.crs.dao.MyBatisConnectionFactory;
/**
 * This class contains all the test cases to check the
 * methods in the MyBatisConnectionFactoryTest.java
 * @author Mohan
 *
 */

public class MyBatisConnectionFactoryTest {

	/**
	 * This test is to ensure that there is 
	 * an active database connection
	 */
	@Test
	public void testGetSqlSessionFactory(){
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
			boolean test = (sqlSessionFactory == null);
			if (test) {
				assertTrue("No database connection", false);
			} else {
				assertTrue("Active connection", true);
			}
		} catch (Exception e) {
			assertTrue("No database connection", false);
		}
	}
}
