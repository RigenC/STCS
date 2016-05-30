package com.springsimple.controller;

import java.io.File;
import java.io.IOException;

import javax.jws.WebResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.json.GsonOption;
import com.mongo.dao.User;
import com.mongo.service.UserService;
import com.springsimple.service.OptionService;

@Controller
public class HelloController {
	static Logger logger=LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	@Autowired
	private UserService userservice;
     @RequestMapping("/index")
     public ModelAndView index(){
               ModelAndView mv =new ModelAndView();
               mv.addObject("spring", "spring mvc");
               mv.setViewName("index");
               return mv;
     }
     @RequestMapping("/hello")
     public ModelAndView hello(){
    	 ModelAndView mv =new ModelAndView();
         mv.setViewName("hello");
         return mv;
     }
     @RequestMapping("/user")
     public ModelAndView finduser(){
    	 ModelAndView mv=new ModelAndView();
    	 User user=userservice.findUserByNum("510872221");
    	 System.out.println(user.getNames().toString());
    	 mv.setViewName("hello");
    	 return mv;
     }

}