package com.woniuxy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.woniuxy.model.User;
import com.woniuxy.util.C3P0;

public class UserDao {
	/**
	 * 不写了
	 * @param userName
	 * @return
	 */
	public User findUser(String userName){
		Connection conn = null;
		User user = null;
		try {
			conn = C3P0.getConnection();
			String sql = " select * from `t_user` where `name` = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
//				user.setRole(rs.getInt("role"));
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
		return user;
	}
}
