package restControllers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dataAccess.HibernateUtil;

@SpringBootApplication
public class Inicializador {

	public static void main(String[] args){
		HibernateUtil.createSessionFactory();
		SpringApplication.run(Inicializador.class, args);
	}

}
