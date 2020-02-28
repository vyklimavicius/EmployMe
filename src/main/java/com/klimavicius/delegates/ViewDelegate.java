package com.klimavicius.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// For static files
public class ViewDelegate {

    public void resolveView(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getServletPath();
        System.out.println("in our view delegate: " + uri);

        switch (uri) {
            case "/test":
                req.getRequestDispatcher("/static/views/index.html").forward(req, resp);
                break;
            case "/directory":
                req.getRequestDispatcher("/static/Views/BirdDirectory.html").forward(req, resp);
                break;
            default:
                resp.sendError(404, "Static Resource Not Found");
        }
    }

}