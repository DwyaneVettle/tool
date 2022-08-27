package com.woniuxy.f_composition.b;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

// 针对于a包的缺点，我们只需要，不重写父类的addAll方法即可
class MyHashSet extends HashSet {
	private int count = 0;

	@Override
	public boolean add(Object e) {
		count++;
		return super.add(e);
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
 	这样做，虽然已经实现了需求，但是设计仍然很脆弱！！！
 	
 	这样的设计，必须以来有一个事实： 就是addAll方法必须去回调add方法才行。 
 	
 	要知道，jdk的那些提前制作好的类底层源码，在不同jdk版本中，很有可能发生变化，尽管在多个版本中持续不变的代码，也难保在未来的某个版本会变！
 	
 	万一在某一个未来的jdk版本中，HashSet的addAll方法不再回调add方法了，这里的客户端代码，就被“撼动”！！
 	
*/
