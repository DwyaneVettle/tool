package com.woniuxy.g_liskvo.postive;

// 要解决反例中的问题，就必须解除Square和Rectangle的继承关系
// 因为在这样的业务场景之下，不能把Square当做一个Rectangle类型！

// 也就是说，这里Square 和 Rectangle 没有 “is a”关系！

interface Quadrangle {
	double getWidth();
	double getLength();
}

class Rectangle implements Quadrangle {
	private double width;
	private double length;
	public double getWidth() {
		return width;
	}
	public double getLength() {
		return length;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public void setLength(double length) {
		this.length = length;
	}
}
class Square implements Quadrangle {
	private double sideLength;

	@Override
	public double getWidth() {
		return sideLength;
	}

	@Override
	public double getLength() {
		return sideLength;
	}
	
	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
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
		Square s = new Square();
		Rectangle r = new Rectangle();
		tranfrom(r);
	}
}

// 如此违反了里氏替换原则。
