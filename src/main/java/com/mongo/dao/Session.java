package com.mongo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {
	private int id;
	private List<String> content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	public Session(int id,List<String> content) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.content=content;
	}
}
