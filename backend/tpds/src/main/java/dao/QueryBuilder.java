package dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import dataTransferObjects.ParametrosDeBusqueda;
import dataTransferObjects.ParametrosDeConsulta;
import dominio.NumeroPoliza;
import enumeradores.CondicionCliente;
import dominio.Cliente.Documento;

public class QueryBuilder {

	public class Parametro {
		private String nombre;
		private Object valor;

		public Parametro(String nombre, Object valor) {
			this.nombre = nombre;
			this.valor = valor;
		}

		public String getNombre() {
			return nombre;
		}

		public Object getValor() {
			return valor;
		}
	}

	public QueryBuilder() {

	}

	public Query getQuery(ParametrosDeBusqueda p, Session s) {
		StringBuffer str = new StringBuffer();
		str.append("FROM Cliente C WHERE ");
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();

		if (!p.nroClienteNulo()) {
			str.append("C.nroCliente.idCliente = :idCliente AND ");
			parametros.add(new Parametro("idCliente", p.getIdCliente()));
			str.append("C.nroCliente.idPais = :idPais AND ");
			parametros.add(new Parametro("idPais", p.getIdPais()));
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

		String hql = str.toString().substring(0, str.toString().length() - 5);
		Query query = s.createQuery(hql);
		for (Parametro parametro : parametros) {
			query.setParameter(parametro.getNombre(), parametro.getValor());
		}

		return query;

	}

	public Query getQuery(ParametrosDeConsulta p, Session s) {
		StringBuffer str = new StringBuffer();
		str.append("FROM Cliente C WHERE ");
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();

		if (!p.nroClienteNulo()) {
			str.append("C.nroCliente.idCliente = :idCliente AND ");
			parametros.add(new Parametro("idCliente", p.getIdCliente()));
			str.append("C.nroCliente.idPais = :idPais AND ");
			parametros.add(new Parametro("idPais", p.getIdPais()));
		}

		if (p.getNombre() != null && p.getNombre() != "") {
			str.append("C.nombre LIKE :nombre AND ");
			parametros.add(new Parametro("nombre", p.getNombre() + "%"));
		}

		if (p.getApellido() != null && p.getApellido() != "") {
			str.append("C.apellido LIKE :apellido AND ");
			parametros.add(new Parametro("apellido", p.getApellido() + "%"));
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
		Query query = s.createQuery(hql);
		for (Parametro parametro : parametros) {
			query.setParameter(parametro.getNombre(), parametro.getValor());
		}

		return query;

	}

	public Query getQuery(NumeroPoliza n, Session s) {
		String hql = "FROM Poliza p WHERE p.nroPoliza.idSucursal = :idSucursal AND p.nroPoliza.autoCliente = :autoCliente AND p.nroPoliza.renovacionPoliza = :renovacionPoliza";
		Query query = s.createQuery(hql);
		query.setParameter("idSucursal", n.getIdSucursal());
		query.setParameter("autoCliente", n.getAutoCliente());
		query.setParameter("renovacionPoliza", n.getRenovacionPoliza());
		return query;
	}

}
