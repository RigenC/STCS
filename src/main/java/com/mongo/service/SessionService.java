package com.mongo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cluster.domain.FrequentWordSet;
import com.mongo.dao.Session;
@Service
public class SessionService {
	private static String SESSION_COLLECTION="sessions";
	@Autowired
	MongoTemplate mongo;
	public void insertSessions(List<Session> list){
		mongo.insert(list, SESSION_COLLECTION);
	}
	/**
	 * 根据频繁词集获取Session的ID集合
	 */
	public List<Integer> getSessions(FrequentWordSet<String> fwset){
		List<Integer> result=new ArrayList<Integer>();
		List<Session> list=mongo.find(new Query(Criteria.where("content").all(fwset)), Session.class, SESSION_COLLECTION);
		for(Session s:list)
			result.add(s.getId());
		return result;
	}
	public void clear(){
		mongo.dropCollection(SESSION_COLLECTION);
	}
}
