package dominio;

public class Descuento {
	public Integer idDescuento;
	public String concepto;
	public Double factor;

	public Descuento() {

	}

	public Descuento(Integer idDescuento, String concepto, Double factor) {
		super();
		this.idDescuento = idDescuento;
		this.concepto = concepto;
		this.factor = factor;
	}

	public Integer getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(Integer idDescuento) {
		this.idDescuento = idDescuento;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

}
