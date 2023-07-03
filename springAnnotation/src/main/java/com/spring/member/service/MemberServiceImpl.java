package com.spring.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;
@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	

	@Override
	public List listMembers() throws DataAccessException {
		List membersList=memberDAO.selectAllMembers();
		return membersList;
	}

	@Override
	public void addMember(MemberVO memberVO) throws DataAccessException {
		memberDAO.addMember(memberVO);
		
	}

	@Override
	public MemberVO findMember(String id) throws DataAccessException {
		MemberVO memberVO=(MemberVO)memberDAO.findMember(id);
		return memberVO;
	}

	@Override
	public void updateMember(MemberVO memberVO) throws DataAccessException {
		memberDAO.updateMember(memberVO);
		
	}

	@Override
	public void delMember(String id) throws DataAccessException {
		memberDAO.delMember(id);
		
	}

}
