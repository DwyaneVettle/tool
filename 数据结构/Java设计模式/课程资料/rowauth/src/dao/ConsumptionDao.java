package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Consumption;
import util.DBUtil;

public class ConsumptionDao {
	
	/**
	 * 查询报表
	 * @param rowAuth
	 * @return
	 * @throws Exception
	 */
	public List<Consumption> query(String rowAuth) throws Exception{
		Connection conn = null;
		Consumption csp = null;
		List<Consumption> list = new ArrayList<Consumption>();
		try {
			conn = DBUtil.getConnection();
			String sql = " select con.id, con.expenditure, con.time, con.mark, dept.`name` deptName "
						+ " from `consumption` con "
						+ " left join `dept` dept on con.dept_id = dept.id "
						+ " where con.dept_id in ( " + rowAuth + " ) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				csp = new Consumption();
				csp.setId(rs.getInt("id"));
				csp.setExpenditure(rs.getBigDecimal("expenditure"));
				csp.setTime(rs.getDate("time"));
				csp.setMark(rs.getString("mark"));
				csp.setDeptName(rs.getString("deptName"));
				list.add(csp);
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
