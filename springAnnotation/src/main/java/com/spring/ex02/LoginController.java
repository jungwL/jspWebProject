package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController") //로그인 컨트롤러 빈 생성
public class LoginController {
	
		@RequestMapping(value = {"/test/loginForm.do","/test/loginForm2.do"},method = RequestMethod.GET) //loginForm.do, loginForm2.do로 찾아가세여~
		public ModelAndView loginForm(HttpServletRequest request,HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("loginForm");
			return mav;
		}
		
		//로그인
		/*@RequestMapping(value = "/test/login.do", method = {RequestMethod.POST,RequestMethod.GET})
		public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception {
			ModelAndView mav= new ModelAndView();
			mav.setViewName("result");
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			mav.addObject("id",id);
			mav.addObject("pwd",pwd);
			return mav;
		}*/
		/*@RequestMapping(value = "/test/login.do", method = {RequestMethod.POST,RequestMethod.GET})
		public ModelAndView login(@RequestParam("id") String id,@RequestParam("pwd") String pwd, HttpServletRequest request,HttpServletResponse response) throws Exception {
			ModelAndView mav= new ModelAndView();
			mav.setViewName("result");
			mav.addObject("id",id);
			mav.addObject("pwd",pwd);
			return mav;
		}*/
		/*@RequestMapping(value = "/test/login.do", method = {RequestMethod.POST,RequestMethod.GET}) //기본값 required = true 무조건 값을 받는다 없으면 에러뜸 , false일때는 : 전달값은 받아서 처리하고 매개변수가 안날라오면 null값
		public ModelAndView login(@RequestParam(value = "id", required = true) String id,@RequestParam(value = "pwd", required = true) String pwd,
			@RequestParam(value = "email" ,required = false) String email,	HttpServletRequest request,HttpServletResponse response) throws Exception {
			ModelAndView mav= new ModelAndView();
			mav.setViewName("result");
			mav.addObject("id",id);
			mav.addObject("pwd",pwd);
			mav.addObject("email",email);
			return mav;
		}*/
		//Map을 이용 RequestParam Map<String, String> memberInfo
		/*@RequestMapping(value = "/test/login.do", method = {RequestMethod.POST,RequestMethod.GET}) //기본값 required = true 무조건 값을 받는다 없으면 에러뜸 , false일때는 : 전달값은 받아서 처리하고 매개변수가 안날라오면 null값
		public ModelAndView login(@RequestParam Map<String, String> memberInfo, HttpServletRequest request,HttpServletResponse response) throws Exception {
			ModelAndView mav= new ModelAndView();
			mav.setViewName("result");
			String id=memberInfo.get("id");
			String pwd=memberInfo.get("pwd");
			mav.addObject("id",id);
			mav.addObject("pwd",pwd);
			return mav;
		}*/
		//ModelAttribute("memberInfo") : 전달되는 매개변수값을 loginVO에 설정된 필드 명으로 자동 설정된다.
		/*@RequestMapping(value = "/test/login.do", method = {RequestMethod.POST,RequestMethod.GET}) //기본값 required = true 무조건 값을 받는다 없으면 에러뜸 , false일때는 : 전달값은 받아서 처리하고 매개변수가 안날라오면 null값
		public ModelAndView login(@ModelAttribute("memberInfo") LoginVO loginVO, HttpServletRequest request,HttpServletResponse response) throws Exception {
			ModelAndView mav= new ModelAndView(); 
			mav.setViewName("result");
			return mav;
		}*/
		//Model클래스 이용 메서드 호출시 jsp에 값을 바로 바인딩하여 전딜할때 사용
		@RequestMapping(value = "/test/login.do", method = {RequestMethod.POST,RequestMethod.GET}) //기본값 required = true 무조건 값을 받는다 없으면 에러뜸 , false일때는 : 전달값은 받아서 처리하고 매개변수가 안날라오면 null값
		public String login(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("utf-8");
			model.addAttribute("id","hong");
			model.addAttribute("pwd","1234");
			model.addAttribute("email","hong@naber.com");
			return "result2";
		}
}
