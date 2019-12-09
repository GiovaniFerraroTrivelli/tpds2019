package dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import org.javamoney.moneta.Money;
import dataTransferObjects.TipoCoberturaDTO;
import dominio.Cuota.CuotaDTO;
import enumeradores.EstadoPoliza;
import enumeradores.FormaPago;
import excepciones.NoHayValorException;
import gestores.GestorPagos;
import usuarios.Usuario;

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
	private Localidad localidad;
	private MedidasSeguridad medidasSeguridad;
	private Set<Hijo> hijos;
	private FormaPago formaPago;
	private Set<Cuota> cuotas;
	private Set<Pago> pagos;
	private Pais pais;
	private BigDecimal sumaAsegurada;
	private BigDecimal prima;
	private BigDecimal premio;
	private Usuario usuario;
	private BigDecimal derechoEmision;

	

	public static class ResumenPoliza {
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
		private String derechoEmision;
		private String descuentos;
		private Date ultimoDiaPago;
		private String montoTotal;
		private String formaPago;
		private TipoCoberturaDTO tipoCobertura;
		private ArrayList<CuotaDTO> cuotas;

		public ResumenPoliza() {

		}

		public ResumenPoliza(String nombreTitular, String apellidoTitular, String marca, String modelo, String motor,
				String chasis, String patente, Date inicioVigencia, Date finVigencia, String sumaAsegurada,
				String premio, String descuentos, Date ultimoDiaPago, String montoTotal, ArrayList<CuotaDTO> cuotas) {
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
			this.ultimoDiaPago = ultimoDiaPago;
			this.montoTotal = montoTotal;
			this.cuotas = cuotas;
		}

		public ResumenPoliza(Poliza p) {

				this.nombreTitular = p.getCliente().getNombre();
				this.apellidoTitular = p.getCliente().getApellido();
				this.marca = p.getModelo().getMarca().getNombre();
				this.modelo = p.getModelo().getNombre();
				this.motor = p.getMotor();
				this.chasis = p.getChasis();
				this.patente = p.getDominio();
				this.inicioVigencia = p.getInicioVigencia();
				this.finVigencia = p.getFinVigencia();
				this.sumaAsegurada = p.getModelo().getSumaAsegurada(p.getAnioFabricacion()).toString();
				this.premio = p.getPremio().toString();
				this.derechoEmision = (p.getDerechoEmision().toString());
				try{
					this.ultimoDiaPago = GestorPagos.getUltimoPago(p).getFechaHora();
				} catch (NullPointerException e) {
					
				}
				this.montoTotal = p.getMontoTotal().toString();
				this.formaPago = p.getFormaPago().toString();
				try {
					this.tipoCobertura = p.getTipoCobertura().getDTO();
				} catch (NoHayValorException e) {
					
				}
				ArrayList<CuotaDTO> c = new ArrayList<CuotaDTO>();
				for (Cuota cuota : p.getCuotas()) {
					c.add(cuota.getDTO());
				}
				Collections.sort(c);
				this.cuotas = c;

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

		public String getDerechoEmision() {
			return derechoEmision;
		}

		public void setDerechoEmision(String derechoEmision) {
			this.derechoEmision = derechoEmision;
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

		public String getFormaPago() {
			return formaPago;
		}

		public void setFormaPago(String formaPago) {
			this.formaPago = formaPago;
		}

		public TipoCoberturaDTO getTipoCobertura() {
			return tipoCobertura;
		}

		public void setTipoCobertura(TipoCoberturaDTO tipoCobertura) {
			this.tipoCobertura = tipoCobertura;
		}

		public ArrayList<CuotaDTO> getCuotas() {
			return cuotas;
		}

		public void setCuotas(ArrayList<CuotaDTO> cuotas) {
			this.cuotas = cuotas;
		}
		

	}
	
	public static class PolizaDTO {
		private Integer idPoliza;
		private NumeroPoliza nroPoliza;
		private String nroCliente;
		private Integer modelo;
		private Integer anio;
		private String chasis;
		private String motor;
		private String patente;
		private Integer localidad;
		private Boolean seGuardaEnGarage;
		private Boolean poseeAlarma;
		private Boolean poseeRastreoVehicular;
		private Boolean poseeTuercasAntirrobo;
		private Money derechoEmision;
		private Integer siniestros;
		private ArrayList<Hijo> hijos;
		private Integer idCobertura;
		private String fechaVigencia;
		private Integer kmAnio;
		private String modalidadPago;
		private FormaPago formaPago;
		private ArrayList<CuotaDTO> cuotas;

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

		public ArrayList<CuotaDTO> getCuotas() {
			return cuotas;
		}

		public void setCuotas(ArrayList<CuotaDTO> cuotas) {
			this.cuotas = cuotas;
		}

		public String getNroCliente() {
			return nroCliente;
		}

		public void setNroCliente(String idCliente) {
			this.nroCliente = idCliente;
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

		public Money getDerechoEmision() {
			return derechoEmision;
		}

		public void setDerechoEmision(Money derechoEmision) {
			this.derechoEmision = derechoEmision;
		}

		public FormaPago getFormaPago() {
			return formaPago;
		}

		public void setFormaPago(FormaPago formaPago) {
			this.formaPago = formaPago;
		}

	}

	public Poliza() {

	}

	public BigDecimal getMontoTotal() {
		return this.getPrima().add(this.getDerechoEmision());
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

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public ResumenPoliza getResumenPoliza() {
		return new ResumenPoliza(this);
	}

	public Set<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(Set<Pago> pagos) {
		this.pagos = pagos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getSumaAsegurada() {
		return sumaAsegurada;
	}

	public void setSumaAsegurada(BigDecimal sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	public BigDecimal getPrima() {
		return prima;
	}

	public void setPrima(BigDecimal prima) {
		this.prima = prima;
	}

	public BigDecimal getPremio() {
		return premio;
	}

	public void setPremio(BigDecimal premio) {
		this.premio = premio;
	}

	public BigDecimal getDerechoEmision() {
		return derechoEmision;
	}

	public void setDerechoEmision(BigDecimal derechoEmision) {
		this.derechoEmision = derechoEmision;
	}

}
