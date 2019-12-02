package dominio;


import java.util.Date;
import java.util.Set;

import usuarios.Usuario;

import org.javamoney.moneta.Money;

public class Pago {
	private Date fechaHora;
	private Usuario usuario;
	private Money premio;
	private Money importe;
	private Recibo recibo;
	private Set<PagoCuota> cuotas;
	
	
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
	public class PagoDTO{
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
	
}
