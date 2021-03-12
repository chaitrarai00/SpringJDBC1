package com.example.jdbc.main.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EmployeeDAOImpl implements EmployeeDAO{
	private DataSource datasource;
	
	public void setDatasource(DataSource datasource) {
		this.datasource=datasource;
	}
	
	@Override
	public void save(Employee employee) {
		String query="insert into Employee (id,name,role) values (?,?,?)";
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=datasource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, employee.getId());
			ps.setString(2, employee.getName());
			ps.setNString(3, employee.getRole());
			int out=ps.executeUpdate();
			if(out!=0) {
				System.out.println("Employee saved with id"+employee.getId());
			}
			else {
				System.out.println("Employee failed to be saved with id"+employee.getId());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			ps.close();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee get(int id) {
		String query="select name, role from Employee where id = ?";
		Employee emp=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=datasource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()) {
				emp=new Employee();
				emp.setId(id);
				emp.setName(rs.getString("name"));
				emp.setRole(rs.getNString("role"));
				System.out.println("employee found"+emp);
			}
			else {
				System.out.println("Employee not found");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
			ps.close();
			con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public void update(Employee employee) {
		String query="Update Employee set name=?,role=? where id=?";
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=datasource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getRole());
			ps.setInt(3, employee.getId());
			int out=ps.executeUpdate();
			if(out!=0) {
				System.out.println("Employee Updated with id:"+employee.getId());
			}
			else {
				System.out.println("Employee cound not be Updated with id:"+employee.getId());
			}
		}catch(SQLException e) {
		e.printStackTrace();
		}
		try {
			ps.close();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletebyid(int id) {
		String query="delete from employee where id=?";
		PreparedStatement ps=null;
		Connection con=null;
		try {
			con=datasource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			int out=ps.executeUpdate();
			if(out!=0) {
				System.out.println("Employee Deleted with id:"+id);
			}
			else {
				System.out.println("Employee could not be deleted with id:"+id);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ps.close();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Employee> getAll() {
		String query="select name,role,id from Employee";
		ArrayList<Employee> emp_list=new ArrayList<Employee>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=datasource.getConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				Employee e=new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setRole(rs.getNString("role"));
				emp_list.add(e);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
			ps.close();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return emp_list;
	}

}
