package dominio;

public class Recargo {
	public Integer idRecargo;
	public String concepto;
	public Double factor;

	public Recargo() {

	}

	public Integer getIdRecargo() {
		return idRecargo;
	}

	public void setIdRecargo(Integer idRecargo) {
		this.idRecargo = idRecargo;
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
