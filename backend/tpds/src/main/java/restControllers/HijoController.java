package restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", exposedHeaders="Date")
public class HijoController {

	@GetMapping("/hijos")
	public List<Hijo> getHijos() {
		Hijo.blankList();

		Hijo hijoA = new Hijo(20, "Soltere", "Masculino");
		Hijo hijoB = new Hijo(23, "Casado", "Femenino");
		Hijo hijoC = new Hijo(22, "Soltero", "non-binary");

		Hijo.addToList(hijoA);
		Hijo.addToList(hijoB);
		Hijo.addToList(hijoC);
		
		return (List<Hijo>) Hijo.getLista();
	}
    /*
	@PostMapping("/login")
	public Boolean login(@RequestBody UserLogin userLogin) {
		System.out.println("lalala");
		return userLogin.getUsername().equals("giovi") && userLogin.getPassword().contentEquals("capo");
	}
    */
	/*
	 * @PostMapping("/users") void addUser(@RequestBody User user) {
	 * userRepository.save(user); }
	 */
}