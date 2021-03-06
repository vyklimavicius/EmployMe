package com.klimavicius.daos;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


import com.klimavicius.models.Reimbursement;
import com.klimavicius.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class ReimbursementDaoConcrete implements ReimbursementDao {

	private static Logger logger = Logger.getLogger(ReimbursementDaoConcrete.class);

	public ReimbursementDaoConcrete() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Reimbursement> getReimbursements() {
		
		List <Reimbursement> reimbursements = new ArrayList<>();
		String sql = "select * from reimbursements";

		try(Connection c = ConnectionUtil.getConnection();
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery(sql)) {

			while(rs.next()){
				int reimbursement_id = rs.getInt("reimbursement_id");				
				int employee_id = rs.getInt("employee_id");				
				int manager_id = rs.getInt("manager_id");				
				String status = rs.getString("status");				
				String type = rs.getString("type");				
				Double reimbursement = rs.getDouble("reimbursement");
				Date createdAt = new Date(rs.getTimestamp("created_at").getTime());
				Date updatedAt = new Date(rs.getTimestamp("updated_at").getTime());
				
				Reimbursement readReimbursement = new Reimbursement(reimbursement_id, employee_id, manager_id, status, type, reimbursement, createdAt, updatedAt);

				reimbursements.add(readReimbursement);
			}
			
		} catch (Exception e) {
			logger.error("Get reimbursements exception", e);
			// e.printStackTrace();
		}

		return reimbursements;
	}

	@Override
	public List <Reimbursement> getReimbursementEmployeeById(int id) {
		String sql = "select * from reimbursements where employee_id = ?";
		List <Reimbursement> selectedReimbursements = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
		 ) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int reimbursement_id = rs.getInt("reimbursement_id");
				int employee_id = rs.getInt("employee_id");
				int manager_id = rs.getInt("manager_id");
				String status = rs.getString("status");
				String type = rs.getString("type");
				Double reimbursement = rs.getDouble("reimbursement");
				Date createdAt = new Date(rs.getTimestamp("created_at").getTime());
				Date updatedAt = new Date(rs.getTimestamp("updated_at").getTime());

				Reimbursement selectedReimbursement = new Reimbursement(reimbursement_id, employee_id, manager_id, status,
						type, reimbursement, createdAt, updatedAt);

				selectedReimbursements.add(selectedReimbursement);
			}
			
		} catch (Exception e) {
			logger.error("Get reimbursement by employee id", e);
			// e.printStackTrace();
		}

		return selectedReimbursements;
	}

	@Override
	public List <Reimbursement> getReimbursementManagerById(int id) {
		
		String sql = "select * from reimbursements where manager_id = ?";
		List<Reimbursement> selectedReimbursements = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				int reimbursement_id = rs.getInt("reimbursement_id");
				int employee_id = rs.getInt("employee_id");
				int manager_id = rs.getInt("manager_id");
				String status = rs.getString("status");
				String type = rs.getString("type");
				Double reimbursement = rs.getDouble("reimbursement");
				Date createdAt = new Date(rs.getTimestamp("created_at").getTime());
				Date updatedAt = new Date(rs.getTimestamp("updated_at").getTime());

				Reimbursement selectedReimbursement = new Reimbursement(reimbursement_id, employee_id, manager_id,
						type, status, reimbursement, createdAt, updatedAt);

				selectedReimbursements.add(selectedReimbursement);
			}

		} catch (Exception e) {
			logger.error("Get reimbursements by manager id");
			// e.printStackTrace();
		}

		return selectedReimbursements;
	}

	@Override
	public int createReimbursement(Reimbursement r) {
		
		String sql = "insert into reimbursements (employee_id, manager_id, status, type, reimbursement) values (?, ?, ?, ?, ?)";

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, r.getEmployeeId());
			ps.setInt(2, r.getManagerId());
			ps.setString(3, r.getStatus());
			ps.setString(4, r.getType());
			ps.setDouble(5, r.getReimbursement());
			ResultSet rs = ps.executeQuery();
		} catch (SQLException x) {
			logger.error("Create reimbursement exception", x);
			// x.printStackTrace();
		}

		logger.info("Reimbursement created");
		return 0;

	}

	// @Override
	// public int updateReimbursementStatus(Reimbursement r, String status) {
		
	// 	int employee_id = r.getEmployeeId();
	// 	ResultSet rs = null;
	// 	String sql = null;

	// 	switch(status){
	// 		case "pending":
	// 		sql = "update reimbursements set status = ? where employee_id = ?";
	// 				try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
	// 					ps.setString(1, "pending");
	// 					ps.setInt(2, employee_id);
	// 					ps.executeUpdate();
	// 				} catch (SQLException x) {
	// 					x.printStackTrace();
	// 				}
	// 		break;
	// 		case "resolved":
	// 			sql = "update reimbursements set status = ? where employee_id = ?";
	// 			try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
	// 				ps.setString(1, "resolved");
	// 				ps.setInt(2, employee_id);
	// 				ps.executeUpdate();
	// 			} catch (SQLException x) {
	// 				x.printStackTrace();
	// 			}
	// 			break;
	// 		case "denied":
	// 			sql = "update reimbursements set status = ? where employee_id = ?";
	// 			try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
	// 				ps.setString(1, "denied");
	// 				ps.setInt(2, employee_id);
	// 				ps.executeUpdate();
	// 			} catch (SQLException x) {
	// 				x.printStackTrace();
	// 			}
	// 			break;
	// 		default:
	// 			break;
	// 	}
				
	// 	return 0;
	// }

	@Override
	public int updateReimbursement(Reimbursement r) {
		
		int reimbursement_id = r.getReimbursementId();
		int manager_id = r.getManagerId();
		String status = r.getStatus();
		String sql = "update reimbursements set manager_id = ?, status = ?  where reimbursement_id = ?";

		// try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
		// 	ps.setInt(1, manager_id);
		// 	ps.setInt(2, employee_id);
		// 	ps.executeUpdate();
		// } catch (SQLException x) {
		// 	x.printStackTrace();
		// }

		try (Connection c = ConnectionUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, manager_id);
			ps.setString(2, status);
			ps.setInt(3, reimbursement_id);
			ResultSet rs = ps.executeQuery();
		} catch (SQLException x) {
			logger.error("Reimbursement update exception", x);
			// x.printStackTrace();
		}

		// System.out.println("Reimbursement updated!");
		logger.info("Reimbursement updated!");
		return 0;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		
		String sql = "select * from reimbursements where reimbursement_id = ?";
		Reimbursement selectedReimbursement = null;

		try (Connection c = ConnectionUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				) {
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimbursement_id = rs.getInt("reimbursement_id");
				int employee_id = rs.getInt("employee_id");
				int manager_id = rs.getInt("manager_id");
				String status = rs.getString("status");
				String type = rs.getString("type");
				Double reimbursement = rs.getDouble("reimbursement");
				Date createdAt = new Date(rs.getTimestamp("created_at").getTime());
				Date updatedAt = new Date(rs.getTimestamp("updated_at").getTime());

				selectedReimbursement = new Reimbursement(reimbursement_id, employee_id, manager_id,
						type, status, reimbursement, createdAt, updatedAt);

			}

		} catch (Exception e) {
			logger.error("Get reimbursememnt by id exception", e);
			e.printStackTrace();
		}

		return selectedReimbursement;
	}

}
