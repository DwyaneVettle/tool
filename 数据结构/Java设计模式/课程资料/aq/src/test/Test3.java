package test;

import demo.VirginiaUtil;

public class Test3 {
	public static void main(String[] args) {
		String str = "Hello world";
		String key = "woniu";
		
		String enCodeStr = VirginiaUtil.enCodeStr(str, key);
		System.out.println("加密之后：" + enCodeStr);
		
		String deCodeStr = VirginiaUtil.deCodeStr(enCodeStr, key);
		System.out.println("解密之后：" + deCodeStr);
		
	}
}
