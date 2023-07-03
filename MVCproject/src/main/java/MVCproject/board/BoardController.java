package MVCproject.board;

import java.io.File;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;



@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	BoardService boardService;
	ArticleVO articleVO;
	//글에첨부한 이미지 저장위치
	private static String ARTICLE_IMG_REPO="C:\\ljw\\board\\image_upload";

	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
			}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		HttpSession session;
		String action = request.getPathInfo(); // 요청명을 가져온다.
		System.out.println("요청이름 : " + action);
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null || action.equals("/listArticles.do")) {
				String _section =request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section = Integer.parseInt((_section==null)?"1":_section);
				int pageNum = Integer.parseInt((_pageNum==null)?"1":_pageNum);
				Map<String, Integer> pagingMap=new HashMap<String,Integer>();
				pagingMap.put("section", section); //1 값을 가지고있다
 				pagingMap.put("pageNum", pageNum); //1
 				Map articleMap=boardService.listArticles(pagingMap);
 				articleMap.put("section", section);
 				articleMap.put("pageNum", pageNum);
				request.setAttribute("articleMap", articleMap); //담은 articleMape
				nextPage = "/viewBoard/listArticles.jsp";
			} else if (action.equals("/articleForm.do")) {
				nextPage = "/viewBoard/articleForm.jsp";
			} else if (action.equals("/addArticle.do")) {
				int articleNo = 0;
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNo(0);
				articleVO.setId("rash");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleNo = boardService.addArticle(articleVO);
				// 새 글 추가시 이미지를 첨부한 경우에만 수행
				if (imageFileName != null && imageFileName.length() != 0) {
					File srcFile = new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);
					File destDir = new File(ARTICLE_IMG_REPO + "\\" + articleNo);
					destDir.mkdir(); // 폴더 만들어주는 메서드
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					srcFile.delete();
				}
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('새글을 추가했습니다.');");
				out.print("location.href='" + request.getContextPath() + "/board/listArticles.do';");
				out.print("</script>");
				return;
			} else if (action.equals("/viewArticle.do")) {
				String articleNo = request.getParameter("articleNo");
				articleVO = boardService.viewArticle(Integer.parseInt(articleNo)); // 숫자로 전달
				request.setAttribute("article", articleVO);
				nextPage = "/viewBoard/viewArticle.jsp";
			} else if (action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				int articleNo = Integer.parseInt(articleMap.get("articleNo"));
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setArticleNo(articleNo);
				articleVO.setParentNo(0);
				articleVO.setId("haalland");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.modArticle(articleVO);
				// 이미지를 새로 첨부한 경우에만 수행
				if (imageFileName != null && imageFileName.length() != 0) {
					String originalFileName = articleMap.get("originalFileName");
					File srcFile = new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);
					File destDir = new File(ARTICLE_IMG_REPO + "\\" + articleNo);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					File oldFile = new File(ARTICLE_IMG_REPO + "\\" + articleNo + "\\" + originalFileName);
					oldFile.delete(); // 기존 파일 삭제

				}
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('글을 수정했습니다.');");
				out.print("location.href='" + request.getContextPath() + "/board/viewArticle.do?articleNo=" + articleNo
						+ "';"); // 수정한 글정보
				out.print("</script>");
				return;
			}else if(action.equals("/removeArticle.do")) {
				int articleNo=Integer.parseInt(request.getParameter("articleNo"));
				List<Integer> articleNoList = boardService.removeArticle(articleNo);
				for(int _articleNo:articleNoList) {
					File imgDir=new File(ARTICLE_IMG_REPO + "\\" + articleNo);
					if(imgDir.exists()) { //이미지가 존재할 경우
						FileUtils.deleteDirectory(imgDir); //파일 삭제작업
					}
				}
				out = response.getWriter();
				out.print("<script>");
				out.print("alert('글을 삭제했습니다.');");
				out.print("location.href='" + request.getContextPath() + "/board/listArticles.do';");
				out.print("</script>");
				return;
			}else if(action.equals("/replyForm.do")) { //답글쓰기 폼구현
				String req=request.getParameter("parentNo");
				if(req == null) return;
				int parentNo=Integer.parseInt(req);
				session=request.getSession();
				session.setAttribute("parentNo", parentNo);
				nextPage="/viewBoard/replyForm.jsp";
			}else if(action.equals("/addReply.do")) {
	            session=request.getSession();
	            int parentNo=(Integer)session.getAttribute("parentNo");
	            session.removeAttribute("parentNo");
	            Map<String, String> articleMap=upload(request,response);
	            String title=articleMap.get("title");
	            String content=articleMap.get("content");
	            String imageFileName=articleMap.get("imageFileName");
	            articleVO.setParentNo(parentNo);
	            articleVO.setId("umm");
	            articleVO.setTitle(title);
	            articleVO.setContent(content);
	            articleVO.setImageFileName(imageFileName);
	            int articleNo=boardService.addReply(articleVO);
	            if(imageFileName !=null && imageFileName.length() !=0) {
	               File srcFile=new File(ARTICLE_IMG_REPO + "\\temp\\" + imageFileName);
	               File destDir=new File(ARTICLE_IMG_REPO + "\\" + articleNo);
	               destDir.mkdir();
	               FileUtils.moveToDirectory(srcFile, destDir, true);
	            }
	            out=response.getWriter();
	            out.print("<script>");
	            out.print("alert('답글 작성 완료');");
	            out.print("location.href='"+request.getContextPath()+"/board/viewArticle.do?articleNo=" + articleNo + "';");
	            out.print("</script>");
	            return;
	         }
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println("요청처리중 에러");
			e.printStackTrace();
		}
	}

	// 이미지 파일 업로드 + 새글 관련 정보가 저장
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		// 글 이미지 저장 폴더에 대한 객체 생성
		File currentDirPath = new File(ARTICLE_IMG_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString());
					fileItem.getString(encoding);
					// 파일 업로드 할 이미지와 같이 전송된 새글 관련(제목, 내용) 매개변수를 Map(키,값)으로 저장
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else { // 이미지
					System.out.println("매개변수 이름 : " + fileItem.getFieldName()); // imageFileName
					System.out.println("파일(이미지)이름 : " + fileItem.getName());
					System.out.println("파일(이미지)크기 :" + fileItem.getSize() + "bytes");
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\"); // 가져올 이미지 폴더 마지막
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						// DB에 저장
						String fileName = fileItem.getName().substring(idx + 1);
						articleMap.put(fileItem.getFieldName(), fileName);
						// 실제 업로드
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						// 실제 저장
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("파일 업로드중 에러!");
			e.printStackTrace();
		}
		return articleMap;
	}
}
