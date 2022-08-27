package com.woniuxy.g_liskvo.supplement;

// 里氏替换原则： 任何能使用父类对象的地方，都能透明的使用子类（直接替换为子类，程序行为不会发生错误！）

// 方法重写的2个限制
// 1. 子类重写父类的方法时，不能抛出比父类方法更多的异常
// 2. 子类重写父类的方法时，方法的访问修饰符不能比类的更严格

class Fu {
	void f1() throws Exception {
		
	}
}

class Zi extends Fu {
	public void f1()  {
		
	}
}

public class Test {
	public static void main(String[] args) throws Exception {
			Fu fu = new Zi();
			fu.f1();
	}
}