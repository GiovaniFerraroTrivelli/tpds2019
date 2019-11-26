package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.PersistentObjectException;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.query.Query;
import org.springframework.util.ReflectionUtils;

import dataAccess.HibernateUtil;
import dataTransferObjects.ParametrosDeBusqueda;
import dataTransferObjects.ParametrosDeConsulta;
import dominio.Cliente;
import dominio.Documento;
import enumeradores.CondicionCliente;
import enumeradores.TipoDocumento;
import restControllers.Parametro;

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

	public static ArrayList<Cliente> buscarClientes(ParametrosDeBusqueda c) {
		Session session = HibernateUtil.openSession();
		StringBuffer str = new StringBuffer();
		str.append("FROM Cliente C WHERE ");
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();

		if (c.getIdCliente() != null) {
			str.append("C.idCliente = :idCliente AND ");
			parametros.add(new Parametro("idCliente", c.getIdCliente()));
		}

		if (c.getNombre() != null && c.getNombre() != "") {
			str.append("C.nombre LIKE :nombre AND ");
			parametros.add(new Parametro("nombre", "%" + c.getNombre() + "%"));
		}

		if (c.getApellido() != null && c.getApellido() != "") {
			str.append("C.apellido LIKE :apellido AND ");
			parametros.add(new Parametro("apellido", "%" + c.getApellido() + "%"));
		}

		if (c.getDocumento() != null) {
			if (c.getDocumento().getTipoDocumento() != null) {
				str.append("C.documento.tipoDocumento = :tipoDocumento AND ");
				parametros.add(new Parametro("tipoDocumento", c.getDocumento().getTipoDocumento()));
			}

			if (c.getDocumento().getNroDocumento() != null) {
				str.append("C.documento.nroDocumento = :nroDocumento AND ");
				parametros.add(new Parametro("nroDocumento", c.getDocumento().getNroDocumento()));
			}
		}

		String hql = str.toString().substring(0, str.toString().length() - 5);
		Query query = session.createQuery(hql);
		for (Parametro p : parametros) {
			query.setParameter(p.getNombre(), p.getValor());
		}

		try {
			// TODO: no se que errores puede lanzar esto
			query.setFirstResult((c.getNumeroPagina() - 1) * c.getResultadosPorPagina());
			query.setMaxResults(c.getResultadosPorPagina());

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

	public static ArrayList<Cliente> buscarClientes(ParametrosDeConsulta p) {
		Session session = HibernateUtil.openSession();
		StringBuffer str = new StringBuffer();
		str.append("FROM Cliente C WHERE ");
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();

		if (p.getIdCliente() != null) {
			str.append("C.idCliente = :idCliente AND ");
			parametros.add(new Parametro("idCliente", p.getIdCliente()));
		}

		if (p.getNombre() != null && p.getNombre() != "") {
			str.append("C.nombre LIKE :nombre AND ");
			parametros.add(new Parametro("nombre", "%" + p.getNombre() + "%"));
		}

		if (p.getApellido() != null && p.getApellido() != "") {
			str.append("C.apellido LIKE :apellido AND ");
			parametros.add(new Parametro("apellido", "%" + p.getApellido() + "%"));
		}

		if (p.getDocumento() != null) {
			if (p.getDocumento().getTipoDocumento() != null) {
				str.append("C.documento.tipoDocumento = :tipoDocumento AND ");
				parametros.add(new Parametro("tipoDocumento", p.getDocumento().getTipoDocumento()));
			}

			if (p.getDocumento().getNroDocumento() != null) {
				str.append("C.documento.nroDocumento = :nroDocumento AND ");
				parametros.add(new Parametro("nroDocumento", p.getDocumento().getNroDocumento()));
			}
		}

		if (p.getCondicionIva() != null) {
			str.append("C.condicionIva = :condicionIva AND ");
			parametros.add(new Parametro("condicionIva", p.getCondicionIva()));
		}

		str.append("C.condicionCliente = :condicionCliente AND ");
		parametros.add(new Parametro("condicionCliente", CondicionCliente.Activo));

		String hql = str.toString().substring(0, str.toString().length() - 5);
		Query query = session.createQuery(hql);
		for (Parametro parametro : parametros) {
			query.setParameter(parametro.getNombre(), parametro.getValor());
		}

		try {
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
