package com.cluster.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.cluster.domain.SessionVector;
import com.cluster.domain.visualPoint;
import kevin.zhang.NLPIR;
@Service
public class visualPointService {
	/**
	 * 使用NLPIR的关键词提取功能抽取关键词
	 */
	public void extracVisualPoint(){
		NLPIR nlpir=NLPIR.getInstance();
		try {
			if (!NLPIR.NLPIR_Init("G:/Program Files/Eclipse/workspace/ShortTextClustering/file/".getBytes("utf-8"), 1)) {  
			    System.out.println("NLPIR初始化失败...");  
			    return;  
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<SessionKMeansService.result.size();i++){
			List<Integer> cluster=SessionKMeansService.result.get(i);
			StringBuilder sb=new StringBuilder();
			for(Integer id:cluster){
				SessionVector session=SessionVector.ALLSESSIONS.get(id);
				for(String s:session.getTfidfMap().keySet()){
					sb.append(s);
				}
			}
			byte[] b=nlpir.NLPIR_GetKeyWords(sb.toString().getBytes(), 50, true);
			String result=new String(b);
			String[] split=result.split(" ");
			for(String s:split){
				String[] str2=s.split("/");
				if(str2.length==3){
					visualPoint vp=new visualPoint(str2[0],Double.parseDouble(str2[2]),i);
					insert(vp);
				}
			}
		}
	}
	public void insert(visualPoint vp){
		for(visualPoint point:visualPoint.ALLVISUALPOINTS){
			if(point.getWord().equals(vp.getWord())){
				if(point.getFrequency()>vp.getFrequency()){
					return;
				}
				else{
					point.setFrequency(vp.getFrequency());
					point.setClusterNum(vp.getClusterNum());
					return;
				}
			}
		}
		visualPoint.ALLVISUALPOINTS.add(vp);
	}
	public JSONObject point2Json(){
		JSONObject json=new JSONObject();
		JSONArray categories=new JSONArray();
		JSONArray nodes=new JSONArray();
		JSONArray links=new JSONArray();
		json.put("type", "force");
		for(int i=0;i<SessionKMeansService.result.size();i++){
			JSONObject category=new JSONObject();
			category.put("name", "聚簇"+i+1);
			category.put("base", "聚簇"+i+1);
			categories.put(category);
		}
		json.put("categories", categories);
		for(int i=0;i<visualPoint.ALLVISUALPOINTS.size();i++){
			visualPoint point=visualPoint.ALLVISUALPOINTS.get(i);
			JSONObject node=new JSONObject();
			node.put("name", point.getWord());
			node.put("value", point.getFrequency());
			node.put("symbolSize", point.getFrequency());
			node.put("category", point.getClusterNum());
			nodes.put(node);
		}
		json.put("nodes", nodes);
		for(int i=0;i<visualPoint.ALLVISUALPOINTS.size();i++){
			for(int j=i+1;j<visualPoint.ALLVISUALPOINTS.size();j++){
				if(visualPoint.ALLVISUALPOINTS.get(i).getClusterNum()==visualPoint.ALLVISUALPOINTS.get(j).getClusterNum()){
					if(visualPoint.ALLVISUALPOINTS.get(i).getFrequency()>8){
						JSONObject link=new JSONObject();
						link.put("source", i);
						link.put("target", j);
						links.put(link);
					}
					else if(WordSimilarity.simWord(visualPoint.ALLVISUALPOINTS.get(i).getWord(), visualPoint.ALLVISUALPOINTS.get(j).getWord())>0){
						JSONObject link=new JSONObject();
						link.put("source", i);
						link.put("target", j);
						links.put(link);
					}
				}
			}
		}
		json.put("links", links);
		return json;
	}
}
