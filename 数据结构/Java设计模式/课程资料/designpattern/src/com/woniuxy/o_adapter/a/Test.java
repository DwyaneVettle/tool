package com.woniuxy.o_adapter.a;

class Calc {
	public int add(int a, int b) {
		return a + b;
	}
}

// ===================================================

// 需求，客户端想要计算3个数字的和，客户端想给add方法，传入3个参数
class CalcAdapter {
	private Calc c = new Calc();

	public CalcAdapter() {
	}
	
	public int add(int x, int y, int z) {
		return c.add(c.add(x,y), z);
	}
}

public class Test {
	public static void main(String[] args) {
		
		CalcAdapter ca = new CalcAdapter();
		
		int r = ca.add(1, 2, 3);
		
		System.out.println(r);
		
		
	}
}
