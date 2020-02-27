package com.klimavicius.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klimavicius.models.Employee;
import com.klimavicius.services.EmployeeService;

public class EmployeeDelegate {


    private EmployeeService employeeService = new EmployeeService();

    public void getAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List <Employee> employees = employeeService.getAllEmployees();

        try (PrintWriter pw = resp.getWriter()) {
            pw.write(new ObjectMapper().writeValueAsString(employees));
        }
    }
}