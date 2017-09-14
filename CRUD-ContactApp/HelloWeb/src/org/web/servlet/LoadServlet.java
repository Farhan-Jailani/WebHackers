package org.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.model.Contact;
import org.web.serv.ContactService;
import org.web.util.CKUtil;

/**
 * Servlet implementation class LoadServlet
 */
@WebServlet("/load")
public class LoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String user = CKUtil.get(request, "auth_user");
		if (user == null || user.isEmpty()) {
			request.getRequestDispatcher("login.html").include(request, response);
		} else {
			try (PrintWriter out = response.getWriter()) {
				Integer id = Integer.valueOf(request.getParameter("cid"));
				Contact contact = new ContactService().getContact(id);
				if (contact == null) {
					out.println("<h1> No Data Found with id #" + id + "</h1>");
				} else {
					out.println("<a href='logout'>Logout</a>");
					out.println("<form action='update' method='post'>");
					out.println("<input type='hidden' name='cid' value = '"+contact.getId()+"'/>");
					out.println("<input type='text' name='cname' value = '"+contact.getName()+"'/>");
					out.println("<input type='text' name='cnumber' value = '"+contact.getNumber()+"'/>");
					out.println("<input type='submit' value = 'update'/>");
					out.println("</form>");
				}
			}
		}
	}

}
