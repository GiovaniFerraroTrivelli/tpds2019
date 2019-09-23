package com.tpds.tpds2019;

import java.util.ArrayList;

public class User {
	private String name;
	private Integer id;
	
	private static ArrayList<User> lista = new ArrayList<User>();
	
	User(Integer id, String nombre)
	{
		this.id = id;
		this.name = nombre;
	}
	
	public static ArrayList<User> getLista()
	{
		return lista;
	}
	
	public static void addToList(User user)
	{
		lista.add(user);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
