package restControllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dominio.Cuota;
import enumeradores.Rol;
import gestores.GestorPagos;
import gestores.GestorPoliza;
import usuarios.Usuario;

public class ControladorPagos {



	@PostMapping("/consultarCostoTotal")
	public ResponseEntity<Object> altaPago(@RequestBody CuotasAPagar p, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null)
			return new ResponseEntity<>(new Error("No se encuentra autenticado en el sistema"), HttpStatus.FORBIDDEN);

		if (!(usuario.getRol() == Rol.Cobrador))
			return new ResponseEntity<>(new Error("No tiene permisos suficientes para realizar esta operación"),
					HttpStatus.FORBIDDEN);
		ArrayList<Cuota> cuotas = GestorPagos.getCuotas(GestorPoliza.getPoliza(p.getIdPoliza()));
		ArrayList<Cuota> cuotasAPagar = new ArrayList<>();
		for (Cuota c : cuotas) {
			for (Integer i : p.getIdsCuotasAPagar()) {
				if(c.getIdCuota() == i) cuotasAPagar.add(c);
			}
		}
		
		return null;
	}
	
	
	
	public static class CuotasAPagar {
		private Integer idPoliza;
		private ArrayList<Integer> idsCuotasAPagar;

		public ArrayList<Integer> getIdsCuotasAPagar() {
			return idsCuotasAPagar;
		}

		public void setIdsCuotasAPagar(ArrayList<Integer> idsCuotasAPagar) {
			this.idsCuotasAPagar = idsCuotasAPagar;
		}

		public Integer getIdPoliza() {
			return idPoliza;
		}

		public void setIdPoliza(Integer idPoliza) {
			this.idPoliza = idPoliza;
		}
	}
}
