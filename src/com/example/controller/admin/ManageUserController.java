package com.example.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin-page/manage-user" })
public class ManageUserController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		RequestDispatcher requestDispatcher;
		try {
			requestDispatcher = req.getRequestDispatcher("/views/admin/manageuser.jsp");
			requestDispatcher.forward(req, resp);
		} catch (Exception e) {
			requestDispatcher = req.getRequestDispatcher("/views/admin/error.jsp");
			requestDispatcher.forward(req, resp);
		}
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
