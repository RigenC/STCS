package com.springsimple.domain;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class GraphData {
	public String type;
	public List<Category> categories;
	public List<Link> links;
	public List<Node> nodes;
	public GraphData() {
		// TODO Auto-generated constructor stub
	}
	public GraphData(String type,List<Category> categories,List<Link> links){
		this.type=type;
		this.categories=categories;
		this.links=links;
	}
	public static GraphData parseJson(JSONObject json){
		String type=json.getString("type");
		List<Category> categories=new ArrayList<Category>();
		JSONArray jsoncategories=json.getJSONArray("categories");
		for(int i=0;i<jsoncategories.length();i++){
			JSONObject jsoncategory=jsoncategories.getJSONObject(i);
			Category category=new Category(jsoncategory.getString("name"),jsoncategory.getString("base"));
			categories.add(category);
		}
		List<Node> nodes=new ArrayList<Node>();
		JSONArray jsonnodes=(JSONArray)json.get("nodes");
		for(int i=0;i<jsonnodes.length();i++){
			JSONObject jsonnode=jsonnodes.getJSONObject(i);
			Node node=new Node(jsonnode.getDouble("symbolSize"),jsonnode.getString("name"), jsonnode.getInt("category"), jsonnode.getDouble("value"));
			nodes.add(node);
		}
		List<Link> links=new ArrayList<Link>();
		JSONArray jsonlinks=(JSONArray)json.get("links");
		for(int i=0;i<jsonlinks.length();i++){
			JSONObject jsonlink=jsonlinks.getJSONObject(i);
			Link link=new Link(jsonlink.getInt("source"), jsonlink.getInt("target"));
			links.add(link);
		}
		GraphData data=new GraphData(type, categories, links);
		return data;
	}
}

