package MVCproject.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	MemberDAO dao;
	
	@Override
	public void init() throws ServletException {	// 서버가 가동될 때 한 번 수행됨
		dao=new MemberDAO();	// MemberDAO 객체 생성
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage=null;	// 이동할 페이지를 담은 변수
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action=request.getPathInfo();  // **요청 매핑이름을 가져옴
		System.out.println("요청 매핑이름 : "+action);
		
		if(action == null || action.equals("/listMembers.do")) {	// url에 '/member' or '/member/listMember.do'일 경우 회원목록 보여줌
			List<MemberVO> membersList=dao.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage="/viewMember/listMembers.jsp";
			
		} else if(action.equals("/addMember.do")) {	// 회원 추가
			String id=request.getParameter("id");
			String pwd=request.getParameter("pwd");
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			MemberVO memVo=new MemberVO(id,pwd, name, email);
			dao.addMember(memVo);
			request.setAttribute("msg", "addMember");
			nextPage="/member/listMembers.do";
			
		} else if(action.equals("/memberForm.do")) {	// 회원가입
			nextPage="/viewMember/memberForm.jsp";
			
		} else if(action.equals("/modMemberForm.do")) {  //회원정보 수정
			String id=request.getParameter("id");
			MemberVO memFindInfo=dao.findMember(id);
			request.setAttribute("memFindInfo", memFindInfo);
			nextPage="/viewMember/modMemberForm.jsp";
			
		} else if(action.equals("/modMember.do")) { //회원가입 수정
			String id=request.getParameter("id");
			String pwd=request.getParameter("pwd");
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			MemberVO memVo=new MemberVO(id,pwd, name, email); 
			dao.modMember(memVo);
			request.setAttribute("msg", "modified");
			nextPage="/member/listMembers.do";
			
			
		} else if(action.equals("/delMember.do")) {	 // 회원정보 삭제
			String id=request.getParameter("id");
			dao.deleteMember(id);
			request.setAttribute("msg", "deleted");
			nextPage="/member/listMembers.do"; //컨트롤러가 다시 호출된다.(if문에 다시걸린다.)
		}else {
			List<MemberVO> membersList=dao.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage="/viewMember/listMembers.jsp";
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);  // 포워딩할 페이지
		dispatcher.forward(request, response);
	}

}
