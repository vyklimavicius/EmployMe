package com.klimavicius.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klimavicius.models.Reimbursement;
import com.klimavicius.services.ManagerService;
import com.klimavicius.services.ReimbursementService;

public class ReimbursementDelegate {

    private ReimbursementService reimbursementService = new ReimbursementService();

    public void getAllReimbursements(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursement();

        try (PrintWriter pw = resp.getWriter()) {
            pw.write(new ObjectMapper().writeValueAsString(reimbursements));
        }
        resp.setStatus(200);
    }

    public void createReimbursement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("I'm in create reimbursement");
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        ReimbursementService reimbursementService = new ReimbursementService();

        try {
            InputStream inputStream = req.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        System.out.println(body);
        Reimbursement newReimbursement = objectMapper.readValue(body, Reimbursement.class);
        System.out.println(newReimbursement);
        reimbursementService.createReimbursement(newReimbursement);
        resp.setStatus(201);
    }
}
