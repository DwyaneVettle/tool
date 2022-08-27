package com.woniuxy.f_composition.d;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

// 针对于c包的缺点，不再重写addAll方法了， 不重写，而是额外扩展方法添加方法，在api文档里说明，但凡要调用addAll的时候，都调用addAll2
class MyHashSet extends HashSet {
	private int count = 0;

	@Override
	public boolean add(Object e) {
		count++;
		return super.add(e);
	}
	
	public boolean addAll2(Collection c) {
		boolean bb = false;
		for(Object obj : c) {
			if(add(obj)) {
				bb = true;
			}
		}
		return bb;
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

/*
 	这样做太勉强了，更重要的是，在未来的新jd版本中，就是那么寸，作者新加了个方法角addAll2。。。 
 	
 	继承，已经尽忠了...
*/
