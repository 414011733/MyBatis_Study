package test;

import domain.Order;
import mapping.UserMapperClass;
import util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDifferentName {
 
    private SqlSession session;
    
    @Before
    public void Before() {
    	this.session = MyBatisUtil.getSqlSession(true);
    }
    
    @After
    public void After() {
    	this.session.close();
    }
	
    @Test
    public void testGetOrderById(){
        String statement = "mapping.orderMapper.getOrderById";
        Order order = session.selectOne(statement,1);
        session.close();
        System.out.println(order);
    }
    
    @Test
    public void testGetOrderById2(){
        String statement = "mapping.orderMapper.selectOrder";
        Order order = session.selectOne(statement,1);
        session.close();
        System.out.println(order);
    }
    
    @Test
    public void testGetOrderById3(){
        String statement = "mapping.orderMapper.selectOrderResultMap";
        Order order = session.selectOne(statement,1);
        session.close();
        System.out.println(order);
    }
}