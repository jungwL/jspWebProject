package com.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.spring.member.vo.MemberVO;

public interface MemberController {
	   public ModelAndView listMembers(HttpServletRequest request,HttpServletResponse response) throws Exception;
	   
	   public ModelAndView addMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request,HttpServletResponse response) throws Exception;
	   
	   public ModelAndView memForm(HttpServletRequest request,HttpServletResponse response) throws Exception;
	   
	   public ModelAndView modMemberForm(@RequestParam("id") String id, HttpServletRequest request,HttpServletResponse response) throws Exception;

	   public ModelAndView updateMember(@ModelAttribute("memberVO") MemberVO memberVO ,HttpServletRequest request,HttpServletResponse response) throws Exception;

	   public ModelAndView delMember(@RequestParam("id") String id , HttpServletRequest request,HttpServletResponse response) throws Exception;

	}