package test;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Classes;
import util.MyBatisUtil;

public class TestOneToMany {
	private SqlSession session;
	
	@Before
	public void before() {
		this.session = MyBatisUtil.getSqlSession(true);
	}
	
	@After
	public void after() {
		this.session.close();
	}
	
	@Test
	public void testGetClass3() {
		String statement = "mapping.classMapper.getClass3";
		Classes classes = session.selectOne(statement, 1);
		System.out.println(classes);
	}
	
	@Test
	public void testGetClass4() {
		String statement = "mapping.classMapper.getClass4";
		Classes classes = session.selectOne(statement, 2);
		System.out.println(classes);
	}
}
