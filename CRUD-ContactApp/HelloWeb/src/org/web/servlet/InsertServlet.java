package org.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.model.Contact;
import org.web.serv.ContactService;
import org.web.util.CKUtil;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/save")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String user = CKUtil.get(request, "auth_user");
		if (user == null || user.isEmpty()) {
			request.getRequestDispatcher("login.html").include(request, response);
		} else {
			String name = request.getParameter("cname");
			Long number = Long.valueOf(request.getParameter("cnumber"));
			Contact contact = new ContactService().setContact(name, number);
			response.sendRedirect("view");
		}
	}

}
