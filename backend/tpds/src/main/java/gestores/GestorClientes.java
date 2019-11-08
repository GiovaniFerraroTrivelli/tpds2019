package gestores;

import dao.DaoCliente;
import dominio.Cliente;
import excepciones.NoExisteClienteException;

public class GestorClientes {
	
	public static Cliente getCliente(Integer idCliente) throws NoExisteClienteException {
		Cliente c = DaoCliente.getCliente(idCliente);
		if (c == null) throw new NoExisteClienteException();
		return c;
	}
	
	public static void saveCliente(Cliente c) {
		DaoCliente.save(c);
	}

}
