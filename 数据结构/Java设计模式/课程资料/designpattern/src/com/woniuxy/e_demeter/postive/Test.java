package com.woniuxy.e_demeter.postive;

// 迪米特法则（最少知道原则）： 一个类，对于其它类，应该知道的越少越好！ (封装)

// 如果用户知道的太多就会
// 1. 操作起来很麻烦，使用难度增加！
// 2. 容易出错。

// 补充：只和朋友通信：
// 一个方法中，参数、返回值都是朋友，也就是说，我们可以随意调用参数对象的方法。
// 如果参数对象的某一个方法，又返回另一个对象，则对于这个对象，最好不要调用它的任何方法！


// 用户
public class Test {
	public static void main(String[] args) {
		Computer c = new Computer();
		c.shutdown();
	}
}
