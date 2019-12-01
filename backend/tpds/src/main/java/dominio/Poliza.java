package dominio;

import java.util.Date;
import java.util.Set;

import enumeradores.EstadoPoliza;

public class Poliza {
	private Integer idPoliza;
	private NumeroPoliza nroPoliza;
	private TipoCobertura tipoCobertura;
	private Date inicioVigencia;
	private Date finVigencia;
	private Integer kmsAnuales;
	private EstadoPoliza estadoPoliza;
	private Integer siniestros;
	private String dominio;
	private String chasis;
	private String motor;
	private Integer anioFabricacion;
	private Modelo modelo;
	private Cliente cliente;
	private MedidasSeguridad medidasSeguridad;
	private Set<Hijo> hijos;
	private FormaPago formaPago;
	private Set<Cuota> cuotas;
	private Pais pais;

	public Poliza() {

	}

	public Integer getIdPoliza() {
		return idPoliza;
	}

	public void setIdPoliza(Integer idPoliza) {
		this.idPoliza = idPoliza;
	}

	public NumeroPoliza getNroPoliza() {
		return nroPoliza;
	}

	public void setNroPoliza(NumeroPoliza nroPoliza) {
		this.nroPoliza = nroPoliza;
	}

	public TipoCobertura getTipoCobertura() {
		return tipoCobertura;
	}

	public void setTipoCobertura(TipoCobertura cobertura) {
		this.tipoCobertura = cobertura;
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

	public EstadoPoliza getEstadoPoliza() {
		return estadoPoliza;
	}

	public void setEstadoPoliza(EstadoPoliza estadoPoliza) {
		this.estadoPoliza = estadoPoliza;
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

	public MedidasSeguridad getMedidasSeguridad() {
		return medidasSeguridad;
	}

	public void setMedidasSeguridad(MedidasSeguridad medidasSeguridad) {
		this.medidasSeguridad = medidasSeguridad;
	}

	public Set<Hijo> getHijos() {
		return hijos;
	}

	public void setHijos(Set<Hijo> hijos) {
		this.hijos = hijos;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public Set<Cuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(Set<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
