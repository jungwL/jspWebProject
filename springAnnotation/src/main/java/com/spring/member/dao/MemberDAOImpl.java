package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.member.vo.MemberVO;
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public List<MemberVO> selectAllMembers() throws DataAccessException {//회원정보리스트 불러오는메서드
		List<MemberVO> membersList=null;
		membersList=sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public void addMember(MemberVO memberVO) throws DataAccessException { //회원 추가메서드
		sqlSession.insert("mapper.member.insertMember",memberVO);
	}
	@Override
	public MemberVO findMember(String id) throws DataAccessException {
		MemberVO memberVO=(MemberVO)sqlSession.selectOne("mapper.member.findMember",id);
		return memberVO;
	}
	@Override
	public void updateMember(MemberVO memberVO) throws DataAccessException {
		sqlSession.update("mapper.member.updateMember",memberVO);
		
	}
	@Override
	public void delMember(String id) throws DataAccessException {
		sqlSession.delete("mapper.member.delMember",id);
		
	}

	

}
