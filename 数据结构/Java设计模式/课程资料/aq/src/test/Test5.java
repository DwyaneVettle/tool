package test;

import util.Security;

public class Test5 {
	public static void main(String[] args) {
		String str = "好好学习，努力成为一个码农";
		//加密
		String encrypt = Security.AES.encrypt(str, Security.AES.DEFAULT_KEY);
		System.out.println("密文：" + encrypt);
		
		String decrypt = Security.AES.decrypt("62BB6D7E2563984918AF4B1344C5C9698067B4747FDD0FA10D638128192F691AEA548ABCD619C714B2E9707E10E994C0", Security.AES.DEFAULT_KEY);
		System.out.println("原文：" + decrypt);
		
		
	}
}
