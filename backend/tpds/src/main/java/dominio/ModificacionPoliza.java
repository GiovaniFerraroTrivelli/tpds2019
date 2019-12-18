package dominio;

import java.util.Date;

public class ModificacionPoliza {
	private Integer kmsAnaules;
	private Integer siniestros;
	private String chasis;
	private String motor;
	private Integer anioFabricacion;
	private Boolean seGuardaEnGarage;
	private Boolean poseeAlarma;
	private Boolean poseeRastreoVehicular;
	private Boolean poseeTuercasAntirrobo;
	private Date fecha;

	public ModificacionPoliza() {
		
	}

	public Integer getKmsAnaules() {
		return kmsAnaules;
	}

	public void setKmsAnaules(Integer kmsAnaules) {
		this.kmsAnaules = kmsAnaules;
	}

	public Integer getSiniestros() {
		return siniestros;
	}

	public void setSiniestros(Integer siniestros) {
		this.siniestros = siniestros;
	}

	public String getChasis() {
		return chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public Integer getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(Integer anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public Boolean getSeGuardaEnGarage() {
		return seGuardaEnGarage;
	}

	public void setSeGuardaEnGarage(Boolean seGuardaEnGarage) {
		this.seGuardaEnGarage = seGuardaEnGarage;
	}

	public Boolean getPoseeAlarma() {
		return poseeAlarma;
	}

	public void setPoseeAlarma(Boolean poseeAlarma) {
		this.poseeAlarma = poseeAlarma;
	}

	public Boolean getPoseeRastreoVehicular() {
		return poseeRastreoVehicular;
	}

	public void setPoseeRastreoVehicular(Boolean poseeRastreoVehicular) {
		this.poseeRastreoVehicular = poseeRastreoVehicular;
	}

	public Boolean getPoseeTuercasAntirrobo() {
		return poseeTuercasAntirrobo;
	}

	public void setPoseeTuercasAntirrobo(Boolean poseeTuercasAntirrobo) {
		this.poseeTuercasAntirrobo = poseeTuercasAntirrobo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
