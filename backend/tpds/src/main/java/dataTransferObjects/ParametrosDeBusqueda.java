package dataTransferObjects;

import dominio.Documento;

public class ParametrosDeBusqueda {
	private Integer idCliente;
	private String nombre;
	private String apellido;
	private Documento documento;

	public ParametrosDeBusqueda(Integer idCliente, String nombre, String apellido, Documento documento) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
	}
	
	public ParametrosDeBusqueda(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

}
