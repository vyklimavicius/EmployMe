package com.klimavicius.daos;

import java.util.List;

import com.klimavicius.models.Employee;
import com.klimavicius.models.Manager;
import com.klimavicius.models.Reimbursement;

public interface ReimbursementDao {

    public List <Reimbursement> getReimbursements();
    public List <Reimbursement> getReimbursementEmployeeById(int id);
    public List <Reimbursement> getReimbursementManagerById(int id);
    public int createReimbursement(Reimbursement r);
    public int updateReimbursementManager(Reimbursement r, Manager m);
    public int updateReimbursementStatus(Reimbursement r, String status);

}
