package servletlink.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sess2")
public class SessionTest2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(); 
		out.print("세션아이디 : " + session.getId() + "<br>"); //세션의 아이디를 가져온다.
		out.print("최초 세션 생성 시간 : " + new Date(session.getCreationTime()) + "<br>");//세선 생성 시간을 가져온다.
		out.print("최근 세션 접근 시간 : " +  new Date(session.getLastAccessedTime())+ "<br>");
		session.setMaxInactiveInterval(10);     // 초단위 세션 유효시간 설정
	
		out.print("세션 유효 시간 : " + session.getMaxInactiveInterval()+ "<br>"); //1800초
		if(session.isNew()) {
			out.print("새 세션이 만들었습니다.");
		}
	}

}
