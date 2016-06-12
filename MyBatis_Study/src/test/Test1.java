package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import domain.User;
import mapping.UserMapperClass;
import util.MyBatisUtil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test1 {

    private SqlSession session;
    private UserMapperClass mapper;
    
    @Before
    public void Before() {
    	this.session = MyBatisUtil.getSqlSession(true);
    	this.mapper = session.getMapper(UserMapperClass.class);
    }
    
    @After
    public void After() {
    	this.session.close();
    }
    
    @Test
    public void testAdd() {
    	User user = new User();
        user.setAge(12);
        user.setName("add");
        int add = mapper.add(user);
        System.out.println(add);
    }
    
    @Test
    public void testUpdate() {
    	User user = new User();
    	user.setId(1);
    	user.setName("update");
    	int update = mapper.update(user);
    }
    
    @Test
    public void testDelete() {
    	mapper.delete(17);
    }
    
    @Test
    public void testSelest() {
    	User user = mapper.getById(1);
    	System.out.println(user);
    }
    
    @Test
    public void testGetAll() {
    	List<User> list = mapper.getAll();
    	for(User user : list) {
    		System.out.println(user);
    	}
    }
}