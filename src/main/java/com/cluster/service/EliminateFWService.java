package com.cluster.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cluster.domain.FrequentWordSet;
@Service
public class EliminateFWService {
	public List<Integer> note=new ArrayList<Integer>();
	public List<ArrayList<FrequentWordSet<String>>> fuseFW(){
		List<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
		List<ArrayList<FrequentWordSet<String>>> re=new ArrayList<ArrayList<FrequentWordSet<String>>>(); 
		for(int i=0;i<FrequentWordService.allLks.size();i++){
			for(int j=i;j<FrequentWordService.allLks.size();j++){
				if(!note.contains(j)&&calSim(FrequentWordService.allLks.get(i), FrequentWordService.allLks.get(j))>0.5){
					if(note.contains(i)){
						for(ArrayList<Integer> list:result){
							if(list.contains(i)){
								list.add(j);
								note.add(j);
								break;
							}
						}
					}
					else{
						ArrayList<Integer> list=new ArrayList<Integer>();
						list.add(i);list.add(j);
						result.add(list);
						note.add(i);note.add(j);
					}
				}
			}
		}
		for(ArrayList<Integer> list:result){
			ArrayList<FrequentWordSet<String>> r=new ArrayList<FrequentWordSet<String>>();
			for(Integer id:list){
				r.add(FrequentWordService.allLks.get(id));
			}
			re.add(r);
		}
		return re;
	}
	private double calSim(FrequentWordSet<String> list1,FrequentWordSet<String> list2){
		double num=0;
		for(String str:list1){
			if(list2.contains(str))
				num++;
		}
		return num/(Math.sqrt(list1.size())*Math.sqrt(list2.size()));
	}
}
