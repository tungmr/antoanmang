package com.example.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.UserDAO;
import com.example.model.UserModel;
import com.example.tools.Md5;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		HttpSession httpSession = request.getSession();
		UserModel user = (UserModel) httpSession.getAttribute("user");
		if (user != null) {
			if (user.getRole() == 0 && user.getRoleName().equals("user")) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/web/home.jsp");
				requestDispatcher.forward(request, response);
			} else {
				if (user.getRole() == 1 && user.getRoleName().equals("admin")) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/home.jsp");
					requestDispatcher.forward(request, response);
				}
			}

		} else {

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/login.jsp");
			requestDispatcher.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("username") != null && request.getParameter("password") != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String hashPassword = Md5.md5(password);

			if (UserDAO.checkUserLogin(username, hashPassword)) {
				UserModel user = UserDAO.getUser(username);
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("user", user);
				int role = user.getRole();
				String roleName = user.getRoleName();
				if (role == 0 && roleName.equals("user")) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/web/home.jsp");
					requestDispatcher.forward(request, response);
				} else if (role == 1 && roleName.equals("admin")) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/home.jsp");
					requestDispatcher.forward(request, response);
				}
			} else {

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/login.jsp?e=1");
				requestDispatcher.forward(request, response);
			}

		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/login.jsp?e=1");
			requestDispatcher.forward(request, response);
		}
	}

}
