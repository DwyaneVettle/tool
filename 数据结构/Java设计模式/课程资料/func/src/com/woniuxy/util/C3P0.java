package com.woniuxy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {
	private static String driveName;
	private static String url;
	private static String user;
	private static String password;
	
	//c3p0连接池对象
	private static ComboPooledDataSource ds;
	//最小连接数
	private static Integer minPoolSize;
	private static Integer maxPoolSize;
	private static Integer maxIdleTime;
	static{
		try {
			//从配置文件中获取属性值
			String path = C3P0.class.getResource(".").getPath() + "c3p0.properties";
			Properties prop = new Properties();
			InputStream in = new FileInputStream(new File(path));
			prop.load( in );
			
			
			driveName = prop.getProperty("driveName");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			minPoolSize = Integer.parseInt(prop.getProperty("minPoolSize"));
			maxPoolSize = Integer.parseInt(prop.getProperty("maxPoolSize"));
			maxIdleTime = Integer.parseInt(prop.getProperty("maxIdleTime"));
			
			
			ds = new ComboPooledDataSource();
			ds.setDriverClass(driveName);
			ds.setJdbcUrl(url);
			ds.setUser(user);
			ds.setPassword(password);
			
			ds.setMinPoolSize(minPoolSize);
			ds.setMaxPoolSize(maxPoolSize);
			ds.setMaxIdleTime(maxIdleTime);
			
			
			in.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
