package dominio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import enumeradores.EstadoCuota;

public class Cuota implements Comparable<Cuota> {
	private Integer idCuota;
	private Poliza poliza;
	private BigDecimal importe;
	private Date fechaVencimiento;
	private EstadoCuota estadoCuota;

	public Cuota() {

	}

	public Integer getIdCuota() {
		return idCuota;
	}

	public void setIdCuota(Integer idCuota) {
		this.idCuota = idCuota;
	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public CuotaDTO getDTO() {
		CuotaDTO cuotaDTO = new CuotaDTO();
		cuotaDTO.setFechaVencimiento(this.fechaVencimiento);
		cuotaDTO.setImporte(this.importe.toString());
		return cuotaDTO;
	}

	@Override
	public int compareTo(Cuota arg0) {
		this.fechaVencimiento.compareTo(arg0.getFechaVencimiento());
		return 0;
	}

	public EstadoCuota getEstadoCuota() {
		return estadoCuota;
	}

	public void setEstadoCuota(EstadoCuota estadoCuota) {
		this.estadoCuota = estadoCuota;
	}

	public class CuotaDTO implements Comparable<CuotaDTO> {
		private String importe;
		private Date fechaVencimiento;
		private Set<Descuento> descuentos;
		private Set<Recargo> recargos;

		public String getImporte() {
			return importe;
		}

		public void setImporte(String importe) {
			this.importe = importe;
		}

		public Date getFechaVencimiento() {
			return fechaVencimiento;
		}

		public void setFechaVencimiento(Date fechaVencimiento) {
			this.fechaVencimiento = fechaVencimiento;
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

		@Override
		public int compareTo(CuotaDTO o) {
			return this.fechaVencimiento.compareTo(o.getFechaVencimiento());
		}

	}

}
