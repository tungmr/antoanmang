package com.example.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.example.dao.NewsDAO;
import com.example.model.NewsModel;

/**
 * Servlet implementation class AddNews
 */
@WebServlet("/AddNews")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 100)
public class AddNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "template\\images";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNews() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		NewsModel news = new NewsModel();

		news.setTitle(request.getParameter("title"));
		news.setDescription(request.getParameter("description"));
		news.setContent(request.getParameter("content"));
		news.setAuthor(request.getParameter("author"));
		news.setImage(uploadFile(request));

		if (NewsDAO.addNews(news)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/addnews.jsp?e=0");
			dispatcher.forward(request, response);

		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/addnews.jsp?e=1");
			dispatcher.forward(request, response);

		}

	}

	private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
		String fileName = "";
		try {
			Part filePart = request.getPart("image");

			// fileName: picture-001.jpg
			fileName = (String) getFileName(filePart);
			// applicationPath:
			String applicationPath = request.getServletContext().getRealPath("");
			System.out.println(applicationPath);
			// File.separator: \
			String basePath = applicationPath  + UPLOAD_DIR + File.separator;
			System.out.println(basePath);
			InputStream inputStream = null;
			OutputStream outputStream = null;
			OutputStream outputStream2 = null;
			String duongDan = "D:/An toàn mạng/awesomenews/WebContent/template/images/";
			try {
				File outputFilePath = new File(basePath + fileName);
				File outputFilePath2 = new File(duongDan + fileName);
				inputStream = filePart.getInputStream();
				outputStream = new FileOutputStream(outputFilePath);
				outputStream2 = new FileOutputStream(outputFilePath2);

				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
					outputStream2.write(bytes, 0, read);
				}
			} catch (Exception e) {
				e.printStackTrace();
				fileName = "";
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
					outputStream2.close();
				}
			}
		} catch (Exception e) {
			fileName = "";
		}
		return fileName;
	}

	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		// System.out.println("partHeader :" + partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
