package dominio;

import java.math.BigDecimal;
import java.util.Set;

import dominio.Cuota.CuotaDTO;

public class PagoCuota implements Comparable<PagoCuota> {
	private Integer idPagoCuota;
	private Pago pago;
	private Cuota cuota;
	private Set<Descuento> descuentos;
	private Set<Recargo> recargos;

	@Override
	public int compareTo(PagoCuota o) {
		return this.getCuota().compareTo(o.getCuota());
	}

	public Integer getIdPagoCuota() {
		return idPagoCuota;
	}

	public void setIdPagoCuota(Integer idPagoCuota) {
		this.idPagoCuota = idPagoCuota;
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

	public Set<Descuento> getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(Set<Descuento> descuentos) {
		this.descuentos = descuentos;
	}

	public Set<Recargo> getRecargos() {
		return recargos;
	}

	public void setRecargos(Set<Recargo> recargos) {
		this.recargos = recargos;
	}

	public BigDecimal importeOrigina() {
		return this.cuota.getImporte();
	}

	public BigDecimal importeFinal() {
		Double importeOriginal = this.cuota.getImporte().doubleValue();
		Double descuentos = 0.0;
		Double recargos = 0.0;

		for (Descuento d : this.descuentos) {
			descuentos += importeOriginal * d.getFactor();
		}

		for (Recargo r : this.recargos) {
			recargos += importeOriginal * r.getFactor();
		}

		Double importeFinal = importeOriginal - descuentos + recargos;
		return new BigDecimal(new Double(Math.floor(importeFinal * 100) / 100).toString());
	}

	public CuotaDTO getDTO() {
		CuotaDTO result = this.cuota.getDTO();
		result.setDescuentos(this.descuentos);
		result.setRecargos(this.recargos);
		result.setImporteFinal(this.importeFinal().toString());
		return result;
	}
}
