package dataAccess;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	private static final Session session;

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session openSession() throws HibernateException {
		try {
			return session;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void shutdown() {
		sessionFactory.close();
	}

	public static SessionFactory buildSessionFactory() {
		return sessionFactory;
	}
	
	public static Session getCurrentSession() throws HibernateException {
		try {
			return sessionFactory.getCurrentSession();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}