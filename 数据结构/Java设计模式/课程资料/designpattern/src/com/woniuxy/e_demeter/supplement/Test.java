package com.woniuxy.e_demeter.supplement;

class Foo {
	public Bar f1() {
		return new Bar();
	}
	public void f2() {
		
	}
}
class Bar {
	public void f3() {
		
	}
	public void f4() {
		
	}
}
class Baz {
	
	public void abc(Foo foo) {
		Bar bar = foo.f1();
		foo.f2();
	}
}

public class Test {

}
