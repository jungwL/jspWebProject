package jspFile.ex01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/download.do")
public class FileDownload extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String filePath="C:\\ljw\\file_upload";
		String fileName=(String)request.getParameter("fileName");
		OutputStream outS=response.getOutputStream();
		String downFile =filePath +"\\" + fileName;
		File file = new File(downFile);
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + fileName);
		FileInputStream finputS=new FileInputStream(file);
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count =finputS.read(buffer);
			if(count==-1) {
				break;
			}
			outS.write(buffer,0,count);
		}
		finputS.close();
		outS.close();
	}
}
