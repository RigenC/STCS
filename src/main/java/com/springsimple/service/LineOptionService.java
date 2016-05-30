package com.springsimple.service;

import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsimple.domain.Message;

@Service
public class LineOptionService {
	@Autowired
	StatisticService statisticservice;
	public JSONObject generateJson(){
		Message message=statisticservice.statisticMessage();
		JSONArray xdata=new JSONArray();
		JSONArray ydata=new JSONArray();
		for(Entry<String,Integer> entry:message.getNums().entrySet()){
			xdata.put(entry.getKey());
			ydata.put(entry.getValue());
		}
		JSONObject option=new JSONObject();
		option.put("title", new JSONObject("{\"text\":\"消息时序图\"}"));
		option.put("tooltip", new JSONObject("{\"trigger\":\"axis\"}"));
		option.put("toolbox", new JSONObject("{\"feature\":{\"saveAsImage\":{}}}"));
		option.put("grid", new JSONObject("{\"left\": \"3%\",\"right\": \"4%\",\"bottom\": \"3%\",\"containLabel\": true}"));
		option.put("legend", new JSONObject("{\"data\":[\"消息数量\"]}"));
		JSONObject xAxis=new JSONObject();
		xAxis.put("type", "category");xAxis.put("boundaryGap", false);xAxis.put("data", xdata);
		option.put("xAxis", new JSONArray().put(xAxis));
		option.put("yAxis", new JSONArray("[{\"type\" : \"value\"}]"));
		JSONObject serie=new JSONObject();
		serie.put("name", "消息时序");serie.put("type", "line");serie.put("stack", "总量");
		serie.put("areaStyle", new JSONObject("{\"normal\": {}}"));serie.put("data", ydata);
		option.put("series", new JSONArray().put(serie));
		return option;
	}
}
