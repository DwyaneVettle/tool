package com.woniuxy.b_openclose.postive;

// зїеп
class DiscountJavaCourse extends JavaCourse {

	public DiscountJavaCourse(String name, double price, int hours) {
		super(name, price, hours);
	}

	@Override
	public double getPrice() {
		return super.getPrice() * 0.8;
	}
}
