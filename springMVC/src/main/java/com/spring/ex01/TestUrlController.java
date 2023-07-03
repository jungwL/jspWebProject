package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//매핑된 요청에 대해 컨트롤러 기능을 수헹
public class TestUrlController  implements Controller{
	
	// doHandle역할 ModelAndView:포워딩역할
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav= new ModelAndView("index.jsp"); //포워딩 역할
		return mav;
	} 
	
}
