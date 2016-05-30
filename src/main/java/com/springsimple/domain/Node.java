package com.springsimple.domain;

public class Node {
	private double symbolSize;
	private String name;
	private int category;
	private double value;
	public Node(double symbolSize, String name, int category, double value) {
		super();
		this.symbolSize = symbolSize;
		this.name = name;
		this.category = category;
		this.value = value;
	}
	public double getSymbolSize() {
		return symbolSize;
	}
	public void setSymbolSize(double symbolSize) {
		this.symbolSize = symbolSize;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
