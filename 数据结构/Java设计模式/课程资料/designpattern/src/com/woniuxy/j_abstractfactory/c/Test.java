package com.woniuxy.j_abstractfactory.c;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
	public  Factory getFactory( ) {
		Factory f = null;
		try {
			InputStream in = SimpleFactory.class.getResourceAsStream("aa.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			String className = prop.getProperty("factory");
			System.out.println(className + "!!!!");
			f = (Factory) Class.forName(className).newInstance();
			
			prop.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}

// ========================================================

public class Test {
	public static void main(String[] args) {
		while(true) {
			Business b = new Business();
			b.taste(new SimpleFactory().getFactory());
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}



