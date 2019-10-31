package restControllers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dataAccess.HibernateUtil;

@SpringBootApplication
public class Inicializador {

	public static void main(String[] args){
		HibernateUtil.getSession();
		SpringApplication.run(Inicializador.class, args);
	}

}
