package com.klimavicius.daos;

import java.util.List;

import com.klimavicius.models.Manager;

public interface ManagerDao {
	
	public List<Manager> getManagers();
	public Manager getManagerByEmail(String s);
	public Manager getManagerById(int id);
	public int createManager(Manager m);
	public Manager updateManager(String value, Manager m);
	public int deleteEmployee(Manager m);

}
