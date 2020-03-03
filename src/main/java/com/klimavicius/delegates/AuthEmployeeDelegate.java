package com.klimavicius.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klimavicius.models.Auth;
import com.klimavicius.models.Employee;
import com.klimavicius.services.EmployeeService;

import org.mindrot.jbcrypt.BCrypt;

public class AuthEmployeeDelegate {


    public void authentication(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("I'm in authentication");
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        EmployeeService employeeService = new EmployeeService();

        try {
            InputStream inputStream = req.getInputStream();
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
        Auth authUser = objectMapper.readValue(body, Auth.class);
        String passPassword = authUser.getPassword();
        Employee currentEmployee = employeeService.getEmployeeByEmail(authUser.getEmail());
        if (currentEmployee == null){
            System.out.println("resource not found");
            resp.setStatus(404);
        } else {
            String userPassword = currentEmployee.getPassword();
            if (BCrypt.checkpw(passPassword, userPassword)) {
                System.out.println("Passwords matches!");
                try (PrintWriter pw = resp.getWriter()) {
                    pw.write(new ObjectMapper().writeValueAsString(currentEmployee));
                }
                resp.setStatus(200);
            } else {
                System.out.println("Passwords don't match!");
                resp.setStatus(401);
            }
        }

    }

}