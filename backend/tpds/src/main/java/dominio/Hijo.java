package dominio;

import java.util.Date;

import enumeradores.EstadoCivil;
import enumeradores.Sexo;

public class Hijo {
	private Sexo sexo;
	private Date fechaNacimiento;
	private EstadoCivil estadoCivil;
	
	public Hijo() {
	}

	public Hijo(Sexo sexo, Date fechaNacimiento, EstadoCivil estadoCivil) {
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.estadoCivil = estadoCivil;
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
