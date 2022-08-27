package util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密/解密工具
 * @author waver
 *
 */
public class Security {

	//AES对称加密算法
	public static class AES {
		/** 默认密钥 */
		public static final String DEFAULT_KEY = "woniuxy";
		
		/**
		 * AES加密
		 * @param content 需要加密的内容
		 * @param password  加密密码
		 * @return 16进制字符串
		 */
		public static String encrypt(String content, String password) {
			try {
				KeyGenerator kgen = KeyGenerator.getInstance("AES");
				kgen.init(128, new SecureRandom(password.getBytes()));
				SecretKey secretKey = kgen.generateKey();
				byte[] enCodeFormat = secretKey.getEncoded();
				SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
				Cipher cipher = Cipher.getInstance("AES");// 创建密码器
				byte[] byteContent = content.getBytes("utf-8");
				cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
				byte[] result = cipher.doFinal(byteContent);
				return parseByte2HexStr(result);//加密
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	    /**
	     * 解密 
	     * @param ciphertext  待解密内容 
	     * @param password 解密密钥 
	     * @return 
	     */  
	    public static String decrypt(String ciphertext, String password) {
	    	byte[] content = parseHexStr2Byte(ciphertext);
	    	try {
	    		KeyGenerator kgen = KeyGenerator.getInstance("AES");  
	    		kgen.init(128, new SecureRandom(password.getBytes()));  
	    		SecretKey secretKey = kgen.generateKey();  
	    		byte[] enCodeFormat = secretKey.getEncoded();  
	    		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
	    		Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
	    		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
	    		byte[] result = cipher.doFinal(content);//解密
	    		return new String(result); // 解密
	    	} catch (NoSuchAlgorithmException e) {  
	    		e.printStackTrace();  
	    	} catch (NoSuchPaddingException e) {  
	    		e.printStackTrace();  
	    	} catch (InvalidKeyException e) {  
	    		e.printStackTrace();  
	    	} catch (IllegalBlockSizeException e) {  
	    		e.printStackTrace();  
	    	} catch (BadPaddingException e) {  
	    		e.printStackTrace();  
	    	}  
	    	return null;  
	    }
	    
	    /**
	     * 将二进制转换成16进制 
	     * @param buf 
	     * @return 
	     */  
	    private static String parseByte2HexStr(byte buf[]) {  
	    	StringBuffer sb = new StringBuffer();  
	    	for (int i = 0; i < buf.length; i++) {  
	    		String hex = Integer.toHexString(buf[i] & 0xFF);  
	    		if (hex.length() == 1) {  
	    			hex = '0' + hex;  
	    		}  
	    		sb.append(hex.toUpperCase());  
	    	}  
	    	return sb.toString();  
	    } 
	    
	    /**将16进制转换为二进制 
	     * @param hexStr 
	     * @return 
	     */  
	    private static byte[] parseHexStr2Byte(String hexStr) {  
	    	if (hexStr.length() < 1)  
	    		return null;  
	    	byte[] result = new byte[hexStr.length()/2];  
	    	for (int i = 0;i< hexStr.length()/2; i++) {  
	    		int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
	    		int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
	    		result[i] = (byte) (high * 16 + low);  
	    	}
	    	return result;  
	    }
	}
}
