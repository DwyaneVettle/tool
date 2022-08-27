package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static String driveName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3306/test";
	private static String user = "root";
	private static String password = "123123";
	
	static{
		try {
			Class.forName(driveName);
		} catch (Exception e) {
			System.out.println("没有找到驱动包！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭连接
	 * @param conn	连接对象
	 */
	public static void close( Connection conn ){
		try {
			if( conn != null )conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
