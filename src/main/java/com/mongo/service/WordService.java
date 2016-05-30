package com.mongo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cluster.domain.FrequentWordSet;
import com.cluster.domain.TextVector;
import com.cluster.domain.Threshold;
import com.cluster.domain.Word;
import com.cluster.domain.WordSimilarityNet;
import com.cluster.service.WordSimilarity;
import com.mongo.dao.Session;
import com.mongo.dao.chatrecord;

@Service
public class WordService {
	private static String CHATRECORD_COLLECTION="chatrecord";
	private static String SESSION_COLLECTION="sessions";
	@Autowired
	MongoTemplate mongo;
	
	public int getWordFrequency(FrequentWordSet<String> set){
		return mongo.find(new Query(Criteria.where("content").all(set)), Session.class, SESSION_COLLECTION).size();
	}
	/**
	 * 加载所有消息，以TextVector的形式存储，加载所有词语，统计频繁词
	 */
	public void loadChatRecords(){
		List<chatrecord> list=mongo.findAll(chatrecord.class, CHATRECORD_COLLECTION);
		Word.totalTextNum=list.size();
		System.out.println(list.size());
		for(chatrecord record:list){
			TextVector vector=TextVector.getTextVector(record);
			TextVector.ALLTEXTVECTORS.put(vector.getRecord_id(), vector);
			List<String> content=record.getContent();
			Set<String> simplecontent=new HashSet<String>(content);
			Word.ALLWORDS.addAll(content);//加载所有词语
			for(String word:simplecontent){//统计词频
				if(Word.WORDIDF.containsKey(word)){
					Word.WORDIDF.put(word, Word.WORDIDF.get(word));
				}
				else
					Word.WORDIDF.put(word, 1);
			}
			for(Entry<String, Integer> entry:Word.WORDIDF.entrySet()){//获取高频词
				if(entry.getValue()/Word.totalTextNum>Threshold.miu){
					Word.FREQUENTWORD.add(entry.getKey());
				}
			}
			initialWordSimilarityNet();
		}
	}
	/**
	 * 加载词语关系网络
	 */
	private void initialWordSimilarityNet(){
		for(String word1:Word.FREQUENTWORD){
			for(String word2:Word.FREQUENTWORD){
				if(word1.equals(word2))
					WordSimilarityNet.putSimilarity(word1, word2, 1.0);
				else {
					Double similarity=WordSimilarity.simWord(word1, word2);
					WordSimilarityNet.putSimilarity(word1, word2, similarity);
				}
			}
		}
	}
}
