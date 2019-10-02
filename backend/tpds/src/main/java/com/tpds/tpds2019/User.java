package com.tpds.tpds2019;

import java.util.ArrayList;

public class User {
	private String name;
	private Integer id;
	private Integer pepitoxd;
	
	private static ArrayList<User> lista = new ArrayList<User>();
	
	User(Integer id, String nombre)
	{
		this.id = id;
		this.name = nombre;
		this.setPepitoxd(25);
	}
	
	public static ArrayList<User> getLista()
	{
		return lista;
	}
	
	public static void blankList()
	{
		lista.clear();
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

	public Integer getPepitoxd() {
		return pepitoxd;
	}

	public void setPepitoxd(Integer pepitoxd) {
		this.pepitoxd = pepitoxd;
	}
	
	
}
