package com.klimavicius.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

// For static files
public class ViewDelegate {

    final static Logger logger = Logger.getLogger(ViewDelegate.class);

    public void resolveView(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getServletPath();
        logger.info("view delegate: " + uri);
        // System.out.println("view delegate: " + uri);

        switch (uri) {
            case "/signup":
                req.getRequestDispatcher("/static/views/SignUp.html").forward(req, resp);
                break;
            case "/login":
                req.getRequestDispatcher("/static/views/Login.html").forward(req, resp);
                break;
            case "/dashboardemployee":
                req.getRequestDispatcher("/static/views/DashBoardEmployee.html").forward(req, resp);
                break;
            case "/dashboardmanager":
                req.getRequestDispatcher("/static/views/DashBoardManager.html").forward(req, resp);
                break;
            default:
                resp.sendError(404, "Static Resource Not Found");
        }
    }

}