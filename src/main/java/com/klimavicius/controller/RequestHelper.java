package com.klimavicius.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.klimavicius.delegates.EmployeeDelegate;

public class RequestHelper {

    private EmployeeDelegate employeeDelegate = new EmployeeDelegate();

    public void getMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());

    }
}