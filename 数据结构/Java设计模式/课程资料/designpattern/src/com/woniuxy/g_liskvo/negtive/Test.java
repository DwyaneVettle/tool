package com.woniuxy.g_liskvo.negtive;
class Rectangle {
	private double width;
	private double length;
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
}
class Square extends Rectangle {
	private double sideLength;
	@Override
	public void setWidth(double width) {
		this.sideLength = width;
	}
	@Override
	public void setLength(double length) {
		this.sideLength = length;
	}
	@Override
	public double getWidth() {
		return sideLength;
	}
	@Override
	public double getLength() {
		return sideLength;
	}
}
// 需求，制作一个方法，接受一个长方形，来修改长方形的宽和长， 
// 只要发现宽比长小，就让宽累加1，知道宽刚好超过长为止。
public class Test {
	public static void tranfrom(Rectangle r) {
		while(r.getWidth() <= r.getLength()) {
			r.setWidth(r.getWidth() + 1);
			System.out.println("width:" + r.getWidth() + ", " + r.getLength());
		}
	}
	public static void main(String[] args) {
		Rectangle r = new Square();
		r.setWidth(20);
		r.setLength(30);
		tranfrom(r);
	}
}

// 如此违反了里氏替换原则。
