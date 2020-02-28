package com.klimavicius.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klimavicius.models.Reimbursement;
import com.klimavicius.services.ReimbursementService;

public class ReimbursementDelegate {

    private ReimbursementService reimbursementService = new ReimbursementService();

    public void getAllReimbursements(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursement();

        try (PrintWriter pw = resp.getWriter()) {
            pw.write(new ObjectMapper().writeValueAsString(reimbursements));
        }
    }
}