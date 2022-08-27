package com.woniuxy.p_strategy.b;

/*
	现在有个需求：
	主管们决定，此模拟程序需要会飞的鸭子来将竞争者抛在后头。当然，在这个时候，Joe的经理拍胸脯告诉主管们，
	Joe只需要一个星期就可以搞定。“毕竟，Joe是一个OO程序员......这有什么困难？？”
	
	Joe认为，只需要在Duck类中加上fly()方法，然后所有鸭子都会继承fly()。 “这是我大显身手，展示OO才华的时候了！”
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

// 问题似乎解决了， 但真的是这样吗?
// Joe，没有想到，在众多的Duck子类中，有了一个RubberDuck（橡皮鸭）
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
}
// Joe忽略了一件事，并非Duck的所有子类都会飞。Joe在Duck超类中加上新的行为，
// 会使得某些并不适合该行为的子类也具有该行为。现在可好了！SimUDuck程序中有了
// 一个无生命的会飞的东西。

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
		duck.fly(); // 橡皮鸭飞起来了！？
		duck.display();
		
	}
}
// 我们发现一件事： 一旦设计“维护”时，为了“复用”目的而使用继承，结局并不完美。


