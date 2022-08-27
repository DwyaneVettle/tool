package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cup;
import model.FieldFupUser;
import util.DBUtil;
import util.SqlUtil;

public class CupDao {
	public List<Cup> queryColumnList(List<FieldFupUser> columnsList) throws Exception{
		List<Cup> list = new ArrayList<Cup>();
		Cup cup = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			//组装sql
			String sql = " select # from `cup` ";
			String selectSql = SqlUtil.sqlList2Str(columnsList, FieldFupUser.class, "fieldName");
			sql = sql.replaceAll("#", selectSql);

			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			//获取数据源信息
			ResultSetMetaData rsmd = rs.getMetaData();
			//获取字段总数
			int columnCount = rsmd.getColumnCount();
			while( rs.next() ){
				cup = new Cup();
				for (int i = 1; i <= columnCount; i++) {
					//存在权限
					if( selectSql.indexOf( rsmd.getColumnName(i) ) >= 0 ){
						Cup.setValue(cup, rsmd.getColumnName(i), rs.getObject(i));
					}
				}
				list.add(cup);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtil.close(conn);
		}
		return list;
	}
	
	public static void main(String[] args) {
		String str = "`id`,`name`,`version`,`ordinary_price`,`wholesale_price`";
		String str2 = "name1";
		
		System.out.println(str.indexOf(str2));
		
	}
}
