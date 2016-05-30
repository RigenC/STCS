package com.cluster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cluster.domain.TextVector;
import com.mongo.service.SessionService;
import com.mongo.service.WordService;
@Service
public class loadVectorService {
	@Autowired
	WordService wordservice;
	@Autowired
	SessionService sessionservice;
	public void loadVector(){
		wordservice.loadChatRecords();//加载所有消息向量、频繁词、词语项等
		TextVector.initialTFIDFVector();//计算所有消息的TFIDF向量
	}
}
