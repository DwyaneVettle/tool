package com.woniuxy.f_composition.c;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

// 针对于b包的缺点，我们需要再次重写addAll，但是这次addAll方法不再直接调用super.addAll()
// 我们需要亲自上手，编写addAll的具体实现，保证addAll一定会回调add方法!
class MyHashSet extends HashSet {
	private int count = 0;

	@Override
	public boolean add(Object e) {
		count++;
		return super.add(e);
	}
	
	@Override
	public boolean addAll(Collection c) {
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
 	这样看起来好像没有任何问题了，其实不然！ 这个设计仍然很脆弱!
 	
 	这有2个缺点：
 	
 	一、
 	现在HashSet添加元素的方法是： add  addAll
 	如果在新版本中的HashSet，做了一些添加元素的方法，addSome .
 	
 	由于这里使用的是继承，那么新版本的HashSet的新addSome方法，也由MyHashSet继承了，客户端就能调用addSome，这个addSome也是加元素的
 	但是该方法中，没有统计数量，如此又违反了需求！
 	
 	二、
 	addAll原本是作者自己实现的，这个addAll难免会被HashSet中的其他方法调用，也就是HashSet的其他方法调用addAll来完成功能，
 	我们一旦自己重写addAll方法，那么其他依赖于这个addAll方法的方法，就有可能出现不可预知错误！
 	
 	以上2个问题，就是源于方法重写。 
 	
*/
