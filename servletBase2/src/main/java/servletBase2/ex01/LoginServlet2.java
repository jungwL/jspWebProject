package servletBase2.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init메서드 호출");
	}

	public void destroy() {
		System.out.println("destroy메서드 호출");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); //컨텍스타입
		PrintWriter out=response.getWriter(); //PrintWriter: 텍스트문서를 브라우저에 보내는 역활
		String id=request.getParameter("user_id");
		String pw=request.getParameter("user_pw");
		String data="<html>";
		data+="<body>";
		data+="<h2>" + id + "님 환영합니다~!~!~</h2>";
		data+="</body>";
		data+="</html>";
		out.print(data);
		/*out.print("<html>");    //""안의 문자를 브라우저로 던져준다.
		out.print("<body>"); 
		out.print("<h2>" + id + "님 환영합니다~!~!~</h2>");
		out.print("</body>"); 
		out.print("</html>"); */
	}

}
