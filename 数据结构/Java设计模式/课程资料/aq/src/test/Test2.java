package test;

import demo.CaesarUtil;
import demo.FenceUtil;

public class Test2 {
	public static void main(String[] args) {
		String str = "woniuxy";
		int key = 5;
		
		String enCodeStr = FenceUtil.enCodeStr(str, key);
		System.out.println("加密之后：" + enCodeStr);
		
		String deCodeStr = FenceUtil.deCodeStr(enCodeStr, key);
		System.out.println("解密之后：" + deCodeStr);
		
		
	}
}
