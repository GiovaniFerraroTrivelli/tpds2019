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
import enumeradores.CondicionCliente;
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

		if (c.getNroCliente() != null && c.getNroCliente().getIdCliente() != null && c.getNroCliente().getIdPais() != null) {
			str.append("C.nroCliente.idCliente = :idCliente AND ");
			parametros.add(new Parametro("idCliente", c.getNroCliente().getIdCliente()));
			str.append("C.nroCliente.idPais = :idPais AND ");
			parametros.add(new Parametro("idPais", c.getNroCliente().getIdPais()));
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
	
	public static Cliente buscarCliente(NumeroCliente n) {
		Session session  = HibernateUtil.openSession();
		/*String hql = "FROM Cliente C WHERE C.nroCliente.idCliente = :idCliente AND C.nroCliente.idPais = :idPais";
		Query query = session.createQuery(hql);
		query.setParameter(":idCliente", n.getIdCliente());
		query.setParameter(":idPais", n.getIdPais());
		System.out.println("HERE");
		return (Cliente) query.uniqueResult();*/
		return session.get(Cliente.class, n);
	}

	public static ArrayList<Cliente> consultarClientes(ParametrosDeConsulta p) {
		Session session = HibernateUtil.openSession();
		StringBuffer str = new StringBuffer();
		str.append("FROM Cliente C WHERE ");
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		
		if (p.getNroCliente() != null && p.getNroCliente().getIdCliente() != null && p.getNroCliente().getIdPais() != null) {
			str.append("C.nroCliente.idCliente = :idCliente AND ");
			parametros.add(new Parametro("idCliente", p.getNroCliente().getIdCliente()));
			str.append("C.nroCliente.idPais = :idPais AND ");
			parametros.add(new Parametro("idPais", p.getNroCliente().getIdPais()));
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
