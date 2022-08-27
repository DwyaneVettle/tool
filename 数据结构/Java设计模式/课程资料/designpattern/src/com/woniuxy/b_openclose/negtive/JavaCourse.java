package com.woniuxy.b_openclose.negtive;

class JavaCourse {

	private String name;
	private double price;
	private int hours;
	
	public JavaCourse(String name, double price, int hours) {
		this.name = name;
		this.price = price;
		this.hours = hours;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price * 0.8;  // 这里直接写死乘以0.8的逻辑，就违反了开闭原则，这是在修改已有功能！
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	
}
