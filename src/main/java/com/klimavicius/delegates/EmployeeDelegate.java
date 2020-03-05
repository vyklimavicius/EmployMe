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

import org.apache.log4j.Logger;

public class EmployeeDelegate {

    final static Logger logger = Logger.getLogger(EmployeeDelegate.class);
    private EmployeeService employeeService = new EmployeeService();

    public void getAllEmployees(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List <Employee> employees = employeeService.getAllEmployees();

        try (PrintWriter pw = resp.getWriter()) {
            pw.write(new ObjectMapper().writeValueAsString(employees));
        }
    }

    public void createEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("I'm in create employee!");
        // System.out.println("I'm in create employee");
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
                logger.error("Employee delegate exception", ex);
                throw ex;
            }
        }
    }

    body = stringBuilder.toString();
    Employee newEmployee = objectMapper.readValue(body, Employee.class);
    Employee currentEmployee = employeeService.getEmployeeByEmail(newEmployee.getEmail());
    logger.info("New employee " + newEmployee);
    // System.out.println(newEmployee);
    if (currentEmployee != null) {
        logger.info("Unprocessable Entity");
        // System.out.println("Unprocessable Entity");
        resp.setStatus(422);
    } else {
        // System.out.println(newEmployee);
        employeeService.createEmployee(newEmployee);
        resp.setStatus(201);
    }
   }

   public void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       ObjectMapper objectMapper = new ObjectMapper();
       logger.info("I'm in update employee");
       // System.out.println("I'm in update employee");
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
                   logger.error("Employee Delegate exception", ex);
                   resp.setStatus(422);
                   throw ex;
               }
           }
       }

       body = stringBuilder.toString();
       Employee newEmployee = objectMapper.readValue(body, Employee.class);
       logger.info("Employee to be updated" + newEmployee);
       //    System.out.println(newEmployee);
       employeeService.updateEmployee(newEmployee);
       resp.setStatus(200);
   }
    
}