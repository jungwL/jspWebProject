package testRecipe.shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/shopping/*")
public class ShoppingController extends HttpServlet {
	ShoppingService shoppingService;
	ShoppingVO shoppingVO;
	
	public void init(ServletConfig config) throws ServletException {
		shoppingService = new ShoppingService();
		shoppingVO = new ShoppingVO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = "";
		PrintWriter out;
		HttpSession session;
		String action = request.getPathInfo();// 요청명 가져오기
		System.out.println("요청이름: " + action);
		try {
				List<ShoppingVO> shoppingList = new ArrayList<ShoppingVO>();
				if(action == null || action.equals("")) {
					
				}else if (action.equals("/matarial.do")) {
					String _section = request.getParameter("section");
					String _pageNum = request.getParameter("pageNum");
					int section = Integer.parseInt((_section == null)?"1":_section);
					int pageNum = Integer.parseInt((_pageNum == null)?"1":_pageNum);
					Map<String, Integer> pagingMap = new HashMap<String, Integer>();
					pagingMap.put("section", section);
					pagingMap.put("pageNum", pageNum);
					Map matarialMap = shoppingService.listMatarial(pagingMap);
					matarialMap.put("section", section);
					matarialMap.put("pageNum", pageNum);
					request.setAttribute("matarialMap", matarialMap);
					nextPage = "/RecipeMall/viewShopping/matarial.jsp";
				}
				
				else if(action.equals("/bestReview.do")) {
					shoppingList = shoppingService.bestReview();
					request.setAttribute("reviewList", shoppingList);
					nextPage = "/RecipeMall/viewShopping/shopping_best.jsp";
				}
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}catch (Exception e) {
			System.out.println("요청 처리 중 에러");
			e.printStackTrace();
		}
	}
}
