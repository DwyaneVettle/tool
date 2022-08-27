package com.woniuxy.h_simplefactory.a;

/*
 工厂模式相关概念：
 1. 产品: new 出的对象，都是产品。
 2. 抽象产品: 抽象类、接口，都称之为抽象产品。
 3. 产品簇:   多个抽象产品组成一个产品系列。
 4. 产品等级:  产品簇中的每一个抽象产品都是产品等级。
*/

interface Food {
	public void eat();
}

class Hamburger implements Food {
	@Override
	public void eat() {
		System.out.println("汉堡包");
	}
}
class FrenchFries implements Food {
	@Override
	public void eat() {
		System.out.println("薯条搭配番茄酱，更配哦！");
	}
}

// ==================================================================

public class Test {
	public static void main(String[] args) {
		Food f = new Hamburger();
		f.eat();
		Food f2 = new FrenchFries();
		f2.eat();
	}
}

// 以上代码， 客户端除了要知道，接口的类型，还必须知道接口的实现类的类型，失败！！
// 这样做的缺点是：当服务端代码在新的版本中把具体类的类名修改了，则客户端代码直接“崩塌”！！ 失败中的失败！！















