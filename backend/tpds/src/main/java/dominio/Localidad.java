package dominio;

import DTOs.LocalidadDTO;
import dominio.Provincia;
import excepciones.NoHayValorException;

public class Localidad {
	private String nombre;
	private String CPA;
	private Provincia provincia;
	private HistorialValor<Float> factorRiesgo;
	
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String Nombre) {
		nombre =Nombre;
	}
	public String getCPA() {
		return CPA;
	}
	public void setCPA(String cPA) {
		CPA = cPA;
	}
	public HistorialValor<Float> getFactorRiesgo() {
		return factorRiesgo;
	}
	public void setFactorRiesgo(HistorialValor<Float> factorRiesgo) {
		this.factorRiesgo = factorRiesgo;
	}
	
	public LocalidadDTO getDTO() {
		LocalidadDTO result = new LocalidadDTO();
		result.setCPA(this.CPA);
		result.setNombre(this.nombre);
		result.setProvinciaId(this.provincia.getIdProvincia());
		return result;
	}
}
