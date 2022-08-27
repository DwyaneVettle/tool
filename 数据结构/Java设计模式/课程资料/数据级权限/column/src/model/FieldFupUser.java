package model;

public class FieldFupUser{
	private Integer id;
	private Integer user_id;
	//字段名
	private String fieldName;
	private String columName;
	//字段中文对照
	private String comment;
	//字段排序
	private Integer fieldOrder;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getFieldOrder() {
		return fieldOrder;
	}
	public void setFieldOrder(Integer fieldOrder) {
		this.fieldOrder = fieldOrder;
	}
	public String getColumName() {
		return columName;
	}
	public void setColumName(String columName) {
		this.columName = columName;
	}
	
	
	@Override
	public String toString() {
		return "FieldFupUser [fieldName=" + fieldName + ", comment=" + comment + ", fieldOrder=" + fieldOrder + "]";
	}
	
}
