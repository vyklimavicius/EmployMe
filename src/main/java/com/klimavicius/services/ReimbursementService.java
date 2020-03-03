package com.klimavicius.services;

import java.util.List;

import javax.servlet.http.HttpServlet;

import com.klimavicius.daos.ReimbursementDaoConcrete;
import com.klimavicius.models.Manager;
import com.klimavicius.models.Reimbursement;

public class ReimbursementService extends HttpServlet {
    

    ReimbursementDaoConcrete reimbursementController = new ReimbursementDaoConcrete();

    public List<Reimbursement> getAllReimbursement(){
        return reimbursementController.getReimbursements();
    }

    public Reimbursement getReimbursementById(int id){
        return reimbursementController.getReimbursementById(id);
    }


    public List<Reimbursement> getReimbursementEmployeeById(int id){
        return reimbursementController.getReimbursementEmployeeById(id);
    }

    public List<Reimbursement> getReimbursementManagerById(int id){
        return reimbursementController.getReimbursementManagerById(id);
    }

    public int createReimbursement(Reimbursement r){
        return reimbursementController.createReimbursement(r);
    }

    public int updateReimbursement(Reimbursement r){
        return reimbursementController.updateReimbursement(r);
    }

    // public int updateReimbursementManager(Reimbursement r, Manager m){
    //     return reimbursementController.updateReimbursementManager(r, m);
    // }
}