package jspBase.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection conn; // DB연동하는 객체
	private PreparedStatement pstmt; // DB쿼리문에 쿼리 실행메소드 //select를 " "를 문자열로 인식
	private DataSource dataFactory; // 서버에서 미리연동된걸 필요할때마다 연동해서 사용
	// 커넥션풀

	public MemberDAO() {
		try {
			// JNDI(Java Naming and Directory Interface)에 접근하기 위해 기본 경로(java:/com/env)를 지정
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			// 톰켓 context.xml에서 설정한 name값인 jdbc/oracle을 이용해 톰켓에 미리 연결한 DataSource를 받아온다.
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("커넥션 풀 연결실패!");
		}
	}

	// 회원정보 목록 메서드
	public List listMembers(MemberVO memVo) {
		List memberList = new ArrayList();
		String _name = memVo.getName();
		try {
			conn = dataFactory.getConnection();// 미리연결해 놓은 서버를 가져온다.
			String query = "select * from membertbl";
			if(_name !=null && _name.length() !=0 ) { // 값을 넣고 조회를 누렀을 때
				query+=" where name=?";
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, _name);
			}else { //아무것도 안넣고 조회를 눌렀을 때
				pstmt=conn.prepareStatement(query);
			}
			ResultSet rs = pstmt.executeQuery(); // ResultSet : 테이블 형태로 DB커서 역할을 해준다.
			while (rs.next()) { // rs.next 컬럼을 하나씩 찍어준다. //레코드가 없을 때 까지 반복해서찍어준다.
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				memberList.add(vo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원정보 목록 처리중 에러!!");
			e.printStackTrace();
		}
		return memberList;
	}
}
	