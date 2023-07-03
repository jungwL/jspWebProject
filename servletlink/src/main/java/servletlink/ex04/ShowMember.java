package servletlink.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/show")
public class ShowMember extends HttpServlet {
	//로그인 했을 경우
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id="";
		Boolean isLogon=false;
		HttpSession session=request.getSession(false);
		if(session != null) { // 세션이 생성된경우 
			isLogon=(Boolean)session.getAttribute("isLogon");
			if(isLogon==true) { //세션을 가지고있는경우
				id=(String)session.getAttribute("log_id");
				out.print("<html><body>");
				out.print("<p>" + id + "님은 골드 회원이라 할인권이 있습니다.</p>");
				out.print("<p>할인권을 이용해서 쇼핑을 즐겨보세요</p>");
				out.print("</body></html>");
			}else { //세션 유효시간(30분)이 지난경우
				response.sendRedirect("logindb.html");
			}
		}else { // 그냥 접속한 경우
			out.print("<script>alert('로그인한 후에 이용해주세요'); location.href='logindb.html'; </script>"); //포워딩 alert후 logindb.html로 이동
		}
	}

}
