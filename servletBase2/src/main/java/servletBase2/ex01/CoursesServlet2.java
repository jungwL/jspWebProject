package servletBase2.ex01;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/clogin2")
public class CoursesServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init메서드 호출");
	}

	
	public void destroy() {
		System.out.println("destroy메서드 호출");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Enumeration enu=request.getParameterNames();//여러개의 매개변수를 한번에 받을 수 있다. 
		while(enu.hasMoreElements()) { //while문이 더이상 받을게 없는 경우 종료된다.
			String name=(String)enu.nextElement(); //string값으로 치환한다.
			String[] values=request.getParameterValues(name); 
			for(String val : values) { //향상된 for문을 이용한다.
				System.out.println("매개변수 이름 (" + name + "):" + val);
			}
		}
	}

}
