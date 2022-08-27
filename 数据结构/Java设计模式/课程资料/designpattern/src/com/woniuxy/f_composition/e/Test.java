package com.woniuxy.f_composition.e;

import java.util.HashSet;

public class Test {
	public static void main(String[] args) {
		HashSet set = new HashSet();
		MyHashSet ms = new MyHashSet(set);
		
		ms.add(1);
		ms.add(2);
		ms.add(3);
		
		HashSet set2 = new HashSet();
		set2.add(10);
		set2.add(20);
		set2.add(30);
		
		ms.addAll(set2);
		
		System.out.println(ms.getCount());
	}
}
