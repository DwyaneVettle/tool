package com.woniuxy.m_decorator.c;


// 为了解决b包的问题，
// 作者，尝试这样来解决：


// 优点，没有类爆炸！

 class Beverage {
	
	private String description;
	
	private boolean steamedMilk;
	private boolean soy;
	private boolean mocha;
	private boolean whip;

	public Beverage(String description) {
		super();
		this.description = description;
	}
	public  double cost() {
		
		double cost = 0;
		
		if(steamedMilk) {
			cost += 0.2;
		}
		if(soy) {
			cost += 0.2;
		}
		if(mocha) {
			cost += 0.3;
		}
		if(whip) {
			cost += 0.1;
		}
		
		return cost;
	}
	
	/*
	 public  double cost(Map map) {
	 	double cost = 0;
	 	for(Entry e : map.entrySet() ) {
	 		cost += e.getValue();
	 	}
	 	return cost;
	 }
	 
	 public String description(Map map) {
	 		String str = 0;
		 	for(Entry e : map.entrySet() ) {
		 		str += e.getKey() + " ";
		 	}
	 		return str;
	 }
	 
	 
	 Map map = new HashMap();
	 map.put("Milk", 0.2);
	 map.put("Soy", 0.2);
	 map.put("Whip", 0.1);
	 map.put("GouqI", 0.1);
	 
	 cost(map);
	 
	 getDescription(map);
	 
	 
	*/
	
	
	public boolean isSteamedMilk() {
		return steamedMilk;
	}
	public void setSteamedMilk(boolean steamedMilk) {
		this.steamedMilk = steamedMilk;
	}
	public boolean isSoy() {
		return soy;
	}
	public void setSoy(boolean soy) {
		this.soy = soy;
	}
	public boolean isMocha() {
		return mocha;
	}
	public void setMocha(boolean mocha) {
		this.mocha = mocha;
	}
	public boolean isWhip() {
		return whip;
	}
	public void setWhip(boolean whip) {
		this.whip = whip;
	}
	
	
	
}


class DarkRoast extends Beverage {

	public DarkRoast(String descriptoin) {
		super(descriptoin);
	}
	
	public double cost() {
		return super.cost() + 10;
	}
	
	/*
	 
	 public String getDescription(Map map) {
	 	return descriptoin + super.getDesciption(Map map);
	 }
	 
	 */
}

class Decaf extends Beverage {

	public Decaf(String description) {
		super(description);
	}

	@Override
	public double cost() {
		return super.cost() + 8;
	}
	
}

class Espresso extends Beverage {

	public Espresso(String description) {
		super(description);
	}

	@Override
	public double cost() {
		return super.cost() + 12;
	}
	
}

class HouseBlend extends Beverage {

	public HouseBlend(String description) {
		super(description);
	}

	@Override
	public double cost() {
		return super.cost() + 15;
	}
	
}

// =========================================================

class Tea extends Beverage {

	public Tea(String description) {
		super(description);
	}
	
	public double cost() {
		return super.cost() + 9;
	}
	
}

public class Test {

	public static void main(String[] args) {
		DarkRoast dr = new DarkRoast("焦炒咖啡");
		dr.setMocha(true);
		dr.setSoy(true);
		System.out.println(dr.cost());
		
		HouseBlend hb = new HouseBlend("混合咖啡");
		hb.setWhip(true);
		System.out.println(hb.cost());
		
		
		Tea tea = new Tea("凉茶");
		tea.setSoy(true);
		System.out.println(tea.cost());
	}

}

// 缺点：
// 现在多了一个调料：枸杞。