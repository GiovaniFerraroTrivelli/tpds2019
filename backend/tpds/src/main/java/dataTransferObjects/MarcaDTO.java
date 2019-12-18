package dataTransferObjects;

public class MarcaDTO {
	private Integer idMarca;
	private String nombre;
	
	public MarcaDTO(Integer idMarca2, String nombre2) {
		idMarca = idMarca2;
		nombre = nombre2;
	}
	public Integer getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
