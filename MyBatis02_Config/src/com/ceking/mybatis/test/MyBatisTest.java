package com.ceking.mybatis.test;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.Soundbank;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.ceking.mybatis.dao.EmoployeeDao;
import com.ceking.mybatis.dao.EmployeeMapperAnnotation;
import com.ceking.mybatis.entities.Employee;

public class MyBatisTest {
	
	@Test
	public void test() throws Exception {
		
		String resource = "mybatis-config.xml";
		//获取sqlSession实例，能执行已经映射的sql语句
		SqlSession openSession=null;
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			openSession = sqlSessionFactory.openSession();
			Object obj = openSession.selectOne("com.ceking.mybatis.dao.EmoployeeDao.getEmpById", 1);
			System.out.println(obj);
			
		}finally {
			//关闭资源
			openSession.close();
		}
	}
	
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
		}catch (Exception e) {
			e.printStackTrace();
			//System.out.println(e.getMessage());
		} finally {
			openSession.close();
		}
	}
	@Test
	public void test2() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();		
		SqlSession openSession = sqlSessionFactory.openSession();		
		try {
			EmployeeMapperAnnotation mapperAnnotation = openSession.getMapper(EmployeeMapperAnnotation.class);
			Employee employee = mapperAnnotation.getEmpById(1);
			System.out.println(employee);
		} finally {
			openSession.close();
		}		
	}
	
}
