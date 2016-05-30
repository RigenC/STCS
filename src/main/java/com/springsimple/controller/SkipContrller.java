package com.springsimple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkipContrller {
	@RequestMapping("/algorithm")
	public ModelAndView toRightSidebar(){
		ModelAndView mv =new ModelAndView();
        mv.setViewName("algorithm");
        return mv;
	}
	@RequestMapping("/demo")
	public ModelAndView toLeftSidebar(){
		ModelAndView mv =new ModelAndView();
        mv.setViewName("demo");
        return mv;
	}
	@RequestMapping("/contact")
	public ModelAndView toElements(){
		ModelAndView mv =new ModelAndView();
        mv.setViewName("contact");
        return mv;
	}
}
