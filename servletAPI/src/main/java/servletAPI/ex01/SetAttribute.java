package servletAPI.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/set")
public class SetAttribute extends HttpServlet {
		//서블릿 스코프
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String ctxMsg = "context에 바인딩 됩니다."; //애플리케이션을 멈추지않는 이상 한번 저장된 어떤 어플에서 볼수있다 : 전체한테 보여준다.
		String sesMsg = "session에 바인딩 됩니다.";// 로그인상태유지
		String reqMsg = "request에 바인딩 됩니다."; //전달해서 받는 값
		ServletContext context = getServletContext();
		HttpSession session = request.getSession();
		context.setAttribute("context", ctxMsg);
		session.setAttribute("session", sesMsg);
		request.setAttribute("request", reqMsg);
		out.print("비인딩을 수행합니다.");
		
	}

}
