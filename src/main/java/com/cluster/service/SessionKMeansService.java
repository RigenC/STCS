package com.cluster.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cluster.domain.FrequentWordSet;
import com.cluster.domain.SessionVector;
import com.mongo.service.SessionService;


@Service
public class SessionKMeansService {
	@Autowired
	EliminateFWService eliminate;
	@Autowired
	SessionService mongosession;
	private Map<Integer,SessionVector> data=SessionVector.ALLSESSIONS;
	private List<Map<String,Double>> classData=new ArrayList<Map<String,Double>>();//存储每个聚簇的均值中心
	private List<Integer> noises=new ArrayList<Integer>();//存储噪音
	public static List<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
	private List<ArrayList<FrequentWordSet<String>>> initialfw;//存储频繁词集融合结果
	/**
	 * 聚类的主要方法体，聚类的入口
	 * @throws IOException
	 */
	public void cluster(List<ArrayList<FrequentWordSet<String>>> initialfw) throws IOException{
		this.initialfw=initialfw;
		boolean change=true;
		int times=1;
		findInitials();
		calCenter();
		firstCluster();
		while(change){
			change=false;
			System.out.println("Adjust Iteration "+times++ +" times"+preHandleService.sdf.format(new Date()));
			//重新计算每个类的均值
			for(int i=0;i<classData.size();i++){
				ArrayList<Integer> cls=result.get(i);
				//新的均值
				Map<String,Double> newMean=new HashMap<String,Double>();
				//计算均值
				for(Integer index:cls){
					SessionVector SessionVector=this.data.get(index);
					for(Entry<String,Double> entry:SessionVector.getTfidfMap().entrySet()){
						String word=entry.getKey();
						Double value=entry.getValue();
						if(newMean.containsKey(word)){
							newMean.put(word, newMean.get(word)+value);
						}
						else
							newMean.put(word, value);
					}
				}
				int num=cls.size();
				for(Map.Entry<String, Double> entry:newMean.entrySet()){
					newMean.put(entry.getKey(),entry.getValue()/(double)num);
				}
				//终止条件是质心不再发生变化
				if(!newMean.equals(classData.get(i))){
					classData.set(i, newMean);
					change=true;
				}
			}
			for(ArrayList<Integer> cls:result){
				cls.clear();
			}
			for(int i=0;i<SessionVector.ALLSESSIONS.size();i++){
				double max=0.0;
				int clsId=-1;
				for(int j=0;j<classData.size();j++){
					double newMax=calSim(data.get(i).getTfidfMap(), classData.get(j));
					if(clsId==-1||newMax>max){
						clsId=j;
						max=newMax;
					}
				}
				result.get(clsId).add(i);
			}
		}
		BufferedWriter fw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("G:/tempfile/result.txt"),true),"utf-8"));
		for(ArrayList<Integer> cluster:result){
			fw.append("聚簇：");
			fw.newLine();
			fw.append(cluster.toString());
			fw.newLine();
		}
		fw.flush();
		fw.close();
	}
	public void firstCluster(){
		for(int i=0;i<SessionVector.ALLSESSIONS.size();i++){
			double max=0.0;
			int clsId=-1;
			for(int j=0;j<classData.size();j++){
				double sim=calSim(data.get(i).getTfidfMap(), classData.get(j));
				if(clsId==-1||sim>max){
					clsId=j;
					max=sim;
				}
			}
			if(!result.get(clsId).contains(i)){
				result.get(clsId).add(i);
			}
		}
		System.out.println("firstCluster complete"+preHandleService.sdf.format(new Date()));
	}
	public double calSim(Map<String,Double> map1,Map<String,Double> map2){
		double fenzi=0.0;
		for(Entry<String,Double> entry:map1.entrySet()){
			String word=entry.getKey();
			if(map2.containsKey(word)){
				fenzi+=entry.getValue()*map2.get(word);
			}
		}
		double fenmu1=0.0;
		for(Entry<String,Double> entry:map1.entrySet()){
			fenmu1+=Math.pow(entry.getValue(), 2);
		}
		double fenmu2=0.0;
		for(Entry<String,Double> entry:map2.entrySet()){
			fenmu2+=Math.pow(entry.getValue(), 2);
		}
		return fenzi/(Math.sqrt(fenmu1)*Math.sqrt(fenmu2));
	}
	/**
	 * 计算聚簇中心
	 */
	public void calCenter(){
		for(List<Integer> list:result){
			double n=(double)list.size();
			Map<String,Double> mean=new HashMap<String, Double>();
			for(Integer id:list){
				SessionVector SessionVector=data.get(id);
				for(Entry<String,Double> entry:SessionVector.getTfidfMap().entrySet()){
					String word=entry.getKey();
					if(mean.containsKey(word)){
						Double value=mean.get(word)+entry.getValue();
						mean.put(word, value);
					}
					else{
						mean.put(word, entry.getValue());
					}
				}
			}
			for(Entry<String,Double> entry:mean.entrySet()){
				mean.put(entry.getKey(), entry.getValue()/n);
			}
			classData.add(mean);
		}
		System.out.println("calCenter complete"+preHandleService.sdf.format(new Date()));
	}
	
	/**
	 * 根据频繁项集结果获得初始局促中心
	 */
	public void findInitials(){
		for(ArrayList<FrequentWordSet<String>> list:initialfw){
			Set<Integer> result=new HashSet<Integer>();
			for(FrequentWordSet<String> FrequentWordSet:list){
				List<Integer> ids=mongosession.getSessions(FrequentWordSet);
				for(Integer id:ids){
					result.add(id);
				}
			}
			this.result.add(new ArrayList<Integer>(result));
		}
		System.out.println("findInitials complete"+preHandleService.sdf.format(new Date()));
	}
}
