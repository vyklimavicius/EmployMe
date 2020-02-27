package com.klimavicius.services;

import java.util.List;

import javax.servlet.http.HttpServlet;

import com.klimavicius.daos.ManagerDaoConcrete;
import com.klimavicius.models.Manager;

public class ManagerService extends HttpServlet{

    ManagerDaoConcrete managerController = new ManagerDaoConcrete();

    public List<Manager> getAllManagers(){
        return managerController.getManagers();
    }

    public Manager getManagerByEmail(String s){
        return managerController.getManagerByEmail(s);
    }

    public Manager getManagerById(int id){
        return managerController.getManagerById(id);
    }

    public int createManager(Manager m){
        return managerController.createManager(m);
    }

    public int updateManager(String value, Manager m){
        return managerController.updateManager(value, m);
    }

    public int deleteManager(Manager m){
        return managerController.deleteManager(m);
    }

}