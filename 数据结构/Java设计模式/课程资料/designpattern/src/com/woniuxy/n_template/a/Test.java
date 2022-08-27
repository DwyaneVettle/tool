package com.woniuxy.n_template.a;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 	比较2个集合的增删、查询效率。
 	
 	ArrayList: 任意查询快 ， 增删慢 。
 	底层：数组， 数组元素在内存中的存储特点是连续存放。
 	恰恰是因为连续存放，所以任意查询快 ， 增删慢 。
 	
 	LinkedList: 任意查询慢， 增删快。
 	底层：双向链表，链表元素在内存中的存储特点是不连续存放。
 	恰恰是因为不连续存放，所以任意查询慢 ， 增删快 。
*/

public class Test {
	public static void main(String[] args) {
		
		System.out.println("ArrayList增加测试开始");
		long start = System.currentTimeMillis();
		List list = new ArrayList();
		for (int i = 1; i <= 1000000; i++) {
			list.add(0,"a");
		}
		long end = System.currentTimeMillis();
		System.out.println("ArrayList增加运行时间:" + (end - start));
		
		System.out.println("ArrayList查询测试开始");
		long start2 = System.currentTimeMillis();
		List list2 = new ArrayList();
		for (int i = 1; i <= 1000000; i++) {
			list2.add("a");
		}
		for(int i = 1; i <= 1000000; i++) {
			list2.get(500000);
		}
		long end2 = System.currentTimeMillis();
		System.out.println("ArrayList查询运行时间:" + (end2 - start2));
		
	}
}

// 缺点：
// 1. 每当该替换测试的集合时，都必须修改类。
// 2. 而且有2个引起类变化的原因： 提升信息， 测试的集合类，违反单一职责。