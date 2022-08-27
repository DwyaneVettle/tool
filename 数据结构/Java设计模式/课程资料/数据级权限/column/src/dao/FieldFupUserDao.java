package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.FieldFupUser;
import util.DBUtil;

public class FieldFupUserDao {
	
	/**
	 * 查询用户field_fup_user表列级权限
	 * @param <T>
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	public List<FieldFupUser> queryColumns(Integer userID) throws SQLException{
		Connection conn = null;
		List<FieldFupUser> list = new ArrayList<FieldFupUser>();
		FieldFupUser ffu = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " select fcu.fieldName fieldName, tempTab.column_comment `comment`, tfo.fieldOrder fieldOrder "
						+ " from `field_cup_user` fcu "
						+ " left join ( "
							+ " select column_name,column_comment "
								+ " from information_schema.columns "
								+ " where table_name='cup' and table_schema='test' "
						+ " )tempTab on tempTab.column_name = fcu.fieldName "
						+ " left join `tab_filed_order` tfo on tfo.fieldName = fcu.fieldName "
						+ " where tfo.tableName = 'cup' and fcu.user_id = ? "
						+ " order by tfo.fieldOrder asc ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				ffu = new FieldFupUser();
				ffu.setFieldName(rs.getString("fieldName"));
				ffu.setComment(rs.getString("comment"));
				ffu.setFieldOrder(rs.getInt("fieldOrder"));
				list.add(ffu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}
	
	
	
	/**
	 * 查询用户在cup表中的缺失权限
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	public List<FieldFupUser> queryUserAuthor(Integer userID) throws SQLException{
		Connection conn = null;
		FieldFupUser ffu = null;
		List<FieldFupUser> list = new ArrayList<FieldFupUser>();
		try {
			conn = DBUtil.getConnection();
			String sql = " select authTab.comment `comment`, userTab.fieldName fieldName, authTab.columName "
						+ " from( "
							+ " select column_name columName, column_comment `comment` "
							+ " from information_schema.columns "
							+ " where table_name='cup' and table_schema='test' "
						+ " )authTab "
						+ " left join ( "
							+ " select * "
								+ " from `field_cup_user` fcu "
								+ " where fcu.user_id = ? "
						+ " )userTab on userTab.fieldName = authTab.columName ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				ffu = new FieldFupUser();
				ffu.setFieldName(rs.getString("fieldName"));
				ffu.setColumName(rs.getString("columName"));
				ffu.setComment(rs.getString("comment"));
				list.add(ffu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}
	
	
	
	public void deleteAuthor(Integer userID) throws SQLException{
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " delete from `field_cup_user` where user_id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
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
	public void addAuthor(String[] fields, Integer userID) throws SQLException{
		if( fields == null )return;
		
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " insert into `field_cup_user`(user_id, fieldName) values ";
			String addSql = "";
			for (String str : fields) {
				addSql += "( " + userID + ", '" + str + "' ),";
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
