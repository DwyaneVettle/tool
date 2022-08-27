package com.woniuxy.l_prototype.c;

import java.util.Date;

/*
 	针对于b包中的问题: 浅克隆的问题。 重构代码如下:
 	
 	注意，只修改了WeekReport中的clone方法。其他哪都没变。
 	
 	
 	
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
			// 但是Object默认的clone方法，是浅克隆，如果想要达到深克隆的目的，还需要修改代码
			WeekReport copy = (WeekReport) super.clone();
			Date pubdate = (Date) copy.getPubdate().clone();
			copy.setPubdate(pubdate	);
			return copy;
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
		
		
		System.out.println("=========================================");
		// 思考2：
		wr.getPubdate().setTime(0);
		
		System.out.println(wr);
		System.out.println(wr2);
		System.out.println(wr3);
		
		
	}
}

// 目前，虽然实现了深拷贝，但是，如果对象的引用层次特别深，这种方法也很麻烦！！





