package com.ceking.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceking.mybatis.dao.EmployeeMapper;
import com.ceking.mybatis.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Employee> getEmps() {
		return employeeMapper.getEmps();
	}
}
