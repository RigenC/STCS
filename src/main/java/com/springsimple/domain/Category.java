package com.springsimple.domain;

public class Category {
	private String name;
	private String base;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Category(String name, String base) {
		super();
		this.name = name;
		this.base = base;
	}
	
}
