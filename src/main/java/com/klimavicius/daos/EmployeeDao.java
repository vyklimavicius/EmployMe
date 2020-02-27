package com.klimavicius.daos;

import java.util.List;

import com.klimavicius.models.Employee;

public interface EmployeeDao {
	
	public List<Employee> getEmployees();
	public Employee getEmployeeByEmail(String s);
	public Employee getEmployeeById(int id);
	public int createEmployee(Employee e);
	public Employee updateEmployee(String value, Employee e);
	public int deleteEmployee(Employee e);

}
