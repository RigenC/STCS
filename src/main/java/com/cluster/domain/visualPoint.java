package com.cluster.domain;

import java.util.ArrayList;
import java.util.List;

public class visualPoint implements Comparable<visualPoint>{
	public static List<visualPoint> ALLVISUALPOINTS=new ArrayList<visualPoint>();
	private String word;
	private double frequency;
	private int clusterNum;
	public visualPoint(String word, double frequency, int cluterNum) {
		super();
		this.word = word;
		this.frequency = frequency;
		this.clusterNum = cluterNum;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public double getFrequency() {
		return frequency;
	}
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	public int getClusterNum() {
		return clusterNum;
	}
	public void setClusterNum(int cluterNum) {
		this.clusterNum = cluterNum;
	}
	public boolean equals(Object obj){
		visualPoint vp=(visualPoint)obj;
		return this.word.equals(vp.getWord());
	}
	public int compareTo(visualPoint o) {
		if(this.clusterNum==o.getClusterNum()&&this.frequency==o.getFrequency())
			return 0;
		else if (this.frequency>o.getFrequency())
			return 1;
		else 
			return -1;
	}
}
