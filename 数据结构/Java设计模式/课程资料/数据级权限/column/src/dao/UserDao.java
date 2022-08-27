package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.DBUtil;

public class UserDao {
	public User queryUser(String name) throws SQLException{
		User user = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " select * from `user` where `name` = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtil.close(conn);
		}
		return user;
	}
	
	
	/**
	 * 查询所有用户
	 * @return
	 * @throws Exception
	 */
	public List<User> getUsers() throws Exception{
		User user = null;
		Connection conn = null;
		List<User> list = new ArrayList<User>();
		try {
			conn = DBUtil.getConnection();
			String sql = " select * from `user` where `name` != 'admin' ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while( rs.next() ){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				list.add(user);
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
