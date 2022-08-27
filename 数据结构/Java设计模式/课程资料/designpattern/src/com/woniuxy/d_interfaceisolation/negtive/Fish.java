package com.woniuxy.d_interfaceisolation.negtive;

public class Fish implements Animal {

	@Override
	public void eat() {
		System.out.println("大鱼吃小鱼，小鱼吃虾米，虾米吃海草");
	}

	@Override
	public void run() {
		System.out.println("tell me how?? 你来");
	}

	@Override
	public void fly() {
		System.out.println("how....");
	}

	@Override
	public void siwm() {
		System.out.println("of course, i can siwm");
	}

}
