package com.woniuxy.a_singleresponsibilty.b;

// 按照这个设计，此时我们要给除法添加判断，只需要修改DivCalc即可，其他的功能不会收到影响!

public class Test4 {
	public static void main(String[] args) {
		// 从键盘输入2个数字，再输入一个运算符，然后计算结果
		double num1 = 100;
		double num2 = 0;
		String oper = "/";
		
		double r = 0;
		
		Calc c = null;
		
		switch (oper) {
			case "+":
				c = new AddCalc();
				break;
			case "-":
				c = new SubCalc();
				break;
			case "*":
				c = new MulCalc();
				break;
			case "/":
				c = new DivCalc();
				break;
			default:
				break;
		}
		
		r = c.calc(num1, num2);
		
		System.out.println("结果是：" + r);
		
	}
}

