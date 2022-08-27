package demo;

/**
 * 维吉尼亚加解密
 * @author waver
 *
 */
public class VirginiaUtil {
	/**
	 * 加密
	 * @param str
	 * @param key
	 * @return
	 */
	public static String enCodeStr(String str, String key){
		if( str == null || str.length() <= 0 )return str;
		if( key == null || key.length() <= 0 )throw new RuntimeException("秘钥不能为空！");
		char tempch = 0;
		int startPos = 0;
		String newKey = rebuildKeyCase(str, key);
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			tempch = str.charAt(i);
			if( tempch != 32 ){
				startPos = getStartPos(tempch);
				strB.append(getCodeStr(String.valueOf(newKey.charAt(i)), tempch - startPos));
			}else{
				strB.append("/");
			}
		}
		return strB.toString();
	}
	
	
	/**
	 * 解密
	 * @param str
	 * @param key
	 * @return
	 */
	public static String deCodeStr(String str, String key){
		if( str == null || str.length() <= 0 )return str;
		if( key == null || key.length() <= 0 )throw new RuntimeException("秘钥不能为空！");
		char tempch = 0;
		int startPos = 0;
		int py = 0;
		String newKey = rebuildKeyCase(str, key);
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			tempch = str.charAt(i);
			if( tempch != 47 ){
				startPos = getStartPos(tempch);
				//('w' - 'a') - ('b' - 'a')
				py = (str.charAt(i) - startPos) - (newKey.charAt(i) - startPos);
				strB.append(getCodeStr(String.valueOf( (char)getStartPos(newKey.charAt(i)) ), py));
			}else{
				strB.append(" ");
			}
		}
		return strB.toString();
	}
	
	
	/**
	 * 位移
	 * @param str
	 * @param key
	 * @return
	 */
	private static String getCodeStr(String str, int key){
		if( !str.matches("^[A-Za-z ]+$") )throw new RuntimeException("原文超出范围！");
		char ch;
		int startPos;
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if( ch != 32 ){//空格
				startPos = getStartPos(ch);
				ch = (char) ((ch - startPos + (key < 0 ? 26 + key : key)) % 26 + startPos);
			}
			strB.append(String.valueOf(ch));
		}
		return strB.toString();
	}
	
	
	/**
	 * 重组秘钥
	 * @param str
	 * @param key
	 * @return
	 */
	private static String rebuildKeyCase(String str, String key){
		StringBuilder newKey = new StringBuilder();
		int index = 0;
		int keyIndex = 0;
		String tempStr = null;
		while( newKey.length() < str.length() ){
			if( getStartPos( str.charAt(index) ) >= 97 ){
				tempStr = String.valueOf( key.charAt(keyIndex) ).toLowerCase();
			}else{
				tempStr = String.valueOf( key.charAt(keyIndex) ).toUpperCase();
			}
			newKey.append(tempStr);
			index++;
			keyIndex++;
			if( keyIndex == key.length() )keyIndex = 0;
		}
		return newKey.toString();
	}
	
	
	/**
	 * 大小写起点
	 * @param ch
	 * @return
	 */
	private static int getStartPos(char ch){
		if( ch >= 65 && ch <= 90 )return 65;
		if( ch >= 97 && ch <= 122 )return 97;
		if( ch == 32 || ch == 47 )return 97;
		throw new RuntimeException("超出范围！");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
