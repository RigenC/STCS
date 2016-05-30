package com.springsimple.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cluster.service.preHandleService;
import com.mongo.dao.User;
import com.mongo.dao.chatrecord;
import com.mongo.service.ChatRecordService;
import com.mongo.service.UserService;
import com.springsimple.domain.Message;

@Service
public class StatisticService {
	@Autowired
	Message message;
	@Autowired
	ChatRecordService mongochatrecord;
	@Autowired
	UserService mongouser;
	//统计消息
	public Message statisticMessage(){
		List<chatrecord> list=mongochatrecord.findAll();
		for(chatrecord record:list){
			String date=Message.sdf.format(record.getRecord_time());
			if(message.getNums().containsKey(date)){
				message.getNums().put(date, message.getNums().get(date)+1);
			}
			else{
				message.getNums().put(date, 1);
			}
		}
		message.setSize(message.getNums().size());
		message.setStarttime(message.getNums().firstKey());
		message.setEndtime(message.getNums().lastKey());
		return message;
	}
	public JSONArray statisticUser(){
		int k=10;
		List<User> list=preHandleService.ALLUSERNAME;
		Map<User,Integer> map=new HashMap<User,Integer>();
		for(User user:list){
			int num=mongouser.findUserActivation(user);
			map.put(user, num);
		}
		JSONArray result=new JSONArray();
		while(k-->0){
			Iterator<Map.Entry<User, Integer>> it=map.entrySet().iterator();
			int max=0;
			User maxuser = null;
			while(it.hasNext()){
				Entry<User, Integer> entry=it.next();
				if(entry.getValue()>max){
					max=entry.getValue();
					maxuser=entry.getKey();
				}
			}
			JSONObject user=new JSONObject();
			user.put("id", maxuser.getUserNum());
			user.put("names", maxuser.getNames());
			user.put("frequency", max);
			result.put(user);
			map.remove(maxuser);
		}
		return result;
	}
	
}
