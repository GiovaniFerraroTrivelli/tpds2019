package dominio;

import org.javamoney.moneta.Money;

public class PagoCuota implements Comparable<PagoCuota> {
	private Pago pago;
	private Cuota cuota;
	private Money recargoMora;
	private Money bonificacionPagoAdelentado;
	
	@Override
	public int compareTo(PagoCuota o) {
		return this.getCuota().compareTo(o.getCuota());
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public Cuota getCuota() {
		return cuota;
	}

	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
	}

	public Money getRecargoMora() {
		return recargoMora;
	}

	public void setRecargoMora(Money recargoMora) {
		this.recargoMora = recargoMora;
	}

	public Money getBonificacionPagoAdelentado() {
		return bonificacionPagoAdelentado;
	}

	public void setBonificacionPagoAdelentado(Money bonificacionPagoAdelentado) {
		this.bonificacionPagoAdelentado = bonificacionPagoAdelentado;
	}	
}
