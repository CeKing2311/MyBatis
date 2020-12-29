package com.ceking.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.ceking.mybatis.entities.Employee;

public interface EmployeeMapper {
	
	//多条记录封装为一个map
	@MapKey("id") //使用id作为key
	public Map<Integer, Employee> getEmpsMapByName(String lastName);
	
	//返回一个map，key为列名，值为对应的值
	public Map<String, Object> getEmpMapById(Integer id);	
	public List<Employee> getEmpsByName(@Param("lastName") String lastName);
	public Employee getEmpById(@Param("id") Integer id);	
	public Employee getEmpByInfo(@Param("id") Integer id,@Param("lastName") String lastName);	
	public Employee getEmpByMap(Map<String, Object> map);		
	public void addEmp(Employee employee);		
	public Boolean updateEmp(Employee employee);	
	public Integer deleteEmpById(Integer id);
}
