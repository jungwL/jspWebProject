package testRecipe.shopping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingService {
	ShoppingDAO shoppingDAO;
	
	public ShoppingService() {
		shoppingDAO = new ShoppingDAO();
	}
	
	public Map listMatarial(Map<String, Integer> pagingMap) {
		Map matarialMap = new HashMap();
		List<ShoppingVO> matarialList = shoppingDAO.selectAllMatarial(pagingMap);
		int totMatarial = shoppingDAO.selectToMatarial();
		matarialMap.put("matarialList", matarialList);
		matarialMap.put("totMatarial", totMatarial);
		return matarialMap;
	}
	
	public List<ShoppingVO> bestReview() {
		List<ShoppingVO> reviewList = shoppingDAO.slelectReview();
		return reviewList;
	}
}
