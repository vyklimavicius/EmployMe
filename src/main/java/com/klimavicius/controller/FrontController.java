package com.klimavicius.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

@WebServlet("/")
public class FrontController extends DefaultServlet {

    private RequestHelper requestHelper = new RequestHelper();

    
    public FrontController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("GET/" + path);
        if (path.startsWith("/static/")) {
            super.doGet(request, response);
        } else {
            // route GET request to appropriate delegate
            requestHelper.getMethod(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("POST/" + path);  
        
        requestHelper.getPost(request, response);
        // super.doPost(request, response);
    }

    

}