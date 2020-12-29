package com.ceking.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ceking.mybatis.entities.Employee;

public interface EmployeeMapperDynamicSQL {
	
	public List<Employee> getEmpByConditionIf(Employee employee);
	
	public List<Employee> getEmpByConditionTrim(Employee employee);
	
	public List<Employee> getEmpByConditionChoose(Employee employee);
	
	public void updateEmpDynamic(Employee employee);
	
	public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);
	
	public void addEmps(@Param("emps") List<Employee> emps);
}
