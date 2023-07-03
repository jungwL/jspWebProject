package servletAPI.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first/*")
public class TestServlet2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String context = request.getContextPath(); // 컨텍스트 이름
		String url = request.getRequestURL().toString(); // 전체(주소) URL경로를 돌려준다.
		String mapping = request.getServletPath(); // 서블릿 매핑이름
		String uri = request.getRequestURI(); // URI : context이름 매핑이름까지만(식별: 스포츠페이지 )
		out.print("<html>");
		out.print("<head>");
		out.print("<title> 서블릿의 여러가지 URL 패턴2</title>");
		out.print("</head>");
		out.print("<body bgcolor='yellow'>");
		out.print("<p>TestServlet2입니다.</p>");
		out.print("<p>컨텍스트명 : " + context + "<p>");
		out.print("<p>전체경로 : " + url + "<p>");
		out.print("<p>매핑명 : " + mapping + "<p>");
		out.print("<p>URI명 : " + uri + "<p>");
		out.print("</body");
		out.print("</html>");
	}

}
