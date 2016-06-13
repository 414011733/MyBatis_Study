package test;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.html.HTMLIsIndexElement;

import com.mysql.jdbc.Util;

import domain.Classes;
import util.MyBatisUtil;

public class TestOneToOne {
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
	public void testGetClass() {
		String statement = "mapping.classMapper.getClass";
		Classes classes = session.selectOne(statement, 1);
		System.out.println(classes);
	}
	
	@Test
	public void testGetClass2() {
		String statement = "mapping.classMapper.getClass2";
		Classes classes = session.selectOne(statement, 2);
		System.out.println(classes);
	}
}
