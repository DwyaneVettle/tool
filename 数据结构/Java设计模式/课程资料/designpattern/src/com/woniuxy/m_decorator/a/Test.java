package com.woniuxy.m_decorator.a;

/*
  星巴克是卖咖啡的， 一开始饮料种类比较少，所以使用继承，看不出什么问题。

  每种咖啡，都有描述，都有价格。 这是所有咖啡的共性，既然是共性，就要上提到父类中。
 
*/

abstract class Beverage {
	
	private String description;

	public Beverage(String description) {
		super();
		this.description = description;
	}
	
	public abstract double cost(); 
}


class DarkRoast extends Beverage {

	public DarkRoast(String descriptoin) {
		super(descriptoin);
	}
	
	public double cost() {
		return 10;
	}
}

class Decaf extends Beverage {

	public Decaf(String description) {
		super(description);
	}

	@Override
	public double cost() {
		return 8;
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
		return 15;
	}
	
}

public class Test {

}
