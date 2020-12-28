package com.ceking.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import com.ceking.mybatis.entities.Employee;

public interface EmployeeMapperAnnotation {
	@Select("select id,last_name lastName,email,gender from t_employee where id = #{id}")
	public Employee getEmpById(Integer id);
}
