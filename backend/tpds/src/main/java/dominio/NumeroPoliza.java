package dominio;

public class NumeroPoliza {
	private Integer idSucursal;
	private Integer autoCliente;
	private Integer renovacionPoliza;
	
	public NumeroPoliza(Integer idSucursal, Integer autoCliente, Integer renovacionPoliza) {
		this.idSucursal = idSucursal;
		this.autoCliente = autoCliente;
		this.renovacionPoliza = renovacionPoliza;
	}
	
	public NumeroPoliza(String numeroPoliza) {
		this.idSucursal = Integer.parseInt(numeroPoliza.substring(0, 4));
		this.autoCliente = Integer.parseInt(numeroPoliza.substring(4, 11));
		this.renovacionPoliza = Integer.parseInt(numeroPoliza.substring(11, 13));
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Integer getAutoCliente() {
		return autoCliente;
	}

	public void setAutoCliente(Integer autoCliente) {
		this.autoCliente = autoCliente;
	}

	public Integer getRenovacionPoliza() {
		return renovacionPoliza;
	}

	public void setRenovacionPoliza(Integer renovacionPoliza) {
		this.renovacionPoliza = renovacionPoliza;
	}
}
