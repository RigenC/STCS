package com.springsimple.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.cluster.service.preHandleService;

public class Message {
	public static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private String starttime;
	private String endtime;
	private TreeMap<String,Integer> nums;
	private int size;
	public Message(){
		starttime=sdf.format(new Date());
		endtime="1970-01-01";
		nums=new TreeMap<String,Integer>();
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public TreeMap<String, Integer> getNums() {
		return nums;
	}
	public void setNums(TreeMap<String, Integer> nums) {
		this.nums = nums;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
