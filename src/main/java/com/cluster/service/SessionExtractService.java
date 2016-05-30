package com.cluster.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cluster.domain.SessionVector;
import com.cluster.domain.TextVector;
import com.cluster.domain.Threshold;
import com.mongo.dao.Session;
import com.mongo.service.SessionService;
import com.mongo.service.WordService;

@Service
public class SessionExtractService {
	@Autowired
	SessionService sessionservice;
	public void SessionExtract(){
		TextVector lastvector = null;
		for(TextVector vector:TextVector.ALLTEXTVECTORS.values()){
			if(SessionVector.ALLSESSIONS.size()==0){
				SessionVector SessionVector=new SessionVector();
				SessionVector.addVector(vector);
				SessionVector.ALLSESSIONS.put(SessionVector.getId(), SessionVector);
				lastvector=vector;
				continue;
			}
			//消息中存在@对象时，将艾特消息划分到被艾特的消息会话中
			if(vector.getResponse_to()!=null){
				TextVector target=null;
				try {
					target = TextVector.getClosestVector(vector.getResponse_to(), vector.getRecord_time());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(target!=null){
					target.getSessionBelong2().addVector(vector);
//					System.out.println("消息"+vector.getRecord_id()+"添加到会话"+target.getSessionBelong2().getId()+"中， 存在艾特");
					lastvector=vector;
					continue;
				}
			}
			//如果连续两条消息的时间间隔小于minduration并且是同一个人发的，则认为两条消息应该是一条消息
			if(lastvector!=null&&vector.getRecord_time().getTime()-lastvector.getRecord_time().getTime()<Threshold.minduration
					&&vector.getUser_number().equals(lastvector.getUser_number())){
				lastvector.getSessionBelong2().addVector(vector);
//				System.out.println("消息"+vector.getRecord_id()+"添加到会话"+lastvector.getSessionBelong2().getId()+"中， 连续两条同一人");
				lastvector=vector;
				continue;
			}
			
			double sessionMax1=0;
			double sessionMax2=0;
			SessionVector belong1=null;
			SessionVector belong2=null;
			List<SessionVector> lastestSessions=SessionVector.getLastestSessions(vector);
			String sessionsnum="";
			for(SessionVector s:lastestSessions){
				sessionsnum+=s.getId()+" ";
			}
			for(SessionVector SessionVector:lastestSessions){
					double similarity=SessionVector.getMaxSimilarity(vector);
					if(similarity>sessionMax2&&similarity>Threshold.gama){
						sessionMax2=similarity;
						belong2=SessionVector;
					}
			}
			if(belong2==null||sessionMax2==0){
				SessionVector SessionVector=new SessionVector();
				SessionVector.addVector(vector);
				SessionVector.ALLSESSIONS.put(SessionVector.getId(), SessionVector);
				lastvector=vector;
				continue;
			}
			else{
				belong2.addVector(vector);
			}
			lastvector=vector;
		}
		System.out.println("SesssionExtract Complete");
		List<Session> list=new LinkedList<Session>();
		System.out.println(SessionVector.ALLSESSIONS.size());
//		初始化SessionVector的originalMap
		for(SessionVector session:SessionVector.ALLSESSIONS.values()){
			session.createOriginalMap();
			List<String> content=new ArrayList<String>();
			for(Entry<String,Integer> entry:session.getOriginalMap().entrySet()){
				for(int i=0;i<entry.getValue();i++)
					content.add(entry.getKey());
			}
			Session s=new Session(session.getId(), content);
			list.add(s);
		}
		sessionservice.insertSessions(list);//保存到数据库
		SessionVector.refreshWordIDF();
		for(SessionVector session:SessionVector.ALLSESSIONS.values()){
			session.createTIDFMap();
		}
		TextVector.clear();//消除TextVector的资源占用
	}
}
