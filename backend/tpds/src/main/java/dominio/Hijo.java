package dominio;

import java.util.Date;

import enumeradores.EstadoCivil;
import enumeradores.Sexo;

public class Hijo {
	private Integer idHijo;
	private Poliza poliza;
	private Sexo sexo;
	private Date fechaNacimiento;
	private EstadoCivil estadoCivil;

	public Hijo() {
	}

	public Hijo(Integer idHijo, Poliza poliza, Sexo sexo, Date fechaNacimiento, EstadoCivil estadoCivil) {
		this.idHijo = idHijo;
		this.poliza = poliza;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.estadoCivil = estadoCivil;
	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	public Integer getIdHijo() {
		return idHijo;
	}

	public void setIdHijo(Integer idHijo) {
		this.idHijo = idHijo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

}
