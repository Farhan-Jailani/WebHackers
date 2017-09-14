package org.web.serv;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.web.model.Contact;
import org.web.util.HBUtil;

public class ContactService {
	private SessionFactory sf = HBUtil.get();
	public List<Contact> getAllContacts() {
		Session session = sf.openSession();
		Query query = session.createQuery("from Contact");
		List<Contact> all = query.getResultList();
		session.close();
		return all;
	}
	
	public Contact setContact(String name, Long number) {
		Session session = sf.openSession();
		session.beginTransaction();
		Contact contact = new Contact();
		contact.setName(name);
		contact.setNumber(number);
		session.save(contact);
		session.getTransaction().commit();
		session.close();
		return contact;
	}
	
	public Contact updateContact(Integer id, String name, Long number) {
		Session session = sf.openSession();
		session.beginTransaction();
		Contact contact = new Contact();
		contact.setId(id);
		contact.setName(name);
		contact.setNumber(number);
		session.update(contact);
		session.getTransaction().commit();
		session.close();
		return contact;
	}
	
	public boolean deleteContact(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Contact contact = new Contact();
		contact.setId(id);
		session.delete(contact);
		session.getTransaction().commit();
		session.close();
		return true;
	}
	
	public Contact getContact(Integer id) {
		Session session = sf.openSession();
		Contact contact = session.get(Contact.class, id);
		session.close();
		return contact;
	}
}
