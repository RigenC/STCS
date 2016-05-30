package com.cluster.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import com.mongo.dao.Session;

public class SessionVector implements Comparable<SessionVector>{
	//公共数据域
	public static Map<Integer,SessionVector> ALLSESSIONS=new HashMap<Integer,SessionVector>();
	private static int num=0;
	//对象私有部分
	private Date latestTime;
	private Date startTime=new Date();
	private int id;
	public List<Integer> vectors=new ArrayList<Integer>();
	private Map<String,Integer> originalMap=new HashMap<String,Integer>();
	private Map<String,Double> tfidfMap=new HashMap<String,Double>();
	public SessionVector() {
		this.id=num++;
		try{
			latestTime=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("1970-1-1 00:00:00");
		}catch(ParseException e){
			e.printStackTrace();
		}
	}
	/**
	 * 根据会话包含的消息向量列表，组成会话的原始消息向量
	 */
	public void createOriginalMap(){
		for(Integer id:vectors){
			TextVector vector=TextVector.ALLTEXTVECTORS.get(id);
			for(String word:vector.getContent()){
				if(originalMap.containsKey(word)){
					originalMap.put(word, originalMap.get(word)+1);
				}
				else{
					originalMap.put(word, 1);
				}
			}
		}
	}
	/**
	 * 根据会话的原始向量，组建TFIDF权重向量，在所有的会话都组建完成原始向量且更新了WORD中的两个静态MAP之后运行.运行结束后清空原始向量
	 */
	public void createTIDFMap(){
		double totalWordNum=0;
		for(Integer value:originalMap.values())
			totalWordNum+=value;
		for(String key:originalMap.keySet()){
			Integer value=originalMap.get(key);
			double tf=value/totalWordNum;
			double idf=Math.log(Word.totalTextNum/(double)Word.WORDIDF.get(key)+0.01);//这里存在问题
			tfidfMap.put(key, tf*idf);
		}
		double der=0;
		for(Double value:tfidfMap.values()){
			der+=value*value;
		}
		der=Math.sqrt(der);
		for(Entry<String,Double> entry:tfidfMap.entrySet()){
			tfidfMap.put(entry.getKey(), entry.getValue()/der);
		}
		originalMap.clear();
	}
	/**
	 * 重新计算会话的最新时间
	 */
	public void refreshLatestDate(){
		for(Integer id:vectors){
			TextVector tv=TextVector.ALLTEXTVECTORS.get(id);
			if(this.latestTime.before(tv.getRecord_time())){
				latestTime=tv.getRecord_time();
			}
		}
	}
	public void addVector(TextVector tv){
		vectors.add(tv.getRecord_id());
		if(tv.getRecord_time().before(startTime))
			startTime=tv.getRecord_time();
		refreshLatestDate();
	}
	/**
	 * 获取TextVector与SessionVector的相似度
	 * @param tv
	 * @return
	 */
	public double getMaxSimilarity(TextVector tv){
		double max=0;
		int i;
		double diffTime2StartTime=(double)((tv.getRecord_time().getTime()-startTime.getTime())/Threshold.hour1Toms);
		double s=12/diffTime2StartTime;
		if(vectors.size()>Threshold.newsetvector)
			i=Threshold.newsetvector;
		else i=0;
		for(;i<vectors.size();i++){
			TextVector vector=TextVector.ALLTEXTVECTORS.get(vectors.get(i));
			double similarity=DynamicTextVector.getDynamicSimilarity(tv.getTfidfVector(), vector.getTfidfVector());
			if(similarity>max)
				max=similarity;
		}
		return s*max;
	}
	/**
	 * 获取获取ALLSESSIONS中最新的会话，即与TV时间相差在12小时内的会话
	 * @param tv
	 * @return
	 */
	public static List<SessionVector> getLastestSessions(TextVector tv){
		Date date=tv.getRecord_time();
		List<SessionVector> list=new ArrayList<SessionVector>();
		for(SessionVector session:ALLSESSIONS.values()){
			if(date.getTime()-session.getLatestTime().getTime()<Threshold.maxduration){
				list.add(session);
			}
		}
		Collections.sort(list);
		List<SessionVector> result=new ArrayList<SessionVector>();
		for(SessionVector s:list){
			if(result.size()<Threshold.k)
				result.add(s);
			else
				break;
		}
		return result;
	}
	/**
	 * 重新计算Word中IDFWord的词频
	 */
	public static void refreshWordIDF(){
		for(Entry<String,Integer> entry:Word.WORDIDF.entrySet()){
			String word=entry.getKey();
			int num=0;
			for(SessionVector session:SessionVector.ALLSESSIONS.values()){
				if(session.getOriginalMap().containsKey(word)){
					num++;
				}
			}
			Word.WORDIDF.put(word, num);
		}
	}
	public int compareTo(SessionVector o) {
		// TODO Auto-generated method stub
		if(this.latestTime.after(o.latestTime))
			return -1;
		else if(this.latestTime.before(o.latestTime))
			return 1;
		else
			return 0;
	}
	public Date getLatestTime() {
		return latestTime;
	}
	public void setLatestTime(Date latestTime) {
		this.latestTime = latestTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Map<String, Integer> getOriginalMap() {
		return originalMap;
	}
	public void setOriginalMap(Map<String, Integer> originalMap) {
		this.originalMap = originalMap;
	}
	public Map<String, Double> getTfidfMap() {
		return tfidfMap;
	}
	public void setTfidfMap(Map<String, Double> tfidfMap) {
		this.tfidfMap = tfidfMap;
	}
	
}
