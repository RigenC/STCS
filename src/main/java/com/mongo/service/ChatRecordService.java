package com.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongo.dao.chatrecord;
import com.springsimple.domain.Message;

@Service
public class ChatRecordService {
	private static String CHATRECORD_COLLECTION="chatrecord";
	@Autowired
	MongoTemplate mongo;
	@Autowired
	Message message;
	public void saveChatRecord(List<chatrecord> list){
		mongo.insert(list,CHATRECORD_COLLECTION);
	}
	public void removeIllegal(){
		mongo.remove(new Query(Criteria.where("content").size(0)), CHATRECORD_COLLECTION);
	}
	public List<chatrecord> findAll(){
		List<chatrecord> list=mongo.findAll(chatrecord.class, CHATRECORD_COLLECTION);
		return list;
	}
	public void clear(){
		mongo.dropCollection(CHATRECORD_COLLECTION);
	}
}
