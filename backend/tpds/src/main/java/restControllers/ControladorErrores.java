package restControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorErrores {
	
	@GetMapping("/error")
	public ResponseEntity<String> error(){
		return new ResponseEntity<>("Se detectó un error procesando la solicitud", HttpStatus.I_AM_A_TEAPOT);
	}
}
