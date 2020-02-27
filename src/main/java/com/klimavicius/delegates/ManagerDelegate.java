package com.klimavicius.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}