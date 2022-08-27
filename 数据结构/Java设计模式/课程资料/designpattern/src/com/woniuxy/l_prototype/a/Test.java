package com.woniuxy.l_prototype.a;

import java.util.Date;

/*
 开发OA（办公自动化项目）项目的时候， 都需要有一个周报功能。
现在就模拟一下周报功能
*/

class WeekReport {
	private String name;			// 员工名字
	private String content;			// 周报内容
	private String department;		// 员工所属部门
	private String question;		// 本周工作遇到的问题
	private String suggestion;		// 一些建议
	private Date pubdate;			// 提交周报的时间
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
		System.out.println("提交周报入库:" + wr);
		
		// 张珊珊，11月第2周周报:
		WeekReport wr2 = new WeekReport();
		wr2.setName("张珊珊");
		wr2.setContent("这周做了一套西服");
		wr2.setQuestion("无");
		wr2.setSuggestion("无");
		wr2.setDepartment("毛线部");
		wr2.setPubdate(new Date());
		System.out.println("提交周报入库:" + wr2);
		
		// 张珊珊，11月第3周周报:
		WeekReport wr3 = new WeekReport();
		wr3.setName("张珊珊");
		wr3.setContent("这周啥都没干");
		wr3.setQuestion("无");
		wr3.setSuggestion("无");
		wr3.setDepartment("毛线部");
		wr3.setPubdate(new Date());
		System.out.println("提交周报入库:" + wr3);
	}
}
