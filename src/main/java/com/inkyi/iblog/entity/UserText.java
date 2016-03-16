package com.inkyi.iblog.entity;
/**
 * 
 * @author InkYi
 * @date 2016年3月15日 下午3:40:56
 *
 */
public class UserText {

	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "UserText [id=" + id + ", name=" + name + "]";
	}
	
	
}
