package org.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CKUtil {

	public static String get(HttpServletRequest request, String name) {
		String value = null;
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals(name)) {
					value = cookies[i].getValue();
					break;
				}
			}
		}
		return value;
	}

	public static void remove(HttpServletResponse response, String name) {
		CKUtil.create(response, name, "", 0);
	}

	public static void create(HttpServletResponse response, String name, String value, int expiry) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(expiry);
		response.addCookie(cookie);
	}
}







