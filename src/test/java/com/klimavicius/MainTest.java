package com.klimavicius;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;



import com.klimavicius.models.Employee;
import com.klimavicius.models.Manager;
import com.klimavicius.models.Reimbursement;
import com.klimavicius.services.EmployeeService;
import com.klimavicius.services.ManagerService;
import com.klimavicius.services.ReimbursementService;
import com.klimavicius.util.ConnectionUtil;

import org.junit.Test;

public class MainTest {

	@Test
	public void checkConnection() {
		String actual = "";
		try {
			Connection c = ConnectionUtil.getConnection();
			actual = c.getMetaData().getDriverName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String expected = "PostgreSQL JDBC Driver";
		assertEquals(expected, actual);
	}

	@Test
	public void createEmployee() {
		EmployeeService employeeController = new EmployeeService();
		Employee johnDoe = new Employee(200, "john", "Doe ", "john@test.com", "test");
		int actual = employeeController.createEmployee(johnDoe);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void updateEmployee() {
		EmployeeService employeeController = new EmployeeService();
		Employee johnDoe = new Employee(200, "john", "Doe ", "john@test.com", "test");
		int actual = employeeController.updateEmployee(johnDoe);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void getEmployeeByEmail() {
		boolean check = false;
		EmployeeService employeeController = new EmployeeService();
		Employee actual = employeeController.getEmployeeByEmail("john@test.com");
		if (actual.getClass() == Employee.class){
			check = true;
		};
		assertTrue(check);
	}

	@Test
	public void getEmployeeById() {
		boolean check = false;
		EmployeeService employeeController = new EmployeeService();
		Employee actual = employeeController.getEmployeeById(2);
		if (actual.getClass() == Employee.class){
			check = true;
		}
		assertTrue(check);
	}

	
	@Test
	public void createManager() {
		ManagerService managerController = new ManagerService();
		Manager johnDoe = new Manager(200, "john", "Doe ", "john@test.com", "test");
		int actual = managerController.createManager(johnDoe);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void getManagerById() {
		boolean check = false;
		ManagerService managerController = new ManagerService();
		Manager actual = managerController.getManagerById(3);
		if (actual.getClass() == Manager.class) {
			check = true;
		}
		assertTrue(check);
		
	}

	@Test
	public void getManagerByEmail() {
		boolean check = false;
		ManagerService managerController = new ManagerService();
		Manager actual = managerController.getManagerByEmail("john@test.com");
		if (actual.getClass() == Manager.class) {
			check = true;
		}
		assertTrue(check);
		
	}

	@Test
	public void createReimbursement() {
		ReimbursementService reimbursementController = new ReimbursementService();
		Reimbursement reimbursementTest = new Reimbursement(200, 200, 200, "Resolved", 2000.0, null, null);
		int actual = reimbursementController.createReimbursement(reimbursementTest);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void updateReimbursement() {
		ReimbursementService reimbursementController = new ReimbursementService();
		Reimbursement reimbursementTest = new Reimbursement(200, 200, 200, "Resolved", 2000.0, null, null);
		int actual = reimbursementController.updateReimbursement(reimbursementTest);
		int expected = 0;
		assertEquals(expected, actual);
	}


}
