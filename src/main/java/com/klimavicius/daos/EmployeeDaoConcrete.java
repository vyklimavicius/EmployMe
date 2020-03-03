package com.klimavicius.daos;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.klimavicius.models.Employee;
import com.klimavicius.util.ConnectionUtil;

public class EmployeeDaoConcrete implements EmployeeDao{

	public EmployeeDaoConcrete() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employee> getEmployees() {
		List <Employee> employees = new ArrayList<>();
		String sql = "select * from employees";

		try (Connection c = ConnectionUtil.getConnection();
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery(sql)){
			
			while(rs.next()){
				int employeeId = rs.getInt("employee_Id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String password = rs.getString("password");

				Employee readEmployee = new Employee(employeeId, firstName, lastName, email, password);
				employees.add(readEmployee);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Employee getEmployeeByEmail(String s) {
		// Prepared statement you need to pass parameters to the query
		String sql = "Select * from employees where email = ?";
		Employee employeeEmail = null;

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int employeeId = rs.getInt("employee_Id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String password = rs.getString("password");

				employeeEmail = new Employee(employeeId, firstName, lastName, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeEmail;
	}

	@Override
	public Employee getEmployeeById(int id) {
		
		String sql = "Select * from employees where employee_id = ?";
		Employee employee = null;

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int employeeId = rs.getInt("employee_Id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String password = rs.getString("password");

				employee = new Employee(employeeId, firstName, lastName, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public int createEmployee(Employee e) {
		String sql = "insert into employees (firstname, lastname, email, password) values (?, ?, ?, ?)";
		
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getPassword());
			ResultSet rs = ps.executeQuery();
		} catch (SQLException x) {
			x.printStackTrace();
		}
		
		System.out.println("New Employee created!");
		return 0;
	}

	@Override
	public int updateEmployee(Employee e) {
		
		int employee_id = e.getEmployeeId();
		String sql = "update employees set firstname = ?, lastname = ?, password = ? where employee_id = ?";

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.setString(3, e.getPassword());
			ps.setInt(4, employee_id);
			ResultSet rs = ps.executeQuery();
		} catch (SQLException x) {
			x.printStackTrace();
		}

		System.out.println("Employee updated!");
		// switch (value) {
		// 	case "firstname":
		// 		sql = "update employees set firstname = ? where employee_id = ?";
		// 		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
		// 			ps.setString(1, e.getFirstName());
		// 			ps.setInt(2, employee_id);
		// 			ps.executeUpdate();
		// 		} catch (SQLException x) {
		// 			x.printStackTrace();
		// 		}
		// 		break;
		// 	case "lastname":
		// 		sql = "update employees set lastname = ? where employee_id = ?";
		// 		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
		// 			ps.setString(1, e.getLastName());
		// 			ps.setInt(2, employee_id);
		// 			ps.executeUpdate();
		// 		} catch (SQLException x) {
		// 			x.printStackTrace();
		// 		}
		// 		break;
		// 	case "email":
		// 		sql = "update employees set email = ? where employee_id = ?";
		// 		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
		// 			ps.setString(1, e.getEmail());
		// 			ps.setInt(2, employee_id);
		// 			ps.executeUpdate();
		// 		} catch (SQLException x) {
		// 			x.printStackTrace();
		// 		}
		// 		break;
		// 	case "password":
		// 		sql = "update employees set password = ? where employee_id = ?";
		// 		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
		// 			ps.setString(1, e.getPassword());
		// 			ps.setInt(2, employee_id);
		// 			ps.executeUpdate();
		// 		} catch (SQLException x) {
		// 			x.printStackTrace();
		// 		}
		// 		break;
		// 	default:
		// 		break;
		// }
		return 0;
	}

	@Override
	public int deleteEmployee(Employee e) {
		
		String sql = "delete from employees where employee_id = ?";
		int employee_id = e.getEmployeeId();
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, employee_id);
			ps.execute(sql);
		} catch (SQLException x) {
			x.printStackTrace();
		}
		return 0;
	}

}
