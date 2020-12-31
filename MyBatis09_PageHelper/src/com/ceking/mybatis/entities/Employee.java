package com.ceking.mybatis.entities;

public class Employee {

	private Integer id;
	private String lastName;
	private String email;
	private String gender;
	private Integer d_id;
	private EmpStatus empStatus=EmpStatus.LOGOUT;	
	
	public Employee(String lastName, String email, String gender, Integer d_id, EmpStatus empStatus) {
		super();
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.d_id = d_id;
		this.empStatus = empStatus;
	}

	public EmpStatus getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(EmpStatus empStatus) {
		this.empStatus = empStatus;
	}

	public Employee() {
		super();
	}
	
	public Employee(String lastName, String email, String gender, Integer d_id) {
		super();
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.d_id = d_id;
	}
	
	public Integer getD_id() {
		return d_id;
	}
	public void setD_id(Integer d_id) {
		this.d_id = d_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + "]";
	}
	
}
