package dataTransferObjects;

import java.util.ArrayList;

import dominio.Hijo;

public class PolizaDTO {
	private Integer idCliente;
	private Integer modelo;
	private Integer anio;
	private String dominio;
	private String chasis;
	private String motor;
	private String patente;
	private Integer localidad;
	private Boolean seGuardaEnGarage;
	private Boolean poseeAlarma;
	private Boolean poseeRastreoVehicular;
	private Boolean poseeTuercasAntirrobo;
	private Integer siniestros;
	private ArrayList<Hijo> hijos;
	private Integer idCobertura;
	private String fechaVigencia;
	private Integer kmAnio;
	private String modalidadPago;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
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

	public Integer getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Integer localidad) {
		this.localidad = localidad;
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

	public Integer getSiniestros() {
		return siniestros;
	}

	public void setSiniestros(Integer siniestros) {
		this.siniestros = siniestros;
	}

	public ArrayList<Hijo> getHijos() {
		return hijos;
	}

	public Integer getIdCobertura() {
		return idCobertura;
	}

	public void setIdCobertura(Integer idCobertura) {
		this.idCobertura = idCobertura;
	}

	public void setHijos(ArrayList<Hijo> hijos) {
		this.hijos = hijos;
	}

	public String getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(String fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public Integer getKmAnio() {
		return kmAnio;
	}

	public void setKmAnio(Integer kmAnio) {
		this.kmAnio = kmAnio;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getModalidadPago() {
		return modalidadPago;
	}

	public void setModalidadPago(String modalidadPago) {
		this.modalidadPago = modalidadPago;
	}

	

}
