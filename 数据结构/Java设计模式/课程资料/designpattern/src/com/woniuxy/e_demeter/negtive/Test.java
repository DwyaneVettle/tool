package com.woniuxy.e_demeter.negtive;

// 迪米特法则（最少知道原则）： 一个类，对于其它类，应该知道的越少越好！ (封装)

// 如果用户知道的太多就会
// 1. 操作起来很麻烦，使用难度增加！
// 2. 容易出错。

// 用户
public class Test {
	public static void main(String[] args) {
		Computer c = new Computer();
		
		c.saveCrruentTask();
		c.logout();
		c.shutdownScreen();
		c.shutdownPower();
	}
}
