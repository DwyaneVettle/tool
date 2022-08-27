package com.woniuxy.j_abstractfactory.a;

// 抽象工厂： 
// 1.减少了工厂方法类，面对产品簇时，类爆炸的反面效果。
// 2.抽象工厂生成的产品簇，就是一系列产品，这些产品之间有逻辑（内在）关系！
// 3.现在想多增加一个产品簇， 表现的很好，很让人满意！

interface Food {
	public void eat();
}

interface Drink {
	public void drink();
}

interface Factory {
	Food getFood();
	Drink getDrink();
}

class Hamburger implements Food {
	@Override
	public void eat() {
		System.out.println("汉堡包");
	}
}
class LiangPi implements Food {
	@Override
	public void eat() {
		System.out.println("凉皮！！");
	}
}

class Cola implements Drink {
	@Override
	public void drink() {
		System.out.println("可乐，你值得拥有!");
	}
}
class IcePeek implements Drink {
	@Override
	public void drink() {
		System.out.println("冰峰，从小就喝它");
	}
}

class KFCFactory implements Factory {

	@Override
	public Food getFood() {
		return new Hamburger();
	}

	@Override
	public Drink getDrink() {
		return new Cola();
	}
}

class SanQinFactory implements Factory {

	@Override
	public Food getFood() {
		return new LiangPi();
	}

	@Override
	public Drink getDrink() {
		return new IcePeek();
	}
}


class Business {
	public void taste(Factory factory) {
		Food food = factory.getFood();
		Drink drink = factory.getDrink();
		
		System.out.println("评委1：开吃");
		food.eat();
		drink.drink();
		System.out.println("评委1：确实很难吃...");
		
		
		System.out.println("评委2：开吃");
		food.eat();
		drink.drink();
		System.out.println("评委2：难吃太太...");
		
		System.out.println("评委3：开吃");
		food.eat();
		drink.drink();
		System.out.println("评委3：难吃到家了...");
	}
}

// ========================================================


public class Test {
	public static void main(String[] args) {
		Business b = new Business();
		b.taste(new KFCFactory());
		b.taste(new SanQinFactory());
	}
}

// 缺点：
// 你试试，加一个产品等级, 就会“牵一发 而动全局”。 说明抽象工厂不适用于产品等级经常变化的情况。
// 如果产品簇中的产品等价是稳定的，则使用抽象工厂才比较何时。反之，还不如使用工厂方法来的更灵活。

// 换一个角度来分析以上代码：
// 我们都知道，产品就是new出来的对象， 抽象产品就是接口、抽象类。
// 那么看看现在main方法中，new了谁？？ new KFCFactory() ， new SanQinFactory()  这2个工厂也可以看做是产品
// 既然是产品，就应该和客户端解耦，以防产品类名变化，倒置客户端奔溃！
