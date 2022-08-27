package test;

import demo.CaesarUtil;

public class Test1 {
	public static void main(String[] args) {
		String str = "hello world";
		int key = 5;
		String codeStr = CaesarUtil.getCodeStr(str, key, CaesarUtil.EN_CRYPT);
		System.out.println("加密之后：" + codeStr);
		
		String deStr = CaesarUtil.getCodeStr(codeStr, key, CaesarUtil.DE_CRYPT);
		System.out.println("解密之后：" + deStr);
	}
}
