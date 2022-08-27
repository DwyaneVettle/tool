package com.woniuxy.a_singleresponsibilty.a;

public class Test2 {
	public static void main(String[] args) {
		// 从键盘输入2个数字，再输入一个运算符，然后计算结果
		double num1 = 1;
		double num2 = 2;
		String oper = "+";
		
		double r = 0;
		
		switch (oper) {
			case "+":
				r = num1 + num2;
				break;
			case "-":
				r = num1 - num2;
				break;
			case "*":
				r = num1 * num2;
				break;
			case "/":
				r = num1 / num2;
				break;
			default:
				break;
		}
		
		System.out.println("结果是：" + r);
		
	}
}
