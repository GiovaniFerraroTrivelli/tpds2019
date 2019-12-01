package restControllers;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.DaoCliente;
import dataAccess.HibernateUtil;
import dataTransferObjects.ParametrosDeBusqueda;
import dominio.Cliente;

public class Test {

	public static void main(String[] args) {

		Session s = HibernateUtil.openSession();
		Transaction tx = s.beginTransaction();



		try { // Code here:
			ParametrosDeBusqueda p = new ParametrosDeBusqueda();
			p.setNumeroPagina(1);
			p.setResultadosPorPagina(1);
			p.setNroCliente("0100000001");
			
			for(Cliente c : DaoCliente.buscarClientes(p)) {
				System.out.println(c.getNombre());
			}
			
			

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			s.close();
			HibernateUtil.shutdown();
		}
		s.close();
		HibernateUtil.shutdown();
		System.out.println("DONE");

	}
}
