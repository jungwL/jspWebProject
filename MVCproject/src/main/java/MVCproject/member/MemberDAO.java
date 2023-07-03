package MVCproject.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;	// 서버 가동시 db정보 전달, 클라 요청할 때마다 db연결
	
	public MemberDAO() {
		try {
			// JNDI(Java Naming and Directory Interface)를 이용
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	// 회원 목록 메소드
	public List<MemberVO> listMembers() {
		List<MemberVO> memberList=new ArrayList();
		try {
			conn=dataFactory.getConnection();
			String query="select * from membertbl order by joinDate desc";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery(query);
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				Date joinDate=rs.getDate("joinDate");
				MemberVO memVo=new MemberVO(id,pwd,name,email,joinDate);
				memberList.add(memVo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("DB 조회 중 오류");
			e.printStackTrace();
		}
		
		return memberList;
	}
	
	// 회원 추가 메소드
	public void addMember(MemberVO memVo) {
		try {
			conn=dataFactory.getConnection();
			String id=memVo.getId();
			String pwd=memVo.getPwd();
			String name=memVo.getName();
			String email=memVo.getEmail();
			String query="insert into membertbl(id,pwd,name,email) values(?,?,?,?)";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원 등록 중 오류 발생");
			e.printStackTrace();
		}
	}
	
	//수정할 회원정보 찾기
	public MemberVO findMember(String _id) {
		MemberVO memFind=null;
		try {
			conn=dataFactory.getConnection();
			String query="select * from membertbl where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, _id);
			System.out.println(query);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			String id=rs.getString("id");
			String pwd=rs.getString("pwd");
			String name=rs.getString("name");
			String email=rs.getString("email");
			Date joinDate=rs.getDate("joinDate");
			memFind=new MemberVO(id, pwd, name, email, joinDate);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원 정보 탐색 중 오류 발생");
			e.printStackTrace();
		}
		return memFind;
	}
	//화원정보 수정 메서드
	
	public void modMember(MemberVO memVo) {
		String id=memVo.getId();
		String pwd=memVo.getPwd();
		String name=memVo.getName();
		String email=memVo.getEmail();
		try {
			conn=dataFactory.getConnection();
			String query="update membertbl set pwd=? ,name=?,email=? where id=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원정보 수정 중 에러!!");
			e.printStackTrace();
		}

	}
	//회원정보 삭제 메서드
	public void deleteMember(String id) {
		try {
			conn=dataFactory.getConnection();
			String query="delete from membertbl where id=? ";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원정보 삭제중 에러!");
			e.printStackTrace();
		}
	}
}
