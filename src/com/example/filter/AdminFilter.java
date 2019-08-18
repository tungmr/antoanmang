package com.example.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.UserModel;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/*")
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
   
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url = req.getRequestURI();
		if (url.startsWith("/AwesomeNews/views/admin/") || url.startsWith("/AwesomeNews/views/user/")) {
			HttpSession session = req.getSession();
			UserModel user = (UserModel) session.getAttribute("user");
			if (user!=null) {
				int role = user.getRole();
				String roleName = user.getRoleName();
				if (role == 1 && roleName.equals("admin")) {
					chain.doFilter(request, response);
				}else if (url.startsWith("/AwesomeNews/views/user/")) {
					chain.doFilter(request, response);

				}
			}else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/login.jsp");
				requestDispatcher.forward(request, response);
				
			}
		}else {
			chain.doFilter(request, response);

		}
		
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
