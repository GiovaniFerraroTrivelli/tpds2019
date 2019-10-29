package dominio;

public class Localidad {
	private String nombre;
	private String CPA;
	private HistorialValor<Float> factorRiesgo;
	
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
	
}
