package dao;

import org.hibernate.Session;

import models.Personaje;

public class PersonajeDaoImpl extends CommonDaoImpl<Personaje> implements PersonajeDaoInt {

	/** Sesion de conexion a BD */
	private Session session;

	/**
	 * Constructor de la clase
	 * 
	 * @param session Session de la base de datos
	 */
	public PersonajeDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

}
