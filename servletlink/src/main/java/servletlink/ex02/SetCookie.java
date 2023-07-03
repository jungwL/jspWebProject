package servletlink.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/set")
public class SetCookie extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Date today=new Date();
		Cookie ck= new Cookie("cookiesTest", URLEncoder.encode("주말 잘보내세여","utf-8")); //(쿠키 이름 ,  쿠키 값)
		//ck.setMaxAge(24*60*60); //쿠키의 유효시간을 준다. (하루동안)
		ck.setMaxAge(-1); //세션 쿠키
		response.addCookie(ck);
		out.print("쿠키가 생성된 시간 : " + today + "<br>");
		out.print("쿠키가 저장되었습니다.");
	}

}
