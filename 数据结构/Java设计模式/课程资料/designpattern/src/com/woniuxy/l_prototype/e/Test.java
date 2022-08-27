package com.woniuxy.l_prototype.e;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/*
 	针对于d包中的问题:  重构代码如下：
 	
 	注意，只修改了WeekReport中的clone方法。其他哪都没变。
 	
 	
 	
*/

class WeekReport implements Cloneable, Serializable  {
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
			
			// 起点：当前程序的内存， 终点：当前程序的内存
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(this);
			oos.close();
			
			// 把那一片内存中的数据，获取到，返回是一个字节数组。
			byte[] bb = out.toByteArray();
			
			InputStream in = new ByteArrayInputStream(bb);
			ObjectInputStream ois = new ObjectInputStream(in);
			
			WeekReport copy = (WeekReport) ois.readObject();
			
			ois.close();
			
			return copy;
		} catch (Exception e) {
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





