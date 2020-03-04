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
import com.klimavicius.models.Manager;
import com.klimavicius.services.ManagerService;

import org.apache.log4j.Logger;

public class ManagerDelegate{


    final static Logger logger = Logger.getLogger(ManagerDelegate.class);
    private ManagerService managerService = new ManagerService();

    public void getAllManagers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Manager> managers = managerService.getAllManagers();

        try (PrintWriter pw = resp.getWriter()) {
            pw.write(new ObjectMapper().writeValueAsString(managers));
        }
    }

    public void createManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("I'm in create manager");
        // System.out.println("I'm in create manager");
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        ManagerService managerService = new ManagerService();

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
                    logger.error("Manager Delegate exception", ex);
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        Manager newManager = objectMapper.readValue(body, Manager.class);
        logger.info("Manager to be created " + newManager);
        // System.out.println(newManager);
        managerService.createManager(newManager);
        resp.setStatus(201);
    }
}