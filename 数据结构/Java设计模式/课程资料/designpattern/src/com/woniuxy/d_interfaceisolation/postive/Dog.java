package com.woniuxy.d_interfaceisolation.postive;

class Dog implements Animal,Swimable, Runnable {

	@Override
	public void swim() {
	}

	@Override
	public void eat() {
	}

	@Override
	public void run() {
	}
}

class Fish implements Animal, Swimable {

	@Override
	public void swim() {
	}

	@Override
	public void eat() {
	}
	
}
