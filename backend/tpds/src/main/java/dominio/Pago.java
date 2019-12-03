package dominio;

import java.util.Date;
import java.util.Set;

import usuarios.Usuario;

import org.javamoney.moneta.Money;

public class Pago implements Comparable<Pago> {
	private Integer idPago;
	private Date fechaHora;
	private Usuario usuario;
	private Poliza poliza;
	private Money premio;
	private Money importe;
	private Recibo recibo;
	private Set<PagoCuota> cuotas;

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

	public Money getPremio() {
		return premio;
	}

	public void setPremio(Money premio) {
		this.premio = premio;
	}

	public Money getImporte() {
		return importe;
	}

	public void setImporte(Money importe) {
		this.importe = importe;
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
		return pago;
	}

	public class PagoDTO {
		private Date fechaHora;
		private Money importe;

		public Date getFechaHora() {
			return fechaHora;
		}

		public void setFechaHora(Date fechaHora) {
			this.fechaHora = fechaHora;
		}

		public Money getImporte() {
			return importe;
		}

		public void setImporte(Money importe) {
			this.importe = importe;
		}

	}
	
	@Override
	public int compareTo(Pago p) {
		return this.fechaHora.compareTo(p.getFechaHora());
	}


}
