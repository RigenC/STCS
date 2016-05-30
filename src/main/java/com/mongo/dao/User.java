package com.mongo.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

public class User implements Serializable{
	Set<String> names;
	String userNum;
	public User(Set<String> names, String userNum) {
		this.names = names;
		this.userNum = userNum;
	}
	public Set<String> getNames() {
		return names;
	}
	public void setNames(Set<String> names) {
		this.names = names;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public void addUserName(String name){
		this.names.add(name);
	}
	@Override
	public String toString() {
		return "{user:{names:"+this.names.toString()+",userNum:"+this.userNum+"}}";
	}
}
