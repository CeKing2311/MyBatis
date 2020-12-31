package com.ceking.mybatis.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceking.mybatis.dao.EmployeeMapper;
import com.ceking.mybatis.entities.Department;
import com.ceking.mybatis.entities.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	/**
	 * 可批量操作的sqlSession
	 */
	@Autowired
	private SqlSession sqlSession;

	public List<Employee> getEmps() {
		System.out.println("EmployeeService getEmps");
		return employeeMapper.getEmps();
	}

	// 批量添加
	public void addList() {
		try {
			long start = System.currentTimeMillis();
			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			for (int i = 0; i < 10000; i++) {
				mapper.addEmp(new Employee(UUID.randomUUID().toString().substring(0, 6), "batch@" + i + "qq.com", "0", 1));
			}
			long end = System.currentTimeMillis();
			Timestamp timestamp= new Timestamp((end-start));
			System.out.println("addList end:"+timestamp.getSeconds());			
			
		} finally {
		}
		
	}

}
