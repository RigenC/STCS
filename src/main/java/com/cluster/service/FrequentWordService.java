package com.cluster.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cluster.domain.FrequentWordSet;
import com.cluster.domain.Threshold;
import com.cluster.domain.Word;
import com.mongo.service.WordService;

@Service
public class FrequentWordService {
	@Autowired
	WordService mongoword;
	
	public static List<FrequentWordSet<String>> allLks=new ArrayList<FrequentWordSet<String>>();
	public static Map<FrequentWordSet<String>,Integer> rankLk=new HashMap<FrequentWordSet<String>,Integer>();

	public void findFrequentWordSet() throws IOException{
		List<List<FrequentWordSet<String>>> topk=new ArrayList<List<FrequentWordSet<String>>>();
		Set<String> allword=Word.ALLWORDS;
		Iterator<String> it=allword.iterator();
		List<FrequentWordSet<String>> c1=new ArrayList<FrequentWordSet<String>>();
		while(it.hasNext()){
			FrequentWordSet<String> set=new FrequentWordSet<String>();
			String word=it.next();
			set.add(word);
			int num=mongoword.getWordFrequency(set);
			if(num<40)
				continue;
			else{
				c1.add(set);
			}
		}
		allLks.addAll(c1);
		System.out.println("1阶频繁项集："+c1.size());
//		printToFile(1, c1);
		//以上部分是找到一阶频繁项集，以下是迭代过程
		//根据Lk-1计算Ck
		//对Ck中的每个元素计算其k-1阶子集，查看其是否在Lk-1中，修剪Ck
		//查看Ck中的元素的支持度是否大于最小值
		List<FrequentWordSet<String>> Lk_1=c1;
		for(int i=2;Lk_1.size()!=0;i++){
			List<FrequentWordSet<String>> Ck=calCandidate(Lk_1);
			deleteInvalid(Lk_1,Ck);
			Iterator<FrequentWordSet<String>> iterator=Ck.iterator();
			while(iterator.hasNext()){
				FrequentWordSet<String> list=iterator.next();
				int num=mongoword.getWordFrequency(list);
				if(num<35){
					iterator.remove();
				}
			}
			//目前Ck是k阶频繁项集，Lk_1是k-1阶频繁项集，以下是寻找Lk-1中的最大频繁项集
			//对Ck中的元素求k-1阶子集，如果某个k-1阶子集在Lk-1中存在，则删除Lk-1中的这个元素，因为不是最大频繁项集
			Iterator<FrequentWordSet<String>> iterator2=Lk_1.iterator();
			while(iterator2.hasNext()){
				FrequentWordSet<String> list=iterator2.next();
				List<FrequentWordSet<String>> childLists=new ArrayList(getChildList(list));
				for(FrequentWordSet<String> childList:childLists){
					if(Lk_1.contains(childList))
						iterator2.remove();
				}
			}
			System.out.println(i+"阶频繁项集："+Ck.size());
			Collections.sort(Ck);
			Lk_1=Ck;
			allLks.addAll(Lk_1);
		}
		getMaxLks();
	}
	/**
	 * 直接删减策略
	 * @param Lk_1
	 * @param Ck
	 */
	public  void deleteInvalid(List<FrequentWordSet<String>> Lk_1,List<FrequentWordSet<String>> Ck){
		List<FrequentWordSet<String>> todelete=new ArrayList<FrequentWordSet<String>>();
		Iterator<FrequentWordSet<String>> it=Ck.iterator();
		while(it.hasNext()){
			FrequentWordSet<String> list=it.next();
			List<FrequentWordSet<String>> childLists=new ArrayList(getChildList(list));
			for(FrequentWordSet<String> childList:childLists){
				if(!Lk_1.contains(childList))
					todelete.add(list);
			}
		}
		for(FrequentWordSet<String> list:todelete){
			Lk_1.remove(list);
		}
	}
	/**
	 * 获取k-1维子集
	 * @param list
	 * @return 返回所有K-1阶子集
	 */
	public Set<FrequentWordSet<String>> getChildList(FrequentWordSet<String> list){
		Set<FrequentWordSet<String>> result=new HashSet<FrequentWordSet<String>>();
		if(list.size()<2)
			return result;
		for(String str:list){
			FrequentWordSet<String> copy=new FrequentWordSet<String>();
			copy.addAll(list);
			copy.remove(str);
			copy.sort();
			result.add(copy);
		}
		return result;
	}
	/**
	 * 获取1阶到K-1阶的所有子集
	 * @param list
	 * @return
	 */
	public Set<FrequentWordSet<String>> getAllChildList(FrequentWordSet<String> list){
		Set<FrequentWordSet<String>> result=new HashSet<FrequentWordSet<String>>();
		Set<FrequentWordSet<String>> childlist=getChildList(list);
		if(childlist.size()>0){
			result.addAll(childlist);
			for(FrequentWordSet<String> child:childlist){
				if(child.size()>0)
					result.addAll(getAllChildList(child));
				else 
					return result;
			}
		}
		return result;
	}
	public List<FrequentWordSet<String>> calCandidate(List<FrequentWordSet<String>> Lk){
		List<FrequentWordSet<String>> result=new ArrayList<FrequentWordSet<String>>();
		for(int i=0;i<Lk.size();i++){
			for(int j=i+1;j<Lk.size();j++){
				FrequentWordSet<String> candidate=Lk.get(i).calAssociate(Lk.get(j));
				if(candidate!=null){
					result.add(candidate);
				}
			}
		}
		return result;
	}
	/**
	 * 计算完频繁项集后直接遍历所有计算最大频繁项集，对比用
	 * @throws IOException 
	 */
	public void getMaxLks() throws IOException{
		System.out.println("allLks数量："+allLks.size());
		Iterator<FrequentWordSet<String>> it=allLks.iterator();
		List<FrequentWordSet<String>> tobedeleted=new ArrayList<FrequentWordSet<String>>();//需要被剔除的频繁项集，包括长度小于4的和
		while(it.hasNext()){
			FrequentWordSet<String> list=it.next();
			if(list.size()<Threshold.fwsize){
				tobedeleted.add(list);
			}
			List<FrequentWordSet<String>> allchilds=new ArrayList(getAllChildList(list));
			for(FrequentWordSet<String> child:allchilds){
				if(allLks.contains(child))
					tobedeleted.add(child);
			}
		}
		System.out.println("待删除的数量:"+tobedeleted.size());
		allLks.removeAll(tobedeleted);
		System.out.println("allLks数量："+allLks.size());
//		for(FrequentWordSet<String> list:allLks){
//			int num=mongoword.getWordFrequency(list);
//			rankLk.put(list, num);
//		}
		//如果不提取top-k，则没有必要对最大频繁词集进行排序，rankLk已经是result
//		List<Map.Entry<FrequentWordSet<String>, Integer>> demolist=new ArrayList(rankLk.entrySet());
//		Collections.sort(demolist,new Comparator() {
//			public int compare(Object o1, Object o2) {  
//                Map.Entry obj1 = (Map.Entry) o1;  
//                Map.Entry obj2 = (Map.Entry) o2;  
//                return ((Integer) obj2.getValue()).compareTo((Integer)obj1.getValue());  
//                }  
//		});
//		List<FrequentWordSet<String>> printlist=new ArrayList<FrequentWordSet<String>>();
//		for(Map.Entry<FrequentWordSet<String>, Integer> entry:demolist)
//			printlist.add(entry.getKey());
	}
}
