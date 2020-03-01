package com.klimavicius.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klimavicius.models.Manager;
import com.klimavicius.services.ManagerService;

public class ManagerDelegate{

    private ManagerService managerService = new ManagerService();

    public void getAllManagers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Manager> managers = managerService.getAllManagers();

        try (PrintWriter pw = resp.getWriter()) {
            pw.write(new ObjectMapper().writeValueAsString(managers));
        }
    }

    public void createManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("I'm in create manager");
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
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        System.out.println(body);
        Manager newManager = objectMapper.readValue(body, Manager.class);
        System.out.println(newManager);
        managerService.createManager(newManager);
        resp.setStatus(201);
    }
}