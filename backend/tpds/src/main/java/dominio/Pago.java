package dominio;

import java.util.ArrayList;
import java.util.Date;

import usuarios.Usuario;

import org.javamoney.moneta.Money;

public class Pago {
	private Date fechaHora;
	private Usuario usuario;
	private Money premio;
	private Money importe;
	private Recibo recibo;
	private ArrayList<PagoCuota> cuotas;
	
	
}
