package com.woniuxy.c_dependencyinversion.postive;
//зїеп
public class Person {
	
	private String name;
	
	public Person(String name) {
		this.name = name;
	}
	
	public void play(Instruments i) {
		i.sound();
	}
}
