package org.web.serv;

import org.hibernate.Session;
import org.web.model.Users;
import org.web.util.HBUtil;

public class UsersService {
	public static Users get(String username) {
		Session session = HBUtil.get().openSession();
		Users users = session.get(Users.class, username);
		session.close();
		return users;
	}
	public static void insert(String username, String password, String name) {
		Session session = HBUtil.get().openSession();
		session.beginTransaction();
		Users users = new Users();
		users.setUsername(username);
		users.setName(name);
		users.setPassword(password);
		session.save(users);
		session.getTransaction().commit();
		session.close();
	}
}





