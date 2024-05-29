package resources;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.context.internal.ThreadLocalSessionContext;

public class HibernateUtil {

	// Atributos de la sesión
	private static SessionFactory sessionFactory;
	private static Session session;

	// Getter, si sessionFactory es null, inicia la sesión
	public static Session getSession() {
		if (sessionFactory == null) {
			session = getSessionFactory().openSession();
		}

		return session;
	}

	// Cierra la sesión, y también la sessionFactory
	public static void closeSession() {
		Session session = ThreadLocalSessionContext.unbind(sessionFactory);
		if (session != null) {
			session.close();
		}
		closeSessionFactory();
	}

	// Getter de sessionFactory, si es null, crea la sesión.
	private static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
			sessionFactory = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		}
		return sessionFactory;
	}

	// Cierra la sessionFactory
	private static void closeSessionFactory() {
		if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
			sessionFactory.close();
		}
	}
}
