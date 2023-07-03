package MVCproject.board;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.net.aso.p;

public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public BoardDAO() {
		try {
			// JNDI(Java Naming and Directory Interface)를 이용해서 DB를 연결
			// 필요한 자원을 키/값의 쌍으로 저장한 후 필요할 키를 이용해 값을 얻는 방법
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle"); // "읽어올 데이터베이스"
		} catch (Exception e) {
			System.out.println("DB연결 오류");
		}
	}
	
	//글 목록 페이징 처리하는 메서드
	public List<ArticleVO> selectAllArticles(Map<String, Integer> pagingMap) {
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		int section= pagingMap.get("section");
		int pageNum= pagingMap.get("pageNum");
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM(SELECT ROWNUM AS recNum, LVL, articleNo, parentNo, title, id, writeDate FROM (SELECT LEVEL "+
			"AS LVL, articleNo, parentNo,title, id, writeDate FROM boardtbl START WITH parentNo=0 CONNECT BY PRIOR articleNo=parentNo"+
			" ORDER SIBLINGS BY articleNo DESC)) WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10"; //where 다음 페이징 기법 1 AND 10(처음 10개만)2페이지로 넘어갈때 11 and 20 까지
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, section); //1페이지 일때 1 And 10 까지
			pstmt.setInt(2, pageNum); 
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("LVL");
				int articleNo = rs.getInt("articleNo");
				int parentNo = rs.getInt("parentNo");
				String title = rs.getString("title");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO articleVO = new ArticleVO();
				articleVO.setLevel(level);
				articleVO.setArticleNo(articleNo);
				articleVO.setParentNo(parentNo);
				articleVO.setTitle(title);
				articleVO.setId(id);
				articleVO.setWriteDate(writeDate);
				articlesList.add(articleVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 목록 페이징 조회에러");
			e.printStackTrace();
		}
		return articlesList;
	}
	//전체 글 목록갯수 메서드
	public int selectToArticles() {
		int totCount=0;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT count(*) from boardtbl ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				totCount=rs.getInt(1); //첫번째 컬럼에있는 값 (전체 자료수)
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("전체 글 목록 수 처리 중 에러");
			e.printStackTrace();
		}
		return totCount;
	}
	
	
	// 글 목록 조회 메서드
	/*public List<ArticleVO> selectAllArticles() {
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT LEVEL,articleNo,parentNo,title,content,id,writeDate FROM boardtbl START WITH parentNo=0 "
					+ "CONNECT BY PRIOR articleNo=parentNo ORDER SIBLINGS BY articleNo DESC";
			// start with절은 조건을 참조해서 최상위 계층 행을 선택
			// connect by prior 명시된 구문에 따라 계층형 관계(부모,자식)를 파악해 자식 row를 차례로 선택(최상위 행을 기준으로
			// 자식행을 선택하고 이 행의 또 다른 자식 행이
			// 있으면 선택하는 식으로 계속해서 조건에 맞는 row를 찾는 절)
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("level");
				int articleNo = rs.getInt("articleNo");
				int parentNo = rs.getInt("parentNo");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO articleVO = new ArticleVO();
				articleVO.setLevel(level);
				articleVO.setArticleNo(articleNo);
				articleVO.setParentNo(parentNo);
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setId(id);
				articleVO.setWriteDate(writeDate);
				articlesList.add(articleVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 목록 조회에러");
		}
		return articlesList;
	}*/

	// 글 번호 생성 메소드
	private int getNewArticleNo() {
		int _articleNo = 1;
		try {
			conn = dataFactory.getConnection();
			String query = "select max(articleNo) from boardtbl"; // 가장 큰 번호 조회 (현재 6)
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				_articleNo = rs.getInt(1) + 1; // 글번호를 7로
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글번호 생성 중 에러!!");
			e.printStackTrace();
		}
		return _articleNo;
	}

	// 새 글 추가 매소드
	public int insertNewArticle(ArticleVO articleVO) {
		int articleNo = getNewArticleNo(); // 위에서 받은 값
		try {
			conn = dataFactory.getConnection();
			int parentNo = articleVO.getParentNo();
			String title = articleVO.getTitle();
			String content = articleVO.getContent();
			String imageFileName = articleVO.getImageFileName();
			String id = articleVO.getId();
			String query = "insert into boardtbl (articleNo, parentNo, title,content,imageFileName,id) values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			pstmt.setInt(2, parentNo);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("새글 추가 중 에러");
			e.printStackTrace();
		}
		return articleNo;
	}

	// 선택한 글 상세 내용을 보여주는 메서드
	public ArticleVO selectArticle(int articleNo) {
		ArticleVO articleVO = new ArticleVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select articleNo,parentNo,title,content, NVL(imageFileName,'null') as imageFileName, id, writeDate from boardtbl "
					+ "where articleNo=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int _articleNo = rs.getInt("articleNo");
			int parentNo = rs.getInt("parentNo");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String imageFileName = URLEncoder.encode(rs.getString("imageFileName"), "utf-8");
			if (imageFileName.equals("null")) {
				imageFileName = null;
			}
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
			articleVO.setArticleNo(_articleNo);
			articleVO.setParentNo(parentNo);
			articleVO.setTitle(title);
			articleVO.setContent(content);
			articleVO.setImageFileName(imageFileName);
			articleVO.setId(id);
			articleVO.setWriteDate(writeDate);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 상세 구현 중 에러");
			e.printStackTrace();
		}
		return articleVO;
	}

	// 글 수정 메서드
	public void updateArticle(ArticleVO articleVO) {
		int articleNo = articleVO.getArticleNo();
		String title = articleVO.getTitle();
		String content = articleVO.getContent();
		String imageFileName = articleVO.getImageFileName();
		try {
			conn = dataFactory.getConnection();
			String query = "update boardtbl set title=? ,content=?";
			// 이미지가 있을 시
			if (imageFileName != null && imageFileName.length() != 0) {
				query += ", imageFileName=?";
			}
			query += " where articleNo=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			// 이미지가 있을 시 4번쨰에 세팅
			if (imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString(3, imageFileName);
				pstmt.setInt(4, articleNo);
			} else { // 이미지가 없을 시 3번째에다 세팅
				pstmt.setInt(3, articleNo);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("글 수정중 에러!!");
			e.printStackTrace();
		}
	}

	// 삭제될 articlNo 목록 가져올 메서드
	public List<Integer> selectRemovedArticles(int articleNo) {
			List<Integer> articleNoList = new ArrayList<Integer>();
			try {
				conn=dataFactory.getConnection();
				String query = "SELECT articleNo FROM boardtbl START WITH articleNo=? CONNECT BY PRIOR articleNo=parentNo";
				pstmt=conn.prepareStatement(query);
				pstmt.setInt(1, articleNo);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					articleNo=rs.getInt("articleNo");
					articleNoList.add(articleNo);
				}
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.println("삭제할 목록 글번호 가져온던 중 에러!!");
				e.printStackTrace();
			}
			return articleNoList;
		}
	//진짜 삭제 메서드
		public void deleteArticle(int articleNo) {
			try {
				conn = dataFactory.getConnection();
				String query = "DELETE FROM boardtbl WHERE articleNo in (SELECT articleNo FROM boardtbl START WITH articleNo=? CONNECT BY PRIOR articleNo=parentNo)";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, articleNo);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.println("글 삭제 중 에러!!");
				e.printStackTrace();
		}
	}
}