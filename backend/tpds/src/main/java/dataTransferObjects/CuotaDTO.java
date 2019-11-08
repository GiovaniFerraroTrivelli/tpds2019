package dataTransferObjects;

import java.util.Date;

public class CuotaDTO implements Comparable<CuotaDTO>{
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
