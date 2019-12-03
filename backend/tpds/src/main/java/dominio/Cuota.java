package dominio;

import java.math.BigDecimal;
import java.util.Date;

import org.javamoney.moneta.Money;


public class Cuota implements Comparable<Cuota>{
	private Integer idCuota;
	private Poliza poliza;
	private BigDecimal importe;
	private Date fechaVencimiento;
	

	public class CuotaDTO implements Comparable<CuotaDTO> {
		private String importe;
		private Date fechaVencimiento;
	
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
	
		@Override
		public int compareTo(CuotaDTO o) {
	
			return this.fechaVencimiento.compareTo(o.getFechaVencimiento());
		}
	
	}

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
		CuotaDTO c = new CuotaDTO();
		c.setFechaVencimiento(this.fechaVencimiento);
		c.setImporte(this.importe.toString());
		// TODO Auto-generated method stub
		return c;
	}

	@Override
	public int compareTo(Cuota arg0) {
		this.fechaVencimiento.compareTo(arg0.getFechaVencimiento());
		return 0;
	}

}
