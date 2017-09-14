package org.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.serv.ContactService;
import org.web.util.CKUtil;

/**
 * Servlet implementation class DeleteSerclet
 */
@WebServlet("/delete")
public class DeleteSerclet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSerclet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String user = CKUtil.get(request, "auth_user");
		if (user == null || user.isEmpty()) {
			request.getRequestDispatcher("login.html").include(request, response);
		} else {
			Integer id = Integer.valueOf(request.getParameter("cid"));
			new ContactService().deleteContact(id);
			response.sendRedirect("view");
		}
	}

}
