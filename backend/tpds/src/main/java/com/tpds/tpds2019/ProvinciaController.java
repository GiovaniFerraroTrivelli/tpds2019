package com.tpds.tpds2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.GetMapping;

import DTOs.ProvinciaDTO;
import dataAccess.HibernateUtil;
import dominio.Pais;
import dominio.Provincia;

public class ProvinciaController {
	@GetMapping("/provincias")
	public List<ProvinciaDTO> getProvincias(Integer idPais){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Pais pais = session.get(Pais.class, idPais);
		Set<Provincia> provincias = pais.getProvincias();
		List<ProvinciaDTO> result = new ArrayList<ProvinciaDTO>();
		
		for (Provincia provincia : provincias) {
			result.add(provincia.getDTO());
		}
		return result;
	}
}
