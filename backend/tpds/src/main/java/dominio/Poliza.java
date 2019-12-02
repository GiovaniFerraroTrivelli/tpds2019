package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;

import dominio.Cuota.CuotaDTO;
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
	private Localidad localidad;
	private MedidasSeguridad medidasSeguridad;
	private Set<Hijo> hijos;
	private FormaPago formaPago;
	private Set<Cuota> cuotas;
	private Pais pais;
	private Money sumaAsegurada;
	private Money prima;
	private Money premio;

	private Money descuentos;

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
		private ArrayList<CuotaDTO> cuotas;

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

	public class ResumenPoliza {
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
			this.descuentos = descuentos;
			this.ultimoDiaPago = ultimoDiaPago;
			this.montoTotal = montoTotal;
			this.cuotas = cuotas;
		}

		public ResumenPoliza(Poliza p) {
			super();
			this.nombreTitular = p.getCliente().getNombre();
			this.apellidoTitular = p.getCliente().getApellido();
			this.marca = p.getModelo().getMarca().getNombre();
			this.modelo = p.getModelo().getNombre();
			this.motor = p.getMotor();
			this.chasis = p.getChasis();
			this.patente = p.getDominio();
			this.inicioVigencia = p.getInicioVigencia();
			this.finVigencia = p.getFinVigencia();
			this.sumaAsegurada = p.getSumaAsegurada().toString();
			this.premio = p.getPremio().toString();
			this.descuentos = p.getDescuentos().toString();
			//this.ultimoDiaPago = p.getUltimoDiaPago();
			this.montoTotal = p.getMontoTotal().toString();

			ArrayList<CuotaDTO> c = new ArrayList<>();
			for (Cuota cuota : p.getCuotas()) {
				c.add(cuota.getDTO());
			}
			Collections.sort(cuotas);

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

	public Poliza() {

	}

	public Money getMontoTotal() {
		
		return null;
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

	public Money getSumaAsegurada() {
		return sumaAsegurada;
	}

	public void setSumaAsegurada(Money sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	public Money getPrima() {
		return prima;
	}

	public void setPrima(Money prima) {
		this.prima = prima;
	}

	public Money getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(Money descuentos) {
		this.descuentos = descuentos;
	}

	public Money getPremio() {
		return premio;
	}

	public void setPremio(Money premio) {
		this.premio = premio;
	}

	public ResumenPoliza getResumenPoliza() {
		
		return new ResumenPoliza(this);
	}
}
