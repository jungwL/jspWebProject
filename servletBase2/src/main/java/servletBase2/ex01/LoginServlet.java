package servletBase2.ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init메서드 호출");
	}

	public void destroy() {
		System.out.println("destroy메서드 호출");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //한글 방식으로 날라로면 utf-8
		String user_id=request.getParameter("user_id"); // getParameter : 매개변수를 얻어온다.
		String user_pw=request.getParameter("user_pw"); //user_pw매개변수에 값이 저장된다.
		System.out.println("요청받은 아이디 : " + user_id);
		System.out.println("요청받은 비밀번호 : " + user_pw);
	}

}
