package org.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.serv.UsersService;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/adduser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			String username = request.getParameter("uname");
			String password = request.getParameter("pass");
			String name = request.getParameter("name");
			if (username == null || username.isEmpty() || password == null || password.isEmpty() || name == null || name.isEmpty()) {
				out.println("username, password and name of users needed to provide this service");
			} else {
				UsersService.insert(username, password, name);
				out.println("User Registered. <a href='index.html'>Login</a> with new user here");
			}
		}
	}

}



