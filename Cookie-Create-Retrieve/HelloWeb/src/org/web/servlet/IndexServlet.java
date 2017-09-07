package org.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			/*Cookie cookie = new Cookie("dummyUser", "Guest");
			cookie.setMaxAge(24 * 60 * 60 * 1000);
			response.addCookie(cookie);*/
			out.println("Reading Cookie");
			Cookie cookies[] = request.getCookies();
			if(cookies != null) {
			    for(int i = 0; i < cookies.length; i++) {
			        out.println(cookies[i].getName() + ":" + cookies[i].getValue());
			    }
			}
		}
	}

	public IndexServlet() {
		super();
	}

}
