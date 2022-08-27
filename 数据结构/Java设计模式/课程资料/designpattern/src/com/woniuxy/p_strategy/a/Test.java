package com.woniuxy.p_strategy.a;

/*
 Joe上班的公司做了一套相当成功的模拟鸭子游戏：SimUDuck。游戏中会出现各种鸭子，一边游泳戏水，一边呱呱叫。
 此系统的内部设计使用了标准的OO技术，设计了一个鸭子超类，并让各种鸭子继承此超类
*/

abstract class Duck {
	public void quack() {
		System.out.println("呱呱叫");
	}
	public void swim() {
		System.out.println("游泳");
	}
	// 因为每一种鸭子的外观都不同，所以display（）方法是抽象的
	public abstract void display(); 
}

// 野鸭
class MallardDuck extends Duck {
	@Override
	public void display() {
		System.out.println("外观是绿头");
	}
}
class RedHeadDuck extends Duck {
	@Override
	public void display() {
		System.out.println("外观是红头");
	}
}


public class Test {
	public static void main(String[] args) {
		Duck duck = new MallardDuck();
		duck.quack();
		duck.swim();
		duck.display();
		
		System.out.println("=========================================");
		
		duck = new RedHeadDuck();
		duck.quack();
		duck.swim();
		duck.display();
	}
}
