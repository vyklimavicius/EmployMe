package com.klimavicius.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.klimavicius.delegates.AuthEmployeeDelegate;
import com.klimavicius.delegates.AuthManagerDelegate;
import com.klimavicius.delegates.EmployeeDelegate;
import com.klimavicius.delegates.ManagerDelegate;
import com.klimavicius.delegates.ReimbursementDelegate;
import com.klimavicius.delegates.ViewDelegate;

public class RequestHelper {

    private EmployeeDelegate employeeDelegate = new EmployeeDelegate();
    private ManagerDelegate managerDelegate = new ManagerDelegate();
    private ReimbursementDelegate reimbursementDelegate = new ReimbursementDelegate();
	private ViewDelegate viewDelegate = new ViewDelegate();
	private AuthManagerDelegate authManagerDelegate = new AuthManagerDelegate();
	private AuthEmployeeDelegate authEmployeeDelegate = new AuthEmployeeDelegate();

    public void getMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI().substring(req.getContextPath().length());
		
        if(path.startsWith("/api/")) {

            String url = path.substring(5);

            switch(url) {
			case "employees":
				employeeDelegate.getAllEmployees(req, resp);
				break;
			case "managers":
				managerDelegate.getAllManagers(req, resp);
				break;
			case "reimbursements":
				reimbursementDelegate.getAllReimbursements(req, resp);
				break;
			default:
				resp.sendError(404, "Record not supported");
			}
			
		} else {
			viewDelegate.resolveView(req, resp);
		}
	}

	public void getPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI().substring(req.getContextPath().length());

		if (path.startsWith("/api/")) {

			String url = path.substring(5);
			// BufferedReader body = req.getReader();
			// System.out.println(body);

			switch (url) {
				case "employees":
					// resp.sendError(404, "Working on it");
					employeeDelegate.createEmployee(req, resp);
					break;
				case "managers":
					managerDelegate.createManager(req, resp);
					break;
				case "authmanager":
					authManagerDelegate.authentication(req, resp);
					break;
				case "authemployee":
					authEmployeeDelegate.authentication(req, resp);
					break;
				case "reimbursements":
					reimbursementDelegate.createReimbursement(req, resp);
					break;
				default:
					resp.sendError(404, "Working on it");
			}

		} else {
			viewDelegate.resolveView(req, resp);
		}
	}
	


}
