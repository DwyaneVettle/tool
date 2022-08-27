package com.woniuxy.a_singleresponsibilty.a;

/*
 设计模式不是新技术，没有新api，没有的新语法点，设计模式是前人不断总结、优化、迭代出来的一系列方法
这些方法，对应于不同场景，应该选择不同的模式！！
没有任何一种设计模式，能达到适用于所有场景的效果！！

设计模式是权衡，是平衡，不能尽善尽美，只能在解决特定场景的问题！！

23种设计模式。

23中设计模式，总共有7种设计原则：
1. 单一职责
2. 迪米特法则（最少知道）
3. 里氏替换原则 
4. 依赖倒置原则
5. 开闭原则
6. 接口隔离原则
7. 组合优于继承
*/

// 单一职责：
//	一个方法，应该只完成一项功能。 登录
//	一个类，应该只完成一项功能。   维护用户（增删改查）
//  一个框架，应该只完成一项功能。 struts2框架简化了MVC操作， hibernate简化了dao层开发
//  一个应用，应该只注重一个领域。 

public class Test {
	public static int getRangeNumber(int a, int b) {
		if(a < b) {
			int t = a;
			a = b;
			b = t;
		}
		int r = (int) (Math.random() * (a - b + 1)+ b);
		// 99表 如果加上99表的输出在这里，就违反了单一职责
		// 应该把打印99表的功能，单独封装在另外一个方法中！
		System.out.println("99表");
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println(getRangeNumber(12, 20));
	}
}
