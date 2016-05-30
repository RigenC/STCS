package com.cluster.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bson.types.ObjectId;

import com.cluster.service.preHandleService;
import com.mongo.dao.Session;
import com.mongo.dao.User;
import com.mongo.dao.chatrecord;

public class TextVector {
	public static Map<Integer,TextVector> ALLTEXTVECTORS=new HashMap<Integer,TextVector>();
	//对应数据库内的字段
	private int record_id;
	private String user_number;
	private List<String> content;
	private Date record_time;
	private String response_to;
	
	public Map<String,Integer> originalWords=new HashMap<String,Integer>();
	public Map<String,Double> tfidfVector=new HashMap<String,Double>();
	public SessionVector getSessionBelong2(){
		if(SessionVector.ALLSESSIONS.isEmpty())
			return null;
		for(SessionVector s:SessionVector.ALLSESSIONS.values()){
			if(s.vectors.contains(this.record_id))
				return s;
		}
		return null;
	}
	/**
	 * 消除所有textvector的资源占用
	 */
	public static void clear(){
		ALLTEXTVECTORS.clear();
	}
	public static TextVector getTextVector(chatrecord record){
		TextVector vector=new TextVector();
		vector.record_id=record.getRecord_id();
		vector.user_number=record.getUser_number();
		vector.content=record.getContent();
		vector.record_time=record.getRecord_time();
		vector.response_to=record.getResponse_to();
		return vector;
	}
	public static void initialTFIDFVector(){
		for(TextVector vector:ALLTEXTVECTORS.values()){
			double totalWordNum=vector.content.size();
			for(String word:vector.content){
				if(vector.originalWords.containsKey((String)word)){
					vector.originalWords.put(word, vector.originalWords.get(word));
				}
				else {
					vector.originalWords.put(word, 1);
				}
			}
			for(String key:vector.originalWords.keySet()){
				Integer value=vector.originalWords.get(key);
				double tf=value/totalWordNum;
				double idf=Math.log(Word.totalTextNum/(double)Word.WORDIDF.get(key)+0.01);
				vector.tfidfVector.put(key, tf*idf);
			}
			double der=0;
			for(Double value:vector.tfidfVector.values()){
				der+=value*value;
			}
			der=Math.sqrt(der);
			for(Entry<String,Double> entry:vector.tfidfVector.entrySet()){
				vector.tfidfVector.put(entry.getKey(), entry.getValue()/der);
			}
			vector.originalWords.clear();
		}
	}
	/**
	 * 根据用户名和时间获取它前面离它最近的该用户的消息
	 * @param usernumber 用户名，被艾特的人
	 * @param date 该消息的时间
	 * @return
	 * @throws ParseException
	 */
	public static TextVector getClosestVector(String username,Date datestr) throws ParseException{
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date recent=format.parse("1970-01-01 00:00:00");
		Date date=datestr;
		TextVector result=null;
		String usernumber=null;
		for(User user:preHandleService.ALLUSERNAME){
			if(user.getNames().contains(username)){
				usernumber=user.getUserNum();
				break;
			}
		}
		for(TextVector vector:ALLTEXTVECTORS.values()){
			if(vector.getUser_number().equals(usernumber)
					&&vector.getRecord_time().before(date)&&vector.getRecord_time().after(recent)){
				recent=vector.getRecord_time();
				result=vector;
			}
		}
		return result;
	}
	public Map<String, Integer> getOriginalWords() {
		return originalWords;
	}
	public void setOriginalWords(Map<String, Integer> originalWords) {
		this.originalWords = originalWords;
	}
	public Map<String, Double> getTfidfVector() {
		return tfidfVector;
	}
	public void setTfidfVector(Map<String, Double> tfidfVector) {
		this.tfidfVector = tfidfVector;
	}
	public String getResponse_to() {
		return response_to;
	}
	public void setResponse_to(String response_to) {
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
}
