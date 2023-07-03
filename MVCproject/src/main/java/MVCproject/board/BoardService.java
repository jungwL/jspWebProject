package MVCproject.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO=new BoardDAO();
	}
	
	public Map listArticles(Map<String, Integer> pagingMap) {
		Map articleMap= new HashMap();
		List<ArticleVO> articlesList= boardDAO.selectAllArticles(pagingMap); //1페이면 1 부터 10까지만 가져온 데이터
		int totArticles=boardDAO.selectToArticles();
		articleMap.put("articlesList", articlesList); //글 내용
		//articleMap.put("totArticles", totArticles);
		articleMap.put("totArticles", 230);
		return articleMap;
	}
	/*public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList= boardDAO.selectAllArticles();
		return articlesList;
	}*/
	public int addArticle(ArticleVO articleVO) {
		return boardDAO.insertNewArticle(articleVO);
	}
	public ArticleVO viewArticle(int articleNo) {
		ArticleVO articleVO=null;
		articleVO=boardDAO.selectArticle(articleNo); //선택한 글정보
		return articleVO;
	}
	public void modArticle(ArticleVO articleVO) {
		boardDAO.updateArticle(articleVO);
	}
	public List<Integer> removeArticle(int articleNo){
		List<Integer> articleNoList=boardDAO.selectRemovedArticles(articleNo);//(delete문X):삭제될 글 들의 articleNo를 담는다.
		boardDAO.deleteArticle(articleNo); //담아온 articleNo를 진짜 삭제 메서드
		return articleNoList;
	}
	public int addReply(ArticleVO articleVO) {
		return boardDAO.insertNewArticle(articleVO);
	}
}
