package com.klimavicius.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.klimavicius.delegates.EmployeeDelegate;
import com.klimavicius.delegates.ManagerDelegate;
import com.klimavicius.delegates.ReimbursementDelegate;
import com.klimavicius.delegates.ViewDelegate;

public class RequestHelper {

    private EmployeeDelegate employeeDelegate = new EmployeeDelegate();
    private ManagerDelegate managerDelegate = new ManagerDelegate();
    private ReimbursementDelegate reimbursementDelegate = new ReimbursementDelegate();
    private ViewDelegate viewDelegate = new ViewDelegate();

    public void getMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println(path);

        if(path.startsWith("/api/")) {

            String url = path.substring(5);

            switch(url) {
			case "employees":
				// process request with bird delegate
				employeeDelegate.getAllEmployees(req, resp);
				break;
			case "managers":
				// process request with habitat delegate
				managerDelegate.getAllManagers(req, resp);
				break;
			case "reimbursements":
				// process request with habitat delegate
				reimbursementDelegate.getAllReimbursements(req, resp);
				break;
			default:
				resp.sendError(404, "Record not supported");
			}
			
		} else {
			//requesting a view
			viewDelegate.resolveView(req, resp);
		}
    }

}
