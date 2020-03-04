package com.klimavicius.daos;

import java.util.List;
import com.klimavicius.models.Reimbursement;

public interface ReimbursementDao {

    public List <Reimbursement> getReimbursements();
    public Reimbursement getReimbursementById(int id);
    public List <Reimbursement> getReimbursementEmployeeById(int id);
    public List <Reimbursement> getReimbursementManagerById(int id);
    public int createReimbursement(Reimbursement r);
    public int updateReimbursement(Reimbursement r);

}
