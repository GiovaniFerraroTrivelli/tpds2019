package com.tpds.tpds2019;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tpds2019Application {

	public static void main(String[] args) throws SQLException {
		/*String ip = "jdbc:mariadb://dbadmin.ngamers.net/tpds";
		String user = "tpds2019";
		String password = "qMxYFmazNR9QGmYG";
		
		Connection connection = DriverManager.getConnection(ip, user, password);
		Statement stmt = connection.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM tabla_prueba;");
		while (rs.next()) {
			System.out.println(rs.getString("name") + " " + rs.getInt("id"));
		}*/
		
		SpringApplication.run(Tpds2019Application.class, args);
	}

}
