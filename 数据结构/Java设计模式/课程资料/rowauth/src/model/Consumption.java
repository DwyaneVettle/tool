package model;

import java.math.BigDecimal;
import java.util.Date;

public class Consumption {
	private Integer id;
	private BigDecimal expenditure;
	private Date time;
	private String mark;
	private String deptName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getExpenditure() {
		return expenditure;
	}
	public void setExpenditure(BigDecimal expenditure) {
		this.expenditure = expenditure;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
