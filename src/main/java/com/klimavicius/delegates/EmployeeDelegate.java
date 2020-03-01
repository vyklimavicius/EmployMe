package com.klimavicius.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("I'm in create employee");
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        EmployeeService employeeService = new EmployeeService();
        
        InputStream inputStream = req.getInputStream();

        try {
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
    } catch (IOException ex) {
        throw ex;
    } finally {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                throw ex;
            }
        }
    }

    body = stringBuilder.toString();
    Employee newEmployee = objectMapper.readValue(body, Employee.class);
    System.out.println(newEmployee);
    employeeService.createEmployee(newEmployee);
    resp.setStatus(201);
   }
    
}