package com.woniuxy.l_prototype.b;

import java.util.Date;

/*
 	针对于a包中的问题，我们需要“原型设计模式”骑着白马来解决我们。
 	
 	原型设计模式步骤:
 	1. 必须让要被克隆的对象所属的类，实现Cloneable接口
 	   注意，该接口中没有任何抽象方法，这样的接口就是“标记接口”。 只是一个标记，标记了但是实现该接口的类，它的对象都可以被克隆。
 	2. 必须重写父类java.lang.Object类的clone方法。
 	
 	注意，被克隆出来的对象，与原先的对象不是同一个对象！！（地址不同）
 	
 	
 	
*/

class WeekReport implements Cloneable  {
	private String name;			// 员工名字
	private String content;			// 周报内容
	private String department;		// 员工所属部门
	private String question;		// 本周工作遇到的问题
	private String suggestion;		// 一些建议
	private Date pubdate;			// 提交周报的时间
	
	
	public WeekReport() {
		System.out.println("构造WeekReport");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	@Override
	public String toString() {
		return "WeekReport [name=" + name + ", content=" + content + ", department=" + department + ", question="
				+ question + ", suggestion=" + suggestion + ", pubdate=" + pubdate + "]";
	}
	
	@Override
	protected Object clone()  {
		try {
			// super.clone() 调用的还是Object的clone方法,该方法已经被实现过了，不同我们自己再写任何代码！
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}

public class Test {
	public static void main(String[] args) {
		
		// 张珊珊，11月第1周周报:
		WeekReport wr = new WeekReport();
		wr.setName("张珊珊");
		wr.setContent("这周织了两件毛衣");
		wr.setQuestion("无");
		wr.setSuggestion("无");
		wr.setDepartment("毛线部");
		wr.setPubdate(new Date());
		
		
		WeekReport wr2 = (WeekReport) wr.clone();
		wr2.setContent("做了一套西服");
		
		WeekReport wr3 = (WeekReport) wr.clone();
		wr3.setContent("啥都没干");
		
		
		System.out.println("1周报入库:" + wr);
		System.out.println("2周报入库:" + wr2);
		System.out.println("2周报入库:" + wr3);
		
		
		// 思考1 ： 克隆对象的时候，有没有调用构造器？？  没有！！ 克隆是吧对象在内存中的2进制数据，复制了一份。
		//	     所以利用克隆创建对象比起构造器创建对象，速度更快！！
		
		System.out.println("=========================================");
		// 思考2：
		wr.getPubdate().setTime(0);
		
		System.out.println(wr);
		System.out.println(wr2);
		System.out.println(wr3);
		
		
	}
}



