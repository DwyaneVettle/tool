package com.woniuxy.i_factorymethod.a;

// 为了解决简单工厂的缺点，我们重构代码：

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

interface FoodFactory {
	Food getFood();
}

class HamburgerFactory implements FoodFactory {
	@Override
	public Food getFood() {
		return new Hamburger();
	}
}
class FrenchFriesFactory implements FoodFactory {
	@Override
	public Food getFood() {
		return new FrenchFries();
	}
}

class Business {
	// 品尝
	public void taste(FoodFactory factory) {
		Food f = factory.getFood();
		System.out.println("评委一：");
		f.eat();
		System.out.println("评唯一打分");
		
		
		System.out.println("评委二：");
		f.eat();
		System.out.println("评唯二打分");
		
		
		System.out.println("评委三：");
		f.eat();
		System.out.println("评唯三打分");
	}
}

//===========================================================

class MexicoChicken implements Food {
	@Override
	public void eat() {
		System.out.println("墨西哥鸡肉卷");
	}
}

class MexicoChickenFactory implements FoodFactory {

	@Override
	public Food getFood() {
		return new MexicoChicken();
	}
	
}


public class Test {

	public static void main(String[] args) {
		FoodFactory ff = new FrenchFriesFactory();
		FoodFactory ff2 = new MexicoChickenFactory();
		
		Business b = new Business();
		
		b.taste(ff);
		b.taste(ff2);
		
		
	}

}

// 有疑惑：
// 1. 既然，客户端程序员，已经能扩展出新产品了，新产品是客户端自己做的，客户端程序员就可以直接使用自己的新产品类，而不用定义那个对应的工厂！
// 2. 那为什么我们还要“不辞辛苦”地去加对应的工厂呢？？？？
// 3. 那么，那个Business的taste方法，直接接受Food即可，这样，就不要工厂了.

// 好吧，工厂方法也有缺点：
// 1. 增加了复杂度
// 2. 当有多种不同类型的产品时（产品等级） ，比如，现在加入饮料 ， 会造成类特别多

// 3. 由此就引出了：抽象工厂设计模式， 下回分解

