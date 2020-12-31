package com.ceking.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.ceking.mybatis.dao.EmoployeeDao;
import com.ceking.mybatis.entities.EmpStatus;
import com.ceking.mybatis.entities.Employee;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class MyBatisTest {

	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	/**
	 * 测试Enum
	 * @throws Exception 
	 */
	
	public void testEnumUse() {
		EmpStatus login = EmpStatus.LOGIN;
		System.out.println("枚举的索引："+login.ordinal());
		System.out.println("枚举的名字："+login.name());
		System.out.println("枚举的状态码："+login.getCode());
		System.out.println("枚举的消息："+login.getMsg());
		
	}
	
	@Test
	public void  tetsEnum() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmoployeeDao mapper = openSession.getMapper(EmoployeeDao.class);
			Employee employee =new Employee("enum_test2", "enum_test2@qq.com", "1", 1);
			mapper.addEmp(employee);
			System.out.println("保存成功！");
			openSession.commit();
			System.out.println(employee.getId());
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			openSession.close();
		}
	}
	/**
	 * 测试批量执行
	 * @throws Exception
	 */
	@Test
	public void testBatch() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//ExecutorType.BATCH 执行批量操作
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		long start = System.currentTimeMillis();
		
		try {
			EmoployeeDao mapper = sqlSession.getMapper(EmoployeeDao.class);					
			//mapper.addEmp(new Employee("hhhhhh", "hhhhh@qq.com", "1", 1));
			
			//批量保存操作			
			for (int i = 0; i < 10000; i++) {
				mapper.addEmp(new Employee(UUID.randomUUID().toString().substring(0, 5), "batch"+i+"@qq.com","0", 1));
			}
			sqlSession.commit();
			long endTime = System.currentTimeMillis();
			System.out.println("批量执行时长："+(endTime-start));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * 接口式编程
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmoployeeDao mapper = openSession.getMapper(EmoployeeDao.class);
			// Employee employee = mapper.getEmpById(1);
			Page<Object> page = PageHelper.startPage(1, 10);
			List<Employee> list = mapper.getEmps();
			for (Employee employee2 : list) {
				System.out.println(employee2);
			}
			PageInfo<Object> pageInfo = new PageInfo<>();			
			System.out.println("当前页码："+page.getPageNum());
			System.out.println("总记录数："+page.getTotal());
			System.out.println("总页码："+page.getPages());
		} finally {
			openSession.close();
		}
	}

}
