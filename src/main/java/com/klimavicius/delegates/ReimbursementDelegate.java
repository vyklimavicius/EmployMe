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
import com.klimavicius.services.ReimbursementService;

import org.apache.log4j.Logger;

public class ReimbursementDelegate {

    final static Logger logger = Logger.getLogger(ReimbursementDelegate.class);
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
        logger.info("I'm in create reimbursement");
        // System.out.println("I'm in create reimbursement");
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
                    logger.error("Reimbursement Delegate exception", ex);
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        Reimbursement newReimbursement = objectMapper.readValue(body, Reimbursement.class);
        logger.info("Reimbursement to be created");
        // System.out.println(newReimbursement);
        if (newReimbursement.getType() == null){
            resp.setStatus(404);
        } else {
            reimbursementService.createReimbursement(newReimbursement);
            resp.setStatus(201);
        }
    }

    public void updateReimbursement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("I'm in update reimbursement!");
        // System.out.println("I'm in update reimbursement!");
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        ReimbursementService reimbursementService = new ReimbursementService();

        InputStream inputStream = req.getInputStream();

        try {
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
                    logger.error("Reimbursement Delegate exception", ex);
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        Reimbursement newReimbursement = objectMapper.readValue(body, Reimbursement.class);
        int managerId = newReimbursement.getManagerId();
        String status = newReimbursement.getStatus();
        Reimbursement updateReimbursement = reimbursementService.getReimbursementById(newReimbursement.getReimbursementId());
        updateReimbursement.setManagerId(managerId);
        updateReimbursement.setStatus(status);
        logger.info("Reimbursement to be updated " + updateReimbursement);
        // System.out.println(updateReimbursement);
        reimbursementService.updateReimbursement(updateReimbursement);
        resp.setStatus(200);
    }
}
