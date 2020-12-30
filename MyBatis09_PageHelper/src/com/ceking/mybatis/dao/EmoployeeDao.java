package com.ceking.mybatis.dao;

import java.util.List;

import com.ceking.mybatis.entities.Employee;

public interface EmoployeeDao {
	
	public Employee getEmpById(Integer id);
	
	public List<Employee> getEmps();
	
	public void addEmp(Employee employee);
}
