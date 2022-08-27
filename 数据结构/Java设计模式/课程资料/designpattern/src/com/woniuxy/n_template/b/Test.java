package com.woniuxy.n_template.b;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

abstract class Template {
	
	private String name;
	
	public Template(String name) {
		this.name = name;
	}

	// 模板方法
	public void template() {
		System.out.println(name + "测试开始");
		long start = System.currentTimeMillis();
		code();
		long end = System.currentTimeMillis();
		System.out.println(name+"测试时间：" + (end - start));
	}
	
	
	public abstract void code();
}

// ===================================================================

class A extends Template {
	public A() {
		super("ArrayList增加");
	}
	@Override
	public void code() {
		List list = new ArrayList();
		for (int i = 1; i <= 100000; i++) {
			list.add(0,"a");
		}
	}
}

class B extends Template {
	public B () {
		super("ArrayList查询");
	}

	@Override
	public void code() {
		List list = new ArrayList();
		for (int i = 1; i <= 100000; i++) {
			list.add("a");
		}
		for(int i = 1; i <= 100000; i++) {
			list.get(50000);
		}
	}
}


class C extends Template {

	public C() {
		super("LinkedList增加");
	}
	@Override
	public void code() {
		List list = new LinkedList();
		for (int i = 1; i <= 100000; i++) {
			list.add(0,"a");
		}
	}
	
}

class D extends Template {

	public D() {
		super("LinkedList查询");
	}
	@Override
	public void code() {
		List list = new LinkedList();
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
		Template t = new A();
		Template t2 = new B();
		Template t3 = new C();
		Template t4 = new D();
		t.template();
		t2.template();
		t3.template();
		t4.template();
	}
}
