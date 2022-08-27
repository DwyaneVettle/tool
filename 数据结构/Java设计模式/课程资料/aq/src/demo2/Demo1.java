package demo2;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

public class Demo1 {
	public static void main(String[] args) throws UnsupportedEncodingException {
//		String str = "学";
//		System.out.println(Arrays.toString( str.getBytes() ));
		byte[] byts = new byte[3];
		byts[0] = -29;
		byts[1] = -85;
		byts[2] = -50;
		
		String str2 = new String(byts, "UTF-8");
		System.out.println(str2);
		
		//base64对不可见数据的加密
		byte[] encode = Base64.getEncoder().encode(byts);
		String strMW = new String(encode); 
		System.out.println(strMW);
		
		//解密
		byte[] decode = Base64.getDecoder().decode(encode);
		System.out.println(Arrays.toString(decode));
		
		
		
	}
}
