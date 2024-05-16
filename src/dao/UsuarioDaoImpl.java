package dao;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import models.Usuario;

public class UsuarioDaoImpl extends CommonDaoImpl<Usuario> implements UsuarioDaoInt {

	private Session session;

	public UsuarioDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public Usuario searchByNombre(String nombre) {
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		return (Usuario) session.createQuery("FROM usuario WHERE nombre='" + nombre + "'").uniqueResult();
	}

}
