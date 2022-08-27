package com.woniuxy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woniuxy.model.Func;
import com.woniuxy.model.User;
import com.woniuxy.util.C3P0;

public class FuncDao {
	
	public List<Func> findUserFunc(Integer uid){
		Connection conn = null;
		List<Func> list= new ArrayList<Func>();
		Func func = null;
		try {
			conn = C3P0.getConnection();
			String sql = " select u.id uID, r.`name` rName, func.`name` fName, func.`pageName` pfName, func.`url` url "
						+ " from `t_func` func "
						+ " left join `t_role_func` rf on rf.`func_id` = func.`id` "
						+ " left join `t_user_role` ur on ur.`role_id` = rf.`role_id` "
						+ " left join `t_role` r on r.`id` = ur.`role_id` "
						+ " left join `t_user` u on u.`id` = ur.`user_id` "
						+ " where u.id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				func = new Func();
				func.setUid(rs.getInt("uID"));
				func.setrName(rs.getString("rName"));
				func.setfName(rs.getString("fName"));
				func.setPfName(rs.getString("pfName"));
				func.setUrl(rs.getString("url"));
				list.add(func);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
}
