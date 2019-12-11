package dataAccess;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final Lock lock = new ReentrantLock();

	private static final SessionFactory sessionFactory;
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unused")
	private static Session session;

	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session getSession() throws HibernateException {
		try {
			return sessionFactory.openSession();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static StatelessSession getStatelessSession() {
		try {
			return sessionFactory.openStatelessSession();
			
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

	public static Lock getLock() {
		return lock;
	}

}