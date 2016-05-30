package com.cluster.domain;

import java.util.HashMap;
import java.util.Map;

import com.cluster.service.WordSimilarity;
import com.mongo.service.WordService;

public class WordSimilarityNet extends HashMap{
	private static Map<WordKeyPair,Double> wordSimilaritymap=new HashMap<WordKeyPair, Double>();
	public static Map getInstance(){
		if(wordSimilaritymap!=null)
			return wordSimilaritymap;
		else{
			synchronized(WordService.class){
				if(wordSimilaritymap==null){
					wordSimilaritymap=new HashMap<WordKeyPair, Double>();
					return wordSimilaritymap;
				}
			}
		}
		return wordSimilaritymap;
	}
	public static void putSimilarity(String word1,String word2,Double similarity){
		wordSimilaritymap.put(new WordKeyPair(word1, word2), similarity);
	}
	public static double getSimilarity(String word1,String word2){
		WordKeyPair key=new WordKeyPair(word1, word2);
		if(wordSimilaritymap.containsKey(key)){
			return wordSimilaritymap.get(key);
		}
		else{
			Double similarity=WordSimilarity.simWord(word1, word2);
//			wordSimilaritymap.put(new WordKeyPair(word1, word2), similarity);
			return similarity;
		}
	}
	public static void clearMap(){
		wordSimilaritymap.clear();
	}
}