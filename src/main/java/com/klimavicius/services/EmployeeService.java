package com.klimavicius.services;

import java.util.List;

import javax.servlet.http.HttpServlet;

import com.klimavicius.daos.EmployeeDaoConcrete;
import com.klimavicius.models.Employee;


public class EmployeeService extends HttpServlet {

    
    private EmployeeDaoConcrete employeeController = new EmployeeDaoConcrete();

    public List<Employee> getAllEmployees(){
       return employeeController.getEmployees();
    }

    public Employee getEmployeeByEmail(String s){
        return employeeController.getEmployeeByEmail(s);
    }

    public Employee getEmployeeById(int id){
        return employeeController.getEmployeeById(id);
    }

    public int createEmployee(Employee e){
        return employeeController.createEmployee(e);
    }

    public int updateEmployee(String value, Employee e){
        return employeeController.updateEmployee(value, e);
    }

    public int deleteEmployee(Employee e){
        return employeeController.deleteEmployee(e);
    }


}