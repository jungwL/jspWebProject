package jspjquery.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax2")
public class AjaxTest2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		out.print("<main>");
		out.print("<book>");
		out.print("<title>jQuery입문</title>");
		out.print("<writer>한빛미디어 : 이준서</writer>");
		out.print("<image>http://127.0.0.1:8090/jspjquery/images/petit.jpg</image>");
		out.print("</book>");
		out.print("<book>");
		out.print("<title>node.js 입문</title>");
		out.print("<writer>한빛미디어 : 이주한</writer>");
		out.print("<image>http://127.0.0.1:8090/jspjquery/images/node.jpg</image>");
		out.print("</book>");
		out.print("</main>");
	}
}
