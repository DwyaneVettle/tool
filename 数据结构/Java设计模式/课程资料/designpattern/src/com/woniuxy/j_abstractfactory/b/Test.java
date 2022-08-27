package com.woniuxy.j_abstractfactory.b;

// 针对于a包所提出的问题，重构代码如下:
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


class SimpleFactory {
	private SimpleFactory() {
	}
	
	public static Factory getFactory(int n) {
		Factory f = null;
		switch (n) {
			case 1:
				f = new KFCFactory();
				break;
			case 2:
				f = new SanQinFactory();
				break;
		}
		return f;
	}
}

// ========================================================


public class Test {
	public static void main(String[] args) {
		Business b = new Business();
		b.taste(SimpleFactory.getFactory(1));
		b.taste(SimpleFactory.getFactory(2));
	}
}

// 我们很容易发现，又回到了原点！！！ 


