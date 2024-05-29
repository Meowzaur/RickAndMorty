package dao;

import org.hibernate.Session;

import models.Usuario;

public class UsuarioDaoImpl extends CommonDaoImpl<Usuario> implements UsuarioDaoInt {

	/** Sesion de conexion a BD */
	private Session session;

	/**
	 * Constructor de la clase
	 * 
	 * @param session Session de la base de datos
	 */
	public UsuarioDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

}
