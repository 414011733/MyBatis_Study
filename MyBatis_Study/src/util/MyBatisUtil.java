package util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	public static SqlSessionFactory getSqlSessionFactory() {
		String resource = "conf.xml";
		
		InputStream inputStream = MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		return factory;
	}
	
	public static SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession();
	}
	
	public static SqlSession getSqlSession(boolean isAutoCommit) {
		return getSqlSessionFactory().openSession(isAutoCommit);
	}
}
