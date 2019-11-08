package restControllers;

import java.util.ArrayList;
import java.util.Date;

import dataTransferObjects.CuotaDTO;
import dominio.Cuota;

public class ResumenPolizaDTO {
	private String nombreTitular;
	private String apellidoTitular;
	private String marca;
	private String modelo;
	private String motor;
	private String chasis;
	private String patente;
	private Date inicioVigencia;
	private Date finVigencia;
	private String sumaAsegurada;
	private String premio;
	private String descuentos;
	private Date ultimoDiaPago;
	private String montoTotal;
	private String FormaPago;
	private ArrayList<CuotaDTO> cuotas;
	
	public ResumenPolizaDTO() {
		
	}

	public ResumenPolizaDTO(String nombreTitular, String apellidoTitular, String marca, String modelo, String motor,
			String chasis, String patente, Date inicioVigencia, Date finVigencia, String sumaAsegurada, String premio,
			String descuentos, Date ultimoDiaPago, String montoTotal, ArrayList<CuotaDTO> cuotas) {
		super();
		this.nombreTitular = nombreTitular;
		this.apellidoTitular = apellidoTitular;
		this.marca = marca;
		this.modelo = modelo;
		this.motor = motor;
		this.chasis = chasis;
		this.patente = patente;
		this.inicioVigencia = inicioVigencia;
		this.finVigencia = finVigencia;
		this.sumaAsegurada = sumaAsegurada;
		this.premio = premio;
		this.descuentos = descuentos;
		this.ultimoDiaPago = ultimoDiaPago;
		this.montoTotal = montoTotal;
		this.cuotas = cuotas;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getApellidoTitular() {
		return apellidoTitular;
	}

	public void setApellidoTitular(String apellidoTitular) {
		this.apellidoTitular = apellidoTitular;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getChasis() {
		return chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Date getFinVigencia() {
		return finVigencia;
	}

	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}

	public String getSumaAsegurada() {
		return sumaAsegurada;
	}

	public void setSumaAsegurada(String sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	public String getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(String descuentos) {
		this.descuentos = descuentos;
	}

	public Date getUltimoDiaPago() {
		return ultimoDiaPago;
	}

	public void setUltimoDiaPago(Date ultimoDiaPago) {
		this.ultimoDiaPago = ultimoDiaPago;
	}

	public String getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}



	public ArrayList<CuotaDTO> getCuotas() {
		return cuotas;
	}

	public void setCuotas(ArrayList<CuotaDTO> cuotas) {
		this.cuotas = cuotas;
	}

	public String getFormaPago() {
		return FormaPago;
	}

	public void setFormaPago(String formaPago) {
		FormaPago = formaPago;
	}

}
