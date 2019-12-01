package dao;

import java.util.ArrayList;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dataTransferObjects.ParametrosDeBusqueda;
import dataTransferObjects.ParametrosDeConsulta;
import dominio.Cliente;
import dominio.NumeroCliente;

public class DaoCliente {
	public static Cliente getCliente(Integer idCliente) {
		Session session = HibernateUtil.openSession();
		Cliente cliente = session.get(Cliente.class, idCliente);
		return cliente;
	}

	public static void guardarCliente(Cliente c) {
		Session session = HibernateUtil.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.save(c.getDireccion());
			session.save(c);
			t.commit();
		} catch (ConstraintViolationException e) {
			t.rollback();
			throw e;
		}
	}

	public static ArrayList<Cliente> buscarClientes(ParametrosDeBusqueda p) {
		Session session = HibernateUtil.openSession();
		QueryBuilder qb = new QueryBuilder();
		Query query = qb.getQuery(p, session);

		try {
			// TODO: no se que errores puede lanzar esto
			query.setFirstResult((p.getNumeroPagina() - 1) * p.getResultadosPorPagina());
			query.setMaxResults(p.getResultadosPorPagina());

			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>(query.list());
			// TODO: Revisar esto
			if (listaClientes.size() == 0) {
				throw new NoResultException();
			}

			return listaClientes;
		} catch (NoResultException e) {
			throw e;
		}
	}

	public static Cliente buscarCliente(NumeroCliente n) {
		Session session = HibernateUtil.openSession();
		/*
		 * String hql =
		 * "FROM Cliente C WHERE C.nroCliente.idCliente = :idCliente AND C.nroCliente.idPais = :idPais"
		 * ; Query query = session.createQuery(hql); query.setParameter(":idCliente",
		 * n.getIdCliente()); query.setParameter(":idPais", n.getIdPais());
		 * System.out.println("HERE"); return (Cliente) query.uniqueResult();
		 */
		return session.get(Cliente.class, n);
	}

	public static ArrayList<Cliente> consultarClientes(ParametrosDeConsulta p) {
		Session session = HibernateUtil.openSession();
		QueryBuilder qb = new QueryBuilder();
		Query query = qb.getQuery(p, session);

		try {
			query.setFirstResult((p.getNumeroPagina() - 1) * p.getResultadosPorPagina());
			query.setMaxResults(p.getResultadosPorPagina());

			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>(query.list());
			// TODO: Revisar esto
			if (listaClientes.size() == 0) {
				throw new NoResultException();
			}

			return listaClientes;
		} catch (NoResultException e) {
			throw e;
		}
	}

}
