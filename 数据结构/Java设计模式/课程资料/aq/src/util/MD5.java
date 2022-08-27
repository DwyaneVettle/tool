package util;

/**
 * MD5加密类
 * @author waver
 */
import java.security.MessageDigest;

public class MD5 {
	static char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f' };
	/**
	 * @param password
	 * @return encryptPassword
	 * @author chenming
	 */
	public static String convert32(String str) {
		try {
			byte[] bytes = str.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			bytes = md.digest();
			int j = bytes.length;
			char[] chars = new char[j * 2];
			int k = 0;
			for (int i = 0; i < bytes.length; i++) {
				byte b = bytes[i];
				chars[k++] = hexChars[b >>> 4 & 0xf];
				chars[k++] = hexChars[b & 0xf];
			}
			return new String(chars);
		} catch (Exception e) {
			return null;
		}
	}

	public String convert16(String s) {
		String ns = convert32(s).substring(8, 24);
		return ns;
	}
	
}
