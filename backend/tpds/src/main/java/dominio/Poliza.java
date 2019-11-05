package dominio;

import java.util.ArrayList;
import java.util.Date;

import enumeradores.EstadoPoliza;

public class Poliza {
	private Integer idSucursal;
	private Integer numeroPoliza;
	private TipoCobertura cobertura;
	private Date inicioVigencia;
	private Date finVigencia;
	private Integer kmsAnuales;
	private EstadoPoliza estado;
	private Integer siniestros;
	private String dominio;
	private String chasis;
	private String motor;
	private Integer anioFabricacion;
	private Boolean seGuardaEnGarage;
	private Boolean poseeAlarma;
	private Boolean poseeRastreoVehicular;
	private Boolean poseeTuercasAntirrobo;
	
	private Localidad domicilioDeRiesgo;
	private Modelo modelo;
	private Cliente cliente;
	private ArrayList<Hijo> hijos;
	private FormaPago formaPago;
	private ArrayList<Pago> pagos;
	public Integer getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}
	public Integer getNumeroPoliza() {
		return numeroPoliza;
	}
	public void setNumeroPoliza(Integer numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}
	public TipoCobertura getCobertura() {
		return cobertura;
	}
	public void setCobertura(TipoCobertura cobertura) {
		this.cobertura = cobertura;
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
	public Integer getKmsAnuales() {
		return kmsAnuales;
	}
	public void setKmsAnuales(Integer kmsAnuales) {
		this.kmsAnuales = kmsAnuales;
	}
	public EstadoPoliza getEstado() {
		return estado;
	}
	public void setEstado(EstadoPoliza estado) {
		this.estado = estado;
	}
	public Integer getSiniestros() {
		return siniestros;
	}
	public void setSiniestros(Integer siniestros) {
		this.siniestros = siniestros;
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
	public Localidad getDomicilioDeRiesgo() {
		return domicilioDeRiesgo;
	}
	public void setDomicilioDeRiesgo(Localidad domicilioDeRiesgo) {
		this.domicilioDeRiesgo = domicilioDeRiesgo;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ArrayList<Hijo> getHijos() {
		return hijos;
	}
	public void setHijos(ArrayList<Hijo> hijos) {
		this.hijos = hijos;
	}
	public FormaPago getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}
	public ArrayList<Pago> getPagos() {
		return pagos;
	}
	public void setPagos(ArrayList<Pago> pagos) {
		this.pagos = pagos;
	}
}
