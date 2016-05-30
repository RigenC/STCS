package com.mongo.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongo.dao.User;

@Service
public class UserService {
	private static String USER_COLLECTION="users";
	private static String CHATRECORD_COLLECTION="chatrecord";
	@Autowired
	MongoTemplate mongoTemplate;
	
	public User findUserByNum(String userNum){
		return mongoTemplate.findOne(new Query(Criteria.where("number").is(userNum)), User.class, USER_COLLECTION);
	}
	public void saveUser(List<User> users){
		mongoTemplate.insert(users, USER_COLLECTION);
	}
	public int findUserActivation(User user){
		return mongoTemplate.find(new Query(Criteria.where("user_number").is(user.getUserNum())), User.class,CHATRECORD_COLLECTION).size();
	}
	public void clear(){
		mongoTemplate.dropCollection(USER_COLLECTION);
	}
}
