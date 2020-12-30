package com.ceking.mybatis.test;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.ceking.mybatis.dao.EmoployeeDao;
import com.ceking.mybatis.entities.Employee;

public class MyBatisTest {
		
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;		
	}
	/**
	 * 接口式编程
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();		
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmoployeeDao mapper = openSession.getMapper(EmoployeeDao.class);
			Employee employee = mapper.getEmpById(2);
			System.out.println(employee);
		} finally {
			openSession.close();
		}
	}
	/**
	 * 插件编写
	 * 1.编写Interceptor的实现类
	 * 2.使用@Intercepts注解完成插件签名
	 * 3.将写好的插件注册到全局配置文件中
	 */
	@Test
	public void testPlugin() {
		
	}
	
	
}