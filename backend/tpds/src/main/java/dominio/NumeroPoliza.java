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

	public NumeroPoliza() {
	}

	public NumeroPoliza(Integer idSucursal, Integer renovacionPoliza) {
		this.idSucursal = idSucursal;
		this.renovacionPoliza = renovacionPoliza;
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

	public String toString() {
		String idSucursal = Integer.toString(this.idSucursal);
		String autoCliente = Integer.toString(this.autoCliente);
		String renovacionPoliza = Integer.toString(this.renovacionPoliza);

		StringBuilder result = new StringBuilder();

		Integer ceros = 4 - idSucursal.length();
		for (int i = 0; i < ceros; i++) {
			result.append("0");
		}
		result.append(idSucursal);

		ceros = 7 - autoCliente.length();
		for (int i = 0; i < ceros; i++) {
			result.append("0");
		}
		result.append(autoCliente);

		ceros = 2 - renovacionPoliza.length();
		for (int i = 0; i < ceros; i++) {
			result.append("0");
		}
		result.append(renovacionPoliza);

		return new String(result);
	}
}
