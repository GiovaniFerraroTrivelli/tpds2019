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
		BigDecimal importeOriginal = this.cuota.getImporte();
		BigDecimal descuentos = new BigDecimal("0");
		BigDecimal recargos = new BigDecimal("0");

		for (Descuento d : this.descuentos) {
			descuentos.add(importeOriginal.multiply(new BigDecimal(d.getFactor().toString())));
		}

		for (Recargo r : this.recargos) {
			recargos.add(importeOriginal.multiply(new BigDecimal(r.getFactor().toString())));
		}

		return importeOriginal.add(recargos).subtract(descuentos);
	}

	public CuotaDTO getDTO() {
		CuotaDTO result = this.cuota.getDTO();
		result.setDescuentos(this.descuentos);
		result.setRecargos(this.recargos);
		result.setImporteFinal(this.importeFinal().toString());
		return result;
	}
}
