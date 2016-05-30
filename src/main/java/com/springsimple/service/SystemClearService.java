package com.springsimple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cluster.domain.SessionVector;
import com.cluster.domain.TextVector;
import com.cluster.domain.Word;
import com.cluster.domain.WordSimilarityNet;
import com.cluster.domain.visualPoint;
import com.cluster.service.FrequentWordService;
import com.cluster.service.SessionKMeansService;
import com.cluster.service.preHandleService;
import com.mongo.service.ChatRecordService;
import com.mongo.service.SessionService;
import com.mongo.service.UserService;

@Service
public class SystemClearService {
	@Autowired
	ChatRecordService chatrecordservice;
	@Autowired
	SessionService sessionservice;
	@Autowired
	UserService userservice;
	public void clear(){
		SessionVector.ALLSESSIONS.clear();
		TextVector.ALLTEXTVECTORS.clear();
		visualPoint.ALLVISUALPOINTS.clear();
		Word.ALLWORDS.clear();
		Word.WORDIDF.clear();
		Word.FREQUENTWORD.clear();
		Word.totalTextNum=0;
		WordSimilarityNet.clearMap();
		FrequentWordService.allLks.clear();
		FrequentWordService.rankLk.clear();
		preHandleService.ALLUSERNAME.clear();
		SessionKMeansService.result.clear();
		
		chatrecordservice.clear();
		sessionservice.clear();
		userservice.clear();
	}
}
