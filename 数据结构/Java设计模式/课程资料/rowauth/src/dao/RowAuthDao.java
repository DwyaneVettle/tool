package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.RowAuth;
import util.DBUtil;

public class RowAuthDao {
	/**
	 * 查询员工权限
	 * @param empID
	 * @return
	 * @throws Exception
	 */
	public String queryRowAuth(Integer empID) throws Exception{
		Connection conn = null;
		StringBuilder strB = new StringBuilder();
		try {
			conn = DBUtil.getConnection();
			String sql = " select dept_id deptID "
						+ " from `row_auth` "
						+ " where tabName = 'consumption' and employee_id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, empID);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				strB.append(rs.getString("deptID") + ",");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtil.close(conn);
		}
		return strB.length() == 0 ? "-1" : strB.toString().substring(0, strB.length() - 1);
	}
	
	/**
	 * 查询员工缺失权限
	 * @param empID
	 * @return
	 * @throws Exception 
	 */
	public List<RowAuth> queryEmpAuth(Integer empID) throws Exception{
		Connection conn = null;
		RowAuth ra = null;
		List<RowAuth> list = new ArrayList<RowAuth>();
		try {
			conn = DBUtil.getConnection();
			String sql = " select dept.id deptID, dept.name deptName, tempTab.empID "
						+ " from `dept` dept "
						+ " left join ( "
							+ " select ra.`employee_id` empID, ra.`dept_id` deptID "
								+ " from `row_auth` ra "
								+ " where ra.`tabName` = 'consumption' and ra.`employee_id` = ? "
						+ " )tempTab on tempTab.deptID = dept.`id` ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, empID);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				ra = new RowAuth();
				ra.setDeptName(rs.getString("deptName"));
				ra.setEmployeeID(rs.getInt("empID"));
				ra.setDeptID(rs.getInt("deptID"));
				list.add(ra);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}
	
	public void deleteAuthor(Integer empID) throws SQLException{
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " delete from `row_auth` where employee_id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, empID);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 添加权限
	 * @param fields
	 * @throws SQLException 
	 */
	public void addAuthor(String[] rowDatas, Integer empID) throws SQLException{
		if( rowDatas == null )return;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " insert into `row_auth`(tabName, employee_id, dept_id) values ";
			String addSql = "";
			for (String str : rowDatas) {
				addSql += "('consumption', " + empID + ", " + str + "),";
			}
			addSql = addSql.substring(0, addSql.length() - 1);
			
			PreparedStatement ps = conn.prepareStatement(sql + addSql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtil.close(conn);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
