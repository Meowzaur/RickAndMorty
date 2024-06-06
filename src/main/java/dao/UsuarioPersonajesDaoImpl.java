package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import models.Personaje;
import models.UsuarioPersonajes;

public class UsuarioPersonajesDaoImpl extends CommonDaoImpl<UsuarioPersonajes> implements UsuarioPersonajesDaoInt {

	/** Sesion de conexion a BD */
	private Session session;

	/**
	 * Constructor de la clase
	 * 
	 * @param session Session de la base de datos
	 */
	public UsuarioPersonajesDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	/**
	 * Te devuelve la lista de personajes que el usuario haya dado como favorito.
	 * 
	 * @param nombre Nombre del usuario
	 */
	@Override
	public List<Personaje> personajesPorUsuario(String nombre) {
		if (!session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		return session.createQuery(
				"select p FROM personaje p INNER JOIN p.personajeOTM up WHERE up.id.usuario.nombre ='" + nombre + "'")
				.list();

	}

	/**
	 * Busca y elimina la relación de favoritismo entre el usuario y el personaje.
	 * 
	 * @param nombre Nombre del usuario
	 * @param id     Número identificador del personaje
	 */
	@Override
	public void eliminarPorIdPersonajeDeUsuario(String nombre, int id) {
		if (!session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
			session.getTransaction().begin();
		}

		UsuarioPersonajes up = (UsuarioPersonajes) session
				.createQuery("select up FROM usuario_personajes up WHERE up.id.usuario.nombre ='" + nombre
						+ "' AND up.id.personaje.id ='" + id + "'")
				.uniqueResult();

		session.delete(up);
		session.getTransaction().commit();

	}

}