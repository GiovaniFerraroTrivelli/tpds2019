package DTOs;

public class LocalidadDTO {
	private String CPA;
	private String nombre;
	private Integer provinciaId;
	
	public String getCPA() {
		return CPA;
	}
	public void setCPA(String cPA) {
		CPA = cPA;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getProvinciaId() {
		return provinciaId;
	}
	public void setProvinciaId(Integer provinciaId) {
		this.provinciaId = provinciaId;
	}
}
