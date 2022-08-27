package demo;
/**
 * 凯撒加解密算法
 * 	支持英文大小写+空格
 * @author waver
 *
 */
public class CaesarUtil {
	/**加密*/
	public static final int EN_CRYPT = 1;
	/**解密*/
	public static final int DE_CRYPT = 0;
	
	
	/**
	 * 凯撒加/解密
	 * @param str	原文（英文+空格）
	 * @param key	
	 * @param type	加解密类型
	 * @return
	 */
	public static String getCodeStr(String str, int key, int type){
		if( !str.matches("^[A-Za-z ]+$") )throw new RuntimeException("原文超出范围！");
		char ch;
		int startPos;
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if( ch != 32 ){//空格
				startPos = getStartPos(ch);
				if( type > 0 ){//加密
					ch = (char) ((ch - startPos + (key < 0 ? 26 + key : key)) % 26 + startPos);
				}else{//解密
					ch = (char) ((ch - startPos + 26 - (key < 0 ? 26 + key : key)) % 26 + startPos);
				}
			}
			strB.append(String.valueOf(ch));
		}
		return strB.toString();
	}
	
	private static int getStartPos(char ch){
		if( ch >= 65 && ch <= 90 )return 65;
		if( ch >= 97 && ch <= 122 )return 97;
		throw new RuntimeException("超出范围！");
	}
}
