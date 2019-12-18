package dominio;

public class Recibo {
	private Integer numeroRecibo;
	private Pago pago;
	private Poliza poliza;
	private Cliente cliente;
	
	
	public Recibo(Pago pago, Poliza poliza, Cliente cliente) {
		super();
		this.pago = pago;
		this.poliza = poliza;
		this.cliente = cliente;
	}
	public Integer getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(Integer numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public Pago getPago() {
		return pago;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	public Poliza getPoliza() {
		return poliza;
	}
	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
