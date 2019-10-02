package com.tpds.tpds2019;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@GetMapping("/users")
	public List<User> getUsers() {
		User.blankList();

		User pepe = new User(1, "Capo");
		User bode = new User(2, "Bode");

		User.addToList(pepe);
		User.addToList(bode);

		return (List<User>) User.getLista();
	}

	@PostMapping("/login")
	public Boolean login(@RequestBody UserLogin userLogin) {
		System.out.println("lalala");
		return userLogin.getUsername().equals("giovi") && userLogin.getPassword().contentEquals("capo");
	}

	/*
	 * @PostMapping("/users") void addUser(@RequestBody User user) {
	 * userRepository.save(user); }
	 */
}