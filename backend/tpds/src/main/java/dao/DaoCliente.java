package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dataAccess.HibernateUtil;
import dominio.Cliente;

public class DaoCliente {
	public static Cliente getCliente(Integer idCliente) {
		Session session = HibernateUtil.getSession();
		Cliente cliente = session.get(Cliente.class, idCliente);
		return cliente;
	}

	public static void save(Cliente c) {
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		session.save(c);
		t.commit();
	}
}
