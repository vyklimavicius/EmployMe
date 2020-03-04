package com.klimavicius.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.klimavicius.models.Manager;
import com.klimavicius.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class ManagerDaoConcrete implements ManagerDao {

	private static Logger logger = Logger.getLogger(ManagerDaoConcrete.class);

	public ManagerDaoConcrete() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Manager> getManagers() {
		
		List<Manager> managers = new ArrayList<>();
		String sql = "select * from managers";

		try (Connection c = ConnectionUtil.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {

			while (rs.next()) {
				int managerId = rs.getInt("manager_Id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String password = rs.getString("password");

				Manager readManager = new Manager(managerId, firstName, lastName, email, password);
				managers.add(readManager);

			}
		} catch (SQLException e) {
			logger.error("Get managers exception", e);
			// e.printStackTrace();
		}

		return managers;
	}

	@Override
	public Manager getManagerByEmail(String s) {
		// Prepared statement you need to pass parameters to the query
		String sql = "Select * from managers where email = ?";
		Manager managerEmail = null;

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int managerId = rs.getInt("manager_Id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String password = rs.getString("password");

				managerEmail = new Manager(managerId, firstName, lastName, email, password);
			}
		} catch (SQLException e) {
			logger.error("Get manager by email exception", e);
			// e.printStackTrace();
		}

		return managerEmail;
	}

	@Override
	public Manager getManagerById(int id) {
		
		String sql = "Select * from managers where manager_id = ?";
		Manager manager = null;

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int managerId = rs.getInt("manager_Id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String password = rs.getString("password");

				manager = new Manager(managerId, firstName, lastName, email, password);
			}
		} catch (SQLException e) {
			logger.error("Get manager by id exception", e);
			// e.printStackTrace();
		}

		return manager;
	}

	@Override
	public int createManager(Manager m) {
		
		String sql = "insert into managers (firstname, lastname, email, password) values (?, ?, ?, ?)";

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, m.getFirstName());
			ps.setString(2, m.getLastName());
			ps.setString(3, m.getEmail());
			ps.setString(4, m.getPassword());
			ResultSet rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.error("Create manager exception", e);
			// e.printStackTrace();
		}

		logger.info("New manager created!");
		// System.out.println("New Manager created!");
		return 0;

	}

	@Override
	public int updateManager(String value, Manager m) {
		
		int manager_id = m.getManagerId();
		ResultSet rs = null;
		String sql = null;
		switch (value) {
			case "firstname":
				sql = "update managers set firstname = ? where manager_id = ?";
				try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
					ps.setString(1, m.getFirstName());
					ps.setInt(2, manager_id);
					ps.executeUpdate();
				} catch (SQLException x) {
					x.printStackTrace();
				}
				break;
			case "lastname":
				sql = "update managers set lastname = ? where manager_id = ?";
				try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
					ps.setString(1, m.getLastName());
					ps.setInt(2, manager_id);
					ps.executeUpdate();
				} catch (SQLException x) {
					x.printStackTrace();
				}
				break;
			case "email":
				sql = "update managers set email = ? where manager_id = ?";
				try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
					ps.setString(1, m.getEmail());
					ps.setInt(2, manager_id);
					ps.executeUpdate();
				} catch (SQLException x) {
					x.printStackTrace();
				}
				break;
			case "password":
				sql = "update managers set password = ? where manager_id = ?";
				try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
					ps.setString(1, m.getPassword());
					ps.setInt(2, manager_id);
					ps.executeUpdate();
				} catch (SQLException x) {
					x.printStackTrace();
				}
				break;
			default:
				break;
		}
		return 0;
	}

	@Override
	public int deleteManager(Manager m) {
		
		String sql = "delete from managers where manager_id = ?";
		int manager_id = m.getManagerId();
		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, manager_id);
			ps.execute(sql);
		} catch (SQLException x) {
			x.printStackTrace();
		}
		return 0;
	}

}
