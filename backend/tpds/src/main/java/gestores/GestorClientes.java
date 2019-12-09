package gestores;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import dao.DaoCliente;
import dataTransferObjects.ParametrosDeBusqueda;
import dataTransferObjects.ParametrosDeConsulta;
import dataTransferObjects.AltaClienteDTO;
import dominio.Cliente;
import dominio.Cuota;
import dominio.Cliente.Documento;
import enumeradores.CondicionCliente;
import enumeradores.EstadoCuota;
import enumeradores.EstadoPoliza;
import dominio.Direccion;
import dominio.Poliza;
import excepciones.DatoNoEncontradoException;
import excepciones.NoExisteClienteException;

public class GestorClientes {

	public static Cliente getCliente(Integer idCliente) throws NoExisteClienteException {
		Cliente c = DaoCliente.getCliente(idCliente);
		if (c == null)
			throw new NoExisteClienteException();
		return c;
	}

	public static void guardarCliente(Cliente c) throws ConstraintViolationException {
		DaoCliente.guardarCliente(c);
	}

	public static ArrayList<Cliente> buscarClientes(ParametrosDeBusqueda p) {
		return DaoCliente.buscarClientes(p);
	}

	public static ArrayList<Cliente> consultarClientes(ParametrosDeConsulta p) {
		return DaoCliente.consultarClientes(p);
	}

	public static void altaCliente(AltaClienteDTO clienteDTO)
			throws DatoNoEncontradoException, java.sql.SQLIntegrityConstraintViolationException {
		Cliente cliente = new Cliente();
		Direccion direccion = new Direccion();

		direccion.setCalle(clienteDTO.getDireccion().getCalle());
		direccion.setCodigoPostal(clienteDTO.getDireccion().getCodigoPostal());
		if (clienteDTO.getDireccion().getDepartamento() != null) {
			direccion.setDepartamento(clienteDTO.getDireccion().getDepartamento());
		}

		direccion.setNumero(clienteDTO.getDireccion().getNumero());

		if (clienteDTO.getDireccion().getPiso() != null) {
			direccion.setPiso(clienteDTO.getDireccion().getPiso());
		}

		try {
			direccion.setLocalidad(GestorGeografico.getLocalidad(clienteDTO.getDireccion().getIdLocalidad()));
		} catch (DatoNoEncontradoException e) {
			throw e;
		}

		cliente.getNroCliente().setIdPais(direccion.getLocalidad().getProvincia().getPais().getIdPais());
		cliente.setDireccion(direccion);
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setApellido(clienteDTO.getApellido());

		Documento documento = new Documento(clienteDTO.getDocumento().getTipoDocumento(),
				clienteDTO.getDocumento().getNroDocumento());
		cliente.setDocumento(documento);
		cliente.setCuil(clienteDTO.getCuil());
		cliente.setSexo(clienteDTO.getSexo());
		cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
		cliente.setProfesion(clienteDTO.getProfesion());
		cliente.setEstadoCivil(clienteDTO.getEstadoCivil());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setCondicionIva(clienteDTO.getCondicionIva());

		try {
			GestorClientes.guardarCliente(cliente);
		} catch (ConstraintViolationException e) {
			throw e;
		}
	}

	public static Long resultados(ParametrosDeBusqueda parametros) {
		return DaoCliente.resultados(parametros);
	}
	

	public static void actualizarCondicionCliente(Cliente c) {
		if (clienteNormal(c)) {
			c.setCondicionCliente(CondicionCliente.Normal);
		}

		else if (clientePlata(c)) {
			c.setCondicionCliente(CondicionCliente.Plata);
		}
		
	}

	private static Boolean clienteNormal(Cliente c) {
		// Si la póliza es la primera que se le asocia al cliente, pasa a ser
		// considerado un cliente “Normal”
		if (c.getPolizas().size() == 1) {
			return true;
		}

		for (Poliza p : c.getPolizas()) {

			// Si el cliente poseía otras pólizas asociadas pero ninguna de ellas se
			// encuentra vigente el cliente debe considerarse “Normal”.
			if (p.getEstadoPoliza() != EstadoPoliza.VIGENTE) {
				return true;
			}

			// Si el cliente posee siniestros en el último año debe ser considerado un
			// cliente “Normal”.
			if (p.getSiniestros() != 0) {
				return true;
			}

			// Si el cliente posee alguna cuota impaga debe ser considerado un cliente
			// “Normal”.
			for (Cuota cuota : p.getCuotas()) {
				if (cuota.getEstadoCuota() == EstadoCuota.MORA) {
					return true;
				}
			}
		}

		return (!clienteActivo(c));
	}

	public static Boolean clientePlata(Cliente c) {
		if (clienteNormal(c)) {
			return false;
		}

		for (Poliza p : c.getPolizas()) {
			if (p.getSiniestros() != 0) {
				return false;
			}

			for (Cuota cuota : p.getCuotas()) {
				if (cuota.getEstadoCuota() == EstadoCuota.MORA) {
					return false;
				}
			}
		}

		return clienteActivo(c);
	}

	private static Boolean clienteActivo(Cliente c) {
		int i = 0;
		boolean flag = true;

		while (flag && i < 24) {
			boolean innerFlag = false;

			Date date = Date.from(ZonedDateTime.now().minusMonths(i).toInstant());
			for (Poliza p : c.getPolizas()) {
				if (p.getInicioVigencia().compareTo(date) * date.compareTo(p.getFinVigencia()) >= 0) {
					innerFlag = true;
				}
			}

			if (innerFlag) {
				i++;
			}

			else {
				flag = false;
			}
		}

		return flag;

	}
}
