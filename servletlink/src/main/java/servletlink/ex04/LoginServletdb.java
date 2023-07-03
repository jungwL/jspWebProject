package servletlink.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logindb")
public class LoginServletdb extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		MemberVO memVo = new MemberVO();
		memVo.setId(user_id);
		memVo.setPwd(user_pw);
		MemberDAO dao = new MemberDAO();
		Boolean result = dao.isExisted(memVo); // 회원정보값이 있으면 true 없으면 false
		out.print("<html><body>");
		if (result) { // 아이디 비번 존재할 경우
			HttpSession session = request.getSession(); // 세션정보가 없으면 새 세션정보를 가져오고 있으면 반환
			session.setAttribute("isLogon", true);
			session.setAttribute("log_id", user_id);
			out.print("<p>안녕하세요 " + user_id + "님이 로그인 하셨습니다.</p>");
			out.print("<a href='show'>회원정보보기</a>"); // show 서블릿으로 이동
		} else { // 존재하지않을 경우
			out.print("<p>회원정보가 틀립니다.");
			out.print("<a href='logindb.html'>다시 로그인하기</a>");
		}
		out.print("</body></html>");
	}
	
}



















