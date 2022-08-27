package model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Cup {
	private Integer id;
	private String name;
	private String version;
	//普通进价
	private Integer ordinary_price;
	//批发进价
	private Integer wholesale_price;
	//零售价
	private Integer retail_price;
	//会员售价
	private Integer membership_price;
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getOrdinary_price() {
		return ordinary_price;
	}
	public void setOrdinary_price(Integer ordinary_price) {
		this.ordinary_price = ordinary_price;
	}
	public Integer getWholesale_price() {
		return wholesale_price;
	}
	public void setWholesale_price(Integer wholesale_price) {
		this.wholesale_price = wholesale_price;
	}
	public Integer getRetail_price() {
		return retail_price;
	}
	public void setRetail_price(Integer retail_price) {
		this.retail_price = retail_price;
	}
	public Integer getMembership_price() {
		return membership_price;
	}
	public void setMembership_price(Integer membership_price) {
		this.membership_price = membership_price;
	}
	
	
	/**
	 * 设置属性值
	 * @param cup
	 * @param fieldName
	 * @param fieldVal
	 * @return
	 */
	public static Cup setValue(Cup cup, String fieldName, Object fieldVal){
		try {
			Class<?> cls = Class.forName("model.Cup");
			Field[] declaredFields = cls.getDeclaredFields();
			for (Field field : declaredFields) {
				String objFileName = field.getName();
				if( objFileName.equals(fieldName) ){
					field.setAccessible(true);
					field.set(cup, fieldVal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cup;
	}
	@Override
	public String toString() {
		return "Cup [id=" + id + ", name=" + name + ", version=" + version + ", ordinary_price=" + ordinary_price
				+ ", wholesale_price=" + wholesale_price + ", retail_price=" + retail_price + ", membership_price="
				+ membership_price + "]";
	}
	
	
	
}
