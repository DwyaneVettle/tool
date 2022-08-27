package com.woniuxy.b_openclose.postive;

public class Test {
	public static void main(String[] args) {
		JavaCourse jc = new DiscountJavaCourse("java se", 300, 50);
		System.out.println(jc.getPrice());
	}
}


