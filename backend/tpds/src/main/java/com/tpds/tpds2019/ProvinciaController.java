package com.tpds.tpds2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import DTOs.ProvinciaDTO;
import dataAccess.HibernateUtil;
import dominio.Pais;
import dominio.Provincia;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProvinciaController {
	
	@GetMapping("/provincias/{idPais}")
	public List<ProvinciaDTO> getProvincias(@PathVariable("idPais") Integer idPais) throws HibernateException{
		Pais pais;
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			pais = session.get(Pais.class, idPais);
			tx.commit();

			Set<Provincia> provincias = pais.getProvincias();
			List<ProvinciaDTO> result = new ArrayList<ProvinciaDTO>();
			
			for (Provincia provincia : provincias) {
				result.add(provincia.getDTO());
			}
			return result;
		} catch (HibernateException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}
