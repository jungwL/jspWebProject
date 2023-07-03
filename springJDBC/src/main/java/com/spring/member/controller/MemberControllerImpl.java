package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController{
   private MemberService memberService;
   
   
   public void setMemberService(MemberService memberService) {
      this.memberService = memberService;
   }

   @Override
   public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName=getViewName(request);
      List membersList=memberService.listMembers();
      ModelAndView mav= new ModelAndView(viewName);
      mav.addObject("membersList",membersList);
      return mav;
   }

   @Override
   public ModelAndView addMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   request.setCharacterEncoding("utf-8");
	   MemberVO memberVO=new MemberVO();
	   System.out.println("하이");
	   String id=request.getParameter("id");
	   String pwd=request.getParameter("pwd");
	   String name=request.getParameter("name");
	   String email=request.getParameter("email");
	   memberVO.setId(id);
	   memberVO.setPwd(pwd);
	   memberVO.setName(name);
	   memberVO.setEmail(email);
	   memberService.addMember(memberVO);
	   List membersList=memberService.listMembers();
	   ModelAndView mav=new ModelAndView();
	   mav.addObject("membersList",membersList);
      return mav;
   }

   @Override
   public ModelAndView memForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName=getViewName(request);
      ModelAndView mav = new ModelAndView();
      mav.setViewName(viewName);
      System.out.println(viewName);
      return mav;
   }
   //요청명과 동일한 jsp로 표시하기
      private String getViewName(HttpServletRequest request) throws Exception {
         String contextPath=request.getContextPath();
         String uri=(String)request.getAttribute("javax.servlet.include.request_uri");
         if(uri == null ||uri.trim().equals("")) {
            uri=request.getRequestURI();
         }
         int begin=0,end;
         if(!((contextPath == null) || "".equals(contextPath))) {
            begin=contextPath.length();
         }
         if(uri.indexOf(";") != -1) {
            end=uri.indexOf(";");
         }else if(uri.indexOf("?") !=-1) {
            end=uri.indexOf("?");
         }else {
            end=uri.length();
         }
         String fileName=uri.substring(begin,end);
         if(fileName.lastIndexOf(".") !=-1) {
            fileName=fileName.substring(0,fileName.lastIndexOf("."));
         }
         if(fileName.lastIndexOf("/") != -1) {
            fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
         }
         return fileName;
      }
   
}