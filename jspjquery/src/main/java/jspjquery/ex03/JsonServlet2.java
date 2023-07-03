package jspjquery.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/json2")
public class JsonServlet2 extends HttpServlet {
	
    
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
		JSONObject totalMember = new JSONObject();//모든 회원정보가 들어갈 제이슨 객체
		JSONArray memberAarry = new JSONArray(); // 제이슨 객체를 저장할 제이슨 배열
		JSONObject memberInfo = new JSONObject(); //한명의 회원정보가 들어갈 제이슨 객체
		memberInfo.put("name", "홍길동");
		memberInfo.put("age", 50);
		memberInfo.put("job", "도적");
		memberAarry.add(memberInfo);
		memberInfo=new JSONObject();
		memberInfo.put("name", "흥민손");
		memberInfo.put("age", 31);
		memberInfo.put("job", "싸커플레이어");
		memberAarry.add(memberInfo);
		memberInfo=new JSONObject();
		memberInfo.put("name", "이주한");
		memberInfo.put("age", 28);
		memberInfo.put("job", "개발자");
		memberAarry.add(memberInfo);
		totalMember.put("members", memberAarry);
		String jsonInfo=totalMember.toJSONString(); //제이슨 객체를 문자열로 변환
		out.print(jsonInfo);
	}
}
