package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;
@Controller("memberController")
public class MemberControllerImpl extends MultiActionController implements MemberController{
	
	@Autowired    //주입하는어노테이션(memberController에 memberService주입)
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
   
	@Override
   @RequestMapping(value = "/member/listMembers.do" , method = RequestMethod.GET)
   public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName=getViewName(request);
      List membersList=memberService.listMembers();
      ModelAndView mav= new ModelAndView(viewName);
      mav.addObject("membersList",membersList);
      return mav;
   }

   @Override
   @RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
   public ModelAndView addMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
	   request.setCharacterEncoding("utf-8");
	   //MemberVO memberVO=new MemberVO(); 주입됨위에서
	   /*bind(request, memberVO); //요청받은 값을 바인딩해준다. MemberVO랑 memForm 에input에서 요청받은값이랑 일치할때만
	   memberService.addMember(memberVO);
	   List membersList=memberService.listMembers();
	   ModelAndView mav=new ModelAndView("listMembers");
	   mav.addObject("membersList",membersList);*/
	   memberService.addMember(memberVO);
	   ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
	   return mav;
   }

   @Override
   @RequestMapping(value = "/member/memForm.do", method = RequestMethod.GET)
   public ModelAndView memForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName=getViewName(request);
      ModelAndView mav = new ModelAndView();
      mav.setViewName(viewName);
      System.out.println(viewName);
      return mav;
   }
   //수정
	@Override
	@RequestMapping(value = "/member/modMemberForm.do" , method = RequestMethod.GET)
	public ModelAndView modMemberForm(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {//수정창
		String viewName=getViewName(request);
		memberVO=memberService.findMember(id);
		ModelAndView mav=new ModelAndView(viewName);
		mav.addObject("member",memberVO);
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/updateMember.do", method = RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.updateMember(memberVO);
		ModelAndView mav= new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/delMember.do", method = RequestMethod.GET)
	public ModelAndView delMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		memberService.delMember(id);
		ModelAndView mav= new ModelAndView("redirect:/member/listMembers.do");
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