package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import models.Personaje;

public class PersonajeDaoImpl extends CommonDaoImpl<Personaje> implements PersonajeDaoInt {

	private Session session;

	public PersonajeDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public List<Personaje> searchByName(String name) {
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		return session.createQuery("FROM Personaje WHERE name='" + name + "'").list();
	}

	@Override
	public Personaje searchByID(int id) {
		if (!session.getTransaction().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		return (Personaje) session.createQuery("FROM Empleado WHERE id='" + id + "'").uniqueResult();
	}

}
