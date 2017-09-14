package org.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.model.Contact;
import org.web.serv.ContactService;
import org.web.util.CKUtil;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/view")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewServlet() {
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
				out.println("<html><head><title>View Contacts</title></head><body>");
				out.println("<a href='new'>Add New Contact</a>");
				out.println(" |  <a href='logout'>Logout</a>");
				out.println("<table width='1024px' border='1px'>");
				out.println("<tr><th>Sl.No</th><th>Id</th><th>Name</th><th>Number</th></tr>");
				List<Contact> lists = new ContactService().getAllContacts();
				if (lists == null || lists.size() < 1) {
					out.println("<tr><th colspan='3'>No Data Found</th></tr>");
				} else {
					int i = 1;
					for (Contact contact : lists) {
						out.println("<tr><td>" + i + "</td><td>" + contact.getId() + "</td><td>" + contact.getName()
								+ "</td><td>" + contact.getNumber() + "</td><td><a href='delete?cid=" + contact.getId()
								+ "'>remove</a> | <a href='load?cid="+contact.getId()+"'>edit</a></td></tr>");
						i++;
					}
				}
				out.println("</table>");
				out.println("</body></html>");
			}
		}
	}

}
