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
			/*
			 * String users[] = request.getParameterValues("uname"); if (users
			 * != null) for (int i = 0; i < users.length; i++) { if
			 * (!users[i].isEmpty()) { out.println("<h1>Hai! " + users[i] +
			 * "</h1>"); } } else out.println("No User Passed");
			 */
			String username = request.getParameter("uname");
			// String password = request.getParameter("pass");
			if (username == null || username.isEmpty()) {
				out.println("username needed to provide this service");
			} else {
				Users res = UsersService.get(username);
				if (res == null) {
					out.println("username available for your use");
				} else {
					/*
					 * if(res.getPassword().equals(password)) { // Success }
					 * else { // Error Combination not found }
					 */
					out.println("Sorry. its already in use. better try with another name");
				}
			}
		}
	}
}