package com.woniuxy.p_strategy.c;

/*
	针对于b包中的问题，Joe的解决之道是这样的：
	我可以把橡皮鸭类中的fly()方法覆盖掉，就好像覆盖quack()的做法一样
*/

abstract class Duck {
	public void quack() {
		System.out.println("呱呱叫");
	}
	public void swim() {
		System.out.println("游泳");
	}
	public void fly() {
		System.out.println("真的飞起来了!");
	}
	// 因为每一种鸭子的外观都不同，所以display（）方法是抽象的
	public abstract void display(); 
}

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

class RubberDuck extends Duck {
	// 橡皮鸭子不会呱呱叫，所以把quack()的定义覆盖成“吱吱叫”
	@Override
	public void quack() {
		System.out.println("吱吱叫"); 
	}
	@Override
	public void display() {
		System.out.println("外观是橡皮鸭");
	}
	// 橡皮鸭子不会飞，所以把fly()覆盖为什么事都不做，或者抛出一个UnsupportedOperationException
	@Override
	public void fly() {
		throw new UnsupportedOperationException("you can you up, no can no bb!");
	}
}

// 这样做，橡皮鸭就不会让人意外地飞起来了！ 如果客户端非要让一个橡皮鸭飞，只会收到一个运行时异常!

public class Test {
	public static void main(String[] args) {
		Duck duck = new MallardDuck();
		duck.quack();
		duck.swim();
		duck.fly();
		duck.display();
		
		System.out.println("=========================================");
		
		duck = new RedHeadDuck();
		duck.quack();
		duck.swim();
		duck.fly();
		duck.display();
		
		System.out.println("=========================================");
		
		duck = new RubberDuck();
		duck.quack();
		duck.swim();
		duck.fly(); // 就不要赶着鸭子上架子了！
		duck.display();
		
	}
}
/*
	可是，如果以后加入木鸭（WoodenDuck），又会如何？？
	木头假鸭，不会飞也不会叫......
*/

class WoodenDuck extends Duck {
	@Override
	public void quack() {
		// 覆盖，变成什么事都不做
	}
	@Override
	public void display() {
		System.out.println("外观是木鸭");
	}
	@Override
	public void fly() {
		// 覆盖，变成什么事都不做
	}
}


// 利用继承来提供Duck的行为，会导致:
// 1. 很难知道所有鸭子的全部行为。
// 2. 改变会牵一发而动全身，造成其他鸭子不想要的改变。

// 更糟糕的是，每当有新鸭子子类出现，Joe就要被迫检查并可能覆盖fly()或quack()方法...
// 这简直是无穷无尽的噩梦!