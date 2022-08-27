package com.woniuxy.f_composition.a;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

// 需求：
// 需要制作一个集合， 这个集合要统计自该集合诞生依赖，都一共加过多少个元素。（不是问目前集合中有多少个元素）
class MyHashSet extends HashSet {
	private int count = 0;

	@Override
	public boolean add(Object e) {
		count++;
		return super.add(e);
	}
	
	@Override
	public boolean addAll(Collection c) {
		count += c.size();
		return super.addAll(c);
	}

	public int getCount() {
		return count;
	}
}


public class Test {
	public static void main(String[] args) {
		MyHashSet set = new MyHashSet();
		set.add(1);
		set.add(22);
		set.add(333);
		
		HashSet set2 = new HashSet();
		set2.add(10);
		set2.add(20);
		set2.add(30);
		set.addAll(set2);
		
		System.out.println(set.getCount());  
	}
}

// 期望得到6，结果得到9，why??? 因为addAll回调了add方法！