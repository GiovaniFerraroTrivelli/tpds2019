package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.query.Query;
import org.springframework.util.ReflectionUtils;

import dataAccess.HibernateUtil;
import dataTransferObjects.ParametrosDeBusqueda;
import dominio.Cliente;
import dominio.Documento;
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
			throw e;
		}
	}

	public static ArrayList<Cliente> buscarCliente(ParametrosDeBusqueda c) {
		Session session = HibernateUtil.openSession();
		StringBuffer str = new StringBuffer();
		str.append("FROM Cliente C WHERE ");
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();

		if (c.getIdCliente() != null) {
			str.append("C.idCliente = :idCliente AND ");
			parametros.add(new Parametro("idCliente", c.getIdCliente()));
		}

		if (c.getNombre() != null) {
			str.append("C.nombre = :nombre AND ");
			parametros.add(new Parametro("nombre", c.getNombre()));
		}

		if (c.getApellido() != null) {
			str.append("C.apellido = :apellido AND ");
			parametros.add(new Parametro("apellido", c.getApellido()));
		}

		if (c.getDocumento() != null) {
			if (c.getDocumento().getTipoDocumento() != null) {
				str.append("C.documento.tipoDocumento = :tipoDocumento AND ");
				parametros.add(new Parametro("tipoDocumento", c.getDocumento().getTipoDocumento()));
			}

			if (c.getDocumento().getNroDocumento() != null) {
				str.append("C.documento.nroDocumento = :nroDocumento AND ");
				parametros.add(new Parametro("nro", c.getDocumento().getNroDocumento()));
			}
		}

		String hql = str.toString().substring(0, str.toString().length() - 5);
		Query query = session.createQuery(hql);
		for (Parametro p : parametros) {
			query.setParameter(p.getNombre(), p.getValor());
		}

		try {
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>(query.list());
			return listaClientes;

		} catch (NoResultException e) {
			System.out.println("NO SE ENCONTRO CLIENTE");
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
			return listaClientes;
		}
	}
}
