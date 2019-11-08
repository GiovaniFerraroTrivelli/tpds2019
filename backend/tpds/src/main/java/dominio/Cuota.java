package dominio;

import java.util.Date;

import dataTransferObjects.CuotaDTO;

public class Cuota {
	private Integer idCuota;
	private Poliza poliza;
	private Double importe;
	private Date fechaVencimiento;

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

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
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

}
