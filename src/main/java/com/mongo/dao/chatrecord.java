package com.mongo.dao;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class chatrecord {
	private int record_id;
	private String user_number;
	private List<String> content;
	private Date record_time;
	private String response_to;
	public chatrecord(int record_id, String user_number, List<String> content, Date record_time, String response_to) {
		this.record_id = record_id;
		this.user_number = user_number;
		this.content = content;
		this.record_time = record_time;
		this.response_to = response_to;
	}
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	public String getUser_number() {
		return user_number;
	}
	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	public Date getRecord_time() {
		return record_time;
	}
	public void setRecord_time(Date record_time) {
		this.record_time = record_time;
	}
	public String getResponse_to() {
		return response_to;
	}
	public void setResponse_to(String response_to) {
		this.response_to = response_to;
	}
}
