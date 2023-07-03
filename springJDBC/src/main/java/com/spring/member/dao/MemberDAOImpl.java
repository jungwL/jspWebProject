package com.spring.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO{
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	@Override
	public List selectAllMembers() throws DataAccessException {
		List membersList = new ArrayList();
		String query="select * from membertbl order by joinDate desc";
		membersList=this.jdbcTemplate.query(query, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException { //레코드 갯수만큼 자료처리역할
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setJoinDate(rs.getDate("joinDate"));
 				return memberVO;
			}
		}); //해당 쿼리문을 가져온다.
		System.out.println(query);
		return membersList;
	}
	@Override
	public void addMember(MemberVO memberVO) throws DataAccessException {
		String id=memberVO.getId();
		String pwd=memberVO.getPwd();
		String name=memberVO.getName();
		String email=memberVO.getEmail();
		String query="insert into membertbl(id,pwd,name,email) values('"+ id+ "','" + pwd +"','"+name+"','"+email+"')";
		jdbcTemplate.update(query);
	}

}
