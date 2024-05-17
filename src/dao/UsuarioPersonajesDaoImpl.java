package dao;

import org.hibernate.Session;

import models.UsuarioPersonajes;

public class UsuarioPersonajesDaoImpl extends CommonDaoImpl<UsuarioPersonajes> implements UsuarioPersonajesDaoInt {

	private Session session;

	public UsuarioPersonajesDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

}
