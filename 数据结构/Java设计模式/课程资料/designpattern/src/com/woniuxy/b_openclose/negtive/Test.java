package com.woniuxy.b_openclose.negtive;

/*
 开闭原则： 对修改关系，对扩展开放。

1. 对于一个已有的功能，我们要保证，在不修改它已经做好的功能的前提下，去扩展新功能。
2. 比如：这一个刮胡刀，能刮胡子，同时它还是一个吹风机。 不能因为要给刮胡刀添加一个吹风功能，就影响了刮胡子。
        应该吹风功能是扩展出来的，对已有的刮胡子功能没有影响。
3. 编写代码的时候，一定要记住，有2个角色： 作者、用户。
        比如：Dbutils，就是作者的，我们就是用户。
*/

public class Test {
	public static void main(String[] args) {
		JavaCourse jc = new JavaCourse("java se", 300, 50);
		System.out.println(jc.getName() + ": " + jc.getPrice());
	}

}

// 现在有一个新需求： 11.11 课程要打折，所有课程打8折。 此时为了达到这个目的，就需要修改JavaCourse源代码！
/*
	直接修改JavaCourse的源代码：
	public double getPrice() {
		return price * 0.8; 
	}
*/

// 这里直接写死乘以0.8的逻辑，就违反了开闭原则，这是在修改已有功能！
// 要知道，项目在经过多年以后，源码很可能丢失.

