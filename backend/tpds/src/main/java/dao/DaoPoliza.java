package dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dataAccess.HibernateUtil;
import dominio.NumeroPoliza;
import dominio.Poliza;
import dominio.TipoCobertura;

public class DaoPoliza {

	public static Session session = HibernateUtil.getSession();
	public static StatelessSession statelessSession = HibernateUtil.getStatelessSession();

	public static Poliza getPoliza(Integer nroPoliza) {
		// Session session = HibernateUtil.getSession();
		Poliza poliza = session.get(Poliza.class, nroPoliza);
		return poliza;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Poliza> buscarPoliza(String numeroPoliza) {
		// Session session = HibernateUtil.getSession();
		QueryBuilder qb = new QueryBuilder();
		Query<Poliza> query = qb.getQuery(new NumeroPoliza(numeroPoliza), session);
		ArrayList<Poliza> listaPolizas = new ArrayList<Poliza>(query.list());
		return listaPolizas;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Poliza> getPolizasVigentesOGeneradasConMotor(String motor) {
		// Session session = HibernateUtil.getSession();
		String hql = "FROM Poliza WHERE motor= :motor AND (estado_poliza='VIGENTE' OR estado_poliza='GENERADA')";
		ArrayList<Poliza> polizas = new ArrayList<>();
		try {
			Query<Poliza> query = statelessSession.createQuery(hql);
			query.setParameter("motor", motor);
			polizas.addAll(query.list());
		} catch (Exception e) {
		}
		return polizas;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Poliza> getPolizasVigentesOGeneradasConChasis(String chasis) {
		// Session session = HibernateUtil.getSession();
		String hql = "FROM Poliza WHERE chasis= :chasis AND (estado_poliza='VIGENTE' OR estado_poliza='GENERADA')";
		ArrayList<Poliza> polizas = new ArrayList<>();
		try {
			Query<Poliza> query = statelessSession.createQuery(hql);
			query.setParameter("chasis", chasis);
			polizas.addAll(query.list());
			return polizas;
		} catch (Exception e) {
		}
		return polizas;

	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Poliza> getAllPolizas() {
		ArrayList<Poliza> polizas = new ArrayList<>();
		try {
			Query<Poliza> query = statelessSession.createQuery("FROM Poliza");
			polizas.addAll(query.list());
		} catch (Exception e) {
		}
		return polizas;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Poliza> getPolizasVigentesOGeneradasConDominio(String dominio) {
		// Session session = HibernateUtil.getSession();
		String hql = "FROM Poliza WHERE dominio= :dominio AND (estado_poliza='VIGENTE' OR estado_poliza='GENERADA')";
		ArrayList<Poliza> polizas = new ArrayList<>();
		try {
			Query<Poliza> query = statelessSession.createQuery(hql);
			query.setParameter("dominio", dominio);
			polizas.addAll(query.list());
		} catch (Exception e) {
		}
		return polizas;
	}

	public static void save(Poliza p) {
		// Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		session.save(p);
		t.commit();
	}

	public static void update(Poliza p) {
		Transaction t = session.beginTransaction();
		session.update(p);
		t.commit();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<TipoCobertura> getCoberturas() {
		try {
			String hql = "FROM TipoCobertura ORDER BY nombre ASC";
			Query<TipoCobertura> query = session.createQuery(hql);
			return new ArrayList<TipoCobertura>(query.list());
		} catch (HibernateException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static TipoCobertura getCoberturaContraTerceros() {
		Query<TipoCobertura> query;
		TipoCobertura cobertura = null;
		try {
			String hql = "FROM TipoCobertura WHERE nombre='Responsabilidad Civil'";
			query = session.createQuery(hql);
			cobertura = query.getSingleResult();
		} catch (HibernateException e) {
		}
		return cobertura;
	}

	public static TipoCobertura getCobertura(Integer id) {

		TipoCobertura tipoCobertura = session.get(TipoCobertura.class, id);
		return tipoCobertura;
	}

	public static void actualizarPolizas(ArrayList<Poliza> polizas) {
		Transaction tx = session.beginTransaction();
		Integer count = 0;
		for (Poliza p : polizas) {
			session.update(p);
			count++;
			if (count == 1000) {
				count = 0;
				tx.commit();
			}
		}
		tx.commit();
	}
	
	public static void refresh(Poliza poliza) {
		try {
			if (poliza.getIdPoliza() != null) session.refresh(poliza);
		}catch (Exception e) {
			
		}
	}
}
