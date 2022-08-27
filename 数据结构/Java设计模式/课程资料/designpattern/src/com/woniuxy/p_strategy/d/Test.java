package com.woniuxy.p_strategy.d;

// 针对与c包中的问题，我们需要一个更清晰的方法，让“某些”（而不是全部）鸭子类型可飞或可叫。
/*
 	
 	通过前面的学习，我们知道：把主要功能定义在父类中，把扩展功能定义在接口中。
 	
 	
 	从目前这个状况来看，“飞”和“呱呱叫”成为了鸭子的扩展功能，因为并不是所有的鸭子都会“飞”和“呱呱叫”

 	
  
	Joe的解决之道是：
	我可以把fly()从超类中取出来，放进一个“Flyable接口”中。这么一来，只有会飞的鸭子才实现此接口。
	同样的方式，也可以用来设计一个“Quackable”接口，因为不是所有的鸭子都会叫。
*/
abstract class Duck {
	public void swim() {
		System.out.println("游泳");
	}
	public abstract void display();
}

interface Flyable {
	public void fly();
}

interface Quackable {
	public void quack();
}

class MallardDuck extends Duck implements Flyable, Quackable {
	@Override
	public void quack() {
		System.out.println("嘎嘎叫");
	}
	@Override
	public void fly() {
		System.out.println("飞起来了");
	}
	@Override
	public void display() {
		System.out.println("外观是绿头鸭");
	}
}

class RedheadDuck extends Duck implements Flyable, Quackable  {
	@Override
	public void quack() {
		System.out.println("嘎嘎叫");
	}
	@Override
	public void fly() {
		System.out.println("飞起来了");
	}
	@Override
	public void display() {
		System.out.println("外观是红头鸭");
	}
}

class RubberDuck extends Duck implements Quackable {
	@Override
	public void quack() {
		System.out.println("嘎嘎叫");
	}
	@Override
	public void display() {
		System.out.println("外观是橡皮鸭");
	}
}

class WoodenDuck extends Duck {
	@Override
	public void display() {
		System.out.println("外观是木头假鸭");
	}
}

public class Test {
	public static void main(String[] args) {
		
	}
}

// 我们知道，并非“所有”的子类都具有飞行和呱呱叫的行为，所以继承并不是适当的解决方式。
// 虽然Flyable与Quackable可以解决“一部分”问题（不会再有会飞的橡皮鸭），但是
// 却造成代码无法复用（fly和quack），这只能算是从一个恶梦跳进另一个恶梦。
// 甚至，在会飞的鸭子中，飞行的动作可能还有多种变化.....

// 也许你会说，自jdk1.8开始，接口有默认实现。
// 那么对于48种鸭子的子类，共有12种飞行的方式，又该怎么说？ 接口的默认实现选择哪一种飞行方式都不行！