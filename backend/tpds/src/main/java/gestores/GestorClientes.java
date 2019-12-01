package gestores;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dao.DaoCliente;
import dataAccess.HibernateUtil;
import dataTransferObjects.ClienteDTO;
import dataTransferObjects.ParametrosDeBusqueda;
import dataTransferObjects.ParametrosDeConsulta;
import dataTransferObjects.AltaClienteDTO;
import dominio.Cliente;
import dominio.Direccion;
import dominio.Documento;
import excepciones.DatoNoEncontradoException;
import excepciones.NoExisteClienteException;
import restControllers.Error;

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
		if (clienteDTO.getDireccion().getDepartamento() != null)
			direccion.setDepartamento(clienteDTO.getDireccion().getDepartamento());
		direccion.setNumero(clienteDTO.getDireccion().getNumero());
		if (clienteDTO.getDireccion().getPiso() != null)
			direccion.setPiso(clienteDTO.getDireccion().getPiso());
		try {
			direccion.setLocalidad(GestorGeografico.getLocalidad(clienteDTO.getDireccion().getIdLocalidad()));
		} catch (DatoNoEncontradoException e) {
			throw e;
		}

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
}
