package com.woniuxy.m_decorator.d;

import java.io.IOException;

// 为了解决c包的问题，

abstract class Beverage {
	
	private String description;

	public Beverage(String description) {
		super();
		this.description = description;
	}
	public abstract double cost();
	
	public String getDescription() {
		return description;
	}
 }


class DarkRoast extends Beverage {

	public DarkRoast(String descriptoin) {
		super(descriptoin);
	}
	
	public double cost() {
		return  10;
	}
}

class Decaf extends Beverage {

	public Decaf(String description) {
		super(description);
	}

	@Override
	public double cost() {
		return  8;
	}
}

class Espresso extends Beverage {

	public Espresso(String description) {
		super(description);
	}

	@Override
	public double cost() {
		return 12;
	}
	
}

class HouseBlend extends Beverage {

	public HouseBlend(String description) {
		super(description);
	}

	@Override
	public double cost() {
		return  15;
	}
	
}


abstract class CondimentDecorator extends Beverage {

	public CondimentDecorator() {
		super("调料");
	}
	
	public abstract double cost();
	
}

class Milk extends CondimentDecorator {

	private Beverage beverage;
	
	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		return beverage.cost() + 0.2;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + " 牛奶";
	}
	
}

class Mocha extends CondimentDecorator{
	private Beverage beverage;
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		return beverage.cost() + 0.3;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + " 摩卡";
	}
	
}

class Soy extends CondimentDecorator {
	private Beverage beverage;
	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		return beverage.cost() + 0.2;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + " 豆浆";
	}
	
}

class Whip extends CondimentDecorator {
	private Beverage beverage;
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		return beverage.cost() + 0.1;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + " 奶泡";
	}
	
}

// =========================================================


class GouQi extends CondimentDecorator {

	private Beverage beverage;
	
	public GouQi(Beverage beverage) {
		super();
		this.beverage = beverage;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription() + " 枸杞";
	}

	@Override
	public double cost() {
		return beverage.cost() + 0.4;
	}
	
}

public class Test {

	public static void main(String[] args) throws IOException {
		
		Beverage b = new DarkRoast("焦炒咖啡");
		
		Beverage b2 = new Milk(b);
		
		Beverage b3 = new Soy(b2);
		
		Beverage b4 = new Whip(b3);
		
		Beverage b5 = new Milk(b4);
		
		Beverage b6 = new GouQi(b5);
		
		System.out.println(b6.getDescription() + ":" +  b6.cost());
		
		
	}
}

