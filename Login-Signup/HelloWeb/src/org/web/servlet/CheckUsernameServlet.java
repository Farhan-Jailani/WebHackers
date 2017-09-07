package org.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.model.Users;
import org.web.serv.UsersService;

/**
 * Servlet implementation class CheckUsernameServlet
 */
@WebServlet("/checkuser")
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckUsernameServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			String username = request.getParameter("uname");
			String password = request.getParameter("pass");
			if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
				out.println("username and password needed to provide this service");
			} else {
				Users res = UsersService.get(username);
				if (res == null) {
					out.println("username doesn't match with our records");
				} else {
					if (res.getPassword().equals(password)) {
						out.println("Login Succes");
					} else {
						out.println("Login Error");
					}
				}
			}
		}
	}
}