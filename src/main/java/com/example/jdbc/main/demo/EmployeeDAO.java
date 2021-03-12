package com.example.jdbc.main.demo;

import java.util.List;

public interface EmployeeDAO {
	
	//create
	public void save(Employee employee);
	
	//retrieve/read
	public Employee get(int id);
	
	//update
	public void update(Employee employee);
	
	//delete object by accessing via id
	public void deletebyid(int id);
	
	//access all objects
	public List<Employee> getAll();
}
