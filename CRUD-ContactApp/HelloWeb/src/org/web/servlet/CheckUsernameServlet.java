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
import org.web.util.CKUtil;

@WebServlet("/checkuser")
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckUsernameServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			String username = request.getParameter("uname");
			String password = request.getParameter("pass");
			response.setContentType("text/html");
			if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
				request.getRequestDispatcher("requiredinput.html").include(request, response);
			} else {
				Users res = UsersService.get(username);
				if (res == null) {
					out.println("<h1>Requested user not found</h1>");
					request.getRequestDispatcher("error.html").include(request, response);
				} else {
					if (res.getPassword().equals(password)) {
						CKUtil.create(response, "auth_user", username, 24 * 60 * 60);
						response.sendRedirect("view");
					} else {
						out.println("<h1>Login error invalid details</h1>");
						request.getRequestDispatcher("error.html").include(request, response);
					}
				}
			}
		}
	}
}
