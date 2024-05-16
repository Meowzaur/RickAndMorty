package utils;

import org.hibernate.Session;

import resources.HibernateUtil;

public class Sesion {

	private Session session;
	
	public void iniciarSesion() {
		session = HibernateUtil.getSession();
	}

	public Session getSession() {
		return session;
	}

}
