package test;

import util.MD5;

public class Test4 {
	public static void main(String[] args) {
		String str = "刘老师好帅！";
		String md5 = MD5.convert32(str);
		System.out.println("密文: " + md5);
		
	}
}
