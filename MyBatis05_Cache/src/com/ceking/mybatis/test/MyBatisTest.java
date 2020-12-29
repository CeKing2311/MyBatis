package com.ceking.mybatis.test;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.MalformedInputException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.ceking.mybatis.dao.DepartmentMapper;
import com.ceking.mybatis.dao.EmployeeMapper;
import com.ceking.mybatis.dao.EmployeeMapperDynamicSQL;
import com.ceking.mybatis.dao.EmployeeMapperPlus;
import com.ceking.mybatis.entities.Department;
import com.ceking.mybatis.entities.Employee;

public class MyBatisTest {

	/**
	 * 测试动态sql
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDynamicSql() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//开启自动提交
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
			Employee employee = new Employee(12, "Jack", "Jack@qq.com", "1");
			// 测试if 和where
			// List<Employee> list = mapper.getEmpByConditionIf(employee);
			// 测试trim
			//List<Employee> list = mapper.getEmpByConditionTrim(employee);
			//测试Choose
			/*List<Employee> list = mapper.getEmpByConditionChoose(employee);			
			for (Employee emp : list) {				
				System.out.println(emp);			
			}*/		
			//测试update操作
			//mapper.updateEmpDynamic(employee);
			//遍历查询条件
			/*List<Employee> list = mapper.getEmpsByConditionForeach(Arrays.asList(2,3,4));
			for (Employee emp : list) {				
				System.out.println(emp);			
			}*/			
			//批量添加
			List<Employee> list =new ArrayList<>();
			for (int i = 0; i < 1000; i++) {
				Employee emp =new Employee(null,"batch3emp"+i, "batch3emp"+i+"@qq.com","0",new Department(4, ""));
				list.add(emp);
			}
			System.out.println(System.currentTimeMillis());
			mapper.addEmps(list);			
			//openSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			openSession.close();
		}
	}

	@Test
	public void test6() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
			Department department = mapper.getDeptByIdStep(1);
			System.out.println(department);
			System.out.println(department.getEmps());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			openSession.close();
		}
	}

	@Test
	public void test5() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			Employee employee = mapper.getEmpByIdStep(3);
			System.out.println(employee);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			openSession.close();
		}
	}

	@Test
	public void test4() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
			Employee employee = mapper.getEmpById(2);
			System.out.println(employee);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			openSession.close();
		}
	}

	@Test
	public void test() throws Exception {
		String resource = "mybatis-config.xml";
		// 获取sqlSession实例，能执行已经映射的sql语句
		SqlSession openSession = null;
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			openSession = sqlSessionFactory.openSession();
			Object obj = openSession.selectOne("com.ceking.mybatis.EmployeeMapper.getEmpById", 1);
			System.out.println(obj);

		} finally {
			// 关闭资源
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
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		System.out.println(openSession);
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmpById(1);
			System.out.println(employee);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			openSession.close();
		}
	}

	/**
	 * 测试数据
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddEmp() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 1.获取到的SqlSession 不会自动提交数据
		// SqlSession openSession =
		// sqlSessionFactory.openSession(true);设置为true即可自动提交
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = new Employee(2, "Jim", "Jim@qq.com", "0");
			// 添加员工
			mapper.addEmp(employee);
			System.out.println(employee.getId());
			// 测试修改
			// mapper.updateEmp(employee);
			// 测试删除
			// Integer deleteEmpById = mapper.deleteEmpById(1);
			// System.out.println(deleteEmpById);

			// 2.手动提交数据
			openSession.commit();
		} finally {
			openSession.close();
		}
	}

	@Test
	public void test3() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//自动提交
		SqlSession openSession = sqlSessionFactory.openSession(true);
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			// Employee employee = mapper.getEmpByInfo(2, "jim");

			// Map<String, Object> map = new HashMap<String, Object>();
			// map.put("id", 2);
			// map.put("lastName", "jim");
			// map.put("tabName", "t_employee");
			// Employee employee = mapper.getEmpByMap(map);
			// 返回集合
			// List<Employee> list = mapper.getEmpsByName("%mar%");
			// 返回Map
			// Map<String, Object> map = mapper.getEmpMapById(2);

			Map<Integer, Employee> maps = mapper.getEmpsMapByName("%mar%");
			System.out.println(maps);

		} finally {
			openSession.close();
		}
	}

}