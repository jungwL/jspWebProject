package testRecipe.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ShoppingDAO {
   private Connection conn;
   private PreparedStatement pstmt;
   private DataSource dataFactory;
   
   public ShoppingDAO() {
      try {
         Context ctx = new InitialContext();
         Context envContext = (Context) ctx.lookup("java:/comp/env");
         dataFactory = (DataSource) envContext.lookup("jdbc/oracle"); // "읽어올 데이터베이스"
      } catch (Exception e) {
         System.out.println("DB연결 오류");
      }
   }   
   
   //재료 조회 메서드
   public List<ShoppingVO> selectAllMatarial (Map<String, Integer> pagingMap) {
      List<ShoppingVO> matarialList = new ArrayList<ShoppingVO>();
      int section = pagingMap.get("section");
      int pageNum = pagingMap.get("pageNum");
      try {
         conn = dataFactory.getConnection();
         String query = "SELECT ROWNUM AS recNum, prodCode, prodName, prodKind, prodPrice, prodImage, avgStar, reviewCnt FROM producttbl WHERE recNum BETWEEN (?-1)*100+(?-1)*10+1 AND (?-1)*100+?*10 AND prodKind=?";
         System.out.println(query);
         pstmt = conn.prepareStatement(query);
         pstmt.setInt(1, section);
         pstmt.setInt(2, pageNum);
         pstmt.setInt(3, section);
         pstmt.setInt(4, pageNum);
         pstmt.setString(5, "채소/");
         ResultSet rs = pstmt.executeQuery();
         while(rs.next ()) {
            int prodCode = rs.getInt("prodCode");
            String prodName = rs.getString("prodName");
            String prodKind = rs.getString("prodKind");
            int prodPrice = rs.getInt("prodPrice");
            String prodImage = rs.getString("prodImage");
            double avgStar = rs.getDouble("avgStar");
            int reviewCnt = rs.getInt("reviewCnt");
            ShoppingVO shoppingVO = new ShoppingVO();
            shoppingVO.setProdCode(prodCode);
            shoppingVO.setProdName(prodName);
            shoppingVO.setProdKind(prodKind);
            shoppingVO.setProdPrice(prodPrice);
            shoppingVO.setProdImage(prodImage);
            shoppingVO.setAvgStar(avgStar);
            shoppingVO.setReviewCnt(reviewCnt);
            matarialList.add(shoppingVO);
         }
         rs.close();
         pstmt.close();
         conn.close();
      }catch (Exception e) {
         System.out.println("글 목록 페이지 조회 중 에러");
         e.printStackTrace();
      }
      return matarialList;
   }
   
   //카테고리 재료 목록 수
   public int selectToMatarial() {
      int totCount = 0;
      try {
         conn = dataFactory.getConnection();
         String query = "select count(*) from producttbl where prodKind='채소/과일";
         pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) {
            totCount = rs.getInt(1);
         }
         pstmt.close();
         rs.close();
         conn.close();
      } catch (Exception e) {
         System.out.println("전체 글 목록 수 처리중 에러");
         e.printStackTrace();
      }
      return totCount;
   }
   
   // 별점 높은 순 글 목록 조회 메서드
   public List<ShoppingVO> slelectReview() {
      List<ShoppingVO> reviewList = new ArrayList<ShoppingVO>();
      try {
         conn = dataFactory.getConnection();
         String query = "SELECT prodCode, prodName, prodPrice, prodImage, avgStar, reviewCnt FROM producttbl ORDER BY avgStar DESC";
         pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery();
         while(rs.next ()) {
            int prodCode = rs.getInt("prodCode");
            String prodName = rs.getString("prodName");
            int prodPrice = rs.getInt("prodPrice");
            String prodImage = rs.getString("prodImage");
            double avgStar = rs.getDouble("avgStar");
            int reviewCnt = rs.getInt("reviewCnt");
            ShoppingVO shoppingVO = new ShoppingVO();
            shoppingVO.setProdCode(prodCode);
            shoppingVO.setProdName(prodName);
            shoppingVO.setProdPrice(prodPrice);
            shoppingVO.setProdImage(prodImage);
            shoppingVO.setAvgStar(avgStar);
            shoppingVO.setReviewCnt(reviewCnt);
            reviewList.add(shoppingVO);
         }
         rs.close();
         pstmt.close();
         conn.close();
      }catch (Exception e) {
         System.out.println("별점 높은 순 글 목록 조회 처리중 에러");
         e.printStackTrace();
      }
      return reviewList;
   }
   
   // 역대 베스트 순 글 목록 조회 메서드
   public List<ShoppingVO> slelectProdCount() {
      List<ShoppingVO> prodCountList = new ArrayList<ShoppingVO>();
      try {
         conn = dataFactory.getConnection();
         String query = "SELECT prodCode, prodName, prodPrice, prodImage, avgStar, reviewCnt, prodCount FROM producttbl ORDER BY prodCount DESC";
         pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery();
         while(rs.next ()) {
            int prodCode = rs.getInt("prodCode");
            String prodName = rs.getString("prodName");
            int prodPrice = rs.getInt("prodPrice");
            String prodImage = rs.getString("prodImage");
            double avgStar = rs.getDouble("avgStar");
            int reviewCnt = rs.getInt("reviewCnt");
            int prodCount = rs.getInt("prodCount");
            ShoppingVO shoppingVO = new ShoppingVO();
            shoppingVO.setProdCode(prodCode);
            shoppingVO.setProdName(prodName);
            shoppingVO.setProdPrice(prodPrice);
            shoppingVO.setProdImage(prodImage);
            shoppingVO.setAvgStar(avgStar);
            shoppingVO.setReviewCnt(reviewCnt);
            shoppingVO.setProdCount(prodCount);
            prodCountList.add(shoppingVO);
         }
         rs.close();
         pstmt.close();
         conn.close();
      }catch (Exception e) {
         System.out.println("역대 베스트 순 글 목록 조회 처리중 에러");
         e.printStackTrace();
      }
      return prodCountList;
   }
}