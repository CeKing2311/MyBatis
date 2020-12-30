package com.ceking.mybatis.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceking.mybatis.dao.EmployeeMapper;
import com.ceking.mybatis.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private SqlSession sqlSession;
	
	public List<Employee> getEmps() {
		System.out.println("EmployeeService getEmps");
		return employeeMapper.getEmps();
	}
	//批量添加
	public void addList(){
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		
	}
	
}
