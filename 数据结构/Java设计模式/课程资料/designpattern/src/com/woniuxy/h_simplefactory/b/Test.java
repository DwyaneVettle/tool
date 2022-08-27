package com.woniuxy.h_simplefactory.b;

/*
 针对于a包的缺点， 重构代码如下：
 1. 添加了一个简单工厂： 根据参数的不同，而返回不同的具体产品。
 2. 优点是：客户端再也不需要知道，具体产品的细节了（类名）
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

// public
// Food工厂
class FoodFactory {
	public static Food getFood(int a) {
		Food food = null;
		switch (a) {
			case 1:
				food = new Hamburger();
				break;
			case 2:
				food = new FrenchFries();
				break;
			default:
				break;
		}
		return food;
	}
}

// ==================================================================


// 凉皮
class LP implements Food {
	@Override
	public void eat() {
		System.out.println("秦镇米皮");
	}
}


public class Test {
	public static void main(String[] args) {
		Food f = FoodFactory.getFood(1);
		f.eat();
		
		
		f= FoodFactory.getFood(2);
		f.eat();
		
	}
}

// 缺点：
// 1. 从某种程度上违反单一职责。
// 2. 如果具体产品特别多，则简单工厂的代码将会十分臃肿。 （这也是有人把简单工厂叫做“上帝类”的原因。）
// 3. 增加了客户端代码的复杂度，客户端程序员，必须知道每个数字，都分别映射什么具体产品
// 4. 最关键的缺点：现在，客户端要添加新产品。咋办？ 客户端倒是能自己扩展一个新产品，但是为了把新产品加入简单工厂中，势必要范围“开闭原则”。

// 由此，就引出了下一个设计模式： 工厂方法设计模式