package com.klimavicius.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;

public class Reimbursement {
	

	int reimbursementId, employeeId, managerId;
	String status;
	Double reimbursement;
	Date createdAt;
	Date updatedAt;

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", employeeId=" + employeeId + ", managerId=" + managerId + ", status="
				+ status + ", reimbursement=" + reimbursement + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	// default constructor
	public Reimbursement(){
		super();
	}

	public Reimbursement(int employeeId, Double reimbursement) {
		this.employeeId = employeeId;
		this.status = "pending";
		this.managerId = 1;
		this.reimbursement = reimbursement;
	}

	public Reimbursement(int reimbursementId, int employeeId, int managerId, String status, Double reimbursement, Date createdAt, Date updatedAt) {
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.status = status;
		this.reimbursement = reimbursement;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Reimbursement(int employeeId, int managerId, String status, Double reimbursement) {
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.status = status;
		this.reimbursement = reimbursement;
	}


	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getReimbursement() {
		return reimbursement;
	}

	public void setReimbursement(Double reimbursement) {
		this.reimbursement = reimbursement;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	
	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	
	

}
