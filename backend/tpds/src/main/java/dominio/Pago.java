package dominio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import dominio.Cuota.CuotaDTO;
import usuarios.Usuario;


public class Pago implements Comparable<Pago> {
	private Integer idPago;
	private Date fechaHora;
	private Usuario usuario;
	private Poliza poliza;
	private BigDecimal premio;
	private BigDecimal importe;
	private Recibo recibo;
	private Set<PagoCuota> cuotas;

	public Pago() {
		super();
	}
	public Pago(Pago pago) {
		super();
		this.fechaHora = pago.getFechaHora();
		this.usuario = pago.getUsuario();
		this.poliza = pago.getPoliza();
		this.premio = pago.getPremio();
		this.importe = pago.getImporte();
		this.recibo = pago.getRecibo();
	}

	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}


	public Recibo getRecibo() {
		return recibo;
	}

	public void setRecibo(Recibo recibo) {
		this.recibo = recibo;
	}

	public Set<PagoCuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(Set<PagoCuota> cuotas) {
		this.cuotas = cuotas;
	}

	public PagoDTO getDTO() {
		PagoDTO pago = new PagoDTO();
		pago.setFechaHora(this.fechaHora);
		pago.setImporte(this.importe);
		ArrayList<CuotaDTO> cuotasPagadas = new ArrayList<>();
		for (PagoCuota pagoCuota : this.cuotas) {
			cuotasPagadas.add(pagoCuota.getCuota().getDTO());
		}
		pago.setIdsCuotasPagadas(cuotasPagadas);
		return pago;
	}

	public class PagoDTO {
		private Date fechaHora;
		private BigDecimal importe;
		private ArrayList<CuotaDTO> idsCuotasPagadas;

		public Date getFechaHora() {
			return fechaHora;
		}

		public void setFechaHora(Date fechaHora) {
			this.fechaHora = fechaHora;
		}

		public BigDecimal getImporte() {
			return importe;
		}

		public void setImporte(BigDecimal importe) {
			this.importe = importe;
		}

		public ArrayList<CuotaDTO> getIdsCuotasPagadas() {
			return idsCuotasPagadas;
		}

		public void setIdsCuotasPagadas(ArrayList<CuotaDTO> idsCuotasPagadas) {
			this.idsCuotasPagadas = idsCuotasPagadas;
		}
		
	}
	
	@Override
	public int compareTo(Pago p) {
		return this.fechaHora.compareTo(p.getFechaHora());
	}

	public BigDecimal getPremio() {
		return premio;
	}

	public void setPremio(BigDecimal premio) {
		this.premio = premio;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	

}
