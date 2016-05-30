package com.springsimple.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.json.types.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cluster.domain.FrequentWordSet;
import com.cluster.service.EliminateFWService;
import com.cluster.service.FrequentWordService;
import com.cluster.service.SessionExtractService;
import com.cluster.service.SessionKMeansService;
import com.cluster.service.loadVectorService;
import com.cluster.service.preHandleService;
import com.github.abel533.echarts.json.GsonOption;
import com.springsimple.domain.Message;
import com.springsimple.service.LineOptionService;
import com.springsimple.service.OptionService;
import com.springsimple.service.StatisticService;
import com.springsimple.service.SystemClearService;

@Controller
public class ClusterController {
	@Autowired
	preHandleService prehandle;
	@Autowired
	loadVectorService loadvector;
	@Autowired
	SessionExtractService sessionExtract;
	@Autowired
	FrequentWordService frequentword;
	@Autowired
	EliminateFWService eliminatefw;
	@Autowired
	SessionKMeansService sessionkmeans;
	@Autowired
	OptionService optionservice;
	@Autowired
	LineOptionService lineoptionservice;
	@Autowired
	StatisticService statisticservice;
	@Autowired
	SystemClearService systemclear;
	@RequestMapping(value="upload",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
    public String uploadFile(HttpServletRequest request,   
            HttpServletResponse response) throws IllegalStateException, IOException{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
        // 获得文件：   
        MultipartFile file = multipartRequest.getFile("files");
        // 获得文件名：   
        String filename = file.getOriginalFilename();
        Date date=new Date();
        String localfilename="g:/SCTS/Files/"+filename;
        File source=new File(localfilename);
        JSONObject result=new JSONObject();
        if(!source.exists()){
        	file.transferTo(source);
        	result.put("result", "ok");
        	result.put("path", localfilename);
        }
        else {
        	result.put("result", "ok");
        	result.put("path", localfilename);
        }
        return result.toString();
   	 	
    }
	@RequestMapping(value="preHandle",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
    public String preHandle(@RequestParam("path") String localfilename){
		System.out.println(localfilename);
		JSONObject result=new JSONObject();
		result.put("msg", "ok");
		try {
			prehandle.Transform(localfilename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result.toString();
   	 	
    }
	@RequestMapping(value="loadVector",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
    public String loadVector(){
		JSONObject result=new JSONObject();
		loadvector.loadVector();
		result.put("msg", "ok");
        return result.toString();
    }
	@RequestMapping(value="sessionextract",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
    public String sessionExtract(){
		JSONObject result=new JSONObject();
		result.put("msg", "ok");
		sessionExtract.SessionExtract();
        return result.toString();
    }
	@RequestMapping(value="frequentword",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
    public String frequentword(){
		JSONObject result=new JSONObject();
		result.put("msg", "ok");
        try {
			frequentword.findFrequentWordSet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result.toString();
    }
	@RequestMapping(value="eliminatefw",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
    public String eliminatefw(){
		JSONObject result=new JSONObject();
		List<ArrayList<FrequentWordSet<String>>> re=eliminatefw.fuseFW();
//		List<ArrayList<FrequentWordSet<String>>> re=new ArrayList<ArrayList<FrequentWordSet<String>>>();
//		for(int i=0;i<2;i++){
//			ArrayList<FrequentWordSet<String>> list=new ArrayList<FrequentWordSet<String>>();
//			for(int j=0;j<2;j++){
//				FrequentWordSet<String> fw=new FrequentWordSet<String>();
//				fw.add(i+"_"+j+"_1");fw.add(i+"_"+j+"_2");
//				list.add(fw);
//			}
//			re.add(list);
//		}
		result.put("fwcluster", re);
        return result.toString();
    }
	@RequestMapping(value="sessionkmeans",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
    public String sessionkmeans(@RequestParam("fwcluster") String jsonstr){
		JSONObject result=new JSONObject(jsonstr);
		JSONArray a1=result.getJSONArray("fwcluster");
		List<ArrayList<FrequentWordSet<String>>> re=new ArrayList<ArrayList<FrequentWordSet<String>>>();
		for(Object o:a1){
			JSONArray jo=(JSONArray)o;
			ArrayList<FrequentWordSet<String>> list=new ArrayList<FrequentWordSet<String>>();
			for(Object o2:jo){
				JSONArray joo=(JSONArray)o2;
				FrequentWordSet<String> fw=new FrequentWordSet<String>();
				for(Object o3:joo){
					fw.add((String)o3);
				}
				list.add(fw);
			}
			re.add(list);
		}
		try {
			sessionkmeans.cluster(re);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result.toString();
    }
	@RequestMapping(value="clusterdata",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String visualizeCluster(){
		JSONObject option=optionservice.generateEchartOption();
		return option.toString();
	}
	@RequestMapping(value="loadmessagestatistics",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String loadMessageStatistics(){
		JSONObject option=lineoptionservice.generateJson();
		System.out.println(option.toString());
		return option.toString();
	}
	@RequestMapping(value="loaduserstatistics",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String loaduserstatistics(){
		JSONArray json=statisticservice.statisticUser();
		System.out.println(json.toString());
		return json.toString();
	}
	@RequestMapping(value="systemclear",method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String SystemClear(){
		systemclear.clear();
		JSONObject result=new JSONObject();
		result.put("msg", "ok");
		return result.toString();
	}
	@RequestMapping(value="test",method=RequestMethod.POST,produces="text/html;charset=utf-8")
	@ResponseBody
	public String test(){
		return "";
	}
}
