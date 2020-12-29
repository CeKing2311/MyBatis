package com.ceking.mybatis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ceking.mybatis.entities.Employee;
import com.ceking.mybatis.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/getemps")
	public String getEmps(Map<String, Object> map) {
		List<Employee> emps = employeeService.getEmps();
		map.put("emps", emps);
		return "list";
	}
}