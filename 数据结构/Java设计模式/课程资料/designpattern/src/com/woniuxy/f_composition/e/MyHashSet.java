package com.woniuxy.f_composition.e;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet implements Set {
	private int count = 0;
	private Set set;
	
	public MyHashSet(Set set) {
		this.set = set;
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return set.contains(o);
	}

	@Override
	public Iterator iterator() {
		return set.iterator();
	}

	@Override
	public Object[] toArray() {
		return set.toArray();
	}

	@Override
	public Object[] toArray(Object[] a) {
		return set.toArray(a);
	}

	@Override
	public boolean add(Object e) {
		count++;
		return set.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return set.remove(o);
	}

	@Override
	public boolean containsAll(Collection c) {
		return set.containsAll(c);
	}

	@Override
	public boolean addAll(Collection c) {
		count += c.size();
		return set.addAll(c);
	}

	@Override
	public boolean retainAll(Collection c) {
		return set.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection c) {
		return set.removeAll(c);
	}

	@Override
	public void clear() {
		set.clear();
	}

	public int getCount() {
		return count;
	}
}

// 可以说一声 完美！！！

// 说明是用继承， 那么什么时候应该用继承。

// 刚才的继承之所以，出现那方方面面的缺点，主要是因为一个点：编写父类的人，和编写子类的人，不是同一个人。
// HashSet是一个人做的，MyhashSet是另外一个人做的。  
// 父类不知道子类会重写自己的哪些方法， 子类也不知道未来父类会加上什么新方法，以及改动什么已有方法的功能

// 在同一个包内，你随便继承，应为同一个包下的所有类，都是一个人做的。 不同包的类，能组合就别继承！
