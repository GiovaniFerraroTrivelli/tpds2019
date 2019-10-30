package RestControllers;

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

import DTOs.LocalidadDTO;
import DTOs.ProvinciaDTO;
import Gestores.GestorGeografico;
import dataAccess.HibernateUtil;
import dominio.Localidad;
import dominio.Pais;
import dominio.Provincia;
import excepciones.DatoNoEncontradoException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorGeografico {
	
	@GetMapping("/provincias/{idPais}")
	public List<ProvinciaDTO> getProvincias(@PathVariable("idPais") Integer idPais) throws DatoNoEncontradoException{
		try {
			ArrayList<Provincia> provincias = GestorGeografico.getProvinciasDePais(idPais);
			ArrayList<ProvinciaDTO> result = new ArrayList<ProvinciaDTO>();
			for (Provincia provincia : provincias) result.add(provincia.getDTO());
			return result;
		} catch (DatoNoEncontradoException e) {
			throw e;
		}
		
	}
	
	@GetMapping("/provincia/{idProvincia}")
	public ProvinciaDTO getProvincia(@PathVariable("idProvincia") Integer idProvincia) throws DatoNoEncontradoException{
		try {
			return GestorGeografico.getProvincia(idProvincia).getDTO();
		} catch (DatoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	@GetMapping("/localidades/{idProvincia}")
	public ArrayList<LocalidadDTO> getLocalidadesDeProvincia(@PathVariable("idProvincia") Integer idProvincia) throws DatoNoEncontradoException{
		try {
			ArrayList<Localidad> localidades = GestorGeografico.getLocalidadesDeProvincia(idProvincia);
			ArrayList<LocalidadDTO> result = new ArrayList<LocalidadDTO>();
			for (Localidad localidad : localidades) result.add(localidad.getDTO());
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
}
