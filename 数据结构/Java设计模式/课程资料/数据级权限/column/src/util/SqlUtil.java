package util;

import java.lang.reflect.Field;
import java.util.List;

public class SqlUtil {
	/**
	 * 集合内容转字符串
	 * @param columnsList
	 * @param cls			泛型类型
	 * @param fieldName		转化字段
	 * @return
	 * @throws Exception
	 */
	public static String sqlList2Str(List columnsList, Class<?> cls, String fieldName) throws Exception{
		if( columnsList == null || columnsList.size() == 0 )return "\'\'";
		StringBuilder strB = new StringBuilder();
		for (Object obj : columnsList) {
			Field field = cls.getDeclaredField(fieldName);
			field.setAccessible(true);
			strB.append( "`" + field.get( obj ) + "`,");
		}
		String str = strB.toString().substring(0, strB.toString().length() - 1);
		return str;
	}
	
}
