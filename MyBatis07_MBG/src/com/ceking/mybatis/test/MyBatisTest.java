package com.ceking.mybatis.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.ceking.mybatis.dao.EmployeeMapper;
import com.ceking.mybatis.entities.Employee;
import com.ceking.mybatis.entities.EmployeeExample;
import com.ceking.mybatis.entities.EmployeeExample.Criteria;

public class MyBatisTest {

	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testMbg() throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("mbg.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
	
	@Test
	public void testSimple() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			Employee employee = mapper.selectByPrimaryKey(2);		
			System.out.println(employee.getEmail());
			
		} finally {
			sqlSession.close();
		}
	}
	@Test
	public void testMybatis3() throws Exception {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			
			/*Employee employee = mapper.selectByPrimaryKey(2);	
			System.out.println(employee.getEmail());	*/
			//1.查询所有
			//List<Employee> list = mapper.selectByExample(null);
			
			//2.按条件查询
			EmployeeExample example =new EmployeeExample();
			//创建Criteria 拼装查询条件
			Criteria criteria = example.createCriteria();
			criteria.andLastNameLike("%b%");
			criteria.andGenderEqualTo("1");
			//添加条件 or ：使用example.or(criteria2);拼接
			Criteria criteria2 = example.createCriteria();
			criteria2.andEmailLike("b");			
			example.or(criteria2);
			
			List<Employee> emps = mapper.selectByExample(example);
			for(Employee employee :emps){
				System.out.println(employee.getId());
			}
					
		} finally {
			sqlSession.close();
		}
	}
	
}
