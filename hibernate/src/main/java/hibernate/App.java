package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
		
		Usuario u = new Usuario("Francisco", "Busso", 41490799);
		Usuario v = new Usuario("Francisco", "Busso", 43490799);
		System.out.println(u.getNombre());
		
        //session.save(u);
		
		try {
        session.save(v);
        tx.commit();
		} catch (ConstraintViolationException e) {
			System.out.println("NO PODES HACER ESTO, GILASTRUN");
		}

        session.close();
        HibernateUtil.shutdown();
        System.out.println("something");
	}

}
