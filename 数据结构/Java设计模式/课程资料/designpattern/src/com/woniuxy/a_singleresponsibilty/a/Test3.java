package com.woniuxy.a_singleresponsibilty.a;

public class Test3 {
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
				// 现在，要添加一个功能：保证除法运算时，检测除数书否为0，如果为0，就...
				if(num2 == 0) {
					return;
				}
				r = num1 / num2;
				break;
			default:
				break;
		}
		
		System.out.println("结果是：" + r);
		
	}
}
// 这样做的缺点是： 因为要修改的功能，和其他已经正常运行的功能在一起，所以有一种风险，在修改某个功能的时候，有几率把已经做好的功能该坏！！

