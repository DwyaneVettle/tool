package com.woniuxy.n_template.c;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

abstract class Template {
	
	private String name;
	
	public Template(String name) {
		this.name = name;
	}
	
	public boolean bb() {
		return true;
	}

	// 模板方法
	public void template() {
		System.out.println(name + "测试开始");
		long start = System.currentTimeMillis();
		code();
		long end = System.currentTimeMillis();
		System.out.println(name+"测试持续时间：" + (end - start));
		
		if(bb()) {
			System.out.println("测试时间：" + new Date() + "*************************");
		}
	}
	
	
	public abstract void code();
}

// ===================================================================

class A extends Template {
	private List list;
	public A(List list) {
		super(list.getClass().getName() + "增加");
		this.list = list;
	}
	@Override
	public void code() {
		for (int i = 1; i <= 100000; i++) {
			list.add(0,"a");
		}
	}
	
	@Override
	public boolean bb() {
		return false;
	}
	
}

class B extends Template {
	private List list;
	public B(List list) {
		super(list.getClass().getName() + "查询");
		this.list = list;
	}
	@Override
	public void code() {
		for (int i = 1; i <= 100000; i++) {
			list.add(0,"a");
		}
		for(int i = 1; i <= 100000; i++) {
			list.get(50000);
		}
	}
}

public class Test {
	public static void main(String[] args) {
		Template[] ts = new Template[] {
				new A(new ArrayList<>()),
				new A(new LinkedList<>()),
				new B(new ArrayList<>()),
				new B(new LinkedList<>()),
				new A(new Vector<>()),
		};
		
		for (Template t : ts) {
			t.template();
			System.out.println("============================");
		}
	}
}

/*
	在模板模式（Template Pattern）中，
	一个抽象类公开定义了执行它的方法的方式/模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。
*/
