package com.woniuxy.d_interfaceisolation.negtive;

public class Dog implements Animal {

	@Override
	public void eat() {
		System.out.println("π∑≥‘π«Õ∑");
	}

	@Override
	public void run() {
		System.out.println("Œ“≈‹");
	}

	@Override
	public void fly() {
		throw new UnsupportedOperationException("π÷Œ“ø©!");
	}

	@Override
	public void siwm() {
		System.out.println("π∑≈Ÿ");
	}

}
