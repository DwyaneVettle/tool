package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import util.DBUtil;

public class EmployeeDao {
	public Employee queryEmp(String name) throws Exception{
		Employee emp = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " select * from `employee` where `name` = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ){
				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtil.close(conn);
		}
		return emp;
	}
	
	public List<Employee> queryAllEmp() throws Exception{
		Connection conn = null;
		Employee emp = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			conn = DBUtil.getConnection();
			String sql = " select * from `employee` where `name` != 'admin' ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}
}
